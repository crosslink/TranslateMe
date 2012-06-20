package internationalization.translate.server;

import internationalization.translate.client.FileUploadService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FileUploadServlet  extends RemoteServiceServlet implements FileUploadService {
	
	protected FileItem uploadedFileItem;
	
	@Override
    protected void service(final HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
 
	boolean isMultiPart = ServletFileUpload
			.isMultipartContent(new ServletRequestContext(request));
 
	if(isMultiPart) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
 
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			uploadedFileItem = items.get(0); // we only upload one file
 
			if(uploadedFileItem == null) {
				super.service(request, response);
				return;
			} else if(uploadedFileItem.getFieldName().equalsIgnoreCase(
					"uploadFormElement")) {
				String fileName = uploadedFileItem.getName();
				response.setStatus(HttpServletResponse.SC_CREATED);
				response.getWriter().print("OK");
				response.flushBuffer();
			}
 
		} catch(FileUploadException e) {
//			LOG.error(e);
		}
	}
 
	else {
		super.service(request, response);
		return;
	}
    }

	@Override
	public void onFileUploadFinished() {

		
	}

	@Override
	public void onFileUploadFinished(InputStream inputStream) {
		// TODO Auto-generated method stub
		
	}

}
