package org.eclipse.emf.edapt.common;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * Default implementation of {@link IResourceSetFactory} returning an instance
 * of {@link ResourceSetImpl}.
 * 
 * @author herrmi
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class ResourceSetFactoryImpl implements IResourceSetFactory {

	/** {@inheritDoc} */
	public ResourceSet createResourceSet() {
		return new ResourceSetImpl();
	}

}
