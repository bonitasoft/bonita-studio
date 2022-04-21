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


import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.search.JavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
/**
 * @author Romain Bioteau
 *
 */
public class InputTypeEditingSupport extends ObservableValueEditingSupport {

    private static  String[] defaultTypes = new String[]{
        String.class.getName(),
        Boolean.class.getName(),
        Double.class.getName(),
        Float.class.getName(),
        Integer.class.getName(),
        Long.class.getName(),
        List.class.getName(),
        Map.class.getName(),
        Messages.browse
    } ;

    private final DataBindingContext context;
    private Input input;

    public InputTypeEditingSupport(ColumnViewer viewer,DataBindingContext context) {
        super(viewer,context);
        this.context = context ;
    }


    @Override
    protected CellEditor getCellEditor(final Object element) {
        final ComboBoxCellEditor editor = new ComboBoxCellEditor((Composite) getViewer().getControl(), defaultTypes) ;
        editor.getControl().addListener(SWT.Modify, new Listener() {

            @Override
            public void handleEvent(Event event) {
                CCombo combo = (CCombo) editor.getControl() ;
                if(Messages.browse.equals(combo.getText())){
                    openClassSelectionDialog() ;
                }
            }
        }) ;
        return  editor;
    }

    @SuppressWarnings("restriction")
    protected void openClassSelectionDialog() {
        JavaSearchScope scope = new JavaSearchScope(true);
        try {
            scope.add(RepositoryManager.getInstance().getCurrentRepository().getJavaProject());
        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
        }
        FilteredTypesSelectionDialog searchDialog = new FilteredTypesSelectionDialog(Display.getDefault().getActiveShell(), false, null, scope, IJavaSearchConstants.TYPE);
        if (searchDialog.open() == Dialog.OK) {
            input.setType(((IType) searchDialog.getFirstResult()).getFullyQualifiedName()) ;
            getViewer().refresh() ;
        }
    }


    @Override
    protected Binding createBinding(IObservableValue target, IObservableValue model) {
        UpdateValueStrategy targetToModel =  new UpdateValueStrategy() ;
        targetToModel.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object input) {
                if(input == null || input.toString().isEmpty()){
                    return ValidationStatus.error(Messages.typeIsEmpty) ;
                }
                return Status.OK_STATUS;
            }
        }) ;
        return context.bindValue(target, model,targetToModel , null);
    }

    @Override
    protected IObservableValue doCreateCellEditorObservable(CellEditor cellEditor) {
        return SWTObservables.observeText(cellEditor.getControl());
    }


    @Override
    protected IObservableValue doCreateElementObservable(Object element, ViewerCell cell) {
        input = (Input) element ;
        return EMFObservables.observeValue((EObject) element, ConnectorDefinitionPackage.Literals.INPUT__TYPE);
    }





}
