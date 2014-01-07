/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

package org.bonitasoft.studio.connectors.ui.wizard;

import java.util.ArrayList;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connectors.ui.wizard.command.ChangeConnectorContainerCommand;
import org.bonitasoft.studio.connectors.ui.wizard.page.ConnectorContainerSwitchWizardPage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorContainerSwitchWizard extends Wizard implements IWizard {

    private final AbstractProcess sourceProcess;
    private ConnectorContainerSwitchWizardPage page ;
    private final TransactionalEditingDomain editingDomain;

    public ConnectorContainerSwitchWizard(TransactionalEditingDomain editingDomain,AbstractProcess parentProcess) {
        this.sourceProcess = parentProcess ;
        this.editingDomain = editingDomain ;
        page = new ConnectorContainerSwitchWizardPage(sourceProcess);
    }

    public ConnectorContainerSwitchWizard(TransactionalEditingDomain editingDomain, AbstractProcess parentProcess, Connector selectedConnector) {
        this.sourceProcess = parentProcess ;
        this.editingDomain = editingDomain ;
        page = new ConnectorContainerSwitchWizardPage(sourceProcess, selectedConnector);
	}

	@Override
    public void addPages() {
        addPage(page);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        Connector selectedConnector = page.getSelectedConnector();
        ConnectableElement selectedConnectableElement = page.getSelectedConnectableElement();
        boolean copy = page.getCopy();
        if(selectedConnector != null && selectedConnectableElement != null){
            try {
                OperationHistoryFactory.getOperationHistory().execute(new ChangeConnectorContainerCommand(editingDomain,selectedConnector,selectedConnectableElement,copy),null,null);
            } catch (ExecutionException e) {
                BonitaStudioLog.error(e);
            }
        }else{
            return false ;
        }

        return true;
    }

}
