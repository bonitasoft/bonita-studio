/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.configuration;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;

/**
 * @author Romain Bioteau
 *
 */
public class EnvironmentResourceProvider implements	IBOSArchiveFileStoreProvider {

	@Override
	public Set<IRepositoryFileStore<?>> getFileStoreForConfiguration(AbstractProcess process, Configuration configuration) {
		final Set<IRepositoryFileStore<?>> files = new HashSet<>();
		final EnvironmentRepositoryStore store =  (EnvironmentRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(EnvironmentRepositoryStore.class);
		for(Configuration conf : process.getConfigurations()){
			final IRepositoryFileStore<?> fileStore = store.getChild(conf.getName()+"."+EnvironmentRepositoryStore.ENV_EXT, true);
			if(fileStore != null){
				files.add(fileStore);
			} else {
				BonitaStudioLog.log("An environment has been defined for Pool but is not available in repository: "+conf.getName());
			}
		}
		return files;
	}

}
