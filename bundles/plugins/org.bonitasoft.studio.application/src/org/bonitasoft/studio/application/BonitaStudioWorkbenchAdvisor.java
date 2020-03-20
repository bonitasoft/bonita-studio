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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.bonitasoft.studio.application.contribution.IPreShutdownContribution;
import org.bonitasoft.studio.application.handler.OpenReleaseNoteHandler;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.splash.BOSSplashHandler;
import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.extension.IPostInitRepositoryJobContribution;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.server.StartEngineJob;
import org.bonitasoft.studio.model.process.impl.ContractInputImpl;
import org.codehaus.groovy.eclipse.dsl.DSLPreferences;
import org.codehaus.groovy.eclipse.dsl.DSLPreferencesInitializer;
import org.codehaus.groovy.eclipse.dsl.GroovyDSLCoreActivator;
import org.codehaus.groovy.eclipse.launchers.GroovyConsoleLineTracker;
import org.eclipse.core.internal.databinding.beans.BeanPropertyHelper;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.gmf.runtime.lite.svg.SVGFigure;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorMapping;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.browser.WebBrowserUtil;
import org.eclipse.ui.internal.ide.IDEInternalWorkbenchImages;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.progress.ProgressMonitorJobsDialog;
import org.eclipse.ui.internal.registry.EditorRegistry;
import org.eclipse.ui.internal.registry.FileEditorMapping;
import org.eclipse.ui.internal.splash.SplashHandlerFactory;
import org.eclipse.wst.html.core.internal.HTMLCorePlugin;
import org.eclipse.wst.html.core.internal.preferences.HTMLCorePreferenceNames;
import org.eclipse.wst.xml.core.internal.XMLCorePlugin;
import org.eclipse.wst.xml.core.internal.preferences.XMLCorePreferenceNames;
import org.osgi.framework.Bundle;

import com.google.common.base.Joiner;

public class BonitaStudioWorkbenchAdvisor extends WorkbenchAdvisor implements IStartup {

    private final class PreShutdownStudio implements IRunnableWithProgress {

        @Override
        public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
            monitor.beginTask(Messages.shuttingDown, IProgressMonitor.UNKNOWN);
            UIDesignerServerManager.getInstance().stop();
            Job.getJobManager().cancel(StartEngineJob.FAMILY);
            executePreShutdownContribution();
            if (BOSWebServerManager.getInstance().serverIsStarted() && BOSEngineManager.getInstance().isRunning()) {
                BOSEngineManager.getInstance().stop();
            }
            FileUtil.deleteDir(ProjectUtil.getBonitaStudioWorkFolder());
            deleteTomcatTempDir();
            monitor.done();
        }

