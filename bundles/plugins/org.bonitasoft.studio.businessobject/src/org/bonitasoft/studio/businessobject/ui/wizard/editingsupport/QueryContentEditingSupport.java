/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard.editingsupport;

import java.util.ArrayList;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.QueryParameter;
import org.bonitasoft.studio.businessobject.ui.wizard.QueryWizard;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 * 
 */
public class QueryContentEditingSupport extends EditingSupport {

    private IViewerObservableValue businessObjectObservable;

    public QueryContentEditingSupport(ColumnViewer viewer, IViewerObservableValue viewerObservableValue) {
        super(viewer);
        this.businessObjectObservable = viewerObservableValue;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        return new DialogCellEditor((Composite) getViewer().getControl()) {

            @Override
            protected Object openDialogBox(Control cellEditorWindow) {
                Query query = (Query) element;
                QueryWizard queryWizard = new QueryWizard((BusinessObject) businessObjectObservable.getValue(), query);
                CustomWizardDialog dialog = new CustomWizardDialog(cellEditorWindow.getShell(), queryWizard, IDialogConstants.OK_LABEL);
                if (dialog.open() == Dialog.OK) {
                    return queryWizard.getQueryWorkingCopy();
                }
                return null;
            }
        };
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
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(Object element) {
        return ((Query) element).getContent();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(Object element, Object value) {
        if (value instanceof Query) {
            Query workingCopy = (Query) value;
            ((Query) element).setName(workingCopy.getName());
            ((Query) element).setContent(workingCopy.getContent());
            ((Query) element).setReturnType(workingCopy.getReturnType());
            ((Query) element).setQueryParameters(new ArrayList<QueryParameter>(workingCopy.getQueryParameters()));
        } else if (value instanceof String) {
            ((Query) element).setContent((String) value);
        }
        getViewer().update(element, null);

    }

}
