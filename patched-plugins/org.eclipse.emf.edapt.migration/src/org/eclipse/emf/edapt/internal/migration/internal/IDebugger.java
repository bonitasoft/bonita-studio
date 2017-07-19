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

import org.eclipse.emf.edapt.spi.migration.Instance;

/**
 * Debugger for model migrations.
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 47D3E8208842791C60FCA0C718D4BD6A
 */
public interface IDebugger {

	/**
	 * Show debug information.
	 *
	 * @param context
	 *            The context instance
	 * @param message
	 *            A message
	 */
	public void debug(Instance context, String message);
}
