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
package org.bonitasoft.studio.actors.ui.wizard.page;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorDefinitionTreeLabelProvider;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectConnectorDefinitionWizardPage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

public class SelectFilterDefinitionWizardPage extends SelectConnectorDefinitionWizardPage implements ISelectionChangedListener,IDoubleClickListener {

    private FilteredTree filterTree;
    protected Connector connector;
    private final Connector connectorWorkingCopy;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private final DefinitionResourceProvider messageProvider;

    public SelectFilterDefinitionWizardPage(Connector connectorWorkingCopy,DefinitionResourceProvider messageProvider) {
        super(connectorWorkingCopy,messageProvider);
        setTitle(Messages.selectFilterDefinitionTitle);
        setDescription(Messages.selectFilterDefinitionDesc);
        this.connectorWorkingCopy = connectorWorkingCopy ;
        this.messageProvider = messageProvider;
    }


    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 10, 0).create());
        filterTree = new FilteredTree(composite, SWT.SINGLE | SWT.BORDER, new PatternFilter(), true);
        filterTree.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
        filterTree.getViewer().setContentProvider(getContentProvider());
        filterTree.getViewer().setLabelProvider(new ConnectorDefinitionTreeLabelProvider(messageProvider));
        filterTree.getViewer().addSelectionChangedListener(this) ;
        filterTree.getViewer().addDoubleClickListener(this) ;
        filterTree.getViewer().addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer arg0, Object arg1, Object element) {
                if(element instanceof Category){
                    return ((ITreeContentProvider)filterTree.getViewer().getContentProvider()).getChildren(element).length > 0 ;
                }
                return true;
            }
        }) ;
        filterTree.getViewer().setInput(new Object());
        filterTree.setFocus();
        // Workaround for FilteredTree first expand
        // See Eclipse bug 299528

        context = new EMFDataBindingContext() ;

        IValidator selectionValidator = new IValidator() {
            @Override
            public IStatus validate(Object value) {
                if(value == null || value instanceof Category){
                    return ValidationStatus.error(Messages.selectAFilterDefWarning);
                }
                return Status.OK_STATUS;
            }
        } ;

        UpdateValueStrategy idStrategy = new UpdateValueStrategy() ;
        idStrategy.setBeforeSetValidator(selectionValidator) ;
        idStrategy.setConverter(new Converter(ConnectorDefinition.class,String.class) {

            @Override
            public Object convert(Object from) {
                if(from instanceof ConnectorDefinition){
                    return ((ConnectorDefinition) from).getId() ;
                }
                return null;
            }
        }) ;

        UpdateValueStrategy versionStrategy = new UpdateValueStrategy() ;
        versionStrategy.setBeforeSetValidator(selectionValidator) ;
        versionStrategy.setConverter(new Converter(ConnectorDefinition.class,String.class) {

            @Override
            public Object convert(Object from) {
                if(from instanceof ConnectorDefinition){
                    return ((ConnectorDefinition) from).getVersion() ;
                }
                return null;
            }
        }) ;

        context.bindValue(ViewersObservables.observeSingleSelection(filterTree.getViewer()), EMFObservables.observeValue(connectorWorkingCopy, ProcessPackage.Literals.CONNECTOR__DEFINITION_ID),idStrategy,null)  ;
        context.bindValue(ViewersObservables.observeSingleSelection(filterTree.getViewer()), EMFObservables.observeValue(connectorWorkingCopy, ProcessPackage.Literals.CONNECTOR__DEFINITION_VERSION),versionStrategy,null)  ;
        context.bindValue(ViewersObservables.observeSingleSelection(filterTree.getViewer()), EMFObservables.observeValue(connectorWorkingCopy.getConfiguration(), ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__DEFINITION_ID),idStrategy,null)  ;
        context.bindValue(ViewersObservables.observeSingleSelection(filterTree.getViewer()), EMFObservables.observeValue(connectorWorkingCopy.getConfiguration(), ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__VERSION),versionStrategy,null)  ;

        pageSupport = WizardPageSupport.create(this, context) ;
        setControl(composite);
    }


    @Override
    protected IStructuredContentProvider getContentProvider() {
        return new FilterDefinitionContentProvider();
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

    @Override
    protected void refresh(){
        if(filterTree != null && !filterTree.isDisposed()){
            filterTree.getViewer().setContentProvider(getContentProvider()) ;
            filterTree.getViewer().setInput(new Object()) ;
        }
    }

    @Override
    public boolean canFlipToNextPage() {
        Object element = ((IStructuredSelection) filterTree.getViewer().getSelection()).getFirstElement();
		return element instanceof ConnectorDefinition && ! (element instanceof UnloadableConnectorDefinition);
    }


    @Override
    public ConnectorDefinition getSelectedDefinition() {
        return (ConnectorDefinition) ((IStructuredSelection) filterTree.getViewer().getSelection()).getFirstElement();
    }


    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        //Intend to be override
    }


    @Override
    public void doubleClick(DoubleClickEvent event) {
        Object selection =  ((IStructuredSelection) event.getSelection()).getFirstElement() ;
        if(selection instanceof Category){
            filterTree.getViewer().expandToLevel(selection, 1) ;
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
