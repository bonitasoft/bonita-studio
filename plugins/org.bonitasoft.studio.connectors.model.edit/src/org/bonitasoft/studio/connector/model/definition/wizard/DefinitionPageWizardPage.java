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

import java.util.Properties;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.IWizardPage;
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
public class DefinitionPageWizardPage extends WizardPage implements ISelectionChangedListener {

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;

    private final ConnectorDefinition definition;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private final Properties messages;

    private TableViewer pagesViewer;

    private Page editingPage;

    private Button downButton;

    private Button removeButton;

    private Button upButton;

    private Button editButton;

    private final DefinitionResourceProvider messageProvider;

    public DefinitionPageWizardPage(ConnectorDefinition definition,Properties messages,DefinitionResourceProvider messageProvider) {
        super(DefinitionPageWizardPage.class.getName());
        setTitle(Messages.connectorPageDefinitionTitle) ;
        setDescription(Messages.connectorPageDefinitionDesc) ;
        this.definition = definition ;
        this.messages = messages;
        this.messageProvider = messageProvider ;
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

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        context = new EMFDataBindingContext() ;
        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create()) ;

        pagesViewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION) ;
        pagesViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        pagesViewer.getTable().setHeaderVisible(true) ;
        pagesViewer.getTable().setLinesVisible(true) ;
        pagesViewer.addSelectionChangedListener(this) ;

        final TableLayout layout = new TableLayout() ;
        layout.addColumnData(new ColumnWeightData(25)) ;
        layout.addColumnData(new ColumnWeightData(25)) ;
        layout.addColumnData(new ColumnWeightData(50)) ;
        pagesViewer.getTable().setLayout(layout) ;
        pagesViewer.setContentProvider(new ArrayContentProvider()) ;

        TableViewerColumn pageIdColumn = new TableViewerColumn(pagesViewer, SWT.FILL);
        pageIdColumn.getColumn().setText(Messages.pageId) ;
        pageIdColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Page)element).getId();
            }
        });

        TableViewerColumn pageDisplayNameColumn = new TableViewerColumn(pagesViewer, SWT.FILL);
        pageDisplayNameColumn.getColumn().setText(Messages.displayName) ;
        pageDisplayNameColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return messageProvider.getPageTitle(messages, ((Page)element).getId()) ;
            }
        });


        TableViewerColumn pageDescriptionColumn = new TableViewerColumn(pagesViewer, SWT.FILL);
        pageDescriptionColumn.getColumn().setText(Messages.pageDescLabel) ;
        pageDescriptionColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                return messageProvider.getPageDescription(messages, ((Page)element).getId()) ;
            }
        });

        context.bindValue(ViewersObservables.observeInput(pagesViewer), EMFObservables.observeValue(definition, ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__PAGE)) ;

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create()) ;

        final Button addButton = new Button(buttonComposite, SWT.FLAT) ;
        addButton.setText(Messages.add) ;
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Page newPage = ConnectorDefinitionFactory.eINSTANCE.createPage() ;
                editingPage = null ;
                IWizardPage page = new PageWidgetsWizardPage(definition,null,newPage, messages,messageProvider) ;
                page.setWizard(getWizard()) ;
                getContainer().showPage(page) ;
            }
        }) ;

        editButton = new Button(buttonComposite, SWT.FLAT) ;
        editButton.setText(Messages.update) ;
        editButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        editButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Page page = (Page) ((IStructuredSelection) pagesViewer.getSelection()).getFirstElement() ;
                editingPage = page ;
                IWizardPage wizPage = new PageWidgetsWizardPage(definition,page,EcoreUtil.copy(page), messages,messageProvider) ;
                wizPage.setWizard(getWizard()) ;
                getContainer().showPage(wizPage) ;
            }
        }) ;

        upButton = new Button(buttonComposite, SWT.FLAT) ;
        upButton.setText(Messages.up) ;
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        upButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Page selectedPage = (Page) ((IStructuredSelection) pagesViewer.getSelection()).getFirstElement() ;
                int index = definition.getPage().indexOf(selectedPage) ;
                if(index > 0){
                    definition.getPage().move(index-1, selectedPage) ;
                }
                pagesViewer.refresh() ;
            }
        }) ;

        downButton = new Button(buttonComposite, SWT.FLAT) ;
        downButton.setText(Messages.down) ;
        downButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        downButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Page selectedPage = (Page) ((IStructuredSelection) pagesViewer.getSelection()).getFirstElement() ;
                int index = definition.getPage().indexOf(selectedPage) ;
                if(index < definition.getPage().size()-1){
                    definition.getPage().move(index+1, selectedPage) ;
                }
            }
        }) ;

        removeButton = new Button(buttonComposite, SWT.FLAT) ;
        removeButton.setText(Messages.remove) ;
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                definition.getPage().removeAll(((IStructuredSelection) pagesViewer.getSelection()).toList()) ;
                pagesViewer.refresh() ;
            }
        }) ;

        updateButtons(new StructuredSelection()) ;
        pageSupport = WizardPageSupport.create(this, context) ;
        setControl(mainComposite) ;
    }

    public void refresh(){
        if(pagesViewer != null && !pagesViewer.getTable().isDisposed()){
            pagesViewer.refresh() ;
        }
    }

    public ConnectorDefinition getDefinition() {
        return definition;
    }

    public Page getEditingPage() {
        return editingPage;
    }

    public Properties getMessages() {
        return messages;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        updateButtons(event.getSelection()) ;
    }

    private void updateButtons(ISelection selection) {
        if(editButton != null && !editButton.isDisposed()){
            editButton.setEnabled(!selection.isEmpty()) ;
        }

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
