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
package org.bonitasoft.studio.dependencies.provider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;


/**
 * @author Romain Bioteau
 *
 */
public class JarBOSArchiveResourceProvider implements IBOSArchiveFileStoreProvider {

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.studio.model.process.AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore> getFileStoreForConfiguration(AbstractProcess process, Configuration configuration) {
        final Set<IRepositoryFileStore> files = new HashSet<IRepositoryFileStore>() ;
        final DependencyRepositoryStore store = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
        for (FragmentContainer fc : configuration.getProcessDependencies()) {
            List<Fragment> fragments = ModelHelper.getAllItemsOfType(fc, ConfigurationPackage.Literals.FRAGMENT) ;
            for (Fragment fragment : fragments) {
                if(fragment.getType().equals(FragmentTypes.JAR)) {
                    if(fragment.isExported()){
                        IRepositoryFileStore jarArtifact = store.getChild(fragment.getValue(), true) ;
                        if(jarArtifact != null){
                            files.add(jarArtifact) ;
                        }
                    }
                }
            }
        }
        for (FragmentContainer fc : configuration.getApplicationDependencies()) {
            List<Fragment> fragments = ModelHelper.getAllItemsOfType(fc, ConfigurationPackage.Literals.FRAGMENT) ;
            for (Fragment fragment : fragments) {
                if(fragment.getType().equals(FragmentTypes.JAR)) {
                    if(fragment.isExported()){
                        IRepositoryFileStore jarArtifact = store.getChild(fragment.getValue(), true) ;
                        if(jarArtifact != null){
                            files.add(jarArtifact) ;
                        }
                    }
                }
            }
        }

        return files;
    }

}
