/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.bpmn.transfo;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.CustomEventLabelEditPart;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorDefFileStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.Messages;
import org.bonitasoft.studio.exporter.bpmn.transfo.data.DataScope;
import org.bonitasoft.studio.exporter.bpmn.transfo.data.ItemDefinitionTransformer;
import org.bonitasoft.studio.exporter.bpmn.transfo.data.XMLNamespaceResolver;
import org.bonitasoft.studio.exporter.bpmn.transfo.expression.FormalExpressionTransformerFactory;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.exporter.extension.IBonitaTransformer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.BoundarySignalEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.EndMessageEvent;
import org.bonitasoft.studio.model.process.EndSignalEvent;
import org.bonitasoft.studio.model.process.EndTerminatedEvent;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Gateway;
import org.bonitasoft.studio.model.process.InclusiveGateway;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent;
import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.model.process.SignalEvent;
import org.bonitasoft.studio.model.process.StartErrorEvent;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.model.process.XMLType;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationAttachmentEditPart;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.omg.spec.bpmn.di.BPMNDiagram;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNLabel;
import org.omg.spec.bpmn.di.BPMNLabelStyle;
import org.omg.spec.bpmn.di.BPMNPlane;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.DiFactory;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TAssignment;
import org.omg.spec.bpmn.model.TAssociation;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TBoundaryEvent;
import org.omg.spec.bpmn.model.TCallActivity;
import org.omg.spec.bpmn.model.TCallableElement;
import org.omg.spec.bpmn.model.TCollaboration;
import org.omg.spec.bpmn.model.TComplexGateway;
import org.omg.spec.bpmn.model.TDataInput;
import org.omg.spec.bpmn.model.TDataInputAssociation;
import org.omg.spec.bpmn.model.TDataObject;
import org.omg.spec.bpmn.model.TDataOutput;
import org.omg.spec.bpmn.model.TDataOutputAssociation;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TDocumentation;
import org.omg.spec.bpmn.model.TEndEvent;
import org.omg.spec.bpmn.model.TErrorEventDefinition;
import org.omg.spec.bpmn.model.TExclusiveGateway;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TGateway;
import org.omg.spec.bpmn.model.TImport;
import org.omg.spec.bpmn.model.TInclusiveGateway;
import org.omg.spec.bpmn.model.TInputOutputSpecification;
import org.omg.spec.bpmn.model.TInputSet;
import org.omg.spec.bpmn.model.TInterface;
import org.omg.spec.bpmn.model.TIntermediateCatchEvent;
import org.omg.spec.bpmn.model.TIntermediateThrowEvent;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TLane;
import org.omg.spec.bpmn.model.TLaneSet;
import org.omg.spec.bpmn.model.TLinkEventDefinition;
import org.omg.spec.bpmn.model.TLoopCharacteristics;
import org.omg.spec.bpmn.model.TMessage;
import org.omg.spec.bpmn.model.TMessageEventDefinition;
import org.omg.spec.bpmn.model.TMessageFlow;
import org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics;
import org.omg.spec.bpmn.model.TOperation;
import org.omg.spec.bpmn.model.TOutputSet;
import org.omg.spec.bpmn.model.TParticipant;
import org.omg.spec.bpmn.model.TPerformer;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TProperty;
import org.omg.spec.bpmn.model.TReceiveTask;
import org.omg.spec.bpmn.model.TResourceRole;
import org.omg.spec.bpmn.model.TScriptTask;
import org.omg.spec.bpmn.model.TSendTask;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.omg.spec.bpmn.model.TServiceTask;
import org.omg.spec.bpmn.model.TSignal;
import org.omg.spec.bpmn.model.TSignalEventDefinition;
import org.omg.spec.bpmn.model.TStandardLoopCharacteristics;
import org.omg.spec.bpmn.model.TStartEvent;
import org.omg.spec.bpmn.model.TSubProcess;
import org.omg.spec.bpmn.model.TTerminateEventDefinition;
import org.omg.spec.bpmn.model.TText;
import org.omg.spec.bpmn.model.TTextAnnotation;
import org.omg.spec.bpmn.model.TThrowEvent;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.omg.spec.bpmn.model.TUserTask;
import org.omg.spec.bpmn.model.util.ModelResourceFactoryImpl;
import org.omg.spec.dd.dc.Bounds;
import org.omg.spec.dd.dc.DcFactory;
import org.omg.spec.dd.dc.DcPackage;
import org.omg.spec.dd.dc.Font;
import org.omg.spec.dd.di.Shape;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 * @author Mickael Istria
 */
public class BonitaToBPMN implements IBonitaTransformer {

    private static final String XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION = "bonitaConnector";
    private static final String JAVA_XMLNS = "java";
    private final List<String> errors;
    private final Map<Element, TFlowElement> mapping = new HashMap<Element, TFlowElement>();
    private final Map<Actor, TParticipant> participantMapping = new HashMap<Actor, TParticipant>();
    private BPMNPlane bpmnPlane;
    private TCollaboration collaboration;
    private final Map<String, TSignal> signalCodeTSignal = new HashMap<String, TSignal>();
    private final Set<TSignal> tSignals = new HashSet<TSignal>();
    private TDefinitions definitions;
    private DataScope dataScope;
    private DocumentRoot root;
    private File destBpmnFile;
    private BPMNDiagram bpmnDiagram;
    private XMLNamespaceResolver xmlNamespaceResolver;
    private final FormalExpressionTransformerFactory formalExpressionTransformerFactory;

    public BonitaToBPMN() {
        errors = new ArrayList<String>();
        errors.add(Messages.formsNotExported);
        formalExpressionTransformerFactory = new FormalExpressionTransformerFactory();
    }

    /**
     * @param part
     * @param destFile
     * @return if the operation has been successful
     */
    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.exporter.extension.IBonitaTransformer#transform(org.bonitasoft.studio.exporter.extension.IBonitaModelExporter, java.io.File,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public boolean transform(final IBonitaModelExporter modelExporter, final File destFile, final IProgressMonitor monitor) {

        final MainProcessEditPart part = modelExporter.getMainProcessEditPart();
        final MainProcess mainProcess = modelExporter.getDiagram();

        initializeDocumentRoot();

        definitions = ModelFactory.eINSTANCE.createTDefinitions();
        definitions.setExpressionLanguage("http://groovy.codehaus.org/");
        collaboration = ModelFactory.eINSTANCE.createTCollaboration();
        setCommonAttributes(mainProcess, collaboration);
        definitions.getRootElement().add(collaboration);
        bpmnDiagram = DiFactory.eINSTANCE.createBPMNDiagram();
        bpmnDiagram.setName(mainProcess.getName());
        bpmnPlane = DiFactory.eINSTANCE.createBPMNPlane();
        bpmnDiagram.setBPMNPlane(bpmnPlane);
        final QName rootQNameIDValue = QName.valueOf(collaboration.getId());
        bpmnPlane.setBpmnElement(rootQNameIDValue);
        bpmnPlane.setId("plane_" + collaboration.getId());
        definitions.getBPMNDiagram().add(bpmnDiagram);
        definitions.setTargetNamespace("http://bonitasoft.com/" + rootQNameIDValue);
        definitions.setExporter("BonitaSoft");
        definitions.setExporterVersion(ProductVersion.CURRENT_VERSION);

        /* Handle Bonita connector */
        destBpmnFile = destFile;

        configureNamespaces();
        //handleBonitaConnectorDefinition(destFile.getParentFile());
        //NO more sense? will create one xsd file on the fly for each required bpmn2 file
        dataScope = new DataScope(new ItemDefinitionTransformer(definitions, xmlNamespaceResolver));
        for (final Object childPart : part.getChildren()) {
            if (childPart instanceof PoolEditPart) {
                processPool((PoolEditPart) childPart, definitions, collaboration);
            }
        }

        populateWithMessageFlow(mainProcess);

        populateWithSignals(definitions);

