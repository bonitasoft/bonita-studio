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
import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.BonitaUnspecifiedTypeCreationTool;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.tools.ToolAndToolEntry;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Tool;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier : improve resource management (free handles)
 */
public class NextElementEditPolicy extends AbstractSingleSelectionEditPolicy implements ZoomListener,LayerConstants {


	public static final String NEXT_ELEMENT_ROLE = "nextElement"; //$NON-NLS-1$

	private IFigure layer;
	private List<IFigure> figures;
	private List<Tool> tools;
	private IFigure textAnnotationFigure;
	private ZoomManager zoomManager;


	public NextElementEditPolicy() {
		figures = new ArrayList<IFigure>();
		tools = new ArrayList<Tool>();
	}

	@Override
	public void activate() {
		super.activate();
		this.zoomManager = ((DiagramRootEditPart) getHost().getRoot()).getZoomManager();
		zoomManager.addZoomListener(this) ;
	}

	protected void hideFeedback() {
		for (IFigure figure : figures) {
			if (figure != null && figure.getParent() != null) {
				layer.remove(figure);
				if(figure instanceof DraggableElement)
					((DraggableElement) figure).freeHandle();
				if(figure instanceof CustomConnectionHandle){
					((CustomConnectionHandle) figure).freeHandle();
				}
			}
		}
		figures.clear();

		for(Tool tool : tools){
			if(tool != null){
				if(tool instanceof BonitaUnspecifiedTypeCreationTool)
					tool.deactivate();
			}
		}
	}

	protected void showFeedback() {
		if(zoomManager.getZoom() > GMFTools.MINIMAL_ZOOM_DISPLAY){
			// cleans other diagram assistants
			if (layer == null) {
				layer = getLayer(HANDLE_LAYER);
			}
			EObject element = ((GraphicalEditPart) getHost()).resolveSemanticElement();
			boolean toolAreBelow = element instanceof BoundaryEvent;

			List<ToolAndToolEntry> allowedChildren = NextElementPaletteProvider.getAllowedNextTypes((GraphicalEditPart) getHost());
			//Set<Tool> allowedChildren = list.keySet();
			IFigure feedbackFigure = SelectionFeedbackEditPolicy.getFeedbackFigure(getHost());

			if (feedbackFigure == null) {
				feedbackFigure = getHostFigure();
			}
			
			//feedbackFigure.getBounds().performScale(zoomManager.getZoom()) ;
			
			Rectangle b = feedbackFigure.getBounds().getCopy();
			Rectangle bounds = new Rectangle(b.x, b.y, b.width, 70) ;

			//get the absolute coordinate of bounds
			FiguresHelper.translateToAbsolute(feedbackFigure, bounds);
			tools = new ArrayList<Tool>();

			int i = 0;
			for (ToolAndToolEntry tate : allowedChildren) {
				IFigure figure = null;
				if(tate.getTool() instanceof UnspecifiedTypeCreationTool){
					try {
						figure = new DraggableElement((GraphicalEditPart)getHost(), (UnspecifiedTypeCreationTool)tate.getTool(), tate.getToolEntry(), layer,zoomManager);
					} catch (Exception ex) {
						BonitaStudioLog.error(ex);
						Button currentButton = new Button(tate.getToolEntry().getLabel());
						currentButton.setSize(100, 20);
						figure = currentButton;
					}
				}else if(tate.getTool() instanceof UnspecifiedTypeConnectionTool){
					try {
						figure = new CustomConnectionHandle((GraphicalEditPart)getHost(), tate.getToolEntry()) ;
					} catch (Exception ex) {
						BonitaStudioLog.error(ex);
						Button currentButton = new Button(tate.getToolEntry().getLabel());
						currentButton.setSize(100, 20);
						figure = currentButton;
					}
				}
				tools.add(tate.getTool());
				figures.add(figure);
				layer.add(figure);
				figure.setVisible(true);
				if(toolAreBelow){
					bounds = feedbackFigure.getBounds().getCopy();
					figure.setLocation(bounds.getBottomRight().getTranslated(-figure.getSize().width/2 + (2+figure.getBounds().width) * (i-1), 5));
				}else if(tate.getToolEntry().getId().contains("createTextAnnotation")){ //$NON-NLS-1$
					this.textAnnotationFigure = figure;
					figure.setLocation(bounds.getTop().getTranslated(-figure.getSize().width/2,- figure.getSize().height-5));
					i--;
				} else {
					/*Math.max is used in order to avoid the case of an unique next allowed children (currently for message)*/
					// -2 on allowedChildrenSize because text anotation is on the top side
					figure.setLocation(bounds.getTopRight().getTranslated(2, (bounds.height-15) * i / Math.max(allowedChildren.size()-2,1) ));
				}
				i++;
			}
		}
	}

	@Override
	public void deactivate() {
		super.deactivate();
		zoomManager.removeZoomListener(this) ;
		figures.clear();
		layer = null;
		tools.clear();
	}

	public IFigure getTextAnnotationFigure() {
		return this.textAnnotationFigure;
	}
	
	public IFigure getFigure(int index){
		return figures.get(index);
	}
   
    
	public void zoomChanged(double zoom) {
		hideFeedback();
	}
}
