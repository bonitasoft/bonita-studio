/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import java.io.File;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.spi.history.History;

/**
 * @author Romain Bioteau
 */
public class ModelVersion {

    public static final String VERSION_6_0_0_ALPHA = "6.0.0-Alpha";
    public static final String CURRENT_VERSION = lastModelVersion();

    private static String lastModelVersion() {
        try {
            final URL resource = Platform.getBundle("org.bonitasoft.studio-models").getResource("process.history");
            final URI historyURI = URI.createFileURI(new File(FileLocator.toFileURL(resource).getFile()).getAbsolutePath());
            final History history = ResourceUtils.loadElement(historyURI);
            return history.getLatestRelease().getLabel();
        } catch (final Throwable t) {
            BonitaStudioLog.error("Failed to load model version from process history", Activator.PLUGIN_ID);
            return VERSION_6_0_0_ALPHA;
        }
    }

}
