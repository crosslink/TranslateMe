package internationalization.translate.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;

public class LanguageList extends Composite {

	private static LanguageListUiBinder uiBinder = GWT
			.create(LanguageListUiBinder.class);
	@UiField ListBox lbLangs;

	interface LanguageListUiBinder extends UiBinder<Widget, LanguageList> {
	}

	public LanguageList() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
