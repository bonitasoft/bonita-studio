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
package org.bonitasoft.studio.engine.export;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aVariableExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.expression.builders.OperatorBuilder.anAssignmentOperator;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.assertions.EngineExpressionAssert;
import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.bonitasoft.studio.model.process.builders.DocumentBuilder;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

public class EngineExpressionUtilTest {

    private Expression createStudioExpression(final String name, final String content,
            final String returnType, final String type, final EObject... dependencies) {
        final Expression studioExpression = ExpressionFactory.eINSTANCE.createExpression();
        studioExpression.setType(type);
        studioExpression.setReturnType(returnType);
        studioExpression.setContent(content);
        studioExpression.setName(name);
        studioExpression.getReferencedElements().addAll(Arrays.asList(dependencies));
        return studioExpression;
    }

    @Test
    public void should_createExpression_document() throws Exception {
        //given
        final Expression studioExpression = createStudioExpression("document1", "document1",
                "org.bonitasoft.engine.bpm.document.DocumentValue",
                ExpressionConstants.DOCUMENT_TYPE);

        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("document1")
                .hasContent("document1")
                .hasReturnType(org.bonitasoft.engine.bpm.document.Document.class.getName())
                .hasExpressionType(ExpressionType.TYPE_DOCUMENT.name());
    }

    @Test
    public void should_createExpression_document_list() throws Exception {
        //given
        final Expression studioExpression = createStudioExpression("document1", "document1", List.class.getName(),
                ExpressionConstants.DOCUMENT_TYPE);
        studioExpression.getReferencedElements().add(DocumentBuilder.aDocument().multiple().build());

        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("document1")
                .hasContent("document1")
                .hasReturnType(List.class.getName())
                .hasExpressionType(ExpressionType.TYPE_DOCUMENT_LIST.name());
    }

    @Test
    public void should_createExpression_constant() throws Exception {
        //given
        final Expression studioExpression = createStudioExpression("12", "12", Long.class.getName(),
                ExpressionConstants.CONSTANT_TYPE);

        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("12")
                .hasContent("12")
                .hasReturnType(Long.class.getName())
                .hasExpressionType(ExpressionType.TYPE_CONSTANT.name())
                .hasNoDependencies();
    }

