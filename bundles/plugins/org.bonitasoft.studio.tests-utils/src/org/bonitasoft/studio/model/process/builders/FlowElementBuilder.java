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
import org.bonitasoft.studio.model.process.FlowElement;

/**
 * @author Romain Bioteau
 *
 */
public abstract class FlowElementBuilder<T extends FlowElement, B extends FlowElementBuilder<T, B>> extends ElementBuilder<T, B> {

    public B havingStepSummary(final ExpressionBuilder stepSummaryExpression) {
        getBuiltInstance().setStepSummary(stepSummaryExpression.build());
        return getThis();
    }

    public B havingDynamicLabel(final ExpressionBuilder dynamicLabelExpression) {
        getBuiltInstance().setDynamicLabel(dynamicLabelExpression.build());
        return getThis();
    }

    public B havingDynamicDescription(final ExpressionBuilder dynamicDescriptionExpression) {
        getBuiltInstance().setDynamicDescription(dynamicDescriptionExpression.build());
        return getThis();
    }

    public B withEstimatedTime(final double estimatedTime) {
        getBuiltInstance().setEstimatedTime(estimatedTime);
        return getThis();
    }


}
