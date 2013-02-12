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
package org.bonitasoft.studio.common.editor;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

/**
 * @author Aurelien Pupier
 * 
 */
public class PartListenerToOpenIntroIfNoMoreEditorIsopened implements IPartListener2 {

	public PartListenerToOpenIntroIfNoMoreEditorIsopened() {
	}

	public void partClosed(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof IEditorPart) {
			PlatformUtil.openIntroIfNoOtherEditorOpen();
		}
	}

	public void partActivated(IWorkbenchPartReference partRef) {
		// DO NOTHING

	}

	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// DO NOTHING
	}

	public void partDeactivated(IWorkbenchPartReference partRef) {
		// DO NOTHING
	}

	public void partHidden(IWorkbenchPartReference partRef) {
		// DO NOTHING
	}

	public void partInputChanged(IWorkbenchPartReference partRef) {
		// DO NOTHING
	}

	public void partOpened(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof IEditorPart) {
			PlatformUtil.closeIntro();
		}
	}

	public void partVisible(IWorkbenchPartReference partRef) {
		// DO NOTHING
	}

}
