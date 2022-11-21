/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.repository;

import static java.util.function.Predicate.not;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.resources.IFolder;

/**
 * @author Romain Bioteau
 */
public class WebWidgetRepositoryStore extends WebArtifactRepositoryStore<WebWidgetFileStore> {

    public static final String WEB_WIDGET_REPOSITORY_NAME = "web_widgets";

    @Override
    public String getName() {
        return WEB_WIDGET_REPOSITORY_NAME;
    }
    
    @Override
    public boolean canBeExported() {
        return getChildren().stream().anyMatch(IRepositoryFileStore::canBeExported);
    }

    @Override
    public WebWidgetFileStore createRepositoryFileStore(final String widgetFolderName) {
        IFolder folder = getResource().getFolder(widgetFolderName);
        if (folder.exists()) {
            return folder.getFile(widgetFolderName + ".json").exists() ? new WebWidgetFileStore(widgetFolderName, this)
                    : null;
        }
        return new WebWidgetFileStore(widgetFolderName, this);
    }

}
