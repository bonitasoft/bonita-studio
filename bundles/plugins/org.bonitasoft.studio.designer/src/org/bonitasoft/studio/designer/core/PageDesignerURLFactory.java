/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.LocaleUtil;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.extensions.Preference;

@Creatable
@Singleton
public class PageDesignerURLFactory implements BonitaPreferenceConstants {

    private static final String WAR_CONTEXT_NAME = "designer";

    private IEclipsePreferences preferenceStore;

    private int uidPort = -1;

    @Inject
    public PageDesignerURLFactory(
            @Preference(nodePath = "org.bonitasoft.studio.preferences") final IEclipsePreferences preferenceStore) {
        checkNotNull(preferenceStore);
        this.preferenceStore = preferenceStore;
    }

    public PageDesignerURLFactory(int uidPort) {
        this.uidPort = uidPort;
    }

    public URL openPageDesignerHome() throws MalformedURLException {
        return new URL(baseURL() + "/#/" + locale() + "/home");
    }

    public URL openPage(final String pageId) throws MalformedURLException {
        return new URL(baseURL() + "/#/" + locale() + "/pages/" + pageId);
    }

    public URL newPage() throws MalformedURLException {
        return new URL(baseURL() + "/rest/pages/");
    }

    public URL newPageFromContract(final FormScope scope, final String formName) throws MalformedURLException {
        return new URL("http://" + host() + ":" + port() + "/" + WAR_CONTEXT_NAME + "/rest/pages/contract/"
                + scope.name().toLowerCase() + "/" + formName);
    }

    public URL exportPage(final String pageId) throws MalformedURLException {
        return new URL(baseURL() + "/export/page/" + pageId);
    }

    private String host() {
        return "localhost";
    }

    private String locale() {
        final String locale = preferenceStore.get(CURRENT_STUDIO_LOCALE, LocaleUtil.DEFAULT_LOCALE.getLanguage());
        checkNotNull(locale);
        return locale;
    }

    private String port() {
        return uidPort != -1 ? String.valueOf(uidPort) : String.valueOf(getUIDesignerServerManager().getPort());
    }

    private String baseURL() {
        return "http://" + host() + ":" + port() + "/" + WAR_CONTEXT_NAME;
    }

    protected UIDesignerServerManager getUIDesignerServerManager() {
        return UIDesignerServerManager.getInstance();
    }
}
