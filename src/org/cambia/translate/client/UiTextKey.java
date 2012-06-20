package org.cambia.translate.client;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;

public class UiTextKey {
	public final static int STATUS_LOCKED = 1;
	public final static int STATUS_NEW = 0;
	
	public final static String ATTRIBUTE_KEY = "key";
	public final static String ATTRIBUTE_TEXT = "text";
	public final static String ATTRIBUTE_STATUS = "status";
	
	private String key;
	private Text text;
	private int status;
	
	public UiTextKey(Entity e) {
		key = (String) e.getProperty(ATTRIBUTE_KEY);
		text = (Text)e.getProperty(ATTRIBUTE_TEXT);
		status = (Integer)e.getProperty(ATTRIBUTE_STATUS);
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public Text getText() {
		return text;
	}
	
	public void setText(Text text) {
		this.text = text;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
