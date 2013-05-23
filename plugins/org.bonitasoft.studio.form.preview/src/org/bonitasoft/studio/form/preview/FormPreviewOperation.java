/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.form.preview;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.model.ActivationState;
import org.bonitasoft.engine.bpm.model.HumanTaskInstance;
import org.bonitasoft.engine.bpm.model.ProcessDefinition;
import org.bonitasoft.engine.bpm.model.ProcessDefinitionCriterion;
import org.bonitasoft.engine.bpm.model.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.model.ProcessInstance;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.exception.IllegalProcessStateException;
import org.bonitasoft.engine.exception.PageOutOfRangeException;
import org.bonitasoft.engine.exception.platform.InvalidSessionException;
import org.bonitasoft.engine.exception.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.exception.process.ProcessDisablementException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.command.OpenBrowserCommand;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.operation.ApplicationURLBuilder;
import org.bonitasoft.studio.form.preview.i18n.Messages;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.browser.ExternalBrowserInstance;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;

/**
 *@Author Aurélie Zara
 *
 *
 */
public class FormPreviewOperation implements IRunnableWithProgress {

	private Form form;
	private Form formCopy;
	private ApplicationLookNFeelFileStore lookNFeel;
	private IBrowserDescriptor browser;
	private boolean isOnTask = false;
	private boolean canPreview = true;
	private static String lastProcessDeployed;
	private static final String VERSION ="1.0";
	private static final int MAX_IT= 100;



