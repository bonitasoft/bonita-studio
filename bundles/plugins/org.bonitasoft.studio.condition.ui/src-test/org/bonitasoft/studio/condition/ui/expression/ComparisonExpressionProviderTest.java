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
package org.bonitasoft.studio.condition.ui.expression;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.decision.DecisionFactory;
import org.junit.Test;

public class ComparisonExpressionProviderTest {

    @Test
    public void should_be_relevant_for_SequenceFlow() {
        assertThat(new ComparisonExpressionProvider().isRelevantFor(ProcessFactory.eINSTANCE.createSequenceFlow())).isTrue();
    }

    @Test
    public void should_be_relevant_for_DecisionTableLine() {
        assertThat(new ComparisonExpressionProvider().isRelevantFor(DecisionFactory.eINSTANCE.createDecisionTableLine())).isTrue();
    }

    @Test
    public void should_not_be_relevant_for_anything_else() {
        assertThat(new ComparisonExpressionProvider().isRelevantFor(null)).isFalse();
        assertThat(new ComparisonExpressionProvider().isRelevantFor(ProcessFactory.eINSTANCE.createConnection())).isFalse();
    }
}
