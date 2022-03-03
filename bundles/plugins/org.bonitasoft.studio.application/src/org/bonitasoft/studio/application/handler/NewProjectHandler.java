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

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.SetProjectMetadataOperation;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.ProjectMetadataPage;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.bonitasoft.studio.ui.wizard.WizardPageBuilder;
import org.eclipse.core.runtime.IStatus;

public class NewProjectHandler extends AbstractProjectMetadataHandler {

    private BonitaMarketplacePage bonitaMarketplacePage;

    @Override
    protected List<WizardPageBuilder> createPages(RepositoryAccessor repositoryAccessor, ProjectMetadata metadata) {
          List<WizardPageBuilder> pages = super.createPages(repositoryAccessor, metadata);
          
          bonitaMarketplacePage = new BonitaMarketplacePage(BonitaMarketplacePage.CONNECTOR_TYPE);
          pages.add(newPage()
                  .withTitle(Messages.addExtensionPageTitle)
                  .withDescription(Messages.addExtensionPageDescription)
                  .withControl(bonitaMarketplacePage));
          
          return pages;
    }
    
    @Override
    protected WizardPageBuilder createProjectMetadataPage(ProjectMetadataPage projectMetadataPage) {
        return super.createProjectMetadataPage(projectMetadataPage)
                .withNextPageButtonLabel(Messages.extensions);
    }
    
    @Override
    protected WizardBuilder<IStatus> createWizard(RepositoryAccessor repositoryAccessor,
            MavenProjectHelper mavenProjectHelper, ExceptionDialogHandler exceptionDialogHandler,
            ProjectMetadata metadata, List<WizardPageBuilder> pages) {
        return super.createWizard(repositoryAccessor, mavenProjectHelper, exceptionDialogHandler, metadata, pages)
                .withSize(750, 700)
                .withFixedInitialSize();
    }
    
    protected String getWizardDescription() {
        return Messages.newProjectWizardDescription;
    }

    protected String getWizardTitle() {
        return Messages.newProjectWizardTitle;
    }

    protected SetProjectMetadataOperation createOperation(MavenProjectHelper mavenProjectHelper,
            ProjectMetadata metadata, RepositoryAccessor repositoryAccessor) {
        return new SetProjectMetadataOperation(metadata, repositoryAccessor, mavenProjectHelper)
                .additionalDependencies(bonitaMarketplacePage.getDependenciesToAdd().stream()
                        .map(BonitaArtifactDependency::toMavenDependency)
                        .collect(Collectors.toList()))
                .createNewProject();
    }

    @Override
    protected boolean isNewProject() {
        return true;
    }
    
    @Override
    protected ProjectMetadata initialMetadata(AbstractRepository currentRepository) {
        ProjectMetadata metadata = ProjectMetadata.defaultMetadata();
        metadata.setName("");
        metadata.setArtifactId("");
        return metadata;
    }
    
    @Override
    public String getFinishLabel() {
        return Messages.create;
    }


}
