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
package org.bonitasoft.studio.actors.ui.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.ProfileAPI;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserCriterion;
import org.bonitasoft.engine.profile.Profile;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.model.organization.DocumentRoot;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class InstallOrganizationHandler extends AbstractHandler {



	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if(event != null){
			String id = event.getParameter("artifact") ;
			IRepositoryStore<?> organizationStore = RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class) ;
			IRepositoryFileStore file = organizationStore.getChild(id) ;
			if(file == null){
				BonitaStudioLog.warning("Organization : "+ id +" not found !",ActorsPlugin.PLUGIN_ID) ;
				List<? extends IRepositoryFileStore> organizationFiles = organizationStore.getChildren();
				if(organizationFiles.isEmpty()){
					BonitaStudioLog.warning("No organization found in repository",ActorsPlugin.PLUGIN_ID) ;
					return null;
				}else{
					file = organizationFiles.get(0) ;
				}
			}
			BonitaStudioLog.info("Loading organization "+file.getDisplayName()+" in portal...",ActorsPlugin.PLUGIN_ID) ;
			APISession session = null;
			try{
				session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR) ;
				IdentityAPI identityAPI = BOSEngineManager.getInstance().getIdentityAPI(session) ;
				ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
				SearchResult<ProcessDeploymentInfo> result = processApi.searchProcessDeploymentInfos(new SearchOptionsBuilder(0,Integer.MAX_VALUE).done());
				for(ProcessDeploymentInfo info : result.getResult()){
					processApi.deleteProcessInstances(info.getProcessId(), 0, Integer.MAX_VALUE);
					processApi.deleteArchivedProcessInstances(info.getProcessId(), 0, Integer.MAX_VALUE);
				}
				identityAPI.deleteOrganization() ;
				String content = export((OrganizationFileStore) file);
				identityAPI.importOrganization(content) ;
			}catch(final Exception e){
				if(PlatformUI.isWorkbenchRunning()){
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							new BonitaErrorDialog(Display.getDefault().getActiveShell(), "Error", "An error occured while publishing the organization", e).open() ;
						}

					}) ;
				}
				throw new ExecutionException("", e) ;
			}finally{
				if(session != null){
					BOSEngineManager.getInstance().logoutDefaultTenant(session) ;
				}
			}

			session = null;
			try{
				session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR) ;
				IdentityAPI identityAPI = BOSEngineManager.getInstance().getIdentityAPI(session) ;
				ProfileAPI profileAPI =   BOSEngineManager.getInstance().getProfileAPI(session) ;
				applyAllProfileToUsers(identityAPI,profileAPI) ;
			}catch (final Exception e) {
				if(PlatformUI.isWorkbenchRunning()){
					Display.getDefault().syncExec(new Runnable() {

						@Override
						public void run() {
							new BonitaErrorDialog(Display.getDefault().getActiveShell(), "Error", "An error occured during synchronization", e).open() ;
						}

					}) ;
				}
				throw new ExecutionException("", e) ;
			}finally{
				if(session != null){
					BOSEngineManager.getInstance().logoutDefaultTenant(session) ;
				}
			}


		}
		return null;
	}

	private String export(OrganizationFileStore file) throws IOException {
		Organization organization =  file.getContent() ;
		DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot() ;
		Organization exportedCopy = EcoreUtil.copy(organization)  ;
		exportedCopy.setName(null) ;
		exportedCopy.setDescription(null) ;
		addStudioTechnicalUser(exportedCopy);
		root.setOrganization(exportedCopy) ;
		final XMLProcessor processor = new OrganizationXMLProcessor() ;
		final Resource resource = new XMLResourceImpl() ;
		resource.getContents().add(root) ;
		final Map<String, Object> options = new HashMap<String, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_XML_VERSION, "1.0");
		return processor.saveToString(resource, options);
	}

	/**
	 * @param exportedCopy
	 */
	private void addStudioTechnicalUser(Organization exportedCopy) {
		org.bonitasoft.studio.actors.model.organization.User user = OrganizationFactory.eINSTANCE.createUser();
		PasswordType passwordType = OrganizationFactory.eINSTANCE.createPasswordType();
		passwordType.setValue("bpm");
		passwordType.setEncrypted(false);
		user.setUserName(BonitaConstants.STUDIO_TECHNICAL_USER_NAME);
		user.setFirstName(BonitaConstants.STUDIO_TECHNICAL_USER_FIRST_NAME);
		user.setJobTitle(BonitaConstants.STUDIO_TECHNICAL_USER_JOB_TITLE);
		user.setPassword(passwordType);
		exportedCopy.getUsers().getUser().add(user);
	}

	protected void applyAllProfileToUsers(IdentityAPI identityAPI, ProfileAPI profileAPI) throws Exception {
		final List<Long> profiles = new ArrayList<Long>() ;
		final SearchOptions options = new SearchOptionsBuilder(0, Integer.MAX_VALUE).sort("name", Order.DESC).done();
		final SearchResult<Profile> searchedProfiles = profileAPI.searchProfiles(options);
		for(Profile profile : searchedProfiles.getResult()){
			long profileId = profile.getId();
			profiles.add(profileId) ;
		}

		List<User> users = identityAPI.getUsers(0, Integer.MAX_VALUE, UserCriterion.USER_NAME_ASC) ;
		for(User u : users){
			long id =  u.getId() ;
			for(Long profile : profiles){
				profileAPI.createProfileMember(profile, id, -1L, -1L);
			}
		}
	}
	
	

}
