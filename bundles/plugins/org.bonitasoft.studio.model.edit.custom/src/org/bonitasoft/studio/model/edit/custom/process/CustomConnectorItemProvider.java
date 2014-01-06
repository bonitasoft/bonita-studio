package org.bonitasoft.studio.model.edit.custom.process;

import java.io.IOException;
import java.io.InputStream;

import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.provider.ConnectorItemProvider;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class CustomConnectorItemProvider extends ConnectorItemProvider {

	private Image image;

	public CustomConnectorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		Connector connector = (Connector) object;

//		try{
//			ConnectorDescription engineConnector = ExtensionProjectUtil.getConnectorAPI(false).getConnector(connector.getConnectorId());
//			return engineConnector.getConnectorLabel() + " - " + connector.getLabel();
//		}catch (Exception e) {
//			return connector.getConnectorId() + " - " + connector.getLabel();
//		}
		return super.getText(object) ;
	
	}
	
	@Override
	public Object getImage(Object object) {
		Connector connector = (Connector) object;
//		try{
//			ConnectorDescription engineConnector = ExtensionProjectUtil.getConnectorAPI(false).getConnector(connector.getConnectorId());
//
//			if (engineConnector != null) {
//				InputStream is = engineConnector.getIcon();
//				image = getIcon(is);
//			}
//		} catch (Exception e) {
//			image = resiezedImage(Pics.getSystemImage(SWT.ICON_ERROR));
//		}
		return super.getImage(object);
	}

	@Override
	public void dispose() {
		super.dispose();
		if(image != null){
			image.dispose() ;
		}
	}
	
	protected Image getIcon(InputStream iconStream) throws IOException {
		if (iconStream != null) {
			Image res = new Image(Display.getCurrent(), new ImageData(iconStream).scaledTo(16, 16));
			iconStream.close();
			return res;
		}else{ 
			return resiezedImage(Pics.getImage(PicsConstants.connectorCategories,"connector")) ;
		}
	}
	
	protected Image resiezedImage(Image image) {
		if(image != null && image.getImageData() != null){
			ImageData data = image.getImageData().scaledTo(16, 16) ;
			return new Image(Display.getDefault(), data);
		}else{
			return image ;
		}
	}
}
