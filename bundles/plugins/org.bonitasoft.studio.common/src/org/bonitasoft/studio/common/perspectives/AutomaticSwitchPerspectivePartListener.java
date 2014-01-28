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
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.IPartListener;
import org.eclipse.ui.internal.e4.compatibility.CompatibilityEditor;

public final class AutomaticSwitchPerspectivePartListener implements IPartListener {


	private boolean isSwitching;

	@Override
	public void partActivated(MPart part) {
//		if(!isSwitching){
//			isSwitching = true;
//			try{
//				if (part.getElementId().equals("org.eclipse.e4.ui.compatibility.editor")) {
//					String activePerspective = getActivePerspectiveId(part);
//					final String id = BonitaPerspectivesUtils.getPerspectiveId(((CompatibilityEditor) part.getObject()).getEditor());
//					if (id != null && !id.equals(activePerspective)) {
//						BonitaPerspectivesUtils.switchToPerspective(id);
//					}
//				}
//			}finally{
//				isSwitching = false;
//			}
//		}
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
		if(!isSwitching){
			isSwitching = true;
			try{
				if (part.getElementId().equals("org.eclipse.e4.ui.compatibility.editor")) {
					if(PlatformUtil.isIntroOpen()){
						PlatformUtil.closeIntro();
					}
					String activePerspective = getActivePerspectiveId(part);
					String id = BonitaPerspectivesUtils.getPerspectiveId(((CompatibilityEditor) part.getObject()).getEditor());
					if (id != null && !id.equals(activePerspective)) {
						BonitaPerspectivesUtils.switchToPerspective(id);
					}
				}
			}finally{
				isSwitching = false;
			}
		}
	}

	protected String getActivePerspectiveId(MPart part) {
		EModelService service =	part.getContext().get(EModelService.class);
		MWindow window = service.getTopLevelWindowFor(part);
		MPerspectiveStack pStack =  (MPerspectiveStack) service.find("PerspectiveStack", window);
		MPerspective selectedElement = pStack.getSelectedElement();
		String activePerspective =null;
		if(selectedElement != null){
			activePerspective = selectedElement.getElementId();
		}
		return activePerspective;
	}
}