package internationalization.translate.server.db;

import internationalization.translate.client.db.UiTextGroup;
import internationalization.translate.client.db.UiTextKey;
import internationalization.translate.client.db.UiTextTranslation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;

public class UiTextTranslationTable {
	public static final String UI_TEXT_TRANSLATION_TABLE = "Translations";
	
	public static void readInTranslations(InputStream file) {
        InputStreamReader stream=null;
        BufferedReader reader=null;
        DatastoreService datastore =
                DatastoreServiceFactory.getDatastoreService();
        
        
        
        try {
			stream=new InputStreamReader(file, "UTF-8");
			reader=new BufferedReader(stream);
			int count = 0;
			String[] tokens;
			String lang_key = null;
			
			while (true)
			{
				String line=reader.readLine().trim();
				
				++count;
				if (count > 1) {
					
				
					if (line==null)
					{
						break;
					}
	
					if (line.startsWith("#")) {
					
					} 
					else {
						tokens = line.split("\t");
						if (tokens.length == 3) {
							String textKey = tokens[0];
							Entity uiEntity = new Entity(UI_TEXT_TRANSLATION_TABLE);
							uiEntity.setProperty(UiTextTranslation.ATTRIBUTE_LANG_KEY, lang_key);
							uiEntity.setProperty(UiTextTranslation.ATTRIBUTE_KEY, textKey);
							Text uiText = new Text(tokens[1]);
							uiEntity.setProperty(UiTextTranslation.ATTRIBUTE_TEXT, uiText);
							uiEntity.setProperty(UiTextKey.ATTRIBUTE_STATUS, UiTextKey.STATUS_NEW);
					
					        datastore.put(uiEntity);
						}
					}
				}
				else { // the first line
					tokens = line.split("\t");
					
					if (tokens.length == 3) {
						lang_key = tokens[2].toLowerCase().replace(" ", "");
						Entity langEntity = UiTextGroup.newEntity(lang_key, tokens[2], tokens[2]);
						datastore.put(langEntity);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
