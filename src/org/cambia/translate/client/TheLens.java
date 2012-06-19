package org.cambia.translate.client;

import java.util.List;


import gwtupload.client.Uploader;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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
public class TheLens implements EntryPoint {

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
  
  //A panel where the thumbnails of uploaded images will be shown
  private FlowPanel panelImages = new FlowPanel();
  private ListBox lbLangs;
  private Uploader defaultUploader;
  private DatabaseHandler databaseHandler;
  
  List<Lang> listLangs;
  
  public TheLens() {
		super();
		
		final Translate translator = new Translate();
		listLangs = translator.getListLangs();
		
	    // Create a new uploader panel and attach it to the document
	    defaultUploader = new Uploader();
	    defaultUploader.setAutoSubmit(false);
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
    rootPanel.getElement().getStyle().setPosition(Position.RELATIVE);
    RootPanel.get("errorLabelContainer").add(errorLabel);
       
    VerticalPanel verticalPanel = new VerticalPanel();
    rootPanel.add(verticalPanel);
    
    CaptionPanel cptnpnlNewPanel = new CaptionPanel("New panel");
    cptnpnlNewPanel.setCaptionHTML("Administration");
    verticalPanel.add(cptnpnlNewPanel);
    cptnpnlNewPanel.setHeight("172px");
    
    VerticalPanel verticalPanel_2 = new VerticalPanel();
    cptnpnlNewPanel.setContentWidget(verticalPanel_2);
    verticalPanel_2.setSize("5cm", "3cm");
    
    VerticalPanel verticalPanel_1 = new VerticalPanel();
    verticalPanel_2.add(verticalPanel_1);
    
    FlowPanel fileUploaderPanel = new FlowPanel();
    verticalPanel_1.add(fileUploaderPanel);
    
        
        final TextBox tbInputFile = new TextBox();
        fileUploaderPanel.add(tbInputFile);
        tbInputFile.setAlignment(TextAlignment.LEFT);
        tbInputFile.setTextAlignment(TextBoxBase.ALIGN_LEFT);
        //RootPanel.get("fileupload").add(defaultUploader);
        fileUploaderPanel.add(defaultUploader);
        databaseHandler = new DatabaseHandler(panelImages, tbInputFile);
        
            HorizontalPanel horizontalPanel = new HorizontalPanel();
            verticalPanel_1.add(horizontalPanel);
            
            Button btnUploadKey = new Button("Upload Key");
            horizontalPanel.add(btnUploadKey);
            btnUploadKey.setSize("151px", "27px");
            
            btnUploadKey.addClickHandler(new ClickHandler() {
            	public void onClick(ClickEvent event) {
            		defaultUploader.setServletPath("/thelens/updatedb");
            		defaultUploader.submit();
//    		formPanel.setAction("/UpdateKey");
//    		formPanel.submit();
            	}
            });
            
            Button btnUploadTranslation = new Button("Upload Translation");
            horizontalPanel.add(btnUploadTranslation);
            btnUploadTranslation.setSize("151px", "27px");
            
            btnUploadTranslation.addClickHandler(new ClickHandler() {
            	public void onClick(ClickEvent event) {
            		defaultUploader.setServletPath("/thelens/updatedb");
            		defaultUploader.submit();
//    		formPanel.setAction("/UpdateTranslation");
//    		formPanel.submit();
            	}
            });
    
    VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
    verticalPanel_2.add(verticalSplitPanel);
    verticalSplitPanel.setSize("447px", "77px");
    
    Button btnClearKey = new Button("Clear Key");
    btnClearKey.addClickHandler(databaseHandler.getClearKeysHandler());
    verticalSplitPanel.setBottomWidget(btnClearKey);
    
    Label lblNewLabel = new Label("");
    lblNewLabel.setSize("50%", "50%");
    verticalSplitPanel.setTopWidget(btnClearKey);
    btnClearKey.setSize("301px", "29px");
    
    // Add a finish handler which will load the image once the upload finishes
//    defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
    defaultUploader.addOnFinishUploadHandler(databaseHandler);
    defaultUploader.addOnChangeUploadHandler(databaseHandler);
    
    CaptionPanel cptnpnlTranslateMe = new CaptionPanel("Translate Me");
    verticalPanel.add(cptnpnlTranslateMe);
    cptnpnlTranslateMe.setSize("701px", "547px");
    
    AbsolutePanel absolutePanel = new AbsolutePanel();
    cptnpnlTranslateMe.setContentWidget(absolutePanel);
    absolutePanel.setSize("692px", "498px");

    
    lbLangs = new ListBox();
    absolutePanel.add(lbLangs, 10, 57);
    lbLangs.setSize("567px", "20px");
    lbLangs.setVisibleItemCount(5);
    
    TextBox textBox = new TextBox();
    absolutePanel.add(textBox, 10, 106);
    textBox.setSize("553px", "108px");
    
    Label lblEnglish = new Label("English");
    absolutePanel.add(lblEnglish, 10, 85);
    
    Label lblTarget = new Label("Target");
    absolutePanel.add(lblTarget, 10, 232);
    
    TextBox textBox_1 = new TextBox();
    absolutePanel.add(textBox_1, 10, 265);
    textBox_1.setSize("553px", "108px");
    
    Button btnNext = new Button("Next");
    absolutePanel.add(btnNext, 349, 411);
    
    Button btnSave = new Button("Save");
    absolutePanel.add(btnSave, 131, 411);

    /*
    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        sendButton.setEnabled(true);
        sendButton.setFocus(true);
      }
    });
     */
    // Create a handler for the sendButton and nameField
//    class MyHandler implements ClickHandler, KeyUpHandler {
//      /**
//       * Fired when the user clicks on the sendButton.
//       */
//      public void onClick(ClickEvent event) {
//        ();
//      }
//
//      /**
//       * Fired when the user types in the nameField.
//       */
//      public void onKeyUp(KeyUpEvent event) {
//        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//          sendNameToServer();
//        }
//      }
//
//      /**
//       * Send the name from the nameField to the server and wait for a response.
//       */
//      private void sendNameToServer() {
//        // First, we validate the input.
//        errorLabel.setText("");
//        String textToServer = nameField.getText();
//        if (!FieldVerifier.isValidName(textToServer)) {
//          errorLabel.setText("Please enter at least four characters");
//          return;
//        }
//        
//        // Then, we send the input to the server.
//        sendButton.setEnabled(false);
//        textToServerLabel.setText(textToServer);
//        serverResponseLabel.setText("");
//        greetingService.greetServer(textToServer, new AsyncCallback<String>() {
//          public void onFailure(Throwable caught) {
//            // Show the RPC error message to the user
//            dialogBox.setText("Remote Procedure Call - Failure");
//            serverResponseLabel.addStyleName("serverResponseLabelError");
//            serverResponseLabel.setHTML(SERVER_ERROR);
//            dialogBox.center();
//            closeButton.setFocus(true);
//          }
//
//          public void onSuccess(String result) {
//            dialogBox.setText("Remote Procedure Call");
//            serverResponseLabel.removeStyleName("serverResponseLabelError");
//            serverResponseLabel.setHTML(result);
//            dialogBox.center();
//            closeButton.setFocus(true);
//          }
//        });
//      }
//    }
//
//    // Add a handler to send the name to the server
//    MyHandler handler = new MyHandler();
    	assignValues();
  }



private void assignValues() {
    for (Lang lang : listLangs)
    	lbLangs.addItem(lang.getLangStr());
	
    lbLangs.setVisibleItemCount(1);
}
  
  // Load the image in the document and in the case of success attach it to the viewer
//  IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
//
//  };
}
