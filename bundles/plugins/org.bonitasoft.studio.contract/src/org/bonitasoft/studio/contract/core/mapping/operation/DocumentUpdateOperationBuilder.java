/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.contract.core.mapping.operation;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.document.core.export.DocumentGroovyScriptExpressionFactory;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Document;
import org.codehaus.groovy.eclipse.refactoring.formatter.DefaultGroovyFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.MalformedTreeException;

public class DocumentUpdateOperationBuilder {

    private ContractInput input;
    private Document document;

    public DocumentUpdateOperationBuilder(ContractInput input, Document document) {
        this.input = input;
        this.document = document;
    }

    public Operation toOperation() {
        Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        Expression rightOperand = createRightOperand();
        Expression leftOperand = ExpressionHelper.createDocumentReferenceExpression(document);
        Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        if (document.isMultiple()) {
            operator.setType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);
        } else {
            operator.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        }
        operation.setLeftOperand(leftOperand);
        operation.setRightOperand(rightOperand);
        operation.setOperator(operator);
        return operation;
    }

    private Expression createRightOperand() {
        if (document.isMultiple() && input.getDataReference() != null) {
            return new DocumentGroovyScriptExpressionFactory().createUpdateDocumentListFromContractExpression(input,
                    document, this::format);
        }
        return ExpressionHelper.createExpressionFromEObject(input);
    }

    private String format(String initialValue) {
        final org.eclipse.jface.text.Document document = new org.eclipse.jface.text.Document(initialValue);
        try {
            new DefaultGroovyFormatter(document, new DefaultFormatterPreferences(), 0).format().apply(document);
        } catch (MalformedTreeException | BadLocationException e) {
            BonitaStudioLog.error("Failed to format generated script", e);
        }
        return document.get();
    }
}
