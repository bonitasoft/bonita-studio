/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.validator;

import org.eclipse.core.runtime.MultiStatus;

public class OrganizationValidationException extends Exception {

    private static final long serialVersionUID = 578679768520738028L;
    private MultiStatus status;

    public OrganizationValidationException(final MultiStatus status, String organization) {
        super(organization);
        this.status = status;
    }

    public MultiStatus getValidationStatus() {
        return status;
    }

    public String getOrganizationName() {
        return getMessage();
    }
}
