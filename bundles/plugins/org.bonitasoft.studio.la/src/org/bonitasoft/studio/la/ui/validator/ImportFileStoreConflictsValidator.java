/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.ui.validator;

import java.io.File;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.validator.TypedValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ImportFileStoreConflictsValidator extends TypedValidator<String, IStatus> {

    private final AbstractRepositoryStore<? extends IRepositoryFileStore> repositoryStore;

    public ImportFileStoreConflictsValidator(AbstractRepositoryStore<? extends IRepositoryFileStore> repositoryStore) {
        this.repositoryStore = repositoryStore;
    }

    @Override
    protected IStatus doValidate(Optional<String> value) {
        if (value.isPresent()) {
            final File file = new File(value.get());
            return repositoryStore.getChild(file.getName(), true) != null
                    ? ValidationStatus.warning(Messages.importConflictWarning)
                    : ValidationStatus.ok();
        }
        return ValidationStatus.ok();
    }
}
