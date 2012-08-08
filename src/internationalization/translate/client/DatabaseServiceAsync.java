package internationalization.translate.client;

import internationalization.translate.client.db.UiTextKey;
import internationalization.translate.client.db.UiTextKeyTable;
import internationalization.translate.client.db.UiTextTranslationTable;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatabaseServiceAsync {
	void clearKeys(AsyncCallback callback);
	void getUiTextKeys(AsyncCallback<UiTextKeyTable> callback); 
	void getUiTextTranslationTable(String lang, AsyncCallback<UiTextTranslationTable> callback);
}
