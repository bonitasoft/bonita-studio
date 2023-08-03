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
package org.bonitasoft.studio.common.databinding.validator;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

/**
 * A validator factory which looks for registered validators for handling a particular EMF {@link EAttribute},
 * eventually in the context of a particular owning {@link EClass}.
 * 
 * @author Vincent Hemery
 */
public abstract class EAttributeValidatorFactory implements IValidatorFactory {

    /** The attribute to look for an */
    private final EAttribute attribute;

    /**
     * Find a registered validator factory using the {@link Adapters} framework.
     * 
     * @param attribute the {@link EAttribute} the validators will check the value for
     * @return registered validator factory
     */
    public static Optional<EAttributeValidatorFactory> findForAttribute(EAttribute attribute) {
        EAttributeValidatorFactory registeredFactory = Adapters.adapt(attribute, EAttributeValidatorFactory.class);
        return Optional.ofNullable(registeredFactory);
    }

    /**
     * Create a new validator factory to validate property value corresponding to a particular {@link EAttribute}
     * 
     * @param attribute the {@link EAttribute} the validators will check the value for
     * @param creatorPerEClass the function to create the validator for a particular EClass context
     * @return new validator factory
     */
    public static EAttributeValidatorFactory forAttributeValidator(EAttribute attribute,
            Function<EClass, IValidator> creatorPerEClass) {
        return new EAttributeValidatorFactory(attribute) {

            @Override
            protected IValidator doCreate(EClass ownerEClass) {
                return creatorPerEClass.apply(ownerEClass);
            }
        };
    }

    /**
     * Create a validator factory to validate property corresponding value to a particular {@link EAttribute}
     * 
     * @param attribute the {@link EAttribute} the validators will check the value for
     */
    private EAttributeValidatorFactory(EAttribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public IValidator create() {
        return this.create(attribute.getEContainingClass());
    }

    /**
     * Create a validator for a particular class context.
     * 
     * @param ownerEClass the EClass of the context element which property is validated
     * @return specialized validator
     */
    public final IValidator create(EClass ownerEClass) {
        checkArgument(attribute.getEContainingClass().isSuperTypeOf(ownerEClass));
        return doCreate(ownerEClass);
    }

    /**
     * Do create a validator for a particular class context.
     * Subclasses must override.
     * 
     * @param ownerEClass the EClass of the context element which property is validated
     * @return specialized validator
     */
    protected abstract IValidator doCreate(EClass ownerEClass);

}
