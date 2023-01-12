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
package org.bonitasoft.studio.exporter.bpmn.transfo.data;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import javax.xml.namespace.QName;

import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.exporter.bpmn.transfo.BPMNConstants;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.XMLData;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TDocumentation;
import org.omg.spec.bpmn.model.TItemDefinition;

/**
 * @author Romain Bioteau
 */
public class ItemDefinitionFunction implements Function<Data, TItemDefinition>, BPMNConstants {

    private final TDefinitions bpmnDefinitions;
    private final XMLNamespaceResolver xmlNamespaceResolver;
    private IModelSearch modelSearch;

    public ItemDefinitionFunction(final TDefinitions bpmnDefinitions, final XMLNamespaceResolver xmlNamespaceResolver,
            IModelSearch modelSearch) {
        this.modelSearch = modelSearch;
        this.bpmnDefinitions = requireNonNull(bpmnDefinitions);
        this.xmlNamespaceResolver = requireNonNull(xmlNamespaceResolver);
    }

    @Override
    public TItemDefinition apply(final Data sourceElement) {
        final ModelFactory modelFactory = ModelFactory.eINSTANCE;
        final TItemDefinition dataItemDefinition = modelFactory.createTItemDefinition();
        dataItemDefinition.setId(modelSearch.getEObjectID(sourceElement));
        final String documentation = sourceElement.getDocumentation();
        if (documentation != null && !documentation.isEmpty()) {
            final TDocumentation doc = modelFactory.createTDocumentation();
            FeatureMapUtil.addText(doc.getMixed(), documentation);
            dataItemDefinition.getDocumentation().add(doc);
        }
        dataItemDefinition.setStructureRef(getStructureRef(sourceElement));
        bpmnDefinitions.getRootElement().add(dataItemDefinition);
        return dataItemDefinition;
    }

    private QName getStructureRef(final Data data) {
        if (data instanceof XMLData) {
            final XMLData xmlData = (XMLData) data;
            final String xmlnsDataType = xmlNamespaceResolver.resolveNamespacePrefix(xmlData);
            return QName.valueOf(xmlnsDataType + ":" + xmlData.getType());
        }
        final String qualifiedClassName = org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data);
        return QName.valueOf(JAVA_XMLNS + ":" + qualifiedClassName);
    }

}
