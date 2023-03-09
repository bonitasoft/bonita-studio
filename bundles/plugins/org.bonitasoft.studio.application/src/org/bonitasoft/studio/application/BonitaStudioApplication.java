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
package org.bonitasoft.studio.application;

import static org.eclipse.jface.util.Util.isValid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.application.contribution.IPreStartupContribution;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.ZipUtil;
import org.bonitasoft.studio.common.editingdomain.BonitaOperationHistory;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.ClearPersistedStateIndication;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.ImportBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.maven.repository.MavenRepositories;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.common.ui.jface.MessageDialogWithLink;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.internal.launching.LaunchingPlugin;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.ide.ChooseWorkspaceDialog;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.application.IDEApplication;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * This class controls all aspects of the application's execution
 */
public class BonitaStudioApplication extends IDEApplication implements IApplication, IJobChangeListener {

    private static final String REQUIRED_JAVA_VERSION = "11"; //$NON-NLS-1$

    private Display display;
    /** A map indicating whether the workspace at given URL needs migration and what's its original version was (before IDE erases the version.ini file) */
    private Map<URL, Pair<Boolean, Version>> workspaceInformation = new HashMap<>(1);
    public static final String PREFERENCES_FILE = ".wsPreferences"; //$NON-NLS-1$
    public static final String WS_ROOT = "wsRootDir"; //$NON-NLS-1$
    public static final String ONLINE_DOC_REQUIREMENTS = RedirectURLBuilder.create("165");
    private static final String PRIORITY = "priority"; //$NON-NLS-1$

    /** Use the plugin associated to Studio product for checking and storing the version */
    protected static final String WORKSPACE_CHECK_STUDIO_BUNDLE_NAME = "org.bonitasoft.studio";//$NON-NLS-1$
    /** The version of the Studio */
    protected static final Version WORKSPACE_CHECK_STUDIO_BUNDLE_VERSION;
    static {
        Bundle bundle = Platform.getBundle(WORKSPACE_CHECK_STUDIO_BUNDLE_NAME);
        WORKSPACE_CHECK_STUDIO_BUNDLE_VERSION = bundle != null ? bundle.getVersion() : null;
    }

    public static long START_TIME = 0;

    public BonitaStudioApplication() {
    }

    public BonitaStudioApplication(final Display display) {
        this.display = display;
    }

    @Override
    public Object start(final IApplicationContext context) throws Exception {
        START_TIME = System.currentTimeMillis();
        //avoid the execution of AutoBuild job during startup
        addBuildJobListener();
        if (display == null) {
            display = PlatformUI.createDisplay();
        }
        if (!isJavaVersionSupported(display)) {
            return IApplication.EXIT_OK;
        }

        try {
            /*
             * Look for a splash shell like in org.eclipse.ui.internal.ide.application.IDEApplication.start(IApplicationContext)
             */
            Shell shell = WorkbenchPlugin.getSplashShell(display);
            Optional.ofNullable(shell).ifPresent(s -> {
                s.setText(ChooseWorkspaceDialog.getWindowTitle());
                s.setImages(Window.getDefaultImages());
            });

            Map arguments = context != null ? context.getArguments() : Collections.emptyMap();
            Object workspaceLocationCheck = initLocations(shell, arguments);
            if (workspaceLocationCheck != null) {
                WorkbenchPlugin.unsetSplashShell(display);
                return workspaceLocationCheck;
            }

            executePreStartupContributions();

            //set our custom operation factory
            OperationHistoryFactory.setOperationHistory(new BonitaOperationHistory());
            return createAndRunWorkbench(display);
        } finally {
            // dispose display and release the instance location lock
            Optional.ofNullable(display).ifPresent(this::disposeDisplay);
            Optional.ofNullable(Platform.getInstanceLocation()).ifPresent(Location::release);
        }
    }

    /**
     * Dispose the display correctly in appropriate thread
     * 
     * @param display display to dispose (non-null)
     */
    protected void disposeDisplay(Display display) {
        display.syncExec(() -> {
            if (display != null && !display.isDisposed()) {
                display.dispose();
            }
        });
    }

