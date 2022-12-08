/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.handler;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.IProgressService;

public class DefineBusinessDataModelHandler {

    @Execute
    public void defineBusinessDataModel(RepositoryAccessor repositoryAccessor, IProgressService progressService) {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> bomRepositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore bdmFileStore = bomRepositoryStore.getChild(
                BusinessObjectModelFileStore.BOM_FILENAME,
                false);
        if (bdmFileStore == null) {
            try {
                progressService.run(true, false, monitor -> {
                    try {
                        createBdmFileStore(repositoryAccessor, monitor);
                    } catch (CoreException e) {
                       throw new InvocationTargetException(e);
                    }
                });
            } catch (InvocationTargetException | InterruptedException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getCurrent().getActiveShell(), "Failed to create the BDM.", e);
                return;
            }
            bdmFileStore = bomRepositoryStore.getChild(
                    BusinessObjectModelFileStore.BOM_FILENAME,
                    false);
        }
        bdmFileStore.open();
    }

    private void createBdmFileStore(RepositoryAccessor repositoryAccessor,
            IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.creatingBusinessDataModel, IProgressMonitor.UNKNOWN);
        var bdmStore = repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        var project = Adapters.adapt(repositoryAccessor.getCurrentRepository().orElseThrow(), BonitaProject.class);
        if (project != null && !project.getBdmParentProject().exists()) {
            bdmStore.createBdmModule(project, monitor);
        }
    }

}
