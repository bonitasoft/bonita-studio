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
package org.bonitasoft.studio.connectors.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.dialog.ITestConfigurationListener;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connectors.configuration.SelectConnectorImplementationWizard;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.operation.TestConnectorOperation;
import org.bonitasoft.studio.dependencies.ui.dialog.ManageConnectorJarDialog;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;


/**
 * @author Romain Bioteau
 *
 */
public class TestConfigurationListener implements ITestConfigurationListener {

    private final ConnectorConfiguration configuration;
    private final WizardDialog dialog;
    private final IImplementationRepositoryStore implStore;

    public TestConfigurationListener(ConnectorConfiguration configuration,WizardDialog dialog,IImplementationRepositoryStore implStore) {
        this.configuration = configuration;
        this.dialog = dialog;
        this.implStore = implStore;
    }



    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public void handleEvent(Event event) {
        final String defId = configuration.getDefinitionId() ;
        final String defVersion = configuration.getVersion() ;
        final List<ConnectorImplementation> implementations =  implStore.getImplementations(defId,defVersion);

    	ManageConnectorJarDialog jd = new ManageConnectorJarDialog(Display.getDefault().getActiveShell()) ;
		int retCode =jd.open();
		
		ConnectorImplementation impl = null ;
        if(implementations.isEmpty()){
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.noImplementationFoundTitle,Messages.bind(Messages.noImplementationFoundMsg,defId+"-"+defVersion)) ;
                }
            }) ;
            return ;
        }else if(implementations.size() == 1){
            impl = implementations.get(0);
        }else{
            impl = openImplementationSelection(defId, defVersion) ;
            if(impl == null){
                return ;
            }
        }

        if(retCode == Window.OK){
        TestConnectorOperation operation = new TestConnectorOperation() ;
        operation.setImplementation(impl) ;
        operation.setConnectorConfiguration(configuration) ;
        Object result = null ;
        try {
            dialog.run(true, false, operation) ;
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
        }
    }

    protected ConnectorImplementation openImplementationSelection(String defId,String defVersion) {
        SelectConnectorImplementationWizard wizard = new SelectConnectorImplementationWizard(defId,defVersion) ;
        WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),wizard ) ;
        if(dialog.open() == Dialog.OK){
            return  wizard.getConnectorImplementation() ;
        }
        return null;
    }



}
