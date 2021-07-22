/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.common.emf.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BusinessObjectType;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.model.process.StringType;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.XMLType;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 */
public class ModelHelper {

    public static final String STRING_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.stringDataType);

    public static final String INTEGER_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.integerDataType);

    public static final String FLOAT_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.floatDataType);

    public static final String BOOLEAN_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.booleanDataType);

    public static final String DATE_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.dateDataType);

    public static final String JAVA_DATA_TYPE = NamingUtils.convertToId(DataTypeLabels.javaDataType);

    /**
     * To be used when dealing with scopes (for groups or datatypes)
     *
     * @param a
     *        model object
     * @return the list of parent processes for this object. Includes object if
     *         object is a process. This basically contains: * Only the Main
     *         process in case of a top level node which is contained in main
     *         process * Main process and pool for pools and items that are
     *         contained inside a pool
     */
    public static List<AbstractProcess> getAllParentProcesses(EObject eObject) {
        final List<AbstractProcess> res = new ArrayList<AbstractProcess>();
        while (eObject != null) {
            if (eObject instanceof AbstractProcess) {
                res.add((AbstractProcess) eObject);
            }
            eObject = eObject.eContainer();
        }
        return res;
    }

    public static List<AbstractProcess> getAllProcesses(final Element eObject) {
        final List<AbstractProcess> res = new ArrayList<>();
        final MainProcess mainProcess = getMainProcess(eObject);
        if (mainProcess != null) {
            findAllProcesses(mainProcess, res);
        }
        return res;
    }


    private static List<AbstractProcess> findAllProcesses(final Element element, final List<AbstractProcess> processes) {
        final List<AbstractProcess> oldwayFindProcesses = oldwayFindProcesses(element, processes);
        return oldwayFindProcesses;
    }

    protected static List<AbstractProcess> oldwayFindProcesses(final Element element,
            final List<AbstractProcess> processes) {
        if (element instanceof AbstractProcess && !(element instanceof MainProcess)) {

            processes.add((AbstractProcess) element);
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                oldwayFindProcesses(e, processes);
            }

        }
        return processes;
    }

    /**
     * Return Top Parent execlude embedded process
     *
     * @param object
     * @return process
     */
    public static AbstractProcess getParentProcess(final EObject object) {
        EObject process = object;
        while (process != null && !(process instanceof AbstractProcess && !(process instanceof SubProcessEvent))) {
            if (process.eContainer() != null) {
                process = process.eContainer();
            } else {
                break;
            }
        }
        if (process instanceof AbstractProcess) {
            return (AbstractProcess) process;
        } else {
            return null;
        }
    }

    public static Container getParentContainer(final EObject object) {
        EObject container = object;
        while (container != null && !(container instanceof Container)) {
            if (container.eContainer() != null) {
                container = container.eContainer();
            } else {
                break;
            }
        }
        if (container instanceof Container) {
            return (Container) container;
        } else {
            return null;
        }
    }

    public static MainProcess getMainProcess(final EObject object) {
        if (object == null) {
            return null;
        }
        EObject process = object;
        while (!(process instanceof MainProcess)) {
            process = process.eContainer();
            if (process == null) {
                return null;
            }
        }
        return (MainProcess) process;
    }

    public static ConnectableElement getConnectableElement(final EObject object) {
        EObject element = object;
        while (!(element instanceof ConnectableElement)) {
            element = element.eContainer();
        }
        return (ConnectableElement) element;
    }

    /**
     * Applies the specifier {@link ElementProcessor} to all instance of eClass
     * in specified model and children
     *
     * @param model
     * @param eClass
     * @param elementProcessor
     */
    @SuppressWarnings("unchecked")
    public static <T extends EObject> void applyTo(final EObject model, final EClass eClass,
            final ElementProcessor<T> elementProcessor) {
        if (eClass.isSuperTypeOf(model.eClass())) {
            elementProcessor.apply((T) model);
        }
        for (final EObject content : model.eContents()) {
            applyTo(content, eClass, elementProcessor);
        }
    }

    private static void addAllDataWithType(final DataAware element, final List<Data> datas, final EClass dataType) {
        if (dataType == null) {
            datas.addAll(element.getData());
        } else {
            for (final Data data : element.getData()) {
                if (dataType.isSuperTypeOf(data.getDataType().eClass())) {
                    datas.add(data);
                }
            }
        }
    }

    public static List<Pool> getChildrenProcess(final MainProcess process) {
        final List<Pool> result = new ArrayList<Pool>();
        for (final Element e : process.getElements()) {
            if (e instanceof Pool) {
                result.add((Pool) e);
            }
        }
        return result;
    }


    public static List<Data> getAccessibleData(final EObject element) {
        return getAccessibleData(element, null);
    }

    public static List<Data> getAccessibleData(EObject element, final EClass dataType) {
        final List<Data> data = new ArrayList<Data>();
        boolean processFound = false;
        while (!processFound && element != null) {
            if (element instanceof SequenceFlow) {
                if (((SequenceFlow) element).getSource() instanceof DataAware) {
                    addAllDataWithType((DataAware) ((SequenceFlow) element).getSource(), data, dataType);
                }
            }
            if (element instanceof DataAware) {
                addAllDataWithType((DataAware) element, data, dataType);
            }
            processFound = element instanceof AbstractProcess && !(element instanceof SubProcessEvent);
            element = element.eContainer();
        }
        return data;
    }

    /**
     * @param proc
     */
    public static void addDataTypes(final MainProcess proc) {
        proc.getDatatypes().add(createBooleanDataType());
        proc.getDatatypes().add(createDateDataType());
        proc.getDatatypes().add(createIntegerDataType());
        proc.getDatatypes().add(createLongDataType());
        proc.getDatatypes().add(createDoubleDataType());
        proc.getDatatypes().add(createStringDataType());
        proc.getDatatypes().add(createJavaDataType());
        proc.getDatatypes().add(createXMLDataType());
        proc.getDatatypes().add(createBusinessObjectType());
    }

    public static BusinessObjectType createBusinessObjectType() {
        final BusinessObjectType businessObjectType = ProcessFactory.eINSTANCE.createBusinessObjectType();
        businessObjectType.setName(NamingUtils.convertToId(DataTypeLabels.businessObjectType));
        return businessObjectType;
    }

    public static XMLType createXMLDataType() {
        final XMLType xmlDataType = ProcessFactory.eINSTANCE.createXMLType();
        xmlDataType.setName(DataTypeLabels.xmlDataType);
        return xmlDataType;
    }

    public static JavaType createJavaDataType() {
        final JavaType javaDataType = ProcessFactory.eINSTANCE.createJavaType();
        javaDataType.setName(NamingUtils.convertToId(DataTypeLabels.javaDataType, javaDataType));
        return javaDataType;
    }

    public static StringType createStringDataType() {
        final StringType stringDataType = ProcessFactory.eINSTANCE.createStringType();
        stringDataType.setName(DataTypeLabels.stringDataType);
        return stringDataType;
    }

    public static DoubleType createDoubleDataType() {
        final DoubleType doubleDataType = ProcessFactory.eINSTANCE.createDoubleType();
        doubleDataType.setName(DataTypeLabels.doubleDataType);
        return doubleDataType;
    }

    public static LongType createLongDataType() {
        final LongType longDataType = ProcessFactory.eINSTANCE.createLongType();
        longDataType.setName(DataTypeLabels.longDataType);
        return longDataType;
    }

    public static IntegerType createIntegerDataType() {
        final IntegerType intDataType = ProcessFactory.eINSTANCE.createIntegerType();
        intDataType.setName(DataTypeLabels.integerDataType);
        return intDataType;
    }

    public static DateType createDateDataType() {
        final DateType dateDataType = ProcessFactory.eINSTANCE.createDateType();
        dateDataType.setName(DataTypeLabels.dateDataType);
        return dateDataType;
    }

    public static BooleanType createBooleanDataType() {
        final BooleanType boolDataType = ProcessFactory.eINSTANCE.createBooleanType();
        boolDataType.setName(DataTypeLabels.booleanDataType);
        return boolDataType;
    }

    public static DataType getDataTypeForID(final EObject elem, final String name) {
        final MainProcess proc = getMainProcess(elem);
        if (proc != null) {
            for (final DataType type : proc.getDatatypes()) {
                if (type.getName().equals(NamingUtils.convertToId(name))) {
                    return type;
                }
            }
        }
        return null;
    }


    public static List<Data> getMessageSourceAccessibleData(final MessageFlow messageFlow) {
        final List<Data> datas = new ArrayList<Data>();

        final FlowElement source = messageFlow.getSource();
        if (source instanceof DataAware) {
            datas.addAll(((DataAware) source).getData());
        }

        datas.addAll(ModelHelper.getParentProcess(source).getData());

        return datas;
    }

    public static Message findEvent(final Element element, final String eventName) {
        final List<Message> events = new ArrayList<Message>();
        findAllEvents(ModelHelper.getMainProcess(element), events);
        // first names, then labels
        for (final Message event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    public static void findAllEvents(final Element element, final List<Message> events) {

        if (element instanceof ThrowMessageEvent) {
            for (final Message ev : ((ThrowMessageEvent) element).getEvents()) {
                events.add(ev);
            }
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllEvents(e, events);
            }

        }
    }

    public static List<AbstractCatchMessageEvent> getAllCatchEvent(final AbstractProcess proc) {
        return getAllItemsOfType(proc, ProcessPackage.eINSTANCE.getAbstractCatchMessageEvent());
    }

    public static void findAllCatchEvents(final Element element, final List<AbstractCatchMessageEvent> events) {

        if (element instanceof AbstractCatchMessageEvent) {
            events.add((AbstractCatchMessageEvent) element);
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllCatchEvents(e, events);
            }

        }
    }

    public static void findAllElements(final Element element, final List<Element> elements, final List<EClass> types) {

        for (final EClass eClass : types) {
            if (eClass.isSuperTypeOf(element.eClass())) {
                elements.add(element);
                break;
            }
        }

        if (element instanceof DataAware) {
            for (final Data d : ((DataAware) element).getData()) {
                findAllElements(d, elements, types);
            }
        }
        // boundary
        if (element instanceof Activity) {
            for (final BoundaryEvent b : ((Activity) element).getBoundaryIntermediateEvents()) {
                findAllElements(b, elements, types);
            }
        }

        if (element instanceof AbstractProcess) {
            for (final Connection c : ((AbstractProcess) element).getConnections()) {
                findAllElements(c, elements, types);
            }
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllElements(e, elements, types);
            }
        }
    }

    public static AbstractProcess findProcess(final Element element, final String procName) {
        return findOldProcesses(element, procName);
    }

    protected static AbstractProcess findOldProcesses(final Element element,
            final String procName) {
        final MainProcess proc = getMainProcess(element);
        final List<AbstractProcess> processes = getAllProcesses(proc);
        for (final AbstractProcess p : processes) {
            if (procName.equals(p.getName())) {
                return p;
            }
        }

        return null;
    }

    public static List<EObject> getAllConnectors(final MainProcess process) {
        final List<EObject> connectors = new ArrayList<EObject>();
        findAllConnectors(process, connectors);
        return connectors;
    }

    public static void findAllConnectors(final Element element, final List<EObject> connectors) {

        if (element instanceof ConnectableElement) {
            for (final Connector c : ((ConnectableElement) element).getConnectors()) {
                connectors.add(c);
            }
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllConnectors(e, connectors);
            }

        }

    }

    public static String getDataTypeNLLabel(final String name) {

        if (STRING_DATA_TYPE.equals(name)) {
            return Messages.StringType;
        }
        if (INTEGER_DATA_TYPE.equals(name)) {
            return Messages.IntegerType;
        }
        if (FLOAT_DATA_TYPE.equals(name)) {
            return Messages.FloatType;
        }
        if (BOOLEAN_DATA_TYPE.equals(name)) {
            return Messages.BooleanType;
        }
        if (DATE_DATA_TYPE.equals(name)) {
            return Messages.DataType;
        }
        if (JAVA_DATA_TYPE.equals(name)) {
            return Messages.JavaType;
        }

        return name;
    }

    public static String getDataTypeID(final String label) {

        if (label.equals(Messages.StringType)) {
            return STRING_DATA_TYPE;
        }
        if (label.equals(Messages.IntegerType)) {
            return INTEGER_DATA_TYPE;
        }
        if (label.equals(Messages.FloatType)) {
            return FLOAT_DATA_TYPE;
        }
        if (label.equals(Messages.BooleanType)) {
            return BOOLEAN_DATA_TYPE;
        }
        if (label.equals(Messages.DataType)) {
            return DATE_DATA_TYPE;
        }
        if (label.equals(Messages.JavaType)) {
            return JAVA_DATA_TYPE;
        }

        return label;
    }

    public static AbstractProcess findProcess(final List<AbstractProcess> allProcesses, final String subDefName) {
        for (final AbstractProcess p : allProcesses) {
            if (subDefName.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    /**
     * @param subProcessName
     */
    public static AbstractProcess findProcess(final String subProcessName, final String version,
            final List<? extends Element> elements) {
        if (version == null || version.trim().isEmpty()) {// it's the latest version
            final List<AbstractProcess> processes = new ArrayList<>();
            findProcessRecursivly(subProcessName, version, elements, processes);
            if (!processes.isEmpty()) {
                Collections.sort(processes, new ProcessVersionComparator());
                return processes.get(processes.size() - 1);
            }
        } else {
            for (final Element item : elements) {
                if (item instanceof AbstractProcess) {
                    final AbstractProcess process = (AbstractProcess) item;
                    if (process.getName().equals(subProcessName) && version.equals(process.getVersion())) {
                        return process;
                    } else {
                        final AbstractProcess res = findProcess(subProcessName, version, process.getElements());
                        if (res != null) {
                            return res;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param subProcessName
     * @param version
     *        : this is not use by now
     * @param elements
     * @param processes
     */
    private static void findProcessRecursivly(final String subProcessName, final String version,
            final List<? extends Element> elements,
            final List<AbstractProcess> processes) {
        // TODO : use the version argument
        for (final Element item : elements) {
            if (item instanceof AbstractProcess) {
                final AbstractProcess process = (AbstractProcess) item;
                if (process.getName().equals(subProcessName)) {
                    processes.add(process);
                } else {
                    findProcessRecursivly(subProcessName, version, process.getElements(), processes);
                }
            }
        }
    }

    public static boolean isReferencedElementIsInExpression(final EObject target) {
        EObject container = target.eContainer();
        while (container != null) {
            if (container instanceof Expression) {
                return true;
            }
            container = container.eContainer();
        }
        return false;
    }


    /**
     * @param modelProcess2
     * @return
     */
    public static List<FlowElement> getFlowElements(final Container container, final boolean includeSubContainers) {
        final ArrayList<FlowElement> flowElements = new ArrayList<FlowElement>();
        for (final Element el : container.getElements()) {
            if (el instanceof FlowElement) {
                flowElements.add((FlowElement) el);
            }
            if (includeSubContainers && el instanceof Container) {
                flowElements.addAll(getFlowElements((Container) el, includeSubContainers));
            }
        }
        return flowElements;
    }

    /**
     * @param name
     * @return
     */
    public static Element findElement(final Container container, final String name, final boolean includeSubContainers) {
        for (final Element el : container.getElements()) {
            if (el.getName().equals(name)) {
                return el;
            }
            if (includeSubContainers && el instanceof Container) {
                final Element res = findElement((Container) el, name, includeSubContainers);
                if (res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    /**
     * @param form
     * @return the diagram corresponding to the form.
     */
    public static Diagram getDiagramFor(final EObject element, final Resource resource) {
        if (element == null) {
            return null;
        }
        if (!resource.isLoaded()) {
            throw new IllegalStateException("EMF Resource is not loaded.");
        }

        final RunnableWithResult<Diagram> runnableWithResult = new DiagramForElementRunnable(resource, element);
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(resource);
        if (editingDomain != null) {
            try {
                editingDomain.runExclusive(runnableWithResult);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        } else {
            runnableWithResult.run();
        }
        return runnableWithResult.getResult();
    }

    public static Diagram getDiagramFor(final EObject element) {
        if (element != null && element.eResource() != null) {
            return getDiagramFor(element, TransactionUtil.getEditingDomain(element.eResource()));
        }
        return null;
    }

    public static Diagram getDiagramFor(final EObject element, EditingDomain domain) {
        if (element == null) {
            return null;
        }
        Resource resource = element.eResource();
        if (resource == null) {
            if (domain == null) {
                domain = TransactionUtil.getEditingDomain(element);
                if (domain != null) {
                    resource = domain.getResourceSet().getResource(element.eResource().getURI(), true);
                }
            } else if (domain.getResourceSet() != null) {
                resource = domain.getResourceSet().getResource(element.eResource().getURI(), true);
            }
        }
        if (resource == null) {
            throw new IllegalStateException(String.format("No resource attached to EObject %s", element));
        }
        return getDiagramFor(element, resource);
    }


    public static String getEObjectID(final EObject eObject) {
        if (eObject == null) {
            return null;
        }
        final Resource eResource = eObject.eResource();
        if (eResource != null) {
            return eResource.getURIFragment(eObject);
        }
        return null;
    }



    public static void findAllThrowLinks(final Element root, final List<ThrowLinkEvent> events) {
        if (root instanceof ThrowLinkEvent) {
            events.add((ThrowLinkEvent) root);
        }

        if (root instanceof Container) {
            for (final Element e : ((Container) root).getElements()) {
                findAllThrowLinks(e, events);
            }

        }

    }

    public static void findAllCatchLinks(final Element root, final List<CatchLinkEvent> events) {
        if (root instanceof CatchLinkEvent) {
            events.add((CatchLinkEvent) root);
        }

        if (root instanceof Container) {
            for (final Element e : ((Container) root).getElements()) {
                findAllCatchLinks(e, events);
            }

        }

    }

    public static List<EnumType> getAllUserDatatype(final MainProcess process) {
        final List<EnumType> result = new ArrayList<EnumType>();
        for (final DataType d : process.getDatatypes()) {
            if (d instanceof EnumType) {
                result.add((EnumType) d);
            }
        }
        return result;
    }

    /**
     * remove all element that are referenced outside the object and remove
     * element that are missing required fields
     *
     * @param widget
     */
    public static void removedReferencedEObjects(final EObject eObject, final EObject targetContainer) {

        final Set<EObject> containedEObjects = new HashSet<EObject>();
        // get all contained EObjects
        getContainedObjectRecusivly(containedEObjects, eObject);
        containedEObjects.add(eObject);
        for (final EObject toCheck : containedEObjects) {
            for (final EReference reference : toCheck.eClass().getEAllReferences()) {
                final Object o = toCheck.eGet(reference);
                if (o instanceof EObject) {
                    final EObject child = (EObject) o;
                    // keep enum reference from the same diagram
                    if (child instanceof Data && ((Data) child).getDataType() instanceof EnumType) {
                        final MainProcess mainProcess = ModelHelper.getMainProcess(targetContainer);
                        final MainProcess childMainProcess = ModelHelper.getMainProcess(child);
                        if (mainProcess != null
                                && childMainProcess != null
                                && mainProcess.equals(childMainProcess)) {
                            break;
                        }
                    }
                    if (!containedEObjects.contains(child)) {
                        boolean removeReference = true;
                        if (child instanceof DataType) { // retrieve the equivalent Data Type in the target MainProcess
                            final MainProcess mainProcess = ModelHelper.getMainProcess(targetContainer);
                            final MainProcess childMainProcess = ModelHelper.getMainProcess(child);
                            if (mainProcess != null
                                    && childMainProcess != null
                                    && mainProcess.equals(childMainProcess)) {
                                removeReference = false;
                            } else {
                                for (final DataType dt : mainProcess.getDatatypes()) {
                                    if (dt.eClass().equals(child.eClass())) {
                                        if (dt.eClass().equals(ProcessPackage.Literals.ENUM_TYPE)) {
                                            if (((EnumType) child).getName().equals(dt.getName())) {
                                                toCheck.eSet(reference, dt);
                                                removeReference = false;
                                                break;
                                            }
                                        } else {
                                            toCheck.eSet(reference, dt);
                                            removeReference = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (removeReference) {
                            // referenced outside: we unset it
                            toCheck.eUnset(reference);
                            // must not be the main eobject
                            if (reference.isRequired() && !toCheck.equals(eObject)) {
                                // field is required: we remove it
                                EcoreUtil.remove(toCheck);
                                break;
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     * remove all element that are referenced outside the object and remove
     * element that are missing required fields
     *
     * @param widget
     */
    public static void removedInvalidReferencedEObjects(final EObject eObject) {

        final Set<EObject> containedEObjects = new HashSet<EObject>();
        // get all contained EObjects
        getContainedObjectRecusivly(containedEObjects, eObject);
        containedEObjects.add(eObject);
        for (final EObject toCheck : containedEObjects) {
            for (final EReference reference : toCheck.eClass().getEAllReferences()) {
                final Object o = toCheck.eGet(reference);

                if (o instanceof EObject) {
                    final EObject child = (EObject) o;
                    if (child.eResource() != null && !child.eResource().equals(eObject.eResource())) {
                        // referenced outside: we unset it
                        toCheck.eUnset(reference);

                    }
                }
            }
        }
    }

    private static void getContainedObjectRecusivly(final Set<EObject> objects, final EObject eObject) {

        for (final EReference reference : eObject.eClass().getEAllContainments()) {

            final Object o = eObject.eGet(reference);
            if (o instanceof EObject) {
                final EObject child = (EObject) o;
                if (child != null) {
                    objects.add(child);
                    getContainedObjectRecusivly(objects, child);
                }
            } else if (o instanceof List<?>) {
                for (final Object childO : (List<?>) o) {
                    if (childO instanceof EObject) {
                        objects.add((EObject) childO);
                        getContainedObjectRecusivly(objects, (EObject) childO);
                    }
                }
            }

        }
    }

    public static void findAllElementsByNature(final Element element, final List<Element> foundElement,
            final List<EClass> supertypes) {

        for (final EClass eClass : supertypes) {
            if (eClass.isSuperTypeOf(element.eClass())) {
                if (!foundElement.contains(element)) {
                    foundElement.add(element);
                }
            }
        }

        if (element instanceof DataAware) {
            for (final Data d : ((DataAware) element).getData()) {
                findAllElementsByNature(d, foundElement, supertypes);
            }
        }

        if (element instanceof AbstractProcess) {
            for (final Connection c : ((AbstractProcess) element).getConnections()) {
                findAllElementsByNature(c, foundElement, supertypes);
            }
        }

        if (element instanceof Container) {
            for (final Element e : ((Container) element).getElements()) {
                findAllElementsByNature(e, foundElement, supertypes);
            }
        }

    }

    public static <T extends EObject> List<T> getAllItemsOfType(final EObject container, final EClass eClass) {
        final List<T> res = new ArrayList<T>();
        addAllElementOfContainer(container, res, eClass);
        return res;
    }

    private static <T extends EObject> void addAllElementOfContainer(final EObject container, final List<T> res,
            final EClass eClass) {
        if (container != null) {
            if (eClass.isSuperTypeOf(container.eClass())) {
                res.add((T) container);
            }
            for (final EObject child : container.eContents()) {
                addAllElementOfContainer(child, res, eClass);
            }
        }
    }

    public static Set<String> getExistingConnectorsName(final Element eObject) {
        final Set<String> result = new HashSet<String>();
        final MainProcess mainProc = ModelHelper.getMainProcess(eObject);
        final List<Connector> connectors = getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);

        for (final Connector c : connectors) {
            if (c.getName().trim().length() > 0) {
                result.add(c.getName());
            }
        }

        return result;
    }

    public static boolean isInEvenementialSubProcessPool(final EObject semanticElement) {
        EObject tempPart = semanticElement;
        while (tempPart != null && !(tempPart instanceof SubProcessEvent)) {
            tempPart = tempPart.eContainer();
        }
        return tempPart instanceof SubProcessEvent;
    }

    public static List<AbstractCatchMessageEvent> findAllCatchEventsCatching(final MainProcess mainProcess,
            final String eventName) {
        final List<AbstractCatchMessageEvent> result = new ArrayList<AbstractCatchMessageEvent>();
        for (final AbstractCatchMessageEvent ev : getAllCatchEvent(mainProcess)) {
            if (ev.getEvent() != null && ev.getEvent().equals(eventName)) {
                result.add(ev);
            }
        }
        return result;
    }

    public static SubProcessEvent findSubprocessEvent(final AbstractProcess process, final String name) {
        for (final EObject e : ModelHelper.getAllItemsOfType(process, ProcessPackage.eINSTANCE.getSubProcessEvent())) {
            if (((Element) e).getName().equals(name)) {
                return (SubProcessEvent) e;
            }
        }
        return null;
    }

    /**
     * As we did a really bad architecture, we need to differentiate concrete Activity which are Abstract Task
     *
     * @param model
     * @return
     */
    public static boolean isActivityButNotAbstractTask(final Activity model) {
        return model instanceof ReceiveTask
                || model instanceof ScriptTask
                || model instanceof SendTask
                || model instanceof ServiceTask
                || model instanceof CallActivity
                || model instanceof Task;
    }

    public static List<Data> getAccessibleData(final EObject element, final boolean includeTransientData) {
        final List<Data> result = new ArrayList<Data>();
        if (includeTransientData) {
            for (final Data d : ModelHelper.getAccessibleData(element)) {
                if (!result.contains(d)) {
                    result.add(d);
                }
            }
            return result;
        } else {
            final List<Data> data = new ArrayList<Data>();
            for (final Data d : ModelHelper.getAccessibleData(element)) {
                if (!d.isTransient()) {
                    data.add(d);
                }
            }
            return data;
        }
    }

    public static ConnectableElement getParentConnectableElement(final Element element) {
        if (element != null) {
            EObject result = element;
            while (result != null && !(result instanceof ConnectableElement)) {
                result = result.eContainer();
            }
            if (result != null) {
                return (ConnectableElement) result;
            }
        }
        return null;
    }

    public static List<AbstractProcess> findAllProcesses(
            final List<AbstractProcess> allProcesses, final String subDefName) {
        final List<AbstractProcess> processes = new ArrayList<AbstractProcess>();
        for (final AbstractProcess p : allProcesses) {
            if (subDefName.equals(p.getName())) {
                processes.add(p);
            }
        }
        return processes;
    }


    public static Document getDocumentReferencedInExpression(final Expression expr) {
        final List<EObject> refs = expr.getReferencedElements();
        for (final EObject ref : refs) {
            if (ref instanceof Document && ((Document) ref).getName().equals(expr.getContent())) {
                return (Document) ref;
            }
        }
        return null;
    }

    public static Lane getParentLane(final EObject element) {
        EObject lane = element.eContainer();
        while (lane != null && !(lane instanceof Lane)) {
            lane = lane.eContainer();
        }
        return (Lane) lane;
    }

    public static FlowElement getParentFlowElement(final EObject eObject) {
        EObject flowElement = eObject;
        while (flowElement != null && !(flowElement instanceof FlowElement)) {
            flowElement = flowElement.eContainer();
        }
        return flowElement != null ? (FlowElement) flowElement : null;
    }

    public static boolean isAnExpressionCopy(final Expression expression) {
        EObject current = expression;
        EObject container = current.eContainer();
        while (container != null) {
            if (current.eContainingFeature().equals(ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS)) {
                return true;
            }
            current = container;
            container = current.eContainer();
        }
        return false;
    }

    public static Element getParentElement(final EObject object) {
        EObject element = object;
        while (element != null && !(element instanceof Element)) {
            element = element.eContainer();
        }
        return (Element) element;
    }

    public static boolean isObjectIsReferencedInExpression(final Expression expr, final Object elementToDisplay) {
        for (final EObject referencedElement : expr.getReferencedElements()) {
            if (referencedElement instanceof Parameter && elementToDisplay instanceof Parameter
                    && ((Parameter) referencedElement).getName().equals(((Parameter) elementToDisplay).getName())) {
                return true;
            }

            if (referencedElement instanceof SearchIndex && elementToDisplay instanceof SearchIndex
                    && ((SearchIndex) referencedElement).getName().getName()
                            .equals(((SearchIndex) elementToDisplay).getName().getName())) {
                return true;
            }

            if (referencedElement instanceof Element && elementToDisplay instanceof Element
                    && isSameElement((Element) elementToDisplay, referencedElement)) {
                return true;
            }
        }
        return false;
    }

    protected static Activity getReferencedDataActivityContainer(final Data refData) {
        EObject container = refData.eContainer();
        while (container != null && !(container instanceof Activity)) {
            container = container.eContainer();
        }
        if (container != null) {
            if (getDataOnActivity(refData, container) != null) {
                return (Activity) container;
            }
        }
        return null;
    }

    protected static Pool getReferencedDataPoolContainer(final Data refData) {
        EObject container = refData.eContainer();
        while (container != null && !(container instanceof Pool)) {
            container = container.eContainer();
        }
        if (container != null) {
            if (getDataOnPool(refData, container) != null) {
                return (Pool) container;
            }
        }
        return null;
    }

    /**
     * @param refData
     * @param container
     */
    protected static Data getDataOnActivity(final Data refData, final EObject container) {
        final List<Data> datas = ((Activity) container).getData();
        for (final Data data : datas) {
            if (data.getName().equals(refData.getName())) {
                return data;
            }
        }
        return null;
    }

    protected static Data getDataOnPool(final Data refData, final EObject container) {
        final List<Data> datas = ((Pool) container).getData();
        for (final Data data : datas) {
            if (data.getName().equals(refData.getName())) {
                return data;
            }
        }
        return null;
    }

    protected static boolean isSameContainer(final EObject referencedElement, final EObject element) {
        if (referencedElement instanceof Data) {
            final Activity stepContainer = getReferencedDataActivityContainer((Data) referencedElement);
            if (stepContainer != null) {
                return stepContainer.equals(element.eContainer());
            }
            final Pool poolContainer = getReferencedDataPoolContainer((Data) referencedElement);
            if (poolContainer != null) {
                return poolContainer.equals(element.eContainer());
            }
            return false;
        }
        return true;
    }

    /**
     * @param elementToDisplay
     * @param referencedElement
     * @return
     */
    public static boolean isSameElement(final EObject elementToDisplay, final EObject referencedElement) {
        if (elementToDisplay.eContainer() != null) {
            return ((Element) referencedElement).getName().equals(((Element) elementToDisplay).getName())
                    && isSameContainer(referencedElement, elementToDisplay);
        } else {
            return EcoreUtil.equals(elementToDisplay, referencedElement);
        }
    }

    public static Pool getParentPool(final EObject semanticElement) {
        EObject process = semanticElement;
        while (process != null && !(process instanceof Pool)) {
            if (process.eContainer() != null) {
                process = process.eContainer();
            } else {
                break;
            }
        }
        if (process instanceof Pool) {
            return (Pool) process;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends EObject> T getFirstContainerOfType(final EObject element, final Class<T> type) {
        EObject current = element;
        while (current != null && !type.isAssignableFrom(current.getClass())) {
            current = current.eContainer();
        }
        return (T) current;
    }

    public static <T extends EObject> List<T> getAllElementOfTypeIn(final EObject container, final Class<T> type) {
        final List<T> res = new ArrayList<>();
        addAllItemsOfContainer(container, res, type);
        return res;
    }

    private static <T extends EObject> void addAllItemsOfContainer(final EObject container, final List<T> res,
            final Class<T> type) {
        if (container != null) {
            if (type.isAssignableFrom(container.getClass())) {
                res.add((T) container);
            }
            for (final EObject child : container.eContents()) {
                addAllItemsOfContainer(child, res, type);
            }
        }

    }

}
