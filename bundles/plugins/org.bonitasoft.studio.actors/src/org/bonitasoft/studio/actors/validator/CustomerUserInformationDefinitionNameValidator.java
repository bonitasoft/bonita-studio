/**
 * 
 */
package org.bonitasoft.studio.actors.validator;

import java.util.Set;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * @author florine
 *
 */
public class CustomerUserInformationDefinitionNameValidator implements IValidator {

	
	
	Set<String> existingNames;
	
	
	public CustomerUserInformationDefinitionNameValidator(Set<String> existingNames) {
		this.existingNames=existingNames;
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
	 */
	@Override
	public IStatus validate(Object value) {
		if(value==null){
			return ValidationStatus.error(Messages.customUserInfoDefinitionNotEmpty);
		}
		String text = value.toString();
		if(text== null || text.trim().isEmpty()){
			return ValidationStatus.error(Messages.customUserInfoDefinitionNotEmpty);
		}
		if(text.startsWith(" ") || text.endsWith(" ")){
			return ValidationStatus.error(Messages.customUserInfoDefinitionNoWhiteSpaceAtStartOrEnd);
		}
		if(existingNames.contains(text.toLowerCase())){
			return ValidationStatus.error(Messages.customUserInfoDefinitionAlreadyExist);
		}
		if(text.length()>50){
			return ValidationStatus.error(Messages.customUserInfoDefinitionNameTooLong);
		}				
		return ValidationStatus.ok();
	}

}
