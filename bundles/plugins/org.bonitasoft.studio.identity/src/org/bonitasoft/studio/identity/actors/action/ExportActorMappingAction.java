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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.configuration.extension.IConfigurationExportAction;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.DocumentRoot;
import org.bonitasoft.studio.model.actormapping.util.ActorMappingResourceFactoryImpl;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

/**
 * @author Romain Bioteau
 *
 */
public class ExportActorMappingAction extends Action implements IConfigurationExportAction {

    private Configuration configuration;
    private AbstractProcess process;
    private String path;

    public ExportActorMappingAction(){
        super() ;
        setText(Messages.actorMapping) ;
        setImageDescriptor(Pics.getImageDescriptor(PicsConstants.actorMapping)) ;
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration ;
    }

    @Override
    public void setProcess(AbstractProcess process) {
        this.process = process ;
    }

    @Override
    public void run() {
        if(path == null){
            FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.SAVE) ;
            dialog.setFileName(process.getName()+"_"+process.getVersion()+"_"+Messages.mapping+".xml") ;
            dialog.setFilterExtensions(new String[]{"*.xml"}) ;
            String filePath = dialog.open() ;
            if(filePath != null){
                exportActorMapping(filePath) ;
            }
        }else{
            exportActorMapping(path+File.separator+"actorMapping.xml") ;
        }
    }

    private void exportActorMapping(String filePath) {
        boolean overwrite = true ;
        if(new File(filePath).exists()){
            if(!FileActionDialog.overwriteQuestion(filePath)){
                overwrite = false ;
            }
        }
        if(overwrite){
            URI uri = URI.createFileURI(filePath) ;
            Resource resource = new ActorMappingResourceFactoryImpl().createResource(uri) ;
            DocumentRoot root = ActorMappingFactory.eINSTANCE.createDocumentRoot() ;
            root.setActorMappings(EcoreUtil.copy(configuration.getActorMappings()));
            resource.getContents().add(root) ;
            Map<String, String> options = new HashMap<String, String>() ;
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            try {
                resource.save(options) ;
            } catch (IOException e) {
                new BonitaErrorDialog(Display.getDefault().getActiveShell(), "Export error", "An error occured during export", e).open() ;
            }
        }

    }

    @Override
    public void setTargetPath(String path) {
        this.path = path ;
    }

}
