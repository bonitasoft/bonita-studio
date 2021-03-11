/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.handler;

import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Named;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.EditProjectMetadataPage;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Shell;

public class EditProjectMetadataHandler {

    protected MavenProjectHelper helper = new MavenProjectHelper();

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, RepositoryAccessor repositoryAccessor) {
        try {
            AbstractRepository currentRepository = repositoryAccessor.getCurrentRepository();
            EditProjectMetadataPage page = new EditProjectMetadataPage(helper.getMavenModel(currentRepository.getProject()));

            WizardBuilder.<Model> newWizard()
                    .withTitle(Messages.editProjectMetadata)
                    .needProgress()
                    .havingPage(newPage()
                            .withTitle(Messages.editProjectMetadata)
                            .withDescription(Messages.editProjectMetadataDescription)
                            .withControl(page))
                    .onFinish(container -> performFinish(container, page, currentRepository))
                    .open(activeShell, IDialogConstants.FINISH_LABEL);

        } catch (CoreException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Model> performFinish(IWizardContainer container, EditProjectMetadataPage page,
            AbstractRepository currentRepository) {
        try {
            Model model = helper.getMavenModel(currentRepository.getProject());
            boolean nameChanged = !Objects.equals(model.getName(), page.getName());
            model.setName(page.getName());
            model.setDescription(page.getDescription());
            model.setGroupId(page.getGroupId());
            model.setArtifactId(page.getArtifactId());
            model.setVersion(page.getVersion());
            container.run(true, false, monitor -> {
                try {
                    monitor.beginTask(Messages.updatingProjectMetadata, IProgressMonitor.UNKNOWN);
                    helper.saveModel(currentRepository.getProject(), model);
                    if (nameChanged) {
                        currentRepository.rename(model.getName(), monitor);
                    }
                    monitor.done();
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                }
            });
            return Optional.of(model);
        } catch (CoreException | InvocationTargetException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
