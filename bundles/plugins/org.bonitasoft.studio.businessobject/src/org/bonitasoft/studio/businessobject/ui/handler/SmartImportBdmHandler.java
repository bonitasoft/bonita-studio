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
import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditor;
import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditorContribution;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.SmartImportBdmPage;
import org.bonitasoft.studio.common.ZipUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.xml.sax.SAXException;

public class SmartImportBdmHandler extends AbstractHandler {

    private boolean smartImport = false;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        execute(Display.getDefault().getActiveShell(), new RepositoryAccessor().init());
        return null;
    }

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, RepositoryAccessor repositoryAccessor) {
        SmartImportBdmPage page = new SmartImportBdmPage(repositoryAccessor);
        Optional<BusinessDataModelEditor> openedEditor = retrieveBdmFileStore(repositoryAccessor)
                .map(BusinessObjectModelFileStore::getOpenedEditor);
        manageDirtyEditor(activeShell, openedEditor);
        Optional<IStatus> status = createWizard(newWizard(), page, repositoryAccessor).open(activeShell,
                Messages.importButtonLabel);
        if (status.isPresent()) {
            IStatus s = status.get();
            if (s.isOK()) {
                MessageDialog.openInformation(activeShell, Messages.bdmImportedTitle, Messages.bdmImported);
                updateWorkingCopy(openedEditor, repositoryAccessor);
            } else {
                MessageDialog.openError(activeShell, Messages.ImportError, s.getMessage());
            }
        }
    }

    private void updateWorkingCopy(Optional<BusinessDataModelEditor> openedEditor, RepositoryAccessor repositoryAccessor) {
        openedEditor.ifPresent(
                editor -> editor.getEditorContribution(BusinessDataModelEditorContribution.ID)
                        .filter(BusinessDataModelEditorContribution.class::isInstance)
                        .map(BusinessDataModelEditorContribution.class::cast)
                        .ifPresent(contribution -> {
                            retrieveBdmFileStore(repositoryAccessor).ifPresent(fStore -> {
                                BusinessObjectModel businessObjectModel = contribution.getConverter()
                                        .toEmfModel(fStore.getContent(), contribution.loadBdmArtifactDescriptor());
                                IObservableValue<BusinessObjectModel> workingCopyObservable = contribution
                                        .observeWorkingCopy();
                                workingCopyObservable.getRealm()
                                        .exec(() -> workingCopyObservable.setValue(businessObjectModel));
                            });
                        }));
    }

    private void manageDirtyEditor(Shell shell, Optional<BusinessDataModelEditor> openedEditor) {
        if (openedEditor.isPresent() && openedEditor.get().isDirty()) {
            if (MessageDialog.openQuestion(shell, Messages.saveBeforeImportTitle, Messages.saveBeforeImportMessage)) {
                openedEditor.get().doSave(new NullProgressMonitor());
            }
        }
    }

    private Optional<BusinessObjectModelFileStore> retrieveBdmFileStore(RepositoryAccessor repositoryAccessor) {
        return Optional.ofNullable((BusinessObjectModelFileStore) repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class).getChild("bom.xml", false));
    }

    private WizardBuilder<IStatus> createWizard(WizardBuilder<IStatus> builder, SmartImportBdmPage page,
            RepositoryAccessor repositoryAccessor) {
        return builder
                .withTitle(Messages.importBdm)
                .withSize(800, 600)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.importBdm)
                        .withDescription(Messages.importBdmDescription)
                        .withControl(page))
                .onFinish(container -> Optional.ofNullable(performFinish(repositoryAccessor, page)));
    }

    private IStatus performFinish(RepositoryAccessor repositoryAccessor, SmartImportBdmPage page) {
        smartImport = page.canPerformSmartImport();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore fileStore = repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (fileStore == null) {
            fileStore = (BusinessObjectModelFileStore) repositoryStore
                    .createRepositoryFileStore(BusinessObjectModelFileStore.BOM_FILENAME);
        } else if (smartImport) {
            try {
                SmartImportBDMOperation operation = new SmartImportBDMOperation(fileStore, page.getImportBdmModel());
                PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
                return operation.getStatus();
            } catch (InvocationTargetException | InterruptedException e) {
                return ValidationStatus.error(e.getMessage(), e.getCause());
            }
        }
        try {
            File fileToImport = ZipUtil.unzip(new File(page.getFilePath()))
                    .resolve(BusinessObjectModelFileStore.BOM_FILENAME)
                    .toFile();
            fileStore.save(repositoryStore.getConverter().unmarshall(FileUtils.readFileToByteArray(fileToImport)));
            return ValidationStatus.ok();
        } catch (IOException | JAXBException | SAXException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(Messages.archiveContentInvalid);
        }
    }

}
