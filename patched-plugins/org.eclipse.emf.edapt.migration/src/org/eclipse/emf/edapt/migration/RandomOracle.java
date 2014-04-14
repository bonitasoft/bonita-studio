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
package org.eclipse.emf.edapt.migration;

import java.util.List;
import java.util.Random;

/**
 * Oracle to perform a random choice.
 * 
 * @author herrmama
 * @author $Author: mherrmannsd $
 * @version $Rev: 138 $
 * @levd.rating YELLOW Hash: 9A6B169DEFB9C692C59DD3847AAE4EA1
 */
public class RandomOracle implements IOracle {

	/** Random generator. */
	private final Random random = new Random();

	/** {@inheritDoc} */
	public <V> V choose(Instance instance, List<V> values, String message) {
		int index = random.nextInt(values.size());
		return values.get(index);
	}
}
