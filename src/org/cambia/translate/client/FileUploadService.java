package org.cambia.translate.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface FileUploadService extends RemoteService {
	 public static class App {
	        private static FileUploadServiceAsync instance = null;

	        public static synchronized FileUploadServiceAsync getInstance() {
	            if (instance == null) {
	                instance = (FileUploadServiceAsync)
	                		GWT.create(org.cambia.translate.client.FileUploadServiceAsync.class);
	                String serverUrl =
	                		"com.wellsfargo.ocs.portal.monitor.Go/GoService";
	                if (GWT.isScript()) {
	                    serverUrl = "GoService";
	                }
	                ((ServiceDefTarget)
	                		instance).setServiceEntryPoint(GWT.getModuleBaseURL() + serverUrl);
	            }
	            return instance;
	        }
	    }
	 
		void onFileUploadFinished();
}
