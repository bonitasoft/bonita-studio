/**
 * 
 */
package org.bonitasoft.studio.properties.filters;

import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

/**
 * @author aurelie
 *
 */
public class IndexFilter implements IFilter {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	@Override
	public boolean select(Object toTest) {
		if ( toTest instanceof IGraphicalEditPart) {
			IGraphicalEditPart editPart = (IGraphicalEditPart)  toTest;
			Object model = editPart.resolveSemanticElement();	
			return model instanceof Pool;
		}
		return false;
	}

}
