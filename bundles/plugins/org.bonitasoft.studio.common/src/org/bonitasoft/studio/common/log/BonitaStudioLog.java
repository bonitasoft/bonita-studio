/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.log;

import java.security.AccessController;

import org.bonitasoft.studio.common.Activator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.framework.log.FrameworkLogEntry;
import org.eclipse.osgi.framework.util.SecureAction;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaStudioLog {

	/** The system property used to specify the log level */
	private static final String PROP_LOG_LEVEL = "eclipse.log.level"; //$NON-NLS-1$
	private static final SecureAction secureAction = AccessController.doPrivileged(SecureAction.createSecureAction());
	private static int logLevel ;
	static{
		String newLogLevel = secureAction.getProperty(PROP_LOG_LEVEL);
		if (newLogLevel != null) {
			if (newLogLevel.equals("ERROR")) {
				logLevel = FrameworkLogEntry.ERROR;
			} else if (newLogLevel.equals("WARNING")) {
				logLevel = FrameworkLogEntry.ERROR | FrameworkLogEntry.WARNING;
			} else if (newLogLevel.equals("INFO")) {
				logLevel = FrameworkLogEntry.INFO | FrameworkLogEntry.ERROR | FrameworkLogEntry.WARNING | FrameworkLogEntry.CANCEL;
			}
			else {
				logLevel = FrameworkLogEntry.OK; // OK (0) means log everything
			}
		}
	}

	public static void log(String message) {
		ILog logger = Activator.getDefault().getLogger();
		if(logger != null){
			logger.log(new Status(IStatus.INFO, Activator.PLUGIN_ID, message));
		}
	}

	public static void error(Throwable exception) {
		final ILog logger = getLogger();
		if(logger != null){
			logger.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, exception.getMessage(),exception));
		}else{
			exception.printStackTrace();
		}
	}

	/**
	 * Log an error
	 * @param exception - the exception to log
	 * @param bundleId - the bundle id of the original exception to log
	 */
	public static void error(Throwable exception,String bundleId){
		final ILog logger = getLogger();
		if(logger != null){
			logger.log(new Status(IStatus.ERROR, bundleId, exception.getMessage(),exception));
		}else{
			exception.printStackTrace();
		}
	}

	protected static ILog getLogger() {
		Activator activator = Activator.getDefault();
		if(activator != null){
			return Activator.getDefault().getLogger();
		}
		return null;
	}

	/**
	 * Log an error
	 * @param message - the message to log
	 * @param bundleId - the bundle id of the original exception to log
	 */
	public static void error(String message,String bundleId){
		final ILog logger = getLogger();
		if(logger != null){
			logger.log(new Status(IStatus.ERROR, bundleId, message));
		}else{
			System.err.println(message);
		}
	}

	/**
	 * Log an information
	 * @param message - the message to log
	 * @param bundleId - the bundle id of the original exception to log
	 */
	public static void info(String message,String bundleId){
		final ILog logger = getLogger();
		if(logger != null){
			logger.log(new Status(IStatus.INFO, bundleId, message));
		}
	}

	/**
	 * Log a warning
	 * @param message - the message to log
	 * @param bundleId - the bundle id of the original exception to log
	 */
	public static void warning(String message,String bundleId){
		final ILog logger = getLogger();
		if(logger != null){
			logger.log(new Status(IStatus.WARNING, bundleId, message));
		}
	}

	/**
	 * Log a debug information
	 * @param message - the message to log
	 * @param bundleId - the bundle id of the original exception to log
	 */
	public static void debug(String message,String bundleId){
		final ILog logger = getLogger();
		if(logger != null){
			logger.log(new Status(IStatus.OK,bundleId, message));
		}
	}

	public static boolean isLoggable(int level) {
		if (logLevel == 0) {
			return true;
		}
		return (level & logLevel) != 0;
	}
}
