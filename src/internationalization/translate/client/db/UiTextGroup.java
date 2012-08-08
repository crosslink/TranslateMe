package internationalization.translate.client.db;

import java.io.Serializable;

public class UiTextGroup  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -328901886916319411L;
	public final static String ATTRIBUTE_LANG_KEY = "lang_key";
	public final static String ATTRIBUTE_LANGUAGE = "language";
	public final static String ATTRIBUTE_COMMENT = "comment";
	
	private int id;
	private String name;
	private String comment;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
