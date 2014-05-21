package org.bonitasoft.studio.properties.form.filters;

import org.bonitasoft.studio.model.form.ImageWidget;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

public class ImageFilter implements IFilter {

	public boolean select(Object toTest) {
		if (toTest instanceof IGraphicalEditPart) {
			IGraphicalEditPart editPart = (IGraphicalEditPart) toTest;
			Object model = editPart.resolveSemanticElement();
			return model instanceof ImageWidget;
		}
		return false;
	}

}
