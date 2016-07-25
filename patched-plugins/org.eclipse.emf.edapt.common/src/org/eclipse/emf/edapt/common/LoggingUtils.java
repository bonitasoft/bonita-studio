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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * Helper methods to issue log entries.
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating RED Rev:
 */
public final class LoggingUtils {

	/** Constructor. */
	private LoggingUtils() {
		// hidden, since this class only provides static helper methods
	}

	/** Log information. */
	public static void logInfo(Plugin plugin, String message) {
		log(plugin, IStatus.INFO, IStatus.OK, message, null);
	}

	/** Log error. */
	public static void logError(Plugin plugin, Throwable exception) {
		logError(plugin, exception.getMessage(), exception);
	}

	/** Log error. */
	public static void logError(Plugin plugin, String message,
			Throwable exception) {
		log(plugin, IStatus.ERROR, IStatus.OK, message, exception);
	}

	/** Log error. */
	public static void logError(Plugin plugin, String message) {
		logError(plugin, message, null);
	}

	/** Log event. */
	public static void log(Plugin plugin, int severity, int code,
			String message, Throwable exception) {
		log(plugin, createStatus(plugin, severity, code, message, exception));
	}

	/** Log status. */
	public static void log(Plugin plugin, IStatus status) {
		if (plugin == null) {
			return;
		}
		plugin.getLog().log(status);
	}

	/** Create status. */
	public static IStatus createStatus(Plugin plugin, int severity, int code,
			String message, Throwable exception) {
		if (plugin == null) {
			return null;
		}
		String pluginId = plugin.getBundle().getSymbolicName();
		return new Status(severity, pluginId, code, message, exception);
	}
}
