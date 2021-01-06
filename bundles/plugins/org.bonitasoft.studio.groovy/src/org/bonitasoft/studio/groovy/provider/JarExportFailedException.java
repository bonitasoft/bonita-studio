/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.provider;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;


public class JarExportFailedException extends RuntimeException {

    private static final long serialVersionUID = -542078154433791781L;
    private final IStatus status;
    private final String globalMessage;

    public JarExportFailedException(final String globalMessage, final IStatus status) {
        this.globalMessage = globalMessage;
        this.status = status;
    }

    @Override
    public String getMessage() {
        final StringBuilder sb = new StringBuilder(globalMessage + "\n");
        if (status instanceof MultiStatus) {
            for (final IStatus childStatus : status.getChildren()) {
                sb.append(childStatus.getMessage());
                sb.append(" \n");
            }
        } else {
            sb.append(status.getMessage());
            sb.append(" \n");
        }
        return sb.toString();
    }

}
