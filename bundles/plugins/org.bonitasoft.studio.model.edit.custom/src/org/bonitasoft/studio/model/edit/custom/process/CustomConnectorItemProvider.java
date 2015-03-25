package org.bonitasoft.studio.model.edit.custom.process;

import java.io.IOException;
import java.io.InputStream;

import org.bonitasoft.studio.model.process.provider.ConnectorItemProvider;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class CustomConnectorItemProvider extends ConnectorItemProvider {

    private Image image;

    public CustomConnectorItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (image != null) {
            image.dispose();
        }
    }

    protected Image getIcon(final InputStream iconStream) throws IOException {
        if (iconStream != null) {
            final Image res = new Image(Display.getCurrent(), new ImageData(iconStream).scaledTo(16, 16));
            iconStream.close();
            return res;
        } else {
            return resiezedImage(Pics.getImage(PicsConstants.connectorCategories, "connector"));
        }
    }

    protected Image resiezedImage(final Image image) {
        if (image != null && image.getImageData() != null) {
            final ImageData data = image.getImageData().scaledTo(16, 16);
            return new Image(Display.getDefault(), data);
        } else {
            return image;
        }
    }
}
