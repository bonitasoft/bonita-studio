/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.model.configuration.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;

/**
 * @author aurelie
 */
public class DefinitionMappingBuilder implements Buildable<DefinitionMapping> {

    private final DefinitionMapping definitionMapping;

    private DefinitionMappingBuilder(final DefinitionMapping definitionMapping) {
        this.definitionMapping = definitionMapping;
    }

    public static DefinitionMappingBuilder aDefinitionMapping() {
        return new DefinitionMappingBuilder(ConfigurationFactory.eINSTANCE.createDefinitionMapping());
    }

    public DefinitionMappingBuilder withDefinitionId(final String definitionId) {
        definitionMapping.setDefinitionId(definitionId);
        return this;
    }

    public DefinitionMappingBuilder withDefinitionVersion(final String definitionVersion) {
        definitionMapping.setDefinitionVersion(definitionVersion);
        return this;
    }

    public DefinitionMappingBuilder withImplementationVersion(final String implementationVersion) {
        definitionMapping.setImplementationVersion(implementationVersion);
        return this;
    }

    public DefinitionMappingBuilder withImplementationId(final String implementationId) {
        definitionMapping.setImplementationId(implementationId);
        return this;
    }

    public DefinitionMappingBuilder withType(final String type) {
        definitionMapping.setType(type);
        return this;
    }

    @Override
    public DefinitionMapping build() {
        return definitionMapping;
    }

}