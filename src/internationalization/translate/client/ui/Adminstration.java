package internationalization.translate.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class Adminstration extends Composite {

	private static AdminstrationUiBinder uiBinder = GWT
			.create(AdminstrationUiBinder.class);
	@UiField SimplePanel uploadFormContainer;
	@UiField Button btnUploadKey;
	@UiField Button btnUploadTranslation;
	
	

	interface AdminstrationUiBinder extends UiBinder<Widget, Adminstration> {
		
	}

	public Adminstration() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}

	@UiHandler("btnUploadKey")
	void onBtnUploadKeyClick(ClickEvent event) {
		
	}
	@UiHandler("btnUploadTranslation")
	void onBtnUploadTranslationClick(ClickEvent event) {
	}
}
