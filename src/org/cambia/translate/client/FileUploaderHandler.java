package org.cambia.translate.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

public class FileUploaderHandler implements IUploader.OnChangeUploaderHandler,  IUploader.OnFinishUploaderHandler {

	protected FlowPanel panelImages;
	private TextBox tbInputFile;

	public FileUploaderHandler(FlowPanel panelImages, TextBox tbInputFile) {
		this.panelImages = panelImages;
		this.tbInputFile = tbInputFile;
	}

	@Override
    public void onFinish(IUploader uploader) {
	      if (uploader.getStatus() == Status.SUCCESS) {

	        new PreloadedImage(uploader.fileUrl(), showImage);
	        
	        // The server sends useful information to the client by default
	        UploadedInfo info = uploader.getServerInfo();
	        System.out.println("File name " + info.name);
	        System.out.println("File content-type " + info.ctype);
	        System.out.println("File size " + info.size);

	        // You can send any customized message and parse it 
	        System.out.println("Server message " + info.message);
	      }
	    }
	    
	    // Attach an image to the pictures viewer
	    OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
	      public void onLoad(PreloadedImage image) {
	        image.setWidth("75px");
	        panelImages.add(image);
	      }
	    };

	@Override
	public void onChange(IUploader uploader) {
		tbInputFile.setText(uploader.getFileName());
		
	}

}
