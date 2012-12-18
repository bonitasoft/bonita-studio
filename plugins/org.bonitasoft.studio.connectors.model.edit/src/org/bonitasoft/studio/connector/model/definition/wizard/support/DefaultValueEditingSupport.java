/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.model.definition.wizard.support;


import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
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
 *
 */
public class DefaultValueEditingSupport extends ObservableValueEditingSupport {

    private final DataBindingContext context;
    private static Set<String> defaultValueTypes;
    static{
        defaultValueTypes = new HashSet<String>() ;
        defaultValueTypes.add(String.class.getName()) ;
        defaultValueTypes.add(Boolean.class.getName()) ;
        defaultValueTypes.add(Long.class.getName()) ;
        defaultValueTypes.add(Double.class.getName()) ;
        defaultValueTypes.add(Float.class.getName()) ;
        defaultValueTypes.add(Integer.class.getName()) ;
    }
    public DefaultValueEditingSupport(ColumnViewer viewer,DataBindingContext context) {
        super(viewer,context);
        this.context = context ;
    }

    @Override
    protected Binding createBinding(IObservableValue target,final IObservableValue model) {
        UpdateValueStrategy targetToModel =  new UpdateValueStrategy() ;
        targetToModel.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object input) {
                if(input != null && !input.toString().isEmpty()){
                    Input observed = (Input) ((EObjectObservableValue)model).getObserved();
                    if(!defaultValueTypes.contains(observed.getType())){
                        return ValidationStatus.error(Messages.bind(Messages.cantSetDefaultValueForType,observed.getType())) ;
                    }
                }
                return Status.OK_STATUS;
            }
        }) ;
        return context.bindValue(target, model,targetToModel, null);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(final Object element) {
        TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl(), SWT.NONE) ;
        return  editor;
    }

    @Override
    protected IObservableValue doCreateCellEditorObservable(CellEditor cellEditor) {
        return SWTObservables.observeText(cellEditor.getControl(),SWT.Modify);
    }

    @Override
    protected IObservableValue doCreateElementObservable(Object element, ViewerCell cell) {
        return EMFObservables.observeValue((EObject) element, ConnectorDefinitionPackage.Literals.INPUT__DEFAULT_VALUE);
    }





}
