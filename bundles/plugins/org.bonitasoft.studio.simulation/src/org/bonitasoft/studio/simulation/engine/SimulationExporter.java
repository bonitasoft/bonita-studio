/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.simulation.engine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.simulation.engine.SimulationException;
import org.bonitasoft.simulation.model.Period;
import org.bonitasoft.simulation.model.RepartitionType;
import org.bonitasoft.simulation.model.TimeUnit;
import org.bonitasoft.simulation.model.calendar.SimCalendar;
import org.bonitasoft.simulation.model.calendar.SimCalendarPeriod;
import org.bonitasoft.simulation.model.calendar.SimCalendarTime;
import org.bonitasoft.simulation.model.loadprofile.InjectionPeriod;
import org.bonitasoft.simulation.model.process.JoinType;
import org.bonitasoft.simulation.model.process.NumericRange;
import org.bonitasoft.simulation.model.process.ResourceAssignement;
import org.bonitasoft.simulation.model.process.SimActivity;
import org.bonitasoft.simulation.model.process.SimBooleanData;
import org.bonitasoft.simulation.model.process.SimData;
import org.bonitasoft.simulation.model.process.SimLiteral;
import org.bonitasoft.simulation.model.process.SimLiteralsData;
import org.bonitasoft.simulation.model.process.SimNumberData;
import org.bonitasoft.simulation.model.process.SimProcess;
import org.bonitasoft.simulation.model.process.SimTransition;
import org.bonitasoft.simulation.model.resource.Resource;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.model.simulation.DataChange;
import org.bonitasoft.studio.model.simulation.DayPeriod;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.model.simulation.ResourceUsage;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.model.simulation.SimulationBoolean;
import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationLiteral;
import org.bonitasoft.studio.model.simulation.SimulationLiteralData;
import org.bonitasoft.studio.model.simulation.SimulationNumberData;
import org.bonitasoft.studio.model.simulation.SimulationNumberRange;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationResourceRepositoryStore;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

/**
 * @author Romain Bioteau
 */
public class SimulationExporter {

    private final Map<org.bonitasoft.studio.model.simulation.Resource, Resource> resourcesMap = new HashMap<org.bonitasoft.studio.model.simulation.Resource, Resource>();
    private final Map<String, List<Resource>> usedResourceMap = new HashMap<String, List<Resource>>();
    private Map<String, SimActivity> simulationActivities;

    public List<SimProcess> createSimulationModel(final List<AbstractProcess> processes) throws Exception {

        final List<SimProcess> results = new ArrayList<SimProcess>();
        for (final AbstractProcess abstractProcess : processes) {
            results.add(createSimulationProcess(abstractProcess));
        }
        return results;
    }

    public SimProcess createSimulationProcess(final AbstractProcess process) throws Exception {
        // CREATE SIMULATION PROCESS
        final SimProcess simProcess = new SimProcess(process.getName(), process.getMaximumTime());
        final List<Resource> usedResource = new ArrayList<Resource>();
        usedResourceMap.put(simProcess.getName(), usedResource);

        final List<Element> elements = new ArrayList<Element>();
        final List<EClass> types = new ArrayList<EClass>();

        types.clear();
        elements.clear();
        types.add(ProcessPackage.Literals.START_EVENT);
        types.add(ProcessPackage.Literals.START_MESSAGE_EVENT);
        types.add(ProcessPackage.Literals.START_SIGNAL_EVENT);
        types.add(ProcessPackage.Literals.START_TIMER_EVENT);
        ModelHelper.findAllElements(process, elements, types);
        if (elements.size() != 1) {
            throw new SimulationException(Messages.simulation_Error_startEvent);
        }
        final List<FlowElement> flowElements = new ArrayList<FlowElement>();
        for (final Element elem : elements) {
            flowElements.add((FlowElement) elem);
        }

        final Map<SimulationActivity, SimActivity> processElems = new HashMap<SimulationActivity, SimActivity>();
        final List<SimTransition> processTransitions = new ArrayList<SimTransition>();
        final HashSet<SimActivity> startElems = new HashSet<SimActivity>();
        simulationActivities = new HashMap<String, SimActivity>();
        buildProcess(flowElements, null, processElems, processTransitions, true, simProcess.getName(), startElems);
        for (final SimActivity start : startElems) {
            simProcess.addStartElement(start);
        }
        createData(simProcess, process);
        return simProcess;
    }

    /**
     * @param simProcess
     * @param process
     */
    private void createData(final SimProcess simProcess, final AbstractProcess process) {
        for (final SimulationData data : process.getSimulationData()) {
            simProcess.addData(getData(data));
        }
    }

