/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard.validator;

import org.bonitasoft.studio.businessobject.ui.wizard.validator.MavenIdValidator;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;

public class ArtifactIdValidator extends MavenIdValidator {

    public ArtifactIdValidator(IWorkspace workspace, String fieldName) {
        super(workspace, fieldName);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.rest.api.extension.ui.wizard.validator.MavenIdValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(Object value) {
        final IStatus status = super.validate(value);
        if (status.isOK()) {
            if (!value.toString().matches("^[a-zA-Z0-9]*$")) {
                return ValidationStatus.error(Messages.alphanumericCharactersOnly);
            }
        }
        return status;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.rest.api.extension.ui.wizard.validator.MavenIdValidator#workspaceNameErrorMessage()
     */
    @Override
    protected String workspaceNameErrorMessage() {
        return fieldName + ": "
                + org.eclipse.m2e.core.ui.internal.Messages.wizardProjectPageMaven2ValidatorArtifactIDinvalid;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.rest.api.extension.ui.wizard.validator.MavenIdValidator#containSpacesMessage()
     */
    @Override
    protected String containSpacesMessage() {
        return Messages.projectNameContainSpaces;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.rest.api.extension.ui.wizard.validator.MavenIdValidator#emptyIdMessage()
     */
    @Override
    protected String emptyIdMessage() {
        return Messages.emptyProjectName;
    }

}
