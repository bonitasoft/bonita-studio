/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface.databinding.validator;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;


/**
 * @author Romain Bioteau
 */
public class ForbiddenCharactersValidator implements IValidator{

    private final String inputName;
    private final char[] forbiddenCharacters;

    public ForbiddenCharactersValidator(final String inputName, final char... forbiddenCharacters) {
        this.inputName = inputName;
        this.forbiddenCharacters = forbiddenCharacters;
    }

	@Override
	public IStatus validate(final Object value) {
        if (value != null) {
            final String s = value.toString();
            for (final char ch : forbiddenCharacters) {
                if (s.indexOf(ch) != -1) {
                    return ValidationStatus.error(NLS.bind(Messages.InvalidCharacterError, inputName, String.valueOf(ch)));
                }
            }
		}
		return ValidationStatus.ok();
	}

}
