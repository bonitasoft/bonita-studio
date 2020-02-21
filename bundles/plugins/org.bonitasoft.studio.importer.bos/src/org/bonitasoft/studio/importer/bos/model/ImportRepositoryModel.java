/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.bos.model;

import java.io.File;

import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.operation.ImportWorkspaceApplication;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class ImportRepositoryModel {

    private final String name;
    private final String version;
    private final String edition;
    private IStatus status;

    public ImportRepositoryModel(String name, String version, String edition) {
        this.name = name;
        this.version = version;
        this.edition = edition;
        this.status = new Status(IStatus.OK, BosArchiveImporterPlugin.PLUGIN_ID, name);
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getEdition() {
        return edition;
    }

    public IStatus getStatus() {
        return status;
    }

    public void setStatus(IStatus status) {
        this.status = status;
    }

    public File getArchiveFile() {
        final File cacheFolder = new File(System.getProperty("java.io.tmpdir"),
                ImportWorkspaceApplication.IMPORT_CACHE_FOLDER);
        return new File(cacheFolder, name + ".bos");
    }

}
