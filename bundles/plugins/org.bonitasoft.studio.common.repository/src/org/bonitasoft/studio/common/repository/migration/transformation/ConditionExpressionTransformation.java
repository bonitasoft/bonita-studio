/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.migration.transformation;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.migration.ProcessModelTransformation;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;

public class ConditionExpressionTransformation implements ProcessModelTransformation {

    @Override
    public void transform(EObject modelObject) {
        Expression expression = (Expression) modelObject;
        if (expression.hasContent()) {
            expression.setType(ExpressionConstants.SCRIPT_TYPE);
            expression.setInterpreter(ExpressionConstants.GROOVY);
        } else {
            expression.setType(ExpressionConstants.CONSTANT_TYPE);
        }
    }

    @Override
    public boolean appliesTo(EObject modelObject) {
        return modelObject instanceof Expression
                && ExpressionConstants.CONDITION_TYPE.equals(((Expression) modelObject).getType());
    }

}
