/*******************************************************************************
 * Copyright (c) 2001, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.views.properties.tabbed.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
 * Provides a section filtering mechanism where the selection is an
 * IStructuredSelection and filtering is based on class.
 *
 * @author Anthony Hunter
 */
public class TabbedPropertyRegistryClassSectionFilter {

	private ITypeMapper typeMapper = null;

	/**
	 * Constructor for TabbedPropertyRegistryClassSectionFilter
	 *
	 * @param typeMapper
	 *            the type mapper.
	 */
	public TabbedPropertyRegistryClassSectionFilter(ITypeMapper typeMapper) {
		super();
		this.typeMapper = typeMapper;
	}

	/**
	 * Verifies if the property section extension represented by sectionElement
	 * applies to the given input.
	 *
	 * @param descriptor
	 *            the section descriptor.
	 * @param selection
	 *            the selection.
	 * @return <code>true</code> if this section applies to the current
	 *         selection.
	 */
	public boolean appliesToSelection(ISectionDescriptor descriptor,
			ISelection selection) {

		if (selection instanceof IStructuredSelection &&
				selection.isEmpty() == false) {

			if (descriptor.getEnablesFor() != ISectionDescriptor.ENABLES_FOR_ANY &&
					((IStructuredSelection) selection).size() != descriptor
							.getEnablesFor()) {
				/**
				 * enablesFor does not match the size of the selection, do not
				 * display section.
				 */
				return false;
			}

			IFilter filter = descriptor.getFilter();

			if (filter != null) {
				for (Iterator i = ((IStructuredSelection) selection).iterator(); i
						.hasNext();) {
					Object object = i.next();

					if (filter != null && filter.select(object) == false) {
						/**
						 * filter fails so section does not apply to the
						 * selection, do not display section.
						 */
						return false;
					}
				}
				/**
				 * filter passes for all objects in the selection.
				 */
				return true;
			}

			Set effectiveTypes = new HashSet();

			for (Iterator i = ((IStructuredSelection) selection).iterator(); i
					.hasNext();) {

				Object object = i.next();

				Class remapType = object.getClass();
				if (typeMapper != null) {
					remapType = typeMapper.mapType(object);
				}

				if (effectiveTypes.add(remapType)) {

					// the effective types of the selection
					if (appliesToEffectiveType(descriptor, remapType) == false) {
						return false;
					}
				}
			}
		} else {
			/* Bug 245690 selection is not a IStructuredSelection */
			if (descriptor.getFilter() != null) {
				return descriptor.getFilter().select(selection);
			}
		}

		return true;
	}

	private boolean appliesToEffectiveType(ISectionDescriptor descriptor,
			Class inputClass) {

		ArrayList classTypes = getClassTypes(inputClass);

		List sectionInputTypes = descriptor.getInputTypes();
		for (Iterator j = sectionInputTypes.iterator(); j.hasNext();) {
			String type = (String) j.next();
			if (classTypes.contains(type)) {
				// found a match
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the classes and interfaces the given target class
	 * extends/implements.
	 */
	protected ArrayList getClassTypes(Class target) {
		ArrayList result = new ArrayList();
		// add classes
		List classes = computeClassOrder(target);
		for (Iterator i = classes.iterator(); i.hasNext();) {
			result.add(((Class) i.next()).getName());
		}
		// add interfaces
		result.addAll(computeInterfaceOrder(classes));
		return result;
	}

	private List computeClassOrder(Class target) {
		List result = new ArrayList(4);
		Class clazz = target;
		while (clazz != null) {
			result.add(clazz);
			clazz = clazz.getSuperclass();
		}
		return result;
	}

	private List computeInterfaceOrder(List classes) {
		List result = new ArrayList(4);
		Map seen = new HashMap(4);
		for (Iterator iter = classes.iterator(); iter.hasNext();) {
			Class[] interfaces = ((Class) iter.next()).getInterfaces();
			internalComputeInterfaceOrder(interfaces, result, seen);
		}
		return result;
	}

	private void internalComputeInterfaceOrder(Class[] interfaces, List result,
			Map seen) {
		List newInterfaces = new ArrayList(seen.size());
		for (Class interfac : interfaces) {
			if (seen.get(interfac) == null) {
				result.add(interfac.getName());
				seen.put(interfac, interfac);
				newInterfaces.add(interfac);
			}
		}
		for (Iterator iter = newInterfaces.iterator(); iter.hasNext();) {
			internalComputeInterfaceOrder(
					((Class) iter.next()).getInterfaces(), result, seen);
		}
	}
}
