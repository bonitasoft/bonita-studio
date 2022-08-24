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
 
package org.bonitasoft.studio.diagram.custom.providers;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.layout.LayoutNodesOperation;
import org.eclipse.gmf.runtime.diagram.ui.providers.TopDownProvider;
import org.eclipse.gmf.runtime.notation.View;


/**
 * @author Romain Bioteau
 *
 */
public class TopDownLayoutProvider extends TopDownProvider{

	public static final String TOP_DOWN_LAYOUT ="TopDown" ;
	
	@Override
	public boolean provides(IOperation operation) {
		 Assert.isNotNull(operation);

	        View cview = getContainer(operation);
	        if (cview == null)
	            return false;


	        IAdaptable layoutHint = ((LayoutNodesOperation) operation).getLayoutHint();
	        String layoutType = (String) layoutHint.getAdapter(String.class);
	        return TOP_DOWN_LAYOUT.equals(layoutType);
	}
	

}
