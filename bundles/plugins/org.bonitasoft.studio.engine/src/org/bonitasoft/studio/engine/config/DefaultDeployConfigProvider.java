//package org.bonitasoft.studio.engine.config;
//
//import org.bonitasoft.studio.common.repository.RepositoryInitializationContributor;
//import org.eclipse.core.runtime.IProgressMonitor;
//
//public class DefaultDeployConfigProvider implements RepositoryInitializationContributor {
//
//	public DefaultDeployConfigProvider() {
//	}
//
//	public boolean mustProcess() {
//		return false ;
//	//	return DeploymentConfigurationRepository.getInstance().getProject().getFolder(RemoteDeployConstants.DEFAULT_CONFIG+".conf") == null || !DeploymentConfigurationRepository.getInstance().getProject().getFolder(RemoteDeployConstants.DEFAULT_CONFIG+".conf").exists();
//	}
//
//	public void contributeToRepo(IProgressMonitor monitor) {
////		Properties props = new Properties();
////		props.put(RemoteDeployConstants.REMOTE_DEPLOYMENT_CHOICE, RemoteDeployConstants.STANDARD_MODE);
////		props.put(RemoteDeployConstants.REMOTE_DEPLOYMENT_FACTORY, "");
////		props.put(RemoteDeployConstants.REMOTE_DEPLOYMENT_URL, "");
////		props.put(RemoteDeployConstants.REST_SERVER_ADDRESS_PROPERTY, "");
////		File jaasFile = new File( BonitaHomeUtil.getJaasPath() );
////		props.put(RemoteDeployConstants.REMOTE_DEPLOYMENT_JAAS_FILE, jaasFile.getAbsolutePath());
////		DeploymentConfigurationRepository.getInstance().createArtifact(props, RemoteDeployConstants.DEFAULT_CONFIG,monitor);
//	
//	}
//
//}
