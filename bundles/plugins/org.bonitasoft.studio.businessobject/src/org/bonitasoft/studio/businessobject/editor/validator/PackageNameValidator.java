/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.validator;

import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;

public class PackageNameValidator implements IBDMValidator<Package> {

    private static final String ORG_BONITASOFT_PREFIX = "org.bonitasoft";
    private static final String COM_BONITASOFT_PREFIX = "com.bonitasoft";

    @Override
    public IStatus validate(Package value) {
        String name = value.getName();
        if (name == null || name.isEmpty()) {
            return ValidationStatus.error(Messages.error_emptyPackageName);
        }
        final IStatus status = javaPackageValidation(name);
        if (status.isOK()) {
            if (name.startsWith(COM_BONITASOFT_PREFIX + ".") || name.equals(COM_BONITASOFT_PREFIX)) {
                return ValidationStatus.error(Messages.bind(Messages.error_reservedPackagePrefix, name));
            }
            if (name.startsWith(ORG_BONITASOFT_PREFIX + ".") || name.equals(ORG_BONITASOFT_PREFIX)) {
                return ValidationStatus.error(Messages.bind(Messages.error_reservedPackagePrefix, name));
            }
        }
        return status;
    }

    protected IStatus javaPackageValidation(String value) {
        return JavaConventions.validatePackageName(value, JavaCore.VERSION_1_8, JavaCore.VERSION_1_8);
    }

    @Override
    public String getValidatorType() {
        return Messages.pakage;
    }

}
