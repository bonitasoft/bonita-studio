/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.intro.actions;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

/**
 * @author Aurelien Pupier
 */
public class OpenInExternalBrowserIntroAction implements IIntroAction {

    @Override
    public void run(final IIntroSite site, final Properties params) {
        final String urlToOpen = retrieveURLToOpen(params);
        openInExternalBrowser(urlToOpen);
    }

    private void openInExternalBrowser(final String urlToOpen) {
        try {
            if (urlToOpen != null) {
                final URL url = new URL(urlToOpen);
                new OpenBrowserOperation(url).execute();
            }
        } catch (final MalformedURLException e) {
            BonitaStudioLog.error(e);
        }
    }

    private String retrieveURLToOpen(final Properties params) {
        String urlToOpen = null;
        try {
            final String urlEncoded = params.getProperty("url");
            urlToOpen = URLDecoder.decode(urlEncoded, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            BonitaStudioLog.error(e);
        }
        return urlToOpen;
    }

}
