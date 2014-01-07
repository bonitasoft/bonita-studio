package org.bonitasoft.studio.model.edit.custom.process;

import org.bonitasoft.studio.model.process.provider.MultiInstantiationItemProvider;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class CustomMultiInstantiationItemProvider extends
		MultiInstantiationItemProvider {

	private Image image;

	public CustomMultiInstantiationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object getImage(Object object) {
		image = resiezedImage(Pics.getImage("decoration/","multiInstance_small")); 
		return  image;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if(image != null){
			image.dispose() ;
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
