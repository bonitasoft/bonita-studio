/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.jface.databinding.validator;

import java.io.UnsupportedEncodingException;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;


/**
 * @author Romain Bioteau
 *
 */
public class UTF8InputValidator implements IValidator {

    private final String inputName;

    public UTF8InputValidator(String inputName){
        this.inputName = inputName ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(Object input) {
        try {
            if(input != null && !NamingUtils.isUTF8String(input.toString())){
                return ValidationStatus.error(Messages.bind(Messages.containsNonUTF8Characters,inputName)) ;
            }
        } catch (UnsupportedEncodingException e) {
            BonitaStudioLog.error(e) ;
        }
        return ValidationStatus.ok() ;
    }

}
