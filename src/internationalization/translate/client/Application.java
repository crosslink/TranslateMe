package internationalization.translate.client;

import internationalization.translate.client.ui.UI;
import internationalization.translate.client.ui.UiLayout;


public class Application {

	// UI
	private UI ui;
	private UiLayout uiLayout;


	// logic
	private Translate translator;
	
	
	// database
	
	
	// mode
	private static boolean debug = true;
	
	private static Application instance;
	
	public Application() {
		ui = new UI();
		uiLayout = new UiLayout(ui);
		translator = new Translate();
		
		ui.assignValues(translator.getListLangs());
		
//		debug = System.getProperty("debug").equalsIgnoreCase("true"); //Boolean.getBoolean("debug"); 
	}
	
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
	
	public static boolean isProductionMode() {
		return !debug;
	}

	public static boolean isDebugMode() {
		return debug;
	}
}
