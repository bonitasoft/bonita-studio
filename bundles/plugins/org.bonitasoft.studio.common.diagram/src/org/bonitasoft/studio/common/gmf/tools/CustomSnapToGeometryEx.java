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

package org.bonitasoft.studio.common.gmf.tools;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToGeometryEx;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CustomSnapToGeometryEx extends SnapToGeometryEx {

    public CustomSnapToGeometryEx(GraphicalEditPart container) {
        super(container);
    }

    // This code will be required to translate the figure's coordinates
    // accordingly if Bugzilla 188308 is fixed
    protected static class EntryEx extends Entry {

        protected EntryEx(int type, int location) {
            super(type, location);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void populateRowsAndCols(List parts) {
        if(!parts.isEmpty()){
            GraphicalEditPart diagramEditPart = (GraphicalEditPart)parts.get(0);
            while (diagramEditPart.getParent() != null){
                diagramEditPart = (GraphicalEditPart)diagramEditPart.getParent();
                if (diagramEditPart instanceof DiagramEditPart) {
                    break;
                }
            }


            Set<EditPart> contentList = new HashSet<EditPart>() ;
            DiagramEditPart dEP = (DiagramEditPart) diagramEditPart ;
            for(Object entry :  dEP.getViewer().getEditPartRegistry().entrySet()){
                java.util.Map.Entry<View, EditPart> epEntry = (java.util.Map.Entry<View, EditPart>) entry;
                EditPart ep = epEntry.getValue();
                if(!(ep instanceof ITextAwareEditPart) && (ep instanceof GraphicalEditPart) && !(ep instanceof ShapeCompartmentEditPart) ){
                    contentList.add(ep);
                }
            }

            parts.clear() ;
            parts.addAll(contentList);

            rows = new Entry[parts.size() * 3];
            cols = new Entry[parts.size() * 3];


            for (int i = 0; i < parts.size(); i++) {

                GraphicalEditPart child = (GraphicalEditPart)parts.get(i);
                Rectangle bounds = getFigureBounds(child).getCopy();

                //translate the figure's coordinates to absolute, then relative
                //to the diagram (i.e the figure's layout constraint on the diagram)
                child.getFigure().translateToAbsolute(bounds);
                diagramEditPart.getFigure().translateToRelative(bounds);

                cols[i * 3] = new EntryEx(-1, bounds.x);
                rows[i * 3] = new EntryEx(-1, bounds.y);
                cols[i * 3 + 1] = new EntryEx(0, bounds.x + (bounds.width - 1) / 2);
                rows[i * 3 + 1] = new EntryEx(0, bounds.y + (bounds.height - 1) / 2);
                cols[i * 3 + 2] = new EntryEx(1, bounds.right() - 1);
                rows[i * 3 + 2] = new EntryEx(1, bounds.bottom() - 1);

            }
        }else{
            rows = new Entry[parts.size() * 3];
            cols = new Entry[parts.size() * 3];
        }
    }


}