    private void executePreStartupContributions() {
        Stream.of(BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.application.prestartup")) //$NON-NLS-1$
                .sorted(this::sortContribution)
                .map(elem -> {
                    try {
                        return elem.createExecutableExtension("class"); //$NON-NLS-1$
                    } catch (CoreException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(IPreStartupContribution.class::cast)
                .filter(IPreStartupContribution::canExecute)
                .forEach(IPreStartupContribution::execute);
    }

    private int sortContribution(final IConfigurationElement e1, final IConfigurationElement e2) {
        int p1 = 0;
        int p2 = 0;
        try {
            p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
        } catch (final NumberFormatException e) {
            p1 = 0;
        }
        try {
            p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
        } catch (final NumberFormatException e) {
            p2 = 0;
        }
        return p1 - p2; // Lowest Priority first
    }

    protected Object createAndRunWorkbench(final Display display) {
        final int returnCode = PlatformUI.createAndRunWorkbench(display, createWorkbenchAdvisor());
        if (returnCode == PlatformUI.RETURN_RESTART) {
            return IApplication.EXIT_RESTART;
        }
        return IApplication.EXIT_OK;
        // display disposed by start methd
    }

    protected BonitaStudioWorkbenchAdvisor createWorkbenchAdvisor() {
        return ContextInjectionFactory.make(BonitaStudioWorkbenchAdvisor.class, EclipseContextFactory.create());
    }

    protected void addBuildJobListener() {
        Job.getJobManager().addJobChangeListener(this);
    }

    @Override
    public void scheduled(final IJobChangeEvent event) {
        cancelAutoBuildJobDuringStartup(event);
    }

    protected void cancelAutoBuildJobDuringStartup(final IJobChangeEvent event) {
        if (event.getJob().belongsTo(ResourcesPlugin.FAMILY_AUTO_BUILD)) {
            if (!isWorkbenchRunning()) {
                event.getJob().cancel();
            }
        }
    }

    protected boolean isWorkbenchRunning() {
        return PlatformUI.isWorkbenchRunning();
    }

    protected boolean isJavaVersionSupported(final Display display) {
        final String javaVersion = getJavaVersion();
        if (!javaVersion.startsWith(REQUIRED_JAVA_VERSION)) {
            openErrorDialog(display, javaVersion);
            return false;
        }
        return true;
    }

    protected void openErrorDialog(final Display display, final String javaVersion) {
        var shell = new Shell(display);
        try {
            var uri = new URI(RedirectURLBuilder.create("165")); //$NON-NLS-1$
            new MessageDialogWithLink(shell,
                    Messages.incompatibleJavaVersionTitle, null, String.format(
                            Messages.incompatibleJavaVersionMessage,
                            javaVersion,
                            org.bonitasoft.studio.common.Messages.bonitaStudioModuleName,
                            REQUIRED_JAVA_VERSION),
                    MessageDialog.ERROR,
                    new String[] { IDialogConstants.OK_LABEL },
                    0,
                    uri).open();
        } catch (final URISyntaxException e) {
            BonitaStudioLog.error(e);
        } finally {
            shell.dispose();
        }
    }

    /**
     * Initialize the workspace, install and JRE locations.
     * 
     * @param shell the splash shell to use as parent
     * @param applicationArguments the command line arguments
     * @return <code>null</code> when the locations are correct, one of the exit codes otherwise:
     *         {@link IApplication#EXIT_OK}, {@link IApplication#EXIT_RELAUNCH} or {@link IApplication#EXIT_RESTART}
     */
    protected Object initLocations(Shell shell, Map applicationArguments) {
        Object instanceLocationCheck = checkInstanceLocation(shell, applicationArguments);
        if (instanceLocationCheck != null) {
            return instanceLocationCheck;
        }
        return null;
    }

    /**
     * Return <code>null</code> if a valid workspace path has been set and an exit code otherwise.
     * Prompt for and set the path if possible and required.
     * 
     * @param shell the splash shell to use as parent
     * @param applicationArguments the command line arguments
     * @return <code>null</code> when a valid instance location is found, one of the exit codes otherwise:
     *         {@link IApplication#EXIT_OK}, {@link IApplication#EXIT_RELAUNCH} or {@link IApplication#EXIT_RESTART}
     */
    protected Object checkInstanceLocation(Shell shell, Map applicationArguments) {
        final Location instanceLoc = Platform.getInstanceLocation();
        if (instanceLoc == null) {
            MessageDialog.openError(
                    shell,
                    IDEWorkbenchMessages.IDEApplication_workspaceMandatoryTitle,
                    IDEWorkbenchMessages.IDEApplication_workspaceMandatoryMessage);
            return EXIT_OK;
        }
        //if workspace is set via -data, can't reset it
        if (!instanceLoc.isSet()) {
            // try and find a workspace location from history
            final String path2 = Platform.getInstallLocation().getURL().getFile() + File.separator + PREFERENCES_FILE;
            String lastUsedWs = null;
            final File propertiesFile = new File(path2);
            if (propertiesFile.exists()) {
                final Properties properties = new Properties();
                try {
                    final FileInputStream fis = new FileInputStream(propertiesFile);
                    properties.load(fis);
                    fis.close();
                    lastUsedWs = properties.getProperty(WS_ROOT);
                } catch (final FileNotFoundException e) {
                    BonitaStudioLog.error(e);
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (lastUsedWs != null && lastUsedWs.length() > 1) {
                // set the last used location and continue
                try {
                    // workspace must not be locked before it has been checked.
                    instanceLoc.set(new URL("file", null, lastUsedWs), false); //$NON-NLS-1$
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
            //no pref found use default ws location
            if (!instanceLoc.isSet()) {
                final String path = Platform.getInstallLocation().getURL().getPath() + "workspace"; //$NON-NLS-1$
                try {
                    // workspace must not be locked before it has been checked.
                    instanceLoc.set(new URL("file", null, path), false); //$NON-NLS-1$
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        // Now, workspace has been chosen. Check whether is is compatible
        Object result = super.checkInstanceLocation(shell, applicationArguments);
        if (instanceLoc.isSet() && result == null) {
            // IDE workspace version has been written, now add the studio version to it
            URL workspaceUrl = instanceLoc.getURL();
            // note that workspace is already locked
            Optional<Version> nonMigratedVersion = Optional.ofNullable(workspaceInformation.get(workspaceUrl))
                    .filter(p -> !p.getFirst()).map(Pair::getSecond).filter(Objects::nonNull);
            writeWorkspaceStudioVersion(nonMigratedVersion);
            // proceed to workspace migration when required
            Boolean needsMigration = Optional.ofNullable(workspaceInformation.get(workspaceUrl)).map(Pair::getFirst)
                    .orElse(false);
            if (needsMigration) {
                doWorkspaceMigration(workspaceUrl, shell);
                return EXIT_RESTART;
            }
        }
        return result;
    }

    /**
     * Write the version of the Studio into metadata version file.
     * 
     * @param nonMigratedVersion the original version of the workspace when it has not been migrated
     * @see IDEApplication#writeWorkspaceVersion()
     */
    protected void writeWorkspaceStudioVersion(Optional<Version> nonMigratedVersion) {
        if (WORKSPACE_CHECK_STUDIO_BUNDLE_VERSION == null) {
            // no studio reference bundle installed, no check possible
            return;
        }
        Location instanceLoc = Platform.getInstanceLocation();
        if (instanceLoc == null || instanceLoc.isReadOnly()) {
            return;
        }
        File versionFile = getVersionFile(instanceLoc.getURL(), true);
        if (versionFile == null || !versionFile.exists()) {
            // writeWorkspaceVersion should have already created it, unless something went wrong
            return;
        }
        // load properties to only add the Studio version, without erasing IDE versions
        Properties props = new Properties();
        try (FileInputStream is = new FileInputStream(versionFile)) {
            props.load(is);
        } catch (IOException e) {
            BonitaStudioLog.error(NLS.bind(Messages.couldNotReadVersion, versionFile), e);
        } catch (IllegalArgumentException e) {
            BonitaStudioLog.error(NLS.bind(Messages.couldNotParseVersion, versionFile), e);
        }
        // worst case scenario, props loading went wrong and we loose the IDE versions... Not a big deal.
        String versionToWrite = nonMigratedVersion.orElse(WORKSPACE_CHECK_STUDIO_BUNDLE_VERSION).toString();
        props.setProperty(WORKSPACE_CHECK_STUDIO_BUNDLE_NAME, versionToWrite);
        // write Studio version and read IDE versions
        try (OutputStream output = new FileOutputStream(versionFile)) {
            props.store(output, null);
        } catch (IOException e) {
            BonitaStudioLog.error(Messages.couldNotWriteVersion, e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.internal.ide.application.IDEApplication#checkValidWorkspace(org.eclipse.swt.widgets.Shell, java.net.URL)
     */
    @Override
    protected ReturnCode checkValidWorkspace(Shell shell, URL url) {
        if (WORKSPACE_CHECK_STUDIO_BUNDLE_VERSION == null) {
            // no studio reference bundle installed, no check possible
            return ReturnCode.VALID;
        }
        // comparing Studio version, not Eclipse IDE version...
        Optional<Version> rawWsStudioVersion = readWorkspaceStudioVersion(url);
        Optional<Version> wsStudioVersion = rawWsStudioVersion.map(BonitaStudioApplication::toMaintenanceVersion);
        VersionsComparison versionCompareResult = compareWorkspaceAndStudioVersions(wsStudioVersion, url);
        workspaceInformation.put(url, new Pair(versionCompareResult.needsMigration(), rawWsStudioVersion.orElse(null)));
        switch (versionCompareResult) {
            case COMPATIBLE_SAME_MAINTENANCE:
            case COMPATIBLE_STUDIO_MAINTENANCE_LOWER_THAN_WORKSPACE:
                /*
                 * Workspace ~ Studio. Just proceed.
                 */
                return ReturnCode.VALID;
            case NEEDS_MIGRATION_STUDIO_MAINTENANCE_HIGHER_THAN_WORKSPACE:
            case NEEDS_MIGRATION_STUDIO_MINOR_HIGHER_THAN_WORKSPACE:
                /*
                 * Workspace < Studio. Update must be possible with explicit migration.
                 * Warn the user that migration is not reversible when minor version changes.
                 */
                // be prepared for making a workspace backup
                File workspaceFile = new File(url.getPath());
                String versionSuffix = wsStudioVersion
                        .map(v -> String.format("_%d_%d_%d", v.getMajor(), v.getMinor(), v.getMicro())).orElse("");//$NON-NLS-1$//$NON-NLS-2$
                // open dialog
                MessageDialogWithToggle dialog = makeWorkspaceVersionMigrationDialog(shell, url,
                        VersionsComparison.NEEDS_MIGRATION_STUDIO_MINOR_HIGHER_THAN_WORKSPACE
                                .equals(versionCompareResult));
                int returnCode = dialog.open();
                if (returnCode == IDialogConstants.RETRY_ID || returnCode == SWT.DEFAULT) {
                    // retry another workspace
                    return ReturnCode.INVALID;
                }
                if (returnCode == IDialogConstants.CLOSE_ID) {
                    // just quit
                    return ReturnCode.EXIT;
                }
                boolean makeBackup = dialog.getToggleState();
                if (makeBackup) {
                    String workspaceBackupFileName = String.format("%s%s_%s.zip", workspaceFile.getName(), //$NON-NLS-1$
                            versionSuffix,
                            new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date()));//$NON-NLS-1$
                    var defaultBackupFile = new File(workspaceFile, workspaceBackupFileName);
                    var workspaceBackupFile = new AtomicReference<File>(defaultBackupFile);
                    var fileDialog = new FileDialog(shell, SWT.SAVE);
                    fileDialog.setText(Messages.saveWorkspaceBackupArchive);
                    fileDialog.setFilterExtensions(new String[] { "*.zip" });
                    fileDialog.setOverwrite(true);
                    fileDialog.setFilterPath(defaultBackupFile.getParent());
                    fileDialog.setFileName(defaultBackupFile.getName());
                    var backupFilePath = fileDialog.open();
                    if (backupFilePath != null) {
                        workspaceBackupFile.set(new File(backupFilePath));

                        ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(shell) {

                            @Override
                            protected Shell getParentShell() {
                                // Use null shell to make migration progress dialog visible in the task manager of the OS
                                return null;
                            }
                        };
                        final AtomicBoolean zipDone = new AtomicBoolean(false);
                        try {
                            progressDialog.run(true, false, monitor -> {
                                monitor.beginTask(Messages.backupOngoing, IProgressMonitor.UNKNOWN);
                                try {
                                    ZipUtil.zip(workspaceFile.toPath(), workspaceBackupFile.get().toPath());
                                    zipDone.set(true);
                                } catch (IOException e) {
                                    BonitaStudioLog.error(Messages.couldNotWriteVersion, e);
                                } finally {
                                    monitor.done();
                                }
                            });
                        } catch (InvocationTargetException | InterruptedException e) {
                            BonitaStudioLog.error(e);
                        }
                        if (!zipDone.get()) {
                            MessageDialog failDialog = new MessageDialog(shell, Messages.backupFailedTitle, null,
                                    Messages.backupFailedMessage,
                                    MessageDialog.ERROR, 0,
                                    IDEWorkbenchMessages.IDEApplication_version_continue,
                                    IDEWorkbenchMessages.IDEApplication_version_exit) {

                                @Override
                                protected Shell getParentShell() {
                                    // Use null shell to make workspace selection dialog visible in the task manager of the OS
                                    return null;
                                }

                            };
                            int failCode = failDialog.open();
                            if (failCode != IDialogConstants.OK_ID & failCode != SWT.DEFAULT) {
                                return ReturnCode.EXIT;
                            }
                        }
                    }
                }
                // keep same workspace and proceed to migration
                return ReturnCode.VALID;
            case INCOMPATIBLE_STUDIO_MINOR_LOWER_THAN_WORKSPACE:
            default:
                /*
                 * Workspace > Studio. It must have been created with a newer Studio version.
                 * Downgrade might be problematic, just forbid it.
                 */
                MessageDialog errorDialog = makeWorkspaceVersionErrorDialog(shell, url, wsStudioVersion);

                int returnErrorCode = errorDialog.open();
                if (returnErrorCode == IDialogConstants.OK_ID || returnErrorCode == SWT.DEFAULT) {
                    // retry another workspace
                    return ReturnCode.INVALID;
                } else {
                    // just quit
                    return ReturnCode.EXIT;
                }
        }
    }

    /**
     * Open dialog for when Workspace < Studio.
     * Update must be possible with explicit migration.
     * Warn the user that migration is not reversible when willBreakCompatibility is <code>true</code>.
     * 
     * @param shell the parent shell
     * @param url the workspace URL
     * @param workspaceBackupFileName the name for file to backup workspace
     * @param willBreakCompatibility <code>true</code> when minor version changes and migration will break compatibility
     * @return the warning/info dialog to open
     */
    protected MessageDialogWithToggle makeWorkspaceVersionMigrationDialog(Shell shell, URL url,
            boolean willBreakCompatibility) {
        String title = IDEWorkbenchMessages.IDEApplication_versionTitle_olderWorkspace;
        String message = NLS.bind(
                willBreakCompatibility ? Messages.updateOlderWorkspaceMsg : Messages.updateOlderWorkspaceMaintenanceMsg,
                url.getFile());

        LinkedHashMap<String, Integer> labelToIdMap = new LinkedHashMap<>();
        labelToIdMap.put(IDEWorkbenchMessages.IDEApplication_version_continue, IDialogConstants.YES_ID);
        labelToIdMap.put(IDEWorkbenchMessages.IDEApplication_version_switch, IDialogConstants.RETRY_ID);
        labelToIdMap.put(IDEWorkbenchMessages.IDEApplication_version_exit, IDialogConstants.CLOSE_ID);
        MessageDialogWithToggle dialog = new MessageDialogWithToggle(shell, title, null, message,
                MessageDialog.WARNING,
                labelToIdMap, 0,
                Messages.backupWorkspaceBeforeUpdate,
                true) {

            @Override
            protected Shell getParentShell() {
                // Use null shell to make workspace selection dialog visible in the task manager of the OS
                return null;
            }

        };
        // hide splash if any
        if (isValid(shell)) {
            shell.setVisible(false);
        }
        return dialog;
    }

    /**
     * Open error dialog for when Workspace > Studio.
     * It must have been created with a newer Studio version.
     * Downgrade might be problematic, just forbid it.
     * 
     * @param shell the parent shell
     * @param url the workspace URL
     * @param wsStudioVersion the Studio version used by workspace
     * @return the error dialog to open
     */
    protected MessageDialog makeWorkspaceVersionErrorDialog(Shell shell, URL url, Optional<Version> wsStudioVersion) {
        String title = IDEWorkbenchMessages.IDEApplication_versionTitle_newerWorkspace;
        String message = wsStudioVersion.isPresent() ? NLS.bind(
                Messages.newerWorkspaceWithVersionMsg,
                url.getFile(), wsStudioVersion.get())
                : NLS.bind(
                        Messages.newerWorkspaceWithoutVersionMsg,
                        url.getFile());
        MessageDialog dialog = new MessageDialog(shell, title, null, message,
                MessageDialog.ERROR, 0,
                IDEWorkbenchMessages.IDEApplication_version_switch,
                IDEWorkbenchMessages.IDEApplication_version_exit) {

            @Override
            protected Shell getParentShell() {
                // Use null shell to make workspace selection dialog visible in the task manager of the OS
                return null;
            }

        };
        // hide splash if any
        if (isValid(shell)) {
            shell.setVisible(false);
        }
        return dialog;
    }

    /**
     * Do the workspace migration.
     * 
     * @param url The URL of the workspace.
     * @param shell the splash shell to use as parent
     */
    protected void doWorkspaceMigration(URL url, Shell shell) {
        // Eclipse .metadata will be updated on its own. Versions have just been rewritten.
        // The application model must be reset because it may contain invalid fragments or descriptors
        ClearPersistedStateIndication.letIndication();
        // Remove the tomcat folder. It will be reconstructed automatically.
        try {
            File tomcatDir = new File(Path.of(url.toURI()).toFile(), "tomcat"); //$NON-NLS-1$
            if (tomcatDir.exists()) {
                FileUtil.deleteDir(tomcatDir);
            }
        } catch (URISyntaxException e) {
            BonitaStudioLog.error(e);
        }
        // delete project "server_configuration"
        var serverConfProject = ResourcesPlugin.getWorkspace().getRoot()
                .getProject(BOSWebServerManager.SERVER_CONFIGURATION_PROJECT);
        Optional.ofNullable(serverConfProject).filter(IProject::exists).ifPresent(IConsumer.safe(
                p -> p.delete(true, new NullProgressMonitor())));
        // Remove server prefs from WST
        ScopedPreferenceStore wstStore = new ScopedPreferenceStore(InstanceScope.INSTANCE,
                "org.eclipse.wst.server.core"); //$NON-NLS-1$
        wstStore.setValue("runtimes", ""); //$NON-NLS-1$ //$NON-NLS-2$
        // Remove the cached data repository binary & UID binary
        Bundle uiDesignerBundle = Platform.getBundle(UIDesignerPlugin.PLUGIN_ID);
        File uidCacheFolder = Platform.getStateLocation(uiDesignerBundle).toFile();
        if (uidCacheFolder.exists()) {
            FileUtil.deleteDir(uidCacheFolder);
        }
        // Remove internal maven repository
        var internalRepository = new File(MavenRepositories.internalRepository().getBasedir());
        if (internalRepository.exists()) {
            FileUtil.deleteDir(internalRepository);
        }

        // Remove org.eclipse.jdt.launching prefs, which may contain old invalid JRE path
        Bundle jdtLaunchingBundle = Platform.getBundle(LaunchingPlugin.ID_PLUGIN);
        File jdtLaunchingCacheFolder = Platform.getStateLocation(jdtLaunchingBundle).toFile();
        if (jdtLaunchingCacheFolder.exists()) {
            FileUtil.deleteDir(jdtLaunchingCacheFolder);
            jdtLaunchingCacheFolder.mkdirs();
        }
        ScopedPreferenceStore launchingStore = new ScopedPreferenceStore(InstanceScope.INSTANCE,
                LaunchingPlugin.ID_PLUGIN);
        launchingStore.setValue(JavaRuntime.PREF_VM_XML, launchingStore.getDefaultString(JavaRuntime.PREF_VM_XML));

        // Projects should migrate too...
        doProjectsMigration(shell);
    }

    /**
     * Do the migration for all Bonita projects
     * 
     * @param shell the splash shell to use as parent
     */
    protected void doProjectsMigration(Shell shell) {
        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        var bonitaProjects = Stream.of(projects).filter(this::isBonitaProject).collect(Collectors.toList());
        // first collect info regarding projects for migration (before they get deleted)
        Map<File, Boolean> projectRootsAndOpen = bonitaProjects.stream()
                .collect(Collectors.toMap(p -> p.getLocation().toFile(), IProject::isOpen));
        /*
         * Delete projects beforehand to allow migration jobs working nominally.
         * We won't use jobs here, because launching the application with non-migrated projects will mess up everything.
         * Also, if you delete then plan migration in a job, other jobs will conflict as migration is waiting for all auto-builds to end.
         * So just execute all migration steps and wait for the end...
         */
        ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(shell) {

            @Override
            protected Shell getParentShell() {
                // Use null shell to make migration progress dialog visible in the task manager of the OS
                return null;
            }
        };
        try {
            progressDialog.run(true, false, monitor -> {
                /*
                 * bonitaProjects.size() * (10 for deletion + 100 for migration) + 10 for refresh + 20 for maven dep install
                 */
                int totalWork = bonitaProjects.size() * 110 + 30;
                monitor.beginTask(Messages.projectsMigration, totalWork);
                SubMonitor subMonitor = SubMonitor.convert(monitor, totalWork);
                var lazyLoadEngine = System.getProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE);
                try {
                    // delete projects beforehand to allow migration jobs working nominally
                    Stream.of(projects).forEach(IConsumer.safe(p -> {
                        p.close(subMonitor.split(5));
                        p.delete(false, true, subMonitor.split(5));
                    }));
                    // refresh workspace to ensure we do not use old projects
                    IConsumer.safeConsume(subMonitor.split(10),
                            m -> ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_ZERO, m));
                    // we have to make sure maven is well configured before migration
                    IConsumer.safeConsume(subMonitor.split(20),
                            RepositoryManager.getInstance()::installRequiredMavenDependencies);

                    // Disable engine start during workspace migration
                    System.setProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE, Boolean.TRUE.toString());

                    // do the migrations steps (closed projects first, to keep active one opened
                    projectRootsAndOpen.forEach((projectRoot, wasOpened) -> {
                        if (!wasOpened) {
                            doProjectMigration(projectRoot, wasOpened, subMonitor);
                        }
                    });
                    projectRootsAndOpen.forEach((projectRoot, wasOpened) -> {
                        if (wasOpened) {
                            doProjectMigration(projectRoot, wasOpened, subMonitor);
                        }
                    });
                } finally {
                    monitor.done();
                    if (lazyLoadEngine != null) {
                        System.setProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE, lazyLoadEngine);
                    } else {
                        System.clearProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE);
                    }
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * Do the project migration
     * 
     * @param projectRoot the file root of the Bonita project
     * @param wasOpened whether project was opened
     * @param subMonitor progress sub-monitor with 100-work allocated for split
     */
    private void doProjectMigration(File projectRoot, boolean wasOpened, SubMonitor subMonitor) {
        /*
         * 100 = 55 for import + 15 to switch repo + 25 for migration post-steps + 5 for eventual close
         */
        subMonitor.subTask(NLS.bind(Messages.migratingProject, projectRoot.getName()));
        // import and migrate in a same operation
        var importOp = new ImportBonitaProjectOperation(projectRoot);
        IConsumer.safeConsume(subMonitor.split(55), importOp::run);
        // post-migration steps (including project refresh)
        var report = importOp.getReport();
        var bonitaProject = importOp.getBonitaProject();

        var currentRepo = RepositoryManager.getInstance().switchToRepository(
                bonitaProject.getId(),
                subMonitor.split(15));
        try {
            currentRepo.migrate(report, subMonitor.split(25));
            if (currentRepo.isShared()) {
                var oldVersion = Optional.ofNullable(workspaceInformation.get(Platform.getInstanceLocation().getURL()))
                        .map(Pair::getSecond).orElse(null);
                var commitMessage = oldVersion == null
                        ? String.format("Bonita '%s' automated migration", ProductVersion.CURRENT_VERSION) //$NON-NLS-1$
                        : String.format("Bonita '%s' to '%s' automated migration", oldVersion, //$NON-NLS-1$
                                ProductVersion.CURRENT_VERSION);
                bonitaProject.commitAll(commitMessage, subMonitor);
            }
        } catch (MigrationException | OperationCanceledException | CoreException e) {
            BonitaStudioLog.error(e);
        } finally {
            // close projects which were not opened
            if (!wasOpened) {
                IConsumer.safeConsume(subMonitor.split(5), bonitaProject::close);
            } else {
                subMonitor.worked(5);
            }
        }
    }

    /**
     * Test a single project to knwo whether it is a Bonita project to migrate
     * 
     * @param project project to test
     */
    protected boolean isBonitaProject(IProject project) {
        Path descPath = project.getFile(IProjectDescription.DESCRIPTION_FILE_NAME).getRawLocation().toFile()
                .toPath();
        try {
            IProjectDescription projectDesc = BonitaProjectMigrator.readDescriptor(descPath);
            // Bonita projects have the version as comment...
            return !Strings.isNullOrEmpty(projectDesc.getComment());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return false;
        }
    }

    /**
     * Consumes an object, eventually throwing a core exception
     */
    @FunctionalInterface
    static interface IConsumer<T> {

        /**
         * Consume an object
         * 
         * @param consumed the consumed object
         * @throws CoreException thrown exception
         */
        public void consume(T consumed) throws CoreException;

        /**
         * Make a consumer safe by catching and loging the exception
         * 
         * @param consumer the unsafe consumer
         * @return safe consumer
         */
        static <T> Consumer<T> safe(IConsumer<T> consumer) {
            return object -> {
                safeConsume(object, consumer);
            };
        }

        /**
         * Safely consume the object by catching and loging the exception
         * 
         * @param consumed the consumed object
         * @param consumer the unsafe consumer
         */
        static <T> void safeConsume(T consumed, IConsumer<T> consumer) {
            try {
                consumer.consume(consumed);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }

    }

    /**
     * @return the major, minor parts of the given version
     */
    protected static Version toMinorVersion(Version version) {
        return new Version(version.getMajor(), version.getMinor(), 0);
    }

    /**
     * @return the major, minor and micro parts of the given version
     */
    protected static Version toMaintenanceVersion(Version version) {
        return new Version(version.getMajor(), version.getMinor(), version.getMicro());
    }

    /**
     * Compare the version of the workspace (from URL) to the version of the running Studio.
     * 
     * @param url The URL of the workspace.
     * @return the versions comparison result
     * @see org.eclipse.ui.internal.ide.application.IDEApplication#compareWorkspaceAndIdeVersions(java.net.URL)
     */
    protected VersionsComparison compareWorkspaceAndStudioVersions(URL url) {
        // we are actually comparing Studio version, not Eclipse IDE version...
        Optional<Version> wsStudioVersion = readWorkspaceStudioVersion(url)
                .map(BonitaStudioApplication::toMaintenanceVersion);
        return compareWorkspaceAndStudioVersions(wsStudioVersion, url);
    }

    /**
     * The result of a comparison between workspace and runtime Studio versions
     */
    protected static enum VersionsComparison {

        /** The versions are compatible : Studio major/minor/maintenance runtime match the workspace version */
        COMPATIBLE_SAME_MAINTENANCE,
        /** The versions are compatible : Studio maintenance runtime is lower than workspace version, but major/minor match */
        COMPATIBLE_STUDIO_MAINTENANCE_LOWER_THAN_WORKSPACE,
        /** We need to migrate : Studio maintenance runtime is higher than workspace version, but major/minor match */
        NEEDS_MIGRATION_STUDIO_MAINTENANCE_HIGHER_THAN_WORKSPACE,
        /** We need to migrate : Studio major/minor runtime is higher than workspace version */
        NEEDS_MIGRATION_STUDIO_MINOR_HIGHER_THAN_WORKSPACE,
        /** The versions are not compatible : Studio major/minor runtime is lower than workspace version */
        INCOMPATIBLE_STUDIO_MINOR_LOWER_THAN_WORKSPACE;

        /**
         * @return true when migration is needed
         */
        public boolean needsMigration() {
            return NEEDS_MIGRATION_STUDIO_MAINTENANCE_HIGHER_THAN_WORKSPACE.equals(this)
                    || NEEDS_MIGRATION_STUDIO_MINOR_HIGHER_THAN_WORKSPACE.equals(this);
        }
    }

    /**
     * Compare the provided version of the workspace to the version of the running Studio.
     * 
     * @param wsStudioVersion The studio version used by workspace.
     * @param url The URL of the workspace.
     * @return the versions comparison result
     */
    protected VersionsComparison compareWorkspaceAndStudioVersions(Optional<Version> wsStudioVersion, URL url) {
        // we are actually comparing Studio version, not Eclipse IDE version...
        if (wsStudioVersion.isPresent()) {
            Version studioVersion = toMaintenanceVersion(WORKSPACE_CHECK_STUDIO_BUNDLE_VERSION);
            // optimize for case where maintenance versions match
            int maintenanceCompare = wsStudioVersion.get().compareTo(studioVersion);
            if (maintenanceCompare == 0) {
                return VersionsComparison.COMPATIBLE_SAME_MAINTENANCE;
            } else {
                // we need to check whether major/minor are still compatible
                int minorCompare = wsStudioVersion.map(IDEApplication::toMajorMinorVersion).get()
                        .compareTo(IDEApplication.toMajorMinorVersion(studioVersion));
                if (minorCompare == 0) {
                    // versions should be compatible but we may have to migrate maintenance for bug fixes
                    if (maintenanceCompare < 0) {
                        return VersionsComparison.NEEDS_MIGRATION_STUDIO_MAINTENANCE_HIGHER_THAN_WORKSPACE;
                    } else {
                        return VersionsComparison.COMPATIBLE_STUDIO_MAINTENANCE_LOWER_THAN_WORKSPACE;
                    }
                } else if (minorCompare < 0) {
                    // minorCompare and maintenanceCompare < 0
                    return VersionsComparison.NEEDS_MIGRATION_STUDIO_MINOR_HIGHER_THAN_WORKSPACE;
                } else {
                    // minorCompare and maintenanceCompare > 0
                    return VersionsComparison.INCOMPATIBLE_STUDIO_MINOR_LOWER_THAN_WORKSPACE;
                }
            }
        } else {
            /*
             * Version could not be read.
             * Workspaces from older Studio did not have the version.
             * But this could also be a brand new workspace...
             * Check whether metadata folder is empty.
             */
            File metaDir = new File(url.getPath(), METADATA_FOLDER);
            if (!metaDir.exists() || metaDir.list().length == 0) {
                // brand new directory that is just starting to be used as a workspace
                return VersionsComparison.COMPATIBLE_SAME_MAINTENANCE;
            } else {
                /*
                 * Try and migrate anyway. Assume the Studio version is greater.
                 * This might actually be erroneous, since we only know the Studio is more recent.
                 * E.g. if workspace is 7.15.1 (prior to fix), it does not have the version.
                 * We might try and migrate it to a 7.14.6 version if it were including the fix.
                 * For this reason, this fix should not be contributed to maintenance versions,
                 * to ensure we only upgrade and never downgrade.
                 */
                return VersionsComparison.NEEDS_MIGRATION_STUDIO_MINOR_HIGHER_THAN_WORKSPACE;
            }
        }
    }

    /**
     * Read the Studio version which the workspace was written with.
     * 
     * @param url The URL of the workspace.
     * @return version if found and null otherwise.
     */
    protected static Optional<Version> readWorkspaceStudioVersion(URL url) {
        File versionFile = getVersionFile(url, false);
        if (versionFile == null || !versionFile.exists()) {
            // Workspaces from older Studio did not have the version.
            return Optional.empty();
        }
        Properties props = new Properties();
        try (FileInputStream is = new FileInputStream(versionFile)) {
            props.load(is);
            String wsStudioVersionString = props.getProperty(WORKSPACE_CHECK_STUDIO_BUNDLE_NAME);
            return Optional.ofNullable(wsStudioVersionString).map(Version::parseVersion);
        } catch (IOException e) {
            BonitaStudioLog.error(NLS.bind(Messages.couldNotReadVersion, versionFile), e);
        } catch (IllegalArgumentException e) {
            BonitaStudioLog.error(NLS.bind(Messages.couldNotParseVersion, versionFile), e);
        }
        return Optional.empty();
    }

    @Override
    public void stop() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench == null) {
            return;
        }
        final Display display = workbench.getDisplay();
        display.syncExec(new Runnable() {

            @Override
            public void run() {
                if (!display.isDisposed()) {
                    workbench.close();
                }
            }
        });
    }

    protected String getJavaVersion() {
        return System.getProperty("java.version", "1.6"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.IJobChangeListener#aboutToRun(org.eclipse.core.runtime.jobs.IJobChangeEvent)
     */
    @Override
    public void aboutToRun(IJobChangeEvent event) {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.IJobChangeListener#awake(org.eclipse.core.runtime.jobs.IJobChangeEvent)
     */
    @Override
    public void awake(IJobChangeEvent event) {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
     */
    @Override
    public void done(IJobChangeEvent event) {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.IJobChangeListener#running(org.eclipse.core.runtime.jobs.IJobChangeEvent)
     */
    @Override
    public void running(IJobChangeEvent event) {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.IJobChangeListener#sleeping(org.eclipse.core.runtime.jobs.IJobChangeEvent)
     */
    @Override
    public void sleeping(IJobChangeEvent event) {
        // do nothing
    }
}
