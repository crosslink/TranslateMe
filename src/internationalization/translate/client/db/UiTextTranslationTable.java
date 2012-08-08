package internationalization.translate.client.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UiTextTranslationTable implements Serializable {
	
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

	public void assignText(UiTextKeyTable keyTable) {
		for (UiTextTranslation tran : translations) {
			String text = keyTable.getText(tran.getKey());
			tran.setKeyText(text);
		}
		
	}

	public int size() {
		return count();
	}

	public List<? extends UiTextTranslation> toList() {
		return translations;
	}
}
