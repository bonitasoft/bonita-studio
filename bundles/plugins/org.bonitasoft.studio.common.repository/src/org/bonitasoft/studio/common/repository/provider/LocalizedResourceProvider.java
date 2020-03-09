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
package org.bonitasoft.studio.common.repository.provider;

import java.util.Locale;
import java.util.ResourceBundle;

import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ILocalizedResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;

public class LocalizedResourceProvider implements ILocalizedResourceProvider {

    private IDefinitionRepositoryStore actorFilterDefStore;
    private IDefinitionRepositoryStore connectorDefStore;
    private Locale locale = Locale.getDefault();

    public LocalizedResourceProvider(IDefinitionRepositoryStore actorFilterDefStore,
            IDefinitionRepositoryStore connectorDefStore, Locale locale) {
        this.actorFilterDefStore = actorFilterDefStore;
        this.connectorDefStore = connectorDefStore;
        this.locale = locale;
    }

    @Override
    public String getConnectorDefinitionName(String definitionId, String definitionVersion) {
        return getKey(connectorDefStore, definitionId, definitionVersion);
    }

    @Override
    public String getActorFilterDefinitionName(String definitionId, String definitionVersion) {
        return getKey(actorFilterDefStore, definitionId, definitionVersion);
    }

    private String getKey(IDefinitionRepositoryStore store, String definitionId, String definitionVersion) {
        ConnectorDefinition definition = store.getDefinition(definitionId, definitionVersion);
        if (definition != null) {
            ResourceBundle resourceBundle = store.getResourceProvider().getResourceBundle(definition, locale);
            if (resourceBundle != null && resourceBundle.containsKey(DefinitionResourceProvider.connectorDefinition)) {
                return resourceBundle.containsKey(DefinitionResourceProvider.connectorDefinition)
                        ? resourceBundle.getString(DefinitionResourceProvider.connectorDefinition)
                        : String.format("%s-%s", definitionId, definitionVersion);
            }
            return store.getResourceProvider().getConnectorDefinitionLabel(definition);
        }
        return String.format("%s-%s", definitionId, definitionVersion);
    }
}
