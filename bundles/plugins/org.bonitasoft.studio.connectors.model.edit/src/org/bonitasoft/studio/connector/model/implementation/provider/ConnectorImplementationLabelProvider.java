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
package org.bonitasoft.studio.connector.model.implementation.provider;

import java.util.List;

import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;


/**
 * @author Romain Bioteau
 *
 */
public class ConnectorImplementationLabelProvider extends LabelProvider {

    private final IDefinitionRepositoryStore store;
    private final DefinitionResourceProvider messageProvider;
    private final List<ConnectorDefinition> definitions;

    @SuppressWarnings("unchecked")
	public ConnectorImplementationLabelProvider(IDefinitionRepositoryStore store,Bundle bundle){
        this.store = store ;
        messageProvider = DefinitionResourceProvider.getInstance((IRepositoryStore<? extends IRepositoryFileStore>) store, bundle) ;
        definitions = store.getDefinitions() ;
    }

    @Override
    public String getText(Object element) {
        ConnectorImplementation impl = (ConnectorImplementation) element ;
        StringBuilder sb = new StringBuilder(impl.getImplementationId());
        if(impl.getImplementationVersion() != null && !impl.getImplementationVersion().isEmpty()){
            sb.append(" ("+impl.getImplementationVersion()+")");
        }
        if(impl.getImplementationClassname() != null && !impl.getImplementationClassname().isEmpty()){
            sb.append(" -- "+ impl.getImplementationClassname());
        }
        return sb.toString();
    }

    @Override
    public Image getImage(Object element) {
        ConnectorImplementation impl = (ConnectorImplementation) element ;
        String defId = impl.getDefinitionId() ;
        String defVerison = impl.getDefinitionVersion() ;
        ConnectorDefinition definition = store.getDefinition(defId, defVerison,definitions) ;
        return messageProvider.getDefinitionIcon(definition);
    }

}
