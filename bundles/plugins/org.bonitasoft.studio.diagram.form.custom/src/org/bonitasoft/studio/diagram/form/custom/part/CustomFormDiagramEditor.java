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
package org.bonitasoft.studio.diagram.form.custom.part;

import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProvider;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;

/**
 * @author Aurelien Pupier
 * Make it implements IProvider in order to use gmf editorsProvider
 */
public class CustomFormDiagramEditor extends FormDiagramEditor implements
		IProvider {
	
	public static String ID = "org.bonitasoft.studio.diagram.form.custom.part.CustomFormDiagram";
	
	public void addProviderChangeListener(IProviderChangeListener listener) {	
	}

	public boolean provides(IOperation operation) {
		// TODO : check that it is a "right" operation to open with this.
		return true;
	}

	public void removeProviderChangeListener(IProviderChangeListener listener) {
	}
	

	
}
