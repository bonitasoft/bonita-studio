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

import java.util.Optional;

import org.bonitasoft.studio.common.Activator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.services.log.Logger;

/**
 * Logger utilities. Prefer logging with Class rather than Bundle name/id.
 * 
 * @author Romain Bioteau
 */
public class BonitaStudioLog {

    /**
     * Get a logger from a java class
     * 
     * @param clazz a class to get logger from
     * @return logger
     */
    public static Logger getLogger(final Class<?> clazz) {
        return BonitaStudioLogger.getLogger(clazz);
    }

    /**
     * Get a logger from a bundle name
     * 
     * @param bundleName name of a bundle to get logger from
     * @return logger
     */
    public static Logger getLogger(final String bundleName) {
        return BonitaStudioLogger.getLogger(bundleName);
    }

    /**
     * Log an information
     *
     * @param message the message to log
     */
    public static void log(final String message) {
        info(message);
    }

    /**
     * Log an information
     *
     * @param message the message to log
     */
    public static void info(final String message) {
        info(message, BonitaStudioLog.class);
    }

    /**
     * Log an information
     *
     * @param message the message to log
     * @param clazz a class to get logger from
     */
    public static void info(final String message, final Class<?> clazz) {
        final Logger logger = getLogger(clazz);
        logInfo(logger, message);
    }

    /**
     * Log an information
     *
     * @param message the message to log
     * @param bundleId the bundle id of the original exception to log
     */
    public static void info(final String message, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        logInfo(logger, message);
    }

    private static void logInfo(final Logger logger, final String message) {
        Optional.ofNullable(logger).ifPresentOrElse(
                l -> l.info(message),
                () -> System.out.println(message));
    }

    public static void error(final Throwable exception) {
        error(exception, BonitaStudioLog.class);
    }

    /**
     * Log an error
     *
     * @param exception the exception to log
     * @param clazz a class to get logger from
     */
    public static void error(final Throwable exception, final Class<?> clazz) {
        final Logger logger = getLogger(clazz);
        logError(logger, null, exception);
    }

    /**
     * Log an error
     *
     * @param exception the exception to log
     * @param bundleId the bundle id of the original exception to log
     */
    public static void error(final Throwable exception, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        logError(logger, null, exception);
    }

    /**
     * Log an error
     *
     * @param exception the exception to log
     * @param clazz a class to get logger from
     */
    public static void error(final String message, final Throwable exception, final Class<?> clazz) {
        final Logger logger = getLogger(clazz);
        logError(logger, message, exception);
    }

    /**
     * Log an error
     *
     * @param exception the exception to log
     * @param clazz a class to get logger from
     */
    public static void error(final String message, final Throwable exception, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        logError(logger, message, exception);
    }

    public static void error(final String message, final Throwable exception) {
        error(message, exception, BonitaStudioLog.class);
    }

    /**
     * Log an error
     *
     * @param message the message to log
     * @param bundleId the bundle id of the original exception to log
     */
    public static void error(final String message, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        logError(logger, message, null);
    }

    /**
     * Log an error
     *
     * @param message the message to log
     * @param clazz a class to get logger from
     */
    public static void error(final String message, final Class<?> clazz) {
        final Logger logger = getLogger(clazz);
        logError(logger, message, null);
    }

    private static void logError(final Logger logger, final String message, final Throwable t) {
        Optional.ofNullable(logger).ifPresentOrElse(
                l -> l.error(t, message),
                () -> {
                    Optional.ofNullable(message).ifPresent(System.err::println);
                    Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
                });
    }

    /**
     * Log a warning
     *
     * @param message the message to log
     * @param clazz a class to get logger from
     */
    public static void warning(final String message, final Class<?> clazz) {
        final Logger logger = getLogger(clazz);
        logWarning(logger, message);
    }

    /**
     * Log a warning
     *
     * @param message the message to log
     * @param bundleId the bundle id of the original exception to log
     */
    public static void warning(final String message, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        logWarning(logger, message);
    }

    private static void logWarning(final Logger logger, final String message) {
        Optional.ofNullable(logger).ifPresentOrElse(
                l -> l.warn(message),
                () -> System.out.println(message));
    }

    /**
     * Log a debug information
     *
     * @param message the message to log
     * @param clazz a class to get logger from
     */
    public static void debug(final String message, final Class<?> clazz) {
        final Logger logger = getLogger(clazz);
        logDebug(logger, message, null);
    }

    /**
     * Log a debug information
     *
     * @param message the message to log
     * @param bundleId the bundle id of the original exception to log
     */
    public static void debug(final String message, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        logDebug(logger, message, null);
    }

    /**
     * Log a debug information
     *
     * @param message the message to log
     * @param throwable the non-fatal exception to log
     * @param clazz a class to get logger from
     */
    public static void debug(final String message, final Throwable throwable, final Class<?> clazz) {
        final Logger logger = getLogger(clazz);
        logDebug(logger, message, throwable);
    }

    /**
     * Log a debug information
     *
     * @param message the message to log
     * @param throwable the non-fatal exception to log
     * @param bundleId the bundle id of the original exception to log
     */
    public static void debug(final String message, final Throwable throwable, final String bundleId) {
        final Logger logger = getLogger(bundleId);
        logDebug(logger, message, throwable);
    }

    private static void logDebug(final Logger logger, final String message, final Throwable t) {
        Optional.ofNullable(logger).ifPresentOrElse(
                l -> {
                    if (t != null) {
                        l.debug(t, message);
                    } else {
                        //debug log are not shown otherwise
                        l.info(message);
                    }
                },
                () -> System.out.println(message));
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

}
