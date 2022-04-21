/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.handler;

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.bonitasoft.studio.ui.page.SelectionPage;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public abstract class DeleteFileHandler {

    @Execute
    public void deleteFile(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, RepositoryAccessor repositoryAccessor) {
        createWizard(newWizard(), repositoryAccessor, activeShell)
                .open(activeShell, Messages.delete)
                .ifPresent(selection -> {
                    selection.forEach(applicationProfileFileStore -> {
                        applicationProfileFileStore.close();
                        applicationProfileFileStore.delete();
                    });
                    MessageDialog.openInformation(activeShell, Messages.deleteDoneTitle, Messages.deleteDoneMessage);
                });
    }

    protected abstract WizardBuilder<Stream<IRepositoryFileStore<?>>> createWizard(
            WizardBuilder<Stream<IRepositoryFileStore<?>>> newWizard,
            RepositoryAccessor repositoryAccessor,
            Shell activeShell);

    protected Optional<Stream<IRepositoryFileStore<?>>> deleteFinish(
            SelectionPage<? extends IRepositoryStore<?>> selectPage, Shell activeShell) {
        return MessageDialog.openConfirm(activeShell, Messages.deleteConfirmation,
                String.format(Messages.deleteConfirmationMessage, getListToDelete(selectPage)))
                        ? Optional.ofNullable(selectPage.getSelection()) : Optional.empty();
    }

    private String getListToDelete(SelectionPage<? extends IRepositoryStore<? extends IRepositoryFileStore<?>>> selectPage) {
        return selectPage.getSelection()
                .map(selection -> "\n" + selection.getName())
                .collect(Collectors.joining());
    }

}
