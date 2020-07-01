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

import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class WebWidgetRepositoryStore extends WebArtifactRepositoryStore<WebWidgetFileStore> {

    private static final String CUSTOM_WIDGET_ICON_PATH = "custom_widget.png";
    public static final String WEB_WIDGET_REPOSITORY_NAME = "web_widgets";

    @Override
    public String getName() {
        return WEB_WIDGET_REPOSITORY_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.widgetRepository;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(CUSTOM_WIDGET_ICON_PATH, UIDesignerPlugin.getDefault());
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
    
    @Override
    protected String getIncompatibleErrorMessage() {
        return Messages.incompatibleWebWidgetArtifact;
    }

}
