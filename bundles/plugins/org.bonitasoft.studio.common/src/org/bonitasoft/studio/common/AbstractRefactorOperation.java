/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author aurelie Zara
 *
 */
public abstract class AbstractRefactorOperation implements
		IRunnableWithProgress {

	public static final String EMPTY_VALUE = "     ";
	
	protected String newValue;
	protected CompoundCommand cc;
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		

	}
	
	public void setNewValue(String newValue){
		this.newValue = newValue;
	}
	
	public void setCompoundCommand(CompoundCommand cc){
		this.cc=cc;
	}

}
