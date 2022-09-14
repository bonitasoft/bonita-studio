package org.bonitasoft.studio.application.views;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class BonitaContentOutlineView extends ContentOutline {

    public static final String VIEW_ID = "org.bonitasoft.studio.views.overview"; //$NON-NLS-1$

    @Override
    protected PageRec doCreatePage(IWorkbenchPart part) {
        Object obj = Adapters.adapt(part, IContentOutlinePage.class, false);
        if (obj instanceof IContentOutlinePage && part instanceof DiagramEditor) {
            return super.doCreatePage(part);
        }
        return null;
    }

}
