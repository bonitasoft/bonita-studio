/**
 * Copyright (C) 2024 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.organization.ui.internal;

import java.util.Optional;

import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * Adapts {@link Organization} to {@link IDisplayable}
 * 
 * @author Vincent Hemery
 */
public class DisplayableAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isAssignableFrom(IDisplayable.class) && adaptableObject instanceof Organization) {
            /*
             * Organization has a "name" attribute, but it is no longer used.
             * We should rely on the resource file name without extension instead.
             * (still use name as a backup for orphan objects)
             */
            var organization = (Organization) adaptableObject;
            var display = new IDisplayable() {

                /*
                 * (non-Javadoc)
                 * @see org.bonitasoft.studio.common.ui.IDisplayable#getDisplayName()
                 */
                @Override
                public String getDisplayName() {
                    var resource = ((EObject) adaptableObject).eResource();
                    return Optional.ofNullable(resource).map(r -> r.getURI().trimFileExtension().lastSegment())
                            .orElseGet(organization::getName);
                }

                @Override
                public Image getIcon() {
                    return Pics.getImage(PicsConstants.organization);
                }
            };
            return (T) display;
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class[] { IDisplayable.class };
    }

}
