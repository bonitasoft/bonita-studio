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

import org.eclipse.emf.edapt.spi.migration.Instance;

/**
 * Oracle to choose a value
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: B7494A4503166132FD3B8AE291566C09
 */
public interface IOracle {

	/**
	 * Choose a value.
	 *
	 * @param <V>
	 *            Type of the value
	 * @param context
	 *            The context instance
	 * @param values
	 *            The values to choose from
	 * @param message
	 *            A message
	 * @return Choice The chosen value
	 */
	<V> V choose(Instance context, List<V> values, String message);
}
