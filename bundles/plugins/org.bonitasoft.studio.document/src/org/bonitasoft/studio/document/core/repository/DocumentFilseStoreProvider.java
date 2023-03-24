/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.document.core.repository;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;


/**
 * @author Romain Bioteau
 *
 */
public class DocumentFilseStoreProvider implements IBOSArchiveFileStoreProvider {

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.studio.model.process.AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore<?>> getFileStoreForConfiguration(final AbstractProcess process, final Configuration configuration) {
        final Set<IRepositoryFileStore<?>> result = new HashSet<>();
        final DocumentRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DocumentRepositoryStore.class);
        if(process instanceof Pool){
            for(final Document doc :  ((Pool) process).getDocuments()){
                if (doc.getDocumentType().equals(org.bonitasoft.studio.model.process.DocumentType.INTERNAL) && doc.getDefaultValueIdOfDocumentStore() != null
                        && !doc.getDefaultValueIdOfDocumentStore().isEmpty()) {
                    final DocumentFileStore fileStore = store.getChild(doc.getDefaultValueIdOfDocumentStore(), true);
                    if(fileStore != null){
                        result.add(fileStore);
                    }
                }
            }
        }
        return result;
    }

}
