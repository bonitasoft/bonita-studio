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

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.BusinessObjectQueryInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.IPropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.MultipleBusinessObjectQueryInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.NewBusinessObjectInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.NewBusinessObjectListInitializer;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;

public class BusinessObjectInitializerFactory extends AbsractInitializerFactory implements InitializerFactory {

    @Override
    public IPropertyInitializer newPropertyInitializer(final FieldToContractInputMapping mapping, final BusinessObjectData data, final boolean isOnPool) {
        final RelationField relationField = (RelationField) mapping.getField();
        return relationField.getType() == Type.AGGREGATION ? newAggregatedObjectInitializer(mapping, data, relationField, isOnPool)
                : newComposedObjectInitializer(
                        mapping, data, relationField, isOnPool);
    }

    private IPropertyInitializer newAggregatedObjectInitializer(final FieldToContractInputMapping mapping,
            final BusinessObjectData data,
            final RelationField relationField, final boolean isOnPool) {
        return relationField.isCollection() ?
                new MultipleBusinessObjectQueryInitializer(businessObject(mapping), relationField,
                        mapping.getContractInput(), toRefName(mapping, data), isOnPool)
                : new BusinessObjectQueryInitializer(firstMultipleParentBusinessObject(mapping), relationField,
                        mapping.getContractInput(),
                        toRefName(mapping, data));
    }

    private IPropertyInitializer newComposedObjectInitializer(final FieldToContractInputMapping mapping,
            final BusinessObjectData data,
            final RelationField relationField, final boolean isOnPool) {
        return relationField.isCollection() ?
                new NewBusinessObjectListInitializer(relationField, mapping.getContractInput(), toRefName(mapping, data), isOnPool) :
                new NewBusinessObjectInitializer(relationField, toRefName(mapping, data), mapping.getContractInput().eContainer() instanceof ContractInput);
    }

}
