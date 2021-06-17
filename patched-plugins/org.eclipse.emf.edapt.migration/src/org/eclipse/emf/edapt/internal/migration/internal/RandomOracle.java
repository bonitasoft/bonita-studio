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
package org.eclipse.emf.edapt.internal.migration.internal;

import java.util.List;
import java.util.Random;

import org.eclipse.emf.edapt.spi.migration.Instance;

/**
 * Oracle to perform a random choice.
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 9A6B169DEFB9C692C59DD3847AAE4EA1
 */
public class RandomOracle implements IOracle {

	/** Random generator. */
	private final Random random = new Random();

	/** {@inheritDoc} */
	@Override
	public <V> V choose(Instance instance, List<V> values, String message) {
		final int index = random.nextInt(values.size());
		return values.get(index);
	}
}
