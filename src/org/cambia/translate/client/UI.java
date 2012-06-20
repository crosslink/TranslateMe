package org.cambia.translate.client;

import java.util.List;

import gwtupload.client.Uploader;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class UI {
	public String[] supportedLangs = {""};
	
	private ListBox lbLanguages = new ListBox();
	
	  //A panel where the thumbnails of uploaded images will be shown
	  
	private FlowPanel panelImages = new FlowPanel();

	private ListBox lbLangs;
	  
	private Uploader defaultUploader;
	
	private TextBox tbInputFile = new TextBox();
	  
	private DatabaseHandler databaseHandler;
	
	private Button btnClearKey;
	
	private TextBox tbEnglish;
	
	private TextBox tbTargetLanguage;
	
	private int index; // the index showing the current text
	
	// data
	Translate translator = new Translate();
	UiTextKey[] keys;
	List<Lang> listLangs;
	
	private UiHandler uiHandler;
	
	public UI() {
		uiHandler = new UiHandler(this);
		
	    // Create a new uploader panel and attach it to the document
	    defaultUploader = new Uploader();
	    defaultUploader.setAutoSubmit(false);
	    
        databaseHandler = new DatabaseHandler(this);
        
        // Add a finish handler which will load the image once the upload finishes
//      ui.defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
      defaultUploader.addOnFinishUploadHandler(databaseHandler);
      defaultUploader.addOnChangeUploadHandler(databaseHandler);
      
  		assignValues();
	}
	
	public FlowPanel getPanelImages() {
		return panelImages;
	}
	  
	public void setPanelImages(FlowPanel panelImages) {
		this.panelImages = panelImages;
	}
	
	public ListBox getLbLangs() {
		return lbLangs;
	}
	
	public void setLbLangs(ListBox lbLangs) {
		this.lbLangs = lbLangs;
		
		this.lbLangs.addChangeHandler(uiHandler);
	}
	
	public Uploader getDefaultUploader() {
		return defaultUploader;
	}
	
	public void setDefaultUploader(Uploader defaultUploader) {
		this.defaultUploader = defaultUploader;
	}
	
	public DatabaseHandler getDatabaseHandler() {
		return databaseHandler;
	}
	
	public void setDatabaseHandler(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public TextBox getTbInputFile() {
		return tbInputFile;
	}

	public void setTbInputFile(TextBox tbInputFile) {
		this.tbInputFile = tbInputFile;
	}

	public Button getBtnClearKey() {
		return btnClearKey;
	}

	public void setBtnClearKey(Button btnClearKey) {
		this.btnClearKey = btnClearKey;
		
		btnClearKey.addClickHandler(databaseHandler.getClearKeysHandler());
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

	public void loadUiTextKeys() {
		Services.getInstance().getDatabaseService().getUiTextKeys(new AsyncCallback<UiTextKey[]>() {
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(UiTextKey[] result) {
				keys = result;
				
				if (keys.length > 0)
					tbEnglish.setText(keys[0].getText().toString());
			}});
	}
	
	private void assignValues() {
		listLangs = translator.getListLangs();
		
	    for (Lang lang : listLangs)
	    	lbLangs.addItem(lang.getLangStr());
		
	    lbLangs.setVisibleItemCount(1);
	    
		loadUiTextKeys();
	}
}
