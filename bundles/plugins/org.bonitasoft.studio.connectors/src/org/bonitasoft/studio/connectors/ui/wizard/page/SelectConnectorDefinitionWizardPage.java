/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorDefinitionTreeLabelProvider;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.provider.ConnectorDefinitionContentProvider;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.WorkbenchMessages;

public class SelectConnectorDefinitionWizardPage extends WizardPage implements ISelectionChangedListener,IDoubleClickListener {

    private TreeViewer treeViewer;
    protected Connector connector;
    private final Connector connectorWorkingCopy;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;

    public SelectConnectorDefinitionWizardPage(final Connector connectorWorkingCopy) {
        super(SelectConnectorDefinitionWizardPage.class.getName());
        setTitle(Messages.selectConnectorDefinitionTitle);
        setDescription(Messages.selectConnectorDefinitionDesc);
        this.connectorWorkingCopy = connectorWorkingCopy ;
    }


    @Override
    public void createControl(final Composite parent) {
        context = new EMFDataBindingContext();

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 10, 0).create());

        final Composite treeComposite = new Composite(composite, SWT.NONE);
        treeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(5, 2).create());
        treeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Text searchText = new Text(treeComposite, SWT.SEARCH | SWT.CANCEL);
        searchText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        searchText.setMessage(WorkbenchMessages.FilteredTree_FilterMessage);
        searchText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent arg0) {
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (treeViewer != null && !treeViewer.getTree().isDisposed()) {
                            treeViewer.refresh();
                        }
                    }
                });

            }
        });


        treeViewer = new TreeViewer(treeComposite, SWT.SINGLE | SWT.BORDER);
        treeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        treeViewer.setContentProvider(getContentProvider());
        treeViewer.setLabelProvider(new ConnectorDefinitionTreeLabelProvider());
        treeViewer.addSelectionChangedListener(this);
        treeViewer.addDoubleClickListener(this);
        treeViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                if (element instanceof Category){
                    final ITreeContentProvider iTreeContentProvider = (ITreeContentProvider)((ContentViewer) viewer).getContentProvider();
                    if(!iTreeContentProvider.hasChildren(element)){
                        return false;
                    }
                    for(final Object c : iTreeContentProvider.getChildren(element)){
                        if(c instanceof ExtendedConnectorDefinition){
                            return selectDefinition(searchText, (ExtendedConnectorDefinition) c);
                        }else{
                            if(select(viewer, element, c)){
                                return true;
                            }
                        }
                    }
                }else if(element instanceof ExtendedConnectorDefinition){
                    return selectDefinition(searchText, (ExtendedConnectorDefinition) element);
                }
                return false;
            }

            private boolean selectDefinition(final Text searchText, final ExtendedConnectorDefinition element) {
                if (searchText == null || searchText.isDisposed() || searchText.getText().isEmpty()) {
                    return true;
                }
                final String text = searchText.getText();
                final String connectorDefinitionLabel = element.getConnectorDefinitionLabel();
                if(connectorDefinitionLabel != null){
                    return connectorDefinitionLabel.contains(text);
                }else{
                    return element.getId().contains(text) || element.getVersion().contains(text);
                }

            }
        }) ;
        treeViewer.setInput(new Object());

        final IValidator selectionValidator = new IValidator() {
            @Override
            public IStatus validate(final Object value) {
                if(value == null || value instanceof Category){
                    return new Status(IStatus.ERROR,ConnectorPlugin.PLUGIN_ID, Messages.selectAConnectorDefWarning);
                }
                return Status.OK_STATUS;
            }
        } ;

        final UpdateValueStrategy idStrategy = new UpdateValueStrategy() ;
        idStrategy.setBeforeSetValidator(selectionValidator) ;
        idStrategy.setConverter(new Converter(ConnectorDefinition.class,String.class) {

            @Override
            public Object convert(final Object from) {
                if(from instanceof ConnectorDefinition){
                    return ((ConnectorDefinition) from).getId() ;
                }
                return null;
            }
        }) ;

        final UpdateValueStrategy versionStrategy = new UpdateValueStrategy() ;
        versionStrategy.setBeforeSetValidator(selectionValidator) ;
        versionStrategy.setConverter(new Converter(ConnectorDefinition.class,String.class) {

            @Override
            public Object convert(final Object from) {
                if(from instanceof ConnectorDefinition){
                    return ((ConnectorDefinition) from).getVersion() ;
                }
                return null;
            }
        }) ;

        context.bindValue(ViewersObservables.observeSingleSelection(treeViewer),
                EMFObservables.observeValue(connectorWorkingCopy, ProcessPackage.Literals.CONNECTOR__DEFINITION_ID), idStrategy, null);
        context.bindValue(ViewersObservables.observeSingleSelection(treeViewer),
                EMFObservables.observeValue(connectorWorkingCopy, ProcessPackage.Literals.CONNECTOR__DEFINITION_VERSION), versionStrategy, null);
        context.bindValue(ViewersObservables.observeSingleSelection(treeViewer), EMFObservables.observeValue(connectorWorkingCopy.getConfiguration(),
                ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__DEFINITION_ID), idStrategy, null);
        context.bindValue(ViewersObservables.observeSingleSelection(treeViewer),
                EMFObservables.observeValue(connectorWorkingCopy.getConfiguration(), ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__VERSION),
                versionStrategy, null);

        pageSupport = WizardPageSupport.create(this, context) ;
        setControl(composite);
    }


    protected IStructuredContentProvider getContentProvider() {
        return new ConnectorDefinitionContentProvider();
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

    protected void refresh(){
        if (treeViewer != null && treeViewer.getTree() != null && !treeViewer.getTree().isDisposed()) {
            treeViewer.setContentProvider(getContentProvider());
            treeViewer.setInput(new Object());
        }
    }

    @Override
    public boolean canFlipToNextPage() {
        return ((IStructuredSelection) treeViewer.getSelection()).getFirstElement() instanceof ConnectorDefinition;
    }


    public ConnectorDefinition getSelectedDefinition() {
        final Object selection = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
        if(selection instanceof ConnectorDefinition){
            return (ConnectorDefinition) selection;
        }
        return null;
    }


    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        //Intend to be override
    }


    @Override
    public void doubleClick(final DoubleClickEvent event) {
        final Object selection =  ((IStructuredSelection) event.getSelection()).getFirstElement() ;
        if(selection instanceof Category){
            treeViewer.expandToLevel(selection, 1);
        }else if(selection instanceof ConnectorDefinition){
            if(getNextPage() != null){
                getContainer().showPage(getNextPage());
            }else{
                if(getWizard().performFinish()){
                    ((WizardDialog) getContainer()).close() ;
                }
            }
        }
    }

}
