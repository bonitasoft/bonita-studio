package org.bonitasoft.studio.diagram.form.custom.editpolicies;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.diagram.form.custom.commands.ChangeSpanCommand;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

public abstract class AbstractChangeSpanOnSelectionEditPolicy extends SelectionEditPolicy {
	
	public static final Object CHANGE_SPAN_ON_SELECTION_FEEDBACK_ROLE = "changeSpanOnselectionFeedback"; //$NON-NLS-1$
	
	/*The list of figure that catch mouse event to launch the command to change the span*/
	protected List<IFigure> figures = new ArrayList<IFigure>();

	public enum SpanAction {
		ADD_LEFT,
		ADD_RIGHT,
		ADD_TOP,
		ADD_BOTTOM,
		REMOVE_LEFT,
		REMOVE_RIGHT,
		REMOVE_TOP,
		REMOVE_BOTTOM
	}
	
	/* The map used to know which place are taken by a widget*/
	protected List<List<Boolean>> map = new ArrayList<List<Boolean>>();
	
	/* The handler layer on which the figures are draw*/
	protected IFigure layer = null;
	
	protected FigureListener figureListener;

	protected IFigure sourceFigure;

	/**
	 * same as focus of SelectionEditPolicy but accessible...
	 */
	protected boolean hasFocus;

	protected int state;
	
