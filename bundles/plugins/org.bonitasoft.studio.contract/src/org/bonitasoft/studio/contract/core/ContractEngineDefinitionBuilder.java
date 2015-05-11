/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.core;

import org.bonitasoft.engine.bpm.contract.InputDefinition;
import org.bonitasoft.engine.bpm.contract.Type;
import org.bonitasoft.engine.bpm.contract.impl.InputDefinitionImpl;
import org.bonitasoft.engine.bpm.process.impl.ContractDefinitionBuilder;
import org.bonitasoft.studio.engine.contribution.BuildProcessDefinitionException;
import org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public abstract class ContractEngineDefinitionBuilder<T> implements IEngineDefinitionBuilder<T> {

    protected T builder;

    @Override
    public void build(final EObject element) throws BuildProcessDefinitionException {
        Assert.isNotNull(builder);
        Assert.isLegal(element instanceof Contract);
        final Contract contract = (Contract) element;

        final ContractDefinitionBuilder contractBuilder = addContract();
        for (final ContractInput input : contract.getInputs()) {
            if (input.getType() == ContractInputType.COMPLEX) {
                addComplexInput(contractBuilder, input);
            } else {
                contractBuilder.addInput(input.getName(), getInputType(input), input.getDescription(), input.isMultiple());
            }
        }
        buildConstraints(contract, contractBuilder);
    }

    protected void buildConstraints(final Contract contract, final ContractDefinitionBuilder contractBuilder) {
        for (final ContractConstraint constraint : contract.getConstraints()) {
            contractBuilder.addConstraint(constraint.getName(),
                    constraint.getExpression(),
                    constraint.getErrorMessage(),
                    constraint.getInputNames().toArray(new String[constraint.getInputNames().size()]));
        }
    }

    protected abstract ContractDefinitionBuilder addContract();

    @Override
    public void setEngineBuilder(final T engineBuilder) {
        builder = engineBuilder;
    }

    protected void addComplexInput(final ContractDefinitionBuilder contractBuilder, final ContractInput input) {
        final InputDefinition complexInput = buildComplexInput(input, contractBuilder);
        contractBuilder.addInput(complexInput.getName(), complexInput.getDescription(), complexInput.isMultiple(), complexInput.getInputs());
    }

    protected InputDefinition buildComplexInput(final ContractInput input, final ContractDefinitionBuilder contractBuilder) {
        final InputDefinitionImpl complexInput = new InputDefinitionImpl(input.getName(), null, input.getDescription(), input.isMultiple());
        for (final ContractInput child : input.getInputs()) {
            if (ContractInputType.COMPLEX == child.getType()) {
                complexInput.getInputs().add(buildComplexInput(child, contractBuilder));
            } else {
                buildLeafInput(contractBuilder, complexInput, child);
            }
        }
        return complexInput;
    }

    protected void buildLeafInput(final ContractDefinitionBuilder contractBuilder, final InputDefinitionImpl complexInput, final ContractInput child) {
        final Type inputType = getInputType(child);
        complexInput.getInputs().add(new InputDefinitionImpl(child.getName(), inputType, child.getDescription(), child.isMultiple()));
    }

    public Type getInputType(final ContractInput input) {
        return org.bonitasoft.engine.bpm.contract.Type.valueOf(input.getType().getName());
    }

    @Override
    public boolean appliesTo(final EObject context, final EObject element) {
        return element instanceof Contract;
    }

}
