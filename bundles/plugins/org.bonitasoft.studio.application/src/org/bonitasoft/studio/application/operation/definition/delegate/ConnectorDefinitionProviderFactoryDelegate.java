/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.operation.definition.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.application.operation.definition.ConnectorDefinitionProvider;
import org.bonitasoft.studio.application.operation.definition.ConnectorDefinitionProviderFactory;

public class ConnectorDefinitionProviderFactoryDelegate implements ConnectorDefinitionProviderFactory {

    private List<ConnectorDefinitionProviderFactory> delegates = new ArrayList<>();

    public ConnectorDefinitionProviderFactoryDelegate(ConnectorDefinitionProviderFactory... delegates) {
        this.delegates = List.of(delegates);
    }

    @Override
    public ConnectorDefinitionProvider get() {
        return (id, version) -> delegates.stream()
                .map(ConnectorDefinitionProviderFactory::get)
                .map(provider -> provider.find(id, version))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

}
