/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.handler;

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.ui.page.SelectionMultiPage;
import org.bonitasoft.studio.ui.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

public class OpenOrganizationHandler {

    @Execute
    public void openExistingApplicationWizard(Shell activeShell, RepositoryAccessor repositoryAccessor) {
        createWizard(newWizard(), repositoryAccessor)
                .open(activeShell, Messages.open)
                .ifPresent(selection -> selection.forEach(IRepositoryFileStore::open));
    }

    private WizardBuilder<Stream<IRepositoryFileStore<?>>> createWizard(
            WizardBuilder<Stream<IRepositoryFileStore<?>>> builder,
            RepositoryAccessor repositoryAccessor) {
        SelectionMultiPage<OrganizationRepositoryStore> selectOrganizationPage = new SelectionMultiPage<>(
                repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class),
                new FileStoreLabelProvider());
        return builder.withTitle(Messages.openExistingOrganization)
                .havingPage(newPage()
                        .withTitle(Messages.openExistingOrganization)
                        .withDescription(Messages.openExistingOrganizationDescription)
                        .withControl(selectOrganizationPage))
                .onFinish(container -> Optional.ofNullable(selectOrganizationPage.getSelection()));
    }

    @CanExecute
    public boolean canExecute(final RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isLoaded()
                && !repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class).getChildren().isEmpty();
    }
}
