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
package org.bonitasoft.studio.diagram.form.custom.commands;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetContainer;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.Info;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.Table;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateFormCommandTest {

    private CreateFormCommand commandUnderTest;

    @Mock
    private PageFlow pageFlow;

    private SimpleField firstNameAttribute;

    private SimpleField validAttribute;

    private SimpleField colors;

    private BusinessObjectData data;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final BusinessObject employee = new BusinessObject();
        employee.setQualifiedName("Employee");

        firstNameAttribute = new SimpleField();
        firstNameAttribute.setName("firstName");
        firstNameAttribute.setType(FieldType.STRING);
        firstNameAttribute.setCollection(false);

        validAttribute = new SimpleField();
        validAttribute.setName("valid");
        validAttribute.setType(FieldType.BOOLEAN);
        validAttribute.setCollection(false);

        colors = new SimpleField();
        colors.setName("colors");
        colors.setType(FieldType.STRING);
        colors.setCollection(true);

        employee.getFields().add(firstNameAttribute);
        employee.getFields().add(validAttribute);
        employee.getFields().add(colors);

        data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setDataType(ProcessFactory.eINSTANCE.createBusinessObjectType());
        data.setName("currentEmployee");
        data.setBusinessDataRepositoryId("pojo");
        data.setEClassName("Employee");
        data.setClassName("org.bonita.pojo.Employee");

        commandUnderTest = spy(new CreateFormCommand(ProcessFactory.eINSTANCE.createTask(),
                ProcessPackage.Literals.PAGE_FLOW__FORM,
                "Test form",
                "Test form description",
                Collections.<WidgetMapping> emptyList(),
                null));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldCreateLabelExpression_ReturnExpression() throws Exception {
        final Expression exp = commandUnderTest.createLabelExpression("Test form");
        assertThat(exp).isNotNull();
        assertThat(exp.getContent()).isEqualTo("Test form");
        assertThat(exp.getName()).isEqualTo("Test form");
        assertThat(exp.getReturnType()).isEqualTo(String.class.getName());
        assertThat(exp.isReturnTypeFixed()).isTrue();
        assertThat(exp.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
        assertThat(exp.getReferencedElements()).isEmpty();
    }

    @Test
    public void shouldCreateLabelExpression_ReturnFormattedNameExpression() throws Exception {
        final Expression exp = commandUnderTest.createLabelExpression("firstName");
        assertThat(exp).isNotNull();
        assertThat(exp.getContent()).isEqualTo("First Name");
        assertThat(exp.getName()).isEqualTo("First Name");
        assertThat(exp.getReturnType()).isEqualTo(String.class.getName());
        assertThat(exp.isReturnTypeFixed()).isTrue();
        assertThat(exp.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
        assertThat(exp.getReferencedElements()).isEmpty();
    }

    @Test
    public void shoudGetHorizontalSpan_AlwaysReturnOneForViewPageFlow() throws Exception {
        commandUnderTest = new CreateFormCommand(ProcessFactory.eINSTANCE.createTask(),
                ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM,
                "Test form",
                "Test form description",
                Collections.<WidgetMapping> emptyList(),
                null);
        final Form viewForm = commandUnderTest.createForm();
        assertThat(viewForm).isNotNull().isInstanceOf(ViewForm.class);
        assertThat(commandUnderTest.getHorizontalSpan(viewForm)).isEqualTo(1);
    }

    @Test
    public void shoudGetHorizontalSpan_ReturnOneForEmptyPageFlow() throws Exception {
        final Form form = commandUnderTest.createForm();
        assertThat(form).isNotNull().isInstanceOf(Form.class);
        assertThat(commandUnderTest.getHorizontalSpan(form)).isEqualTo(1);
    }

    @Test
    public void shoudGetHorizontalSpan_ReturnTwoForNonEmptyPageFlow() throws Exception {
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.getForm().add(FormFactory.eINSTANCE.createForm());
        commandUnderTest = new CreateFormCommand(task,
                ProcessPackage.Literals.PAGE_FLOW__FORM,
                "Test form",
                "Test form description",
                Collections.<WidgetMapping> emptyList(),
                null);
        final Form form = commandUnderTest.createForm();
        assertThat(form).isNotNull().isInstanceOf(Form.class);
        assertThat(commandUnderTest.getHorizontalSpan(form)).isEqualTo(2);
    }

    @Test
    public void shouldCreateInsertWidgetIfScript_ReturnExpression() throws Exception {
        final Expression exp = commandUnderTest.createInsertWidgetIfScript();
        assertThat(exp).isNotNull();
        assertThat(exp.getReturnType()).isEqualTo(Boolean.class.getName());
        assertThat(exp.isReturnTypeFixed()).isTrue();
        assertThat(exp.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
        assertThat(exp.getReferencedElements()).isEmpty();
    }

    @Test
    public void shouldGetVerticalSpan_ReturnOneIfWidgetIsNotAGroup() throws Exception {
        for (final EClassifier eClass : FormFactory.eINSTANCE.getEPackage().getEClassifiers()) {
            if (eClass instanceof EClass && !((EClass) eClass).isAbstract() && FormPackage.Literals.WIDGET.isSuperTypeOf((EClass) eClass)) {
                final Widget w = (Widget) FormFactory.eINSTANCE.create((EClass) eClass);
                if (!(w instanceof Group)) {
                    assertThat(commandUnderTest.getVerticalSpan(w)).isEqualTo(1);
                }
            }
        }
    }

    @Test
    public void shouldGetVerticalSpan_ReturnVerticalSpanForGroup() throws Exception {
        final Group group = FormFactory.eINSTANCE.createGroup();
        final Widget w1 = FormFactory.eINSTANCE.createTextFormField();
        final Widget w2 = FormFactory.eINSTANCE.createTextFormField();
        final Group childGroup = FormFactory.eINSTANCE.createGroup();
        final Widget w3 = FormFactory.eINSTANCE.createTextFormField();
        childGroup.getWidgets().add(w3);
        group.getWidgets().add(w1);
        group.getWidgets().add(w2);
        group.getWidgets().add(childGroup);
        assertThat(commandUnderTest.getVerticalSpan(group)).isEqualTo(3);
    }

    @Test
    public void shouldGetLineIndex_ReturnCurrentLineIndex() throws Exception {
        final Form form = FormFactory.eINSTANCE.createForm();
        final Group group = FormFactory.eINSTANCE.createGroup();
        final TextFormField w1 = FormFactory.eINSTANCE.createTextFormField();
        final CheckBoxSingleFormField w2 = FormFactory.eINSTANCE.createCheckBoxSingleFormField();
        final TextFormField w3 = FormFactory.eINSTANCE.createTextFormField();
        final CheckBoxSingleFormField w4 = FormFactory.eINSTANCE.createCheckBoxSingleFormField();

        final WidgetContainer widgetContainer = new WidgetContainer(form);
        assertThat(commandUnderTest.getLineIndex(widgetContainer)).isEqualTo(0);
        form.getWidgets().add(w1);
        assertThat(commandUnderTest.getLineIndex(widgetContainer)).isEqualTo(1);
        widgetContainer.getWidgets().add(w2);
        assertThat(commandUnderTest.getLineIndex(widgetContainer)).isEqualTo(2);

        final WidgetContainer groupContainer = new WidgetContainer(group);
        assertThat(commandUnderTest.getLineIndex(groupContainer)).isEqualTo(0);
        groupContainer.getWidgets().add(w3);
        assertThat(commandUnderTest.getLineIndex(groupContainer)).isEqualTo(1);
        groupContainer.getWidgets().add(w4);
        assertThat(commandUnderTest.getLineIndex(groupContainer)).isEqualTo(2);
        widgetContainer.getWidgets().add(group);
        assertThat(commandUnderTest.getLineIndex(widgetContainer)).isEqualTo(4);
    }

    @Test
    public void should_createActionExpressionForDocument_should_return_Expression_Not_Null() throws Exception {
        final FileWidget w1 = FormFactory.eINSTANCE.createFileWidget();
        w1.setName("toto");
        final Expression storageExpr = commandUnderTest.createActionExpressionForDocument(w1);
        assertThat(storageExpr).isNotNull();
        assertThat(storageExpr.getType()).isEqualTo(ExpressionConstants.FORM_FIELD_TYPE);
        assertThat(storageExpr.getReturnType()).isEqualTo(DocumentValue.class.getName());

    }

    @Test
    public void should_createStorageExpressionForDocumentSimple_should_return_Expression_Not_Null() throws Exception {
        final Document doc = ProcessFactory.eINSTANCE.createDocument();
        doc.setName("toto");
        final Expression actionExpr = commandUnderTest.createStorageExpressionForDocument(doc, false);
        assertThat(actionExpr).isNotNull();
        assertThat(actionExpr.getType()).isEqualTo(ExpressionConstants.DOCUMENT_REF_TYPE);
        assertThat(actionExpr.getReturnType()).isEqualTo(String.class.getName());
    }

    @Test
    public void should_createStorageExpressionForDocumentMultiple_should_return_Expression_Not_Null() throws Exception {
        final Document doc = ProcessFactory.eINSTANCE.createDocument();
        doc.setName("toto");
        final Expression actionExpr = commandUnderTest.createStorageExpressionForDocument(doc, true);
        assertThat(actionExpr).isNotNull();
        assertThat(actionExpr.getType()).isEqualTo(ExpressionConstants.DOCUMENT_REF_TYPE);
        assertThat(actionExpr.getReturnType()).isEqualTo(List.class.getName());
    }

    @Test
    public void should_createDocumentOutputOperation_should_create_action_Not_Null() throws Exception {
        final Document doc = ProcessFactory.eINSTANCE.createDocument();
        doc.setName("toto");
        final FileWidget w1 = FormFactory.eINSTANCE.createFileWidget();
        w1.setName("toto");

        final Operation action = commandUnderTest.createDocumentOutputOperation(w1, doc);
        assertThat(action).isNotNull();
        assertThat(action.getRightOperand()).isNotNull();
        assertThat(action.getLeftOperand()).isNotNull();
    }

    @Test
    public void should_computeWidgetId_return_key_without_number_when_pageflow_is_null_and_widget_is_in_a_Group() throws Exception {

        commandUnderTest = new CreateFormCommand(null,
                ProcessPackage.Literals.PAGE_FLOW__FORM,
                "Test form",
                "Test form description",
                Collections.<WidgetMapping> emptyList(),
                null);

        final Widget w1 = FormFactory.eINSTANCE.createTextFormField();
        final Group group = FormFactory.eINSTANCE.createGroup();
        group.setName("theGroup");
        group.getWidgets().add(w1);
        final String keyName = "titi";

        final String keyResult = commandUnderTest.computeWidgetId(keyName, w1);
        assertThat(keyResult).isEqualTo("theGroup" + "_" + keyName);
    }

    @Test
    public void should_computeWidgetId_return_key_without_number_when_pageflow_is_null_and_widget_is_not_in_a_Group() throws Exception {

        commandUnderTest = new CreateFormCommand(null,
                ProcessPackage.Literals.PAGE_FLOW__FORM,
                "Test form",
                "Test form description",
                Collections.<WidgetMapping> emptyList(),
                null);

        final Widget w1 = FormFactory.eINSTANCE.createTextFormField();
        final Form form = FormFactory.eINSTANCE.createForm();
        form.getWidgets().add(w1);
        final String keyName = "titi";

        final String keyResult = commandUnderTest.computeWidgetId(keyName, w1);
        assertThat(keyResult).isEqualTo(keyName);
    }

    @Test
    public void should_computeWidgetId_return_key_without_number_when_pageflow_is_not_null_and_widget_is_in_a_Group() throws Exception {

        final Widget w1 = FormFactory.eINSTANCE.createTextFormField();
        final Group group = FormFactory.eINSTANCE.createGroup();
        group.setName("theGroup");
        group.getWidgets().add(w1);
        final String keyName = "titi";

        final String keyResult = commandUnderTest.computeWidgetId(keyName, w1);
        assertThat(keyResult).isEqualTo("theGroup" + "_" + keyName + "1");
    }

    @Test
    public void should_computeWidgetId_return_key_without_number_when_pageflow_is_not_null_and_widget_is_not_in_a_Group() throws Exception {

        final Widget w1 = FormFactory.eINSTANCE.createTextFormField();
        final Form form = FormFactory.eINSTANCE.createForm();
        form.getWidgets().add(w1);
        final String keyName = "titi";

        final String keyResult = commandUnderTest.computeWidgetId(keyName, w1);
        assertThat(keyResult).isEqualTo(keyName + "1");
    }

    @Test
    public void should_supportMandatory_return_false_if_widget_is_a_HiddenWidget() throws Exception {
        final HiddenWidget widget = FormFactory.eINSTANCE.createHiddenWidget();
        assertThat(commandUnderTest.supportMandatory(widget)).isFalse();
    }

    @Test
    public void should_supportMandatory_return_true_if_widget_is_a_Group() throws Exception {
        final Group widget = FormFactory.eINSTANCE.createGroup();
        assertThat(commandUnderTest.supportMandatory(widget)).isTrue();
    }

    @Test
    public void should_supportMandatory_return_true_if_widget_is_a_FormField_and_not_a_HiddenWidget() throws Exception {
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        assertThat(commandUnderTest.supportMandatory(widget)).isTrue();
    }

    @Test
    public void should_supportReadOnly_return_false_if_widget_is_a_FileWidget() throws Exception {
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        assertThat(commandUnderTest.supportReadOnly(widget)).isFalse();
    }

    @Test
    public void should_supportReadOnly_return_true_if_widget_is_a_Group() throws Exception {
        final Group widget = FormFactory.eINSTANCE.createGroup();
        assertThat(commandUnderTest.supportReadOnly(widget)).isTrue();
    }

    @Test
    public void should_supportReadOnly_return_true_if_widget_is_not_a_FileWidget() throws Exception {
        final TextFormField widget = FormFactory.eINSTANCE.createTextFormField();
        assertThat(commandUnderTest.supportReadOnly(widget)).isTrue();
    }

    @Test
    public void should_addInputExpressionForData_set_widget_input_expression_with_fixed_return_type_if_widget_is_CheckBoxSingleFormField() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("amazingData");
        final CheckBoxSingleFormField widget = FormFactory.eINSTANCE.createCheckBoxSingleFormField();
        commandUnderTest.addInputExpressionForData(data, widget);
        assertThat(widget.getInputExpression().isReturnTypeFixed()).isTrue();
    }

    @Test
    public void should_addInputExpressionForData_set_widget_input_expression_with_not_fixed_return_type_if_widget_is_CheckBoxSingleFormField() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("amazingData");
        final TextFormField widget = FormFactory.eINSTANCE.createTextFormField();
        commandUnderTest.addInputExpressionForData(data, widget);
        ExpressionAssert.assertThat(widget.getInputExpression()).isNotReturnTypeFixed();
        ExpressionAssert.assertThat(widget.getInputExpression()).hasType(ExpressionConstants.VARIABLE_TYPE);
    }

    @Test
    public void should_isAnEnumOnAMultipleValuatedFormField_return_false_if_widget_is_not_MultipleValuatedFormField() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        final DataType value = ProcessFactory.eINSTANCE.createEnumType();
        data.setDataType(value);
        final TextFormField textFormField = FormFactory.eINSTANCE.createTextFormField();
        assertThat(commandUnderTest.isAnEnumOnAMultipleValuatedFormField(data, textFormField)).isFalse();

    }

    @Test
    public void should_isAnEnumOnAMultipleValuatedFormField_return_false_if_data_is_not_an_EnumType() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        final DataType value = ProcessFactory.eINSTANCE.createBooleanType();
        data.setDataType(value);
        final ListFormField listFormField = FormFactory.eINSTANCE.createListFormField();
        assertThat(commandUnderTest.isAnEnumOnAMultipleValuatedFormField(data, listFormField)).isFalse();
    }

    @Test
    public void should_isAnEnumOnAMultipleValuatedFormField_return_true_if_data_is_EnumType_and_widge_is_MultipleValuatedFormField() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        final DataType value = ProcessFactory.eINSTANCE.createEnumType();
        data.setDataType(value);
        final ListFormField listFormField = FormFactory.eINSTANCE.createListFormField();
        assertThat(commandUnderTest.isAnEnumOnAMultipleValuatedFormField(data, listFormField)).isTrue();
    }

    @Test
    public void should_isOnInstantiationForm_return_false_if_feature_is_not_PAGE_FLOW__FORM() throws Exception {
        commandUnderTest = new CreateFormCommand(ProcessFactory.eINSTANCE.createPool(),
                null,
                "Test form",
                "Test form description",
                Collections.<WidgetMapping> emptyList(),
                null);
        final Data data = ProcessFactory.eINSTANCE.createData();
        assertThat(commandUnderTest.isOnInstantiationForm(data)).isFalse();
    }

    @Test
    public void should_isOnInstantiationForm_return_false_if_feature_is_pageFlow_is_not_Pool() throws Exception {
        commandUnderTest = new CreateFormCommand(ProcessFactory.eINSTANCE.createTask(),
                ProcessPackage.Literals.PAGE_FLOW__FORM,
                "Test form",
                "Test form description",
                Collections.<WidgetMapping> emptyList(),
                null);
        final Data data = ProcessFactory.eINSTANCE.createData();
        assertThat(commandUnderTest.isOnInstantiationForm(data)).isFalse();
    }

    @Test
    public void should_hasOutputOperation_return_false_is_widget_is_Info() throws Exception {
        final Info info = FormFactory.eINSTANCE.createInfo();
        assertThat(commandUnderTest.hasOutputOperation(info)).isFalse();
    }

    @Test
    public void should_hasOutputOperation_return_false_is_widget_is_Table() throws Exception {
        final Table table = FormFactory.eINSTANCE.createTable();
        assertThat(commandUnderTest.hasOutputOperation(table)).isFalse();
    }

    @Test
    public void should_hasOutputOperation_return_true_is_widget_is_neither_Table_or_Info() throws Exception {
        final Group group = FormFactory.eINSTANCE.createGroup();
        assertThat(commandUnderTest.hasOutputOperation(group)).isTrue();
    }

    @Test
    public void should_canUndo_return_false() throws Exception {
        assertThat(commandUnderTest.canUndo()).isFalse();
    }

    @Test
    public void should_createVariableExpression_return_an_Expression_with_VARIABLE_TYPE() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("theData");
        final Expression resExpr = commandUnderTest.createVariableExpression(data);
        ExpressionAssert.assertThat(resExpr).hasType(ExpressionConstants.VARIABLE_TYPE);
    }

    @Test
    public void should_createWidgetExpression_return_expression_with_name_start_with_prefix_and_type_is_FORM_FIELD_TYPE() throws Exception {
        final TextFormField text = FormFactory.eINSTANCE.createTextFormField();
        text.setName("myTextForm");
        final Expression resExpr = commandUnderTest.createWidgetExpression(text);
        ExpressionAssert.assertThat(resExpr).hasType(ExpressionConstants.FORM_FIELD_TYPE);
        assertThat(resExpr.getName()).startsWith(WidgetHelper.FIELD_PREFIX);
    }

    @Test
    public void should_createDataOutputOperation_returns_Operation_with_assignement_with_ASSIGNMENT_OPERATOR_type() throws Exception {
        final TextFormField text = FormFactory.eINSTANCE.createTextFormField();
        text.setName("myTextForm");
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("theData");

        final Operation ope = commandUnderTest.createDataOutputOperation(text, data);
        assertThat(ope.getOperator()).isNotNull();
        assertThat(ope.getOperator().getType()).isEqualTo(ExpressionConstants.ASSIGNMENT_OPERATOR);
        assertThat(ope.getLeftOperand().getType().equals(ExpressionConstants.VARIABLE_TYPE));
        assertThat(ope.getRightOperand().getType().equals(ExpressionConstants.FORM_FIELD_TYPE));
    }

    @Test
    public void should_createWidgetLayout_returns_WidgetLayoutInfo_with_values_of_entry_parameters() throws Exception {
        final int nLine = 10;
        final int nCol = 22;
        final int horizontalSpan = 33;
        final int verticalSpan = 40;
        final WidgetLayoutInfo wLayout = commandUnderTest.createWidgetLayout(nLine, nCol, horizontalSpan, verticalSpan);
        assertThat(wLayout.getColumn()).isEqualTo(nCol);
        assertThat(wLayout.getLine()).isEqualTo(nLine);
        assertThat(wLayout.getHorizontalSpan()).isEqualTo(horizontalSpan);
        assertThat(wLayout.getVerticalSpan()).isEqualTo(verticalSpan);
    }

    @Test
    public void shouldAddMappingExpressions_AddFieldMappings() throws Exception {
        final Widget widget = FormFactory.eINSTANCE.createTextFormField();
        final WidgetMapping widgetMappingEx = new WidgetMapping(data);
        final WidgetMapping child = new WidgetMapping(firstNameAttribute);
        widgetMappingEx.addChild(child);
        commandUnderTest.addMappingExpressions(child, widget);
        verify(commandUnderTest).addInputExpressionForBusinessDataField(eq(data), any(SimpleField.class), eq(widget));
        verify(commandUnderTest).addOutputOperationForBusinessDataField(eq(data), any(SimpleField.class), eq(widget));
    }

    @Test
    public void shouldAddOutputOperationForBusinessDataField_AddActionOnWidget() throws Exception {
        final Widget widget = FormFactory.eINSTANCE.createTextFormField();
        widget.setName("firstName1");
        commandUnderTest.addOutputOperationForBusinessDataField(data, firstNameAttribute, widget);
        final Operation action = widget.getAction();
        assertThat(action).isNotNull();
        final Expression leftOperand = action.getLeftOperand();
        assertThat(leftOperand).isNotNull();
        assertThat(leftOperand.getType()).isEqualTo(ExpressionConstants.VARIABLE_TYPE);
        assertThat(leftOperand.getName()).isEqualTo("currentEmployee");
        assertThat(leftOperand.getReferencedElements()).isNotEmpty().hasSize(1);
        assertThat(leftOperand.getReturnType()).isEqualTo("org.bonita.pojo.Employee");
        final Expression rightOperand = action.getRightOperand();
        assertThat(rightOperand).isNotNull();
        assertThat(rightOperand.getType()).isEqualTo(ExpressionConstants.FORM_FIELD_TYPE);
        assertThat(rightOperand.getName()).isEqualTo("field_firstName1");
        assertThat(rightOperand.getReferencedElements()).isNotEmpty().hasSize(1);
        assertThat(rightOperand.getReturnType()).isEqualTo(String.class.getName());
        final Operator operator = action.getOperator();
        assertThat(operator).isNotNull();
        assertThat(operator.getType()).isEqualTo(ExpressionConstants.JAVA_METHOD_OPERATOR);
        assertThat(operator.getExpression()).isNotNull().isEqualTo("setFirstName");
        assertThat(operator.getInputTypes()).isNotEmpty().containsOnly(String.class.getName());
    }

    @Test
    public void shouldAddInputExpressionForBusinessDataEAttribute_AddInputExpressionOnWidget() throws Exception {
        final Widget widget = FormFactory.eINSTANCE.createTextFormField();
        widget.setName("firstName1");
        commandUnderTest.addInputExpressionForBusinessDataField(data, firstNameAttribute, widget);
        final Expression inputExpression = widget.getInputExpression();
        assertThat(inputExpression).isNotNull();
        assertThat(inputExpression.getType()).isEqualTo(ExpressionConstants.JAVA_TYPE);
        assertThat(inputExpression.getName()).isEqualTo("currentEmployee - org.bonita.pojo.Employee#getFirstName");
        assertThat(inputExpression.getContent()).isEqualTo("getFirstName");
        assertThat(inputExpression.getReferencedElements()).isNotEmpty().hasSize(1);
        assertThat(inputExpression.getReturnType()).isEqualTo(String.class.getName());
    }

    @Test
    public void shouldGetSetterFor_ReturnValidNameForStringAttribute() throws Exception {
        final String setterName = commandUnderTest.getSetterFor(firstNameAttribute);
        assertThat(setterName).isNotNull().isEqualTo("setFirstName");
    }

    @Test
    public void shouldGetGetterFor_ReturnValidNameForStringAttribute() throws Exception {
        final String setterName = commandUnderTest.getGetterFor(firstNameAttribute);
        assertThat(setterName).isNotNull().isEqualTo("getFirstName");
    }

    @Test
    public void shouldGetGetterFor_ReturnValidNameForBooleanAttribute() throws Exception {
        final String setterName = commandUnderTest.getGetterFor(validAttribute);
        assertThat(setterName).isNotNull().isEqualTo("isValid");
    }

    @Test
    public void shoulSetMaxLength_SetMaxLengthAttributeOnTextField() throws Exception {
        final TextFormField widget = FormFactory.eINSTANCE.createTextFormField();
        final SimpleField f = new SimpleField();
        f.setName("name");
        f.setType(FieldType.STRING);
        f.setLength(255);
        commandUnderTest.setMaxLength(widget, f);
        assertThat(widget.getMaxLength()).isEqualTo(f.getLength());
    }

}
