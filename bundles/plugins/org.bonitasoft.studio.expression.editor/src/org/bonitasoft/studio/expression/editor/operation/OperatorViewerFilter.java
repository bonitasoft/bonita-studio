/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.operation;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.XMLData;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Aurelien Pupier
 */
public final class OperatorViewerFilter extends ViewerFilter {

    private final Operation operation;

    public OperatorViewerFilter(final Operation operation) {
        this.operation = operation;
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        final Expression exp = operation.getLeftOperand();
        if (isVariableType(exp)) {
            return selectFilterForVariableStorage(element, exp);
        } else if (isDocumentType(exp)) {
            final EObject referencedDocument = exp.getReferencedElements().get(0);
            if (referencedDocument instanceof Document) {

                if (((Document) referencedDocument).isMultiple()) {
                    return ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR.equals(element);
                } else {
                    return ExpressionConstants.SET_DOCUMENT_OPERATOR.equals(element);
                }
            }
        }
        return ExpressionConstants.ASSIGNMENT_OPERATOR.equals(element);
    }

    private boolean isDocumentType(final Expression exp) {
        return exp != null && !exp.getReferencedElements().isEmpty() && ExpressionConstants.DOCUMENT_REF_TYPE.equals(exp.getType());
    }

    private boolean selectFilterForVariableStorage(final Object element, final Expression exp) {
        final EObject data = exp.getReferencedElements().get(0);
        if (data instanceof BusinessObjectData) {
            return isBusinessObjectDataCompatible(element);
        } else if (data instanceof JavaObjectData) {
            return isJavaObjectDataCompatible(element);
        } else if (data instanceof XMLData) {
            return isXMLDataCompatible(element);
        } else {
            return element.equals(ExpressionConstants.ASSIGNMENT_OPERATOR);
        }
    }

    private boolean isXMLDataCompatible(final Object element) {
        return ExpressionConstants.ASSIGNMENT_OPERATOR.equals(element)
                || ExpressionConstants.XPATH_UPDATE_OPERATOR.equals(element);
    }

    private boolean isJavaObjectDataCompatible(final Object element) {
        return ExpressionConstants.ASSIGNMENT_OPERATOR.equals(element)
                || ExpressionConstants.JAVA_METHOD_OPERATOR.equals(element);
    }

    private boolean isBusinessObjectDataCompatible(final Object element) {
        return ExpressionConstants.ASSIGNMENT_OPERATOR.equals(element)
                || ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(element)
                || ExpressionConstants.JAVA_METHOD_OPERATOR.equals(element)
                || ExpressionConstants.DELETION_OPERATOR.equals(element);
    }

    protected boolean isVariableType(final Expression exp) {
        if (exp != null && !exp.getReferencedElements().isEmpty()) {
            final String type = exp.getType();
            return ExpressionConstants.VARIABLE_TYPE.equals(type);
        } else {
            return false;
        }
    }
}
