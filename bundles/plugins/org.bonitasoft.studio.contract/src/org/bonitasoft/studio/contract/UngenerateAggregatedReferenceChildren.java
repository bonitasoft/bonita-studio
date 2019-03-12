/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;

public class UngenerateAggregatedReferenceChildren {

    public void apply(List<FieldToContractInputMapping> mappings) {
        ungenerateAggregateReferencesChildren(mappings);
    }

    private void ungenerateAggregateReferencesChildren(List<FieldToContractInputMapping> mappings) {
        for (FieldToContractInputMapping mapping : mappings) {
            if (mapping.getField() instanceof RelationField
                    && ((RelationField) mapping.getField()).getType() == Type.AGGREGATION) {
                mapping.getChildren()
                        .stream()
                        .filter(childMapping -> !Objects.equals(childMapping.getField().getName(),
                                FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME))
                        .forEach(childMapping -> childMapping.setGenerated(false));
            }
            ungenerateAggregateReferencesChildren(mapping.getChildren());
        }
    }

}
