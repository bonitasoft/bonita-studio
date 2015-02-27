package org.bonitasoft.studio.engine.export;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.document.core.expression.DocumentReferenceExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.ecore.EObject;
import org.junit.Ignore;
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
        final Expression studioExpression = createStudioExpression("document1", "document1", "org.bonitasoft.engine.bpm.document.DocumentValue",
                ExpressionConstants.DOCUMENT_TYPE);

        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        assertEquals(new ExpressionBuilder().createDocumentReferenceExpression("document1"), engineExpression);
    }

    @Test
    @Ignore("interpreter null vs empty")
    public void should_createExpression_constant() throws Exception {
        //given
        final Expression studioExpression = createStudioExpression("12", "12", Long.class.getName(), ExpressionConstants.CONSTANT_TYPE);

        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        assertEquals(new ExpressionBuilder().createConstantLongExpression(12), engineExpression);
    }

    @Test
    public void should_createExpression_groovy() throws Exception {
        //given
        final Expression studioExpression = createStudioExpression("myScript", "return 12", Long.class.getName(), ExpressionConstants.SCRIPT_TYPE);
        studioExpression.setInterpreter(ExpressionConstants.GROOVY);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        assertEquals(new ExpressionBuilder().createGroovyScriptExpression("myScript", "return 12", Long.class.getName()), engineExpression);
    }

    @Test
    @Ignore("interpreter null vs empty")
    public void should_createExpression_data() throws Exception {
        //given

        final Data stringData = createData("myData", false, DatasourceConstants.BOS_DATASOURCE);
        final Expression studioExpression = createStudioExpression("a", "a", String.class.getName(), ExpressionConstants.VARIABLE_TYPE, stringData);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        assertEquals(new ExpressionBuilder().createDataExpression("a", String.class.getName()), engineExpression);
    }

    @Test
    @Ignore("interpreter null vs empty")
    public void should_createExpression_form_transient_data() throws Exception {
        //given

        final Data stringData = createData("myData", false, DatasourceConstants.PAGEFLOW_DATASOURCE);
        final Expression studioExpression = createStudioExpression("a", "a", String.class.getName(), ExpressionConstants.VARIABLE_TYPE, stringData);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        assertEquals(new ExpressionBuilder().createInputExpression("a", String.class.getName()), engineExpression);
    }

    @Test
    @Ignore("interpreter null vs empty")
    public void should_createExpression_transient_data() throws Exception {
        //given

        final Data stringData = createData("myData", true, DatasourceConstants.IN_MEMORY_DATASOURCE);
        final Expression studioExpression = createStudioExpression("a", "a", String.class.getName(), ExpressionConstants.VARIABLE_TYPE, stringData);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        assertEquals(new ExpressionBuilder().createTransientDataExpression("a", String.class.getName()), engineExpression);
    }

    @Test
    public void should_createExpression_groovy_with_data_dep() throws Exception {
        //given

        final Data stringData = createData("myData", false, DatasourceConstants.BOS_DATASOURCE);
        final Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'", String.class.getName(), ExpressionConstants.SCRIPT_TYPE,
                stringData);
        studioExpression.setInterpreter(ExpressionConstants.GROOVY);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        final org.bonitasoft.engine.expression.Expression excpectedExpression = new ExpressionBuilder().createGroovyScriptExpression("myScript",
                "return myData+'plop'", String.class.getName(), new ExpressionBuilder().createDataExpression("myData", String.class.getName()));
        assertEquals(excpectedExpression, engineExpression);
    }

    @Test
    public void should_createExpression_groovy_with_transient_data_dep() throws Exception {
        //given

        final Data stringData = createData("myData", true, DatasourceConstants.BOS_DATASOURCE);
        final Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'", String.class.getName(), ExpressionConstants.SCRIPT_TYPE,
                stringData);
        studioExpression.setInterpreter(ExpressionConstants.GROOVY);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        final org.bonitasoft.engine.expression.Expression excpectedExpression = new ExpressionBuilder().createGroovyScriptExpression("myScript",
                "return myData+'plop'", String.class.getName(), new ExpressionBuilder().createTransientDataExpression("myData", String.class.getName()));
        assertEquals(excpectedExpression, engineExpression);
    }

    @Test
    public void should_createExpression_groovy_with_form_transient_data_dep() throws Exception {
        //given
        final Data stringData = createData("myData", false, DatasourceConstants.PAGEFLOW_DATASOURCE);
        final Expression studioExpression = createStudioExpression("myScript", "return myData+'plop'", String.class.getName(), ExpressionConstants.SCRIPT_TYPE,
                stringData);
        studioExpression.setInterpreter(ExpressionConstants.GROOVY);
        //when
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(studioExpression);

        //then
        final org.bonitasoft.engine.expression.Expression excpectedExpression = new ExpressionBuilder().createGroovyScriptExpression("myScript",
                "return myData+'plop'", String.class.getName(), new ExpressionBuilder().createInputExpression("myData", String.class.getName()));
        assertEquals(excpectedExpression, engineExpression);
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
        assertThat(EngineExpressionUtil.createVariableExpression(textData).getExpressionType()).isEqualTo(ExpressionType.TYPE_VARIABLE.name());
    }

    @Test
    public void shouldCreateVariableExpression_SetBusinessObjectExpressionType() throws Exception {
        final BusinessObjectData businessObjectData = ProcessFactory.eINSTANCE.createBusinessObjectData();
        businessObjectData.setDataType(ProcessFactory.eINSTANCE.createBusinessObjectType());
        businessObjectData.setName("leaveRequest");
        businessObjectData.setClassName("org.bonita.business.LeaveRequest");
        final org.bonitasoft.engine.expression.Expression createVariableExpression = EngineExpressionUtil.createVariableExpression(businessObjectData);
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
        final org.bonitasoft.studio.model.expression.Expression businessDataExpression = ExpressionFactory.eINSTANCE.createExpression();
        businessDataExpression.setContent("businessDataExpression");
        businessDataExpression.getReferencedElements().add(businessObjectData);
        operation.setLeftOperand(businessDataExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.JAVA_METHOD_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getOperatorType(operation)).isEqualTo(ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR);
    }

    @Test
    public void shouldGetEngineOperatorType_Return_assignment_for_SetDocument() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression documentExpression = ExpressionFactory.eINSTANCE.createExpression();
        documentExpression.setContent("docName");
        documentExpression.getReferencedElements().add(document);
        operation.setLeftOperand(documentExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getEngineOperator(operation).name()).isEqualTo(ExpressionConstants.ASSIGNMENT_OPERATOR);
    }

    @Test
    public void shouldGetEngineOperatorType_Return_assignment_for_SetDocumentList() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        document.setMultiple(true);
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression documentExpression = ExpressionFactory.eINSTANCE.createExpression();
        documentExpression.setContent("docName");
        documentExpression.getReferencedElements().add(document);
        operation.setLeftOperand(documentExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getEngineOperator(operation).name()).isEqualTo(ExpressionConstants.ASSIGNMENT_OPERATOR);//TODO: [BS-9610] change it if a specific operation is created in the Engine
    }

    @Test
    public void shouldGetEngineLeftOperandType_Return_DocumentList() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        document.setMultiple(true);
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression documentExpression = ExpressionFactory.eINSTANCE.createExpression();
        documentExpression.setContent("docName");
        documentExpression.setType(ExpressionConstants.DOCUMENT_TYPE);
        documentExpression.getReferencedElements().add(document);
        operation.setLeftOperand(documentExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getLeftOperandType(documentExpression, false)).isEqualTo(ExpressionConstants.LEFT_OPERAND_DOCUMENT_LIST);
    }

    @Test
    public void shouldGetEngineLeftOperandType_Return_DocumentSimple() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final org.bonitasoft.studio.model.expression.Expression documentExpression = ExpressionFactory.eINSTANCE.createExpression();
        documentExpression.setContent("docName");
        documentExpression.setType(ExpressionConstants.DOCUMENT_TYPE);
        documentExpression.getReferencedElements().add(document);
        operation.setLeftOperand(documentExpression);
        final Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        operation.setOperator(op);
        assertThat(EngineExpressionUtil.getLeftOperandType(documentExpression, false)).isEqualTo(ExpressionConstants.LEFT_OPERAND_DOCUMENT);
    }

    @Test
    public void shouldGetEngineLeftOperandType_Return_External() throws Exception {
        assertThat(EngineExpressionUtil.getLeftOperandType(null, true)).isEqualTo(ExpressionConstants.LEFT_OPERAND_EXTERNAL_DATA);
    }

    @Test
    public void toEngineExpressionType_returnDocumentListForDocumentReferenceList(){
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setMultiple(true);
        final Expression expression= new DocumentReferenceExpressionProvider().createDocRefExpression(document);
        assertThat(EngineExpressionUtil.toEngineExpressionType(expression)).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST);
    }

    @Test
    public void toEngineExpressionType_returnDocumentListForDocumentReferenceSimple() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        final Expression expression = new DocumentReferenceExpressionProvider().createDocRefExpression(document);
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
        final org.bonitasoft.engine.expression.Expression createdExpression = EngineExpressionUtil.createExpression(expressionWithDocumentList);
        assertThat(createdExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST.name());
    }

    @Test
    public void testCreateExpressionForDocumentSimple() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        final Expression expressionWithDocumentSimple = ExpressionHelper.createExpressionFromDocument(document);
        expressionWithDocumentSimple.setType(ExpressionConstants.DOCUMENT_TYPE);
        expressionWithDocumentSimple.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final org.bonitasoft.engine.expression.Expression createdExpression = EngineExpressionUtil.createExpression(expressionWithDocumentSimple);
        assertThat(createdExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT.name());
    }

    @Test
    public void testCreateExpressionForDocumentRefSimple() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        final Expression expressionWithDocumentList = ExpressionHelper.createExpressionFromDocument(document);
        expressionWithDocumentList.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        expressionWithDocumentList.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final org.bonitasoft.engine.expression.Expression createdExpression = EngineExpressionUtil.createExpression(expressionWithDocumentList);
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
        final org.bonitasoft.engine.expression.Expression createdExpression = EngineExpressionUtil.createExpression(expressionWithDocumentList);
        assertThat(createdExpression.getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST.name());
        assertThat(createdExpression.getReturnType()).isEqualTo(List.class.getName());
    }

    @Test
    public void testCreateDependenciesListForMultipleDocument() {
        final Expression expression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        expression.setName("script");
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        document.setMultiple(true);
        expression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final List<org.bonitasoft.engine.expression.Expression> dependenciesList = EngineExpressionUtil.createDependenciesList(expression);

        assertThat(dependenciesList.get(0).getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST.name());
        assertThat(dependenciesList.get(0).getReturnType()).isEqualTo(List.class.getName());
    }

    @Test
    public void testCreateDependenciesListForSimpleDocument() {
        final Expression expression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        expression.setName("script");
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("docName");
        expression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        final List<org.bonitasoft.engine.expression.Expression> dependenciesList = EngineExpressionUtil.createDependenciesList(expression);

        assertThat(dependenciesList.get(0).getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT.name());
    }
}
