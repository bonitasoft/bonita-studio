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

import java.util.List;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;

public class RelationFieldToContractInputMapping extends FieldToContractInputMapping {

    private final RelationField relationField;

    public RelationFieldToContractInputMapping(final RelationField field) {
        super(field);
        relationField = field;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping#toContractInput()
     */
    @Override
    public ContractInput toContractInput(final ContractInput parentInput) {
        final ContractInput input = super.toContractInput(parentInput);
        for (final FieldToContractInputMapping child : getChildren()) {
            if (child.isGenerated()) {
                child.toContractInput(input);
            }
        }
        return input;
    }

    @Override
    protected ContractInputType toContractInputType() {
        return ContractInputType.COMPLEX;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping#getFieldType()
     */
    @Override
    public String getFieldType() {
        return relationField.isCollection() ? List.class.getName() : relationField.getReference().getQualifiedName();
    }
}
