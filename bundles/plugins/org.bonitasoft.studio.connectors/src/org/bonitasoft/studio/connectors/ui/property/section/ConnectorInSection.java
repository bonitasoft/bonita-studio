/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.ui.property.section;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigratorFactory;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationToConnectorDefinitionConverter;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorWizard;
import org.eclipse.jface.viewers.ViewerFilter;

public class ConnectorInSection extends ConnectorSection {

    @Inject
    public ConnectorInSection(ConnectorConfigurationMigratorFactory migrationFactory,
            ConnectorConfigurationToConnectorDefinitionConverter configurationToDefinitionConverter) {
        super(migrationFactory, configurationToDefinitionConverter);
    }

    @Override
    protected ConnectorWizard createAddConnectorWizard() {
        return createConnectorWizard(ConnectorEvent.ON_ENTER.name());
    }

    @Override
    protected ViewerFilter getViewerFilter() {
        return new ConnectorEventFilter(ConnectorEvent.ON_ENTER.name());
    }

}
