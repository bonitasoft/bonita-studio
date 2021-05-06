/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.log;

import org.bonitasoft.studio.common.Activator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.internal.workbench.WorkbenchLogger;

/**
 * @author Romain Bioteau
 */
public class BonitaStudioLog {

    public static void log(final String message) {
        final Logger logger = getLogger(Activator.PLUGIN_ID);
        if (logger != null) {
            logger.info(message);
        } else {
            System.out.println(message);
        }
    }

    public static void error(final Throwable exception) {
        final Logger logger = getLogger(Activator.PLUGIN_ID);
        if (logger != null) {
            logger.error(exception);
        } else {
            exception.printStackTrace();
        }
    }

    /**
     * Log an error
     *
     * @param exception - the exception to log
     * @param bundleId - the bundle id of the original exception to log
     */
    public static void error(final Throwable exception, final String bundleId) {
        error(exception);
    }

    public static void error(final String message, final Throwable t, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        if (logger != null) {
            logger.error(t, message);
        } else {
            System.err.println(message);
            t.printStackTrace();
        }
    }

    public static void error(final String message, final Throwable t) {
        final Logger logger = getLogger(Activator.PLUGIN_ID);
        if (logger != null) {
            logger.error(t, message);
        } else {
            System.err.println(message);
            t.printStackTrace();
        }
    }

    public static Logger getLogger(final String bundleName) {
        return BonitaStudioLogger.getLogger(bundleName);
    }

    /**
     * Log an error
     *
     * @param message - the message to log
     * @param bundleId - the bundle id of the original exception to log
     */
    public static void error(final String message, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        if (logger != null) {
            logger.error(message);
        } else {
            System.err.println(message);
        }
    }

    /**
     * Log an information
     *
     * @param message - the message to log
     * @param bundleId - the bundle id of the original exception to log
     */
    public static void info(final String message, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        if (logger != null) {
            logger.info(message);
        }else {
            System.out.println(message);
        }
    }

    /**
     * Log a warning
     *
     * @param message - the message to log
     * @param bundleId - the bundle id of the original exception to log
     */
    public static void warning(final String message, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        if (logger != null) {
            logger.warn(message);
        } else {
            System.out.println(message);
        }
    }

    /**
     * Log a debug information
     *
     * @param message - the message to log
     * @param bundleId - the bundle id of the original exception to log
     */
    public static void debug(final String message, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        if (logger != null) {
            //debug log are not shown otherwise
            logger.info(message);
        } else {
            System.out.println(message);
        }
    }

    public static void debug(final String message, final Throwable throwable, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        if (logger != null) {
            logger.debug(throwable, message);
        } else {
            System.out.println(message);
        }
    }

    public static boolean isLoggable(final int level) {
        final Logger logger = getLogger(Activator.PLUGIN_ID);
        if (logger != null) {
            switch (level) {
                case IStatus.ERROR:
                    return logger.isErrorEnabled();
                case IStatus.WARNING:
                    return logger.isWarnEnabled();
                case IStatus.INFO:
                default:
                    return logger.isInfoEnabled();
            }
        }
        return false;
    }

    public static void setLogger(final WorkbenchLogger workbenchLogger) {
        BonitaStudioLogger.setLogger(workbenchLogger);
    }

}
