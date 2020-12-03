/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.preferences;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class PreferenceUtil {

    public static IPreferenceNode findNodeMatching(String nodeId) {
        List<?> nodes = PlatformUI.getWorkbench().getPreferenceManager().getElements(PreferenceManager.POST_ORDER);
        for (Iterator<?> i = nodes.iterator(); i.hasNext();) {
            final IPreferenceNode node = (IPreferenceNode) i.next();
            if (node.getId().equals(nodeId)) {
                return node;
            }
        }
        return null;
    }

    public static boolean isDarkTheme() {
        if (BonitaStudioPreferencesPlugin.getDefault() != null) {
            String theme = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                    .getString(BonitaThemeConstants.STUDIO_THEME_PREFERENCE);
            return Objects.equals(theme, BonitaThemeConstants.DARK_THEME);
        }
        return false;
    }

}
