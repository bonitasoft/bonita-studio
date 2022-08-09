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

import java.util.Objects;

import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;

public class FieldToContractInputMappingViewerCheckStateManager implements ICheckStateListener, ICheckStateProvider {

    @Override
    public boolean isChecked(final Object element) {
        return ((FieldToContractInputMapping) element).isGenerated();
    }

    @Override
    public boolean isGrayed(Object element) {
        FieldToContractInputMapping mapping = (FieldToContractInputMapping) element;
        boolean isGrayed = hasAtLeastOneMappingGenerated(mapping) && !hasAllMappingGenerated(mapping);
        return isGrayed || mapping.getChildren().stream().anyMatch(this::isGrayed);
    }

    private boolean hasAllMappingGenerated(FieldToContractInputMapping mapping) {
        return mapping.getChildren().stream().allMatch(FieldToContractInputMapping::isGenerated);
    }

    private boolean hasAtLeastOneMappingGenerated(FieldToContractInputMapping mapping) {
        return mapping.getChildren().stream().anyMatch(FieldToContractInputMapping::isGenerated);
    }

    private boolean isPersistenceIdMapping(FieldToContractInputMapping mapping) {
        return Objects.equals(mapping.getField().getName(),
                FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME);
    }

    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {
        FieldToContractInputMapping mapping = (FieldToContractInputMapping) event.getElement();
        mapping.setGenerated(event.getChecked());
        setGeneratedStatePersistenceIdChild(mapping, event.getChecked());
        CheckboxTreeViewer checkboxTreeViewer = (CheckboxTreeViewer) event.getSource();
        checkboxTreeViewer.setSubtreeChecked(mapping, event.getChecked());
        setChildrenChecked(mapping, event.getChecked());
        selectParentIfChildIsSelected(event, mapping, checkboxTreeViewer);
        deselectParentIfNoChildSelected(event, mapping, checkboxTreeViewer);
        checkboxTreeViewer.getControl().getDisplay().asyncExec(() -> checkboxTreeViewer.refresh());
    }

    private void setGeneratedStatePersistenceIdChild(FieldToContractInputMapping parent, boolean generated) {
        parent.getChildren().stream()
                .filter(this::isPersistenceIdMapping)
                .findFirst()
                .ifPresent(m -> m.setGenerated(generated));
    }

    private void setChildrenChecked(final FieldToContractInputMapping parent, final boolean state) {
        for (final FieldToContractInputMapping mapping : parent.getChildren()) {
            if (!isPersistenceIdMapping(mapping)) {
                mapping.setGenerated(state);
                setChildrenChecked(mapping, state);
            } else {
                mapping.setGenerated(state);
            }
        }
    }

    private void deselectParentIfNoChildSelected(CheckStateChangedEvent event,
            FieldToContractInputMapping mapping,
            CheckboxTreeViewer checkboxTreeViewer) {
        FieldToContractInputMapping parentMapping = mapping.getParent();
        if (parentMapping != null && !event.getChecked() && hasNoChildGenerated(parentMapping)) {
            checkboxTreeViewer.setChecked(parentMapping, false);
            parentMapping.setGenerated(false);
            setGeneratedStatePersistenceIdChild(parentMapping, false);
            deselectParentIfNoChildSelected(event, parentMapping, checkboxTreeViewer);
        }
    }

    private boolean hasNoChildGenerated(FieldToContractInputMapping parentMapping) {
        return parentMapping.getChildren().stream().noneMatch(FieldToContractInputMapping::isGenerated);
    }

    private void selectParentIfChildIsSelected(CheckStateChangedEvent event, FieldToContractInputMapping mapping,
            CheckboxTreeViewer checkboxTreeViewer) {
        if (event.getChecked()) {
            final FieldToContractInputMapping parentMapping = mapping.getParent();
            if (parentMapping != null) {
                checkboxTreeViewer.setChecked(parentMapping, true);
                parentMapping.setGenerated(true);
                setGeneratedStatePersistenceIdChild(parentMapping, true);
                selectParentIfChildIsSelected(event, parentMapping, checkboxTreeViewer);
            }
        }
    }

}
