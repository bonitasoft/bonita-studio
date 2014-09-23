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
package org.bonitasoft.studio.contract.core;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintUtil {

    public static List<ContractConstraint> getConstraintsForInput(final Contract contract, final ContractInput input) {
        final List<ContractConstraint> result = new ArrayList<ContractConstraint>();
        for (final ContractConstraint constraint : contract.getConstraints()) {
            if (constraint.getInputNames().contains(input.getName())) {
                result.add(constraint);
            }
        }
        return result;
    }

    public static ContractConstraint createConstraint(final String name, final String expression, final String errorMessage, final ContractInput... inputs) {
        final ContractConstraint c = ProcessFactory.eINSTANCE.createContractConstraint();
        c.setName(name);
        c.setExpression(expression);
        c.setErrorMessage(errorMessage);
        for (final ContractInput input : inputs) {
            c.getInputNames().add(input.getName());
        }
        return c;
    }

}
