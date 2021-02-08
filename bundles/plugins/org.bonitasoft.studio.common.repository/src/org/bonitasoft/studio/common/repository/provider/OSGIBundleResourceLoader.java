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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.Bundle;

public class OSGIBundleResourceLoader implements BundleResourceLoader {

    private Bundle bundle;
    private Resource resource;
    private IRepositoryStore<?> store;

    public OSGIBundleResourceLoader(Resource resource, Bundle bundle, IRepositoryStore<?> store) {
        this.resource = resource;
        this.bundle = bundle;
        this.store = store;
    }

    @Override
    public ResourceBundle getResourceBundle(final Locale locale) {
        final IRepositoryFileStore<?> fileStore = store.getChild(URI.decode(resource.getURI().lastSegment()), true);
        if (fileStore == null) {
            return null;
        }
        String baseName = URI.decode(resource.getURI().lastSegment());
        if (baseName.lastIndexOf(".") != -1) {
            baseName = baseName.substring(0, baseName.lastIndexOf("."));
        }
        try {
            if (fileStore.canBeShared()) {
                ResourceBundle.clearCache();
            }
            return ResourceBundle.getBundle(baseName, locale, new DefinitionControl(this.bundle, store.getName()));
        } catch (final MissingResourceException e) {
            try {
                ResourceBundle.clearCache(); //Clear the cache to always have updated i18n for custom connectors
                return ResourceBundle.getBundle(baseName, locale,
                        new StoreControl(store.getResource().getLocation().toFile().getAbsolutePath()));
            } catch (final MissingResourceException e1) {
                return null;
            }
        }
    }

}
