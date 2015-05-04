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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;

/**
 * @author aurelie
 */
public class FieldToContractInputMappingFactory {

    private final BusinessObjectModelRepositoryStore businessObjectStore;

    public FieldToContractInputMappingFactory(final BusinessObjectModelRepositoryStore businessObjectStore) {
        this.businessObjectStore = businessObjectStore;

    }

    public List<FieldToContractInputMapping> createMappingForBusinessObjectType(final String className) {
        final List<FieldToContractInputMapping> mappings = new ArrayList<FieldToContractInputMapping>();
        final BusinessObject businessObject = businessObjectStore.getBusinessObjectByQualifiedName(className);
        if (businessObject == null) {
            throw new IllegalArgumentException("Cannot find a BusinessObject with qualified name: " + className);
        }
        for (final Field field : businessObject.getFields()) {
            mappings.add(createFieldToContractInputMapping(field));
        }
        return mappings;
    }

    private FieldToContractInputMapping createFieldToContractInputMapping(final Field field) {
        if (field instanceof SimpleField) {
            return new SimpleFieldToContractInputMapping((SimpleField) field);
        } else if (field instanceof RelationField) {
            return createRelationFieldToContractInputMapping((RelationField) field);
        }
        throw new IllegalStateException("Unkwown Field type");
    }

    private FieldToContractInputMapping createRelationFieldToContractInputMapping(final RelationField field) {
        final RelationFieldToContractInputMapping relationFieldMapping = new RelationFieldToContractInputMapping(field);
        for (final Field child : field.getReference().getFields()) {
            relationFieldMapping.addChild(createFieldToContractInputMapping(child));
        }
        return relationFieldMapping;
    }

}
