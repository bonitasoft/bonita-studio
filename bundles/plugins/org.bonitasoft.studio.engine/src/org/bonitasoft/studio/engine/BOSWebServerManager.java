/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
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

import static org.bonitasoft.studio.engine.server.ClientBonitaHomeBuildler.newClientBonitaHomeBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;
import org.bonitasoft.studio.common.Activator;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.engine.server.PortConfigurator;
import org.bonitasoft.studio.engine.server.StartEngineJob;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.launching.RuntimeClasspathEntry;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.server.tomcat.core.internal.ITomcatServer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.internal.ProjectProperties;
import org.eclipse.wst.server.core.internal.ServerType;

public class BOSWebServerManager implements IBonitaProjectListener {

    // Visibility policy: package-private members are test seams - the tests fragment shares
    // this package and the host classloader. Extension-point subclasses in other bundles
    // (bonitaWebServerManager) override only the protected methods. The collaborators are NOT
    // seams: they are constructor arguments, so their accessors stay private and a fixture cannot
    // half-redirect them.
    static final String BONITA_TOMCAT_SERVER_ID = "bonita-tomcat-server-id";
    static final String BONITA_TOMCAT_RUNTIME_ID = "bonita-tomcat-runtime-id";
    public static final String SERVER_CONFIGURATION_PROJECT = "server_configuration";
    private static final String LOGINSERVICE_PATH = "/bonita/loginservice";
    protected static final String WEBSERVERMANAGER_EXTENSION_ID = "org.bonitasoft.studio.engine.bonitaWebServerManager";
    /**
     * Must match the Tomcat major.minor shipped by the engine distribution bundle
     * (org.bonitasoft.distrib:bundle-tomcat, following bonita-runtime.version): WTP validates the
     * installed Tomcat version against the adapter. Enforcement is layered: the runtime-bundle
     * configure-bundle.groovy scripts assert the bundle content against the
     * tomcat.expected.version.prefix pom property at build time, and
     * {@link TomcatInstall#matchesAdapter()} checks the workspace install against this constant
     * on every start - this constant and the pom property still have to be bumped together.
     */
    static final String TOMCAT_MAJOR_MINOR = "10.1";
    /**
     * WTP's adapter suffix, written down rather than derived. Stripping the dot from
     * {@link #TOMCAT_MAJOR_MINOR} happens to produce it for every Tomcat line shipped so far, which is
     * what makes the derivation look like a rule - but the ids are an enumeration WTP owns
     * (.55 .60 .70 .80 .85 .90 .100 .101), not a formula, and nothing obliges the next one to follow
     * it. When it stops matching, findRuntimeType returns null and the user is told the Tomcat adapter
     * is missing, which points at the target platform instead of at this constant.
     */
    static final String TOMCAT_ADAPTER_VERSION = "101";
    static final String TOMCAT_SERVER_TYPE = "org.eclipse.jst.server.tomcat." + TOMCAT_ADAPTER_VERSION;
    static final String TOMCAT_RUNTIME_TYPE = "org.eclipse.jst.server.tomcat.runtime."
            + TOMCAT_ADAPTER_VERSION;
    protected static final String START_TIMEOUT = "start-timeout";
    private static final String HTTP = "HTTP";
    private static final String SERVER_URL = "server.url";
    private static final String APPLICATION_NAME = "application.name";
    private static final String BONITA_APPLICATION = "bonita";
    private static final String BONITA_CLIENT_HOST_DEFAULT = "bonita.client.host.default";
    private static final String BONITA_CLIENT_PORT_DEFAULT = "bonita.client.port.default";

    // The collaborators, injected by one of the two constructors and substituted by constructing the
    // manager rather than by overriding an accessor
    private final TomcatInstall install;
    private final WtpRegistry registry;
    /** Drives the purge, the create path and the recovery messages, which must name one project. */
    private final String configurationProjectName;
    private final StaleServerStatePurger purger;
    private final RuntimeErrorReporter errorReporter;
    private final IJobManager jobManager;
    private static final String TOMCAT_LOG_FILE = "tomcat.log";

    private static final int MAX_LOGGING_TRY = 50;

    private static BOSWebServerManager INSTANCE;
    private IServer tomcat;
    private PortConfigurator portConfigurator;
    private IStatus startResult;
    private final ReentrantLock startStoplock = new ReentrantLock();

