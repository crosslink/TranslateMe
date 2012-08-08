package internationalization.translate.client;

import java.io.InputStream;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("updatedb")
public interface FileUploadService extends RemoteService {
//	 public static class App {
//	        private static FileUploadServiceAsync instance = null;
//
//	        public static synchronized FileUploadServiceAsync getInstance() {
//	            if (instance == null) {
//	                instance = (FileUploadServiceAsync)
//	                		GWT.create(org.cambia.translate.client.FileUploadServiceAsync.class);
//	                String serverUrl =
//	                		"com.wellsfargo.ocs.portal.monitor.Go/GoService";
//	                if (GWT.isScript()) {
//	                    serverUrl = "GoService";
//	                }
//	                ((ServiceDefTarget)
//	                		instance).setServiceEntryPoint(GWT.getModuleBaseURL() + serverUrl);
//	            }
//	            return instance;
//	        }
//	    }
	 
		void onFileUploadFinished();
		void onFileUploadFinished(InputStream inputStream);
}
