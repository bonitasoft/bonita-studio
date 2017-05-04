/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ExportApplicationFileAction {

    public static final String DO_NOT_SHOW_EXPORT_MESSAGE_DIALOG = "DO_NOT_SHOW_EXPORT_MESSAGE_DIALOG";

    public Optional<IRepositoryFileStore> export(Shell shell, IRepositoryFileStore fileStore) {
        final Optional<String> optionalPath = getPath(shell, fileStore);
        if (optionalPath.isPresent()) {
            File targetFile = Paths.get(optionalPath.get()).toFile();
            if (targetFile.exists() && !FileActionDialog.overwriteQuestion(targetFile.getName())) {
                return Optional.empty();
            }
            final IPreferenceStore preferenceStore = LivingApplicationPlugin.getDefault().getPreferenceStore();
            if (!preferenceStore.getBoolean(DO_NOT_SHOW_EXPORT_MESSAGE_DIALOG)) {
                MessageDialogWithPrompt.open(MessageDialog.WARNING,
                        shell,
                        Messages.exportApplicationDescriptorTitle,
                        Messages.exportApplicationDescriptorMessage,
                        Messages.doNotShowMeAgain,
                        false,
                        preferenceStore,
                        DO_NOT_SHOW_EXPORT_MESSAGE_DIALOG,
                        SWT.NONE);
            }

            try {
                Files.copy(fileStore.getResource().getLocation().toFile().toPath(), targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getCurrent().getActiveShell(),
                        Messages.exportFailedTitle, e);
            }

            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.exportDoneTitle,
                    String.format(Messages.exportDoneMessage, targetFile.getAbsolutePath()));
            return Optional.of(fileStore);

        }
        return Optional.empty();

    }

    protected Optional<String> getPath(Shell shell, IRepositoryFileStore fStore) {
        final FileDialog fd = new FileDialog(shell, SWT.SAVE | SWT.SHEET);
        fd.setFileName(fStore.getName());
        fd.setFilterExtensions(new String[] { "*.xml" });
        fd.setText(Messages.exportApplicationDescriptor);
        return Optional.ofNullable(fd.open());
    }

}
