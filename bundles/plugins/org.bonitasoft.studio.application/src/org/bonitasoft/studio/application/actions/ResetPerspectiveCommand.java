/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.actions;

import org.bonitasoft.studio.application.actions.coolbar.NormalCoolBarHandler;
import org.bonitasoft.studio.application.actions.coolbar.SmallCoolBarHandler;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.preferences.BonitaCoolBarPreferenceConstant;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 * Reset the current perspective
 */
public class ResetPerspectiveCommand extends AbstractHandler implements IHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
		IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore() ;
		if(store.getString(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE).equals(BonitaCoolBarPreferenceConstant.SMALL)){
			new SmallCoolBarHandler().execute(null) ;
		}else if(store.getString(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE).equals(BonitaCoolBarPreferenceConstant.NORMAL)){
			new NormalCoolBarHandler().execute(null) ;
		}
        PlatformUtil.openIntroIfNoOtherEditorOpen();
		return null;
	}

}
