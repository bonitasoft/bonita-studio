/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BMW Car IT - Initial API and implementation
 *     Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * Helper methods to deal with Ecore models.
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating RED Rev:
 */
public final class EcoreUtils {

	/** Constructor. */
	private EcoreUtils() {
		// hidden, since this class only provides static helper methods
	}

	/**
	 * Get the inverse of a reference to an element within a search area which
	 * is denoted by the root elements.
	 */
	public static List<? extends EObject> getInverse(EObject element,
			EReference reference, List<? extends EObject> searchArea) {
		List<EObject> inverseList = new ArrayList<EObject>();
		for (Setting setting : UsageCrossReferencer.find(element, searchArea)) {
			if (setting.getEStructuralFeature() == reference) {
				inverseList.add(setting.getEObject());
			}
		}
		return inverseList;
	}
	
	/** Set the UUID of a model element. */
	public static void setUUID(EObject element, String uuid) {
		XMLResource resource = getXMLResource(element);
		if (resource != null) {
			resource.setID(element, uuid);
		}
	}

	/** Get the {@link XMLResource} in which a model element is contained. */
	private static XMLResource getXMLResource(EObject element) {
		Resource resource = element.eResource();
		if (resource != null && resource instanceof XMLResource) {
			XMLResource xmlResource = (XMLResource) resource;
			return xmlResource;
		}
		return null;
	}

	/** Get the UUID of a model element. */
	public static String getUUID(EObject element) {
		XMLResource resource = getXMLResource(element);
		if (resource != null) {
			return resource.getID(element);
		}
		return null;
	}
}
