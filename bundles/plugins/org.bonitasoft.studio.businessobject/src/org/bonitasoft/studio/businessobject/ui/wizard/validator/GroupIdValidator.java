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

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;

public class GroupIdValidator extends MavenIdValidator {

    public GroupIdValidator(IWorkspace workspace) {
        super(workspace, Messages.groupId);
    }

    @Override
    public IStatus validate(Object value) {
        final IStatus status = super.validate(value);
        if (status.isOK()) {
            final IStatus packageNameStatus = validateJavaPackageName(value);
            return new Status(packageNameStatus.getSeverity(), packageNameStatus.getPlugin(),
                    fieldName + ": " + packageNameStatus.getMessage());
        }
        return status;
    }

    protected IStatus validateJavaPackageName(Object value) {
        try {
            return JavaConventions.validatePackageName(value.toString(), JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
        } catch (IllegalStateException e) {
            //avoid stacktrace in tests
            return Status.OK_STATUS;
        }
    }

    @Override
    protected String workspaceNameErrorMessage() {
        return fieldName + ": " + org.eclipse.m2e.core.ui.internal.Messages.wizardProjectPageMaven2ValidatorGroupIDinvalid;
    }

    @Override
    protected String containSpacesMessage() {
        return Messages.groupIdCannotContainsSpace;
    }

    @Override
    protected String emptyIdMessage() {
        return Messages.bind(org.bonitasoft.studio.common.Messages.emptyField, Messages.groupId);
    }
}
