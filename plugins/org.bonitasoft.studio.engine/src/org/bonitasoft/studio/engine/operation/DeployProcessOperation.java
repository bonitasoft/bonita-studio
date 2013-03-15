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

package org.bonitasoft.studio.engine.operation;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.engine.api.ProcessManagementAPI;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.model.ProcessDefinition;
import org.bonitasoft.engine.bpm.model.ProcessDefinitionCriterion;
import org.bonitasoft.engine.bpm.model.ProcessDeploymentInfo;
import org.bonitasoft.engine.exception.DeletingEnabledProcessException;
import org.bonitasoft.engine.exception.InvalidSessionException;
import org.bonitasoft.engine.exception.PageOutOfRangeException;
import org.bonitasoft.engine.exception.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.exception.ProcessDefinitionReadException;
import org.bonitasoft.engine.exception.ProcessDeletionException;
import org.bonitasoft.engine.exception.ProcessDeployException;
import org.bonitasoft.engine.exception.ProcessDisablementException;
import org.bonitasoft.engine.exception.ProcessEnablementException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.ui.handler.ConfigureHandler;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier
 */
public class DeployProcessOperation  {

	private final Set<String> undeployedParentProcess = new HashSet<String>();
	private Set<EObject> excludedObject;
	private final String deploymentMode;
	private final Map<Object, Object> options = new HashMap<Object, Object>();
	private ProcessManagementAPI processApi;
	private String configurationId;
	private final List<AbstractProcess> processes ;
	private final Map<AbstractProcess, Long> processIdsMap;

