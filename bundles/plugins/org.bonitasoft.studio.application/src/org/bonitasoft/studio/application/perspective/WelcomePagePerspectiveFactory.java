/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.perspective;

import org.bonitasoft.studio.common.ui.perspectives.AbstractPerspectiveFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.internal.intro.IIntroConstants;


public class WelcomePagePerspectiveFactory extends AbstractPerspectiveFactory {

    public static final String ID = "org.bonitasoft.studio.perspective.welcomePage";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        layout.addStandaloneViewPlaceholder(IIntroConstants.INTRO_VIEW_ID, IPageLayout.TOP, 1f, layout.getEditorArea(), true);
    }

    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return part == null;
    }

    @Override
    public String getID() {
        return ID;
    }

}
