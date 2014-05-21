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

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Group;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.definition.dialog.SelectPageWidgetDialog;
import org.bonitasoft.studio.connector.model.definition.dialog.WidgetLabelProvider;
import org.bonitasoft.studio.connector.model.definition.provider.ConnectorDefinitionItemProviderAdapterFactory;
import org.bonitasoft.studio.connector.model.definition.wizard.support.WidgetInputNameEditingSupport;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
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
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * @author Romain Bioteau
 *
 */
public class PageWidgetsWizardPage extends WizardPage implements ISelectionChangedListener{

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;

    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private Page page;
    private final ConnectorDefinition definition;
    private final ConnectorDefinitionItemProviderAdapterFactory adapterFactory;
    private final Properties messages;
    private String displayName ;
    private String pageDescription ;
    private final Page originalPage;

    private Button editButton;

    private Button upButton;

    private Button downButton;

    private Button removeButton;

    private final DefinitionResourceProvider messageProvider;

    public PageWidgetsWizardPage(ConnectorDefinition definition,Page originalPage,Page page,Properties messages,DefinitionResourceProvider messageProvider) {
        super(PageWidgetsWizardPage.class.getName());
        setTitle(Messages.widgetPageTitle) ;
        setDescription(Messages.widgetPageDesc) ;
        this.page = page ;
        this.originalPage = originalPage ;
        this.definition = definition ;
        this.messages = messages ;
        this.messageProvider = messageProvider ;
        setDisplayName(messageProvider.getPageTitle(messages, page.getId())) ;
        setPageDescription(messageProvider.getPageDescription(messages, page.getId())) ;
        adapterFactory = new ConnectorDefinitionItemProviderAdapterFactory();

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
        adapterFactory.dispose() ;

    }


    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        context = new EMFDataBindingContext() ;
        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create()) ;

        final Label pageIdLabel = new Label(mainComposite, SWT.NONE);
        pageIdLabel.setText(Messages.pageId+" *");
        pageIdLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;

        final Text idText = new Text(mainComposite, SWT.BORDER);
        idText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        UpdateValueStrategy idStrategy = new UpdateValueStrategy() ;
        idStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                if(value == null || value.toString().isEmpty()){
                    return ValidationStatus.error(Messages.idIsEmpty) ;
                }else if(value.toString().contains(" ")){
					return  ValidationStatus.error(Messages.noWhiteSpaceInPageID) ;
				}else if(!FileUtil.isValidName(value.toString())){
					return  ValidationStatus.error(Messages.idIsInvalid) ;
				}
                for(Page p : definition.getPage()){
                    if(!p.equals(originalPage) && p.getId().equals(value.toString())){
                        return ValidationStatus.error(Messages.idAlreadyExists) ;
                    }
                }
                return Status.OK_STATUS;
            }
        }) ;

        context.bindValue(SWTObservables.observeText(idText, SWT.Modify), EMFObservables.observeValue(page, ConnectorDefinitionPackage.Literals.PAGE__ID),idStrategy,null) ;

        final Label displayNameLabel = new Label(mainComposite, SWT.NONE);
        displayNameLabel.setText(Messages.displayName);
        displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;

        final Text displayNameText = new Text(mainComposite, SWT.BORDER);
        displayNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        IObservableValue displayNameObs =   PojoProperties.value(PageWidgetsWizardPage.class, "displayName").observe(this) ;
        context.bindValue(SWTObservables.observeText(displayNameText, SWT.Modify),displayNameObs) ;

        final Label descriptionLabel = new Label(mainComposite, SWT.NONE);
        descriptionLabel.setText(Messages.pageDescLabel);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create()) ;

        final Text descriptionText = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT,60).create());

        IObservableValue descObs =   PojoProperties.value(PageWidgetsWizardPage.class, "pageDescription").observe(this) ;
        context.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify),descObs) ;

        final Label inputsLabel = new Label(mainComposite, SWT.NONE);
        inputsLabel.setText(Messages.widgets);
        inputsLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create()) ;

        createWidgetViewer(mainComposite) ;
        updateButtons(new StructuredSelection()) ;

        pageSupport = WizardPageSupport.create(this, context) ;
        setControl(mainComposite) ;
    }

    protected void createWidgetViewer(Composite parent) {
        final Composite viewerComposite = new Composite(parent, SWT.NONE) ;
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final TreeViewer inputsViewer = new TreeViewer(viewerComposite, SWT.BORDER | SWT.FULL_SELECTION) ;
        inputsViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        inputsViewer.getTree().setHeaderVisible(true) ;
        inputsViewer.addSelectionChangedListener(this) ;
        inputsViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory)) ;
        inputsViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory)) ;
        inputsViewer.getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

            @Override
            public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {}

            @Override
            public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {}

            @Override
            public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                inputsViewer.setSelection(new StructuredSelection()) ;
            }

            @Override
            public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {}

        }) ;

        final TableLayout layout =new TableLayout() ;
        layout.addColumnData(new ColumnWeightData(70)) ;
        layout.addColumnData(new ColumnWeightData(30)) ;
        inputsViewer.getTree().setLayout(layout) ;


        TreeViewerColumn widgetColumn = new TreeViewerColumn(inputsViewer, SWT.FILL);
        widgetColumn.getColumn().setText(Messages.widget) ;
        widgetColumn.setLabelProvider(new ColumnLabelProvider(){
            WidgetLabelProvider labelProvider = new WidgetLabelProvider() ;

            @Override
            public String getText(Object element) {
                Component comp =  (Component)element ;
                String label = messageProvider.getFieldLabel(messages, comp.getId()) ;
                if(label == null || label.isEmpty()){
                    label = comp.getId() ;
                }
                return labelProvider.getText(comp.eClass()) +" "+ label;
            }
        });

        TreeViewerColumn inputNameColumn = new TreeViewerColumn(inputsViewer, SWT.FILL);
        inputNameColumn.getColumn().setText(Messages.input) ;
        inputNameColumn.setEditingSupport(new WidgetInputNameEditingSupport(inputsViewer, definition,page,context));
        inputNameColumn.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public String getText(Object element) {
                if(element instanceof WidgetComponent){
                    return ((WidgetComponent)element).getInputName();
                }else{
                    return "" ;
                }
            }
        });




        context.bindValue(ViewersObservables.observeInput(inputsViewer), PojoProperties.value(PageWidgetsWizardPage.class,"page").observe(this)) ;

        inputsViewer.expandAll();

        final Composite buttonComposite = new Composite(viewerComposite, SWT.NONE) ;
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create()) ;

        final Button addButton = new Button(buttonComposite, SWT.FLAT) ;
        addButton.setText(Messages.Add) ;
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SelectPageWidgetDialog dialog = new SelectPageWidgetDialog(Display.getDefault().getActiveShell(),definition,page,null, null) ;
                if(dialog.open() == Dialog.OK){
                    Component widget = dialog.getWidget() ;
                    messageProvider.setFieldLabel(messages, widget.getId(), dialog.getDisplayName()) ;
                    messageProvider.setFieldDescription(messages, widget.getId(), dialog.getDescription()) ;
                    Component component = (Component) ((IStructuredSelection) inputsViewer.getSelection()).getFirstElement() ;
                    if(component instanceof Group){
                        ((Group)component).getWidget().add(widget) ;
                    }else{
                        page.getWidget().add(widget) ;
                    }
                    inputsViewer.refresh() ;
                    getContainer().updateMessage() ;
                    getContainer().updateButtons() ;
                }

            }
        }) ;

        editButton = new Button(buttonComposite, SWT.FLAT) ;
        editButton.setText(Messages.update) ;
        editButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        editButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Component component = (Component) ((IStructuredSelection) inputsViewer.getSelection()).getFirstElement() ;
                SelectPageWidgetDialog dialog = new SelectPageWidgetDialog(Display.getDefault().getActiveShell(),definition,page,component, EcoreUtil.copy(component)) ;
                dialog.setDisplayName(messageProvider.getFieldLabel(messages, component.getId())) ;
                dialog.setDescription(messageProvider.getFieldDescription(messages, component.getId())) ;
                if(dialog.open() == Dialog.OK){
                    Component widget = dialog.getWidget() ;
                    messageProvider.setFieldLabel(messages, widget.getId(), dialog.getDisplayName()) ;
                    messageProvider.setFieldDescription(messages, widget.getId(), dialog.getDescription()) ;
                    if(component != null && component.eContainer() instanceof Group){
                        Group group = (Group) component.eContainer();
                        int i = group.getWidget().indexOf(component) ;
                        group.getWidget().remove(i) ;
                        group.getWidget().add(i,widget) ;
                    }else{
                        int i = page.getWidget().indexOf(component) ;
                        page.getWidget().remove(i) ;
                        page.getWidget().add(i,widget) ;
                    }

                    inputsViewer.refresh() ;
                    getContainer().updateMessage() ;
                    getContainer().updateButtons() ;
                }

            }
        }) ;


        upButton = new Button(buttonComposite, SWT.FLAT) ;
        upButton.setText(Messages.up) ;
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        upButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Component selectedWidget = (Component) ((IStructuredSelection) inputsViewer.getSelection()).getFirstElement() ;
                int index = page.getWidget().indexOf(selectedWidget) ;
                if(index > 0){
                    page.getWidget().move(index-1, selectedWidget) ;
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
                Component selectedWidget = (Component) ((IStructuredSelection) inputsViewer.getSelection()).getFirstElement() ;
                int index = page.getWidget().indexOf(selectedWidget) ;
                if(index < page.getWidget().size()-1){
                    page.getWidget().move(index+1, selectedWidget) ;
                }
                inputsViewer.refresh() ;
            }
        }) ;

        removeButton = new Button(buttonComposite, SWT.FLAT) ;
        removeButton.setText(Messages.remove) ;
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                page.getWidget().removeAll(((IStructuredSelection) inputsViewer.getSelection()).toList()) ;
                inputsViewer.refresh() ;
            }
        }) ;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }


    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
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
