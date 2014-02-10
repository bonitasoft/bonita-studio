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
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorFileStore;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class ValidatorBarResourceProvider implements BARResourcesProvider {


    @Override
    public List<BarResource> addResourcesForConfiguration(BusinessArchiveBuilder builder, AbstractProcess process, Configuration configuration,Set<EObject> exludedObject) {
        final List<BarResource> resources = new ArrayList<BarResource>() ;
        if(configuration == null){
            return resources ;
        }
        final DependencyRepositoryStore store = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
        final ValidatorDescriptorRepositoryStore validatorDescStore = (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;
        final ValidatorSourceRepositorySotre validatorSourceStore = (ValidatorSourceRepositorySotre) RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class) ;
        FragmentContainer validatorContainer = getContainer(configuration) ;
        for(FragmentContainer validator :  validatorContainer.getChildren()){

            String validatorId = validator.getId() ;
            ValidatorDescriptorFileStore defFile = (ValidatorDescriptorFileStore) validatorDescStore.getChild(validatorId+"."+ValidatorDescriptorRepositoryStore.VALIDATOR_EXT) ;
            if(defFile == null){
                throw new RuntimeException("Validator descriptor not found !") ;
            }
            ValidatorDescriptor descriptor = defFile.getContent() ;
            if(defFile != null && defFile.canBeShared()){
                SourceFileStore file = (SourceFileStore) validatorSourceStore.getChild(descriptor.getClassName()) ;
                if(file == null){
                    throw new RuntimeException("Validator class not found !") ;
                }

                try{
                    File tmpFile = File.createTempFile(file.getName(), ".jar", ProjectUtil.getBonitaStudioWorkFolder());
                    tmpFile.deleteOnExit();
                    file.exportAsJar(tmpFile.getAbsolutePath(),true) ;
                    FileInputStream fis = new FileInputStream(tmpFile) ;
                    tmpFile.delete();
                    byte[] content = new byte[fis.available()] ;
                    fis.read(content) ;
                    fis.close() ;
                    resources.add(new BarResource(ValidatorSourceRepositorySotre.VALIDATOR_PATH_IN_BAR+descriptor.getClassName()+".jar", content)) ;

                }catch (Exception e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }

        for(BarResource barResource : resources){
            builder.addExternalResource(barResource) ;
        }

        return resources;
    }

    private FragmentContainer getContainer(Configuration configuration) {
        for(FragmentContainer container: configuration.getApplicationDependencies()){
            if(container.getId().equals(FragmentTypes.VALIDATOR)){
                return container ;
            }
        }
        return null;
    }

}
