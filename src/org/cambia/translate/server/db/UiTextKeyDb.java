package org.cambia.translate.server.db;

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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;

public class UiTextKeyDb {
	
	public static final String UI_TEXT_KEY_TABLE = "Translate-Key";
	
	static public void deleteKeys() {
        DatastoreService datastore =
                DatastoreServiceFactory.getDatastoreService();
        
//        datastore.
        
        Query q = new Query(UI_TEXT_KEY_TABLE);
        q.setKeysOnly();

        PreparedQuery pq = datastore.prepare(q);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withLimit(2000));

        List<Key> keys = new ArrayList<Key>();
        for(Entity e : entities) {
                keys.add(e.getKey());
        }
        datastore.delete(keys);
	}
	
	static public void readInKeys(InputStream file) {
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
				String line=reader.readLine();
				++count;
				if (count > 1) {
					
				
					if (line==null)
					{
						break;
					}
					
	//    		System.out.println(line);
	
					String tokens[]=line.split("\t");
	//    		if (tokens.length == 3)
	//    			lines.add(tokens);
	//    		else
	//    			lines.add(new String[] {line});
					if (tokens.length == 3) {
						String textKey = tokens[0];
						Entity uiEntity = new Entity(UI_TEXT_KEY_TABLE);
						uiEntity.setProperty("key", textKey);
						Text uiText = new Text(tokens[1]);
						uiEntity.setProperty("text", uiText);
	//			        greeting.setProperty("user", user);
	//			        greeting.setProperty("date", date);
	//			        greeting.setProperty("content", content);
				
	
				        datastore.put(uiEntity);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}