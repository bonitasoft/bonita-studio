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

import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public final class BusinessDataSelectedValidator extends MultiValidator {

    private final List<Data> availableBusinessData;
    private final WritableValue selectedDataObservable;
    private final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectStore;
    private final SelectObservableValue selectionTypeObservable;

    public BusinessDataSelectedValidator(final List<Data> availableBusinessData, final WritableValue selectedDataObservable,
            final SelectObservableValue selectionTypeObservable,
            final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectStore) {
        this.availableBusinessData = availableBusinessData;
        this.selectedDataObservable = selectedDataObservable;
        this.selectionTypeObservable = selectionTypeObservable;
        this.businessObjectStore = businessObjectStore;
    }

    @Override
    protected IStatus validate() {
        final Object selectedData = selectedDataObservable.getValue();
        if (selectedData != null && selectedData instanceof BusinessObjectData) {
            final BusinessObjectData value = (BusinessObjectData) selectedData;
            return toBusinessObject(value).isPresent() ? Status.OK_STATUS
                    : ValidationStatus.error(Messages.invalidBusinessDataSelected);
        } else {
            if (availableBusinessData.isEmpty()) {
                return Boolean.TRUE.equals(selectionTypeObservable.getValue())
                        ? ValidationStatus.warning(Messages.warningAddFromData_noDataAvailable)
                        : Status.OK_STATUS;
            } else {
                return Boolean.TRUE.equals(selectionTypeObservable.getValue())
                        ? ValidationStatus.warning(Messages.warningAddFromData_noDataSelected)
                        : Status.OK_STATUS;
            }
        }
    }

    private Optional<BusinessObject> toBusinessObject(final BusinessObjectData selectedData) {
        return businessObjectStore.getBusinessObjectByQualifiedName(selectedData.getClassName());
    }
}
