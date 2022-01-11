/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.core.migration.dependencies.operation;

import java.util.Collection;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.core.migration.dependencies.configuration.ProcessConfigurationChange;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.connector.ConnectorImplementationChange;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.model.configuration.Configuration;

public class JarRemovedChange implements ProcessConfigurationChange, ConnectorImplementationChange {

    private String jar;
    private Collection<Configuration> configurations;
    private Collection<ConnectorImplementation> implementations;

    public JarRemovedChange(String jar, 
            Collection<Configuration> configurations,
            Collection<ConnectorImplementation> implementations) {
        this.jar = jar;
        this.configurations = configurations;
        this.implementations = implementations;
    }

    @Override
    public void apply(Configuration configuration) {
        var otherJarFragmentContainer = getOtherJarFragmentContainer(configuration);
        otherJarFragmentContainer.getFragments().removeIf(f -> Objects.equals(f.getValue(), jar));
    }
    
    @Override
    public void apply(ConnectorImplementation implementation) {
       if(implementation.getJarDependencies() != null) {
           implementation.getJarDependencies().getJarDependency().removeIf(dep -> Objects.equals(dep, jar));
       }
    }

    @Override
    public Collection<Configuration> getConfigurations() {
        return configurations;
    }

    @Override
    public Collection<ConnectorImplementation> getConnectorImplementations() {
        return implementations;
    }

}
