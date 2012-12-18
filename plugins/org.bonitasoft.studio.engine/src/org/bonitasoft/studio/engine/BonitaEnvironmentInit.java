/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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


public class BonitaEnvironmentInit {
	
	
//	private static final String PLATFORM_PASSWORD = "platform";
//	private static final String PLATFORM_USER = "platformAdmin";
//	private static final String BONITA_TECHNICAL_USER = "studio";
//	private static final String BONITA_TECHNICAL_USER_PASSWORD = "bpm";
//	
//	private static final String JAVA_SECURITY_AUTH_LOGIN_CONFIG_PROPERTY = "java.security.auth.login.config"; //$NON-NLS-1$
//	private static final String ORG_OW2_BONITA_API_TYPE_PROPERTY = "org.ow2.bonita.api-type"; //$NON-NLS-1$
//	
//	private static final String DEFAULT_TENANT_NAME = "default";
//	private static final String DEFAULT_TENANT_DESC = "The default tenant created by the Studio";
//	
//	private static boolean initialized;
//
//	public static synchronized void init(IProgressMonitor monitor) throws Exception {
//		if (!isInitialized()) {
//			monitor.setTaskName(Messages.initializingProcessEngine);
//
//
//			// Setting useCaches to false avoids a memory leak of URLJarFile
//			// instances
//			// It's a workaround for a Sun bug (see bug id 4167874 - fixed in
//			// jdk 1.7). Otherwise,
//			// URLJarFiles will never be garbage collected.
//			// o.a.g.deployment.util.DeploymentUtil.readAll()
//			// causes URLJarFiles to be created
//			try {
//				// Protocol/file shouldn't matter.
//				// As long as we don't get an input/output stream, no operations
//				// should occur...
//				new URL("http://a").openConnection().setDefaultUseCaches(false); //$NON-NLS-1$
//			} catch (IOException ioe) {
//				// Can't Log this. Should we send to STDOUT/STDERR?
//			}
//
//		
//			 //INITIALIZE DEPLOYMENT CONFIGURATION
//			String currentConfig = RemoteDeployPlugin.getDefault().getPreferenceStore().getString(RemoteDeployConstants.CURRENT_CONFIG);
//			if (currentConfig != null && currentConfig.length() > 0) {
//				DeploymentConfigurationArtifact artifact = DeploymentConfigurationRepository.getInstance().getArtifact(currentConfig);
//				Properties prop = (Properties)artifact.getContent() ;
//				for(Object key : prop.keySet()){
//					System.setProperty(key.toString(), prop.getProperty(key.toString())) ;
//				}
//			}
//			AccessorUtil.resetContext() ;
//			File destBonitaHome = BonitaHomeUtil.initBonitaHome();
//
//
//			removeMe(destBonitaHome);
//
//
//
//			IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.engine.preEngineAction"); //$NON-NLS-1$
//			IEngineAction contrib = null;
//			for (IConfigurationElement elem : elements){
//				try {
//					contrib = (IEngineAction) elem.createExecutableExtension("class"); //$NON-NLS-1$
//				} catch (CoreException e) {
//					BonitaStudioLog.log(e);
//				} 
//				contrib.run();
//			}
//
//			ClassLoader ori = Thread.currentThread().getContextClassLoader();
//
//			if (useEJB()) {
//				Thread.currentThread().setContextClassLoader(RemoteDeployManager.createClassLoader());
//			}
//			LoginContext loginContext = createALoginContext();			
//			monitor.worked(1);
//			try {
//				loginContext.login();
//				AccessorUtil.getQueryDefinitionAPI().getProcesses(); // first call to start the engine
//				if (BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.DROP_DB)) {
//					if (BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.RETRIEVE_USERS)) {
//						retrieveUsersAndRoles();
//					}
//				}
//				initialized = true;
//				monitor.worked(1);
//			} catch (GenericJDBCException e) {
//				// can't connect to the database
//				BonitaStudioLog.log(e);
//				handleDataBaseError();
//			} catch (Exception e) {
//				BonitaStudioLog.log(e);
//				throw e;
//			} finally {
//				loginContext.logout();
//				Thread.currentThread().setContextClassLoader(ori);
//			}
//
//
//
//		}
//	}
//
//
//
//
//	/**
//	 * @param bonita_home 
//	 * @throws IOException
//	 * @throws FileNotFoundException
//	 * @deprecated TO BE REMOVED ASAP
//	 */
//	protected static void removeMe(File bonita_home) {
//		System.setProperty(ORG_OW2_BONITA_API_TYPE_PROPERTY, RemoteDeployManager.getAPIType());
//	}
//
//	private static void handleDataBaseError() {
//		if (PlatformUI.isWorkbenchRunning()) {
//			Display.getDefault().syncExec(new Runnable() {
//				public void run() {
//					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//					if (MessageDialog.openConfirm(shell, Messages.dataBaseError_Title, Messages.dataBaseError_Msg)) {
//						IWorkspace workspace = ResourcesPlugin.getWorkspace();
//						IPath bonitaDb = workspace.getRoot().getLocation().append("bonita-db");
//						IPath bonitaLargeDataRepository = workspace.getRoot().getLocation().append("bonita-large-data-repository");
//						NullProgressMonitor monitor = new NullProgressMonitor();
//						File file = bonitaDb.toFile();
//						if(file.exists()){
//							PlatformUtil.delete(file, monitor);
//						}
//						file = bonitaLargeDataRepository.toFile();
//						if(file.exists()){
//							PlatformUtil.delete(file, monitor);
//						}
//						PlatformUI.getWorkbench().close();
//					}
//
//				}
//			});
//		}
//	}
//
//	/**
//	 * retrieve users/group/roles from files saved the last time the studio was
//	 * used: must be logged
//	 */
//	@SuppressWarnings("unchecked")
//	public static void retrieveUsersAndRoles() {
//		try {
//			List<Role> roles = null;
//			List<User> users = null;
//			List<Group> groups = null;
//			List<ProfileMetadata> profileMetadatas = null;
//
//			XStream xstream = new XStream(new DomDriver());
//			File rolesFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + File.separatorChar + ".roles"); //$NON-NLS-1$
//			File usersFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + File.separatorChar + ".users"); //$NON-NLS-1$
//			File groupsFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + File.separatorChar + ".groups"); //$NON-NLS-1$
//			File profileMetadataFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + File.separatorChar + ".profilesMetadata"); //$NON-NLS-1$
//
//			if (rolesFile.exists() && usersFile.exists() && groupsFile.exists() && profileMetadataFile.exists()) {
//
//				// get roles
//				FileInputStream in = new FileInputStream(rolesFile);
//				roles = (List<Role>) xstream.fromXML(in);
//				in.close();
//
//				// get users
//				in = new FileInputStream(usersFile);
//				users = (List<User>) xstream.fromXML(in);
//				in.close();
//
//				// get groups
//				in = new FileInputStream(groupsFile);
//				groups = (List<Group>) xstream.fromXML(in);
//				in.close();
//
//				// get profiles
//				in = new FileInputStream(profileMetadataFile);
//				profileMetadatas = (List<ProfileMetadata>) xstream.fromXML(in);
//				in.close();
//
//				if (roles != null && users != null && groups != null && profileMetadatas != null) {
//
//					final IdentityAPI identityAPI = AccessorUtil.getIdentityAPI();
//					cleanIdentityAPI(identityAPI) ;
//					for (Role role : roles) {
//						try {
//							identityAPI.importRole(role.getUUID(), role.getName(), role.getLabel(), role.getDescription());
//						} catch (RoleAlreadyExistsException e) {
//							// do nothing
//						}
//					}
//
//					List<Group> added;
//					List<Group> notAdded;
//					// create groups: can't create a group if it's parent is not
//					// null or not created
//					do {
//						added = new ArrayList<Group>();
//						notAdded = new ArrayList<Group>();
//						for (Group group : groups) {
//							if (group.getParentGroup() == null || identityAPI.groupExists(group.getParentGroup().getUUID())) {
//								try {
//									identityAPI.importGroup(group.getUUID(), group.getName(), group.getLabel(), group.getDescription(),
//											group.getParentGroup() != null ? group.getParentGroup().getUUID() : null);
//								} catch (GroupAlreadyExistsException e) {
//									// do nothing
//								}
//								added.add(group);
//							} else {
//								notAdded.add(group);
//							}
//						}
//						// try to add not already added groups
//						groups = notAdded;
//					} while (added.size() > 0);// if no new groups were created,
//					// we stop
//					if (notAdded.size() > 0) {
//						BonitaStudioLog.log("Some groups were not added:" + notAdded);
//					}
//					for (ProfileMetadata profile : profileMetadatas) {
//						try {
//							identityAPI.addProfileMetadata(profile.getName(), profile.getLabel());
//						} catch (MetadataAlreadyExistsException e) {
//							// do nothing
//						}
//					}
//
//					// import users
//					for (User user : users) {
//						// must create a Map<String,String> for metadata (key is
//						// the name of the metadata)
//						Map<String, String> metadatas = new HashMap<String, String>();
//						for (Entry<ProfileMetadata, String> group : user.getMetadata().entrySet()) {
//							metadatas.put(group.getKey().getName(), group.getValue());
//						}
//
//						try {
//							User importUser = identityAPI.importUser(user.getUUID(), user.getUsername(), user.getPassword(),
//									user.getFirstName(), user.getLastName(), user.getTitle(), user.getJobTitle(), user.getManagerUUID(), metadatas);
//							ContactInfo contactInfo = user.getProfessionalContactInfo();
//							if(contactInfo != null){
//								identityAPI.updateUserProfessionalContactInfo(user.getUUID(), contactInfo.getEmail(), contactInfo.getPhoneNumber(),
//										contactInfo.getMobileNumber(),
//										contactInfo.getFaxNumber(),
//										contactInfo.getBuilding(),
//										contactInfo.getRoom(),
//										contactInfo.getAddress(),
//										contactInfo.getZipCode(),
//										contactInfo.getCity(),
//										contactInfo.getState(),
//										contactInfo.getCountry(),
//										contactInfo.getWebsite());
//							}
//							contactInfo = user.getPersonalContactInfo();
//							if(contactInfo != null){
//								identityAPI.updateUserPersonalContactInfo(user.getUUID(), contactInfo.getEmail(), contactInfo.getPhoneNumber(),
//										contactInfo.getMobileNumber(),
//										contactInfo.getFaxNumber(),
//										contactInfo.getBuilding(),
//										contactInfo.getRoom(),
//										contactInfo.getAddress(),
//										contactInfo.getZipCode(),
//										contactInfo.getCity(),
//										contactInfo.getState(),
//										contactInfo.getCountry(),
//										contactInfo.getWebsite());
//							}
//							// add membership
//							for (Membership membership : user.getMemberships()) {
//								// retreive or create the new membership (group
//								// and role have the same uuid as before)
//								Membership newMemberShip = identityAPI.getMembershipForRoleAndGroup(membership.getRole().getUUID(),
//										membership.getGroup().getUUID());
//								identityAPI.addMembershipToUser(importUser.getUUID(), newMemberShip.getUUID());
//							}
//						} catch (UserAlreadyExistsException e) {
//							// do nothing
//						} catch (Exception e) {
//							BonitaStudioLog.log(e);
//						}
//					}
//
//					//Set delegee
//					for (User user : users) {
//						String delegeeUUID = user.getDelegeeUUID();
//						if(delegeeUUID!= null){
//							try{
//								identityAPI.updateUserDelegee(user.getUUID(), delegeeUUID);
//							}catch (UserNotFoundException e) {
//								//Nothing to do
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			BonitaStudioLog.log(e);
//		}
//
//	}
//
//	/**
//	 * Removes all users,group,roles, profile metadata from the database
//	 * @param identityAPI
//	 * @throws UserNotFoundException 
//	 * @throws GroupNotFoundException 
//	 * @throws RoleNotFoundException 
//	 * @throws MetadataNotFoundException 
//	 */
//	private static void cleanIdentityAPI(final IdentityAPI identityAPI) throws UserNotFoundException, GroupNotFoundException, RoleNotFoundException, MetadataNotFoundException {
//		for(User u : identityAPI.getAllUsers()){
//			identityAPI.removeUserByUUID(u.getUUID()) ;
//		}
//
//		for(Group g : identityAPI.getAllGroups()){
//			identityAPI.removeGroupByUUID(g.getUUID()) ;
//		}
//
//		for(Role r : identityAPI.getAllRoles()){
//			identityAPI.removeRoleByUUID(r.getUUID()) ;
//		}
//
//		for(ProfileMetadata p : identityAPI.getAllProfileMetadata()){
//			identityAPI.removeProfileMetadataByUUID(p.getUUID()) ;
//		}
//
//	}
//
//	public synchronized static LoginContext createALoginContext() {
//		if (System.getProperty(JAVA_SECURITY_AUTH_LOGIN_CONFIG_PROPERTY) == null) {
//			String jaasFilePath = BonitaHomeUtil.getJaasPath();
//			System.setProperty(JAVA_SECURITY_AUTH_LOGIN_CONFIG_PROPERTY, jaasFilePath);
//		}
//
//		final IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
//		String login = store.getString(BonitaPreferenceConstants.USER_NAME);
//		String pass = store.getString(BonitaPreferenceConstants.USER_PASSWORD);
//		LoginContext loginContext = null;
//		
//		try {
//			loginContext = createLoginContextForParameters(login, pass);
//		} catch (LoginException ex) {
//			BonitaStudioLog.log(ex);
//			/*perhaps user has changed user name and password and gave wrong values*/
//			if(!BonitaPreferenceConstants.USER_NAME_DEFAULT.equals(login)
//					|| !BonitaPreferenceConstants.USER_PASSWORD_DEFAULT.equals(pass)){
//				try {
//					loginContext = createLoginContextForParameters(BonitaPreferenceConstants.USER_NAME_DEFAULT, BonitaPreferenceConstants.USER_PASSWORD_DEFAULT);
//					final String warningMessage = Messages.bind(Messages.incorrectUserNamePassword, BonitaPreferenceConstants.USER_NAME_DEFAULT, BonitaPreferenceConstants.USER_PASSWORD_DEFAULT);
//					
//					if(PlatformUI.isWorkbenchRunning()){
//						Display.getDefault().syncExec(new Runnable() {
//							
//							@Override
//							public void run() {
//								Shell shell = Display.getDefault().getActiveShell();
//								MessageDialog.openWarning(shell, "Wrong username/password", warningMessage);
//							}
//						});
//						
//					} else {
//						BonitaStudioLog.log("Workbench is not yet running.\n"+warningMessage);
//					}
//					store.setValue(BonitaPreferenceConstants.USER_NAME, BonitaPreferenceConstants.USER_NAME_DEFAULT);
//					store.setValue(BonitaPreferenceConstants.USER_PASSWORD, BonitaPreferenceConstants.USER_PASSWORD_DEFAULT);
//				} catch (LoginException e) {
//					BonitaStudioLog.log(e);
//					/*Currently no solution, but it is not the use case asked to be solved*/
//					/*User name/password by default and provided by user are not correct
//					 * Ask him to reset database or to provide a new username/password*/
//				} catch (IOException e) {
//					BonitaStudioLog.log(ex);
//				}
//			}
//		} catch (Exception e) {
//			BonitaStudioLog.log(e);
//		}
//		return loginContext;
//	}
//
//	private static LoginContext createLoginContextForParameters(String login, String pass)
//			throws IOException, LoginException {
//		LoginContext loginContext;
//		CallbackHandler handler = new SimpleCallbackHandler(login, pass, true);
//		BonitaHomeUtil.initBonitaHome();
//		// This piece of code is to take in consideration the change of the
//		// System property java.security.auth.login.config
//
//		Configuration config = (Configuration) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
//			public Object run() {
//				return Configuration.getConfiguration();
//			}
//		});
//		config.refresh();
//
//		loginContext = new LoginContext("BonitaAuth", handler); //$NON-NLS-1$
//		loginContext.login() ;
//		loginContext.logout() ;
//		loginContext = new LoginContext("BonitaStore", handler); //$NON-NLS-1$
//		return loginContext;
//	}
//
//
//
//	public static boolean useEJB() {
//		return RemoteDeployManager.useEJB();
//	}
//
//	public static synchronized boolean isInitialized() {
//		return initialized;
//	}
//
//
//
//	public static URL getRestWebApp() {
//		return getEngineLibsBundle().getResource("rest/bonita-server-rest.war");
//	}
//
//
//	public static Bundle getEngineLibsBundle() {
//		BundleClassLoader cl = (BundleClassLoader) AccessorUtil.class.getClassLoader();
//		return cl.getBundle();
//	}

}
