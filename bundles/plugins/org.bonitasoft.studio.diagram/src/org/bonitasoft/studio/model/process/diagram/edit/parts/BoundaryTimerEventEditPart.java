/*
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
package org.bonitasoft.studio.model.process.diagram.edit.parts;

import org.bonitasoft.studio.common.diagram.ActivityCursorMouseMotionListener;
import org.bonitasoft.studio.common.diagram.tools.DragEditPartsTrackerExWithoutCopyWithModKeyPressed;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.model.process.diagram.edit.policies.BoundaryTimerEventItemSemanticEditPolicy;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.BorderedBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.lite.svg.SVGFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class BoundaryTimerEventEditPart extends BorderedBorderItemEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 3043;

	/**
	* @generated
	*/
	protected IFigure contentPane;

	/**
	* @generated
	*/
	protected IFigure primaryShape;

	/**
	* @generated
	*/
	public BoundaryTimerEventEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, getPrimaryDragEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new BoundaryTimerEventItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	* @generated
	*/
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {

				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	* @generated
	*/
	protected IFigure createNodeShape() {
		return primaryShape = new BoundaryTimerEventFigure();
	}

	/**
	* @generated
	*/
	public BoundaryTimerEventFigure getPrimaryShape() {
		return (BoundaryTimerEventFigure) primaryShape;
	}

	/**
	* @generated
	*/
	protected void addBorderItem(IFigure borderItemContainer, IBorderItemEditPart borderItemEditPart) {
		if (borderItemEditPart instanceof BoundaryTimerEventLabelEditPart) {
			BorderItemLocator locator = new BorderItemLocator(getMainFigure(), PositionConstants.SOUTH);
			locator.setBorderItemOffset(new Dimension(-20, -20));
			borderItemContainer.add(borderItemEditPart.getFigure(), locator);
		} else {
			super.addBorderItem(borderItemContainer, borderItemEditPart);
		}
	}

	/**
	* @generated
	*/
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(25, 25);

		//FIXME: workaround for #154536
		result.getBounds().setSize(result.getPreferredSize());
		return result;
	}

	public EditPolicy getPrimaryDragEditPolicy() {
		return new BorderItemSelectionEditPolicy() {
			@Override
			protected void showPrimarySelection() {
				super.showPrimarySelection();
				hideSelection();
				IFigure layer = getLayer(LayerConstants.HANDLE_LAYER);
				layer.setBackgroundColor(ColorConstants.black);
				layer.setVisible(true);
				for (Object f : layer.getChildren()) {
					((IFigure) f).setBackgroundColor(ColorConstants.black);
					((IFigure) f).setVisible(true);
				}
			}

			@Override
			protected IFigure createDragSourceFeedbackFigure() {
				IFigure r = FiguresHelper.getSelectedFigure(resolveSemanticElement().eClass(), -1, -1, null, null);
				addFeedback(r);
				return r;
			}
		};
	}

	/**
	* Creates figure for this edit part.
	* 
	* Body of this method does not depend on settings in generation model
	* so you may safely remove <i>generated</i> tag and modify it.
	* 
	* @generated
	*/
	protected NodeFigure createMainFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	* Default implementation treats passed figure as content pane.
	* Respects layout one may have set for generated figure.
	* @param nodeShape instance of generated figure class
	* @generated
	*/
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (VISUAL_ID != 3007 && VISUAL_ID != 2007) {
			getPrimaryShape().addMouseMotionListener(new ActivityCursorMouseMotionListener());
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	* @generated
	*/
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	* @generated
	*/
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	* @generated
	*/
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	* @generated
	*/
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	* @generated
	*/
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	* @generated
	*/
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(ProcessVisualIDRegistry.getType(BoundaryTimerEventLabelEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class BoundaryTimerEventFigure extends SVGFigure {

		/**
		 * @generated
		 */
		public BoundaryTimerEventFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			this.setLayoutManager(layoutThis);

			this.setURI("platform:/plugin/org.bonitasoft.studio.pics/icons/figures/BoundaryTimerEvent.svgz");
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(25), getMapMode().DPtoLP(25)));
			this.setMaximumSize(new Dimension(getMapMode().DPtoLP(25), getMapMode().DPtoLP(25)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			WrappingLabel boundaryTimerEventNameFigure0 = new WrappingLabel();

			boundaryTimerEventNameFigure0.setText("");

			GridData constraintBoundaryTimerEventNameFigure0 = new GridData();
			constraintBoundaryTimerEventNameFigure0.verticalAlignment = GridData.CENTER;
			constraintBoundaryTimerEventNameFigure0.horizontalAlignment = GridData.CENTER;
			constraintBoundaryTimerEventNameFigure0.horizontalIndent = 0;
			constraintBoundaryTimerEventNameFigure0.horizontalSpan = 1;
			constraintBoundaryTimerEventNameFigure0.verticalSpan = 1;
			constraintBoundaryTimerEventNameFigure0.grabExcessHorizontalSpace = true;
			constraintBoundaryTimerEventNameFigure0.grabExcessVerticalSpace = true;
			this.add(boundaryTimerEventNameFigure0, constraintBoundaryTimerEventNameFigure0);

		}

	}

	/*
	* (non-Javadoc)
	* 
	* @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
	*/
	@Override
	public DragTracker getDragTracker(Request request) {
		return new DragEditPartsTrackerExWithoutCopyWithModKeyPressed(this);
	}

	/**
	 * @Generated BonitaSoft
	 */
	@Override
	public java.lang.Object getAdapter(Class key) {

		if (key == SnapToHelper.class) {
			EditPart parent = getParent();
			while (!(parent instanceof DiagramEditPart)) {
				parent = parent.getParent();
			}
			return GMFTools.getSnapHelper((GraphicalEditPart) parent);
		}

		return super.getAdapter(key);
	}
}
