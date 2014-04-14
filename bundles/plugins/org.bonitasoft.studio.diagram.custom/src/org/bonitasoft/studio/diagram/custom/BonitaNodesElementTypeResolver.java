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
package org.bonitasoft.studio.diagram.custom;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.GMFTools.ElementTypeResolver;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @author Mickael Istria
 *
 */
public class BonitaNodesElementTypeResolver implements ElementTypeResolver {
    public IElementType getElementType(GraphicalEditPart parentEditPart, EClass targetEClass) {
        final List<IElementType> allowedChildren = new ArrayList<IElementType>();
        if(parentEditPart instanceof ShapeCompartmentEditPart){
            
            allowedChildren.add(ProcessElementTypes.Activity_3006);
            allowedChildren.add(ProcessElementTypes.Task_3005);
            allowedChildren.add(ProcessElementTypes.CallActivity_3063);
            allowedChildren.add(ProcessElementTypes.XORGateway_3008);
            allowedChildren.add(ProcessElementTypes.ANDGateway_3009);
            allowedChildren.add(ProcessElementTypes.InclusiveGateway_3051);
            allowedChildren.add(ProcessElementTypes.IntermediateCatchMessageEvent_3013);
            allowedChildren.add(ProcessElementTypes.IntermediateThrowMessageEvent_3014);
            allowedChildren.add(ProcessElementTypes.EndMessageEvent_3011);
            allowedChildren.add(ProcessElementTypes.EndErrorEvent_3050);
            allowedChildren.add(ProcessElementTypes.StartMessageEvent_3012);
            allowedChildren.add(ProcessElementTypes.StartTimerEvent_3016);
            allowedChildren.add(ProcessElementTypes.StartSignalEvent_3023);
            allowedChildren.add(ProcessElementTypes.IntermediateCatchTimerEvent_3017);
            allowedChildren.add(ProcessElementTypes.CatchLinkEvent_3019);
            allowedChildren.add(ProcessElementTypes.ThrowLinkEvent_3018);
            allowedChildren.add(ProcessElementTypes.IntermediateCatchSignalEvent_3021);
            allowedChildren.add(ProcessElementTypes.IntermediateThrowSignalEvent_3022);
            allowedChildren.add(ProcessElementTypes.EndSignalEvent_3020);
            allowedChildren.add(ProcessElementTypes.EndEvent_3003);
            allowedChildren.add(ProcessElementTypes.StartEvent_3002);
            allowedChildren.add(ProcessElementTypes.ReceiveTask_3026);
            allowedChildren.add(ProcessElementTypes.SendTask_3025);
            allowedChildren.add(ProcessElementTypes.ScriptTask_3028);
            allowedChildren.add(ProcessElementTypes.ServiceTask_3027);
            allowedChildren.add(ProcessElementTypes.StartErrorEvent_3060);
            allowedChildren.add(ProcessElementTypes.EndTerminatedEvent_3062);
        }else{
            allowedChildren.add(ProcessElementTypes.Activity_2006);
            allowedChildren.add(ProcessElementTypes.Task_2004);
            allowedChildren.add(ProcessElementTypes.CallActivity_2036);
            allowedChildren.add(ProcessElementTypes.XORGateway_2008);
            allowedChildren.add(ProcessElementTypes.ANDGateway_2009);
            allowedChildren.add(ProcessElementTypes.InclusiveGateway_2030);
            allowedChildren.add(ProcessElementTypes.IntermediateCatchMessageEvent_2013);
            allowedChildren.add(ProcessElementTypes.IntermediateThrowMessageEvent_2014);
            allowedChildren.add(ProcessElementTypes.EndMessageEvent_2011);
            allowedChildren.add(ProcessElementTypes.EndErrorEvent_2029);
            allowedChildren.add(ProcessElementTypes.StartMessageEvent_2010);
            allowedChildren.add(ProcessElementTypes.StartTimerEvent_2016);
            allowedChildren.add(ProcessElementTypes.StartSignalEvent_2022);
            allowedChildren.add(ProcessElementTypes.IntermediateCatchTimerEvent_2017);
            allowedChildren.add(ProcessElementTypes.CatchLinkEvent_2018);
            allowedChildren.add(ProcessElementTypes.ThrowLinkEvent_2019);
            allowedChildren.add(ProcessElementTypes.IntermediateCatchSignalEvent_2021);
            allowedChildren.add(ProcessElementTypes.IntermediateThrowSignalEvent_2020);
            allowedChildren.add(ProcessElementTypes.EndSignalEvent_2023);
            allowedChildren.add(ProcessElementTypes.EndEvent_2003);
            allowedChildren.add(ProcessElementTypes.StartEvent_2002);
            allowedChildren.add(ProcessElementTypes.ReceiveTask_2025);
            allowedChildren.add(ProcessElementTypes.SendTask_2026);
            allowedChildren.add(ProcessElementTypes.ScriptTask_2028);
            allowedChildren.add(ProcessElementTypes.ServiceTask_2027);
            allowedChildren.add(ProcessElementTypes.StartErrorEvent_2033);
            allowedChildren.add(ProcessElementTypes.EndTerminatedEvent_2035);
            //can't use anymore ModelingAssistantService with popupbar because it has been deactivated
            //allowedChildren = ModelingAssistantService.getInstance().getTypesForPopupBar(parentEditPart);
        }


        for (IElementType child : allowedChildren) {
            if (child.getEClass().equals(targetEClass)) {
                return child;
            }
        }


        return null;
    }
}