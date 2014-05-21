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
package org.bonitasoft.studio.connectors.configuration;

import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.eclipse.jface.viewers.ColumnLabelProvider;


/**
 * @author Romain Bioteau
 *
 */
public class ImplementationLabelProvider extends ColumnLabelProvider {


    private final IImplementationRepositoryStore implStore;

    public ImplementationLabelProvider(IImplementationRepositoryStore implStore) {
        this.implStore = implStore ;
    }

    @Override
    public String getText(Object element) {
        String id =   ((DefinitionMapping) element).getImplementationId() ;
        String version = ((DefinitionMapping) element).getImplementationVersion() ;
        ConnectorImplementation impl = implStore.getImplementation(id, version) ;
        if(impl != null){
            final String description = impl.getDescription() ;
            String desc = "" ;
            if(description != null && !description.isEmpty()){
                desc = " -- " + description ;
            }
            return impl.getImplementationId() +" (" +impl.getImplementationVersion()+")"+ desc ;
        }
        if(id != null){
            return id + " ("+version+")" ;
        }
        return "" ;
    }

}
