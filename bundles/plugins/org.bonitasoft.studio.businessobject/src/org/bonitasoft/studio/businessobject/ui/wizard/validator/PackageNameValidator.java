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
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.IInputValidator;

public class PackageNameValidator implements IInputValidator, IValidator<String> {

    private static final String ORG_BONITASOFT_PREFIX = "org.bonitasoft";
    private static final String COM_BONITASOFT_PREFIX = "com.bonitasoft";

    @Override
    public String isValid(String packageName) {
        IStatus status = validate(packageName);
        return status.isOK() ? null : status.getMessage();
    }

    @Override
    public IStatus validate(String packageName) {
        if (packageName == null || packageName.isEmpty()) {
            return ValidationStatus.error(Messages.error_emptyPackageName);
        }
        IStatus status = validateReservedPackages(packageName);
        if (!status.isOK()) {
            return status;
        }
        return validateJavaPackageName(packageName);
    }

    protected IStatus validateReservedPackages(String packageName) {
        if (packageName.startsWith(COM_BONITASOFT_PREFIX + ".") || packageName.toString().equals(COM_BONITASOFT_PREFIX)) {
            return ValidationStatus.error(Messages.bind(Messages.error_reservedPackagePrefix, packageName.toString()));
        }
        if (packageName.startsWith(ORG_BONITASOFT_PREFIX + ".") || packageName.toString().equals(ORG_BONITASOFT_PREFIX)) {
            return ValidationStatus.error(Messages.bind(Messages.error_reservedPackagePrefix, packageName.toString()));
        }
        return ValidationStatus.ok();
    }

    protected IStatus validateJavaPackageName(String packageName) {
        return JavaConventions.validatePackageName(packageName, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

}
