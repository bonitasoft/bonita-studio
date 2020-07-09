/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.repository;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.emf.common.util.URI;

/**
 * @author Romain Bioteau
 */
public class DiagramFileStoreBOSArchiveProvider implements IBOSArchiveFileStoreProvider {

    @Inject
    private RepositoryAccessor repositoryAccessor;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.studio.model.process.
     * AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore<?>> getFileStoreForConfiguration(final AbstractProcess process, final Configuration configuration) {
        final Set<IRepositoryFileStore<?>> files = new HashSet<>();
        final DiagramRepositoryStore diagramSotre = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        final String diagramFileName = URI.decode(process.eResource().getURI().lastSegment());
        final IRepositoryFileStore diagram = diagramSotre.getChild(diagramFileName, true);
        if (diagram != null) {
            files.add(diagram);
        }
        return files;
    }

}
