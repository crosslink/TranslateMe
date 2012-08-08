package internationalization.translate.server.db;

import java.util.List;

import internationalization.translate.client.AppResources;
import internationalization.translate.client.db.UiTextGroup;
import internationalization.translate.client.db.UiTextGroupTable;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;

public class UiTextLanguageTableImpl {
	public static String checkLanguage(String language, String comment) {
		String langKey = AppResources.langToKey(language);
        DatastoreService datastore =
                DatastoreServiceFactory.getDatastoreService();
        
        
        Query q = new Query(UiTextGroupTable.UI_TEXT_TRANSLATION_GROUP_TABLE);
        q.addFilter(UiTextGroup.ATTRIBUTE_LANG_KEY, Query.FilterOperator.EQUAL, langKey);

        PreparedQuery pq = datastore.prepare(q);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withDefaults());

        if (entities.size() > 0) {
//       int count = 0;
//       for(Entity e : entities) {
//        	UiTextTranslation tran = new UiTextTranslation();
//        	tran.setLangKey((String) e.getProperty(UiTextTranslation.ATTRIBUTE_LANG_KEY));
//        	tran.setKey((String) e.getProperty(UiTextTranslation.ATTRIBUTE_KEY));
//        	Text text = (Text) e.getProperty(UiTextTranslation.ATTRIBUTE_TEXT);
//    		tran.setText(text.getValue());
//    		tran.setLanguage((String) e.getProperty(UiTextTranslation.ATTRIBUTE_LANGUAGE));
//        }
        }
        else {
			Entity langEntity = UiTextGroup.newEntity(langKey, language, comment);
			datastore.put(langEntity);
        }
		return langKey;
	}
}
