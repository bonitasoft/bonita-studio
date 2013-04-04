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

package org.bonitasoft.studio.engine;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.Date;
import java.util.NoSuchElementException;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.core.IInternalDebugCoreConstants;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.preferences.IDebugPreferenceConstants;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.server.tomcat.core.internal.ITomcatServer;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.ServerPort;

/**
 * Provides all methods to manage Tomcat server in BonitaStudio.
 * 
 * @author Romain Bioteau
 */
public class BOSWebServerManager {

	private static final String SERVER_CONFIGURATION_PROJECT = "server_configuration";
	private static final String LOGINSERVICE_PATH = "/bonita/loginservice?";
	protected static final String WEBSERVERMANAGER_EXTENSION_D = "org.bonitasoft.studio.engine.bonitaWebServerManager";
	protected static final String TOMCAT_SERVER_TYPE = "org.eclipse.jst.server.tomcat.60";
	protected static final String TOMCAT_RUNTIME_TYPE = "org.eclipse.jst.server.tomcat.runtime.60";
	protected static int WATCHDOG_PORT = 6969;
	protected static final String START_TIMEOUT = "start-timeout";
	protected static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();
	protected final String tomcatInstanceLocation = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(),"tomcat").getAbsolutePath();

	protected static final String WATCHDOG_PORT_PROPERTY = "org.bonitasoft.studio.watchdog.port";
	private static final int MIN_PORT_NUMBER = 1024;
	private static final int MAX_PORT_NUMBER = 65535;
	private static final int MAX_SERVER_START_TIME = 300000;
	private static final String TOMCAT_LOG_FILE = "tomcat.log";
	private static final int MAX_LOGGING_TRY = 5;
	private ServerSocket watchdogServer;

	private static BOSWebServerManager INSTANCE;


	private IServer tomcat;
	public synchronized static BOSWebServerManager getInstance(){
		if(INSTANCE == null){
			INSTANCE =  createInstance() ;
		}
		return INSTANCE ;
	}

	protected static BOSWebServerManager createInstance() {
		for(IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(WEBSERVERMANAGER_EXTENSION_D)){
			try {
				return (BOSWebServerManager) element.createExecutableExtension("class");
			} catch (CoreException e) {
				BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			}
		}

		return new BOSWebServerManager();
	}

	protected BOSWebServerManager(){

	}


	public void copyTomcatBundleInWorkspace(IProgressMonitor monitor) {
		File tomcatFolder = null;
		try {
			final File targetFolder = new File(tomcatInstanceLocation);
			if(!targetFolder.exists()){
				BonitaStudioLog.debug("Copying tomcat bundle in worksapce...", EnginePlugin.PLUGIN_ID);
				URL url = ProjectUtil.getConsoleLibsBundle().getResource("tomcat") ;
				tomcatFolder = new File(FileLocator.toFileURL(url).getFile());
				PlatformUtil.copyResource(targetFolder, tomcatFolder, monitor);
				BonitaStudioLog.debug("Tomcat bundle copied in worksapce.", EnginePlugin.PLUGIN_ID);
			}
		} catch (IOException e) {
			BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
		}

	}

	/**
	 * Start the Server.
	 * @param monitor
	 */
	public synchronized void startServer(IProgressMonitor monitor) {
		if(!serverIsStarted()){
			monitor.subTask(Messages.startingWebServer);
			if(BonitaStudioLog.isLoggable(IStatus.OK)){
				BonitaStudioLog.debug("Starting tomcat...", EnginePlugin.PLUGIN_ID);
			}
			if(tomcat != null){
				try {
					tomcat.delete();
				} catch (CoreException e) {
					BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
				}
			}
			configureConsolePreferences();
			updateRuntimeLocationIfNeeded();
			IRuntimeType type =  ServerCore.findRuntimeType(TOMCAT_RUNTIME_TYPE);
			try {
				final IProject confProject = createServerConfigurationProject(monitor);
				final IRuntime runtime = createServerRuntime(type);
				tomcat = createServer(monitor, confProject, runtime);
				createLaunchConfiguration(tomcat,monitor);
				startWatchdog();
				final String mode = EnginePlugin.getDefault().getPreferenceStore().getString(EnginePreferenceConstants.TOMCAT_START_MODE);
				tomcat.start(mode,monitor);
				waitServerRunning(monitor);
			} catch (CoreException e) {
				BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			}

		}
	}

	private void updateRuntimeLocationIfNeeded() {
		for(IRuntime runtime : ServerCore.getRuntimes()){
			if(runtime instanceof org.eclipse.wst.server.core.internal.Runtime && runtime.getLocation() != null && !runtime.getLocation().toFile().getAbsolutePath().equals(tomcatInstanceLocation)){
				IRuntimeWorkingCopy copy = runtime.createWorkingCopy();
				final String oldLocaiton = copy.getLocation().toFile().getAbsolutePath();
				copy.setLocation(Path.fromOSString(tomcatInstanceLocation));
				File serverXmlFile = new File(tomcatInstanceLocation,"conf"+File.separatorChar+"server.xml");
				//for Windows, we need to escape \
				FileUtil.replaceStringInFile(serverXmlFile, oldLocaiton, tomcatInstanceLocation.replace("\\", "\\\\"));
				try {
					copy.save(true, Repository.NULL_PROGRESS_MONITOR);
				} catch (CoreException e) {
					BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
				}
			}
		}
	}

	private void waitServerRunning(IProgressMonitor monitor) {
		int totalTime = 0 ;
		while (totalTime < MAX_SERVER_START_TIME && tomcat != null && tomcat.getServerState() != IServer.STATE_STARTED) {
			try {
				Thread.sleep(1000);
				totalTime = totalTime + 1000;
			} catch (InterruptedException e) {
				BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			}
		}
		if(BonitaStudioLog.isLoggable(IStatus.OK)){
			if(tomcat.getServerState() == IServer.STATE_STARTED){
				BonitaStudioLog.debug("Tomcat server started.", EnginePlugin.PLUGIN_ID);
			}else{
				BonitaStudioLog.debug("Tomcat failed to start.", EnginePlugin.PLUGIN_ID);	
			}
		}

		int loginTry = 0;
		while (loginTry < MAX_LOGGING_TRY) {
			APISession session = null;
			try {
				session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
			} catch (Exception e) {

			}finally{
				if(session != null){
					BOSEngineManager.getInstance().logoutDefaultTenant(session);
					return ;
				}
				loginTry++;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
				}
			}
		}	
		BonitaStudioLog.error("Failed to login to engine after "+MAX_LOGGING_TRY+" tries", EnginePlugin.PLUGIN_ID);
	}

	protected void createLaunchConfiguration(IServer server,IProgressMonitor monitor) throws CoreException {
		ILaunchConfiguration conf = server.getLaunchConfiguration(false,Repository.NULL_PROGRESS_MONITOR);
		if(conf == null){
			conf = server.getLaunchConfiguration(true,Repository.NULL_PROGRESS_MONITOR);
		}
		ILaunchConfigurationWorkingCopy workingCopy = conf.getWorkingCopy();
		String args = workingCopy.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,"");
		if(!args.contains(tomcatInstanceLocation)){
			conf = server.getLaunchConfiguration(true,Repository.NULL_PROGRESS_MONITOR);
			workingCopy = conf.getWorkingCopy();
		}
		workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,getVMArgs());
		workingCopy.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_FILE, getTomcatLogFile());
		workingCopy.setAttribute(IDebugUIConstants.ATTR_APPEND_TO_FILE,true);
		workingCopy.doSave();
	}

	protected String getTomcatLogFile() {
		File parentDir = Platform.getLogFileLocation().toFile().getParentFile();
		return new File(parentDir,TOMCAT_LOG_FILE).getAbsolutePath();
	}

	protected void configureConsolePreferences() {
		DebugUIPlugin.getDefault().getPreferenceStore().setValue(IDebugPreferenceConstants.CONSOLE_OPEN_ON_OUT,false);
		DebugUIPlugin.getDefault().getPreferenceStore().setValue(IDebugPreferenceConstants.CONSOLE_OPEN_ON_ERR,false);
		DebugPlugin.getDefault().getPluginPreferences().setValue(IInternalDebugCoreConstants.PREF_ENABLE_STATUS_HANDLERS, false);
	}

	protected IServer createServer(IProgressMonitor monitor, final IProject confProject, IRuntime runtime) throws CoreException {
		IServerType sType = ServerCore.findServerType(TOMCAT_SERVER_TYPE);
		IFile file = confProject.getFile("bonitaTomcatServerSerialization");
		confProject.open(Repository.NULL_PROGRESS_MONITOR);
		final IFolder configurationFolder = confProject.getFolder("tomcat_conf");
		final File sourceFolder = new File(tomcatInstanceLocation,"conf");

		PlatformUtil.copyResource(configurationFolder.getLocation().toFile(), sourceFolder, Repository.NULL_PROGRESS_MONITOR);
		configurationFolder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
		IServerWorkingCopy server = configureServer(runtime, sType, file, configurationFolder);


		ServerPort tomcatPort = null;
		ServerPort adminPortServer = null ;
		ServerPort ajpConnectorPort = null ;
		for(ServerPort p : server.getServerPorts(Repository.NULL_PROGRESS_MONITOR)){
			if ("server".equals(p.getId())){
				adminPortServer = p;
			}
			if("0".equals(p.getId())){
				tomcatPort = p;

			}
			if ("1".equals(p.getId())){
				ajpConnectorPort = p;
			}
		}
		int port = tomcatPort.getPort();
		int serverPortNumber = adminPortServer.getPort();
		int ajpConnectorPortNumber = ajpConnectorPort.getPort();
		if(!isPortAvailable(port)) {
			updatePortConfiguration(serverPortNumber, serverPortNumber+1);
			serverPortNumber++;
			updatePortConfiguration(ajpConnectorPortNumber,ajpConnectorPortNumber+1);
			ajpConnectorPortNumber++;
			server = updatePort(port,server,runtime,sType,file,configurationFolder);
		}
		if(!isPortAvailable(serverPortNumber)) {
			server=updatePort(serverPortNumber,server,runtime,sType,file,configurationFolder);
		}

		if(!isPortAvailable(ajpConnectorPortNumber)) {
			server=updatePort(ajpConnectorPortNumber,server,runtime,sType,file,configurationFolder);
		}
		int tomcatPortNumber=getTomcatPort(server);
		if (tomcatPortNumber!=-1){
			BonitaHomeUtil.configureBonitaClient(BonitaHomeUtil.HTTP,"localhost",tomcatPortNumber);
			BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_PORT, tomcatPortNumber);
		} else {
			BonitaHomeUtil.configureBonitaClient(BonitaHomeUtil.HTTP,"localhost",port);
			BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_PORT, port);
		}
		return server.save(true, null);
	}


	private int getTomcatPort(IServerWorkingCopy server){
		for(ServerPort p : server.getServerPorts(Repository.NULL_PROGRESS_MONITOR)){
			if("0".equals(p.getId())){
				return p.getPort();
			}
		}
		return -1;
	}

	private IServerWorkingCopy updatePort(int port, IServerWorkingCopy server,IRuntime runtime, IServerType sType, IFile file, IFolder configurationFolder) throws CoreException{
		int oldPort = port;
		port  = getNextAvailable(oldPort);
		updatePortConfiguration(oldPort, port);
		server = configureServer(runtime, sType, file, configurationFolder);
		BonitaStudioLog.debug("Port "+oldPort+" is not availble, studio will use next available port : "+port,EnginePlugin.PLUGIN_ID);
		return server;
	}

	protected IServerWorkingCopy configureServer(IRuntime runtime, IServerType sType, IFile file, final IFolder configurationFolder) throws CoreException {
		IServerWorkingCopy server = sType.createServer("bonitaTomcatServer", file, runtime, null);
		server.setServerConfiguration(configurationFolder);
		server.setAttribute(ITomcatServer.PROPERTY_INSTANCE_DIR,tomcatInstanceLocation);
		server.setAttribute(ITomcatServer.PROPERTY_DEPLOY_DIR, tomcatInstanceLocation + File.separatorChar+ "webapps");
		server.setAttribute(START_TIMEOUT, 300);
		return server;
	}



	protected IRuntime createServerRuntime(IRuntimeType type) throws CoreException {
		IRuntimeWorkingCopy tomcatRuntime = type.createRuntime("bonitaTomcatRuntime",null);
		tomcatRuntime.setLocation(Path.fromOSString(tomcatInstanceLocation));
		IStatus status = tomcatRuntime.validate(null);
		if(!status.isOK()){
			throw new RuntimeException("Failed to create a tomcat server : "+status.getMessage());
		}
		IRuntime runtime =  tomcatRuntime.save(true, null);
		return runtime;
	}

	protected IProject createServerConfigurationProject(IProgressMonitor monitor) throws CoreException {
		final IProject confProject = ResourcesPlugin.getWorkspace().getRoot().getProject(SERVER_CONFIGURATION_PROJECT);
		if(!confProject.exists()){
			confProject.create(Repository.NULL_PROGRESS_MONITOR);
		}
		return confProject;
	}

	protected String getVMArgs() {
		StringBuilder args = new StringBuilder();
		args.append("-Xms128m");
		args.append(" ");
		args.append("-Xmx512m");
		args.append(" ");
		args.append("-XX:MaxPermSize=256m");
		if("true".equals(System.getProperty("bonita.tomcat.debug.mode"))){
			args.append("-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8500");
		}
		addSystemProperty(args, "catalina.home", "\""+tomcatInstanceLocation+"\"");
		addSystemProperty(args, "wtp.deploy", "\""+tomcatInstanceLocation+File.separatorChar+"webapps\"");
		addSystemProperty(args, "java.endorsed.dirs", "\""+tomcatInstanceLocation+File.separatorChar+"endorsed\"");
		addSystemProperty(args, "bonita.home", "\""+tomcatInstanceLocation+File.separatorChar+"bonita\"");
		addSystemProperty(args, "java.util.logging.manager", "org.apache.juli.ClassLoaderLogManager");
		addSystemProperty(args, "java.util.logging.config.file", "\""+tomcatInstanceLocation+File.separatorChar+"conf"+File.separatorChar+"logging.properties\"");
		addSystemProperty(args, "file.encoding", "UTF-8");
		if(!isPortAvailable(WATCHDOG_PORT)){    
			int oldPort = WATCHDOG_PORT;
			WATCHDOG_PORT = getNextAvailable(WATCHDOG_PORT);
			BonitaStudioLog.debug("Port "+oldPort+" is not availble for server watchdog, studio will use next available port : "+WATCHDOG_PORT,EnginePlugin.PLUGIN_ID);
		}
		addSystemProperty(args, WATCHDOG_PORT_PROPERTY,String.valueOf(WATCHDOG_PORT));
		addSystemProperty(args, "eclipse.product", Platform.getProduct().getApplication());
		return args.toString();
	}

	protected void addSystemProperty(StringBuilder sBuilder,String key,String value) {
		sBuilder.append(" ");
		sBuilder.append("-D"+key+"="+value);
	}


	protected void startWatchdog() {
		if(watchdogServer == null){
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						if(BonitaStudioLog.isLoggable(IStatus.OK)){
							BonitaStudioLog.debug("Starting studio watchdog", EnginePlugin.PLUGIN_ID);
						}
						watchdogServer = new ServerSocket(WATCHDOG_PORT);
						while (watchdogServer != null) {
							final Socket connection = watchdogServer.accept();
							connection.close();
						}
						if(BonitaStudioLog.isLoggable(IStatus.OK)){
							BonitaStudioLog.debug("Studio watchdog shutdown", EnginePlugin.PLUGIN_ID);
						}
					}catch (SocketException e1) {

					} catch (IOException e) {
						BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
					}

				}
			}).start();
		}
	}

	protected void stopWatchdog() {
		if(watchdogServer != null){
			try {
				if(BonitaStudioLog.isLoggable(IStatus.OK)){
					BonitaStudioLog.debug("Shuttingdown watchdog...", EnginePlugin.PLUGIN_ID);
				}
				watchdogServer.close();
				watchdogServer = null;
				if(BonitaStudioLog.isLoggable(IStatus.OK)){
					BonitaStudioLog.debug("Watchdog shutdown ...", EnginePlugin.PLUGIN_ID);
				}
			} catch (IOException e) {
				BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			}
		}
	}

	private void waitServerStopped(IProgressMonitor monitor) {
		while (tomcat != null && tomcat.getServerState() != IServer.STATE_STOPPED) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			}
		}
	}

	public boolean serverIsStarted() {
		return tomcat != null && tomcat.getServerState() == IServer.STATE_STARTED ;
	}


	public void resetServer(IProgressMonitor monitor) {
		stopServer(monitor);
		startServer(monitor);
	}

	public synchronized void stopServer(IProgressMonitor monitor) {
		if(serverIsStarted()){
			monitor.subTask(Messages.stoppingWebServer);
			if(BonitaStudioLog.isLoggable(IStatus.OK)){
				BonitaStudioLog.debug("Stopping tomcat server...", EnginePlugin.PLUGIN_ID);
			}
			stopWatchdog();
			tomcat.stop(true);
			waitServerStopped(monitor);
			try {
				tomcat.delete();
			} catch (CoreException e) {
				BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			}
			if(BonitaStudioLog.isLoggable(IStatus.OK)){
				BonitaStudioLog.debug("Tomcat server stopped", EnginePlugin.PLUGIN_ID);
			}
		}
	}

	public String generateLoginURL(String username, String password) {
		final IPreferenceStore store =  BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore() ;
		String locale = store.getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE) ;
		String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
		String host = store.getString(BonitaPreferenceConstants.CONSOLE_HOST) ;
		return "http://"+host+":"+ port+ LOGINSERVICE_PATH + "username="+username+"&password="+password+"&locale="+locale;
	}


	public void cleanTomCatServer(){
		String urlServer = tomcatInstanceLocation+File.separator+"bonita"+File.separator+"server"+File.separator+"tenants"+File.separator+"1";
		String urlClient = tomcatInstanceLocation+File.separator+"bonita"+File.separator+"client"+File.separator+"tenants"+File.separator+"1";
		File bonitaServerFile = new File(urlServer);
		File bonitaClientFile = new File(urlClient);
		//FileUtil.deleteDir(bonitaFile);
		PlatformUtil.delete(bonitaServerFile,null);
		PlatformUtil.delete(bonitaClientFile,null);
		String platformTomcatConfigURL = tomcatInstanceLocation+File.separator+"bonita"+File.separator+"client"+File.separator+"platform"+File.separator+"conf"+File.separator+"platform-tenant-config.properties";
		File platformTomcatConfig = new File(platformTomcatConfigURL);
		PlatformUtil.delete(platformTomcatConfig, null);
		try {
			FileUtil.copyFile(BonitaHomeUtil.getDefaultPlatformTenantConfigFile(), platformTomcatConfig);
		} catch (IOException e) {
			BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
		}
		String workURL = tomcatInstanceLocation+File.separator+"bonita"+File.separator+"server"+File.separator+"platform"+File.separator+"work";
		File workDir = new File(workURL);
		if (workDir!=null && workDir.exists()){
			for (File file:workDir.listFiles()){
				PlatformUtil.delete(file, null);
			}
		}
	}


	public File getBonitaLogFile() {
		File logDir = new File(tomcatInstanceLocation,"logs");
		File[] list = logDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File file, String fileName) {
				return fileName.contains("bonita") ;
			}
		});
		Date fileDate = new Date(0);
		File lastLogFile = null;
		for(File f : list){
			Date d = new Date(f.lastModified());
			if(d.after(fileDate)){
				fileDate = d ;
				lastLogFile = f ;
			}
		}
		return lastLogFile;
	}

	public static boolean isPortAvailable(int port) {
		if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
			throw new IllegalArgumentException("Invalid start port: " + port);
		}

		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}

		return false;
	}

	public static int getNextAvailable(int fromPort){
		if ((fromPort < MIN_PORT_NUMBER) || (fromPort > MAX_PORT_NUMBER))
		{
			throw new IllegalArgumentException("Invalid start port: "
					+ fromPort);
		}

		for (int i = fromPort; i <= MAX_PORT_NUMBER; i++)
		{
			if( isPortAvailable( i ) )
			{
				return i;
			}
		}

		throw new NoSuchElementException("Could not find an available port " + "above " + fromPort);
	}

	public void updatePortConfiguration(Integer oldPort, Integer newPort) throws CoreException {
		final File confFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString(), "tomcat"+File.separatorChar+"conf"+File.separatorChar+"server.xml") ;
		final String oldPortString =  "port=\""+oldPort+"\"" ;
		final String newPortString =  "port=\""+newPort+"\"" ;
		FileUtil.replaceStringInFile(confFile, oldPortString, newPortString) ;

		final IProject confProject = ResourcesPlugin.getWorkspace().getRoot().getProject(SERVER_CONFIGURATION_PROJECT);
		final IFolder configurationFolder = confProject.getFolder("tomcat_conf");
		try {
			FileUtil.copy(confFile, configurationFolder.getFile("server.xml").getLocation().toFile());
		} catch (IOException e) {
			BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
		}
		configurationFolder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
	}

}
