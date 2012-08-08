package internationalization.translate.client.ui;

import gwtupload.client.Uploader;
import internationalization.translate.client.Application;
import internationalization.translate.client.DatabaseHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.ListBox;

public class Adminstration extends Composite {

	private static AdminstrationUiBinder uiBinder = GWT
			.create(AdminstrationUiBinder.class);
	@UiField SimplePanel fileUploaderPanel;
	@UiField Button btnUploadKey;
	@UiField Button btnUploadTranslation;
	@UiField SimplePanel lbLangsPanel;
	@UiField Button btnLogout;
	
	private Uploader defaultUploader;
	private FlowPanel panelImages = new FlowPanel();
	
	private TextBox tbInputFile = new TextBox();
	  
	private DatabaseHandler databaseHandler;
	
	private Button btnClearKey;
	private UI ui;

	interface AdminstrationUiBinder extends UiBinder<Widget, Adminstration> {
		
	}

	public Adminstration(UI ui) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.ui = ui;
        
	    // Create a new uploader panel and attach it to the document
	    defaultUploader = new Uploader();
	    defaultUploader.setAutoSubmit(false);
	    
        fileUploaderPanel.add(defaultUploader);
	    
        databaseHandler = new DatabaseHandler(this);
        
        // Add a finish handler which will load the image once the upload finishes
//      ui.defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
      defaultUploader.addOnFinishUploadHandler(databaseHandler);
      defaultUploader.addOnChangeUploadHandler(databaseHandler);
      
      lbLangsPanel.add(ui.getLbLangs());
//	    ui.setBtnClearKey(btnClearKey);
	}

	@UiHandler("btnUploadKey")
	void onBtnUploadKeyClick(ClickEvent event) {
		getDefaultUploader().setServletPath("/thelens/updatedb?target=key");
		getDefaultUploader().submit();
	}
	
	@UiHandler("btnUploadTranslation")
	void onBtnUploadTranslationClick(ClickEvent event) {
		getDefaultUploader().setServletPath("/thelens/updatedb?target=translation");
		getDefaultUploader().submit();
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
	
	
	public FlowPanel getPanelImages() {
		return panelImages;
	}
	  
	public void setPanelImages(FlowPanel panelImages) {
		this.panelImages = panelImages;
	}
	@UiHandler("btnLogout")
	void onBtnLogoutClick(ClickEvent event) {
		Application.getInstance().getUiLayout().showLogin();
	}
}
