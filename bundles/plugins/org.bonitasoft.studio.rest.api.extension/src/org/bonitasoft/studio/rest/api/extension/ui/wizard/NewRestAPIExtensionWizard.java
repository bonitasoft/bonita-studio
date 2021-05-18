/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.NewCustomPageArtifactConfigurationPage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.RestAPIAddressResolver;
import org.bonitasoft.studio.rest.api.extension.core.maven.CreateRestAPIExtensionProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;

public class NewRestAPIExtensionWizard extends Wizard {

    private static final String REST_API_EXT_DOC_URL = "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=690&bos_redirect_product=bos&bos_redirect_major_version=%s&bos_redirect_minor_version=";
    private final RestAPIExtensionRepositoryStore repositoryStore;
    private final RestAPIExtensionArchetypeConfiguration configuration;
    private final IProjectConfigurationManager projectConfigurationManager;
    private final WidgetFactory widgetFactory;
    private final MavenProjectConfiguration projectConfiguration;
    private final IWorkspace workspace;
    private final RestAPIAddressResolver addressReolver;

    public NewRestAPIExtensionWizard(RestAPIExtensionRepositoryStore repositoryStore,
            IProjectConfigurationManager projectConfigurationManager,
            MavenProjectConfiguration projectConfiguration,
            IWorkspace workspace,
            WidgetFactory widgetFactory,
            String bdmPackage,
            String bdmVersion,
            RestAPIAddressResolver addressReolver) {
        this.repositoryStore = repositoryStore;
        this.configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration(bdmPackage, bdmVersion);
        this.projectConfigurationManager = projectConfigurationManager;
        this.widgetFactory = widgetFactory;
        this.projectConfiguration = projectConfiguration;
        this.workspace = workspace;
        this.addressReolver = addressReolver;
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.newRestApiExtensionTitle);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        final NewCustomPageArtifactConfigurationPage configurationPage = new NewCustomPageArtifactConfigurationPage(
                widgetFactory,
                configuration,
                projectConfiguration,
                workspace);
        configurationPage.setTitle(Messages.artifactConfigurationPageTitle);
        configurationPage.setDescription(Messages.artifactConfigurationPageDescription);
        configurationPage.setHelpLinkURL(String.format(REST_API_EXT_DOC_URL,ProductVersion.majorVersion()));
        addPage(configurationPage);

        final NewRestAPIProjectAdvancedConfigurationPage advancedConfigurationPage = new NewRestAPIProjectAdvancedConfigurationPage(
                widgetFactory,
                configuration, repositoryStore, addressReolver);
        advancedConfigurationPage.setTitle(Messages.advancedConfigurationPageTitle);
        advancedConfigurationPage.setDescription(Messages.advancedConfigurationPageDescription);
        addPage(advancedConfigurationPage);

        final NewRestAPIProjectURLParametersPage urlParametersPage = new NewRestAPIProjectURLParametersPage(widgetFactory,
                configuration);
        urlParametersPage.setTitle(Messages.urlParametersPageTile);
        urlParametersPage.setDescription(Messages.urlParametersPageDescription);
        addPage(urlParametersPage);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final CreateRestAPIExtensionProjectOperation operation = new CreateRestAPIExtensionProjectOperation(repositoryStore,
                projectConfigurationManager,
                projectConfiguration.toImportConfiguration(),
                configuration);
        try {
            getContainer().run(true, true, operation.asWorkspaceModifyOperation());
            return handleRestult(operation.getStatus());
        } catch (InvocationTargetException | InterruptedException e) {
            showErrorDialog(e);
            return false;
        }
    }

    private boolean handleRestult(IStatus status) {
        if (!status.isOK()) {
            showErrorDialog(status.getException());
            return false;
        }
        return true;
    }

    protected void showErrorDialog(Throwable e) {
        BonitaStudioLog.error("Failed to execute new rest api extension project operation.", e,
                RestAPIExtensionActivator.PLUGIN_ID);
        new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.errorDuringProjectCreation, e).open();
    }

    public RestAPIExtensionFileStore getNewFileStore() {
        return repositoryStore.getChild(configuration.getPageName(), true);
    }

}
