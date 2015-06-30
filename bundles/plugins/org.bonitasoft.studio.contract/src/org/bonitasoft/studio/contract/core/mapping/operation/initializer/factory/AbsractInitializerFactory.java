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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer.factory;

import static com.google.common.collect.Iterables.tryFind;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withContractInputName;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;

import com.google.common.base.Optional;

public abstract class AbsractInitializerFactory implements InitializerFactory {

    protected ContractInput persistenceIdInput(final ContractInput contractInput) {
        if (withContractInputName(Field.PERSISTENCE_ID).apply(contractInput)) {
            return contractInput;
        }
        final Optional<ContractInput> persistenceIdInput = tryFind(contractInput.getInputs(), withContractInputName(Field.PERSISTENCE_ID));
        if (persistenceIdInput.isPresent()) {
            return persistenceIdInput.get();
        }
        throw new IllegalStateException(String.format("persistenceId input not found in %s", contractInput.getName()));
    }

    protected BusinessObject firstMultipleParentBusinessObject(final FieldToContractInputMapping mapping) {
        FieldToContractInputMapping parentMapping = mapping.getParent();
        while (parentMapping != null && !parentMapping.getField().isCollection()) {
            parentMapping = parentMapping.getParent();
        }

        return parentMapping != null ? ((RelationField) parentMapping.getField()).getReference() : null;
    }

    protected String toRefName(final FieldToContractInputMapping mapping, final BusinessObjectData data) {
        return mapping.getParent() != null ? mapping.getParent().getField().getName() + "Var" + "." + mapping.getField().getName() : data.getName() + "."
                + mapping.getField().getName();
    }

}
