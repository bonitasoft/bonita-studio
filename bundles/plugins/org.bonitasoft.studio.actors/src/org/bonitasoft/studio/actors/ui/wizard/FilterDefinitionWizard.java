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
package org.bonitasoft.studio.actors.ui.wizard;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractDefinitionWizard;


/**
 * @author Romain Bioteau
 *
 */
public class FilterDefinitionWizard extends AbstractDefinitionWizard {


    public FilterDefinitionWizard(DefinitionResourceProvider messageProvider){
        super(Messages.newFilterDefinition,RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class),messageProvider) ;
    }

    public FilterDefinitionWizard(ConnectorDefinition definition,DefinitionResourceProvider messageProvider){
        super(Messages.editFilterDefinition,definition,RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class),messageProvider) ;
    }

    @Override
    protected void addOutputPage() {

    }
}
