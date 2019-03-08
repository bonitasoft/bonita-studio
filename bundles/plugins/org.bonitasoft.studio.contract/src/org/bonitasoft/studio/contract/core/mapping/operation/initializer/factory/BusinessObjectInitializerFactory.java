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
import org.bonitasoft.studio.contract.core.mapping.operation.VariableNameResolver;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.BusinessObjectQueryInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.IPropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.InitializerContext;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.MultipleAggregationBusinessObjectQueryInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.MultipleCompositionBusinessObjectQueryInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.NewBusinessObjectInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.NewBusinessObjectListInitializer;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;

public class BusinessObjectInitializerFactory extends AbsractInitializerFactory {

    private VariableNameResolver variableNameResolver;

    public BusinessObjectInitializerFactory(VariableNameResolver variableNameResolver) {
        this.variableNameResolver = variableNameResolver;
    }

    @Override
    public IPropertyInitializer newPropertyInitializer(FieldToContractInputMapping mapping, BusinessObjectData data,
            boolean isOnPool) {
        RelationField relationField = (RelationField) mapping.getField();
        InitializerContext context = createContext(data, variableNameResolver, mapping, isOnPool);
        return relationField.getType() == Type.AGGREGATION
                ? newAggregatedObjectInitializer(context)
                : newComposedObjectInitializer(context);
    }

    private IPropertyInitializer newAggregatedObjectInitializer(InitializerContext context) {
        return context.getField().isCollection()
                ? new MultipleAggregationBusinessObjectQueryInitializer(businessObject(context.getMapping()), context)
                : new BusinessObjectQueryInitializer(firstMultipleParentBusinessObject(context.getMapping()), context);
    }

    private IPropertyInitializer newComposedObjectInitializer(InitializerContext context) {
        if (context.getField().isCollection()) {
            return context.isOnPool()
                    ? new NewBusinessObjectListInitializer(context)
                    : new MultipleCompositionBusinessObjectQueryInitializer(businessObject(context.getMapping()), context);
        }
        context.setCheckExistence(context.getMapping().getContractInput().eContainer() instanceof ContractInput);
        return new NewBusinessObjectInitializer(context);
    }
}
