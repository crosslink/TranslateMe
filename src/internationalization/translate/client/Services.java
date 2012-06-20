package internationalization.translate.client;

import com.google.gwt.core.client.GWT;

public class Services {

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final DatabaseServiceAsync databaseService = GWT.create(DatabaseService.class);
	
	private static Services instance;
	
	public Services() {
		
	}
	
	public static Services getInstance() {
		if (instance == null)
			instance = new Services();
		return instance;
	}

	public DatabaseServiceAsync getDatabaseService() {
		return databaseService;
	}

}
