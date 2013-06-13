/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.bonitasoft.studio.presentation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.presentations.WorkbenchPresentationFactory;

public class BonitaTabsPresentationFactory extends WorkbenchPresentationFactory {

    @Override
    public Control createStatusLineControl(IStatusLineManager statusLine,
            Composite parent) {
        final Control createdStatusLineControl = super.createStatusLineControl(statusLine, parent);
        createdStatusLineControl.setSize(createdStatusLineControl.getSize().x, 1);
        return createdStatusLineControl;
    }


    @Override
    public IStatusLineManager createStatusLineManager() {
        return new StatusLineManager(){

            boolean canceled = false;

            /*
             * (non-Javadoc)
             * Method declared on IStatusLineManager
             */
            @Override
            public IProgressMonitor getProgressMonitor() {
                return new IProgressMonitorWithBlocking() {

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitor#beginTask(java.lang.String, int)
                     */
                    public void beginTask(String name, int totalWork) {}

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitor#done()
                     */
                    public void done() {}

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitor#internalWorked(double)
                     */
                    public void internalWorked(double work) {}

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitor#isCanceled()
                     */
                    public boolean isCanceled() {
                        return canceled;
                    }

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitor#setCanceled(boolean)
                     */
                    public void setCanceled(boolean value) {
                        canceled = value;
                    }

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitor#setTaskName(java.lang.String)
                     */
                    public void setTaskName(String name) {}

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitor#subTask(java.lang.String)
                     */
                    public void subTask(String name) { }

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitor#worked(int)
                     */
                    public void worked(int work) {}

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitorWithBlocking#clearBlocked()
                     */
                    public void clearBlocked() { }

                    /* (non-Javadoc)
                     * @see org.eclipse.core.runtime.IProgressMonitorWithBlocking#setBlocked(org.eclipse.core.runtime.IStatus)
                     */
                    public void setBlocked(IStatus reason) { }
                };
            }

        };
    }
}
