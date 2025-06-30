/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.wizard;

import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.extensions.wizard.NewExtensionWizard;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.rest.api.extension.core.RestAPIAddressResolver;
import org.eclipse.core.resources.IWorkspace;

public class NewRestAPIExtensionWizard extends NewExtensionWizard {

    private final RestAPIAddressResolver addressReolver;

    public NewRestAPIExtensionWizard(ProjectMetadata projectMetadata,
            ExtensionRepositoryStore repositoryStore,
            MavenProjectConfiguration projectConfiguration,
            IWorkspace workspace,
            WidgetFactory widgetFactory,
            boolean addBdmDependency,
            RestAPIAddressResolver addressReolver) {
        super(ArtifactType.REST_API, repositoryStore,
                RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration(projectMetadata, addBdmDependency),
                projectConfiguration, workspace, widgetFactory);
        this.addressReolver = addressReolver;
    }

    @Override
    public void addPages() {
        super.addPages();

        final NewRestAPIProjectAdvancedConfigurationPage advancedConfigurationPage = new NewRestAPIProjectAdvancedConfigurationPage(
                getWidgetFactory(),
                getConfiguration(), getRepositoryStore(), addressReolver);
        advancedConfigurationPage.setTitle(Messages.advancedConfigurationPageTitle);
        advancedConfigurationPage.setDescription(Messages.advancedConfigurationPageDescription);
        addPage(advancedConfigurationPage);

        final NewRestAPIProjectURLParametersPage urlParametersPage = new NewRestAPIProjectURLParametersPage(
                getWidgetFactory(),
                getConfiguration());
        urlParametersPage.setTitle(Messages.urlParametersPageTile);
        urlParametersPage.setDescription(Messages.urlParametersPageDescription);
        addPage(urlParametersPage);
    }

    @Override
    protected RestAPIExtensionArchetypeConfiguration getConfiguration() {
        return (RestAPIExtensionArchetypeConfiguration) super.getConfiguration();
    }

}
