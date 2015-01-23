package org.eclipse.emf.edapt.common;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Factory to create {@link ResourceSet}s for customizing serialization.
 * 
 * @author herrmi
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public interface IResourceSetFactory {

	/** Create an instance of an implementation of {@link ResourceSet}. */
	ResourceSet createResourceSet();
}
