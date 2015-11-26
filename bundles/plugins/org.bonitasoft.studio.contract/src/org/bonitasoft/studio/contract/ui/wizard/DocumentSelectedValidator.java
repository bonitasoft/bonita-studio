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
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author aurelie
 */
public class DocumentSelectedValidator extends MultiValidator {

    private final WritableValue selectedDataObservable;
    private final List<Document> availableDocuments;
    private final SelectObservableValue selectionTypeObservable;

    public DocumentSelectedValidator(final WritableValue selectedDataObservable, final SelectObservableValue selectionTypeObservable,
            final List<Document> availableDocuments) {
        this.selectedDataObservable = selectedDataObservable;
        this.availableDocuments = availableDocuments;
        this.selectionTypeObservable = selectionTypeObservable;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.MultiValidator#validate()
     */
    @Override
    protected IStatus validate() {
        final Object selectedData = selectedDataObservable.getValue();
        if (selectedData != null && selectedData instanceof Document) {
            return selectedData != null ? Status.OK_STATUS : ValidationStatus.error(Messages.invalidBusinessDataSelected);
        } else {
            if (availableDocuments.isEmpty()) {
                return selectionTypeObservable.getValue().equals(Boolean.FALSE) ? ValidationStatus.warning(Messages.warningAddFromData_noDocumentAvailable)
                        : Status.OK_STATUS;
            } else {
                return selectionTypeObservable.getValue().equals(Boolean.FALSE) ? ValidationStatus.warning(Messages.warningAddFromData_noDocumentSelected)
                        : Status.OK_STATUS;
            }
        }
    }
}
