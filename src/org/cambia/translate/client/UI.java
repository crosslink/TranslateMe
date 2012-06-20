package org.cambia.translate.client;

import java.util.List;

import gwtupload.client.Uploader;

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
	  
	Translate translator = new Translate();
	
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
	
	private void assignValues() {
		List<Lang> listLangs;
		listLangs = translator.getListLangs();
		
	    for (Lang lang : listLangs)
	    	lbLangs.addItem(lang.getLangStr());
		
	    lbLangs.setVisibleItemCount(1);
	}
}
