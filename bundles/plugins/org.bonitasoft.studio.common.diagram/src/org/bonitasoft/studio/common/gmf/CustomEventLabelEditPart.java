/**
 * Copyright (C) 2010-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.LabelLocator;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;


/**
 * @author Baptiste Mesta
 *
 */
public class CustomEventLabelEditPart extends LabelEditPart {

    /**
     * @param view
     */
    public CustomEventLabelEditPart(final View view) {
        super(view);
    }

    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#createDefaultEditPolicies()
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(
                EditPolicy.PRIMARY_DRAG_ROLE,
                new CustomNonResizableLabelEditPolicy());
    }

    @Override
    public void refreshBounds() {
        final IFigure refFigure = ((GraphicalEditPart) getParent()).getFigure();
        final int dx = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE
                .getLocation_X())).intValue();
        final int dy = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE
                .getLocation_Y())).intValue();
        final Point offset = new Point(dx, dy);
        if(getFigure().getParent() != null){
            getFigure().getParent().setConstraint(getFigure(),
                    new LabelLocator(refFigure, offset, getKeyPoint()) {

                @Override
                public void relocate(final IFigure target) {
                    final Point location = getReferencePoint().getTranslated(
                            getOffset());
                    location.translate(-target.getBounds().width / 2, 0);
                    target.setLocation(location);
                    target.setSize(new Dimension(
                            target.getPreferredSize().width, target
                            .getPreferredSize().height));
                }

                @Override
                protected Point getReferencePoint() {
                    return getLabelLocation(parent);
                }
            });
        }
    }

    @Override
    public Point getReferencePoint() {
        return getLabelLocation(((GraphicalEditPart) getParent()).getFigure());
    }


    protected Point getLabelLocation(final IFigure parent) {
        return parent.getBounds().getBottom();
    }


}
