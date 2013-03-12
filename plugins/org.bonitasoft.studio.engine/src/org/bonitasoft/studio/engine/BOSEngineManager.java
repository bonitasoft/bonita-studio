/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.xbean.classloader.NonLockingJarFileClassLoader;
import org.bonitasoft.engine.api.CommandAPI;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.InvalidSessionException;
import org.bonitasoft.engine.exception.LoginException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IEngineAction;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author Romain Bioteau
 *
 */
public class BOSEngineManager {

	private static final String INSTALL_ORGANIZATION_CMD_ID = "org.bonitasoft.studio.engine.installOrganization";
	private static final String PRESTARTUP_CONTIBUTION_ID = "org.bonitasoft.studio.engine.preEngineAction";
	public static final String PLATFORM_PASSWORD = "platform";
	public static final String PLATFORM_USER = "platformAdmin";
	public static final String BONITA_TECHNICAL_USER = "install";
	public static final String BONITA_TECHNICAL_USER_PASSWORD = "install";

	public static final String API_TYPE_PROPERTY_NAME = "org.bonitasoft.engine.api-type";
	public static final String DEFAULT_TENANT_NAME = "default";
	public static final String DEFAULT_TENANT_DESC = "The default tenant created by the Studio";
	private static final String ENGINESERVERMANAGER_EXTENSION_D = "org.bonitasoft.studio.engine.bonitaEngineManager";
	private static BOSEngineManager INSTANCE ;
	private boolean isRunning = false;
	private IProgressMonitor monitor;
	private int retry = 0;


	protected BOSEngineManager(IProgressMonitor monitor) {
		if(monitor == null){
			this.monitor = Repository.NULL_PROGRESS_MONITOR ;
		}else{
			this.monitor = monitor ;
		}
	}

	public static BOSEngineManager getInstance(){
		return getInstance(null) ;
	}

	public static synchronized BOSEngineManager getInstance(IProgressMonitor monitor){
		if(INSTANCE == null){
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
			} catch (IOException ioe) {
				// Can't Log this. Should we send to STDOUT/STDERR?
			}
			INSTANCE = createInstance(monitor) ;
		}
		return INSTANCE ;
	}

	protected static BOSEngineManager createInstance(IProgressMonitor monitor) {
		for(IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(ENGINESERVERMANAGER_EXTENSION_D)){
			try {
				return (BOSEngineManager) element.createExecutableExtension("class");
			} catch (CoreException e) {
				BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			}
		}

		return new BOSEngineManager(monitor);
	}


	public synchronized void start(){
		if(!isRunning()){
			monitor.beginTask(Messages.initializingProcessEngine, IProgressMonitor.UNKNOWN) ;
			initBonitaHome() ;
			BOSWebServerManager.getInstance().startServer(monitor) ;
			synchronizeDefaultOrganization();
			monitor.done() ;
		}
	}



	public synchronized void stop(){
		BOSWebServerManager.getInstance().stopServer(monitor);
		if(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT)){
			BOSWebServerManager.getInstance().cleanTomCatServer();
		}
		isRunning = false ;
	}

	protected void executePreStartupContributions() {
		IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(PRESTARTUP_CONTIBUTION_ID);
		IEngineAction contrib = null;
		for (IConfigurationElement elem : elements){
			try {
				contrib = (IEngineAction) elem.createExecutableExtension("class"); //$NON-NLS-1$
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}
			contrib.run();
		}

	}

	protected void synchronizeDefaultOrganization()  {
		isRunning = true ;
		loadOrganization();
	}


	protected void recoverFromCrash() {
		if(retry == 0){
			if(!FileActionDialog.getDisablePopup()){
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.crashRecoveryTitle,Messages.crashRecoveryMsg);
					}
				});
			}
			retry ++ ;
			BonitaStudioLog.log("Clean platform and retry") ;
			PlatformUtil.delete(BonitaHomeUtil.getBonitaHome(), monitor);
			initBonitaHome();
			synchronizeDefaultOrganization();
		}
	}

	private void initBonitaHome() {
		BonitaHomeUtil.initBonitaHome() ;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public ProcessAPI getProcessAPI(APISession session) {
		try {
			return	TenantAPIAccessor.getProcessAPI(session);
		} catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}
		return null ;
	}

	protected LoginAPI getLoginAPI() throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
		return TenantAPIAccessor.getLoginAPI();
	}

	public APISession loginDefaultTenant(IProgressMonitor monitor) throws LoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
		return loginTenant(BONITA_TECHNICAL_USER, BONITA_TECHNICAL_USER_PASSWORD, monitor);
	}

	public APISession loginTenant(String login, String password, IProgressMonitor monitor) throws LoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
		if(!isRunning()){
			monitor.subTask(Messages.waitingForEngineToStart) ;
			start() ;
			monitor.done() ;
		}
		if(BonitaStudioLog.isLoggable(IStatus.OK)){
			BonitaStudioLog.debug("Attempt to login as "+login, EnginePlugin.PLUGIN_ID);
		}
		APISession session = getLoginAPI().login(login, password);
		if(session != null){
			if(BonitaStudioLog.isLoggable(IStatus.OK)){
				BonitaStudioLog.debug("Login successful.", EnginePlugin.PLUGIN_ID);
			}

		}
		return session;
	}

	public void logoutDefaultTenant(APISession session) {
		try{
			getLoginAPI().logout(session)  ;
		} catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}
	}

	public boolean useEJB() {
		// TODO Auto-generated method stub
		return false;
	}

	private void loadOrganization()  {
		if(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.LOAD_ORGANIZATION)) {
			ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class) ;
			Command cmd = service.getCommand(INSTALL_ORGANIZATION_CMD_ID) ;
			Map<String, String> parameters = new HashMap<String, String>() ;
			String artifactId = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.DEFAULT_ORGANIZATION) ;
			parameters.put("artifact", artifactId+".organization") ;
			ExecutionEvent ee = new ExecutionEvent(cmd,parameters,null,null);
			try {
				cmd.executeWithChecks(ee) ;
			} catch (Exception e) {
				BonitaStudioLog.error(e) ;
			}

		}
	}

	protected ClassLoader createEngineClassloader(){
		Set<URL> urls = new HashSet<URL>();
		Enumeration<URL> foundJars = ProjectUtil.getConsoleLibsBundle().findEntries("/lib","*.jar", true);
		while (foundJars.hasMoreElements()) {
			URL url = foundJars.nextElement();
			try {
				urls.add(FileLocator.toFileURL(url));
			} catch (IOException e) {
				BonitaStudioLog.error(e);
			}
		}
		foundJars = ProjectUtil.getConsoleLibsBundle().findEntries("/h2","h2-*.jar", true);
		while (foundJars.hasMoreElements()) {
			URL url = foundJars.nextElement();
			try {
				urls.add(FileLocator.toFileURL(url));
			} catch (IOException e) {
				BonitaStudioLog.error(e);
			}
		}
		return new NonLockingJarFileClassLoader("Bonita Engine CLassloader", urls.toArray(new URL[]{}));
	}

	public IdentityAPI getIdentityAPI(APISession session) throws InvalidSessionException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
		return TenantAPIAccessor.getIdentityAPI(session);
	}

	public CommandAPI getCommandAPI(APISession session) throws InvalidSessionException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
		return TenantAPIAccessor.getCommandAPI(session);
	}


}
