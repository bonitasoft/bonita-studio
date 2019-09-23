/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.core.operation;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class DeployBDMJob extends Job {

    private BusinessObjectModelFileStore fileStore;
    private boolean dropDatabase;

    public DeployBDMJob(BusinessObjectModelFileStore fileStore,boolean dropDatabase) {
        super(Messages.deployBDMJobName);
        this.fileStore = fileStore;
        this.dropDatabase = dropDatabase;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        try {
            new GenerateBDMOperation(fileStore).run(monitor);
            new DeployBDMOperation(fileStore,dropDatabase).run(monitor);
        } catch (final InvocationTargetException e) {
            return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID,
                    "Failed to deploy BDM. Check Studio logs for more information.",
                    new DeployBDMStackTraceResolver().reduceHibernateException(e));
        } catch (InterruptedException e) {
            return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID, "Failed to deploy BDM", e);
        }
        return Status.OK_STATUS;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
     */
    @Override
    public boolean belongsTo(Object family) {
        return DeployBDMJob.class.equals(family);
    }

}
