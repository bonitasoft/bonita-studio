/**
 * Copyright (C) 2009-2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.export;

import java.util.List;

import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;
import org.bonitasoft.engine.bpm.process.InvalidProcessDefinitionException;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.engine.export.builder.AbstractProcessBuilder;
import org.bonitasoft.studio.engine.export.builder.EngineFlowElementBuilder;
import org.bonitasoft.studio.engine.export.builder.EngineProcessBuilder;
import org.bonitasoft.studio.engine.export.builder.EngineSequenceFlowBuilder;
import org.bonitasoft.studio.engine.export.builder.IEngineDefinitionBuilderProvider;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.SubProcessEvent;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier
 *         - add exception transition
 */
public class DesignProcessDefinitionBuilder {

    private IEngineDefinitionBuilderProvider engineDefinitionBuilderProvider;
    private IModelSearch modelSearch;

    public DesignProcessDefinitionBuilder(IEngineDefinitionBuilderProvider engineDefinitionBuilderProvider,
            IModelSearch modelSearch) {
        this.engineDefinitionBuilderProvider = engineDefinitionBuilderProvider;
        this.modelSearch = modelSearch;
    }

    public IEngineDefinitionBuilderProvider getEngineDefinitionBuilderProvider() {
        return engineDefinitionBuilderProvider;
    }

    public IModelSearch getModelSearch() {
        return modelSearch;
    }

    public DesignProcessDefinition createDefinition(final AbstractProcess process) throws InvalidProcessDefinitionException {
        final ProcessDefinitionBuilder processBuilder = createProcessDefinitionBuilderInstance(process);
        final String decription = process.getDocumentation();
        if (decription != null) {
            processBuilder.addDescription(decription);
            processBuilder.addDisplayDescription(decription);
        }
        newEngineProcessBuilder(processBuilder).doSwitch(process);
        processFlowElements(process, processBuilder);
        processSequenceFlows(process, processBuilder);
        return processBuilder.done();
    }

    protected ProcessDefinitionBuilder createProcessDefinitionBuilderInstance(final AbstractProcess process) {
        return new ProcessDefinitionBuilder().createNewInstance(process.getName(), process.getVersion());
    }

    protected EngineProcessBuilder newEngineProcessBuilder(final ProcessDefinitionBuilder processBuilder) {
        return new EngineProcessBuilder(processBuilder, engineDefinitionBuilderProvider, modelSearch);
    }

    protected void processFlowElements(final AbstractProcess process,
            final ProcessDefinitionBuilder processBuilder) {
        final List<FlowElement> flowElements = modelSearch.getAllItemsOfType(process, FlowElement.class);
        final AbstractProcessBuilder flowElementSwitch = newEngineFlowElementBuilder(processBuilder);
        for (final FlowElement flowElement : flowElements) {
            if (!modelSearch.isInEvenementialSubProcessPool(flowElement)) {
                flowElementSwitch.doSwitch(flowElement);
            }
        }
        final List<SubProcessEvent> elements = modelSearch.getAllItemsOfType(process, SubProcessEvent.class);
        for (final SubProcessEvent flowElement : elements) {
                flowElementSwitch.doSwitch(flowElement);
        }
    }

    protected AbstractProcessBuilder newEngineFlowElementBuilder(
            final ProcessDefinitionBuilder processBuilder) {
        return new EngineFlowElementBuilder(processBuilder, engineDefinitionBuilderProvider, modelSearch);
    }

    protected void processSequenceFlows(final AbstractProcess process,
            final ProcessDefinitionBuilder processBuilder) {
        final List<SourceElement> sourceElements = modelSearch.getAllItemsOfType(process, SourceElement.class);
        final EngineSequenceFlowBuilder sequenceFlowSwitch = new EngineSequenceFlowBuilder(processBuilder);
        for (final SourceElement sourceElement : sourceElements) {
            for (final Connection connection : sourceElement.getOutgoing()) {
                if (!modelSearch.isInEvenementialSubProcessPool(connection.getSource())) {
                    sequenceFlowSwitch.doSwitch(connection);
                }
            }
        }
    }

}
