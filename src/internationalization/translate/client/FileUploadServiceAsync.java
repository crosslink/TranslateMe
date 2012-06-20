package internationalization.translate.client;

import java.io.InputStream;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileUploadServiceAsync {
	void onFileUploadFinished(AsyncCallback callback);
	void onFileUploadFinished(InputStream inputStream, AsyncCallback callback);
}
