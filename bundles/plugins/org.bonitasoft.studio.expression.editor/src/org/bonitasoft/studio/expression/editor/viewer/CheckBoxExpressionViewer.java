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
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class CheckBoxExpressionViewer extends ExpressionViewer implements ExpressionConstants {

    private MagicComposite mc;
    private Button checkBoxControl;
    private final Label expressionModeLabel;

    private ControlDecoration checkBoxDecoration;

    public CheckBoxExpressionViewer(final Composite composite, final Label expressionModeLabel, final int style,
            final EReference expressionReference) {
        super(composite, style, expressionReference);
        this.expressionModeLabel = expressionModeLabel;
    }

    @Override
    protected void createControl(final Composite composite, final int style,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        mc = new MagicComposite(composite, SWT.INHERIT_DEFAULT);
        mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());
        checkBoxControl = new Button(mc, SWT.CHECK);
        if (widgetFactory != null) {
            widgetFactory.adapt(checkBoxControl, true, true);
        }
        checkBoxControl
                .setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(SWT.DEFAULT, 30).indent(16, 0)
                        .align(SWT.BEGINNING, SWT.CENTER).create());

        control = new Composite(mc, SWT.INHERIT_DEFAULT);
        if (widgetFactory != null) {
            widgetFactory.adapt(control);
        }
        control.addDisposeListener(disposeListener);
        control.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).align(SWT.BEGINNING, SWT.CENTER).create());
        control.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(0, 0).create());
        createTextControl(style, widgetFactory);

        createToolbar(style, widgetFactory);
        if ((style & SWT.BORDER) != 0) {//Not in a table
            createSwitchEditorControl(widgetFactory);
        }
        addDecorator(composite);
        mc.show(checkBoxControl);
        mc.hide(control);
    }

    private void addDecorator(final Composite composite) {
        checkBoxDecoration = new ControlDecoration(checkBoxControl, SWT.LEFT, composite);
        checkBoxDecoration.setImage(Pics.getImage(PicsConstants.hint));
        refreshDecoration();
    }

    private void createSwitchEditorControl(final TabbedPropertySheetWidgetFactory widgetFactory) {
        ((GridLayout) control.getLayout()).numColumns++;
        final Link switchControl = new Link(mc, SWT.NONE);
        switchControl.setLayoutData(GridDataFactory.fillDefaults().indent(15, 0).create());
        switchControl.setText(Messages.switchEditorCondition);
        switchControl.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                switchEditorType();
            }
        });
    }

    protected void switchEditorType() {
        if (!control.isVisible()) {
            switchToExpressionMode();
            bindExpression();
        } else {
            if (MessageDialog.openQuestion(mc.getShell(), Messages.eraseExpressionTitle, Messages.eraseExpressionMsg)) {
                switchToCheckBoxMode();
                //Reset checkbox to false
                final Expression falseExp = ExpressionFactory.eINSTANCE.createExpression();
                falseExp.setName(Boolean.FALSE.toString());
                falseExp.setContent(Boolean.FALSE.toString());
                falseExp.setReturnType(Boolean.class.getName());
                falseExp.setType(ExpressionConstants.CONSTANT_TYPE);
                updateSelection(null, falseExp);
                bindExpression();
            }
        }
        mc.layout(true, true);
    }

    private void switchToExpressionMode() {
        mc.hide(checkBoxControl);
        mc.show(control);
        if (expressionModeLabel != null) {
            mc.show(expressionModeLabel);
        }
        refreshDecoration();
    }

    private void switchToCheckBoxMode() {
        mc.hide(control);
        if (expressionModeLabel != null) {
            mc.hide(expressionModeLabel);
        }
        mc.show(checkBoxControl);
        refreshDecoration();
    }

    public boolean isCheckboxMode() {
        return getCheckboxControl().isVisible();
    }

    @Override
    public Control getControl() {
        return mc;
    }

    public Button getCheckboxControl() {
        return checkBoxControl;
    }

    @Override
    protected void bindExpression() {
        if (control.isVisible()) {
            super.bindExpression();
        } else {
            if (expressionBinding != null && externalDataBindingContext != null) {
                externalDataBindingContext.removeBinding(expressionBinding);
                expressionBinding.dispose();
            }
            final IObservableValue nameObservable = getExpressionNameObservable();
            final UpdateValueStrategy targetToModelNameStrategy = new UpdateValueStrategy();
            targetToModelNameStrategy.setConverter(new Converter(Boolean.class, String.class) {

                @Override
                public Object convert(final Object fromObject) {
                    String input = Boolean.FALSE.toString();
                    if (fromObject != null) {
                        input = ((Boolean) fromObject).toString();
                    }
                    updateContentType(ExpressionConstants.CONSTANT_TYPE);
                    updateContent(getContentFromInput(input));
                    refresh();
                    return input;
                }
            });

            final UpdateValueStrategy modelToTargetNameStrategy = new UpdateValueStrategy();
            modelToTargetNameStrategy.setConverter(new Converter(String.class, Boolean.class) {

                @Override
                public Object convert(final Object fromObject) {
                    if (fromObject != null) {
                        final String input = fromObject.toString();
                        if (input.equalsIgnoreCase(Boolean.TRUE.toString())) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            internalDataBindingContext.bindValue(
                    SWTObservables.observeSelection(checkBoxControl),
                    nameObservable,
                    targetToModelNameStrategy,
                    modelToTargetNameStrategy);
        }
    }

    @Override
    protected void internalRefresh() {
        super.internalRefresh();
        final String description = getMessage();
        if (description != null) {
            checkBoxControl.setToolTipText(description);
        }
    }

    @Override
    public void setSelection(final ISelection selection) {
        if (!selection.isEmpty()) {
            final Expression exp = (Expression) ((IStructuredSelection) selection).getFirstElement();
            if (exp != null) {
                if (ExpressionConstants.CONSTANT_TYPE.equals(exp.getType())) {
                    if (!checkBoxControl.isVisible()) {
                        switchToCheckBoxMode();
                        mc.layout(true, true);
                    }
                } else {
                    if (!control.isVisible()) {
                        switchToExpressionMode();
                        mc.layout(true, true);
                    }
                }
            }
        }
        super.setSelection(selection);
    }

    @Override
    public void setMessage(final String message) {
        super.setMessage(message);
        refreshDecoration();
    }

    private void refreshDecoration() {
        final String message = getMessage();
        if (checkBoxControl.isVisible() && message != null && !message.isEmpty()) {
            checkBoxDecoration.setDescriptionText(message);
            checkBoxDecoration.show();
        } else {
            checkBoxDecoration.hide();
        }

    }

}
