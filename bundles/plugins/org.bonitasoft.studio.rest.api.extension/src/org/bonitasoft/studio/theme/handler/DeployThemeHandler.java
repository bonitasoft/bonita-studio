/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.theme.handler;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.handler.CustomPageProjectSelectionProvider;
import org.bonitasoft.studio.maven.ui.handler.OpenWizardDialogAbstractHandler;
import org.bonitasoft.studio.maven.ui.wizard.DeployCustomPageWizard;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.eclipse.jface.wizard.IWizard;

public class DeployThemeHandler extends OpenWizardDialogAbstractHandler {

    @Override
    protected String getFinishLabel() {
        return Messages.deploy;
    }

    @Override
    protected IWizard newWizard(final RepositoryAccessor repositoryAccessor, final WidgetFactory widgetFactory,
            final HttpClientFactory httpClientFactory,
            final CustomPageProjectSelectionProvider selectionProvider) {
        return new DeployThemeWizard(getStore(repositoryAccessor),
                BOSEngineManager.getInstance(), widgetFactory, httpClientFactory, selectionProvider);
    }

    @Override
    protected CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> getStore(
            RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getRepositoryStore(ThemeRepositoryStore.class);
    }
    private class DeployThemeWizard extends DeployCustomPageWizard {

        public DeployThemeWizard(
                CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore,
                BOSEngineManager engineManager, WidgetFactory widgetFactory, HttpClientFactory httpClientFactory,
                CustomPageProjectSelectionProvider selectionProvider) {
            super(repositoryStore, engineManager, widgetFactory, httpClientFactory, selectionProvider);
        }

        @Override
        protected String getSelectionPageDeployDescription() {
            return Messages.selectThemeToDeployDescription;
        }

        @Override
        protected String getSelectionPageTitle() {
            return Messages.selectThemeTitle;
        }
        
    }
}
