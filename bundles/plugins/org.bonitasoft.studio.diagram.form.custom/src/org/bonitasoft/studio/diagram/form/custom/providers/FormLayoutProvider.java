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

package org.bonitasoft.studio.diagram.form.custom.providers;

import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.providers.TopDownProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Aurelien Pupier
 *
 */
public class FormLayoutProvider extends TopDownProvider/*AbstractLayoutEditPartProvider*/ {

	
	/** 
	 * Provides only for Form Diagram.
	 * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
	 */
	public boolean provides(IOperation operation) {
		// enable this provider only on form diagrams
        if (operation instanceof ILayoutNodeOperation) {
            Iterator nodes = ((ILayoutNodeOperation) operation)
                .getLayoutNodes().listIterator();
            if (nodes.hasNext()) {
                View node = ((ILayoutNode) nodes.next()).getNode();
                Diagram container = node.getDiagram();
                if (container == null
                    || !(container.getType().equals("Form"))) //$NON-NLS-1$
                    return false;
            }
        } else {
            return false;
        }
        IAdaptable layoutHint = ((ILayoutNodeOperation) operation)
            .getLayoutHint();
        String layoutType = (String) layoutHint.getAdapter(String.class);
        return LayoutType.DEFAULT.equals(layoutType);
	}

}
