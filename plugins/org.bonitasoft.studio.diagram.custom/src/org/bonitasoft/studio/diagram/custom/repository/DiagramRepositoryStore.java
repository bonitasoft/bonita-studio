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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.notation.util.NotationAdapterFactory;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class DiagramRepositoryStore extends AbstractEMFRepositoryStore<DiagramFileStore> {

	private static final String STORE_NAME = "diagrams" ;
	private static final Set<String> extensions = new HashSet<String>() ;
	static{
		extensions.add("proc") ;
	}

	private AdapterFactoryLabelProvider labelProvider;

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getName()
	 */
	public String getName() {
		return STORE_NAME;
	}

	public String getDisplayName() {
		return Messages.diagrams;
	}

	public Image getIcon() {
		return Pics.getImage(PicsConstants.diagram);
	}

	@Override
	public DiagramFileStore createRepositoryFileStore(String fileName) {
		return new DiagramFileStore(fileName,this)  ;
	}

	public List<AbstractProcess> getAllProcesses() {
		List<AbstractProcess> processes = new ArrayList<AbstractProcess>() ;
		for(IRepositoryFileStore file : getChildren()){
			processes.addAll(((DiagramFileStore)file).getProcesses()) ;
		}
		return processes ;
	}

	public Set<String> getCompatibleExtensions() {
		return extensions  ;
	}

	public List<AbstractProcess> findProcesses(String processName) {
		List<AbstractProcess> result = new ArrayList<AbstractProcess>() ;
		for(AbstractProcess proc : getAllProcesses()){
			if(proc.getName().equals(processName)){
				result.add(proc) ;
			}
		}
		return result;
	}

	public AbstractProcess findProcess(String processName, String processVersion) {
		if(processVersion != null && !processVersion.trim().isEmpty()){
			for(AbstractProcess proc : getAllProcesses()){
				if(proc.getName().equals(processName)
						&& proc.getVersion().equals(processVersion)){
					return proc;
				}
			}
		} else {
			//return the process  with the higher version
					AbstractProcess currentHigher = null;
			for(AbstractProcess proc : getAllProcesses()){
				if(proc.getName().equals(processName)){
					if(currentHigher == null || proc.getVersion().compareTo(currentHigher.getVersion()) > 0){
						currentHigher = proc;
					}
				}
			}
			return currentHigher;
		}
		return null;
	}

	public  List<DiagramFileStore> getRecentChildren(int nbResult) {
		refresh() ;

		List<DiagramFileStore> result = new ArrayList<DiagramFileStore>() ;
		List<IResource> resources = new ArrayList<IResource>() ;
		IFolder folder = getResource();
		try {
			for(IResource r : folder.members()){
				if(r.getFileExtension() != null && getCompatibleExtensions().contains(r.getFileExtension())){
					resources.add(r);
				}
			}
		} catch (CoreException e) {
			BonitaStudioLog.error(e) ;
		}


		Collections.sort(resources, new Comparator<IResource>() {
			public int compare(IResource arg0, IResource arg1) {
				final long lastModifiedArg1 = arg1.getLocation().toFile().lastModified();
				final long lastModifiedArg0 = arg0.getLocation().toFile().lastModified();
				return Long.valueOf(lastModifiedArg1).compareTo(Long.valueOf(lastModifiedArg0));
			}
		}) ;

		for(int i=0; i<nbResult;i++){
			if(resources.size() > i){
				result.add(createRepositoryFileStore(resources.get(i).getName()));
			}
		}


		return result;
	}

	public DiagramFileStore getDiagram(String name, String version) {
		for(DiagramFileStore diagram : getChildren()){
			MainProcess diagramModel = diagram.getContent() ;
			if(diagramModel.getName().equals(name) && diagramModel.getVersion().equals(version)){
				return diagram ;
			}
		}

		return null;
	}

	@Override
	protected DiagramFileStore doImportIResource(String fileName, IResource resource) {
		DiagramFileStore fileStore = super.doImportIResource(fileName, resource);
		return fileStore;
	}

	@Override
	protected DiagramFileStore doImportInputStream(String fileName, InputStream inputStream) {
		final CopyInputStream copyIs = new CopyInputStream(inputStream);
		final InputStream originalStream = copyIs.getCopy();
		final String newFileName = getValidFileName(fileName,copyIs.getCopy());
		copyIs.close();
		return super.doImportInputStream(newFileName, originalStream);
	}


	protected String getValidFileName(final String fileName,final InputStream is) {
		FileOutputStream fos = null;
		File tmpFile = null ;
		try{
			tmpFile = File.createTempFile("tmp", fileName, ProjectUtil.getBonitaStudioWorkFolder());
			fos = new FileOutputStream(tmpFile);
			FileUtil.copy(is, fos);
			final ResourceSet rSet = new ResourceSetImpl();

			final Resource resource = rSet.createResource(URI.createFileURI(tmpFile.getAbsolutePath()));
			final Map<Object, Object> loadOptions = new HashMap<Object, Object>(rSet.getLoadOptions());
			//Ignore unknown features
			loadOptions.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
			resource.load(loadOptions);
			EObject mainProcess = resource.getContents().get(0);
			if(mainProcess instanceof MainProcess){
				return NamingUtils.toDiagramFilename(((MainProcess) mainProcess).getName(), ((MainProcess) mainProcess).getVersion());
			}
		}catch (Exception e) {
			BonitaStudioLog.error(e, Activator.PLUGIN_ID);
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e,Activator.PLUGIN_ID);
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e,Activator.PLUGIN_ID);
				}
			}
			if(tmpFile != null && tmpFile.exists()){
				tmpFile.delete();
			}
		}
		return fileName;
	}

	public Set<String> getAllProcessIds() {
		Set<String> resut = new HashSet<String>() ;
		for(AbstractProcess process : getAllProcesses()){
			resut.add(ModelHelper.getEObjectID(process)) ;
		}
		return resut;
	}

	public AbstractProcess getProcessByUUID(String processUUID) {
		for(AbstractProcess process : getAllProcesses()){
			final String id = ModelHelper.getEObjectID(process);
			if(processUUID.equals(id)){
				return process;
			}
		}
		return null;
	}

	@Override
	public AdapterFactoryLabelProvider getLabelProvider() {
		if(labelProvider != null){
			labelProvider.dispose();
		}
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ProcessAdapterFactory()) ;
		adapterFactory.addAdapterFactory(new NotationAdapterFactory()) ;
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		return labelProvider;
	}

	@Override
	protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
		adapterFactory.addAdapterFactory(new ProcessAdapterFactory()) ;
		adapterFactory.addAdapterFactory(new NotationAdapterFactory()) ;
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
		for(Release release : targetMigrator.getReleases()){
			for(EObject root : resource.getContents()){
				if(root instanceof MainProcess){
					String modelVersion = ((MainProcess) root).getBonitaModelVersion();
					if(release.getLabel().equals(modelVersion)){
						return release;
					}
				}
			}
		}
		return targetMigrator.getReleases().iterator().next(); //First release of all time
	}
}
