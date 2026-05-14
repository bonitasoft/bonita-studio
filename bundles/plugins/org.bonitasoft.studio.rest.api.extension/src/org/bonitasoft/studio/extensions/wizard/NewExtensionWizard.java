/**
 * Copyright (C) 2025 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.extensions.wizard;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.ui.jface.BonitaErrorDialog;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.ExtensionProjectArchetypeConfiguration;
import org.bonitasoft.studio.maven.model.ExtensionProjectArchetypes;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.NewExtensionProjectArtifactConfigurationPage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.Wizard;

/**
 * Wizard for creating a new extension project.
 */
public class NewExtensionWizard extends Wizard {

    private static final String DOC_URL_TEMPLATE = "http://www.ofelia.com/bos_redirect.php?bos_redirect_id=%1$s&bos_redirect_product=bos&bos_redirect_major_version=%2$s&bos_redirect_minor_version=";

    private final ArtifactType artifactType;
    private final ExtensionRepositoryStore repositoryStore;
    private final ExtensionProjectArchetypeConfiguration configuration;
    private final WidgetFactory widgetFactory;
    private final MavenProjectConfiguration projectConfiguration;
    private final IWorkspace workspace;

    public NewExtensionWizard(ArtifactType artifactType,
            ExtensionRepositoryStore repositoryStore,
            ExtensionProjectArchetypeConfiguration archetypeConfiguration,
            MavenProjectConfiguration projectConfiguration,
            IWorkspace workspace,
            WidgetFactory widgetFactory) {
        super();
        this.artifactType = artifactType;
        this.repositoryStore = repositoryStore;
        this.configuration = archetypeConfiguration;
        this.projectConfiguration = projectConfiguration;
        this.workspace = workspace;
        this.widgetFactory = widgetFactory;
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        setWindowTitle(constructWindowTitle());
    }

    protected ExtensionRepositoryStore getRepositoryStore() {
        return repositoryStore;
    }

    protected ExtensionProjectArchetypeConfiguration getConfiguration() {
        return configuration;
    }

    protected WidgetFactory getWidgetFactory() {
        return widgetFactory;
    }

    protected MavenProjectConfiguration getProjectConfiguration() {
        return projectConfiguration;
    }

    private String constructWindowTitle() {
        switch (artifactType) {
            case CONNECTOR:
                return Messages.newConnectorTitle;
            case ACTOR_FILTER:
                return Messages.newActorFilterTitle;
            case REST_API:
                return Messages.newRestApiExtensionTitle;
            case THEME:
                return Messages.newThemeTitle;
            default:
                return null;
        }
    }

    private String constructDocUrl() {
        var version = ProductVersion.minorVersion();
        switch (artifactType) {
            case CONNECTOR:
                return String.format(DOC_URL_TEMPLATE, 768, version);
            case ACTOR_FILTER:
                return String.format(DOC_URL_TEMPLATE, 769, version);
            case REST_API:
                return String.format(DOC_URL_TEMPLATE, 690, version);
            case THEME:
                return String.format(DOC_URL_TEMPLATE, 689, version);
            default:
                return null;
        }
    }

    @Override
    public void addPages() {
        final NewExtensionProjectArtifactConfigurationPage configurationPage = new NewExtensionProjectArtifactConfigurationPage(
                widgetFactory,
                configuration,
                projectConfiguration,
                workspace);
        configurationPage.setTitle(Messages.artifactConfigurationPageTitle);
        configurationPage.setDescription(Messages.artifactConfigurationPageDescription);
        configurationPage.setHelpLinkURL(constructDocUrl());
        addPage(configurationPage);
    }

    public ExtensionProjectFileStore<?> getNewFileStore() {
        return repositoryStore.getChild(configuration.getProjectName(), true);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        var operation = ExtensionProjectArchetypes.buildCreateOperation(artifactType, repositoryStore,
                projectConfiguration.toImportConfiguration(), configuration);
        try {
            getContainer().run(true, true, operation.asWorkspaceModifyOperation());
            return handleResult(operation.getStatus());
        } catch (InvocationTargetException | InterruptedException e) {
            showErrorDialog(e);
            return false;
        }
    }

    private boolean handleResult(IStatus status) {
        if (!status.isOK()) {
            showErrorDialog(status.getException());
            return false;
        }
        return true;
    }

    protected void showErrorDialog(Throwable e) {
        var error = MessageFormat.format("Failed to execute new {0} extension project operation.", artifactType.name());
        BonitaStudioLog.error(error, e, RestAPIExtensionActivator.PLUGIN_ID);
        new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.errorDuringProjectCreation, e).open();
    }

}
