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
 
package org.bonitasoft.studio.common.diagram.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * 
 * Utility class to help retrieving allowed elements types
 * @author Romain Bioteau
 *
 */
public class BonitaConnectionTypes {

	private static List<IElementType> relationTypes = new ArrayList<IElementType>() ;
	static {
		relationTypes.add(getElementType("org.bonitasoft.studio.diagram.SequenceFlow_4001")) ; //$NON-NLS-1$
		relationTypes.add(getElementType("org.bonitasoft.studio.diagram.MessageFlow_4002")) ; //$NON-NLS-1$
		relationTypes.add(getElementType("org.bonitasoft.studio.diagram.TextAnnotationAttachment_4003")) ; //$NON-NLS-1$
	}
	//all type allowed when using the generic event
	private static List<IElementType> eventTypes = new ArrayList<IElementType>() ;
	static {
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.Event_3024")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartEvent_2002")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartEvent_3002")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndEvent_2003")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndEvent_3003")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateCatchTimerEvent_2017")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateCatchTimerEvent_3017")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartMessageEvent_2010")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartMessageEvent_3012")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateCatchMessageEvent_2013")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateCatchMessageEvent_3013")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndMessageEvent_2011")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndMessageEvent_3011")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateThrowMessageEvent_2014")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateThrowMessageEvent_3014")) ; //$NON-NLS-1$
		
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateThrowSignalEvent_2020")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateThrowSignalEvent_3022")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateCatchSignalEvent_2021")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.IntermediateCatchSignalEvent_3021")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndSignalEvent_2023")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndSignalEvent_3020")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.CatchLinkEvent_3019")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.CatchLinkEvent_2018")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.ThrowLinkEvent_3018")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.ThrowLinkEvent_2019")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndErrorEvent_2029")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndErrorEvent_3050")) ; //$NON-NLS-1$

		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartTimerEvent_2016")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartTimerEvent_3016")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartSignalEvent_2022")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartSignalEvent_3023")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndTerminatedEvent_2035")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.EndTerminatedEvent_3062")) ; //$NON-NLS-1$
		
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartErrorEvent_2033")) ; //$NON-NLS-1$
		eventTypes.add(getElementType("org.bonitasoft.studio.diagram.StartErrorEvent_3060")) ; //$NON-NLS-1$
	
	}
	
	public static List<IElementType> getRelationTypes() {
		return relationTypes;
	}
	
	public static List<IElementType> getTypesFor(List<IElementType> type) {
		if(type.get(0).equals(getElementType("org.bonitasoft.studio.diagram.Event_3024"))){ //$NON-NLS-1$
			return eventTypes;
		}else{
			return type;
		}
	}

	public static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}
}
