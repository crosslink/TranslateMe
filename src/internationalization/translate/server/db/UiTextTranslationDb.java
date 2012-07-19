package internationalization.translate.server.db;

import internationalization.translate.client.UiTextKey;

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

public class UiTextTranslationDb {
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
						String tokens[]=line.split("\t");
						if (tokens.length == 3) {
							String textKey = tokens[0];
							Entity uiEntity = new Entity(UI_TEXT_TRANSLATION_TABLE);
							uiEntity.setProperty("key", textKey);
							Text uiText = new Text(tokens[1]);
							uiEntity.setProperty("text", uiText);
							uiEntity.setProperty(UiTextKey.ATTRIBUTE_STATUS, UiTextKey.STATUS_NEW);
					
		
					        datastore.put(uiEntity);
						}
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
