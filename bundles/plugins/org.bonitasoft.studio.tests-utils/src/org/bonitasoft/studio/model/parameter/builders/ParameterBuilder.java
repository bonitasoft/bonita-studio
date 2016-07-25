/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.parameter.builders;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterBuilder {


    public static ParameterBuilder aParameter() {
        return new ParameterBuilder();
    }

    private final Parameter parameter;

    private ParameterBuilder() {
        parameter = ParameterFactory.eINSTANCE.createParameter();
    }

    public ParameterBuilder withName(final String name) {
        parameter.setName(name);
        return this;
    }

    public ParameterBuilder withDescription(final String description) {
        parameter.setDescription(description);
        return this;
    }

    public ParameterBuilder withType(final String className) {
        parameter.setTypeClassname(className);
        return this;
    }

    public ParameterBuilder withValue(final String value) {
        parameter.setValue(value);
        return this;
    }

    public Parameter build() {
        return parameter;
    }

}
