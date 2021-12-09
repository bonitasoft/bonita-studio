
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
package org.bonitasoft.studio.model.process.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.diagram.tools.BonitaConnectionTypes;
import org.bonitasoft.studio.common.diagram.tools.BonitaUnspecifiedTypeConnectionTool;
import org.bonitasoft.studio.common.diagram.tools.BonitaUnspecifiedTypeProcessCreationTool;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.palette.PaletteToolEntry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class ProcessPaletteFactory {

	/**
	* @generated
	*/
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createSwimlanes1Group());
		paletteRoot.add(createGateways2Group());
		paletteRoot.add(createFlow3Group());
		paletteRoot.add(createTasks4Group());
		paletteRoot.add(createActivities5Group());
		paletteRoot.add(createStartEvents6Group());
		paletteRoot.add(createIntEvents7Group());
		paletteRoot.add(createEndEvents8Group());
		paletteRoot.add(createTextAnnotation9Group());
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Swimlanes" palette tool group
	* @generated
	*/
	protected PaletteContainer createSwimlanes1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.Swimlanes1Group_title);
		paletteContainer.setId("createSwimlanes1Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.Swimlanes1Group_desc);
		paletteContainer.add(createPool1CreationTool());
		paletteContainer.add(createLane2CreationTool());
		return paletteContainer;
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Gateways" palette tool group
	* @generated
	*/
	protected PaletteContainer createGateways2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.Gateways2Group_title);
		paletteContainer.setId("createGateways2Group"); //$NON-NLS-1$
		paletteContainer.add(createGate1CreationTool());
		paletteContainer.add(createXORGate2CreationTool());
		paletteContainer.add(createInclusiveGate3CreationTool());
		return paletteContainer;
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Flow" palette tool group
	* @generated
	*/
	protected PaletteContainer createFlow3Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.Flow3Group_title);
		paletteContainer.setId("createFlow3Group"); //$NON-NLS-1$
		paletteContainer.add(createTransition1CreationTool());
		return paletteContainer;
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Tasks" palette tool group
	* @generated
	*/
	protected PaletteContainer createTasks4Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.Tasks4Group_title);
		paletteContainer.setId("createTasks4Group"); //$NON-NLS-1$
		paletteContainer.add(createServiceTask1CreationTool());
		paletteContainer.add(createHuman2CreationTool());
		paletteContainer.add(createScriptTask3CreationTool());
		paletteContainer.add(createReceiveTask4CreationTool());
		paletteContainer.add(createSendTask5CreationTool());
		paletteContainer.add(createStep6CreationTool());
		return paletteContainer;
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Activities" palette tool group
	* @generated
	*/
	protected PaletteContainer createActivities5Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.Activities5Group_title);
		paletteContainer.setId("createActivities5Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.Activities5Group_desc);
		paletteContainer.add(createCallActivity1CreationTool());
		paletteContainer.add(createEvenementialSubProcess2CreationTool());
		return paletteContainer;
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Start Events" palette tool group
	* @generated
	*/
	protected PaletteContainer createStartEvents6Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.StartEvents6Group_title);
		paletteContainer.setId("createStartEvents6Group"); //$NON-NLS-1$
		paletteContainer.add(createStart1CreationTool());
		paletteContainer.add(createStartMessage2CreationTool());
		paletteContainer.add(createStartTimer3CreationTool());
		paletteContainer.add(createStartSignal4CreationTool());
		paletteContainer.add(createStartError5CreationTool());
		return paletteContainer;
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Int. Events" palette tool group
	* @generated
	*/
	protected PaletteContainer createIntEvents7Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.IntEvents7Group_title);
		paletteContainer.setId("createIntEvents7Group"); //$NON-NLS-1$
		paletteContainer.add(createThrowLink1CreationTool());
		paletteContainer.add(createCatchLink2CreationTool());
		paletteContainer.add(createThrowMessage3CreationTool());
		paletteContainer.add(createCatchMessage4CreationTool());
		paletteContainer.add(createThrowSignal5CreationTool());
		paletteContainer.add(createCatchSignal6CreationTool());
		paletteContainer.add(createCatchError7CreationTool());
		paletteContainer.add(createNonInterruptingTimer8CreationTool());
		paletteContainer.add(createEvent9CreationTool());
		paletteContainer.add(createTimer10CreationTool());
		return paletteContainer;
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "End Events" palette tool group
	* @generated
	*/
	protected PaletteContainer createEndEvents8Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.EndEvents8Group_title);
		paletteContainer.setId("createEndEvents8Group"); //$NON-NLS-1$
		paletteContainer.add(createEnd1CreationTool());
		paletteContainer.add(createEndMessage2CreationTool());
		paletteContainer.add(createEndSignal3CreationTool());
		paletteContainer.add(createEndError4CreationTool());
		paletteContainer.add(createEndTerminatedEvent5CreationTool());
		return paletteContainer;
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Text Annotation" palette tool group
	* @generated
	*/
	protected PaletteContainer createTextAnnotation9Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.TextAnnotation9Group_title);
		paletteContainer.setId("createTextAnnotation9Group"); //$NON-NLS-1$
		paletteContainer.add(createTextAnnotation1CreationTool());
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createPool1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(Messages.Pool1CreationTool_title, Messages.Pool1CreationTool_desc,
				Collections.singletonList(ProcessElementTypes.Pool_2007));
		entry.setId("createPool1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.Pool_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createLane2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(Messages.Lane2CreationTool_title, Messages.Lane2CreationTool_desc,
				Collections.singletonList(ProcessElementTypes.Lane_3007));
		entry.setId("createLane2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.Lane_3007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createGate1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.ANDGateway_2009);
		types.add(ProcessElementTypes.ANDGateway_3009);
		NodeToolEntry entry = new NodeToolEntry(Messages.Gate1CreationTool_title, Messages.Gate1CreationTool_desc,
				types);
		entry.setId("createGate1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.ANDGateway_2009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createXORGate2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.XORGateway_3008);
		types.add(ProcessElementTypes.XORGateway_2008);
		NodeToolEntry entry = new NodeToolEntry(Messages.XORGate2CreationTool_title, Messages.XORGate2CreationTool_desc,
				types);
		entry.setId("createXORGate2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.XORGateway_3008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createInclusiveGate3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.InclusiveGateway_3051);
		types.add(ProcessElementTypes.InclusiveGateway_2030);
		NodeToolEntry entry = new NodeToolEntry(Messages.InclusiveGate3CreationTool_title,
				Messages.InclusiveGate3CreationTool_desc, types);
		entry.setId("createInclusiveGate3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.InclusiveGateway_3051));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTransition1CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(Messages.Transition1CreationTool_title,
				Messages.Transition1CreationTool_desc,
				Collections.singletonList(ProcessElementTypes.SequenceFlow_4001));
		entry.setId("createTransition1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.SequenceFlow_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createServiceTask1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.ServiceTask_3027);
		types.add(ProcessElementTypes.ServiceTask_2027);
		NodeToolEntry entry = new NodeToolEntry(Messages.ServiceTask1CreationTool_title,
				Messages.ServiceTask1CreationTool_desc, types);
		entry.setId("createServiceTask1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.ServiceTask_3027));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createHuman2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.Task_2004);
		types.add(ProcessElementTypes.Task_3005);
		NodeToolEntry entry = new NodeToolEntry(Messages.Human2CreationTool_title, Messages.Human2CreationTool_desc,
				types);
		entry.setId("createHuman2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.Task_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createScriptTask3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.ScriptTask_3028);
		types.add(ProcessElementTypes.ScriptTask_2028);
		NodeToolEntry entry = new NodeToolEntry(Messages.ScriptTask3CreationTool_title,
				Messages.ScriptTask3CreationTool_desc, types);
		entry.setId("createScriptTask3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.ScriptTask_3028));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createReceiveTask4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.ReceiveTask_3026);
		types.add(ProcessElementTypes.ReceiveTask_2025);
		NodeToolEntry entry = new NodeToolEntry(Messages.ReceiveTask4CreationTool_title,
				Messages.ReceiveTask4CreationTool_desc, types);
		entry.setId("createReceiveTask4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.ReceiveTask_3026));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSendTask5CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.SendTask_3025);
		types.add(ProcessElementTypes.SendTask_2026);
		NodeToolEntry entry = new NodeToolEntry(Messages.SendTask5CreationTool_title,
				Messages.SendTask5CreationTool_desc, types);
		entry.setId("createSendTask5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.SendTask_3025));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createStep6CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.Activity_3006);
		types.add(ProcessElementTypes.Activity_2006);
		NodeToolEntry entry = new NodeToolEntry(Messages.Step6CreationTool_title, Messages.Step6CreationTool_desc,
				types);
		entry.setId("createStep6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.Activity_3006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCallActivity1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.CallActivity_2036);
		types.add(ProcessElementTypes.CallActivity_3063);
		NodeToolEntry entry = new NodeToolEntry(Messages.CallActivity1CreationTool_title,
				Messages.CallActivity1CreationTool_desc, types);
		entry.setId("createCallActivity1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.CallActivity_2036));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEvenementialSubProcess2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.SubProcessEvent_2031);
		types.add(ProcessElementTypes.SubProcessEvent_3058);
		NodeToolEntry entry = new NodeToolEntry(Messages.EvenementialSubProcess2CreationTool_title, null, types);
		entry.setId("createEvenementialSubProcess2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.SubProcessEvent_2031));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createStart1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.StartEvent_2002);
		types.add(ProcessElementTypes.StartEvent_3002);
		NodeToolEntry entry = new NodeToolEntry(Messages.Start1CreationTool_title, Messages.Start1CreationTool_desc,
				types);
		entry.setId("createStart1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.StartEvent_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createStartMessage2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.StartMessageEvent_3012);
		types.add(ProcessElementTypes.StartMessageEvent_2010);
		NodeToolEntry entry = new NodeToolEntry(Messages.StartMessage2CreationTool_title,
				Messages.StartMessage2CreationTool_desc, types);
		entry.setId("createStartMessage2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.StartMessageEvent_3012));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createStartTimer3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.StartTimerEvent_3016);
		types.add(ProcessElementTypes.StartTimerEvent_2016);
		NodeToolEntry entry = new NodeToolEntry(Messages.StartTimer3CreationTool_title,
				Messages.StartTimer3CreationTool_desc, types);
		entry.setId("createStartTimer3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.StartTimerEvent_3016));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createStartSignal4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.StartSignalEvent_3023);
		types.add(ProcessElementTypes.StartSignalEvent_2022);
		NodeToolEntry entry = new NodeToolEntry(Messages.StartSignal4CreationTool_title,
				Messages.StartSignal4CreationTool_desc, types);
		entry.setId("createStartSignal4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.StartSignalEvent_3023));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createStartError5CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.StartErrorEvent_3060);
		types.add(ProcessElementTypes.StartErrorEvent_2033);
		NodeToolEntry entry = new NodeToolEntry(Messages.StartError5CreationTool_title, null, types);
		entry.setId("createStartError5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.StartErrorEvent_3060));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createThrowLink1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.ThrowLinkEvent_3018);
		types.add(ProcessElementTypes.ThrowLinkEvent_2019);
		NodeToolEntry entry = new NodeToolEntry(Messages.ThrowLink1CreationTool_title,
				Messages.ThrowLink1CreationTool_desc, types);
		entry.setId("createThrowLink1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.ThrowLinkEvent_3018));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCatchLink2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.CatchLinkEvent_3019);
		types.add(ProcessElementTypes.CatchLinkEvent_2018);
		NodeToolEntry entry = new NodeToolEntry(Messages.CatchLink2CreationTool_title,
				Messages.CatchLink2CreationTool_desc, types);
		entry.setId("createCatchLink2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.CatchLinkEvent_3019));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createThrowMessage3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.IntermediateThrowMessageEvent_3014);
		types.add(ProcessElementTypes.IntermediateThrowMessageEvent_2014);
		NodeToolEntry entry = new NodeToolEntry(Messages.ThrowMessage3CreationTool_title,
				Messages.ThrowMessage3CreationTool_desc, types);
		entry.setId("createThrowMessage3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				ProcessElementTypes.getImageDescriptor(ProcessElementTypes.IntermediateThrowMessageEvent_3014));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCatchMessage4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(4);
		types.add(ProcessElementTypes.BoundaryMessageEvent_3035);
		types.add(ProcessElementTypes.BoundaryMessageEvent_3036);
		types.add(ProcessElementTypes.IntermediateCatchMessageEvent_3013);
		types.add(ProcessElementTypes.IntermediateCatchMessageEvent_2013);
		NodeToolEntry entry = new NodeToolEntry(Messages.CatchMessage4CreationTool_title,
				Messages.CatchMessage4CreationTool_desc, types);
		entry.setId("createCatchMessage4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.BoundaryMessageEvent_3035));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createThrowSignal5CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.IntermediateThrowSignalEvent_3022);
		types.add(ProcessElementTypes.IntermediateThrowSignalEvent_2020);
		NodeToolEntry entry = new NodeToolEntry(Messages.ThrowSignal5CreationTool_title,
				Messages.ThrowSignal5CreationTool_desc, types);
		entry.setId("createThrowSignal5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				ProcessElementTypes.getImageDescriptor(ProcessElementTypes.IntermediateThrowSignalEvent_3022));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCatchSignal6CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(4);
		types.add(ProcessElementTypes.BoundarySignalEvent_3052);
		types.add(ProcessElementTypes.BoundarySignalEvent_3053);
		types.add(ProcessElementTypes.IntermediateCatchSignalEvent_3021);
		types.add(ProcessElementTypes.IntermediateCatchSignalEvent_2021);
		NodeToolEntry entry = new NodeToolEntry(Messages.CatchSignal6CreationTool_title,
				Messages.CatchSignal6CreationTool_desc, types);
		entry.setId("createCatchSignal6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.BoundarySignalEvent_3052));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCatchError7CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(6);
		types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3029);
		types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3030);
		types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3031);
		types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3032);
		types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3033);
		types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3034);
		NodeToolEntry entry = new NodeToolEntry(Messages.CatchError7CreationTool_title,
				Messages.CatchError7CreationTool_desc, types);
		entry.setId("createCatchError7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				ProcessElementTypes.getImageDescriptor(ProcessElementTypes.IntermediateErrorCatchEvent_3029));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNonInterruptingTimer8CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3064);
		types.add(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3065);
		NodeToolEntry entry = new NodeToolEntry(Messages.NonInterruptingTimer8CreationTool_title,
				Messages.NonInterruptingTimer8CreationTool_desc, types);
		entry.setId("createNonInterruptingTimer8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				ProcessElementTypes.getImageDescriptor(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3064));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEvent9CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.Event_3024);
		types.add(ProcessElementTypes.Event_2024);
		NodeToolEntry entry = new NodeToolEntry(Messages.Event9CreationTool_title, Messages.Event9CreationTool_desc,
				types);
		entry.setId("createEvent9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.model.edit/icons/full/obj16/Event.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTimer10CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(4);
		types.add(ProcessElementTypes.BoundaryTimerEvent_3043);
		types.add(ProcessElementTypes.BoundaryTimerEvent_3044);
		types.add(ProcessElementTypes.IntermediateCatchTimerEvent_3017);
		types.add(ProcessElementTypes.IntermediateCatchTimerEvent_2017);
		NodeToolEntry entry = new NodeToolEntry(Messages.Timer10CreationTool_title, Messages.Timer10CreationTool_desc,
				types);
		entry.setId("createTimer10CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.BoundaryTimerEvent_3043));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEnd1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.EndEvent_2003);
		types.add(ProcessElementTypes.EndEvent_3003);
		NodeToolEntry entry = new NodeToolEntry(Messages.End1CreationTool_title, Messages.End1CreationTool_desc, types);
		entry.setId("createEnd1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.EndEvent_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEndMessage2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.EndMessageEvent_3011);
		types.add(ProcessElementTypes.EndMessageEvent_2011);
		NodeToolEntry entry = new NodeToolEntry(Messages.EndMessage2CreationTool_title,
				Messages.EndMessage2CreationTool_desc, types);
		entry.setId("createEndMessage2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.EndMessageEvent_3011));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEndSignal3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.EndSignalEvent_3020);
		types.add(ProcessElementTypes.EndSignalEvent_2023);
		NodeToolEntry entry = new NodeToolEntry(Messages.EndSignal3CreationTool_title,
				Messages.EndSignal3CreationTool_desc, types);
		entry.setId("createEndSignal3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.EndSignalEvent_3020));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEndError4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.EndErrorEvent_3050);
		types.add(ProcessElementTypes.EndErrorEvent_2029);
		NodeToolEntry entry = new NodeToolEntry(Messages.EndError4CreationTool_title,
				Messages.EndError4CreationTool_desc, types);
		entry.setId("createEndError4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.EndErrorEvent_3050));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEndTerminatedEvent5CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.EndTerminatedEvent_3062);
		types.add(ProcessElementTypes.EndTerminatedEvent_2035);
		NodeToolEntry entry = new NodeToolEntry(Messages.EndTerminatedEvent5CreationTool_title, null, types);
		entry.setId("createEndTerminatedEvent5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.EndTerminatedEvent_3062));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTextAnnotation1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.TextAnnotation_3015);
		types.add(ProcessElementTypes.TextAnnotation_2015);
		NodeToolEntry entry = new NodeToolEntry(Messages.TextAnnotation1CreationTool_title,
				Messages.TextAnnotation1CreationTool_desc, types);
		entry.setId("createTextAnnotation1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.TextAnnotation_3015));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/

	/**@deprecated WARN : set t public only as a workaround in order to be able be used in a sublcass of PaletteToolEntry*/
	public static class NodeToolEntry extends PaletteToolEntry {

		/**
		* @generated
		*/
		private final List<IElementType> elementTypes;;

		/**
		* @generated
		*/
		/**@deprecated WARN : set t public only as a workaround in order to be able be used in a sublcass of PaletteToolEntry*/
		public NodeToolEntry(String title, String description, List<IElementType> elementTypes) {

			super(null, NamingUtils.getPaletteTitle(elementTypes), null);
			super.setDescription(NamingUtils.getPaletteDescription(elementTypes));
			this.elementTypes = elementTypes;
		}

		/**
		* Used a custom tool to have a better feedback.
		* @generated
		*/
		public Tool createTool() {

			Tool tool = new BonitaUnspecifiedTypeProcessCreationTool(BonitaConnectionTypes.getTypesFor(elementTypes));
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	* @generated
	*/
	private static class LinkToolEntry extends ToolEntry {

		/**
		* @generated
		*/
		private final List relationshipTypes;

		/**
		* @generated
		*/
		private LinkToolEntry(String title, String description, List relationshipTypes) {
			super(NamingUtils.getPaletteTitle(relationshipTypes), NamingUtils.getPaletteDescription(relationshipTypes),
					null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		* @generated
		*/
		public Tool createTool() {

			Tool tool = new BonitaUnspecifiedTypeConnectionTool(BonitaConnectionTypes.getRelationTypes());
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

}
