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

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ContractDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.engine.operation.Operation;
import org.bonitasoft.studio.contract.core.exception.ContractCreationException;
import org.bonitasoft.studio.contract.core.exception.OperationCreationException;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.runtime.Assert;

/**
 * @author Romain Bioteau
 *
 */
public class EngineContractBuilder {

    private UserTaskDefinitionBuilder taskBuilder;
    private Contract contract;
    private ActivityDefinitionBuilder activityDefinitionBuilder;
    private final ContractMappingFactory contractMappingBuilder;


    public EngineContractBuilder() {
        contractMappingBuilder = new ContractMappingFactory();
    }

    public void build() throws ContractCreationException {
        Assert.isNotNull(taskBuilder);
        Assert.isNotNull(contract);

        final ContractDefinitionBuilder contractBuilder = taskBuilder.addContract();
        for (final ContractInput input : contract.getInputs()) {
            contractBuilder.addSimpleInput(input.getName(), org.bonitasoft.engine.bpm.contract.Type.valueOf(input.getType().getName()),
                    input.getDescription());
            if (input.isMandatory()) {
                contractBuilder.addMandatoryConstraint(input.getName());
            }
            //addMappingOperation(input);
        }
        for (final ContractConstraint constraint : contract.getConstraints()) {
            contractBuilder.addConstraint(constraint.getName(),
                    constraint.getExpression(),
                    constraint.getErrorMessage(),
                    constraint.getInputNames().toArray(new String[constraint.getInputNames().size()]));
        }

    }

    protected void addMappingOperation(final ContractInput input) throws ContractCreationException {
        if (isMapped(input)) {
            try {
                final Operation storageOperation = contractMappingBuilder.createOperation(input.getMapping());
                activityDefinitionBuilder.addOperation(storageOperation);
            } catch (final OperationCreationException e) {
                throw new ContractCreationException("Failed to create mapping operation for input " + input.getName(), e);
            }
        }
    }

    private boolean isMapped(final ContractInput input) {
        return input != null && input.getMapping() != null && input.getMapping().getData() != null;
    }

    public void setEngineBuilder(final UserTaskDefinitionBuilder builder) {
        taskBuilder = builder;
        activityDefinitionBuilder = builder;
    }

    public void setContract(final Contract contract) {
        this.contract = contract;
    }

}
