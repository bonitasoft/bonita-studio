/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.extension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.bonitasoft.engine.bpm.bar.BarResource;

public class BarResourcesProviderUtil {

    public static void addFileContents(final List<BarResource> resources, final File file) throws FileNotFoundException, IOException {
        if (file.exists()) {
            addFileContents(resources, file, file.getName());
        }
    }

    public static void addFileContents(final List<BarResource> resources, final File file, final String barResourceName) throws FileNotFoundException,
            IOException {
        if (file.exists()) {
            final byte[] jarBytes = new byte[(int) file.length()];
            final InputStream stream = new FileInputStream(file);
            stream.read(jarBytes);
            stream.close();
            resources.add(new BarResource(barResourceName, jarBytes));
        }
    }

}
