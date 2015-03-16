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

import java.io.File;
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
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.process.ActivationState;
import org.bonitasoft.engine.bpm.process.IllegalProcessStateException;
import org.bonitasoft.engine.bpm.process.ProcessActivationException;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoCriterion;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.studio.browser.operation.OpenBrowserOperation;
import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.operation.ApplicationURLBuilder;
import org.bonitasoft.studio.form.preview.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.internal.browser.ExternalBrowserInstance;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;

/**
 *@Author Aurelie Zara
 *
 */
public class FormPreviewOperation implements IRunnableWithProgress {

	private final Form form;
	private Form formCopy;
	private final ApplicationLookNFeelFileStore lookNFeel;
	private final IBrowserDescriptor browser;
	private static String lastProcessDeployed;
	private static final int MAX_IT= 100;
	private TransactionalEditingDomain editingDomain;
	private final AbstractFormPreviewInitialization formPreviewInit;



	public FormPreviewOperation(final AbstractFormPreviewInitialization formPreviewInit){
		this.formPreviewInit = formPreviewInit;
		form = formPreviewInit.getForm();
		lookNFeel = formPreviewInit.getLookNFeel();
		browser = formPreviewInit.getBrowser();


	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void run(final IProgressMonitor monitor) throws InvocationTargetException,
	InterruptedException {

		monitor.beginTask(Messages.formPreview, IProgressMonitor.UNKNOWN);

		APISession session = null;
		ProcessAPI processApi = null;
		long procId = -1;
		final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
		final AbstractProcess proc = formPreviewInit.createAbstractProcess(configuration);

		if (formPreviewInit.isCanPreview()){
			configuration.setName("formPreviewConfig");

			new ConfigurationSynchronizer(proc, configuration).synchronize();

			try {
				final Resource resource = doCreateEMFResource(proc,monitor);
				initializeLookNFeel(proc, resource);
				session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
				processApi = BOSEngineManager.getInstance().getProcessAPI(session);
				Assert.isNotNull(processApi) ;

				undeployProcess(proc, processApi);

				final BusinessArchive businessArchive = BarExporter.getInstance().createBusinessArchive(proc,configuration,Collections.EMPTY_SET,false);
				cleanResources(proc, resource);
				final ProcessDefinition def = processApi.deploy(businessArchive);
				procId = def.getId();
				processApi.enableProcess(procId) ;


				final ExternalBrowserInstance browserInstance = new ExternalBrowserInstance(null, browser);
				if (!formPreviewInit.isOnTask()){
					final ApplicationURLBuilder builder = new ApplicationURLBuilder(proc,procId,configuration.getName(),ApplicationURLBuilder.MODE_FORM);
					final URL url = builder.toURL(monitor);
                    final OpenBrowserOperation openCmd = new OpenBrowserOperation(url);
					if(browser.getLocation() != null){
						openCmd.setExternalBrowser(browserInstance);
					}
                    openCmd.execute();
				} else {
					final IdentityAPI identityApi = BOSEngineManager.getInstance().getIdentityAPI(session);
					final long userId = identityApi.getUserByUserName(BonitaConstants.STUDIO_TECHNICAL_USER_NAME).getId();
					final ProcessInstance procInstance = processApi.startProcess(procId);
					boolean isAvailable = false;
					int it = 0;
					while(!isAvailable && it<MAX_IT){
						isAvailable = !processApi.getOpenActivityInstances(procInstance.getId(), 0, 1, null).isEmpty();
						it++;
						Thread.sleep(100);
					}
					if (it<MAX_IT && !processApi.getPendingHumanTaskInstances(userId, 0, 20, null).isEmpty() ){
						final HumanTaskInstance task = processApi.getPendingHumanTaskInstances(userId,0, 20, null).get(0);
						processApi.assignUserTask(task.getId(), userId);
						final URL taskURL = toTaskURL(configuration,proc,procId,task,monitor);
                        final OpenBrowserOperation openCmd = new OpenBrowserOperation(taskURL);
						if(browser.getLocation() != null){
							openCmd.setExternalBrowser(browserInstance);
						}
                        openCmd.execute();
					}
				}
			} catch (final Exception e) {
				BonitaStudioLog.error(e);

			}finally{
				if(session != null){
					BOSEngineManager.getInstance().logoutDefaultTenant(session);
					lastProcessDeployed = proc.getName();
				}
			}
		}

	}


	public URL toTaskURL(final Configuration configuration,final AbstractProcess process,final long procId,final HumanTaskInstance task,final IProgressMonitor monitor) throws UnsupportedEncodingException, MalformedURLException{
		final IPreferenceStore store =  BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore() ;
		final String locale = store.getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE) ;
		final String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
		final String host = store.getString(BonitaPreferenceConstants.CONSOLE_HOST) ;
		final String token = "" ;
		String userName = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.USER_NAME) ;
		String password = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.USER_PASSWORD) ;
		if(configuration != null && configuration.getUsername() != null){
			userName = configuration.getUsername() ;
			password = configuration.getPassword() ;
		}
		final String taskName=((Task)process.getElements().get(0)).getName();
		final String loginURL = BOSWebServerManager.getInstance().generateLoginURL(userName, password) ;
		final String runUrl = ApplicationURLBuilder.APPLI_PATH + token +"ui=form&theme="+procId+"&locale="+locale+"#form="+URLEncoder.encode(process.getName()+"--"+process.getVersion()+"--"+taskName, "UTF-8")+"$entry&task="+task.getId()+"&mode=form";
		return new URL(loginURL+"&redirectUrl="+URLEncoder.encode(runUrl, "UTF-8"));

	}







	private Resource doCreateEMFResource(final AbstractProcess proc,final IProgressMonitor monitor) throws IOException, ExecutionException{
		final URI uri = URI.createFileURI(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath()+File.separator+proc.getName()+".proc");

		final XMLResourceImpl resource =	new XMLResourceImpl(uri){
			@Override
            protected boolean useUUIDs() {
				return true;
			};
		};
		resource.getContents().add(proc);
		resource.save(ProcessDiagramEditorUtil.getSaveOptions()) ;
		return resource;
	}

	private void cleanResources(final AbstractProcess proc, final Resource resource) throws IOException{
		final ApplicationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
		store.getChild(ModelHelper.getEObjectID(proc)).delete();
		resource.delete(Collections.EMPTY_MAP);
	}

	private void initializeLookNFeel(AbstractProcess proc,final Resource resource){
		final ResourceSet resourceSet = new ResourceSetImpl();
		editingDomain  = GMFEditingDomainFactory.getInstance().createEditingDomain(resourceSet);
		proc = (AbstractProcess) resource.getEObject(proc.eResource().getURIFragment(proc));
		final CompoundCommand cc = WebTemplatesUtil.createAddTemplateCommand(editingDomain, proc, lookNFeel, new NullProgressMonitor());
		editingDomain.getCommandStack().execute(cc);
	}








	protected void undeployProcess(final AbstractProcess process, final ProcessAPI processApi) throws InvalidSessionException,   ProcessDefinitionNotFoundException,  IllegalProcessStateException, DeletionException {
		final long nbDeployedProcesses = processApi.getNumberOfProcessDeploymentInfos() ;
		if(nbDeployedProcesses > 0){
			if(lastProcessDeployed == null){
				lastProcessDeployed = process.getName();
			}
			final List<ProcessDeploymentInfo> processes = processApi.getProcessDeploymentInfos(0, (int) nbDeployedProcesses, ProcessDeploymentInfoCriterion.DEFAULT) ;
			for(final ProcessDeploymentInfo info : processes){
				if(info.getName().equals(lastProcessDeployed) && info.getVersion().equals(formPreviewInit.VERSION)){
					try{
						if (processApi.getProcessDeploymentInfo(info.getProcessId()).getActivationState() == ActivationState.ENABLED){
							processApi.disableProcess(info.getProcessId()) ;
						}
					}catch (final ProcessActivationException e) {
						BonitaStudioLog.error(e);
					}
					processApi.deleteProcess(info.getProcessId()) ;
				}
			}
		}
	}




}
