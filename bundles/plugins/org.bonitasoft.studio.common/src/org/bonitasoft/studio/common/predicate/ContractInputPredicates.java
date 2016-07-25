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
package org.bonitasoft.studio.common.predicate;

import java.util.Objects;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;

import com.google.common.base.Predicate;

public class ContractInputPredicates {

    public static Predicate<ContractInput> withContractInputName(final String name) {
        return new Predicate<ContractInput>() {

            @Override
            public boolean apply(final ContractInput input) {
                return Objects.equals(name, input.getName());
            }
        };
    }

    public static Predicate<ContractInput> withContractInputType(final ContractInputType type) {
        return new Predicate<ContractInput>() {

            @Override
            public boolean apply(final ContractInput input) {
                return Objects.equals(type, input.getType());
            }
        };
    }

    public static Predicate<ContractInput> multipleContractInput() {
        return new Predicate<ContractInput>() {

            @Override
            public boolean apply(final ContractInput input) {
                return input.isMultiple();
            }
        };
    }

    public static Predicate<ContractInput> withMultipleInHierarchy() {
        return new Predicate<ContractInput>() {

            @Override
            public boolean apply(final ContractInput input) {
                ContractInput current = input;
                while (current.eContainer() instanceof ContractInput) {
                    if (current.isMultiple()) {
                        return true;
                    }
                    current = (ContractInput) current.eContainer();
                }
                return current.isMultiple();
            }
        };
    }

    public static Predicate<ContractInput> withComplexMultipleInHierarchy() {
        return new Predicate<ContractInput>() {

            @Override
            public boolean apply(final ContractInput input) {
                ContractInput current = input;
                while (current.eContainer() instanceof ContractInput) {
                    if (current.isMultiple() && current.getType() == ContractInputType.COMPLEX) {
                        return true;
                    }
                    current = (ContractInput) current.eContainer();
                }
                return current.isMultiple();
            }
        };
    }
}
