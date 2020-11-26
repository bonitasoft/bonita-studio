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
package org.bonitasoft.studio.actors.repository;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ExportConnectorArchiveOperation;
import org.osgi.framework.Bundle;


/**
 * @author Romain Bioteau
 *
 */
public class ExportActorFilterArchiveOperation extends ExportConnectorArchiveOperation {

    public static final String FILTER_TYPE = "filter";


    @Override
    protected IRepositoryStore<?> getImplementationStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ActorFilterImplRepositoryStore.class);
    }



    @Override
    protected IRepositoryStore<?> getDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
    }


    @Override
    protected SourceRepositoryStore<?> getSourceStore() {
        return (SourceRepositoryStore<?>) RepositoryManager.getInstance().getRepositoryStore(ActorFilterSourceRepositoryStore.class);
    }


    @Override
    protected String getArchiveType() {
        return FILTER_TYPE;
    }

    @Override
    protected Bundle getBundle() {
        return ActorsPlugin.getDefault().getBundle() ;
    }

}
