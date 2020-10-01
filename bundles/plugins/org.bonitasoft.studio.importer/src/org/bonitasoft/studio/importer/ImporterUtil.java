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

package org.bonitasoft.studio.importer;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Activity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;

/**
 * @author Romain Bioteau
 *
 */
public class ImporterUtil {

    public static  void updateActiviesSize(DiagramEditPart root) {
        resizeActivities(root);
        for(Object o : root.getChildren()){
            if(o instanceof PoolEditPart){
                for(Object child :((PoolEditPart) o).getChildren()){
                    if(child instanceof PoolPoolCompartmentEditPart){
                        resizeActivities((EditPart) child);
                        for(Object laneChild : ((PoolPoolCompartmentEditPart) child).getChildren()){
                            if(laneChild instanceof LaneEditPart){
                                for(Object child2 : ((LaneEditPart) laneChild).getChildren()){
                                    if(child2 instanceof LaneLaneCompartmentEditPart){
                                        resizeActivities((EditPart) child2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void resizeActivities(EditPart root){
        for(Object o : root.getChildren()){
            if(o instanceof ActivityEditPart){
                FiguresHelper.resizeActivitiesFigure((IGraphicalEditPart) o, getTextFromEditPart((EditPart) o));
            }else if(o instanceof Activity2EditPart){
                FiguresHelper.resizeActivitiesFigure((IGraphicalEditPart) o, getTextFromEditPart((EditPart) o));
            }else if(o instanceof TaskEditPart){
                FiguresHelper.resizeActivitiesFigure((IGraphicalEditPart) o, getTextFromEditPart((EditPart) o));
            }else if(o instanceof Task2EditPart){
                FiguresHelper.resizeActivitiesFigure((IGraphicalEditPart) o, getTextFromEditPart((EditPart) o));
            }else if(o instanceof CallActivity2EditPart){
                FiguresHelper.resizeActivitiesFigure((IGraphicalEditPart) o, getTextFromEditPart((EditPart) o));
            }else if(o instanceof CallActivityEditPart){
                FiguresHelper.resizeActivitiesFigure((IGraphicalEditPart) o, getTextFromEditPart((EditPart) o));
            }
        }
    }

    private static String getTextFromEditPart(EditPart o) {
        for(Object obj : o.getChildren()){
            if(obj instanceof ITextAwareEditPart){
                return ((ITextAwareEditPart)obj).getEditText();
            }
        }
        return null;
    }
}
