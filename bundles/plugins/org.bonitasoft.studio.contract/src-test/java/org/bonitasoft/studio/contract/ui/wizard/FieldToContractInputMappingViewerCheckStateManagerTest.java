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

import java.util.List;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

/**
 * @author aurelie
 */
public class FieldToContractInputMappingViewerCheckStateManagerTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_selectParentIfChildIsSelected() {
        final FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();

        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        rootMapping.addChild(childMapping);
        final CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));

        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, childMapping, true));

        assertThat(viewer.getChecked(rootMapping)).isTrue();
        assertThat(rootMapping.isGenerated()).isTrue();
    }

    @Test
    public void should_deselectParent_IfNoChildIsSelected() {
        final FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();

        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        rootMapping.addChild(childMapping);
        final CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));

        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, childMapping, false));

        assertThat(viewer.getChecked(rootMapping)).isFalse();
        assertThat(rootMapping.isGenerated()).isFalse();
    }

    @Test
    public void should_not_deselectParent_IfNoChildIsSelected() {
        final FieldToContractInputMappingViewerCheckStateManager checkedStateManager = new FieldToContractInputMappingViewerCheckStateManager();

        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
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
        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
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

        final FieldToContractInputMapping rootMapping = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));

        final CheckboxTreeViewer viewer = aCheckBoxTreeViewer(Lists.newArrayList(rootMapping));
        checkedStateManager.checkStateChanged(new CheckStateChangedEvent(viewer, rootMapping, false));
        assertThat(viewer.getChecked(rootMapping)).isFalse();
    }

    private CheckboxTreeViewer aCheckBoxTreeViewer(final List<FieldToContractInputMapping> input) {
        final CheckboxTreeViewer viewer = new CheckboxTreeViewer(realmWithDisplay.createComposite());
        viewer.setContentProvider(new ObservableListTreeContentProvider(new FieldToContractInputMappingObservableFactory(),
                new FieldToContractInputMappingTreeStructureAdvisor()));
        viewer.setInput(input);
        return viewer;
    }

}
