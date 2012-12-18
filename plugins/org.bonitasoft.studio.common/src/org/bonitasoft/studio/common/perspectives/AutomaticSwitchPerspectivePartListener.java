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
package org.bonitasoft.studio.common.perspectives;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.progress.UIJob;

public final class AutomaticSwitchPerspectivePartListener implements IPartListener {

	public void partOpened(IWorkbenchPart part) {
	}

	public void partDeactivated(IWorkbenchPart part) {
	}

	public void partClosed(IWorkbenchPart part) {
	}

	public void partBroughtToTop(IWorkbenchPart part) {
	}

	public void partActivated(IWorkbenchPart part) {
		if (part instanceof IEditorPart) {
			final String id = BonitaPerspectivesUtils.getPerspectiveId((IEditorPart) part);
			if (id != null) {
				UIJob job = new UIJob("changePerspective") {
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								BonitaPerspectivesUtils.switchToPerspective(id);
							}
						});
						return Status.OK_STATUS;
					}
				};
				job.setSystem(true);
				job.schedule();
			}
		}
	}
}