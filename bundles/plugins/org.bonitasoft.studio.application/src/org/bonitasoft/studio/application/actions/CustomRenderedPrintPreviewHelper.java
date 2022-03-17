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
 
package org.bonitasoft.studio.application.actions;

import org.eclipse.gmf.runtime.common.ui.action.actions.IPrintActionHelper;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.internal.printpreview.RenderedPrintPreviewHelper;

/**
 * @author Romain Bioteau
 *
 */
public class CustomRenderedPrintPreviewHelper extends RenderedPrintPreviewHelper {

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.printing.internal.printpreview.PrintPreviewHelper#doPrintPreview(org.eclipse.gmf.runtime.common.ui.action.actions.IPrintActionHelper)
	 */
	@Override
	public void doPrintPreview(IPrintActionHelper prActionHelper) {
		setPercentScaling(50);		
		super.doPrintPreview(prActionHelper);
	}


}
