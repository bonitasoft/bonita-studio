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
package org.bonitasoft.studio.contract.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

public class FieldToContractInputMappingViewerCheckStateManagerTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_selectParentIfChildIsSelected() {
        final FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();

        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        rootMapping.addChild(childMapping);
        final CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));

        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, childMapping, true));

        assertThat(viewer.getChecked(rootMapping)).isTrue();
        assertThat(rootMapping.isGenerated()).isTrue();
    }

    @Test
    public void should_deselectParent_IfNoChildIsSelected() {
        final FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();

        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        rootMapping.addChild(childMapping);
        final CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));

        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, childMapping, false));

        assertThat(viewer.getChecked(rootMapping)).isFalse();
        assertThat(rootMapping.isGenerated()).isFalse();
    }

    @Test
    public void should_not_deselectParent_IfNoChildIsSelected() {
        final FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();

        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        rootMapping.addChild(childMapping1);
        rootMapping.addChild(childMapping2);
        final CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));
        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, childMapping2, true));
        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, childMapping1, false));

        assertThat(viewer.getChecked(rootMapping)).isTrue();
    }

    @Test
    public void should_regeneratedAttributes_whenReselectParent() {
        final FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();
        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        rootMapping.addChild(childMapping1);
        rootMapping.addChild(childMapping2);
        final CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));
        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, rootMapping, false));
        assertThat(rootMapping.isGenerated()).isFalse();
        assertThat(childMapping1.isGenerated()).isFalse();
        assertThat(childMapping2.isGenerated()).isFalse();
        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, rootMapping, true));
        assertThat(rootMapping.isGenerated()).isTrue();
        assertThat(childMapping1.isGenerated()).isTrue();
        assertThat(childMapping2.isGenerated()).isTrue();
    }

    @Test
    public void should_not_deselectParent_IfmappingHasNoParent() {
        final FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();

        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));

        final CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));
        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, rootMapping, false));
        assertThat(viewer.getChecked(rootMapping)).isFalse();
    }

    @Test
    public void should_manage_persistence_id_fields_generated() {

        BusinessObject rootBo = mock(BusinessObject.class);

        RelationField root = RelationFieldBuilder.aCompositionField("root", rootBo);
        SimpleField nameField = SimpleFieldBuilder.aStringField("name").build();
        SimpleField persistenceIdField = SimpleFieldBuilder.aStringField("persistenceId_string").build();

        RelationFieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(root);
        SimpleFieldToContractInputMapping rootNameMapping = new SimpleFieldToContractInputMapping(nameField);
        SimpleFieldToContractInputMapping rootPersistenceIdMapping = new SimpleFieldToContractInputMapping(
                persistenceIdField);
        rootMapping.addChild(rootNameMapping);
        rootMapping.addChild(rootPersistenceIdMapping);

        CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));
        FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();

        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, rootMapping, false));
        assertThat(viewer.getCheckedElements()).isEmpty();
        assertThat(Arrays.asList(rootMapping, rootNameMapping, rootPersistenceIdMapping))
                .allMatch(mapping -> !mapping.isGenerated());

        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, rootMapping, true));
        assertThat(viewer.getChecked(rootMapping)).isTrue();
        assertThat(checkedStateManager.isGrayed(rootMapping)).isFalse();
        assertThat(viewer.getChecked(rootNameMapping)).isTrue();
        assertThat(Arrays.asList(rootMapping, rootNameMapping, rootPersistenceIdMapping))
                .allMatch(FieldToContractInputMapping::isGenerated);

        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, rootNameMapping, false));
        assertThat(viewer.getChecked(rootMapping)).isTrue();
        assertThat(checkedStateManager.isGrayed(rootMapping)).isTrue();
        assertThat(rootMapping.isGenerated()).isTrue();
        assertThat(rootNameMapping.isGenerated()).isFalse();
        assertThat(rootPersistenceIdMapping.isGenerated()).isTrue();
    }

    private CheckboxTreeViewer aCheckBoxTreeViewer(List<FieldToContractInputMapping> input) {
        CheckboxTreeViewer viewer = new CheckboxTreeViewer(realmWithDisplay.createComposite());
        viewer.setFilters(hidePersistenceIdMapping());
        ObservableListTreeContentProvider provider = new ObservableListTreeContentProvider(
                new FieldToContractInputMappingObservableFactory(),
                new FieldToContractInputMappingTreeStructureAdvisor());
        viewer.setContentProvider(provider);
        viewer.setInput(input);
        return viewer;
    }

    private ViewerFilter hidePersistenceIdMapping() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return !Objects.equals(FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME,
                        ((FieldToContractInputMapping) element).getField().getName());
            }
        };
    }

}
