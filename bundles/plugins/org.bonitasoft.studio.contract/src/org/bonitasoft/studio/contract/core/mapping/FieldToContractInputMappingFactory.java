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
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Task;

public class FieldToContractInputMappingFactory {

    private static final int MAX_DEPTH = 5;

    public static final String PERSISTENCE_ID_STRING_FIELD_NAME = "persistenceId_string";

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectRepositoryStore;

    public FieldToContractInputMappingFactory(BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store) {
        this.businessObjectRepositoryStore = store;
    }

    public List<FieldToContractInputMapping> createMappingForBusinessObjectType(ContractContainer contractContainer,
            BusinessObjectData data) {
        List<FieldToContractInputMapping> mappings = new ArrayList<>();
        toBusinessObject(data).ifPresent(businessObject -> businessObject.getFields().stream()
                .map(field -> createFieldToContractInputMapping(contractContainer, field, MAX_DEPTH))
                .forEach(mappings::add));
        if (data.isMultiple() && contractContainer instanceof Task) {
            mappings.add(createPersistenceIdContractInputMapping());
        }
        return mappings;
    }

    private FieldToContractInputMapping createFieldToContractInputMapping(ContractContainer contractContainer,
            final Field field, final int depth) {
        if (field instanceof SimpleField) {
            return new SimpleFieldToContractInputMapping((SimpleField) field);
        } else if (field instanceof RelationField) {
            return createRelationFieldToContractInputMapping(contractContainer, (RelationField) field, depth);
        }
        throw new IllegalStateException("Unkwown Field type");
    }

    private FieldToContractInputMapping createRelationFieldToContractInputMapping(ContractContainer contractContainer,
            RelationField field, int depth) {
        RelationFieldToContractInputMapping relationFieldMapping = new RelationFieldToContractInputMapping(field);
        if (shouldAddPersistenceIdContractInputMapping(contractContainer, relationFieldMapping, field.isCollection())) {
            relationFieldMapping.addChild(createPersistenceIdContractInputMapping());
        }
        if (depth > 0) {
            depth--;
            for (Field child : field.getReference().getFields()) {
                relationFieldMapping.addChild(createFieldToContractInputMapping(contractContainer, child, depth));
            }
        }
        return relationFieldMapping;
    }

    public boolean shouldAddPersistenceIdContractInputMapping(ContractContainer contractContainer,
            RelationFieldToContractInputMapping relationFieldMapping, boolean isCollection) {
        return isAggregation(relationFieldMapping) || (contractContainer instanceof Task && isCollection);
    }

    private boolean isAggregation(RelationFieldToContractInputMapping relationFieldMapping) {
        return Objects.equals(((RelationField) relationFieldMapping.getField()).getType(),
                RelationField.Type.AGGREGATION);
    }

    private FieldToContractInputMapping createPersistenceIdContractInputMapping() {
        SimpleField persistenceId = new SimpleField();
        persistenceId.setName(PERSISTENCE_ID_STRING_FIELD_NAME);
        persistenceId.setType(FieldType.STRING);
        return new SimpleFieldToContractInputMapping(persistenceId);
    }

    private Optional<BusinessObject> toBusinessObject(BusinessObjectData data) {
        return businessObjectRepositoryStore.getBusinessObjectByQualifiedName(data.getClassName());
    }

}
