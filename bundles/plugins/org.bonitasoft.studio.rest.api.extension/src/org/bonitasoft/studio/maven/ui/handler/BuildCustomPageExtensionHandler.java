/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.handler;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.BuildCustomPageWizard;

public class BuildCustomPageExtensionHandler extends OpenWizardDialogAbstractHandler {

    @Override
    protected String getFinishLabel() {
        return Messages.buildLabel;
    }

    @Override
    protected BuildCustomPageWizard newWizard(final RepositoryAccessor repositoryAccessor,
            final WidgetFactory widgetFactory,
            final HttpClientFactory httpClientFactory,
            final CustomPageProjectSelectionProvider selectionProvider) {
        return new BuildCustomPageWizard(getStore(repositoryAccessor), widgetFactory, selectionProvider);
    }

    @Override
    protected ExtensionRepositoryStore getStore(
            RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getRepositoryStore(ExtensionRepositoryStore.class);
    }

}
