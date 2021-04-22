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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.junit.Test;

public class UngenerateAggregatedReferenceChildrenTest {

    @Test
    public void should_ungenerated_aggregated_reference_children() {
        List<FieldToContractInputMapping> mappings = new ArrayList<>();

        FieldToContractInputMapping root = new RelationFieldToContractInputMapping(null);
        root.setGenerated(true);

        FieldToContractInputMapping simpleChild = new SimpleFieldToContractInputMapping(mock(SimpleField.class));
        simpleChild.setGenerated(true);

        RelationField aggregatedField = mock(RelationField.class);
        when(aggregatedField.getType()).thenReturn(Type.AGGREGATION);
        FieldToContractInputMapping aggregatedChild = new RelationFieldToContractInputMapping(aggregatedField);
        aggregatedChild.setGenerated(true);

        SimpleField persistenceIdField = mock(SimpleField.class);
        when(persistenceIdField.getName()).thenReturn(FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME);
        FieldToContractInputMapping persistenceIdChild = new SimpleFieldToContractInputMapping(persistenceIdField);
        persistenceIdChild.setGenerated(true);

        FieldToContractInputMapping simpleChild2 = new SimpleFieldToContractInputMapping(mock(SimpleField.class));
        simpleChild2.setGenerated(true);

        RelationField composedField = mock(RelationField.class);
        when(composedField.getType()).thenReturn(Type.COMPOSITION);
        FieldToContractInputMapping composedGrandChild = new RelationFieldToContractInputMapping(composedField);
        composedGrandChild.setGenerated(true);

        FieldToContractInputMapping simpleChild3 = new SimpleFieldToContractInputMapping(mock(SimpleField.class));
        simpleChild3.setGenerated(true);

        composedGrandChild.addChild(simpleChild3);
        aggregatedChild.getChildren().addAll(Arrays.asList(persistenceIdChild, simpleChild2, composedGrandChild));
        root.getChildren().addAll(Arrays.asList(simpleChild, aggregatedChild));
        mappings.add(root);

        new UngenerateAggregatedReferenceChildren().apply(mappings);

        assertThat(root.isGenerated()).isTrue();
        assertThat(simpleChild.isGenerated()).isTrue();
        assertThat(aggregatedChild.isGenerated()).isTrue();
        assertThat(persistenceIdChild.isGenerated()).isTrue();

        assertThat(simpleChild2.isGenerated()).isFalse();
        assertThat(composedGrandChild.isGenerated()).isFalse();
        assertThat(simpleChild3.isGenerated()).isFalse();
    }

}
