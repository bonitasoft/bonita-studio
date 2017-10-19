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
package org.bonitasoft.studio.model.process.diagram.edit.policies;

import org.bonitasoft.studio.model.process.diagram.edit.commands.ANDGateway2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.Activity2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.CallActivity2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndErrorEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndMessageEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndSignalEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndTerminatedEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.Event2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.InclusiveGateway2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateCatchMessageEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateCatchSignalEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateCatchTimerEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateThrowMessageEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateThrowSignalEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.ReceiveTask2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.ScriptTask2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.SendTask2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.ServiceTask2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartErrorEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartMessageEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartSignalEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartTimerEvent2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.Task2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.TextAnnotation2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.XORGateway2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class SubProcessEventSubProcessCompartment2ItemSemanticEditPolicy extends ProcessBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public SubProcessEventSubProcessCompartment2ItemSemanticEditPolicy() {
		super(ProcessElementTypes.SubProcessEvent_3058);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ProcessElementTypes.ANDGateway_3009 == req.getElementType()) {
			return getGEFWrapper(new ANDGateway2CreateCommand(req));
		}
		if (ProcessElementTypes.EndEvent_3003 == req.getElementType()) {
			return getGEFWrapper(new EndEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.CallActivity_3063 == req.getElementType()) {
			return getGEFWrapper(new CallActivity2CreateCommand(req));
		}
		if (ProcessElementTypes.Task_3005 == req.getElementType()) {
			return getGEFWrapper(new Task2CreateCommand(req));
		}
		if (ProcessElementTypes.ReceiveTask_3026 == req.getElementType()) {
			return getGEFWrapper(new ReceiveTask2CreateCommand(req));
		}
		if (ProcessElementTypes.SendTask_3025 == req.getElementType()) {
			return getGEFWrapper(new SendTask2CreateCommand(req));
		}
		if (ProcessElementTypes.ServiceTask_3027 == req.getElementType()) {
			return getGEFWrapper(new ServiceTask2CreateCommand(req));
		}
		if (ProcessElementTypes.ScriptTask_3028 == req.getElementType()) {
			return getGEFWrapper(new ScriptTask2CreateCommand(req));
		}
		if (ProcessElementTypes.XORGateway_3008 == req.getElementType()) {
			return getGEFWrapper(new XORGateway2CreateCommand(req));
		}
		if (ProcessElementTypes.Activity_3006 == req.getElementType()) {
			return getGEFWrapper(new Activity2CreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateCatchMessageEvent_3013 == req.getElementType()) {
			return getGEFWrapper(new IntermediateCatchMessageEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.StartMessageEvent_3012 == req.getElementType()) {
			return getGEFWrapper(new StartMessageEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.EndMessageEvent_3011 == req.getElementType()) {
			return getGEFWrapper(new EndMessageEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateThrowMessageEvent_3014 == req.getElementType()) {
			return getGEFWrapper(new IntermediateThrowMessageEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.TextAnnotation_3015 == req.getElementType()) {
			return getGEFWrapper(new TextAnnotation2CreateCommand(req));
		}
		if (ProcessElementTypes.StartTimerEvent_3016 == req.getElementType()) {
			return getGEFWrapper(new StartTimerEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateCatchTimerEvent_3017 == req.getElementType()) {
			return getGEFWrapper(new IntermediateCatchTimerEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.StartSignalEvent_3023 == req.getElementType()) {
			return getGEFWrapper(new StartSignalEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateThrowSignalEvent_3022 == req.getElementType()) {
			return getGEFWrapper(new IntermediateThrowSignalEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateCatchSignalEvent_3021 == req.getElementType()) {
			return getGEFWrapper(new IntermediateCatchSignalEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.EndSignalEvent_3020 == req.getElementType()) {
			return getGEFWrapper(new EndSignalEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.EndErrorEvent_3050 == req.getElementType()) {
			return getGEFWrapper(new EndErrorEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.EndTerminatedEvent_3062 == req.getElementType()) {
			return getGEFWrapper(new EndTerminatedEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.StartErrorEvent_3060 == req.getElementType()) {
			return getGEFWrapper(new StartErrorEvent2CreateCommand(req));
		}
		if (ProcessElementTypes.Event_3024 == req.getElementType()) {
			return getGEFWrapper(new Event2CreateCommand(req));
		}
		if (ProcessElementTypes.InclusiveGateway_3051 == req.getElementType()) {
			return getGEFWrapper(new InclusiveGateway2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
