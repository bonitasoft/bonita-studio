/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
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

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetModifiersSwitch;
import org.bonitasoft.studio.common.jface.databinding.converter.BooleanInverserConverter;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 *
 * @author Romain Bioteau
 *
 */
public class WidgetModifierContribution implements IExtensibleGridPropertySectionContribution, ISelectionChangedListener {

    protected TransactionalEditingDomain editingDomain;

    protected Widget widget;

    protected EMFDataBindingContext dataBindingContext;

    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory, final ExtensibleGridPropertySection extensibleGridPropertySection) {
        dataBindingContext = new EMFDataBindingContext();
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        final ComboViewer modifiersCombo = createModifiersCombo(composite);

        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(modifiersCombo),
                EMFEditObservables.observeValue(editingDomain, widget, FormPackage.Literals.WIDGET__RETURN_TYPE_MODIFIER));

        final UpdateValueStrategy notStrategy = new UpdateValueStrategy();
        notStrategy.setConverter(new BooleanInverserConverter());

        final IObservableValue duplicableValueObserved = EMFEditObservables.observeValue(editingDomain, widget, FormPackage.Literals.DUPLICABLE__DUPLICATE);
        duplicableValueObserved.addValueChangeListener(new IValueChangeListener() {

            public void handleValueChange(final ValueChangeEvent arg0) {
                if (widget instanceof FileWidget) {
                    modifiersCombo.setSelection(new StructuredSelection());
                    if (!modifiersCombo.getControl().isDisposed()) {
                        modifiersCombo.refresh();
                    }
                    if (((FileWidget) widget).isDuplicate()) {
                        modifiersCombo.setSelection(new StructuredSelection(WidgetModifiersSwitch.NEW_LIST_OF_DOCUMENT));
                    } else {
                        modifiersCombo.setSelection(new StructuredSelection(WidgetModifiersSwitch.ENGINE_DOCUMENT_QUALIFIED_NAME));
                    }
                }
            }
        });
        if (!(widget instanceof FileWidget)) {
            dataBindingContext.bindValue(SWTObservables.observeEnabled(modifiersCombo.getCombo()),
                    duplicableValueObserved, notStrategy, notStrategy);
        } else {
            dataBindingContext.bindValue(SWTObservables.observeEnabled(modifiersCombo.getCombo()),
                    duplicableValueObserved);
        }
    }

    protected ComboViewer createModifiersCombo(final Composite composite) {
        final ComboViewer modifiersCombo = new ComboViewer(composite, SWT.READ_ONLY | SWT.BORDER);
        modifiersCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().indent(5, 0).create());
        modifiersCombo.setLabelProvider(new LabelProvider());
        modifiersCombo.setContentProvider(new ArrayContentProvider());
        modifiersCombo.setSorter(new ViewerSorter());
        if (widget instanceof FileWidget) {
            modifiersCombo.addFilter(new FileWidgetModifierFilter((FileWidget) widget));
        }
        modifiersCombo.setInput(getAvailableModifiersFor(widget));
        modifiersCombo.addSelectionChangedListener(this);

        final ControlDecoration deco = new ControlDecoration(modifiersCombo.getControl(), SWT.LEFT);
        deco.setDescriptionText(Messages.modifierDescription);
        deco.setImage(Pics.getImage(PicsConstants.hint));
        deco.setMarginWidth(2);
        deco.setShowOnlyOnFocus(false);
        return modifiersCombo;
    }

    private Collection<String> getAvailableModifiersFor(final Widget widget) {
        final WidgetModifiersSwitch modifierSwitch = new WidgetModifiersSwitch();
        return modifierSwitch.doSwitch(widget);
    }

    public void dispose() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
    }

    public String getLabel() {
        return Messages.fieldModifier;
    }

    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof TextFormField || eObject instanceof FileWidget;
    }

    public void refresh() {
    }

    public void setEObject(final EObject object) {
        widget = (Widget) object;
    }

    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    public void setSelection(final ISelection selection) {

    }

    public void selectionChanged(final SelectionChangedEvent event) {
        final String type = (String) ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (widget != null && type != null) {
            updateWidgetReferences(widget, type);
        }
    }

    protected void updateWidgetReferences(final Widget widget, final String type) {
        Assert.isNotNull(type);
        final CompoundCommand cc = new CompoundCommand("Update widget modifier");
        final List<Expression> allExpressionOfWidget = ModelHelper.getAllItemsOfType(ModelHelper.getParentForm(widget), ExpressionPackage.Literals.EXPRESSION);
        for (final Expression exp : allExpressionOfWidget) {
            if (exp.getContent() != null && (WidgetHelper.FIELD_PREFIX + widget.getName()).equals(exp.getContent())
                    && ExpressionConstants.FORM_FIELD_TYPE.equals(exp.getType()) && !type.equals(exp.getReturnType())) {
                if (((Duplicable) widget).isDuplicate()) {
                    cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, List.class.getName()));
                } else {
                    cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, type));
                }
            }
        }
        final List<Widget> allWidget = ModelHelper.getAllItemsOfType(ModelHelper.getParentForm(widget), FormPackage.Literals.WIDGET);
        for (final Widget w : allWidget) {
            if (w.getName().equals(widget.getName()) && w.eClass().equals(widget.eClass()) && !type.equals(w.getReturnTypeModifier())) {
                cc.append(SetCommand.create(editingDomain, w, FormPackage.Literals.WIDGET__RETURN_TYPE_MODIFIER, type));
            }
        }
        if (!cc.isEmpty()) {
            editingDomain.getCommandStack().execute(cc);
        }
    }

}
