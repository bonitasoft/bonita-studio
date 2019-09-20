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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import javax.inject.Named;
import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.ImportAndMergeBdmPage;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.MergeBdmValidator;
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
import org.xml.sax.SAXException;

public class ImportBdmHandler {

    private boolean mergeOnImport = false;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, RepositoryAccessor repositoryAccessor) {
        ImportAndMergeBdmPage page = new ImportAndMergeBdmPage(repositoryAccessor);
        Optional<IStatus> status = createWizard(newWizard(), page, repositoryAccessor).open(activeShell,
                Messages.importButtonLabel);
        if (status.isPresent()) {
            IStatus s = status.get();
            if (s.isOK()) {
                MessageDialog.openInformation(activeShell,
                        mergeOnImport ? Messages.mergeCompletedTitle : Messages.bdmImportedTitle,
                        mergeOnImport ? Messages.mergeCompleted : Messages.bdmImported);
            } else if (s.isMultiStatus()) {
                new MultiStatusDialog(activeShell, Messages.mergeImpossibleTitle, Messages.mergeImpossible,
                        new String[] { IDialogConstants.OK_LABEL }, (MultiStatus) s).open();
            } else {
                MessageDialog.openError(activeShell, Messages.mergeImpossibleTitle, s.getMessage());
            }
        }
    }

    private WizardBuilder<IStatus> createWizard(WizardBuilder<IStatus> builder, ImportAndMergeBdmPage page,
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

    private IStatus performFinish(RepositoryAccessor repositoryAccessor, ImportAndMergeBdmPage page) {
        mergeOnImport = page.mergeOnImport();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore fileStore = repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        try {
            BusinessObjectModel newModel = retrieveModelToMerge(repositoryStore.getConverter(), page.getFilePath());
            if (fileStore == null) {
                fileStore = (BusinessObjectModelFileStore) repositoryStore
                        .createRepositoryFileStore(BusinessObjectModelFileStore.BOM_FILENAME);
            } else if (mergeOnImport) {
                return validateAndMerge(fileStore, newModel);
            }
            save(fileStore, newModel);
            return ValidationStatus.ok();
        } catch (IOException | JAXBException | SAXException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(Messages.archiveContentInvalid);
        }
    }

    private IStatus validateAndMerge(BusinessObjectModelFileStore fileStore, BusinessObjectModel modelToMerge) {
        BusinessObjectModel currentModel = fileStore.getContent();
        IStatus status = new MergeBdmValidator(currentModel).validate(modelToMerge);
        if (!status.isOK()) {
            return status;
        }
        performMerge(currentModel, modelToMerge);
        save(fileStore, currentModel);
        return ValidationStatus.ok();
    }

    private void save(BusinessObjectModelFileStore fileStore, BusinessObjectModel model) {
        fileStore.save(model);
    }

    protected void performMerge(BusinessObjectModel currentModel, BusinessObjectModel modelToMerge) {
        currentModel.getBusinessObjects().addAll(modelToMerge.getBusinessObjects());
    }

    private BusinessObjectModel retrieveModelToMerge(BusinessObjectModelConverter converter, String pathToArchive)
            throws IOException, JAXBException, SAXException {
        Path path = ZipUtil.unzip(new File(pathToArchive));
        return converter.unmarshall(Files.readAllBytes(path.resolve("bom.xml")));

    }

}
