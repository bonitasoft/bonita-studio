/*
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.process.diagram.form.edit.parts;

import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.diagram.form.edit.policies.CheckBoxSingleFormFieldItemSemanticEditPolicy;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.lite.svg.SVGFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * @generated
 */
public class CheckBoxSingleFormFieldEditPart extends ShapeNodeEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 2130;

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
	public CheckBoxSingleFormFieldEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CheckBoxSingleFormFieldItemSemanticEditPolicy());
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
		return primaryShape = new CheckBoxFigureDescriptor();
	}

	/**
	* @generated
	*/
	public CheckBoxFigureDescriptor getPrimaryShape() {
		return (CheckBoxFigureDescriptor) primaryShape;
	}

	/**
	* @generated
	*/
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof CheckBoxSingleFormFieldNameEditPart) {
			((CheckBoxSingleFormFieldNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureLabelWidgetLabel());
			return true;
		}
		return false;
	}

	/**
	* @generated
	*/
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof CheckBoxSingleFormFieldNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	* @generated
	*/
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	* @generated
	*/
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	* @generated
	*/
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	* @generated
	*/
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	* @generated
	*/
	public EditPolicy getPrimaryDragEditPolicy() {
		EditPolicy result = super.getPrimaryDragEditPolicy();
		if (result instanceof ResizableEditPolicy) {
			ResizableEditPolicy ep = (ResizableEditPolicy) result;
			ep.setResizeDirections(PositionConstants.NONE);
		}
		return result;
	}

	/**
	* Modified using Bonitasoft aspectCreates figure for this edit part.
	* 
	* Body of this method does not depend on settings in generation model
	* so you may safely remove <i>generated</i> tag and modify it.
	* 
	* @generated
	*/
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		/* BonitaSoft
		* add a margin border in order to not hide grid line.
		**/
		figure.setBorder(new MarginBorder(1));
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
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
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
		return getChildBySemanticHint(ProcessVisualIDRegistry.getType(CheckBoxSingleFormFieldNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class CheckBoxFigureDescriptor extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureLabelWidgetLabel;

		/**
		 * @generated
		 */
		public CheckBoxFigureDescriptor() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 2;
			layoutThis.makeColumnsEqualWidth = true;
			this.setLayoutManager(layoutThis);

			this.setOutline(false);
			this.setMaximumSize(new Dimension(getMapMode().DPtoLP(300), getMapMode().DPtoLP(100)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureLabelWidgetLabel = new WrappingLabel();

			fFigureLabelWidgetLabel.setText("");

			fFigureLabelWidgetLabel.setFont(FFIGURELABELWIDGETLABEL_FONT);

			GridData constraintFFigureLabelWidgetLabel = new GridData();
			constraintFFigureLabelWidgetLabel.verticalAlignment = GridData.CENTER;
			constraintFFigureLabelWidgetLabel.horizontalAlignment = GridData.END;
			constraintFFigureLabelWidgetLabel.horizontalIndent = 0;
			constraintFFigureLabelWidgetLabel.horizontalSpan = 1;
			constraintFFigureLabelWidgetLabel.verticalSpan = 1;
			constraintFFigureLabelWidgetLabel.grabExcessHorizontalSpace = false;
			constraintFFigureLabelWidgetLabel.grabExcessVerticalSpace = false;
			constraintFFigureLabelWidgetLabel.widthHint = 150;
			constraintFFigureLabelWidgetLabel.heightHint = -1;
			this.add(fFigureLabelWidgetLabel, constraintFFigureLabelWidgetLabel);

			SVGFigure sVGfigureName0 = new SVGFigure();

			sVGfigureName0.setURI("platform:/plugin/org.bonitasoft.studio.pics/icons/formFields/checkbox.svgz");
			sVGfigureName0.setPreferredSize(new Dimension(getMapMode().DPtoLP(30), getMapMode().DPtoLP(30)));
			sVGfigureName0.setMaximumSize(new Dimension(getMapMode().DPtoLP(250), getMapMode().DPtoLP(22)));
			sVGfigureName0.setMinimumSize(new Dimension(getMapMode().DPtoLP(22), getMapMode().DPtoLP(22)));

			GridData constraintSVGfigureName0 = new GridData();
			constraintSVGfigureName0.verticalAlignment = GridData.CENTER;
			constraintSVGfigureName0.horizontalAlignment = GridData.BEGINNING;
			constraintSVGfigureName0.horizontalIndent = 0;
			constraintSVGfigureName0.horizontalSpan = 1;
			constraintSVGfigureName0.verticalSpan = 1;
			constraintSVGfigureName0.grabExcessHorizontalSpace = true;
			constraintSVGfigureName0.grabExcessVerticalSpace = true;
			constraintSVGfigureName0.widthHint = 150;
			constraintSVGfigureName0.heightHint = 22;
			this.add(sVGfigureName0, constraintSVGfigureName0);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureLabelWidgetLabel() {
			return fFigureLabelWidgetLabel;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGURELABELWIDGETLABEL_FONT = new Font(Display.getCurrent(),
			Display.getDefault().getSystemFont().getFontData()[0].getName(), 13, SWT.NORMAL);

	/**
	* Generated using BonitaSoft aspects
	* 
	* @see ShapeEditPart#refreshBounds()
	*/
	@Override
	protected void refreshBounds() {
		/*Sometimes return a FormImpl : it seems to be an inconsistent state during command execution/composition*/
		if (!(resolveSemanticElement() instanceof Widget))
			return;
		Widget widget = (Widget) resolveSemanticElement();
		int x;
		int y;
		int width;
		int height;
		if (widget.getWidgetLayoutInfo() != null) {
			x = widget.getWidgetLayoutInfo().getColumn();
			y = widget.getWidgetLayoutInfo().getLine();
			height = widget.getWidgetLayoutInfo().getVerticalSpan();
			width = widget.getWidgetLayoutInfo().getHorizontalSpan();
		} else {
			return;
		}
		Dimension size = new Dimension(width, height);
		Point loc = new Point(x, y);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new Rectangle(loc, size));
	}

}
