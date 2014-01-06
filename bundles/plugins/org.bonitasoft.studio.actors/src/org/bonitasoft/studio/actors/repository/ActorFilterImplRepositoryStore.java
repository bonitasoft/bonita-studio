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
package org.bonitasoft.studio.actors.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationAdapterFactory;
import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationResourceImpl;
import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationXMLProcessor;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.ValidationLevel;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class ActorFilterImplRepositoryStore extends AbstractEMFRepositoryStore<ActorFilterImplFileStore> implements IImplementationRepositoryStore {

	private static final String STORE_NAME = "filters-impl";
	private static final Set<String> extensions = new HashSet<String>() ;
	public static final String IMPL_EXT = "impl";
	static{
		extensions.add(IMPL_EXT) ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
	 */
	@Override
	public ActorFilterImplFileStore createRepositoryFileStore(String fileName) {
		return new ActorFilterImplFileStore(fileName, this);
	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
	 */
	@Override
	public String getName() {
		return STORE_NAME ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return Messages.filterImplRepositoryName;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
	 */
	@Override
	public Image getIcon() {
		return Pics.getImage("actor_filter-implem-new.png",ActorsPlugin.getDefault());
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
	 */
	@Override
	public Set<String> getCompatibleExtensions() {
		return extensions;
	}

	@Override
	public ConnectorImplementation getImplementation(String id, String version) {
		for(ConnectorImplementation impl : getImplementations()){
			if(impl.getImplementationId().equals(id) && impl.getImplementationVersion().equals(version)){
				return impl ;
			}
		}
		return null;
	}

	@Override
	public List<ConnectorImplementation> getImplementations() {
		List<ConnectorImplementation> result = new ArrayList<ConnectorImplementation>();
		for(IRepositoryFileStore fileStore : getChildren()){
			ConnectorImplementation def = (ConnectorImplementation) fileStore.getContent() ;
			result.add(def) ;
		}
		return result ;
	}

	@Override
	public ActorFilterImplFileStore getChild(String fileName) {
		ActorFilterImplFileStore file = super.getChild(fileName) ;
		if(file == null){
			URL url = ActorsPlugin.getDefault().getBundle().getResource(STORE_NAME+ "/" +fileName);
			if(url != null){
				return new URLActorFilterImplFileStore(url,this) ;
			}else{
				return null ;
			}
		}else{
			return file ;
		}

	}

	@Override
	public List<ActorFilterImplFileStore> getChildren() {
		List<ActorFilterImplFileStore> result = super.getChildren();
		Enumeration<URL> connectorImplementations = ActorsPlugin.getDefault().getBundle().findEntries(STORE_NAME, "*.impl", false);
		if( connectorImplementations != null ){
			while (connectorImplementations.hasMoreElements()) {
				URL url = connectorImplementations.nextElement();
				String[] segments = url.getFile().split("/") ;
				String fileName = segments[segments.length-1] ;
				if(fileName.lastIndexOf(".") != -1){
					String extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()) ;
					if(extensions.contains(extension)){
						result.add(new URLActorFilterImplFileStore(url,this)) ;
					}
				}
			}
		}
		return result ;
	}


	@Override
	public List<ConnectorImplementation> getImplementations(String definitionId, String definitionVersion) {
		List<ConnectorImplementation> implementations = new ArrayList<ConnectorImplementation>() ;
		for(ConnectorImplementation impl : getImplementations()){
			if(impl.getDefinitionId().equals(definitionId)
					&& impl.getDefinitionVersion().equals(definitionVersion)){
				implementations.add(impl) ;
			}
		}
		return implementations ;
	}


	@Override
	protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
		adapterFactory.addAdapterFactory(new ConnectorImplementationAdapterFactory());
	}


	@Override
	public IRepositoryFileStore getImplementationFileStore(String implId,String implVersion) {
		for(ActorFilterImplFileStore implStore : getChildren()){
			ConnectorImplementation impl = implStore.getContent();
			if(impl != null && implId.equals(impl.getImplementationId())
					&& implVersion.equals(impl.getImplementationVersion())){
				return implStore;
			}
		}
		return null;
	}

	@Override
	protected void performMigration(Migrator migrator, URI resourceURI,
			Release release) throws MigrationException {
		migrator.setLevel(ValidationLevel.NONE);
		ResourceSet rSet = migrator.migrateAndLoad(
				Collections.singletonList(resourceURI), release,
				null, Repository.NULL_PROGRESS_MONITOR);
		if(!rSet.getResources().isEmpty()){
			FileOutputStream fos = null;
			try{
				ConnectorImplementationResourceImpl r = (ConnectorImplementationResourceImpl) rSet.getResources().get(0);
				Resource resource = new XMLResourceImpl(resourceURI) ;
				org.bonitasoft.studio.connector.model.implementation.DocumentRoot root = ConnectorImplementationFactory.eINSTANCE.createDocumentRoot() ;
				final ConnectorImplementation definition = EcoreUtil.copy(((org.bonitasoft.studio.connector.model.implementation.DocumentRoot)r.getContents().get(0)).getConnectorImplementation());
				root.setConnectorImplementation(definition);
				resource.getContents().add(root) ;
				Map<String, String> options = new HashMap<String, String>() ;
				options.put(XMLResource.OPTION_ENCODING, "UTF-8");
				options.put(XMLResource.OPTION_XML_VERSION, "1.0");
				File target = new File(resourceURI.toFileString());
				fos = new FileOutputStream(target)  ;
				new ConnectorImplementationXMLProcessor().save(fos, resource, options)  ;
			}catch (Exception e) {
				BonitaStudioLog.error(e, ConnectorPlugin.PLUGIN_ID);
			}finally{
				if(fos != null){
					try {
						fos.close() ;
					} catch (IOException e) {
						BonitaStudioLog.error(e,ConnectorPlugin.PLUGIN_ID);
					}
				}
			}
		}
	}

}
