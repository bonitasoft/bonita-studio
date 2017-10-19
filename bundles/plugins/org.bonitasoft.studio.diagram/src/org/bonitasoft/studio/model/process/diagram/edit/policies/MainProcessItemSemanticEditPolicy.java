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

import org.bonitasoft.studio.model.process.diagram.edit.commands.ANDGatewayCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.ActivityCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.CallActivityCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.CatchLinkEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndErrorEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndMessageEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndSignalEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EndTerminatedEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.EventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.InclusiveGatewayCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateCatchMessageEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateCatchSignalEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateCatchTimerEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateThrowMessageEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.IntermediateThrowSignalEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.PoolCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.ReceiveTaskCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.ScriptTaskCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.SendTaskCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.ServiceTaskCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartErrorEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartMessageEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartSignalEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.StartTimerEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.SubProcessEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.TaskCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.TextAnnotationCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.ThrowLinkEventCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.commands.XORGatewayCreateCommand;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class MainProcessItemSemanticEditPolicy extends ProcessBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public MainProcessItemSemanticEditPolicy() {
		super(ProcessElementTypes.MainProcess_1000);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ProcessElementTypes.ANDGateway_2009 == req.getElementType()) {
			return getGEFWrapper(new ANDGatewayCreateCommand(req));
		}
		if (ProcessElementTypes.StartEvent_2002 == req.getElementType()) {
			return getGEFWrapper(new StartEventCreateCommand(req));
		}
		if (ProcessElementTypes.EndEvent_2003 == req.getElementType()) {
			return getGEFWrapper(new EndEventCreateCommand(req));
		}
		if (ProcessElementTypes.Task_2004 == req.getElementType()) {
			return getGEFWrapper(new TaskCreateCommand(req));
		}
		if (ProcessElementTypes.CallActivity_2036 == req.getElementType()) {
			return getGEFWrapper(new CallActivityCreateCommand(req));
		}
		if (ProcessElementTypes.SubProcessEvent_2031 == req.getElementType()) {
			return getGEFWrapper(new SubProcessEventCreateCommand(req));
		}
		if (ProcessElementTypes.ReceiveTask_2025 == req.getElementType()) {
			return getGEFWrapper(new ReceiveTaskCreateCommand(req));
		}
		if (ProcessElementTypes.SendTask_2026 == req.getElementType()) {
			return getGEFWrapper(new SendTaskCreateCommand(req));
		}
		if (ProcessElementTypes.ServiceTask_2027 == req.getElementType()) {
			return getGEFWrapper(new ServiceTaskCreateCommand(req));
		}
		if (ProcessElementTypes.ScriptTask_2028 == req.getElementType()) {
			return getGEFWrapper(new ScriptTaskCreateCommand(req));
		}
		if (ProcessElementTypes.XORGateway_2008 == req.getElementType()) {
			return getGEFWrapper(new XORGatewayCreateCommand(req));
		}
		if (ProcessElementTypes.InclusiveGateway_2030 == req.getElementType()) {
			return getGEFWrapper(new InclusiveGatewayCreateCommand(req));
		}
		if (ProcessElementTypes.Activity_2006 == req.getElementType()) {
			return getGEFWrapper(new ActivityCreateCommand(req));
		}
		if (ProcessElementTypes.Pool_2007 == req.getElementType()) {
			return getGEFWrapper(new PoolCreateCommand(req));
		}
		if (ProcessElementTypes.StartMessageEvent_2010 == req.getElementType()) {
			return getGEFWrapper(new StartMessageEventCreateCommand(req));
		}
		if (ProcessElementTypes.EndMessageEvent_2011 == req.getElementType()) {
			return getGEFWrapper(new EndMessageEventCreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateCatchMessageEvent_2013 == req.getElementType()) {
			return getGEFWrapper(new IntermediateCatchMessageEventCreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateThrowMessageEvent_2014 == req.getElementType()) {
			return getGEFWrapper(new IntermediateThrowMessageEventCreateCommand(req));
		}
		if (ProcessElementTypes.TextAnnotation_2015 == req.getElementType()) {
			return getGEFWrapper(new TextAnnotationCreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateCatchTimerEvent_2017 == req.getElementType()) {
			return getGEFWrapper(new IntermediateCatchTimerEventCreateCommand(req));
		}
		if (ProcessElementTypes.StartTimerEvent_2016 == req.getElementType()) {
			return getGEFWrapper(new StartTimerEventCreateCommand(req));
		}
		if (ProcessElementTypes.CatchLinkEvent_2018 == req.getElementType()) {
			return getGEFWrapper(new CatchLinkEventCreateCommand(req));
		}
		if (ProcessElementTypes.ThrowLinkEvent_2019 == req.getElementType()) {
			return getGEFWrapper(new ThrowLinkEventCreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateThrowSignalEvent_2020 == req.getElementType()) {
			return getGEFWrapper(new IntermediateThrowSignalEventCreateCommand(req));
		}
		if (ProcessElementTypes.IntermediateCatchSignalEvent_2021 == req.getElementType()) {
			return getGEFWrapper(new IntermediateCatchSignalEventCreateCommand(req));
		}
		if (ProcessElementTypes.StartSignalEvent_2022 == req.getElementType()) {
			return getGEFWrapper(new StartSignalEventCreateCommand(req));
		}
		if (ProcessElementTypes.EndSignalEvent_2023 == req.getElementType()) {
			return getGEFWrapper(new EndSignalEventCreateCommand(req));
		}
		if (ProcessElementTypes.Event_2024 == req.getElementType()) {
			return getGEFWrapper(new EventCreateCommand(req));
		}
		if (ProcessElementTypes.EndErrorEvent_2029 == req.getElementType()) {
			return getGEFWrapper(new EndErrorEventCreateCommand(req));
		}
		if (ProcessElementTypes.StartErrorEvent_2033 == req.getElementType()) {
			return getGEFWrapper(new StartErrorEventCreateCommand(req));
		}
		if (ProcessElementTypes.EndTerminatedEvent_2035 == req.getElementType()) {
			return getGEFWrapper(new EndTerminatedEventCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	* @generated
	*/
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	* @generated
	*/
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		* @generated
		*/
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}

	}

}
