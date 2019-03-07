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
package org.bonitasoft.studio.contract.core.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class UnselectLazyReferencesInMultipleContainer {

    private Map<FieldToContractInputMapping, IStatus> statuses = new HashMap<FieldToContractInputMapping, IStatus>();

    public void apply(List<FieldToContractInputMapping> mappings, boolean multipleData) {
        statuses.clear();
        updateGeneratedMappings(mappings, multipleData);
    }

    private void updateGeneratedMappings(List<FieldToContractInputMapping> mappings, boolean multipleData) {
        for (FieldToContractInputMapping mapping : mappings) {
            if (mapping.getField() instanceof RelationField
                    && ((RelationField) mapping.getField()).isLazy()
                    && (multipleData || hasAMultipleParent(mapping))) {
                mapping.setGenerated(false);
                statuses.put(mapping,
                        ValidationStatus
                                .warning(String.format(Messages.lazyFieldInAMultipleParentRelationHasBeenDeselect,
                                        ((RelationField) mapping.getParent().getField()).getReference().getSimpleName(),
                                        mapping.getField().getName())));
                FieldToContractInputMapping parent = mapping.getParent();
                while (parent != null) {
                    statuses.put(parent,ValidationStatus.warning(Messages.aChildHasBeenUnselected));
                    parent = parent.getParent();
                }
                unselect(mapping.getChildren());
            }else {
                updateGeneratedMappings(mapping.getChildren(), multipleData);
            }
        }
    }

    private void unselect(List<FieldToContractInputMapping> children) {
        for (FieldToContractInputMapping mapping : children) {
            mapping.setGenerated(false);
            unselect(mapping.getChildren());
        }
    }

    private boolean hasAMultipleParent(FieldToContractInputMapping mapping) {
        FieldToContractInputMapping parent = mapping.getParent();
        while (parent != null && !parent.getField().isCollection()) {
            parent = parent.getParent();
        }
        return parent != null && parent.getField().isCollection();
    }

    public IStatus getStatus(FieldToContractInputMapping mapping) {
        return statuses.getOrDefault(mapping,Status.OK_STATUS);
    }

}
