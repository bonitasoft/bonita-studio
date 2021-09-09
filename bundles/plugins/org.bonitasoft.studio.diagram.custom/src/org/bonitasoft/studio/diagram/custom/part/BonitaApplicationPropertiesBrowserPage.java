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
package org.bonitasoft.studio.diagram.custom.part;

import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @author Aurelien Pupier
 */
public class BonitaApplicationPropertiesBrowserPage extends
		BonitaPropertiesBrowserPage {

    public static final String VIEW_ID = "org.bonitasoft.studio.views.properties.application";

    public BonitaApplicationPropertiesBrowserPage(
			ITabbedPropertySheetPageContributor contributor) {
		super(contributor);
	}

	@Override
	protected String getViewID() {
        return VIEW_ID;
	}

}
