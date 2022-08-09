/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.refactoring.ui;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;

/**
 * @author aurelie
 *
 */

public class DiffTreeLabelProvider  extends StyledCellLabelProvider implements ILabelProvider  {

	private static Styler boldgreen = new StyledString.Styler(){

		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.font = BonitaStudioFontRegistry.getActiveFont();
		}
	};

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof DiffNode){
			DiffNode node = ((DiffNode)element);
			if (node.getLeft()!=null){
				return node.getImage();
			} else {
				return node.getAncestor().getImage();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof DiffNode){
			DiffNode node = ((DiffNode)element);
			if (node.getLeft()!=null){
				return node.getName();
			} else {
				return node.getAncestor().getName();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
		
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {
		
		
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.StyledCellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
	 */
	@Override
	public void update(ViewerCell cell) {
		StyledString styledString = new StyledString();
		
		DiffNode node = (DiffNode)cell.getElement();
		if (node.getLeft()!=null){
			styledString.append(getText(cell.getElement()),boldgreen);
		} else {
			styledString.append(getText(cell.getElement()));
		}
		cell.setText(styledString.getString());
		cell.setImage(getImage(cell.getElement())) ;
		cell.setStyleRanges(styledString.getStyleRanges());
		
	}
	
	
}
