/**
 * Copyright (C) 2010-2013 BonitaSoft S.A.
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

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.internal.ViewIntroAdapterPart;
import org.eclipse.ui.intro.IIntroPart;

public final class AutomaticSwitchPerspectivePartListener implements IPartListener2 {

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof IEditorPart) {
			final String id = BonitaPerspectivesUtils.getPerspectiveId((IEditorPart) part);
			if (id != null) {
				BonitaPerspectivesUtils.switchToPerspective(id);
			}
		}
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {

	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof IEditorPart) {
			PlatformUtil.openIntroIfNoOtherEditorOpen();
		}
//		if("org.eclipse.ui.internal.introview".equals(partRef.getId())){
//			PlatformUtil.openIntroIfNoOtherEditorOpen();
//		}
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {

	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof IEditorPart) {
			if(PlatformUtil.isIntroOpen()){
				PlatformUtil.closeIntro();
			}
			final String id = BonitaPerspectivesUtils.getPerspectiveId((IEditorPart) part);
			if (id != null) {
				BonitaPerspectivesUtils.switchToPerspective(id);
			}
		}
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {

	}
}