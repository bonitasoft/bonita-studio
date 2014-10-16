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
package org.bonitasoft.studio.contract.core.constraint;

import java.util.Map;

import org.bonitasoft.studio.contract.core.exception.ConstraintEvaluationException;
import org.mvel2.MVEL;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintEvaluator {

    public boolean run(final String expression, final Map<String, Object> context) throws ConstraintEvaluationException {
        final Object result = MVEL.eval(expression, context);
        if (result != null) {
            if (result instanceof Boolean) {
                return ((Boolean) result).booleanValue();
            }
            throw new ConstraintEvaluationException("Unexpected evaluation returned value. Must be a boolean but was a :" + result.getClass().getName());
        }
        throw new ConstraintEvaluationException("Unexpected evaluation returned value. Constraint expression can't return null.");
    }
}
