package org.cambia.translate.server;

import java.io.InputStream;

import org.cambia.translate.client.FileUploadService;
import org.cambia.translate.utils.AcceptFileUploadServlet;

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
