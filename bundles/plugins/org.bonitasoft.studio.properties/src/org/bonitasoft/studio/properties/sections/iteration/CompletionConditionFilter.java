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

import static com.google.common.collect.Iterables.tryFind;
import static org.bonitasoft.studio.common.predicate.DataPredicates.withName;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Activity;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;

public class CompletionConditionFilter extends AvailableExpressionTypeFilter{

	private ISelectionProvider selectionProvider;

	public CompletionConditionFilter(ISelectionProvider selectionProvider) {
		super(new String[] { ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
						ExpressionConstants.PARAMETER_TYPE, ExpressionConstants.SCRIPT_TYPE });
		this.selectionProvider = selectionProvider;
	}
	
	@Override
	protected boolean isExpressionAllowed(Object element) {
		if (super.isExpressionAllowed(element)) {
			if (element instanceof Expression
					&& ExpressionConstants.VARIABLE_TYPE.equals(((Expression) element).getType())) {
				IStructuredSelection selection = (IStructuredSelection) selectionProvider.getSelection();
				Object task = selection.getFirstElement();
				if (task instanceof Activity) {
					if (tryFind(((Activity) task).getData(), withName(((Expression) element).getName()))
							.isPresent()) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
}
