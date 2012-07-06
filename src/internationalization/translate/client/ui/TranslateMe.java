package internationalization.translate.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TranslateMe extends Composite {

	private static TranslateMeUiBinder uiBinder = GWT
			.create(TranslateMeUiBinder.class);

	interface TranslateMeUiBinder extends UiBinder<Widget, TranslateMe> {
	}

	public TranslateMe() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
