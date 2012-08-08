package internationalization.translate.server.db;

import internationalization.translate.client.db.UiTextGroup;
import internationalization.translate.client.db.UiTextKey;
import internationalization.translate.client.db.UiTextTranslation;
import internationalization.translate.client.db.UiTextTranslationTable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;

public class UiTextTranslationTableImpl {
	
//	public UiTextTranslationTableImpl(String langKey) {
//		load(langKey);
//	}

	public static UiTextTranslationTable load(String lang_key) {
		UiTextTranslationTable table = new UiTextTranslationTable();
        DatastoreService datastore =
                DatastoreServiceFactory.getDatastoreService();
        Query q = new Query(UiTextTranslationTable.UI_TEXT_TRANSLATION_TABLE);
        q.addFilter(UiTextTranslation.ATTRIBUTE_LANG_KEY, Query.FilterOperator.EQUAL, lang_key);

        PreparedQuery pq = datastore.prepare(q);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withDefaults());

       int count = 0;
        for(Entity e : entities) {
        	UiTextTranslation tran = new UiTextTranslation();
        	tran.setLangKey((String) e.getProperty(UiTextTranslation.ATTRIBUTE_LANG_KEY));
        	
        	String key = (String) e.getProperty(UiTextTranslation.ATTRIBUTE_KEY);
        	tran.setKey(key);
        	Text text = (Text) e.getProperty(UiTextTranslation.ATTRIBUTE_TEXT);
    		tran.setText(text.getValue());
    		tran.setLanguage((String) e.getProperty(UiTextTranslation.ATTRIBUTE_LANGUAGE));
    		table.add(tran);
        }
        return table;
	}
	
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
				String line=reader.readLine();
				
				++count;
				if (count > 1) {
					
				
					if (line==null)
					{
						break;
					}
					line.trim();
					if (line.startsWith("#")) {
					
					} 
					else {
						tokens = line.split("\t");
						if (tokens.length == 3) {
							String textKey = tokens[0];
							Entity uiEntity = new Entity(UiTextTranslationTable.UI_TEXT_TRANSLATION_TABLE);
							uiEntity.setProperty(UiTextTranslation.ATTRIBUTE_LANG_KEY, lang_key);
							uiEntity.setProperty(UiTextTranslation.ATTRIBUTE_KEY, textKey);
							Text uiText = new Text(tokens[1]);
							uiEntity.setProperty(UiTextTranslation.ATTRIBUTE_TEXT, uiText);
							uiEntity.setProperty(UiTextTranslation.ATTRIBUTE_STATUS, UiTextTranslation.STATUS_NEW);
					
					        datastore.put(uiEntity);
						}
					}
				}
				else { // the first line
					tokens = line.split("\t");
					
					if (tokens.length == 3) {
						UiTextGroupTableImpl.checkLanguage(tokens[2], tokens[2]);
//						lang_key = tokens[2].toLowerCase().replace(" ", "");
//						Entity langEntity = newEntity(lang_key, tokens[2], tokens[2]);
//						datastore.put(langEntity);
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
