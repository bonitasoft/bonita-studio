/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.advisor;

import java.io.File;
import java.io.FileFilter;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.importer.ImporterRegistry;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * @author Romain Bioteau
 */
public class InitWorkspaceAdvisor extends InstallerApplicationWorkbenchAdvisor {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#executePostStartupHandler()
     */
    @Override
    protected void executePostStartupHandler() {
        final File[] repositoryToImport = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().listFiles(new FileFilter() {

            @Override
            public boolean accept(final File file) {
                return file.getName().endsWith(".bos");
            }
        });

        if (repositoryToImport != null && repositoryToImport.length > 0) {
            for (final File workspaceArchive : repositoryToImport) {
                final String name = workspaceArchive.getName();
                final String repositoryName = name.substring(0, name.lastIndexOf("."));
                RepositoryManager.getInstance().setRepository(repositoryName, Repository.NULL_PROGRESS_MONITOR);
                final IRepository repository = RepositoryManager.getInstance().getRepository(repositoryName);
                if (repository != null) {
                    try {
                        FileActionDialog.setDisablePopup(true);
                        final ToProcProcessor importer = ImporterRegistry.getInstance().createImporterFor(name);
                        if (importer != null) {
                            importer.setRepository(repositoryName);
                            importer.createDiagram(workspaceArchive.toURL(), Repository.NULL_PROGRESS_MONITOR);
                        }
                        workspaceArchive.delete();
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
            RepositoryManager.getInstance().setRepository("default", Repository.NULL_PROGRESS_MONITOR);
        }
    }
}
