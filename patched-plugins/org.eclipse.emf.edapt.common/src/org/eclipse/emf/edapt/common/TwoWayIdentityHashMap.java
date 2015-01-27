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

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * A map that works in both directions
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating RED Rev:
 */
public class TwoWayIdentityHashMap<K, V> extends IdentityHashMap<K, V> implements ReversableMap<K, V> {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -3631090890347846277L;

	/**
	 * Map in the reverse direction
	 */
	private final Map<V, K> reverseMap = new IdentityHashMap<V, K>();

	/**
	 * Constructor
	 */
	public TwoWayIdentityHashMap() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public K reverseGet(V value) {
		return reverseMap.get(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V put(K key, V value) {
		V result = super.put(key, value);
		reverseMap.put(value, key);
		return result;
	}

	/**
	 * Override for efficiency
	 */
	@Override
	public boolean containsValue(Object value) {
		return reverseMap.containsKey(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public V remove(Object key) {
		V value = super.remove(key);
		reverseMap.remove(value);
		return value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		super.clear();
		reverseMap.clear();
	}
}