    @Test
    public void should_createExpression_groovy() throws Exception {
        //given
        final Expression studioExpression = createStudioExpression("myScript", "return 12", Long.class.getName(),
                ExpressionConstants.SCRIPT_TYPE);
        studioExpression.setInterpreter(ExpressionConstants.GROOVY);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("myScript")
                .hasContent("return 12")
                .hasReturnType(Long.class.getName())
                .hasExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name())
                .hasInterpreter(ExpressionConstants.GROOVY);
    }

    @Test
    public void should_createExpression_data() throws Exception {
        //given

        final Data stringData = createData("myData", false, DatasourceConstants.BOS_DATASOURCE);
        final Expression studioExpression = createStudioExpression("a", "a", String.class.getName(),
                ExpressionConstants.VARIABLE_TYPE, stringData);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("a")
                .hasContent("a")
                .hasReturnType(String.class.getName())
                .hasExpressionType(ExpressionType.TYPE_VARIABLE.name())
                .hasNoDependencies();
    }

    @Test
    public void should_createExpression_form_transient_data() throws Exception {
        //given

        final Data stringData = createData("myData", false, DatasourceConstants.PAGEFLOW_DATASOURCE);
        final Expression studioExpression = createStudioExpression("a", "a", String.class.getName(),
                ExpressionConstants.VARIABLE_TYPE, stringData);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("a")
                .hasContent("a")
                .hasReturnType(String.class.getName())
                .hasExpressionType(ExpressionType.TYPE_INPUT.name())
                .hasNoDependencies();
    }

    @Test
    public void should_createExpression_transient_data() throws Exception {
        //given

        final Data stringData = createData("myData", true, DatasourceConstants.IN_MEMORY_DATASOURCE);
        final Expression studioExpression = createStudioExpression("a", "a", String.class.getName(),
                ExpressionConstants.VARIABLE_TYPE, stringData);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("a")
                .hasContent("a")
                .hasReturnType(String.class.getName())
                .hasExpressionType(ExpressionType.TYPE_TRANSIENT_VARIABLE.name())
                .hasNoDependencies();
    }

    @Test
    public void should_createExpression_groovy_with_data_dep() throws Exception {
        //given

        final Data stringData = createData("myData", false, DatasourceConstants.BOS_DATASOURCE);
        final Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'",
                String.class.getName(), ExpressionConstants.SCRIPT_TYPE,
                stringData);
        studioExpression.setInterpreter(ExpressionConstants.GROOVY);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("myScript")
                .hasContent("return myData+'plop'")
                .hasReturnType(String.class.getName())
                .hasExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name())
                .hasInterpreter(ExpressionConstants.GROOVY);
        EngineExpressionAssert.assertThat(engineExpression.getDependencies().get(0))
                .hasName("myData").hasReturnType(String.class.getName())
                .hasExpressionType(ExpressionType.TYPE_VARIABLE.name());
    }

    @Test
    public void should_createExpression_groovy_with_transient_data_dep() throws Exception {
        //given

        final Data stringData = createData("myData", true, DatasourceConstants.BOS_DATASOURCE);
        final Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'",
                String.class.getName(), ExpressionConstants.SCRIPT_TYPE,
                stringData);
        studioExpression.setInterpreter(ExpressionConstants.GROOVY);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("myScript")
                .hasContent("return myData+'plop'")
                .hasReturnType(String.class.getName())
                .hasExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name())
                .hasInterpreter(ExpressionConstants.GROOVY);
        EngineExpressionAssert.assertThat(engineExpression.getDependencies().get(0))
                .hasName("myData").hasReturnType(String.class.getName())
                .hasExpressionType(ExpressionType.TYPE_TRANSIENT_VARIABLE.name());
    }

    @Test
    public void should_createExpression_groovy_with_form_transient_data_dep() throws Exception {
        //given
        final Data stringData = createData("myData", false, DatasourceConstants.PAGEFLOW_DATASOURCE);
        final Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'",
                String.class.getName(), ExpressionConstants.SCRIPT_TYPE,
                stringData);
        studioExpression.setInterpreter(ExpressionConstants.GROOVY);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(studioExpression);

        //then
        EngineExpressionAssert.assertThat(engineExpression)
                .hasName("myScript")
                .hasContent("return myData+'plop'")
                .hasReturnType(String.class.getName())
                .hasExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name())
                .hasInterpreter(ExpressionConstants.GROOVY);
        EngineExpressionAssert.assertThat(engineExpression.getDependencies().get(0))
                .hasName("myData").hasReturnType(String.class.getName()).hasExpressionType(ExpressionType.TYPE_INPUT.name());
    }

    private Data createData(final String name, final boolean isTransient, final String datasource) {
        final Data stringData = ProcessFactory.eINSTANCE.createData();
        stringData.setDatasourceId(datasource);
        stringData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        stringData.setName(name);
        stringData.setTransient(isTransient);
        return stringData;
    }

    @Test
    public void shouldCreateVariableExpression_SetVariableExpressionType() throws Exception {
        final Data textData = ProcessFactory.eINSTANCE.createData();
        textData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        textData.setName("textData");
        assertThat(EngineExpressionUtil.createVariableExpression(textData).getExpressionType())
                .isEqualTo(ExpressionType.TYPE_VARIABLE.name());
    }

    @Test
    public void shouldCreateVariableExpression_SetBusinessObjectExpressionType() throws Exception {
        final BusinessObjectData businessObjectData = ProcessFactory.eINSTANCE.createBusinessObjectData();
        businessObjectData.setDataType(ProcessFactory.eINSTANCE.createBusinessObjectType());
        businessObjectData.setName("leaveRequest");
        businessObjectData.setClassName("org.bonita.business.LeaveRequest");
        final org.bonitasoft.engine.expression.Expression createVariableExpression = EngineExpressionUtil
                .createVariableExpression(businessObjectData);
        assertThat(createVariableExpression.getExpressionType()).isEqualTo("TYPE_BUSINESS_DATA");
        assertThat(createVariableExpression.getReturnType()).isEqualTo("org.bonita.business.LeaveRequest");
    }

    @Test
    public void shouldGetOperatorType_ReturnBusinessDataJavaSetter() throws Exception {
        final BusinessObjectData businessObjectData = ProcessFactory.eINSTANCE.createBusinessObjectData();
        businessObjectData.setDataType(ProcessFactory.eINSTANCE.createBusinessObjectType());
        businessObjectData.setName("leaveRequest");
        businessObjectData.setClassName("org.bonita.business.LeaveRequest");
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression businessDataExpression = ExpressionFactory.eINSTANCE
                .createExpression();
        businessDataExpression.setContent("businessDataExpression");
        businessDataExpression.getReferencedElements().add(businessObjectData);
        operation.setLeftOperand(businessDataExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.JAVA_METHOD_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getOperatorType(operation))
                .isEqualTo(ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR);
    }

    @Test
    public void shouldGetEngineOperatorType_Return_assignment_for_SetDocument() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression documentExpression = ExpressionFactory.eINSTANCE
                .createExpression();
        documentExpression.setContent("docName");
        documentExpression.getReferencedElements().add(document);
        operation.setLeftOperand(documentExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getEngineOperator(operation).name())
                .isEqualTo(ExpressionConstants.ASSIGNMENT_OPERATOR);
    }

    @Test
    public void shouldGetEngineOperatorType_Return_assignment_for_SetDocumentList() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        document.setMultiple(true);
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression documentExpression = ExpressionFactory.eINSTANCE
                .createExpression();
        documentExpression.setContent("docName");
        documentExpression.getReferencedElements().add(document);
        operation.setLeftOperand(documentExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getEngineOperator(operation).name())
                .isEqualTo(ExpressionConstants.ASSIGNMENT_OPERATOR);//TODO: [BS-9610] change it if a specific operation is created in the Engine
    }

    @Test
    public void shouldGetEngineLeftOperandType_Return_DocumentList() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        document.setMultiple(true);
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression documentExpression = ExpressionFactory.eINSTANCE
                .createExpression();
        documentExpression.setContent("docName");
        documentExpression.setType(ExpressionConstants.DOCUMENT_TYPE);
        documentExpression.getReferencedElements().add(document);
        operation.setLeftOperand(documentExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getLeftOperandType(documentExpression, false))
                .isEqualTo(ExpressionConstants.LEFT_OPERAND_DOCUMENT_LIST);
    }

    @Test
    public void shouldGetEngineLeftOperandType_Return_DocumentSimple() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression documentExpression = ExpressionFactory.eINSTANCE
                .createExpression();
        documentExpression.setContent("docName");
        documentExpression.setType(ExpressionConstants.DOCUMENT_TYPE);
        documentExpression.getReferencedElements().add(document);
        operation.setLeftOperand(documentExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getLeftOperandType(documentExpression, false))
                .isEqualTo(ExpressionConstants.LEFT_OPERAND_DOCUMENT);
    }

    @Test
    public void shouldGetEngineLeftOperandType_Return_External() throws Exception {
        assertThat(EngineExpressionUtil.getLeftOperandType(null, true))
                .isEqualTo(ExpressionConstants.LEFT_OPERAND_EXTERNAL_DATA);
    }

    @Test
    public void toEngineExpressionType_returnDocumentListForDocumentReferenceList() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setMultiple(true);
        final Expression expression = ExpressionHelper.createDocumentReferenceExpression(document);
        assertThat(EngineExpressionUtil.toEngineExpressionType(expression)).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST);
    }

    @Test
    public void toEngineExpressionType_returnDocumentListForDocumentReferenceSimple() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        final Expression expression = ExpressionHelper.createDocumentReferenceExpression(document);
        assertThat(EngineExpressionUtil.toEngineExpressionType(expression)).isEqualTo(ExpressionType.TYPE_CONSTANT);
    }

    @Test
    public void testCreateExpressionForDocumentList() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        document.setMultiple(true);
        final Expression expressionWithDocumentList = ExpressionHelper.createExpressionFromDocument(document);
        expressionWithDocumentList.setType(ExpressionConstants.DOCUMENT_LIST_TYPE);
        expressionWithDocumentList.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final org.bonitasoft.engine.expression.Expression createdExpression = EngineExpressionUtil
                .createExpression(expressionWithDocumentList);
        assertThat(createdExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST.name());
    }

    @Test
    public void testCreateExpressionForDocumentSimple() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        final Expression expressionWithDocumentSimple = ExpressionHelper.createExpressionFromDocument(document);
        expressionWithDocumentSimple.setType(ExpressionConstants.DOCUMENT_TYPE);
        expressionWithDocumentSimple.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final org.bonitasoft.engine.expression.Expression createdExpression = EngineExpressionUtil
                .createExpression(expressionWithDocumentSimple);
        assertThat(createdExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT.name());
    }

    @Test
    public void testCreateExpressionForDocumentRefSimple() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        final Expression expressionWithDocumentList = ExpressionHelper.createExpressionFromDocument(document);
        expressionWithDocumentList.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        expressionWithDocumentList.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final org.bonitasoft.engine.expression.Expression createdExpression = EngineExpressionUtil
                .createExpression(expressionWithDocumentList);
        assertThat(createdExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_CONSTANT.name());
        assertThat(createdExpression.getReturnType()).isEqualTo(String.class.getName());
    }

    @Test
    public void testCreateExpressionForDocumentRefMultiple() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        document.setMultiple(true);
        final Expression expressionWithDocumentList = ExpressionHelper.createExpressionFromDocument(document);
        expressionWithDocumentList.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        expressionWithDocumentList.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final org.bonitasoft.engine.expression.Expression createdExpression = EngineExpressionUtil
                .createExpression(expressionWithDocumentList);
        assertThat(createdExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST.name());
        assertThat(createdExpression.getReturnType()).isEqualTo(List.class.getName());
    }

    @Test
    public void testCreateDependenciesListForMultipleDocument() throws Exception {
        final Expression expression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        expression.setName("script");
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        document.setMultiple(true);
        expression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final List<org.bonitasoft.engine.expression.Expression> dependenciesList = EngineExpressionUtil
                .createDependenciesList(expression);

        assertThat(dependenciesList.get(0).getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST.name());
        assertThat(dependenciesList.get(0).getReturnType()).isEqualTo(List.class.getName());
    }

    @Test
    public void testCreateDependenciesListForSimpleDocument() throws Exception {
        final Expression expression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        expression.setName("script");
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        expression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final List<org.bonitasoft.engine.expression.Expression> dependenciesList = EngineExpressionUtil
                .createDependenciesList(expression);

        assertThat(dependenciesList.get(0).getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT.name());
    }

    @Test
    public void testCreateBusinessDataObjectReference() {
        final BusinessObjectData data = BusinessObjectDataBuilder.aBusinessData().withName("bName")
                .withClassname("my.classname").build();

        final org.bonitasoft.engine.expression.Expression expression = EngineExpressionUtil
                .createBusinessObjectDataReferenceExpression(data);

        assertThat(expression.getName()).isEqualTo("bName");
        assertThat(expression.getExpressionType()).isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());
    }

    @Test
    public void should_create_a_message_operation_from_a_message_id() throws Exception {
        org.bonitasoft.engine.operation.Operation operation = EngineExpressionUtil
                .createOperationForMessageContent(anOperation()
                        .havingLeftOperand(aVariableExpression().withName("myData"))
                        .havingRightOperand(
                                anExpression().withExpressionType(ExpressionConstants.MESSAGE_ID_TYPE)
                                        .withName("dataFromMessage").withContent("dataFromMessage")
                                        .withReturnType(String.class.getName()))
                        .havingOperator(anAssignmentOperator())
                        .build());

        assertThat(operation.getRightOperand().getExpressionType()).isEqualTo(ExpressionType.TYPE_VARIABLE.name());
    }

    @Test
    public void should_create_a_message_operation_from_a_constant() throws Exception {
        org.bonitasoft.engine.operation.Operation operation = EngineExpressionUtil
                .createOperationForMessageContent(anOperation()
                        .havingLeftOperand(aVariableExpression().withName("myData"))
                        .havingRightOperand(
                                anExpression().withExpressionType(ExpressionConstants.CONSTANT_TYPE)
                                        .withName("dataFromMessage").withContent("dataFromMessage")
                                        .withReturnType(String.class.getName()))
                        .havingOperator(anAssignmentOperator())
                        .build());

        assertThat(operation.getRightOperand().getExpressionType()).isEqualTo(ExpressionType.TYPE_VARIABLE.name());
    }
}