    /**
     * @param data
     * @return
     */
    public SimData getData(final SimulationData data) {
        if (data instanceof SimulationBoolean) {
            if (data.isExpressionBased()) {
                return new SimBooleanData(data.getName(), data.getExpression() == null ? "" : data.getExpression().getContent());
            } else {
                final SimulationBoolean booleanData = (SimulationBoolean) data;
                return new SimBooleanData(booleanData.getName(), booleanData.getProbabilityOfTrue());
            }
        } else if (data instanceof SimulationLiteralData) {
            if (data.isExpressionBased()) {
                // TODO expression for literals
                throw new UnsupportedOperationException();
            } else {
                final SimulationLiteralData literalData = (SimulationLiteralData) data;
                final List<SimLiteral> literals = new ArrayList<SimLiteral>();
                for (final SimulationLiteral literal : literalData.getLiterals()) {
                    literals.add(new SimLiteral(literal.getValue(), literal.getProbability()));
                }
                return new SimLiteralsData(literalData.getName(), literals);
            }
        } else if (data instanceof SimulationNumberData) {

            if (data.isExpressionBased()) {
                return new SimNumberData(data.getName(), data.getExpression() == null ? "" : data.getExpression().getContent());
            } else {
                final SimulationNumberData numberData = (SimulationNumberData) data;
                final List<NumericRange> ranges = new ArrayList<NumericRange>();
                for (final SimulationNumberRange numericRange : numberData.getRanges()) {
                    ranges.add(new NumericRange(numericRange.getMin(), numericRange.getMax(), numericRange.getProbability(), RepartitionType.CONSTANT));
                }
                return new SimNumberData(numberData.getName(), ranges);
            }
        } else {
            return null;
        }
    }

    public void buildProcess(final List<FlowElement> elems, final SimActivity simActivity, final Map<SimulationActivity, SimActivity> processElems,
            final List<SimTransition> processTransitions,
            final boolean isStartElement, final String parentProcessName, final Set<SimActivity> startElems) throws Exception {

        for (final FlowElement startElem : elems) {
            SimActivity simElem = null;
            if (simActivity == null) {
                simElem = getSimActivity(startElem, processElems, isStartElement, parentProcessName);
            } else {
                simElem = simActivity;
            }

            final EList<Connection> outgoingConnection = startElem.getOutgoing();
            for (final Connection c : outgoingConnection) {
                if (c instanceof SequenceFlow) {
                    String transitionName;
                    if (c.getName() == null || c.getName().trim().length() == 0) {
                        transitionName = c.getSource().getName() + "_" + c.getTarget().getName(); //$NON-NLS-1$
                    } else {
                        transitionName = c.getName();
                    }
                    SimTransition t;
                    if (c.getTarget() instanceof FlowElement && !(c.getTarget() instanceof ThrowLinkEvent)) {
                        if (c.getTarget().getName().equals(simElem.getName())) {
                            throw new Exception(Messages.loopError);
                        }

                        if (c.isUseExpression() && c.getExpression() != null) {
                            t = new SimTransition(transitionName, getSimActivity((FlowElement) c.getTarget(), processElems, false, parentProcessName), true,
                                    toSimpleString(c.getExpression()));
                        } else {
                            t = new SimTransition(transitionName, getSimActivity((FlowElement) c.getTarget(), processElems, false, parentProcessName), false, c
                                    .getProbability());
                        }

                        if (!processTransitions.contains(t)) {
                            processTransitions.add(t);
                        }

                        simElem.addOutgoingTransition(t);
                        simulationActivities.put(simElem.getName(), simElem);
                        if (!simulationActivities.containsKey(t.getTarget().getName())) {
                            buildProcess(Collections.singletonList((FlowElement) c.getTarget()), t.getTarget(), processElems, processTransitions, false,
                                    parentProcessName, startElems);
                        }
                    } else if (c.getTarget() instanceof ThrowLinkEvent) {
                        if (c.getTarget().getName().equals(simElem.getName())) {
                            throw new Exception(Messages.loopError);
                        }
                        final ThrowLinkEvent throwLink = (ThrowLinkEvent) c.getTarget();
                        if (throwLink.getTo() != null) {
                            final CatchLinkEvent target = throwLink.getTo();
                            for (final Connection conn : target.getOutgoing()) {
                                if (conn.getName() == null || conn.getName().trim().length() == 0) {
                                    transitionName = c.getSource().getName() + "_" + conn.getTarget().getName(); //$NON-NLS-1$
                                } else {
                                    transitionName = conn.getName();
                                }
                                if (conn.isUseExpression() && c.getExpression() != null) {
                                    t = new SimTransition(transitionName,
                                            getSimActivity((FlowElement) conn.getTarget(), processElems, false, parentProcessName), true,
                                            toSimpleString(c.getExpression()));
                                } else {
                                    t = new SimTransition(transitionName,
                                            getSimActivity((FlowElement) conn.getTarget(), processElems, false, parentProcessName), false, c.getProbability());
                                }

                                if (!processTransitions.contains(t)) {
                                    processTransitions.add(t);
                                }

                                simElem.addOutgoingTransition(t);
                                simulationActivities.put(simElem.getName(), simElem);
                                if (!simulationActivities.containsKey(conn.getTarget().getName())) {
                                    buildProcess(Collections.singletonList((FlowElement) conn.getTarget()), t.getTarget(), processElems, processTransitions,
                                            false, parentProcessName, startElems);
                                }
                            }
                        }

                    }
                }
            }
            if (simElem.isStartElement()) {
                startElems.add(simElem);
            }
        }

    }

