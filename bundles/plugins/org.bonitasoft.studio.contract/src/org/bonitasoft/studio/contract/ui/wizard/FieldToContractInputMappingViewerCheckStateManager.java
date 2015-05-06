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

import static com.google.common.collect.Iterables.any;

import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * @author aurelie
 */
public class FieldToContractInputMappingViewerCheckStateManager implements ICheckStateListener, ICheckStateProvider {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICheckStateProvider#isChecked(java.lang.Object)
     */
    @Override
    public boolean isChecked(final Object element) {
        return ((FieldToContractInputMapping) element).isGenerated();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICheckStateProvider#isGrayed(java.lang.Object)
     */
    @Override
    public boolean isGrayed(final Object element) {
        final FieldToContractInputMapping mapping = (FieldToContractInputMapping) element;
        return any(mapping.getChildren(), isGenerated()) && !Iterables.all(mapping.getChildren(), isGenerated());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
     */
    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {
        final FieldToContractInputMapping mapping = (FieldToContractInputMapping) event.getElement();
        mapping.setGenerated(event.getChecked());
        final CheckboxTreeViewer checkboxTreeViewer = (CheckboxTreeViewer) event.getSource();
        checkboxTreeViewer.setSubtreeChecked(mapping, event.getChecked());
        selectParentIfChildIsSelected(event, mapping, checkboxTreeViewer);
        deselectParentIfNoChildSelected(event, mapping, checkboxTreeViewer);
        // checkboxTreeViewer.setGrayChecked(mapping, isGrayed(mapping));

    }

    /**
     * @param event
     * @param mapping
     * @param checkboxTreeViewer
     */
    private void deselectParentIfNoChildSelected(final CheckStateChangedEvent event, final FieldToContractInputMapping mapping,
            final CheckboxTreeViewer checkboxTreeViewer) {
        if (!event.getChecked() && mapping.getParent() != null) {
            boolean deselect = true;
            for (final FieldToContractInputMapping m : mapping.getParent().getChildren()) {
                if (checkboxTreeViewer.getChecked(m)) {
                    deselect = false;
                }
            }
            // ALL CHILD ARE UNCHECKED
            if (deselect) {
                checkboxTreeViewer.setChecked(mapping.getParent(), false);
            }
        }
    }

    /**
     * @param event
     * @param mapping
     * @param checkboxTreeViewer
     */
    private void selectParentIfChildIsSelected(final CheckStateChangedEvent event, final FieldToContractInputMapping mapping,
            final CheckboxTreeViewer checkboxTreeViewer) {
        if (event.getChecked()) {
            if (mapping.getParent() != null) {
                checkboxTreeViewer.setChecked(mapping.getParent(), true);
            }
        }
    }

    /**
     * @return
     */
    private Predicate<FieldToContractInputMapping> isGenerated() {

        return new Predicate<FieldToContractInputMapping>() {

            /*
             * (non-Javadoc)
             * @see com.google.common.base.Predicate#apply(java.lang.Object)
             */
            @Override
            public boolean apply(final FieldToContractInputMapping input) {
                return input.isGenerated();
            }
        };
    }

}
