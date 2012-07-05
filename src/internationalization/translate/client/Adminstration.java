package internationalization.translate.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Adminstration extends Composite {

	private static AdminstrationUiBinder uiBinder = GWT
			.create(AdminstrationUiBinder.class);

	interface AdminstrationUiBinder extends UiBinder<Widget, Adminstration> {
	}

	public Adminstration() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