	public AbstractChangeSpanOnSelectionEditPolicy(){
		figureListener = new FigureListener() {
			
			public void figureMoved(IFigure source) {
				hideSelection();
				if(hasFocus || state == EditPart.SELECTED || state == EditPart.SELECTED_PRIMARY){
					showSelection();
				}
			}
		};
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideSelection()
	 */
	@Override
	protected void hideSelection() {
		if (layer != null) {
			for (IFigure fig : figures) {
				// fig.erase();
				fig.removeFigureListener(figureListener);
				layer.remove(fig);
			}
			figures.clear();
			layer.repaint();
		}
		layer = null;
		map.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#showSelection()
	 */
	@Override
	protected void showSelection() {
		initSourceFigure();

		
		hideSelection();
		layer = getLayer(LayerConstants.HANDLE_LAYER);
		fillMap();
		/*
		 * need to flush in order to have the new correct size of the host
		 * figure
		 */
		((IGraphicalEditPart) getHost()).getViewer().flush();
		Rectangle ref = getHostFigure().getBounds();

		if (ref.x == 0 && ref.y == 0 && ref.height == 0 && ref.width == 0) {
			return;
		}

		showSelectionForAddLeft();
		showSelectionForAddRight();
		showSelectionForAddTop();
		showSelectionForAddBottom();
		showSelectionForRemoveLeft();
		showSelectionForRemoveRight();
		showSelectionForRemoveBottom();
		showSelectionForRemoveTop();
	}
	
	protected void initSourceFigure() {
		if (sourceFigure == null) {
			sourceFigure = ((IGraphicalEditPart)getHost()).getContentPane();//getHostFigure();
			sourceFigure.addFigureListener(figureListener);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#deactivate()
	 */
	@Override
	public void deactivate() {
		super.deactivate();
		if(sourceFigure != null){
			sourceFigure.removeFigureListener(figureListener);
			sourceFigure = null;
		}
		layer = null;
		figures.clear();	
	}
	
	/**
	 * Convenient method.
	 * @return the Widget relative to the Host.
	 */
	protected Widget getWidget() {
		return (Widget) ((IGraphicalEditPart) getHost()).resolveSemanticElement();
	}
	
	

	/**
	 * Fill the map of the place took by the widgets in the grid.
	 */
	protected void fillMap() {
		map.clear();
		/*init to FALSE*/
		for (int n = 0; n < getNColumn(); n++) {
			List<Boolean> list = new ArrayList<Boolean>(getNLine());
			for (int size = 0; size < getNLine(); size++) {
				list.add(Boolean.FALSE);
			}
			map.add(n, list);
		}
		/*Set to TRUE the place taken*/
		for (Widget w : getWidgets()) {
			WidgetLayoutInfo wLInfo = w.getWidgetLayoutInfo();
			for (int i = 0; i < wLInfo.getHorizontalSpan(); i++) {
				for (int j = 0; j < wLInfo.getVerticalSpan(); j++) {
					int columnIndex = wLInfo.getColumn() + i;
					int lineIndex = wLInfo.getLine() + j;
					if(map.size() > columnIndex && map.get(columnIndex).size() > lineIndex)
						map.get(columnIndex).set(lineIndex, Boolean.TRUE);
				}
			}
		}
	}
	
	protected abstract List<Widget> getWidgets();

	protected abstract int getNLine();

	protected abstract int getNColumn();

	protected Form getForm(){
		EObject o = getWidget();
		while(!(o instanceof Form)){
			o = o.eContainer();
		}
		return (Form) o;
	}
	
	protected void refresh() {
		hideSelection();
		showSelection();
	}
	

	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#setFocus(boolean)
	 */
	@Override
	protected void setFocus(boolean value) {
		hasFocus = value;
		super.setFocus(value);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#setSelectedState(int)
	 */
	@Override
	protected void setSelectedState(int type) {
		state = type;
		super.setSelectedState(type);
	}

	/**
	 * 
	 */
	protected void showSelectionForAddLeft() {
		if (!isOnLeft() && !isPartOnTheLeft()) {
			Rectangle ref = getHostFigure().getBounds();
			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowLeft));
			f.setSize(20, 20);
			f.setLocation(ref.getLeft().translate(-20, -10));
			f.addMouseListener(new MouseListenerForSpan(SpanAction.ADD_LEFT));
			layer.add(f);
			figures.add(f);
		}
	}

	/**
	 * 
	 */
	protected void showSelectionForRemoveLeft() {
		if (canRemoveLeft()) {
			Rectangle ref = getHostFigure().getBounds();
			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowRight));
			f.setSize(20, 20);
			f.setLocation(ref.getLeft().translate(0, -10));
			f.addMouseListener(new MouseListenerForSpan(SpanAction.REMOVE_LEFT));
			layer.add(f);
			figures.add(f);
		}
	}

	protected boolean canRemoveLeft() {
		return getWidgetLayoutInfo().getHorizontalSpan() > 1;
	}

	/**
	 * 
	 */
	protected void showSelectionForAddRight() {
		if (!isOnRight() && !isPartOnTheRight()) {
			Rectangle ref = getHostFigure().getBounds();
			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowRight));
			f.setSize(20, 20);
			f.setLocation(ref.getRight().translate(0, -10));
			f.addMouseListener(new MouseListenerForSpan(SpanAction.ADD_RIGHT));
			layer.add(f);
			figures.add(f);
		}
	}

	/**
	 * 
	 */
	protected void showSelectionForRemoveRight() {
		if (canRemoveRight()) {
			Rectangle ref = getHostFigure().getBounds();
			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowLeft));
			f.setSize(20, 20);
			f.setLocation(ref.getRight().translate(-20, -10));
			f.addMouseListener(new MouseListenerForSpan(SpanAction.REMOVE_RIGHT));
			layer.add(f);
			figures.add(f);
		}
	}

	protected boolean canRemoveRight() {
		return getWidgetLayoutInfo().getHorizontalSpan() > 1;
	}

	/**
	 * 
	 */
	protected void showSelectionForAddBottom() {
		if (!isOnBottom() && !isPartOnTheBottom()) {
			Rectangle ref = getHostFigure().getBounds();
			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowDown));
			f.setSize(20, 20);
			f.setLocation(ref.getBottom().translate(-10, 0));
			f.addMouseListener(new MouseListenerForSpan(SpanAction.ADD_BOTTOM));
			layer.add(f);
			figures.add(f);
		}
	}

	/**
	 * 
	 */
	protected void showSelectionForRemoveBottom() {
		if (canRemoveBottom()) {
			Rectangle ref = getHostFigure().getBounds();
			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowUp));
			f.setSize(20, 20);
			f.setLocation(ref.getBottom().translate(-10, -20));
			f.addMouseListener(new MouseListenerForSpan(SpanAction.REMOVE_BOTTOM));
			layer.add(f);
			figures.add(f);
		}
	}
	
	protected boolean canRemoveBottom() {
		return getWidgetLayoutInfo().getVerticalSpan() > 1;
	}

	/**
	 * 
	 */
	protected void showSelectionForAddTop() {
		if (!isOnTop() && !isPartOnTheTop()) {
			Rectangle ref = getHostFigure().getBounds();
			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowUp));
			f.setSize(20, 20);
			f.setLocation(ref.getTop().translate(-10, -20));
			f.addMouseListener(new MouseListenerForSpan(SpanAction.ADD_TOP));
			layer.add(f);
			figures.add(f);
		}
	}

	/**
	 * 
	 */
	protected void showSelectionForRemoveTop() {
		if (canRemoveTop()) {
			Rectangle ref = getHostFigure().getBounds();
			IFigure f = new ImageFigure(Pics.getImage(PicsConstants.arrowDown));
			f.setSize(20, 20);
			f.setLocation(ref.getTop().translate(-10, 0));
			f.addMouseListener(new MouseListenerForSpan(SpanAction.REMOVE_TOP));
			layer.add(f);
			figures.add(f);
		}
	}
	
	protected boolean canRemoveTop() {
		return getWidgetLayoutInfo().getVerticalSpan() > 1;
	}
	
	protected class MouseListenerForSpan extends MouseListener.Stub{
		
		private SpanAction type;

		/**
		 * 
		 */
		public MouseListenerForSpan(SpanAction type) {
			this.type = type;
		}

		public void mousePressed(MouseEvent me) {
			try {
				IUndoableOperation c = createChangeSpanCommand((IGraphicalEditPart) getHost(), type);//new ChangeSpanCommand((IGraphicalEditPart) getHost(), type);
				//c.execute(null, getHost());
				OperationHistoryFactory.getOperationHistory().execute(c,null,null);
				me.consume();
				refresh();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Convenient method.
	 * @return The info relative to the Host widget.
	 */
	protected WidgetLayoutInfo getWidgetLayoutInfo() {
		return getWidget().getWidgetLayoutInfo();
	}

	protected IUndoableOperation createChangeSpanCommand(IGraphicalEditPart host, SpanAction type) {
		return new ChangeSpanCommand((IGraphicalEditPart) getHost(), type);
	}

	/**
	 * @return
	 */
	protected boolean isPartOnTheLeft() {
		int line = getWidgetLayoutInfo().getLine();
		int column = getWidgetLayoutInfo().getColumn() - 1;
		for (int i = 0; i < getWidgetLayoutInfo().getVerticalSpan(); i++) {
			if (map.get(column).get(line + i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	protected boolean isPartOnTheRight() {
		int line = getWidgetLayoutInfo().getLine();
		int column = getWidgetLayoutInfo().getColumn() + getWidgetLayoutInfo().getHorizontalSpan();
		if (column < getNColumn()) {
			for (int i = 0; i < getWidgetLayoutInfo().getVerticalSpan(); i++) {
				if (map.get(column).get(line + i)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	protected boolean isPartOnTheBottom() {
		int line = getWidgetLayoutInfo().getLine() + getWidgetLayoutInfo().getVerticalSpan();
		int column = getWidgetLayoutInfo().getColumn();
		if (column < getNColumn()) {
			for (int i = 0; i < getWidgetLayoutInfo().getHorizontalSpan(); i++) {
				if (getNLine() > line && map.get(column + i).get(line)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	protected boolean isPartOnTheTop() {
		int line = getWidgetLayoutInfo().getLine() - 1;
		int column = getWidgetLayoutInfo().getColumn();
		if (column < getNColumn()) {
			for (int i = 0; i < getWidgetLayoutInfo().getHorizontalSpan(); i++) {
				if (getNLine() < line || map.get(column + i).get(line)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	protected boolean isOnLeft() {
		return getWidgetLayoutInfo().getColumn() == 0;
	}

	/**
	 * @return
	 */
	protected boolean isOnRight() {
		return getWidgetLayoutInfo().getColumn() + getWidgetLayoutInfo().getHorizontalSpan() >= getNColumn();
	}

	/**
	 * @return
	 */
	protected boolean isOnTop() {
		return getWidgetLayoutInfo().getLine() == 0;
	}

	/**
	 * @return
	 */
	protected boolean isOnBottom() {
		return getWidgetLayoutInfo().getLine() + getWidgetLayoutInfo().getVerticalSpan() >= getNLine();
	}
}
