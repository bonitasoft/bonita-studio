/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.core.operation;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import org.bonitasoft.studio.contract.core.mapping.treeMaching.TreeResult;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.web.designer.model.contract.LeafContractInput;
import org.bonitasoft.web.designer.model.contract.NodeContractInput;

import com.google.common.base.Function;

public class ToWebContract implements Function<Contract, org.bonitasoft.web.designer.model.contract.Contract> {

    private TreeResult treeResult;

    public ToWebContract(TreeResult treeResult) {
        this.treeResult = treeResult;
    }
    
    public ToWebContract() {
        this(new TreeResult());
    }

    @Override
    public org.bonitasoft.web.designer.model.contract.Contract apply(final Contract fromContract) {
        final org.bonitasoft.web.designer.model.contract.Contract contract = new org.bonitasoft.web.designer.model.contract.Contract();
        contract.getInput().addAll(newArrayList(transform(fromContract.getInputs(), toWebContractInput())));
        return contract;
    }

    private Function<ContractInput, org.bonitasoft.web.designer.model.contract.ContractInput> toWebContractInput() {
        return new Function<ContractInput, org.bonitasoft.web.designer.model.contract.ContractInput>() {

            @Override
            public org.bonitasoft.web.designer.model.contract.ContractInput apply(final ContractInput input) {
                switch (input.getType()) {
                    case TEXT:
                        return createLeafContractInput(input, String.class);
                    case INTEGER:
                        return createLeafContractInput(input, Integer.class);
                    case LONG:
                        return createLeafContractInput(input, Long.class);
                    case DECIMAL:
                        return createLeafContractInput(input, Double.class);
                    case DATE:
                        return createLeafContractInput(input, Date.class);
                    case LOCALDATE:
                        return createLeafContractInput(input, LocalDate.class);
                    case LOCALDATETIME:
                        return createLeafContractInput(input, LocalDateTime.class);
                    case OFFSETDATETIME:
                        return createLeafContractInput(input, OffsetDateTime.class);
                    case BOOLEAN:
                        return createLeafContractInput(input, Boolean.class);
                    case FILE:
                        return createLeafContractInput(input, File.class);
                    case COMPLEX:
                        return createNodeContractInput(input);
                    default:
                        throw new IllegalStateException(String.format("Unsupported input type: %s", input.getType()));
                }
            }
        };
    }

    private org.bonitasoft.web.designer.model.contract.ContractInput createLeafContractInput(final ContractInput input,
            final Class<?> type) {
        return copyInputProperties(input, new LeafContractInput(input.getName(), type));
    }

    private org.bonitasoft.web.designer.model.contract.ContractInput copyInputProperties(final ContractInput input,
            final org.bonitasoft.web.designer.model.contract.ContractInput contractInput) {
        contractInput.setMultiple(input.isMultiple());
        contractInput.setDescription(input.getDescription());
        if(contractInput instanceof NodeContractInput) {
            ((NodeContractInput) contractInput).setDataReference(treeResult.getDataReference(input));
        }
        return contractInput;
    }

    private org.bonitasoft.web.designer.model.contract.ContractInput createNodeContractInput(final ContractInput input) {
        final org.bonitasoft.web.designer.model.contract.ContractInput nodeInput = copyInputProperties(input,
                new NodeContractInput(input.getName()));
        nodeInput.getInput().addAll(newArrayList(transform(input.getInputs(), toWebContractInput())));
        return nodeInput;
    }
}
