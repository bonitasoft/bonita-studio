/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.bpmn.transfo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import javax.xml.namespace.QName;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.builders.XMLDataBuilder;
import org.bonitasoft.studio.model.process.builders.XMLDataTypeBuilder;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics;
import org.omg.spec.bpmn.model.TStandardLoopCharacteristics;
import org.omg.spec.bpmn.model.TTask;


public class BonitaToBPMNTest extends BonitaToBPMN {

    private BonitaToBPMN bonitaToBPMN;

    @Before
    public void setUp() throws Exception {
        bonitaToBPMN = new BonitaToBPMN();
    }

    @Test
    public void testMultiInstanceLoop() {
        final Task bonitaTask = ProcessFactory.eINSTANCE.createTask();
        bonitaTask.setType(MultiInstanceType.STANDARD);
        bonitaTask.setLoopMaximum(ExpressionHelper.createConstantExpression("45", Integer.class.getName()));
        bonitaTask.setLoopCondition(ExpressionHelper.createGroovyScriptExpression("my condition script", Boolean.class.getName()));
        bonitaTask.setTestBefore(true);

        final TTask bpmTask = (TTask) bonitaToBPMN.createActivity(bonitaTask);
        final TStandardLoopCharacteristics loopCharacteristics = (TStandardLoopCharacteristics) bpmTask.getLoopCharacteristics();
        assertThat(loopCharacteristics).isNotNull();
        assertThat(loopCharacteristics.getLoopMaximum()).isEqualTo(BigInteger.valueOf(45));
        assertThat(((TFormalExpression) loopCharacteristics.getLoopCondition()).getMixed().getValue(0)).isEqualTo("my condition script");
        assertThat(loopCharacteristics.isTestBefore()).isTrue();
    }

    @Test
    public void testMultiInstanceParallelWithFilledAttributes() {
        final Activity bonitaActivity = ProcessFactory.eINSTANCE.createActivity();
        bonitaActivity.setType(MultiInstanceType.PARALLEL);
        bonitaActivity.setCompletionCondition(ExpressionHelper.createGroovyScriptExpression("my completion condition", Boolean.class.getName()));
        bonitaActivity.setCardinalityExpression(ExpressionHelper.createConstantExpression("45", Integer.class.getName()));

        final TActivity bpmTask = (TActivity) bonitaToBPMN.createActivity(bonitaActivity);
        final TMultiInstanceLoopCharacteristics loopCharacteristics = (TMultiInstanceLoopCharacteristics) bpmTask.getLoopCharacteristics();
        Assertions.assertThat(loopCharacteristics).isNotNull();
        Assertions.assertThat(loopCharacteristics.isIsSequential()).isFalse();
        Assertions.assertThat(loopCharacteristics.getCompletionCondition().getMixed().getValue(0)).isEqualTo("my completion condition");
        Assertions.assertThat(loopCharacteristics.getLoopCardinality().getMixed().getValue(0)).isEqualTo("45");
    }

    @Test
    public void testMultiInstanceSequentialWithFilledAttributes() {
        final Activity bonitaActivity = ProcessFactory.eINSTANCE.createActivity();
        bonitaActivity.setType(MultiInstanceType.SEQUENTIAL);
        bonitaActivity.setCompletionCondition(ExpressionHelper.createGroovyScriptExpression("my completion condition", Boolean.class.getName()));

        final TActivity bpmTask = (TActivity) bonitaToBPMN.createActivity(bonitaActivity);
        final TMultiInstanceLoopCharacteristics loopCharacteristics = (TMultiInstanceLoopCharacteristics) bpmTask.getLoopCharacteristics();
        Assertions.assertThat(loopCharacteristics).isNotNull();
        Assertions.assertThat(loopCharacteristics.isIsSequential()).isTrue();
        Assertions.assertThat(loopCharacteristics.getCompletionCondition().getMixed().getValue(0)).isEqualTo("my completion condition");
    }

    @Test
    @Ignore("Multi instance initialized by a data is not implemented yet")
    public void testMultiInstanceInitializedByData() {
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        pool.getData().add(data);
        final Activity bonitaActivity = ProcessFactory.eINSTANCE.createActivity();
        pool.getElements().add(bonitaActivity);
        bonitaActivity.setType(MultiInstanceType.PARALLEL);
        bonitaActivity.setCollectionDataToMultiInstantiate(data);
        //Assertions.assertThat(loopCharacteristics.getLoopDataInputRef().getLocalPart()).isEqualTo("myData");
    }

    @Test
    public void testMultiInstanceParallelWithMinimalAttributesFilled() {
        final Activity bonitaActivity = ProcessFactory.eINSTANCE.createActivity();
        bonitaActivity.setType(MultiInstanceType.PARALLEL);


        final TActivity bpmTask = (TActivity) bonitaToBPMN.createActivity(bonitaActivity);
        final TMultiInstanceLoopCharacteristics loopCharacteristics = (TMultiInstanceLoopCharacteristics) bpmTask.getLoopCharacteristics();
        Assertions.assertThat(loopCharacteristics).isNotNull();
        Assertions.assertThat(loopCharacteristics.isIsSequential()).isFalse();
        Assertions.assertThat(loopCharacteristics.getCompletionCondition()).isNull();
        Assertions.assertThat(loopCharacteristics.getLoopCardinality()).isNull();
    }

    @Test
    public void should_export_xmldata_without_namespace() throws Exception {
        bonitaToBPMN.initializeDocumentRoot();
        bonitaToBPMN.configureNamespaces();
        final QName ref = bonitaToBPMN.getStructureRef(XMLDataBuilder.anXMLData().havingDataType(XMLDataTypeBuilder.create()).build());
        final QName ref2 = bonitaToBPMN.getStructureRef(XMLDataBuilder.anXMLData().havingDataType(XMLDataTypeBuilder.create()).build());
        assertThat(ref.getLocalPart()).isEqualTo("n0:null");
        assertThat(ref2.getLocalPart()).isEqualTo("n1:null");
    }

}
