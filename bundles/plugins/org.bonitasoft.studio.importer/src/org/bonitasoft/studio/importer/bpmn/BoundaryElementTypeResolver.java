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
package org.bonitasoft.studio.importer.bpmn;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.GMFTools.ElementTypeResolver;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Activity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskEditPart;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @author Baptiste Mesta
 * 
 * Duplicated Class (bad)
 *
 */
public final class BoundaryElementTypeResolver implements ElementTypeResolver {
    public IElementType getElementType(GraphicalEditPart parentEditPart, EClass targetEClass) {
        List<IElementType> allowedChildren = new ArrayList<IElementType>();

        if (parentEditPart instanceof Task2EditPart || parentEditPart instanceof TaskEditPart) {
            allowedChildren.add(ProcessElementTypes.IntermediateErrorCatchEvent_3029);
            allowedChildren.add(ProcessElementTypes.BoundaryMessageEvent_3035);
            allowedChildren.add(ProcessElementTypes.BoundaryTimerEvent_3043);
            allowedChildren.add(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3064);
            allowedChildren.add(ProcessElementTypes.BoundarySignalEvent_3052);
        } else if (parentEditPart instanceof CallActivity2EditPart || parentEditPart instanceof CallActivityEditPart) {
            allowedChildren.add(ProcessElementTypes.IntermediateErrorCatchEvent_3030);
            allowedChildren.add(ProcessElementTypes.BoundaryMessageEvent_3036);
            allowedChildren.add(ProcessElementTypes.BoundaryTimerEvent_3044);
            allowedChildren.add(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3065);
            allowedChildren.add(ProcessElementTypes.BoundarySignalEvent_3053);
        } else if (parentEditPart instanceof ScriptTask2EditPart || parentEditPart instanceof ScriptTaskEditPart) {
            allowedChildren.add(ProcessElementTypes.IntermediateErrorCatchEvent_3033);
        } else if (parentEditPart instanceof ServiceTask2EditPart || parentEditPart instanceof ServiceTaskEditPart) {
            allowedChildren.add(ProcessElementTypes.IntermediateErrorCatchEvent_3032);
        } else if (parentEditPart instanceof ReceiveTask2EditPart || parentEditPart instanceof ReceiveTaskEditPart) {
            allowedChildren.add(ProcessElementTypes.IntermediateErrorCatchEvent_3031);
        } else if (parentEditPart instanceof Activity2EditPart || parentEditPart instanceof ActivityEditPart) {
            allowedChildren.add(ProcessElementTypes.IntermediateErrorCatchEvent_3034);
        }


        for (IElementType child : allowedChildren) {
            if (child.getEClass().equals(targetEClass)) {
                return child;
            }
        }

        return null;

    }
}