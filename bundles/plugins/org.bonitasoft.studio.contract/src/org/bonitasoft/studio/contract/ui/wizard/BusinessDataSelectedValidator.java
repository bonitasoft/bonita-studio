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

import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public final class BusinessDataSelectedValidator extends MultiValidator {

    private final List<Data> availableBusinessData;
    private final WritableValue selectedDataObservable;

    public BusinessDataSelectedValidator(final List<Data> availableBusinessData, final WritableValue selectedDataObservable) {
        this.availableBusinessData = availableBusinessData;
        this.selectedDataObservable = selectedDataObservable;
    }

    @Override
    protected IStatus validate() {
        if (selectedDataObservable.getValue() != null) {
            return Status.OK_STATUS;
        } else {
            if (availableBusinessData.isEmpty()) {
                return ValidationStatus.warning(Messages.warningAddFromData_noDataAvailable);
            } else {
                return ValidationStatus.warning(Messages.warningAddFromData_noDataSelected);
            }
        }
    }
}