package org.bonitasoft.studio.properties.filters;

import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

public class ElementExceptContainersFilter implements IFilter {

	public boolean select(Object toTest) {
		if (toTest instanceof IGraphicalEditPart) {
			IGraphicalEditPart editPart = (IGraphicalEditPart) toTest;
			Object model = editPart.resolveSemanticElement();
			if(model instanceof MainProcess)
				return false ;
			return !(model instanceof Pool || model instanceof Lane);
		}
		return false;
	}

}
