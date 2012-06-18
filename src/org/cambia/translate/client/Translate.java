package org.cambia.translate.client;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
//import com.google.gwt.dev.cfg.Properties;
import java.util.Properties;


public class Translate {
	Properties supportedLangs; 
	List<Lang> listLangs = new ArrayList<Lang>();
	HashMap<String, Lang> langMap = new HashMap<String, Lang>();
	
	public class LangComparator implements Comparator<Lang> {
	    @Override
	    public int compare(Lang o1, Lang o2) {
	        return o1.lang.compareTo(o2.lang);
	    }
	}
	
	public Translate() {
		supportedLangs = new Properties();
		String text = AppResources.INSTANCE.initialConfiguration().getText();
		//InputStream in = /*Translate.class.getClass()*/getServletContext().getResourceAsStream("org.cambia.translate.GoogleTranslateLangs.properties");
		try {
			StringReader sr = new StringReader(text);
			supportedLangs.load(sr);
			sr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		convertToList();
	}

	private void convertToList() {
		for (Object lang : supportedLangs.keySet()) {
			String langStr = (String) lang;
			String code = (String)supportedLangs.get(lang);
			Lang langObj = new Lang(langStr, code);
			listLangs.add(langObj);
			langMap.put(code, langObj);
		}
		Collections.sort(listLangs, new LangComparator());
	}

	public List<Lang> getListLangs() {
		return listLangs;
	}
	
	
}
