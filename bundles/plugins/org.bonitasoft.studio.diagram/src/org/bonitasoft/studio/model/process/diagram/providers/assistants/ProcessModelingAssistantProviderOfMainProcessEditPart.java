/*
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
package org.bonitasoft.studio.model.process.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessModelingAssistantProvider;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class ProcessModelingAssistantProviderOfMainProcessEditPart extends ProcessModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(31);
		types.add(ProcessElementTypes.ANDGateway_2009);
		types.add(ProcessElementTypes.StartEvent_2002);
		types.add(ProcessElementTypes.EndEvent_2003);
		types.add(ProcessElementTypes.Task_2004);
		types.add(ProcessElementTypes.CallActivity_2036);
		types.add(ProcessElementTypes.SubProcessEvent_2031);
		types.add(ProcessElementTypes.ReceiveTask_2025);
		types.add(ProcessElementTypes.SendTask_2026);
		types.add(ProcessElementTypes.ServiceTask_2027);
		types.add(ProcessElementTypes.ScriptTask_2028);
		types.add(ProcessElementTypes.XORGateway_2008);
		types.add(ProcessElementTypes.InclusiveGateway_2030);
		types.add(ProcessElementTypes.Activity_2006);
		types.add(ProcessElementTypes.Pool_2007);
		types.add(ProcessElementTypes.StartMessageEvent_2010);
		types.add(ProcessElementTypes.EndMessageEvent_2011);
		types.add(ProcessElementTypes.IntermediateCatchMessageEvent_2013);
		types.add(ProcessElementTypes.IntermediateThrowMessageEvent_2014);
		types.add(ProcessElementTypes.TextAnnotation_2015);
		types.add(ProcessElementTypes.IntermediateCatchTimerEvent_2017);
		types.add(ProcessElementTypes.StartTimerEvent_2016);
		types.add(ProcessElementTypes.CatchLinkEvent_2018);
		types.add(ProcessElementTypes.ThrowLinkEvent_2019);
		types.add(ProcessElementTypes.IntermediateThrowSignalEvent_2020);
		types.add(ProcessElementTypes.IntermediateCatchSignalEvent_2021);
		types.add(ProcessElementTypes.StartSignalEvent_2022);
		types.add(ProcessElementTypes.EndSignalEvent_2023);
		types.add(ProcessElementTypes.Event_2024);
		types.add(ProcessElementTypes.EndErrorEvent_2029);
		types.add(ProcessElementTypes.StartErrorEvent_2033);
		types.add(ProcessElementTypes.EndTerminatedEvent_2035);
		return types;
	}

}
