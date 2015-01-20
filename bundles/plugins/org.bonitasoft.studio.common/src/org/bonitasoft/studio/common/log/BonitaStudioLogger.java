/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.log;

import java.lang.reflect.Field;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.internal.workbench.WorkbenchLogger;



/**
 * @author Romain Bioteau
 *
 */
public class BonitaStudioLogger {

    private static Logger logger;

    @PostConstruct
    protected void init(final Logger logger) {
        BonitaStudioLogger.logger = logger;
    }

    public static Logger getLogger(final String bundleName) {
        if (logger != null) {
            setBundleName(bundleName);
        }
        return logger;
    }

    protected static void setBundleName(final String bundleName) {
        try {
            final Field bundleNameField = WorkbenchLogger.class.getDeclaredField("bundleName");
            bundleNameField.setAccessible(true);
            bundleNameField.set(logger, bundleName);
        } catch (final NoSuchFieldException e) {
            e.printStackTrace();
        } catch (final SecurityException e) {
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setLogger(final WorkbenchLogger workbenchLogger) {
        BonitaStudioLogger.logger = workbenchLogger;
    }

}
