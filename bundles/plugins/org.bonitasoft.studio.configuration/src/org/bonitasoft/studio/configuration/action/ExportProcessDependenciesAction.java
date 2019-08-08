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
package org.bonitasoft.studio.configuration.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.extension.IConfigurationExportAction;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ExportProcessDependenciesAction extends Action implements IConfigurationExportAction {

    private Configuration configuration;
    private String path;

    public ExportProcessDependenciesAction(){
        super() ;
        setText(Messages.javaDependencies);
        setImageDescriptor(Pics.getImageDescriptor(PicsConstants.dependencies)) ;
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration  = configuration ;
    }

    @Override
    public void setProcess(AbstractProcess process) {
    }

    @Override
    public void run() {
        if(path != null){

            File destDir = new File(path,"process_classpath");
            if(!destDir.exists()){
                destDir.mkdirs() ;
            }

            DependencyRepositoryStore depStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
            for(FragmentContainer container: configuration.getProcessDependencies()){
                List<Fragment> fragments = ModelHelper.getAllItemsOfType(container, ConfigurationPackage.Literals.FRAGMENT) ;
                for(Fragment f : fragments){
                    if(f.isExported() && f.getType().equals(FragmentTypes.JAR)){
                        IRepositoryFileStore fileStore = depStore.getChild(f.getValue()) ;
                        if(fileStore != null){
                            try {
								fileStore.export(destDir.getAbsolutePath()) ;
							} catch (IOException e) {
								MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailedTitle, e.getMessage());
							}
                        }
                    }
                }
            }

        }
    }

    @Override
    public void setTargetPath(String path) {
        this.path = path ;
    }


}
