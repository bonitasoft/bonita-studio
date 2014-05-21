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
package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.common.diagram.tools.CustomTextDirectEditManager;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ProcessEditPartFactory;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationText2EditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Aurelien Pupier
 * Allow wrap text.
 */
public class CustomTextAnnotationText2EditPart extends
		TextAnnotationText2EditPart {
	
	private DirectEditManager manager;
	
	public CustomTextAnnotationText2EditPart(View view) {
		super(view);
	}

	@Override
	public void setLabel(WrappingLabel figure) {
		figure.setTextWrap(true);
		super.setLabel(figure);
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityName2EditPart#getManager()
	 */
	protected DirectEditManager getManager() {
		if (manager == null) {
			/*Create custom text director which use wrap label editor*/
			setManager(new CustomTextDirectEditManager(this, CustomTextDirectEditManager
					.getTextCellEditorClass(this), ProcessEditPartFactory
					.getTextCellEditorLocator(this)));
		}
		return manager;
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityName2EditPart#setManager(org.eclipse.gef.tools.DirectEditManager)
	 */
	protected void setManager(DirectEditManager manager) {
		this.manager = manager;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart#isSelectable()
	 */
	@Override
	public boolean isSelectable() {
		return false;
	}
	
}
