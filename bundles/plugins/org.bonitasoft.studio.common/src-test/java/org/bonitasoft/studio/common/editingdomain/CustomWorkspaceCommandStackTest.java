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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.bonitasoft.studio.common.editingdomain.transaction.AlwaysValidEMFOperationTransaction;
import org.bonitasoft.studio.common.editingdomain.transaction.AlwaysValidTriggerCommandTransaction;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.impl.EMFCommandTransaction;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TriggerCommand;
import org.junit.Test;

public class CustomWorkspaceCommandStackTest {

    @Test
    public void should_create_always_valid_emf_transaction_if_command_is_not_a_command_trigger() throws Exception {
        final CustomWorkspaceCommandStack commandStack = new CustomWorkspaceCommandStack(new DefaultOperationHistory());
        commandStack.setEditingDomain(new TransactionalEditingDomainImpl(new ProcessAdapterFactory()));

        final EMFCommandTransaction transaction = commandStack.createTransaction(null, Collections.emptyMap());

        assertThat(transaction).isInstanceOf(AlwaysValidEMFOperationTransaction.class);
    }

    @Test
    public void should_create_always_valid_trigger_command_transaction_if_command_a_command_trigger() throws Exception {
        final CustomWorkspaceCommandStack commandStack = new CustomWorkspaceCommandStack(new DefaultOperationHistory());
        commandStack.setEditingDomain(new TransactionalEditingDomainImpl(new ProcessAdapterFactory()));

        final EMFCommandTransaction transaction = commandStack.createTransaction(new TriggerCommand(Collections.<Command> emptyList()), Collections.emptyMap());

        assertThat(transaction).isInstanceOf(AlwaysValidTriggerCommandTransaction.class);
    }
}
