package internationalization.translate.server;

import internationalization.translate.client.FileUploadService;
import internationalization.translate.client.UiTextTranslation;
import internationalization.translate.server.db.UiTextKeyDb;
import internationalization.translate.server.db.UiTextTranslationDb;
import internationalization.translate.utils.AcceptFileUploadServlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class UpdateDbServlet extends GwtFileUploadServlet implements FileUploadService {

	@Override
	public void onFileUploadFinished() {
		if (files.size() > 0) {
			InputStream file = files.get(0);
			if (target.equalsIgnoreCase("key"))
				UiTextKeyDb.readInKeys(file);
			else if (target.equalsIgnoreCase("translation"))
				UiTextTranslationDb.readInTranslations(file);
		}

	}

	@Override
	public void onFileUploadFinished(InputStream file) {
		if (target.equalsIgnoreCase("key"))
			UiTextKeyDb.readInKeys(file);
		else if (target.equalsIgnoreCase("translation"))
			UiTextTranslationDb.readInTranslations(file);
	}

//	@Override
//	public void onFileUploadFinished() {
//		super.onFileUploadFinished();
//		
//	}

}
