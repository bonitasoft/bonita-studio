/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.Info;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.TextInfo;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 */
public class InitialValueContribution implements IExtensibleGridPropertySectionContribution {

    protected TransactionalEditingDomain editingDomain;
    protected Widget widget;
    protected ExpressionViewer expressionViewer;
    protected EMFDataBindingContext dataBindingContext;
    protected Composite composite;
    public static final int MARGIN_WIDTH = 5;
    public static final int MARGIN_HEIGHT = 0;
    protected Button allowHtmlButton;
    private InitialValueExpressionFilter initialValueExpressionFilter;

    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory,
            final ExtensibleGridPropertySection extensibleGridPropertySection) {
        initCreateControl(composite);
        doCreateControl(widgetFactory);
        bindWidgets();
    }

    protected void doCreateControl(final TabbedPropertySheetWidgetFactory widgetFactory) {
        /* Create control for initial value */
        expressionViewer = new ExpressionViewer(composite, SWT.BORDER, widgetFactory, editingDomain,
                FormPackage.Literals.WIDGET__INPUT_EXPRESSION, true);
        expressionViewer.addFilter(getExpressionViewerFilter());
        if (widget instanceof CheckBoxSingleFormField) {
            expressionViewer.setMessage(Messages.data_tooltip_boolean);
        } else {
            expressionViewer.setMessage(Messages.data_tooltip_text);
        }
        if (widget instanceof DateFormField) {
            expressionViewer.addExpressionValidator(new IExpressionValidator() {

                private Expression inputExpression;

                public IStatus validate(final Object value) {
                    final String returnType = inputExpression.getReturnType();
                    if (returnType.equals(Date.class.getName())
                            || returnType.equals(String.class.getName())) {
                        return ValidationStatus.ok();
                    } else {
                        return ValidationStatus.warning(Messages.dateWidgetReturnTypeWarning);
                    }
                }

                public void setInputExpression(final Expression inputExpression) {
                    this.inputExpression = inputExpression;
                }

                public void setDomain(final EditingDomain domain) {

                }

                public void setContext(final EObject context) {

                }

                public boolean isRelevantForExpressionType(final String type) {
                    return true;
                }
            });
        }
        expressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        /* Create checkbox to allow HTML */
        createAllowHtmlButton(composite, widgetFactory);
    }

    protected AvailableExpressionTypeFilter getExpressionViewerFilter() {

        final boolean allowGroupIterator = widget.eContainer() instanceof Group;

        if (initialValueExpressionFilter == null) {
            final List<String> contentTypes = new ArrayList<String>();
            contentTypes.add(ExpressionConstants.VARIABLE_TYPE);
            contentTypes.add(ExpressionConstants.SCRIPT_TYPE);
            contentTypes.add(ExpressionConstants.CONSTANT_TYPE);
            contentTypes.add(ExpressionConstants.PARAMETER_TYPE);
            contentTypes.add(ExpressionConstants.SCRIPT_TYPE);
            contentTypes.add(ExpressionConstants.DOCUMENT_TYPE);
            contentTypes.add(ExpressionConstants.XPATH_TYPE);
            contentTypes.add(ExpressionConstants.I18N_TYPE);

            if (allowGroupIterator) {
                contentTypes.add(ExpressionConstants.GROUP_ITERATOR_TYPE);
            }
            initialValueExpressionFilter = new InitialValueExpressionFilter(
                    contentTypes.toArray(new String[contentTypes.size()]));
            initialValueExpressionFilter.setWidget(widget);
        }
        return initialValueExpressionFilter;
    }

    private void initCreateControl(final Composite composite) {
        this.composite = composite;
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
        dataBindingContext = new EMFDataBindingContext();
        composite.setLayout(getCompositeLayout());
    }

    protected GridLayout getCompositeLayout() {
        final GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = MARGIN_HEIGHT;
        layout.marginWidth = MARGIN_WIDTH;
        return layout;
    }

    protected void createAllowHtmlButton(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory) {
        //HTML can be an issue only with these two kind of widget
        if (widget instanceof MessageInfo
                || widget instanceof TextInfo) {
            allowHtmlButton = widgetFactory.createButton(composite, Messages.GeneralSection_allowHTML, SWT.CHECK);
        }
    }

    protected void bindWidgets() {
        if (allowHtmlButton != null && !allowHtmlButton.isDisposed()) {
            Expression inputExpression = widget.getInputExpression();
            if (inputExpression == null) {
                inputExpression = ExpressionFactory.eINSTANCE.createExpression();
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, widget,
                        FormPackage.Literals.WIDGET__INPUT_EXPRESSION, inputExpression));
            }
            dataBindingContext.bindValue(SWTObservables.observeSelection(allowHtmlButton), EMFEditObservables
                    .observeValue(editingDomain, inputExpression, ExpressionPackage.Literals.EXPRESSION__HTML_ALLOWED));
        }
        updateViewerInput();
    }

    public void dispose() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
    }

    public String getLabel() {
        if (widget instanceof IFrameWidget) {
            return Messages.Action_UrlOfTheIFrame;
        } else {
            return Messages.Action_InitialValue;
        }
    }

    public boolean isRelevantFor(final EObject eObject) {
        return (eObject instanceof FormField || eObject instanceof Info)
                && !(eObject instanceof DateFormField)
                && !(eObject instanceof MultipleValuatedFormField)
                && !(eObject instanceof FileWidget)
                && !(eObject instanceof DurationFormField)
                && !(eObject instanceof HtmlWidget);
    }

    public void refresh() {
    }

    public void setEObject(final EObject object) {
        widget = (Widget) object;
        if (initialValueExpressionFilter != null) {
            initialValueExpressionFilter.setWidget(widget);
        }
    }

    protected void updateViewerInput() {
        if (expressionViewer != null && !expressionViewer.getControl().isDisposed()) {
            Expression input = widget.getInputExpression();
            if (input == null) {
                input = ExpressionFactory.eINSTANCE.createExpression();
                editingDomain.getCommandStack().execute(
                        SetCommand.create(editingDomain, widget, FormPackage.Literals.WIDGET__INPUT_EXPRESSION, input));
            }
            dataBindingContext.bindValue(
                    ViewersObservables.observeSingleSelection(expressionViewer),
                    EMFEditProperties.value(editingDomain, FormPackage.Literals.WIDGET__INPUT_EXPRESSION).observe(input));
            expressionViewer.setInput(widget);
            expressionViewer.setSelection(new StructuredSelection(input));
        }
    }

    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    public void setSelection(final ISelection selection) {

    }

}
