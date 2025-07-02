package org.eclipse.gmf.tooling.runtime.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @since 3.1
 */
public abstract class DiagramElementTypes {

	private DiagramElementTypeImages myImages;

	public DiagramElementTypes(AdapterFactory adapterFactory) {
		this(new DiagramElementTypeImages(adapterFactory));
	}

	public DiagramElementTypes(DiagramElementTypeImages images) {
		myImages = images;
	}

	public abstract IElementType getElementTypeForVisualId(int visualID);

	public abstract boolean isKnownElementType(IElementType elementType);

	public abstract ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter);

	public DiagramElementTypeImages getElementTypeImages() {
		return myImages;
	}

}