	public DeployProcessOperation() {
		processes = new ArrayList<AbstractProcess>() ;
		processIdsMap = new HashMap<AbstractProcess, Long>() ;
		deploymentMode =  BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.APLLICATION_DEPLOYMENT_MODE) ;
	}

	public void setObjectToExclude(Set<EObject> excludedObject){
		this.excludedObject = excludedObject ;
	}

	public void addProcessToDeploy(AbstractProcess process){
		Assert.isTrue(!(process instanceof MainProcess),"process can't be a MainProcess") ;
		if(!processes.contains(process)){
			processes.add(process) ;
		}
	}

	public void setConfigurationId(String configurationId){
		this.configurationId = configurationId;
	}


	public URL getUrlFor(AbstractProcess process,IProgressMonitor monitor) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException{
		long pId = 0 ;
		for(AbstractProcess p : processIdsMap.keySet()){
			if(p.getName().equals(process.getName()) && p.getVersion().equals(process.getVersion())){
				pId =processIdsMap.get(p);
			}
		}
		return new ApplicationURLBuilder(process,pId,configurationId).toURL(monitor);
	}


	public IStatus run(IProgressMonitor monitor)  {
		assert(!processes.isEmpty());

		Configuration configuration = BarExporter.getInstance().getConfiguration(processes.get(0), configurationId);
		APISession session;
		try {
			session = BOSEngineManager.getInstance().loginTenant(configuration.getUsername(), configuration.getPassword(), monitor);
		} catch (Exception e1) {
			return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,Messages.bind(Messages.loginFailed,configuration.getUsername()),e1);
		} 
		if(session == null){
			return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,Messages.bind(Messages.loginFailed,configuration.getUsername()));
		}
		processApi = BOSEngineManager.getInstance().getProcessAPI(session);
		try {
			undeploy(monitor);
		} catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}
		try{
			IStatus status = deploy(monitor);
			if(status.getSeverity() == IStatus.OK){
				status = enable(monitor) ;
			}
			return status ;
		} catch (Exception e) {
			BonitaStudioLog.error(e) ;
			return new Status(Status.ERROR,EnginePlugin.PLUGIN_ID,Messages.deploymentFailedMessage,e) ;
		} finally{
			if(session != null){
				BOSEngineManager.getInstance().logoutDefaultTenant(session) ;
			}
		}
	}


	private IStatus enable(IProgressMonitor monitor) {
		IStatus status = Status.CANCEL_STATUS ;
		for(Entry<AbstractProcess, Long> entry : processIdsMap.entrySet()){
			AbstractProcess process = entry.getKey() ;
			monitor.subTask(Messages.bind(Messages.enablingProcess,getProcessLabel(process))) ;
			try {
				status =  enableProcess(process,monitor) ;
				if(!status.isOK()){
					return status;
				}
			} catch (Exception e) {
				return new Status(IStatus.ERROR,EnginePlugin.PLUGIN_ID,e.getMessage(),e) ;
			}

		}
		return status ;
	}

	protected IStatus deploy(IProgressMonitor monitor) {
		try{
			IStatus status = Status.OK_STATUS ;
			for(AbstractProcess process : processes){
				status =  deployProcess(process,monitor) ;
				if(status.getSeverity() != IStatus.OK){
					return status ;
				}
			}
		}catch (Exception e) {
			BonitaStudioLog.error(e) ;
			if(e.getMessage() != null) {
				return new Status(Status.ERROR,EnginePlugin.PLUGIN_ID,e.getMessage(), e);
			} else {
				return new Status(Status.ERROR,EnginePlugin.PLUGIN_ID,Messages.deploymentFailedMessage, e);
			}
		}

		return Status.OK_STATUS;
	}

	private IStatus deployProcess(AbstractProcess process ,IProgressMonitor monitor) throws Exception {
		monitor.subTask(Messages.bind(Messages.deployingProcess, getProcessLabel(process)));

		BusinessArchive bar = BarExporter.getInstance().createBusinessArchive(process,configurationId,excludedObject);
		ProcessDefinition def = null ;
		try {
			def = processApi.deploy(bar) ;
			//TODO Use definition state to detect resolution issues
		}  catch (ProcessDeployException e) {
			if(process != null){
				BonitaStudioLog.log("Error when trying to deploy the process named: "+ process.getName());
				BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			}
			return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(),e);

		} catch (Exception e1){
			if(process != null){
				BonitaStudioLog.error("Error when trying to deploy the process named: "+ process.getName(),EnginePlugin.PLUGIN_ID);
			}
			BonitaStudioLog.error(e1,EnginePlugin.PLUGIN_ID);
			return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e1.getMessage(),e1);
		}
		processIdsMap.put(process,def.getId());
		return Status.OK_STATUS ;
	}

	protected IStatus enableProcess(final AbstractProcess process,IProgressMonitor monitor) throws Exception {
		try {
			processApi.enableProcess(processIdsMap.get(process)) ;
		}  catch (ProcessEnablementException e) {
			BonitaStudioLog.error(e,EnginePlugin.PLUGIN_ID);
			if(e.getMessage() != null && e.getMessage().contains("org.bonitasoft.engine.xml.SValidationException")){
				return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(),e);
			}
			IStatus status = openConfigurationPopup(process) ;
			if(status.isOK()){
				undeployProcess(process, monitor) ;
				status = deployProcess(process,monitor) ;
				if(status.getSeverity() != IStatus.OK){
					return status ;
				}
				return enableProcess(process ,monitor) ;
			}else{
				return Status.CANCEL_STATUS ;
			}
		}
		return Status.OK_STATUS;
	}

	/**
	 * @param monitor
	 * @throws ProcessDefinitionReadException
	 * @throws PageOutOfRangeException
	 * @throws InvalidSessionException
	 * @throws DeletingEnabledProcessException
	 * @throws ProcessDeletionException
	 * @throws ProcessDefinitionNotFoundException
	 * @throws ProcessDisablementException
	 * @throws Exception
	 */
	protected void undeploy(IProgressMonitor monitor) throws InvalidSessionException, PageOutOfRangeException, ProcessDefinitionReadException, ProcessDefinitionNotFoundException, ProcessDeletionException, DeletingEnabledProcessException {
		for(AbstractProcess process : processes){
			undeployProcess(process,monitor) ;
		}
	}



	protected void undeployProcess(AbstractProcess process, IProgressMonitor monitor) throws InvalidSessionException, ProcessDefinitionReadException, PageOutOfRangeException, ProcessDefinitionNotFoundException, ProcessDeletionException, DeletingEnabledProcessException {
		long nbDeployedProcesses = processApi.getNumberOfProcesses() ;
		if(nbDeployedProcesses > 0){
			List<ProcessDeploymentInfo> processes = processApi.getProcesses(0, (int) nbDeployedProcesses, ProcessDefinitionCriterion.DEFAULT) ;
			for(ProcessDeploymentInfo info : processes){
				if(info.getName().equals(process.getName()) && info.getVersion().equals(process.getVersion())){
					monitor.subTask(Messages.bind(Messages.undeploying,getProcessLabel(process)));
					try{
						processApi.disableProcess(info.getProcessId()) ;
					}catch (ProcessDisablementException e) {

					}
					processApi.deleteProcess(info.getProcessId()) ;
				}
			}
		}
	}

	private String getProcessLabel(AbstractProcess process) {
		return process.getName()+ " ("+process.getVersion()+")";
	}

	protected IStatus openConfigurationPopup(final AbstractProcess process) {
		ICommandService service = (ICommandService)PlatformUI.getWorkbench().getService(ICommandService.class);
		Command cmd = service.getCommand("org.bonitasoft.studio.configuration.configure") ;
		try {
			Map<String,Object> parameters = new HashMap<String, Object>() ;
			String configuration = ConfigurationPlugin.getDefault().getPreferenceStore().getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION) ;
			parameters.put("configuration", configuration) ;
			parameters.put("process", process) ;
			return (IStatus) new ConfigureHandler().execute(new ExecutionEvent(cmd,parameters,null,null)) ;
		} catch (Exception e){
			BonitaStudioLog.error(e) ;
		}
		return Status.CANCEL_STATUS ;
	}


}
