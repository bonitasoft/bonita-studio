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
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 * 
 */
public class PublishBusinessObjectWizard extends SelectBusinessObjectWizard {

    public PublishBusinessObjectWizard(
            BusinessObjectModelRepositoryStore store) {
        super(store);
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.publishBusinessObjectTitle);
    }

    @Override
    protected SelectBusinessObjectWizardPage createSelectBusinessObjectWizardPage() {
        SelectBusinessObjectWizardPage page = super.createSelectBusinessObjectWizardPage();
        page.setTitle(Messages.publishBusinessObjectTitle);
        page.setDescription(Messages.publishBusinessObjectDescription);
        return page;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        if (super.performFinish()) {
            try {
                runDeployOperationInWizard();
            } catch (InvocationTargetException e) {
                BonitaStudioLog.error(e);
                new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.error, Messages.errorWhileDeployingBDR, e.getCause()).open();
                return false;
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
                new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.error, Messages.errorWhileDeployingBDR, e.getCause()).open();
                return false;
            }
            openSuccessDialog();
            return true;
        }
        return false;
    }

    protected void openSuccessDialog() {
        MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.businessObjectPublishedTitle, Messages.businessObjectPublishedMessage);
    }

    protected void runDeployOperationInWizard() throws InvocationTargetException,
            InterruptedException {
        getContainer().run(true, false, new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException,
                    InterruptedException {
                try {
                    deployBDR(getSelectedArtifact(), monitor);
                } catch (IOException e) {
                    throw new InvocationTargetException(e);
                }
            }
        });
    }

    protected void deployBDR(BusinessObjectModelFileStore fileStore, IProgressMonitor monitor) throws IOException, InvocationTargetException,
            InterruptedException {
        DeployBDMOperation deployBDROperation = createDeployBDROperation(fileStore);
        deployBDROperation.run(monitor);
    }

    protected DeployBDMOperation createDeployBDROperation(BusinessObjectModelFileStore fileStore) {
        return new DeployBDMOperation(fileStore);
    }

}
