/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.data.ui.wizard.provider;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class BooleanExpressionNatureProvider implements IExpressionNatureProvider {

    @Override
    public Expression[] getExpressions(final EObject context) {
        final List<Expression> result = new ArrayList<Expression>();

        Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setName("true");
        exp.setContent("true");
        exp.setReturnType(Boolean.class.getName());
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        result.add(exp);

        exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setName("false");
        exp.setContent("false");
        exp.setReturnType(Boolean.class.getName());
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        result.add(exp);

        return result.toArray(new Expression[2]);
    }


}
