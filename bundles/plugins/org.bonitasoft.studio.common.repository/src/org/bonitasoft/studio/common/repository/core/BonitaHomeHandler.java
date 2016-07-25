/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import java.io.File;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.resources.IProject;

public class BonitaHomeHandler {

    public static final String H2_DATABASE_FOLDER_NAME = "h2_database";
    public static final String DB_LOCATION_PROPERTY = "org.bonitasoft.h2.database.dir";

    private final IProject project;

    public BonitaHomeHandler(final IProject project) {
        this.project = project;
    }

    public void cleanTenant() {
        deleteBonitaDbFiles();
    }

    public void deleteBusinessDataDBFiles() {
        deleteDbFiles("business");
    }

    protected void deleteBonitaDbFiles() {
        deleteDbFiles("bonita");
    }

    protected void deleteDbFiles(final String fileStartName) {
        final File workDir = getDBLocation();
        if (workDir != null && workDir.exists()) {
            for (final File file : workDir.listFiles()) {
                final String fileName = file.getName();
                if (fileName.endsWith("h2.db") && fileName.contains(fileStartName)) {
                    PlatformUtil.delete(file, null);
                    if (file.exists()) {
                        BonitaStudioLog.info(fileName + " failed to be deleted", CommonRepositoryPlugin.PLUGIN_ID);
                    } else {
                        BonitaStudioLog.info(fileName + " has been deleted successfuly", CommonRepositoryPlugin.PLUGIN_ID);
                    }
                }
            }
        }
    }

    public File getDBLocation() {
        return new File(project.getLocation().toFile(), H2_DATABASE_FOLDER_NAME);
    }


}
