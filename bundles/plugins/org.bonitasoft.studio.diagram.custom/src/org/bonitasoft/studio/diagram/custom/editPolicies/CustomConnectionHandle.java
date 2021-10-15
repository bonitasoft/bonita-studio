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
 
package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.ArrayList;

import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.handles.ConnectionHandle;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier : improve resource management (free handles)
 */
public class CustomConnectionHandle extends ConnectionHandle {


	private final Image image;
	private final ArrayList<IElementType> types;
	
	public CustomConnectionHandle(IGraphicalEditPart ownerEditPart, ToolEntry toolEntry) {
		super(ownerEditPart, HandleDirection.OUTGOING, null);
		setLocator(new Locator() {
			
			@Override
            public void relocate(IFigure target) {
				target.setLocation(new Point(10, 10));
			}
		});
		setOwner(ownerEditPart);
		setLayoutManager(new StackLayout());
		setSize(16, 16);
		
		
		image = toolEntry.getSmallIcon().createImage();
		final ImageFigure imageFigure = new ImageFigure(image);
		imageFigure.setSize(16, 16);
        setCursor(Pics.getOpenedHandCursor());
		add(imageFigure);
		setLocator(	
				new Locator() {
			
			@Override
            public void relocate(IFigure target) {
				target.setLocation(new Point(10, 10));
			}
		});
		
		setToolTip(null);
		
		types = new ArrayList<>() ;
		types.add(ProcessElementTypes.MessageFlow_4002) ;
		types.add(ProcessElementTypes.SequenceFlow_4001);
		types.add(ProcessElementTypes.TextAnnotationAttachment_4003);
	}
	
	@Override
	public boolean isValid() {
		return true;
	}
	
	

	@Override
	protected DragTracker createDragTracker() {
		return new CustomConnectionHandleTool(getOwner(),types);
	}
	
	public void freeHandle(){
		if(image != null && !image.isDisposed()){
			image.dispose();
		}
	}
	
	

	
}
