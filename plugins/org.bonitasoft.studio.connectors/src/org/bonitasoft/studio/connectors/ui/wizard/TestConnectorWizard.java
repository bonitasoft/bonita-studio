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
package org.bonitasoft.studio.connectors.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connectors.configuration.SelectConnectorImplementationWizard;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.operation.TestConnectorOperation;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.ui.TestConnectorResultDialog;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class TestConnectorWizard extends ConnectorWizard {


    public TestConnectorWizard(){
        super((EObject)null, null, null);
        setForcePreviousAndNextButtons(true);
        setWindowTitle(Messages.testConnectorTitle);
    }

    @Override
    protected void initializeContainment() {
        //KEEP IT EMPTY
    }


    @Override
    protected void addOuputPage(ConnectorDefinition definition) {
        //KEEP IT EMPTY
    }

    @Override
    protected void addNameAndDescriptionPage() {
        //KEEP IT EMPTY
    }

    @Override
    protected IWizardPage getOutputPageFor(ConnectorDefinition definition) {
        return null;
    }

    @Override
    protected void clearConnectorConfiguration(ConnectorDefinition definition) {
        ConnectorConfiguration configuration =  connectorWorkingCopy.getConfiguration() ;
        configuration.getParameters().clear() ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final ConnectorConfiguration configuration = connectorWorkingCopy.getConfiguration();
        final String defId = connectorWorkingCopy.getDefinitionId() ;
        final String defVersion = connectorWorkingCopy.getDefinitionVersion() ;
        IImplementationRepositoryStore implStore = getImplementationStore();
        final List<ConnectorImplementation> implementations =  implStore.getImplementations(defId,defVersion);

        ConnectorImplementation impl = null ;
        if(implementations.isEmpty()){
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.noImplementationFoundTitle,Messages.bind(Messages.noImplementationFoundMsg,defId+"-"+defVersion)) ;
                }
            }) ;
            return false;
        }else if(implementations.size() == 1){
            impl = implementations.get(0);
        }else{
            impl = openImplementationSelection(defId, defVersion) ;
            if(impl == null){
                return false;
            }
        }


        TestConnectorOperation operation = new TestConnectorOperation() ;
        operation.setImplementation(impl) ;
        operation.setConnectorConfiguration(configuration) ;
        Object result = null ;
        try {
            getContainer().run(true, false, operation) ;
            result = operation.getResult() ;
        } catch (InvocationTargetException e) {
            result = e ;
            BonitaStudioLog.error(e) ;
        } catch (InterruptedException e) {
            result = e ;
            BonitaStudioLog.error(e) ;
        }


        if(result != null){
            TestConnectorResultDialog dialog = new TestConnectorResultDialog(Display.getDefault().getActiveShell(), result) ;
            dialog.open() ;
        }
        return false; //Keep wizard open on after test operation
    }

    protected ConnectorImplementation openImplementationSelection(String defId, String defVersion) {
        SelectConnectorImplementationWizard wizard = new SelectConnectorImplementationWizard(defId,defVersion) ;
        WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),wizard ) ;
        if(dialog.open() == Dialog.OK){
            return  wizard.getConnectorImplementation() ;
        }
        return null;
    }

    protected IImplementationRepositoryStore getImplementationStore() {
        return (IImplementationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
    }

    @Override
    public Connector getOriginalConnector() {
        return null;
    }

    @Override
    public ConnectorDefinition getDefinition() {
        ConnectorDefRepositoryStore defStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class) ;
        if(connectorWorkingCopy.getDefinitionId() != null && !connectorWorkingCopy.getDefinitionId().isEmpty()){
            return defStore.getDefinition(connectorWorkingCopy.getDefinitionId(), connectorWorkingCopy.getDefinitionVersion()) ;
        }

        return null;
    }


    @Override
    public boolean isEditMode() {
        return false;
    }
}
