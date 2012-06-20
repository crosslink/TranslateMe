package org.cambia.translate.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;

public class UiHandler implements ChangeHandler {

	private UI ui;
	
	public UiHandler(UI ui) {
		super();
		this.ui = ui;
	}

	@Override
	public void onChange(ChangeEvent event) {
		if (event.getSource() == ui.getLbLangs()) {
			
		}
		
	}
	
	

}
