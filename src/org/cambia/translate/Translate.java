package org.cambia.translate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Translate {
	Properties supportedLangs; // = new HashMap<String, String>();
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
		InputStream in = getClass().getResourceAsStream("org.cambia.translate.GoogleTranslateLangs.properties");
		try {
			supportedLangs.load(in);
			in.close();
			
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
