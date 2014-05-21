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

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorFileStore;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;


/**
 * @author Romain Bioteau
 *
 */
public class ValidatorResourceProvider implements IBOSArchiveFileStoreProvider {

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.studio.model.process.AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore> getFileStoreForConfiguration(AbstractProcess process, Configuration configuration) {
        final Set<IRepositoryFileStore> files = new HashSet<IRepositoryFileStore>() ;

        final ValidatorDescriptorRepositoryStore validatorDefSotre = (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;
        final ValidatorSourceRepositorySotre validatorSourceSotre = (ValidatorSourceRepositorySotre) RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class) ;
        final DependencyRepositoryStore depStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
        FragmentContainer validatorContainer = getContainer(configuration) ;
        for(FragmentContainer validator : validatorContainer.getChildren()){
            String validatorId = validator.getId() ;
            ValidatorDescriptorFileStore defFile = (ValidatorDescriptorFileStore) validatorDefSotre.getChild(validatorId+"."+ValidatorDescriptorRepositoryStore.VALIDATOR_EXT) ;
            if(defFile != null && defFile.canBeShared()){
                files.add(defFile) ;

                ValidatorDescriptor vd = defFile.getContent() ;
                String className = vd.getClassName() ;
                String packageName = className.substring(0, className.lastIndexOf(".")) ;
                IRepositoryFileStore packageFileStore = validatorSourceSotre.getChild(packageName) ;
                if(packageFileStore != null){
                    files.add(packageFileStore) ;
                }

                for(String jarName :  vd.getDependencies()){
                    IRepositoryFileStore jarFile =  depStore.getChild(jarName) ;
                    if(jarFile != null){
                        files.add(jarFile) ;
                    }
                }
            }


        }

        return files;
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
