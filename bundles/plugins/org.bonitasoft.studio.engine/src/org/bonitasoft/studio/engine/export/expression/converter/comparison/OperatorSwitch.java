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
package org.bonitasoft.studio.engine.export.expression.converter.comparison;

import org.bonitasoft.studio.condition.conditionModel.Operation_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Greater;
import org.bonitasoft.studio.condition.conditionModel.Operation_Greater_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Less;
import org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Not_Equals;
import org.bonitasoft.studio.condition.conditionModel.util.ConditionModelSwitch;
import org.eclipse.emf.ecore.EObject;


/**
 * @author Romain Bioteau
 *
 */
public class OperatorSwitch extends ConditionModelSwitch<String> {

    @Override
    public String caseOperation_Equals(final Operation_Equals object) {
        return "==";
    }

    @Override
    public String caseOperation_Greater(final Operation_Greater object) {
        return ">";
    }

    @Override
    public String caseOperation_Greater_Equals(final Operation_Greater_Equals object) {
        return ">=";
    }

    @Override
    public String caseOperation_Less(final Operation_Less object) {
        return "<";
    }

    @Override
    public String caseOperation_Less_Equals(final Operation_Less_Equals object) {
        return "<=";
    }

    @Override
    public String caseOperation_Not_Equals(final Operation_Not_Equals object) {
        return "!=";
    }

    @Override
    public String defaultCase(final EObject object) {
        return null;
    }
}
