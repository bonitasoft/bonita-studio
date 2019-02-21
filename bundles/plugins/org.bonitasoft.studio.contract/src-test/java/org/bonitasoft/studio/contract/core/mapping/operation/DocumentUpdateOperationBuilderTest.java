
package org.bonitasoft.studio.contract.core.mapping.operation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.bonitasoft.studio.model.process.builders.DocumentBuilder;
import org.junit.Test;

public class DocumentUpdateOperationBuilderTest {

    private static final String DOCUMENT_NAME = "documentName";
    private static final String CONTRACT_INPUT_NAME = "documentNameDocumentInput";

    @Test
    public void should_create_operation_for_simple_document() {
        ContractInput input = ContractInputBuilder.aContractInput()
                .withName(CONTRACT_INPUT_NAME)
                .withDataReference(DOCUMENT_NAME)
                .withType(ContractInputType.FILE)
                .single()
                .build();
        Document document = new DocumentBuilder()
                .single()
                .withName(DOCUMENT_NAME)
                .build();
        Operation operation = new DocumentUpdateOperationBuilder(input, document).toOperation();

        Expression leftOperand = operation.getLeftOperand();
        assertThat(leftOperand.getType()).isEqualTo(ExpressionConstants.DOCUMENT_REF_TYPE);
        assertThat(leftOperand.getName()).isEqualTo(DOCUMENT_NAME);
        assertThat(leftOperand.getContent()).isEqualTo(DOCUMENT_NAME);
        assertThat(leftOperand.getReturnType()).isEqualTo(String.class.getName());

        assertThat(operation.getOperator().getType()).isEqualTo(ExpressionConstants.SET_DOCUMENT_OPERATOR);

        Expression rightOperand = operation.getRightOperand();
        assertThat(rightOperand.getType()).isEqualTo(ExpressionConstants.CONTRACT_INPUT_TYPE);
        assertThat(rightOperand.getName()).isEqualTo(CONTRACT_INPUT_NAME);
        assertThat(rightOperand.getContent()).isEqualTo(CONTRACT_INPUT_NAME);
        assertThat(rightOperand.getReturnType()).isEqualTo(input.getJavaType());
    }

    @Test
    public void should_create_operation_for_multiple_document_without_data_ref() {
        ContractInput input = ContractInputBuilder.aContractInput()
                .withName(CONTRACT_INPUT_NAME)
                .withType(ContractInputType.FILE)
                .multiple()
                .build();
        Document document = new DocumentBuilder()
                .multiple()
                .withName(DOCUMENT_NAME)
                .build();
        Operation operation = new DocumentUpdateOperationBuilder(input, document).toOperation();

        Expression leftOperand = operation.getLeftOperand();
        assertThat(leftOperand.getType()).isEqualTo(ExpressionConstants.DOCUMENT_REF_TYPE);
        assertThat(leftOperand.getName()).isEqualTo(DOCUMENT_NAME);
        assertThat(leftOperand.getContent()).isEqualTo(DOCUMENT_NAME);
        assertThat(leftOperand.getReturnType()).isEqualTo(List.class.getName());

        assertThat(operation.getOperator().getType()).isEqualTo(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);

        Expression rightOperand = operation.getRightOperand();
        assertThat(rightOperand.getType()).isEqualTo(ExpressionConstants.CONTRACT_INPUT_TYPE);
        assertThat(rightOperand.getName()).isEqualTo(CONTRACT_INPUT_NAME);
        assertThat(rightOperand.getContent()).isEqualTo(CONTRACT_INPUT_NAME);
        assertThat(rightOperand.getReturnType()).isEqualTo(List.class.getName());
    }

    @Test
    public void should_create_operation_for_multiple_document_with_data_ref() {
        ContractInput input = ContractInputBuilder.aContractInput()
                .withName(CONTRACT_INPUT_NAME)
                .withType(ContractInputType.FILE)
                .withDataReference(DOCUMENT_NAME)
                .multiple()
                .build();
        Document document = new DocumentBuilder()
                .multiple()
                .withName(DOCUMENT_NAME)
                .build();
        DocumentUpdateOperationBuilder operationBuilder = new DocumentUpdateOperationBuilder(input, document);

        Operation operation = operationBuilder.toOperation();

        Expression leftOperand = operation.getLeftOperand();
        assertThat(leftOperand.getType()).isEqualTo(ExpressionConstants.DOCUMENT_REF_TYPE);
        assertThat(leftOperand.getName()).isEqualTo(DOCUMENT_NAME);
        assertThat(leftOperand.getContent()).isEqualTo(DOCUMENT_NAME);
        assertThat(leftOperand.getReturnType()).isEqualTo(List.class.getName());

        assertThat(operation.getOperator().getType()).isEqualTo(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);

        Expression rightOperand = operation.getRightOperand();
        assertThat(rightOperand.getType()).isEqualTo(ExpressionConstants.SCRIPT_TYPE);
        assertThat(rightOperand.getName()).isEqualTo(String.format("update_%s", DOCUMENT_NAME));
        assertThat(rightOperand.getReturnType()).isEqualTo(List.class.getName());
        assertThat(rightOperand.getReferencedElements())
                .filteredOn(Document.class::isInstance)
                .extracting("name").containsExactly(DOCUMENT_NAME);
        assertThat(rightOperand.getReferencedElements())
                .filteredOn(ContractInput.class::isInstance)
                .extracting("name").containsExactly(CONTRACT_INPUT_NAME);
        assertThat(rightOperand.getReferencedElements())
                .filteredOn(Expression.class::isInstance)
                .extracting("name").containsExactly("apiAccessor");

        String script = rightOperand.getContent();
        assertThat(script).contains(DOCUMENT_NAME);
        assertThat(script).contains(CONTRACT_INPUT_NAME);
    }

}
