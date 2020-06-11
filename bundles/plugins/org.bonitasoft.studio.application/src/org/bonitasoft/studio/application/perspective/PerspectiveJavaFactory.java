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

import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

/**
 * @author Aurelien Pupier
 */
public class PerspectiveJavaFactory extends AbstractPerspectiveFactory {

    public static final String JAVA_PERSPECTIVE_ID = "org.bonitasoft.studio.perspective.java";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();

        final IFolderLayout leftFolder = layout.createFolder("left", IPageLayout.LEFT, getExplorerViewRatio(), editorArea); //$NON-NLS-1$
        leftFolder.addView("org.bonitasoft.studio.application.project.explorer");
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.75f, "left");
        bottomLeft.addView("org.eclipse.ui.views.ProblemView");
        
        final IFolderLayout bottomfolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f, editorArea); //$NON-NLS-1$
        if (RepositoryManager.getInstance().getCurrentRepository().isShared("org.eclipse.egit.core.GitProvider")) {
            bottomfolder.addView("org.eclipse.egit.ui.StagingView");
            bottomfolder.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        }

        final IFolderLayout rightFolder = layout.createFolder("right", IPageLayout.RIGHT, 0.75f, editorArea); //$NON-NLS-1$
        rightFolder.addView(IPageLayout.ID_OUTLINE);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#isRelevantFor(org.eclipse.ui.IEditorPart)
     */
    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return part instanceof JavaEditor && !isInsideprojectWithREStApiExtensionNature(part);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#getID()
     */
    @Override
    public String getID() {
        return JAVA_PERSPECTIVE_ID;
    }

}
