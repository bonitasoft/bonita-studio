/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.PathTemplate;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.osgi.util.NLS;

public class UniquePathTemplateValidator implements IValidator {

    private final RestAPIExtensionRepositoryStore repositoryStore;

    public UniquePathTemplateValidator(final RestAPIExtensionRepositoryStore repositoryStore) {
        this.repositoryStore = repositoryStore;
    }

    @Override
    public IStatus validate(final Object value) {
        if (value instanceof String) {
            return doValidatePathTemplate((String) value);
        }
        if (value instanceof RestAPIExtensionFileStore) {
            return doValidatePathTemplate((RestAPIExtensionFileStore) value);
        }
        return null;
    }
    
    protected List<PathTemplate> pathTemplates(RestAPIExtensionFileStore fileStore) {
        try {
            return fileStore.getContent().getPathTemplates();
        } catch (ReadFileStoreException e) {
            return Collections.emptyList();
        }
    }

    protected IStatus doValidatePathTemplate(final String pathTemplate) {
        final StringBuilder sb = new StringBuilder("[");
        for (final RestAPIExtensionFileStore fileStore : repositoryStore.getChildren()) {
            if (pathTemplates(fileStore).contains(new PathTemplate(pathTemplate, "GET"))) {
                sb.append(fileStore.getDisplayName());
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        if (sb.length() > 1) {
            return ValidationStatus
                    .error(NLS.bind(Messages.pathTemplateAlreadyExists, pathTemplate, sb.toString()));
        }
        return ValidationStatus.ok();
    }

    protected MultiStatus doValidatePathTemplate(final RestAPIExtensionFileStore fileStore) {
        final MultiStatus status = new MultiStatus(RestAPIExtensionActivator.PLUGIN_ID, IStatus.OK, "", null);
        final List<PathTemplate> templates = pathTemplates(fileStore);
        final Set<PathTemplate> duplicates = findDuplicates(templates);
        for (final PathTemplate duplicatedTemplate : duplicates) {
            status.add(new PathTemplateErrorStatus(NLS.bind(Messages.pathTemplateAlreadyExists, duplicatedTemplate.getPath(), fileStore.getDisplayName()),
                    duplicatedTemplate));
        }
        for (final PathTemplate t : templates) {
            final StringBuilder sb = new StringBuilder("[");
            for (final RestAPIExtensionFileStore f : repositoryStore.getChildren()) {
                if (!Objects.equals(fileStore.getName(), f.getName())) {
                    if (pathTemplates(f).contains(t)) {
                        sb.append(f.getDisplayName());
                        sb.append(",");
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            if (sb.length() > 1) {
                status.add(new PathTemplateErrorStatus(NLS.bind(Messages.pathTemplateAlreadyExists, t.getPath(), sb.toString()), t));
            }
        }
        return status;
    }

    private Set<PathTemplate> findDuplicates(final List<PathTemplate> pathTemplates) {
        final Set<PathTemplate> duplicated = new HashSet<>();
        final Set<PathTemplate> current = new HashSet<>();

        for (final PathTemplate pt : pathTemplates) {
            if (current.contains(pt)) {
                duplicated.add(pt);
            }
            current.add(pt);
        }
        return duplicated;
    }

}
