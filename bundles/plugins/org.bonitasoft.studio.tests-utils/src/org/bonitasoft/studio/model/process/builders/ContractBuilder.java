/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class ContractBuilder implements Buildable<Contract> {

    public static ContractBuilder aContract() {
        return new ContractBuilder(ProcessFactory.eINSTANCE.createContract());
    }

    private final Contract contract;

    private ContractBuilder(final Contract contract) {
        this.contract = contract;
    }

    @SafeVarargs
    public final ContractBuilder havingInput(final Buildable<ContractInput>... contractInput) {
        for (final Buildable<ContractInput> cb : contractInput) {
            contract.getInputs().add(cb.build());
        }
        return this;
    }

    public ContractBuilder havingInput(final ContractInput... contractInput) {
        for (final ContractInput input : contractInput) {
            contract.getInputs().add(input);
        }
        return this;
    }

    @SafeVarargs
    public final ContractBuilder havingConstraint(final Buildable<ContractConstraint>... contractConstraints) {
        for (final Buildable<ContractConstraint> cc : contractConstraints) {
            contract.getConstraints().add(cc.build());
        }
        return this;
    }

    @Override
    public Contract build() {
        return contract;
    }

    public ContractBuilder in(final Buildable<? extends ContractContainer> contractContainerBuildable) {
        contractContainerBuildable.build().setContract(contract);
        return this;
    }
}
