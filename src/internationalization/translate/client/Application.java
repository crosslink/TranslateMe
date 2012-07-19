package internationalization.translate.client;

import internationalization.translate.client.ui.UI;
import internationalization.translate.client.ui.UiLayout;


public class Application {
	// UI
	private UI ui;
	private UiLayout uiLayout;


	// logic
	private Translate translator = new Translate();
	
	
	// database
	
	private static Application instance;
	
	public static Application getInstance() {
		if (instance == null)
			instance = new Application();
		return instance;
	}

	
	public UiLayout getUiLayout() {
		return uiLayout;
	}

	public void setUiLayout(UiLayout uiLayout) {
		this.uiLayout = uiLayout;
	}

	public Translate getTranslator() {
		return translator;
	}

	public void setTranslator(Translate translator) {
		this.translator = translator;
	}
}
