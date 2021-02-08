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
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

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
        try (InputStream is = new JarFile(file).getInputStream(new JarEntry(iconPath))) {
            return ImageDescriptor
                    .createFromImage(new Image(Display.getCurrent(), new ImageData(is)))
                    .createImage();
        } catch (IOException e) {
            return null;
        }
    }

}
