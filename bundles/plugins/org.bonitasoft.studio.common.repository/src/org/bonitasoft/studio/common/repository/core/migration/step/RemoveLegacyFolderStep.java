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
package org.bonitasoft.studio.common.repository.core.migration.step;

import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class RemoveLegacyFolderStep implements MigrationStep {

    public static final Set<String> LEGACY_REPOSITORIES = Set.of("application_resources",
            "forms",
            "widgets",
            "looknfeels",
            "validators",
            "src-validators",
            "simulation",
            "customTypes",
            "src-customTypes");
    
    @Override
    public MigrationReport run(IProject project, IProgressMonitor monitor) throws CoreException {
        LEGACY_REPOSITORIES.stream().forEach(folderName -> removeFolder(project, folderName, monitor));
        return MigrationReport.emptyReport();
    }
    
    private void removeFolder(IProject project, String folderName, final IProgressMonitor monitor) {
        IFolder resourceFolder = project.getFolder(folderName);
        if (resourceFolder.exists()) {
            try {
                resourceFolder.delete(true, monitor);
            } catch (CoreException e) {
                BonitaStudioLog.error(String.format("Failed to delete folder %s during migration", folderName),
                        CommonRepositoryPlugin.PLUGIN_ID);
            }
            BonitaStudioLog.info(String.format("Folder %s has been removed during migration", folderName),
                    CommonRepositoryPlugin.PLUGIN_ID);
        }
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.8.0")) < 0;
    }

}
