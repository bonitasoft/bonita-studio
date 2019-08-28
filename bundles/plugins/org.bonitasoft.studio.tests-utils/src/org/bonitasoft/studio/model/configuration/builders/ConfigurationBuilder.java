/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.configuration.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.builders.ParameterBuilder;

/**
 * @author Romain Bioteau
 */
public class ConfigurationBuilder {

    private final Configuration configuration;

    private ConfigurationBuilder(final Configuration configuration) {
        this.configuration = configuration;
    }

    public static ConfigurationBuilder aConfiguration() {
        return new ConfigurationBuilder(ConfigurationFactory.eINSTANCE.createConfiguration());
    }

    public ConfigurationBuilder withName(final String name) {
        configuration.setName(name);
        return this;
    }

    public ConfigurationBuilder withDescription(final String description) {
        configuration.setDescription(description);
        return this;
    }

    public ConfigurationBuilder withVersion(final String version) {
        configuration.setVersion(version);
        return this;
    }

    public ConfigurationBuilder withPassword(final String password) {
        configuration.setPassword(password);
        return this;
    }

    public ConfigurationBuilder withUsername(final String username) {
        configuration.setUsername(username);
        return this;
    }

    public ConfigurationBuilder withAnonymousUsername(final String anonymousUserName) {
        configuration.setAnonymousUserName(anonymousUserName);
        return this;
    }

    public ConfigurationBuilder withAnonymousPassword(final String anonymousPassword) {
        configuration.setAnonymousPassword(anonymousPassword);
        return this;
    }

    public ConfigurationBuilder havingDefinitionMappings(final Buildable<? extends DefinitionMapping>... defMappings) {
        for (final Buildable<? extends DefinitionMapping> def : defMappings) {
            configuration.getDefinitionMappings().add(def.build());
        }
        return this;
    }

    public ConfigurationBuilder havingProcessDependencies(final Buildable<? extends FragmentContainer>... dependencies) {
        for (final Buildable<? extends FragmentContainer> dep : dependencies) {
            configuration.getProcessDependencies().add(dep.build());
        }
        return this;
    }

    public ConfigurationBuilder havingApplicationDependencies(final Buildable<? extends FragmentContainer>... dependencies) {
        for (final Buildable<? extends FragmentContainer> dep : dependencies) {
            configuration.getApplicationDependencies().add(dep.build());
        }
        return this;
    }

    public ConfigurationBuilder havingParameters(final Buildable<? extends Parameter>... parameters) {
        for (final Buildable<? extends Parameter> parameter : parameters) {
            configuration.getParameters().add(parameter.build());
        }
        return this;
    }

    public ConfigurationBuilder havingActorMapping(final Buildable<? extends ActorMappingsType> actorMapping) {
        configuration.setActorMappings(actorMapping.build());
        return this;
    }

    public Configuration build() {
        return configuration;
    }

}
