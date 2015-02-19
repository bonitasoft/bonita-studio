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
package org.bonitasoft.studio.exporter.bpmn.transfo.expression;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.exporter.bpmn.transfo.data.DataScope;
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
public class FormalExpressionTransformerFactoryTest {

    private FormalExpressionTransformerFactory formalExpressionTransformerFactory;
    @Mock
    private DataScope dataScope;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        formalExpressionTransformerFactory = new FormalExpressionTransformerFactory();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_create_FormalExpressionTransformer() throws Exception {
        assertThat(formalExpressionTransformerFactory.newFormalExpressionTransformer(null, ExpressionConstants.PARAMETER_TYPE)).hasSameClassAs(
                new FormalExpressionTransformer());
    }

    @Test
    public void should_create_ScriptFormalExpressionTransformer() throws Exception {
        assertThat(formalExpressionTransformerFactory.newFormalExpressionTransformer(null, ExpressionConstants.SCRIPT_TYPE)).hasSameClassAs(
                new ScriptFormalExpressionTransformer());
    }

    @Test
    public void should_create_VariableFormalExpressionTransformer() throws Exception {
        assertThat(formalExpressionTransformerFactory.newFormalExpressionTransformer(dataScope, ExpressionConstants.VARIABLE_TYPE)).hasSameClassAs(
                new VariableFormalExpressionTransformer(dataScope));
    }
}
