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
package org.bonitasoft.studio.common.diagram.tools;

import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;

/**
 * @author Aurelien Pupier
 *
 */
public class DragEditPartsTrackerExWithoutCopyWithModKeyPressed extends DragEditPartsTrackerEx {

	/**
	 * Key modifier for cloning. It's ALT on Mac, and CTRL on all other
	 * platforms.
	 */
	static final int MODIFIER_CLONE;

	static {
		if (SWT.getPlatform().equals("carbon") || SWT.getPlatform().equals("cocoa") ) //$NON-NLS-1$
			MODIFIER_CLONE = SWT.COMMAND;
		else
			MODIFIER_CLONE = SWT.CTRL;
	}

	/**
	 * @param sourceEditPart
	 */
	public DragEditPartsTrackerExWithoutCopyWithModKeyPressed(EditPart sourceEditPart) {
		super(sourceEditPart);
		setDefaultCursor(Pics.getClosedHandCursor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DragEditPartsTracker#setState(int)
	 */
	@Override
	protected void setState(int state) {
		super.setState(state);
		/* deactivate clone if modkey down */
		if (getCurrentInput().isModKeyDown(MODIFIER_CLONE)) {
			setCloneActive(false);
		}
	}


	@Override
	protected void executeCurrentCommand() {
		if(getOperationSet().size() > 1){
			FiguresHelper.AVOID_OVERLAP_ENABLE = false ;
			super.executeCurrentCommand();
			FiguresHelper.AVOID_OVERLAP_ENABLE = true ;
			CompoundCommand cc = new CompoundCommand("Check Overlap") ;
			for(Object ep : getOperationSet()){
				if(ep instanceof IGraphicalEditPart){
					IGraphicalEditPart gep = (IGraphicalEditPart) ep ;
					Location loc = (Location) ((Node) ((IGraphicalEditPart) ep).getNotationView()).getLayoutConstraint() ;
					Point oldLoc = new Point(loc.getX(), loc.getY()) ;
					Point newLoc = FiguresHelper.handleCompartmentMargin(gep, loc.getX(), loc.getY(),(gep.resolveSemanticElement() instanceof SubProcessEvent)) ;
					if((newLoc.x != 0 && newLoc.y != 0) && !newLoc.equals(oldLoc)){
						cc.add(new ICommandProxy(new SetBoundsCommand(gep.getEditingDomain(), "Check Overlap", new EObjectAdapter(gep.getNotationView()),newLoc))) ;
					}
				}
			}
			executeCommand(cc) ;
		}else{
			super.executeCurrentCommand() ;
		}
	}


}
