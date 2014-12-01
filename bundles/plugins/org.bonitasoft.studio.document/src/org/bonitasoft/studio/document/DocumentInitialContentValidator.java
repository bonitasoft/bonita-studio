/**
 * Copyright (C) 2014 Bonitasoft S.A.
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

import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Florine Boudin
 */
public class DocumentInitialContentValidator implements IValidator {

    int maxLength;

    public DocumentInitialContentValidator(final int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public IStatus validate(final Object value) {
        final Document document = (Document) value;
        if (document.isMultiple()) {
            return ValidationStatus.ok();
        } else {
            return validateSimpleDocument(document);
        }
    }

    private IStatus validateSimpleDocument(final Document document) {
        final DocumentType documentType = document.getDocumentType();
        if (DocumentType.EXTERNAL.equals(documentType)){
            return validateExternalSimpleDocument(document);
        }
        return ValidationStatus.ok();
    }

    private IStatus validateExternalSimpleDocument(final Document document) {
        final Expression url = document.getUrl();
        if (url == null || url.getContent() == null || url.getContent().isEmpty()) {
            return ValidationStatus.error(Messages.error_documentURLEmpty);
        }

        if (url != null && url.getContent() != null
                && url.getContent().length() > maxLength) {
            return ValidationStatus.error(Messages.bind(Messages.error_documentURLTooLong, maxLength + 1));
        }

        return ValidationStatus.ok();
    }

}
