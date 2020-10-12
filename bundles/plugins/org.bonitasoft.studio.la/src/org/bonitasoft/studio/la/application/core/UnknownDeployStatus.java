/**
 * Copyright (C) 2017 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.la.application.core;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class UnknownDeployStatus implements IRunnableWithStatus {

    public String message;

    public UnknownDeployStatus(String message) {
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        //NOTHING TO RUN
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.core.IRunnableWithStatus#getStatus()
     */
    @Override
    public IStatus getStatus() {
        return new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID, message);
    }
}
