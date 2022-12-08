/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools.convert;

import java.util.Optional;

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.TargetElement;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;

public final class RemoveBoundaryWithItsFlows extends AbstractEMFOperation {

    private final BoundaryEvent boundaryToRemove;
    private final Activity boundaryHolder;

    public RemoveBoundaryWithItsFlows(final TransactionalEditingDomain domain, final BoundaryEvent boundaryToRemove,
            final Activity boundaryHolder) {
        super(domain, "Remove boundary child");
        this.boundaryToRemove = boundaryToRemove;
        this.boundaryHolder = boundaryHolder;
    }

    @Override
    protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        /*
         * Remove the BoundaryEvent from the list and
         * its incoming and outgoing transition
         */
        boundaryHolder.getBoundaryIntermediateEvents().remove(boundaryToRemove);
        cleanExceptionFlow();
        cleanMessageFlow();

        return new Status(IStatus.OK, "org.bonitasoft.studio.diagram.common", "Remove boundary child succeeded");
    }

    private void cleanMessageFlow() {
        if (boundaryToRemove instanceof BoundaryMessageEvent) {
            Optional.ofNullable(((BoundaryMessageEvent) boundaryToRemove).getIncomingMessag()).ifPresent(messageFlow -> {
                EcoreUtil.remove(messageFlow);
                messageFlow.getSource().getOutgoingMessages().remove(messageFlow);
            });
        }
    }

    protected void cleanExceptionFlow() {
        final EList<Connection> exceptionFlowsToDelete = boundaryToRemove.getOutgoing();
        for (final Connection exceptionFlowToDelete : exceptionFlowsToDelete) {
            EcoreUtil.remove(exceptionFlowToDelete);
            final TargetElement target = exceptionFlowToDelete.getTarget();
            if (target != null) {
                target.getIncoming().remove(exceptionFlowToDelete);
            }
        }
        exceptionFlowsToDelete.clear();
    }
}
