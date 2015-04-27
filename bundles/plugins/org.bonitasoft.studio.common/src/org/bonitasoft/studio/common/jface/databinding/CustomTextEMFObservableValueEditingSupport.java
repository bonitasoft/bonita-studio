/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.jface.databinding;

import static com.google.common.base.Preconditions.checkArgument;

import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

public abstract class CustomTextEMFObservableValueEditingSupport extends ObservableValueEditingSupport {

    private final DataBindingContext dbc;
    private final EStructuralFeature featureToEdit;
    private TextCellEditor cellEditor;
    private final IMessageManager messageManager;
    private String controlId;

    public CustomTextEMFObservableValueEditingSupport(final ColumnViewer viewer, final EStructuralFeature featureToEdit,
            final IMessageManager messageManager, final DataBindingContext dbc) {
        super(viewer, dbc);
        this.dbc = dbc;
        this.messageManager = messageManager;
        this.featureToEdit = featureToEdit;
    }

    public void setControlId(final String controlId) {
        this.controlId = controlId;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport#doCreateCellEditorObservable(org.eclipse.jface.viewers.CellEditor)
     */
    @Override
    protected IObservableValue doCreateCellEditorObservable(final CellEditor cellEditor) {
        return SWTObservables.observeText(cellEditor.getControl(), SWT.Modify);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport#doCreateElementObservable(java.lang.Object,
     * org.eclipse.jface.viewers.ViewerCell)
     */
    @Override
    protected IObservableValue doCreateElementObservable(final Object element, final ViewerCell cell) {
        checkArgument(element instanceof EObject);
        final IObservableValue observableValue = EMFEditObservables.observeValue(TransactionUtil.getEditingDomain(element), (EObject) element,
                featureToEdit);
        observableValue.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
        return observableValue;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport#createBinding(org.eclipse.core.databinding.observable.value.IObservableValue,
     * org.eclipse.core.databinding.observable.value.IObservableValue)
     */
    @Override
    protected Binding createBinding(final IObservableValue target, final IObservableValue model) {
        final Binding binding = dbc.bindValue(target, model, targetToModelConvertStrategy(observedElement(model)), null);
        final IObservableValue validationStatus = binding.getValidationStatus();
        validationStatus.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                validationStatusChanged((IStatus) event.diff.getNewValue());
            }

        });
        return binding;
    }

    private EObject observedElement(final IObservableValue model) {
        return (EObject) ((EditingDomainEObjectObservableValue) model).getObserved();
    }

    protected void updateTextEditorFeedback(final IStatus status) {
        if (cellEditor != null && cellEditor.getControl() != null && !cellEditor.getControl().isDisposed()) {
            final Control control = cellEditor.getControl();
            if (status.getSeverity() == IStatus.ERROR) {
                control.setBackground(JFaceColors.getErrorBackground(control.getDisplay()));
                control.setForeground(JFaceColors.getErrorText(control.getDisplay()));
            } else {
                control.setBackground(control.getDisplay().getSystemColor(SWT.COLOR_WHITE));
                control.setForeground(control.getDisplay().getSystemColor(SWT.COLOR_BLACK));
            }
        }
    }

    protected abstract UpdateValueStrategy targetToModelConvertStrategy(EObject element);

    protected void validationStatusChanged(final IStatus status) {
        updateTextEditorFeedback(status);
        messageManager.removeAllMessages();
        if (!status.isOK() && getViewer().isCellEditorActive()) {
            messageManager.addMessage(null, status.getMessage(), null, new StatusToMessageSeverity(status).toMessageSeverity());
        }
    }

    @Override
    protected TextCellEditor getCellEditor(final Object object) {
        cellEditor = new TextCellEditor((Composite) getViewer().getControl()) {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.CellEditor#deactivate()
             */
            @Override
            public void deactivate() {
                super.deactivate();
                messageManager.removeAllMessages();
            }
        };
        cellEditor.getControl().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, controlId);
        return cellEditor;
    }

}
