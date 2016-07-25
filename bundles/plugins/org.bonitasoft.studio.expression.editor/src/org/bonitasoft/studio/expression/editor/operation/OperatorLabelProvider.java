/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.operation;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Operator;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author Romain Bioteau
 */
public class OperatorLabelProvider extends LabelProvider {

    @Override
    public String getText(final Object element) {
        if (element instanceof String) {
            if (ExpressionConstants.ASSIGNMENT_OPERATOR.equals(element)) {
                return Messages.assignment;
            } else if (ExpressionConstants.JAVA_METHOD_OPERATOR.equals(element)) {
                return Messages.javaMethodOperator;
            } else if (ExpressionConstants.XPATH_UPDATE_OPERATOR.equals(element)) {
                return Messages.xpathUpdateOperator;
            } else if (ExpressionConstants.SET_DOCUMENT_OPERATOR.equals(element)) {
                return Messages.setDocumentOperator;
            } else if (ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR.equals(element)) {
                return Messages.setDocumentListOperator;
            } else if (ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(element)) {
                return Messages.createBusinessData;
            } else if (ExpressionConstants.DELETION_OPERATOR.equals(element)) {
                return Messages.deleteOperatorLabel;
            }
        } else if (element instanceof Operator) {
            final Operator op = (Operator) element;
            final String operatorType = op.getType();
            if (ExpressionConstants.ASSIGNMENT_OPERATOR.equals(operatorType)) {
                return Messages.assignment;
            } else if (ExpressionConstants.DELETION_OPERATOR.equals(operatorType)) {
                return Messages.deleteOperatorLabel;
            } else if (ExpressionConstants.JAVA_METHOD_OPERATOR.equals(operatorType)) {
                String methodName = op.getExpression();
                if (methodName != null && methodName.length() > 40) {
                    methodName = methodName.substring(0, 35) + "...";
                }
                return methodName;
            } else if (ExpressionConstants.XPATH_UPDATE_OPERATOR.equals(operatorType)) {
                String xpathExpression = op.getExpression();
                if (xpathExpression != null && xpathExpression.length() > 40) {
                    xpathExpression = xpathExpression.substring(0, 35) + "...";
                }
                return xpathExpression;
            } else if (ExpressionConstants.SET_DOCUMENT_OPERATOR.equals(operatorType)) {
                return Messages.setDocumentOperator;
            } else if (ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR.equals(operatorType)) {
                return Messages.setDocumentListOperator;
            } else if (ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(operatorType)) {
                return Messages.createBusinessData;
            }
        }
        return super.getText(element);
    }

}
