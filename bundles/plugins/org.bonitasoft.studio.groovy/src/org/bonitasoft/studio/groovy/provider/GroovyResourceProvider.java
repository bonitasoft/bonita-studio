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
package org.bonitasoft.studio.groovy.provider;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;


/**
 * @author Romain Bioteau
 *
 */
public class GroovyResourceProvider implements IBOSArchiveFileStoreProvider {

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.studio.model.process.AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore> getFileStoreForConfiguration(AbstractProcess process, Configuration configuration) {
        final Set<IRepositoryFileStore> files = new HashSet<IRepositoryFileStore>() ;

        final GroovyRepositoryStore groovySotre = (GroovyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class) ;
        addScriptFile(files, groovySotre, getProcessContainer(configuration));
        addScriptFile(files, groovySotre, getApplicationContainer(configuration));

        return files;
    }

    protected void addScriptFile(final Set<IRepositoryFileStore> files, final GroovyRepositoryStore groovySotre, FragmentContainer groovyContainer) {
        for(Fragment script : groovyContainer.getFragments()){
            GroovyFileStore file = groovySotre.getChild(script.getValue(), true) ;
            if(script.isExported() && file != null && file.canBeShared()){
                files.add(file) ;
            }
        }
    }

    private FragmentContainer getProcessContainer(Configuration configuration) {
        for(FragmentContainer container: configuration.getProcessDependencies()){
            if(container.getId().equals(FragmentTypes.GROOVY_SCRIPT)){
                return container ;
            }
        }
        return null;
    }

    private FragmentContainer getApplicationContainer(Configuration configuration) {
        for(FragmentContainer container: configuration.getApplicationDependencies()){
            if(container.getId().equals(FragmentTypes.GROOVY_SCRIPT)){
                return container ;
            }
        }
        return null;
    }
}
