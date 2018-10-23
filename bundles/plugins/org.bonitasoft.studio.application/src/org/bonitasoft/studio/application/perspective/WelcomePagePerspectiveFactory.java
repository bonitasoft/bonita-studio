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

import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.internal.intro.IIntroConstants;


public class WelcomePagePerspectiveFactory extends AbstractPerspectiveFactory {

    public static final String ID = "org.bonitasoft.studio.perspective.welcomePage";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        layout.setEditorAreaVisible(false);
        final String editorArea = layout.getEditorArea();
        final IFolderLayout leftFolder = layout.createFolder("left", IPageLayout.LEFT, getExplorerViewRatio(), editorArea); //$NON-NLS-1$
        leftFolder.addView(BonitaProjectExplorer.ID);
        final IFolderLayout rightFolder = layout.createFolder("right", IPageLayout.RIGHT, (float) 0.75, editorArea); //$NON-NLS-1$
        rightFolder.addPlaceholder(IIntroConstants.INTRO_VIEW_ID);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#isRelevantFor(org.eclipse.ui.IEditorPart)
     */
    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return part == null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#getID()
     */
    @Override
    public String getID() {
        return ID;
    }

}
