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
package org.bonitasoft.studio.engine.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.HttpURLConnection;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UndeployProcessOperationTest {

    @Mock
    private ProcessAPI processAPI;
    @Mock
    private HttpURLConnection connection;

    @Test
    public void should_disable_process_definition_before_deleting_instances_and_definition() throws Exception {
        final UndeployProcessOperation operation = createFixture();

        when(processAPI.getProcessDefinitionId("TestProcess", "1.0")).thenReturn(1L);
        operation.undeployProcess(aPool().withName("TestProcess").withVersion("1.0").build(), new NullProgressMonitor());

        final InOrder inOrder = inOrder(processAPI);
        inOrder.verify(processAPI).disableProcess(1L);
        inOrder.verify(processAPI).deleteProcessInstances(1L, 0, 1000);
        inOrder.verify(processAPI).deleteArchivedProcessInstances(1L, 0, 1000);
        inOrder.verify(processAPI, never()).deleteProcessDefinition(1L);

        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(operation, times(3)).openConnection(captor.capture());
        assertThat(captor.getAllValues())
                .contains("http://localhost:8080/bonita/bonita/API/system/session/1","http://localhost:8080/bonita/bonita/API/bpm/process/1", "http://localhost:8080/bonita/bonita/logoutservice");
    }

    private UndeployProcessOperation createFixture() throws Exception {
        final BOSEngineManager engineManager = mock(BOSEngineManager.class);
        when(engineManager.createSession(notNull(), any(), any(IProgressMonitor.class))).thenReturn(mock(APISession.class));
        when(engineManager.getProcessAPI(notNull())).thenReturn(processAPI);
        when(processAPI.getNumberOfProcessDeploymentInfos()).thenReturn(1L);
        final UndeployProcessOperation operation = spy(new UndeployProcessOperation(engineManager));
        doReturn(connection).when(operation).openLoginConnection(any(IProgressMonitor.class));
        doReturn(connection).when(operation).openConnection(anyString());
        doReturn(HttpURLConnection.HTTP_OK).when(connection).getResponseCode();
        doReturn("http://localhost:8080/bonita").when(operation).getUrlBase();
        return operation;
    }
}
