///**
// * Copyright (C) 2009-2011 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// * 
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//
//package org.bonitasoft.studio.application.actions;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.security.auth.callback.CallbackHandler;
//import javax.security.auth.login.LoginContext;
//import javax.security.auth.login.LoginException;
//
//import org.bonitasoft.console.security.server.api.ICredentialsEncryptionAPI;
//import org.bonitasoft.engine.bpm.bar.BusinessArchive;
//import org.bonitasoft.engine.bpm.model.DesignProcessDefinition;
//import org.bonitasoft.engine.bpm.model.ProcessDefinition;
//import org.bonitasoft.engine.bpm.model.ProcessDefinitionCriterion;
//import org.bonitasoft.engine.bpm.model.ProcessDeploymentInfo;
//import org.bonitasoft.studio.application.Activator;
//import org.bonitasoft.studio.application.i18n.Messages;
//import org.bonitasoft.studio.common.ProjectUtil;
//import org.bonitasoft.studio.common.emf.tools.ModelHelper;
//import org.bonitasoft.studio.common.extension.BARResourcesProvider;
//import org.bonitasoft.studio.common.log.BonitaStudioLog;
//import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
//import org.bonitasoft.studio.console.commands.ConsoleManagement;
//import org.bonitasoft.studio.engine.BOSEngineManager;
//import org.bonitasoft.studio.engine.BonitaEnvironmentInit;
//import org.bonitasoft.studio.exporter.ExporterService;
//import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
//import org.bonitasoft.studio.exporter.application.ResourcesExporter;
//import org.bonitasoft.studio.exporter.application.service.IWarFactory;
//import org.bonitasoft.studio.exporter.application.service.IWarFactory.ExportMode;
//import org.bonitasoft.studio.model.process.AbstractProcess;
//import org.bonitasoft.studio.model.process.Element;
//import org.bonitasoft.studio.model.process.MainProcess;
//import org.bonitasoft.studio.model.process.Pool;
//import org.bonitasoft.studio.model.process.SubProcessEvent;
//import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
//import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
//import org.bonitasoft.studio.remotedeploy.RemoteDeployManager;
//import org.bonitasoft.studio.repository.ProcessRepository;
//import org.eclipse.core.commands.AbstractHandler;
//import org.eclipse.core.commands.ExecutionEvent;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.NullProgressMonitor;
//import org.eclipse.core.runtime.Status;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.jface.preference.IPreferenceStore;
//
///**
// * @author Romain Bioteau
// * @author Aurelien Pupier
// */
//public class DeployProcessCommand extends AbstractHandler {
//
//	private AbstractProcess process;
//	private IProgressMonitor monitor ;
//	private Set<String> undeployedParentProcess = new HashSet<String>();
//	private Set<EObject> excludedObject;
//	private String deploymentMode;
//	private DesignProcessDefinition processDefinition;
//
//
//	public DeployProcessCommand() {
//		super();
//	}
//
//	public DeployProcessCommand(AbstractProcess process,Set<EObject> excludedObject,IProgressMonitor monitor) {
//		setProcess(process);
//		this.monitor = monitor ;
//		this.excludedObject = excludedObject ;
//		this.deploymentMode =  BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.APLLICATION_DEPLOYMENT_MODE) ;
//	}
//
//	// Custo for contexts
//	private Map<String, byte[]> additionalResources;
//	private Map<Object, Object> options = new HashMap<Object, Object>();
//
//	/*public void setSkipBARResourceProviders(boolean skip) {
//		this.skipBARResourceProviders = skip;
//	}*/
//
//	public void setAdditionalBARResources(Map<String, byte[]> resources) {
//		this.additionalResources = resources;
//	}
//
//	/**
//	 * @return the path
//	 */
//	public String getPath() {
//
//		IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
//		String userName = store.getString(BonitaPreferenceConstants.USER_NAME);
//		String password = store.getString(BonitaPreferenceConstants.USER_PASSWORD);
//		ICredentialsEncryptionAPI crendential = org.bonitasoft.console.security.server.api.impl.CredentialsEncryptionAPIImpl.getInstance();
//		String token = null;
//		try {
//			String cred = crendential.encryptCredential(userName+ICredentialsEncryptionAPI.USER_CREDENTIALS_SEPARATOR+password);
//
//			//			CallbackHandler handler = new SimpleCallbackHandler(userName, password);
//			//			LoginContext loginContext = new LoginContext("BonitaStore", handler); //$NON-NLS-1$
//			//			loginContext.login();
//			token = crendential.generateTemporaryToken(cred);
//			//			loginContext.logout();
//
//		} catch (Exception e) {
//			BonitaStudioLog.log(e);
//		} 
//		return  OpenBrowserCommand.APPLI_PATH+"identityKey="+token+"&process="+processDefinition.getId();//$NON-NLS-1$ //$NON-NLS-2$
//	}
//
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//
//		ClassLoader ori  = Thread.currentThread().getContextClassLoader() ;
//
//		try {
//			try{
//				undeploy();
//			}catch (Exception e) {
//				if(!(RemoteDeployManager.isDeploymentRemote() && !RemoteDeployManager.useEJB())){
//					throw e ; 
//				}
//			}
//			deploy();
//			monitor.worked(1);
//
//		} catch (LoginException ex) {
//			return new Status(Status.ERROR,Activator.PLUGIN_ID,Messages.wrongCredentialsMessages,ex);
//		} catch (Exception ex) {
//			return new Status(Status.ERROR,Activator.PLUGIN_ID,Messages.deploymentFailedMessage,ex);
//		}finally{
//			Thread.currentThread().setContextClassLoader(ori);
//		}
//
//		return new Status(Status.OK,Activator.PLUGIN_ID,Messages.endOfDeploySuccessful);
//	}
//
//	private void undeployParentProcess(AbstractProcess process) {
//		//		if (process == null) {
//		//			return;
//		//		}
//		//		QueryDefinitionAPI queryDefinitionAPI = AccessorUtil.getQueryDefinitionAPI();
//		//		try {
//		//			Set<ProcessDefinition> processes = queryDefinitionAPI.getProcesses();
//		//			/*For each deployed process*/
//		//			for(ProcessDefinition pDef: processes){
//		//				/*For each subprocess of the deployed process*/
//		//				for(String subDefName : pDef.getSubProcesses()){
//		//					/*Find the corresponding ProcessArtifact*/
//		//					AbstractProcess modelSubProcess = ModelHelper.findProcess(ProcessRepository.getInstance().getAllProcesses(), subDefName);
//		//					/*And if we find one and its name is not the same as the first*/
//		//					String processName = process.getName();
//		//					if(modelSubProcess != null){
//		//						String subProcessName = modelSubProcess.getName();
//		//						String parentProcessName = pDef.getName();
//		//						if(subProcessName.equals(processName)
//		//								&& ! undeployedParentProcess.contains(parentProcessName)){
//		//							/*Undeploy also its parent processes keeping trace of it to not try to re-undeployparent of this process*/
//		//							undeployedParentProcess.add(parentProcessName);
//		//							undeployParentProcess(ModelHelper.findProcess(ProcessRepository.getInstance().getAllProcesses(), parentProcessName));
//		//						}
//		//					}
//		//				}
//		//				/*Do quite the same for SubProcessEVent*/
//		//				/*For each SubProcessEvent of the deployed process*/
//		//				for(EventProcessDefinition subProcEventDef : pDef.getEventSubProcesses()){
//		//					/*Find the corresponding Process in the model*/
//		//					AbstractProcess modelSubProcess = ModelHelper.findProcess(ModelHelper.getMainProcess(process), subProcEventDef.getName());//ModelHelper.findProcess(ProcessRepository.getInstance().getAllProcesses(), subProcEventDef.get);
//		//					/*And if we find one and its name is not the same as the first*/
//		//					String processName = process.getName();
//		//					if(modelSubProcess != null){
//		//						String subProcessName = modelSubProcess.getName();
//		//						String parentProcessName = pDef.getName();
//		//						if(subProcessName.equals(processName)
//		//								&& ! undeployedParentProcess.contains(parentProcessName)){
//		//							/*Undeploy also its parent processes keeping trace of it to not try to re-undeployparent of this process*/
//		//							undeployedParentProcess.add(parentProcessName);
//		//							//NOTA: can keep call to ProcessRepository because a SubProcessEventPool can't contain a SubProcessEvent
//		//							undeployParentProcess(ModelHelper.findProcess(ProcessRepository.getInstance().getAllProcesses(), parentProcessName));
//		//						}
//		//					}
//		//				}
//		//			}
//		//			undeployProcess(process);
//		//		} catch (Exception e) {
//		//			BonitaStudioLog.log(e);
//		//		}
//	}
//
//	/**
//	 * @throws Exception
//	 * @throws LoginException
//	 */
//	public IStatus deploy() throws Exception, LoginException {
//		BOSEngineManager.getInstance().start();
//		monitor.setTaskName(Messages.generatingWarFor + " " + process.getLabel()+"..."); //$NON-NLS-1$ //$NON-NLS-2$
//		if(RemoteDeployManager.useEJB()){
//			Thread.currentThread().setContextClassLoader(RemoteDeployManager.createClassLoader());
//		}
//
//
//		/***
//		 * loads bonita API
//		 */
//
//		try{
//			engineDeploy(process);
//			if(deploymentMode.equals(BonitaPreferenceConstants.WAR_GENERATION)){
//				File myWebapps = ProjectUtil.getBonitaWebappeProject() ;
//				IWarFactory warFactoryService = (IWarFactory) ExporterService.getInstance().getExporterService(SERVICE_TYPE.WarFactory);
//				File warFile = warFactoryService.generateProcessApplicationWar(process, ExportMode.STUDIO, myWebapps, monitor, processDefinition);
//				if(warFile == null || monitor.isCanceled()){
//					return null;
//				}
//				ConsoleManagement.deployWar(monitor, warFile.getName());
//				if(monitor.isCanceled()){
//					return null;
//				}
//			}
//		}catch (Exception e) {
//			if(e.getMessage() != null)
//				return new Status(Status.ERROR,Activator.PLUGIN_ID,e.getMessage(), e);
//			else
//				return new Status(Status.ERROR,Activator.PLUGIN_ID,Messages.deploymentFailedMessage, e);
//		}
//
//
//		return Status.OK_STATUS;
//	}
//
//	/**
//	 * @throws Exception
//	 */
//	public void undeploy() throws Exception {
//		if(RemoteDeployManager.useEJB()){
//			Thread.currentThread().setContextClassLoader(RemoteDeployManager.createClassLoader());
//		}
//		undeployProcess(process) ;
//		//undeployParentProcess(process);
//
//	}
//
//
//	private ProcessDefinition engineDeploy(AbstractProcess process) throws Exception{
//
//		monitor.setTaskName(Messages.deployingProcess + " "+process.getLabel()+"...");  //$NON-NLS-1$//$NON-NLS-2$
//
//		ExportAsBarFileCommand exportAsBarFileCommand = new ExportAsBarFileCommand();
//		if(!deploymentMode.equals(BonitaPreferenceConstants.ALL_IN_BAR)){
//			if(additionalResources == null){
//				additionalResources = new HashMap<String, byte[]>();
//			}
//			File tmp = new File(ProjectUtil.getBonitaStudioWorkFolder(),"tmpVal");
//			tmp.mkdir();
//			ResourcesExporter.exportValidators(process, tmp, monitor);
//			for(File  f : tmp.listFiles()){
//				byte[] jarBytes = new byte[(int) f.length()];
//				InputStream stream = new FileInputStream(f);
//				stream.read(jarBytes);
//				stream.close();
//				//need to have / and not path separator
//				additionalResources.put("forms/validators/"+f.getName(),jarBytes) ;
//			}
//			PlatformUtil.delete(tmp,new NullProgressMonitor());
//			tmp = new File(ProjectUtil.getBonitaStudioWorkFolder(),"tmpLib");
//			tmp.mkdir();
//			ResourcesExporter.exportJars(process, tmp, monitor);
//			for(File  f : tmp.listFiles()){
//				byte[] jarBytes = new byte[(int) f.length()];
//				InputStream stream = new FileInputStream(f);
//				stream.read(jarBytes);
//				stream.close();
//				//need to have / and not path separator
//				additionalResources.put("forms/lib/"+f.getName(),jarBytes) ;
//			}
//			PlatformUtil.delete(tmp,new NullProgressMonitor());
//		}
//
//
//		BusinessArchive bar = exportAsBarFileCommand.createBusinessArchive((AbstractProcess)process,deploymentMode.equals(BonitaPreferenceConstants.ALL_IN_BAR), options, additionalResources,excludedObject);
//		processDefinition = bar.getProcessDefinition();
//
//		return BOSEngineManager.getInstance().getProcessAPI().deploy(bar) ;
//
//	}
//
//	private void undeployProcess(AbstractProcess proc) throws Exception {
//		monitor.setTaskName(Messages.undeploying+" "+proc.getLabel()+"..."); //$NON-NLS-1$ //$NON-NLS-2$
//		//		CommandAPI commandAPI = AccessorUtil.getCommandAPI();
//		if (proc instanceof Pool
//				|| proc instanceof SubProcessEvent) {
//			List<ProcessDeploymentInfo> processes = BOSEngineManager.getInstance().getProcessAPI().getProcesses(0, (int) BOSEngineManager.getInstance().getProcessAPI().getNumberOfProcesses(), ProcessDefinitionCriterion.DEFAULT) ;
//			for(ProcessDeploymentInfo info : processes){
//				if(info.getName().equals(proc.getName()) && info.getVersion().equals(proc.getVersion())){
//					BOSEngineManager.getInstance().getProcessAPI().deleteProcess(info.getId()) ;
//				}
//			}
//			
//			//			try {
//			//				ProcessDefinitionUUID uuid =	new ProcessDefinitionUUID(proc.getName(), proc.getVersion());
//			//				boolean alreadyDeployed = false ;
//			//				for(LightProcessDefinition pi : AccessorUtil.getQueryDefinitionAPI().getLightProcesses()){
//			//					if(pi.getUUID().equals(uuid)){
//			//						alreadyDeployed = true ;
//			//						break;
//			//					}
//			//				}
//			//				commandAPI.execute(new WebDeleteDocumentsOfProcessCommand(uuid,true)) ; 
//			//				if(alreadyDeployed){
//			//					commandAPI.execute(new WebDeleteProcessCommand(uuid));
//			//					ConsoleManagement.undeploy(uuid.toString());
//			//				}
//			//			} catch (ProcessNotFoundException ex) {
//			//				// no problem
//			//			}
//		} else if (proc instanceof MainProcess) {
//			for (Element element : proc.getElements()) {
//				if (element instanceof AbstractProcess) {
//					undeployProcess((AbstractProcess)element);
//				}
//			}
//		}
//	}
//
//
//	/**
//	 * @param process2
//	 */
//	public void setProcess(AbstractProcess process) {
//		if (process != null) {
//			this.process = process;
//		} else {
//			this.process = null;
//		}
//	}
//
//	/**
//	 * Set options for deploy. These options are propagated to all {@link BARResourcesProvider}
//	 * @param key An option key (Example: ContentResourceProvider#ENABLE)
//	 * @param value
//	 */
//	public void setOption(Object key, Object value) {
//		this.options .put(key, value);
//	}
//
//	public long getProcessID() {
//		return processDefinition.getId();
//	}
//
//}
