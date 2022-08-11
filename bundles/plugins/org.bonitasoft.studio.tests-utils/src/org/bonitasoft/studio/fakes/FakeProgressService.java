/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.fakes;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;

public class FakeProgressService implements IProgressService {

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.progress.IProgressService#getLongOperationTime()
     */
    @Override
    public int getLongOperationTime() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.progress.IProgressService#registerIconForFamily(org.eclipse.jface.resource.ImageDescriptor, java.lang.Object)
     */
    @Override
    public void registerIconForFamily(final ImageDescriptor icon, final Object family) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.progress.IProgressService#runInUI(org.eclipse.jface.operation.IRunnableContext, org.eclipse.jface.operation.IRunnableWithProgress,
     * org.eclipse.core.runtime.jobs.ISchedulingRule)
     */
    @Override
    public void runInUI(final IRunnableContext context, final IRunnableWithProgress runnable, final ISchedulingRule rule)
            throws InvocationTargetException,
            InterruptedException {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.progress.IProgressService#getIconFor(org.eclipse.core.runtime.jobs.Job)
     */
    @Override
    public Image getIconFor(final Job job) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.progress.IProgressService#busyCursorWhile(org.eclipse.jface.operation.IRunnableWithProgress)
     */
    @Override
    public void busyCursorWhile(final IRunnableWithProgress runnable)
            throws InvocationTargetException, InterruptedException {
        runnable.run(new NullProgressMonitor());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.progress.IProgressService#run(boolean, boolean, org.eclipse.jface.operation.IRunnableWithProgress)
     */
    @Override
    public void run(final boolean fork, final boolean cancelable, final IRunnableWithProgress runnable)
            throws InvocationTargetException, InterruptedException {
        runnable.run(new NullProgressMonitor());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.progress.IProgressService#showInDialog(org.eclipse.swt.widgets.Shell, org.eclipse.core.runtime.jobs.Job)
     */
    @Override
    public void showInDialog(final Shell shell, final Job job) {

    }

}
