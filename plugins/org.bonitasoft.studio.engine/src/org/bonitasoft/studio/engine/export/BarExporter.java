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
package org.bonitasoft.studio.engine.export;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.model.DesignProcessDefinition;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.gmf.tools.CopyToImageUtilEx;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.DocumentRoot;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.actormapping.util.ActorMappingResourceFactoryImpl;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;


/**
 * @author Romain Bioteau
 *
 */
public class BarExporter {

    private static final String PROCESS_DEFINITION_EXPORTER_ID = "org.bonitasoft.studio.engine.processDefinitionExporter";
    private static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();
    private static final String BAR_RESOURCE_PROVIDERS_EXTENSION_POINT = "org.bonitasoft.studio.common.barResourcesProvider";
    private static final String BAR_APPLICATION_RESOURCE_PROVIDERS_EXTENSION_POINT = "org.bonitasoft.studio.exporter.barApplicationResourceProvider";


    private static BarExporter INSTANCE;


    private BarExporter(){

    }

    public static BarExporter getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BarExporter() ;
        }
        return INSTANCE ;
    }

    public BusinessArchive createBusinessArchive(final AbstractProcess process,final Configuration configuration,final Set<EObject> excludedObject) throws Exception {
        BonitaStudioLog.info("Building bar for process " + process.getName() +" ("+process.getVersion()+" )...",EnginePlugin.PLUGIN_ID);

        final DesignProcessDefinitionBuilder procBuilder = getProcessDefinitionBuilder();
        procBuilder.seteObjectNotExported(excludedObject);
        final DesignProcessDefinition def = procBuilder.createDefinition(process);

        if(def == null){
            throw new Exception(Messages.cantDeployEmptyPool);
        }

        final BusinessArchiveBuilder builder = new BusinessArchiveBuilder().createNewBusinessArchive() ;
        builder.setProcessDefinition(def) ;

        if(configuration != null){
            builder.setParameters(getParameterMapFromConfiguration(configuration)) ;
            final byte[] content = getActorMappingContent(configuration) ;
            if(content != null){
                builder.setActorMapping(content) ;
            }
        }

        for (BARResourcesProvider resourceProvider : getBARResourcesProvider()) {
            resourceProvider.addResourcesForConfiguration(builder,process,configuration);
        }

        //Add forms resources
        BARResourcesProvider provider = getBARApplicationResourcesProvider();
        if(provider != null){
            provider.addResourcesForConfiguration(builder,process,configuration);

        }

        if(!(process instanceof SubProcessEvent)){
            Display.getDefault().syncExec(new Runnable() {
                @Override
                public void run() {
                    try {
                        addProcessImage(builder,process);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            });
        }

        final BusinessArchive archive = builder.done();
        BonitaStudioLog.info("Build complete for process " + process.getName() +" ("+process.getVersion()+" ).",EnginePlugin.PLUGIN_ID);
        return archive;
    }

    public DesignProcessDefinitionBuilder getProcessDefinitionBuilder() {
        for(IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(PROCESS_DEFINITION_EXPORTER_ID)){
            try {
                return (DesignProcessDefinitionBuilder) element.createExecutableExtension("class");
            } catch (CoreException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }
        return new DesignProcessDefinitionBuilder();
    }

    /**
     * @param process The pool to export
     * @param configurationId
     * @param excludedObject elements of the process not exported in process definition
     */
    public BusinessArchive createBusinessArchive(final AbstractProcess process,final String configurationId,final Set<EObject> excludedObject) throws Exception {
        Configuration configuration = null ;
        if(configurationId != null){
            configuration = getConfiguration(process, configurationId);
        }
        return createBusinessArchive(process, configuration, excludedObject);
    }

    public byte[] getActorMappingContent(Configuration configuration) throws Exception {
        if(configuration.getActorMappings() != null && configuration.getActorMappings().getActorMapping() != null && !configuration.getActorMappings().getActorMapping().isEmpty()){
    		
        	StringBuffer result = new StringBuffer("");
        	for(ActorMapping mapping : configuration.getActorMappings().getActorMapping()){
        		if(!checkActorMappingGroup(mapping)){
        			result.append("- "+mapping.getName()+"\n");
        		}
        	}
        	if(result.length()>0){
        		throw new Exception(Messages.errorActorMappingGroup+" : \n"+ result.toString());
        	}
        	
            org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createFileURI(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath()+File.separatorChar+EcoreUtil.generateUUID()+".xml") ;
            Resource resource = new ActorMappingResourceFactoryImpl().createResource(uri) ;
            DocumentRoot root = ActorMappingFactory.eINSTANCE.createDocumentRoot() ;
            ActorMappingsType mapping = EcoreUtil.copy(configuration.getActorMappings()) ;
            cleanMapping(mapping) ;
            root.setActorMappings(mapping);
            resource.getContents().add(root) ;
            Map<String, String> options = new HashMap<String, String>() ;
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            try {
                resource.save(options) ;
                String content = new XMLProcessor().saveToString(resource, options) ;
                resource.delete(Collections.EMPTY_MAP) ;
                return content.getBytes();
            } catch (IOException e) {
                new BonitaErrorDialog(Display.getDefault().getActiveShell(), "Export error", "An error occured during export", e).open() ;
            }
        }
        return null ;
    }

    private void cleanMapping(ActorMappingsType mapping) {
        for(ActorMapping m : mapping.getActorMapping()){
            final Groups groups = m.getGroups();
			if(groups != null && groups.getGroup().isEmpty()){
                m.setGroups(null) ;
            }
            final Membership memberships = m.getMemberships();
			if(memberships != null && memberships.getMembership().isEmpty()){
                m.setMemberships(null) ;
            }
            final Roles roles = m.getRoles();
			if(roles != null && roles.getRole().isEmpty()){
                m.setRoles(null) ;
            }
            final Users users = m.getUsers();
			if(users != null && users.getUser().isEmpty()){
                m.setUsers(null) ;
            }
        }
    }

    public Configuration getConfiguration(final AbstractProcess process,String configurationId) {
        Configuration configuration = null ;
        final ProcessConfigurationRepositoryStore processConfStore = (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class) ;
        if(configurationId == null){
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore().getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION) ;
        }
        if(configurationId.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)){
            String id = ModelHelper.getEObjectID(process) ;
            IRepositoryFileStore file = processConfStore.getChild(id+".conf") ;
            if(file == null){
            	file = processConfStore.createRepositoryFileStore(id+".conf");
            	 configuration = ConfigurationFactory.eINSTANCE.createConfiguration() ;
                 configuration.setName(configurationId) ;
                 configuration.setVersion(ProductVersion.CURRENT_VERSION);
                 file.save(configuration);
            }
            configuration = (Configuration) file.getContent();
        }else{
            for(Configuration conf : process.getConfigurations()){
                if(configurationId.equals(conf.getName())){
                    configuration = conf ;
                }
            }
        }
        if(configuration == null){
            configuration = ConfigurationFactory.eINSTANCE.createConfiguration() ;
            configuration.setName(configurationId) ;
            configuration.setVersion(ProductVersion.CURRENT_VERSION);
        }
        //Synchronize configuration with definition
        new ConfigurationSynchronizer(process, configuration).synchronize() ;
        return configuration ;
    }



    public Map<String, String> getParameterMapFromConfiguration(final Configuration configuration) {
        Map<String, String> result = new HashMap<String, String>() ;
        for(Parameter p : configuration.getParameters()){
            if(p.getValue() != null){
                result.put(p.getName(),p.getValue()) ;
            }
        }
        return result;
    }

    private BARResourcesProvider getBARApplicationResourcesProvider() {
        BARResourcesProvider result = null ;
        int maxPriority = -1 ;
        IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(BAR_APPLICATION_RESOURCE_PROVIDERS_EXTENSION_POINT);
        for (IConfigurationElement extension : extensions) {

            try {
                int p = Integer.parseInt(extension.getAttribute("priority"));
                if(p >= maxPriority){
                    result = (BARResourcesProvider)extension.createExecutableExtension("providerClass");
                    maxPriority = p ;
                }
            } catch (Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
        return result;
    }


    public List<BARResourcesProvider> getBARResourcesProvider() {
        List<BARResourcesProvider> res = new ArrayList<BARResourcesProvider>();
        IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(BAR_RESOURCE_PROVIDERS_EXTENSION_POINT);
        for (IConfigurationElement extension : extensions) {
            try {
                res.add((BARResourcesProvider)extension.createExecutableExtension("providerClass"));
            } catch (Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
        return res;
    }

    private void addProcessImage(BusinessArchiveBuilder builder, final AbstractProcess process) throws CoreException {
        if(PlatformUI.isWorkbenchRunning()){
            final String processName = process.getName()+"_"+process.getVersion();
            final String path = TMP_DIR + File.separatorChar + processName +".png" ; //$NON-NLS-1$

            try {
                Diagram diagram = ModelHelper.getDiagramFor(ModelHelper.getMainProcess(process),null);
                if(diagram == null){
                    return;//DON'T ADD IMAGE, DON'T THROW EXCEPTION FOR TESTS PURPUSES
                }
                ResourceSet resourceSet = new ResourceSetImpl();
                TransactionalEditingDomain editingDomain  = GMFEditingDomainFactory.getInstance().createEditingDomain(resourceSet);
                Resource resource = resourceSet.createResource(diagram.eResource().getURI());
                try {
                    resource.load(resourceSet.getLoadOptions());
                } catch (IOException e1) {
                    BonitaStudioLog.error(e1);
                }
                diagram = (Diagram) resource.getEObject(diagram.eResource().getURIFragment(diagram));
                CopyToImageUtilEx copyToImageUtil = new CopyToImageUtilEx();
                ByteArrayInputStream stream = null;
                try {
                    if(!new File(TMP_DIR).exists()){
                        new File(TMP_DIR).mkdir() ;
                    }
                    File newFile = new File(TMP_DIR, processName +".png");
                    newFile.delete();

                    byte[] imageBytes = copyToImageUtil.copyToImageByteArray(diagram, process,800, 800, ImageFileFormat.PNG, new NullProgressMonitor(), new PreferencesHint("exportToImage"), true) ;
                    stream = new ByteArrayInputStream(imageBytes);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    FileUtil.copy(stream, fos);
                    fos.close();
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                    return ;
                } finally {
                    if(stream != null){
                        try {
                            stream.close();
                        } catch (IOException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                    editingDomain.dispose() ;
                }


            }catch (Exception e) {
                BonitaStudioLog.error(e);
            }


            final File imgFile = new File(path);
            if(imgFile.exists()){
                try {
                    builder.addExternalResource(createBarResource(imgFile,""));
                } catch (Exception e) {
                    BonitaStudioLog.log("Process image file generation has failed"); //$NON-NLS-1$
                } finally{
                    PlatformUtil.delete(imgFile, Repository.NULL_PROGRESS_MONITOR);
                }
            }else{
                BonitaStudioLog.log("Process image file generation has failed"); //$NON-NLS-1$
            }
        }
    }

    /**
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     * @return a BarResource
     */
    private BarResource createBarResource(final File file,String barPathPrefix) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            throw new  FileNotFoundException(file.getName());
        }
        byte[] jarBytes = new byte[(int) file.length()];
        InputStream stream = new FileInputStream(file);
        stream.read(jarBytes);
        stream.close();
        if(barPathPrefix != null && !barPathPrefix.isEmpty() && !barPathPrefix.endsWith("/")){
            barPathPrefix = barPathPrefix+"/" ;
        }
        return new BarResource(barPathPrefix+file.getName(), jarBytes);
    }
    
    private boolean checkActorMappingGroup(ActorMapping mapping){
    	
    	List<String> list1 = mapping.getGroups().getGroup();
    	List<String> list2 = mapping.getGroups().getGroup();
    	
    	for(String s1 : list1){
			for(String s2 : list2){
				if(!s1.equals(s2) && (s2.startsWith(s1) || s1.startsWith(s2)) ){
					return false;
				}
			}
		}
		return true;
    }
}
