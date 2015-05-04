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
package org.bonitasoft.studio.contract.core.mapping;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author aurelie
 */
public class RootContractInputGenerator {

    private final String rootContractInputName;
    private List<FieldToContractInputMapping> children = new ArrayList<FieldToContractInputMapping>();

    public RootContractInputGenerator(final String rootContractInputName, final List<FieldToContractInputMapping> children) {
        this.rootContractInputName = rootContractInputName;
        this.children = children;
    }

    public ContractInput toRootContractInput() {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(rootContractInputName);
        contractInput.setType(ContractInputType.COMPLEX);
        for (final FieldToContractInputMapping mapping : children) {
            if (mapping.isGenerated()) {
                contractInput.getInputs().add(mapping.toContractInput());
            }
        }
        return contractInput;
    }
}
