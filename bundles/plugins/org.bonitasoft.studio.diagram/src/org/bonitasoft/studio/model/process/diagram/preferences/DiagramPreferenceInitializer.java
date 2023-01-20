/*
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.process.diagram.preferences;

import org.bonitasoft.studio.common.preferences.CommonDiagramPreferencesConstants;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	* @generated
	*/
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		DiagramGeneralPreferencePage.initDefaults(store);
		DiagramAppearancePreferencePage.initDefaults(store);
		DiagramConnectionsPreferencePage.initDefaults(store);
		DiagramPrintingPreferencePage.initDefaults(store);
		DiagramRulersAndGridPreferencePage.initDefaults(store);

		store.setDefault(WorkspaceViewerProperties.PREF_USE_LANDSCAPE, true);
		store.setDefault(WorkspaceViewerProperties.PREF_USE_PORTRAIT, false);
		store.setDefault(CommonDiagramPreferencesConstants.DEFAULT_OUTLINE_TYPE,
				CommonDiagramPreferencesConstants.OVERVIEW_OUTLINE_TYPE);

		store.setDefault(IPreferenceConstants.PREF_RULER_UNITS, RulerProvider.UNIT_CENTIMETERS);
		store.setDefault(IPreferenceConstants.PREF_SHOW_GRID, false);
		store.setDefault(IPreferenceConstants.PREF_SNAP_TO_GRID, true);
		store.setDefault(IPreferenceConstants.PREF_SNAP_TO_GEOMETRY, true);
		store.setDefault(IPreferenceConstants.PREF_GRID_SPACING, 0.5);

	}

	/**
	* @generated
	*/
	protected IPreferenceStore getPreferenceStore() {
		return ProcessDiagramEditorPlugin.getInstance().getPreferenceStore();
	}
}
