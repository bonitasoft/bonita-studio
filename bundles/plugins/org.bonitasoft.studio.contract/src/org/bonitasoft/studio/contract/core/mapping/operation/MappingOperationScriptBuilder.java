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
package org.bonitasoft.studio.contract.core.mapping.operation;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Objects;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.BusinessObjectInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.CompositionReferencePropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.IPropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.SimpleFieldPropertyInitializer;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;

public class MappingOperationScriptBuilder {

    private boolean needsDataDependency = false;
    private final BusinessObjectData data;
    private final FieldToContractInputMapping mapping;
    private final Field field;

    public MappingOperationScriptBuilder(final BusinessObjectData data,
            final FieldToContractInputMapping mapping,
            final Field field) {
        this.data = data;
        this.mapping = mapping;
        this.field = field;
    }

    public String toScript() throws BusinessObjectInstantiationException {
        return buildPropertyInitializerTree(mapping, field, data).getInitialValue();
    }

    private IPropertyInitializer buildPropertyInitializerTree(final FieldToContractInputMapping mapping, final Field parentField, final BusinessObjectData data) {
        final Field field = mapping.getField();
        final ContractInput input = mapping.getContractInput();
        if (field instanceof SimpleField) {
            return new SimpleFieldPropertyInitializer((SimpleField) field, input);
        }
        if (field instanceof RelationField) {
            final RelationField relationField = (RelationField) field;
            if (relationField.getType() == Type.COMPOSITION) {
                final BusinessObjectInitializer scriptInitializer = newRelationPropertyInitializer(mapping, parentField, data);
                for (final FieldToContractInputMapping child : mapping.getChildren()) {
                    scriptInitializer.addPropertyInitializer(buildPropertyInitializerTree(child, parentField, data));
                }
                needsDataDependency = true;
                return scriptInitializer;
            }
        }
        throw new UnsupportedOperationException(field.getClass().getName() + " type is not supported");
    }

    private BusinessObjectInitializer newRelationPropertyInitializer(final FieldToContractInputMapping mapping, final Field parentField,
            final BusinessObjectData data) {
        final RelationField field = (RelationField) mapping.getField();
        if (Objects.equals(field, parentField)) {
            return new BusinessObjectInitializer(field, data.getName() + "." + field.getName());
        }
        return new CompositionReferencePropertyInitializer(field, toRefName(mapping));

    }

    private String toRefName(final FieldToContractInputMapping mapping) {
        checkArgument(mapping.getParent() != null);
        return mapping.getParent().getField().getName() + "Var" + "." + mapping.getField().getName();
    }

    public boolean needsDataDependency() {
        return needsDataDependency;
    }

}
