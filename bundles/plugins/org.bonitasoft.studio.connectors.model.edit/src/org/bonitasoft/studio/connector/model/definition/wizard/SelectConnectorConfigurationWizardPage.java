/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import static org.bonitasoft.studio.common.jface.SWTBotConstants.SELECTION_CONNECTOR_CONFIGURATION_TREE_ID;
import static org.bonitasoft.studio.common.jface.SWTBotConstants.SWTBOT_WIDGET_ID_KEY;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
/**
 * @author Romain Bioteau
 *
 */
public class SelectConnectorConfigurationWizardPage extends WizardSelectionPage {

    protected FilteredTree configurationsTree;
    protected Button removeButton;
    protected final ConnectorConfiguration configuration;
    private ConnectorConfiguration selectedConfiguration ;
    protected EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    protected final IRepositoryStore<? extends IRepositoryFileStore> configurationStore;

    public SelectConnectorConfigurationWizardPage(ConnectorConfiguration configuration, IRepositoryStore<? extends IRepositoryFileStore> configurationStore) {
        super(SelectConnectorConfigurationWizardPage.class.getName());
        setTitle(Messages.selectConfigurationPageName);
        setDescription(Messages.selectConfigurationPageDesc);
        this.configuration = configuration ;
        this.configurationStore = configurationStore ;
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createControl(Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        context = new EMFDataBindingContext() ;

        doCreateControl(composite) ;


        bindTree();

        pageSupport = WizardPageSupport.create(this,context) ;

        setControl(composite);
    }


    protected void bindTree() {
        final IValidator selectionValidator = new IValidator() {
            @Override
            public IStatus validate(Object value) {
                if(value == null || value instanceof ConnectorParameter){
                    return ValidationStatus.error(Messages.selectAConnectorConfDefWarning);
                }
                return Status.OK_STATUS;
            }
        } ;
        final UpdateValueStrategy selectionStrategy = new UpdateValueStrategy() ;
        selectionStrategy.setBeforeSetValidator(selectionValidator) ;
        context.bindValue(ViewersObservables.observeSingleSelection(configurationsTree.getViewer()), PojoProperties.value(SelectConnectorConfigurationWizardPage.class, "selectedConfiguration").observe(this),selectionStrategy,null)  ;
    }

    protected void doCreateControl(Composite composite) {
        configurationsTree = new FilteredTree(composite, SWT.SINGLE | SWT.BORDER, new PatternFilter(), true);
        configurationsTree.getViewer().getTree().setData(SWTBOT_WIDGET_ID_KEY, SELECTION_CONNECTOR_CONFIGURATION_TREE_ID);
        configurationsTree.getViewer().setContentProvider(new ConfigurationsContentProvider(configuration.getDefinitionId(),configuration.getVersion(),configurationStore));
        configurationsTree.getViewer().setLabelProvider(new ConnectorConfiguraitonLabelProvider()) ;
        configurationsTree.getViewer().addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                final ITreeSelection selection = (ITreeSelection) event.getSelection();
                final Object item = selection.getFirstElement();
                if (configurationsTree.getViewer().isExpandable(item)) {
                    final boolean currentState = configurationsTree.getViewer().getExpandedState(item);
                    configurationsTree.getViewer().setExpandedState(item, !currentState);
                } else {
                    if (canFlipToNextPage()) {
                        getContainer().showPage(getNextPage());
                    } else if (getWizard().canFinish()) {
                        if (getWizard().performFinish()) {
                            ((WizardDialog) getContainer()).close();
                        }
                    }
                }
            }
        });

        configurationsTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                updateButton();
            }
        });

        configurationsTree.getViewer().setInput(new Object()) ;

        //Avoid bug with filtered tree on linux
        if(configurationsTree.getViewer().getTree().getItemCount() > 0){
        	selectedConfiguration = (ConnectorConfiguration)configurationsTree.getViewer().getTree().getItem(0).getData();
            configurationsTree.getViewer().setSelection(new StructuredSelection(selectedConfiguration));
        }
        configurationsTree.setFocus();
        createRemoveButton(composite);

    }


    @Override
    public void dispose() {
        super.dispose();
        if(pageSupport != null){
            pageSupport.dispose() ;
        }
        if(context != null){
            context.dispose() ;
        }
    }

    protected void createRemoveButton(Composite composite) {
        removeButton = new Button(composite,SWT.PUSH) ;
        removeButton.setText(Messages.removeData);
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(!configurationsTree.getViewer().getSelection().isEmpty()){
                    final Object selection = ((StructuredSelection)configurationsTree.getViewer().getSelection()).getFirstElement() ;
                    if(selection instanceof ConnectorConfiguration){
                        final Resource r = ((ConnectorConfiguration)selection).eResource() ;
                        final String fileName =  URI.decode(r.getURI().lastSegment()) ;
                        final IRepositoryFileStore artifact = configurationStore.getChild(fileName) ;
                        if(artifact != null){
                            if(FileActionDialog.confirmDeletionQuestion(fileName)){
                                artifact.delete();
                            }
                        }

                        configurationsTree.getViewer().setInput(new Object());
                    }
                }

            }
        });
        removeButton.setEnabled(false);
    }

    protected void updateButton() {
        if(removeButton != null && !removeButton.isDisposed()){
            if(!configurationsTree.getViewer().getSelection().isEmpty()){
                final Object selection = ((StructuredSelection)configurationsTree.getViewer().getSelection()).getFirstElement() ;
                removeButton.setEnabled(selection instanceof ConnectorConfiguration);
            }else{
                removeButton.setEnabled(false);
            }

        }
    }


    public ConnectorConfiguration getSelectedConfiguration() {
        return selectedConfiguration;
    }

    public void setSelectedConfiguration(ConnectorConfiguration selectedConfiguration) {
        this.selectedConfiguration = selectedConfiguration;
    }

}
