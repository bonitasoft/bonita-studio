/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.actions;

import java.net.URL;
import java.util.Properties;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * @author Mickael Istria
 */
public class ShowHelpCommand extends AbstractHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    private static final String HELP_URL_PROPERTY = "help.url";

    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            Properties globalProperties = PlatformUtil.getStudioGlobalProperties();
            String url = null;
            if (globalProperties != null) {
                url = globalProperties.getProperty(HELP_URL_PROPERTY);
            } else {
                url = "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=74";
            }
            url = url.concat("&").concat(majorVersion());
            IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport()
                    .createBrowser(IWorkbenchBrowserSupport.AS_EDITOR, BonitaPreferenceConstants.HELP_BROWSER_ID, "Quick Start", "");
            browser.openURL(new URL(url));

        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
        }
        return null;
    }

    private String majorVersion() {
        return "bos_redirect_major_version=" + ProductVersion.majorVersion();
    }

}
