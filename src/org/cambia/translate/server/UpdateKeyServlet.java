package org.cambia.translate.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.cambia.translate.client.FileUploadService;
import org.cambia.translate.utils.AcceptFileUploadServlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class UpdateKeyServlet extends GwtFileUploadServlet implements FileUploadService {

	@Override
	public void onFileUploadFinished() {
		if (files.size() > 0) {
			InputStream file = files.get(0);
			
	        InputStreamReader stream=null;
	        BufferedReader reader=null;
	        DatastoreService datastore =
	                DatastoreServiceFactory.getDatastoreService();
	        
	        try {
				stream=new InputStreamReader(file, "UTF-8");
				reader=new BufferedReader(stream);

				
				while (true)
				{
					String line=reader.readLine();
					
//        		if (lines.size() == 0)
//        			lines.add(new String[] {line});
					
					if (line==null)
					{
						break;
					}
					
//        		System.out.println(line);

					String tokens[]=line.split("\t");
//        		if (tokens.length == 3)
//        			lines.add(tokens);
//        		else
//        			lines.add(new String[] {line});
					if (tokens.length == 3) {
						String textKey = tokens[0];
						Entity uiEntity = new Entity("Translate-Keys", textKey);
//    			        greeting.setProperty("user", user);
//    			        greeting.setProperty("date", date);
//    			        greeting.setProperty("content", content);
				

				        datastore.put(uiEntity);
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

//	@Override
//	public void onFileUploadFinished() {
//		super.onFileUploadFinished();
//		
//	}

}
