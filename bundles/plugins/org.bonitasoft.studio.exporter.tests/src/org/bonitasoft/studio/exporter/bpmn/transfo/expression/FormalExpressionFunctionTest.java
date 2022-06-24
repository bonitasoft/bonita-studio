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
package org.bonitasoft.studio.exporter.bpmn.transfo.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;

import javax.xml.namespace.QName;

import org.bonitasoft.studio.exporter.bpmn.transfo.BPMNConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.spec.bpmn.model.TFormalExpression;

/**
 * @author Romain Bioteau
 */
public class FormalExpressionFunctionTest {

    private FormalExpressionFunction formalExpressionTransformer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        formalExpressionTransformer = new FormalExpressionFunction();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void should_transform_throw_a_NullPointerException() throws Exception {
        formalExpressionTransformer.apply(null);
    }

    @Test
    public void should_transform_a_expression_into_a_TFormalExpression() throws Exception {
        final TFormalExpression formalExpression = formalExpressionTransformer.apply(
                anExpression()
                        .withContent("some content")
                        .withReturnType(String.class.getName())
                        .build());

        assertThat(formalExpression).isNotNull();
        assertThat(formalExpression.getId()).isNotEmpty();
        assertThat(formalExpression.getEvaluatesToTypeRef()).isEqualTo(QName.valueOf("java:java.lang.String"));
        assertThat(formalExpression.getLanguage()).isEqualTo(BPMNConstants.XPATH_LANGUAGE_NS);
        assertThat(formalExpression.getMixed().getValue(0)).isEqualTo("some content");
    }

}
