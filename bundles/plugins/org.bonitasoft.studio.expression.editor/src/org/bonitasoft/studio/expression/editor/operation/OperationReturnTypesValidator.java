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

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.Info;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * @author Romain Bioteau
 *
 */
public class OperationReturnTypesValidator implements IExpressionValidator {

	private Expression dataExpression;
	private ExpressionTypeLabelProvider typeLabelProvider = new ExpressionTypeLabelProvider();
	private OperatorLabelProvider operatorLabelProvider = new OperatorLabelProvider();
	private Expression inputExpression;


	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
	 */
	@Override
	public IStatus validate(Object value) {
		Expression expression = getExpression(value);
		if(expression != null){
			String expressionContent = getExpressionContent(value, expression);
			String expressionName = getExpressionName(value, expression);
			if(dataExpression != null 
					&&  dataExpression.getContent() != null 
					&& !dataExpression.getContent().isEmpty() 
					&& expressionContent != null 
					&& !expressionContent.isEmpty()){
				final Operation operation = getOperation(expression);
				if(operation != null && needValidation(operation)){
					final String operatorType = operation.getOperator().getType();
					if(ExpressionConstants.JAVA_METHOD_OPERATOR.equals(operatorType)){
						return validateJavaMethodOperation(expression,
								expressionName, operation);
					}else if (ExpressionConstants.XPATH_UPDATE_OPERATOR.equals(operatorType)){
						return validateXPathOperation(expression,
								expressionName, operation);
					}else if(ExpressionConstants.SET_DOCUMENT_OPERATOR.equals(operatorType)){
						IStatus status = validateSetDocumentOperation(expression,
								expressionName, operation);
						if(status != null){
							return status;
						}
					}else if(ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(operatorType)){
						IStatus status = validateCreateBusinessDataOperation(expression,
								expressionName, operation);
						if(status != null){
							return status;
						}
					}else if(ExpressionConstants.ASSIGNMENT_OPERATOR.equals(operatorType)){
						if(ExpressionConstants.DOCUMENT_REF_TYPE.equals(dataExpression.getType())){
							return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,typeLabelProvider.getText(dataExpression.getType()),operatorLabelProvider.getText(operation.getOperator())));
						}
						if(ExpressionConstants.VARIABLE_TYPE.equals(dataExpression.getType())
								&&  (!dataExpression.getReferencedElements().isEmpty() && dataExpression.getReferencedElements().get(0) instanceof BusinessObjectData)){
							return ValidationStatus.error(Messages.bind(Messages.businessDataNotCompatibleForOperator,operatorLabelProvider.getText(operation.getOperator())));
						}
					}
					if(ExpressionConstants.MESSAGE_ID_TYPE.equals(operation.getRightOperand().getType())){
						return ValidationStatus.ok();
					}

					if(ModelHelper.isAnExpressionCopy((Expression) expression)){
						return ValidationStatus.ok();
					}
				}

				try{
					Class<?> dataReturnTypeClass = Class.forName(dataExpression.getReturnType());
					Class<?> expressionReturnTypeClass = Class.forName(((Expression) expression).getReturnType());
					if(!dataReturnTypeClass.isAssignableFrom(expressionReturnTypeClass)){
						return ValidationStatus.warning(Messages.bind(
								Messages.invalidReturnTypeBetween,dataExpression.getName(),
								expressionName));
					}
				}catch (Exception e) {
					if(!dataExpression.getReturnType().equals(((Expression) expression).getReturnType())){
						return ValidationStatus.warning(Messages.bind(
								Messages.invalidReturnTypeFor,
								expressionName));
					}
				}

				if(ExpressionConstants.CONSTANT_TYPE.equals(expression.getType())){
					String returnType = expression.getReturnType();
					if(expressionContent != null && !expressionContent.isEmpty()){
						if(Integer.class.getName().equals(returnType)){
							try{
								Integer.valueOf(expressionContent);
							}catch (NumberFormatException e) {
								return ValidationStatus.warning(Messages.bind(Messages.expressionValueNotCompatibleWithReturnType,expressionContent,returnType));
							}
						}else if(Double.class.getName().equals(returnType)){
							try{
								Double.valueOf(expressionContent);
							}catch (NumberFormatException e) {
								return ValidationStatus.warning(Messages.bind(Messages.expressionValueNotCompatibleWithReturnType,expressionContent,returnType));
							}
						}else if(Float.class.getName().equals(returnType)){
							try{
								Float.valueOf(expressionContent);
							}catch (NumberFormatException e) {
								return ValidationStatus.warning(Messages.bind(Messages.expressionValueNotCompatibleWithReturnType,expressionContent,returnType));
							}
						}else if(Long.class.getName().equals(returnType)){
							try{
								Long.valueOf(expressionContent);
							}catch (NumberFormatException e) {
								return ValidationStatus.warning(Messages.bind(Messages.expressionValueNotCompatibleWithReturnType,expressionContent,returnType));
							}
						}
					}

				}

			}
		}
		return ValidationStatus.ok();
	}

	protected IStatus validateCreateBusinessDataOperation(Expression expression,
			String expressionName, Operation operation) {
		if(!ExpressionConstants.VARIABLE_TYPE.equals(dataExpression.getType()) 
				&& !(dataExpression.getReferencedElements().get(0) instanceof BusinessObjectData)){
			return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,typeLabelProvider.getText(dataExpression.getType()),operatorLabelProvider.getText(operation.getOperator())));
		}
		return null;
	}

	protected boolean needValidation(Operation operation) {
		return !(operation.eContainer() instanceof Info);
	}

	protected Operation getOperation(Expression expression) {
		EObject container = expression.eContainer() ;
		if(container instanceof Operation){
			return (Operation) container;
		}
		return null;
	}

	protected Expression getExpression(Object value) {
		Expression expression = null;
		if(value instanceof Expression){
			expression = (Expression) value;
		}else if( inputExpression != null){
			expression = inputExpression;
		}
		return expression;
	}

	protected String getExpressionContent(Object value, Expression expression) {
		String expressionContent = expression.getContent();
		if(value instanceof String){//Expression content to validate
			expressionContent = value.toString();
		}
		return expressionContent;
	}

	protected String getExpressionName(Object value, Expression expression) {
		String expressionName = ((Expression) expression).getName();
		if(expressionName == null || expressionName.isEmpty()){
			expressionName = value.toString();
		}
		return expressionName;
	}

	protected IStatus validateSetDocumentOperation(Expression expression,
			String expressionName, final Operation operation) {
		if(!String.class.getName().equals(dataExpression.getReturnType())){
			return ValidationStatus.error(Messages.bind(Messages.incompatibleStorageReturnType,dataExpression.getName(),operatorLabelProvider.getText(operation.getOperator())));
		}
		if (dataExpression!=null && dataExpression.getContent()!=null && !dataExpression.getContent().isEmpty()){
			String typeName = dataExpression.getReturnType();
			String actionType = ((Expression) expression).getReturnType();
			if(!(actionType.equals(DocumentValue.class.getName()) && typeName.equals(String.class.getName()))){
				return ValidationStatus.warning(Messages.bind(
						Messages.invalidReturnTypeBetween,dataExpression.getName(),
						expressionName)+ "\n"+ Messages.documentValueExprected);
			}else{
				return ValidationStatus.ok();
			}
		}
		return null;
	}

	protected IStatus validateXPathOperation(Expression expression,
			String expressionName, final Operation operation) {
		if(!ExpressionConstants.VARIABLE_TYPE.equals(dataExpression.getType())){
			return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,typeLabelProvider.getText(dataExpression.getType()),operatorLabelProvider.getText(operation.getOperator())));
		}
		if (dataExpression!=null && dataExpression.getContent()!=null && !dataExpression.getContent().isEmpty()){
			if (!operation.getOperator().getInputTypes().isEmpty()){
				String typeName = operation.getOperator().getInputTypes().get(0);
				try{
					Class<?> dataReturnTypeClass = Class.forName(typeName);
					Class<?> expressionReturnTypeClass = Class.forName(((Expression) expression).getReturnType());
					if(!dataReturnTypeClass.isAssignableFrom(expressionReturnTypeClass)){
						return ValidationStatus.warning(Messages.bind(
								Messages.invalidReturnTypeBetween,dataExpression.getName(),
								expressionName));
					}
				}catch (Exception e) {
					if(!operation.getOperator().getInputTypes().get(0).equals(((Expression) expression).getReturnType())){
						return ValidationStatus.warning(Messages.bind(
								Messages.invalidReturnTypeFor,
								expressionName));
					}
				}
			}
		}
		return ValidationStatus.ok();
	}

	protected IStatus validateJavaMethodOperation(Expression expression,
			String expressionName, final Operation operation) {
		if(!ExpressionConstants.VARIABLE_TYPE.equals(dataExpression.getType())
				|| !(!dataExpression.getReferencedElements().isEmpty() && dataExpression.getReferencedElements().get(0) instanceof JavaObjectData)){
			return ValidationStatus.error(Messages.bind(Messages.incompatibleExpressionTypeForOperator,typeLabelProvider.getText(dataExpression.getType()),operatorLabelProvider.getText(operation.getOperator())));
		}
		if(!operation.getOperator().getInputTypes().isEmpty()){
			String typeName = operation.getOperator().getInputTypes().get(0);
			try{
				Class<?> dataReturnTypeClass = Class.forName(typeName);
				Class<?> expressionReturnTypeClass = Class.forName(((Expression) expression).getReturnType());
				if(!dataReturnTypeClass.isAssignableFrom(expressionReturnTypeClass)){
					return ValidationStatus.warning(Messages.bind(
							Messages.invalidReturnTypeBetween,dataExpression.getName(),
							expressionName));
				}
			}catch (Exception e) {
				if(!operation.getOperator().getInputTypes().get(0).equals(((Expression) expression).getReturnType())){
					return ValidationStatus.warning(Messages.bind(
							Messages.invalidReturnTypeFor,
							expressionName));
				}
			}
		}
		return ValidationStatus.ok();
	}

	public void setDataExpression(Expression dataExpression){
		this.dataExpression =dataExpression ;
	}

	@Override
	public void setInputExpression(Expression inputExpression) {
		this.inputExpression = inputExpression;
	}

	@Override
	public void setDomain(EditingDomain domain) {

	}

	@Override
	public void setContext(EObject context) {
	}

}
