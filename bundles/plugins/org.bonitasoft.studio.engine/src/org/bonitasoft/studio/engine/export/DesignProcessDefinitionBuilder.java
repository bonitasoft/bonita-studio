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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;
import org.bonitasoft.engine.bpm.process.InvalidProcessDefinitionException;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.engine.export.switcher.AbstractProcessSwitch;
import org.bonitasoft.studio.engine.export.switcher.AbstractSwitch;
import org.bonitasoft.studio.engine.export.switcher.FlowElementSwitch;
import org.bonitasoft.studio.engine.export.switcher.SequenceFlowSwitch;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.LinkEvent;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier
 *         - add exception transition
 */
public class DesignProcessDefinitionBuilder {

    private final Set<String> definedAuthorities;
    private final Set<Data> definedData;
    private final Set<LinkEvent> definedLink;
    private Set<EObject> eObjectNotExported;

    public DesignProcessDefinitionBuilder() {
        definedAuthorities = new HashSet<String>();
        definedData = new HashSet<Data>();
        definedLink = new HashSet<LinkEvent>();
        eObjectNotExported = new HashSet<EObject>();
    }

    public Set<EObject> geteObjectNotExported() {
        return eObjectNotExported;
    }

    public void seteObjectNotExported(final Set<EObject> eObjectNotExported) {
        this.eObjectNotExported = eObjectNotExported;
    }

    /**
     * can be called on a MainProcess
     *
     * @param studioProcess
     * @return
     * @throws BonitaExportException
     * @throws InvalidProcessDefinitionException
     */
    public List<DesignProcessDefinition> createProcessDefinition(final AbstractProcess studioProcess) throws InvalidProcessDefinitionException {
        final List<DesignProcessDefinition> res = new ArrayList<DesignProcessDefinition>();

        final DesignProcessDefinition def = createDefinition(studioProcess);
        if (null != def) {
            res.add(def);
            reset();
        }

        if (studioProcess instanceof MainProcess) {
            for (final Element item : studioProcess.getElements()) {
                if (item instanceof AbstractProcess) {
                    res.addAll(createProcessDefinition((AbstractProcess) item));
                }
            }
        }

        return res;
    }

    private void reset() {
        definedAuthorities.clear();
        definedData.clear();
        definedLink.clear();
    }

    /**
     * Must be called on a single process
     *
     * @param process
     * @return
     * @throws BonitaExportException
     * @throws InvalidProcessDefinitionException
     */
    public DesignProcessDefinition createDefinition(final AbstractProcess process) throws InvalidProcessDefinitionException {
        final ProcessDefinitionBuilder processBuilder = createProcessDefinitionBuilderInstance(process);
        final String decription = process.getDocumentation();
        if (decription != null) {
            processBuilder.addDescription(decription);
            processBuilder.addDisplayDescription(decription);
        }
        final AbstractProcessSwitch processSwitch = createProcessSwitch(processBuilder);
        processSwitch.doSwitch(process);

        processFlowElements(process, processBuilder);
        processSequenceFlows(process, processBuilder);

        return processBuilder.done();
    }

    protected ProcessDefinitionBuilder createProcessDefinitionBuilderInstance(final AbstractProcess process) {
        return new ProcessDefinitionBuilder().createNewInstance(process.getName(), process.getVersion());
    }

    protected AbstractProcessSwitch createProcessSwitch(final ProcessDefinitionBuilder processBuilder) {
        return new AbstractProcessSwitch(processBuilder, eObjectNotExported);
    }

    protected void processFlowElements(final AbstractProcess process,
            final ProcessDefinitionBuilder processBuilder) {
        final List<FlowElement> flowElements = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.FLOW_ELEMENT);
        final AbstractSwitch flowElementSwitch = createFlowElementSwitch(processBuilder);
        for (final FlowElement flowElement : flowElements) {
            if (!eObjectNotExported.contains(flowElement) && !ModelHelper.isInEvenementialSubProcessPool(flowElement)) {
                flowElementSwitch.doSwitch(flowElement);
            }
        }
        final List<SubProcessEvent> elements = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.SUB_PROCESS_EVENT);
        for (final SubProcessEvent flowElement : elements) {
            if (!eObjectNotExported.contains(flowElement)) {
                flowElementSwitch.doSwitch(flowElement);
            }
        }
    }

    protected AbstractSwitch createFlowElementSwitch(
            final ProcessDefinitionBuilder processBuilder) {
        return new FlowElementSwitch(processBuilder, eObjectNotExported);
    }

    protected void processSequenceFlows(final AbstractProcess process,
            final ProcessDefinitionBuilder processBuilder) {
        final List<SourceElement> sourceElements = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.SOURCE_ELEMENT);
        final SequenceFlowSwitch sequenceFlowSwitch = new SequenceFlowSwitch(processBuilder);
        for (final SourceElement sourceElement : sourceElements) {
            for (final Connection connection : sourceElement.getOutgoing()) {
                if (!ModelHelper.isInEvenementialSubProcessPool(connection.getSource())) {
                    sequenceFlowSwitch.doSwitch(connection);
                }
            }
        }
    }

}
