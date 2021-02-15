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
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.bonitasoft.plugin.analyze.report.model.Definition;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;

public class JarBundleResourceLoader implements BundleResourceLoader {

    private Definition definition;

    public JarBundleResourceLoader(Definition definition) {
        this.definition = definition;
    }

    @Override
    public ResourceBundle getResourceBundle(final Locale locale) {
        String baseName = definition.getJarEntry();
        if (baseName.lastIndexOf(".") != -1) {
            baseName = baseName.substring(0, baseName.lastIndexOf("."));
        }
        File file = new File(definition.getFilePath());
        if (file.isFile()) {
            try (URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { file.toURI().toURL() })) {
                return ResourceBundle.getBundle(baseName, locale, urlClassLoader);
            } catch (final MissingResourceException | IOException e) {
                BonitaStudioLog.warning(e.getMessage(), CommonRepositoryPlugin.PLUGIN_ID);

            }
        }
        return null;
    }

}
