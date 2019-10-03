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
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.ExternalBrowserInstance;
import org.eclipse.ui.internal.browser.Trace;
import org.eclipse.ui.internal.browser.WebBrowserPreference;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.eclipse.ui.internal.browser.WebBrowserUtil;
import org.eclipse.ui.internal.browser.WebBrowserView;


public class OpenBrowserOperation implements Runnable {

    protected static final String TYPE_ID = "org.bonitasoft.studio.browser"; //$NON-NLS-1$
    private final URL url;
    private ExternalBrowserInstance externalBrowser;
    private boolean useInternalBrowser = false;
    private String name;
    private String id;

    public OpenBrowserOperation(final URL url) {
        this.url = url;
    }

    public OpenBrowserOperation withExternalBrowser(final ExternalBrowserInstance externalBrowser) {
        this.externalBrowser = externalBrowser;
        return this;
    }

    public OpenBrowserOperation withInteralBrowser(String id) {
        this.useInternalBrowser = true;
        this.id = id;
        return this;
    }
    
    public OpenBrowserOperation withName(String name) {
        this.name = name;
        return this;
    }

    public void execute() {
        Display.getDefault().syncExec(this);
    }

    protected void openBrowser() throws PartInitException {
        IWebBrowser browser = null;
        WebBrowserUtil.isInternalBrowserOperational = null;
        if (useInternalBrowser &&
                PlatformUI.getWorkbench().getBrowserSupport().isInternalWebBrowserAvailable()) {
            IWorkbenchWindow workbenchWindow = WebBrowserUIPlugin.getInstance().getWorkbench().getActiveWorkbenchWindow();
            final IWorkbenchPage page = workbenchWindow.getActivePage();
                try {
                    WebBrowserView view = null;
                  IViewReference findViewReference = page.findViewReference(WebBrowserView.WEB_BROWSER_VIEW_ID, WebBrowserUtil.encodeStyle(id, IWorkbenchBrowserSupport.AS_VIEW));
                  if(findViewReference == null) {
                      view = (WebBrowserView)page.showView(WebBrowserView.WEB_BROWSER_VIEW_ID, WebBrowserUtil.encodeStyle(id, IWorkbenchBrowserSupport.AS_VIEW), IWorkbenchPage.VIEW_CREATE);
                  }else {
                      view = (WebBrowserView) findViewReference.getView(true);
                  }
                    if (name != null && name.length() > 0) {
                        view.setBrowserViewName(name);
                    }
                    if (view!=null) {
                        view.setURL(url.toExternalForm());
                    }
                } catch (Exception e) {
                    Trace.trace(Trace.SEVERE, "Error opening Web browser", e); //$NON-NLS-1$
                }
        } else if (browserIsSet()) {
            browser = externalBrowser;
            if (browser == null) {
                browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(
                        IWorkbenchBrowserSupport.AS_EDITOR,
                        TYPE_ID, "", ""); //$NON-NLS-1$
            }
        }
        if (browser != null) {
            browser.openURL(url);
        }
        WebBrowserUtil.isInternalBrowserOperational = false;
        WebBrowserPreference.setBrowserChoice(WebBrowserPreference.EXTERNAL);
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
