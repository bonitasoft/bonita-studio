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
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.extension.IConfigurationExportAction;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ExportConnectorArchiveOperation;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.Action;

/**
 * @author Romain Bioteau
 *
 */
public class ExportConnectorAction extends Action implements IConfigurationExportAction {

    private Configuration configuration;
    private String path;
    public ExportConnectorAction(){
        super() ;
        setText(Messages.connectors) ;
        setImageDescriptor(Pics.getImageDescriptor(PicsConstants.connector)) ;
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration ;
    }

    @Override
    public void setProcess(AbstractProcess process) {
    }

    @Override
    public void run() {
        if(path != null){

            final File destDir = new File(path,"connectors");
            if(!destDir.exists()){
                destDir.mkdirs() ;
            }

            ConnectorImplRepositoryStore implStore = (ConnectorImplRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
            for(DefinitionMapping mapping: configuration.getDefinitionMappings()){
                if(mapping.getType().equals(FragmentTypes.CONNECTOR) && mapping.getImplementationId() != null){
                    ExportConnectorArchiveOperation op =  new ExportConnectorArchiveOperation() ;
                    op.setAddDependencies(true) ;

                    FragmentContainer container = getConnectorFragmentContainer(configuration) ;
                    Set<String> ignoredLibs = new HashSet<String>() ;
                    for(FragmentContainer fc : container.getChildren()){
                        if(fc.getId().equals(mapping.getImplementationId() +"-"+ mapping.getImplementationVersion())){
                            for(Fragment f: fc.getFragments()){
                                if(!f.isExported()){
                                    ignoredLibs.add(f.getValue()) ;
                                }
                            }
                        }
                    }
                    op.addIgnoredDependencies(ignoredLibs) ;
                    op.setImplementation(implStore.getImplementation(mapping.getImplementationId(), mapping.getImplementationVersion())) ;
                    String fileName = destDir.getAbsolutePath()+File.separator+mapping.getImplementationId() +"--"+ mapping.getImplementationVersion()+".zip" ;
                    File targetFile = new File(fileName);
                    if(targetFile.exists()){
                        if(FileActionDialog.overwriteQuestion(fileName)){
                            targetFile.delete() ;
                        }else{
                            continue ;
                        }
                    }

                    op.setTargetPath(fileName) ;
                    op.setIncludeSources(false) ;
                    op.run(AbstractRepository.NULL_PROGRESS_MONITOR) ;
                }
            }

        }

    }

    private FragmentContainer getConnectorFragmentContainer(Configuration configuration) {
        for(FragmentContainer container: configuration.getProcessDependencies()){
            if(container.getId().equals(FragmentTypes.CONNECTOR)){
                return container ;
            }
        }
        return null;
    }

    @Override
    public void setTargetPath(String path) {
        this.path = path ;
    }

}
