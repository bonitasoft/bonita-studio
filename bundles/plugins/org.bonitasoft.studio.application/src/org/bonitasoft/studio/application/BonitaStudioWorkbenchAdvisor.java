/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.common.Messages.bosProductName;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.bonitasoft.studio.application.contribution.IPreShutdownContribution;
import org.bonitasoft.studio.application.contribution.IPreStartupContribution;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.job.StartEngineJob;
import org.bonitasoft.studio.application.registration.BonitaRegistration;
import org.bonitasoft.studio.application.splash.BOSSplashHandler;
import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.PerspectiveIDRegistry;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.job.WorkspaceInitializationJob;
import org.bonitasoft.studio.common.repository.extension.IPostInitRepositoryJobContribution;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.model.process.impl.ContractInputImpl;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.codehaus.groovy.eclipse.launchers.GroovyConsoleLineTracker;
import org.eclipse.core.internal.databinding.beans.BeanPropertyHelper;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gmf.runtime.lite.svg.SVGFigure;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.browser.WebBrowserUtil;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.progress.ProgressMonitorJobsDialog;
import org.eclipse.ui.internal.splash.SplashHandlerFactory;
import org.osgi.framework.Bundle;

import com.google.common.base.Joiner;

public class BonitaStudioWorkbenchAdvisor extends WorkbenchAdvisor implements IStartup {

    private final class PreShutdownStudio implements IRunnableWithProgress {

        @Override
        public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
            monitor.beginTask(Messages.shuttingDown, IProgressMonitor.UNKNOWN);
            Job.getJobManager().cancel(StartEngineJob.FAMILY);
            executePreShutdownContribution();
            BOSEngineManager.getInstance().stop();
            FileUtil.deleteDir(ProjectUtil.getBonitaStudioWorkFolder());
            monitor.done();
        }

        private void executePreShutdownContribution() {
            final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                    "org.bonitasoft.studio.application.preshutdown"); //$NON-NLS-1$
            IPreShutdownContribution contrib = null;
            for (final IConfigurationElement elem : elements) {
                try {
                    contrib = (IPreShutdownContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
                contrib.execute();
            }
        }
    }

    protected static final String PRIORITY = "priority";

    private IProgressMonitor monitor;

