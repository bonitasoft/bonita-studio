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

import org.bonitasoft.studio.model.process.Document;
import org.eclipse.core.databinding.observable.value.IObservableValue;

public class MultipleContractInputValidator extends ContractInputValidator {

    public MultipleContractInputValidator(final Document document, final IObservableValue selectionObservable, final IObservableValue viewerInputObservable) {
        super(document, selectionObservable, viewerInputObservable);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.document.ui.validator.ContractInputValidator#shouldValidate(boolean)
     */
    @Override
    protected boolean shouldValidate(final boolean isMultiple) {
        return isMultiple;
    }

}
