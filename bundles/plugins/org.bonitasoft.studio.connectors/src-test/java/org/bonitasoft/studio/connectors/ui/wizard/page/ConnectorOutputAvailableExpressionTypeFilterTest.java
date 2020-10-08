/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.viewers.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectorOutputAvailableExpressionTypeFilterTest {

    private ConnectorOutputAvailableExpressionTypeFilter connectorOutputAvailableExpressionTypeFilter;

    @Mock
    private Viewer expressionViewer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        connectorOutputAvailableExpressionTypeFilter = new ConnectorOutputAvailableExpressionTypeFilter();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_select_returns_true_for_contract_input_expression_in_on_finish_connector_input()
            throws Exception {
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        final Connector onFinishConnector = ProcessFactory.eINSTANCE.createConnector();
        activity.getConnectors().add(onFinishConnector);
        onFinishConnector.setEvent(ConnectorEvent.ON_FINISH.name());
        when(expressionViewer.getInput()).thenReturn(onFinishConnector);

        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("myInput");
        input.setType(ContractInputType.TEXT);
        assertThat(connectorOutputAvailableExpressionTypeFilter.select(expressionViewer, null, ExpressionHelper.createContractInputExpression(input))).isTrue();
    }

    @Test
    public void should_select_returns_true_for_contract_input_expression_in_pool_on_enter_connector_input()
            throws Exception {
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Connector onEnterConnector = ProcessFactory.eINSTANCE.createConnector();
        onEnterConnector.setEvent(ConnectorEvent.ON_ENTER.name());
        pool.getConnectors().add(onEnterConnector);
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE
                .createConnectorConfiguration();
        onEnterConnector.setConfiguration(config);
        when(expressionViewer.getInput()).thenReturn(config);

        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("myInput");
        input.setType(ContractInputType.TEXT);
        assertThat(connectorOutputAvailableExpressionTypeFilter.select(expressionViewer, null, ExpressionHelper.createContractInputExpression(input))).isTrue();
    }

    @Test
    public void should_select_returns_false_for_contract_input_expression_in_activity_on_enter_connector_input()
            throws Exception {
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        final Connector onEnterConnector = ProcessFactory.eINSTANCE.createConnector();
        onEnterConnector.setEvent(ConnectorEvent.ON_ENTER.name());
        activity.getConnectors().add(onEnterConnector);
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE
                .createConnectorConfiguration();
        onEnterConnector.setConfiguration(config);
        when(expressionViewer.getInput()).thenReturn(config);

        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("myInput");
        input.setType(ContractInputType.TEXT);
        assertThat(connectorOutputAvailableExpressionTypeFilter.select(expressionViewer, null, ExpressionHelper.createContractInputExpression(input)))
                .isFalse();
    }

}
