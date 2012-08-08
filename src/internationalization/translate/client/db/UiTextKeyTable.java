package internationalization.translate.client.db;

import java.io.Serializable;
import java.util.HashMap;


public class UiTextKeyTable implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -788838018310647429L;
	public static final String UI_TEXT_KEY_TABLE = "UI_TEXT_KEYS";
	private HashMap<String, UiTextKey> keyMap;
	
	public UiTextKeyTable() {
		keyMap = new HashMap<String, UiTextKey>();
	}
	
	public void add(String key, UiTextKey text) {
		keyMap.put(key, text);
	}

	public String getText(String key) {
		String text = keyMap.get(key).getText();
		return text;
	}
}
