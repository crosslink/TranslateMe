package internationalization.translate.client.ui;

import internationalization.translate.client.db.UiTextTranslation;
import internationalization.translate.client.db.UiTextTranslationTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

public class TranslationCellTable extends Composite {

	private static TranslationCellTableUiBinder uiBinder = GWT
			.create(TranslationCellTableUiBinder.class);
	@UiField(provided=true) CellTable<UiTextTranslation> cellTable = new CellTable<UiTextTranslation>();

	interface TranslationCellTableUiBinder extends
			UiBinder<Widget, TranslationCellTable> {
	}

	public TranslationCellTable() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TranslationCellTable(UiTextTranslationTable tranTable) {
		initWidget(uiBinder.createAndBindUi(this));
		
		// Create key column.
	    TextColumn<UiTextTranslation> keyColumn = new TextColumn<UiTextTranslation>() {
	      @Override
	      public String getValue(UiTextTranslation tran) {
	        return tran.getKey();
	      }
	    };

	    // Create text column.
	    TextColumn<UiTextTranslation> textColumn = new TextColumn<UiTextTranslation>() {
	      @Override
	      public String getValue(UiTextTranslation tran) {
	        return tran.getKeyText();
	      }
	    };

	    // Create translation column.
	    TextColumn<UiTextTranslation> tranColumn = new TextColumn<UiTextTranslation>() {
	      @Override
	      public String getValue(UiTextTranslation tran) {
	        return tran.getText();
	      }
	    };
	    
	    // Add the columns.
	    cellTable.addColumn(keyColumn, "Key");
	    cellTable.addColumn(textColumn, "Text");
	    cellTable.addColumn(textColumn, "Translation");

	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    cellTable.setRowCount(tranTable.size(), true);

	    // Push the data into the widget.
	    cellTable.setRowData(0, tranTable.toList());
	}

}
