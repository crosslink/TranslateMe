package internationalization.translate.server;

import static gwtupload.shared.UConsts.PARAM_DELAY;
import static gwtupload.shared.UConsts.TAG_CANCELED;
import static gwtupload.shared.UConsts.TAG_ERROR;
import gwtupload.server.AbstractUploadListener;
import gwtupload.server.UploadAction;
import gwtupload.server.UploadServlet;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.server.exceptions.UploadCanceledException;
import gwtupload.server.exceptions.UploadException;
import gwtupload.server.exceptions.UploadSizeLimitException;
import gwtupload.server.exceptions.UploadTimeoutException;
import gwtupload.server.gae.AppEngineUploadAction;
import gwtupload.shared.UConsts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class GwtFileUploadServlet extends UploadAction {

	  private static final long serialVersionUID = 1L;
	  
	  Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();
	  /**
	   * Maintain a list with received files and their content types. 
	   */
	  Hashtable<String, InputStream> receivedFiles = new Hashtable<String, InputStream>();
	  ArrayList<InputStream> files = new ArrayList<InputStream>();

	private boolean removeSessionFiles;

	private boolean removeData;

	  /**
	   * Override executeAction to save the received files in a custom place
	   * and delete this items from session.  
	   */
	  @Override
	  public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
	    String response = "";
	    int cont = 0;
	    for (FileItem item : sessionFiles) {
	      if (false == item.isFormField()) {
	        cont ++;
	        try {
	          /// Create a new file based on the remote file name in the client
	          // String saveName = item.getName().replaceAll("[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");
	          // File file =new File("/tmp/" + saveName);
	          
	          /// Create a temporary file placed in /tmp (only works in unix)
	          // File file = File.createTempFile("upload-", ".bin", new File("/tmp"));
	          
	          /// Create a temporary file placed in the default system temp folder
//	          File file = File.createTempFile("upload-", ".bin");
//	          item.write(file);
	          InputStream file;
	          file = item.getInputStream();
	          /// Save a list with the received files
	          receivedFiles.put(item.getFieldName(), file);
	          receivedContentTypes.put(item.getFieldName(), item.getContentType());
	          
	          files.add(file);
	          
	          /// Send a customized message to the client.
//	          response += "File saved as " + file.getAbsolutePath();

	        } catch (Exception e) {
	          throw new UploadActionException(e);
	        }
	      }
	    }
	    
	    /// Remove files from session because we have a copy of them
	    removeSessionFileItems(request);
	    
	    /// Send your customized message to the client.
	    return response;
	  }
	  
	  /**
	   * Get the content of an uploaded file.
	   */
	  @Override
	  public void getUploadedFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String fieldName = request.getParameter(UConsts.PARAM_SHOW);
	    InputStream is = receivedFiles.get(fieldName);
	    if (is != null) {
	      response.setContentType(receivedContentTypes.get(fieldName));
//	      FileInputStream is = new FileInputStream(f);
	      copyFromInputStreamToOutputStream(is, response.getOutputStream());
	    } else {
	      renderXmlResponse(request, response, XML_ERROR_ITEM_NOT_FOUND);
	   }
	  }
	  
	  /**
	   * Remove a file when the user sends a delete request.
	   */
	  @Override
	  public void removeItem(HttpServletRequest request, String fieldName)  throws UploadActionException {
	    InputStream file = receivedFiles.get(fieldName);
	    receivedFiles.remove(fieldName);
	    receivedContentTypes.remove(fieldName);
//	    if (file != null) {
//	      file.delete();
//	    }
	  }

	  protected String parsePostRequest(HttpServletRequest request, HttpServletResponse response) {
		    try {
		      String delay = request.getParameter(PARAM_DELAY);
		      uploadDelay = Integer.parseInt(delay);
		    } catch (Exception e) { }

		    HttpSession session = request.getSession();

		    logger.debug("UPLOAD-SERVLET (" + session.getId() + ") new upload request received.");

		    AbstractUploadListener listener = getCurrentListener(request);
		    if (listener != null) {
		      if (listener.isFrozen() || listener.isCanceled() || listener.getPercent() >= 100) {
		        removeCurrentListener(request);
		      } else {
		        String error = getMessage("busy");
		        logger.error("UPLOAD-SERVLET (" + session.getId() + ") " + error);
		        return error;
		      }
		    }
		    // Create a file upload progress listener, and put it in the user session,
		    // so the browser can use ajax to query status of the upload process
		    listener = createNewListener(request);

		    List<FileItem> uploadedItems;
		    try {

		      // Call to a method which the user can override
		      checkRequest(request);

		      // Create the factory used for uploading files,
		      FileItemFactory factory = getFileItemFactory(request.getContentLength());
		      ServletFileUpload uploader = new ServletFileUpload(factory);
		      uploader.setSizeMax(maxSize);
		      uploader.setProgressListener(listener);

		      // Receive the files
		      logger.debug("UPLOAD-SERVLET (" + session.getId() + ") parsing HTTP POST request ");
		      uploadedItems = uploader.parseRequest(request);
		      logger.debug("UPLOAD-SERVLET (" + session.getId() + ") parsed request, " + uploadedItems.size() + " items received.");

		      // Received files are put in session
		      Vector<FileItem> sessionFiles = (Vector<FileItem>) getSessionFileItems(request);
		      if (sessionFiles == null) {
		        sessionFiles = new Vector<FileItem>();
		      }

		      String error = "";
		      session.setAttribute(SESSION_LAST_FILES, uploadedItems);

		      if (uploadedItems.size() > 0) {
		        sessionFiles.addAll(uploadedItems);
		        String msg = "";
		        for (FileItem i : sessionFiles) {
		          msg += i.getFieldName() + " => " + i.getName() + "(" + i.getSize() + " bytes),";
		        }
		        logger.debug("UPLOAD-SERVLET (" + session.getId() + ") puting items in session: " + msg);
		        session.setAttribute(SESSION_FILES, sessionFiles);
		      } else {
		        logger.error("UPLOAD-SERVLET (" + session.getId() + ") error NO DATA received ");
		        error += getMessage("no_data");
		      }

		      return error.length() > 0 ? error : null;

		    } catch (SizeLimitExceededException e) {
		      RuntimeException ex = new UploadSizeLimitException(e.getPermittedSize(), e.getActualSize());
		      listener.setException(ex);
		      throw ex;
		    } catch (UploadSizeLimitException e) {
		      listener.setException(e);
		      throw e;
		    } catch (UploadCanceledException e) {
		      listener.setException(e);
		      throw e;
		    } catch (UploadTimeoutException e) {
		      listener.setException(e);
		      throw e;
		    } catch (Exception e) {
		      logger.error("UPLOAD-SERVLET (" + request.getSession().getId() + ") Unexpected Exception -> " + e.getMessage() + "\n" + stackTraceToString(e));
		      e.printStackTrace();
		      RuntimeException ex = new UploadException(e);
		      listener.setException(ex);
		      throw ex;
		    }
		  }
	  
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	    String error = null;
	    String message = null;
        ServletFileUpload upload = new ServletFileUpload();

        try{
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();

                String name = item.getFieldName();
                InputStream inputStream = item.openStream();

                onFileUploadFinished(inputStream);
                // Process the input stream
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                int len;
//                byte[] buffer = new byte[8192];
//                while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
//                    out.write(buffer, 0, len);
//                }
//
//                int maxFileSize = 10*(1024*1024); //10 megs max 
//                if (out.size() > maxFileSize) { 
//                    throw new RuntimeException("File is > than " + maxFileSize);
//                }
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
	    
//	    perThreadRequest.set(request);
//	    try {
//	      // Receive the files and form elements, updating the progress status
//	      error = super.parsePostRequest(request, response);
//	      if (error == null) {
//	        // Call to the user code 
//	        message = executeAction(request, getSessionFileItems(request));
//	      }
//	    } catch (UploadCanceledException e) {
//	      renderXmlResponse(request, response, "<" + TAG_CANCELED + ">true</" + TAG_CANCELED + ">");
//	      return;
//	    } catch (UploadActionException e) {
//	      logger.info("ExecuteUploadActionException when receiving a file.", e);
//	      error =  e.getMessage();
//	    } catch (Exception e) {
//	      logger.info("Unknown Exception when receiving a file.", e);
//	      error = e.getMessage();
//	    } finally {
//	      perThreadRequest.set(null);
//	    }
//
//	    AbstractUploadListener listener = getCurrentListener(request);
//	    if (error != null) {
//	      renderXmlResponse(request, response, "<" + TAG_ERROR + ">" + error + "</" + TAG_ERROR + ">");
//	      if (listener != null) {
//	        listener.setException(new RuntimeException(error));
//	      }
//	      UploadServlet.removeSessionFileItems(request);
//	    } else {
//	      Map<String, String> stat = new HashMap<String, String>();
//	      getFileItemsSummary(request, stat);
//	      if (message != null) {
//	        stat.put("message", "\n<![CDATA[\n" + message + "\n]]>\n");
//	      }
//	      renderXmlResponse(request, response, statusToString(stat), true);
//	    }
	    
	    finish(request);
//	    onFileUploadFinished();
//	    if (removeSessionFiles) {
//	      removeSessionFileItems(request, removeData);
//	    }
	}
	
	public void onFileUploadFinished(InputStream inputStream) {
		
	}

	public void onFileUploadFinished() {
		
	}
}
