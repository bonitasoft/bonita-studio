/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author Romain Bioteau
 */
public class ApplicationURLBuilder {

    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String APPLI_PATH = "portal/resource"; //$NON-NLS-1$

    private final AbstractProcess process;
    private String configurationId;
    private final long procDefId;

    public ApplicationURLBuilder(final AbstractProcess process, final String configurationId, final long procDefId) {
        this.process = process;
        this.configurationId = configurationId;
        this.procDefId = procDefId;
    }

    public URL toURL(final IProgressMonitor monitor)
            throws MalformedURLException, UnsupportedEncodingException,
            URISyntaxException {
        final String locale = getWebLocale();
        String userName = getDefaultUsername();
        String password = getDefaultPassword();

        final Configuration conf = getConfiguration();
        if (conf != null && conf.getUsername() != null) {
            userName = conf.getUsername();
            password = conf.getPassword();
        }

        final String loginURL = buildLoginUrl(userName, password);
        return new URL(loginURL + "&redirectUrl="
                + URLEncoder.encode(getRedirectURL(locale), ENCODING_UTF8));

    }

    protected String getRedirectURL(final String locale) throws UnsupportedEncodingException {
        return APPLI_PATH
                + "/process"
                + "/" + encodeForPathURL(process.getName())
                + "/" + encodeForPathURL(process.getVersion())
                + "/content/?id=" + procDefId
                + "&" + getLocaleParameter(locale)
                + "&mode=app";
    }

    private String encodeForPathURL(final String toEncode) throws UnsupportedEncodingException {
        return URLEncoder.encode(toEncode, ENCODING_UTF8).replaceAll("\\+", "%20").replaceAll("%2F", "/");
    }

    protected String getLocaleParameter(final String locale) {
        return "locale=" + locale;
    }

    protected String buildLoginUrl(final String userName, final String password) {
        return BOSWebServerManager.getInstance().generateLoginURL(userName, password);
    }

    protected IPreferenceStore getPreferenceStore() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }

    protected String getWebLocale() {
        return getPreferenceStore().getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE);
    }

    protected String getDefaultPassword() {
        return getPreferenceStore().getString(BonitaPreferenceConstants.USER_PASSWORD);
    }

    protected String getDefaultUsername() {
        return getPreferenceStore().getString(BonitaPreferenceConstants.USER_NAME);
    }

    protected Configuration getConfiguration() {
        if (process != null) {
            initConfigurationId();
            if (ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON.equals(configurationId)) {
                return retrieveConfigurationForLocalConf();
            } else {
                return retrieveConfigurationInsideProcess();
            }
        }
        return null;
    }

    protected void initConfigurationId() {
        if (configurationId == null) {
            configurationId = ConfigurationPlugin
                    .getDefault()
                    .getPreferenceStore()
                    .getString(
                            ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        }
    }

    private Configuration retrieveConfigurationInsideProcess() {
        for (final Configuration conf : process.getConfigurations()) {
            if (configurationId.equals(conf.getName())) {
                return conf;
            }
        }
        return null;
    }

    private Configuration retrieveConfigurationForLocalConf() {
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager
                .getInstance().getRepositoryStore(
                        ProcessConfigurationRepositoryStore.class);
        final String id = ModelHelper.getEObjectID(process);
        final IRepositoryFileStore file = processConfStore.getChild(id
                + ".conf");
        if (file == null) {
            return null;
        }
        try {
            return (Configuration) file.getContent();
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error("Failed to read process configuration", e);
        }
        return null;
    }
}
