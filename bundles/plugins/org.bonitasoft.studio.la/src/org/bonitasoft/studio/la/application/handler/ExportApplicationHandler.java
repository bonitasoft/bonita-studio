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
package org.bonitasoft.studio.la.application.handler;

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.provider.ApplicationFileStoreLabelProvider;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.page.SelectionSinglePage;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ExportApplicationHandler extends AbstractHandler {

    @Execute
    public void exportApplicationWizard(Shell activeShell, RepositoryAccessor repositoryAccessor) {
        createWizard(newWizard(), repositoryAccessor, activeShell)
                .open(activeShell, Messages.export);
    }

    private WizardBuilder<IRepositoryFileStore> createWizard(WizardBuilder<IRepositoryFileStore> builder,
            RepositoryAccessor repositoryAccessor, Shell activeShell) {
        final SelectionSinglePage<ApplicationRepositoryStore> exportApplicationDescriptorPage = new SelectionSinglePage<>(
                repositoryAccessor, ApplicationRepositoryStore.class, new ApplicationFileStoreLabelProvider());
        ExportApplicationFileAction exportApplicationFileAction = new ExportApplicationFileAction();
        return builder.withTitle(Messages.exportApplicationDescriptor)
                .havingPage(newPage()
                        .withTitle(Messages.exportApplicationDescriptor)
                        .withDescription(Messages.exportApplicationDescriptor)
                        .withControl(exportApplicationDescriptorPage))
                .onFinish(container -> exportApplicationFileAction
                        .export(activeShell, exportApplicationDescriptorPage.getSingleSelection()));
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return !repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren().isEmpty();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        exportApplicationWizard(Display.getDefault().getActiveShell(), repositoryAccessor);
        return null;
    }

}
