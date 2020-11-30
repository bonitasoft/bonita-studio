/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.provider;

import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.junit.Test;

public class ExpressionEditionAdapterTest {
    
    @Test
    public void shouldAdaptStringConstantExpressionIntoGroovyScript() throws Exception {
        String content = "Some constant value";
        Expression expression = ExpressionEditionAdapter.adapt(ExpressionBuilder.aConstantExpression()
                .withName(content)
                .withContent(content)
                .withReturnType(String.class.getName())
                .build());
        
        assertThat(expression)
            .hasType(ExpressionConstants.SCRIPT_TYPE)
            .hasReturnType(String.class.getName())
            .hasContent("'Some constant value'")
            .hasName(content);
    }
    
    @Test
    public void shouldAdaptBooleanConstantExpressionIntoGroovyScript() throws Exception {
        String content = "true";
        Expression expression = ExpressionEditionAdapter.adapt(ExpressionBuilder.aConstantExpression()
                .withName(content)
                .withContent(content)
                .withReturnType(Boolean.class.getName())
                .build());
        
        assertThat(expression)
            .hasType(ExpressionConstants.SCRIPT_TYPE)
            .hasReturnType(Boolean.class.getName())
            .hasContent("true")
            .hasName(content);
    }
    
    @Test
    public void shouldAdaptLocalDateConstantExpressionIntoGroovyScript() throws Exception {
        String content = "2013-01-02";
        Expression expression = ExpressionEditionAdapter.adapt(ExpressionBuilder.aConstantExpression()
                .withName(content)
                .withContent(content)
                .withReturnType(LocalDate.class.getName())
                .build());
        
        assertThat(expression)
            .hasType(ExpressionConstants.SCRIPT_TYPE)
            .hasReturnType(LocalDate.class.getName())
            .hasContent("java.time.LocalDate.parse('2013-01-02')")
            .hasName(content);
    }
    
    @Test
    public void shouldAdaptLocalDateTimeConstantExpressionIntoGroovyScript() throws Exception {
        String content = "2013-01-02T12:05:00";
        Expression expression = ExpressionEditionAdapter.adapt(ExpressionBuilder.aConstantExpression()
                .withName(content)
                .withContent(content)
                .withReturnType(LocalDateTime.class.getName())
                .build());
        
        assertThat(expression)
            .hasType(ExpressionConstants.SCRIPT_TYPE)
            .hasReturnType(LocalDateTime.class.getName())
            .hasContent("java.time.LocalDateTime.parse('2013-01-02T12:05:00')")
            .hasName(content);
    }
    
    @Test
    public void shouldAdaptOffsetDateTimeConstantExpressionIntoGroovyScript() throws Exception {
        String content = "2020-11-26T14:42:49+01:00";
        Expression expression = ExpressionEditionAdapter.adapt(ExpressionBuilder.aConstantExpression()
                .withName(content)
                .withContent(content)
                .withReturnType(OffsetDateTime.class.getName())
                .build());
        
        assertThat(expression)
            .hasType(ExpressionConstants.SCRIPT_TYPE)
            .hasReturnType(OffsetDateTime.class.getName())
            .hasContent("java.time.OffsetDateTime.parse('2020-11-26T14:42:49+01:00')")
            .hasName(content);
    }
    
}
