/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.constraints.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyZeroInteractions;

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
 *
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
    public void should_performLiveValidation_return_null() throws Exception {
        assertThat(cmisConnectorDefinitionConstraint.performLiveValidation(context)).isNull();
        verifyZeroInteractions(context);
    }

    @Test
    public void should_performBatchValidation() throws Exception {

    }

    private Connector aValidAtompubConfig() {
        // final ConnectorBuilder
        return null;
    }

    private Connector aValidWerbserviceConfig() {
        return null;
    }

    private Connector anInvalidAtompubConfig() {
        return null;
    }

    private Connector anInvalidWebserviceConfig() {
        return null;
    }

}
