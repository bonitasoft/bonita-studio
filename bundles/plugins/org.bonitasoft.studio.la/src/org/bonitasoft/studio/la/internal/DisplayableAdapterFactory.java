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
package org.bonitasoft.studio.la.internal;

import org.bonitasoft.studio.application.ui.control.model.RepositoryFileStoreDisplayable;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.swt.graphics.Image;

/**
 * Adapts {@link ApplicationFileStore} or {@link ApplicationRepositoryStore} to {@link IDisplayable}
 * 
 * @author Vincent Hemery
 */
public class DisplayableAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isAssignableFrom(IDisplayable.class)) {
            if (adaptableObject instanceof ApplicationFileStore) {
                ApplicationFileStore store = (ApplicationFileStore) adaptableObject;
                IDisplayable display = new RepositoryFileStoreDisplayable(store) {

                    @Override
                    public Image getIcon() {
                        return IDisplayable.adapt(store.getParentStore()).map(IDisplayable::getIcon).orElse(null);
                    }
                };
                return (T) display;
            } else if (adaptableObject instanceof ApplicationRepositoryStore) {
                IDisplayable display = new IDisplayable() {

                    @Override
                    public String getDisplayName() {
                        return Messages.applicationStoreName;
                    }

                    @Override
                    public Image getIcon() {
                        return Pics.getImage(PicsConstants.application);
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
