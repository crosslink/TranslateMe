package internationalization.translate.client.db;

import java.io.InputStream;
import java.io.Serializable;

public class UiTextTranslation  implements Serializable {

	public final static int STATUS_LOCKED = 1;
	public final static int STATUS_NEW = 0;
	
	
	public final static String ATTRIBUTE_LANG_KEY = "lang_key";
	public final static String ATTRIBUTE_KEY = "key";
	public final static String ATTRIBUTE_TEXT = "translation";
	public final static String ATTRIBUTE_LANGUAGE = "language";
	public final static String ATTRIBUTE_LANG_CODE = "language_code";
	public final static String ATTRIBUTE_STATUS = "status";
	
	private String langKey;
	private String key;
	private String text; // translation
	private int status;
	private String language;
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getLangKey() {
		return langKey;
	}
	
	public void setLangKey(String lang_key) {
		this.langKey = lang_key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
