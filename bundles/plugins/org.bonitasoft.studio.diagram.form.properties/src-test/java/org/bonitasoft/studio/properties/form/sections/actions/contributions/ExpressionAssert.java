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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.assertj.core.api.AbstractAssert;
import org.bonitasoft.studio.model.expression.Expression;


/**
 * @author Romain Bioteau
 *
 */
public class ExpressionAssert extends AbstractAssert<ExpressionAssert, Expression> {

    /**
     * @param actual
     * @param selfType
     */
    protected ExpressionAssert(Expression actual) {
        super(actual, ExpressionAssert.class);
    }
    
    // 3 - A fluent entry point to your specific assertion class, use it with static import.
    public static ExpressionAssert assertThat(Expression actual) {
      return new ExpressionAssert(actual);
    }

    // 4 - a specific assertion !
    public ExpressionAssert hasReturnType(String returnType) {
      isNotNull();

      // check condition
      if (!actual.getReturnType().equals(returnType)) {
        failWithMessage("Expected return type to be <%s> but was <%s>", returnType, actual.getReturnType());
      }

      // return the current assertion for method chaining
      return this;
    }

}
