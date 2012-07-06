package internationalization.translate.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class Login extends Composite {

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	@UiField Button button;

	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	public Login() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Login(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
//		button.setText(firstName);
	}



	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		
	}
}
