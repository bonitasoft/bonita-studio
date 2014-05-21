/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowLabelEditPart;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CustomMessageFlowLabelEditPart extends MessageFlowLabelEditPart {

	private int max;

	public CustomMessageFlowLabelEditPart(View view) {
		super(view);
	}
	
	protected void setLabelTextHelper(IFigure figure, String text) {
		if (figure instanceof WrappingLabel) {
			int half = figure.getBounds().width /2 ;
			int center = figure.getBounds().x + half ;
			int lenghtPx = center - 35 ;

			max = (int) (lenghtPx/(((WrappingLabel) figure).getFont().getFontData()[0].getHeight()*0.4)) ;
			if(text.length() > max && max-3 > 0 ){
				
				if(text.length()>= max-3)
					text = text.substring(0, max-3);
				
				if(!text.endsWith("...")){ //$NON-NLS-1$
					text = text.concat("..."); //$NON-NLS-1$
				}
			}
			if(center != 0){
				((WrappingLabel) figure).setText(text);
			}else{
				if(text.length() > 20){
					text = text.substring(0,20);
					if(!text.endsWith("...")){ //$NON-NLS-1$
						text = text.concat("..."); //$NON-NLS-1$
					}
					((WrappingLabel) figure).setText(text);
				}else{
					((WrappingLabel) figure).setText(text);
				}
			}
		} else {
			((Label) figure).setText(text);
		}
	}

}