	public FormPreviewOperation(Form form,ApplicationLookNFeelFileStore lookNFeel, IBrowserDescriptor browser){
		this.form = form;
		this.lookNFeel = lookNFeel;
		this.browser = browser;
		initializeForm();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
	InterruptedException {

		monitor.beginTask(Messages.formPreview, IProgressMonitor.UNKNOWN);

		APISession session = null;
		ProcessAPI processApi = null;
		long procId = -1;
		final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
		final AbstractProcess proc = createAbstractProcess(configuration);
		if (canPreview){
			configuration.setName("formPreviewConfig");

			new ConfigurationSynchronizer(proc, configuration).synchronize();

			try {

				session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
				processApi = BOSEngineManager.getInstance().getProcessAPI(session);
				Assert.isNotNull(processApi) ;

				undeployProcess(proc, processApi);

				BusinessArchive businessArchive = BarExporter.getInstance().createBusinessArchive(proc,configuration,Collections.EMPTY_SET,false);

				ProcessDefinition def = processApi.deploy(businessArchive);
				procId = def.getId();
				processApi.enableProcess(procId) ;
			

				ExternalBrowserInstance browserInstance = new ExternalBrowserInstance(null, browser);
				if (!isOnTask){
					ApplicationURLBuilder builder = new ApplicationURLBuilder(proc,procId,configuration.getName());
					URL url = builder.toURL(monitor);
					OpenBrowserCommand openCmd = new OpenBrowserCommand(url, browserInstance.getId(), "");
					if(browser.getLocation() != null){
						openCmd.setExternalBrowser(browserInstance);
					}
					openCmd.execute(null);
				} else {
					IdentityAPI identityApi = BOSEngineManager.getInstance().getIdentityAPI(session);
					long userId = identityApi.getUserByUserName(BonitaConstants.STUDIO_TECHNICAL_USER_NAME).getId();
					ProcessInstance procInstance = processApi.startProcess(procId);
					boolean isAvailable = false;
					int it = 0;
					while(!isAvailable && it<MAX_IT){
						isAvailable = !processApi.getOpenedActivityInstances(procInstance.getId(), 0, 1, null).isEmpty();
						it++;
						Thread.sleep(100);
					}
					if (it<MAX_IT && !processApi.getPendingHumanTaskInstances(userId, 0, 20, null).isEmpty() ){
						HumanTaskInstance task = processApi.getPendingHumanTaskInstances(userId,0, 20, null).get(0);
						URL taskURL = toTaskURL(configuration,proc,procId,task,monitor);
						OpenBrowserCommand openCmd = new OpenBrowserCommand(taskURL, browserInstance.getId(), "");
						if(browser.getLocation() != null){
							openCmd.setExternalBrowser(browserInstance);
						}
						openCmd.execute(null);
					} 
				}
			} catch (Exception e) {
				BonitaStudioLog.error(e);

			}finally{
				if(session != null){
					BOSEngineManager.getInstance().logoutDefaultTenant(session);
					lastProcessDeployed = proc.getName();
				}
			}
		}

	}


	public URL toTaskURL(Configuration configuration,AbstractProcess process,long procId,HumanTaskInstance task,IProgressMonitor monitor) throws UnsupportedEncodingException, MalformedURLException{
		IPreferenceStore store =  BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore() ;
		String locale = store.getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE) ;
		String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
		String host = store.getString(BonitaPreferenceConstants.CONSOLE_HOST) ;
		String token = "" ;
		String userName = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.USER_NAME) ;
		String password = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.USER_PASSWORD) ;
		if(configuration != null && configuration.getUsername() != null){
			userName = configuration.getUsername() ;
			password = configuration.getPassword() ;
		}
		final String taskName=((Task)process.getElements().get(0)).getName();
		final String loginURL = BOSWebServerManager.getInstance().generateLoginURL(userName, password) ;
		final String runUrl = "http://"+ host+":"+ port + ApplicationURLBuilder.APPLI_PATH + token +"ui=form&theme="+procId+"&locale="+locale+"#form="+URLEncoder.encode(process.getName()+"--"+process.getVersion()+"--"+taskName, "UTF-8")+"$entry&task="+task.getId()+"&mode=app";
		return new URL(loginURL+"&redirectUrl="+URLEncoder.encode(runUrl, "UTF-8"));

	}




	public AbstractProcess createAbstractProcess(Configuration configuration){
		AbstractProcess proc = ProcessFactory.eINSTANCE.createPool();
		proc.setName(form.getName()+" preview");
		proc.setVersion(VERSION);
		proc.setBasedOnLookAndFeel(lookNFeel.getName());
		//initializeLookNFeel(proc);
		Element parent =ModelHelper.getParentFlowElement(form);
		if (parent ==null){
			proc.getForm().add(formCopy);
			
		} else {
			if (parent instanceof Task){
				initializeTask(parent,proc,configuration);
			}
		}
		configuration.setUsername(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
		configuration.setPassword("bpm");
		return proc;
	}


	private void initializeTask(Element parent,AbstractProcess proc, Configuration configuration){
		AbstractProcess parentProc = ModelHelper.getParentProcess(parent);
		Task task= ProcessFactory.eINSTANCE.createTask();
		task.setName(((Task)parent).getName()+" preview");
		task.getForm().add(formCopy);
		copyActors(parentProc, proc);
		if (((Task)parent).getActor()!=null){
			addActorToTask(task,((Task)parent).getActor());
		} else {
			if(ModelHelper.getParentContainer(parent) instanceof Lane){
				Lane lane = ModelHelper.getParentLane(parent);
				Actor actorCopy=addActorToTask(task,lane.getActor());
				proc.getActors().add(actorCopy);
			} else {
				canPreview=false;
				MessageDialog.openError(Display.getCurrent().getActiveShell(), Messages.noActorDefinedTitle, Messages.bind(Messages.noActorDefined, ((Task)parent).getName()));
			}

		}
		setActorMapping(parentProc, configuration);
		proc.setByPassFormsGeneration(true);
		proc.getElements().add(task);
		isOnTask = true;
	}


	private Actor addActorToTask(Task task,Actor actor){
		Actor actorCopy = (Actor)EcoreUtil.copy(actor);
		actorCopy.setInitiator(true);
		task.setActor(actorCopy);
		return actorCopy;
	}


	private void setActorMapping(AbstractProcess proc,Configuration previewConfiguration){
		ProcessConfigurationRepositoryStore configurationStore =  (ProcessConfigurationRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
		String id = ModelHelper.getEObjectID(proc);
		ProcessConfigurationFileStore configurationFileStore = configurationStore.getChild(id+".conf");
		Configuration configuration = configurationFileStore.getContent();
		ActorMappingsType actorMapping = EcoreUtil.copy(configuration.getActorMappings());
		if (actorMapping==null){
			MessageDialog.openError(Display.getCurrent().getActiveShell(),Messages.noActorMappingDefinedTitle ,  Messages.noActorMappingDefined);
			canPreview = false;
		} else {

			for(ActorMapping mapping : actorMapping.getActorMapping()){
				mapping.getMemberships().getMembership().clear();
				mapping.getGroups().getGroup().clear();
				mapping.getUsers().getUser().clear();
				mapping.getRoles().getRole().clear();
				mapping.getUsers().getUser().add(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
			}
			previewConfiguration.setActorMappings(actorMapping);

		}

	}


	private void initializeLookNFeel(AbstractProcess proc){
		ResourceSet resourceSet = new ResourceSetImpl();
		TransactionalEditingDomain editingDomain  = GMFEditingDomainFactory.getInstance().createEditingDomain(resourceSet);
		Resource resource = resourceSet.createResource(proc.eResource().getURI());
		try {
			resource.load(resourceSet.getLoadOptions());
		} catch (IOException e1) {
			BonitaStudioLog.error(e1);
		}
		proc = (AbstractProcess) resource.getEObject(proc.eResource().getURIFragment(proc));
		CompoundCommand cc = WebTemplatesUtil.createAddTemplateCommand(editingDomain, proc, lookNFeel, new NullProgressMonitor());
		editingDomain.getCommandStack().execute(cc);
	}


	private void initializeForm(){
		formCopy = EcoreUtil.copy(form);
		initializeAllWidgets(formCopy);
		formCopy.getData().clear();
		formCopy.getKpis().clear();
		formCopy.getValidators().clear();
		formCopy.getActions().clear();
		formCopy.getConnectors().clear();

	}

	private void initializeAllWidgets(Form formCopy){
		List<Widget> widgets = ModelHelper.getAllWidgetInsideForm(formCopy);
		WidgetSwitch widgetSwitch = new WidgetSwitch();
		for (Widget widget:widgets){
			List<Expression> exprs = ModelHelper.getAllItemsOfType(widget, ExpressionPackage.Literals.EXPRESSION);
			for (Expression expr:exprs){
				expr = initializeExpression(form,expr);
			}
			if (!widget.getDependOn().isEmpty()){
				Expression exprDisplayDependentWidgetOnly = widget.getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition();
				exprDisplayDependentWidgetOnly.setContent("true");
				exprDisplayDependentWidgetOnly.setReturnType(Boolean.class.getName());
				Expression exprDisplayAfterEventDependsOnCondiditionScript = widget.getDisplayAfterEventDependsOnConditionScript();
				exprDisplayAfterEventDependsOnCondiditionScript.setContent("true");
				exprDisplayAfterEventDependsOnCondiditionScript.setReturnType(Boolean.class.getName());
			}
			widget = (Widget)widgetSwitch.doSwitch(widget);
		}
	}

	private Expression initializeExpression(Form form,Expression expr){
		if (ExpressionConstants.VARIABLE_TYPE.equals(expr.getType())){
			Data data =getReferencedData(form, expr);
			if (data !=null && data.getDefaultValue()!=null && data.getDefaultValue().getContent()!=null && !data.getDefaultValue().getContent().isEmpty()){
				if (data.getDataType() instanceof JavaType ) {
					expr.setType(ExpressionConstants.SCRIPT_TYPE);
					expr.setInterpreter(ExpressionConstants.GROOVY);
					if (data.getDefaultValue().getReferencedElements().isEmpty()){
						expr.setContent(data.getDefaultValue().getContent());
						expr.getReferencedElements().clear();
					} else {
						expr.setType(ExpressionConstants.CONSTANT_TYPE);
						expr.setContent("");

					}
				} else {
					if (data.getDataType() instanceof XMLData){
						expr.setType(ExpressionConstants.CONSTANT_TYPE);
						expr.setContent("");
					} else {
						expr.setType(ExpressionConstants.CONSTANT_TYPE);
						expr.setContent(data.getDefaultValue().getContent());
					}
				}
			} else {
				expr.setType(ExpressionConstants.CONSTANT_TYPE);
				expr.setContent("");
				expr.getReferencedElements().clear();
			}

		} else {
			if (ExpressionConstants.PARAMETER_TYPE.equals(expr.getType())){
				Parameter parameter = (Parameter) expr.getReferencedElements().get(0);
				expr.setType(ExpressionConstants.CONSTANT_TYPE);
				expr.setContent(parameter.getValue());
			} else {
				if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(expr.getType())) {
					expr.setType(ExpressionConstants.CONSTANT_TYPE);
					expr.setContent("");
				} else {
					if (ExpressionConstants.CONNECTOR_TYPE.equals(expr.getType())) {
						expr.setType(ExpressionConstants.CONSTANT_TYPE);
						expr.setContent("");
					} else {
						if (ExpressionConstants.SCRIPT_TYPE.equals(expr.getType())){
							if (!expr.getReferencedElements().isEmpty()){
								expr.setType(ExpressionConstants.CONSTANT_TYPE);
								expr.setContent("");
								expr.getReferencedElements().clear();
							}
						} else {
							expr.setType(ExpressionConstants.CONSTANT_TYPE);
						}
					}
				}
			}
		}	
		return expr;
	}

	private Data getReferencedData(Form form,Expression expr){
		List<Data> datas = ModelHelper.getAccessibleData(form, true);
		for (Data data:datas){
			if (data.getName().equals(expr.getName())){
				return data;
			}
		}
		return null;
	}



	protected void undeployProcess(AbstractProcess process, ProcessAPI processApi) throws InvalidSessionException,  PageOutOfRangeException, ProcessDefinitionNotFoundException,  IllegalProcessStateException, DeletionException {
		long nbDeployedProcesses = processApi.getNumberOfProcesses() ;
		if(nbDeployedProcesses > 0){
			if(lastProcessDeployed == null){
				lastProcessDeployed = process.getName();
			}
			List<ProcessDeploymentInfo> processes = processApi.getProcesses(0, (int) nbDeployedProcesses, ProcessDefinitionCriterion.DEFAULT) ;
			for(ProcessDeploymentInfo info : processes){
				if(info.getName().equals(lastProcessDeployed) && info.getVersion().equals(VERSION)){
					try{
						if (processApi.getProcessDeploymentInfo(info.getProcessId()).getActivationState() == ActivationState.ENABLED){
							processApi.disableProcess(info.getProcessId()) ;
						}
					}catch (ProcessDisablementException e) {
						BonitaStudioLog.error(e);
					}
					processApi.deleteProcess(info.getProcessId()) ;
				}
			}
		}
	}


	private void copyActors(AbstractProcess proc,AbstractProcess procCopy){
		List<Actor> actors = proc.getActors();
		for (Actor actor:actors){
			procCopy.getActors().add((Actor)EcoreUtil.copy(actor));
		}
	}

}
