package internationalization.translate.client;

import internationalization.translate.client.ui.UI;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class UiHandler implements ChangeHandler, ClickHandler {

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

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == ui.getBtnNext()) {
			ui.setIndex(ui.getIndex() + 1);
			ui.updateText();
		}
		
	}
	
	

}
