package internationalization.translate.client.db;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;

public class UiTextGroup  implements Serializable {
	public static final String UI_TEXT_TRANSLATION_GROUP_TABLE = "Translations_Group";
	
	public final static String ATTRIBUTE_LANG_KEY = "lang_key";
	public final static String ATTRIBUTE_LANGUAGE = "language";
	public final static String ATTRIBUTE_COMMENT = "comment";
	
	private int id;
	private String name;
	private String comment;
	
	public static Entity newEntity(String lang_key, String language, String comment) {
		Entity langEntity = new Entity(UI_TEXT_TRANSLATION_GROUP_TABLE);
		langEntity.setProperty(ATTRIBUTE_LANG_KEY, lang_key);
		langEntity.setProperty(ATTRIBUTE_LANGUAGE, language);
		langEntity.setProperty(ATTRIBUTE_COMMENT, comment);
		return langEntity;
	}
	
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