    private String toSimpleString(final Expression expression) throws Exception {
        if (expression == null) {
            return "";
        } else {
            return expression.getContent();
        }
    }

    /**
     * @param target
     * @param processElems
     * @param parentProcessName
     * @param isStartElement
     * @return
     * @throws Exception
     */
    private SimActivity getSimActivity(final FlowElement activity, final Map<SimulationActivity, SimActivity> processElems, final boolean isStartElement,
            final String parentProcessName) throws Exception {
        SimActivity simElem;
        if (processElems.containsKey(activity)) {
            simElem = processElems.get(activity);
        } else {

            final long estimatedTime = Math.round(activity.getExecutionTime() + activity.getEstimatedTime() * activity.getExecutionTime());
            final long maximumTime = Math.round(activity.getExecutionTime() + activity.getMaximumTime() * activity.getExecutionTime());

            if (activity instanceof ANDGateway) {
                simElem = new SimActivity(activity.getName(), JoinType.AND, parentProcessName, activity.getExecutionTime(), estimatedTime, maximumTime,
                        activity.isExclusiveOutgoingTransition(), activity.isContigous());
            } else if (activity instanceof XORGateway) {
                simElem = new SimActivity(activity.getName(), parentProcessName, isStartElement, activity.getExecutionTime(), estimatedTime, maximumTime,
                        true, activity.isContigous());
            } else {
                simElem = new SimActivity(activity.getName(), parentProcessName, isStartElement, activity.getExecutionTime(), estimatedTime, maximumTime,
                        activity.isExclusiveOutgoingTransition(), activity.isContigous());
            }
            addResourceAssignments(simElem, activity);
            addDataChanges(simElem, activity);
            processElems.put(activity, simElem);
        }
        return simElem;
    }

    /**
     * @param simElem
     * @param activity
     * @throws Exception
     */
    private void addDataChanges(final SimActivity simElem, final FlowElement activity) throws Exception {
        for (final DataChange dataChange : activity.getDataChange()) {
            if (dataChange.getData() != null && dataChange.getValue() != null) {
                if (dataChange.getData() instanceof SimulationBoolean) {
                    simElem.addData(new SimBooleanData(dataChange.getData().getName(), toSimpleString(dataChange.getValue())));
                } else if (dataChange.getData() instanceof SimulationLiteralData) {
                    simElem.addData(new SimLiteralsData(dataChange.getData().getName(), toSimpleString(dataChange.getValue())));
                } else if (dataChange.getData() instanceof SimulationNumberData) {
                    simElem.addData(new SimNumberData(dataChange.getData().getName(), toSimpleString(dataChange.getValue())));
                }
            }
        }
    }

    /**
     * @param simElem
     * @param activity
     * @throws ReadFileStoreException
     */
    private void addResourceAssignments(final SimActivity simElem, final FlowElement activity) throws ReadFileStoreException {
        for (final ResourceUsage resourceUsage : activity.getResourcesUsages()) {

            ResourceAssignement assignment;
            if (resourceUsage.isUseActivityDuration()) {
                assignment = new ResourceAssignement(getResource(resourceUsage.getResourceID()), activity.getExecutionTime(), resourceUsage
                        .getQuantity());
            } else {
                assignment = new ResourceAssignement(getResource(resourceUsage.getResourceID()), resourceUsage.getDuration(), resourceUsage
                        .getQuantity());
            }
            simElem.addResourceAssignement(assignment);
            final List<Resource> list = usedResourceMap.get(simElem.getParentProcessName());
            final Resource assignmentResource = assignment.getResource();
            if (!list.contains(assignmentResource)) {
                list.add(assignmentResource);
            }
        }
    }

