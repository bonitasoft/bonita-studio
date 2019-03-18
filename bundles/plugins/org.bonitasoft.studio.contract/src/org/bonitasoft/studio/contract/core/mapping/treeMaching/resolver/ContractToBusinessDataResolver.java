/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping.treeMaching.resolver;

import java.util.Optional;

import org.bonitasoft.studio.contract.core.mapping.treeMaching.BusinessDataStore;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.TreeBuilder;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.TreeResult;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;

public class ContractToBusinessDataResolver {

    private BusinessDataStore store;

    public ContractToBusinessDataResolver(BusinessDataStore store) {
        this.store = store;
    }

    public Optional<BusinessObjectData> findBusinessDataFor(ContractInput input) {
        return store.getBusinessData().stream()
                .filter(data -> data.getName().equals(input.getDataReference()))
                .findFirst();
    }
    
    public TreeResult resolve(Contract contract) {
        TreeResult result = new TreeResult();
        TreeBuilder builder = new TreeBuilder(store);
        for(ContractInput input : contract.getInputs()) {
            findBusinessDataFor(input).ifPresent(data -> result.addNode(builder.buildBusinessObjectTree(data, input)));
        }
        return result;
    }
    
    public TreeResult resolve(ContractInput input) {
        TreeResult result = new TreeResult();
        TreeBuilder builder = new TreeBuilder(store);
        findBusinessDataFor(input).ifPresent(data -> result.addNode(builder.buildBusinessObjectTree(data, input)));
        return result;
    }
}