        root.setDefinitions(definitions);
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".bpmn", new ModelResourceFactoryImpl());
        if (destFile.exists()) {
            destFile.delete();
        }
        final Resource resource = resourceSet.createResource(URI.createFileURI(destFile.getAbsolutePath()), "org.omg.schema.spec.bpmn.content-type");
        resource.getContents().add(root);
        try {
            final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
            saveOptions.put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));
            saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(saveOptions);
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
            return false;
        }
        return true;
    }

    protected void configureNamespaces() {
        root.getXMLNSPrefixMap().put(JAVA_XMLNS, "http://jcp.org/en/jsr/detail?id=270");
        root.getXMLNSPrefixMap().put(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION, "http://www.bonitasoft.org/studio/connector/definition/6.0");
        /* Two lines only for Bruce validation tools conformance... but it currently doesn't work either... */
        root.getXMLNSPrefixMap().put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.getXSISchemaLocation().put("schemaLocation", "http://www.omg.org/spec/BPMN/20100524/MODEL schemas/BPMN20.xsd");//http://www.omg.org/spec/BPMN/20100501/BPMN20.xsd
    }

    protected void initializeDocumentRoot() {
        root = ModelFactory.eINSTANCE.createDocumentRoot();
        xmlNamespaceResolver = new XMLNamespaceResolver(root);
    }

    protected void populateWithMessageFlow(final MainProcess mainProcess) {
        for (final MessageFlow messageFlow : mainProcess.getMessageConnections()) {
            final TMessageFlow bpmnMessageFlow = ModelFactory.eINSTANCE.createTMessageFlow();
            setCommonAttributes(messageFlow, bpmnMessageFlow);
            final TFlowElement source = mapping.get(messageFlow.getSource());
            bpmnMessageFlow.setSourceRef(QName.valueOf(source.getId()));
            final TFlowElement target = mapping.get(messageFlow.getTarget());
            bpmnMessageFlow.setTargetRef(QName.valueOf(target.getId()));
            collaboration.getMessageFlow().add(bpmnMessageFlow);
        }
    }

    protected void handleBonitaConnectorDefinition(final String connectorDefId) {
        final File connectorDefFile = createXSDForConnectorDef(connectorDefId);

        addConnectorDefInXsdIfNotYetIncluded(connectorDefFile);

        /* Handle input */
        final TMessage tMessageInputBonitaConnector = createConnectorDefInput(connectorDefId);
        /* handle output */
        final TMessage tMessageOutputBonitaConnector = createConnectorDefOutput(connectorDefId);
        /* Create interface with it operation */
        final TInterface tInterfaceBonitaConnector = ModelFactory.eINSTANCE.createTInterface();
        tInterfaceBonitaConnector.setName(connectorDefId + "_Bonita_Connector_Interface");
        tInterfaceBonitaConnector.setId(connectorDefId + "_Bonita_Connector_Interface");
        //tInterfaceBonitaConnector.setImplementationRef(QName.valueOf("BonitaConnector"));
        final TOperation tOperationConnector = ModelFactory.eINSTANCE.createTOperation();
        tOperationConnector.setId("Exec" + connectorDefId);
        tOperationConnector.setName("Exec" + connectorDefId);
        tOperationConnector.setInMessageRef(QName.valueOf(tMessageInputBonitaConnector.getId()));
        tOperationConnector.setOutMessageRef(QName.valueOf(tMessageOutputBonitaConnector.getId()));
        tInterfaceBonitaConnector.getOperation().add(tOperationConnector);
        definitions.getRootElement().add(tInterfaceBonitaConnector);
    }

    protected TMessage createConnectorDefOutput(final String connectorDefId) {
        final TItemDefinition tItemDefinitionBonitaConnectorOutput = ModelFactory.eINSTANCE.createTItemDefinition();
        tItemDefinitionBonitaConnectorOutput.setStructureRef(QName.valueOf(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":" + connectorDefId
                + "OutputType"));
        final String itemDefConnectorOutput = generateConnectorOutputItemDef(connectorDefId);
        tItemDefinitionBonitaConnectorOutput.setId(itemDefConnectorOutput);
        definitions.getRootElement().add(tItemDefinitionBonitaConnectorOutput);
        final TMessage tMessageOutputBonitaConnector = ModelFactory.eINSTANCE.createTMessage();
        tMessageOutputBonitaConnector.setItemRef(QName.valueOf(itemDefConnectorOutput));
        tMessageOutputBonitaConnector.setId(connectorDefId + "ConnectorMessageOutput");
        definitions.getRootElement().add(tMessageOutputBonitaConnector);
        return tMessageOutputBonitaConnector;
    }

    protected TMessage createConnectorDefInput(final String connectorDefId) {
        final TItemDefinition tItemDefinitionBonitaConnectorInput = ModelFactory.eINSTANCE.createTItemDefinition();
        tItemDefinitionBonitaConnectorInput.setStructureRef(QName.valueOf(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":" + connectorDefId
                + "InputType"));
        final String itemDefConnectorInput = generateConnectorInputItemDef(connectorDefId);
        tItemDefinitionBonitaConnectorInput.setId(itemDefConnectorInput);
        definitions.getRootElement().add(tItemDefinitionBonitaConnectorInput);
        final TMessage tMessageInputBonitaConnector = ModelFactory.eINSTANCE.createTMessage();
        tMessageInputBonitaConnector.setItemRef(QName.valueOf(itemDefConnectorInput));
        tMessageInputBonitaConnector.setId(connectorDefId + "ConnectorMessageInput");
        definitions.getRootElement().add(tMessageInputBonitaConnector);
        return tMessageInputBonitaConnector;
    }

    protected File createXSDForConnectorDef(final String connectorDefId) {
        final File connectorDefFolder = new File(destBpmnFile.getParentFile().getAbsolutePath() + File.separator + "connectorDefs");
        /* Export the xsd */
        if (!connectorDefFolder.exists()) {
            connectorDefFolder.mkdirs();
        }
        File connectorDefFile = null;
        try {
            final ConnectorDefRepositoryStore cdrs = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
            for (final IRepositoryFileStore rfs : cdrs.getChildren()) {
                final ConnectorDefFileStore cdfs = (ConnectorDefFileStore) rfs;
                final ConnectorDefinition cd = cdfs.getContent();
                if (cd.getId().equals(connectorDefId)) {
                    connectorDefFile = new File(((ConnectorDefFileStore) rfs).getEMFResource().getURI().toFileString());
                    break;
                }
            }
            if (connectorDefFile != null) {
                generateXSDForConnector(connectorDefFile/* child.getResource().getFullPath().toFile() */);
            } else {
                errors.add("The connector with id " + connectorDefId + " was not found.");
            }
        } catch (final URISyntaxException e) {
            BonitaStudioLog.error(e);
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        } catch (final TransformerException e) {
            BonitaStudioLog.error(e);
        }
        return connectorDefFile;
    }

    protected void addConnectorDefInXsdIfNotYetIncluded(final File connectorDefFile) {
        if (connectorDefFile != null) {
            boolean alreadyImported = false;
            final String locationImport = "connectorDefs/" + connectorDefFile.getName() + "connectors.xsd";
            for (final TImport imported : definitions.getImport()) {
                if (imported.getLocation().equals(locationImport)) {
                    alreadyImported = true;
                    break;
                }
            }
            if (!alreadyImported) {
                final TImport tImportBonitaConnector = ModelFactory.eINSTANCE.createTImport();
                tImportBonitaConnector.setImportType("http://www.w3.org/2001/XMLSchema");
                tImportBonitaConnector.setLocation(locationImport);
                tImportBonitaConnector.setNamespace("http://www.bonitasoft.org/studio/connector/definition/6.0");
                definitions.getImport().add(tImportBonitaConnector);
            }
        }
    }

    protected String generateConnectorInputItemDef(final String connectorDefId) {
        return connectorDefId + "ConnectorInput";
    }

    protected String generateConnectorOutputItemDef(final String connectorDefId) {
        return connectorDefId + "ConnectorOutput";
    }

    private static Templates xslTemplate = null;

    private void generateXSDForConnector(final File connectorToTransform) throws URISyntaxException, IOException, TransformerException {
        if (xslTemplate == null) {
            final TransformerFactory transFact = TransformerFactory.newInstance();
            final URL xsltUrl = ConnectorPlugin.getDefault().getBundle().getEntry("transfo/genConnectorsXSD.xsl");
            final File xsltFileoriginal = new File(FileLocator.toFileURL(xsltUrl).getFile());
            final Source xsltSource = new StreamSource(xsltFileoriginal);
            xslTemplate = transFact.newTemplates(xsltSource);
        }

        //FIXME: this is only a workaround because currently we can't serialize the xml file in a way that both EMF and xslt can handle it correctly
        // see http://java.dzone.com/articles/emf-reading-model-xml-%E2%80%93-how
        final File connectorToTransformWC = File.createTempFile(connectorToTransform.getName(), "");
        FileUtil.copy(connectorToTransform, connectorToTransformWC);
        FileUtil.replaceStringInFile(connectorToTransformWC,
                "xmlns:definition=\"http://www.bonitasoft.org/ns/connector/definition/6.0\"",
                "xmlns=\"http://www.bonitasoft.org/ns/connector/definition/6.0\" xmlns:definition=\"http://www.bonitasoft.org/ns/connector/definition/6.0\"");

        final Source xmlSource = new StreamSource(connectorToTransformWC);

        final String generatedXsdName = connectorToTransform.getName() + "connectors.xsd";
        final File connectorsDefFolder = new File(destBpmnFile.getParentFile().getAbsolutePath() + File.separator + "connectorDefs");
        if (!connectorsDefFolder.exists()) {
            connectorsDefFolder.mkdirs();
        }
        final OutputStream stream = new FileOutputStream(new File(connectorsDefFolder.getAbsolutePath() + File.separator + generatedXsdName));
        final Result result = new StreamResult(stream);

        final Transformer transformer = xslTemplate.newTransformer();
        transformer.setParameter("indent", true);
        transformer.transform(xmlSource, result);
    }

    /**
     * @param pollEditPart
     * @param definitions
     * @param collaborationDiagram
     * @param collaboration
     */
    private void processPool(final PoolEditPart pollEditPart, final TDefinitions definitions, final TCollaboration collaboration) {
        final Pool pool = (Pool) pollEditPart.resolveSemanticElement();
        // create semantic process
        final TProcess bpmnProcess = ModelFactory.eINSTANCE.createTProcess();
        setCommonAttributes(pool, bpmnProcess);
        definitions.getRootElement().add(bpmnProcess);
        final TParticipant participant = ModelFactory.eINSTANCE.createTParticipant();
        participant.setProcessRef(QName.valueOf(bpmnProcess.getId()));
        participant.setName(bpmnProcess.getName());//should be done after populate test Common attributes
        participant.setId(EcoreUtil.generateUUID());
        collaboration.getParticipant().add(participant);

        for (final Actor actor : pool.getActors()) {
            final TParticipant actorParticipant = ModelFactory.eINSTANCE.createTParticipant();
            setCommonAttributes(actor, actorParticipant);
            collaboration.getParticipant().add(actorParticipant);
            participantMapping.put(actor, actorParticipant);
        }

        //create graphic process
        final BPMNShape processShape = createBPMNShape(pollEditPart);
        processShape.setBpmnElement(QName.valueOf(participant.getId()));
        final IFigure bonitaPoolFigure = pollEditPart.getFigure();
        final Rectangle bounds = getAbsoluteLabelBounds(bonitaPoolFigure);
        final Bounds laneBounds = DcFactory.eINSTANCE.createBounds();
        laneBounds.setHeight(bounds.preciseHeight());
        laneBounds.setWidth(bounds.preciseWidth());
        laneBounds.setX(bounds.preciseX());
        laneBounds.setY(bounds.preciseY());
        processShape.setBounds(laneBounds);
        processShape.setId(ModelHelper.getEObjectID(pollEditPart.getNotationView()));
        processShape.setIsHorizontal(true);
        bpmnPlane.getDiagramElement().add(processShape);

        populateWithData(pool, bpmnProcess);//create data before in order to have them accessible after
        populateWithMessage(pool, bpmnProcess);
        populate(pollEditPart, bpmnProcess, null);
        populateWithSequenceFlow(pollEditPart, bpmnProcess);
        populateWithTextAnnotation(pollEditPart, pool, bpmnProcess);

    }

    private void populateWithTextAnnotation(final PoolEditPart poolEditPart, final Pool pool, final TProcess bpmnProcess) {
        final List<TextAnnotation> annotations = ModelHelper.getAllItemsOfType(pool, ProcessPackage.Literals.TEXT_ANNOTATION);
        bpmnProcess.getArtifact().addAll(newArrayList(Iterables.transform(annotations, new Function<TextAnnotation, TTextAnnotation>() {

            @Override
            public TTextAnnotation apply(final TextAnnotation source) {
                final TTextAnnotation annotation = ModelFactory.eINSTANCE.createTTextAnnotation();
                final TText text = ModelFactory.eINSTANCE.createTText();
                text.getMixed().add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, source.getText());
                annotation.setText(text);
                annotation.setId(EcoreUtil.generateUUID());
                final List<TextAnnotationAttachment> textAnnotationAttachments = ModelHelper.getAllItemsOfType(pool,
                        ProcessPackage.Literals.TEXT_ANNOTATION_ATTACHMENT);

                final Map filterEntries = Maps.filterEntries(poolEditPart.getViewer().getEditPartRegistry(),
                        entryFor(source, TextAnnotation2EditPart.VISUAL_ID));

                final ShapeNodeEditPart editPart = (ShapeNodeEditPart) filterEntries.values().iterator().next();
                createGraphicForFlowElement(editPart, annotation);

                for (final TextAnnotationAttachment attachement : textAnnotationAttachments) {
                    if (Objects.equals(attachement.getSource(), source)) {
                        TFlowElement tFlowElement = mapping.get(attachement.getTarget());
                        if (tFlowElement != null && !isNullOrEmpty(tFlowElement.getId())) {
                            final TAssociation association = ModelFactory.eINSTANCE.createTAssociation();
                            association.setId(EcoreUtil.generateUUID());
                            association.setSourceRef(QName.valueOf(annotation.getId()));
                            association.setTargetRef(QName.valueOf(mapping.get(attachement.getTarget()).getId()));
                            bpmnProcess.getArtifact().add(association);
                            final BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
                            edge.setBpmnElement(QName.valueOf(association.getId()));
                            final Map attachmentEntries = Maps.filterEntries(poolEditPart.getViewer().getEditPartRegistry(),
                                    entryFor(attachement, TextAnnotationAttachmentEditPart.VISUAL_ID));
                            final TextAnnotationAttachmentEditPart connectionEditPart = (TextAnnotationAttachmentEditPart) attachmentEntries.values().iterator()
                                    .next();
                            final PointList pointList = connectionEditPart.getConnectionFigure().getPoints();
                            for (int i = 0; i < pointList.size(); i++) {
                                final org.omg.spec.dd.dc.Point ddPoint = DcFactory.eINSTANCE.createPoint();
                                final Point point = pointList.getPoint(i);
                                ddPoint.setX(point.preciseX());
                                ddPoint.setY(point.preciseY());
                                edge.getWaypoint().add(ddPoint);
                            }

                            edge.setId(ModelHelper.getEObjectID(connectionEditPart.getNotationView()));
                            bpmnPlane.getDiagramElement().add(edge);
                        }
                    }
                }
                return annotation;
            }

        })));
    }

    private Predicate<Entry<Object, Object>> entryFor(final EObject element, final int visualID) {
        return new Predicate<Entry<Object, Object>>() {

            @Override
            public boolean apply(Entry<Object, Object> entry) {
                if (entry.getValue() instanceof IGraphicalEditPart) {
                    final View view = (View) entry.getKey();
                    final IGraphicalEditPart part = (IGraphicalEditPart) entry.getValue();
                    return Objects.equals(part.resolveSemanticElement(), element)
                            && Objects.equals(Integer.valueOf(view.getType()), visualID);
                }
                return false;
            }

        };
    }

    private void populateWithMessage(final Pool pool, final TProcess bpmnProcess) {
        //messageMap.clear();
        final List<ThrowMessageEvent> thowMessageEvents = ModelHelper.getAllItemsOfType(pool, ProcessPackage.eINSTANCE.getThrowMessageEvent());
        for (final ThrowMessageEvent throwMessageEvent : thowMessageEvents) {
            for (final Message message : throwMessageEvent.getEvents()) {
                final TMessage tMessage = ModelFactory.eINSTANCE.createTMessage();
                tMessage.setId(EcoreUtil.getID(message));
                //				tMessage.setItemRef(value)
                //				message.getMessageContent().getExpressions()
            }
        }
    }

    private void populateWithSignals(final TDefinitions definitions) {
        definitions.getRootElement().addAll(tSignals);
    }

    protected void populateWithData(final Pool pool, final TProcess bpmnProcess) {
        dataScope.initializeContext(pool);
        for (final EObject item : pool.getData()) {
            final Data bonitaData = (Data) item;

            /* Create the itemDefinition */
            final TItemDefinition dataItemDefinition = dataScope.get(bonitaData);
            final QName dataItemDefinitionIdAsQname = QName.valueOf(dataItemDefinition.getId());

            /* Add the dataObject using the reference */
            createDataObject(bpmnProcess, bonitaData, dataItemDefinitionIdAsQname);

            /* Define required data on the process, facultative? */
            final TDataInput tDataInput = fillIOSpecification(bpmnProcess, dataItemDefinitionIdAsQname);

            //TODO: handle default values of datas...
            /* Add default value as inputdata on the process */
            final Expression defaultValue = bonitaData.getDefaultValue();
            if (defaultValue != null) {
                final TDataInputAssociation tDataInputAssociation = createDataInputAssociation(tDataInput, defaultValue);
                //TODO: and now I put the inputassociation where??? I can't put it on processes :'(
                tDataInputAssociation.getAnyAttribute();
            }
        }

        TInputOutputSpecification ioSpecification = bpmnProcess.getIoSpecification();
        if (ioSpecification == null) {
            ioSpecification = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            ioSpecification.setId(EcoreUtil.generateUUID());
            bpmnProcess.setIoSpecification(ioSpecification);
        }
        if (ioSpecification.getInputSet().isEmpty()) {
            final TInputSet createTInputSet = ModelFactory.eINSTANCE.createTInputSet();
            createTInputSet.setId(EcoreUtil.generateUUID());
            ioSpecification.getInputSet().add(createTInputSet);
        }
        if (ioSpecification.getOutputSet().isEmpty()) {
            final TOutputSet createTOutputSet = ModelFactory.eINSTANCE.createTOutputSet();
            createTOutputSet.setId(EcoreUtil.generateUUID());
            ioSpecification.getOutputSet().add(createTOutputSet);
        }
    }

    protected TDataInputAssociation createDataInputAssociation(final TDataInput tDataInput, final Expression defaultValue) {
        final TDataInputAssociation tDataInputAssociation = ModelFactory.eINSTANCE.createTDataInputAssociation();
        tDataInputAssociation.setId(EcoreUtil.generateUUID());
        tDataInputAssociation.setTargetRef(tDataInput.getId());
        final EList<TAssignment> assignment = tDataInputAssociation.getAssignment();
        final TAssignment tAssignment = ModelFactory.eINSTANCE.createTAssignment();

        final TFormalExpression toExpression = ModelFactory.eINSTANCE.createTFormalExpression();
        toExpression.setId(EcoreUtil.generateUUID());
        FeatureMapUtil.addText(toExpression.getMixed(), /* "getDataInput('"+ */tDataInput.getId()/* +"')" */);
        tAssignment.setTo(toExpression);

        final TFormalExpression fromExpression = createBPMNFormalExpressionFromBonitaExpression(defaultValue);
        tAssignment.setFrom(fromExpression);

        assignment.add(tAssignment);
        return tDataInputAssociation;
    }

    /**
     * Scope of data defined by this?
     *
     * @param callableElement
     * @param dataItemDefinitionIdAsQname
     * @return
     */
    protected TDataInput fillIOSpecification(final TCallableElement callableElement, final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = callableElement.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            callableElement.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataInput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    protected TDataInput fillIOSpecificationWithNewDataInput(final QName dataItemDefinitionIdAsQname, final TInputOutputSpecification tInputOutputAssociation) {
        final TDataInput tDataInput = ModelFactory.eINSTANCE.createTDataInput();
        tDataInput.setItemSubjectRef(dataItemDefinitionIdAsQname);
        tDataInput.setId(EcoreUtil.generateUUID());

        final TInputSet tInputSet = ModelFactory.eINSTANCE.createTInputSet();
        tInputSet.setId(EcoreUtil.generateUUID());
        tInputSet.getDataInputRefs().add(tDataInput.getId());
        final EList<TDataInput> dataInput = tInputOutputAssociation.getDataInput();
        final EList<TInputSet> inputSet = tInputOutputAssociation.getInputSet();
        inputSet.add(tInputSet);
        dataInput.add(tDataInput);
        return tDataInput;
    }

    protected TDataOutput fillIOSpecificationWithNewDataOutput(final QName dataItemDefinitionIdAsQname,
            final TInputOutputSpecification tInputOutputAssociation) {
        final TDataOutput tDataOutput = ModelFactory.eINSTANCE.createTDataOutput();
        tDataOutput.setItemSubjectRef(dataItemDefinitionIdAsQname);
        tDataOutput.setId(EcoreUtil.generateUUID());

        final TOutputSet tOutputSet = ModelFactory.eINSTANCE.createTOutputSet();
        tOutputSet.setId(EcoreUtil.generateUUID());
        tOutputSet.getDataOutputRefs().add(tDataOutput.getId());
        final EList<TDataOutput> dataOutput = tInputOutputAssociation.getDataOutput();
        final EList<TOutputSet> outputSet = tInputOutputAssociation.getOutputSet();
        outputSet.add(tOutputSet);
        dataOutput.add(tDataOutput);
        return tDataOutput;
    }

    protected TDataInput fillIOSpecificationWithNewDataInput(final TActivity tActivity, final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = tActivity.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            tActivity.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataInput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    private TDataOutput fillIOSpecificationWithNewDataOutput(final TServiceTask serviceTask, final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = serviceTask.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            serviceTask.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataOutput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    protected void createDataObject(final TProcess bpmnProcess, final Data bonitaData, final QName dataItemDefinitionIdAsQname) {
        final TDataObject bpmnData = ModelFactory.eINSTANCE.createTDataObject();
        bpmnData.setItemSubjectRef(dataItemDefinitionIdAsQname);
        setCommonAttributes(bonitaData, bpmnData);
        bpmnData.setName(bonitaData.getName());
        bpmnData.setId("DataObject" + EcoreUtil.generateUUID() + bpmnData.getId());//avoid to have duplicate id for dataobject and itemDefinition
        bpmnProcess.getFlowElement().add(bpmnData);
        bpmnData.setIsCollection(bonitaData.isMultiple());
    }

    protected TItemDefinition createDataItemDefinition(final Data bonitaData) {
        final TItemDefinition dataItemDefinition = ModelFactory.eINSTANCE.createTItemDefinition();
        setCommonAttributes(bonitaData, dataItemDefinition);
        dataItemDefinition.setStructureRef(getStructureRef(bonitaData));
        definitions.getRootElement().add(dataItemDefinition);
        return dataItemDefinition;
    }

    protected TExpression createBPMNExpressionFromString(final String value) {
        final TExpression fromExpression = ModelFactory.eINSTANCE.createTExpression();
        fromExpression.setId(EcoreUtil.generateUUID());
        FeatureMapUtil.addText(fromExpression.getMixed(), value);
        return fromExpression;
    }

    protected TFormalExpression createBPMNFormalExpressionFromBonitaExpression(final Expression bonitaExpression) {
        return formalExpressionTransformerFactory.newFormalExpressionTransformer(dataScope, bonitaExpression.getType()).transform(bonitaExpression);
    }

    protected QName getStructureRef(final Data data) {
        if (data.getDataType() instanceof XMLType) {
            final String xmlnsDataType = xmlNamespaceResolver.resolveNamespacePrefix((XMLData) data);
            return QName.valueOf(xmlnsDataType + ":" + ((XMLData) data).getType());
        } else {
            final String technicalTypeFor = org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data);
            return QName.valueOf(JAVA_XMLNS + ":" + technicalTypeFor);
        }
    }

    protected void populateWithSequenceFlow(final PoolEditPart poolEditPart, final TProcess bpmnProcess) {
        final Pool pool = (Pool) poolEditPart.resolveSemanticElement();
        for (final EObject item : ModelHelper.getAllItemsOfType(pool, ProcessPackage.Literals.SEQUENCE_FLOW)) {
            final SequenceFlow bonitaFlow = (SequenceFlow) item;
            final TSequenceFlow bpmnFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
            setCommonAttributes(bonitaFlow, bpmnFlow);
            if (bonitaFlow.getCondition() != null
                    && bonitaFlow.getCondition().getContent() != null) {
                final TExpression expression = createBPMNFormalExpressionFromBonitaExpression(bonitaFlow.getCondition());
                bpmnFlow.setConditionExpression(expression);
            }
            final TFlowElement source = mapping.get(bonitaFlow.getSource());
            if (source != null) {
                bpmnFlow.setSourceRef(source.getId());
                final TFlowElement target = mapping.get(bonitaFlow.getTarget());
                if (target != null) {
                    bpmnFlow.setTargetRef(target.getId());
                    bpmnProcess.getFlowElement().add(bpmnFlow);

                    // graphic
                    final ConnectionNodeEditPart editPart = (ConnectionNodeEditPart) GMFTools.findEditPart(poolEditPart, bonitaFlow);
                    final BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
                    edge.setBpmnElement(QName.valueOf(bpmnFlow.getId()));
                    final SequenceFlowNameEditPart labelPart = getSequenceFlowNameEditPart(editPart);
                    setSequenceFLowLabel(labelPart, edge);
                    final PointList pointList = editPart.getConnectionFigure().getPoints();
                    for (int i = 0; i < pointList.size(); i++) {
                        final org.omg.spec.dd.dc.Point ddPoint = DcFactory.eINSTANCE.createPoint();
                        final Point point = pointList.getPoint(i);
                        ddPoint.setX(point.preciseX());
                        ddPoint.setY(point.preciseY());
                        edge.getWaypoint().add(ddPoint);
                    }

                    edge.setId(ModelHelper.getEObjectID(editPart.getNotationView()));
                    bpmnPlane.getDiagramElement().add(edge);
                    if (bonitaFlow.isIsDefault()) {
                        if (bonitaFlow instanceof SequenceFlow) {
                            if (source instanceof TInclusiveGateway) {
                                ((TInclusiveGateway) source).setDefault(bpmnFlow.getId());
                            } else if (source instanceof TExclusiveGateway) {
                                ((TExclusiveGateway) source).setDefault(bpmnFlow.getId());
                            } else if (source instanceof TComplexGateway) {
                                ((TComplexGateway) source).setDefault(bpmnFlow.getId());
                            } else if (source instanceof TActivity) {
                                ((TActivity) source).setDefault(bpmnFlow.getId());
                            }
                        }
                    }
                } else {
                    BonitaStudioLog.log("Can't create the sequence flow named " + bonitaFlow.getName() + " because target element is null");
                }
            } else {
                BonitaStudioLog.log("Can't create the sequence flow named " + bonitaFlow.getName() + " because source element is null");
            }
        }
    }

    private SequenceFlowNameEditPart getSequenceFlowNameEditPart(final ConnectionNodeEditPart editPart) {
        final List<Object> children = editPart.getChildren();
        for (final Object child : children) {
            if (child instanceof SequenceFlowNameEditPart) {
                return (SequenceFlowNameEditPart) child;
            }
        }
        return null;
    }

    protected void setSequenceFLowLabel(final SequenceFlowNameEditPart labelPart, final BPMNEdge edge) {
        if (labelPart != null) {
            final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
            final IFigure bonitaElementFigure = labelPart.getFigure();
            setLabelBounds(label, bonitaElementFigure);
            edge.setBPMNLabel(label);
        }
    }

    /**
     * @param constraint
     * @param label
     * @param bonitaElementFigure
     */
    protected void setLabelBounds(final BPMNLabel label, final IFigure bonitaElementFigure) {
        final Rectangle bounds = getAbsoluteLabelBounds(bonitaElementFigure);
        final Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
        elementBounds.setHeight(bounds.preciseHeight());
        elementBounds.setWidth(bounds.preciseWidth());
        elementBounds.setX(bounds.preciseX());
        elementBounds.setY(bounds.preciseY());
        label.setBounds(elementBounds);
    }

    /**
     * @param bonitaElementFigure
     * @return
     */
    protected Rectangle getAbsoluteLabelBounds(final IFigure bonitaElementFigure) {
        final Rectangle bounds = bonitaElementFigure.getBounds().getCopy();
        FiguresHelper.translateToAbsolute(bonitaElementFigure, bounds);
        return bounds;
    }

    protected void setEventLabelBounds(final CustomEventLabelEditPart labelPart, final BPMNLabel label) {
        final IFigure bonitaElementFigure = labelPart.getFigure();
        setLabelBounds(label, bonitaElementFigure);
    }

    /**
     * @param bpmnProcess
     * @param bpmnProcessDiagram
     * @param pool
     */
    private void populate(IGraphicalEditPart poolOrLanePart, final TProcess bpmnProcess, final TLane bpmnLane) {
        if (poolOrLanePart instanceof PoolEditPart) {
            for (final Object subPart : poolOrLanePart.getChildren()) {
                if (subPart instanceof ShapeCompartmentEditPart) {
                    poolOrLanePart = (ShapeCompartmentEditPart) subPart;
                }
            }
        } else if (poolOrLanePart instanceof LaneEditPart) {
            for (final Object subPart : poolOrLanePart.getChildren()) {
                if (subPart instanceof ShapeCompartmentEditPart) {
                    poolOrLanePart = (ShapeCompartmentEditPart) subPart;
                }
            }
        }
        populateWithLanes(poolOrLanePart, bpmnProcess);
        populateWithElements(poolOrLanePart, bpmnProcess, bpmnLane);
    }

    /**
     * @param bpmnProcess
     * @param pool
     */
    protected void populateWithLanes(final IGraphicalEditPart poolOrLane, final TProcess bpmnProcess) {
        TLaneSet laneSet = null;
        for (final Object child : poolOrLane.getChildren()) {
            if (child instanceof LaneEditPart) {
                if (laneSet == null) {
                    //init the laneset (we are using only one laneset per pool
                    laneSet = ModelFactory.eINSTANCE.createTLaneSet();
                    laneSet.setId(bpmnProcess.getName() + "_laneSet");
                    bpmnProcess.getLaneSet().add(laneSet);
                }

                // semantic
                final LaneEditPart bonitaLanePart = (LaneEditPart) child;
                final Lane bonitaLane = (Lane) bonitaLanePart.resolveSemanticElement();
                final TLane bpmnLane = ModelFactory.eINSTANCE.createTLane();
                bpmnLane.setName(bonitaLane.getName());
                bpmnLane.setId(bonitaLane.getName());
                setCommonAttributes(bonitaLane, bpmnLane);
                laneSet.getLane().add(bpmnLane);
                // graphic
                final BPMNShape laneShape = createBPMNShape(bonitaLanePart);
                laneShape.setBpmnElement(QName.valueOf(bpmnLane.getId()));
                final IFigure bonitaLaneFigure = bonitaLanePart.getFigure();
                final Rectangle bounds = getAbsoluteLabelBounds(bonitaLaneFigure);
                final Bounds laneBounds = DcFactory.eINSTANCE.createBounds();
                laneBounds.setHeight(bounds.preciseHeight());
                laneBounds.setWidth(bounds.preciseWidth());
                laneBounds.setX(bounds.preciseX());
                laneBounds.setY(bounds.preciseY());
                laneShape.setBounds(laneBounds);
                laneShape.setId(ModelHelper.getEObjectID(bonitaLanePart.getNotationView()));
                laneShape.setIsHorizontal(true);
                bpmnPlane.getDiagramElement().add(laneShape);
                //TODO: add font attribute
                //				BPMNLabel bpmnLabel = DiFactory.eINSTANCE.createBPMNLabel();
                //				bonitaLanePart.getFigure().getFont();
                // recurse
                populate(bonitaLanePart, bpmnProcess, bpmnLane);
            }
        }
    }

    protected void populateWithElements(final IGraphicalEditPart part, final TSubProcess bpmnSubProcess, final TLane bpmnParentLane) {
        for (final Object child : part.getChildren()) {
            if (child instanceof CompartmentEditPart) {
                populateWithElements((IGraphicalEditPart) child, bpmnSubProcess, bpmnParentLane);
            } else if (!(child instanceof LaneEditPart)
                    && !(child instanceof SubProcessEventEditPart)
                    && !(child instanceof SubProcessEvent2EditPart)
                    && !(child instanceof ITextAwareEditPart)
                    && !(child instanceof CompartmentEditPart)) {
                final ShapeNodeEditPart bonitaElementPart = (ShapeNodeEditPart) child;
                if (bonitaElementPart.resolveSemanticElement() instanceof FlowElement) {
                    final FlowElement bonitaElement = (FlowElement) bonitaElementPart.resolveSemanticElement();
                    // semantic
                    final TFlowElement bpmnElement = createTFlowElement(bonitaElement);
                    bpmnSubProcess.getFlowElement().add(bpmnElement);
                    if (bpmnParentLane != null) {
                        bpmnParentLane.getFlowNodeRef().add(bpmnElement.getId());
                    }
                    mapping.put(bonitaElement, bpmnElement);

                    createGraphicForFlowElement(bonitaElementPart, bpmnElement);
                }
            } else if (child instanceof SubProcessEventEditPart
                    || child instanceof SubProcessEvent2EditPart) {
                final ShapeNodeEditPart bonitaElementPart = (ShapeNodeEditPart) child;
                final SubProcessEvent subProcEvent = (SubProcessEvent) bonitaElementPart.resolveSemanticElement();
                // semantic
                final TSubProcess bpmnSubProcess2 = (TSubProcess) createTFlowElement(subProcEvent);
                bpmnSubProcess.getFlowElement().add(bpmnSubProcess2);
                if (bpmnParentLane != null) {
                    bpmnParentLane.getFlowNodeRef().add(bpmnSubProcess.getId());
                }
                mapping.put(subProcEvent, bpmnSubProcess2);

                // graphic
                final boolean isExpanded = ((ShapeCompartmentEditPart) ((SubProcessEvent2EditPart) child).getChildren().get(0)).getCompartmentFigure()
                        .isExpanded();
                final BPMNShape elementShape = createBPMNShape(bonitaElementPart);
                elementShape.setIsExpanded(isExpanded);
                elementShape.setBpmnElement(QName.valueOf(bpmnSubProcess.getId()));
                final IFigure bonitaElementFigure = bonitaElementPart.getFigure();
                final Rectangle bounds = getAbsoluteLabelBounds(bonitaElementFigure);
                final Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
                elementBounds.setHeight(bounds.preciseHeight());
                elementBounds.setWidth(bounds.preciseWidth());
                elementBounds.setX(bounds.preciseX());
                elementBounds.setY(bounds.preciseY());
                elementShape.setBounds(elementBounds);
                elementShape.setId(ModelHelper.getEObjectID(bonitaElementPart.getNotationView()));
                bpmnPlane.getDiagramElement().add(elementShape);

                populateWithElements(bonitaElementPart, bpmnSubProcess2, bpmnParentLane);
            }
        }
    }

    /**
     * @param bpmnProcess
     * @param pool
     */
    protected void populateWithElements(final IGraphicalEditPart part, final TProcess bpmnProcess, final TLane bpmnParentLane) {
        final Map<Data, TItemDefinition> localDataMap = new HashMap<Data, TItemDefinition>();
        for (final Object child : part.getChildren()) {
            localDataMap.clear();
            if (!(child instanceof LaneEditPart)
                    && !(child instanceof SubProcessEventEditPart)
                    && !(child instanceof SubProcessEvent2EditPart)) {
                final ShapeNodeEditPart bonitaElementPart = (ShapeNodeEditPart) child;
                final EObject resolvedSemanticElement = bonitaElementPart.resolveSemanticElement();
                if (resolvedSemanticElement instanceof FlowElement) {
                    final FlowElement bonitaElement = (FlowElement) resolvedSemanticElement;
                    // semantic
                    final TFlowElement bpmnElement = createTFlowElement(bonitaElement);
                    bpmnProcess.getFlowElement().add(bpmnElement);
                    if (bpmnParentLane != null) {
                        bpmnParentLane.getFlowNodeRef().add(bpmnElement.getId());
                    }
                    mapping.put(bonitaElement, bpmnElement);

                    populateDataOnActivity(bpmnProcess, localDataMap, resolvedSemanticElement, bpmnElement);

                    // graphic
                    createGraphicForFlowElement(bonitaElementPart, bpmnElement);

                    createBoundaries(bpmnProcess, bonitaElementPart, bonitaElement, bpmnElement);
                }
            } else if (child instanceof SubProcessEventEditPart
                    || child instanceof SubProcessEvent2EditPart) {
                final ShapeNodeEditPart bonitaElementPart = (ShapeNodeEditPart) child;
                final SubProcessEvent subProcEvent = (SubProcessEvent) bonitaElementPart.resolveSemanticElement();
                // semantic
                final TSubProcess bpmnSubProcess = (TSubProcess) createTFlowElement(subProcEvent);
                bpmnProcess.getFlowElement().add(bpmnSubProcess);
                if (bpmnParentLane != null) {
                    bpmnParentLane.getFlowNodeRef().add(bpmnSubProcess.getId());
                }
                mapping.put(subProcEvent, bpmnSubProcess);

                // graphic
                boolean isExpanded = true;
                for (final Object editpart : ((SubProcessEvent2EditPart) child).getChildren()) {
                    if (editpart instanceof ShapeCompartmentEditPart) {
                        isExpanded = ((ShapeCompartmentEditPart) editpart).getCompartmentFigure().isExpanded();
                        break;
                    }
                }

                final BPMNShape elementShape = createBPMNShape(bonitaElementPart);
                elementShape.setIsExpanded(isExpanded);
                elementShape.setBpmnElement(QName.valueOf(bpmnSubProcess.getId()));
                final IFigure bonitaElementFigure = bonitaElementPart.getFigure();
                final Rectangle bounds = getAbsoluteLabelBounds(bonitaElementFigure);
                final Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
                elementBounds.setHeight(bounds.preciseHeight());
                elementBounds.setWidth(bounds.preciseWidth());
                elementBounds.setX(bounds.preciseX());
                elementBounds.setY(bounds.preciseY());
                elementShape.setBounds(elementBounds);
                elementShape.setId(ModelHelper.getEObjectID(bonitaElementPart.getNotationView()));
                bpmnPlane.getDiagramElement().add(elementShape);

                populateWithElements(bonitaElementPart, bpmnSubProcess, bpmnParentLane);
            }
        }
    }

    protected void createGraphicForFlowElement(final ShapeNodeEditPart bonitaElementPart, final TBaseElement bpmnElement) {
        final BPMNShape elementShape = createBPMNShape(bonitaElementPart);

        elementShape.setBpmnElement(QName.valueOf(bpmnElement.getId()));
        final IFigure bonitaElementFigure = bonitaElementPart.getFigure();
        final Rectangle bounds = getAbsoluteLabelBounds(bonitaElementFigure);
        final Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
        elementBounds.setHeight(bounds.preciseHeight());
        elementBounds.setWidth(bounds.preciseWidth());
        elementBounds.setX(bounds.preciseX());
        elementBounds.setY(bounds.preciseY());
        elementShape.setBounds(elementBounds);
        elementShape.setId(ModelHelper.getEObjectID(bonitaElementPart.getNotationView()));

        bpmnPlane.getDiagramElement().add(elementShape);
    }

    protected BPMNShape createBPMNShape(final ShapeNodeEditPart bonitaElementPart) {
        final BPMNShape elementShape = DiFactory.eINSTANCE.createBPMNShape();
        final Font font = createFont(bonitaElementPart);
        if (font != null) {
            final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
            final BPMNLabelStyle labelStyle = getLabelStyle(font);
            label.setId(EcoreUtil.generateUUID());
            label.setLabelStyle(QName.valueOf(labelStyle.getId()));
            final CustomEventLabelEditPart labelPart = getLabelEditPart(bonitaElementPart);
            if (labelPart != null) {
                setEventLabelBounds(labelPart, label);
                elementShape.setBPMNLabel(label);
            }

        }

        final Map<String, String> colors = getShapeColors(bonitaElementPart);
        //TODO add an extension for colors
        return elementShape;
    }

    private CustomEventLabelEditPart getLabelEditPart(final ShapeNodeEditPart bonitaElementPart) {
        final List<?> children = bonitaElementPart.getChildren();
        for (final Object child : children) {
            if (child instanceof CustomEventLabelEditPart) {
                return (CustomEventLabelEditPart) child;
            }
        }
        return null;
    }

    protected Map<String, String> getShapeColors(final ShapeNodeEditPart bonitaElementPart) {
        final Map<String, String> colorsMap = new HashMap<String, String>();
        final View shape = bonitaElementPart.getNotationView();
        final FontStyle fontStyle = (FontStyle) shape.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (fontStyle != null) {
            colorsMap.put("fontColor", String.valueOf(fontStyle.getFontColor()));
        }
        final FillStyle fillStyle = (FillStyle) shape.getStyle(NotationPackage.Literals.FILL_STYLE);
        if (fillStyle != null) {
            colorsMap.put("fillColor", String.valueOf(fillStyle.getFillColor()));
        }
        final LineStyle lineStyle = (LineStyle) shape.getStyle(NotationPackage.Literals.LINE_STYLE);
        if (lineStyle != null) {
            colorsMap.put("lineColor", String.valueOf(lineStyle.getLineColor()));
        }
        return colorsMap;
    }

    protected BPMNLabelStyle getLabelStyle(final Font font) {
        final Comparator<Font> fontComparator = new Comparator<Font>() {

            @Override
            public int compare(final Font font1, final Font font2) {
                for (final EStructuralFeature f : DcPackage.Literals.FONT.getEStructuralFeatures()) {
                    if (!font1.eGet(f).equals(font2.eGet(f))) {
                        return 1;
                    }
                }
                return 0;
            }
        };
        for (final BPMNLabelStyle style : bpmnDiagram.getBPMNLabelStyle()) {
            if (fontComparator.compare(style.getFont(), font) == 0) {
                return style;
            }
        }
        final BPMNLabelStyle labelStyle = DiFactory.eINSTANCE.createBPMNLabelStyle();
        labelStyle.setFont(font);
        labelStyle.setId(EcoreUtil.generateUUID());
        bpmnDiagram.getBPMNLabelStyle().add(labelStyle);
        return labelStyle;
    }

    protected Font createFont(final ShapeNodeEditPart bonitaElementPart) {
        final View shape = bonitaElementPart.getNotationView();
        final FontStyle fontStyle = (FontStyle) shape.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (fontStyle != null) {
            final Font font = DcFactory.eINSTANCE.createFont();
            font.setIsBold(fontStyle.isBold());
            font.setIsItalic(fontStyle.isItalic());
            font.setIsStrikeThrough(fontStyle.isStrikeThrough());
            font.setIsUnderline(fontStyle.isUnderline());
            font.setName(fontStyle.getFontName());
            font.setSize(fontStyle.getFontHeight());
            return font;
        }
        return null;
    }

    protected void populateDataOnActivity(final TProcess bpmnProcess,
            final Map<Data, TItemDefinition> localDataMap,
            final EObject resolvedSemanticElement, final TFlowElement bpmnElement) {
        if (resolvedSemanticElement instanceof DataAware
                && bpmnElement instanceof TActivity) {
            for (final Data bonitaData : ((DataAware) resolvedSemanticElement).getData()) {
                /* Create the itemDefinition */
                final TItemDefinition dataItemDefinition = createDataItemDefinition(bonitaData);
                localDataMap.put(bonitaData, dataItemDefinition);
                final QName dataItemDefinitionIdAsQname = QName.valueOf(dataItemDefinition.getId());
                if (!bonitaData.isTransient()) {
                    /* Add the dataObject using the reference */
                    createDataObject(bpmnProcess, bonitaData, dataItemDefinitionIdAsQname);
                } else {
                    /* it is a BPMN Property */
                    final TProperty tProperty = ModelFactory.eINSTANCE.createTProperty();
                    tProperty.setId(EcoreUtil.generateUUID());
                    tProperty.setName(bonitaData.getName());
                    tProperty.setItemSubjectRef(dataItemDefinitionIdAsQname);
                    ((TActivity) bpmnElement).getProperty().add(tProperty);
                }
                /* Define required data on the process, facultative? */
                final TDataInput tDataInput = fillIOSpecificationWithNewDataInput((TActivity) bpmnElement, dataItemDefinitionIdAsQname);

                if (bonitaData.getDefaultValue() != null
                        && bonitaData.getDefaultValue().getName() != null) {
                    final List<TDataInputAssociation> dataInputAssociation = ((TActivity) bpmnElement).getDataInputAssociation();
                    dataInputAssociation.add(createDataInputAssociation(tDataInput, bonitaData.getDefaultValue()));
                }
            }
        }
    }

    protected void createBoundaries(final TProcess bpmnProcess,
            final ShapeNodeEditPart bonitaElementPart, final FlowElement bonitaElement,
            final TFlowElement bpmnElement) {
        if (bonitaElement instanceof Activity) {
            for (final BoundaryEvent boundaryEvent : ((Activity) bonitaElement).getBoundaryIntermediateEvents()) {
                final TBoundaryEvent bpmnBoundary = ModelFactory.eINSTANCE.createTBoundaryEvent();
                setCommonAttributes(boundaryEvent, bpmnBoundary);
                if (boundaryEvent instanceof IntermediateErrorCatchEvent) {
                    final TErrorEventDefinition errorventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
                    errorventDef.setId("eventdef-" + boundaryEvent.getName() + EcoreUtil.generateUUID());
                    final String errorCode = ((IntermediateErrorCatchEvent) boundaryEvent).getErrorCode();
                    if (errorCode != null && errorCode.length() != 0) {
                        errorventDef.setErrorRef(QName.valueOf(errorCode));
                    }
                    bpmnBoundary.getEventDefinition().add(errorventDef);
                } else if (boundaryEvent instanceof BoundarySignalEvent) {
                    final TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
                    eventDef.setId("eventdef-" + boundaryEvent.getName() + EcoreUtil.generateUUID());
                    final TSignal tSignal = getOrCreateTSignal((SignalEvent) boundaryEvent);
                    if (tSignal != null) {
                        eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
                    }
                    bpmnBoundary.getEventDefinition().add(eventDef);
                } else if (boundaryEvent instanceof BoundaryMessageEvent) {
                    final TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
                    eventDef.setId("eventdef-" + boundaryEvent.getName() + EcoreUtil.generateUUID());
                    final String eventCaught = ((BoundaryMessageEvent) boundaryEvent).getEvent();
                    if (eventCaught != null && eventCaught.length() != 0) {
                        eventDef.setMessageRef(QName.valueOf(eventCaught));
                    }
                    bpmnBoundary.getEventDefinition().add(eventDef);
                } else if (boundaryEvent instanceof BoundaryTimerEvent) {
                    final TTimerEventDefinition eventDef = createTimerEventDef((AbstractTimerEvent) boundaryEvent);
                    bpmnBoundary.getEventDefinition().add(eventDef);
                }

                if (boundaryEvent instanceof NonInterruptingBoundaryTimerEvent) {
                    bpmnBoundary.setCancelActivity(false);
                } else {
                    bpmnBoundary.setCancelActivity(true);
                }

                bpmnBoundary.setAttachedToRef(QName.valueOf(bpmnElement.getId()));
                bpmnProcess.getFlowElement().add(bpmnBoundary);
                mapping.put(boundaryEvent, bpmnBoundary);

                //graphic
                final BPMNShape boundaryShape = createBPMNShape(bonitaElementPart);
                boundaryShape.setBpmnElement(QName.valueOf(bpmnBoundary.getId()));
                final IGraphicalEditPart boundaryElementPart = GMFTools.findEditPart(bonitaElementPart, boundaryEvent);
                final IFigure bonitaBoundaryFigure = boundaryElementPart.getFigure();
                final Rectangle bBounds = getAbsoluteLabelBounds(bonitaBoundaryFigure);
                final Bounds boundaryBounds = DcFactory.eINSTANCE.createBounds();
                boundaryBounds.setHeight(bBounds.preciseHeight());
                boundaryBounds.setWidth(bBounds.preciseWidth());
                boundaryBounds.setX(bBounds.preciseX());
                boundaryBounds.setY(bBounds.preciseY());
                boundaryShape.setBounds(boundaryBounds);
                boundaryShape.setId(ModelHelper.getEObjectID(boundaryElementPart.getNotationView()));
                bpmnPlane.getDiagramElement().add(boundaryShape);
            }
        }
    }

    protected TSignal getOrCreateTSignal(final SignalEvent signalEvent) {
        final String signalCode = signalEvent.getSignalCode();
        TSignal tSignal = null;
        if (signalCode != null) {
            tSignal = signalCodeTSignal.get(signalCode);
            if (tSignal == null) {
                tSignal = ModelFactory.eINSTANCE.createTSignal();
                tSignal.setId(EcoreUtil.generateUUID());
                tSignal.setName(signalCode);
                signalCodeTSignal.put(signalCode, tSignal);
                tSignals.add(tSignal);
            }
        } else {
            tSignal = ModelFactory.eINSTANCE.createTSignal();
            tSignal.setId(EcoreUtil.generateUUID());
            tSignals.add(tSignal);
        }
        return tSignal;
    }

    /**
     * @param bonitaLane
     * @param bpmnLane
     */
    protected void setCommonAttributes(final Element bonitaElement, final TBaseElement bpmnElement) {
        final String name = bonitaElement.getName();
        /* We must assure that each element have an id */
        //		if(name != null && name.length() != 0){
        //			bpmnElement.setId(name);
        //		} else {
        bpmnElement.setId(ModelHelper.getEObjectID(bonitaElement));
        //		}
        if (bpmnElement instanceof TFlowElement) {
            ((TFlowElement) bpmnElement).setName(bonitaElement.getName());
        } else if (bpmnElement instanceof TProcess) {
            ((TProcess) bpmnElement).setName(bonitaElement.getName());
        } else if (bpmnElement instanceof TParticipant) {
            ((TParticipant) bpmnElement).setName(bonitaElement.getName());
        }
        final String documentation = bonitaElement.getDocumentation();
        if (documentation != null && !documentation.isEmpty()) {
            final TDocumentation doc = ModelFactory.eINSTANCE.createTDocumentation();
            FeatureMapUtil.addText(doc.getMixed(), documentation);
            bpmnElement.getDocumentation().add(doc);
        }
    }

    public void setCommonDiagramAttributes(final ShapeNodeEditPart part, final Shape bpmnNode) {
        final Element semantic = (Element) part.resolveSemanticElement();
        final Bounds bounds = DcFactory.eINSTANCE.createBounds();
        final Point absoluteLocation = part.getLocation().getCopy();
        FiguresHelper.translateToAbsolute(part.getFigure(), absoluteLocation);
        final Dimension size = part.getSize();
        bounds.setHeight(size.height);
        bounds.setWidth(size.width);
        bounds.setX(absoluteLocation.x);
        bounds.setY(absoluteLocation.y);
        bpmnNode.setId(semantic.getName());
        //bpmnNode.setName(semantic.getLabel());
        bpmnNode.setBounds(bounds);
    }

    /**
     * @param child
     * @return
     */
    private TFlowElement createTFlowElement(final Element child) {
        TFlowElement res = null;
        if (child instanceof Activity) {
            res = createActivity((Activity) child);
            final Activity activity = (Activity) child;
            if (child instanceof ServiceTask
                    && !activity.getConnectors().isEmpty()) {
                final TServiceTask serviceTask = (TServiceTask) res;
                handleConnectorOnServiceTask(activity, serviceTask);
            } else if (child instanceof CallActivity) {
                /* Do the mapping */
                final CallActivity callActivity = (CallActivity) child;
                final TCallActivity ca = (TCallActivity) res;
                dataMappingWithCallActivity(callActivity, ca);
            }
        } else if (child instanceof Gateway) {
            res = createGateway((Gateway) child);
        } else if (child instanceof SubProcessEvent) {
            final TSubProcess eventSubProc = ModelFactory.eINSTANCE.createTSubProcess();
            eventSubProc.setTriggeredByEvent(true);
            //((SubProcessEvent) child).getElements()
            //errors.add(NLS.bind(Messages.subprocessReferenceLost, child.getName()));
            res = eventSubProc;
        }

        // Start Events
        else if (child instanceof StartTimerEvent) {
            final TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            final TTimerEventDefinition eventDef = ModelFactory.eINSTANCE.createTTimerEventDefinition();
            final Expression conditionExpression = ((StartTimerEvent) child).getCondition();
            if (conditionExpression != null) {
                final String condition = conditionExpression.getContent();//FIXME
                final TExpression expression = ModelFactory.eINSTANCE.createTExpression();
                expression.setId(ModelHelper.getEObjectID(expression));
                if (condition != null) {
                    if (DateUtil.isDuration(condition)) {
                        eventDef.setTimeDuration(expression);
                        FeatureMapUtil.addText(eventDef.getTimeDuration().getMixed(), condition);
                    } else if (DateUtil.isDate(condition)) {
                        eventDef.setTimeDate(expression);
                        FeatureMapUtil.addText(eventDef.getTimeDate().getMixed(), condition);
                    } else {
                        eventDef.setTimeCycle(expression);
                        FeatureMapUtil.addText(eventDef.getTimeCycle().getMixed(), condition);
                    }
                }
            }
            eventDef.setId("event-def" + child.getName());
            bpmnStart.getEventDefinition().add(eventDef);
            errors.add(Messages.bind(Messages.timerDefinitionNotExported, child.getName()));
            res = bpmnStart;
        } else if (child instanceof StartErrorEvent) {
            final StartErrorEvent bonitaSart = (StartErrorEvent) child;
            final TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            final TErrorEventDefinition eventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
            bpmnStart.getEventDefinition().add(eventDef);
            final String errorCode = bonitaSart.getErrorCode();
            final String eventDefId = errorCode != null ? errorCode + EcoreUtil.generateUUID() : EcoreUtil.generateUUID();
            eventDef.setId(eventDefId);
            if (errorCode != null) {
                eventDef.setErrorRef(QName.valueOf(errorCode));
            }
            res = bpmnStart;
        } else if (child instanceof StartMessageEvent) {
            final TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            final TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
            bpmnStart.getEventDefinition().add(eventDef);
            final String eventId = ((StartMessageEvent) child).getEvent() != null ? ((StartMessageEvent) child).getEvent() + EcoreUtil.generateUUID()
                    : EcoreUtil.generateUUID();
            eventDef.setId("event-def" + eventId);
            final EList<Operation> messageContent = ((StartMessageEvent) child).getMessageContent();
            if (messageContent.size() > 0) {
                //            	createOperation
                //            	eventDef.setOperationRef(value)
                //            	bpmnStart.
            }
            res = bpmnStart;
        } else if (child instanceof StartSignalEvent) {
            final TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            final TSignal tSignal = getOrCreateTSignal((SignalEvent) child);
            final TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
            if (tSignal != null) {
                eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
            }
            bpmnStart.getEventDefinition().add(eventDef);
            eventDef.setId(((StartSignalEvent) child).getName());
            res = bpmnStart;
        } else if (child instanceof StartEvent) {
            final TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            res = bpmnStart;
        }

        // End events
        else if (child instanceof EndErrorEvent) {
            final EndErrorEvent bonitaEnd = (EndErrorEvent) child;
            final TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            final TErrorEventDefinition eventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
            bpmnEnd.getEventDefinition().add(eventDef);
            final String errorCode = bonitaEnd.getErrorCode();
            final String eventDefId = errorCode != null ? errorCode + EcoreUtil.generateUUID() : EcoreUtil.generateUUID();
            eventDef.setId(eventDefId);
            if (errorCode != null) {
                eventDef.setErrorRef(QName.valueOf(errorCode));
            }
            res = bpmnEnd;
        } else if (child instanceof EndMessageEvent) {
            final TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            for (final Message eventObject : ((EndMessageEvent) child).getEvents()) {
                final TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
                //eventDef.setMessageRef(eventObject.get);
                //eventDef.setOperationRef(value);
                final String name = eventObject.getName() != null ? eventObject.getName() : EcoreUtil.generateUUID();
                eventDef.setId("event-def" + name);
                bpmnEnd.getEventDefinition().add(eventDef);
            }
            res = bpmnEnd;
        } else if (child instanceof EndSignalEvent) {
            final TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            createThrowSignalEventDefinition((EndSignalEvent) child, bpmnEnd);

            res = bpmnEnd;
        } else if (child instanceof EndTerminatedEvent) {
            final TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            final TTerminateEventDefinition eventDef = ModelFactory.eINSTANCE.createTTerminateEventDefinition();
            bpmnEnd.getEventDefinition().add(eventDef);
            eventDef.setId(EcoreUtil.generateUUID());
            res = bpmnEnd;
        } else if (child instanceof EndEvent) {
            final TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            res = bpmnEnd;
        }

        // Events
        else if (child instanceof IntermediateCatchMessageEvent) {
            final IntermediateCatchMessageEvent bonitaEvent = (IntermediateCatchMessageEvent) child;
            final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            final String event = bonitaEvent.getEvent();
            if (event != null) {
                bpmnEvent.getEventDefinitionRef().add(QName.valueOf(event));
            }
            res = bpmnEvent;
        } else if (child instanceof IntermediateThrowMessageEvent) {
            final IntermediateThrowMessageEvent bonitaEvent = (IntermediateThrowMessageEvent) child;
            final TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
            for (final Message bonitaEventDef : bonitaEvent.getEvents()) {
                final TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
                final String name = bonitaEventDef.getName();
                eventDef.setId(name);
                //                TMessage tMessage = createMessage(bonitaEventDef);
                //                eventDef.setMessageRef(QName.valueOf(tMessage.getId()));
                final String eventDefId = eventDef.getId();
                if (eventDefId != null) {
                    bpmnEvent.getEventDefinitionRef().add(QName.valueOf(eventDefId));
                }
                bpmnEvent.getEventDefinition().add(eventDef);
            }
            res = bpmnEvent;
        } else if (child instanceof IntermediateCatchSignalEvent) {
            final IntermediateCatchSignalEvent bonitaEvent = (IntermediateCatchSignalEvent) child;
            final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            final TSignal tSignal = getOrCreateTSignal(bonitaEvent);
            if (tSignal != null) {
                bpmnEvent.getEventDefinitionRef().add(QName.valueOf(tSignal.getId()));
            }
            res = bpmnEvent;
        } else if (child instanceof IntermediateThrowSignalEvent) {
            final IntermediateThrowSignalEvent bonitaEvent = (IntermediateThrowSignalEvent) child;
            final TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
            /* TSignalEventDefinition eventDef = */createThrowSignalEventDefinition(bonitaEvent, bpmnEvent);
            //bpmnEvent.getEventDefinition().add(eventDef);
            res = bpmnEvent;
        } else if (child instanceof CatchLinkEvent) {
            final CatchLinkEvent bonitaEvent = (CatchLinkEvent) child;
            final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            createTLinkEventDefinition(bonitaEvent, bpmnEvent);
            //			final EList<ThrowLinkEvent> fromLinkEvent = bonitaEvent.getFrom();
            //			for(ThrowLinkEvent from : fromLinkEvent){
            //				bpmnEvent.getEventDefinitionRef().add(QName.valueOf(ModelHelper.getEObjectID(from)));
            //			}
            res = bpmnEvent;
        } else if (child instanceof ThrowLinkEvent) {
            final ThrowLinkEvent bonitaEvent = (ThrowLinkEvent) child;
            final TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
            final TLinkEventDefinition eventDef = ModelFactory.eINSTANCE.createTLinkEventDefinition();
            final CatchLinkEvent to = bonitaEvent.getTo();
            if (to != null) {
                final String targetName = to.getName();
                eventDef.setId(EcoreUtil.generateUUID());
                eventDef.setName(to.getName() != null ? to.getName() : targetName);
                eventDef.setTarget(QName.valueOf(ModelHelper.getEObjectID(to)));
            } else {
                eventDef.setId(EcoreUtil.generateUUID());
            }
            bpmnEvent.getEventDefinition().add(eventDef);
            res = bpmnEvent;
        } else if (child instanceof IntermediateCatchTimerEvent) {
            final IntermediateCatchTimerEvent bonitaEvent = (IntermediateCatchTimerEvent) child;
            final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            final TTimerEventDefinition eventDef = createTimerEventDef(bonitaEvent);
            bpmnEvent.getEventDefinition().add(eventDef);
            res = bpmnEvent;
        } else {
            errors.add(NLS.bind(Messages.defaultMappingToActivity, child.getName()));
            final TActivity bpmnActivity = ModelFactory.eINSTANCE.createTTask();
            setCommonAttributes(child, bpmnActivity);
            res = bpmnActivity;
        }
        setCommonAttributes(child, res);
        return res;
    }

    protected void dataMappingWithCallActivity(final CallActivity callActivity,
            final TCallActivity ca) {
        final TDataInputAssociation dia = ModelFactory.eINSTANCE.createTDataInputAssociation();
        dia.setId(EcoreUtil.generateUUID());
        ca.getDataInputAssociation().add(dia);
        for (final InputMapping im : callActivity.getInputMappings()) {
            final TAssignment inputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            final Expression processSource = im.getProcessSource();
            if (processSource != null) {
                inputAssignment.setFrom(createBPMNExpressionFromString(processSource.getName()));
                final String dataTo = getDataReferenceValue(callActivity, im.getSubprocessTarget());
                inputAssignment.setTo(createBPMNExpressionFromString(dataTo));//FIXME: I think we need to search the real targeted data to find the correct id
                dia.getAssignment().add(inputAssignment);
            }
        }
        final TDataOutputAssociation doa = ModelFactory.eINSTANCE.createTDataOutputAssociation();
        doa.setId(EcoreUtil.generateUUID());
        ca.getDataOutputAssociation().add(doa);
        for (final OutputMapping om : callActivity.getOutputMappings()) {
            final TAssignment outputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            final String dataFrom = getDataReferenceValue(callActivity, om.getSubprocessSource());
            outputAssignment.setFrom(createBPMNExpressionFromString(dataFrom));//FIXME: I think we need to search the real targeted data to find the correct id
            final Data processTarget = om.getProcessTarget();
            if (processTarget != null) {
                final TItemDefinition dataTo = dataScope.get(processTarget);
                outputAssignment.setTo(createBPMNExpressionFromString(dataTo != null ? dataTo.getId() : processTarget.getName()));
                doa.getAssignment().add(outputAssignment);
            }
        }
    }

    protected void handleConnectorOnServiceTask(final Activity activity, final TServiceTask serviceTask) {
        final EList<Connector> connectors = activity.getConnectors();
        if (!connectors.isEmpty()) {
            final Connector connector = connectors.get(0);
            handleBonitaConnectorDefinition(connector.getDefinitionId());
            /*
             * Service task should be used with a connector,
             * this connector will be the bpmn2 operation used
             * /!\BEGIN TO HANDLE A SINGLE OPERATION
             */
            serviceTask.setImplementation("BonitaConnector");
            serviceTask.setOperationRef(QName.valueOf("Exec" + connector.getDefinitionId()));

            final TDataInput dataInput = fillIOSpecificationWithNewDataInput(serviceTask,
                    QName.valueOf(generateConnectorInputItemDef(connector.getDefinitionId())));
            final TDataOutput dataOutput = fillIOSpecificationWithNewDataOutput(serviceTask,
                    QName.valueOf(generateConnectorOutputItemDef(connector.getDefinitionId())));

            final TDataInputAssociation tDataInputAssociation = ModelFactory.eINSTANCE.createTDataInputAssociation();

            final EList<TAssignment> inputAssignments = tDataInputAssociation.getAssignment();

            for (final ConnectorParameter cp : connector.getConfiguration().getParameters()) {
                final TAssignment inputAssignment = ModelFactory.eINSTANCE.createTAssignment();
                if (cp.getExpression() instanceof Expression
                        && ((Expression) cp.getExpression()).getContent() != null) {
                    inputAssignment.setFrom(createBPMNFormalExpressionFromBonitaExpression((Expression) cp.getExpression()));
                    inputAssignment.setTo(createBPMNExpressionFromString("getDataInput('" + dataInput.getId() + "')/"
                            + XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":" + cp.getKey()));
                    inputAssignments.add(inputAssignment);
                }
            }
            if (!tDataInputAssociation.getAssignment().isEmpty()) {
                serviceTask.getDataInputAssociation().add(tDataInputAssociation);
                tDataInputAssociation.setTargetRef(dataInput.getId());
                //tDataInputAssociation.getSourceRef().add(da);
            }

            final TDataOutputAssociation tDataOutputAssociation = ModelFactory.eINSTANCE.createTDataOutputAssociation();

            final EList<TAssignment> outputAssignments = tDataOutputAssociation.getAssignment();
            for (final Operation opm : connector.getOutputs()) {
                if (opm.getRightOperand().hasName() &&
                        opm.getRightOperand().hasContent()) {
                    final TAssignment outputAssignment = ModelFactory.eINSTANCE.createTAssignment();
                    if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(opm.getRightOperand().getType())) {
                        outputAssignment.setFrom(createBPMNExpressionFromString("getDataOutput('" + dataInput.getId() + "')/"
                                + XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":" + opm.getRightOperand().getName()));
                    } else {
                        outputAssignment.setFrom(createBPMNFormalExpressionFromBonitaExpression(opm.getRightOperand()));
                    }
                    if (opm.getLeftOperand().hasContent()) {
                        outputAssignment.setTo(createBPMNFormalExpressionFromBonitaExpression(opm.getLeftOperand()));
                    }
                    outputAssignments.add(outputAssignment);
                }
            }
            if (!tDataOutputAssociation.getAssignment().isEmpty()) {
                serviceTask.getDataOutputAssociation().add(tDataOutputAssociation);
                tDataOutputAssociation.setTargetRef(dataOutput.getId());
            }
        }
    }

    protected String getDataReferenceValue(final CallActivity callActivity, final String bonitaReferenceString) {
        String result = bonitaReferenceString;
        final DiagramRepositoryStore drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final Expression calledActivityName = callActivity.getCalledActivityName();
        if (calledActivityName != null
                && calledActivityName.getType().equals(ExpressionConstants.CONSTANT_TYPE)
                && calledActivityName.getContent() != null) {
            String version = null;
            final Expression calledActivityVersion = callActivity.getCalledActivityVersion();
            if (calledActivityVersion != null
                    && calledActivityVersion.getType().equals(ExpressionConstants.CONSTANT_TYPE)
                    && calledActivityVersion.getContent() != null) {
                version = calledActivityVersion.getContent();
            }
            final AbstractProcess calledProcess = drs.findProcess(calledActivityName.getContent(), version);
            if (calledProcess != null) {
                for (final Data calledProcessData : calledProcess.getData()) {
                    if (calledProcessData.getName().equals(bonitaReferenceString)) {
                        result = ModelHelper.getEObjectID(calledProcessData);//it will be the id of the targeted date
                        break;
                    }
                }
            }
        }
        return result;
    }

    private TLinkEventDefinition createTLinkEventDefinition(final CatchLinkEvent bonitaEvent,
            final TIntermediateCatchEvent bpmnEvent) {
        final TLinkEventDefinition eventDef = ModelFactory.eINSTANCE.createTLinkEventDefinition();
        eventDef.setId(EcoreUtil.generateUUID());
        final EList<ThrowLinkEvent> fromLinkEvent = bonitaEvent.getFrom();
        final EList<QName> sourceLink = eventDef.getSource();
        for (final ThrowLinkEvent from : fromLinkEvent) {
            sourceLink.add(QName.valueOf(ModelHelper.getEObjectID(from)));
        }
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    protected TSignalEventDefinition createThrowSignalEventDefinition(final SignalEvent bonitaEvent, final TThrowEvent bpmnEvent) {
        final TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
        eventDef.setId(EcoreUtil.generateUUID());
        final TSignal tSignal = getOrCreateTSignal(bonitaEvent);
        if (tSignal != null) {
            eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
        }
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    protected TTimerEventDefinition createTimerEventDef(final AbstractTimerEvent bonitaEvent) {
        final TTimerEventDefinition eventDef = ModelFactory.eINSTANCE.createTTimerEventDefinition();
        final Expression conditionExpression = bonitaEvent.getCondition();
        if (conditionExpression != null) {
            final String condition = conditionExpression.getContent();//FIXME
            if (condition != null) {
                final TExpression expression = ModelFactory.eINSTANCE.createTExpression();
                FeatureMapUtil.addText(expression.getMixed(), condition);
                if (DateUtil.isDuration(condition)) {
                    eventDef.setTimeDuration(expression);
                } else if (DateUtil.isDate(condition)) {
                    eventDef.setTimeDate(expression);
                } else {
                    eventDef.setTimeCycle(expression);
                }
            }
        }
        eventDef.setId("eventdef-" + bonitaEvent.getName());
        return eventDef;
    }

    protected TFlowElement createGateway(final FlowElement child) {
        TFlowElement res = null;
        if (child instanceof XORGateway) {
            final TExclusiveGateway bpmnGateway = ModelFactory.eINSTANCE.createTExclusiveGateway();
            res = bpmnGateway;
        } else if (child instanceof ANDGateway) {
            final TGateway bpmnGateway = ModelFactory.eINSTANCE.createTParallelGateway();
            res = bpmnGateway;
        } else if (child instanceof InclusiveGateway) {
            final TInclusiveGateway bpmnGateway = ModelFactory.eINSTANCE.createTInclusiveGateway();
            res = bpmnGateway;
        }
        return res;
    }

    protected TFlowElement createActivity(final FlowElement child) {
        // Tasks
        TFlowElement res = null;
        if (child instanceof CallActivity) {
            //WARNING in fact this is a call activity
            //TSubProcess bpmnSubprocess = ModelFactory.eINSTANCE.createTSubProcess();
            final TCallActivity tCallActivity = ModelFactory.eINSTANCE.createTCallActivity();
            //TODO: construct calledElement ID
            final CallActivity cActivity = (CallActivity) child;
            final Expression calledActivityName = cActivity.getCalledActivityName();
            if (calledActivityName != null
                    && ExpressionConstants.CONSTANT_TYPE.equals(calledActivityName.getType())
                    && calledActivityName.getContent() != null) {
                final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
                final Expression calledVersion = cActivity.getCalledActivityVersion();
                String version = null;
                if (calledVersion != null && calledVersion.getContent() != null && !calledVersion.getContent().isEmpty()) {
                    version = calledVersion.getContent();
                }
                final AbstractProcess calledProcess = ModelHelper.findProcess(calledActivityName.getContent(), version, diagramStore.getAllProcesses());
                if (calledProcess != null) {
                    tCallActivity.setCalledElement(QName.valueOf(ModelHelper.getEObjectID(calledProcess)));
                }
            }
            //bpmnSubprocess.set
            //errors.add(NLS.bind(Messages.subprocessReferenceLost, child.getName()));
            res = tCallActivity;
        } else if (child instanceof ServiceTask) {
            final TServiceTask serviceTask = ModelFactory.eINSTANCE.createTServiceTask();
            res = serviceTask;
        } else if (child instanceof ScriptTask) {
            final TScriptTask scriptTask = ModelFactory.eINSTANCE.createTScriptTask();
            res = scriptTask;
        } else if (child instanceof Task) {
            final Task bonitaTask = (Task) child;
            final TUserTask bpmnTask = ModelFactory.eINSTANCE.createTUserTask();
            final Actor actor = bonitaTask.getActor();
            if (actor != null) {
                final EList<TResourceRole> resourceRoles = bpmnTask.getResourceRole();
                final TPerformer role = ModelFactory.eINSTANCE.createTPerformer();
                role.setResourceRef(QName.valueOf(ModelHelper.getEObjectID(actor)));
                role.setId(EcoreUtil.generateUUID());
                resourceRoles.add(role);
                //TODO: check that a performer is the well thing to use search in the whole inheritance of ResourceRole
                //TODO: use assignment if specifying a name
                //TODO:
            }
            // TODO performer
            res = bpmnTask;
        } else if (child instanceof SendTask) {
            final TSendTask scriptTask = ModelFactory.eINSTANCE.createTSendTask();
            res = scriptTask;
        } else if (child instanceof ReceiveTask) {
            final TReceiveTask scriptTask = ModelFactory.eINSTANCE.createTReceiveTask();
            res = scriptTask;
        } else if (child instanceof Activity) {
            final TActivity bpmnActivity = ModelFactory.eINSTANCE.createTTask();
            res = bpmnActivity;
        }
        if (child instanceof MultiInstantiable && res instanceof TActivity) {
            handleMultiInstance((MultiInstantiable) child, (TActivity) res);
        }
        return res;
    }

    protected void handleMultiInstance(final MultiInstantiable multiInstantiable, final TActivity res) {
        final MultiInstanceType multiInstanceType = multiInstantiable.getType();
        TLoopCharacteristics loopCharacteristics = null;
        if (MultiInstanceType.STANDARD.equals(multiInstanceType)) {
            loopCharacteristics = ModelFactory.eINSTANCE.createTStandardLoopCharacteristics();
            ((TStandardLoopCharacteristics) loopCharacteristics).setTestBefore(multiInstantiable.getTestBefore());
            final Expression loopCondition = multiInstantiable.getLoopCondition();
            if (loopCondition != null) {
                ((TStandardLoopCharacteristics) loopCharacteristics).setLoopCondition(createBPMNFormalExpressionFromBonitaExpression(loopCondition));
            }
            final Expression loopMaximum = multiInstantiable.getLoopMaximum();
            if (loopMaximum != null && ExpressionConstants.CONSTANT_TYPE.equals(loopMaximum.getType()) && loopMaximum.getContent() != null
                    && !loopMaximum.getContent().isEmpty()) {
                ((TStandardLoopCharacteristics) loopCharacteristics).setLoopMaximum(new BigInteger(loopMaximum.getContent()));
            }
        } else if (MultiInstanceType.PARALLEL.equals(multiInstanceType) || MultiInstanceType.SEQUENTIAL.equals(multiInstanceType)) {
            loopCharacteristics = ModelFactory.eINSTANCE.createTMultiInstanceLoopCharacteristics();
            ((TMultiInstanceLoopCharacteristics) loopCharacteristics).setIsSequential(MultiInstanceType.SEQUENTIAL.equals(multiInstanceType));
            final Expression completionCondition = multiInstantiable.getCompletionCondition();
            if (completionCondition != null) {
                ((TMultiInstanceLoopCharacteristics) loopCharacteristics)
                        .setCompletionCondition(createBPMNFormalExpressionFromBonitaExpression(completionCondition));
            }
            final Expression cardinalityExpression = multiInstantiable.getCardinalityExpression();
            if (cardinalityExpression != null) {
                ((TMultiInstanceLoopCharacteristics) loopCharacteristics)
                        .setLoopCardinality(createBPMNFormalExpressionFromBonitaExpression(cardinalityExpression));
            }
        }
        if (loopCharacteristics != null) {
            loopCharacteristics.setId(EcoreUtil.generateUUID());
            res.setLoopCharacteristics(loopCharacteristics);
        }
    }

    /**
     * @return
     */
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getErrorMessage() {
        return createErrorMessage(getErrors());
    }

    /**
     * @param errors
     * @return
     */
    private String createErrorMessage(final List<String> errors) {
        final StringBuilder builder = new StringBuilder();
        builder.append(Messages.exportLimitations_message);
        builder.append(SWT.CR);
        for (final String error : errors) {
            builder.append(SWT.CR + " * ");
            builder.append(error);
        }
        return builder.toString();
    }

}
