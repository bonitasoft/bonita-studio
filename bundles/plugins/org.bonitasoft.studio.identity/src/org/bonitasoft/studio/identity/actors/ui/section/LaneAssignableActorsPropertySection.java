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
package org.bonitasoft.studio.identity.actors.ui.section;

import javax.inject.Inject;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigratorFactory;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationToConnectorDefinitionConverter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class LaneAssignableActorsPropertySection extends AbstractActorsPropertySection {

    @Inject
    public LaneAssignableActorsPropertySection(ConnectorConfigurationMigratorFactory migrationFactory,
            ConnectorConfigurationToConnectorDefinitionConverter configurationToDefinitionConverter,
            RepositoryAccessor repositoryAccessor) {
        super(migrationFactory, configurationToDefinitionConverter, repositoryAccessor);
    }

    @Override
    protected void createRadioComposite(TabbedPropertySheetWidgetFactory widgetFactory, Composite mainComposite) {

    }

}
