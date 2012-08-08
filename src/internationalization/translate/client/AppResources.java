package internationalization.translate.client;

public class AppResources {
	
	public static String langToKey(String lang) {
		return lang.toLowerCase().replace(" ", "");
	}
}
