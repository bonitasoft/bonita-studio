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
package org.bonitasoft.studio.common;

import java.util.Set;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ProcessUnicityValidator extends MultiValidator {

    private final IObservableValue nameObservable;
    private final IObservableValue versionObservable;
    private final Set<Identifier> processeIdentifiers;
    private final AbstractProcess abstractProcess;

    public ProcessUnicityValidator(final AbstractProcess abstractProcess, final IObservableValue nameObservable, final IObservableValue versionObservable,
            final Set<Identifier> processeIdentifiers) {
        this.abstractProcess = abstractProcess;
        this.nameObservable = nameObservable;
        this.versionObservable = versionObservable;
        this.processeIdentifiers = processeIdentifiers;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.MultiValidator#validate()
     */
    @Override
    protected IStatus validate() {
        final String name = (String) nameObservable.getValue();
        final String version = (String) versionObservable.getValue();
        final Identifier newIdentifier = new Identifier(name, version);
        if (name.equals(abstractProcess.getName()) && version.equals(abstractProcess.getVersion())) {
            return ValidationStatus.ok();
        }
        if (processeIdentifiers.contains(newIdentifier)) {
            return ValidationStatus.error(Messages.bind(Messages.differentCaseSameNameError, Messages.Pool_title.toLowerCase()));
        }
        return ValidationStatus.ok();
    }

}
