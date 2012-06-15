package org.cambia.translate.client;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.PreloadedImage;

import org.cambia.translate.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

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
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	//A panel where the thumbnails of uploaded images will be shown
	 private FlowPanel panelImages = new FlowPanel();

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
    
    // Create a new uploader panel and attach it to the document
    MultiUploader defaultUploader = new MultiUploader();
    RootPanel.get("default").add(defaultUploader);

    // Add a finish handler which will load the image once the upload finishes
    defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
    
    AbsolutePanel absolutePanel = new AbsolutePanel();
    rootPanel.add(absolutePanel, 10, 0);
    absolutePanel.setSize("628px", "640px");
    final TextBox nameField = new TextBox();
    absolutePanel.add(nameField);
    nameField.setText("GWT User");
    
        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();

    final Button sendButton = new Button("Upload Key");
    absolutePanel.add(sendButton, 182, 0);
    sendButton.setSize("132px", "27px");
    
    // We can add style names to widgets
    sendButton.addStyleName("sendButton");

    
    ListBox listBox = new ListBox();
    absolutePanel.add(listBox, 10, 188);
    listBox.setSize("567px", "20px");
    listBox.setVisibleItemCount(5);
    
    TextBox textBox = new TextBox();
    absolutePanel.add(textBox, 10, 237);
    textBox.setSize("553px", "108px");
    
    Label lblEnglish = new Label("English");
    absolutePanel.add(lblEnglish, 10, 216);
    
    Label lblTarget = new Label("Target");
    absolutePanel.add(lblTarget, 10, 363);
    
    TextBox textBox_1 = new TextBox();
    absolutePanel.add(textBox_1, 10, 396);
    textBox_1.setSize("553px", "108px");
    
    Button btnNext = new Button("Next");
    absolutePanel.add(btnNext, 349, 542);
    
    Button btnSave = new Button("Save");
    absolutePanel.add(btnSave, 131, 542);
    
    final FormPanel formPanel = new FormPanel();
    formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
    formPanel.setMethod(FormPanel.METHOD_POST);

    absolutePanel.add(formPanel, 10, 75);
    formPanel.setSize("561px", "107px");
    
    AbsolutePanel absolutePanel_1 = new AbsolutePanel();
    formPanel.setWidget(absolutePanel_1);
    absolutePanel_1.setSize("100%", "97px");
    
    final TextBox tbInputFile = new TextBox();
    tbInputFile.setAlignment(TextAlignment.LEFT);
    tbInputFile.setTextAlignment(TextBoxBase.ALIGN_LEFT);
    absolutePanel_1.add(tbInputFile, 0, 60);
    
    final FileUpload fileUpload = new FileUpload();
    fileUpload.addChangeHandler(new ChangeHandler() {
    	public void onChange(ChangeEvent event) {
    		tbInputFile.setText(fileUpload.getFilename());
    	}
    });
    absolutePanel_1.add(fileUpload, 175, 59);
    
    Button btnUploadTranslation = new Button("Upload Translation");
    absolutePanel_1.add(btnUploadTranslation, 369, 60);
    btnUploadTranslation.setSize("151px", "27px");
    
    Button btnUploadKey = new Button("Upload Key");
    absolutePanel_1.add(btnUploadKey, 10, 27);
    btnUploadKey.setSize("151px", "27px");
    
    btnUploadKey.addClickHandler(new ClickHandler() {
    	public void onClick(ClickEvent event) {
    		formPanel.setAction("/UpdateKey");
    		formPanel.submit();
    	}
    });
    
    btnUploadTranslation.addClickHandler(new ClickHandler() {
    	public void onClick(ClickEvent event) {
    		formPanel.setAction("/UpdateTranslation");
    		formPanel.submit();
    	}
    });
    
 // Add an event handler to the form.
    formPanel.addFormHandler(new FormHandler() {
      public void onSubmit(FormSubmitEvent event) {
        // This event is fired just before the form is submitted. We can take
        // this opportunity to perform validation.
    	  String inputFileString = tbInputFile.getText();
        if (inputFileString.length() == 0) {
          Window.alert("The text box must not be empty");
          event.setCancelled(true);
        }
      }


  	  @Override
      public void onSubmitComplete(FormSubmitCompleteEvent event) {
        // When the form submission is successfully completed, this event is
        // fired. Assuming the service returned a response of type text/html,
        // we can get the result text here (see the FormPanel documentation for
        // further explanation).
        Window.alert(event.getResults());
      }

    });

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

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
        sendNameToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToServer();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = nameField.getText();
        if (!FieldVerifier.isValidName(textToServer)) {
          errorLabel.setText("Please enter at least four characters");
          return;
        }
        
        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");
        greetingService.greetServer(textToServer, new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            dialogBox.setText("Remote Procedure Call - Failure");
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(SERVER_ERROR);
            dialogBox.center();
            closeButton.setFocus(true);
          }

          public void onSuccess(String result) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(result);
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    nameField.addKeyUpHandler(handler);
    sendButton.addClickHandler(handler);
    
  }
  
  // Load the image in the document and in the case of success attach it to the viewer
  IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
    public void onFinish(IUploader uploader) {
      if (uploader.getStatus() == Status.SUCCESS) {

        new PreloadedImage(uploader.fileUrl(), showImage);
        
        // The server sends useful information to the client by default
        UploadedInfo info = uploader.getServerInfo();
        System.out.println("File name " + info.name);
        System.out.println("File content-type " + info.ctype);
        System.out.println("File size " + info.size);

        // You can send any customized message and parse it 
        System.out.println("Server message " + info.message);
      }
    }
    
    // Attach an image to the pictures viewer
    OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
      public void onLoad(PreloadedImage image) {
        image.setWidth("75px");
        panelImages.add(image);
      }
    };
  };
}
