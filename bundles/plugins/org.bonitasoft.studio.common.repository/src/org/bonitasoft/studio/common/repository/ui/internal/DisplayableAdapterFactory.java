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
package org.bonitasoft.studio.common.repository.ui.internal;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.swt.graphics.Image;

/**
 * Adapts {@link IRepository} to {@link IDisplayable}
 * 
 */
public class DisplayableAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isAssignableFrom(IDisplayable.class) && adaptableObject instanceof IRepository) {
            IRepository repository = (IRepository) adaptableObject;
            IDisplayable display = new IDisplayable() {

                @Override
                public Image getIcon() {
                    if (repository.isShared()) {
                        return Pics.getImage("git.png", CommonRepositoryPlugin.getDefault());
                    } else {
                        return Pics.getImage(PicsConstants.project);
                    }
                }

                @Override
                public String getDisplayName() {
                    return Optional.ofNullable(Adapters.adapt(repository, BonitaProject.class))
                                .map(BonitaProject::getDisplayName)
                                .orElse(repository.getProjectId());
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
