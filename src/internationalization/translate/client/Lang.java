package internationalization.translate.client;

import java.util.Comparator;


public class Lang {
	String lang;
	String code;
	

	
	public Lang(String langStr, String c) {
		lang = langStr;
		code = c;
				
	}


	public String getLangStr() {
		return lang;
	}

	public String getCode() {
		return code;
	}
}
