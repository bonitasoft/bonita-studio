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

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.ui.OverwriteFileFilter;
import org.bonitasoft.studio.la.core.ExportApplicationRunnable;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.ui.control.SelectApplicationDescriptorPage;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ExportApplicationHandler {

    @Execute
    public void exportApplicationWizard(Shell activeShell, RepositoryAccessor repositoryAccessor) {
        createWizard(newWizard(), repositoryAccessor)
                .open(activeShell, Messages.export);
    }

    private WizardBuilder<List<IRepositoryFileStore>> createWizard(WizardBuilder<List<IRepositoryFileStore>> builder,
            RepositoryAccessor repositoryAccessor) {
        final SelectApplicationDescriptorPage exportApplicationDescriptorPage = new SelectApplicationDescriptorPage(
                repositoryAccessor);
        return builder.withTitle(Messages.exportApplicationDescriptor)
                .havingPage(newPage()
                        .withTitle(Messages.exportApplicationDescriptor)
                        .withDescription(Messages.exportApplicationDescriptor)
                        .withControl(exportApplicationDescriptorPage))
                .onFinish(container -> finish(exportApplicationDescriptorPage, container));
    }

    private Optional<List<IRepositoryFileStore>> finish(SelectApplicationDescriptorPage exportApplicationDescriptorPage,
            IWizardContainer container) {
        final Optional<String> optionalPath = getPath(Display.getCurrent().getActiveShell());
        if (optionalPath.isPresent()) {
            final List<IRepositoryFileStore> resolveConflicts = new OverwriteFileFilter(optionalPath.get())
                    .resolveConflicts(exportApplicationDescriptorPage.getSelection().collect(Collectors.toList()));
            if (resolveConflicts.isEmpty()) {
                return Optional.empty();
            }
            final ExportApplicationRunnable operation = new ExportApplicationRunnable(optionalPath.get(), resolveConflicts);
            try {
                container.run(true, false, operation);
                MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.exportDoneTitle,
                        String.format(Messages.exportDoneMessage));
                return Optional.of(resolveConflicts);
            } catch (InvocationTargetException | InterruptedException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getCurrent().getActiveShell(),
                        Messages.exportFailedTitle, e);
            }
        }
        return Optional.empty();
    }

    protected Optional<String> getPath(Shell shell) {
        final DirectoryDialog fd = new DirectoryDialog(shell, SWT.SAVE | SWT.SHEET);
        fd.setText(Messages.exportApplicationDescriptor);
        return Optional.ofNullable(fd.open());
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return !repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren().isEmpty();
    }

}
