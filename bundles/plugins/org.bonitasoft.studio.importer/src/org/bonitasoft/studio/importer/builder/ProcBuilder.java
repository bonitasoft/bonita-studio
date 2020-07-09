/**
 * Copyright (C) 2011-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.editingdomain.CustomDiagramEditingDomainFactory;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.jface.databinding.validator.URLEncodableInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.ErrorEvent;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.model.process.SignalEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.model.process.decision.DecisionFactory;
import org.bonitasoft.studio.model.process.decision.DecisionTable;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class ProcBuilder implements IProcBuilder {

    private TransactionalEditingDomain editingDomain;
    private final IProgressMonitor monitor;
    private final Map<String, Resource> diagramResources;
    private MainProcessEditPart diagramPart;
    private Diagram diagram;
    private EObject currentContainer;
    private EObject currentStep;
    private EObject currentAssignable;
    private CompoundCommand commandStack;
    private final Map<String, Data> dataByName;
    private final Map<String, ShapeNodeEditPart> editParts;
    private final Map<String, Actor> participants;
    private org.bonitasoft.studio.model.process.Connector currentConnector;
    private EObject currentElement;
    private final Map<String, EnumType> datatypes;
    private final Map<ThrowLinkEvent, String> throwLinkEvents;
    private final Map<String, CatchLinkEvent> catchLinkEvents;
    private final Map<String, EObject> steps;
    private final Map<AbstractProcess, String> processIds;
    private final List<MessageFlowData> messageFlows;
    private final List<Pair<String, String>> createdSequenceFlows;
    private final Map<String, AbstractProcess> processes;
    private final Map<String, Lane> lanes;
    private Shell shell;
    private final Map<Element, String> elementToReplaceName;
    private View currentView;

    public ProcBuilder() {
        this(new NullProgressMonitor());
    }

    public ProcBuilder(final IProgressMonitor progressMonitor) {
        monitor = progressMonitor;
        diagramResources = new HashMap<String, Resource>();
        commandStack = new CompoundCommand();
        dataByName = new HashMap<String, Data>();
        datatypes = new HashMap<String, EnumType>();
        participants = new HashMap<String, Actor>();
        editParts = new HashMap<String, ShapeNodeEditPart>();
        throwLinkEvents = new HashMap<ThrowLinkEvent, String>();
        catchLinkEvents = new HashMap<String, CatchLinkEvent>();
        steps = new HashMap<String, EObject>();
        processIds = new HashMap<AbstractProcess, String>();
        messageFlows = new ArrayList<MessageFlowData>();
        createdSequenceFlows = new ArrayList<Pair<String, String>>();
        processes = new HashMap<String, AbstractProcess>();
        lanes = new HashMap<String, Lane>();
        elementToReplaceName = new HashMap<Element, String>();
    }

    @Override
    public void createDiagram(final String id, final String name, final String version, final File targetFile) throws ProcBuilderException {
        editingDomain = createEditingDomain();
        final Resource diagramResource = editingDomain.getResourceSet().createResource(URI.createFileURI(targetFile.getAbsolutePath()));
        diagramResources.put(id, diagramResource);
        final AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain, "Create Diagram Task", Collections.EMPTY_LIST) {

            @Override
            protected CommandResult doExecuteWithResult(
                    final IProgressMonitor monitor, final IAdaptable info)
                    throws ExecutionException {
                final MainProcess diagramModel = ProcessFactory.eINSTANCE.createMainProcess();
                diagram = ViewService.createDiagram(diagramModel, MainProcessEditPart.MODEL_ID, ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                diagramResource.getContents().add(diagramModel);

                if (diagram != null) {
                    diagramResource.getContents().add(diagram);
                    diagram.setName(id);
                    if (!new URLEncodableInputValidator("").validate(name).isOK()) {
                        diagramModel.setName(NamingUtils.convertToId(name));
                    } else {
                        diagramModel.setName(name);
                    }
                    diagramModel.setVersion(version);
                    diagramModel.setBonitaModelVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
                    diagramModel.setBonitaVersion(ProductVersion.CURRENT_VERSION);
                    ModelHelper.addDataTypes(diagramModel);
                    diagram.setElement(diagramModel);
                }

                return CommandResult.newOKCommandResult();
            }
        };

        try {
            OperationHistoryFactory.getOperationHistory().execute(command, monitor, null);
        } catch (final ExecutionException e) {
            ProcessDiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e); //$NON-NLS-1$
        }
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                shell = new Shell();
                try {
                    diagramPart = (MainProcessEditPart) OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell);
                } catch (final Exception ex) {
                    diagramPart = (MainProcessEditPart) OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell);
                } finally {
                    //from 3.7, no more cal dispose on the shell here because it disposes some controls that we need after.
                }
            }
        });

        setCharset(WorkspaceSynchronizer.getFile(diagramResource));

    }

    public TransactionalEditingDomain createEditingDomain() {
        return CustomDiagramEditingDomainFactory.getInstance().createEditingDomain();
    }

    @Override
    public void addPool(String id, final String name, final String version, final Point location, final Dimension size) throws ProcBuilderException {

        if (diagramPart == null) {
            throw new ProcBuilderException("Impossible to add Pool outside a diagram");
        }

        id = NamingUtils.convertToId(id);
        final ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(new CreateElementRequestAdapter(new CreateElementRequest(
                ProcessElementTypes.Pool_2007)), Node.class,
                ((IHintedType) ProcessElementTypes.Pool_2007).getSemanticHint(), diagramPart.getDiagramPreferencesHint());
        final CreateViewAndElementRequest createRequest = createCreationRequest(
                location, size, viewDescriptor);
        diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(diagramPart.getCommand(createRequest));

        final Node newNode = (Node) viewDescriptor.getAdapter(Node.class);
        final Pool pool = (Pool) newNode.getElement();
        commandStack.append(SetCommand.create(editingDomain, pool, ProcessPackage.eINSTANCE.getElement_Name(), name));
        commandStack.append(SetCommand.create(editingDomain, pool, ProcessPackage.eINSTANCE.getAbstractProcess_Version(), version));

        if (size != null) {
            commandStack.append(SetCommand.create(editingDomain, newNode.getLayoutConstraint(), NotationPackage.eINSTANCE.getSize_Width(), size.width));
            commandStack.append(SetCommand.create(editingDomain, newNode.getLayoutConstraint(), NotationPackage.eINSTANCE.getSize_Height(), size.height));
        }
        commandStack.append(SetCommand.create(editingDomain, newNode.getStyle(NotationPackage.eINSTANCE.getLineStyle()),
                NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.colorToInteger(ColorConstants.lightGray)));

        processIds.put(pool, id);
        processes.put(id, pool);
        currentContainer = pool;
        currentStep = pool;
        currentElement = pool;
        currentView = newNode;
        execute();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addLane(java.lang.String, java.lang.String, org.eclipse.draw2d.geometry.Dimension)
     */
    @Override
    public void addLane(String id, final String name, final Dimension size) throws ProcBuilderException {
        id = NamingUtils.convertToId(id);

        if (currentContainer instanceof Lane) {
            currentContainer = currentContainer.eContainer();
        }

        final EReference containmentFeature = ProcessPackage.Literals.CONTAINER__ELEMENTS;
        final ViewAndElementDescriptor descriptor = new ViewAndElementDescriptor(new CreateElementRequestAdapter(
                new CreateElementRequest(
                        editingDomain,
                        currentContainer,
                        ProcessElementTypes.Lane_3007,
                        containmentFeature)
                ),
                Node.class,
                ((IHintedType) ProcessElementTypes.Lane_3007).getSemanticHint(),
                diagramPart.getDiagramPreferencesHint());
        final CreateViewAndElementRequest request = new CreateViewAndElementRequest(descriptor);

        if (size != null) {
            request.setSize(size);
        }

        diagramPart.refresh();
        final IGraphicalEditPart parentEditPart = GMFTools.findEditPart(diagramPart, currentContainer);

        final IGraphicalEditPart compartment = retrieveCompartmentEditPartFor(parentEditPart);

        diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(compartment.getCommand(request));
        compartment.refresh();

        final Node newNode = (Node) descriptor.getAdapter(Node.class);
        final Lane lane = (Lane) newNode.getElement();
        commandStack.append(SetCommand.create(editingDomain, lane, ProcessPackage.eINSTANCE.getElement_Name(), name));

        if (size != null) {
            commandStack.append(SetCommand.create(editingDomain, newNode.getLayoutConstraint(), NotationPackage.eINSTANCE.getSize_Width(), size.width));
            commandStack.append(SetCommand.create(editingDomain, newNode.getLayoutConstraint(), NotationPackage.eINSTANCE.getSize_Height(), size.height));
        }

        commandStack.append(SetCommand.create(editingDomain, newNode.getStyle(NotationPackage.eINSTANCE.getLineStyle()),
                NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.colorToInteger(ColorConstants.lightGray)));

        lanes.put(id, lane);
        currentContainer = lane;
        currentStep = lane;
        currentElement = lane;
        currentAssignable = lane;
        currentView = newNode;

        execute();

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addData(java.lang.String, java.lang.String, java.lang.String,
     * org.bonitasoft.studio.importer.builder.IProcBuilder.DataType)
     */
    @Override
    @Deprecated
    public void addData(final String id, final String name, final String defaultValue, final boolean isMultiple, final DataType datatype)
            throws ProcBuilderException {
        addData(id, name, defaultValue, "", "", isMultiple, false, datatype, ExpressionConstants.SCRIPT_TYPE);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addData(java.lang.String, java.lang.String, java.lang.String,
     * org.bonitasoft.studio.importer.builder.IProcBuilder.DataType)
     */
    @Override
    public void addData(final String id, final String name, final String defaultValueContent, final String defaultValueReturnType,
            final String defaultValueInterpreter, final boolean isMultiple, final boolean isTransient, final DataType datatype, final String expressionType)
            throws ProcBuilderException {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName(name != null ? name : id);
        createAndAddData(name, defaultValueContent, defaultValueReturnType, defaultValueInterpreter, isMultiple, isTransient, data, datatype, expressionType);
    }

    @Override
    public void addXMLData(final String id, final String name, final String defaultValueContent, final String defaultValueReturnType,
            final String defaultValueInterpreter, final String type, final String nameSpace, final boolean isMultiple, final boolean isTransient,
            final String expressionType) throws ProcBuilderException {
        final XMLData data = ProcessFactory.eINSTANCE.createXMLData();
        data.setName(name != null ? name : id);
        data.setType(type);
        data.setNamespace(nameSpace);
        createAndAddData(name, defaultValueContent, defaultValueReturnType, defaultValueInterpreter, isMultiple, isTransient, data, DataType.XML,
                expressionType);
    }

    @Override
    public void addJavaData(final String id, final String name, final String defaultValueContent, final String defaultValueReturnType,
            final String defaultValueInterpreter, final String javaQualifiedName, final boolean isMultiple, final boolean isTransient,
            final String expressiontype) throws ProcBuilderException {
        final JavaObjectData data = ProcessFactory.eINSTANCE.createJavaObjectData();
        data.setName(name != null ? name : id);
        data.setClassName(javaQualifiedName);
        createAndAddData(name, defaultValueContent, defaultValueReturnType, defaultValueInterpreter, isMultiple, isTransient, data, DataType.JAVA,
                expressiontype);
    }

    protected void createAndAddData(final String name,
            final String defaultValueContent,
            final String defaultValueReturnType,
            final String defaultValueInterpreter,
            final boolean isMultiple,
            final boolean isTransient,
            final Data data,
            final DataType dataType,
            final String expressionType)
            throws ProcBuilderException {
        data.setDataType(toProcDataType(currentContainer, dataType));
        data.setMultiple(isMultiple);
        data.setTransient(isTransient);
        if (defaultValueContent != null) {
            data.setDefaultValue(createExpression(defaultValueContent, defaultValueReturnType, defaultValueInterpreter, expressionType));
        }

        if (currentStep instanceof DataAware) {
            commandStack.append(AddCommand.create(editingDomain, currentStep, ProcessPackage.eINSTANCE.getDataAware_Data(), data));
        } else if (currentContainer instanceof DataAware) {
            commandStack.append(AddCommand.create(editingDomain, currentContainer, ProcessPackage.eINSTANCE.getDataAware_Data(), data));
        } else {
            throw new ProcBuilderException("Impossible to add Data to the current container : " + currentContainer != null
                    ? ((Element) currentContainer).getName() : "null");
        }

        dataByName.put(name, data);

        currentElement = data;
        execute();
    }

    @Override
    public void addEnumType(final String id, final String name, final Set<String> literals) throws ProcBuilderException {
        if (datatypes.get(id) == null) {
            final EnumType enumType = ProcessFactory.eINSTANCE.createEnumType();
            enumType.setName(id);
            enumType.getLiterals().addAll(literals);

            datatypes.put(id, enumType);

            final MainProcess mainProc = ModelHelper.getMainProcess(currentContainer);
            if (mainProc == null) {
                throw new ProcBuilderException("Impossible to find the root element");
            }
            if (ModelHelper.getDataTypeForID(mainProc, id) == null) {
                commandStack.append(AddCommand.create(editingDomain, mainProc, ProcessPackage.eINSTANCE.getAbstractProcess_Datatypes(), enumType));
            }
            execute();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addDescription(java.lang.String)
     */
    @Override
    public void addDescription(final String description) throws ProcBuilderException {
        if (currentElement != null && currentElement instanceof Element) {
            commandStack.append(SetCommand.create(editingDomain, currentElement, ProcessPackage.eINSTANCE.getElement_Documentation(), description));
        } else if (currentStep != null && currentStep instanceof Element) {
            commandStack.append(SetCommand.create(editingDomain, currentStep, ProcessPackage.eINSTANCE.getElement_Documentation(), description));
        } else if (currentContainer != null && currentContainer instanceof Element) {
            commandStack.append(SetCommand.create(editingDomain, currentContainer, ProcessPackage.eINSTANCE.getElement_Documentation(), description));
        } else {
            throw new ProcBuilderException("Impossible to set description on null element");
        }
        execute();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addSequenceFlow(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean,
     * org.eclipse.draw2d.geometry.PointList)
     */
    @Override
    public void addSequenceFlow(final String name, final String sourceId, final String targetId, final boolean isDefault, final Point sourceAnchor,
            final Point targetAnchor, final PointList bendpoints) throws ProcBuilderException {
        final String srcId = NamingUtils.convertToId(sourceId);
        final String trgtId = NamingUtils.convertToId(targetId);
        final ShapeNodeEditPart sourceNode = editParts.get(srcId);
        final ShapeNodeEditPart targetNode = editParts.get(trgtId);

        if (!canSequenceFlowBeCreated(srcId, trgtId, sourceNode, targetNode)) {
            return;
        }

        final CreateConnectionViewAndElementRequest request = new CreateConnectionViewAndElementRequest(ProcessElementTypes.SequenceFlow_4001,
                ((IHintedType) ProcessElementTypes.SequenceFlow_4001).getSemanticHint(), diagramPart
                        .getDiagramPreferencesHint());
        final Command createSequenceFlowCommand = CreateConnectionViewAndElementRequest.getCreateCommand(request, sourceNode, targetNode);
        if(!createSequenceFlowCommand.canExecute()) {
           return;
        }
        createSequenceFlowCommand.execute();
        
        final ConnectionViewAndElementDescriptor newObject = (ConnectionViewAndElementDescriptor) request.getNewObject();
        final Edge edge = (Edge) newObject.getAdapter(Edge.class);
        final SequenceFlow createdElement = (SequenceFlow) newObject.getElementAdapter().getAdapter(EObject.class);
        if (createdElement == null) {
            throw new ProcBuilderException("Impossible to create SequenceFlow " + name);
        }

        if (bendpoints != null && bendpoints.size() > 1) {
            setBendPoints(bendpoints, newObject);
        }

        handleSequenceFlowAnchors(sourceAnchor, targetAnchor, newObject);
        if (name != null) {
            commandStack.append(SetCommand.create(editingDomain, createdElement, ProcessPackage.Literals.ELEMENT__NAME, name));
        }
        commandStack.append(SetCommand.create(editingDomain, createdElement, ProcessPackage.eINSTANCE.getSequenceFlow_IsDefault(), isDefault));
        if (edge != null) {
            commandStack.append(SetCommand.create(editingDomain, edge.getStyle(NotationPackage.eINSTANCE.getLineStyle()),
                    NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.colorToInteger(ColorConstants.lightGray)));
        }

        createdSequenceFlows.add(new Pair<String, String>(srcId, trgtId));
        currentElement = createdElement;
        currentView = edge;
        execute();
    }

    private Node createLabel(final View owner, final String hint) {
        final DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
        rv.setType(hint);
        ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
        return rv;
    }

    private void handleSequenceFlowAnchors(final Point sourceAnchor,
            final Point targetAnchor,
            final ConnectionViewAndElementDescriptor newObject) {
        final SetConnectionAnchorsCommand setConnectionAnchorsCommand = new SetConnectionAnchorsCommand(editingDomain, "Add anchors");
        setConnectionAnchorsCommand.setEdgeAdaptor(newObject);
        if (sourceAnchor != null) {
            setConnectionAnchorsCommand.setNewSourceTerminal("(" + sourceAnchor.preciseX() + "," + sourceAnchor.preciseY() + ")");
        }
        if (targetAnchor != null) {
            setConnectionAnchorsCommand.setNewTargetTerminal("(" + targetAnchor.preciseX() + "," + targetAnchor.preciseY() + ")");
        }
        diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setConnectionAnchorsCommand));
    }

    /**
     * It depends if source and target are correctly defined and already exists.
     *
     * @param srcId
     * @param trgtId
     * @param sourceNode
     * @param targetNode
     * @return
     */
    private boolean canSequenceFlowBeCreated(final String srcId, final String trgtId,
            final ShapeNodeEditPart sourceNode, final ShapeNodeEditPart targetNode) {
        if (sourceNode == null) {
            return false;
        }

        if (targetNode == null) {
            return false;
        }

        final EObject sourceElement = steps.get(srcId);
        final EObject targetElement = steps.get(trgtId);
        if (sourceElement != null && targetElement != null) {
            if (!ModelHelper.getParentProcess(sourceElement).equals(ModelHelper.getParentProcess(targetElement))) {
                return false;//TODO HAPPENS WITH EMBEDDED SUBPROC AND NOT SUPPORTED YET IN BOS
            }
        }

        if (createdSequenceFlows.contains(new Pair<String, String>(srcId, trgtId))) {
            return false; //Already exists
        }

        return true;
    }

    private void setBendPoints(final PointList bendpoints,
            final ConnectionViewAndElementDescriptor newObject) {
        final SetConnectionBendpointsCommand setConnectionBendPointsCommand = new SetConnectionBendpointsCommand(editingDomain);
        setConnectionBendPointsCommand.setEdgeAdapter(newObject);
        setConnectionBendPointsCommand.setNewPointList(bendpoints, bendpoints.getFirstPoint(), bendpoints.getLastPoint());
        diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setConnectionBendPointsCommand));
    }

    @Override
    public void addSequenceFlowCondition(final String content, final String interpreter, final String expressionType) {
        final Expression condition = createExpression(content, Boolean.class.getName(), interpreter, expressionType);
        condition.setReturnTypeFixed(true);
        commandStack.append(SetCommand.create(editingDomain, currentElement, ProcessPackage.eINSTANCE.getSequenceFlow_ConditionType(),
                SequenceFlowConditionType.EXPRESSION));
        commandStack.append(SetCommand.create(editingDomain, currentElement, ProcessPackage.eINSTANCE.getSequenceFlow_Condition(), condition));
        execute();
    }

    @Override
    public void addSequenceFlowDecisionTable() {
        final DecisionTable dt = DecisionFactory.eINSTANCE.createDecisionTable();
        commandStack.append(SetCommand.create(editingDomain, currentElement, ProcessPackage.eINSTANCE.getSequenceFlow_ConditionType(),
                SequenceFlowConditionType.DECISION_TABLE));
        commandStack.append(SetCommand.create(editingDomain, currentElement, ProcessPackage.eINSTANCE.getSequenceFlow_DecisionTable(), dt));
        execute();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addMessageFlow(java.lang.String, java.lang.String, java.lang.String,
     * org.eclipse.draw2d.geometry.PointList)
     */
    @Override
    public void addMessageFlow(final String name, final String eventName, final String sourceId, final String targetId, final Point sourceAnchor,
            final Point targetAnchor, final PointList bendpoints) {

        final String srcId = NamingUtils.convertToId(sourceId);
        final String trgtId = NamingUtils.convertToId(targetId);

        final Element source = (Element) steps.get(srcId);
        final Element target = (Element) steps.get(trgtId);

        if (source != null && target != null && source instanceof ThrowMessageEvent) {
            final ThrowMessageEvent sourceEvent = (ThrowMessageEvent) source;
            //must have an eventObject in the list in order to be able to create the MessageFlow
            final Message eventObject = ProcessFactory.eINSTANCE.createMessage();
            eventObject.setName(NamingUtils.convertToId(eventName));

            eventObject.setTargetElementExpression(ExpressionHelper.createConstantExpression(trgtId, String.class.getName()));
            final String processName = processIds.get(ModelHelper.getParentProcess(target));
            eventObject.setTargetProcessExpression(ExpressionHelper.createConstantExpression(processName, String.class.getName()));

            commandStack.append(SetCommand.create(editingDomain, eventObject, ProcessPackage.eINSTANCE.getMessage_Source(), sourceEvent));
            commandStack.append(SetCommand.create(editingDomain, target, ProcessPackage.eINSTANCE.getAbstractCatchMessageEvent_Event(), eventObject.getName()));

            final EditPart sourceEditPart = editParts.get(srcId);
            final EditPart targetEditPart = editParts.get(trgtId);
            messageFlows.add(new MessageFlowData(sourceEditPart, targetEditPart, sourceAnchor, targetAnchor));
            execute();

        }

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addTask(java.lang.String, java.lang.String, org.eclipse.draw2d.geometry.Point,
     * org.eclipse.draw2d.geometry.Dimension, org.bonitasoft.studio.importer.builder.IProcBuilder.TaskType)
     */
    @Override
    public void addTask(String id, final String name, final Point location, final Dimension size, final TaskType taskType) throws ProcBuilderException {

        id = NamingUtils.convertToId(id);
        final IElementType type = resolveTaskType(taskType);
        final Element createdElement = createShape(id, currentContainer, location, size, type);

        commandStack.append(SetCommand.create(editingDomain, createdElement, ProcessPackage.eINSTANCE.getElement_Name(), name != null ? name : id));

        if (taskType == TaskType.HUMAN) {
            commandStack.append(SetCommand.create(editingDomain, createdElement, ProcessPackage.eINSTANCE.getTask_Priority(), 1));
            currentAssignable = createdElement;
        }

        currentStep = createdElement;
        currentElement = createdElement;

        steps.put(id, createdElement);
        elementToReplaceName.put(createdElement, name);
    }

    private Element createShape(final String id, final EObject container, final Point location, final Dimension size, final IElementType type)
            throws ProcBuilderException {
        return createShape(id, container, location, size, type, true);
    }

    private IElementType resolveTaskType(final TaskType taskType) {
        switch (taskType) {
            case ABSTRACT:
                return ProcessElementTypes.Activity_3006;
            case CALL_ACTIVITY:
                return ProcessElementTypes.CallActivity_3063;
            case HUMAN:
                return ProcessElementTypes.Task_3005;
            case RECIEVE_TASK:
                return ProcessElementTypes.ReceiveTask_3026;
            case SCRIPT:
                return ProcessElementTypes.ScriptTask_3028;
            case SEND_TASK:
                return ProcessElementTypes.SendTask_3025;
            case SERVICE:
                return ProcessElementTypes.ServiceTask_3027;
        }
        return ProcessElementTypes.ServiceTask_3027;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addGateway(java.lang.String, java.lang.String, org.eclipse.draw2d.geometry.Point,
     * org.eclipse.draw2d.geometry.Dimension, org.bonitasoft.studio.importer.builder.IProcBuilder.GatewayType)
     */
    @Override
    public void addGateway(final String id, final String name, final Point location, final Dimension size, final GatewayType gatewayType)
            throws ProcBuilderException {
        addGateway(id, name, location, size, gatewayType, true);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addGateway(java.lang.String, java.lang.String, org.eclipse.draw2d.geometry.Point,
     * org.eclipse.draw2d.geometry.Dimension, org.bonitasoft.studio.importer.builder.IProcBuilder.GatewayType)
     */
    @Override
    public void addGateway(String id, final String name, final Point location, final Dimension size, final GatewayType gatewayType, final boolean displayLabel)
            throws ProcBuilderException {
        id = NamingUtils.convertToId(id);
        final IElementType type = resolveGatewayType(gatewayType);
        final Element createdElement = createShape(id, currentContainer, location, size, type);
        if (currentView != null && !currentView.getPersistedChildren().isEmpty()) {
            final Object decorator = currentView.getPersistedChildren().get(0);
            if (decorator instanceof DecorationNode) {
                commandStack.append(SetCommand.create(editingDomain, decorator, NotationPackage.Literals.VIEW__VISIBLE, displayLabel));
            }
        }
        commandStack.append(SetCommand.create(editingDomain, createdElement, ProcessPackage.eINSTANCE.getElement_Name(), name));

        currentStep = createdElement;
        currentElement = createdElement;

        steps.put(id, createdElement);
        execute();
    }

    private IElementType resolveGatewayType(final GatewayType gatewayType) {
        switch (gatewayType) {
            case XOR:
                return ProcessElementTypes.XORGateway_3008;
            case AND:
                return ProcessElementTypes.ANDGateway_3009;
            case INCLUSIVE:
                return ProcessElementTypes.InclusiveGateway_3051;
        }
        return ProcessElementTypes.ANDGateway_3009;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addEventSubprocess(java.lang.String, java.lang.String, org.eclipse.draw2d.geometry.Point,
     * org.eclipse.draw2d.geometry.Dimension, boolean)
     */
    @Override
    public void addEventSubprocess(String id, final String name, final Point location, final Dimension size, final boolean isCollapsed)
            throws ProcBuilderException {

        id = NamingUtils.convertToId(id);
        final Element createdElement = createShape(id, currentContainer, location, size, ProcessElementTypes.SubProcessEvent_3058, isCollapsed);

        final ShapeNodeEditPart nodeEditPart = (ShapeNodeEditPart) GMFTools.findEditPart(diagramPart, createdElement);
        final Node node = (Node) nodeEditPart.getNotationView();

        if (node != null) {
            DrawerStyle drawer = null;
            for (final Object child : node.getPersistedChildren()) {
                if (child instanceof DrawerStyle) {
                    drawer = (DrawerStyle) child;
                }
            }
            if (drawer != null) {
                commandStack.append(SetCommand.create(editingDomain, drawer, NotationPackage.eINSTANCE.getDrawerStyle_Collapsed(), isCollapsed));
            }
        }

        commandStack.append(SetCommand.create(editingDomain, createdElement, ProcessPackage.eINSTANCE.getElement_Name(), name));

        currentContainer = createdElement;
        currentStep = createdElement;
        currentElement = createdElement;
        execute();
    }

    private Element createShape(final String id, final EObject container, final Point location, final Dimension size, final IElementType type,
            final boolean isCollapsed) throws ProcBuilderException {

        diagramPart.refresh();
        final IGraphicalEditPart parentEditPart = GMFTools.findEditPart(diagramPart, container);
        final IGraphicalEditPart compartment = retrieveCompartmentEditPartFor(parentEditPart);
        if (compartment instanceof LaneLaneCompartmentEditPart) {
            int laneHeightOffset = 0;

            final PoolPoolCompartmentEditPart poolCompartment = (PoolPoolCompartmentEditPart) compartment.getParent().getParent();
            final int currentLaneIndex = ((Container) currentContainer.eContainer()).getElements().indexOf(currentContainer);
            if (currentLaneIndex > 0) {
                for (final Object object : poolCompartment.getChildren()) {
                    if (object instanceof LaneEditPart) {
                        final EObject semanticElement = ((LaneEditPart) object).resolveSemanticElement();
                        final int laneIndex = ((Container) currentContainer.eContainer()).getElements().indexOf(semanticElement);
                        if (laneIndex < currentLaneIndex) {
                            laneHeightOffset = laneHeightOffset + ((LaneEditPart) object).getFigure().getSize().height;
                        }
                    }
                }
            }
            if (laneHeightOffset != 0 && laneHeightOffset + 80 > location.y) {
                location.y = location.y + laneHeightOffset + 20;
            }
        }

        final PreferencesHint hint = diagramPart.getDiagramPreferencesHint();
        final PreferenceStore store = (PreferenceStore) hint.getPreferenceStore();
        store.setValue("isCollapsed", isCollapsed);

        final ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(new CreateElementRequestAdapter(new CreateElementRequest(type)),
                Node.class,
                ((IHintedType) type).getSemanticHint(), hint);
        final CreateViewAndElementRequest createRequest = createCreationRequest(location, size, viewDescriptor);

        if (container == null || !(container instanceof Element)) {
            throw new ProcBuilderException("Impossible to find the parent EditPart");
        }

        diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(compartment.getCommand(createRequest));
        compartment.refresh();

        final Node newNode = (Node) viewDescriptor.getAdapter(Node.class);
        if (newNode == null) {
            throw new ProcBuilderException("New element not created");
        }
        currentView = newNode;
        final Element createdElement = (Element) newNode.getElement();
        final ShapeNodeEditPart nodeEditPart = (ShapeNodeEditPart) GMFTools.findEditPart(diagramPart, createdElement);

        if (nodeEditPart == null) {
            throw new ProcBuilderException("New edit part not created");
        }

        editParts.put(id, nodeEditPart);

        return createdElement;
    }

    private CreateViewAndElementRequest createCreationRequest(final Point location,
            final Dimension size, final ViewAndElementDescriptor viewDescriptor) {
        final CreateViewAndElementRequest createRequest = new CreateViewAndElementRequest(viewDescriptor);
        if (location != null) {
            createRequest.setLocation(location);
        }
        if (size != null) {
            createRequest.setSize(size);
        }
        return createRequest;
    }

    private IGraphicalEditPart retrieveCompartmentEditPartFor(
            final IGraphicalEditPart editPart) {
        IGraphicalEditPart compartment = null;
        for (final Object c : editPart.getChildren()) {
            if (c instanceof ShapeCompartmentEditPart) {
                compartment = (IGraphicalEditPart) c;
            }
        }

        if (compartment == null) {
            compartment = editPart;
        }
        return compartment;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addAnnotation(java.lang.String, java.lang.String, org.eclipse.draw2d.geometry.Point,
     * org.eclipse.draw2d.geometry.Dimension, java.lang.String)
     */
    @Override
    public void addAnnotation(final String text, final Point location, final Dimension size, final String sourceId) throws ProcBuilderException {

        final ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(new CreateElementRequestAdapter(new CreateElementRequest(
                ProcessElementTypes.TextAnnotation_3015)), Node.class,
                ((IHintedType) ProcessElementTypes.TextAnnotation_3015).getSemanticHint(), diagramPart.getDiagramPreferencesHint());
        final CreateViewAndElementRequest createRequest = createCreationRequest(
                location, size, viewDescriptor);
        IGraphicalEditPart parentEditPart = null;

        boolean sourceCanBeProcessed = false;
        if (sourceId != null) {
            Element sourceElement = (Element) steps.get(NamingUtils.convertToId(sourceId));
            if (sourceElement == null) {
                if (currentContainer == null || !(currentContainer instanceof Element)) {
                    throw new ProcBuilderException("Impossible to find the parent EditPart");
                }
                diagramPart.refresh();
                parentEditPart = GMFTools.findEditPart(diagramPart, currentContainer);
            } else {
                while (sourceElement != null && !(sourceElement instanceof Container)) {
                    sourceElement = (Element) sourceElement.eContainer();
                }
                sourceCanBeProcessed = true;
                parentEditPart = GMFTools.findEditPart(diagramPart, sourceElement);
            }
        } else {

            if (currentContainer == null || !(currentContainer instanceof Element)) {
                throw new ProcBuilderException("Impossible to find the parent EditPart");
            }
            diagramPart.refresh();
            parentEditPart = GMFTools.findEditPart(diagramPart, currentContainer);
        }

        final IGraphicalEditPart compartment = retrieveCompartmentEditPartFor(parentEditPart);

        diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(compartment.getCommand(createRequest));
        compartment.refresh();

        final Node newNode = (Node) viewDescriptor.getAdapter(Node.class);
        if (newNode == null) {
            throw new ProcBuilderException("New elment not created");
        }
        currentView = newNode;
        final TextAnnotation createdElement = (TextAnnotation) newNode.getElement();

        commandStack.append(SetCommand.create(editingDomain, createdElement, ProcessPackage.eINSTANCE.getTextAnnotation_Text(), text));

        if (sourceId != null && sourceCanBeProcessed) {
            createLinkBetweenTextAnnotationAndSourceEditPart(sourceId, createdElement);
        }
        execute();

    }

    private void createLinkBetweenTextAnnotationAndSourceEditPart(final String sourceId, final TextAnnotation createdElement) {
        final Element sourceElement = (Element) steps.get(NamingUtils.convertToId(sourceId));
        if (sourceElement != null) {
            final IElementType itemType = ProcessElementTypes.TextAnnotationAttachment_4003;
            final CreateConnectionViewAndElementRequest request = new CreateConnectionViewAndElementRequest(itemType,
                    ((IHintedType) itemType).getSemanticHint(), diagramPart.getDiagramPreferencesHint());

            final EditPart sourceEp = editParts.get(NamingUtils.convertToId(sourceId));
            final EditPart targetEp = GMFTools.findEditPart(diagramPart, createdElement);
            if (sourceEp != null) {//this case can happen when th etext annotation is link to a seuqneceflow but don't know why.
                final Command createSequenceFlowCommand = CreateConnectionViewAndElementRequest.getCreateCommand(request, targetEp, sourceEp);
                diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(createSequenceFlowCommand);

                final Node source = (Node) ((IGraphicalEditPart) sourceEp).getNotationView();
                final Location loc = (Location) source.getLayoutConstraint();
                final Node node = (Node) ((IGraphicalEditPart) targetEp).getNotationView();
                if (loc != null) {
                    commandStack
                            .append(SetCommand.create(editingDomain, node.getLayoutConstraint(), NotationPackage.eINSTANCE.getLocation_X(), loc.getX() + 60));
                    commandStack
                            .append(SetCommand.create(editingDomain, node.getLayoutConstraint(), NotationPackage.eINSTANCE.getLocation_Y(), loc.getY() - 50));
                }
            }
        }
    }

    private void execute() {
        editingDomain.getCommandStack().execute(commandStack);
        commandStack = new CompoundCommand();
    }

    @Override
    public void addActor(final String name, final String description) {
        final Actor actor = ProcessFactory.eINSTANCE.createActor();
        actor.setName(name);
        actor.setDocumentation(description);

        currentElement = actor;

        final AbstractProcess proc = ModelHelper.getParentProcess(currentContainer);
        commandStack.append(AddCommand.create(editingDomain, proc, ProcessPackage.eINSTANCE.getAbstractProcess_Actors(), actor));
        participants.put(name, actor);
        execute();

        execute();
    }

    private void updateDependencies(final String connectorId) {
        //		MainProcess mainProc = ModelHelper.getMainProcess(currentContainer) ;
        //		Set<String> entries = ConnectorJarUtil.getEnclosingJarArtifactNames(connectorId) ;
        //		commandStack.append(AddCommand.create(editingDomain, mainProc, ProcessPackage.eINSTANCE.getMainProcess_IncludedEntries(),entries)) ;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addRoleMapperRoleResolverParticipants(java.lang.String, java.lang.String)
     */
    @Override
    public void addConnector(final String id, final String name, final String connectorId, final String version, final ConnectorEvent event,
            final boolean ignoreError) throws ProcBuilderException {
        if (!(currentStep instanceof ConnectableElement)) {
            throw new ProcBuilderException("Impossible to add a Connector on Current element :" + currentStep != null ? ((Element) currentStep).getName()
                    : "null");
        }

        final org.bonitasoft.studio.model.process.Connector connector = ProcessFactory.eINSTANCE.createConnector();
        connector.setName(NamingUtils.convertToId(id));
        connector.setDefinitionId(connectorId);
        connector.setDefinitionVersion(version);
        connector.setEvent(event == null ? null : event.toString());
        connector.setIgnoreErrors(ignoreError);
        final ConnectorConfiguration createConnectorConfiguration = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        createConnectorConfiguration.setName(NamingUtils.convertToId(id));
        createConnectorConfiguration.setDefinitionId(connectorId);
        createConnectorConfiguration.setVersion(version);
        connector.setConfiguration(createConnectorConfiguration);
        currentConnector = connector;
        currentElement = connector;

        commandStack.append(AddCommand.create(editingDomain, currentStep, ProcessPackage.eINSTANCE.getConnectableElement_Connectors(), connector));
        execute();
        updateDependencies(connectorId);
        execute();

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addRoleMapperRoleResolverParticipants(java.lang.String, java.lang.String)
     */
    @Override
    public void addFilter(final String id, final String name, final String connectorId, final boolean ignoreError) throws ProcBuilderException {
        if (!(currentAssignable instanceof Assignable)) {
            throw new ProcBuilderException("Impossible to add a Filter on Current element :" + currentAssignable != null
                    ? ((Element) currentAssignable).getName() : "null");
        }

        final org.bonitasoft.studio.model.process.Connector filter = ProcessFactory.eINSTANCE.createConnector();
        filter.setName(NamingUtils.convertToId(id));
        filter.setDefinitionId(connectorId);
        filter.setIgnoreErrors(ignoreError);
        currentConnector = filter;
        currentElement = filter;

        commandStack.append(AddCommand.create(editingDomain, currentAssignable, ProcessPackage.eINSTANCE.getAssignable_Filters(), filter));
        execute();
        updateDependencies(connectorId);
        execute();

    }

    @Override
    public void addMultiInstantiation(final boolean isSequential) throws ProcBuilderException {
        if (!(currentStep instanceof MultiInstantiable)) {
            throw new ProcBuilderException("Impossible to add a MultiInstantiation on Current element :" + currentStep != null
                    ? ((Element) currentStep).getName() : "null");
        }
        MultiInstanceType type = MultiInstanceType.NONE;
        if (isSequential) {
            type = MultiInstanceType.SEQUENTIAL;
        } else {
            type = MultiInstanceType.PARALLEL;
        }
        currentElement = currentStep;
        commandStack.append(SetCommand.create(editingDomain, currentStep, ProcessPackage.eINSTANCE.getMultiInstantiable_Type(), type));
        execute();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addCompletionConditionExpression(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public void addCompletionConditionExpression(final Expression completionCondition) throws ProcBuilderException {
        if (!(currentStep instanceof MultiInstantiable)) {
            throw new ProcBuilderException("Impossible to add a completion condition on Current element :" + currentStep != null
                    ? ((Element) currentStep).getName() : "null");
        }
        if (completionCondition != null && completionCondition.hasContent() && !completionCondition.hasName()) {
            completionCondition.setName("completionCondition");
        }
        commandStack.append(SetCommand
                .create(editingDomain, currentStep, ProcessPackage.Literals.MULTI_INSTANTIABLE__COMPLETION_CONDITION, completionCondition));
        execute();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#addCardinalityExpression(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public void addCardinalityExpression(final Expression cardinalityExpression) throws ProcBuilderException {
        if (!(currentStep instanceof MultiInstantiable)) {
            throw new ProcBuilderException("Impossible to add a cardinality expression on Current element :" + currentStep != null
                    ? ((Element) currentStep).getName() : "null");
        }
        if (cardinalityExpression != null && cardinalityExpression.hasContent() && !cardinalityExpression.hasName()) {
            cardinalityExpression.setName("cardinalityExpression");
        }
        commandStack.append(SetCommand
                .create(editingDomain, currentStep, ProcessPackage.Literals.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION, cardinalityExpression));
        execute();
    }

    @Override
    @Deprecated
    public void addConnectorParameter(final String parameterKey, final String valueContent) throws ProcBuilderException {

        if (!(currentConnector instanceof org.bonitasoft.studio.model.process.Connector)) {
            String name = "null";
            if (currentConnector != null) {
                name = ((Element) currentConnector).getName();
            }
            throw new ProcBuilderException("Impossible to add a connector parameter on Current connector : " + name);
        }

        final ConnectorParameter parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        parameter.setKey(parameterKey);
        parameter.setExpression(createExpression(valueContent, "java.lang.String", ExpressionConstants.GROOVY, ExpressionConstants.SCRIPT_TYPE));

        commandStack.append(AddCommand.create(editingDomain, currentConnector.getConfiguration(),
                ConnectorConfigurationPackage.eINSTANCE.getConnectorConfiguration_Parameters(), parameter));
        execute();
    }

    @Override
    public void addConnectorParameter(final String parameterKey, final String valueContent, final String valueReturnType, final String valueInterpreter,
            final String expressionType) throws ProcBuilderException {

        if (!(currentConnector instanceof org.bonitasoft.studio.model.process.Connector)) {
            throw new ProcBuilderException("Impossible to add a connector parameter on Current connector :" + currentConnector != null
                    ? ((Element) currentConnector).getName() : "null");
        }

        final ConnectorParameter parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        parameter.setKey(parameterKey);
        parameter.setExpression(createExpression(valueContent, valueReturnType, valueInterpreter, expressionType));

        commandStack.append(AddCommand.create(editingDomain, currentConnector.getConfiguration(),
                ConnectorConfigurationPackage.eINSTANCE.getConnectorConfiguration_Parameters(), parameter));
        execute();
    }

    @Override
    public void addConnectorOutput(final String targetDataId, final String valueContent, final String valueReturnType, final String valueInterpreter,
            final String expressionType) throws ProcBuilderException {

        if (!(currentConnector instanceof org.bonitasoft.studio.model.process.Connector)) {
            throw new ProcBuilderException("Impossible to add a connector output on Current connector :" + currentConnector != null
                    ? ((Element) currentConnector).getName() : "null");
        }

        final Operation parameter = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
        assignment.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        parameter.setOperator(assignment);
        parameter.setRightOperand(createExpression(valueContent, valueReturnType, valueInterpreter, expressionType));
        for (final Data data : ModelHelper.getAccessibleData(currentElement, true)) {
            if (targetDataId.equals(data.getName())) {
                parameter.setLeftOperand(createExpression(targetDataId, DataUtil.getTechnicalTypeFor(data), null, ExpressionConstants.VARIABLE_TYPE));
                break;
            }
        }

        commandStack.append(AddCommand.create(editingDomain, currentConnector, ProcessPackage.eINSTANCE.getConnector_Outputs(), parameter));
        execute();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.builder.IProcBuilder#done()
     */
    @Override
    public void done() throws ProcBuilderException {

        processLinkEvents();

        processMessageFlows();

        processExpressionDataReferences();

        processElementIDNameConversion();

        /* Need to release DiagramEventBroker because the OffscreenEditpart create on, and don't release it itself */
        DiagramEventBroker.stopListening(editingDomain);
        editingDomain.dispose();

        for (final Resource resource : diagramResources.values()) {
            try {
                resource.save(org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil.getSaveOptions());
            } catch (final IOException e) {
                ProcessDiagramEditorPlugin.getInstance().logError("Unable to store model and diagram resources", e); //$NON-NLS-1$
            }
        }

        if (shell != null) {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    shell.dispose();
                    shell = null;
                }
            });

        }
    }

    private void processElementIDNameConversion() {
        for (final Entry<Element, String> entry : elementToReplaceName.entrySet()) {
            //commandStack.append(SetCommand.create(editingDomain, entry.getKey(), ProcessPackage.eINSTANCE.getElement_Name(), entry.getValue()));
        }
        execute();
    }

    private void processExpressionDataReferences() {
        // TODO Auto-generated method stub

    }

    private void processLinkEvents() {
        final Set<Entry<ThrowLinkEvent, String>> entrySet = throwLinkEvents.entrySet();

        for (final Entry<ThrowLinkEvent, String> entry : entrySet) {
            final ThrowLinkEvent tle = entry.getKey();
            final Element cathLinkEvent = catchLinkEvents.get(entry.getValue());
            if (cathLinkEvent instanceof CatchLinkEvent) {//avoid case of duplicate id
                commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), tle, ProcessPackage.eINSTANCE.getThrowLinkEvent_To(), cathLinkEvent));
            }
        }
        execute();
    }

    private void processMessageFlows() throws ProcBuilderException {

        commandStack = new CompoundCommand();
        for (final MessageFlowData entry : messageFlows) {

            final EditPart source = entry.getSourceEP();
            final EditPart target = entry.getTargetEP();

            final CreateConnectionViewAndElementRequest request = new CreateConnectionViewAndElementRequest(ProcessElementTypes.MessageFlow_4002,
                    ((IHintedType) ProcessElementTypes.MessageFlow_4002).getSemanticHint(), diagramPart.getDiagramPreferencesHint());
            final Command createSequenceFlowCommand = CreateConnectionViewAndElementRequest.getCreateCommand(request, source, target);
            if (createSequenceFlowCommand == null) {
                continue;
            }

            diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(createSequenceFlowCommand);

            final MessageFlow createdElement = (MessageFlow) ((CreateRelationshipRequest) ((ConnectionViewAndElementDescriptor) request.getNewObject())
                    .getCreateElementRequestAdapter().getAdapter(CreateElementRequest.class)).getNewElement();
            final ThrowMessageEvent event = (ThrowMessageEvent) ((IGraphicalEditPart) source).resolveSemanticElement();
            String eventName = "";

            final Point sourceAnchorPoint = entry.getSourceAnchor();
            final Point targetAnchorPoint = entry.getTargetAnchor();

            if (!event.getEvents().isEmpty()) {
                eventName = event.getEvents().get(0).getName();
            }

            final Edge edge = (Edge) ((ConnectionViewAndElementDescriptor) request.getNewObject()).getAdapter(Edge.class);

            if (sourceAnchorPoint != null) {
                final IdentityAnchor sourceAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                sourceAnchor.setId("(" + sourceAnchorPoint.preciseX() + "," + sourceAnchorPoint.preciseY() + ")");
                commandStack.append(SetCommand.create(editingDomain, edge, NotationPackage.eINSTANCE.getEdge_SourceAnchor(), sourceAnchor));
            }
            if (targetAnchorPoint != null) {
                final IdentityAnchor targetAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                targetAnchor.setId("(" + targetAnchorPoint.preciseX() + "," + targetAnchorPoint.preciseY() + ")");
                commandStack.append(SetCommand.create(editingDomain, edge, NotationPackage.eINSTANCE.getEdge_TargetAnchor(), targetAnchor));
            }
            commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), createdElement, ProcessPackage.eINSTANCE.getElement_Name(),
                    NamingUtils.convertToId(eventName)));
            commandStack.append(SetCommand.create(editingDomain, edge.getStyle(NotationPackage.eINSTANCE.getLineStyle()),
                    NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.colorToInteger(ColorConstants.lightGray)));
        }

        execute();
    }

    private void setCharset(final IFile file) {
        if (file == null) {
            return;
        }
        try {
            file.setCharset("UTF-8", new NullProgressMonitor()); //$NON-NLS-1$
        } catch (final CoreException e) {
            ProcessDiagramEditorPlugin.getInstance().logError("Unable to set charset for file " + file.getFullPath(), e); //$NON-NLS-1$
        }
    }

    private org.bonitasoft.studio.model.process.DataType toProcDataType(final EObject container, final DataType dataType) throws ProcBuilderException {

        final MainProcess mainProc = ModelHelper.getMainProcess(container);
        if (mainProc == null || mainProc.getDatatypes().isEmpty()) {
            throw new ProcBuilderException("Datatypes not found : Invalid MainProcess");
        }
        switch (dataType) {
            case INTEGER:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.integerDataType);
            case STRING:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.stringDataType);
            case BOOLEAN:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.booleanDataType);
            case DOUBLE:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.doubleDataType);
            case DECIMAL:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.floatDataType);
            case JAVA:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.javaDataType);
            case XML:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.xmlDataType);
            case ENUM:
                break;
            case ATTACHMENT:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.attachedDocDataType);
            case DATE:
                return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.dateDataType);
            default:
                break;
        }
        return ModelHelper.getDataTypeForID(mainProc, DataTypeLabels.stringDataType);
    }

    @Override
    public void addAssignableActor(String actorName) throws ProcBuilderException {
        if (currentAssignable instanceof Assignable) {
            actorName = NamingUtils.convertToId(actorName);
            commandStack
                    .append(SetCommand.create(editingDomain, currentAssignable, ProcessPackage.eINSTANCE.getAssignable_Actor(), participants.get(actorName)));
            execute();
        } else {
            throw new ProcBuilderException("Invalid parent for group");
        }
    }

    @Override
    public void addEvent(String id, final String name, final Point location, final Dimension size, EventType eventType) throws ProcBuilderException {
        id = NamingUtils.convertToId(id);
        Element createdElement = null;
        if (eventType == EventType.TIMER_BOUNDARY
                || eventType == EventType.NON_INTERRUPTING_TIMER_BOUNDARY
                || eventType == EventType.MESSAGE_BOUNDARY
                || eventType == EventType.ERROR_BOUNDARY
                || eventType == EventType.SIGNAL_BOUNDARY) {
            if (!(currentStep instanceof Activity)) {
                throw new ProcBuilderException("Impossible to add a boundary on current element " + currentStep != null ? ((Element) currentStep).getName()
                        : "null");
            }
            if (currentStep instanceof SendTask) {
                BonitaStudioLog.log("Impossible to add a boundary on current element " + currentStep != null ? ((Element) currentStep).getName() : "null");
                return;
                //throw new ProcBuilderException("Impossible to add a boundary on current element "+currentStep != null ? ((Element) currentStep).getName() : "null") ;
            }
            final IElementType type = resolveBoundaryEventType(eventType, currentStep);
            createdElement = createShape(id, currentStep, location, size, type);
            if (location != null) {
                diagramPart.refresh();
                final IGraphicalEditPart parentEditPart = GMFTools.findEditPart(diagramPart, createdElement);
                diagramPart
                        .getDiagramEditDomain()
                        .getDiagramCommandStack()
                        .execute(
                                new ICommandProxy(new SetBoundsCommand(editingDomain, "Set position", new EObjectAdapter(parentEditPart.getNotationView()),
                                        location)));
            }
        } else {
            if (currentContainer instanceof SubProcessEvent
                    && eventType.equals(EventType.START)) {//it might be a not supported start event type that we converted in Start bfore
                eventType = EventType.START_ERROR;
                BonitaStudioLog.log("The event " + id + " has been converted into a Start Error");
            }
            final IElementType type = resolveEventType(eventType);
            createdElement = createShape(id, currentContainer, location, size, type);
        }

        commandStack.append(SetCommand.create(editingDomain, createdElement, ProcessPackage.eINSTANCE.getElement_Name(), name));

        if (createdElement instanceof CatchLinkEvent) {
            catchLinkEvents.put(id, (CatchLinkEvent) createdElement);
        }

        currentStep = createdElement;
        currentElement = createdElement;
        steps.put(id, createdElement);
        execute();
    }

    private IElementType resolveBoundaryEventType(final EventType eventType, final EObject parentStep) {
        if (parentStep instanceof Task) {
            return getBoundaryElementTypeOnTask(eventType);
        } else if (parentStep instanceof CallActivity) {
            return getBoundaryElementTypeOnCallActivity(eventType);
        } else if (parentStep instanceof ScriptTask) {
            return ProcessElementTypes.IntermediateErrorCatchEvent_3033;
        } else if (parentStep instanceof ServiceTask) {
            return ProcessElementTypes.IntermediateErrorCatchEvent_3032;
        } else if (parentStep instanceof ReceiveTask) {
            return ProcessElementTypes.IntermediateErrorCatchEvent_3031;
        } else if (parentStep instanceof Activity) {
            return ProcessElementTypes.IntermediateErrorCatchEvent_3034;
        }
        return null;
    }

    private IElementType getBoundaryElementTypeOnTask(final EventType eventType) {
        switch (eventType) {
            case ERROR_BOUNDARY:
                return ProcessElementTypes.IntermediateErrorCatchEvent_3029;
            case MESSAGE_BOUNDARY:
                return ProcessElementTypes.BoundaryMessageEvent_3035;
            case TIMER_BOUNDARY:
                return ProcessElementTypes.BoundaryTimerEvent_3043;
            case NON_INTERRUPTING_TIMER_BOUNDARY:
                return ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3064;
            case SIGNAL_BOUNDARY:
                return ProcessElementTypes.BoundarySignalEvent_3052;
            default:
                return null;
        }
    }

    private IElementType getBoundaryElementTypeOnCallActivity(final EventType eventType) {
        switch (eventType) {
            case ERROR_BOUNDARY:
                return ProcessElementTypes.IntermediateErrorCatchEvent_3030;
            case MESSAGE_BOUNDARY:
                return ProcessElementTypes.BoundaryMessageEvent_3036;
            case TIMER_BOUNDARY:
                return ProcessElementTypes.BoundaryTimerEvent_3044;
            case NON_INTERRUPTING_TIMER_BOUNDARY:
                return ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3065;
            case SIGNAL_BOUNDARY:
                return ProcessElementTypes.BoundarySignalEvent_3053;
            default:
                return null;
        }
    }

    private IElementType resolveEventType(final EventType eventType) {
        switch (eventType) {
            case END:
                return ProcessElementTypes.EndEvent_3003;
            case END_ERROR:
                return ProcessElementTypes.EndErrorEvent_3050;
            case END_MESSAGE:
                return ProcessElementTypes.EndMessageEvent_3011;
            case END_SIGNAL:
                return ProcessElementTypes.EndSignalEvent_3020;
            case END_TERMINATED:
                return ProcessElementTypes.EndTerminatedEvent_3062;
            case ERROR_BOUNDARY:
                return ProcessElementTypes.IntermediateErrorCatchEvent_3029;
            case INTERMEDIATE_CATCH_LINK:
                return ProcessElementTypes.CatchLinkEvent_3019;
            case INTERMEDIATE_CATCH_MESSAGE:
                return ProcessElementTypes.IntermediateCatchMessageEvent_3013;
            case INTERMEDIATE_CATCH_SIGNAL:
                return ProcessElementTypes.IntermediateCatchSignalEvent_3021;
            case INTERMEDIATE_CATCH_TIMER:
                return ProcessElementTypes.IntermediateCatchTimerEvent_3017;
            case INTERMEDIATE_THROW_LINK:
                return ProcessElementTypes.ThrowLinkEvent_3018;
            case INTERMEDIATE_THROW_MESSAGE:
                return ProcessElementTypes.IntermediateThrowMessageEvent_3014;
            case INTERMEDIATE_THROW_SIGNAL:
                return ProcessElementTypes.IntermediateThrowSignalEvent_3022;
            case MESSAGE_BOUNDARY:
                return ProcessElementTypes.BoundaryMessageEvent_3036;
            case SIGNAL_BOUNDARY:
                return ProcessElementTypes.BoundarySignalEvent_3053;
            case START:
                return ProcessElementTypes.StartEvent_3002;
            case START_ERROR:
                return ProcessElementTypes.StartErrorEvent_3060;
            case START_MESSAGE:
                return ProcessElementTypes.StartMessageEvent_3012;
            case START_SIGNAL:
                return ProcessElementTypes.StartSignalEvent_3023;
            case START_TIMER:
                return ProcessElementTypes.StartTimerEvent_3016;
            case TIMER_BOUNDARY:
                return ProcessElementTypes.BoundaryTimerEvent_3044;
            case NON_INTERRUPTING_TIMER_BOUNDARY:
                return ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3065;
            default:
                break;
        }
        return ProcessElementTypes.StartEvent_3002;
    }

    @Override
    public void addCallActivityTargetProcess(final String targetProcessId, final String targetProcessVersion) throws ProcBuilderException {
        if (currentStep instanceof CallActivity) {
            String targetProcessIdForExpression = targetProcessId;
            final AbstractProcess targetProcess = processes.get(targetProcessId);
            if (targetProcess != null) {
                targetProcessIdForExpression = targetProcess.getName();
            }
            commandStack.append(SetCommand.create(editingDomain, currentStep, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME,
                    createExpression(targetProcessIdForExpression, String.class.getName(), null, ExpressionConstants.CONSTANT_TYPE)));
            commandStack.append(SetCommand.create(editingDomain, currentStep, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION,
                    createExpression(targetProcessVersion, String.class.getName(), null, ExpressionConstants.CONSTANT_TYPE)));
            execute();
        } else {
            throw new ProcBuilderException("Can't add target process on current element");
        }
    }

    @Override
    public void addCallActivityInParameter(final String sourceDataId, final String targetDataId) throws ProcBuilderException {
        if (currentStep instanceof CallActivity) {
            final InputMapping inputMapping = ProcessFactory.eINSTANCE.createInputMapping();

            if (dataByName.get(sourceDataId) != null) {
                inputMapping.setProcessSource(ExpressionHelper.createVariableExpression(dataByName.get(NamingUtils.convertToId(sourceDataId))));

            }
            if (dataByName.get(targetDataId) != null) {
                inputMapping.setSubprocessTarget(dataByName.get(NamingUtils.convertToId(targetDataId)).getName());
            }

            commandStack.append(AddCommand.create(editingDomain, currentStep, ProcessPackage.Literals.CALL_ACTIVITY__INPUT_MAPPINGS, inputMapping));
            execute();
        } else {
            throw new ProcBuilderException("Can't process input parameter on current element");
        }
    }

    @Override
    public void addCallActivityOutParameter(final String sourceDataId, final String targetDataId)
            throws ProcBuilderException {
        if (currentStep instanceof CallActivity) {
            final OutputMapping outputMapping = ProcessFactory.eINSTANCE.createOutputMapping();
            if (dataByName.get(sourceDataId) != null) {
                outputMapping.setSubprocessSource(dataByName.get(sourceDataId).getName());
            }
            if (dataByName.get(targetDataId) != null) {
                outputMapping.setProcessTarget(dataByName.get(targetDataId));
            }

            commandStack.append(AddCommand.create(editingDomain, currentStep, ProcessPackage.Literals.CALL_ACTIVITY__OUTPUT_MAPPINGS, outputMapping));
            execute();
        } else {
            throw new ProcBuilderException("Can't process output parameter on current element");
        }

    }

    @Override
    public void addEnumData(final String id, final String name, final String defaultValue, final boolean isMultiple, final String dataTypeId)
            throws ProcBuilderException {
        if (datatypes.get(dataTypeId) == null) {
            throw new ProcBuilderException("Datatype " + dataTypeId + " not found");
        }

        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName(id);
        data.setDataType(datatypes.get(dataTypeId));
        data.setMultiple(isMultiple);
        if (defaultValue != null) {
            data.setDefaultValue(createExpression(defaultValue, "", "", ""));
        }

        if (currentStep instanceof DataAware) {
            commandStack.append(AddCommand.create(editingDomain, currentStep, ProcessPackage.eINSTANCE.getDataAware_Data(), data));
        } else if (currentContainer instanceof DataAware) {
            commandStack.append(AddCommand.create(editingDomain, currentContainer, ProcessPackage.eINSTANCE.getDataAware_Data(), data));
        } else {
            throw new ProcBuilderException("Impossible to add Data to the current container : " + currentContainer != null
                    ? ((Element) currentContainer).getName() : "null");
        }

        dataByName.put(id, data);

        currentElement = data;
        execute();
    }

    protected Expression createExpression(final String defaultValueContent, final String defaultValueReturnType, final String defaultValueInterpreter,
            final String expressionType) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        if (ExpressionConstants.GROOVY.equals(defaultValueInterpreter)) {
            exp.setName("groovyExpression");
            exp.setContent(defaultValueContent);
        } else {
            exp.setName(defaultValueContent);
            exp.setContent(defaultValueContent);
        }

        exp.setReturnType(defaultValueReturnType);
        if (ExpressionConstants.CONSTANT_TYPE.equals(expressionType)) {
            exp.setInterpreter(null);
            exp.setType(expressionType != null ? expressionType : ExpressionConstants.CONSTANT_TYPE);
        } else if (ExpressionConstants.GROOVY.equals(defaultValueInterpreter)) {//TODO: official groovy language to set?
            exp.setType(expressionType != null ? expressionType : ExpressionConstants.SCRIPT_TYPE);
            exp.setInterpreter(ExpressionConstants.GROOVY);

        } else if (ExpressionConstants.VARIABLE_TYPE.equals(expressionType)) {
            exp.setInterpreter(null);
            exp.setType(ExpressionConstants.VARIABLE_TYPE);
            final Data originalData = dataByName.get(defaultValueContent);
            if (originalData != null) {
                final Data copiedData = (Data) ExpressionHelper.createDependencyFromEObject(originalData);
                if (copiedData != null) {
                    exp.getReferencedElements().add(copiedData);
                }
            }
            exp.setContent(defaultValueContent);
        } else {
            exp.setInterpreter(null);
            exp.setType(expressionType != null ? expressionType : ExpressionConstants.CONSTANT_TYPE);
        }
        return exp;
    }

    @Override
    public void addErrorCode(final String errorCode) throws ProcBuilderException {
        if (!(currentStep instanceof ErrorEvent)) {
            throw new ProcBuilderException("Impossible to add an error code on currrent element :" + currentStep != null ? ((Element) currentStep).getName()
                    : "null");
        }

        if (errorCode == null) {
            throw new ProcBuilderException("Impossible to add a null error code");
        }
        commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, ProcessPackage.eINSTANCE.getErrorEvent_ErrorCode(), errorCode));
        execute();
    }

    @Override
    public void addSignalCode(final String signalCode) throws ProcBuilderException {
        if (!(currentStep instanceof SignalEvent)) {
            throw new ProcBuilderException("Impossible to add an signal code on currrent element :" + currentStep != null ? ((Element) currentStep).getName()
                    : "null");
        }

        if (signalCode == null) {
            throw new ProcBuilderException("Impossible to add a null signal code");
        }
        commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, ProcessPackage.eINSTANCE.getSignalEvent_SignalCode(), signalCode));
        execute();
    }

    @Override
    public void addTimerEventCondition(final String timerCondition) throws ProcBuilderException {
        if (!(currentStep instanceof AbstractTimerEvent)) {
            throw new ProcBuilderException("Impossible to add a timer condition on currrent element :" + currentStep != null
                    ? ((Element) currentStep).getName() : "null");
        }

        if (timerCondition == null) {
            throw new ProcBuilderException("Impossible to add a null error code");
        }
        commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, ProcessPackage.eINSTANCE.getAbstractTimerEvent_Condition(),
                timerCondition));
        execute();
    }

    @Override
    public void addThrowLinkEventTarget(final String eventID) throws ProcBuilderException {
        if (!(currentStep instanceof ThrowLinkEvent)) {
            throw new ProcBuilderException("Impossible to add a target event on currrent element :" + currentStep != null ? ((Element) currentStep).getName()
                    : "null");
        }
        throwLinkEvents.put((ThrowLinkEvent) currentStep, NamingUtils.convertToId(eventID));
    }

    @Override
    public void switchToParentContainer() throws ProcBuilderException {
        if (currentContainer.eContainer() != null) {
            currentContainer = currentContainer.eContainer();
        } else {
            throw new ProcBuilderException("Parent container is null");
        }

    }

    @Override
    public void setCurrentStep(final String stepId) throws ProcBuilderException {
        final EObject step = steps.get(NamingUtils.convertToId(stepId));
        if (step == null) {
            throw new ProcBuilderException(NamingUtils.convertToId(stepId) + " not found");
        }

        currentStep = step;
        currentElement = step;
        if (currentStep instanceof Assignable) {
            currentAssignable = step;
        }

    }

    @Override
    public void updateSize(String id, final Dimension size) throws ProcBuilderException {
        id = NamingUtils.convertToId(id);
        if (processes.get(id) != null) {
            final IGraphicalEditPart ep = GMFTools.findEditPart(diagramPart, processes.get(id));
            final Node newNode = (Node) ep.getNotationView();
            if (size != null) {
                commandStack.append(SetCommand.create(editingDomain, newNode.getLayoutConstraint(), NotationPackage.eINSTANCE.getSize_Width(), size.width));
                commandStack.append(SetCommand.create(editingDomain, newNode.getLayoutConstraint(), NotationPackage.eINSTANCE.getSize_Height(), size.height));
            }
        } else if (lanes.get(id) != null) {
            final IGraphicalEditPart ep = GMFTools.findEditPart(diagramPart, lanes.get(id));
            final Node newNode = (Node) ep.getNotationView();
            if (size != null) {
                commandStack.append(SetCommand.create(editingDomain, newNode.getLayoutConstraint(), NotationPackage.eINSTANCE.getSize_Width(), size.width));
                commandStack.append(SetCommand.create(editingDomain, newNode.getLayoutConstraint(), NotationPackage.eINSTANCE.getSize_Height(), size.height));
            }
        } else {
            throw new ProcBuilderException(id + " not found");
        }
    }

