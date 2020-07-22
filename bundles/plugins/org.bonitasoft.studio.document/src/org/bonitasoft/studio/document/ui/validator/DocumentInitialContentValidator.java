/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
import org.bonitasoft.studio.expression.editor.viewer.DefaultExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Florine Boudin
 */
public class DocumentInitialContentValidator extends DefaultExpressionValidator {

    private final int maxLength;
    private final Document document;

    public DocumentInitialContentValidator(final Document document, final int maxLength) {
        this.maxLength = maxLength;
        this.document = document;
    }

    @Override
    public IStatus validate(final Object value) {
        if (!document.isMultiple() && document.getDocumentType() == DocumentType.EXTERNAL) {
            return validateExternalSimpleDocument(document.getUrl());
        }
        return ValidationStatus.ok();
    }

    private IStatus validateExternalSimpleDocument(final Expression urlExpression) {
        if (urlExpression == null || !urlExpression.hasContent()) {
            return ValidationStatus.error(Messages.error_documentURLEmpty);
        }
        if (urlExpression.getContent().length() > maxLength) {
            return ValidationStatus.error(Messages.bind(Messages.error_documentURLTooLong, maxLength + 1));
        }
        return ValidationStatus.ok();
    }

}
