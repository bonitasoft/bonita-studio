/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.studio.application.views.ProjectExplorerViewerComparator;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ITenantResource;

public class TenantArtifact extends FileStoreArtifact implements ITenantResource, Comparable<TenantArtifact> {

    public TenantArtifact(RepositoryStore parent, IRepositoryFileStore fStore) {
        super(parent, fStore);
    }

    @Override
    public int compareTo(TenantArtifact artifact) {
        return Integer.compare(ProjectExplorerViewerComparator.REPO_STORE_ORDER.get(((RepositoryStore) getParent()).getName()), ProjectExplorerViewerComparator.REPO_STORE_ORDER.get(((RepositoryStore) artifact.getParent()).getName()));
    }
    
}
