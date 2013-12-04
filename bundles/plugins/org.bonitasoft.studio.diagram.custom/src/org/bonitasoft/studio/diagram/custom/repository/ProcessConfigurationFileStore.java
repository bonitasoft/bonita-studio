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

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class ProcessConfigurationFileStore extends EMFFileStore implements IRepositoryFileStore {

    public ProcessConfigurationFileStore(String folderName, IRepositoryStore store) {
        super(folderName, store);
    }

    @Override
    public Configuration getContent() {
        Resource resource = getEMFResource() ;
        if(!resource.isLoaded()){
            try {
                resource.load(Collections.EMPTY_MAP) ;
            } catch (IOException e) {
                BonitaStudioLog.error(e) ;
            }
        }
        if(!resource.getContents().isEmpty()){
            return (Configuration) resource.getContents().get(0) ;
        }
        return null ;
    }

    @Override
    public boolean isShared() {
        return false;
    }


    @Override
    protected void doSave(Object content) {
        Resource resource = getEMFResource() ;
        if(content instanceof Configuration){
            resource.getContents().clear() ;
            resource.getContents().add(EcoreUtil.copy((Configuration)content)) ;
        }
        try {
            Map<String, String> options = new HashMap<String, String>() ;
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            resource.save(options) ;
            resource.unload() ;
           
           
        } catch (IOException e) {
            BonitaStudioLog.error(e) ;
        }
    }


    @Override
    protected IWorkbenchPart doOpen() {
        return null ;
    }


    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }
    


    @Override
    public String getDisplayName() {
        Configuration conf =  getContent() ;
        final DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class) ;
        AbstractProcess process = null ;
        String confId = getName().substring(0,getName().lastIndexOf(".")) ;
        for(AbstractProcess p : diagramStore.getAllProcesses()){
            String pId =  ModelHelper.getEObjectID(p) ;
            if(confId.equals(pId)){
                process = p ;
                break ;
            }
        }
        String processName ="" ;
        if(process != null){
            processName = process.getName() +" ("+process.getVersion()+")";
        }
        String confName = conf.getName() ;
        if(confName == null){
            confName = "Local" ;
        }
        return Messages.bind(Messages.configuration, confName,processName) ;
    }

    @Override
    public Image getIcon() {
        return getParentStore().getIcon() ;
    }
    
   
    
    
    

}
