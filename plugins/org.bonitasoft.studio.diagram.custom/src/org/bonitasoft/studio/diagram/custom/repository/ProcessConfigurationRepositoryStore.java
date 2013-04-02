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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.diagram.custom.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ProcessConfigurationRepositoryStore extends AbstractEMFRepositoryStore<ProcessConfigurationFileStore>{

	public static final String STORE_NAME = "process_configurations" ;
	private static final Set<String> extensions = new HashSet<String>() ;
	public static final String CONF_EXT = "conf";

	static{
		extensions.add(CONF_EXT) ;
	}
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getName()
	 */
	public String getName() {
		return STORE_NAME ;
	}


	public String getDisplayName() {
		return Messages.configurations;
	}

	public Image getIcon() {
		return Pics.getImage(PicsConstants.configuration);
	}


	@Override
	public ProcessConfigurationFileStore createRepositoryFileStore(String fileName) {
		return new ProcessConfigurationFileStore(fileName,this) ;
	}


	public Set<String> getCompatibleExtensions() {
		return extensions;
	}

	@Override
	public boolean canBeShared() {
		return false;
	}

	@Override
	protected ProcessConfigurationFileStore doImportInputStream(String fileName, InputStream inputStream) {
		final IFile file = getResource().getFile(fileName);
		try{
			if(file.exists()){
				String fileNameLabel = fileName;
				final String processUUID = fileName.substring(0, fileName.lastIndexOf("."));
				final DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
				final AbstractProcess process = diagramStore.getProcessByUUID(processUUID);
				if(process != null){
					fileNameLabel =Messages.bind(Messages.localConfigurationFor,process.getName() +" ("+process.getVersion()+")");
				}
				if(FileActionDialog.overwriteQuestion(fileNameLabel)){
					file.setContents(inputStream, true, false, Repository.NULL_PROGRESS_MONITOR);
				}else{
					return createRepositoryFileStore(fileName);
				}
			} else {
				File f = file.getLocation().toFile();
				if(!f.getParentFile().exists()){
					f.getParentFile().mkdirs();
					refresh();
				}
				file.create(inputStream, true, Repository.NULL_PROGRESS_MONITOR);
			}
		}catch(Exception e){
			BonitaStudioLog.error(e) ;
		}
		return createRepositoryFileStore(fileName) ;
	}

	@Override
	protected Release getRelease(Migrator targetMigrator, Resource resource) {
		final Map<Object, Object> loadOptions = new HashMap<Object, Object>();
		//Ignore unknown features
		loadOptions.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		XMLOptions options = new XMLOptionsImpl() ;
		options.setProcessAnyXML(true) ;
		loadOptions.put(XMLResource.OPTION_XML_OPTIONS, options);
		try {
			resource.load(loadOptions);
		} catch (IOException e) {
			BonitaStudioLog.error(e,CommonRepositoryPlugin.PLUGIN_ID);
		}
		String modelVersion = ProductVersion.VERSION_6_0_0_ALPHA;
		for(EObject root : resource.getContents()){
			if(root instanceof Configuration){
				String version = ((Configuration) root).getVersion();
				if(version != null){
					modelVersion = version ;
				}
			}
		}
		for(Release release : targetMigrator.getReleases()){
			if(release.getLabel().equals(modelVersion)){
				return release;
			}
		}
		return targetMigrator.getReleases().iterator().next(); //First release of all time
	}

	@Override
	protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
		adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory()) ;
	}
}
