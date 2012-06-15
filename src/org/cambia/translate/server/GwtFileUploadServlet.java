package org.cambia.translate.server;

import static gwtupload.shared.UConsts.TAG_CANCELED;
import static gwtupload.shared.UConsts.TAG_ERROR;
import gwtupload.server.AbstractUploadListener;
import gwtupload.server.UploadAction;
import gwtupload.server.UploadServlet;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.server.exceptions.UploadCanceledException;
import gwtupload.server.gae.AppEngineUploadAction;
import gwtupload.shared.UConsts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class GwtFileUploadServlet extends AppEngineUploadAction/*UploadAction*/ {

	  private static final long serialVersionUID = 1L;
	  
	  Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();
	  /**
	   * Maintain a list with received files and their content types. 
	   */
	  Hashtable<String, File> receivedFiles = new Hashtable<String, File>();

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
	          File file = File.createTempFile("upload-", ".bin");
	          item.write(file);
	          
	          /// Save a list with the received files
	          receivedFiles.put(item.getFieldName(), file);
	          receivedContentTypes.put(item.getFieldName(), item.getContentType());
	          
	          /// Send a customized message to the client.
	          response += "File saved as " + file.getAbsolutePath();

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
	    File f = receivedFiles.get(fieldName);
	    if (f != null) {
	      response.setContentType(receivedContentTypes.get(fieldName));
	      FileInputStream is = new FileInputStream(f);
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
	    File file = receivedFiles.get(fieldName);
	    receivedFiles.remove(fieldName);
	    receivedContentTypes.remove(fieldName);
	    if (file != null) {
	      file.delete();
	    }
	  }

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	    String error = null;
	    String message = null;

	    perThreadRequest.set(request);
	    try {
	      // Receive the files and form elements, updating the progress status
//	      error = super.parsePostRequest(request, response);
	      if (error == null) {
	        // Call to the user code 
	        message = executeAction(request, getSessionFileItems(request));
	      }
	    } catch (UploadCanceledException e) {
	      renderXmlResponse(request, response, "<" + TAG_CANCELED + ">true</" + TAG_CANCELED + ">");
	      return;
	    } catch (UploadActionException e) {
	      logger.info("ExecuteUploadActionException when receiving a file.", e);
	      error =  e.getMessage();
	    } catch (Exception e) {
	      logger.info("Unknown Exception when receiving a file.", e);
	      error = e.getMessage();
	    } finally {
	      perThreadRequest.set(null);
	    }

	    AbstractUploadListener listener = getCurrentListener(request);
	    if (error != null) {
	      renderXmlResponse(request, response, "<" + TAG_ERROR + ">" + error + "</" + TAG_ERROR + ">");
	      if (listener != null) {
	        listener.setException(new RuntimeException(error));
	      }
	      UploadServlet.removeSessionFileItems(request);
	    } else {
	      Map<String, String> stat = new HashMap<String, String>();
	      getFileItemsSummary(request, stat);
	      if (message != null) {
	        stat.put("message", "\n<![CDATA[\n" + message + "\n]]>\n");
	      }
	      renderXmlResponse(request, response, statusToString(stat), true);
	    }
	    
	    finish(request);
//	    if (removeSessionFiles) {
//	      removeSessionFileItems(request, removeData);
//	    }
	}
}
