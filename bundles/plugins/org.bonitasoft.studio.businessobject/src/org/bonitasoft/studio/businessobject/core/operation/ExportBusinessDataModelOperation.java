/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.core.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.Repository;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 * 
 */
public class ExportBusinessDataModelOperation implements IRunnableWithProgress {

    private String destinationFilePath;

    private BusinessObjectModelFileStore bdmFileStore;

    public ExportBusinessDataModelOperation() {

    }

    public ExportBusinessDataModelOperation(String destinationFilePath, BusinessObjectModelFileStore bdmFileStore) {
        this.destinationFilePath = destinationFilePath;
        this.bdmFileStore = bdmFileStore;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(destinationFilePath, "destinationFilePath");
        Assert.isNotNull(bdmFileStore, "bdmFileStore");
        if (monitor == null) {
            monitor = Repository.NULL_PROGRESS_MONITOR;
        }

        monitor.beginTask(Messages.exportingBusinessDataModel, IProgressMonitor.UNKNOWN);
        boolean disablePopup = FileActionDialog.getDisablePopup();
        try {
            FileActionDialog.setDisablePopup(true);
            bdmFileStore.export(destinationFilePath);
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        } finally {
            FileActionDialog.setDisablePopup(disablePopup);
        }
    }

    public void setDestinationFilePath(String destinationFilePath) {
        this.destinationFilePath = destinationFilePath;
    }

    public void setBdmFileStore(BusinessObjectModelFileStore bdmFileStore) {
        this.bdmFileStore = bdmFileStore;
    }

}
