/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.document.ui.validator;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Resource;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Strings;

public class AdditionalResourceProjectPathValidator implements IValidator<Resource> {

    protected RepositoryAccessor repositoryAccessor;

    public AdditionalResourceProjectPathValidator() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }

    @Override
    public IStatus validate(Resource resource) {
        String projectPath = resource.getProjectPath();
        if (Strings.isNullOrEmpty(projectPath)) {
            return ValidationStatus.error(org.bonitasoft.studio.ui.i18n.Messages.required);
        }
        return repositoryAccessor.getCurrentRepository().getProject().getFile(projectPath).exists()
                ? ValidationStatus.ok()
                : ValidationStatus.error(Messages.unknownResource);
    }

}
