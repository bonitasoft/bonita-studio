/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.refactoring.core;

import static com.google.common.base.Preconditions.checkArgument;

import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class DependencyFeatureNameResolver {

    public EAttribute resolveNameDependencyFeatureFor(final EObject referencedElement) {
        checkArgument(referencedElement != null);
        final EClass eClass = referencedElement.eClass();
        if (ProcessPackage.Literals.ELEMENT.isSuperTypeOf(eClass)) {
            return ProcessPackage.Literals.ELEMENT__NAME;
        }
        if (ProcessPackage.Literals.CONTRACT_INPUT.equals(eClass)) {
            return ProcessPackage.Literals.CONTRACT_INPUT__NAME;
        }
        if (ParameterPackage.Literals.PARAMETER.equals(eClass)) {
            return ParameterPackage.Literals.PARAMETER__NAME;
        }
        if (ProcessPackage.Literals.SEARCH_INDEX.equals(eClass)) {
            return ProcessPackage.Literals.ELEMENT__NAME;
        }
        throw new IllegalStateException("Unresolvable dependency name feature for: " + referencedElement);
    }

}
