/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.core.operation;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class DeployBDMStackTraceResolver {

    public Throwable reduceHibernateException(final InvocationTargetException e) {
        final Throwable targetException = e.getTargetException();
        int index = -1;
        for (int i = 0; i < targetException.getStackTrace().length; i++) {
            final StackTraceElement element = targetException.getStackTrace()[i];
            final String className = element.getClassName();
            if (!isNullOrEmpty(className) && className.contains("org.hibernate.HibernateException")) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            targetException.setStackTrace(
                    Arrays.copyOfRange(targetException.getStackTrace(), index, targetException.getStackTrace().length));
        }
        return targetException;
    }

}
