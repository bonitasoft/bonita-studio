/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.editPolicies.command;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubprocessEventCompartmentEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 */
public class OverlapSetBoundsCommand extends SetBoundsCommand {

    private final Rectangle bounds;
    private final IAdaptable adapter;
    private final GraphicalEditPart editPart;
    private final EditPart host;

    public OverlapSetBoundsCommand(final TransactionalEditingDomain editingDomain, final GraphicalEditPart editPart, final EditPart host,
            final IAdaptable adapter,
            final Rectangle bounds) {
        super(editingDomain, OverlapSetBoundsCommand.class.getName(), adapter, bounds);
        this.bounds = bounds;
        this.adapter = adapter;
        this.editPart = editPart;
        this.host = host;
    }

    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        if (adapter == null) {
            return CommandResult.newErrorCommandResult("SetBoundsCommand: viewAdapter does not adapt to IView.class");
        }
        final View view = (View) adapter.getAdapter(View.class);
        final Point location = bounds.getLocation();
        if (location != null) {
            Point newLoc = location.getCopy();
            if (FiguresHelper.AVOID_OVERLAP_ENABLE) {
                newLoc = findValidLocation(newLoc);
                if (!newLoc.equals(handleMargins(newLoc))) { //Still overlapping
                    return CommandResult.newCancelledCommandResult();
                }
            }
            ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_X(), Integer.valueOf(newLoc.x));
            ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_Y(), Integer.valueOf(newLoc.y));
        }
        final Dimension size = bounds.getSize();
        if (size != null) {
            ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getSize_Width(), Integer.valueOf(size.width));
            ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getSize_Height(), Integer.valueOf(size.height));
        }
        return CommandResult.newOKCommandResult();
    }

    protected Point findValidLocation(Point location) {
        int retry = 0;
        do {
            location = handleMargins(location);
            retry++;
        } while (retry < 20);

        return location;
    }

    protected Point handleMargins(final Point newLoc) {
        return FiguresHelper.handleCompartmentMargin(editPart, newLoc.x, newLoc.y,
                host instanceof CustomSubprocessEventCompartmentEditPart);
    }
}
