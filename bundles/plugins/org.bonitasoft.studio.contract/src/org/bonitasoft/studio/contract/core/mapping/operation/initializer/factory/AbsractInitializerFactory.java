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
import org.bonitasoft.studio.model.process.BusinessObjectData;

public abstract class AbsractInitializerFactory implements InitializerFactory {

    protected BusinessObject firstMultipleParentBusinessObject(final FieldToContractInputMapping mapping) {
        FieldToContractInputMapping parentMapping = mapping.getParent();
        while (parentMapping != null && !(parentMapping.getField().isCollection() && parentMapping instanceof RelationFieldToContractInputMapping)) {
            parentMapping = parentMapping.getParent();
        }

        return parentMapping != null ? ((RelationField) parentMapping.getField()).getReference() : null;
    }

    protected BusinessObject businessObject(final FieldToContractInputMapping mapping) {
        FieldToContractInputMapping parentMapping = mapping;
        while (parentMapping != null && !(parentMapping.getField().isCollection() && parentMapping instanceof RelationFieldToContractInputMapping)) {
            parentMapping = parentMapping.getParent();
        }

        return parentMapping != null ? ((RelationField) parentMapping.getField()).getReference() : null;
    }

    protected String toRefName(final FieldToContractInputMapping mapping, final BusinessObjectData data) {
        return mapping.getParent() != null ? toParentRefName(mapping.getParent()) + "." + mapping.getField().getName() : data.getName() + "."
                + mapping.getField().getName();
    }

    protected String toParentRefName(final FieldToContractInputMapping mapping) {
        String refName = mapping.getField().getName();
        FieldToContractInputMapping parent = mapping.getParent();
        while (parent != null) {
            refName = parent.getField().getName() + "." + refName;
            parent = parent.getParent();
        }
        return refName;
    }

}
