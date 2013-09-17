package internationalization.translate.client;

import com.google.gwt.core.client.EntryPoint;

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
  
  public TheLens() {
		super();
//		ui = new UI();
	}

  
//  private Translate translator;

  /**
   * This is the entry point method.
   */
  @SuppressWarnings("deprecation")
public void onModuleLoad() {

//	  RootPanel header = RootPanel.get("header").add(new Header());
//	  RootPanel leftNav = RootPanel.get("leftnav").add(new NavigationMenu());
//	  RootPanel footer = RootPanel.get("footer").add(new Footer());
	  
//    }
    Application.getInstance().getUiLayout().showLogin();

  }



  
  // Load the image in the document and in the case of success attach it to the viewer
//  IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
//
//  };
}
