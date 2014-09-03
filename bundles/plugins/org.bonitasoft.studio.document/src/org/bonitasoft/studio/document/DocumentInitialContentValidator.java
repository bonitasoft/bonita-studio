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
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Florine Boudin
 */
public class DocumentInitialContentValidator implements IValidator {

    int maxLenght;

    public DocumentInitialContentValidator(final int maxLenght) {
        this.maxLenght = maxLenght;
    }

    @Override
    public IStatus validate(final Object value) {

        final Document document = (Document) value;

        if (document.getDocumentType().equals(DocumentType.EXTERNAL) && (document.getUrl() == null || document.getUrl().getContent().isEmpty())) {
            return ValidationStatus.error(Messages.error_documentURLEmpty);
        }

        if (document.getDocumentType().equals(DocumentType.INTERNAL)
                && (document.getDefaultValueIdOfDocumentStore() == null || document.getDefaultValueIdOfDocumentStore().isEmpty())) {
            return ValidationStatus.error(Messages.error_documentDefaultIDEmpty);
        }

        if (document.getUrl() != null && document.getUrl().getContent().length() > maxLenght) {
            return ValidationStatus.error(Messages.bind(Messages.error_documentURLTooLong, maxLenght));
        }

        return ValidationStatus.ok();

    }


}
