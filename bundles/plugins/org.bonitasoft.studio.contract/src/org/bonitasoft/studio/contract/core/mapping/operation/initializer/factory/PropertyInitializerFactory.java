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
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.IPropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.SimpleFieldPropertyInitializer;
import org.bonitasoft.studio.model.process.BusinessObjectData;

public class PropertyInitializerFactory extends AbsractInitializerFactory {

    private RelationPropertyInitializerFactory relationPropertyInitializerFactory;

    public PropertyInitializerFactory(RelationPropertyInitializerFactory relationPropertyInitializerFactory) {
        this.relationPropertyInitializerFactory = relationPropertyInitializerFactory;
    }

    @Override
    public IPropertyInitializer newPropertyInitializer(FieldToContractInputMapping mapping,
            BusinessObjectData data, boolean isOnPool) {
        Field field = mapping.getField();
        if (field instanceof SimpleField) {
            return new SimpleFieldPropertyInitializer(firstMultipleParentBusinessObject(mapping), (SimpleField) field,
                    mapping.getContractInput());
        } else if (field instanceof RelationField) {
            return relationPropertyInitializerFactory.newPropertyInitializer(mapping, data, isOnPool);
        }
        throw new UnsupportedOperationException(field.getClass().getName() + " is not supported");
    }

}
