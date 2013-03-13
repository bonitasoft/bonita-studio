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

import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.IConnectorDefinitionContainer;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.SelectConnectorConfigurationWizard;
import org.bonitasoft.studio.connector.model.definition.wizard.SelectNameAndDescWizardPage;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractDefinitionWizardDialog extends WizardDialog {

    private ToolItem loadItem;
    private ToolItem saveItem;
    private ToolItem testItem;
    private ToolBar toolbar;
    private SaveConfigurationListener saveConfigurationListener;
    private final IRepositoryStore<? extends IRepositoryFileStore> configurationStore;
    private Listener testConfigurationListener;
    private final IImplementationRepositoryStore implStore;
    private final IDefinitionRepositoryStore definitionRepositoryStore;
	private List<ConnectorDefinition> existingDefinitions;

    public AbstractDefinitionWizardDialog(Shell parentShell, IWizard newWizard,IRepositoryStore<? extends IRepositoryFileStore> configurationStore,IRepositoryStore definitionRepositoryStore, IImplementationRepositoryStore implStore) {
        super(parentShell, newWizard);
        this.configurationStore = configurationStore ;
        this.definitionRepositoryStore = (IDefinitionRepositoryStore) definitionRepositoryStore;
        this.implStore = implStore ;
        this.existingDefinitions = this.definitionRepositoryStore.getDefinitions();
    }

    @Override
    protected Control createButtonBar(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.horizontalSpacing = 0;
        layout.marginLeft = 5 ;
        layout.numColumns ++ ;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        composite.setFont(parent.getFont());

        createToolbar(composite) ;

        Control buttonSection = super.createButtonBar(composite);
        ((GridData) buttonSection.getLayoutData()).grabExcessHorizontalSpace = true;
        return composite;
    }



    protected void createToolbar(Composite parent) {
        toolbar = new ToolBar(parent, SWT.FLAT) ;
        toolbar.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(true, false).create());

        loadItem = new ToolItem(toolbar, SWT.NO_FOCUS | SWT.FLAT) ;
        loadItem.setImage(Pics.getImage("load_conf.png")) ;
        loadItem.setText(Messages.loadConfiguration) ;
        loadItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                IWizardPage page = getCurrentPage() ;
                if(page instanceof AbstractConnectorConfigurationWizardPage){
                    AbstractConnectorConfigurationWizardPage connectorConfPage = (AbstractConnectorConfigurationWizardPage) page ;
                    final ConnectorConfiguration connectorConfigurationToLoad = connectorConfPage.getConfiguration();
					SelectConnectorConfigurationWizard wizard = new SelectConnectorConfigurationWizard(connectorConfigurationToLoad,configurationStore) ;
                    WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard) ;
                    if(dialog.open() == Dialog.OK){
                        IConnectorDefinitionContainer connectorWizard = (IConnectorDefinitionContainer) getWizard() ;
                        ConnectorDefinition def = connectorWizard.getDefinition() ;
                        IWizardPage namePage = getWizard().getPage(SelectNameAndDescWizardPage.class.getName()) ;
                        if(namePage != null){
                        	IWizardPage previousNamePage =  namePage.getPreviousPage() ;
                        	showPage(namePage) ;
                        	namePage.setPreviousPage(previousNamePage) ;
                        	connectorWizard.recreateConnectorConfigurationPages(def) ;
                        } else {
                        	IWizardPage[] wizardPages = getWizard().getPages();
                        	if(wizardPages.length > 1){
                        		IWizardPage firstPage = wizardPages[1];
                        		showPage(firstPage);
                        		connectorWizard.recreateConnectorConfigurationPages(def) ;
                        		//showPage(firstPage.getNextPage());
                        	}
                        }
                        
                        updateButtons() ;
                    }
                }
            }
        }) ;

        saveItem = new ToolItem(toolbar, SWT.NO_FOCUS | SWT.FLAT) ;
        saveItem.setImage(Pics.getImage("save_conf.png")) ;
        saveItem.setText(Messages.saveConfiguration) ;
        ITestConfigurationListener listener = getTestListener(null);
        if(implStore != null && listener != null){
            testItem = new ToolItem(toolbar, SWT.NO_FOCUS | SWT.FLAT) ;
            testItem.setImage(Pics.getImage("test.png")) ;
            testItem.setText(Messages.testConfiguration) ;
            testItem.setEnabled(false);
        }
    }


    @Override
    public void updateButtons() {
        super.updateButtons();
        IWizardPage page = getCurrentPage() ;
        if(page instanceof AbstractConnectorConfigurationWizardPage){
            toolbar.setVisible(true) ;
            AbstractConnectorConfigurationWizardPage connectorConfPage = (AbstractConnectorConfigurationWizardPage) page ;
            if(saveConfigurationListener != null){
                saveItem.removeListener(SWT.Selection, saveConfigurationListener) ;
            }
            saveConfigurationListener = new SaveConfigurationListener(this,connectorConfPage.getConfiguration(),configurationStore) ;
            saveItem.addListener(SWT.Selection,saveConfigurationListener) ;

            if(implStore != null && testItem != null){
                if(testConfigurationListener != null){
                    testItem.removeListener(SWT.Selection, testConfigurationListener) ;
                }
                testConfigurationListener = getTestListener(connectorConfPage.getConfiguration()) ;
                testItem.addListener(SWT.Selection,testConfigurationListener) ;
                testItem.setEnabled(getButton(IDialogConstants.FINISH_ID).isEnabled());
            }
            String defId = connectorConfPage.getConfiguration().getDefinitionId() ;
            String defVersion = connectorConfPage.getConfiguration().getVersion() ;
            boolean confExists = false ;
            for(IRepositoryFileStore file : configurationStore.getChildren()){
                ConnectorConfiguration conf = (ConnectorConfiguration) file.getContent() ;
                if(conf.getDefinitionId().equals(defId) && conf.getVersion().equals(defVersion)){
                    confExists = true ;
                    break ;
                }
            }

            loadItem.setEnabled(confExists) ;

        }else{
            toolbar.setVisible(false) ;
        }
    }

    @Override
    public void showPage(IWizardPage page) {
        super.showPage(page);
        if(page instanceof AbstractConnectorConfigurationWizardPage){
            final AbstractConnectorConfigurationWizardPage connectorConfPage = (AbstractConnectorConfigurationWizardPage) page ;
            final String defId = connectorConfPage.getConfiguration().getDefinitionId();
            final String defVersion = connectorConfPage.getConfiguration().getVersion();
            ConnectorDefinition defintion = definitionRepositoryStore.getDefinition(defId, defVersion, existingDefinitions);
            IRepositoryFileStore def = ((IRepositoryStore<? extends IRepositoryFileStore>) definitionRepositoryStore).getChild(URI.decode(defintion.eResource().getURI().lastSegment()));
            if(def != null){
                final String displayName = def.getDisplayName();
                if(!displayName.equals(getShell().getText())){
                    getShell().setText(displayName);
                }
            }
        }
    }


    protected abstract ITestConfigurationListener getTestListener(ConnectorConfiguration configuration) ;

}
