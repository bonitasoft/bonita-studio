/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.jface.databinding;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.forms.IMessage;

public class StatusToMessageSeverity {

    private final IStatus status;

    public StatusToMessageSeverity(final IStatus status) {
        this.status = status;
    }

    public int toMessageSeverity() {
        switch (status.getSeverity()) {
            case IStatus.OK:
                return IMessage.NONE;
            case IStatus.ERROR:
                return IMessage.ERROR;
            case IStatus.WARNING:
                return IMessage.WARNING;
            case IStatus.INFO:
                return IMessage.INFORMATION;
            default:
                throw new IllegalArgumentException("Unsupported status severity code: " + status.getSeverity());
        }
    }
}
