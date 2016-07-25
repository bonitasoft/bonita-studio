/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.PageFlowTransition;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.RecapFlow;

public class PageflowTransitionBuilder implements Buildable<PageFlowTransition> {

    public static PageflowTransitionBuilder aPageflowTransition() {
        return new PageflowTransitionBuilder(ProcessFactory.eINSTANCE.createPageFlowTransition());
    }

    private final PageFlowTransition pageflowTransition;

    private PageflowTransitionBuilder(final PageFlowTransition pageflowTransition) {
        this.pageflowTransition = pageflowTransition;
    }

    public PageflowTransitionBuilder havingCondition(final ExpressionBuilder expressionBuilder) {
        pageflowTransition.setCondition(expressionBuilder.build());
        return this;
    }

    public PageflowTransitionBuilder in(final Buildable<? extends PageFlow> pageflowBuildable) {
        pageflowBuildable.build().getPageFlowTransitions().add(pageflowTransition);
        return this;
    }

    public PageflowTransitionBuilder inOverview(final Buildable<? extends RecapFlow> pageflowBuildable) {
        pageflowBuildable.build().getRecapPageFlowTransitions().add(pageflowTransition);
        return this;
    }

    @Override
    public PageFlowTransition build() {
        return pageflowTransition;
    }

}