    @Inject
    private RepositoryAccessor repositoryAccessor;

    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer) {
        return new BonitaStudioWorkbenchWindowAdvisor(configurer);
    }

    @Override
    public void initialize(final IWorkbenchConfigurer configurer) {
        super.initialize(configurer);
        configurer.setSaveAndRestore(true);
        final IContextService contextService = (IContextService) PlatformUI.getWorkbench().getService(IContextService.class);
        contextService.activateContext("org.bonitasoft.studio.context.id");
        initializeIDEImages(configurer);
    }

    /**
     * Workaround to load icons for Common Navigator componenet in a RCP
     */
    private void initializeIDEImages(final IWorkbenchConfigurer configurer) {
        IDE.registerAdapters();
        //Declared projects icon
        final String ICONS_PATH = "icons/full/obj16/";
        final Bundle ideBundle = Platform.getBundle(IDEWorkbenchPlugin.IDE_WORKBENCH);
        configurer.declareImage(IDE.SharedImages.IMG_OBJ_PROJECT, ImageDescriptor.createFromURL(ideBundle.getEntry(ICONS_PATH + "prj_obj.gif")), true);
        configurer.declareImage(IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, ImageDescriptor.createFromURL(ideBundle.getEntry(ICONS_PATH + "cprj_obj.gif")), true);
    }

    @Override
    public String getInitialWindowPerspectiveId() {
        return PerspectiveIDRegistry.PROCESS_PERSPECTIVE_ID;
    }

    @Override
    public String getMainPreferencePageId() {
        return "org.bonitasoft.studio.preferences.preferences.UIPreferencePage";
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.application.WorkbenchAdvisor#preStartup()
     */
    @Override
    public void preStartup() {
        try {
            SplashHandlerFactory.findSplashHandlerFor(Platform.getProduct());
            monitor = BOSSplashHandler.getMonitor();
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        if (monitor == null) {
            BonitaStudioLog.log("Progress Monitor is null");
            monitor = Repository.NULL_PROGRESS_MONITOR;
        }

        monitor.beginTask(BOSSplashHandler.BONITA_TASK, 100);
        monitor.subTask(Messages.initializingCurrentRepository);

        disableInternalWebBrowser();
        checkCurrentRepository(monitor);

        final List<IConfigurationElement> sortedConfigElems = retrievePreStartupContribution();
        sortConfigurationElementsByPriority(sortedConfigElems);
        executeConfigurationElement(sortedConfigElems);

        doInitWorkspace();
        doStartEngine();
        executeContributions();
    }

    protected void doInitWorkspace() {
        new WorkspaceInitializationJob(repositoryAccessor).schedule();
    }

    private void checkCurrentRepository(final IProgressMonitor monitor) {
        final String current = CommonRepositoryPlugin.getDefault().getPreferenceStore().getString(RepositoryPreferenceConstant.CURRENT_REPOSITORY);
        final IRepository repository = RepositoryManager.getInstance().getCurrentRepository();
        if (repository.getProject().exists() && !RepositoryPreferenceConstant.DEFAULT_REPOSITORY_NAME.equals(repository.getName())) {
            if (!repository.getProject().isOpen()) {
                repository.open(monitor);
            }
            if (!repository.isOnline()) {
                MessageDialog.openWarning(
                        Display.getDefault().getActiveShell(),
                        Messages.offlineRepositoryTitle,
                        Messages.bind(Messages.offlineRepositoryMessage, current));
            }
            final String version = repository.getVersion();
            if (!ProductVersion.sameMinorVersion(version)) {
                MessageDialog.openWarning(
                        Display.getDefault().getActiveShell(),
                        Messages.badWorkspaceVersionTitle,
                        Messages.bind(Messages.badWorkspaceVersionMessage, new Object[] { current, version, bonitaStudioModuleName,
                                ProductVersion.CURRENT_VERSION, bosProductName }));
                resetToDefaultRepository(monitor);
            }

        }
    }

    protected void resetToDefaultRepository(final IProgressMonitor monitor) {
        CommonRepositoryPlugin.setCurrentRepository(RepositoryPreferenceConstant.DEFAULT_REPOSITORY_NAME);
        RepositoryManager.getInstance().setRepository(RepositoryPreferenceConstant.DEFAULT_REPOSITORY_NAME, monitor);
    }

    private List<IConfigurationElement> retrievePreStartupContribution() {
        final IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.application.prestartup"); //$NON-NLS-1$
        final List<IConfigurationElement> sortedConfigElems = new ArrayList<IConfigurationElement>();
        for (final IConfigurationElement elem : elems) {
            sortedConfigElems.add(elem);
        }
        return sortedConfigElems;
    }

    private void executeConfigurationElement(final List<IConfigurationElement> sortedConfigElems) {
        IPreStartupContribution preStartupcontrib = null;
        for (final IConfigurationElement elem : sortedConfigElems) {
            try {
                preStartupcontrib = (IPreStartupContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            try {
                if (preStartupcontrib.canExecute()) {
                    preStartupcontrib.execute();
                    monitor.worked(1);
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
                System.exit(-1);
            }

        }
    }

    private void sortConfigurationElementsByPriority(
            final List<IConfigurationElement> sortedConfigElems) {
        Collections.sort(sortedConfigElems, new Comparator<IConfigurationElement>() {

            @Override
            public int compare(final IConfigurationElement e1, final IConfigurationElement e2) {
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

        });
    }

    private void executeContributions() {
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.common.repository.postinitrepository"); //$NON-NLS-1$
        IPostInitRepositoryJobContribution contrib = null;
        for (final IConfigurationElement elem : elements) {
            try {
                contrib = (IPostInitRepositoryJobContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            contrib.execute();
        }
    }

    /**
     * Disconnect from the core workspace.
     */
    private void disconnectFromWorkspace(final IProgressMonitor monitor) {
        // save the workspace
        final MultiStatus status = new MultiStatus(
                IDEWorkbenchPlugin.IDE_WORKBENCH, 1,
                IDEWorkbenchMessages.ProblemSavingWorkbench, null);
        try {
            final ProgressMonitorJobsDialog p = new ProgressMonitorJobsDialog(
                    null);

            final boolean applyPolicy = ResourcesPlugin.getWorkspace()
                    .getDescription().isApplyFileStatePolicy();

            final IRunnableWithProgress runnable = new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) {
                    try {
                        if (applyPolicy) {
                            status.merge(((Workspace) ResourcesPlugin
                                    .getWorkspace()).save(true, true, monitor));
                        }
                    } catch (final CoreException e) {
                        status.merge(e.getStatus());
                    }
                }
            };
            p.run(true, false, runnable);
        } catch (final InvocationTargetException e) {
            status
                    .merge(new Status(IStatus.ERROR,
                            IDEWorkbenchPlugin.IDE_WORKBENCH, 1,
                            IDEWorkbenchMessages.InternalError, e
                                    .getTargetException()));
        } catch (final InterruptedException e) {
            status.merge(new Status(IStatus.ERROR,
                    IDEWorkbenchPlugin.IDE_WORKBENCH, 1,
                    IDEWorkbenchMessages.InternalError, e));
        }
        ErrorDialog.openError(null,
                IDEWorkbenchMessages.ProblemsSavingWorkspace, null, status,
                IStatus.ERROR | IStatus.WARNING);
        if (!status.isOK()) {
            IDEWorkbenchPlugin.log(
                    IDEWorkbenchMessages.ProblemsSavingWorkspace, status);
        }
    }

    protected void disableInternalWebBrowser() {
        final String noRegister = System.getProperty("bonita.noregister"); //$NON-NLS-1$
        if (noRegister == null || !noRegister.equals("1")) {
            WebBrowserUtil.isInternalBrowserOperational = false;
        }
    }

    protected void doStartEngine() {
        if (PlatformUtil.isHeadless()) {
            return;
        }
        //Avoid deadlock when starting engine caused by ProcessConsoleManger triggering some UI dependent code in a non UI thread.
        new GroovyConsoleLineTracker();
        final StartEngineJob job = new StartEngineJob("Starting BOS Engine");
        job.setPriority(Job.DECORATE);
        job.setUser(false);
        job.schedule();
    }

    private void openStartupDialog() {
        final String noRegister = System.getProperty("bonita.noregister"); //$NON-NLS-1$
        if (noRegister == null || !noRegister.equals("1")) { //$NON-NLS-1$
            final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                    "org.bonitasoft.studio.common.startupDialog"); //$NON-NLS-1$
            IPostStartupContribution contrib = null;
            for (final IConfigurationElement elem : elements) {
                try {
                    contrib = (IPostStartupContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
                contrib.execute();
            }
        }
    }

    @Override
    public void postShutdown() {
        super.postShutdown();
        disconnectFromWorkspace(Repository.NULL_PROGRESS_MONITOR);
    }

    @Override
    public boolean preShutdown() {
        Job.getJobManager().cancel(StartEngineJob.FAMILY);
        final boolean returnValue = super.preShutdown();
        if (returnValue) {
            try {
                if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                        && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
                    final boolean closeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(true);
                    if (closeEditor) {
                        PlatformUI.getWorkbench().getProgressService().run(true, false, new PreShutdownStudio());
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllPerspectives(false, true);
                    }
                    return closeEditor;
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        return returnValue;
    }

    private void sendUserInfo() {
        new BonitaRegistration(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()).sendUserInfoIfNotSent();;
    }

    @Override
    public void earlyStartup() {
        if (PlatformUtil.isHeadless()) {
            return;//Do not execute earlyStartup in headless mode
        }
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.common.poststartup"); //$NON-NLS-1$
        IPostStartupContribution contrib = null;
        for (final IConfigurationElement elem : elements) {
            try {
                contrib = (IPostStartupContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            contrib.execute();
        }

        preLoad();

        final long startupDuration = System.currentTimeMillis() - BonitaStudioApplication.START_TIME;
        BonitaStudioLog.info("Startup duration : " + DateUtil.getDisplayDuration(startupDuration), ApplicationPlugin.PLUGIN_ID);
    }

    private void preLoad() {
        //Fix performance issue
        BeanPropertyHelper.getPropertyDescriptor(ContractInputImpl.class, "name");
        preLoadSVG();
    }

    private void preLoadSVG() {
        final SVGFigure svgFigure = new SVGFigure();
        try {
            final File iconsFolder = new File(FileLocator.toFileURL(Platform.getBundle("org.bonitasoft.studio.pics").getResource("icons")).getFile());
            initSVGFigure(svgFigure, iconsFolder, "figures");
            initSVGFigure(svgFigure, iconsFolder, "decoration", "svg");
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    private void initSVGFigure(final SVGFigure svgFigure, final File iconsFolder, final String... pathToFolder) {
        for (final String filename : new File(iconsFolder, Joiner.on(File.separatorChar).join(pathToFolder)).list()) {
            if (filename.endsWith(".svgz")) {
                svgFigure.setURI("platform:/plugin/org.bonitasoft.studio.pics/icons/" + Joiner.on("/").join(pathToFolder) + "/" + filename);
            }
        }
    }

    @Override
    public void postStartup() {
        super.postStartup();
        try {
            Job.getJobManager().join(WorkspaceInitializationJob.WORKSPACE_INIT_FAMILY, monitor);
        } catch (final OperationCanceledException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }
        if (PlatformUI.isWorkbenchRunning()) {
            sendUserInfo();
            openStartupDialog();
        }
    }

}
