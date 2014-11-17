/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.export.expression.converter.comparison;

import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.studio.condition.conditionModel.Expression_Boolean;
import org.bonitasoft.studio.condition.conditionModel.Expression_Double;
import org.bonitasoft.studio.condition.conditionModel.Expression_Integer;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Expression_String;
import org.bonitasoft.studio.condition.conditionModel.util.ConditionModelSwitch;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class ExpressionConditionModelSwitch extends ConditionModelSwitch<Expression> {

	private final org.bonitasoft.studio.model.expression.Expression studioExpression;

	public ExpressionConditionModelSwitch(final org.bonitasoft.studio.model.expression.Expression studioExpression){
		this.studioExpression = studioExpression;
	}

	@Override
	public Expression caseExpression_Boolean(final Expression_Boolean object) {
		return EngineExpressionUtil.createConstantExpression(String.valueOf(object.isValue()),String.valueOf(object.isValue()),Boolean.class.getName());
	}

	@Override
	public Expression caseExpression_Double(final Expression_Double object) {
		return EngineExpressionUtil.createConstantExpression(String.valueOf(object.getValue()),String.valueOf(object.getValue()),Double.class.getName());
	}

	@Override
	public Expression caseExpression_Integer(final Expression_Integer object) {
		return EngineExpressionUtil.createConstantExpression(String.valueOf(object.getValue()),String.valueOf(object.getValue()),Long.class.getName());
	}

	@Override
	public Expression caseExpression_String(final Expression_String object) {
		final String value = object.getValue();
		if (value==null || value.isEmpty()){
			return EngineExpressionUtil.createConstantExpression("<empty-string>","",String.class.getName());
		}
		return EngineExpressionUtil.createConstantExpression(value,value,String.class.getName());
	}

	@Override
	public Expression caseExpression_ProcessRef(final Expression_ProcessRef object) {
        final EObject resolvedProxy = object.getValue();
		for(final EObject dep : studioExpression.getReferencedElements()){
			if(dep instanceof Data && resolvedProxy instanceof Data){
				if(((Data) dep).getName().equals(((Data) resolvedProxy).getName())){
					return EngineExpressionUtil.createVariableExpression((Data) dep);
				}
			}else if(dep instanceof Parameter && resolvedProxy instanceof Parameter){
				if(((Parameter) dep).getName().equals(((Parameter) resolvedProxy).getName())){
					return EngineExpressionUtil.createParameterExpression((Parameter) dep);
				}
			}
		}
		return null;
	}


}
