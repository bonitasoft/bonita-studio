/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.engine.bpm.contract.FileInputValue;
import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
public class OperationReturnTypesValidator implements IExpressionValidator {

    private final ExpressionTypeLabelProvider typeLabelProvider = new ExpressionTypeLabelProvider();

    private final OperatorLabelProvider operatorLabelProvider = new OperatorLabelProvider();

    private Expression inputExpression;

    private Expression dataExpression;

    private static Set<String> primitiveTypes = Stream.of(Long.class.getName(),
            Integer.class.getName(),
            Float.class.getName(),
            Double.class.getName())
            .collect(Collectors.toSet());

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(final Object value) {
        final Expression expression = getExpression(value);
        if (expression != null) {
            final String expressionContent = getExpressionContent(value, expression);
            final String expressionName = getExpressionName(value, expression);
            final Operation operation = getOperation(expression);
            if (operation != null) {
                dataExpression = operation.getLeftOperand();
            }
            if (dataExpression != null
                    && dataExpression.getContent() != null
                    && !dataExpression.getContent().isEmpty()
                    && expressionContent != null
                    && !expressionContent.isEmpty()) {
                if (operation != null) {
                    final String operatorType = operation.getOperator().getType();
                    if (ExpressionConstants.JAVA_METHOD_OPERATOR.equals(operatorType)) {
                        return validateJavaMethodOperation(expression,
                                expressionName, operation);
                    } else if (ExpressionConstants.XPATH_UPDATE_OPERATOR.equals(operatorType)) {
                        return validateXPathOperation(expression,
                                expressionName, operation);
                    } else if (ExpressionConstants.SET_DOCUMENT_OPERATOR.equals(operatorType)) {
                        final IStatus status = validateSetDocumentOperation(expression, operation);
                        if (status != null) {
                            return status;
                        }
                    } else if (ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR.equals(operatorType)) {
                        final IStatus status = validateSetListDocumentOperation(expression, operation);
                        if (status != null) {
                            return status;
                        }
                    } else if (ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(operatorType)) {
                        final IStatus status = validateCreateBusinessDataOperation(expression,
                                expressionName, operation);
                        if (status != null) {
                            return status;
                        }
                    } else if (ExpressionConstants.DELETION_OPERATOR.equals(operatorType)) {
                        final IStatus status = validateDeletionOperation(expression,
                                expressionName, operation);
                        if (status != null) {
                            return status;
                        }
                    } else if (ExpressionConstants.ASSIGNMENT_OPERATOR.equals(operatorType)) {
                        if (isInvalidQueryExpression(operation)) {
                            return ValidationStatus.error(Messages.cannotStoreBusinessObjectInProcessVariable);
                        }
                        if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(dataExpression.getType())) {
                            return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,
                                    typeLabelProvider.getText(dataExpression.getType()),
                                    operatorLabelProvider.getText(operation.getOperator())));
                        }
                    }
                    if (ExpressionConstants.MESSAGE_ID_TYPE.equals(operation.getRightOperand().getType())) {
                        return ValidationStatus.ok();
                    }

                    if (ModelHelper.isAnExpressionCopy(expression)) {
                        return ValidationStatus.ok();
                    }
                }

                try {
                    final Class<?> dataReturnTypeClass = Class.forName(dataExpression.getReturnType());
                    final Class<?> expressionReturnTypeClass = Class.forName(expression.getReturnType());
                    if (!dataReturnTypeClass.isAssignableFrom(expressionReturnTypeClass)) {
                        return ValidationStatus.warning(Messages.bind(
                                Messages.invalidReturnTypeBetween, dataExpression.getName(),
                                expressionName));
                    }
                } catch (final Exception e) {
                    if (!dataExpression.getReturnType().equals(expression.getReturnType())) {
                        return ValidationStatus.warning(Messages.bind(
                                Messages.invalidReturnTypeFor,
                                expressionName));
                    }
                }

