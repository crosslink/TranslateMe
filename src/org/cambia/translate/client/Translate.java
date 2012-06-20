package org.cambia.translate.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
//import com.google.gwt.dev.cfg.Properties;
import java.util.Properties;

import org.cambia.translate.server.db.UiTextKeyDb;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class Translate {
//	Properties supportedLangs; 
	List<Lang> listLangs = new ArrayList<Lang>();
	HashMap<String, Lang> langMap = new HashMap<String, Lang>();
	
	public class LangComparator implements Comparator<Lang> {
	    @Override
	    public int compare(Lang o1, Lang o2) {
	        return o1.lang.compareTo(o2.lang);
	    }
	}
	
	public Translate() {
//		supportedLangs = new Properties();

		convertToList();
		
		loadKeys();
	}

	private void convertToList() {
		String text = AppResources.INSTANCE.initialConfiguration().getText();
		//InputStream in = /*Translate.class.getClass()*/getServletContext().getResourceAsStream("org.cambia.translate.GoogleTranslateLangs.properties");
		try {
			BufferedReader reader = new BufferedReader(new StringReader(text));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] array = line.split("=");
				String langStr = (String) array[0];
				String code = (String)array[1];
				Lang langObj = new Lang(langStr.trim(), code.trim());
				listLangs.add(langObj);
				langMap.put(code, langObj);
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
//		for (Object lang : supportedLangs.keySet()) {
//			String langStr = (String) lang;
//			String code = (String)supportedLangs.get(lang);
//			Lang langObj = new Lang(langStr, code);
//			listLangs.add(langObj);
//			langMap.put(code, langObj);
//		}
		Collections.sort(listLangs, new LangComparator());
	}

	public List<Lang> getListLangs() {
		return listLangs;
	}
	
	public void loadKeys() {
        DatastoreService datastore =
                DatastoreServiceFactory.getDatastoreService();
        
        Query q = new Query(UiTextKeyDb.UI_TEXT_KEY_TABLE);

        PreparedQuery pq = datastore.prepare(q);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withLimit(2000));

//        for (Entity en)
	}
	
}
