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
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.IPartListener;
import org.eclipse.ui.internal.e4.compatibility.CompatibilityEditor;

public final class AutomaticSwitchPerspectivePartListener implements IPartListener {

	private String lastPerspective;

	@Override
	public void partActivated(MPart part) {
		if (part.getElementId().equals("org.eclipse.e4.ui.compatibility.editor")) {
			final String id = BonitaPerspectivesUtils.getPerspectiveId(((CompatibilityEditor) part.getObject()).getEditor());
			if (id != null && !id.equals(lastPerspective)) {
				lastPerspective = id;
				BonitaPerspectivesUtils.switchToPerspective(id);
			}
		}
	}

	@Override
	public void partBroughtToTop(MPart part) {
		
	}

	@Override
	public void partDeactivated(MPart part) {
		
	}

	@Override
	public void partHidden(MPart part) {
		
	}

	@Override
	public void partVisible(MPart part) {
		if (part.getElementId().equals("org.eclipse.e4.ui.compatibility.editor")) {
			if(PlatformUtil.isIntroOpen()){
				PlatformUtil.closeIntro();
			}
			final String id = BonitaPerspectivesUtils.getPerspectiveId(((CompatibilityEditor) part.getObject()).getEditor());
			if (id != null && !id.equals(lastPerspective)) {
				lastPerspective = id;
				BonitaPerspectivesUtils.switchToPerspective(id);
			}
		}
	}
}