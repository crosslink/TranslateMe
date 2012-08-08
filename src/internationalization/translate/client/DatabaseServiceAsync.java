package internationalization.translate.client;

import internationalization.translate.client.db.UiTextKey;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatabaseServiceAsync {
	void clearKeys(AsyncCallback callback);
	void getUiTextKeys(AsyncCallback<UiTextKey[]> callback); 
}
