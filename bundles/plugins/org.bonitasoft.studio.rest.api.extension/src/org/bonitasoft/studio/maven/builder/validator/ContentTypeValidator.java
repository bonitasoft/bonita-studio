/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven.builder.validator;

import java.util.Objects;
import java.util.Properties;

import org.bonitasoft.studio.common.extension.properties.PagePropertyConstants;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ContentTypeValidator implements PagePropertyValidator, PagePropertyConstants {

    private String contentType;

    public ContentTypeValidator(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public IStatus validate(Properties properties, LocationResolver locationResolver) {
        if (properties.containsKey(PagePropertyConstants.CONTENT_TYPE)) {
            String type = (String) properties.get(PagePropertyConstants.CONTENT_TYPE);
            if (!Objects.equals(type, contentType)) {
                return new StatusWithLocation(String.format(Messages.invalidContentType, contentType),
                        locationResolver.getLineLocation(PagePropertyConstants.CONTENT_TYPE));
            }
        }
        return ValidationStatus.ok();
    }

}
