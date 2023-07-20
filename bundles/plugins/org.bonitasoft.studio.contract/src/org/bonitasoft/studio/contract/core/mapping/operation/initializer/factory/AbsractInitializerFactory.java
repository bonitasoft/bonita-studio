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

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.VariableNameResolver;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.InitializerContext;
import org.bonitasoft.studio.model.process.Data;

public abstract class AbsractInitializerFactory implements InitializerFactory {

    protected BusinessObject firstMultipleParentBusinessObject(final FieldToContractInputMapping mapping) {
        FieldToContractInputMapping parentMapping = mapping.getParent();
        while (parentMapping != null && !(parentMapping.getField().isCollection()
                && parentMapping instanceof RelationFieldToContractInputMapping)) {
            parentMapping = parentMapping.getParent();
        }

        return parentMapping != null ? ((RelationField) parentMapping.getField()).getReference() : null;
    }

    protected BusinessObject businessObject(final FieldToContractInputMapping mapping) {
        FieldToContractInputMapping parentMapping = mapping;
        while (parentMapping != null && !(parentMapping.getField().isCollection()
                && parentMapping instanceof RelationFieldToContractInputMapping)) {
            parentMapping = parentMapping.getParent();
        }

        return parentMapping != null ? ((RelationField) parentMapping.getField()).getReference() : null;
    }

    protected InitializerContext createContext(final Data data,
            final VariableNameResolver resolver,
            final FieldToContractInputMapping mapping,
            final boolean createMode) {
        final InitializerContext context = new InitializerContext();
        final BusinessObject businessObject = ((RelationField) mapping.getField()).getReference();
        context.setMapping(mapping);
        context.setCheckExistence(!createMode);
        context.setCreateMode(createMode);
        context.setData(data);
        context.setContractInput(mapping.getContractInput());
        context.setLocalListVariableName(resolver.newListVarName(businessObject));
        context.setLocalVariableName(resolver.newVarName(businessObject));
        return context;
    }

}
