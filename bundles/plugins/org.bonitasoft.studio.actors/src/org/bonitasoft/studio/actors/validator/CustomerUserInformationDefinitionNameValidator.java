/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.validator;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author florine
 *
 */
public class CustomerUserInformationDefinitionNameValidator implements IValidator {

    private final Set<String> exisingNames = new HashSet<String>();

    public CustomerUserInformationDefinitionNameValidator(final Organization organization, final ColumnViewer viewer) {
        final StructuredSelection structuredSelection = (StructuredSelection) viewer.getSelection();
        final CustomUserInfoDefinition selectedCustomUserDef = (CustomUserInfoDefinition) structuredSelection.getFirstElement();
        final String selectedDefinitionName = selectedCustomUserDef.getName();

        if (organization != null && organization.getCustomUserInfoDefinitions() != null
                && organization.getCustomUserInfoDefinitions().getCustomUserInfoDefinition() != null) {
            for (final CustomUserInfoDefinition def : organization.getCustomUserInfoDefinitions().getCustomUserInfoDefinition()) {
                if (!def.getName().equalsIgnoreCase(selectedDefinitionName)) {
                    exisingNames.add(def.getName().toLowerCase());
                }
            }
        }
    }


    /* (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(final Object value) {
        if(value==null){
            return ValidationStatus.error(Messages.customUserInfoDefinitionNotEmpty);
        }
        final String text = value.toString();
        if(text== null || text.trim().isEmpty()){
            return ValidationStatus.error(Messages.customUserInfoDefinitionNotEmpty);
        }
        if(text.startsWith(" ") || text.endsWith(" ")){
            return ValidationStatus.error(Messages.customUserInfoDefinitionNoWhiteSpaceAtStartOrEnd);
        }
        if(exisingNames.contains(text.toLowerCase())){
            return ValidationStatus.error(Messages.customUserInfoDefinitionAlreadyExist);
        }
        if(text.length()>50){
            return ValidationStatus.error(Messages.customUserInfoDefinitionNameTooLong);
        }
        return ValidationStatus.ok();
    }

}
