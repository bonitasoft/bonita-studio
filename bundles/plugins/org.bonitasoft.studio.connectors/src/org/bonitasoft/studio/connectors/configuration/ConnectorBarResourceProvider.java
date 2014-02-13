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
package org.bonitasoft.studio.connectors.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorBarResourceProvider implements BARResourcesProvider {


	@Override
	public List<BarResource> addResourcesForConfiguration(BusinessArchiveBuilder builder, AbstractProcess process, Configuration configuration,Set<EObject> excludedObjects) {
		final List<BarResource> resources = new ArrayList<BarResource>() ;
		if(configuration == null){
			return resources ;
		}

		final DependencyRepositoryStore libStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
		final ConnectorImplRepositoryStore implStore = (ConnectorImplRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class) ;
		for(DefinitionMapping association :  configuration.getDefinitionMappings()){
			if(association.getType().equals(FragmentTypes.CONNECTOR)){
				final String implId = association.getImplementationId() ;
				final String implVersion = association.getImplementationVersion() ;
				if(implId != null&& implVersion != null){
					ConnectorImplementation implementation = implStore.getImplementation(implId, implVersion) ;
					if(implementation == null){
						throw new RuntimeException(implId + "("+implVersion+") not found in repository") ;
					}
					final String fileName = URI.decode(implementation.eResource().getURI().lastSegment());
					final EMFFileStore file = implStore.getChild(fileName) ;
					Assert.isNotNull(file) ;
					boolean isUserImplementation = file.canBeShared() ;
					try{
						File implementationFile = file.getResource().getLocation().toFile() ;
						if(!implementationFile.exists()){
							implementationFile =  new File(URI.decode(file.getEMFResource().getURI().toFileString())) ;
						}
						final FileInputStream fis = new FileInputStream(implementationFile) ;
						byte[] content = new byte[fis.available()] ;
						fis.read(content) ;
						fis.close() ;

						builder.addConnectorImplementation(new BarResource(NamingUtils.toConnectorImplementationFilename(implId, implVersion, true), content)) ;

						if(isUserImplementation){ //Generate jar from source file
							addImplementationJar(builder,implementation) ;
						}

						//Add jar dependencies
						for(FragmentContainer fc : configuration.getProcessDependencies()){
							if(fc.getId().equals(FragmentTypes.CONNECTOR)){
								for(FragmentContainer connector : fc.getChildren()){
									if(connector.getId().equals(NamingUtils.toConnectorImplementationFilename(implId, implVersion, false))){
										for(Fragment fragment : connector.getFragments()){
											final String jarName = fragment.getValue() ;
											if(jarName.endsWith(".jar") && fragment.isExported()){
												IRepositoryFileStore jarFile =  libStore.getChild(jarName) ;
												if(jarFile != null){
													addFileContents(resources, jarFile.getResource().getLocation().toFile()) ;
												}
											}
										}
									}
								}
							}
						}
					}catch (Exception e) {
						BonitaStudioLog.error(e) ;
					}
				}
			}
		}

		for(BarResource barResource : resources ){
			builder.addClasspathResource(barResource) ;
		}

		return resources;
	}

	private void addImplementationJar(BusinessArchiveBuilder builder, ConnectorImplementation impl) {
		final ConnectorSourceRepositoryStore sourceStore = (ConnectorSourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class) ;
		final String connectorJarName = NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(), false)  +".jar";
		String qualifiedClassName = impl.getImplementationClassname() ;
		String packageName ="" ;
		if(qualifiedClassName.indexOf(".")!= -1){
			packageName = qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf(".")) ;
		}

		final PackageFileStore file =  (PackageFileStore) sourceStore.getChild(packageName) ;
		if(file != null){
			File tmpFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), connectorJarName) ;
			tmpFile.delete();

			try{
				file.exportAsJar(tmpFile.getAbsolutePath(), false) ;
				final FileInputStream fis = new FileInputStream(tmpFile) ;
				byte[] content = new byte[fis.available()] ;
				fis.read(content) ;
				fis.close() ;
				tmpFile.delete();
				builder.addClasspathResource(new BarResource(connectorJarName,content)) ;
			}catch (Exception e) {
				BonitaStudioLog.error(e) ;
			}
		}

	}

	private void addFileContents(final List<BarResource> resources, final File file) throws FileNotFoundException, IOException {
		if (file.exists()) {
			byte[] jarBytes = new byte[(int) file.length()];
			InputStream stream = new FileInputStream(file);
			stream.read(jarBytes);
			stream.close();
			resources.add(new BarResource(file.getName(), jarBytes));
		}
	}

}
