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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

import org.bonitasoft.studio.application.contribution.IPreStartupContribution;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.editingdomain.BonitaOperationHistory;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.MessageDialogWithLink;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMInstallType;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Version;

/**
 * This class controls all aspects of the application's execution
 */
public class BonitaStudioApplication extends JobChangeAdapter implements IApplication {

    private static final String HOTSPOT_JRE_11 = "OpenJDK Hotspot JRE 11";
    private static final String JAVA_11 = "11";
    private Display display;
    public static final String PREFERENCES_FILE = ".wsPreferences";
    public static final String WS_ROOT = "wsRootDir";
    public static final String ONLINE_DOC_REQUIREMENTS = "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=165&bos_redirect_product=bos&bos_redirect_major_version=";
    private static final String PRIORITY = "priority";

    public static long START_TIME = 0;

    public BonitaStudioApplication() {
    }

    public BonitaStudioApplication(final Display display) {
        this.display = display;
    }

    @Override
    public Object start(final IApplicationContext context) {
        START_TIME = System.currentTimeMillis();
        //avoid the execution of AutoBuild job during startup
        addBuildJobListener();
        if (display == null) {
            display = PlatformUI.createDisplay();
        }
        if (!isJavaVersionSupported(display)) {
            return IApplication.EXIT_OK;
        }

        initWorkspaceLocation();
        executePreStartupContributions();

        //set our custom operation factory
        OperationHistoryFactory.setOperationHistory(new BonitaOperationHistory());
        return createAndRunWorkbench(display);
    }

    private void executePreStartupContributions() {
        Stream.of(BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.application.prestartup"))
                .sorted(this::sortContribution)
                .map(elem -> {
                    try {
                        return elem.createExecutableExtension("class");
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
        try {
            final int returnCode = PlatformUI.createAndRunWorkbench(display, createWorkbenchAdvisor());
            if (returnCode == PlatformUI.RETURN_RESTART) {
                return IApplication.EXIT_RESTART;
            }
            return IApplication.EXIT_OK;
        } finally {
            display.dispose();
        }
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
        if (!javaVersion.startsWith(JAVA_11)) {
            openErrorDialog(display, javaVersion);
            return false;
        }
        return true;
    }

    protected void openErrorDialog(final Display display, final String javaVersion) {
        final Shell shell = new Shell(display);
        try {
            final Version version = Version.parseVersion(ProductVersion.CURRENT_VERSION);
            final String uriWithProductVersion = ONLINE_DOC_REQUIREMENTS + version.getMajor() + "."
                    + version.getMinor();
            final URI uri = new URI(uriWithProductVersion);
            final MessageDialogWithLink messageDialog = new MessageDialogWithLink(shell,
                    Messages.incompatibleJavaVersionTitle, null, String.format(
                            Messages.incompatibleJavaVersionMessage,
                            org.bonitasoft.studio.common.Messages.bonitaStudioModuleName, javaVersion,
                            "Java 11."),
                    MessageDialog.ERROR,
                    new String[] { IDialogConstants.OK_LABEL },
                    0,
                    uri);
            messageDialog.open();
        } catch (final URISyntaxException e) {
            BonitaStudioLog.error(e);
        } finally {
            shell.dispose();
        }
    }

    protected void initWorkspaceLocation() {
        final Location instanceLoc = Platform.getInstanceLocation();
        //if workspace is set via -Data, can't reset it
        if (!instanceLoc.isSet()) {
            final String path2 = Platform.getInstallLocation().getURL().getFile() + File.separator + PREFERENCES_FILE;
            String lastUsedWs = null;//preferences.get(WS_ROOT, null);
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
                    instanceLoc.set(new URL("file", null, lastUsedWs), true);
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
            //no pref found use default ws location
            if (!instanceLoc.isSet()) {
                final String path = Platform.getInstallLocation().getURL().getPath() + "workspace";
                try {
                    instanceLoc.set(new URL("file", null, path), true);
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        File installLocation = new File(Platform.getInstallLocation().getURL().getPath());
        File jre11Location = new File(installLocation, "jre");
        IVMInstall defaultVMInstall = JavaRuntime.getDefaultVMInstall();
        if (!Objects.equals(Platform.getOS(), Platform.OS_MACOSX) && jre11Location.exists()) {
            if (defaultVMInstall.getName() != null
                    && !Objects.equals(defaultVMInstall.getName(), HOTSPOT_JRE_11)
                    && Objects.equals(defaultVMInstall.getInstallLocation(), installLocation)) { //Remove invalid JRE 
                defaultVMInstall.getVMInstallType().disposeVMInstall(defaultVMInstall.getId());
            }
            IVMInstallType type = JavaRuntime
                    .getVMInstallType("org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType");
            IVMInstall jre11Install = type.findVMInstall(HOTSPOT_JRE_11);
            if (jre11Install == null) {
                jre11Install = type.createVMInstall(HOTSPOT_JRE_11);
                jre11Install.setName(HOTSPOT_JRE_11);
                jre11Install.setInstallLocation(jre11Location);
                try {
                    JavaRuntime.setDefaultVMInstall(jre11Install, new NullProgressMonitor(), true);
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
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
        return System.getProperty("java.version", "1.6");
    }
}
