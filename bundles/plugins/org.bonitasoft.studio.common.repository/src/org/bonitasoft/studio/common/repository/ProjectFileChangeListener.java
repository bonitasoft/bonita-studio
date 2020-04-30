/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository;

import java.util.Objects;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class ProjectFileChangeListener implements IResourceChangeListener {

    protected Repository repository;

    public ProjectFileChangeListener(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        try {
            if (event != null && event.getDelta() != null) {
                event.getDelta().accept(new IResourceDeltaVisitor() {

                    @Override
                    public boolean visit(IResourceDelta delta) throws CoreException {
                        IProject project = repository.getProject();
                        if (project.isAccessible()) {
                            IFile projectFile = project.getFile(".project");
                            final IResource resource = delta.getResource();
                            if (Objects.equals(resource, projectFile)) {
                                return checkVersion(project);
                            }
                        }
                        return true;
                    }
                });
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    public boolean checkVersion(IProject project) {
        String version = repository.getVersion();
        if (!ProductVersion.CURRENT_VERSION.equals(version)) {
            if (ProductVersion.canBeMigrated(version)) {
                openMigrationDialog();
            } else {
                openErrorDialog(project, version);
            }
            return false;
        }
        return true;
    }

    protected void openErrorDialog(IProject project, String version) {
        Display.getDefault().asyncExec(() -> MessageDialog
                .openError(Display.getDefault().getActiveShell(),
                        Messages.repositoryError,
                        String.format(Messages.repositoryError, project.getName(),
                                version,
                                ProductVersion.CURRENT_VERSION)));
    }

    protected void openMigrationDialog() {
        Display.getDefault()
                .asyncExec(() -> {
                    MessageDialog.open(MessageDialog.INFORMATION,
                            Display.getDefault().getActiveShell(),
                            Messages.migrationTitle,
                            String.format(Messages.mustMigrationMsg,
                                    ProductVersion.CURRENT_VERSION),
                            SWT.NONE, Messages.migrate);
                    repository.runMigrationInDialog();
                });
    }

}
