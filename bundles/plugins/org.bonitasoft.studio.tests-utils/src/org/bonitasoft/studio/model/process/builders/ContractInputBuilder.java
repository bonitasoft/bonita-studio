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
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class ContractInputBuilder implements Buildable<ContractInput> {

    public static ContractInputBuilder aContractInput() {
        return new ContractInputBuilder(ProcessFactory.eINSTANCE.createContractInput());
    }

    private final ContractInput contractInput;

    private ContractInputBuilder(final ContractInput contractInput) {
        this.contractInput = contractInput;
    }

    public ContractInputBuilder withName(final String name) {
        contractInput.setName(name);
        return this;
    }

    public ContractInputBuilder withType(final ContractInputType type) {
        contractInput.setType(type);
        return this;
    }

    public ContractInputBuilder withDescription(final String description) {
        contractInput.setDescription(description);
        return this;
    }

    public ContractInputBuilder multiple() {
        contractInput.setMultiple(true);
        return this;
    }

    public ContractInputBuilder single() {
        contractInput.setMultiple(false);
        return this;
    }
    
    public ContractInputBuilder withDataReference(String dataReference) {
        contractInput.setDataReference(dataReference);
        return this;
    }

    public ContractInputBuilder havingMapping(final ContractInputMapping mapping) {
        contractInput.setMapping(mapping);
        return this;
    }

    public ContractInputBuilder havingInput(final Buildable<ContractInput>... input) {
        for (final Buildable<ContractInput> buildable : input) {
            contractInput.getInputs().add(buildable.build());
        }
        return this;
    }

    public ContractInputBuilder in(final Buildable<ContractInput> input) {
        input.build().getInputs().add(contractInput);
        return this;
    }

    @Override
    public ContractInput build() {
        return contractInput;
    }

}
