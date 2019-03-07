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

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.VariableNameResolver;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.AggregationReferencePropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.CompositionReferencePropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.IPropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.InitializerContext;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.MultipleAggregationReferencePropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.MultipleCompositionReferencePropertyInitializer;
import org.bonitasoft.studio.model.process.BusinessObjectData;

public class RelationPropertyInitializerFactory extends AbsractInitializerFactory {

    private final VariableNameResolver variableNameResolver;

    public RelationPropertyInitializerFactory(final VariableNameResolver variableNameResolver) {
        this.variableNameResolver = variableNameResolver;
    }

    @Override
    public IPropertyInitializer newPropertyInitializer(FieldToContractInputMapping mapping,
            BusinessObjectData data, boolean isOnPool) {
        InitializerContext context = createContext(data, variableNameResolver, mapping, checkExistence(mapping, isOnPool),
                isOnPool);
        return context.getField().getType() == Type.AGGREGATION
                ? newAggregatedReferenceInitializer(context)
                : newComposedReferenceInitializer(context);
    }

    protected boolean checkExistence(FieldToContractInputMapping mapping, boolean isOnPool) {
        if (!isOnPool && mapping.getParent() != null) {
            Field parent = mapping.getParent().getField();
            boolean isComposition = ((RelationField) mapping.getField()).getType().equals(RelationField.Type.COMPOSITION);
            return parent.isCollection() && isComposition;
        }
        return false;
    }

    private IPropertyInitializer newAggregatedReferenceInitializer(InitializerContext context) {
        return context.getField().isCollection()
                ? new MultipleAggregationReferencePropertyInitializer(
                        firstMultipleParentBusinessObject(context.getMapping()),
                        businessObject(context.getMapping()),
                        context)
                : new AggregationReferencePropertyInitializer(firstMultipleParentBusinessObject(context.getMapping()),
                        context);
    }

    private IPropertyInitializer newComposedReferenceInitializer(InitializerContext context) {
        return context.getField().isCollection()
                ? new MultipleCompositionReferencePropertyInitializer(
                        firstMultipleParentBusinessObject(context.getMapping()), context)
                : new CompositionReferencePropertyInitializer(context);
    }

}
