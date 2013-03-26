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
package org.bonitasoft.studio.expression.editor.operation;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.Info;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;


/**
 * @author Romain Bioteau
 *
 */
public class OperationReturnTypesValidator implements IValidator {

	private Expression dataExpression;

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
	 */
	@Override
	public IStatus validate(Object expression) {
		if(expression instanceof Expression){
			EObject container = ((Expression) expression).eContainer() ;
			if(container instanceof Operation){
				final Operation operation = (Operation) container;

				EObject parent = operation.eContainer();
				if(parent instanceof Info){
					return ValidationStatus.ok();
				}

				if(ExpressionConstants.JAVA_METHOD_OPERATOR.equals(operation.getOperator().getType())){
					if(dataExpression != null && dataExpression.getContent() != null && !dataExpression.getContent().isEmpty() && ((Expression) expression).getContent() != null && !((Expression) expression).getContent().isEmpty()){
						if(!operation.getOperator().getInputTypes().isEmpty()){
							String typeName = operation.getOperator().getInputTypes().get(0);
							try{
								Class<?> dataReturnTypeClass = Class.forName(typeName);
								Class<?> expressionReturnTypeClass = Class.forName(((Expression) expression).getReturnType());
								if(!dataReturnTypeClass.isAssignableFrom(expressionReturnTypeClass)){
									return ValidationStatus.warning(Messages.bind(
											Messages.invalidReturnTypeBetween,dataExpression.getName(),
											((Expression) expression).getName()));
								}
							}catch (Exception e) {
								if(!operation.getOperator().getInputTypes().get(0).equals(((Expression) expression).getReturnType())){
									return ValidationStatus.warning(Messages.bind(
											Messages.invalidReturnTypeFor,
											((Expression) expression).getName()));
								}
							}
						}
					}
					return ValidationStatus.ok();
				}
				if(ExpressionConstants.MESSAGE_ID_TYPE.equals(operation.getRightOperand().getType())){
					return ValidationStatus.ok();
				}

				if(ModelHelper.isAnExpressionCopy((Expression) expression)){
					return ValidationStatus.ok();
				}
			}

			if(dataExpression != null && dataExpression.getContent() != null && !dataExpression.getContent().isEmpty() && ((Expression) expression).getContent() != null && !((Expression) expression).getContent().isEmpty()){
				try{
					Class<?> dataReturnTypeClass = Class.forName(dataExpression.getReturnType());
					Class<?> expressionReturnTypeClass = Class.forName(((Expression) expression).getReturnType());
					if(!dataReturnTypeClass.isAssignableFrom(expressionReturnTypeClass)){
						return ValidationStatus.warning(Messages.bind(
								Messages.invalidReturnTypeBetween,dataExpression.getName(),
								((Expression) expression).getName()));
					}
				}catch (Exception e) {
					if(!dataExpression.getReturnType().equals(((Expression) expression).getReturnType())){
						return ValidationStatus.warning(Messages.bind(
								Messages.invalidReturnTypeFor,
								((Expression) expression).getName()));
					}
				}

			}
		}
		return ValidationStatus.ok();
	}

	public void setDataExpression(Expression dataExpression){
		this.dataExpression =dataExpression ;
	}

}
