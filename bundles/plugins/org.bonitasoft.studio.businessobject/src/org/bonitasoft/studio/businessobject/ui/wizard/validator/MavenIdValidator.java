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
package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public abstract class MavenIdValidator implements IValidator {

    private final IWorkspace workspace;
    protected final String fieldName;

    public MavenIdValidator(IWorkspace workspace, String fieldName) {
        this.workspace = workspace;
        this.fieldName = fieldName;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(Object value) {
        final String text = (String) value;
        if (text == null || text.length() == 0) {
            return ValidationStatus.error(emptyIdMessage());
        }

        if (text.contains(" ")) {
            return ValidationStatus.error(containSpacesMessage());
        }

        final IStatus nameStatus = workspace.validateName(text, IResource.PROJECT);
        if (!nameStatus.isOK()) {
            return ValidationStatus.error(NLS.bind(workspaceNameErrorMessage(), nameStatus.getMessage(), fieldName));
        }

        if (!text.matches("[A-Za-z0-9_\\-.]+")) {
            return ValidationStatus.error(NLS.bind(workspaceNameErrorMessage(), text, fieldName));
        }
        return ValidationStatus.ok();
    }

    protected abstract String workspaceNameErrorMessage();

    protected abstract String containSpacesMessage();

    protected abstract String emptyIdMessage();

}