//    @Override
//    public void setStepDuration(final long duration) throws ProcBuilderException {
//        if (!(currentStep instanceof Activity)) {
//            throw new ProcBuilderException("Impossible to set duration property on " + currentStep != null ? ((Element) currentStep).getName() : "null");
//        }
//        commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, ProcessPackage.eINSTANCE.getActivity_Duration(),
//                String.valueOf(duration)));
//    }

    @Override
    public void addLoopCondition(final Expression loopConditionExpression, final String maxLoopExpression, final TestTimeType testTime)
            throws ProcBuilderException {
        if (!(currentStep instanceof Activity)) {
            throw new ProcBuilderException("Impossible to set duration property on " + currentStep != null ? ((Element) currentStep).getName() : "null");
        }
        commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, ProcessPackage.eINSTANCE.getMultiInstantiable_Type(),
                MultiInstanceType.STANDARD));
        if (loopConditionExpression != null && loopConditionExpression.hasContent() && !loopConditionExpression.hasName()) {
            loopConditionExpression.setName("loopCondition");
        }
        commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, ProcessPackage.eINSTANCE.getMultiInstantiable_LoopCondition(),
                loopConditionExpression));
        commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, ProcessPackage.eINSTANCE.getMultiInstantiable_LoopMaximum(),
                createExpression(maxLoopExpression, Integer.class.getName(), ExpressionConstants.GROOVY, ExpressionConstants.SCRIPT_TYPE)));
        if (testTime != null) {
            commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, ProcessPackage.eINSTANCE.getMultiInstantiable_TestBefore(),
                    testTime == TestTimeType.BEFORE ? true : false));
        }
    }

    @Override
    public void setAttributeOnCurrentStep(final EAttribute emfModelAttribute, final Object value) {
        if (currentStep != null) {
            commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentStep, emfModelAttribute, value));
        }
    }

    @Override
    public void setAttributeOnCurrentContainer(final EAttribute emfModelAttribute,
            final Object value) {
        if (currentContainer != null) {
            commandStack.append(SetCommand.create(diagramPart.getEditingDomain(), currentContainer, emfModelAttribute, value));
        }
    }

    public void addTargetAnchor(final Point targetAnchor) throws ProcBuilderException {
        if (!(currentElement instanceof Connection)) {
            throw new ProcBuilderException("Impossible to add a source anchor on " + currentElement != null ? ((Element) currentElement).getName() : "null");
        }

        final IGraphicalEditPart edge = GMFTools.findEditPart(diagramPart, currentElement);

        if (edge != null) {
            /* Add source anchors */
            final SetConnectionAnchorsCommand setConnectionAnchorsCommand = new SetConnectionAnchorsCommand(editingDomain, "Add source anchor");
            setConnectionAnchorsCommand.setEdgeAdaptor(new EObjectAdapter(edge.getNotationView()));
            setConnectionAnchorsCommand.setNewTargetTerminal("(" + targetAnchor.preciseX() + "," + targetAnchor.preciseY() + ")");
            diagramPart.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(setConnectionAnchorsCommand));
        }

    }

    class MessageFlowData {

        private final EditPart sourceEP;
        private final EditPart targetEP;
        private final Point sourceAnchor;
        private final Point targetAnchor;

        public MessageFlowData(final EditPart sourceEp, final EditPart targetEp, final Point sourceAnchor, final Point targetAnchor) {
            sourceEP = sourceEp;
            targetEP = targetEp;
            this.sourceAnchor = sourceAnchor;
            this.targetAnchor = targetAnchor;
        }

        public EditPart getSourceEP() {
            return sourceEP;
        }

        public EditPart getTargetEP() {
            return targetEP;
        }

        public Point getSourceAnchor() {
            return sourceAnchor;
        }

        public Point getTargetAnchor() {
            return targetAnchor;
        }

    }

    @Override
    public void setFontStyle(final String name, final int height, final boolean isBold, final boolean isItalic) throws ProcBuilderException {
        if (currentView == null) {
            throw new ProcBuilderException("Impossible to set font style property. There is no view set");
        }
        FontStyle fontStyle = (FontStyle) currentView.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (fontStyle == null) {
            fontStyle = NotationFactory.eINSTANCE.createFontStyle();
        }
        commandStack.append(SetCommand.create(editingDomain, fontStyle, NotationPackage.Literals.FONT_STYLE__BOLD, isBold));
        commandStack.append(SetCommand.create(editingDomain, fontStyle, NotationPackage.Literals.FONT_STYLE__ITALIC, isItalic));
        commandStack.append(SetCommand.create(editingDomain, fontStyle, NotationPackage.Literals.FONT_STYLE__FONT_NAME, name));
        commandStack.append(SetCommand.create(editingDomain, fontStyle, NotationPackage.Literals.FONT_STYLE__FONT_HEIGHT, height));
        execute();
    }

    /**
     * For SequenceFlow, the location need to be computed in the referential with x following the direction of the edge and y in anti-trigo
     */
    @Override
    public void setLabelPositionOnSequenceFlowOrEvent(final Point location) throws ProcBuilderException {
        if (currentView == null) {
            throw new ProcBuilderException("Impossible to set font style property. There is no view set");
        }
        if (!currentView.getChildren().isEmpty()) {
            final Node labelNode = (Node) currentView.getChildren().get(0);
            setLabelPosition(location, labelNode);
        }
    }

    /**
     * @param location
     * @param labelPart
     */
    protected void setLabelPosition(final Point location, final Node labelNode) {
        final Location labelLocation = (Location) labelNode.getLayoutConstraint();
        commandStack.append(SetCommand.create(editingDomain, labelLocation, NotationPackage.Literals.LOCATION__X, location.x()));
        commandStack.append(SetCommand.create(editingDomain, labelLocation, NotationPackage.Literals.LOCATION__Y, location.y()));
        execute();
    }

    /**
     * @param currentView the currentView to set
     */
    public void setCurrentView(final View currentView) {
        this.currentView = currentView;
    }

    /**
     * @return the currentView
     */
    public View getCurrentView() {
        return currentView;
    }

}
