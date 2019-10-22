/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export.builder;

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;

/**
 * @author Romain Bioteau
 */
public class EngineProcessBuilder extends AbstractProcessBuilder {

    protected final ProcessDefinitionBuilder builder;

    public EngineProcessBuilder(final ProcessDefinitionBuilder processBuilder,
            IEngineDefinitionBuilderProvider engineDefinitionBuilderProvider, IModelSearch modelSearch) {
        super(engineDefinitionBuilderProvider, modelSearch);
        builder = processBuilder;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.model.process.util.ProcessSwitch#casePool(org.bonitasoft.studio.model.process.Pool)
     */
    @Override
    public Element casePool(final Pool pool) {
        if (pool.getDisplayName() != null) {
            builder.addDisplayName(pool.getDisplayName());
        }
        addDocuments(builder, pool);
        addActors(builder, pool);
        addData(builder, pool);
        addParameters(builder, pool);
        addConnector(builder, pool);
        addKPIBinding(builder, pool);
        addContract(builder, pool);
        addContext(builder, pool);
        return pool;
    }

    public ProcessDefinitionBuilder getProcessBuilder() {
        return builder;
    }
}
