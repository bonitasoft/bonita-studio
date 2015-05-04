/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.emf.tools;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.BooleanDataTypeBuilder.aBooleanDataType;
import static org.bonitasoft.studio.model.process.builders.DateDataTypeBuilder.aDateDataType;
import static org.bonitasoft.studio.model.process.builders.DoubleDataTypeBuilder.aDoubleDataType;
import static org.bonitasoft.studio.model.process.builders.IntegerDataTypeBuilder.anIntegerDataType;
import static org.bonitasoft.studio.model.process.builders.JavaDataTypeBuilder.aJavaDataType;
import static org.bonitasoft.studio.model.process.builders.LongDataTypeBuilder.aLongDataType;
import static org.bonitasoft.studio.model.process.builders.MainProcessBuilder.aMainProcess;
import static org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder.aStringDataType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.StringType;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ModelHelperTest {

    private Pool process;
    private Task task1;
    private Task task2;
    private Data processData;
    private Data t1Data;
    private Data t2Data;
    private Form myForm;
    private TextFormField textField;
    private Data pageFlowTransientData;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final ProcessFactory processFactory = ProcessFactory.eINSTANCE;
        final FormFactory formFactory = FormFactory.eINSTANCE;
        process = processFactory.createPool();
        processData = processFactory.createData();
        process.getData().add(processData);
        task1 = processFactory.createTask();
        t1Data = processFactory.createData();
        task1.getData().add(t1Data);

        myForm = formFactory.createForm();
        textField = formFactory.createTextFormField();
        myForm.getWidgets().add(textField);
        task1.getForm().add(myForm);

        pageFlowTransientData = processFactory.createData();
        pageFlowTransientData.setTransient(true);
        task1.getTransientData().add(pageFlowTransientData);

        task2 = processFactory.createTask();
        t2Data = processFactory.createData();
        task2.getData().add(t2Data);
        process.getElements().add(task1);
        process.getElements().add(task2);

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldGetAccessibleData_ReturnEmptyListWhenNoDataAccessible() throws Exception {
        final Element container = ProcessFactory.eINSTANCE.createPool();
        assertThat(ModelHelper.getAccessibleData(container, true)).isNotNull().isEmpty();
    }

    @Test
    public void shouldGetAccessibleData_ReturnEmptyListWhenContainerIsNull() throws Exception {
        assertThat(ModelHelper.getAccessibleData(null, true)).isNotNull().isEmpty();
    }

    @Test
    public void shouldGetAccessibleData_ForProcessReturnProcessData() throws Exception {
        assertThat(ModelHelper.getAccessibleData(process, false)).isNotNull().containsOnly(processData);
    }

    @Test
    public void shouldGetAccessibleData_ForTask1ReturnProcessDataAndT1Data() throws Exception {
        assertThat(ModelHelper.getAccessibleData(task1, false)).isNotNull().containsOnly(processData, t1Data);
    }

    @Test
    public void shouldGetAccessibleData_ForWidgetReturnAllAccessibleAndPageflowTransientData() throws Exception {
        assertThat(ModelHelper.getAccessibleData(textField, true)).isNotNull().containsOnly(processData, t1Data, pageFlowTransientData);
    }

    @Test
    public void shouldGetAccessibleData_ForWidgetReturnAllAccessibleWithoutPageflowTransientData_IfIncludeTransientDataIsFalse() throws Exception {
        assertThat(ModelHelper.getAccessibleData(textField, false)).isNotNull().containsOnly(processData, t1Data);
    }

    @Test
    public void shouldGetReferencedDocument() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setName("MyDocument");
        expr.setContent("MyDocument");
        final Document doc = ProcessFactory.eINSTANCE.createDocument();
        doc.setName("MyDocument");
        expr.getReferencedElements().add(doc);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myDocument");
        expr.getReferencedElements().add(data);
        assertThat(ModelHelper.getDocumentReferencedInExpression(expr)).isNotNull().isEqualTo(doc);
    }

    @Test
    public void shouldGedReferencedDocument_returnNull() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setName("MyDocument");
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myDocument");
        expr.getReferencedElements().add(data);
        assertThat(ModelHelper.getDocumentReferencedInExpression(expr)).isNull();
        final Document doc = ProcessFactory.eINSTANCE.createDocument();
        doc.setName("MyDocumentBis");
        expr.getReferencedElements().add(doc);
        assertThat(ModelHelper.getDocumentReferencedInExpression(expr)).isNull();
    }

    @Test
    public void shouldReturnDataOnActivity() {
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        activity.getData().add(data);
        activity.getData().add(anotherData);
        final Data returnData = ModelHelper.getDataOnActivity(data, activity);
        assertEquals(returnData.getName(), data.getName());
    }

    @Test
    public void shouldNotReturDataOnActivity() {
        final Activity activity1 = ProcessFactory.eINSTANCE.createActivity();
        final Activity activity2 = ProcessFactory.eINSTANCE.createActivity();
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        activity1.getData().add(data);
        activity2.getData().add(anotherData);
        final Data returnData = ModelHelper.getDataOnActivity(data, activity2);
        assertNull(returnData);
    }

    @Test
    public void shouldReturnDataOnPool() {
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        pool.getData().add(data);
        pool.getData().add(anotherData);
        final Data returnData = ModelHelper.getDataOnPool(data, pool);
        assertEquals(returnData.getName(), data.getName());
    }

    @Test
    public void shouldNotReturnDataOnPool() {
        final Pool pool1 = ProcessFactory.eINSTANCE.createPool();
        final Pool pool2 = ProcessFactory.eINSTANCE.createPool();
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        pool1.getData().add(data);
        pool2.getData().add(anotherData);
        final Data returnData = ModelHelper.getDataOnPool(data, pool2);
        assertNull(returnData);
    }

    @Test
    public void shouldReturnReferencedDataActivityContainer() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);

        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        activity.getData().add(data);
        activity.getData().add(anotherData);
        final EObject container = ModelHelper.getReferencedDataActivityContainer(refData);
        assertEquals(container, activity);
    }

    @Test
    public void shouldReturnReferencedDataPoolContainer() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.getElements().add(activity);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");
        pool.getData().add(data);
        pool.getData().add(anotherData);
        final EObject container = ModelHelper.getReferencedDataPoolContainer(refData);
        assertEquals(container, pool);
    }

    @Test
    public void shouldNotReturnReferencedDataActivityContainer() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.getElements().add(activity);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");
        pool.getData().add(data);
        pool.getData().add(anotherData);
        final EObject container = ModelHelper.getReferencedDataActivityContainer(refData);
        assertNull(container);
    }

    @Test
    public void shouldNotReturnReferencedDatWhenNoActivityContainer() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");
        pool.getData().add(data);
        pool.getData().add(anotherData);
        final EObject container = ModelHelper.getReferencedDataActivityContainer(refData);
        assertNull(container);
    }

    @Test
    public void shouldNotReturnReferencedDatWhenNoPoolContainer() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");
        final EObject container = ModelHelper.getReferencedDataPoolContainer(refData);
        assertNull(container);
    }

    @Test
    public void shouldNotReturnReferencedDataPoolContainer() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.getElements().add(activity);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");
        pool.getData().add(anotherData);
        final EObject container = ModelHelper.getReferencedDataPoolContainer(refData);
        assertNull(container);
    }

    @Test
    public void isSamecontainerReturnsTrueWhenContainerIsActivity() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);

        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        activity.getData().add(data);
        activity.getData().add(anotherData);
        assertTrue(ModelHelper.isSameContainer(refData, data));
    }

    @Test
    public void isSameContainerReturnsTrueWhenContainerIsPool() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.getElements().add(activity);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");
        pool.getData().add(data);
        pool.getData().add(anotherData);
        assertTrue(ModelHelper.isSameContainer(refData, data));
    }

    @Test
    public void isSameContainerReturnsFalseWhenContainerIsNull() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.getElements().add(activity);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");
        pool.getData().add(anotherData);
        assertFalse(ModelHelper.isSameContainer(refData, data));
    }

    @Test
    public void isSameContainerReturnsTrueWhenReferecencedElementIsParamater() {
        final Parameter parameter = ParameterFactory.eINSTANCE.createParameter();
        parameter.setName("myParameter");
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Parameter refParameter = EcoreUtil.copy(parameter);
        expr.getReferencedElements().add(refParameter);
        pool.getParameters().add(parameter);
        assertTrue(ModelHelper.isSameContainer(refParameter, pool));
    }

    @Test
    public void isSameElementReturnsTrue() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);

        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        activity.getData().add(data);
        activity.getData().add(anotherData);

        assertTrue(ModelHelper.isSameElement(data, refData));
    }

    @Test
    public void isSameElementReturnsFalseWhenNotSameName() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);

        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData1");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        activity.getData().add(data);
        activity.getData().add(anotherData);

        assertFalse(ModelHelper.isSameElement(data, refData));
    }

    @Test
    public void isSameElementReturnsFalseWhenNotSameContainer() {
        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        final Data refData = ProcessFactory.eINSTANCE.createData();
        refData.setName("myData");
        expr.getReferencedElements().add(refData);
        final Operation op = ExpressionFactory.eINSTANCE.createOperation();
        op.setRightOperand(expr);

        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getOperations().add(op);
        final Activity activity2 = ProcessFactory.eINSTANCE.createActivity();
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final Data anotherData = ProcessFactory.eINSTANCE.createData();
        anotherData.setName("anotherData");

        activity2.getData().add(data);
        activity2.getData().add(anotherData);
        assertFalse(ModelHelper.isSameElement(data, refData));
    }

    @Test
    public void should_get_StringType_for_String_classname() throws Exception {
        final StringType stringDataType = aStringDataType().build();
        assertThat(ModelHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(stringDataType).build()
                , String.class.getName())).isEqualTo(stringDataType);
    }

    @Test
    public void should_get_BooleanType_for_Boolean_classname() throws Exception {
        final BooleanType booleanDataType = aBooleanDataType().build();
        assertThat(ModelHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(booleanDataType).build()
                , Boolean.class.getName())).isEqualTo(booleanDataType);
    }

    @Test
    public void should_get_IntegerType_for_Integer_classname() throws Exception {
        final IntegerType integerDataType = anIntegerDataType().build();
        assertThat(ModelHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(integerDataType).build()
                , Integer.class.getName())).isEqualTo(integerDataType);
    }

    @Test
    public void should_get_DoubleType_for_Double_classname() throws Exception {
        final DoubleType doubleDataType = aDoubleDataType().build();
        assertThat(ModelHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(doubleDataType).build()
                , Double.class.getName())).isEqualTo(doubleDataType);
    }

    @Test
    public void should_get_LongType_for_Long_classname() throws Exception {
        final LongType longDataType = aLongDataType().build();
        assertThat(ModelHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(longDataType).build()
                , Long.class.getName())).isEqualTo(longDataType);
    }

    @Test
    public void should_get_DateType_for_Date_classname() throws Exception {
        final DateType dataDataType = aDateDataType().build();
        assertThat(ModelHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(dataDataType).build()
                , Date.class.getName())).isEqualTo(dataDataType);
    }

    @Test
    public void should_get_JavaType_for_Other_classname() throws Exception {
        final JavaType javaDataType = aJavaDataType().build();
        assertThat(ModelHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(javaDataType).build()
                , Object.class.getName())).isEqualTo(javaDataType);
    }
}
