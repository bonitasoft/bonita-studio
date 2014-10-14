/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.core.validation;

import org.bonitasoft.studio.common.databinding.MultiValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputDescriptionValidationRule implements IValidationRule {

    protected static final String DESCRIPTION_CONSTRAINT_ID = "description";

    private final MultiValidator descriptionValidator;

    public ContractInputDescriptionValidationRule() {
        descriptionValidator = new MultiValidator();
        descriptionValidator.addValidator(new InputLengthValidator(Messages.description, 255));
    }

    @Override
    public boolean appliesTo(final EObject element) {
        return element instanceof ContractInput;
    }

    @Override
    public IStatus validate(final EObject element) {
        final ContractInput input = (ContractInput) element;
        return descriptionValidator.validate(input.getDescription());
    }

    @Override
    public String getRuleId() {
        return DESCRIPTION_CONSTRAINT_ID;
    }

}
