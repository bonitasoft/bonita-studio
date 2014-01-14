/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.pics;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * @author Mickael Istria
 *
 */
public class Pics extends AbstractUIPlugin{

    // The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.pics"; //$NON-NLS-1$

    // The shared instance
    private static Pics plugin;

    private static ImageDescriptor wizban;

    private static class ClassPathResourceImageDescriptor extends ImageDescriptor {

        private final String file;

        public ClassPathResourceImageDescriptor(String file) {
            this.file = file;
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.resource.ImageDescriptor#getImageData()
         */
        @Override
        public ImageData getImageData() {
            return new ImageData(Pics.class.getResourceAsStream(file));
        }

    }

    /**
     * @return
     */
    public static ImageDescriptor getWizban() {
        if (wizban == null) {
            wizban = new ClassPathResourceImageDescriptor("/icons/wizban/wizard.png"); //$NON-NLS-1$
        }
        return wizban;
    }

    /**
     * Returns the given image. The image will be managed by the plugin's
     * image registry.
     * 
     * @param imageName a pathname relative to the icons directory of
     * this project.
     */
    public static Image getImage(String imageName) {
        if(PicsConstants.hint.equals(imageName)){
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
        }else{
            return getImage(imageName,plugin);
        }
    }

    public static Image getImage(String imageName, AbstractUIPlugin plugin) {
        ImageRegistry reg = plugin.getImageRegistry();

        Image result = reg.get(imageName);

        if (result != null && !result.isDisposed()) {//prevent from bad dispose
            return result;
        }

        ImageDescriptor descriptor = getImageDescriptor(imageName,plugin) ;
        if(descriptor != null){
            result = descriptor.createImage();
        }

        reg.remove(imageName);
        if(result != null){
            reg.put(imageName, result);
        }

        return result;
    }

    public static ImageDescriptor getImageDescriptor(String imageName, AbstractUIPlugin plugin) {
        ImageDescriptor desc;
        try {
            desc =
                    ImageDescriptor.createFromURL(
                            new URL(
                                    plugin.getBundle().getResource("/"), //$NON-NLS-1$
                                    "icons/" + imageName)); //$NON-NLS-1$
        } catch (MalformedURLException e) {
            desc = ImageDescriptor.getMissingImageDescriptor();
        }

        return desc;
    }

    /**
     * Returns the given image descriptor. The caller will be responsible
     * for deallocating the image if it creates the image from the descriptor
     * 
     * @param imageName is a pathname relative to the icons directory
     * within this project.
     */
    public static ImageDescriptor getImageDescriptor(String imageName) {
        return getImageDescriptor(imageName,plugin);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * @param connectorCategories
     * @param element
     */
    public static Image getImage(String connectorCategories, String element) {
        return getImage(connectorCategories + element + ".png"); //$NON-NLS-1$
    }


    public static Image getFlag(Locale locale) {
        return getImage("/flags/" + locale.getCountry() + ".png"); //$NON-NLS-1$
    }

    /**
     *  get the system image resized in 16x16 (only used in connectors label provider)
     * @param id
     * @return
     */
    public static Image getSystemImage(int id){
        ImageRegistry reg = plugin.getImageRegistry();

        String imageName = id+"";
        Image result = reg.get(imageName);

        if (result != null && !result.isDisposed()) {//prevent from bad dispose
            return result;
        }
        result = Display.getCurrent().getSystemImage(id);
        Image scaled = new Image(Display.getDefault(), 16, 16);
        GC gc = new GC(scaled);
        gc.setAntialias(SWT.ON);
        gc.setInterpolation(SWT.HIGH);
        gc.drawImage(result, 0, 0, result.getBounds().width, result.getBounds().height, 0, 0, 16, 16);
        gc.dispose();
        result = scaled;
        reg.remove(imageName);
        reg.put(imageName, result);
        return result;
    }

}
