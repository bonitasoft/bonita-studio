/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * @author Romain Bioteau
 *
 */
public class WebBrowserFactory {

    public void openExteranlBrowser(final String urlAsString) throws MalformedURLException {
        final URL url = new URL(urlAsString);
        try {
            final IWebBrowser browser = createExternalBrowser();
            browser.openURL(url);
        } catch (final PartInitException e) {
            BonitaStudioLog.error("Failed to oepn browser to display contract constraint expression help content", e, ContractPlugin.PLUGIN_ID);
        }
    }

    protected IWebBrowser createExternalBrowser() throws PartInitException {
        return PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EXTERNAL,
                null,
                null,
                null);
    }

}
