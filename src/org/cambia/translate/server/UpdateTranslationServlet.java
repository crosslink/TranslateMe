package org.cambia.translate.server;

import org.cambia.translate.client.FileUploadService;
import org.cambia.translate.utils.AcceptFileUploadServlet;

public class UpdateTranslationServlet extends GwtFileUploadServlet implements FileUploadService {

	@Override
	public void onFileUploadFinished() {
		
	}

//	@Override
//	public void onFileUploadFinished() {
//		super.onFileUploadFinished();
//		
//		
//	}

}
