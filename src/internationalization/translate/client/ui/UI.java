package internationalization.translate.client.ui;

import internationalization.translate.client.Application;
import internationalization.translate.client.Lang;
import internationalization.translate.client.Translate;
import internationalization.translate.client.UiHandler;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/*
 * THe common UI 
 */
public class UI {
	  private static final String SERVER_ERROR_NETWORK = "An error occurred while "
		      + "attempting to contact the server. Please check your network "
		      + "connection and try again.";
	  
	  private static final String SERVER_ERROR_DATABASE = "An error occurred while "
		      + "loadng the data from server. Please try again.";
	  
	public String[] supportedLangs = {""};
	
//	private ListBox lbLanguages = new ListBox();
	
	  //A panel where the thumbnails of uploaded images will be shown
	  


	private ListBox lbLangs = new ListBox();
	
//	private TextBox tbEnglish;
//	
//	private TextBox tbTargetLanguage;
	
	DialogBox dialogBox = new DialogBox();
    HTML serverResponseLabel = new HTML();
    
//	private int index; // the index showing the current text
	

//	List<Lang> listLangs;
	
	private UiHandler uiHandler;

	private Button btnNext;


	private TranslateMe uiTranslateMe;
	private Adminstration uiAdministration;
	
	public UI() {
		uiHandler = new UiHandler(this);
      
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
//      dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
      dialogVPanel.add(serverResponseLabel);
      dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
      dialogVPanel.add(closeButton);
      dialogBox.setWidget(dialogVPanel);
      
//      assignValues();
	}

	
	public ListBox getLbLangs() {
		return lbLangs;
	}
	
	public void setLbLangs(ListBox lbLangs) {
		this.lbLangs = lbLangs;
		
		this.lbLangs.addChangeHandler(uiHandler);
	}
	

	public void showErrorDialogBox(String info) {
        dialogBox.setText(info);
        serverResponseLabel.addStyleName("serverResponseLabelError");
        serverResponseLabel.setHTML(SERVER_ERROR_DATABASE);
        dialogBox.center();
	}

	
	public void assignValues(List<Lang> listLangs) {
//		List<Lang>  listLangs = Application.getInstance().getTranslator().getListLangs();
		
	    for (Lang lang : listLangs)
	    	lbLangs.addItem(lang.getLangStr());
		
	    lbLangs.setVisibleItemCount(1);
	    
//		loadUiTextKeys();
	}


	public UiHandler getUiHandler() {
		return uiHandler;
	}

	public TranslateMe getTranslateMeUi() {
		return uiTranslateMe;
	}

	public void setUiTranslateMe(TranslateMe uiTranslateMe) {
		this.uiTranslateMe = uiTranslateMe;
	}

	public Adminstration getUiAdministration() {
		return uiAdministration;
	}

	public void setUiAdministration(Adminstration uiAdministration) {
		this.uiAdministration = uiAdministration;
	}
}
