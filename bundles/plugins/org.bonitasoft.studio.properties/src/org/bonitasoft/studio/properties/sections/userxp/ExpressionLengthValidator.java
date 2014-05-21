package org.bonitasoft.studio.properties.sections.userxp;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

public class ExpressionLengthValidator implements IExpressionValidator {

	private Expression inputExpression;
	private int maxLength;
	
	public ExpressionLengthValidator(int maxLength){
		this.maxLength=maxLength;
	}
	
	@Override
	public IStatus validate(Object value) {
		if (ExpressionConstants.CONSTANT_TYPE.equals(inputExpression.getType())){
			if (((String)value).length()>maxLength){
				return ValidationStatus.error(Messages.bind(Messages.errorDisplayLabelMaxLength,maxLength+1));
			}
		}
	return Status.OK_STATUS;
	
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
