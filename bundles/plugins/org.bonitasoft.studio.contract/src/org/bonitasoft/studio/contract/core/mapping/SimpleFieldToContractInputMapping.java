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

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.process.ContractInputType;

/**
 * @author aurelie
 */
public class SimpleFieldToContractInputMapping extends FieldToContractInputMapping {

    private final SimpleField simpleField;

    public SimpleFieldToContractInputMapping(final SimpleField field) {
        super(field);
        simpleField = field;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping#getFieldType()
     */
    @Override
    public String getFieldType() {
        return simpleField.isCollection() ? List.class.getName() : simpleField.getType().getClazz().getName();
    }

    @Override
    protected ContractInputType toContractInputType() {
        final FieldType fieldType = simpleField.getType();
        switch (fieldType) {
            case STRING:
                return ContractInputType.TEXT;
            case TEXT:
                return ContractInputType.TEXT;
            case INTEGER:
                return ContractInputType.INTEGER;
            case LONG:
                return ContractInputType.TEXT;
            case DOUBLE:
                return ContractInputType.DECIMAL;
            case FLOAT:
                return ContractInputType.DECIMAL;
            case BOOLEAN:
                return ContractInputType.BOOLEAN;
            case DATE:
                return ContractInputType.DATE;
            case LOCALDATE:
                return ContractInputType.LOCALDATE;
            case LOCALDATETIME:
                return ContractInputType.LOCALDATETIME;
            case OFFSETDATETIME:
                return ContractInputType.OFFSETDATETIME;
            default:
                throw new IllegalStateException(
                        String.format("Failed to convert field type %s to contract input type", fieldType));
        }
    }
}
