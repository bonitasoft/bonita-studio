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
package org.bonitasoft.studio.engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.xbean.classloader.NonLockingJarFileClassLoader;
import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.api.CommandAPI;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.api.PlatformAPI;
import org.bonitasoft.engine.api.PlatformAPIAccessor;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.ProfileAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.api.TenantAdministrationAPI;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.exception.UpdateException;
import org.bonitasoft.engine.platform.InvalidPlatformCredentialsException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.platform.PlatformLoginException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.engine.session.PlatformSession;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IEngineAction;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author Romain Bioteau
 */
public class BOSEngineManager {

    public static final String CUSTOM_PERMISSIONS_MAPPING_PROPERTIES = "custom-permissions-mapping.properties";

    private static final String POSTSTARTUP_CONTIBUTION_ID = "org.bonitasoft.studio.engine.postEngineAction";

    public static final String PLATFORM_PASSWORD = "platform";

    public static final String PLATFORM_USER = "platformAdmin";

    public static final String BONITA_TECHNICAL_USER = "install";

    public static final String BONITA_TECHNICAL_USER_PASSWORD = "install";

    public static final String API_TYPE_PROPERTY_NAME = "org.bonitasoft.engine.api-type";

    public static final String DEFAULT_TENANT_NAME = "default";

    public static final String DEFAULT_TENANT_DESC = "The default tenant created by the Studio";

    private static final String ENGINESERVERMANAGER_EXTENSION_D = "org.bonitasoft.studio.engine.bonitaEngineManager";

    private static final long DEFAULT_TENANT_ID = 1;

    public static final String SECURITY_CONFIG_PROPERTIES = "security-config.properties";

    private static BOSEngineManager INSTANCE;

    private boolean isRunning = false;

    private IProgressMonitor monitor;

    protected BOSEngineManager(final IProgressMonitor monitor) {
        if (monitor == null) {
            this.monitor = Repository.NULL_PROGRESS_MONITOR;
        } else {
            this.monitor = monitor;
        }
    }

    public static BOSEngineManager getInstance() {
        return getInstance(null);
    }

    public static synchronized BOSEngineManager getInstance(final IProgressMonitor monitor) {
        if (INSTANCE == null) {
            // Setting useCaches to false avoids a memory leak of URLJarFile
            // instances
            // It's a workaround for a Sun bug (see bug id 4167874 - fixed in
            // jdk 1.7). Otherwise,
            // URLJarFiles will never be garbage collected.
            // o.a.g.deployment.util.DeploymentUtil.readAll()
            // causes URLJarFiles to be created
            try {
                // Protocol/file shouldn't matter.
                // As long as we don't get an input/output stream, no operations
                // should occur...
                new URL("http://a").openConnection().setDefaultUseCaches(false); //$NON-NLS-1$
            } catch (final IOException ioe) {
                // Can't Log this. Should we send to STDOUT/STDERR?
            }
            INSTANCE = createInstance(monitor);
        }
        return INSTANCE;
    }

    protected static BOSEngineManager createInstance(final IProgressMonitor monitor) {
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(ENGINESERVERMANAGER_EXTENSION_D)) {
            try {
                return (BOSEngineManager) element.createExecutableExtension("class");
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }

        return new BOSEngineManager(monitor);
    }

    public synchronized void start() {
        if (!isRunning()) {
            monitor.beginTask(Messages.initializingProcessEngine, IProgressMonitor.UNKNOWN);
            initBonitaHome();
            BOSWebServerManager.getInstance().startServer(monitor);
            isRunning = postEngineStart();
            monitor.done();
        }
    }

    protected boolean postEngineStart() {
        //RESUME ENGINE IF PAUSED AT STARTUP
        try {
            final APISession apiSession = getLoginAPI().login(BONITA_TECHNICAL_USER, BONITA_TECHNICAL_USER_PASSWORD);
            final TenantAdministrationAPI tenantManagementAPI = getTenantAdministrationAPI(apiSession);
            if (tenantManagementAPI.isPaused()) {
                tenantManagementAPI.resume();
            }
            executePostStartupContributions();
        } catch (final Exception e) {
            return handlePostEngineStartException(e);
        }
        return true;
    }

    private boolean handlePostEngineStartException(final Exception e) {
        if (tomcatServerIsRunning()) {
            BonitaStudioLog.error(e);
        } else {
            BonitaStudioLog.warning("Tomcat server has been shutdown before first start ended.", EnginePlugin.PLUGIN_ID);
        }
        return false;
    }

