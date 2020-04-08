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

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;

/**
 * @author Romain Bioteau
 *
 */
public class DefaultElementNameProvider {

    private static final String BOUNDARY_PREFIX = "Boundary ";
    private static Map<Integer, String> classifierIDToDefaultName;
    static {
        classifierIDToDefaultName = new HashMap<Integer, String>();

        classifierIDToDefaultName.put(ProcessPackage.SUB_PROCESS_EVENT, Messages.SubprocessEventDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.AND_GATEWAY, Messages.GatewayDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.XOR_GATEWAY, Messages.GatewayDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.INCLUSIVE_GATEWAY, Messages.GatewayDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.ACTIVITY, Messages.StepDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.TASK, Messages.StepDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.SERVICE_TASK, Messages.StepDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.SCRIPT_TASK, Messages.StepDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.SEND_TASK, Messages.StepDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.RECEIVE_TASK, Messages.StepDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.POOL, Messages.PoolDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.SEQUENCE_FLOW, Messages.SequenceFlowDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.MESSAGE_FLOW, Messages.MessageFlowDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.START_ERROR_EVENT, Messages.startEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.START_EVENT, Messages.startEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.START_MESSAGE_EVENT, Messages.startEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.START_SIGNAL_EVENT, Messages.startEventDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.END_ERROR_EVENT, Messages.endEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.END_EVENT, Messages.endEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.END_MESSAGE_EVENT, Messages.endEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.END_TERMINATED_EVENT, Messages.endEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.END_SIGNAL_EVENT, Messages.endEventDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.INTERMEDIATE_ERROR_CATCH_EVENT, Messages.IntermediateErrorCatchEventLabel);

        classifierIDToDefaultName.put(ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT, Messages.signalEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT, Messages.signalEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.BOUNDARY_SIGNAL_EVENT, BOUNDARY_PREFIX + Messages.signalEventDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT, Messages.intermediateMessageEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.INTERMEDIATE_THROW_MESSAGE_EVENT, Messages.intermediateMessageEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.BOUNDARY_MESSAGE_EVENT, BOUNDARY_PREFIX + Messages.intermediateMessageEventDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.INTERMEDIATE_CATCH_TIMER_EVENT, Messages.intermeiateTimerEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.TIMER_EVENT, Messages.intermeiateTimerEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.START_TIMER_EVENT, Messages.intermeiateTimerEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.BOUNDARY_TIMER_EVENT, BOUNDARY_PREFIX + Messages.intermeiateTimerEventDefaultName);

        classifierIDToDefaultName.put(ProcessPackage.THROW_LINK_EVENT, Messages.linkEventDefaultName);
        classifierIDToDefaultName.put(ProcessPackage.CATCH_LINK_EVENT, Messages.linkEventDefaultName);
    }

    private final ProcessPaletteLabelProvider processPaletteLabelProvider;

    public DefaultElementNameProvider() {
        processPaletteLabelProvider = new ProcessPaletteLabelProvider();
    }

    public String getNameFor(final Element element) {
        String name = classifierIDToDefaultName.get(element.eClass().getClassifierID());
        if (name == null) {
            name = processPaletteLabelProvider.getProcessPaletteText(element.eClass());
        }
        return name;
    }

}
