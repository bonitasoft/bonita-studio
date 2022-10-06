/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.eclipse.emf.common.util.EList;

/**
 * @author Aurelien Pupier
 * This class provides the ability to customize expressions when clicking Add Button on an ExpressionCollectionViewer when there is a Table.
 * Fir instance it can be used to fixed the return type
 */
public class LineTableCreator {
		
	/**
	 * @param size, the size of the list expression to be created.
	 * @return a ListExpression of size @size
	 */
	public ListExpression createListExpressionForNewLineInTable(int size) {
		ListExpression rowExp = ExpressionFactory.eINSTANCE.createListExpression();
		EList<Expression> expressions = rowExp.getExpressions();
		for (int i = 0; i < size; i++) {
			Expression cellExpression = ExpressionFactory.eINSTANCE.createExpression();
			expressions.add(cellExpression);
		}
		return rowExp;
	}
}