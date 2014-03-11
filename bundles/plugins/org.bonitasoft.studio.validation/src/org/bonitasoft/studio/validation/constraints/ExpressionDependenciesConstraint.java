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
package org.bonitasoft.studio.validation.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 *
 */
public class ExpressionDependenciesConstraint  extends AbstractLiveValidationMarkerConstraint {

	private static final String CONSTRAINT_ID = "org.bonitasoft.studio.validation.constraint.expressionDependencies";


	@Override
	protected IStatus performLiveValidation(IValidationContext context) {
		return null;
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext context) {
		final EObject eObj = context.getTarget();
		if (eObj instanceof Expression 
				&& ((Expression)eObj).isAutomaticDependencies()
				&& !ModelHelper.isAnExpressionCopy((Expression) eObj)
				&& !ExpressionConstants.CONSTANT_TYPE.equals(((Expression) eObj).getType()))  {
			return evaluateExpression(context, eObj);
		}
		return context.createSuccessStatus();
	}

	private IStatus evaluateExpression(IValidationContext context,final EObject eObj) {
		final Expression expression = (Expression) eObj;
		final String type = expression.getType();
		if(type.equals(ExpressionConstants.SCRIPT_TYPE)){

		}else if(type.equals(ExpressionConstants.PATTERN_TYPE)){
			String content = expression.getContent();
			Pattern pattern = Pattern.compile("(.*)\\${(\\w+)}(.*)");
			Matcher matcher = pattern.matcher(content) ;  
			while (matcher.find()) {  
				String depName = matcher.group(); 
				boolean found = false;
				for(EObject dep : expression.getReferencedElements()){
					if(dep instanceof Data){
						String name = ((Data) dep).getName();
						if(depName.equals(name)){
							found = true;
							break;
						}
					}
					if(dep instanceof Parameter){
						String name = ((Parameter) dep).getName();
						if(depName.equals(name)){
							found = true;
							break;
						}
					}
					if(dep instanceof Widget){
						String name = WidgetHelper.FIELD_PREFIX+((Widget) dep).getName();
						if(depName.equals(name)){
							found = true;
							break;
						}
					}
				}
				if(!found){
					return context.createFailureStatus(Messages.bind(Messages.unresolvedPatternDependenciesFor,depName, expression.getName()));
				}
			}

		}else if(type.equals(ExpressionConstants.CONDITION_TYPE)){

		}else{
			if(expression.getReferencedElements().isEmpty()){
				return context.createFailureStatus(Messages.bind(Messages.unresolvedDependenciesFor, expression.getName(),new ExpressionTypeLabelProvider().getText(type)));
			}
		}
		return context.createSuccessStatus();
	}

	@Override
	protected String getConstraintId() {
		return CONSTRAINT_ID;
	}

}
