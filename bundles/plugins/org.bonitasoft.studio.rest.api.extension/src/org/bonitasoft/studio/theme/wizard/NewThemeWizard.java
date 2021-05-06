/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.theme.wizard;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.ThemeArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.NewCustomPageArtifactConfigurationPage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.theme.CreateThemeProjectOperation;
import org.bonitasoft.studio.theme.ThemeFileStore;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;

public class NewThemeWizard extends Wizard {
    
    private static final String THEME_DOC_URL = "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=689&bos_redirect_product=bos&bos_redirect_major_version=%s&bos_redirect_minor_version=";
    private final ThemeRepositoryStore repositoryStore;
    private final ThemeArchetypeConfiguration configuration;
    private final IProjectConfigurationManager projectConfigurationManager;
    private final WidgetFactory widgetFactory;
    private final MavenProjectConfiguration projectConfiguration;
    private final IWorkspace workspace;

    public NewThemeWizard(ThemeRepositoryStore repositoryStore,
            IProjectConfigurationManager projectConfigurationManager,
            MavenProjectConfiguration projectConfiguration,
            IWorkspace workspace,
            WidgetFactory widgetFactory) {
        this.repositoryStore = repositoryStore;
        this.configuration = ThemeArchetypeConfiguration.defaultArchetypeConfiguration();
        this.projectConfigurationManager = projectConfigurationManager;
        this.widgetFactory = widgetFactory;
        this.projectConfiguration = projectConfiguration;
        this.workspace = workspace;
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.newThemeTitle);
    }

    @Override
    public void addPages() {
        final NewCustomPageArtifactConfigurationPage configurationPage = new NewCustomPageArtifactConfigurationPage(
                widgetFactory,
                configuration,
                projectConfiguration,
                workspace);
        configurationPage.setTitle(Messages.artifactConfigurationPageTitle);
        configurationPage.setDescription(Messages.artifactConfigurationPageDescription);
        configurationPage.setHelpLinkURL(String.format(THEME_DOC_URL,ProductVersion.majorVersion()));
        addPage(configurationPage);
    }

    @Override
    public boolean performFinish() {
        final CreateThemeProjectOperation operation = new CreateThemeProjectOperation(repositoryStore,
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
        BonitaStudioLog.error("Failed to execute new theme project operation.", e,
                RestAPIExtensionActivator.PLUGIN_ID);
        new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.errorDuringProjectCreation, e).open();
    }

    public ThemeFileStore getNewFileStore() {
        return repositoryStore.getChild(configuration.getPageName(), true);
    }
}
