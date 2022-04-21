/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.internal.migration.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edapt.spi.migration.Instance;

/**
 * A list automatically updating the model.
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 5ADE31BFBF6A91E602D57A9F0C075DBF
 */
public class UpdatingList<E> extends BasicEList<E> {

	/** Serial ID */
	private static final long serialVersionUID = -4463041349924185459L;

	/** Feature */
	private EStructuralFeature feature;

	/** Instance */
	private Instance instance;

	/** Constructor */
	public UpdatingList(Instance instance, EStructuralFeature feature) {
		this(instance, feature, new ArrayList<E>());
	}

	/** Constructor */
	public UpdatingList(Instance instance, EStructuralFeature feature, Collection<E> values) {
		super(values);
		this.instance = instance;
		this.feature = feature;
	}

	/** {@inheritDoc} */
	@Override
	protected void didAdd(int index, Object object) {
		instance.add(feature, index, object);
	}

	/** {@inheritDoc} */
	@Override
	protected void didRemove(int index, Object object) {
		instance.remove(feature, index);
	}

	/** {@inheritDoc} */
	@Override
	protected boolean isUnique() {
		return feature.isUnique();
	}
}
