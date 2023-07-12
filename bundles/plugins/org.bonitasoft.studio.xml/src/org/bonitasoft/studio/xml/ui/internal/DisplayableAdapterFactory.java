/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.xml.ui.internal;

import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.xml.Messages;
import org.bonitasoft.studio.xml.repository.XSDFileStore;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

/**
 * Adapts {@link XSDFileStore} or {@link XSDRepositoryStore} to {@link IDisplayable}
 * 
 * @author Vincent Hemery
 */
public class DisplayableAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isAssignableFrom(IDisplayable.class)) {
            if (adaptableObject instanceof XSDFileStore) {
                XSDFileStore store = (XSDFileStore) adaptableObject;
                IDisplayable display = new IDisplayable() {

                    @Override
                    public String getDisplayName() {
                        if (store.getName().indexOf('.') != -1) {
                            return store.getName().substring(0, store.getName().lastIndexOf('.'));
                        }
                        return store.getName();
                    }

                    @Override
                    public Image getIcon() {
                        return Pics.getImage(PicsConstants.xml);
                    }

                    @Override
                    public StyledString getStyledString() {
                        return new StyledString(store.getName());
                    }
                };
                return (T) display;
            } else if (adaptableObject instanceof XSDRepositoryStore) {

                IDisplayable display = new IDisplayable() {

                    @Override
                    public String getDisplayName() {
                        return Messages.xsdRepositoryName;
                    }

                    @Override
                    public Image getIcon() {
                        return Pics.getImage(PicsConstants.xml);
                    }
                };
                return (T) display;
            }
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class[] { IDisplayable.class };
    }

}
