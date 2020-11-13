/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.ui.handler;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

public class ExploreBusinessDataModelHandler {

    @Execute
    public void execute() {
        try {
            String adress = String.format("http://%s:%s/bdm/graphical", InetAddress.getByName(null).getHostAddress(),
                    InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID)
                            .get(BonitaPreferenceConstants.DATA_REPOSITORY_PORT, "-1"));
            new OpenBrowserOperation(new URL(adress))
                    .withExternalBrowser(getBrowser())
                    .withName(Messages.bdmVoyager)
                    .execute();
        } catch (MalformedURLException | UnknownHostException | PartInitException e) {
            BonitaStudioLog.error(e);
        }
    }

    private IWebBrowser getBrowser() throws PartInitException {
        IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
        return support.getExternalBrowser();
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false) != null;
    }

}
