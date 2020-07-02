/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.model.validator;

import static org.bonitasoft.studio.common.model.NamespaceUtil.namespaceVersion;

import java.util.Collection;
import java.util.Collections;

import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.model.NamespaceUtil;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ModelNamespaceValidator implements IValidator<String> {

    private String currentNamespace;
    private String incompatibilityErrorMessage;
    private Collection<String> legacyNamespaces;

    public ModelNamespaceValidator(String currentNamespace, String incompatibilityErrorMessage,
            Collection<String> legacyNamespaces) {
        this.currentNamespace = currentNamespace;
        this.incompatibilityErrorMessage = incompatibilityErrorMessage;
        this.legacyNamespaces = legacyNamespaces;
    }

    public ModelNamespaceValidator(String currentNamespace, String incompatibilityErrorMessage) {
        this(currentNamespace, incompatibilityErrorMessage, Collections.emptyList());
    }

    @Override
    public IStatus validate(String namespace) {
        if (namespace == null || namespace.isEmpty()) {
            // No namespace in the imported artifact -> A namespace will be added during migration (BDM specificity).
            return ValidationStatus.warning(Messages.migrationWillBreakRetroCompatibility);
        }
        if (namespace.endsWith("/")) {
            namespace = namespace.substring(0, namespace.length() - 1);
        }
        if (legacyNamespaces.contains(namespace)) {
            return ValidationStatus.warning(Messages.migrationWillBreakRetroCompatibility);
        }
        if (!namespace.startsWith(NamespaceUtil.namespaceRoot(currentNamespace) + "/")) {
            return ValidationStatus.error(incompatibilityErrorMessage);
        }
        String version = namespaceVersion(namespace);
        if (version == null) {
            return ValidationStatus.error(incompatibilityErrorMessage);
        }
        ArtifactVersion currentVersion = new DefaultArtifactVersion(namespaceVersion(currentNamespace));
        ArtifactVersion namespaceVersion = new DefaultArtifactVersion(version);
        int compare = currentVersion.compareTo(namespaceVersion);
        switch (compare) {
            case 0:
                return ValidationStatus.ok();
            case 1:
                return ValidationStatus.warning(Messages.migrationWillBreakRetroCompatibility);
            case -1:
            default:
                return ValidationStatus.error(incompatibilityErrorMessage);
        }
    }

}
