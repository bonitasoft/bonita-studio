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
package org.bonitasoft.studio.validators.provider;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.configuration.extension.IConfigurationExportAction;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorFileStore;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ExportValidatorAction extends Action implements IConfigurationExportAction {

    private Configuration configuration;
    private String path;

    public ExportValidatorAction(){
        super() ;
        setText(Messages.validators) ;
        setImageDescriptor(Pics.getImageDescriptor(PicsConstants.validator)) ;
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
            exportValidatorJar(path) ;
        }
    }

    private void exportValidatorJar(String filePath) {
        File destDir = new File(path,"application_classpath");
        if(!destDir.exists()){
            destDir.mkdirs() ;
        }
        final ValidatorDescriptorRepositoryStore defStore = (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class);
        final ValidatorSourceRepositorySotre sourceStore = (ValidatorSourceRepositorySotre) RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class);
        FragmentContainer validatorContainers = getContainer(configuration) ;
        for(FragmentContainer validator: validatorContainers.getChildren()){
            String id = validator.getId() ;
            ValidatorDescriptorFileStore file = (ValidatorDescriptorFileStore) defStore.getChild(id+"."+ValidatorDescriptorRepositoryStore.VALIDATOR_EXT) ;
            if(file != null){
                String className = file.getContent().getClassName() ;
                if(className != null){
                    SourceFileStore sourceFile = (SourceFileStore) sourceStore.getChild(className) ;
                    if(sourceFile != null){
                        File targetFile = new File(destDir,sourceFile.getName() + ".jar");
                        try {
							sourceFile.exportAsJar(targetFile.getAbsolutePath(),true) ;
						} catch (InvocationTargetException e) {
							MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailedTitle, e.getMessage());
						} catch (InterruptedException e) {
							MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailedTitle, e.getMessage());
						}
                    }
                }
            }
        }
    }

    private FragmentContainer getContainer(Configuration configuration) {
        for(FragmentContainer fc : configuration.getApplicationDependencies()){
            if(fc.getId().equals(FragmentTypes.VALIDATOR)){
                return fc ;
            }
        }
        return null;
    }

    @Override
    public void setTargetPath(String path) {
        this.path = path ;
    }

}
