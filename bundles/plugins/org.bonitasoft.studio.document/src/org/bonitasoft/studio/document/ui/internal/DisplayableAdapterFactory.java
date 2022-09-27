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
package org.bonitasoft.studio.document.ui.internal;

import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.document.core.repository.DocumentFileStore;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.swt.graphics.Image;

/**
 * Adapts {@link DocumentFileStore} or {@link DocumentRepositoryStore} to {@link IDisplayable}
 * 
 * @author Vincent Hemery
 */
public class DisplayableAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isAssignableFrom(IDisplayable.class)) {
            if (adaptableObject instanceof DocumentFileStore) {
                DocumentFileStore store = (DocumentFileStore) adaptableObject;
                IDisplayable display = store::getName;
                return (T) display;
            } else if (adaptableObject instanceof DocumentRepositoryStore) {
                IDisplayable display = new IDisplayable() {

                    @Override
                    public String getDisplayName() {
                        return Messages.documentRepository;
                    }

                    @Override
                    public Image getIcon() {
                        return Pics.getImage(PicsConstants.attachmentData);
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
