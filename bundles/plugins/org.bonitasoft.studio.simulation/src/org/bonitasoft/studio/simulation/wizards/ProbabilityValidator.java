/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.simulation.wizards;

import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author aurelie Zara
 *
 */
public class ProbabilityValidator implements IValidator {
	
	IValidator doubleValidator;
	
	public ProbabilityValidator(IValidator doubleValidator){
		this.doubleValidator=doubleValidator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
	 */
	@Override
	public IStatus validate(Object value) {
		if (value!=null){
			IStatus status = doubleValidator.validate(value);
		if (status.getSeverity()==Status.OK){
		if (value instanceof String){
			String s=(String) value;
			if (s.endsWith("%")){
				s=s.substring(0,s.length()-1);
			}
			double d = Double.parseDouble((String)s);
			if (d>=0 && d<=100){
				return Status.OK_STATUS;
			} else {
				return ValidationStatus.error(Messages.notAProba);
			}
		}
		} else {
			return status;
		}
		}
		return null;
	
	}

}
