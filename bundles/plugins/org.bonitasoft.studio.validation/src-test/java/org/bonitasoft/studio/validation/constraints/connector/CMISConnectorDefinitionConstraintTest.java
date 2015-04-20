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
package org.bonitasoft.studio.validation.constraints.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ConnectorBuilder.aConnector;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorConfigurationBuilder;
import org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorParameterBuilder;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.validation.IValidationContext;
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
public class CMISConnectorDefinitionConstraintTest {

    private CMISConnectorDefinitionConstraint cmisConnectorDefinitionConstraint;
    @Mock
    private IValidationContext context;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        cmisConnectorDefinitionConstraint = new CMISConnectorDefinitionConstraint();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getConstraintId_return_ID_constant() throws Exception {
        assertThat(cmisConnectorDefinitionConstraint.getConstraintId()).isEqualTo(CMISConnectorDefinitionConstraint.ID);
    }

    @Test
    public void should_performLiveValidation_return_null() throws Exception {
        assertThat(cmisConnectorDefinitionConstraint.performLiveValidation(context)).isNull();
        verifyZeroInteractions(context);
    }

    @Test
    public void should_performBatchValidation_return_a_failure_status_for_a_cmis_connector_without_binding_type() throws Exception {
        when(context.getTarget()).thenReturn(anInvalidCMISConfigWithoutBindingType());
        cmisConnectorDefinitionConstraint.performBatchValidation(context);
        verify(context).createFailureStatus(anyString());
    }

    @Test
    public void should_performBatchValidation_return_a_failure_status_for_a_cmis_connector_with_invalid_binding_type() throws Exception {
        when(context.getTarget()).thenReturn(anInvalidCMISConfigWithInvalidBindingType());
        cmisConnectorDefinitionConstraint.performBatchValidation(context);
        verify(context).createFailureStatus(anyString());
    }

    @Test
    public void should_performBatchValidation_return_a_succes_status_for_non_cmis_connector() throws Exception {
        when(context.getTarget()).thenReturn(aNonCMISConnectorConfig());
        cmisConnectorDefinitionConstraint.performBatchValidation(context);
        verify(context).createSuccessStatus();
    }

    @Test
    public void should_performBatchValidation_return_a_failure_status_for_a_cmis_connector_with_invalid_atom_pub_config() throws Exception {
        when(context.getTarget()).thenReturn(aCMISConfigWithUrl("", ""));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        when(context.getTarget()).thenReturn(aCMISConfigWithUrl(null, ""));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        when(context.getTarget()).thenReturn(aCMISConfigWithUrl("atompub", ""));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        when(context.getTarget()).thenReturn(aCMISConfigWithUrl("atompub", null));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        when(context.getTarget()).thenReturn(anAtompubConfigWithoutURL("atompub"));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        verify(context, times(5)).createFailureStatus(anyString());
    }

    @Test
    public void should_performBatchValidation_return_a_failure_status_for_a_cmis_connector_with_invalid_webservice_config() throws Exception {
        when(context.getTarget()).thenReturn(aCMISConfigWithWSUrl("webservices", "", ""));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        verify(context).createFailureStatus(anyString());
    }

    @Test
    public void should_performBatchValidation_return_a_succes_status_for_a_valid_websercie_cmis_connector_config() throws Exception {
        when(context.getTarget()).thenReturn(aCMISConfigWithWSUrl("webservices", "http://some/url", null));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        when(context.getTarget()).thenReturn(aCMISConfigWithWSUrl("webservices", null, "http://some/url"));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        verify(context, times(2)).createSuccessStatus();
    }

    @Test
    public void should_performBatchValidation_return_a_succes_status_for_a_valid_atompub_cmis_connector_config() throws Exception {
        when(context.getTarget()).thenReturn(aCMISConfigWithUrl("atompub", "http://some/url"));
        cmisConnectorDefinitionConstraint.performBatchValidation(context);

        verify(context).createSuccessStatus();
    }

    @Test
    public void should_isAnExpressionWithContent_return_false() throws Exception {
        assertThat(cmisConnectorDefinitionConstraint.isAnExpressionWithContent(ExpressionBuilder.anExpression().build())).isFalse();
        assertThat(cmisConnectorDefinitionConstraint.isAnExpressionWithContent(ExpressionBuilder.anExpression().withContent("").build())).isFalse();
        assertThat(cmisConnectorDefinitionConstraint.isAnExpressionWithContent(null)).isFalse();
    }

    @Test
    public void should_isAnExpressionWithContent_return_true() throws Exception {
        assertThat(cmisConnectorDefinitionConstraint.isAnExpressionWithContent(ExpressionBuilder.anExpression().withContent("content").build())).isTrue();
    }

    private Connector aNonCMISConnectorConfig() {
        return aConnector().withDefinitionId("whatever").withDefinitionVersion("1.0").build();
    }

    private Connector anInvalidCMISConfigWithoutBindingType() {
        return aConnector().
                withDefinitionId("cmis-createfolder").
                withDefinitionVersion("1.0").
                havingConfiguration(ConnectorConfigurationBuilder.aConnectorConfiguration().
                        havingParameters(aConnectorParameter("url", "http://some/url"))).build();
    }

    private Connector anInvalidCMISConfigWithInvalidBindingType() {
        return aConnector().
                withDefinitionId("cmis-createfolder").
                withDefinitionVersion("1.0").
                havingConfiguration(ConnectorConfigurationBuilder.aConnectorConfiguration().
                        havingParameters(aConnectorParameter("binding_type", "anotherType"),
                                aConnectorParameter("url", "http://some/url"))).build();
    }

    private ConnectorParameterBuilder aConnectorParameter(final String key, final String expressionContent) {
        final ConnectorParameterBuilder builder = ConnectorParameterBuilder.aConnectorParameter().withKey(key);
        if (expressionContent != null) {
            builder.havingExpression(ExpressionBuilder.anExpression().withContent(expressionContent));
        }
        return builder;
    }

    private Connector aCMISConfigWithUrl(final String bindingContent, final String urlContent) {
        return aConnector().
                withDefinitionId("cmis-createfolder").
                withDefinitionVersion("1.0").
                havingConfiguration(ConnectorConfigurationBuilder.aConnectorConfiguration().
                        havingParameters(aConnectorParameter("binding_type", bindingContent),
                                aConnectorParameter("url", urlContent))).build();
    }

    private Connector anAtompubConfigWithoutURL(final String bindingContent) {
        return aConnector().
                withDefinitionId("cmis-createfolder").
                withDefinitionVersion("1.0").
                havingConfiguration(ConnectorConfigurationBuilder.aConnectorConfiguration().
                        havingParameters(aConnectorParameter("binding_type", bindingContent))).build();
    }

    private Connector aCMISConfigWithWSUrl(final String bindingContent, final String wsServiceUrl, final String wsServiceEndpointURL) {
        return aConnector().
                withDefinitionId("cmis-createfolder").
                withDefinitionVersion("1.0").
                havingConfiguration(ConnectorConfigurationBuilder.aConnectorConfiguration().
                        havingParameters(aConnectorParameter("binding_type", bindingContent),
                                aConnectorParameter("wsServiceUrl", wsServiceUrl),
                                aConnectorParameter("wsServiceEndpointUrl", wsServiceEndpointURL))).build();
    }

}
