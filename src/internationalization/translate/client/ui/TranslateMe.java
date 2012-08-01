package internationalization.translate.client.ui;

import java.util.List;

import internationalization.translate.client.Application;
import internationalization.translate.client.Lang;
import internationalization.translate.client.Services;
import internationalization.translate.client.Translate;
import internationalization.translate.client.UiTextKey;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.SimplePanel;

public class TranslateMe extends Composite {

	private static TranslateMeUiBinder uiBinder = GWT
			.create(TranslateMeUiBinder.class);
	@UiField Button btnNext;
	@UiField Button btnSave;
	@UiField Button btnPrevious;
	@UiField TextBox tbTargetLanguage;
	@UiField TextBox tbEnglish;
	@UiField(provided=true) DataGrid<Object> dataGrid = new DataGrid<Object>();
	@UiField Button button;
	@UiField SimplePanel lbLangsPanel;

	UiTextKey[] keys;
	
	Translate translator = new Translate();
	
	List<Lang> listLangs;
	private UI ui;
	
	private int index;
	
	interface TranslateMeUiBinder extends UiBinder<Widget, TranslateMe> {
	}

	public TranslateMe(UI ui) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.ui = ui;
	}

	public TextBox getTbEnglish() {
		return tbEnglish;
	}

	public void setTbEnglish(TextBox tbEnglish) {
		this.tbEnglish = tbEnglish;
	}

	public TextBox getTbTargetLanguage() {
		return tbTargetLanguage;
	}

	public void setTbTargetLanguage(TextBox tbTargetLanguage) {
		this.tbTargetLanguage = tbTargetLanguage;
	}
	
	public void updateText() {
		tbEnglish.setText(keys[index].getText());
//		tbTarget
	}
	
	public void assignValues() {
//		listLangs = translator.getListLangs();
//		
//	    for (Lang lang : listLangs)
//	    	lbLangs.addItem(lang.getLangStr());
//		
//	    lbLangs.setVisibleItemCount(1);
//	    lbLangs = ui.getLbLangs();
		loadUiTextKeys();
	}
	
	public void loadUiTextKeys() {
		Services.getInstance().getDatabaseService().getUiTextKeys(new AsyncCallback<UiTextKey[]>() {
			public void onFailure(Throwable caught) {
				ui.showErrorDialogBox("Remote Procedure Call - Failure");
			}

			@Override
			public void onSuccess(UiTextKey[] result) {
				keys = result;
				
				if (keys.length > 0)
					updateText();
			}});
	}
	
	public void setBtnNext(Button btnNext) {
		this.btnNext = btnNext;
		this.btnNext.addClickHandler(ui.getUiHandler());
	}
	
	public Button getBtnNext() {
		return btnNext;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		Application.getInstance().getUiLayout().showLogin();
	}
	
	@UiHandler("btnSave")
	void onBtnSaveClick(ClickEvent event) {
	}
	
	@UiHandler("btnNext")
	void onBtnNextClick(ClickEvent event) {
		setIndex(getIndex() + 1);
		updateText();
	}
	
	@UiHandler("btnPrevious")
	void onBtnPreviousClick(ClickEvent event) {
		setIndex(getIndex() - 1);
		updateText();
	}
}
