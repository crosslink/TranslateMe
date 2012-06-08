package org.cambia.translate.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface FileUploadService extends RemoteService {
		void onFileUploadFinished();
}
