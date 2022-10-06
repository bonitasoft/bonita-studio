/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.core;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Same as org.eclipse.jface.operation.IRunnableWithProgress but without JFace UI dependency.
 * Use with {@link Adapters} if you need a org.eclipse.jface.operation.IRunnableWithProgress, or just use the operation "::run".
 * 
 * @author Vincent Hemery
 */
@FunctionalInterface
public interface IRunnableWithProgress {

    public void run(IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException;
}
