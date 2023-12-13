package org.eclipse.gmf.tooling.runtime.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.GetIconOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.swt.graphics.Image;

public class DefaultElementTypeIconProvider extends AbstractProvider implements IIconProvider {

	private final DiagramElementTypes myElementTypes;

	public DefaultElementTypeIconProvider(DiagramElementTypes elementTypes) {
		myElementTypes = elementTypes;
	}

	public final DiagramElementTypes getElementTypes() {
		return myElementTypes;
	}

	public final DiagramElementTypeImages getElementTypeImages() {
		return myElementTypes.getElementTypeImages();
	}

	public Image getIcon(IAdaptable hint, int flags) {
		ENamedElement definingElement = getElementTypes().getDefiningNamedElement(hint);
		return definingElement == null ? null : getElementTypeImages().getImage(definingElement);
	}

	public boolean provides(IOperation operation) {
		if (operation instanceof GetIconOperation) {
			return ((GetIconOperation) operation).execute(this) != null;
		}
		return false;
	}
}
