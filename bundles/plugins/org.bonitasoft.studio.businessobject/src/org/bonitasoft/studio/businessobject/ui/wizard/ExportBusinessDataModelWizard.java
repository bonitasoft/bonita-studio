/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.businessobject.core.operation.ExportBusinessDataModelOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ExportBusinessDataModelWizard extends Wizard {

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store;

    private ExportBusinessDataModelWizardPage wizardPage;

    public ExportBusinessDataModelWizard(
            BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store) {
        this.store = store;
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.export);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        wizardPage = createExportBusinessObjectWizardPage();
        addPage(wizardPage);
    }

    protected ExportBusinessDataModelWizardPage createExportBusinessObjectWizardPage() {
        ExportBusinessDataModelWizardPage wizardPage = new ExportBusinessDataModelWizardPage(getStore());
        wizardPage.setTitle(Messages.exportBusinessDataModelTitle);
        wizardPage.setDescription(Messages.bind(Messages.exportBusinessDataModelDescription,
                org.bonitasoft.studio.common.Messages.bonitaPortalModuleName));
        return wizardPage;
    }

    protected BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> getStore() {
        return store;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        try {
            String destinationPath = getTargetFileName();
            File file = new File(destinationPath);
            File targetFolder = file.getParentFile();
            if (!targetFolder.exists()) {
                return false;
            }
            if (file.exists() && !askOverwriteFile(destinationPath)) {
                return false;
            }
            runExportOperationInWizard(file.getParent());
        } catch (InvocationTargetException e) {
            BonitaStudioLog.error(e);
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.error, e.getCause().getMessage(),
                    e.getCause()).open();
            return false;
        } catch (InterruptedException e) {
            BonitaStudioLog.error(e);
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.error, e.getCause().getMessage(),
                    e.getCause()).open();
            return false;
        }
        openSuccessDialog();
        return true;
    }

    protected boolean askOverwriteFile(String destinationPath) {
        return FileActionDialog.overwriteQuestion(destinationPath);
    }

    protected void openSuccessDialog() {
        MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.exportCompleted,
                Messages.bind(Messages.exportBusinessDataModelSuccessFull, getTargetFileName()));
    }

    protected String getTargetFileName() {
        String destinationPath = wizardPage.getDestinationPath();
        if (!destinationPath.endsWith(File.separator)) {
            destinationPath = destinationPath + File.separatorChar;
        }
        return destinationPath + BusinessObjectModelFileStore.ZIP_FILENAME;
    }

    protected BusinessObjectModelFileStore getDefaultArtifact() {
        return getStore().getChild(BusinessObjectModelFileStore.BOM_FILENAME);
    }

    protected void runExportOperationInWizard(final String destinationPath) throws InvocationTargetException,
            InterruptedException {
        getContainer().run(true, false, getExportRunnable(destinationPath));
    }

    protected IRunnableWithProgress getExportRunnable(
            final String destinationPath) {
        return new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                try {
                    exportBusinessDataModel(destinationPath, getDefaultArtifact(), monitor);
                } catch (IOException e) {
                    throw new InvocationTargetException(e);
                }
            }
        };
    }

    protected void exportBusinessDataModel(String targetPath, BusinessObjectModelFileStore fileStore,
            IProgressMonitor monitor) throws IOException,
            InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.exportingBusinessDataModel, IProgressMonitor.UNKNOWN);
        ExportBusinessDataModelOperation exportOperation = createExportBDMOperation(targetPath, fileStore);
        exportOperation.run(monitor);
    }

    protected ExportBusinessDataModelOperation createExportBDMOperation(String targetPath,
            BusinessObjectModelFileStore bdmFileStore) {
        return new ExportBusinessDataModelOperation(targetPath, bdmFileStore);
    }

}
