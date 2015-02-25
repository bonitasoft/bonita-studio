/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms.mapping;

import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.EObjectSelectionProviderSection;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class FormMappingSection extends EObjectSelectionProviderSection {

    private EMFDataBindingContext context;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getSectionDescription()
     */
    @Override
    public String getSectionDescription() {
        return "Form Mapping description";
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
   
        
        context = new EMFDataBindingContext();
        final TabbedPropertySheetWidgetFactory widgetFactory = aTabbedPropertySheetPage.getWidgetFactory();
    
        Composite mainComposite = widgetFactory.createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        Composite buttonsComposite = widgetFactory.createComposite(mainComposite);
        buttonsComposite.setLayoutData(GridDataFactory.swtDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0,0).spacing(0, 3).create());
        
        Button addButton = widgetFactory.createButton(buttonsComposite, "Add...", SWT.PUSH);
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
               addFormMapping();
            }
        });
        widgetFactory.createButton(buttonsComposite, "Edit...", SWT.PUSH);
        Button removeButton = widgetFactory.createButton(buttonsComposite, "Remove", SWT.PUSH);
       
        
        
        Table table = widgetFactory.createTable(mainComposite, SWT.BORDER);
        table.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final TableViewer viewer = new TableViewer(table);
        viewer.setLabelProvider(new LabelProvider(){
            @Override
            public String getText(Object element) {
                if(element instanceof FormMapping){
                    return ((FormMapping) element).getTargetForm();
                }
                return super.getText(element);
            }
        });
        viewer.setContentProvider(new ObservableListContentProvider());
        viewer.setInput(CustomEMFEditObservables.observeDetailList(Realm.getDefault(), getEObjectObservable(), ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPINGS));
    
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
               removeSelectedFormMapping(viewer);
            }
        });
    }

    protected void removeSelectedFormMapping(TableViewer viewer) {
        IStructuredSelection iSelection = (IStructuredSelection) viewer.getSelection();
        IObservableList formsMappingListObservable = CustomEMFEditObservables.observeDetailList(Realm.getDefault(), getEObjectObservable(), ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPINGS);
        formsMappingListObservable.removeAll(iSelection.toList());
    }

    protected void addFormMapping() {
        IObservableList formsMappingListObservable = CustomEMFEditObservables.observeDetailList(Realm.getDefault(), getEObjectObservable(), ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPINGS);
        FormMapping formMapping = ProcessFactory.eINSTANCE.createFormMapping();
        Element element = (Element) getEObjectObservable().getValue();
        formMapping.setTaskName(element.getName());
        formMapping.setTargetForm(String.valueOf(System.currentTimeMillis()));
        formsMappingListObservable.add(formMapping);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
    }

}
