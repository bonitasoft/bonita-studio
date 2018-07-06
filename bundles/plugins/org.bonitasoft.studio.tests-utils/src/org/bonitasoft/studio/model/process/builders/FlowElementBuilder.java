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
package org.bonitasoft.studio.model.process.builders;

import static org.bonitasoft.studio.model.process.builders.SequenceFlowBuilder.aSequenceFlow;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.FlowElement;

/**
 * @author Romain Bioteau
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

    public B havingOutgoingConnections(final Connection... connections) {
        if (connections != null) {
            for (final Connection connection : connections) {
                getBuiltInstance().getOutgoing().add(connection);
            }
        }
        return getThis();
    }

    public B havingOutgoingConnections(final ConnectionBuilder<?, ?>... connectionBuilders) {
        if (connectionBuilders != null) {
            for (final ConnectionBuilder<?, ?> connectionBuilder : connectionBuilders) {
                getBuiltInstance().getOutgoing().add(connectionBuilder.build());
            }
        }
        return getThis();
    }

    public B in(final Buildable<? extends Container> containerBuildable) {
        containerBuildable.build().getElements().add(getBuiltInstance());
        return getThis();
    }

    public B in(final Container container) {
        container.getElements().add(getBuiltInstance());
        return getThis();
    }

    public FlowElementBuilder<?, ?> goingTo(final FlowElementBuilder<?, ?>... flowElementBuilders) {
        if (flowElementBuilders != null) {
            for (final FlowElementBuilder<?, ?> flowElementBuilder : flowElementBuilders) {
                havingOutgoingConnections(aSequenceFlow().havingSource(getBuiltInstance()).havingTarget(flowElementBuilder.build()));
            }
        }

        return getThis();
    }

    public FlowElementBuilder<?, ?> goingTo(final FlowElement... flowElements) {
        if (flowElements != null) {
            for (final FlowElement flowElement : flowElements) {
                havingOutgoingConnections(aSequenceFlow().havingSource(getBuiltInstance()).havingTarget(flowElement));
            }
        }

        return getThis();
    }

}
