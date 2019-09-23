/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences.browser;

import java.net.URL;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.ExternalBrowserInstance;
import org.eclipse.ui.internal.browser.WebBrowserUtil;

/**
 * @author Romain Bioteau
 */
public class OpenBrowserOperation implements Runnable {

    protected static final String TYPE_ID = "org.bonitasoft.studio.browser"; //$NON-NLS-1$
    private final URL url;
    private ExternalBrowserInstance externalBrowser;

    public OpenBrowserOperation(final URL url) {
        this.url = url;
    }

    public void setExternalBrowser(final ExternalBrowserInstance externalBrowser) {
        this.externalBrowser = externalBrowser;
    }

    public void execute() {
        Display.getDefault().syncExec(this);
    }

    protected void openBrowser() throws PartInitException {
        if (browserIsSet()) {
            IWebBrowser browser = externalBrowser;
            if (browser == null) {
                browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EDITOR,
                        TYPE_ID, "", ""); //$NON-NLS-1$
            }
            browser.openURL(url);
        }
    }

    protected boolean browserIsSet() {
        if (noExternalBrowserSet()) {
            if (!FileActionDialog.getDisablePopup()
                    && MessageDialog.openConfirm(Display.getDefault().getActiveShell(), Messages.noBrowserFoundTitle,
                            Messages.noBrowserFoundMsg)) {
                final BonitaPreferenceDialog dialog = new BonitaPreferenceDialog(Display.getDefault().getActiveShell());
                dialog.create();
                dialog.setSelectedPreferencePage(BonitaPreferenceDialog.WEB_BROWSER_PAGE_ID);
                dialog.open();
            }
            return false;
        }
        return true;
    }

    protected boolean noExternalBrowserSet() {
        return !WebBrowserUtil.canUseSystemBrowser() && WebBrowserUtil.getExternalBrowserPaths().isEmpty();
    }

    @Override
    public void run() {
        try {
            openBrowser();
        } catch (final PartInitException e) {
            BonitaStudioLog.error(e);
        }
    }

}
