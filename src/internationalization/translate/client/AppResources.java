package internationalization.translate.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface AppResources extends ClientBundle {
	public static final AppResources INSTANCE =  GWT.create(AppResources.class);
	
	@Source("GoogleTranslateLangs.properties")
	  public TextResource initialConfiguration();
}
