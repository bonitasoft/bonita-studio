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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultSimpleTabListener;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultTabFolder;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultThemeListener;
import org.eclipse.ui.internal.presentations.util.PresentablePartFolder;
import org.eclipse.ui.internal.presentations.util.StandardViewSystemMenu;
import org.eclipse.ui.internal.presentations.util.TabbedStackPresentation;
import org.eclipse.ui.presentations.IStackPresentationSite;
import org.eclipse.ui.presentations.StackPresentation;
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
    public StackPresentation createViewPresentation(Composite parent,
    		IStackPresentationSite site) {
    	DefaultTabFolder folder = new DefaultTabFolder(parent, PlatformUI.getPreferenceStore()
    			.getInt(IWorkbenchPreferenceConstants.VIEW_TAB_POSITION)
				| SWT.BORDER, false, false);

		final IPreferenceStore store = PlatformUI.getPreferenceStore();
		final int minimumCharacters = store.getInt(IWorkbenchPreferenceConstants.VIEW_MINIMUM_CHARACTERS);
		if (minimumCharacters >= 0) {
			folder.setMinimumCharacters(minimumCharacters);
		}

		PresentablePartFolder partFolder = new PresentablePartFolder(folder);

		folder.setUnselectedCloseVisible(false);
		folder.setUnselectedImageVisible(false);


		TabbedStackPresentation result = new TabbedStackPresentation(site,
				partFolder, new StandardViewSystemMenu(site));

		DefaultThemeListener themeListener = new DefaultThemeListener(folder,
				result.getTheme());
		result.getTheme().addListener(themeListener);

		new DefaultSimpleTabListener(result.getApiPreferences(),
				IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS,
				folder);

		return result;
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