        private void deleteTomcatTempDir() {
            File tempDir = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString() + File.separator
                    + "tomcat" + File.separator + "server" + File.separator + "temp");
            if (tempDir.exists()) {
                FileUtil.deleteDir(tempDir);
            }
        }

        private void executePreShutdownContribution() {
            final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                    .getConfigurationElements(
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

    private static final String FIRST_STARTUP = "firstStartup";

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
        final IContextService contextService = PlatformUI.getWorkbench().getService(IContextService.class);
        contextService.activateContext("org.bonitasoft.studio.context.id");
        initializeIDEImages(configurer);
    }

    /**
     * Workaround to load icons for Common Navigator component in a RCP
     */
    private void initializeIDEImages(final IWorkbenchConfigurer configurer) {
        IDE.registerAdapters();
        declareWorkbenchImages();
    }

    /********************************************************************
     * /!\ Copy-pasted from IDEWorkbenchAvisor.declareWorkbenchImages() *
     * ******************************************************************
     * Declares all IDE-specific workbench images. This includes both "shared"
     * images (named in {@link org.eclipse.ui.ide.IDE.SharedImages}) and internal images (named in
     * {@link org.eclipse.ui.internal.ide.IDEInternalWorkbenchImages}).
     *
     * @see IWorkbenchConfigurer#declareImage
     */
    private void declareWorkbenchImages() {

        final String ICONS_PATH = "$nl$/icons/full/";//$NON-NLS-1$
        final String PATH_ELOCALTOOL = ICONS_PATH + "elcl16/"; // Enabled //$NON-NLS-1$

        // toolbar
        // icons.
        final String PATH_DLOCALTOOL = ICONS_PATH + "dlcl16/"; // Disabled //$NON-NLS-1$
        // //$NON-NLS-1$
        // toolbar
        // icons.
        final String PATH_ETOOL = ICONS_PATH + "etool16/"; // Enabled toolbar //$NON-NLS-1$
        // //$NON-NLS-1$
        // icons.
        final String PATH_DTOOL = ICONS_PATH + "dtool16/"; // Disabled toolbar //$NON-NLS-1$
        // //$NON-NLS-1$
        // icons.
        final String PATH_OBJECT = ICONS_PATH + "obj16/"; // Model object //$NON-NLS-1$
        // //$NON-NLS-1$
        // icons
        final String PATH_WIZBAN = ICONS_PATH + "wizban/"; // Wizard //$NON-NLS-1$
        // //$NON-NLS-1$
        // icons

        // View icons
        final String PATH_EVIEW = ICONS_PATH + "eview16/"; //$NON-NLS-1$

        final Bundle ideBundle = Platform.getBundle(IDEWorkbenchPlugin.IDE_WORKBENCH);

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_BUILD_EXEC, PATH_ETOOL
                        + "build_exec.png", //$NON-NLS-1$
                false);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_BUILD_EXEC_HOVER,
                PATH_ETOOL + "build_exec.png", false); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_BUILD_EXEC_DISABLED,
                PATH_DTOOL + "build_exec.png", false); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_SEARCH_SRC, PATH_ETOOL
                        + "search_src.png", //$NON-NLS-1$
                false);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_SEARCH_SRC_HOVER,
                PATH_ETOOL + "search_src.png", false); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_SEARCH_SRC_DISABLED,
                PATH_DTOOL + "search_src.png", false); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_NEXT_NAV, PATH_ETOOL
                        + "next_nav.png", //$NON-NLS-1$
                false);

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_PREVIOUS_NAV, PATH_ETOOL
                        + "prev_nav.png", //$NON-NLS-1$
                false);

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_WIZBAN_NEWPRJ_WIZ, PATH_WIZBAN
                        + "newprj_wiz.png", //$NON-NLS-1$
                false);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_WIZBAN_NEWFOLDER_WIZ,
                PATH_WIZBAN + "newfolder_wiz.png", false); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_WIZBAN_NEWFILE_WIZ, PATH_WIZBAN
                        + "newfile_wiz.png", //$NON-NLS-1$
                false);

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_WIZBAN_IMPORTDIR_WIZ,
                PATH_WIZBAN + "importdir_wiz.png", false); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_WIZBAN_IMPORTZIP_WIZ,
                PATH_WIZBAN + "importzip_wiz.png", false); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_WIZBAN_EXPORTDIR_WIZ,
                PATH_WIZBAN + "exportdir_wiz.png", false); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_WIZBAN_EXPORTZIP_WIZ,
                PATH_WIZBAN + "exportzip_wiz.png", false); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_WIZBAN_RESOURCEWORKINGSET_WIZ,
                PATH_WIZBAN + "workset_wiz.png", false); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_DLGBAN_SAVEAS_DLG, PATH_WIZBAN
                        + "saveas_wiz.png", //$NON-NLS-1$
                false);

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_DLGBAN_QUICKFIX_DLG, PATH_WIZBAN
                        + "quick_fix.png", //$NON-NLS-1$
                false);

        declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT,
                PATH_OBJECT + "prj_obj.png", true); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, PATH_OBJECT
                        + "cprj_obj.png", //$NON-NLS-1$
                true);
        declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OPEN_MARKER,
                PATH_ELOCALTOOL + "gotoobj_tsk.png", true); //$NON-NLS-1$

        // Quick fix icons
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ELCL_QUICK_FIX_ENABLED,
                PATH_ELOCALTOOL + "smartmode_co.png", true); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_DLCL_QUICK_FIX_DISABLED,
                PATH_DLOCALTOOL + "smartmode_co.png", true); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_FIXABLE_WARNING,
                PATH_OBJECT + "quickfix_warning_obj.png", true); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_FIXABLE_ERROR,
                PATH_OBJECT + "quickfix_error_obj.png", true); //$NON-NLS-1$

        // task objects
        // declareRegistryImage(IDEInternalWorkbenchImages.IMG_OBJS_HPRIO_TSK,
        // PATH_OBJECT+"hprio_tsk.png");
        // declareRegistryImage(IDEInternalWorkbenchImages.IMG_OBJS_MPRIO_TSK,
        // PATH_OBJECT+"mprio_tsk.png");
        // declareRegistryImage(IDEInternalWorkbenchImages.IMG_OBJS_LPRIO_TSK,
        // PATH_OBJECT+"lprio_tsk.png");

        declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OBJS_TASK_TSK,
                PATH_OBJECT + "taskmrk_tsk.png", true); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OBJS_BKMRK_TSK,
                PATH_OBJECT + "bkmrk_tsk.png", true); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_COMPLETE_TSK, PATH_OBJECT
                        + "complete_tsk.png", //$NON-NLS-1$
                true);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_INCOMPLETE_TSK, PATH_OBJECT
                        + "incomplete_tsk.png", //$NON-NLS-1$
                true);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_WELCOME_ITEM, PATH_OBJECT
                        + "welcome_item.png", //$NON-NLS-1$
                true);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_WELCOME_BANNER, PATH_OBJECT
                        + "welcome_banner.png", //$NON-NLS-1$
                true);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_ERROR_PATH, PATH_OBJECT
                        + "error_tsk.png", //$NON-NLS-1$
                true);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_WARNING_PATH, PATH_OBJECT
                        + "warn_tsk.png", //$NON-NLS-1$
                true);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_OBJS_INFO_PATH, PATH_OBJECT
                        + "info_tsk.png", //$NON-NLS-1$
                true);

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_LCL_FLAT_LAYOUT, PATH_ELOCALTOOL
                        + "flatLayout.png", //$NON-NLS-1$
                true);
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_LCL_HIERARCHICAL_LAYOUT,
                PATH_ELOCALTOOL + "hierarchicalLayout.png", true); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_PROBLEM_CATEGORY,
                PATH_ETOOL + "problem_category.png", true); //$NON-NLS-1$

        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_PROBLEMS_VIEW,
                PATH_EVIEW + "problems_view.png", true); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_PROBLEMS_VIEW_ERROR,
                PATH_EVIEW + "problems_view_error.png", true); //$NON-NLS-1$
        declareWorkbenchImage(ideBundle,
                IDEInternalWorkbenchImages.IMG_ETOOL_PROBLEMS_VIEW_WARNING,
                PATH_EVIEW + "problems_view_warning.png", true); //$NON-NLS-1$
    }

    /**
     * Declares an IDE-specific workbench image.
     *
     * @param symbolicName
     *        the symbolic name of the image
     * @param path
     *        the path of the image file; this path is relative to the base
     *        of the IDE plug-in
     * @param shared
     *        <code>true</code> if this is a shared image, and
     *        <code>false</code> if this is not a shared image
     * @see IWorkbenchConfigurer#declareImage
     */
    private void declareWorkbenchImage(final Bundle ideBundle, final String symbolicName,
            final String path, final boolean shared) {
        final URL url = FileLocator.find(ideBundle, new Path(path), null);
        final ImageDescriptor desc = ImageDescriptor.createFromURL(url);
        getWorkbenchConfigurer().declareImage(symbolicName, desc, shared);
    }

    @Override
    public String getInitialWindowPerspectiveId() {
        return null;
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
            monitor = Repository.NULL_PROGRESS_MONITOR;
        }

        monitor.beginTask(BOSSplashHandler.BONITA_TASK, 100);
        disableInternalWebBrowser();
        disableGroovyDSL();
        initXMLandHTMLValidationPreferences();
        //Avoid deadlock and thread timeout at startup
        new GroovyConsoleLineTracker();
        repositoryAccessor.start(monitor);

        executeContributions();
    }

    protected void initXMLandHTMLValidationPreferences() {
        IEclipsePreferences xmlNode = DefaultScope.INSTANCE
                .getNode(XMLCorePlugin.getDefault().getBundle().getSymbolicName());
        xmlNode.putInt(XMLCorePreferenceNames.INDICATE_NO_GRAMMAR, -1);

        IEclipsePreferences htmlNode = DefaultScope.INSTANCE
                .getNode(HTMLCorePlugin.getDefault().getBundle().getSymbolicName());
        htmlNode.putInt(HTMLCorePreferenceNames.ATTRIBUTE_INVALID_NAME, -1);
        htmlNode.putInt(HTMLCorePreferenceNames.ATTRIBUTE_INVALID_VALUE, -1);
        htmlNode.putInt(HTMLCorePreferenceNames.ATTRIBUTE_UNDEFINED_NAME, -1);
        htmlNode.putInt(HTMLCorePreferenceNames.ATTRIBUTE_UNDEFINED_VALUE, -1);
        htmlNode.putInt(HTMLCorePreferenceNames.ATTRIBUTE_VALUE_EQUALS_MISSING, -1);
        htmlNode.putInt(HTMLCorePreferenceNames.ELEM_UNKNOWN_NAME, -1);
    }

    protected void disableGroovyDSL() {
        final IPreferenceStore groovyDSLstore = GroovyDSLCoreActivator.getDefault().getPreferenceStore();
        groovyDSLstore.setDefault(DSLPreferencesInitializer.AUTO_ADD_DSL_SUPPORT, false);
        groovyDSLstore.setValue(DSLPreferencesInitializer.AUTO_ADD_DSL_SUPPORT, false);
        groovyDSLstore.setDefault(DSLPreferencesInitializer.DSLD_DISABLED, true);
        groovyDSLstore.setValue(DSLPreferencesInitializer.DSLD_DISABLED, true);
        groovyDSLstore.setDefault(DSLPreferences.DISABLED_SCRIPTS, false);
        groovyDSLstore.setValue(DSLPreferences.DISABLED_SCRIPTS, false);
    }

    private void executeContributions() {
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(
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

    @Override
    public void postShutdown() {
        super.postShutdown();
        disconnectFromWorkspace(Repository.NULL_PROGRESS_MONITOR);
    }

    @Override
    public boolean preShutdown() {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage();
        Stream.of(activePage.getViewReferences())
                .filter(vr -> Objects.equals("org.eclipse.ui.browser.view", vr.getId()))
                .forEach(vr -> activePage.hideView(vr));
        Job.getJobManager().cancel(StartEngineJob.FAMILY);
        final boolean returnValue = super.preShutdown();
        if (returnValue) {
            try {
                if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                        && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
                    PlatformUI.getWorkbench().getProgressService().run(true, false, new PreShutdownStudio());
                    return true;
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        return returnValue;
    }

    @Override
    public void earlyStartup() {
        if (PlatformUtil.isHeadless()) {
            return;//Do not execute earlyStartup in headless mode
        }
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(
                        "org.bonitasoft.studio.common.poststartup"); //$NON-NLS-1$
        for (final IConfigurationElement elem : elements) {
            final Workbench workbench = (Workbench) PlatformUI.getWorkbench();
            try {
                IPostStartupContribution contrib = (IPostStartupContribution) ContextInjectionFactory
                        .make(Platform.getBundle(elem.getDeclaringExtension().getNamespaceIdentifier())
                                .loadClass(elem.getAttribute("class")), workbench.getContext());
                Display.getDefault().asyncExec(contrib::execute);
            } catch (InjectionException | ClassNotFoundException | InvalidRegistryObjectException e) {
                BonitaStudioLog.error(e);
            }
        }

        preLoad();
        
        // Fix issue with asciidoctor plugin overriding text content-type
        final EditorRegistry editorRegistry = (EditorRegistry) WorkbenchPlugin.getDefault().getEditorRegistry();
        IFileEditorMapping[] fileEditorMappings = editorRegistry.getFileEditorMappings();
        List<IFileEditorMapping> mappings = Stream.of(fileEditorMappings).collect(Collectors.toList());
        FileEditorMapping mapping = new FileEditorMapping("*", "log");
        mapping.setDefaultEditor(editorRegistry.findEditor("org.eclipse.ui.DefaultTextEditor"));
        mappings.add(mapping);
        Display.getDefault().asyncExec(()-> editorRegistry.setFileEditorMappings(mappings.toArray(new FileEditorMapping[] {})));
        editorRegistry.setDefaultEditor("*.txt", "org.eclipse.ui.DefaultTextEditor");
        
        final long startupDuration = System.currentTimeMillis() - BonitaStudioApplication.START_TIME;
        BonitaStudioLog.info("Startup duration : " + DateUtil.getDisplayDuration(startupDuration),
                ApplicationPlugin.PLUGIN_ID);
        ApplicationPlugin.getDefault().getPreferenceStore().setDefault(FIRST_STARTUP, true);
        if (isFirstStartup()) {
            new OpenReleaseNoteHandler().setFocus(false).asView().openBrowser();
        }
        ApplicationPlugin.getDefault().getPreferenceStore().setValue(FIRST_STARTUP, false);
    }

    private boolean isFirstStartup() {
        return ApplicationPlugin.getDefault().getPreferenceStore().getBoolean(FIRST_STARTUP);
    }

    private void preLoad() {
        //Fix performance issue
        BeanPropertyHelper.getPropertyDescriptor(ContractInputImpl.class, "name");
        preLoadSVG();
    }

    private void preLoadSVG() {
        final SVGFigure svgFigure = new SVGFigure();
        try {
            final File iconsFolder = new File(
                    FileLocator.toFileURL(Platform.getBundle("org.bonitasoft.studio.pics").getResource("icons"))
                            .getFile());
            initSVGFigure(svgFigure, iconsFolder, "figures");
            initSVGFigure(svgFigure, iconsFolder, "decoration", "svg");
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    private void initSVGFigure(final SVGFigure svgFigure, final File iconsFolder, final String... pathToFolder) {
        for (final String filename : new File(iconsFolder, Joiner.on(File.separatorChar).join(pathToFolder)).list()) {
            if (filename.endsWith(".svgz")) {
                svgFigure
                        .setURI("platform:/plugin/org.bonitasoft.studio.pics/icons/" + Joiner.on("/").join(pathToFolder)
                                + "/" + filename);
            }
        }
    }

}
