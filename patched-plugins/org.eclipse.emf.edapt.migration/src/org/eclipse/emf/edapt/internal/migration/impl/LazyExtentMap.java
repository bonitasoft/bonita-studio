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
package org.eclipse.emf.edapt.internal.migration.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.Type;

/**
 * An extent of a model that is built lazily.
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 65DEE09316B7F5F9429D1B92F91B6282
 */
public class LazyExtentMap implements Map<EClass, Set<Instance>> {

	/** Delegate map */
	private HashMap<EClass, Set<Instance>> delegate;

	/** Model */
	private final Model model;

	/** Constructor */
	public LazyExtentMap(Model model) {
		this.model = model;
	}

	/** {@inheritDoc} */
	@Override
	public Set<Instance> get(Object arg0) {
		if (delegate == null) {
			init();
		}
		return delegate.get(arg0);
	}

	/** Initialize the map. */
	private void init() {
		delegate = new HashMap<EClass, Set<Instance>>();
		for (final Type type : model.getTypes()) {
			final EClass clazz = type.getEClass();
			final EList<EClass> types = new UniqueEList<EClass>(clazz.getEAllSuperTypes());
			types.add(clazz);
			for (final EClass t : types) {
				Set<Instance> instances = delegate.get(t);
				if (instances == null) {
					instances = new HashSet<Instance>();
					delegate.put(t, instances);
				}
				instances.addAll(type.getInstances());
			}
		}
	}

	// strictly delegating methods

	/** {@inheritDoc} */
	@Override
	public void clear() {
		delegate.clear();
	}

	/** {@inheritDoc} */
	@Override
	public Object clone() {
		return delegate.clone();
	}

	/** {@inheritDoc} */
	@Override
	public boolean containsKey(Object arg0) {
		return delegate.containsKey(arg0);
	}

	/** {@inheritDoc} */
	@Override
	public boolean containsValue(Object arg0) {
		return delegate.containsValue(arg0);
	}

	/** {@inheritDoc} */
	@Override
	public Set<Entry<EClass, Set<Instance>>> entrySet() {
		return delegate.entrySet();
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object arg0) {
		return delegate.equals(arg0);
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		return delegate.hashCode();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/** {@inheritDoc} */
	@Override
	public Set<EClass> keySet() {
		return delegate.keySet();
	}

	/** {@inheritDoc} */
	@Override
	public Set<Instance> put(EClass arg0, Set<Instance> arg1) {
		return delegate.put(arg0, arg1);
	}

	/** {@inheritDoc} */
	@Override
	public void putAll(Map<? extends EClass, ? extends Set<Instance>> arg0) {
		delegate.putAll(arg0);
	}

	/** {@inheritDoc} */
	@Override
	public Set<Instance> remove(Object arg0) {
		return delegate.remove(arg0);
	}

	/** {@inheritDoc} */
	@Override
	public int size() {
		return delegate.size();
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return delegate.toString();
	}

	/** {@inheritDoc} */
	@Override
	public Collection<Set<Instance>> values() {
		return delegate.values();
	}
}
