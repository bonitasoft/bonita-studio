/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.ui;

import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.ui.editors.FilteredXMLEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

public class ApplicationPerspectiveFactory extends AbstractPerspectiveFactory {

    public static final String APPLICATION_PERSPECTIVE_ID = "org.bonitasoft.studio.la.perspective";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();

        final IFolderLayout rightFolder = layout.createFolder("right", IPageLayout.RIGHT, (float) 0.75, editorArea); //$NON-NLS-1$
        rightFolder.addView(IPageLayout.ID_OUTLINE);
    }

    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return part instanceof FilteredXMLEditor;
    }

    @Override
    public String getID() {
        return APPLICATION_PERSPECTIVE_ID;
    }

}
