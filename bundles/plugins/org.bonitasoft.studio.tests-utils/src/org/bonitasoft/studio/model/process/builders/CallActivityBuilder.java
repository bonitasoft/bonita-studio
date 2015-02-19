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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 *
 */
public class CallActivityBuilder extends ActivityBuilder<CallActivity, CallActivityBuilder> {

    public static CallActivityBuilder aCallActivity() {
        return new CallActivityBuilder();
    }

    public CallActivityBuilder havingCalledActivityName(final ExpressionBuilder callActivityNameExpression) {
        getBuiltInstance().setCalledActivityName(callActivityNameExpression.build());
        return this;
    }

    public CallActivityBuilder havingCalledActivityVersion(final ExpressionBuilder callActivityVersionExpression) {
        getBuiltInstance().setCalledActivityVersion(callActivityVersionExpression.build());
        return this;
    }

    @Override
    protected CallActivity newInstance() {
        return ProcessFactory.eINSTANCE.createCallActivity();
    }

}
