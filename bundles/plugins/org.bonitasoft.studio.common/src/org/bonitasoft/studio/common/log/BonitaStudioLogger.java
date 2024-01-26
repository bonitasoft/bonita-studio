/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import jakarta.annotation.PostConstruct;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.services.log.ILoggerProvider;
import org.eclipse.e4.core.services.log.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

/**
 * @author Romain Bioteau
 * @author Vincent Hemery
 */
public class BonitaStudioLogger {

    /** Provides loggers */
    private static ILoggerProvider loggerProvider;

    @PostConstruct
    protected void init(final ILoggerProvider loggerProvider) {
        BonitaStudioLogger.loggerProvider = loggerProvider;
    }

    /**
     * Get a logger from a java class
     * 
     * @param clazz a class to get logger from
     * @return logger
     */
    public static Logger getLogger(final Class<?> clazz) {
        // loggerProvider should be initialized by DI, but may be null e.g. in unit tests
        return Optional.ofNullable(loggerProvider).map(p -> {
            try {
                return p.getClassLogger(clazz);
            } catch (Exception e) {
                // may happen on disposal
                return null;
            }
        }).orElse(null);
    }

    /**
     * Get a logger from a bundle name
     * 
     * @param bundleName name of a bundle to get logger from
     * @return logger
     */
    public static Logger getLogger(final String bundleName) {
        Class<?> clazz = BonitaStudioLogger.class;
        Bundle bundle = Platform.getBundle(bundleName);
        if (bundle != null) {
            String activator = bundle.getHeaders().get(Constants.BUNDLE_ACTIVATOR);
            if (activator != null) {
                try {
                    clazz = bundle.loadClass(activator);
                } catch (ClassNotFoundException e) {
                    getLogger(BonitaStudioLogger.class).error(e);
                }
            }
        }
        return getLogger(clazz);
    }

}
