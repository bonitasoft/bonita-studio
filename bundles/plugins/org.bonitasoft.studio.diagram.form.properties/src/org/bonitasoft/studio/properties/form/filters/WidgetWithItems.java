package org.bonitasoft.studio.properties.form.filters;

import org.bonitasoft.studio.model.form.ItemContainer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

public class WidgetWithItems implements IFilter {

	public boolean select(Object toTest) {
		if (toTest instanceof IGraphicalEditPart) {
			IGraphicalEditPart editPart = (IGraphicalEditPart) toTest;
			Object model = editPart.resolveSemanticElement();
			return model instanceof ItemContainer;
		}
		return false;
	}
}
