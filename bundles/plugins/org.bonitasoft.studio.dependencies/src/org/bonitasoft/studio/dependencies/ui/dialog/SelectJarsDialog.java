/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.dependencies.ui.dialog;


import java.util.List;

import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class SelectJarsDialog extends ManageJarDialog {

    private List<IRepositoryFileStore> selectedJars;
    private IRepositoryFileStore selectedJar;

    public SelectJarsDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Control control = super.createDialogArea(parent);

        UpdateValueStrategy selectionStartegy = new UpdateValueStrategy() ;
        selectionStartegy.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                if( value == null){
                    return ValidationStatus.error("Selection is empty") ;
                }
                return Status.OK_STATUS;
            }
        }) ;

        context.bindList(ViewersObservables.observeMultiSelection(tableViewer), PojoProperties.list(SelectJarsDialog.class,"selectedJars").observe(this)) ;
        context.bindValue(ViewersObservables.observeSingleSelection(tableViewer), PojoProperties.value(SelectJarsDialog.class,"selectedJar").observe(this), selectionStartegy, null) ;

        return control ;
    }

    @Override
    protected Control createContents(Composite parent) {
        Control control =  super.createContents(parent);
        DialogSupport.create(this, context) ;
        return control ;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.selectJars) ;
    }

    public List<IRepositoryFileStore> getSelectedJars() {
        return selectedJars;
    }

    public void setSelectedJars(List<IRepositoryFileStore> selectedJars) {
        this.selectedJars = selectedJars;
    }

    public IRepositoryFileStore getSelectedJar() {
        return selectedJar;
    }

    public void setSelectedJar(IRepositoryFileStore selectedJar) {
        this.selectedJar = selectedJar;
    }

}
