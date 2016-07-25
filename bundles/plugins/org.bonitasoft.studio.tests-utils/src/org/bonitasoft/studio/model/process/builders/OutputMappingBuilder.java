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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;


public class OutputMappingBuilder implements Buildable<OutputMapping> {

    private final OutputMapping outputMapping;

    public static OutputMappingBuilder anOutputMapping() {
        return new OutputMappingBuilder();
    }

    protected OutputMappingBuilder() {
        outputMapping = ProcessFactory.eINSTANCE.createOutputMapping();
    }

    public OutputMappingBuilder setProcessTarget(final Data data) {
        outputMapping.setProcessTarget(data);
        return this;
    }

    public OutputMappingBuilder setSubProcessSource(final String elementInCalledProcess) {
        outputMapping.setSubprocessSource(elementInCalledProcess);
        return this;
    }

    @Override
    public OutputMapping build() {
        return outputMapping;
    }

}
