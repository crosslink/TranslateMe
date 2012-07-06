package internationalization.translate.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class UiLayout {
	static Composite content;
	
	public static void setContent(Composite contentComposite) {
		content = contentComposite;
		RootPanel.get("content").add(content);
	}
	
	public static Composite getContent() {
		return content;
	}
}
