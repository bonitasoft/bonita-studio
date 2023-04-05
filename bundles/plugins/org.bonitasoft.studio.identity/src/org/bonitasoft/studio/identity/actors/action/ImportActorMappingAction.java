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
package org.bonitasoft.studio.identity.actors.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.extension.IConfigurationImportAction;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.DocumentRoot;
import org.bonitasoft.studio.model.actormapping.util.ActorMappingResourceFactoryImpl;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

/**
 * @author Romain Bioteau
 *
 */
public class ImportActorMappingAction extends Action implements IConfigurationImportAction {

    private Configuration configuration;
    private String filePath;

    public ImportActorMappingAction(){
        super() ;
        setText(Messages.actorMapping) ;
        setImageDescriptor(Pics.getImageDescriptor(PicsConstants.actorMapping)) ;
    }

    @Override
    public void setProcess(AbstractProcess process) {
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration ;
    }

    @Override
    public void run() {
        if(filePath == null){
            FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN) ;
            dialog.setFilterExtensions(new String[]{"*.xml"}) ;
            filePath = dialog.open() ;
        }
        if(filePath != null){
            URI uri = URI.createFileURI(filePath) ;
            Resource resource = new ActorMappingResourceFactoryImpl().createResource(uri) ;
            Map<String, String> options = new HashMap<String, String>() ;
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            try {
                resource.load(options) ;
            } catch (IOException e) {
                BonitaStudioLog.error(e) ;
            }
            if(!resource.getContents().isEmpty()){
                EObject root = resource.getContents().get(0);
                if(root instanceof DocumentRoot){
                    ActorMappingsType mappings = ((DocumentRoot) root).getActorMappings() ;
                    for(ActorMapping mapping : mappings.getActorMapping()){
                        for(ActorMapping configurationMapping : configuration.getActorMappings().getActorMapping()){
                            if(configurationMapping.getName().equals(mapping.getName())){
                                configurationMapping.setGroups(mapping.getGroups()) ;
                                configurationMapping.setRoles(mapping.getRoles()) ;
                                configurationMapping.setUsers(mapping.getUsers()) ;
                                configurationMapping.setMemberships(mapping.getMemberships()) ;
                            }
                        }
                    }
                }
            }

        }

    }

    @Override
    public void setFilePath(String absolutePath) {
        filePath = absolutePath;
    }


}
