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
import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public class BundleDefinitionImageResourceLoader implements DefinitionImageResourceLoader {

    private Bundle bundle;
    private IRepositoryStore<?> store;

    public BundleDefinitionImageResourceLoader(Bundle bundle, IRepositoryStore<?> store) {
        this.bundle = bundle;
        this.store = store;
    }

    @Override
    public Image getIcon(ConnectorDefinition connectorDefinition) {
        String icon = connectorDefinition.getIcon();
        Resource resource = connectorDefinition.eResource();
        return loadImage(icon, resource);
    }

    private Image loadImage(String icon, Resource resource) {
        File parentFolder = getParentFile(resource);
        if (parentFolder != null && parentFolder.exists() && icon != null && !icon.isEmpty()) {
            final File iconFile = new File(parentFolder, icon);
            try {
                URL iconURL = !iconFile.exists() ? lookupInBundle(icon) : iconFile.toURI().toURL();
                if (iconURL != null) {
                    return ImageDescriptor
                            .createFromURL(iconURL)
                            .createImage();
                }
            } catch (final MalformedURLException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    private URL lookupInBundle(String icon) {
        try {
            final String name = store.getResource().getName() + "/" + icon;
            final URL resourceURL = bundle.getResource(name);
            if (resourceURL != null) {
                return FileLocator.toFileURL(resourceURL);
            }
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    private File getParentFile(Resource resource) {
        File f = null;
        if (resource != null) {
            final URI uri = resource.getURI();
            f = new File(URI.decode(uri.toFileString()));
            f = f.getParentFile();
        } else {
            f = store.getResource().getLocation().toFile();
        }
        return f;
    }

    @Override
    public Image getIcon(Category category) {
        String icon = category.getIcon();
        final Resource resource = category.eResource();
        return loadImage(icon, resource);
    }

}
