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
package org.bonitasoft.studio.la.application.handler;

import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.provider.ApplicationFileStoreLabelProvider;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.handler.DeleteFileHandler;
import org.bonitasoft.studio.ui.page.SelectionMultiPage;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.swt.widgets.Shell;

public class DeleteApplicationHandler extends DeleteFileHandler {

    @Override
    protected WizardBuilder<Stream<IRepositoryFileStore>> createWizard(WizardBuilder<Stream<IRepositoryFileStore>> builder,
            RepositoryAccessor repositoryAccessor, Shell activeShell) {
        SelectionMultiPage<ApplicationRepositoryStore> selectApplicationDescriptorPage = new SelectionMultiPage<>(
                repositoryAccessor, ApplicationRepositoryStore.class, new ApplicationFileStoreLabelProvider());
        return builder.withTitle(Messages.deleteExistingApplication)
                .havingPage(newPage()
                        .withTitle(Messages.deleteExistingApplication)
                        .withDescription(Messages.deleteExistingApplicationDescription)
                        .withControl(selectApplicationDescriptorPage))
                .onFinish(container -> deleteFinish(selectApplicationDescriptorPage, activeShell));
    }

    @CanExecute
    public boolean canExecute(final RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isLoaded()
                && !repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren().isEmpty();
    }

}
