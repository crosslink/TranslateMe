package internationalization.translate.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class UiLayout {
	static Composite content;
	private Composite mainWindow;
	private Composite adminWindow;
	private UI ui; 
	
	public UiLayout(UI ui) {
		this.ui = ui;
	}

	public static void setContent(Composite contentComposite) {
		RootPanel contentPanel = RootPanel.get("content");
		contentPanel.clear();
		content = contentComposite;
		contentPanel.add(content);
	}
	
	public static Composite getContent() {
		return content;
	}

	public void showMainWindow() {
		if (mainWindow == null) {
			mainWindow = new TranslateMe(ui);
		}
		
		setContent(mainWindow);
	}
	
	public void showAdminWindow() {
		if (adminWindow == null) {
			adminWindow = new Adminstration(ui);
		}
		
		setContent(adminWindow);
	}
}
