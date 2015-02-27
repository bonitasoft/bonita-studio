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

import static com.google.common.base.Preconditions.checkArgument;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.omg.spec.bpmn.model.TFormalExpression;


/**
 * @author Romain Bioteau
 *
 */
public class ScriptFormalExpressionTransformer extends FormalExpressionTransformer {


    @Override
    public TFormalExpression transform(final Expression bonitaExpression) {
        checkArgument(ExpressionConstants.SCRIPT_TYPE.equals(bonitaExpression.getType()), "Expression type is invalid. Expected %s but was %s",
                ExpressionConstants.SCRIPT_TYPE, bonitaExpression.getType());

        final TFormalExpression formalExpression = super.transform(bonitaExpression);

        if (!ExpressionConstants.GROOVY.equals(bonitaExpression.getInterpreter())) {
            formalExpression.setLanguage(bonitaExpression.getInterpreter());//it is another Interpreter, doesn't exist yet
        }

        return formalExpression;
    }

    @Override
    protected String getFormalExpressionLanguage() {
        //null for default : GROOVY
        return null;
    }

}
