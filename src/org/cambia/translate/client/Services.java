package org.cambia.translate.client;

import com.google.gwt.core.client.GWT;

public class Services {

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final DatabaseServiceAsync databaseService = GWT.create(DatabaseService.class);
	
	public Services() {

	}

}
