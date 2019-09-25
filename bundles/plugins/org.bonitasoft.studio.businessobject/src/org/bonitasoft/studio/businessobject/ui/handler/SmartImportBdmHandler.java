/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.handler;

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.inject.Named;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.bonitasoft.studio.businessobject.core.operation.SmartImportBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.SmartImportBdmPage;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.SmartImportBdmValidator;
import org.bonitasoft.studio.common.ZipUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.xml.sax.SAXException;

public class SmartImportBdmHandler {

    private boolean smartImport = false;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, RepositoryAccessor repositoryAccessor) {
        SmartImportBdmPage page = new SmartImportBdmPage(repositoryAccessor);
        Optional<IStatus> status = createWizard(newWizard(), page, repositoryAccessor).open(activeShell,
                Messages.importButtonLabel);
        if (status.isPresent()) {
            IStatus s = status.get();
            if (s.isOK()) {
                MessageDialog.openInformation(activeShell,
                        smartImport ? Messages.smartImportCompletedTitle : Messages.bdmImportedTitle,
                        smartImport ? Messages.smartImportCompleted : Messages.bdmImported);
            } else if (s.isMultiStatus()) {
                new MultiStatusDialog(activeShell, Messages.smartImportImpossibleTitle,
                        String.format("%s\n%s", Messages.smartImportImpossible, Messages.updateModelsHint),
                        new String[] { IDialogConstants.OK_LABEL }, (MultiStatus) s).open();
            } else {
                MessageDialog.openError(activeShell, Messages.smartImportImpossibleTitle, s.getMessage());
            }
        }
    }

    private WizardBuilder<IStatus> createWizard(WizardBuilder<IStatus> builder, SmartImportBdmPage page,
            RepositoryAccessor repositoryAccessor) {
        return builder
                .withTitle(Messages.importBdm)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.importBdm)
                        .withDescription(Messages.importBdmDescription)
                        .withControl(page))
                .onFinish(container -> Optional.ofNullable(performFinish(repositoryAccessor, page)));
    }

    private IStatus performFinish(RepositoryAccessor repositoryAccessor, SmartImportBdmPage page) {
        smartImport = page.performSmartImport();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore fileStore = repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        try {
            File fileToMerge = ZipUtil.unzip(new File(page.getFilePath()))
                    .resolve(BusinessObjectModelFileStore.BOM_FILENAME)
                    .toFile();
            if (fileStore == null) {
                fileStore = (BusinessObjectModelFileStore) repositoryStore
                        .createRepositoryFileStore(BusinessObjectModelFileStore.BOM_FILENAME);
            } else if (smartImport) {
                IStatus status = new SmartImportBdmValidator(fileStore).validate(fileToMerge);
                if (!status.isOK()) {
                    return status;
                }

                try {
                    SmartImportBDMOperation operation = new SmartImportBDMOperation(fileStore, fileToMerge);
                    PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
                    return operation.getStatus();
                } catch (InvocationTargetException | InterruptedException e) {
                    return ValidationStatus.error(e.getMessage(), e.getCause());
                }
            }
            fileStore.save(repositoryStore.getConverter().unmarshall(FileUtils.readFileToByteArray(fileToMerge)));
            return ValidationStatus.ok();
        } catch (IOException | JAXBException | SAXException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(Messages.archiveContentInvalid);
        }
    }

}
