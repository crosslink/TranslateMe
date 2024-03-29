package internationalization.translate.utils;

import internationalization.translate.client.FileUploadService;

import java.io.IOException;
import java.io.InputStream; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.commons.fileupload.FileItemIterator; 
import org.apache.commons.fileupload.FileItemStream; 
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class AcceptFileUploadServlet extends HttpServlet implements FileUploadService {
	protected InputStream inputStream;
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        ServletFileUpload upload = new ServletFileUpload();

        try{
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();

                String name = item.getFieldName();
                InputStream inputStream = item.openStream();

                onFileUploadFinished();
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

    }

	public void onFileUploadFinished() {

		
	}

	@Override
	public void onFileUploadFinished(InputStream inputStream) {
		// TODO Auto-generated method stub
		
	}
}