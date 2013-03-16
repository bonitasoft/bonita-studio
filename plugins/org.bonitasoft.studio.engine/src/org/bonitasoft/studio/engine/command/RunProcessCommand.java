/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.command;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.ValidationDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.DeployProcessOperation;
import org.bonitasoft.studio.engine.preferences.BonitaUserXpPreferencePage;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.IProgressService;

public class RunProcessCommand extends AbstractHandler implements IHandler {

	public static final Object FORCE_OVERRITE = null;
	protected static final String APPLI_PATH = "/bonita?"; //$NON-NLS-1$;
	public static final String PROCESS = "process";

	protected boolean runSynchronously;
	protected AbstractProcess selectedProcess;
	private Set<EObject> excludedObject;
	private IStatus status;

	private URL url;

	public RunProcessCommand() {
		this(false);
	}


	public RunProcessCommand(boolean runSynchronously) {
		this.runSynchronously = runSynchronously;
		excludedObject = new HashSet<EObject>();
	}

	public RunProcessCommand(AbstractProcess proc,boolean runSynchronously) {
		this(runSynchronously);
		selectedProcess = proc ;
	}


	public RunProcessCommand(Set<EObject> excludedObject) {
		this(false);
		this.excludedObject = excludedObject ;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final String configurationId = retrieveConfigurationId(event);
		String currentTheme = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.DEFAULT_USERXP_THEME) ;
		String installedTheme = BonitaUserXpPreferencePage.getInstalledThemeId()  ;
		if(installedTheme != null && !installedTheme.equals(currentTheme)){
			BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.DEFAULT_USERXP_THEME, currentTheme) ;
			BonitaUserXpPreferencePage.updateBonitaHome() ;
		}

		final Set<AbstractProcess> executableProcesses = getProcessesToDeploy(event) ;

		if(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.VALIDATION_BEFORE_RUN)){
			//Validate before run
			final ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
			Command cmd = cmdService.getCommand("org.bonitasoft.studio.validation.batchValidation");
			Map<String, Object> parameters = new HashMap<String,Object>();
			parameters.put("showReport", false);
			Set<Diagram> diagrams = new HashSet<Diagram>();
			for(AbstractProcess p : executableProcesses){
				Resource eResource = p.eResource();
				if(eResource!=null){
					for(EObject e : eResource.getContents()){
						if(e instanceof Diagram){
							diagrams.add((Diagram) e);
						}
					}
				}
			}
			parameters.put("diagrams", diagrams);
			try {
				final IStatus status = (IStatus) cmd.executeWithChecks(new ExecutionEvent(cmd,parameters,null,null));
				if(statusContainsError(status)){
					if(!FileActionDialog.getDisablePopup()){
						String errorMessage = Messages.errorValidationMessage +PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getTitle()+Messages.errorValidationContinueAnywayMessage ;
						int result = new ValidationDialog(Display.getDefault().getActiveShell(), Messages.validationFailedTitle,errorMessage, ValidationDialog.YES_NO_SEEDETAILS).open();

						if(result == ValidationDialog.NO){
							return null;
						}else if(result == ValidationDialog.SEE_DETAILS){
							IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
							try {
								activePage.showView("org.bonitasoft.studio.validation.view");
							} catch (PartInitException e) {
								BonitaStudioLog.error(e);
							}
							return null;
						}

					}
				}
			} catch (Exception e) {
				BonitaStudioLog.error(e);
			}
		}


		IRunnableWithProgress runnable =  new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
				monitor.beginTask(Messages.run, IProgressMonitor.UNKNOWN) ;
				final DeployProcessOperation operation = new DeployProcessOperation() ;
				operation.setConfigurationId(configurationId) ;
				operation.setObjectToExclude(excludedObject) ;

				for(AbstractProcess process : executableProcesses){
					operation.addProcessToDeploy(process) ;
				}

				status = operation.run(monitor) ;
				if(status == null){
					return ;
				}
				if(status.getSeverity() == IStatus.CANCEL){
					return ;
				}
				if(!status.isOK()){
					if(!runSynchronously){
						Display.getDefault().syncExec(new Runnable() {

							@Override
							public void run() {
								new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.deploymentFailedMessage, Messages.deploymentFailedMessage,status,IStatus.ERROR | IStatus.WARNING).open() ;
							}
						}) ;
					}
					return ;
				}

				AbstractProcess p = getProcessToRun(event) ;
				if(p!= null){
					try{
						url = operation.getUrlFor(p,monitor) ;
						if(!runSynchronously){
							BOSWebServerManager.getInstance().startServer(monitor) ;
							new OpenBrowserCommand(url, BonitaPreferenceConstants.APPLICATION_BROWSER_ID, "Bonita Application").execute(null) ;
						}
					}catch (Exception e) {
						status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,e.getMessage(),e);
						BonitaStudioLog.error(e) ;
					}
				}else{
					if(!runSynchronously){
						ICommandService service = (ICommandService)PlatformUI.getWorkbench().getService(ICommandService.class);
						Command cmd = service.getCommand("org.bonitasoft.studio.application.openConsole") ;
						try {
							cmd.executeWithChecks(new ExecutionEvent());
						} catch (Exception ex) {
							status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,ex.getMessage(),ex);
							BonitaStudioLog.error(ex);
						}
					}
				}
			}

		};

		IProgressService service = PlatformUI.getWorkbench().getProgressService() ;



		try {
			if(runSynchronously){
				runnable.run(Repository.NULL_PROGRESS_MONITOR);
			}else{
				service.run(true,false,runnable) ;
			}
		} catch (final Exception e) {
			BonitaStudioLog.error(e) ;
			status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,e.getMessage(),e);
			if(!runSynchronously){
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.deploymentFailedMessage, Messages.deploymentFailedMessage, e).open() ;
					}
				}) ;
			}
		}
		return status;
	}

	private boolean statusContainsError(IStatus validationStatus) {
		if(validationStatus != null){
			for(IStatus s : validationStatus.getChildren()){
				if(s.getSeverity() == IStatus.WARNING || s.getSeverity() == IStatus.ERROR){
					return true;
				}
			}
		}
		return false;
	}


	protected AbstractProcess getProcessToRun(ExecutionEvent event) {
		if(event !=null && event.getParameters().get(PROCESS) != null){
			return (AbstractProcess) event.getParameters().get(PROCESS) ;
		}else{
			IEditorPart editor = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor() ;
			boolean isADiagram = editor != null && editor instanceof DiagramEditor;
			if(isADiagram){
				List selectedEditParts = ((DiagramEditor)editor).getDiagramGraphicalViewer().getSelectedEditParts();
				if(selectedEditParts != null && !selectedEditParts.isEmpty()){
					Object selectedEp = selectedEditParts.iterator().next() ;
					if(selectedEp != null){
						return ModelHelper.getParentProcess(((IGraphicalEditPart)selectedEp).resolveSemanticElement()) ;
					}
				}else{
					EObject element = ((DiagramEditor) editor).getDiagramEditPart().resolveSemanticElement();
					return ModelHelper.getParentProcess(element) ;
				}
			}
		}
		return null;
	}


	protected String retrieveConfigurationId(ExecutionEvent event) {
		String configurationId = null ;
		if(event != null){
			configurationId = event.getParameter("configuration") ;
		}
		if(configurationId == null){
			configurationId = ConfigurationPlugin.getDefault().getPreferenceStore().getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION) ;
		}
		return configurationId;
	}

	protected Set<AbstractProcess> getProcessesToDeploy(ExecutionEvent event){

		Set<AbstractProcess> result = new TreeSet<AbstractProcess>(new Comparator<AbstractProcess>() {

			@Override
			public int compare(AbstractProcess p1, AbstractProcess p2) {
				String s1 = p1.getName() + "--"+ p1.getVersion();
				String s2 = p2.getName() + "--"+ p2.getVersion();
				return  s1.compareTo(s2) ;
			}
		});
		if(event != null && event.getParameters().get(PROCESS) != null){
			selectedProcess = (AbstractProcess) event.getParameters().get(PROCESS) ;
			Set<AbstractProcess> calledProcesses = new HashSet<AbstractProcess>();
			findCalledProcesses(selectedProcess,calledProcesses);
			if(!calledProcesses.isEmpty()){
				result.addAll(calledProcesses);
			}
			if(selectedProcess != null){
				result.add(selectedProcess) ;
			}
		}else{
			selectedProcess = getProcessInEditor() ;
			if(selectedProcess instanceof MainProcess){
				for(EObject p : ModelHelper.getAllItemsOfType(selectedProcess, ProcessPackage.Literals.POOL)){
					result.add((AbstractProcess) p) ;
					Set<AbstractProcess> calledProcesses = new HashSet<AbstractProcess>();
					findCalledProcesses((AbstractProcess) p,calledProcesses);
					if(!calledProcesses.isEmpty()){
						result.addAll(calledProcesses);
					}
				}
			}else{
				Set<AbstractProcess> calledProcesses = new HashSet<AbstractProcess>();
				findCalledProcesses(selectedProcess,calledProcesses);
				if(!calledProcesses.isEmpty()){
					result.addAll(calledProcesses);
				}
				if(selectedProcess != null){
					result.add(selectedProcess) ;
				}
			}
		}

		return result ;
	}


	private void findCalledProcesses(AbstractProcess process,Set<AbstractProcess> result) {
		final List<CallActivity> callActivities = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.CALL_ACTIVITY);
		final DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
		for(CallActivity callActivity : callActivities){
			final Expression calledName = callActivity.getCalledActivityName();
			if(calledName != null && calledName.getContent() != null && !calledName.getContent().isEmpty()){
				final Expression calledVersion = callActivity.getCalledActivityVersion();
				String version = null ;
				if(calledVersion != null && calledVersion.getContent() != null && !calledVersion.getContent().isEmpty()){
					version = calledVersion.getContent();
				}
				AbstractProcess subProcess = ModelHelper.findProcess(calledName.getContent(), version,diagramStore.getAllProcesses());
				if (subProcess != null && !containsSubProcess(subProcess,result)) {
					result.add(subProcess);
					findCalledProcesses(subProcess, result);
				}
			}
		}
	}

	private boolean containsSubProcess(AbstractProcess subProcess,Set<AbstractProcess> result){
		String currentSubProcessId=subProcess.getName()+"--"+subProcess.getVersion();
		for (AbstractProcess process:result){
			String id=process.getName()+"--"+process.getVersion();
			if (id.equals(currentSubProcessId)){
				return true;
			}
		}
		return false;
	}

	protected MainProcess getProcessInEditor() {
		IEditorPart editor = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor() ;
		boolean isADiagram = editor != null && editor instanceof DiagramEditor;
		if(isADiagram){
			EObject root = ((DiagramEditor)editor).getDiagramEditPart().resolveSemanticElement() ;
			MainProcess mainProc = ModelHelper.getMainProcess(root) ;
			return mainProc ;
		}

		return null;
	}


	@Override
	public boolean isEnabled() {
		MainProcess process =  getProcessInEditor() ;
		return process != null && process.isEnableValidation() ;
	}


	public URL getUrl() {
		return url;
	}

}