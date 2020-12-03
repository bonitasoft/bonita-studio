/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.List;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class ReadOnlyExpressionViewer extends ExpressionViewer {

    public ReadOnlyExpressionViewer(final Composite composite, final int style,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final EditingDomain editingDomain, final EReference expressionReference) {
        super(composite, style, widgetFactory, editingDomain, expressionReference);
    }

    @Override
    protected void createTextControl(final int style,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        super.createTextControl(style | SWT.READ_ONLY, widgetFactory);
        getTextControl().setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        getTextControl().setMessage(Messages.selectTarget);
        final IConfigurationElement[] configurationElements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.expression.editor.caretDestroyer");
        if (configurationElements.length == 1) {
            try {
                final FocusListener caretDestroyerFocusListener = (FocusListener) configurationElements[0].createExecutableExtension("class");
                getTextControl().addFocusListener(caretDestroyerFocusListener);
            } catch (final CoreException e1) {
                BonitaStudioLog.error(e1);
            }
        }
    }

    @Override
    public void manageNatureProviderAndAutocompletionProposal(final Object input) {
        super.manageNatureProviderAndAutocompletionProposal(input);
        setProposalsFiltering(false);
    }

    @Override
    protected void bindEditableText(final IObservableValue typeObservable) {

    }

    @Override
    protected boolean shouldAddEditToolItem() {
        return false;
    }

    /**
     * Override In order to update Operator and Return type of Right Operand if we are updating Left Operand of an Operation
     */
    @Override
    protected void sideModificationOnProposalAccepted(final CompoundCommand cc, final Expression copy) {
        super.sideModificationOnProposalAccepted(cc, copy);
        if (context instanceof Operation) {
            final Expression selectedExpression = getSelectedExpression();
            if (selectedExpression != null && ExpressionPackage.Literals.OPERATION__LEFT_OPERAND.equals(selectedExpression.eContainingFeature())) {
                final Operator operator = ((Operation) context).getOperator();
                final String newOperatorType = updateOperatorType(cc, operator, copy);
                updateRightOperand(cc, (Operation) context, newOperatorType, copy);
            }
        }
    }

    private String updateOperatorType(final CompoundCommand cc, final Operator operator, final Expression storageExpression) {
        final String newOperatorType = getNewOperatorTypeFor(operator, storageExpression);
        if (!operator.getType().equals(newOperatorType)) {
            cc.append(SetCommand.create(getEditingDomain(), operator, ExpressionPackage.Literals.OPERATOR__TYPE,
                    newOperatorType));
        }
        return newOperatorType;
    }

    protected String getNewOperatorTypeFor(final Operator currentOperator, final Expression newStorageExpression) {
        final String currentOperatorType = currentOperator.getType();
        String newOperatorType = currentOperatorType;
        final String storageExpressionType = newStorageExpression.getType();
        if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(storageExpressionType)) {
            final Document documentReferenced = (Document) newStorageExpression.getReferencedElements().get(0);
            if (!documentReferenced.isMultiple() && !isDocumentSimpleOperator(currentOperatorType)) {
                newOperatorType = ExpressionConstants.SET_DOCUMENT_OPERATOR;
            } else if (documentReferenced.isMultiple() && !isDocumentMultipleOperator(currentOperatorType)) {
                newOperatorType = ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR;
            }
        } else if (ExpressionConstants.BUSINESS_DATA_TYPE.equals(storageExpressionType)) {
            if (!isBusinessDataOperator(currentOperatorType)) {
                newOperatorType = ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR;
            }
        } else {
            if (isDocumentSimpleOperator(currentOperatorType)
                    || isDocumentMultipleOperator(currentOperatorType)
                    || isBusinessDataOperator(currentOperatorType)) {
                newOperatorType = ExpressionConstants.ASSIGNMENT_OPERATOR;
            }
        }
        return newOperatorType;
    }

    private boolean isDocumentSimpleOperator(final String currentOperatorType) {
        return ExpressionConstants.SET_DOCUMENT_OPERATOR.equals(currentOperatorType);
    }

    private boolean isDocumentMultipleOperator(final String currentOperatorType) {
        return ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR.equals(currentOperatorType);
    }

    private boolean isBusinessDataOperator(final String currentOperatorType) {
        return ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR.equals(currentOperatorType)
                || ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(currentOperatorType)
                || ExpressionConstants.DELETION_OPERATOR.equals(currentOperatorType)
                || ExpressionConstants.ATTACH_EXISTING_BUSINESS_DATA.equals(currentOperatorType);
    }

    protected void updateRightOperand(final CompoundCommand cc, final Operation action, final String newOperatorType, final Expression newLeftOperand) {
        final Expression right = action.getRightOperand();
        //final Expression newLeftOperand = action.getLeftOperand();
        if (newLeftOperand != null && right != null) {
            if (ExpressionConstants.ASSIGNMENT_OPERATOR.equals(newOperatorType)
                    && !newLeftOperand.getReturnType().equals(right.getReturnType())) {
                if (ExpressionConstants.CONSTANT_TYPE.equals(right.getType())
                        && isPrimitiveType(newLeftOperand.getReturnType())) {
                    appendCommandToSetReturnType(cc, right, newLeftOperand.getReturnType());
                } else if (DocumentValue.class.getName().equals(right.getReturnType())) {
                    appendCommandToSetReturnType(cc, right, DocumentValue.class.getName());
                }
            } else if (canSwitchToDocumentValueWithGroovyScript(newOperatorType, right)) {
                appendCommandToSetReturnType(cc, right, DocumentValue.class.getName());
                appendCommandToScriptType(cc, right);
            } else if (canSwitchToListWithGroovyScript(newOperatorType, right)) {
                appendCommandToSetReturnType(cc, right, List.class.getName());
                appendCommandToScriptType(cc, right);
            }
        }
    }

    private boolean canSwitchToListWithGroovyScript(final String newOperatorType, final Expression right) {
        return isDocumentMultipleOperator(newOperatorType)
                && !List.class.getName().equals(right.getReturnType())
                && isExpressionEmpty(right);
    }

    private boolean canSwitchToDocumentValueWithGroovyScript(final String newOperatorType, final Expression right) {
        return isDocumentSimpleOperator(newOperatorType)
                && !DocumentValue.class.getName().equals(right.getReturnType())
                && isExpressionEmpty(right);
    }

    private boolean isExpressionEmpty(final Expression right) {
        return right.getName() == null
                || right.getName().isEmpty() && right.getContent() == null || right.getContent().isEmpty();
    }

    protected void appendCommandToScriptType(final CompoundCommand cc, final Expression right) {
        cc.append(
                SetCommand.create(getEditingDomain(), right, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.SCRIPT_TYPE));
        cc.append(
                SetCommand.create(getEditingDomain(), right, ExpressionPackage.Literals.EXPRESSION__INTERPRETER, ExpressionConstants.GROOVY));
    }

    protected void appendCommandToSetReturnType(final CompoundCommand cc, final Expression right, final String newReturnType) {
        cc.append(
                SetCommand.create(getEditingDomain(), right, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, newReturnType));
    }

    private boolean isPrimitiveType(final String returnType) {
        return returnType.equals(String.class.getName())
                || returnType.equals(Integer.class.getName())
                || returnType.equals(Long.class.getName())
                || returnType.equals(Boolean.class.getName())
                || returnType.equals(Double.class.getName())
                || returnType.equals(Float.class.getName());
    }

}
