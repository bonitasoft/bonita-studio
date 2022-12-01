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

package org.bonitasoft.studio.connectors.ui.wizard.command;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * @author Romain Bioteau
 */
public class ChangeConnectorContainerCommand extends AbstractTransactionalCommand {

    private final Collection<Connector> connectorsToMove;
    private final ConnectableElement target;
    private final boolean copy;
    private final String connectorEvent;

    public ChangeConnectorContainerCommand(final TransactionalEditingDomain editingDomain, final Collection<Connector> connectorsToMove,
            final ConnectableElement target,
            final String connectorEvent,
            final boolean copy) {
        super(editingDomain, ChangeConnectorContainerCommand.class.getName(), getWorkspaceFiles(connectorsToMove.iterator().next()));
        this.connectorsToMove = connectorsToMove;
        this.target = target;
        this.copy = copy;
        this.connectorEvent = connectorEvent;
    }

    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor,
            final IAdaptable info) throws ExecutionException {
        return copy ? doCopyConnectors() : doMoveConnectors();
    }

    private CommandResult doMoveConnectors() {
        for (final Connector connector : connectorsToMove) {
            connectorContainer(connector).getConnectors().remove(connector);
            connector.setEvent(connectorEvent);
            target.getConnectors().add(connector);
        }
        return CommandResult.newOKCommandResult();
    }

    private ConnectableElement connectorContainer(final Connector connector) {
        return ModelHelper.getFirstContainerOfType(connector, ConnectableElement.class);
    }

    private CommandResult doCopyConnectors() {
        for (final Connector connector : connectorsToMove) {
            final Set<String> existingNames = new HashSet<String>();
            for (final Connector c : target.getConnectors()) {
                existingNames.add(c.getName());
            }
            final Connector connectorCopy = EcoreUtil.copy(connector);
            final String name = NamingUtils.generateNewName(existingNames, connectorCopy.getName(), 1);
            connectorCopy.setName(name);
            connectorCopy.setEvent(connectorEvent);
            target.getConnectors().add(connectorCopy);
        }
        return CommandResult.newOKCommandResult();
    }

}
