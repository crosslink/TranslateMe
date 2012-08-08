package internationalization.translate.client.db;

import java.io.Serializable;

public class UiTextKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8017297879710993847L;
	
	public final static String ATTRIBUTE_KEY = "key";
	public final static String ATTRIBUTE_TEXT = "text";
	public final static String ATTRIBUTE_STATUS = "status";
	
	private String key;
	private String text;
	private int status;
	
	public UiTextKey() {
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
