/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.converter.BooleanInverserConverter;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.data.provider.DataExpressionNatureProviderForFormOutput;
import org.bonitasoft.studio.data.provider.DataExpressionProviderForOutput;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 * @author Aurelien Pupier - allow to create a data from here
 */
public class OutputSectionContribution implements IExtensibleGridPropertySectionContribution {

    protected Widget element;

    protected TransactionalEditingDomain editingDomain;

    protected EMFDataBindingContext dataBinding;

    private OperationViewer operationViewer;

    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory,
            final ExtensibleGridPropertySection extensibleGridPropertySection) {
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final AvailableExpressionTypeFilter expressionFilter = new AvailableExpressionTypeFilter(new String[] {
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.FORM_FIELD_TYPE,
                ExpressionConstants.QUERY_TYPE,
                ExpressionConstants.DOCUMENT_TYPE
        });

        final AvailableExpressionTypeFilter storageExpressionFilter = new AvailableExpressionTypeFilter(new String[] {
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.DOCUMENT_REF_TYPE
        });

        operationViewer = new OperationViewer(composite, widgetFactory, getEditingDomain(), expressionFilter, storageExpressionFilter);
        final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        operationViewer.setStorageExpressionNatureProvider(new DataExpressionNatureProviderForFormOutput(
                new DataExpressionProviderForOutput(repositoryAccessor)));
        operationViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        bindWidgets();

    }

    protected TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    protected void bindWidgets() {

        if (operationViewer != null && !operationViewer.isDisposed()) {
            if (dataBinding != null) {
                dataBinding.dispose();
            }
            dataBinding = new EMFDataBindingContext();
            operationViewer.setContext(dataBinding);
            Operation action = element.getAction();
            if (action == null) {
                action = ExpressionFactory.eINSTANCE.createOperation();
                final Operator op = ExpressionFactory.eINSTANCE.createOperator();
                op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
                op.setExpression("=");
                action.setOperator(op);

                final Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
                final Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
                action.setLeftOperand(variableExp);
                action.setRightOperand(actionExp);
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.WIDGET__ACTION, action));
            }
            operationViewer.setEditingDomain(getEditingDomain());
            operationViewer.setEObject(element);
            final UpdateValueStrategy strategy = new UpdateValueStrategy();
            strategy.setConverter(new BooleanInverserConverter());

            dataBinding.bindValue(SWTObservables.observeVisible(ExtensibleGridPropertySection.getLabelCompositeOf(operationViewer.getParent())),
                    EMFEditObservables.observeValue(getEditingDomain(), element, FormPackage.Literals.WIDGET__READ_ONLY), strategy, strategy);
            dataBinding.bindValue(SWTObservables.observeVisible(operationViewer.getParent()),
                    EMFEditObservables.observeValue(getEditingDomain(), element, FormPackage.Literals.WIDGET__READ_ONLY), strategy, strategy);
            if (element instanceof FileWidget) {
                dataBinding.bindValue(SWTObservables.observeVisible(ExtensibleGridPropertySection.getLabelCompositeOf(operationViewer.getParent())),
                        EMFEditObservables.observeValue(getEditingDomain(), element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), strategy, strategy);
                dataBinding.bindValue(SWTObservables.observeVisible(operationViewer.getParent()),
                        EMFEditObservables.observeValue(getEditingDomain(), element, FormPackage.Literals.FILE_WIDGET__DOWNLOAD_ONLY), strategy, strategy);
            }
        }
    }

    public void dispose() {
        if (dataBinding != null) {
            dataBinding.dispose();
        }
        if (operationViewer != null) {
            operationViewer.dispose();
        }
    }

    public String getLabel() {
        return Messages.outputOperation;
    }

    public boolean isRelevantFor(final EObject eObject) {
        if (eObject instanceof FormField && !ModelHelper.isInDuplicatedGrp(eObject)) {
            final Form form = ModelHelper.getForm((Widget) eObject);
            return !(form instanceof ViewForm);
        } else {
            return false;
        }

    }

    public void setEObject(final EObject object) {
        element = (Widget) object;
    }

    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    public void setSelection(final ISelection selection) {

    }

    public void refresh() {

    }

}
