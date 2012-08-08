package internationalization.translate.client.db;

import java.util.ArrayList;

public class UiTextTranslationTable {
	
	public static final String UI_TEXT_TRANSLATION_TABLE = "TRANSLATIONS";
	
	public ArrayList<UiTextTranslation> translations;
	
	public UiTextTranslationTable() {
		translations = new ArrayList<UiTextTranslation>();
	}
	
	public void add(UiTextTranslation tran) {
		translations.add(tran);
	}
	
	public String getKey(int index) {
		return translations.get(index).getKey();
	}

	public UiTextTranslation getTranslation(
			int index) {
		return translations.get(index);
	}

	public int count() {
		return translations.size();
	}
}
