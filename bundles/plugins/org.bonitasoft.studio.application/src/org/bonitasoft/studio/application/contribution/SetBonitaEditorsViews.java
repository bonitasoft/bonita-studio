/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application.contribution;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.INullSelectionListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Mickael Istria
 *
 */
public class SetBonitaEditorsViews implements INullSelectionListener {

	private boolean isBonitaEditorActive;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part != null
				&& part.getClass() != null
				&& part.getClass().getName() != null
				&& part.getClass().getName().startsWith("org.bonitasoft")) {
			if (!isBonitaEditorActive) {
				try {
				//	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(BonitaModelingPropertiesView.VIEW_ID);
				} catch (Exception ex) {
					BonitaStudioLog.error(ex);
				}
			}
			isBonitaEditorActive = true;
		} else {
			isBonitaEditorActive = false;
		}
	}

}
