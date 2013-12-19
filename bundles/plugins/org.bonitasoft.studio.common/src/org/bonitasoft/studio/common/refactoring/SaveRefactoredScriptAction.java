/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.refactoring;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author aurelie zara
 *
 */
public class SaveRefactoredScriptAction {

	
	public static void saveRefactoring(List<Expression> oldExpressions,List<Expression> newExpressions,CompoundCommand cc,EditingDomain domain ){
		for (int i=0; i<oldExpressions.size();i++){
			if (!oldExpressions.get(i).getContent().equals(newExpressions.get(i).getContent())){
				cc.append(SetCommand.create(domain,oldExpressions.get(i) , ExpressionPackage.Literals.EXPRESSION__CONTENT, newExpressions.get(i).getContent()));
				if (ExpressionConstants.CONDITION_TYPE.equals(oldExpressions.get(i).getType())){
					cc.append(SetCommand.create(domain, oldExpressions.get(i),ExpressionPackage.Literals.EXPRESSION__NAME, newExpressions.get(i).getContent()));
				}
			}
		}
	}
}
