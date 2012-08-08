package internationalization.translate.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface AppResourcesInterface extends ClientBundle {
	public static final AppResourcesInterface INSTANCE =  GWT.create(AppResourcesInterface.class);
	
	@Source("GoogleTranslateLangs.properties")
	  public TextResource initialConfiguration();
}
