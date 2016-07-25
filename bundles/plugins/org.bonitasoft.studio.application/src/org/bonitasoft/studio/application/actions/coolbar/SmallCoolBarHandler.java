/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.actions.coolbar;

import org.bonitasoft.studio.application.coolbar.CoolbarToolControl;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MToolControl;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

/**
 * @author Romain Bioteau
 *
 */
public class SmallCoolBarHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		MWindow model = ((WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow()).getModel();
		EModelService modelService = model.getContext().get(EModelService.class);
		MToolControl bonitaCoolBar = (MToolControl) modelService.find(
				"BonitaCoolbar", model);
		if(bonitaCoolBar != null){
			CoolbarToolControl coolbarControl = (CoolbarToolControl) bonitaCoolBar.getObject();
			coolbarControl.minimizeCoolbar();
		}
		return null;
	}

}
