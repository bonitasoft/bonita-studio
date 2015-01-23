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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.Diagnostician;

/**
 * Helper class to administer the extent of a metamodel (root packages, class
 * extents, ...)
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating RED Rev:
 */
public class MetamodelExtent {

	/**
	 * Class extent
	 */
	private final Map<EClass, Collection<EObject>> extentMap;

	/**
	 * Root packages
	 */
	private Collection<EPackage> rootPackages;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public MetamodelExtent() {
		this(Collections.EMPTY_LIST);
	}

	/**
	 * Constructor
	 */
	public MetamodelExtent(Collection<EPackage> rootPackages) {
		this.rootPackages = new ArrayList<EPackage>();
		this.extentMap = new IdentityHashMap<EClass, Collection<EObject>>();

		addRootPackages(rootPackages);
	}

	/*
	 * ------------- Root packages -------------
	 */

	/**
	 * Add a root package
	 * 
	 * @param rootPackage
	 *            Root package
	 */
	public void addRootPackage(EPackage rootPackage) {
		addRootPackages(Arrays.asList(rootPackage));
	}

	/**
	 * Set the collection of root packages
	 * 
	 * @param rootPackages
	 *            Collection of root packages
	 */
	public void setRootPackages(Collection<EPackage> rootPackages) {
		this.rootPackages.clear();
		addRootPackages(rootPackages);
	}

	/**
	 * Add a collection of root packages
	 * 
	 * @param rootPackages
	 *            Collection of root packages
	 */
	public void addRootPackages(Collection<EPackage> rootPackages) {
		for (EPackage rootPackage : rootPackages) {
			if (!this.rootPackages.contains(rootPackage)) {
				this.rootPackages.add(rootPackage);
			}
		}
		clearExtentMap();
	}

	/**
	 * Getter for the root packages
	 * 
	 * @return Collection of root packages
	 */
	public Collection<EPackage> getRootPackages() {
		return rootPackages;
	}

	/*
	 * ---------- Extent map ----------
	 */

	/**
	 * Get the extent of a certain type
	 * 
	 * @param type
	 *            Type
	 * @return Collection of objects
	 */
	public Collection<EObject> getChoiceOfValues(EClass type) {
		initExtentMap();
		Collection<EObject> collection = extentMap.get(type);
		return collection == null ? Collections.<EObject> emptySet()
				: collection;
	}

	/**
	 * Add a collection of metamodel elements to the extent
	 */
	public void addToExtent(Collection<EObject> elements) {
		for (EObject element : elements) {
			addToExtent(element);
		}
	}

	/**
	 * Add a metamodel element to the extent
	 */
	public void addToExtent(EObject element) {
		EClass eClass = element.eClass();
		add(element, eClass);
		for (EClass superClass : eClass.getEAllSuperTypes()) {
			add(element, superClass);
		}
		if (element instanceof EPackage && element.eContainer() == null) {
			if (!rootPackages.contains(element)) {
				addRootPackage((EPackage) element);
			}
		}
	}

	/**
	 * Add a metamodel element to the extent for a certain class (helper method)
	 */
	private void add(EObject element, EClass eClass) {
		Collection<EObject> collection = extentMap.get(eClass);
		if (collection == null) {
			collection = new HashSet<EObject>();
			extentMap.put(eClass, collection);
		}
		collection.add(element);
	}

	/**
	 * Remove a collection of element from the extent
	 * 
	 * @param elements
	 *            Collection of objects
	 */
	public void removeFromExtent(Collection<EObject> elements) {
		for (EObject element : elements) {
			removeFromExtent(element);
		}
	}

	/**
	 * Remove a metamodel element from the extent
	 */
	public void removeFromExtent(EObject element) {
		EClass eClass = element.eClass();
		remove(element, eClass);
		for (EClass superClass : eClass.getEAllSuperTypes()) {
			remove(element, superClass);
		}
	}

	/**
	 * Remove a metamodel element from the extent for a certain clas (helper
	 * method)
	 */
	private void remove(EObject element, EClass eClass) {
		Collection<EObject> collection = extentMap.get(eClass);
		collection.remove(element);
		if (collection.isEmpty()) {
			extentMap.remove(collection);
		}
	}

	/**
	 * Perform a full recalculation of the extent map starting from the root
	 * packages
	 */
	private void initExtentMap() {
		if (extentMap.isEmpty()) {
			for (EClassifier classifier : EcorePackage.eINSTANCE
					.getEClassifiers()) {
				if (classifier instanceof EDataType) {
					addToExtent(classifier);
				}
			}

			for (EPackage rootPackage : rootPackages) {
				addToExtent(rootPackage);
				for (Iterator<EObject> i = rootPackage.eAllContents(); i
						.hasNext();) {
					EObject element = i.next();
					addToExtent(element);
				}
			}
		}
	}

	/**
	 * Dispose the extent
	 */
	public void dispose() {
		clearExtentMap();
		rootPackages = null;
	}

	/**
	 * Clear the extent map
	 */
	public void clearExtentMap() {
		extentMap.clear();
	}

	/**
	 * Determine whether the metamodel is consistent, i.e. fulfills the
	 * constraints defined by the meta-metamodel
	 * 
	 * @return true if it is consistent, false otherwise
	 */
	public boolean isConsistent() {
		BasicDiagnostic diagnostic = new BasicDiagnostic();
		Diagnostician diagnostician = new Diagnostician();
		for (EPackage p : getRootPackages()) {
			diagnostician.validate(p, diagnostic);
		}
		return diagnostic.getSeverity() == Diagnostic.OK
				|| diagnostic.getSeverity() == Diagnostic.WARNING;
	}
}
