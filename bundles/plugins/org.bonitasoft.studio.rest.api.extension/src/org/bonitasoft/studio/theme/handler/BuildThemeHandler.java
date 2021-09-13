/*******************************************************************************
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.theme.handler;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.handler.CustomPageProjectSelectionProvider;
import org.bonitasoft.studio.maven.ui.handler.OpenWizardDialogAbstractHandler;
import org.bonitasoft.studio.maven.ui.wizard.BuildCustomPageWizard;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;

public class BuildThemeHandler extends OpenWizardDialogAbstractHandler {

    @Override
    protected String getFinishLabel() {
        return Messages.buildLabel;
    }

    @Override
    protected BuildCustomPageWizard newWizard(final RepositoryAccessor repositoryAccessor,
            final WidgetFactory widgetFactory,
            final HttpClientFactory httpClientFactory,
            final CustomPageProjectSelectionProvider selectionProvider) {
        return new BuildThemeWizard(getStore(repositoryAccessor), widgetFactory, selectionProvider);
    }

    @Override
    protected CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> getStore(
            RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getRepositoryStore(ThemeRepositoryStore.class);
    }
    
    private class BuildThemeWizard extends BuildCustomPageWizard {

        public BuildThemeWizard(
                CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore,
                WidgetFactory widgetFactory, CustomPageProjectSelectionProvider selectionProvider) {
            super(repositoryStore, widgetFactory, selectionProvider);
        }
        
        @Override
        protected String getSelectionPageDescription() {
            return Messages.selectThemeDescription;
        }
        
        @Override
        protected String getSelectionPageTitle() {
             return Messages.selectThemeTitle;
        }
        
    }

}
