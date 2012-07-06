package internationalization.translate.client.ui;

import internationalization.translate.client.DatabaseHandler;
import internationalization.translate.client.Lang;
import internationalization.translate.client.Services;
import internationalization.translate.client.Translate;
import internationalization.translate.client.UiHandler;
import internationalization.translate.client.UiTextKey;

import java.util.List;

import gwtupload.client.Uploader;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UI {
	  private static final String SERVER_ERROR_NETWORK = "An error occurred while "
		      + "attempting to contact the server. Please check your network "
		      + "connection and try again.";
	  
	  private static final String SERVER_ERROR_DATABASE = "An error occurred while "
		      + "loadng the data from server. Please try again.";
	  
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
	
	DialogBox dialogBox = new DialogBox();
    HTML serverResponseLabel = new HTML();
    
	private int index; // the index showing the current text
	
	// data
	Translate translator = new Translate();
	UiTextKey[] keys;
	List<Lang> listLangs;
	
	private UiHandler uiHandler;

	private Button btnNext;
	
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
      
      dialogBox.setAnimationEnabled(true);

      final Button closeButton = new Button("Close");
      // Add a handler to close the DialogBox
      closeButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          dialogBox.hide();
//          sendButton.setEnabled(true);
//          sendButton.setFocus(true);
        }
      });
      // We can set the id of a widget by accessing its Element
      closeButton.getElement().setId("closeButton");
      VerticalPanel dialogVPanel = new VerticalPanel();
      dialogVPanel.addStyleName("dialogVPanel");
      dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//      dialogVPanel.add(textToServerLabel);
      dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
      dialogVPanel.add(serverResponseLabel);
      dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
      dialogVPanel.add(closeButton);
      dialogBox.setWidget(dialogVPanel);
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
	            dialogBox.setText("Remote Procedure Call - Failure");
	            serverResponseLabel.addStyleName("serverResponseLabelError");
	            serverResponseLabel.setHTML(SERVER_ERROR_DATABASE);
	            dialogBox.center();
			}

			@Override
			public void onSuccess(UiTextKey[] result) {
				keys = result;
				
				if (keys.length > 0)
					updateText();
			}});
	}
	
	public void assignValues() {
		listLangs = translator.getListLangs();
		
	    for (Lang lang : listLangs)
	    	lbLangs.addItem(lang.getLangStr());
		
	    lbLangs.setVisibleItemCount(1);
	    
		loadUiTextKeys();
	}

	public void setBtnNext(Button btnNext) {
		this.btnNext = btnNext;
		this.btnNext.addClickHandler(uiHandler);
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

	public void updateText() {
		tbEnglish.setText(keys[index].getText());
//		tbTarget
	}
}