                if (ExpressionConstants.CONSTANT_TYPE.equals(expression.getType())) {
                    final String returnType = expression.getReturnType();
                    if (expressionContent != null && !expressionContent.isEmpty()) {
                        if (Integer.class.getName().equals(returnType)) {
                            try {
                                Integer.valueOf(expressionContent);
                            } catch (final NumberFormatException e) {
                                return ValidationStatus.warning(
                                        Messages.bind(Messages.expressionValueNotCompatibleWithReturnType, expressionContent,
                                                returnType));
                            }
                        } else if (Double.class.getName().equals(returnType)) {
                            try {
                                Double.valueOf(expressionContent);
                            } catch (final NumberFormatException e) {
                                return ValidationStatus.warning(
                                        Messages.bind(Messages.expressionValueNotCompatibleWithReturnType, expressionContent,
                                                returnType));
                            }
                        } else if (Float.class.getName().equals(returnType)) {
                            try {
                                Float.valueOf(expressionContent);
                            } catch (final NumberFormatException e) {
                                return ValidationStatus.warning(
                                        Messages.bind(Messages.expressionValueNotCompatibleWithReturnType, expressionContent,
                                                returnType));
                            }
                        } else if (Long.class.getName().equals(returnType)) {
                            try {
                                Long.valueOf(expressionContent);
                            } catch (final NumberFormatException e) {
                                return ValidationStatus.warning(
                                        Messages.bind(Messages.expressionValueNotCompatibleWithReturnType, expressionContent,
                                                returnType));
                            }
                        }
                    }

                }

            }
        }
        return ValidationStatus.ok();
    }

    private boolean isInvalidQueryExpression(final Operation operation) {
        if (leftOperandHasReferencedElement(operation)) {
            final EObject data = operation.getLeftOperand().getReferencedElements().get(0);
            return !(data instanceof BusinessObjectData)
                    && Objects.equals(operation.getRightOperand().getType(), ExpressionConstants.QUERY_TYPE)
                    && !isPrimitive(operation.getRightOperand().getReturnType());
        }
        return false;
    }

    private boolean isPrimitive(String returnType) {
        return primitiveTypes.contains(returnType);
    }

    private boolean leftOperandHasReferencedElement(final Operation operation) {
        return !operation.getLeftOperand().getReferencedElements().isEmpty();
    }

    protected IStatus validateDeletionOperation(final Expression expression, final String expressionName,
            final Operation operation) {
        if (!dataExpression.getReferencedElements().isEmpty()
                && !(dataExpression.getReferencedElements().get(0) instanceof BusinessObjectData)) {
            return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,
                    typeLabelProvider.getText(dataExpression.getType()),
                    operatorLabelProvider.getText(operation.getOperator())));
        }
        return null;
    }

    protected IStatus validateSetListDocumentOperation(final Expression expression, final Operation operation) {
        final boolean isTask = operation.eContainer() instanceof Task;
        final String listClass = List.class.getName();
        final Expression storageExpression = operation.getLeftOperand();
        if (!listClass.equals(storageExpression.getReturnType())) {
            return ValidationStatus.error(Messages.bind(Messages.incompatibleStorageReturnType, storageExpression.getName(),
                    operatorLabelProvider.getText(operation.getOperator())));
        }

        if (expression != null && expression.getContent() != null && !expression.getContent().isEmpty()) {
            final String dataReturnType = storageExpression.getReturnType();
            final String returnType = expression.getReturnType();
            try {
                final boolean isListType = listClass.equals(returnType)
                        || List.class.isAssignableFrom(Class.forName(returnType));
                if (!isListType && listClass.equals(dataReturnType)) {

                    if (isTask) {
                        return ValidationStatus
                                .warning(Messages.incompatibleType + " " + Messages.messageOperationWithListDocumentInTask);
                    } else {
                        if (PlatformUtil.isACommunityBonitaProduct()) {
                            return ValidationStatus.warning(Messages.incompatibleType + " "
                                    + Messages.messageOperationWithListDocumentInFormInCommunity);
                        } else {
                            return ValidationStatus.warning(
                                    Messages.incompatibleType + " " + Messages.messageOperationWithListDocumentInForm);
                        }
                    }
                } else {
                    return ValidationStatus.ok();
                }
            } catch (final ClassNotFoundException e) {
                return ValidationStatus.warning(Messages.bind(
                        Messages.invalidReturnTypeFor,
                        expression.getName()));
            }

        } else {
            if (isTask) {
                return ValidationStatus.info(Messages.messageOperationWithListDocumentInTask);
            } else {
                return ValidationStatus.info(Messages.messageOperationWithListDocumentInForm);
            }
        }
    }

    protected IStatus validateCreateBusinessDataOperation(final Expression expression,
            final String expressionName, final Operation operation) {
        if (!ExpressionConstants.VARIABLE_TYPE.equals(dataExpression.getType())
                && !(dataExpression.getReferencedElements().get(0) instanceof BusinessObjectData)) {
            return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,
                    typeLabelProvider.getText(dataExpression.getType()),
                    operatorLabelProvider.getText(operation.getOperator())));
        }
        return null;
    }

    protected Operation getOperation(final Expression expression) {
        final EObject container = expression.eContainer();
        if (container instanceof Operation) {
            return (Operation) container;
        }
        return null;
    }

    protected Expression getExpression(final Object value) {
        Expression expression = null;
        if (value instanceof Expression) {
            expression = (Expression) value;
        } else if (inputExpression != null) {
            expression = inputExpression;
        }
        return expression;
    }

    protected String getExpressionContent(final Object value, final Expression expression) {
        String expressionContent = expression.getContent();
        if (value instanceof String) {// Expression content to validate
            expressionContent = value.toString();
        }
        return expressionContent;
    }

    protected String getExpressionName(final Object value, final Expression expression) {
        String expressionName = expression.getName();
        if (expressionName == null || expressionName.isEmpty() && value != null) {
            expressionName = value.toString();
        }
        return expressionName;
    }

    protected IStatus validateSetDocumentOperation(final Expression expression, final Operation operation) {
        final boolean isTask = operation.eContainer() instanceof Task;
        final Expression storageExpression = operation.getLeftOperand();
        if (!String.class.getName().equals(storageExpression.getReturnType())) {
            return ValidationStatus.error(Messages.bind(Messages.incompatibleStorageReturnType, storageExpression.getName(),
                    operatorLabelProvider.getText(operation.getOperator())));
        }
        if (expression != null && expression.getContent() != null && !expression.getContent().isEmpty()) {
            final String typeName = storageExpression.getReturnType();
            final String actionExpressionReturnType = expression.getReturnType();
            if (!((DocumentValue.class.getName().equals(actionExpressionReturnType)
                    || FileInputValue.class.getName().equals(actionExpressionReturnType))
                    && typeName.equals(String.class.getName()))) {
                return isTask
                        ? ValidationStatus
                                .warning(Messages.incompatibleType + " " + Messages.messageOperationWithDocumentInTask)
                        : ValidationStatus
                                .warning(Messages.incompatibleType + " " + Messages.messageOperationWithDocumentInForm);
            } else {
                return ValidationStatus.ok();
            }
        } else {
            return isTask ? ValidationStatus.info(Messages.messageOperationWithDocumentInTask) : ValidationStatus
                    .info(Messages.messageOperationWithDocumentInForm);
        }
    }

    protected IStatus validateXPathOperation(final Expression expression,
            final String expressionName, final Operation operation) {
        if (!ExpressionConstants.VARIABLE_TYPE.equals(dataExpression.getType())) {
            return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,
                    typeLabelProvider.getText(dataExpression.getType()),
                    operatorLabelProvider.getText(operation.getOperator())));
        }
        if (dataExpression != null && dataExpression.getContent() != null && !dataExpression.getContent().isEmpty()) {
            if (!operation.getOperator().getInputTypes().isEmpty()) {
                final String typeName = operation.getOperator().getInputTypes().get(0);
                try {
                    final Class<?> dataReturnTypeClass = Class.forName(typeName);
                    final Class<?> expressionReturnTypeClass = Class.forName(expression.getReturnType());
                    if (!dataReturnTypeClass.isAssignableFrom(expressionReturnTypeClass)) {
                        return ValidationStatus.warning(Messages.bind(
                                Messages.invalidReturnTypeBetween, dataExpression.getName(),
                                expressionName));
                    }
                } catch (final Exception e) {
                    if (!operation.getOperator().getInputTypes().get(0).equals(expression.getReturnType())) {
                        return ValidationStatus.warning(Messages.bind(
                                Messages.invalidReturnTypeFor,
                                expressionName));
                    }
                }
            }
        }
        return ValidationStatus.ok();
    }

    protected IStatus validateJavaMethodOperation(final Expression expression,
            final String expressionName, final Operation operation) {
        if (!ExpressionConstants.VARIABLE_TYPE.equals(dataExpression.getType())
                || !(!dataExpression.getReferencedElements().isEmpty()
                        && dataExpression.getReferencedElements().get(0) instanceof JavaObjectData)) {
            return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,
                    typeLabelProvider.getText(dataExpression.getType()),
                    operatorLabelProvider.getText(operation.getOperator())));
        }
        if (!operation.getOperator().getInputTypes().isEmpty()) {
            final String typeName = operation.getOperator().getInputTypes().get(0);
            try {
                final Class<?> dataReturnTypeClass = Class.forName(typeName);
                final Class<?> expressionReturnTypeClass = Class.forName(expression.getReturnType());
                if (!dataReturnTypeClass.isAssignableFrom(expressionReturnTypeClass)) {
                    return ValidationStatus.warning(Messages.bind(
                            Messages.invalidReturnTypeBetween, dataExpression.getName(),
                            expressionName));
                }
            } catch (final Exception e) {
                if (!operation.getOperator().getInputTypes().get(0).equals(expression.getReturnType())) {
                    return ValidationStatus.warning(Messages.bind(
                            Messages.invalidReturnTypeFor,
                            expressionName));
                }
            }
        }
        if (isInvalidQueryExpression(operation)) {
            return ValidationStatus.error(Messages.cannotStoreBusinessObjectInProcessVariable);
        }
        return ValidationStatus.ok();
    }

    @Override
    public void setInputExpression(final Expression inputExpression) {
        this.inputExpression = inputExpression;
    }

    @Override
    public void setDomain(final EditingDomain domain) {

    }

    @Override
    public void setContext(final EObject context) {
    }

    @Override
    public boolean isRelevantForExpressionType(final String type) {
        return ExpressionConstants.ALL_TYPES.equals(type);
    }

}
