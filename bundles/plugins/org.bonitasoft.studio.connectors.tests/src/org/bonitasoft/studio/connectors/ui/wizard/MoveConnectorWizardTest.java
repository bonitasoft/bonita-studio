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
package org.bonitasoft.studio.connectors.ui.wizard;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ConnectorBuilder.aConnector;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.connectors.ui.wizard.page.MoveConnectorWizardPage;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.junit.Rule;
import org.junit.Test;

public class MoveConnectorWizardTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void should_add_a_MoveConnectorWizardPage() throws Exception {
        final MoveConnectorWizard wizard = new MoveConnectorWizard(mock(IOperationHistory.class), mock(TransactionalEditingDomain.class),
                newArrayList(aConnector().in(aPool()).build()));

        wizard.addPages();

        assertThat(wizard.getPage(MoveConnectorWizardPage.class.getName())).isNotNull();
    }

    @Test
    public void should_execute_ChangeConnectorContainerCommand_on_finish() throws Exception {
        final IOperationHistory operationHistory = mock(IOperationHistory.class);
        final MoveConnectorWizard wizard = new MoveConnectorWizard(operationHistory, mock(InternalTransactionalEditingDomain.class),
                newArrayList(aConnector().in(aPool()).build()));

        wizard.addPages();
        wizard.performFinish();

        verify(operationHistory).execute(notNull(), any(), any());
    }

}
