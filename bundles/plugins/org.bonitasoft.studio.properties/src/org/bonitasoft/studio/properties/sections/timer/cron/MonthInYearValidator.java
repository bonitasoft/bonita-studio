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
package org.bonitasoft.studio.properties.sections.timer.cron;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Romain Bioteau
 *
 */
public class MonthInYearValidator implements IValidator {

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
	 */
	@Override
	public IStatus validate(Object value) {
		if(value instanceof Integer){
			if(((Integer) value).intValue() < 1 || ((Integer) value).intValue() > 12){
				return ValidationStatus.error(Messages.bind(Messages.mustBeAnValueBetween,1,12));
			}
			return ValidationStatus.ok();
		}
		return ValidationStatus.error(Messages.notAValidNumber);
	}

}
