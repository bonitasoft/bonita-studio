/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.iteration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Test;

public class CompletionConditionFilterTest {

	@Test
	public void should_not_select_task_data() throws Exception {
		ISelectionProvider selectionProvider = mock(ISelectionProvider.class);
		Task task = aTask().havingData(aData().withName("stepData")).build();
		when(selectionProvider.getSelection()).thenReturn(new StructuredSelection(task));
		
		CompletionConditionFilter completionConditionFilter = new CompletionConditionFilter(selectionProvider);
		
		boolean expressionAllowed = completionConditionFilter.isExpressionAllowed(ExpressionBuilder.aVariableExpression().withName("stepData").build());
		
		assertThat(expressionAllowed).isFalse();
	}
	
	@Test
	public void should_select_data_not_in_task() throws Exception {
		ISelectionProvider selectionProvider = mock(ISelectionProvider.class);
		Task task = aTask().havingData(aData().withName("stepData")).build();
		when(selectionProvider.getSelection()).thenReturn(new StructuredSelection(task));
		
		CompletionConditionFilter completionConditionFilter = new CompletionConditionFilter(selectionProvider);
		
		boolean expressionAllowed = completionConditionFilter.isExpressionAllowed(ExpressionBuilder.aVariableExpression().withName("anotherData").build());
		
		assertThat(expressionAllowed).isTrue();
	}
	
}
