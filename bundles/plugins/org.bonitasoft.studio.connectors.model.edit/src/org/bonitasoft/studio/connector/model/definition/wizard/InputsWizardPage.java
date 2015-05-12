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
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.wizard.support.DefaultValueEditingSupport;
import org.bonitasoft.studio.connector.model.definition.wizard.support.InputMandatoryEditingSupport;
import org.bonitasoft.studio.connector.model.definition.wizard.support.InputNameEditingSupport;
import org.bonitasoft.studio.connector.model.definition.wizard.support.InputTypeEditingSupport;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


/**
 * @author Romain Bioteau
 *
 */
public class InputsWizardPage extends WizardPage implements ISelectionChangedListener {

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;

    private final ConnectorDefinition definition;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;

    private Button upButton;
    private Button downButton;
    private Button removeButton;

    public InputsWizardPage(ConnectorDefinition definition) {
        super(InputsWizardPage.class.getName());
        setTitle(Messages.connectorInputTitle) ;
        setDescription(Messages.connectorInputDesc) ;
        this.definition = definition ;
    }

    @Override
    public void dispose() {
        super.dispose();
        if(context != null){
            context.dispose() ;
        }
        if(pageSupport != null){
            pageSupport.dispose() ;
        }

    }

    private String generateInputName() {
        Set<String> names = new HashSet<String>() ;
        for(Input i : definition.getInput()){
            names.add(i.getName()) ;
        }

        return NamingUtils.generateNewName(names, Messages.defaultInputName, 1);
    }


    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        context = new EMFDataBindingContext() ;
        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create()) ;

        final TableViewer inputsViewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI) ;
        inputsViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        inputsViewer.getTable().setHeaderVisible(true) ;
        inputsViewer.getTable().setLinesVisible(true) ;
        inputsViewer.addSelectionChangedListener(this) ;
        inputsViewer.setContentProvider(new ArrayContentProvider()) ;
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(25));
        layout.addColumnData(new ColumnWeightData(20));
        layout.addColumnData(new ColumnWeightData(30));
        layout.addColumnData(new ColumnWeightData(25));
        inputsViewer.getTable().setLayout(layout) ;

        inputsViewer.getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

            @Override
            public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {}

            @Override
            public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {}

            @Override
            public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                inputsViewer.refresh() ;
            }

            @Override
            public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {}
        }) ;

        TableViewerColumn inputNameColumn = new TableViewerColumn(inputsViewer, SWT.FILL);
        inputNameColumn.getColumn().setText(Messages.name) ;

        inputNameColumn.setEditingSupport(new InputNameEditingSupport(inputsViewer, definition,context));
        inputNameColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Input)element).getName();
            }
        });

        TableViewerColumn mandatoryColumn = new TableViewerColumn(inputsViewer, SWT.FILL);
        mandatoryColumn.getColumn().setText(Messages.mandatory) ;
        mandatoryColumn.setEditingSupport(new InputMandatoryEditingSupport(inputsViewer, context));
        mandatoryColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                if(((Input)element).isMandatory()){
                    return Messages.mandatory ;
                }else{
                    return Messages.optional ;
                }
            }
        });


        TableViewerColumn inputTypeColumn = new TableViewerColumn(inputsViewer, SWT.FILL);
        inputTypeColumn.getColumn().setText(Messages.type) ;
        inputTypeColumn.setEditingSupport(new InputTypeEditingSupport(inputsViewer, context));
        inputTypeColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Input)element).getType();
            }
        });


        TableViewerColumn defautltValueColumn = new TableViewerColumn(inputsViewer, SWT.FILL);
        defautltValueColumn.getColumn().setText(Messages.defaultValue) ;
        defautltValueColumn.setEditingSupport(new DefaultValueEditingSupport(inputsViewer, context));
        defautltValueColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Input)element).getDefaultValue() ;
            }
        });

        context.bindValue(ViewersObservables.observeInput(inputsViewer), EMFObservables.observeValue(definition, ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__INPUT)) ;

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create()) ;

        final Button addButton = new Button(buttonComposite, SWT.FLAT) ;
        addButton.setText(Messages.add) ;
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Input input = ConnectorDefinitionFactory.eINSTANCE.createInput() ;
                input.setName(generateInputName()) ;
                input.setType(String.class.getName()) ;
                definition.getInput().add(input) ;
                inputsViewer.refresh() ;
                inputsViewer.editElement(input, 0) ;
            }
        }) ;


        upButton = new Button(buttonComposite, SWT.FLAT) ;
        upButton.setText(Messages.up) ;
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        upButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Input selectedInput = (Input) ((IStructuredSelection) inputsViewer.getSelection()).getFirstElement() ;
                int index = definition.getInput().indexOf(selectedInput) ;
                if(index > 0){
                    definition.getInput().move(index-1, selectedInput) ;
                }
                inputsViewer.refresh() ;
            }
        }) ;

        downButton = new Button(buttonComposite, SWT.FLAT) ;
        downButton.setText(Messages.down) ;
        downButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        downButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Input selectedInput = (Input) ((IStructuredSelection) inputsViewer.getSelection()).getFirstElement() ;
                int index = definition.getInput().indexOf(selectedInput) ;
                if(index < definition.getInput().size()-1){
                    definition.getInput().move(index+1, selectedInput) ;
                }
            }
        }) ;

        removeButton = new Button(buttonComposite, SWT.FLAT) ;
        removeButton.setText(Messages.remove) ;
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                definition.getInput().removeAll(((IStructuredSelection) inputsViewer.getSelection()).toList()) ;
                inputsViewer.refresh() ;
            }
        }) ;

        updateButtons(new StructuredSelection()) ;
        pageSupport = WizardPageSupport.create(this, context) ;
        setControl(mainComposite) ;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        updateButtons(event.getSelection()) ;
    }

    private void updateButtons(ISelection selection) {
        if(upButton != null && !upButton.isDisposed()){
            upButton.setEnabled(!selection.isEmpty()) ;
        }

        if(downButton != null && !downButton.isDisposed()){
            downButton.setEnabled(!selection.isEmpty()) ;
        }

        if(removeButton != null && !removeButton.isDisposed()){
            removeButton.setEnabled(!selection.isEmpty()) ;
        }
    }

}
