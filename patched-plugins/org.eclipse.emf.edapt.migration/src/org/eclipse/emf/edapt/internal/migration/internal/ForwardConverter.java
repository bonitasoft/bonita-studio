/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.internal.migration.internal;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edapt.internal.common.EcoreUtils;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.MigrationFactory;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.ModelResource;
import org.eclipse.emf.edapt.spi.migration.ReferenceSlot;

/**
 * Convert an EMF model to a model graph.
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 53452BD2610B0C191D9E7AFA0A7895E8
 */
public class ForwardConverter {

	/** Model graph. */
	protected Model model;

	/** Mapping from EMF elements to graph nodes. */
	protected Map<EObject, Instance> mapping;

	/** Convert an EMF model to a model graph. */
	public Model convert(ResourceSet resourceSet) {
		model = MigrationFactory.eINSTANCE.createModel();
		mapping = new IdentityHashMap<EObject, Instance>();

		initElements(resourceSet);
		initProperties(resourceSet);
		initResources(resourceSet);

		return model;
	}

	/** Create a node for each EMF model element */
	protected void initElements(ResourceSet resourceSet) {
		for (final Resource resource : resourceSet.getResources()) {
			for (final TreeIterator<EObject> i = resource.getAllContents(); i
				.hasNext();) {
				final EObject eObject = i.next();
				if (mapping.containsKey(eObject)) {
					i.prune();
				} else {
					final Instance instance = newInstance(eObject, eObject.eIsProxy());
					final String uuid = EcoreUtils.getUUID(eObject);
					instance.setUuid(uuid);
				}
			}
		}
	}

	/** Initialize the properties of the nodes. */
	protected void initProperties(ResourceSet resourceSet) {
		final Set<EObject> done = new HashSet<EObject>();
		for (final Resource resource : resourceSet.getResources()) {
			for (final TreeIterator<EObject> i = resource.getAllContents(); i
				.hasNext();) {
				final EObject eObject = i.next();
				if (done.contains(eObject)) {
					i.prune();
				} else {
					initInstance(eObject);
					done.add(eObject);
				}
			}
		}
	}

	/** Determine the root nodes. */
	protected void initResources(ResourceSet resourceSet) {
		for (final Resource resource : resourceSet.getResources()) {
			if (resource.getContents().isEmpty()) {
				continue;
			}
			final ModelResource modelResource = MigrationFactory.eINSTANCE
				.createModelResource();

			// For CDO we need the connection aware URI!
			modelResource.setUri(resource.getURI());
			if (resource instanceof XMLResource) {
				final XMLResource xmlResource = (XMLResource) resource;
				modelResource.setEncoding(xmlResource.getEncoding());
			}
			model.getResources().add(modelResource);
			for (final EObject element : resource.getContents()) {
				modelResource.getRootInstances().add(resolve(element));
			}
		}
	}

	/** Initialize edges outgoing from a node. */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void initInstance(EObject eObject) {
		final Instance element = resolve(eObject);
		final EClass c = eObject.eClass();

		for (final EAttribute attribute : c.getEAllAttributes()) {
			if (ignore(attribute)) {
				continue;
			}
			if (eObject.eIsSet(attribute)) {
				final Object value = eObject.eGet(attribute);
				element.set(attribute, value);
			}
		}

		for (final EReference reference : c.getEAllReferences()) {
			if (ignore(reference)) {
				continue;
			}
			final Object value = eObject.eGet(reference);

			if (reference.isMany()) {
				final List<EObject> valueEObjects = (List<EObject>) value;
				int index = 0;
				for (final EObject valueEObject : valueEObjects) {
					final Instance valueInstance = resolve(valueEObject);
					if (reference.isUnique()
						&& ((List) element.get(reference))
							.contains(valueInstance)) {
						final ReferenceSlot referenceSlot = (ReferenceSlot) element
							.getSlot(reference);
						try {
							referenceSlot.getValues()
								.move(index, valueInstance);
							index++;
						} catch (final IndexOutOfBoundsException e) {
							// ignore missing inverse link
						}
					} else {
						element.add(reference, index, valueInstance);
						index++;
					}
				}
			} else {
				if (value != null) {
					final EObject valueEObject = (EObject) value;
					final Instance valueInstance = resolve(valueEObject);
					element.set(reference, valueInstance);
				}
			}
		}
	}

	/**
	 * Determines whether a certain feature should be ignored during conversion.
	 */
	protected boolean ignore(EStructuralFeature feature) {
		if (feature.isTransient()) {
			if (feature instanceof EReference) {
				final EReference reference = (EReference) feature;
				if (reference.getEOpposite() != null
					&& !reference.getEOpposite().isTransient()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/** Create a new node for an EMF model element. */
	protected Instance newInstance(EObject eObject, boolean proxy) {
		final EClass eClass = eObject.eClass();
		final Instance element = model.newInstance(eClass);
		mapping.put(eObject, element);
		if (proxy) {
			element.setUri(EcoreUtil.getURI(eObject));
		}
		return element;
	}

	/** Get the node corresponding to an EMF model element. */
	protected Instance resolve(EObject eObject) {
		Instance resolved = mapping.get(eObject);
		if (resolved == null) {
			resolved = newInstance(eObject, true);
		}
		return resolved;
	}
}
