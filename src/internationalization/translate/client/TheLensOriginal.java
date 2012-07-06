package internationalization.translate.client;

import internationalization.translate.client.ui.UI;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.VerticalSplitPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TheLensOriginal implements EntryPoint {

/**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  
  UI ui;
  

  
  public TheLensOriginal() {
		super();
		ui = new UI();
	}

  
//  private Translate translator;

  /**
   * This is the entry point method.
   */
  @SuppressWarnings("deprecation")
public void onModuleLoad() {
    final Label errorLabel = new Label();

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel rootPanel = RootPanel.get("nameFieldContainer");
//	if (rootPanel != null) {
	    rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);
	    RootPanel.get("errorLabelContainer").add(errorLabel);
	       
	    VerticalPanel verticalPanel = new VerticalPanel();
	    rootPanel.add(verticalPanel);
	    
	    CaptionPanel cptnpnlNewPanel = new CaptionPanel("Administration");
	    cptnpnlNewPanel.setCaptionHTML("Administration");
	    verticalPanel.add(cptnpnlNewPanel);
	    cptnpnlNewPanel.setHeight("172px");
	    
	    VerticalPanel verticalPanel_2 = new VerticalPanel();
	    cptnpnlNewPanel.setContentWidget(verticalPanel_2);
	    verticalPanel_2.setSize("100%", "1cm");
	    
	    VerticalPanel verticalPanel_1 = new VerticalPanel();
	    verticalPanel_2.add(verticalPanel_1);
	    verticalPanel_1.setWidth("100%");
	    
	    CaptionPanel cptnpnlNewPanel_1 = new CaptionPanel("Upload CSV File");
	    cptnpnlNewPanel_1.setCaptionHTML("Upload CSV File");
	    verticalPanel_1.add(cptnpnlNewPanel_1);
	    cptnpnlNewPanel_1.setSize("676px", "63px");
            
            VerticalPanel verticalPanel_4 = new VerticalPanel();
            cptnpnlNewPanel_1.setContentWidget(verticalPanel_4);
            verticalPanel_4.setSize("679px", "55px");
                
                HorizontalPanel fileUploaderPanel = new HorizontalPanel();
                verticalPanel_4.add(fileUploaderPanel);
                
                    
                    TextBox tbInputFile = new TextBox();
//                    fileUploaderPanel.add(tbInputFile);
                    tbInputFile.setAlignment(TextAlignment.LEFT);
                    tbInputFile.setTextAlignment(TextBoxBase.ALIGN_LEFT);
                    //RootPanel.get("fileupload").add(ui.getDefaultUploader());
                    fileUploaderPanel.add(ui.getDefaultUploader());
            
                HorizontalPanel horizontalPanel = new HorizontalPanel();
                verticalPanel_4.add(horizontalPanel);
                
                Button btnUploadKey = new Button("Upload Key");
                horizontalPanel.add(btnUploadKey);
                btnUploadKey.setSize("151px", "27px");
                
                btnUploadKey.addClickHandler(new ClickHandler() {
                	public void onClick(ClickEvent event) {
                		ui.getDefaultUploader().setServletPath("/thelens/updatedb");
                		ui.getDefaultUploader().submit();
//    		formPanel.setAction("/UpdateKey");
//    		formPanel.submit();
                	}
                });
                
                Button btnUploadTranslation = new Button("Upload Translation");
                horizontalPanel.add(btnUploadTranslation);
                btnUploadTranslation.setSize("151px", "27px");
                
                btnUploadTranslation.addClickHandler(new ClickHandler() {
                	public void onClick(ClickEvent event) {
                		ui.getDefaultUploader().setServletPath("/thelens/updatedb");
                		ui.getDefaultUploader().submit();
//    		formPanel.setAction("/UpdateTranslation");
//    		formPanel.submit();
                	}
                });
    
	    VerticalPanel verticalPanel_3 = new VerticalPanel();
	    verticalPanel_2.add(verticalPanel_3);
	    
	    CaptionPanel cptnpnlNewPanel_2 = new CaptionPanel("Empty Database");
	    cptnpnlNewPanel_2.setCaptionHTML("Empty Database");
	    verticalPanel_3.add(cptnpnlNewPanel_2);
	    cptnpnlNewPanel_2.setSize("676px", "63px");
	    
	    Button btnClearKey = new Button("Clear Key");
	    cptnpnlNewPanel_2.setContentWidget(btnClearKey);
	    btnClearKey.setSize("300px", "29px");
	    ui.setBtnClearKey(btnClearKey);
	    
	    Label lblNewLabel = new Label("");
	    lblNewLabel.setSize("50%", "50%");
	    
	    CaptionPanel cptnpnlTranslateMe = new CaptionPanel("Translate Me");
	    verticalPanel.add(cptnpnlTranslateMe);
	    cptnpnlTranslateMe.setSize("701px", "547px");
	    
	    AbsolutePanel absolutePanel = new AbsolutePanel();
	    cptnpnlTranslateMe.setContentWidget(absolutePanel);
	    absolutePanel.setSize("692px", "498px");
	
	    
	    ListBox lbLangs = new ListBox();
	    absolutePanel.add(lbLangs, 10, 57);
	    lbLangs.setSize("567px", "20px");
	    lbLangs.setVisibleItemCount(5);
	    ui.setLbLangs(lbLangs);
	    
	    TextBox tbEnglish = new TextBox();
	    tbEnglish.setStyleName("englishText");
	    tbEnglish.setAlignment(TextAlignment.LEFT);
	    tbEnglish.setTextAlignment(TextBoxBase.ALIGN_LEFT);
	    tbEnglish.setReadOnly(true);
	    absolutePanel.add(tbEnglish, 10, 106);
	    tbEnglish.setSize("553px", "108px");
	    
	    ui.setTbEnglish(tbEnglish);
	    
	    Label lblEnglish = new Label("English");
	    absolutePanel.add(lblEnglish, 10, 85);
	    
	    Label lblTarget = new Label("Translation");
	    absolutePanel.add(lblTarget, 10, 244);
	    
	    TextBox tbTargetLanguage = new TextBox();
	    tbTargetLanguage.setTextAlignment(TextBoxBase.ALIGN_LEFT);
	    tbTargetLanguage.setVisibleLength(2000);
	    absolutePanel.add(tbTargetLanguage, 10, 265);
	    tbTargetLanguage.setSize("553px", "108px");
	    
	    ui.setTbTargetLanguage(tbTargetLanguage);
	    
	    Button btnNext = new Button("Next");
	    absolutePanel.add(btnNext, 349, 411);
	    ui.setBtnNext(btnNext);
	    
	    Button btnSave = new Button("Save");
	    absolutePanel.add(btnSave, 131, 411);
	    ui.assignValues();
//    }
    

  }



  
  // Load the image in the document and in the case of success attach it to the viewer
//  IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
//
//  };
}
