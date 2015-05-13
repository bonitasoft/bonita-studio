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
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.wizard.support.OutputNameEditingSupport;
import org.bonitasoft.studio.connector.model.definition.wizard.support.OutputTypeEditingSupport;
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
public class OutputsWizardPage extends WizardPage implements ISelectionChangedListener{

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;

    private final ConnectorDefinition definition;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;

    private Button removeButton;
    private Button downButton;
    private Button upButton;

    public OutputsWizardPage(ConnectorDefinition definition) {
        super(OutputsWizardPage.class.getName());
        setTitle(Messages.connectorOutputTitle) ;
        setDescription(Messages.connectorOutputDesc) ;
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

    private String generateOutputName() {
        Set<String> names = new HashSet<String>() ;
        for(Output o : definition.getOutput()){
            names.add(o.getName()) ;
        }

        return NamingUtils.generateNewName(names, Messages.defaultOutputName, 1);
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

        final TableViewer outputsViewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI) ;
        outputsViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        outputsViewer.getTable().setHeaderVisible(true) ;
        outputsViewer.getTable().setLinesVisible(true) ;
        outputsViewer.addSelectionChangedListener(this) ;
        outputsViewer.setContentProvider(new ArrayContentProvider()) ;
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(30));
        layout.addColumnData(new ColumnWeightData(70));
        outputsViewer.getTable().setLayout(layout) ;

        outputsViewer.getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

            @Override
            public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {}

            @Override
            public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {}

            @Override
            public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                outputsViewer.refresh() ;
            }

            @Override
            public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {}
        }) ;

        TableViewerColumn outputNameColumn = new TableViewerColumn(outputsViewer, SWT.FILL);
        outputNameColumn.getColumn().setText(Messages.name) ;

        outputNameColumn.setEditingSupport(new OutputNameEditingSupport(outputsViewer, definition,context));
        outputNameColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Output)element).getName();
            }
        });



        TableViewerColumn outputTypeColumn = new TableViewerColumn(outputsViewer, SWT.FILL);
        outputTypeColumn.getColumn().setText(Messages.type) ;
        outputTypeColumn.setEditingSupport(new OutputTypeEditingSupport(outputsViewer, context));
        outputTypeColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Output)element).getType();
            }
        });


        context.bindValue(ViewersObservables.observeInput(outputsViewer), EMFObservables.observeValue(definition, ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__OUTPUT)) ;

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create()) ;

        final Button addButton = new Button(buttonComposite, SWT.FLAT) ;
        addButton.setText(Messages.add) ;
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Output input = ConnectorDefinitionFactory.eINSTANCE.createOutput() ;
                input.setName(generateOutputName()) ;
                input.setType(String.class.getName()) ;
                definition.getOutput().add(input) ;
                outputsViewer.refresh() ;
                outputsViewer.editElement(input, 0) ;
            }
        }) ;


        upButton = new Button(buttonComposite, SWT.FLAT) ;
        upButton.setText(Messages.up) ;
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        upButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Output selectedInput = (Output) ((IStructuredSelection) outputsViewer.getSelection()).getFirstElement() ;
                int index = definition.getOutput().indexOf(selectedInput) ;
                if(index > 0){
                    definition.getOutput().move(index-1, selectedInput) ;
                }
                outputsViewer.refresh() ;
            }
        }) ;

        downButton = new Button(buttonComposite, SWT.FLAT) ;
        downButton.setText(Messages.down) ;
        downButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        downButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Output selectedInput = (Output) ((IStructuredSelection) outputsViewer.getSelection()).getFirstElement() ;
                int index = definition.getOutput().indexOf(selectedInput) ;
                if(index < definition.getOutput().size()-1){
                    definition.getOutput().move(index+1, selectedInput) ;
                }
            }
        }) ;

        removeButton = new Button(buttonComposite, SWT.FLAT) ;
        removeButton.setText(Messages.remove) ;
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                definition.getOutput().removeAll(((IStructuredSelection) outputsViewer.getSelection()).toList()) ;
                outputsViewer.refresh() ;
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
