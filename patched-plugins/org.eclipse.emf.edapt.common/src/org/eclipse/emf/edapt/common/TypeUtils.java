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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Helper methods for inheritance of Ecore classes
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating RED Rev:
 */
public final class TypeUtils {

	/**
	 * Constructor
	 */
	private TypeUtils() {
		// hidden, since this class only provides static helper methods
	}

	/**
	 * Get the wrapped least common ancestor of a collection of classes
	 * 
	 * @param classes
	 *            Collection of classes
	 * @return Wrapped least common ancestor
	 */
	public static EClass leastCommonAncestor(Collection<EClass> classes) {
		return wrap(leastCommonAncestorHelper(classes));
	}

	/**
	 * Get the least common ancestor of a collection of classes 
	 * 
	 * @param classes Collection of classes
	 * @return Least common ancestor
	 */
	private static EClass leastCommonAncestorHelper(Collection<EClass> classes) {
		if(classes.isEmpty()) {
			return null;
		}
		else if(classes.size() == 1) {
			return classes.iterator().next();
		}
		else {
			Iterator<EClass> i = classes.iterator();
			EClass lca = i.next();
			while(i.hasNext()) {
				if(lca == null) {
					break;
				}
				lca = leastCommonAncestorHelper(lca, i.next());
			}
			return lca;
		}
	}

	/**
	 * Get the wrapped least common ancestor of two classes
	 * 
	 * @param class1 First class
	 * @param class2 Second class
	 * @return Wrapped least common ancestor
	 */
	public static EClass leastCommonAncestor(EClass class1, EClass class2) {
		return wrap(leastCommonAncestorHelper(class1, class2));
	}

	/**
	 * Helper method to wrap a null result by the EObject class (ancestor of all classes)
	 * 
	 * @param c Class
	 * @return Replaced class
	 */
	static EClass wrap(EClass c) {
		return (c == null) ? EcorePackage.eINSTANCE.getEObject() : c;
	}

	/**
	 * Get the least common ancestor of two classes
	 * 
	 * @param class1 First class
	 * @param class2 Second class
	 * @return Least common ancestor
	 */
	static EClass leastCommonAncestorHelper(EClass class1, EClass class2) {
		if(ancestor(class1, class2)) {
			return class2;
		}
		else if(ancestor(class2, class1)) {
			return class1;
		}
		else {
			EClass ancestor1 = superClass(class1);
			while(ancestor1 != null) {
				if(ancestor(class2, ancestor1)) {
					return ancestor1;
				}
				ancestor1 = superClass(ancestor1);
			}
			
			EClass ancestor2 = superClass(class2);
			while(ancestor2 != null) {
				if(ancestor(class1, ancestor2)) {
					return ancestor2;
				}
				ancestor2 = superClass(ancestor2);
			}
			
			return null;
		}
	}

	/**
	 * Decide whether the second class is an ancestor of the first class
	 * 
	 * @param class1 First class
	 * @param class2 Second class
	 * @return true if the second class is an ancestor of the first class, false otherwise
	 */
	public static boolean ancestor(EClass class1, EClass class2) {
		if(class1 == class2) {
			return true;
		}
		else if(class2 == EcorePackage.eINSTANCE.getEObject()) {
			return true;
		}
		else if(class2 == null) {
			return true;
		}
		else if(class1 == null) {
			return false;
		}
		else {
			return ancestor(superClass(class1), class2);
		}
	}

	/**
	 * Get superclass of a class (multiple inheritance is not used in the Ecore core model)
	 * 
	 * @param c class
	 * @return Super class
	 */
	static EClass superClass(EClass c) {
		if(c.getESuperTypes().isEmpty()) {
			return null;
		}
		return c.getESuperTypes().get(0);
	}
	
	/**
	 * Calculate the common super class of a list of instances
	 * 
	 * @param elements List of instances
	 * @return Common super class
	 */
	public static EClass commonSuperClass(List<EObject> elements) {
		Set<EClass> classes = new HashSet<EClass>();
		for(Iterator<EObject> i = elements.iterator(); i.hasNext(); ) {
			classes.add(i.next().eClass());
		}
		return leastCommonAncestor(classes);
	}

	/**
	 * Check whether a type can be used in the context where another type is expected
	 * 
	 * @param classifier Actual type
	 * @param withClassifier Expected type
	 * @return true if the actual type is compatible with the expected type, false otherwise
	 */
	public static boolean isCompatible(EClassifier classifier, EClassifier withClassifier) {
		if(classifier instanceof EDataType) {
			return classifier == withClassifier;
		}
		EClass clazz = (EClass) classifier;
		if(withClassifier instanceof EClass) {
			EClass withClazz = (EClass) withClassifier;
			return ancestor(clazz, withClazz);
		}
		return false;
	}
}
