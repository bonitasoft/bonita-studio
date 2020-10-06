/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class ExportRestApiHandler extends AbstractHandler {

    private FileStoreFinder fileStoreFinder;

    public ExportRestApiHandler() {
        fileStoreFinder = new FileStoreFinder();
    }

    @Execute
    public void export(RepositoryAccessor repositoryAccessor) {
        fileStoreFinder
                .findSelectedFileStore(repositoryAccessor.getCurrentRepository())
                .filter(RestAPIExtensionFileStore.class::isInstance)
                .map(RestAPIExtensionFileStore.class::cast)
                .ifPresent(this::export);
    }

    private void export(RestAPIExtensionFileStore fileStore) {
        getPath(Display.getDefault().getActiveShell(), fileStore).ifPresent(destinationPath -> {
            ExportBosArchiveOperation operation = new ExportBosArchiveOperation();
            operation.setDestinationPath(destinationPath);
            Set<IRepositoryFileStore> fileStoreSet = new HashSet<>();
            fileStoreSet.add(fileStore);
            operation.setFileStores(fileStoreSet);
            IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            try {
                progressService.run(true, false, operation::run);
                displayExportResult(operation, fileStore);
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error("An error occurred while exporting rets api extension", e);
            }
        });
    }

    private Optional<String> getPath(Shell shell, IRepositoryFileStore fStore) {
        final FileDialog fd = new FileDialog(shell, SWT.SAVE | SWT.SHEET);
        fd.setFileName(fStore.getName());
        fd.setFilterExtensions(new String[] { "*.bos" });
        fd.setText(Messages.exportRestApiTitle);
        return Optional.ofNullable(fd.open());
    }

    private void displayExportResult(ExportBosArchiveOperation operation, RestAPIExtensionFileStore fileStore) {
        IStatus status = operation.getStatus();
        if (status.isOK()) {
            MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.exportDoneTitle,
                    String.format(Messages.exportDoneMessage, fileStore.getDisplayName()));
        } else if (status.getSeverity() != ValidationStatus.CANCEL) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailedTitle, status.getMessage());
        }

    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        export(repositoryAccessor);
        return null;
    }

}
