/**
 * Copyright (C) 2014-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import org.bonitasoft.studio.contract.i18n.Messages;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Strings;

/**
 * @author Romain Bioteau
 */
public class ConstraintExpressionEditorValidator extends MultiValidator {

    private final IObservableValue expressionObservable;
    private final IObservableList dependenciesObservable;

    public ConstraintExpressionEditorValidator(final IObservableValue expressionObservable,
            final IObservableList dependenciesObservable) {
        this.expressionObservable = expressionObservable;
        this.dependenciesObservable = dependenciesObservable;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.MultiValidator#validate()
     */
    @Override
    protected IStatus validate() {
        final String text = (String) expressionObservable.getValue();
        if (Strings.isNullOrEmpty(text)) {
            return ValidationStatus.error(Messages.emptyExpressionContent);
        }
        if (dependenciesObservable.isEmpty()) {
            return ValidationStatus.warning(Messages.noContractInputReferencedInExpression);
        }
        return ValidationStatus.ok();
    }

}
