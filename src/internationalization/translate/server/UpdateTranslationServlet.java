package internationalization.translate.server;

import internationalization.translate.client.FileUploadService;
import internationalization.translate.utils.AcceptFileUploadServlet;

import java.io.InputStream;


public class UpdateTranslationServlet extends GwtFileUploadServlet implements FileUploadService {

	@Override
	public void onFileUploadFinished() {
		
	}

	@Override
	public void onFileUploadFinished(InputStream inputStream) {

		
	}

//	@Override
//	public void onFileUploadFinished() {
//		super.onFileUploadFinished();
//		
//		
//	}

}
