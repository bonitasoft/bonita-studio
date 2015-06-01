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
package org.bonitasoft.studio.document.ui.validator;

import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFObservables;

public abstract class ContractInputValidator extends MultiValidator {

    private final Document document;
    private final IObservableValue selectionObservable;
    private final IObservableValue viewerInputObservable;

    public ContractInputValidator(final Document document, final IObservableValue selectionObservable, final IObservableValue viewerInputObservable) {
        this.document = document;
        this.selectionObservable = selectionObservable;
        this.viewerInputObservable = viewerInputObservable;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.MultiValidator#validate()
     */
    @Override
    protected IStatus validate() {
        //Trigger validation when input is updated
        viewerInputObservable.getValue();
        //Trigger validation when multiple is updated
        final boolean isMultiple = (boolean) EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__MULTIPLE).getValue();

        final Object type = EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__DOCUMENT_TYPE).getValue();
        if (shouldValidate(isMultiple) && selectionObservable.getValue() == null && type == DocumentType.CONTRACT) {
            return ValidationStatus.error(Messages.contractInputNotSet);
        }
        return ValidationStatus.ok();
    }

    protected abstract boolean shouldValidate(final boolean isMultiple);
}
