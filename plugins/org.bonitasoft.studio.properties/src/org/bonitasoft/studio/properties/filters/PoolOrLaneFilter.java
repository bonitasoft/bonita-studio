package org.bonitasoft.studio.properties.filters;

import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

public class PoolOrLaneFilter implements IFilter {

	public boolean select(Object object) {
		if (object instanceof IGraphicalEditPart) {
			IGraphicalEditPart editPart = (IGraphicalEditPart) object;
			Object model = editPart.resolveSemanticElement();
			return (model instanceof Lane || model instanceof Pool);
		}
		return false;
	}

}
