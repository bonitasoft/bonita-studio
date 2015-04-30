/**
 * Copyright (C) 2013-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.document;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Maxence Raoux
 */
public class DocumentNameValidator implements IValidator {

    private final EObject context;
    private final GroovyReferenceValidator groovyValidator;
    private String currentName = null;

    public DocumentNameValidator(final EObject context, final String currentName) {
        this.context = context;
        this.currentName = currentName;
        groovyValidator = new GroovyReferenceValidator(Messages.name);
    }

    @Override
    public IStatus validate(final Object value) {
        final IStatus groovyValidationStatus = groovyValidator.validate(value);
        if (!groovyValidationStatus.isOK()) {
            return groovyValidationStatus;
        }

        if (context != null) {
            final Pool pool = (Pool) ModelHelper.getParentProcess(context);
            for (final Document document : pool.getDocuments()) {
                final String documentName = document.getName();
                if (documentName.equals(value) && !documentName.equals(currentName)) {
                    return ValidationStatus.error(Messages.error_documentAllreadyexist);
                }
            }
        }

        return ValidationStatus.ok();
    }

}
