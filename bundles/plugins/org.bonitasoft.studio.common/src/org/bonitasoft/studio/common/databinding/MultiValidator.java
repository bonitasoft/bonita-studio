package org.bonitasoft.studio.common.databinding;

import java.util.List;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class MultiValidator implements IValidator {

	private List<IValidator> validators;
	
	
	public MultiValidator(List<IValidator> validators){
		this.validators = validators;
	}
	
	
	@Override
	public IStatus validate(Object value) {
		for (IValidator validator:validators){
			IStatus status = validator.validate(value);
			if (!status.isOK()){
				return status;
			}
		}
		return Status.OK_STATUS;
	}

}
