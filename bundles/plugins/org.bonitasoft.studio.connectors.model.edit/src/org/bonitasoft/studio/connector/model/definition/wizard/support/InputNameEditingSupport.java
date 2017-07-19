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
package org.bonitasoft.studio.connector.model.definition.wizard.support;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.EObjectObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class InputNameEditingSupport extends ObservableValueEditingSupport {

    private final ConnectorDefinition definition;
    private final DataBindingContext context;

    public InputNameEditingSupport(ColumnViewer viewer, ConnectorDefinition definition, DataBindingContext context) {
        super(viewer, context);
        this.definition = definition;
        this.context = context;
    }

    @Override
    protected Binding createBinding(IObservableValue target, final IObservableValue model) {
        UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setConverter(new Converter(String.class, String.class) {

            @Override
            public Object convert(Object from) {
                return from != null ? NamingUtils.toJavaIdentifier(from.toString(), false) : null;
            }

        });
        targetToModel.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object input) {
                if (input == null || input.toString().isEmpty()) {
                    return ValidationStatus.error(Messages.nameIsEmpty);
                }
                for (Input i : definition.getInput()) {
                    EObject observed = (EObject) ((EObjectObservableValue) model).getObserved();
                    if (!i.equals(observed) && i.getName() != null && i.getName().equals(input.toString())) {
                        return ValidationStatus.error(Messages.nameAlreadyExists);
                    }
                }
                return Status.OK_STATUS;
            }
        });
        return context.bindValue(target, model, targetToModel, null);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(final Object element) {
        TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl(), SWT.NONE);
        return editor;
    }

    @Override
    protected IObservableValue doCreateCellEditorObservable(CellEditor editor) {
        return SWTObservables.observeText(editor.getControl(), SWT.Modify);
    }

    @Override
    protected IObservableValue doCreateElementObservable(Object element, ViewerCell cell) {
        return EMFObservables.observeValue((EObject) element, ConnectorDefinitionPackage.Literals.INPUT__NAME);
    }

}
