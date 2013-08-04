package org.bonitasoft.studio.application.views;

import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DiagramRootTreeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.ViewsPlugin;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class BonitaContentOutlineTreeView extends ContentOutline{

    public static final String VIEW_ID ="org.bonitasoft.studio.views.overview.tree"; //$NON-NLS-1$
    /* (non-Javadoc)
     * @see org.eclipse.ui.views.contentoutline.ContentOutline#doCreatePage(org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    protected PageRec doCreatePage(IWorkbenchPart part) {
        Object obj = ViewsPlugin.getAdapter(part, IContentOutlinePage.class, false);
        if (obj instanceof IContentOutlinePage && part instanceof DiagramEditor) {
        	TreeViewer viewer = new TreeViewer();
			viewer.setRootEditPart(new DiagramRootTreeEditPart());
            IContentOutlinePage page = new BonitaTreeOutlinePage(viewer,(DiagramEditor) part);
            if (page instanceof IPageBookViewPage) {
				initPage((IPageBookViewPage) page);
			}
            page.createControl(getPageBook());
            return new PageRec(part, page);
        }
        // There is no content outline
        return null;
    }
    
    @Override
    public void createPartControl(Composite parent) {
    	parent.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				System.out.println("i'm disposed");
			}
		});
    	super.createPartControl(parent);
    }
}
