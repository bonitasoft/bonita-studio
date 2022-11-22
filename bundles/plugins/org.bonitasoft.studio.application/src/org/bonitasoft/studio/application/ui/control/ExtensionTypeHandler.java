/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.application.validator.ActorFilterExtensionTypeValidator;
import org.bonitasoft.studio.application.validator.ConnectorExtensionTypeValidator;
import org.bonitasoft.studio.application.validator.RestApiExtensionExtensionTypeValidator;
import org.bonitasoft.studio.application.validator.ThemeExtensionTypeValidator;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;

public class ExtensionTypeHandler {

    private ArtifactType type;

    public ExtensionTypeHandler(ArtifactType type) {
        this.type = type;
    }

    public String getExtensionType() {
        switch (type) {
            case THEME:
            case REST_API:
                return "zip";
            default:
                return "jar";
        }
    }

    public Optional<String> getHintMessage() {
        switch (type) {
            case CONNECTOR:
            case ACTOR_FILTER:
                return Optional.of(Messages.legacyArchiveFormatHint);
            default:
                return Optional.empty();
        }
    }

    public IValidator<File> getExtensionValidator() {
        switch (type) {
            case CONNECTOR:
                return new ConnectorExtensionTypeValidator();
            case ACTOR_FILTER:
                return new ActorFilterExtensionTypeValidator();
            case REST_API:
                return new RestApiExtensionExtensionTypeValidator();
            case THEME:
                return new ThemeExtensionTypeValidator();
            default:
                return f -> ValidationStatus.ok();
        }
    }

    public String getExtensionLabel() {
        return Objects.equals(type, ArtifactType.OTHER)
                ? Messages.extension
                : type.getName();
    }

    public ArtifactType getType() {
        return type;
    }

}