    protected boolean tomcatServerIsRunning() {
        return BOSWebServerManager.getInstance().serverIsStarted();
    }

    public synchronized void stop() {
        APISession session = null;
        TenantAdministrationAPI tenantManagementAPI = null;
        try {
            session = loginDefaultTenant(null);
            tenantManagementAPI = getTenantAdministrationAPI(session);
            tenantManagementAPI.pause();
            if (dropBusinessDataDBOnExit()) {
                tenantManagementAPI.cleanAndUninstallBusinessDataModel();
            } else {
                tenantManagementAPI.uninstallBusinessDataModel();
            }
            tenantManagementAPI.resume();
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        } finally {
            if (tenantManagementAPI != null && tenantManagementAPI.isPaused()) {
                try {
                    tenantManagementAPI.resume();
                } catch (final UpdateException e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (session != null) {
                logoutDefaultTenant(session);
            }
        }

        if (isRunning()) {
            BOSWebServerManager.getInstance().stopServer(monitor);
            isRunning = false;
        }
        try {
            BOSWebServerManager.getInstance().cleanBeforeShutdown();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    private boolean dropBusinessDataDBOnExit() {
        final IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
        return preferenceStore.getBoolean(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_EXIT_PREF);
    }

    protected void executePostStartupContributions() throws Exception {
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(POSTSTARTUP_CONTIBUTION_ID);
        IEngineAction contrib = null;
        for (final IConfigurationElement elem : elements) {
            try {
                contrib = (IEngineAction) elem.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            final APISession session = getLoginAPI().login(BONITA_TECHNICAL_USER, BONITA_TECHNICAL_USER_PASSWORD);
            try {
                contrib.run(session);
            } finally {
                if (session != null) {
                    logoutDefaultTenant(session);
                }
            }
        }

    }

    private void initBonitaHome() {
        BonitaHomeUtil.initBonitaHome();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ProcessAPI getProcessAPI(final APISession session) {
        try {
            return TenantAPIAccessor.getProcessAPI(session);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    protected LoginAPI getLoginAPI() throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        return TenantAPIAccessor.getLoginAPI();
    }

    public APISession loginDefaultTenant(final IProgressMonitor monitor)
            throws LoginException, BonitaHomeNotSetException, ServerAPIException,
            UnknownAPITypeException {
        return loginTenant(BONITA_TECHNICAL_USER, BONITA_TECHNICAL_USER_PASSWORD, false, monitor);
    }

    public APISession loginTenant(final String login, final String password, final IProgressMonitor monitor)
            throws LoginException, BonitaHomeNotSetException,
            ServerAPIException, UnknownAPITypeException {
        return loginTenant(login, password, true, monitor);
    }

    protected APISession loginTenant(final String login, final String password, final boolean waitForOrganization,
            final IProgressMonitor monitor)
            throws LoginException,
            BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        if (!isRunning()) {
            if (monitor != null) {
                monitor.setTaskName(Messages.waitingForEngineToStart);
            }
            start();
        }
        if (BonitaStudioLog.isLoggable(IStatus.OK)) {
            BonitaStudioLog.debug("Attempt to login as " + login, EnginePlugin.PLUGIN_ID);
        }
        final APISession session = getLoginAPI().login(login, password);
        if (session != null) {
            if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                BonitaStudioLog.debug("Login successful.", EnginePlugin.PLUGIN_ID);
            }

        }
        if (monitor != null) {
            monitor.done();
        }
        return session;
    }

    public void logoutDefaultTenant(final APISession session) {
        try {
            getLoginAPI().logout(session);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    protected ClassLoader createEngineClassloader() {
        final Set<URL> urls = new HashSet<>();
        Enumeration<URL> foundJars = ProjectUtil.getConsoleLibsBundle().findEntries("/lib", "*.jar", true);
        while (foundJars.hasMoreElements()) {
            final URL url = foundJars.nextElement();
            try {
                urls.add(FileLocator.toFileURL(url));
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        foundJars = ProjectUtil.getConsoleLibsBundle().findEntries("/h2", "h2-*.jar", true);
        while (foundJars.hasMoreElements()) {
            final URL url = foundJars.nextElement();
            try {
                urls.add(FileLocator.toFileURL(url));
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        return new NonLockingJarFileClassLoader("Bonita Engine CLassloader", urls.toArray(new URL[] {}));
    }

    public IdentityAPI getIdentityAPI(final APISession session)
            throws InvalidSessionException, BonitaHomeNotSetException, ServerAPIException,
            UnknownAPITypeException {
        return TenantAPIAccessor.getIdentityAPI(session);
    }

    public CommandAPI getCommandAPI(final APISession session)
            throws InvalidSessionException, BonitaHomeNotSetException, ServerAPIException,
            UnknownAPITypeException {
        return TenantAPIAccessor.getCommandAPI(session);
    }

    public ProfileAPI getProfileAPI(final APISession session)
            throws InvalidSessionException, BonitaHomeNotSetException, ServerAPIException,
            UnknownAPITypeException {
        return TenantAPIAccessor.getProfileAPI(session);
    }

    public PageAPI getPageAPI(final APISession session)
            throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        return TenantAPIAccessor.getCustomPageAPI(session);
    }

    public ApplicationAPI getApplicationAPI(final APISession session)
            throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        return TenantAPIAccessor.getLivingApplicationAPI(session);
    }

    public TenantAdministrationAPI getTenantAdministrationAPI(final APISession session)
            throws BonitaHomeNotSetException,
            ServerAPIException, UnknownAPITypeException {
        return TenantAPIAccessor.getTenantAdministrationAPI(session);
    }

    public PlatformSession loginPlatform()
            throws InvalidPlatformCredentialsException, PlatformLoginException, BonitaHomeNotSetException,
            ServerAPIException, UnknownAPITypeException {
        return PlatformAPIAccessor.getPlatformLoginAPI().login(PLATFORM_USER, PLATFORM_PASSWORD);
    }

    public void logoutPlatform(PlatformSession session)
            throws PlatformLogoutException, SessionNotFoundException, BonitaHomeNotSetException, ServerAPIException,
            UnknownAPITypeException {
        PlatformAPIAccessor.getPlatformLoginAPI().logout(session);
    }

    public PlatformAPI getPlatformAPI(PlatformSession session)
            throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        return PlatformAPIAccessor.getPlatformAPI(session);
    }

    public APISession createSession(final AbstractProcess process, final String configurationId,
            final IProgressMonitor monitor) throws Exception {
        final Configuration configuration = BarExporter.getInstance().getConfiguration(process, configurationId);
        APISession session;
        try {
            session = BOSEngineManager.getInstance().loginTenant(configuration.getUsername(), configuration.getPassword(),
                    monitor);
        } catch (final Exception e1) {
            throw new Exception(Messages.bind(Messages.loginFailed,
                    new String[] { configuration.getUsername(), process.getName(), process.getVersion() }), e1);
        }
        if (session == null) {
            throw new Exception(Messages.bind(Messages.loginFailed,
                    new String[] { configuration.getUsername(), process.getName(), process.getVersion() }));
        }
        return session;
    }

    public byte[] getTenantConfigResourceContent(String resourceName)
            throws InvalidPlatformCredentialsException, PlatformLoginException,
            BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, PlatformLogoutException,
            SessionNotFoundException, FileNotFoundException {
        PlatformSession loginPlatform = null;
        try {
            loginPlatform = loginPlatform();
            final PlatformAPI platformAPI = getPlatformAPI(loginPlatform);
            final Map<Long, Map<String, byte[]>> clientTenantConfigurations = platformAPI.getClientTenantConfigurations();
            final Map<String, byte[]> resources = clientTenantConfigurations.get(DEFAULT_TENANT_ID);
            if (!resources.containsKey(resourceName)) {
                throw new FileNotFoundException(String.format("Resource %s does not esists in database.", resourceName));
            }
            return resources.get(resourceName);
        } finally {
            if (loginPlatform != null) {
                logoutPlatform(loginPlatform);
            }
        }
    }

    public void updateTenantConfigResourceContent(String resourceName, byte[] content)
            throws InvalidPlatformCredentialsException, PlatformLoginException, BonitaHomeNotSetException,
            ServerAPIException, UnknownAPITypeException,
            UpdateException, PlatformLogoutException, SessionNotFoundException {
        PlatformSession loginPlatform = null;
        try {
            loginPlatform = loginPlatform();
            final PlatformAPI platformAPI = getPlatformAPI(loginPlatform);
            platformAPI.updateClientTenantConfigurationFile(DEFAULT_TENANT_ID, resourceName, content);
        } finally {
            if (loginPlatform != null) {
                logoutPlatform(loginPlatform);
            }
        }
    }
}
