/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.connectors.ui.wizard;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.wizard.command.ChangeConnectorContainerCommand;
import org.bonitasoft.studio.connectors.ui.wizard.page.MoveConnectorWizardPage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 */
public class MoveConnectorWizard extends Wizard implements IWizard {

    private final AbstractProcess sourceProcess;
    private MoveConnectorWizardPage page;
    private final TransactionalEditingDomain editingDomain;
    private final Collection<Connector> connectorsToMove;
    private final WritableValue targetLocation;
    private final IOperationHistory operationHistory;
    private final WritableValue connectorEventObservable;

    public MoveConnectorWizard(final IOperationHistory operationHistory,
            final TransactionalEditingDomain editingDomain,
            final Collection<Connector> connectorsToMove) {
        checkArgument(connectorsToMove != null && !connectorsToMove.isEmpty(), "connectorsToMove cannot be null or empty");
        this.connectorsToMove = connectorsToMove;
        sourceProcess = sourceProcess();
        this.editingDomain = editingDomain;
        this.operationHistory = operationHistory;
        targetLocation = new WritableValue(sourceConnectableElement(), ConnectableElement.class);
        connectorEventObservable = new WritableValue(connectorEvent(), String.class);
    }

    private String connectorEvent() {
        return connectorsToMove.iterator().next().getEvent();
    }

    private ConnectableElement sourceConnectableElement() {
        return ModelHelper.getFirstContainerOfType(connectorsToMove.iterator().next(), ConnectableElement.class);
    }

    private AbstractProcess sourceProcess() {
        return ModelHelper.getParentProcess(connectorsToMove.iterator().next());
    }

    @Override
    public void addPages() {
        page = new MoveConnectorWizardPage(sourceProcess, targetLocation, connectorEventObservable);
        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        if (targetLocation.getValue() != null) {
            try {
                operationHistory.execute(
                        new ChangeConnectorContainerCommand(editingDomain, connectorsToMove, (ConnectableElement) targetLocation.getValue(),
                                (String) connectorEventObservable.getValue(), page.isCopy()),
                        null, null);
            } catch (final ExecutionException e) {
                return handleError(e);
            }
        }
        return true;
    }

    protected boolean handleError(final ExecutionException e) {
        BonitaStudioLog.error(e);
        new BonitaErrorDialog(getShell(), Messages.moveFailed, Messages.moveFailedMsg, e).open();
        return false;
    }

}
