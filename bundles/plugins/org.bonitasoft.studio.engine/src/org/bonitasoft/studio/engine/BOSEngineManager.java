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

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.platform.PlatformLoginException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.PlatformSession;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IEngineAction;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class BOSEngineManager {

    public static final String CUSTOM_PERMISSIONS_MAPPING_PROPERTIES = "custom-permissions-mapping.properties";

    public static final String CONSOLE_CONFIG_PROPERTIES = "console-config.properties";

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

    private static final String FIND_USER_PASSWORD_COMMAND = "org.bonitasoft.studio.actors.command.userPassword";

    private static BOSEngineManager INSTANCE;

    private boolean isRunning = false;

    private IProgressMonitor monitor;

    private CommandExecutor commandExecutor = new CommandExecutor();

    protected BOSEngineManager(final IProgressMonitor monitor) {
        if (monitor == null) {
            this.monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        } else {
            this.monitor = monitor;
        }
    }

    public static BOSEngineManager getInstance() {
        return getInstance(null);
    }

    public static synchronized BOSEngineManager getInstance(final IProgressMonitor monitor) {
        if (INSTANCE == null) {
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

    public synchronized void start(AbstractRepository repository) {
        if (!isRunning() || !BOSWebServerManager.getInstance().serverIsStarted()) {
            boolean notifying = notifyStartServer();
            monitor.beginTask(Messages.initializingProcessEngine, IProgressMonitor.UNKNOWN);
            BOSWebServerManager.getInstance().startServer(repository, monitor);
            isRunning = postEngineStart();
            if (notifying) {
                notifyServerStarted();
            }
        }
    }

    private boolean notifyStartServer() {
        if (EngineNotificationSemaphore.getInstance().tryAcquire()) {
            if (!isLazyModeEnabled(EnginePlugin.getDefault().getPreferenceStore())) {
                BonitaNotificator.openNotification(Messages.startServerNotificationTitle,
                        Messages.engineLazyModeNotificationLink, e -> {
                            BonitaPreferenceDialog dialog = new BonitaPreferenceDialog(new Shell(Display.getDefault()));
                            dialog.create();
                            dialog.setSelectedPreferencePage(BonitaPreferenceDialog.SERVER_SETTINGS_PAGE_ID);
                            dialog.open();
                        });
            }
            return true;
        }
        return false;
    }

    private boolean isLazyModeEnabled(IPreferenceStore preferenceStore) {
        return preferenceStore.getBoolean(EnginePreferenceConstants.LAZYLOAD_ENGINE)
                || System.getProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE) != null;
    }

    public synchronized void start() {
        start(RepositoryManager.getInstance().getCurrentRepository());
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

    private void notifyServerStarted() {
        BonitaNotificator.openNotification(Messages.startServerCompletedNotificationTitle,
                Messages.serverRunningNotificationMessage);
        EngineNotificationSemaphore.getInstance().release();
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
        if (BOSWebServerManager.getInstance().serverIsStarted()) {
            BOSWebServerManager.getInstance().stopServer(monitor);
        }
        isRunning = false;
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
            if (contrib != null && contrib.shouldRun()) {
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
        return loginTenant(BONITA_TECHNICAL_USER, BONITA_TECHNICAL_USER_PASSWORD, monitor);
    }

    public APISession loginTenant(final String login, final String password, final IProgressMonitor monitor)
            throws LoginException,
            BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        if (!isRunning() && monitor != null) {
            monitor.beginTask(Messages.waitingForEngineToStart, IProgressMonitor.UNKNOWN);
        }
        start();
        BonitaStudioLog.debug("Attempt to login as " + login, EnginePlugin.PLUGIN_ID);
        final APISession session = getLoginAPI().login(requireNonNull(login), requireNonNull(password));
        if (session != null) {
            BonitaStudioLog.debug("Login successful.", EnginePlugin.PLUGIN_ID);
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

    public IdentityAPI getIdentityAPI(final APISession session)
            throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        return TenantAPIAccessor.getIdentityAPI(session);
    }

    public CommandAPI getCommandAPI(final APISession session)
            throws BonitaHomeNotSetException, ServerAPIException,
            UnknownAPITypeException {
        return TenantAPIAccessor.getCommandAPI(session);
    }

    public ProfileAPI getProfileAPI(final APISession session)
            throws BonitaHomeNotSetException, ServerAPIException,
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

    public PlatformSession loginPlatform(IProgressMonitor monitor)
            throws PlatformLoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        if (!isRunning() && monitor != null) {
            monitor.beginTask(Messages.waitingForEngineToStart, IProgressMonitor.UNKNOWN);
        }
        start();
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
        String username = configuration.getUsername();
        String password = retrieveUserPasswordFromActiveOrga(username)
                .orElseThrow(() -> new Exception(
                        String.format("Unable to retrieve the password of %s in the active organization.",
                                username)));
        try {
            session = BOSEngineManager.getInstance().loginTenant(username, password,
                    monitor);
        } catch (final Exception e1) {
            throw new Exception(Messages.bind(Messages.loginFailed,
                    new String[] { username, process.getName(),
                            process.getVersion() }),
                    e1);
        }
        if (session == null) {
            throw new Exception(Messages.bind(Messages.loginFailed,
                    new String[] { username, process.getName(),
                            process.getVersion() }));
        }
        return session;
    }

    private Optional<String> retrieveUserPasswordFromActiveOrga(String user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", user);
        Object result = commandExecutor.executeCommand(FIND_USER_PASSWORD_COMMAND, parameters);
        return result instanceof Optional ? (Optional<String>) result : Optional.empty();
    }

    public byte[] getTenantConfigResourceContent(String resourceName, IProgressMonitor monitor)
            throws PlatformLoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException,
            PlatformLogoutException, SessionNotFoundException, FileNotFoundException {
        PlatformSession loginPlatform = null;
        try {
            loginPlatform = loginPlatform(monitor);
            final PlatformAPI platformAPI = getPlatformAPI(loginPlatform);
            final Map<Long, Map<String, byte[]>> clientTenantConfigurations = platformAPI.getClientTenantConfigurations();
            final Map<String, byte[]> resources = clientTenantConfigurations.get(DEFAULT_TENANT_ID);
            if (!resources.containsKey(resourceName)) {
                throw new FileNotFoundException(String.format("Resource %s does not exist in database.", resourceName));
            }
            return resources.get(resourceName);
        } finally {
            if (loginPlatform != null) {
                logoutPlatform(loginPlatform);
            }
        }
    }

    public void updateTenantConfigResourceContent(String resourceName, byte[] content)
            throws PlatformLoginException, BonitaHomeNotSetException,
            ServerAPIException, UnknownAPITypeException,
            UpdateException, PlatformLogoutException, SessionNotFoundException {
        PlatformSession loginPlatform = null;
        try {
            loginPlatform = loginPlatform(null);
            final PlatformAPI platformAPI = getPlatformAPI(loginPlatform);
            platformAPI.updateClientTenantConfigurationFile(DEFAULT_TENANT_ID, resourceName, content);
        } finally {
            if (loginPlatform != null) {
                logoutPlatform(loginPlatform);
            }
        }
    }

}
