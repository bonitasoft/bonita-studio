/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer;

import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;

import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;

import com.google.common.base.Joiner;

public class MultipleCompositionBusinessObjectQueryInitializer extends MultipleAggregationBusinessObjectQueryInitializer {

    public MultipleCompositionBusinessObjectQueryInitializer(BusinessObject businessObject, InitializerContext context) {
        super(businessObject, context);
    }

    @Override
    protected void constructor(StringBuilder scriptBuilder, BusinessObject businessObject) {
        SimpleField peristenceIdField = new SimpleField();
        peristenceIdField.setType(FieldType.STRING);
        peristenceIdField.setName(FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME);
        SimpleFieldPropertyInitializer persistenceIdInitializer = new SimpleFieldPropertyInitializer(businessObject,
                peristenceIdField, persistenceIdInput);
        scriptBuilder.append(listFromData());
        scriptBuilder.append(".find { ");
        scriptBuilder.append("it.persistenceId.toString() == ");
        scriptBuilder.append(persistenceIdInitializer.getInitialValue());
        scriptBuilder.append(String.format("} ?: new %s()", businessObject.getQualifiedName()));
    }

    private String listFromData() {
        List<String> ancestorList = toAncestorNameList().apply(contractInput);
        if (ancestorList.isEmpty()) {
            throw new IllegalStateException(String.format("The ancestor list of the contract input %s should not be empty",
                    contractInput.getName()));
        }
        ancestorList.set(0, context.getData().getName());
        return Joiner.on(".").join(ancestorList);
    }

}
