package internationalization.translate.client;

import internationalization.translate.client.ui.Adminstration;
import internationalization.translate.client.ui.TranslateMe;
import internationalization.translate.client.ui.UI;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class UiHandler implements ChangeHandler, ClickHandler {

	private UI ui;
	
	private TranslateMe uiTranslateMe;
	private Adminstration uiAdmin;
	
	public UiHandler(UI ui) {
		super();
		this.ui = ui;
		
		this.uiTranslateMe = ui.getTranslateMeUi();
	}

	@Override
	public void onChange(ChangeEvent event) {
		if (event.getSource() == ui.getLbLangs()) {
			
		}
		
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == uiTranslateMe.getBtnNext()) {
			uiTranslateMe.setIndex(uiTranslateMe.getIndex() + 1);
			uiTranslateMe.updateText();
		}
		
	}
	
	

}
