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

import java.util.Objects;

import org.bonitasoft.engine.bpm.contract.Type;
import org.bonitasoft.engine.bpm.process.impl.ContractDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.InputContainerDefinitionBuilder;
import org.bonitasoft.studio.engine.contribution.BuildProcessDefinitionException;
import org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public abstract class ContractEngineDefinitionBuilder<T> implements IEngineDefinitionBuilder<T> {

    protected T builder;

    @Override
    public void build(final EObject element) throws BuildProcessDefinitionException {
        Objects.requireNonNull(builder);
        if (!(element instanceof Contract)) {
            throw new IllegalArgumentException();
        }
        final Contract contract = (Contract) element;

        final ContractDefinitionBuilder contractBuilder = addContract();
        for (final ContractInput input : contract.getInputs()) {
            addInput(input, contractBuilder);
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

    private void addChildInput(final ContractInput input,
            final InputContainerDefinitionBuilder contractInputDefinitionBuilder) {
        for (final ContractInput child : input.getInputs()) {
            addInput(child, contractInputDefinitionBuilder);
        }
    }

    private void addInput(final ContractInput input,
            final InputContainerDefinitionBuilder contractInputDefinitionBuilder) {
        if (ContractInputType.COMPLEX == input.getType()) {
            addChildInput(input, contractInputDefinitionBuilder.addComplexInput(input.getName(),
                    input.getDescription(), input.isMultiple()));
        } else if (Type.FILE == getInputType(input)) {
            contractInputDefinitionBuilder.addFileInput(input.getName(), input.getDescription(), input.isMultiple());
        } else {
            contractInputDefinitionBuilder.addInput(input.getName(), getInputType(input), input.getDescription(),
                    input.isMultiple());
        }
    }

    private Type getInputType(final ContractInput input) {
        return org.bonitasoft.engine.bpm.contract.Type.valueOf(input.getType().getName());
    }

    @Override
    public boolean appliesTo(final EObject context, final EObject element) {
        return element instanceof Contract;
    }

}
