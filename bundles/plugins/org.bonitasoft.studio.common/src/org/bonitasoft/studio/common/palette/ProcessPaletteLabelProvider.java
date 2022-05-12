/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.palette;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;

/**
 * @author Romain Bioteau
 *
 */
public class ProcessPaletteLabelProvider {

    private static Map<Integer, String> classifierIDToTitle;
    private static Map<Integer, String> classifierIDToDescription;
    static {
        classifierIDToTitle = new HashMap<Integer, String>();
        classifierIDToDescription = new HashMap<Integer, String>();

        classifierIDToTitle.put(ProcessPackage.ACTIVITY, Messages.Step_title);
        classifierIDToDescription.put(ProcessPackage.ACTIVITY, Messages.Step_desc);

        classifierIDToTitle.put(ProcessPackage.CALL_ACTIVITY, Messages.CallActivity_title);
        classifierIDToDescription.put(ProcessPackage.CALL_ACTIVITY, Messages.CallActivity_desc);

        classifierIDToTitle.put(ProcessPackage.TASK, Messages.Human_title);
        classifierIDToDescription.put(ProcessPackage.TASK, Messages.Human_desc);

        classifierIDToTitle.put(ProcessPackage.AND_GATEWAY, Messages.Gate_title);
        classifierIDToDescription.put(ProcessPackage.AND_GATEWAY, Messages.Gate_desc);

        classifierIDToTitle.put(ProcessPackage.XOR_GATEWAY, Messages.XORGate_title);
        classifierIDToDescription.put(ProcessPackage.XOR_GATEWAY, Messages.XORGate_desc);

        classifierIDToTitle.put(ProcessPackage.INCLUSIVE_GATEWAY, Messages.InclusiveGate_title);
        classifierIDToDescription.put(ProcessPackage.INCLUSIVE_GATEWAY, Messages.InclusiveGate_desc);

        classifierIDToTitle.put(ProcessPackage.THROW_LINK_EVENT, Messages.ThrowLink_title);
        classifierIDToDescription.put(ProcessPackage.THROW_LINK_EVENT, Messages.ThrowLink_desc);

        classifierIDToTitle.put(ProcessPackage.CATCH_LINK_EVENT, Messages.CatchLink_title);
        classifierIDToDescription.put(ProcessPackage.CATCH_LINK_EVENT, Messages.CatchLink_desc);

        classifierIDToTitle.put(ProcessPackage.POOL, Messages.Pool_title);
        classifierIDToDescription.put(ProcessPackage.POOL, Messages.Pool_desc);

        classifierIDToTitle.put(ProcessPackage.LANE, Messages.Lane_title);
        classifierIDToDescription.put(ProcessPackage.LANE, Messages.Lane_desc);

        classifierIDToTitle.put(ProcessPackage.TEXT_ANNOTATION, Messages.TextAnnotation_title);
        classifierIDToDescription.put(ProcessPackage.TEXT_ANNOTATION, Messages.TextAnnotation_desc);

        classifierIDToTitle.put(ProcessPackage.START_EVENT, Messages.Start_title);
        classifierIDToDescription.put(ProcessPackage.START_EVENT, Messages.Start_desc);

        classifierIDToTitle.put(ProcessPackage.START_MESSAGE_EVENT, Messages.StartMessage_title);
        classifierIDToDescription.put(ProcessPackage.START_MESSAGE_EVENT, Messages.StartMessage_desc);

        classifierIDToTitle.put(ProcessPackage.START_ERROR_EVENT, Messages.StartError_title);
        classifierIDToDescription.put(ProcessPackage.START_ERROR_EVENT, Messages.StartError_desc);

        classifierIDToTitle.put(ProcessPackage.START_SIGNAL_EVENT, Messages.StartSignal_title);
        classifierIDToDescription.put(ProcessPackage.START_SIGNAL_EVENT, Messages.StartSignal_desc);

        classifierIDToTitle.put(ProcessPackage.START_TIMER_EVENT, Messages.StartTimer_title);
        classifierIDToDescription.put(ProcessPackage.START_TIMER_EVENT, Messages.StartTimer_desc);

        classifierIDToTitle.put(ProcessPackage.END_EVENT, Messages.End_title);
        classifierIDToDescription.put(ProcessPackage.END_EVENT, Messages.End_desc);

        classifierIDToTitle.put(ProcessPackage.END_ERROR_EVENT, Messages.EndError_title);
        classifierIDToDescription.put(ProcessPackage.END_ERROR_EVENT, Messages.EndError_desc);

        classifierIDToTitle.put(ProcessPackage.END_SIGNAL_EVENT, Messages.EndSignal_title);
        classifierIDToDescription.put(ProcessPackage.END_SIGNAL_EVENT, Messages.EndSignal_desc);

        classifierIDToTitle.put(ProcessPackage.END_MESSAGE_EVENT, Messages.EndMessage_title);
        classifierIDToDescription.put(ProcessPackage.END_MESSAGE_EVENT, Messages.EndMessage_desc);

        classifierIDToTitle.put(ProcessPackage.END_TERMINATED_EVENT, Messages.TerminateEnd_title);
        classifierIDToDescription.put(ProcessPackage.END_TERMINATED_EVENT, Messages.TerminateEnd_desc);

        classifierIDToTitle.put(ProcessPackage.BOUNDARY_TIMER_EVENT, Messages.Timer_title);
        classifierIDToDescription.put(ProcessPackage.BOUNDARY_TIMER_EVENT, Messages.Timer_desc);

        classifierIDToTitle.put(ProcessPackage.TIMER_EVENT, Messages.Timer_title);
        classifierIDToDescription.put(ProcessPackage.TIMER_EVENT, Messages.Timer_desc);

        classifierIDToTitle.put(ProcessPackage.INTERMEDIATE_CATCH_TIMER_EVENT, Messages.Timer_title);
        classifierIDToDescription.put(ProcessPackage.INTERMEDIATE_CATCH_TIMER_EVENT, Messages.Timer_desc);

        classifierIDToTitle.put(ProcessPackage.INTERMEDIATE_THROW_MESSAGE_EVENT, Messages.ThrowMessage_title);
        classifierIDToDescription.put(ProcessPackage.INTERMEDIATE_THROW_MESSAGE_EVENT, Messages.ThrowMessage_desc);

        classifierIDToTitle.put(ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT, Messages.CatchMessage_title);
        classifierIDToDescription.put(ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT, Messages.CatchMessage_desc);

        classifierIDToTitle.put(ProcessPackage.BOUNDARY_MESSAGE_EVENT, Messages.CatchMessage_title);
        classifierIDToDescription.put(ProcessPackage.BOUNDARY_MESSAGE_EVENT, Messages.CatchMessage_desc);

        classifierIDToTitle.put(ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT, Messages.ThrowSignal_title);
        classifierIDToDescription.put(ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT, Messages.ThrowSignal_desc);

        classifierIDToTitle.put(ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT, Messages.CatchSignal_title);
        classifierIDToDescription.put(ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT, Messages.CatchSignal_desc);

        classifierIDToTitle.put(ProcessPackage.BOUNDARY_SIGNAL_EVENT, Messages.CatchSignal_title);
        classifierIDToDescription.put(ProcessPackage.BOUNDARY_SIGNAL_EVENT, Messages.CatchSignal_desc);

        classifierIDToTitle.put(ProcessPackage.INTERMEDIATE_ERROR_CATCH_EVENT, Messages.CatchError_title);
        classifierIDToDescription.put(ProcessPackage.INTERMEDIATE_ERROR_CATCH_EVENT, Messages.CatchError_desc);

        classifierIDToTitle.put(ProcessPackage.NON_INTERRUPTING_BOUNDARY_TIMER_EVENT, Messages.nonInterruptingTimerEvent_title);
        classifierIDToDescription.put(ProcessPackage.NON_INTERRUPTING_BOUNDARY_TIMER_EVENT, Messages.nonInterruptingTimerEvent_desc);

        classifierIDToTitle.put(ProcessPackage.EVENT, Messages.Event_title);
        classifierIDToDescription.put(ProcessPackage.EVENT, Messages.Event_desc);

        classifierIDToTitle.put(ProcessPackage.SERVICE_TASK, Messages.ServiceTask_title);
        classifierIDToDescription.put(ProcessPackage.SERVICE_TASK, Messages.ServiceTask_desc);

        classifierIDToTitle.put(ProcessPackage.SCRIPT_TASK, Messages.ScriptTask_title);
        classifierIDToDescription.put(ProcessPackage.SCRIPT_TASK, Messages.ScriptTask_desc);

        classifierIDToTitle.put(ProcessPackage.RECEIVE_TASK, Messages.ReceiveTask_title);
        classifierIDToDescription.put(ProcessPackage.RECEIVE_TASK, Messages.ReceiveTask_desc);

        classifierIDToTitle.put(ProcessPackage.SEND_TASK, Messages.SendTask_title);
        classifierIDToDescription.put(ProcessPackage.SEND_TASK, Messages.SendTask_desc);

        classifierIDToTitle.put(ProcessPackage.SEQUENCE_FLOW, Messages.Transition_title);
        classifierIDToDescription.put(ProcessPackage.SEQUENCE_FLOW, Messages.Transition_desc);

        classifierIDToTitle.put(ProcessPackage.SUB_PROCESS_EVENT, Messages.SubprocessEvent_title);
        classifierIDToDescription.put(ProcessPackage.SUB_PROCESS_EVENT, Messages.SubprocessEvent_desc);
    }

    public String getProcessPaletteDescription(final EClass eClass) {
        final int id = getEClassifierId(eClass);
        return classifierIDToDescription.get(id);
    }

    public String getProcessPaletteText(final EClass eClass) {
        final int id = getEClassifierId(eClass);
        final String label = classifierIDToTitle.get(id);
        if (label == null) {
            return eClass.getName();
        }
        return label;
    }

    protected int getEClassifierId(final EClass eClass) {
        Assert.isLegal(eClass != null);
        Assert.isLegal(ProcessPackage.eINSTANCE.equals(eClass.getEPackage()), MessageFormat.format("EClass {0} is not supported.", eClass.getName()));
        return eClass.getClassifierID();
    }



}
