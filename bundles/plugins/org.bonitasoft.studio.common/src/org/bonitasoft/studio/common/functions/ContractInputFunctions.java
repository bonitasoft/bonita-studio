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
package org.bonitasoft.studio.common.functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.emf.ecore.EObject;


public class ContractInputFunctions {

    public static Function<ContractInput, List<String>> toAncestorNameList() {
        return input -> {
            final List<String> ancestors = new ArrayList<>();
            EObject current = input;
            while (current instanceof ContractInput) {
                ancestors.add(((ContractInput) current).getName());
                current = current.eContainer();
            }
            Collections.reverse(ancestors);
            return ancestors;
        };
    }

    public static Function<ContractInput, List<String>> toAncestorNameListUntilMultipleComplex() {
        return input -> {
            final List<String> ancestors = new ArrayList<>();
            EObject current = input;
            do {
                ancestors.add(((ContractInput) current).getName());
                current = current.eContainer();
            } while (current instanceof ContractInput
                    && !(((ContractInput) current).isMultiple()
                            && ((ContractInput) current).getType() == ContractInputType.COMPLEX));
            Collections.reverse(ancestors);
            return ancestors;
        };
    }

}