    public static synchronized BOSWebServerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }
        return INSTANCE;
    }

    protected static BOSWebServerManager createInstance() {
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(WEBSERVERMANAGER_EXTENSION_ID)) {
            try {
                return (BOSWebServerManager) element.createExecutableExtension("class");
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }

        return new BOSWebServerManager();
    }

    /**
     * The production wiring. Kept no-arg because the manager is instantiated reflectively through
     * {@link #WEBSERVERMANAGER_EXTENSION_ID} - see createInstance - and because BOSWebServerManagerEx
     * inherits it implicitly.
     */
    protected BOSWebServerManager() {
        // the workspace read happens here rather than in a field initializer, so the test
        // constructor below can build a manager without a running workspace at all
        this(workspaceInstall(), new WtpRegistry(), SERVER_CONFIGURATION_PROJECT, new RuntimeErrorReporter(),
                Job.getJobManager());
    }

    /**
     * Package-private for the tests, which build the manager directly rather than through the
     * extension point and so need no subclass to pass mocks.
     * <p>
     * The purge is DERIVED from the install, not passed separately: a purger built on a different
     * install would recursively delete one tree while every version check read another.
     */
    BOSWebServerManager(TomcatInstall install, WtpRegistry registry, String configurationProjectName,
            RuntimeErrorReporter errorReporter, IJobManager jobManager) {
        this.install = install;
        this.registry = registry;
        this.configurationProjectName = configurationProjectName;
        this.purger = defaultPurger(install, registry, configurationProjectName);
        this.errorReporter = errorReporter;
        this.jobManager = jobManager;
    }

    /** Package-private so the suite can assert the production wiring; static, so nothing can redirect it. */
    static TomcatInstall workspaceInstall() {
        return new TomcatInstall(
                new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), "tomcat"),
                TOMCAT_MAJOR_MINOR);
    }

    private static StaleServerStatePurger defaultPurger(TomcatInstall install, WtpRegistry registry,
            String configurationProjectName) {
        return new StaleServerStatePurger(install, registry, configurationProjectName,
                new WtpRegistry.PersistedIds(BONITA_TOMCAT_SERVER_ID, TOMCAT_SERVER_TYPE,
                        BONITA_TOMCAT_RUNTIME_ID, TOMCAT_RUNTIME_TYPE));
    }

    public BundleCopy copyTomcatBundleInWorkspace(final IProgressMonitor monitor) {
        return install().copyBundle(monitor);
    }

    public void startServer(IRepository repository, IProgressMonitor monitor) {
        startStoplock.lock();
        try {
            if (!serverIsStarted()) {
                monitor.subTask(Messages.startingWebServer);
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Starting tomcat...",
                            EnginePlugin.PLUGIN_ID);
                }
                try {
                    setupLaunchConfiguration(monitor);
                    UIDesignerServerManager uidManager = UIDesignerServerManager.getInstance();
                    if (uidManager.getPortalPort() != portConfigurator.getHttpPort()) {
                        uidManager.setPortalPort(portConfigurator.getHttpPort());
                        if (uidManager.shouldRestartAfterConfigurationChange(repository)) {
                            uidManager.stop();
                            uidManager.start(repository, monitor);
                        }
                    }

                    startResult = null;

                    tomcat.start(ILaunchManager.RUN_MODE, result -> startResult = result);
                    waitServerRunning();
                } catch (final CoreException e) {
                    clearConfiguration();
                    errorReporter().report(e);
                }
            }
        } finally {
            startStoplock.unlock();
        }
    }

    public void clearConfiguration() {
        if (tomcat != null) {
            try {
                tomcat.delete();
            } catch (CoreException e1) {
                BonitaStudioLog.error(e1);
            }
            // dropped even when the deletion failed: keeping the reference would make
            // setupLaunchConfiguration skip the reconfiguration it was called for, so a port
            // change would silently start the server it was meant to replace
            tomcat = null;
        }
    }

    public void setupLaunchConfiguration(IProgressMonitor monitor) throws CoreException {
        monitor = IProgressMonitor.nullSafe(monitor);
        if (tomcat == null) {
            var rule = ResourcesPlugin.getWorkspace().getRoot();
            // labeled before acquiring the rule: that wait is contended by the auto-build right
            // after an import, and the purge+copy under it can run for minutes
            monitor.subTask(Messages.preparingWebServer);
            // Avoid workspace deadlock
            jobManager().beginRule(rule, monitor);
            try {
                purgeStaleServerState();
                configureBonitaClient();
                var copy = copyTomcatBundleInWorkspace(monitor);
                if (copy == BundleCopy.FAILED || !versionMarkerJar().exists()) {
                    // FAILED covers a copy that died halfway, where server/lib may exist without
                    // the marker jar: the version check would call that a shipped-bundle mismatch
                    // and tell the user to reinstall. TomcatInstall.matchesAdapter cannot subsume
                    // the exists() check either - it reports an absent bundle dir as fresh
                    throw new CoreException(Status.error(
                            NLS.bind(Messages.tomcatBundleExtractionFailed, tomcatBundleDirectory())));
                }
                if (copy == BundleCopy.EXTRACTED && !install().matchesAdapter()) {
                    // the SHIPPED bundle is the wrong Tomcat line: fail with the cause instead of
                    // the opaque WTP validation error (the next start still purges and
                    // re-extracts - this buys the diagnostic). Gated on the copy having
                    // extracted: a kept install was already version-checked by the purge
                    throw new CoreException(Status.error(NLS.bind(Messages.shippedTomcatBundleMismatch,
                            TOMCAT_MAJOR_MINOR, tomcatInstallDirectory())));
                }
                updateRuntimeLocationIfNeeded();
                var type = ServerCore.findRuntimeType(TOMCAT_RUNTIME_TYPE);
                if (type == null) {
                    throw new CoreException(Status.error(
                            NLS.bind(Messages.tomcatAdapterMissing, TOMCAT_MAJOR_MINOR, TOMCAT_RUNTIME_TYPE)));
                }
                var runtime = createServerRuntime(type, new NullProgressMonitor());
                var confProject = createServerConfigurationProject(new NullProgressMonitor());

                tomcat = createServer(monitor, confProject, runtime);
                createLaunchConfiguration(tomcat, new NullProgressMonitor());
                confProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new NullProgressMonitor());
            } finally {
                jobManager().endRule(rule);
            }
        }
    }

    private StaleServerStatePurger purger() {
        return purger;
    }

    /**
     * The WTP lookups. Separate from the purger because the paths below CREATE a server: reaching a
     * registry lookup through an object named "stale state purger" said the wrong thing about them.
     */
    private WtpRegistry registry() {
        return registry;
    }

    void purgeStaleServerState() throws CoreException {
        purger().purge();
    }

    /**
     * Where WTP keeps the server object: it is serialized into a file of the
     * configuration project, which is why doWorkspaceMigration disposes of
     * a stale one by deleting that project. NOT .metadata/.plugins/org.eclipse.wst.server.core,
     * which holds publish and temp state only - a user told to delete that would hit the same
     * failure on the next start.
     */
    private String serverConfigurationLocation() {
        return ResourcesPlugin.getWorkspace().getRoot().getLocation()
                .append(configurationProjectName).toOSString();
    }

    /** Where WTP keeps the runtime: an InstanceScope preference node, not a metadata folder. */
    private static String runtimePreferencesLocation() {
        return ResourcesPlugin.getWorkspace().getRoot().getLocation()
                .append(".metadata/.plugins/org.eclipse.core.runtime/.settings/org.eclipse.wst.server.core.prefs")
                .toOSString();
    }

    private TomcatInstall install() {
        return install;
    }

    private RuntimeErrorReporter errorReporter() {
        return errorReporter;
    }

    private File tomcatInstallDirectory() {
        return install().installDirectory();
    }

    private File versionMarkerJar() {
        return install().versionMarkerJar();
    }

    private IJobManager jobManager() {
        return jobManager;
    }

    private File tomcatBundleDirectory() {
        return install().bundleDirectory();
    }

    private void updateRuntimeLocationIfNeeded() {
        for (final IRuntime runtime : ServerCore.getRuntimes()) {
            if (runtime instanceof org.eclipse.wst.server.core.internal.Runtime && runtime.getLocation() != null
                    && !runtime.getLocation().toFile().getAbsolutePath()
                            .equals(tomcatInstallDirectory().getAbsolutePath())) {
                final IRuntimeWorkingCopy copy = runtime.createWorkingCopy();
                final String oldLocation = copy.getLocation().toFile()
                        .getAbsolutePath();
                copy.setLocation(Path.fromOSString(tomcatInstallDirectory().getAbsolutePath()));
                final File serverXmlFile = new File(tomcatInstallDirectory(),
                        "conf" + File.separatorChar + "server.xml");
                try {
                    Files.writeString(serverXmlFile.toPath(),
                            Files.readString(serverXmlFile.toPath(), Charset.forName("UTF-8")).replace(oldLocation,
                                    tomcatInstallDirectory().getAbsolutePath()),
                            Charset.forName("UTF-8"));
                } catch (final IOException e1) {
                    BonitaStudioLog.error(e1, EnginePlugin.PLUGIN_ID);
                }
                try {
                    copy.save(true, AbstractRepository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
                }
            }
        }
    }

    private void waitServerRunning() {
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException ex) {
            BonitaStudioLog.error(ex, EnginePlugin.PLUGIN_ID);
        }
        while (startResult == null) {
            try {
                Thread.sleep(500);
            } catch (final InterruptedException ex) {
                BonitaStudioLog.error(ex, EnginePlugin.PLUGIN_ID);
            }
        }
        if (startResult.isOK()) {
            // the fresh install owns its log again, so the copy the purge kept has no reader left:
            // dropping it closes the window where an unrelated later failure could serve it as
            // current, and keeps the workspace root clean
            FileUtil.tryAndDeleteFile(previousLogFile());
            connectWithRetries();
            BonitaStudioLog.debug("Tomcat server started.",
                    EnginePlugin.PLUGIN_ID);
        } else {
            BonitaStudioLog.debug(
                    "Tomcat failed to start. Check the log file for more informations. (bonita.log or catalina.log)",
                    EnginePlugin.PLUGIN_ID);
            errorReporter().openErrorDialog(Messages.cannotStartTomcatTitle, Messages.cannotStartTomcatMessage);
        }
    }

    private void connectWithRetries() {
        int loginTry = 0;
        APISession session = null;
        while (session == null && loginTry < MAX_LOGGING_TRY) {
            try {
                session = BOSEngineManager.getInstance().getLoginAPI().login(BOSEngineManager.BONITA_TECHNICAL_USER,
                        BOSEngineManager.BONITA_TECHNICAL_USER_PASSWORD);
            } catch (final Exception e) {
                loginTry++;
                try {
                    Thread.sleep(500);
                } catch (final InterruptedException ex) {
                    BonitaStudioLog.error(ex, EnginePlugin.PLUGIN_ID);
                }
            }
        }
        if (session != null) {
            try {
                BOSEngineManager.getInstance().getLoginAPI().logout(session);
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        } else {
            BonitaStudioLog.error("Failed to login to engine after " + MAX_LOGGING_TRY + " tries",
                    EnginePlugin.PLUGIN_ID);
        }
    }

    protected ILaunchConfiguration createLaunchConfiguration(final IServer server, final IProgressMonitor monitor)
            throws CoreException {
        ILaunchConfigurationType launchConfigType = ((ServerType) server.getServerType()).getLaunchConfigurationType();
        ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
        ILaunchConfiguration[] configurations = launchManager.getLaunchConfigurations(launchConfigType);
        if (configurations != null) {
            for (var existingConf : configurations) {
                existingConf.delete();
            }
        }
        ILaunchConfiguration conf = server.getLaunchConfiguration(true, AbstractRepository.NULL_PROGRESS_MONITOR);
        return configureLaunchConfiguration(conf.getWorkingCopy());
    }

    protected ILaunchConfiguration configureLaunchConfiguration(ILaunchConfigurationWorkingCopy workingCopy)
            throws CoreException {
        // Contains log4j jars, checked before the VM args a damaged install would discard.
        // lib/ext absent while setupLaunchConfiguration hard-fails on a missing lib itself:
        // Tomcat would start with broken logging and time out, telling the user to check the
        // log file that cannot be written. The build-time configure-bundle.groovy assert keeps
        // this the workspace-damage case it names: a bundle without these jars fails the build
        File[] extraJars = install().extLibDirectory()
                .listFiles(file -> file.getName().endsWith(".jar"));
        if (extraJars == null || extraJars.length == 0) {
            // null = ext/ is not a directory; empty = it holds no jar - both start Tomcat
            // without logging, so both are the damaged-install case
            throw new CoreException(Status.error(
                    NLS.bind(Messages.tomcatInstallDamaged, tomcatInstallDirectory())));
        }
        final RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();

        workingCopy.setAttribute(
                IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
                getTomcatVMArgsBuilder(repositoryAccessor, EnginePlugin.getDefault().getPreferenceStore())
                        .getVMArgs(tomcatBundleDirectory().getAbsolutePath()));
        workingCopy.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_FILE,
                getTomcatLogFile());
        workingCopy.setAttribute(IDebugUIConstants.ATTR_APPEND_TO_FILE, true);
        workingCopy.setAttribute(DebugPlugin.ATTR_CONSOLE_ENCODING, "UTF-8");
        final List<String> classpathEntries = workingCopy.getAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH,
                new ArrayList<>());
        Stream.of(extraJars).map(file -> {
            try {
                return new RuntimeClasspathEntry(
                        JavaCore.newLibraryEntry(Path.fromOSString(file.getAbsolutePath()), null, null, true))
                                .getMemento();
            } catch (CoreException e) {
                throw new RuntimeException(e);
            }
        }).forEach(classpathEntries::add);
        workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH, classpathEntries);
        return workingCopy.doSave();
    }

    protected TomcatVmArgsBuilder getTomcatVMArgsBuilder(final RepositoryAccessor repositoryAccessor,
            IPreferenceStore enginePreference) {
        return new TomcatVmArgsBuilder(repositoryAccessor, enginePreference);
    }

    protected String getTomcatLogFile() {
        final File parentDir = Platform.getLogFileLocation().toFile().getParentFile();
        return new File(parentDir, TOMCAT_LOG_FILE).getAbsolutePath();
    }

    protected IServer createServer(final IProgressMonitor monitor, final IProject confProject, final IRuntime runtime)
            throws CoreException {
        final IServerType sType = ServerCore.findServerType(TOMCAT_SERVER_TYPE);
        if (sType == null) {
            throw new CoreException(Status.error(
                    NLS.bind(Messages.tomcatAdapterMissing, TOMCAT_MAJOR_MINOR, TOMCAT_SERVER_TYPE)));
        }
        final IFile file = confProject.getFile("bonitaTomcatServerSerialization");

        final IFolder configurationFolder = confProject
                .getFolder("tomcat_conf");
        final File sourceFolder = new File(tomcatInstallDirectory(), "conf");
        PlatformUtil.copyResource(configurationFolder.getLocation().toFile(),
                sourceFolder, AbstractRepository.NULL_PROGRESS_MONITOR);
        configurationFolder.refreshLocal(IResource.DEPTH_INFINITE,
                AbstractRepository.NULL_PROGRESS_MONITOR);
        final IServer server = configureServer(runtime, sType, file,
                configurationFolder, monitor);
        portConfigurator = newPortConfigurator(server);
        portConfigurator.configureServerPort(AbstractRepository.NULL_PROGRESS_MONITOR);
        return server;
    }

    private PortConfigurator newPortConfigurator(final IServer server) {
        return new PortConfigurator(server, newClientBonitaHomeBuilder(),
                BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore());
    }

    protected IServer configureServer(final IRuntime runtime, final IServerType sType, final IFile file,
            final IFolder configurationFolder,
            final IProgressMonitor monitor)
            throws CoreException {
        IServer server = registry().findServer(BONITA_TOMCAT_SERVER_ID);
        // reuse only a server of the expected type: a mistyped server surviving the purge (e.g. a
        // failed WTP deletion) must not silently drive the launch with the previous adapter.
        // Compared by id - ServerType does not override equals
        if (server != null && !sType.getId().equals(registry().typeId(server))) {
            var staleServer = server;
            registry().deleteQuietly(staleServer::delete, BONITA_TOMCAT_SERVER_ID);
            // deleteQuietly covers the object having already been dropped by the conf
            // project resource delta, but a genuinely surviving one must not get a same-id
            // twin: WTP's registry behavior on duplicate ids is not part of its contract
            if (registry().findServer(BONITA_TOMCAT_SERVER_ID) != null) {
                throw new CoreException(Status.error(
                        NLS.bind(Messages.staleTomcatServerDeletionFailed, serverConfigurationLocation())));
            }
            server = null;
        }
        var serverWC = server == null
                ? sType.createServer(BONITA_TOMCAT_SERVER_ID, file, runtime, monitor)
                : server.createWorkingCopy();
        if (serverWC == null) {
            // the adapter itself resolved - createServer would have thrown otherwise - so this is
            // WTP misbehaving, not a missing target-platform feature
            throw new CoreException(Status.error(
                    NLS.bind(Messages.wtpServerCreationFailed, sType.getId(), serverConfigurationLocation())));
        }
        serverWC.setServerConfiguration(configurationFolder);
        serverWC.setAttribute(ITomcatServer.PROPERTY_INSTANCE_DIR,
                tomcatInstallDirectory().getAbsolutePath());
        serverWC.setAttribute(ITomcatServer.PROPERTY_DEPLOY_DIR,
                tomcatInstallDirectory().getAbsolutePath() + File.separatorChar + "webapps");
        serverWC.setAttribute(START_TIMEOUT, 300);
        return serverWC.save(true, monitor);
    }

    protected IRuntime createServerRuntime(final IRuntimeType type, final IProgressMonitor monitor)
            throws CoreException {
        IRuntime runtime = registry().findRuntime(BONITA_TOMCAT_RUNTIME_ID);
        // a mistyped runtime surviving the purge (e.g. a swallowed deletion failure) would be
        // validated against the wrong adapter while the error message names the current one:
        // recreate it so the recovery is self-healing
        if (runtime != null && !type.getId().equals(registry().typeId(runtime))) {
            var staleRuntime = runtime;
            registry().deleteQuietly(staleRuntime::delete, BONITA_TOMCAT_RUNTIME_ID);
            // same contract as configureServer: never create a same-id twin of a survivor
            if (registry().findRuntime(BONITA_TOMCAT_RUNTIME_ID) != null) {
                throw new CoreException(Status.error(
                        NLS.bind(Messages.staleTomcatRuntimeDeletionFailed, runtimePreferencesLocation())));
            }
            runtime = null;
        }
        if (runtime == null) {
            runtime = type.createRuntime(BONITA_TOMCAT_RUNTIME_ID, monitor);
        }
        final IRuntimeWorkingCopy tomcatRuntimeWC = runtime.createWorkingCopy();
        tomcatRuntimeWC.setLocation(Path.fromOSString(tomcatInstallDirectory().getAbsolutePath()));
        final IStatus status = tomcatRuntimeWC.validate(null);
        if (!status.isOK()) {
            // WTP's own status carried as a child: its message is often a MultiStatus summary and
            // the reason a user needs is in the children, which a fresh Status would drop
            throw new CoreException(new MultiStatus(EnginePlugin.PLUGIN_ID, IStatus.ERROR,
                    new IStatus[] { status },
                    NLS.bind(Messages.tomcatRuntimeValidationFailed,
                            new Object[] { TOMCAT_MAJOR_MINOR, tomcatInstallDirectory(), status.getMessage() }),
                    null));
        }
        return tomcatRuntimeWC.save(true, monitor);
    }

    protected IProject createServerConfigurationProject(final IProgressMonitor monitor) throws CoreException {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject confProject = workspace.getRoot().getProject(configurationProjectName);
        if (!confProject.exists()) {
            confProject.create(AbstractRepository.NULL_PROGRESS_MONITOR);
            confProject.open(AbstractRepository.NULL_PROGRESS_MONITOR);
            final ProjectProperties projectProperties = new ProjectProperties(confProject);
            confProject.getWorkspace().run(new IWorkspaceRunnable() {

                @Override
                public void run(final IProgressMonitor monitor) throws CoreException {
                    projectProperties.setServerProject(true, monitor);
                }
            }, monitor);
        } else if (!confProject.isOpen()) {
            confProject.open(AbstractRepository.NULL_PROGRESS_MONITOR);
        }
        return confProject;
    }

    private void waitServerStopped(final IProgressMonitor monitor) throws CoreException {
        while (tomcat != null
                && tomcat.getServerState() != IServer.STATE_STOPPED) {
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }
    }

    public boolean serverIsStarted() {
        return tomcat != null
                && (tomcat.getServerState() == IServer.STATE_STARTED);
    }

    public void resetServer(boolean cleanConfiguration, IProgressMonitor monitor) {
        boolean notifying = notifyRestartServer();
        stopServer(monitor);
        if (cleanConfiguration) {
            // null-safe: a failed setup leaves tomcat null and projectOpened no longer schedules
            // a start, so a port change here would otherwise NPE
            clearConfiguration();
        }
        startServer(RepositoryManager.getInstance().getCurrentRepository().orElseThrow(), monitor);
        if (notifying) {
            notifyRestartServerCompleted();
        }
    }

    private void notifyRestartServerCompleted() {
        BonitaNotificator.openInfoNotification(Messages.restartServerCompletedNotificationTitle,
                Messages.serverRunningNotificationMessage);
        EngineNotificationSemaphore.getInstance().release();
    }

    private boolean notifyRestartServer() {
        if (EngineNotificationSemaphore.getInstance().tryAcquire()) {
            BonitaNotificator.openInfoNotification(Messages.restartServerNotificationTitle,
                    Messages.restartServerNotificationMessage);
            return true;
        }
        return false;
    }

    public void stopServer(final IProgressMonitor monitor) {
        startStoplock.lock();
        try {
            if (serverIsStarted()) {
                monitor.subTask(Messages.stoppingWebServer);
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Stopping tomcat server...", EnginePlugin.PLUGIN_ID);
                }
                tomcat.stop(true);
                try {
                    waitServerStopped(monitor);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
                }
                BonitaStudioLog.debug("Tomcat server stopped",
                        EnginePlugin.PLUGIN_ID);
            }
        } finally {
            startStoplock.unlock();
        }
    }

    public String generateUrlBase() {
        final IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        final String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
        final String host = store.getString(BonitaPreferenceConstants.CONSOLE_HOST);
        return "http://" + host + ":" + port;
    }

    public String generateLoginURL(final String username, final String password) {
        return generateUrlBase() + LOGINSERVICE_PATH + "?username=" + username + "&password=" + password;
    }

    public String generateLoginURL() {
        return generateUrlBase() + LOGINSERVICE_PATH;
    }

    private File previousLogFile() {
        return install().previousLogFile();
    }

    public File getBonitaLogFile() {
        return install().engineLogFile();
    }

    @Override
    public void projectOpened(IRepository repository, IProgressMonitor monitor) {
        if (PlatformUtil.isHeadless()) {
            return;
        }
        if (!isLazyModeEnabled()) {
            try {
                clearConfiguration();
                setupLaunchConfiguration(monitor);
            } catch (CoreException e) {
                // like startServer: a setup that failed after createServer must not leave a
                // half-configured tomcat field short-circuiting every later setup attempt
                clearConfiguration();
                // surface the failure (e.g. the manual-recovery instruction of a locked bundle
                // purge) instead of letting the engine job fail later with an unrelated symptom
                errorReporter().report(e);
                return;
            }
            final StartEngineJob job = new StartEngineJob(Messages.startingEngineServer, repository);
            job.setPriority(Job.LONG);
            job.setUser(false);
            job.schedule();
        }
    }

    public boolean isLazyModeEnabled() {
        IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
        return preferenceStore.getBoolean(EnginePreferenceConstants.LAZYLOAD_ENGINE)
                || System.getProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE) != null;
    }

    @Override
    public void projectClosed(IRepository repository, IProgressMonitor monitor) {
        stopServer(monitor);
    }

    // package-private instance seam so the ordering tests can stub the JVM-wide APITypeManager
    // mutation; the class-level lock stays on the static method that performs it
    void configureBonitaClient() {
        configureBonitaClientStatically();
    }

    private static synchronized void configureBonitaClientStatically() {
        try {
            final String host = System.getProperty(BONITA_CLIENT_HOST_DEFAULT, "localhost");
            final int serverPort = Integer
                    .parseInt(System.getProperty(BONITA_CLIENT_PORT_DEFAULT, "8080"));
            BonitaStudioLog.debug(
                    "Configuring bonita client on host " + host + ":" + serverPort + " with API_TYPE=" + HTTP,
                    Activator.PLUGIN_ID);
            final Map<String, String> parameters = new HashMap<>();
            parameters.put(SERVER_URL, "http://" + host + ":" + serverPort);
            parameters.put(APPLICATION_NAME, BONITA_APPLICATION);
            parameters.put("basicAuthentication.active", "true");
            parameters.put("basicAuthentication.username", "http-api");
            parameters.put("basicAuthentication.password", "h11p-@p1");
            APITypeManager.setAPITypeAndParams(ApiAccessType.valueOf(HTTP), parameters);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }
}
