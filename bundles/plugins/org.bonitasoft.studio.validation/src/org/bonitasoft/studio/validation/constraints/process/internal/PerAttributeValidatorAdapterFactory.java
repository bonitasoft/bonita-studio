/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.constraints.process.internal;

import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.studio.common.databinding.validator.EAttributeValidatorFactory;
import org.bonitasoft.studio.validation.constraints.process.AbstractProcessVersionConstraint;
import org.bonitasoft.studio.validation.constraints.process.EmptyNameConstraint;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EAttribute;

/**
 * This adapter factory registers validator factories for {@link ProcessPackage} attributes
 * 
 * @author Vincent Hemery
 */
public class PerAttributeValidatorAdapterFactory implements IAdapterFactory {

    public PerAttributeValidatorAdapterFactory() {
        // do nothing
    }

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isAssignableFrom(EAttributeValidatorFactory.class) && adaptableObject instanceof EAttribute) {
            // create corresponding validator factory to validate the attribute
            if (ProcessPackage.Literals.ELEMENT__NAME.equals(adaptableObject)) {
                return (T) EAttributeValidatorFactory.forAttributeValidator(
                        ProcessPackage.Literals.ELEMENT__NAME,
                        EmptyNameConstraint::nameValidator);
            } else if (ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION.equals(adaptableObject)) {
                return (T) EAttributeValidatorFactory.forAttributeValidator(
                        ProcessPackage.Literals.ELEMENT__NAME,
                        AbstractProcessVersionConstraint::versionValidator);
            }
        }
        return null;

    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class<?>[] { EAttributeValidatorFactory.class };
    }

}
