/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.emf.tools;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.BooleanDataTypeBuilder.aBooleanDataType;
import static org.bonitasoft.studio.model.process.builders.DateDataTypeBuilder.aDateDataType;
import static org.bonitasoft.studio.model.process.builders.DoubleDataTypeBuilder.aDoubleDataType;
import static org.bonitasoft.studio.model.process.builders.IntegerDataTypeBuilder.anIntegerDataType;
import static org.bonitasoft.studio.model.process.builders.JavaDataTypeBuilder.aJavaDataType;
import static org.bonitasoft.studio.model.process.builders.LongDataTypeBuilder.aLongDataType;
import static org.bonitasoft.studio.model.process.builders.MainProcessBuilder.aMainProcess;
import static org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder.aStringDataType;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bpm.contract.FileInputValue;
import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.StringType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ExpressionHelperTest {

    /**
     * Test method for {@link org.bonitasoft.studio.common.emf.tools.ExpressionHelper#createDependencyFromEObject(org.eclipse.emf.ecore.EObject)}.
     */
    @Test
    public void shouldCreateDependencyFromEObject_CopyDataAndRemoveDefaultValue() throws Exception {
        final JavaObjectData myData = ProcessFactory.eINSTANCE.createJavaObjectData();
        myData.setName("dataName");
        myData.setDatasourceId("datasourceId");
        myData.setClassName("org.bonita.test.MyClass");
        myData.setTransient(true);
        myData.setGenerated(false);
        myData.setMultiple(true);
        myData.setDocumentation("some doc");
        myData.setDefaultValue(ExpressionFactory.eINSTANCE.createExpression());
        final DataType dataType = ProcessFactory.eINSTANCE.createJavaType();
        myData.setDataType(dataType);

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(myData);

        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(myData).isInstanceOf(JavaObjectData.class);
        assertThat(((JavaObjectData) dependencyFromEObject).getName()).isEqualTo(myData.getName());
        assertThat(((JavaObjectData) dependencyFromEObject).getDatasourceId()).isEqualTo(myData.getDatasourceId());
        assertThat(((JavaObjectData) dependencyFromEObject).getDocumentation()).isEqualTo(myData.getDocumentation());
        assertThat(((JavaObjectData) dependencyFromEObject).isGenerated()).isEqualTo(myData.isGenerated());
        assertThat(((JavaObjectData) dependencyFromEObject).isMultiple()).isEqualTo(myData.isMultiple());
        assertThat(((JavaObjectData) dependencyFromEObject).isTransient()).isEqualTo(myData.isTransient());
        assertThat(((JavaObjectData) dependencyFromEObject).getDataType()).isEqualTo(myData.getDataType());
        assertThat(((JavaObjectData) dependencyFromEObject).getDefaultValue()).isNull();
    }



    @Test
    public void shouldCreateDependencyFromEObject_CopyDocumentWithMultiplicty() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("document");
        document.setMultiple(true);

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(document);
        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(document).isInstanceOf(Document.class);
        assertThat(((Document) dependencyFromEObject).getName()).isEqualTo(document.getName());
        assertThat(((Document) dependencyFromEObject).isMultiple()).isTrue();
    }

    @Test
    public void shouldCreateDependencyFromEObject_CopySearchIndexWithoutName() throws Exception {
        final SearchIndex searchIndex = ProcessFactory.eINSTANCE.createSearchIndex();

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(searchIndex);
        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(searchIndex).isInstanceOf(SearchIndex.class);
    }

    @Test
    public void shouldCreateDependencyFromEObject_CopySearchIndexWithName() throws Exception {
        final SearchIndex searchIndex = ProcessFactory.eINSTANCE.createSearchIndex();
        searchIndex.setName(ExpressionHelper.createConstantExpression("searchIndex", String.class.getName()));

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(searchIndex);
        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(searchIndex).isInstanceOf(SearchIndex.class);
        assertThat(((SearchIndex) dependencyFromEObject).getName().getName()).isEqualTo(searchIndex.getName().getName());
    }


    @Test
    public void shouldClearExpression_SetEmptyExpression() throws Exception {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setName("Toto");
        expression.setContent("Titi2014");
        expression.setType(ExpressionConstants.SCRIPT_TYPE);
        expression.setReturnType(DocumentValue.class.getName());
        expression.getReferencedElements().add(ProcessFactory.eINSTANCE.createData());

        ExpressionHelper.clearExpression(expression);
        assertThat(expression.getName()).isEmpty();
        assertThat(expression.getContent()).isEmpty();
        assertThat(expression.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
        assertThat(expression.getReferencedElements()).isEmpty();
        assertThat(expression.getReturnType()).isEqualTo(String.class.getName());
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldClearExpression_ThrowIllegalArgumentException() throws Exception {
        ExpressionHelper.clearExpression(null);
    }

    @Test
    public void shouldCreateEmptyListGroovyScriptExpression_ReturnAValidEmptyListExpression() throws Exception {
        final Expression expression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        assertThat(expression).hasContent("[]").
                hasInterpreter(ExpressionConstants.GROOVY).
                hasType(ExpressionConstants.SCRIPT_TYPE).
                hasName(Messages.emptyListExpressionName).
                hasReturnType(Collection.class.getName());
    }

    @Test
    public void should_createExpressionFromEObject_Returns_a_VariableExpression_if_EObject_is_a_Data() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myVar");
        final DataType textDataType = ProcessFactory.eINSTANCE.createStringType();
        textDataType.setName(Messages.StringType);
        data.setDataType(textDataType);
        final Expression expression = ExpressionHelper.createExpressionFromEObject(data);
        assertThat(expression).hasContent(data.getName()).
                hasInterpreter("").
                hasType(ExpressionConstants.VARIABLE_TYPE).
                hasName(data.getName()).
                hasReturnType(String.class.getName());
        assertThat(expression.getReferencedElements()).hasSize(1);
        final EObject refElement = expression.getReferencedElements().get(0);
        assertThat(EcoreUtil.equals(data, refElement)).isTrue();
    }

    @Test
    public void should_createExpressionFromEObject_Returns_a_ParameterExpression_if_EObject_is_a_Parameter() throws Exception {
        final Parameter parameter = ParameterFactory.eINSTANCE.createParameter();
        parameter.setName("myParam");
        parameter.setTypeClassname(Integer.class.getName());
        final Expression expression = ExpressionHelper.createExpressionFromEObject(parameter);
        assertThat(expression).hasContent(parameter.getName()).
                hasInterpreter("").
                hasType(ExpressionConstants.PARAMETER_TYPE).
                hasName(parameter.getName()).
                hasReturnType(parameter.getTypeClassname());
        assertThat(expression.getReferencedElements()).hasSize(1);
        final EObject refElement = expression.getReferencedElements().get(0);
        assertThat(EcoreUtil.equals(parameter, refElement)).isTrue();
    }

    @Test
    public void should_createExpressionFromEObject_Returns_a_ConnectorOutputExpression_if_EObject_is_a_ConnectorOutput() throws Exception {
        final Output output = ConnectorDefinitionFactory.eINSTANCE.createOutput();
        output.setName("connectorOutput");
        output.setType(Boolean.class.getName());
        final Expression expression = ExpressionHelper.createExpressionFromEObject(output);
        assertThat(expression).hasContent(output.getName()).
                hasInterpreter("").
                hasType(ExpressionConstants.CONNECTOR_OUTPUT_TYPE).
                hasName(output.getName()).
                hasReturnType(output.getType());
        assertThat(expression.getReferencedElements()).hasSize(1);
        final EObject refElement = expression.getReferencedElements().get(0);
        assertThat(EcoreUtil.equals(output, refElement)).isTrue();
    }


    @Test
    public void should_createExpressionFromEObject_Returns_a_DocumentExpression_if_EObject_is_a_Document() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("leave_request");
        final Expression expression = ExpressionHelper.createExpressionFromEObject(document);
        assertThat(expression).hasContent(document.getName()).
                hasInterpreter("").
                hasType(ExpressionConstants.DOCUMENT_TYPE).
                hasName(document.getName()).
                hasReturnType(org.bonitasoft.engine.bpm.document.Document.class.getName());
        assertThat(expression.getReferencedElements()).hasSize(1);
        final EObject refElement = expression.getReferencedElements().get(0);
        assertThat(EcoreUtil.equals(document, refElement)).isTrue();
    }

    @Test
    public void should_createExpressionFromEObject_Returns_a_copy_if_EObject_is_an_Expression() throws Exception {
        final Expression inputExpression = ExpressionFactory.eINSTANCE.createExpression();
        inputExpression.setName("connectorOutput");
        inputExpression.setType(ExpressionConstants.CONSTANT_TYPE);
        final Expression expression = ExpressionHelper.createExpressionFromEObject(inputExpression);
        assertThat(expression).isNotEqualTo(inputExpression);
        assertThat(EcoreUtil.equals(expression, inputExpression)).isTrue();
    }

    @Test
    public void should_createExpressionFromEObject_Returns_a_ContractInputExpression_if_EObject_is_a_ContractInput() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("inputName");
        input.setType(ContractInputType.TEXT);
        final Expression expression = ExpressionHelper.createExpressionFromEObject(input);
        assertThat(expression).hasContent(input.getName()).
                hasInterpreter("").
                hasType(ExpressionConstants.CONTRACT_INPUT_TYPE).
                hasName(input.getName()).
                hasReturnType(String.class.getName());
        assertThat(expression.getReferencedElements()).hasSize(1);
        final EObject refElement = expression.getReferencedElements().get(0);
        assertThat(EcoreUtil.equals(input, refElement)).isTrue();
    }

    @Test
    public void should_createExpressionFromEObject_Returns_a_ContractInputExpression_of_type_List_if_EObject_is_a_multiple_ContractInput() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("inputName");
        input.setMultiple(true);
        input.setType(ContractInputType.TEXT);
        final Expression expression = ExpressionHelper.createExpressionFromEObject(input);
        assertThat(expression).hasContent(input.getName()).
                hasInterpreter("").
                hasType(ExpressionConstants.CONTRACT_INPUT_TYPE).
                hasName(input.getName()).
                hasReturnType(List.class.getName());
        assertThat(expression.getReferencedElements()).hasSize(1);
        final EObject refElement = expression.getReferencedElements().get(0);
        assertThat(EcoreUtil.equals(input, refElement)).isTrue();
    }

    @Test
    public void should_createExpressionFromEObject_Returns_a_ContractInputExpression_of_type_Map_if_EObject_is_a_complex_ContractInput() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("inputName");
        input.setType(ContractInputType.COMPLEX);
        final Expression expression = ExpressionHelper.createExpressionFromEObject(input);
        assertThat(expression).hasContent(input.getName()).
                hasInterpreter("").
                hasType(ExpressionConstants.CONTRACT_INPUT_TYPE).
                hasName(input.getName()).
                hasReturnType(Map.class.getName());
        assertThat(expression.getReferencedElements()).hasSize(1);
        final EObject refElement = expression.getReferencedElements().get(0);
        assertThat(EcoreUtil.equals(input, refElement)).isTrue();
    }

    @Test
    public void should_createExpressionFromEObject_Returns_a_ContractInputExpression_of_type_FileInputValue_if_EObject_is_a_FILE_ContractInput()
            throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("inputName");
        input.setType(ContractInputType.FILE);
        final Expression expression = ExpressionHelper.createExpressionFromEObject(input);
        assertThat(expression).hasContent(input.getName()).
                hasInterpreter("").
                hasType(ExpressionConstants.CONTRACT_INPUT_TYPE).
                hasName(input.getName()).
                hasReturnType(FileInputValue.class.getName());
        assertThat(expression.getReferencedElements()).hasSize(1);
        final EObject refElement = expression.getReferencedElements().get(0);
        assertThat(EcoreUtil.equals(input, refElement)).isTrue();
    }

    @Test
    public void should_createOperationFromOutput() {
        final Output output = ConnectorDefinitionFactory.eINSTANCE.createOutput();
        output.setName("outputName");
        output.setType(Integer.class.getName());
        final Operation createDefaultConnectorOutputOperation = ExpressionHelper.createDefaultConnectorOutputOperation(output);
        assertThat(createDefaultConnectorOutputOperation.getOperator().getType()).isEqualTo(ExpressionConstants.ASSIGNMENT_OPERATOR);
        assertThat(createDefaultConnectorOutputOperation.getRightOperand())
                .hasType(ExpressionConstants.CONNECTOR_OUTPUT_TYPE)
                .hasReturnType(Integer.class.getName())
                .hasName("outputName")
                .hasContent("outputName");
        assertThat(createDefaultConnectorOutputOperation.getLeftOperand()).isNotNull();
    }

    @Test
    public void should_create_a_data_with_name_from_an_iterator_expression() throws Exception {
        final Data data = ExpressionHelper.dataFromIteratorExpression(aTask().build(), anExpression()
                .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE).withName("iterator").build(),
                aMainProcess().build());

        assertThat(data.getName()).isEqualTo("iterator");
    }

    @Test
    public void should_create_a_data_with_datatype_from_an_iterator_expression() throws Exception {
        final StringType stringDataType = aStringDataType().build();

        final Data data = ExpressionHelper.dataFromIteratorExpression(aTask().build(),
                anExpression()
                        .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE).withReturnType(String.class.getName()).build(),
                aMainProcess().havingDatatypes(stringDataType).build());

        assertThat(data.getDataType()).isEqualTo(stringDataType);
    }

    @Test
    public void should_get_StringType_for_String_classname() throws Exception {
        final StringType stringDataType = aStringDataType().build();
        assertThat(ExpressionHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(stringDataType).build(),
                String.class.getName())).isEqualTo(stringDataType);
    }

    @Test
    public void should_get_BooleanType_for_Boolean_classname() throws Exception {
        final BooleanType booleanDataType = aBooleanDataType().build();
        assertThat(ExpressionHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(booleanDataType).build(),
                Boolean.class.getName())).isEqualTo(booleanDataType);
    }

    @Test
    public void should_get_IntegerType_for_Integer_classname() throws Exception {
        final IntegerType integerDataType = anIntegerDataType().build();
        assertThat(ExpressionHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(integerDataType).build(),
                Integer.class.getName())).isEqualTo(integerDataType);
    }

    @Test
    public void should_get_DoubleType_for_Double_classname() throws Exception {
        final DoubleType doubleDataType = aDoubleDataType().build();
        assertThat(ExpressionHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(doubleDataType).build(),
                Double.class.getName())).isEqualTo(doubleDataType);
    }

    @Test
    public void should_get_LongType_for_Long_classname() throws Exception {
        final LongType longDataType = aLongDataType().build();
        assertThat(ExpressionHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(longDataType).build(),
                Long.class.getName())).isEqualTo(longDataType);
    }

    @Test
    public void should_get_DateType_for_Date_classname() throws Exception {
        final DateType dataDataType = aDateDataType().build();
        assertThat(ExpressionHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(dataDataType).build(),
                Date.class.getName())).isEqualTo(dataDataType);
    }

    @Test
    public void should_get_JavaType_for_Other_classname() throws Exception {
        final JavaType javaDataType = aJavaDataType().build();
        assertThat(ExpressionHelper.getDataTypeByClassName(aMainProcess().havingDatatypes(javaDataType).build(),
                Object.class.getName())).isEqualTo(javaDataType);
    }

}
