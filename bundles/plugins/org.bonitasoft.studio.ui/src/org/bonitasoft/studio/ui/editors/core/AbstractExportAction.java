/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractExportAction {

    public Optional<IRepositoryFileStore> export(Shell shell, IRepositoryFileStore fileStore) {
        final Optional<String> optionalPath = getPath(shell, fileStore);
        if (optionalPath.isPresent()) {
            final File targetFile = Paths.get(optionalPath.get()).toFile();
            if (targetFile.exists() && !FileActionDialog.overwriteQuestion(targetFile.getName())) {
                return Optional.empty();
            }
            openExportMessageDialog(shell);
            try {
                Files.copy(fileStore.getResource().getLocation().toFile().toPath(), targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (final IOException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getCurrent().getActiveShell(),
                        Messages.exportFailedTitle, e);
            }

            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.exportDoneTitle,
                    String.format(getExportDoneMessage(), targetFile.getAbsolutePath()));
            return Optional.of(fileStore);

        }
        return Optional.empty();

    }

    protected void openExportMessageDialog(Shell shell) {
        // might be overwritten in subclasses
    }

    protected abstract String getExportDoneMessage();

    protected Optional<String> getPath(Shell shell, IRepositoryFileStore fStore) {
        final FileDialog fd = new FileDialog(shell, SWT.SAVE | SWT.SHEET);
        fd.setFileName(fStore.getName());
        fd.setFilterExtensions(new String[] { "*.xml" });
        fd.setText(getExportTitle());
        return Optional.ofNullable(fd.open());
    }

    protected abstract String getExportTitle();
}
