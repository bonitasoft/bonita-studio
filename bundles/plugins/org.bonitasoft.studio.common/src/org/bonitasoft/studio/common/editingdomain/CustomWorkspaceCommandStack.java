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
package org.bonitasoft.studio.common.editingdomain;

import java.util.Map;

import org.bonitasoft.studio.common.editingdomain.transaction.AlwaysValidEMFOperationTransaction;
import org.bonitasoft.studio.common.editingdomain.transaction.AlwaysValidTriggerCommandTransaction;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.impl.EMFCommandTransaction;
import org.eclipse.emf.transaction.util.TriggerCommand;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;

public class CustomWorkspaceCommandStack extends WorkspaceCommandStackImpl {

    public CustomWorkspaceCommandStack(final IOperationHistory history) {
        super(history);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl#createTransaction(org.eclipse.emf.common.command.Command, java.util.Map)
     */
    @Override
    public EMFCommandTransaction createTransaction(final Command command, final Map<?, ?> options) throws InterruptedException {
        EMFCommandTransaction result;
        if (command instanceof TriggerCommand) {
            result = new AlwaysValidTriggerCommandTransaction((TriggerCommand) command,
                    getDomain(), options);
        } else {
            result = new AlwaysValidEMFOperationTransaction(command, getDomain(), options);
        }

        result.start();

        return result;
    }
}
