/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.provider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public class JarDefinitionResourceLoader implements DefinitionImageResourceLoader {

    private File file;

    public JarDefinitionResourceLoader(File file) {
        this.file = file;
    }

    @Override
    public Image getIcon(ConnectorDefinition definition) {
        return loadImage(definition.getIcon());
    }

    @Override
    public Image getIcon(Category category) {
        return loadImage(category.getIcon());
    }

    private Image loadImage(String iconPath) {
        if (iconPath != null) {
            try {
                URL jarFileURL = file.toURI().toURL();
                URL imageURL = new URL("jar:" + jarFileURL.toExternalForm() + "!/" + iconPath);
                return ImageDescriptor
                        .createFromURL(imageURL)
                        .createImage();
            } catch (MalformedURLException e) {
                BonitaStudioLog.error(e);
                return null;
            }
        }
        return null;
    }

}
