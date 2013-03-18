/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.application.contribution.IPreShutdownContribution;
import org.bonitasoft.studio.application.contribution.IPreStartupContribution;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.job.StartEngineJob;
import org.bonitasoft.studio.application.splash.BOSSplashHandler;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.perspectives.PerspectiveIDRegistry;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.extension.IPostInitRepositoryJobContribution;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.diagram.providers.ElementInitializers;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.splash.SplashHandlerFactory;

public class BonitaStudioWorkbenchAdvisor extends WorkbenchAdvisor {


    protected static final String PRIORITY = "priority";
    private int workload;
    private IProgressMonitor monitor;

    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new BonitaStudioWorkbenchWindowAdvisor(configurer);
    }


    @Override
    public void initialize(IWorkbenchConfigurer configurer) {
        super.initialize(configurer);
        configurer.setSaveAndRestore(false);
    }

    @Override
    public String getInitialWindowPerspectiveId() {
        return PerspectiveIDRegistry.PROCESS_PERSPECTIVE_ID;
    }


    @Override
    public String getMainPreferencePageId() {
        return "org.bonitasoft.studio.preferences.preferences.UIPreferencePage";
    }
    /* (non-Javadoc)
     * @see org.eclipse.ui.application.WorkbenchAdvisor#preStartup()
     */
    @SuppressWarnings({ "restriction"})
    @Override
    public void preStartup() {
        try{
            SplashHandlerFactory.findSplashHandlerFor(Platform.getProduct()) ;
            monitor  = BOSSplashHandler.getMonitor() ;
        }catch (Exception e) {
            BonitaStudioLog.error(e) ;
        }
        if(monitor == null){
            BonitaStudioLog.log("Progress Monitor is null") ;
            monitor = new NullProgressMonitor() ;
        }

        workload = 20 ;
        try {
            if(ResourcesPlugin.getWorkspace().getRoot().members().length == 0){
                workload = 40;
            }
        } catch (CoreException e3) {
            BonitaStudioLog.error(e3) ;
        }
        monitor.beginTask(BOSSplashHandler.BONITA_TASK, workload+30) ;
        monitor.subTask(Messages.initializingCurrentRepository);


        String current = CommonRepositoryPlugin.getDefault().getPreferenceStore().getString(RepositoryPreferenceConstant.CURRENT_REPOSITORY) ;

        IRepository repository = RepositoryManager.getInstance().getCurrentRepository() ;
        if(repository.getProject().exists() && !RepositoryPreferenceConstant.DEFAULT_REPOSITORY_NAME.equals(repository.getName())){
            if(!repository.getProject().isOpen()){
                repository.open() ;
            }
            String version =  repository.getVersion() ;
            if(!ProductVersion.sameMinorVersion(version)){
                MessageDialog.openWarning(Display.getDefault().getActiveShell(),Messages.badWorkspaceVersionTitle, Messages.bind(Messages.badWorkspaceVersionMessage,new Object[]{current,version,ProductVersion.CURRENT_VERSION})) ;
                CommonRepositoryPlugin.setCurrentRepository(RepositoryPreferenceConstant.DEFAULT_REPOSITORY_NAME) ;
            }
        }

        IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.application.prestartup"); //$NON-NLS-1$
        IPreStartupContribution preStartupcontrib = null;
        List<IConfigurationElement> sortedConfigElems = new ArrayList<IConfigurationElement>() ;
        for(IConfigurationElement elem : elems){
            sortedConfigElems.add(elem) ;
        }

        Collections.sort(sortedConfigElems, new Comparator<IConfigurationElement>() {

            @Override
            public int compare(IConfigurationElement e1, IConfigurationElement e2) {
                int	p1 = 0;
                int p2 = 0 ;
                try{
                    p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
                }catch (NumberFormatException e) {
                    p1 = 0 ;
                }
                try{
                    p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
                }catch (NumberFormatException e) {
                    p2 = 0 ;
                }
                return  p1 -p2; //Lowest Priority first
            }

        }) ;
        for (IConfigurationElement elem : sortedConfigElems){
            try {
                preStartupcontrib = (IPreStartupContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            try{
                if(preStartupcontrib.canExecute()){
                    preStartupcontrib.execute();
                    monitor.worked(1);
                }
            }catch (Exception e) {
                BonitaStudioLog.error(e) ;
                System.exit(-1) ;
            }

        }

        createServerSocket();
        ElementInitializers.getInstance().init_Pool_2007(ProcessFactory.eINSTANCE.createPool());
        BonitaPerspectivesUtils.initializePerspectives();

        IWorkspaceRunnable workspaceOperation = new IWorkspaceRunnable() {

            @Override
            public void run(final IProgressMonitor monitor) throws CoreException {
                monitor.worked(1) ;
                FileUtil.deleteDir(ProjectUtil.getBonitaStudioWorkFolder());
                monitor.worked(1) ;
                IRepository repository = RepositoryManager.getInstance().getCurrentRepository() ;
                monitor.worked(1) ;
                if(!repository.getProject().exists()){
                    repository.create() ;
                }
                repository.open() ;
                monitor.worked(5) ;
            }
        };
        try {
            workspaceOperation.run(monitor) ;
        } catch (CoreException e3) {
            BonitaStudioLog.error(e3) ;
        }
        monitor.subTask(org.bonitasoft.studio.common.repository.Messages.openingStudio) ;

        /*Look if there are contribution to launch,
         * typically add repo team listener
         * */
        IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.common.repository.postinitrepository"); //$NON-NLS-1$
        IPostInitRepositoryJobContribution contrib = null;
        for (IConfigurationElement elem : elements){
            try {
                contrib = (IPostInitRepositoryJobContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            contrib.execute();
        }
    }


    private void createServerSocket() {
        BOSWebServerManager.getInstance().copyTomcatBundleInWorkspace(monitor) ;
        BonitaHomeUtil.initBonitaHome();
        if(PlatformUtil.isHeadless()){
            return ;
        }
        StartEngineJob job = new StartEngineJob("Starting BOS Engine");
        job.setPriority(Job.LONG);
        job.setUser(false);
        job.schedule();
    }


    @Override
    public void postStartup() {
        try {
            IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.common.poststartup"); //$NON-NLS-1$
            IPostStartupContribution contrib = null;
            for (IConfigurationElement elem : elements){
                try {
                    contrib = (IPostStartupContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
                contrib.execute();
            }


            if (PlatformUI.isWorkbenchRunning()) {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                PlatformUI.getWorkbench().showPerspective(PerspectiveIDRegistry.PROCESS_PERSPECTIVE_ID, activeWorkbenchWindow);

                if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective() != null) {
                    PlatformUI.getWorkbench().getIntroManager().showIntro(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), false);
                }
            }

            long startupDuration = System.currentTimeMillis() - BonitaStudioApplication.START_TIME ;
            BonitaStudioLog.info("Startup duration : "+DateUtil.getDisplayDuration(startupDuration),ApplicationPlugin.PLUGIN_ID) ;

            if (PlatformUI.isWorkbenchRunning()) {
                sendUserInfo();
                openStartupDialog() ;
            }
        } catch (WorkbenchException ex) {
            BonitaStudioLog.error(ex);
        }
    }

    private void openStartupDialog() {
        String noRegister = System.getProperty("bonita.noregister"); //$NON-NLS-1$
        if (noRegister == null || !noRegister.equals("1")) { //$NON-NLS-1$

            IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.common.startupDialog"); //$NON-NLS-1$
            IPostStartupContribution contrib = null;
            for (IConfigurationElement elem : elements){
                try {
                    contrib = (IPostStartupContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
                contrib.execute();
            }
        }

    }


    @Override
    public boolean preShutdown() {
        boolean returnValue =  super.preShutdown();
        if(returnValue){
        	try {
        		if(PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
        			boolean closeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(true) ;
        			if(closeEditor){
        				PlatformUI.getWorkbench().getProgressService().run(true, false, new IRunnableWithProgress() {

        					@Override
        					public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
        						monitor.beginTask(Messages.shuttingDown, IProgressMonitor.UNKNOWN) ;
        						IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.application.preshutdown"); //$NON-NLS-1$
        						IPreShutdownContribution contrib = null;
        						for (IConfigurationElement elem : elements){
        							try {
        								contrib = (IPreShutdownContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
        							} catch (CoreException e) {
        								BonitaStudioLog.error(e);
        							}
        							contrib.execute();
        						}
        						if(BOSEngineManager.getInstance().isRunning()){
        							BOSEngineManager.getInstance().stop() ;
        						}
        						FileUtil.deleteDir(ProjectUtil.getBonitaStudioWorkFolder());
        						monitor.done() ;
        					}
        				}) ;
        			}
        			return closeEditor ;
        		}
        	} catch (Exception e){
                BonitaStudioLog.error(e) ;
            }
        }
        return returnValue ;
    }

    private void sendUserInfo() {
        String noRegister = System.getProperty("bonita.noregister"); //$NON-NLS-1$
        if (noRegister == null || !noRegister.equals("1")) { //$NON-NLS-1$

            IPreferenceStore prefStore = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
            int ok = prefStore.getInt(BonitaRegistration.BONITA_USER_REGISTERED);
            int nbTry = prefStore.getInt(BonitaRegistration.BONITA_USER_REGISTER_TRY);
            int infoSent = prefStore.getInt(BonitaRegistration.BONITA_INFO_SENT);
            if(nbTry <= BonitaRegistration.BONITA_USER_REGISTER_MAXTRY){
                if (ok != 1) {

                    IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.application.registration"); //$NON-NLS-1$
                    IPostStartupContribution contrib = null;
                    for (IConfigurationElement elem : elements){
                        try {
                            contrib = (IPostStartupContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
                        } catch (CoreException e) {
                            BonitaStudioLog.error(e);
                        }
                        if (contrib != null) {
                            prefStore.setValue(BonitaRegistration.BONITA_USER_REGISTER_TRY, ++nbTry);
                            contrib.execute();
                            break;
                        }
                    }

                    if(elements.length == 0){

                        prefStore.setValue(BonitaRegistration.BONITA_USER_REGISTERED, 1);
                        BonitaRegistration.sendUserInfo(prefStore, "bonita_sp");
                    }
                } else if (infoSent == 0) {
                    // registered but was offline
                    // try to send data
                    BonitaRegistration.sendUserInfo(prefStore, prefStore.getString(BonitaRegistration.BONITA_USER_MAIL));

                }
            }
        }
    }



}
