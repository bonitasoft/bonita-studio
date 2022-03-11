/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.operation;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;

public abstract class AbstractBonitaURLBuilder {

    protected static final String ENCODING_UTF8 = "UTF-8";

    protected abstract String getRedirectURL(final String locale, final IProgressMonitor monitor)
            throws UnsupportedEncodingException;

    protected String encodeForPathURL(final String toEncode) throws UnsupportedEncodingException {
        return URLEncoder.encode(toEncode, ENCODING_UTF8).replaceAll("\\+", "%20").replaceAll("%2F", "/");
    }

    protected String buildLoginUrl(final String userName, final String password) {
        return BOSWebServerManager.getInstance().generateLoginURL(userName, password);
    }

    protected IPreferenceStore getPreferenceStore() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }

    protected ActiveOrganizationProvider getActiveOrganizationProvider() {
        return new ActiveOrganizationProvider();
    }

    protected String getWebLocale() {
        return getPreferenceStore().getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE);
    }

    protected String getDefaultPassword() {
        return getActiveOrganizationProvider().getDefaultPassword();
    }

    protected String getDefaultUsername() {
        return getActiveOrganizationProvider().getDefaultUser();
    }

    protected String userAppToken() {
        if(EnginePlugin.getDefault() == null) {
            return EnginePreferenceConstants.DEFAULT_USER_APP_TOKEN;
        }
        return EnginePlugin.getDefault().getPreferenceStore().getString(EnginePreferenceConstants.USER_APP_TOKEN);
    }

    public URL toURL(final IProgressMonitor monitor)
            throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
        final String locale = getWebLocale();
        final String loginURL = buildLoginUrl();
        return new URL(loginURL + "&redirectUrl="
                + URLEncoder.encode(getRedirectURL(locale, monitor), ENCODING_UTF8));
    }

    public URL toTechnicalUserLoginURL(final IProgressMonitor monitor)
            throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
        final String locale = getWebLocale();
        final String loginURL = buildLoginUrl("install", "install");
        return new URL(loginURL + "&redirectUrl="
                + URLEncoder.encode(getRedirectURL(locale, monitor), ENCODING_UTF8));
    }

    protected String buildLoginUrl() {
        final String userName = getDefaultUsername();
        final String password = getDefaultPassword();
        return buildLoginUrl(userName, password);
    }

}
