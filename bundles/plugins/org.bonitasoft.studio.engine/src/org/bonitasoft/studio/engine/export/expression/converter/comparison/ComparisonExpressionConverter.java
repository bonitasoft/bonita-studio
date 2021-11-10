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
package org.bonitasoft.studio.engine.export.expression.converter.comparison;

import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.engine.export.expression.converter.IExpressionConverter;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ComparisonExpressionConverter implements IExpressionConverter {


    @Override
    public boolean appliesTo(final Expression expression) {
        return expression != null && ExpressionConstants.CONDITION_TYPE.equals(expression.getType());
    }

    @Override
    public org.bonitasoft.engine.expression.Expression convert(final Expression expression) throws InvalidExpressionException {
        Expression groovyExpression = EcoreUtil.copy(expression);
        groovyExpression.setName(getExpressionName(expression));
        groovyExpression.setType(ExpressionConstants.SCRIPT_TYPE);
        groovyExpression.setInterpreter(ExpressionConstants.GROOVY);
        return EngineExpressionUtil.createExpression(groovyExpression);
    }

    protected String getExpressionName(final Expression expression) {
        String name = expression.getName();
        if (name == null || name.isEmpty()) {
            name = "<empty-name>";
        }
        return name;
    }

}
