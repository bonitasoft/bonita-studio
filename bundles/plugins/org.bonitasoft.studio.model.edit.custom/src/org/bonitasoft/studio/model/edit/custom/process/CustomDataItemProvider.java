/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.edit.custom.process;

import java.net.URL;

import org.bonitasoft.studio.model.edit.custom.EMFEditCustomPlugin;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.provider.DataItemProvider;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class CustomDataItemProvider extends DataItemProvider {

    public CustomDataItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(final Object object) {
        final Data d = (Data) object;
        return "Data " + d.getName();
    }

    @Override
    public Object getImage(final Object object) {
        final Object icon = super.getImage(object);
        if (object instanceof Data) {
            Image iconImage = null;
            if (icon instanceof URL) {
                iconImage = ExtendedImageRegistry.getInstance().getImage(icon);
            } else if (icon instanceof Image) {
                iconImage = (Image) icon;
            }
            if (iconImage != null) {
                final boolean formTransient = ((Data) object).getDatasourceId() != null && ((Data) object).getDatasourceId().equals("PAGEFLOW");
                if (formTransient) {
                    Image img = EMFEditCustomPlugin.getDefault().getImageRegistry().get("decoratedImageFor" + ((EObject) object).eClass().getName());
                    if (img == null) {
                        img = new DecorationOverlayIcon(iconImage, Pics.getImageDescriptor("form_decorator.png", EMFEditCustomPlugin.getDefault()),
                                IDecoration.BOTTOM_LEFT).createImage();
                        EMFEditCustomPlugin.getDefault().getImageRegistry().put("decoratedImageFor" + ((EObject) object).eClass().getName(), img);
                    }
                    return img;
                }
            }
        }
        return icon;
    }

}