    /**
     * @param modelResource
     * @return
     * @throws ReadFileStoreException
     */
    private Resource getResource(final String resourceID) throws ReadFileStoreException {
        final SimulationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class);
        final IRepositoryFileStore file = store.getChild(resourceID + "." + SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT);
        if (file != null) {
            final org.bonitasoft.studio.model.simulation.Resource modelResource = (org.bonitasoft.studio.model.simulation.Resource) file.getContent();
            Resource resource;
            if (resourcesMap.containsKey(modelResource)) {
                resource = resourcesMap.get(modelResource);
                return resource;
            } else {
                TimeUnit timeUnit;
                switch (modelResource.getTimeUnit()) {
                    case DAY:
                        timeUnit = TimeUnit.DAY;
                        break;
                    case MINUTE:
                        timeUnit = TimeUnit.MINUTE;
                        break;
                    case HOUR:
                        timeUnit = TimeUnit.HOUR;
                        break;
                    case MONTH:
                        timeUnit = TimeUnit.MONTH;
                        break;
                    case WEEK:
                        timeUnit = TimeUnit.WEEK;
                        break;
                    case YEAR:
                        timeUnit = TimeUnit.YEAR;
                        break;

                    default:
                        // most common unit
                        timeUnit = TimeUnit.HOUR;
                        break;
                }
                int quantity = modelResource.getQuantity();
                if (modelResource.isUnlimited()) {
                    quantity = -1;
                }
                resource = new Resource(modelResource.getName(), modelResource.getType(), quantity, modelResource.getMaximumQuantity(),
                        createSimCalendar(modelResource.getCalendar()), modelResource.getCostUnit(), timeUnit, modelResource.getFixedCost(), modelResource
                                .getTimeCost());
                resourcesMap.put(modelResource, resource);
                return resource;
            }
        }
        return null;
    }

    /**
     * @param calendar
     * @return
     */
    private SimCalendar createSimCalendar(final SimulationCalendar calendar) {
        final SimCalendar cal = new SimCalendar();

        if (calendar != null) {
            final Map<Integer, Set<SimCalendarPeriod>> map = new HashMap<Integer, Set<SimCalendarPeriod>>();
            for (int i = Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_WEEK); i <= Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_WEEK); i++) {
                map.put(i, new HashSet<SimCalendarPeriod>());
            }

            for (final DayPeriod dayPeriod : calendar.getDaysOfWeek()) {
                for (final Integer dayNumber : dayPeriod.getDay()) {
                    map.get(dayNumber).add(
                            new SimCalendarPeriod(new SimCalendarTime(dayPeriod.getStartHour(), dayPeriod.getStartMinute()), new SimCalendarTime(dayPeriod
                                    .getEndHour(), dayPeriod.getEndMinute())));
                }

            }
            for (final Entry<Integer, Set<SimCalendarPeriod>> entry : map.entrySet()) {
                try {
                    cal.addSimCalendarDay(entry.getKey(), entry.getValue());
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return cal;
    }

    /**
     * @param profile
     * @return
     */
    public org.bonitasoft.simulation.model.loadprofile.LoadProfile createLoadProfile(final LoadProfile profile) {
        final org.bonitasoft.simulation.model.loadprofile.LoadProfile loadProfile = new org.bonitasoft.simulation.model.loadprofile.LoadProfile(
                createSimCalendar(profile.getCalendar()), createInjectionPeriods(profile.getInjectionPeriods()));
        return loadProfile;
    }

    /**
     * @param injectionPeriods
     * @return
     */
    private List<InjectionPeriod> createInjectionPeriods(final EList<org.bonitasoft.studio.model.simulation.InjectionPeriod> injectionPeriods) {
        final List<InjectionPeriod> result = new ArrayList<InjectionPeriod>();
        for (final org.bonitasoft.studio.model.simulation.InjectionPeriod injectionPeriod : injectionPeriods) {
            RepartitionType type;
            if (injectionPeriod.getRepartition().equals(org.bonitasoft.studio.model.simulation.RepartitionType.CONSTANT)) {
                type = RepartitionType.CONSTANT;
            } else {
                type = RepartitionType.DIRECT;
            }
            result.add(new InjectionPeriod(new Period(injectionPeriod.getBegin(), injectionPeriod.getEnd()), injectionPeriod.getNbInstances(), type));
        }
        return result;
    }

    /**
     * @return the resourcesMap
     */
    public List<Resource> getSimProcessResources(final String simProcessName) {
        return usedResourceMap.get(simProcessName);
    }

}
