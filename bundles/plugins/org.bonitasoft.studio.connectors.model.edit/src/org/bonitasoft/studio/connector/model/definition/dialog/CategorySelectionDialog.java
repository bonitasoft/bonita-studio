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
package org.bonitasoft.studio.connector.model.definition.dialog;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.provider.ExtendedCategory;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorDefinitionTreeLabelProvider;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.IViewerObservableList;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


public class CategorySelectionDialog extends Dialog {

    private TreeViewer categoryViewer;
    private List<ExtendedCategory> categories  = new ArrayList<>() ;
    private DataBindingContext context;
    private final DefinitionResourceProvider messageProvider;

    public CategorySelectionDialog(Shell parentShell,DefinitionResourceProvider messageProvider) {
        super(parentShell);
        this.messageProvider = messageProvider ;
    }

    @Override
    protected Control createContents(Composite parent) {
        Control contents =  super.createContents(parent);
        getButton(IDialogConstants.OK_ID).setEnabled(!categories.isEmpty());
        return contents ;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(300, 300).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10,10).create()) ;

        context = new DataBindingContext() ;

        categoryViewer = new TreeViewer(mainComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION) ;
        categoryViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        categoryViewer.setContentProvider(new DefinitionCategoryContentProvider()) ;
        categoryViewer.setLabelProvider(new ConnectorDefinitionTreeLabelProvider()) ;
        categoryViewer.setInput(getAllCategories()) ;
        final IViewerObservableList observeSelection = ViewerProperties.multipleSelection().observe(categoryViewer);
        MultiValidator validator = new MultiValidator() {

            @Override
            protected IStatus validate() {
                if(observeSelection.isEmpty()){
                    return ValidationStatus.error("");
                }
                return ValidationStatus.ok();
            }
        };
        context.addValidationStatusProvider(validator);
        context.bindList(observeSelection, validator.observeValidatedList(PojoProperties.list("categories").observe(this)), null,new UpdateListStrategy()) ;

        DialogSupport.create(this,context);

        return mainComposite ;
    }

    @Override
    public boolean close() {
        boolean closed = super.close();
        if(closed && context != null){
            context.dispose() ;
        }
        return closed;
    }

    protected List<ExtendedCategory> getAllCategories() {
        return messageProvider.getAllCategories();
    }


    public List<ExtendedCategory> getCategories(){
        return categories ;
    }

    public void setCategories(List<ExtendedCategory> categories) {
        this.categories = categories;
    }

}
