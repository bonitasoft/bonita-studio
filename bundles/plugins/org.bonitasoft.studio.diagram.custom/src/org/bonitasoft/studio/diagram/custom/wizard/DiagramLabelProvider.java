/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.wizard;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class DiagramLabelProvider extends StyledCellLabelProvider implements
		ILabelProvider {

	private LabelProvider fileStoreLabelProvider;


	public DiagramLabelProvider(LabelProvider fileStoreLabelProvider){
		this.fileStoreLabelProvider = fileStoreLabelProvider;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		return fileStoreLabelProvider.getImage(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		return fileStoreLabelProvider.getText(element);
	}
	

	@Override
	public void update(ViewerCell cell) {
        if (cell.getElement() instanceof DiagramFileStore) {
        	DiagramFileStore filseStore = (DiagramFileStore) cell.getElement();
            StyledString styledString = new StyledString();

            styledString.append(fileStoreLabelProvider.getText(filseStore), null);
            cell.setText(styledString.getString());
            cell.setImage(fileStoreLabelProvider.getImage(filseStore)) ;
            cell.setStyleRanges(styledString.getStyleRanges());
        }
		super.update(cell);
	}

}
