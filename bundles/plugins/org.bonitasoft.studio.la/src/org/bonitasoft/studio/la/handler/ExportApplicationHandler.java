/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.handler;

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.ui.control.SelectApplicationDescriptorPage;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ExportApplicationHandler {

    private String path;

    @Execute
    public void exportApplicationWizard(Shell activeShell, RepositoryAccessor repositoryAccessor) {
        createWizard(newWizard(), repositoryAccessor)
                .open(activeShell, Messages.export)
                .ifPresent(selection -> {
                    selection.forEach(applicationFileStore -> {
                        try {
                            applicationFileStore.export(path);
                        } catch (IOException e) {
                            throw new RuntimeException(
                                    String.format("failed to export the application %s", applicationFileStore.getName()), e);
                        }
                    });
                    MessageDialog.openInformation(activeShell, Messages.exportDoneTitle,
                            String.format(Messages.exportDoneMessage));
                });
    }

    private WizardBuilder<Stream<ApplicationFileStore>> createWizard(WizardBuilder<Stream<ApplicationFileStore>> builder,
            RepositoryAccessor repositoryAccessor) {
        SelectApplicationDescriptorPage exportApplicationDescriptorPage = new SelectApplicationDescriptorPage(
                repositoryAccessor);
        return builder.withTitle(Messages.exportApplicationDescriptor)
                .havingPage(newPage()
                        .withTitle(Messages.exportApplicationDescriptor)
                        .withDescription(Messages.exportApplicationDescriptor)
                        .withControl(exportApplicationDescriptorPage))
                .onFinish(container -> finish(exportApplicationDescriptorPage));
    }

    private Optional<Stream<ApplicationFileStore>> finish(SelectApplicationDescriptorPage exportApplicationDescriptorPage) {
        Optional<String> optionalPath = getPath(Display.getCurrent().getActiveShell());

        if (optionalPath.isPresent()) {
            this.path = optionalPath.get();
            return manageConflicts(exportApplicationDescriptorPage.getSelection());
        }
        return Optional.empty();
    }

    protected Optional<String> getPath(Shell shell) {
        final DirectoryDialog fd = new DirectoryDialog(shell, SWT.SAVE | SWT.SHEET);
        fd.setText(Messages.exportApplicationDescriptor);
        return Optional.ofNullable(fd.open());
    }

    private Optional<Stream<ApplicationFileStore>> manageConflicts(Stream<ApplicationFileStore> selection) {
        List<ApplicationFileStore> applicationFileStoreToImport = selection.filter(this::conflictFilter)
                .collect(Collectors.toList());
        return applicationFileStoreToImport.isEmpty() ? Optional.empty()
                : Optional.ofNullable(applicationFileStoreToImport.stream());
    }

    /**
     * Ask the user if he wants to overwrite conflicting file. A conflicting file not overwritten is not kept by the filter.
     */
    private boolean conflictFilter(ApplicationFileStore applicationFileStore) {
        File target = new File(new File(path), applicationFileStore.getName());
        if (target.exists()) {
            if (FileActionDialog.overwriteQuestion(applicationFileStore.getName())) {
                PlatformUtil.delete(target, Repository.NULL_PROGRESS_MONITOR);
            } else {
                return false;
            }
        }
        return true;
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return !repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren().isEmpty();
    }

}
