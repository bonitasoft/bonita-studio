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
package org.bonitasoft.studio.connectors.ui.wizard;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractDefinitionWizard;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;


/**
 * @author Romain Bioteau
 *
 */
public class ConnectorDefinitionWizard extends AbstractDefinitionWizard {

	public ConnectorDefinitionWizard(DefinitionResourceProvider messageProvider){
		super(Messages.newConnectorDefinition,RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class),messageProvider) ;
	}

	public ConnectorDefinitionWizard(ConnectorDefinition definition,DefinitionResourceProvider messageProvider){
		super(Messages.editConnectorDefinition,definition,RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class),messageProvider) ;
	}

}
