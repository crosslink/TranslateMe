package internationalization.translate.client.ui;

import internationalization.translate.client.GreetingService;
import internationalization.translate.client.GreetingServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class Login extends Composite {

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	@UiField Button button;
	@UiField TextBox tbUserName;
	@UiField PasswordTextBox tbPassword;

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
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
		final String username = tbUserName.getText();
		String password = tbPassword.getText();
		if (tbUserName.getText().length() == 0
				|| tbPassword.getText().length() == 0) {
				Window.alert("Username or password is empty."); 
			}
		else {

			greetingService.authenticate(username, password,
					new AsyncCallback<Boolean>() {
						public void onFailure(Throwable caught) {
							Window.alert("Unknow error. Try again"); 
						}

						@Override
						public void onSuccess(Boolean result) {
							if (result) {
								if (username.equalsIgnoreCase("admin"))
									UiLayout.getInstance().showAdminWindow();
								else
									UiLayout.getInstance().showMainWindow();	
							}						
						}
					});
		}
	}
}
