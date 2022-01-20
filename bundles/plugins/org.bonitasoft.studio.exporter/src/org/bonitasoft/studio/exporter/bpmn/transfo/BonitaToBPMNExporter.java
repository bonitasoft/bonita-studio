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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.exporter.bpmn.transfo.data.DataScope;
import org.bonitasoft.studio.exporter.bpmn.transfo.data.ItemDefinitionFunction;
import org.bonitasoft.studio.exporter.bpmn.transfo.data.XMLNamespaceResolver;
import org.bonitasoft.studio.exporter.bpmn.transfo.expression.FormalExpressionFunctionFactory;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.omg.spec.bpmn.di.BPMNEdge;
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
import org.omg.spec.bpmn.model.TCatchEvent;
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
import org.omg.spec.bpmn.model.TResourceRole;
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

@Creatable
public class BonitaToBPMNExporter {

    private static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd/HH/mm/ss";
    private static final String DISPLAY_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final String XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION = "bonitaConnector";
    private static final String JAVA_XMLNS = "java";
    private final List<String> errors = new ArrayList<>();
    private final Map<Element, TFlowElement> mapping = new HashMap<>();
    private final Map<Actor, TParticipant> participantMapping = new HashMap<>();
    private BPMNPlane bpmnPlane;
    private TCollaboration collaboration;
    private final Map<String, TSignal> signalCodeTSignal = new HashMap<>();
    private final Set<TSignal> tSignals = new HashSet<>();
    private TDefinitions definitions;
    private DataScope dataScope;
    private DocumentRoot root;
    private File destBpmnFile;
    private XMLNamespaceResolver xmlNamespaceResolver;
    private final FormalExpressionFunctionFactory formalExpressionTransformerFactory = new FormalExpressionFunctionFactory();
    private IModelSearch modelSearch;
    private MultiStatus status;
    private ConnectorTransformationXSLProvider connectorXSLProvider;

    public BonitaToBPMNExporter() {
        errors.add("Forms and other resources are not exported.");
    }

    public void export(final IBonitaModelExporter modelExporter,
            IModelSearch modelSearch,
            final File destFile,
            ConnectorTransformationXSLProvider connectorXSLProvider,
            String currentVersion) {
        this.modelSearch = modelSearch;
        this.connectorXSLProvider = connectorXSLProvider;
        final MainProcess mainProcess = modelExporter.getMainProcess();

        initializeDocumentRoot();

        definitions = ModelFactory.eINSTANCE.createTDefinitions();
        definitions.setExpressionLanguage("http://groovy.apache.org/");
        collaboration = ModelFactory.eINSTANCE.createTCollaboration();
        setCommonAttributes(mainProcess, collaboration);
        definitions.getRootElement().add(collaboration);
        var bpmnDiagram = DiFactory.eINSTANCE.createBPMNDiagram();
        bpmnDiagram.setName(mainProcess.getName());
        bpmnPlane = DiFactory.eINSTANCE.createBPMNPlane();
        bpmnDiagram.setBPMNPlane(bpmnPlane);
        final QName rootQNameIDValue = QName.valueOf(collaboration.getId());
        bpmnPlane.setBpmnElement(rootQNameIDValue);
        bpmnPlane.setId("plane_" + collaboration.getId());
        definitions.getBPMNDiagram().add(bpmnDiagram);
        definitions.setTargetNamespace("http://bonitasoft.com/" + rootQNameIDValue);
        definitions.setExporter("BonitaSoft");
        definitions.setExporterVersion(currentVersion);

        /* Handle Bonita connector */
        destBpmnFile = destFile;

        configureNamespaces();
        dataScope = new DataScope(new ItemDefinitionFunction(definitions, xmlNamespaceResolver, modelSearch));

        BPMNShapeFactory shapeFactory = new BPMNShapeFactory(modelExporter, bpmnDiagram);
        modelExporter.getPools().stream()
                .forEach(pool -> processPool(shapeFactory, pool, definitions, collaboration, modelSearch));

        populateWithMessageFlow(mainProcess);
        populateWithSignals(definitions);

        root.setDefinitions(definitions);
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".bpmn",
                new ModelResourceFactoryImpl());
        if (destFile.exists()) {
            destFile.delete();
        }
        Resource resource = new org.omg.spec.bpmn.di.util.DiResourceFactoryImpl()
                .createResource(URI.createFileURI(destFile.getAbsolutePath()));
        resourceSet.getResources().add(resource);
        resource.getContents().add(root);
        try {
            final Map<Object, Object> saveOptions = new HashMap<>();
            saveOptions.put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));
            saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(saveOptions);

            status = createOKStatus();
        } catch (final Exception ex) {
            status = createErrorStatus(ex);
        } finally {
            resource.unload();
        }
    }

    private void configureNamespaces() {
        root.getXMLNSPrefixMap().put(JAVA_XMLNS, "http://jcp.org/en/jsr/detail?id=270");
        root.getXMLNSPrefixMap().put(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION,
                "http://www.bonitasoft.org/studio/connector/definition/6.0");
        /* Two lines only for Bruce validation tools conformance... but it currently doesn't work either... */
        root.getXMLNSPrefixMap().put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.getXSISchemaLocation().put("schemaLocation",
                "http://www.omg.org/spec/BPMN/20100524/MODEL schemas/BPMN20.xsd");//http://www.omg.org/spec/BPMN/20100501/BPMN20.xsd
    }

    private void initializeDocumentRoot() {
        root = ModelFactory.eINSTANCE.createDocumentRoot();
        xmlNamespaceResolver = new XMLNamespaceResolver(root);
    }

    private void populateWithMessageFlow(final MainProcess mainProcess) {
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

    private void handleBonitaConnectorDefinition(final String connectorDefId, IModelSearch modelSearch) {
        try {
            File connectorDefFile = createXSDForConnectorDef(connectorDefId, modelSearch);
            addConnectorDefInXsdIfNotYetIncluded(connectorDefFile);
        } catch (URISyntaxException | IOException | TransformerException e) {
            throw new RuntimeException("Failed to create xsd for connector.", e);
        }

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

    private TMessage createConnectorDefOutput(final String connectorDefId) {
        final TItemDefinition tItemDefinitionBonitaConnectorOutput = ModelFactory.eINSTANCE.createTItemDefinition();
        tItemDefinitionBonitaConnectorOutput
                .setStructureRef(
                        QName.valueOf(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":" + connectorDefId
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

    private TMessage createConnectorDefInput(final String connectorDefId) {
        final TItemDefinition tItemDefinitionBonitaConnectorInput = ModelFactory.eINSTANCE.createTItemDefinition();
        tItemDefinitionBonitaConnectorInput
                .setStructureRef(
                        QName.valueOf(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":" + connectorDefId
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

    private File createXSDForConnectorDef(final String connectorDefId, IModelSearch modelSearch)
            throws URISyntaxException, IOException, TransformerException {
        final File connectorDefFolder = new File(
                destBpmnFile.getParentFile().getAbsolutePath() + File.separator + "connectorDefs");
        /* Export the xsd */
        if (!connectorDefFolder.exists()) {
            connectorDefFolder.mkdirs();
        }
        Optional<File> connectorDefFile = modelSearch.getConnectorDefinitions().stream()
                .filter(def -> Objects.equals(def.getId(), connectorDefId))
                .map(def -> {
                    try {
                        return createTmpDefinitionFile(def);
                    } catch (IOException e) {
                        errors.add(e.getMessage());
                        return null;
                    }
                })
                .findFirst();
        if (connectorDefFile.isPresent()) {
            generateXSDForConnector(connectorDefFile.get());
        } else {
            errors.add("The connector with id " + connectorDefId + " was not found.");
        }
        return connectorDefFile.orElse(null);
    }

    private File createTmpDefinitionFile(ConnectorDefinition definition) throws IOException {
        final Map<String, String> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_XML_VERSION, "1.0");
        Path tmpFile = Files.createTempFile(definition.getId(), ".def");
        org.bonitasoft.studio.connector.model.definition.DocumentRoot root = (org.bonitasoft.studio.connector.model.definition.DocumentRoot) definition
                .eResource().getContents().get(0);
        root.getXMLNSPrefixMap().clear();
        try (OutputStream os = Files.newOutputStream(tmpFile)) {
            definition.eResource().save(os, options);
        }
        return tmpFile.toFile();
    }

    private void addConnectorDefInXsdIfNotYetIncluded(final File connectorDefFile) {
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
                tImportBonitaConnector.setNamespace("http://www.bonitasoft.org/studio/connector/definition/6.1");
                definitions.getImport().add(tImportBonitaConnector);
            }
        }
    }

    private String generateConnectorInputItemDef(final String connectorDefId) {
        return connectorDefId + "ConnectorInput";
    }

    private String generateConnectorOutputItemDef(final String connectorDefId) {
        return connectorDefId + "ConnectorOutput";
    }

    private static Templates xslTemplate = null;

    private void generateXSDForConnector(final File connectorToTransformWC)
            throws IOException, TransformerException {
        if (xslTemplate == null) {
            final TransformerFactory transFact = TransformerFactory.newInstance();
            final File xsltFileoriginal = connectorXSLProvider.getConnectorXSLFile();
            final Source xsltSource = new StreamSource(xsltFileoriginal);
            xslTemplate = transFact.newTemplates(xsltSource);
        }

        //FIXME: this is only a workaround because currently we can't serialize the xml file in a way that both EMF and xslt can handle it correctly
        // see http://java.dzone.com/articles/emf-reading-model-xml-%E2%80%93-how
        var content = Files.readString(connectorToTransformWC.toPath(), StandardCharsets.UTF_8);
        content = content.replaceAll("xmlns:definition=\"http://www.bonitasoft.org/ns/connector/definition/6.1\"",
                "xmlns=\"http://www.bonitasoft.org/ns/connector/definition/6.1\" xmlns:definition=\"http://www.bonitasoft.org/ns/connector/definition/6.1\"");
        Files.writeString(connectorToTransformWC.toPath(), content);

        final Source xmlSource = new StreamSource(connectorToTransformWC);

        final String generatedXsdName = connectorToTransformWC.getName() + "connectors.xsd";
        final File connectorsDefFolder = new File(
                destBpmnFile.getParentFile().getAbsolutePath() + File.separator + "connectorDefs");
        if (!connectorsDefFolder.exists()) {
            connectorsDefFolder.mkdirs();
        }
        try (final OutputStream stream = new FileOutputStream(
                new File(connectorsDefFolder.getAbsolutePath(), generatedXsdName))) {
            final Result result = new StreamResult(stream);
            final Transformer transformer = xslTemplate.newTransformer();
            transformer.setParameter("indent", true);
            transformer.transform(xmlSource, result);
        } finally {
            Files.delete(connectorToTransformWC.toPath());
        }
    }

    private void processPool(BPMNShapeFactory shapeFactory, Pool pool, TDefinitions definitions,
            final TCollaboration collaboration, IModelSearch modelSearch) {
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
        BPMNShape processShape = shapeFactory.createPool(participant.getId(), pool);
        bpmnPlane.getDiagramElement().add(processShape);

        populateWithData(pool, bpmnProcess);//create data before in order to have them accessible after
        populateWithMessage(pool, bpmnProcess);
        populate(shapeFactory, pool, modelSearch.getAllItemsOfType(pool, Lane.class), bpmnProcess,
                processShape.getBounds(), modelSearch);
        populateWithSequenceFlow(shapeFactory, pool, bpmnProcess);
        populateWithTextAnnotation(shapeFactory, pool, bpmnProcess, processShape.getBounds());

    }

    private void populateWithTextAnnotation(final BPMNShapeFactory shapeFactory, final Pool pool,
            final TProcess bpmnProcess, Bounds processBounds) {
        bpmnProcess.getArtifact()
                .addAll(modelSearch.getAllItemsOfType(pool, TextAnnotation.class).stream().map(source -> {
                    final TTextAnnotation annotation = ModelFactory.eINSTANCE.createTTextAnnotation();
                    final TText text = ModelFactory.eINSTANCE.createTText();
                    text.getMixed().add(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, source.getText());
                    annotation.setText(text);
                    annotation.setId(EcoreUtil.generateUUID());
                    final List<TextAnnotationAttachment> textAnnotationAttachments = modelSearch.getAllItemsOfType(
                            pool,
                            TextAnnotationAttachment.class);
                    EObject eContainer = source.eContainer() instanceof SubProcessEvent ? source.eContainer().eContainer() : source.eContainer();
                    var containerId = modelSearch.getEObjectID(eContainer);
                    var containerBounds = bpmnPlane.getDiagramElement().stream()
                            .filter(BPMNShape.class::isInstance)
                            .map(BPMNShape.class::cast)
                            .filter(shape -> Objects.equals(shape.getBpmnElement().getLocalPart(), containerId))
                            .findFirst()
                            .map(BPMNShape::getBounds)
                            .orElseGet(() -> processBounds);

                    BPMNShape elementShape = shapeFactory.create(source, annotation.getId(), containerBounds);
                    bpmnPlane.getDiagramElement().add(elementShape);

                    for (final TextAnnotationAttachment attachement : textAnnotationAttachments) {
                        if (Objects.equals(attachement.getSource(), source)) {
                            final TFlowElement tFlowElement = mapping.get(attachement.getTarget());
                            if (tFlowElement != null && Strings.hasText(tFlowElement.getId())) {
                                final TAssociation association = ModelFactory.eINSTANCE.createTAssociation();
                                association.setId(EcoreUtil.generateUUID());
                                association.setSourceRef(QName.valueOf(annotation.getId()));
                                association
                                        .setTargetRef(QName.valueOf(mapping.get(attachement.getTarget()).getId()));
                                bpmnProcess.getArtifact().add(association);

                                final BPMNEdge edge = shapeFactory.createBPMNEdge(association.getId(), attachement);
                                if (edge != null) {
                                    bpmnPlane.getDiagramElement().add(edge);
                                }
                            }
                        }
                    }
                    return annotation;

                }).collect(Collectors.toList()));
    }

    private void populateWithMessage(final Pool pool, final TProcess bpmnProcess) {
        final List<ThrowMessageEvent> thowMessageEvents = modelSearch.getAllItemsOfType(pool, ThrowMessageEvent.class);
        for (final ThrowMessageEvent throwMessageEvent : thowMessageEvents) {
            for (final Message message : throwMessageEvent.getEvents()) {
                final TMessage tMessage = ModelFactory.eINSTANCE.createTMessage();
                tMessage.setId(EcoreUtil.getID(message));
            }
        }
    }

    private void populateWithSignals(final TDefinitions definitions) {
        definitions.getRootElement().addAll(tSignals);
    }

    private void populateWithData(final Pool pool, final TProcess bpmnProcess) {
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
                final TDataInputAssociation tDataInputAssociation = createDataInputAssociation(tDataInput,
                        defaultValue);
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

    private TDataInputAssociation createDataInputAssociation(final TDataInput tDataInput,
            final Expression defaultValue) {
        final TDataInputAssociation tDataInputAssociation = ModelFactory.eINSTANCE.createTDataInputAssociation();
        tDataInputAssociation.setId(EcoreUtil.generateUUID());
        tDataInputAssociation.setTargetRef(tDataInput.getId());
        final EList<TAssignment> assignment = tDataInputAssociation.getAssignment();
        final TAssignment tAssignment = ModelFactory.eINSTANCE.createTAssignment();

        final TFormalExpression toExpression = ModelFactory.eINSTANCE.createTFormalExpression();
        toExpression.setId(EcoreUtil.generateUUID());
        FeatureMapUtil.addText(toExpression.getMixed(), /* "getDataInput('"+ */tDataInput.getId()/* +"')" */);
        tAssignment.setTo(toExpression);

        final TFormalExpression fromExpression = convertExpression(defaultValue);
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
    private TDataInput fillIOSpecification(final TCallableElement callableElement,
            final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = callableElement.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            callableElement.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataInput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    private TDataInput fillIOSpecificationWithNewDataInput(final QName dataItemDefinitionIdAsQname,
            final TInputOutputSpecification tInputOutputAssociation) {
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

    private TDataOutput fillIOSpecificationWithNewDataOutput(final QName dataItemDefinitionIdAsQname,
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

    private TDataInput fillIOSpecificationWithNewDataInput(final TActivity tActivity,
            final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = tActivity.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            tActivity.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataInput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    private TDataOutput fillIOSpecificationWithNewDataOutput(final TServiceTask serviceTask,
            final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = serviceTask.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            serviceTask.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataOutput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    private void createDataObject(final TProcess bpmnProcess, final Data bonitaData,
            final QName dataItemDefinitionIdAsQname) {
        final TDataObject bpmnData = ModelFactory.eINSTANCE.createTDataObject();
        bpmnData.setItemSubjectRef(dataItemDefinitionIdAsQname);
        setCommonAttributes(bonitaData, bpmnData);
        bpmnData.setName(bonitaData.getName());
        bpmnData.setId("DataObject" + EcoreUtil.generateUUID() + bpmnData.getId());//avoid to have duplicate id for dataobject and itemDefinition
        bpmnProcess.getFlowElement().add(bpmnData);
        bpmnData.setIsCollection(bonitaData.isMultiple());
    }

    private TItemDefinition createDataItemDefinition(final Data bonitaData) {
        final TItemDefinition dataItemDefinition = ModelFactory.eINSTANCE.createTItemDefinition();
        setCommonAttributes(bonitaData, dataItemDefinition);
        dataItemDefinition.setStructureRef(getStructureRef(bonitaData));
        definitions.getRootElement().add(dataItemDefinition);
        return dataItemDefinition;
    }

    private TExpression createBPMNExpressionFromString(final String value) {
        final TExpression fromExpression = ModelFactory.eINSTANCE.createTExpression();
        fromExpression.setId(EcoreUtil.generateUUID());
        FeatureMapUtil.addText(fromExpression.getMixed(), value == null ? "" : value);
        return fromExpression;
    }

    private TFormalExpression convertExpression(final Expression bonitaExpression) {
        return formalExpressionTransformerFactory
                .newFormalExpressionFunction(dataScope, bonitaExpression.getType(), modelSearch)
                .apply(bonitaExpression);
    }

    private QName getStructureRef(final Data data) {
        if (data.getDataType() instanceof XMLType) {
            final String xmlnsDataType = xmlNamespaceResolver.resolveNamespacePrefix((XMLData) data);
            return QName.valueOf(xmlnsDataType + ":" + ((XMLData) data).getType());
        } else {
            final String technicalTypeFor = org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data);
            return QName.valueOf(JAVA_XMLNS + ":" + technicalTypeFor);
        }
    }

    private void populateWithSequenceFlow(final BPMNShapeFactory shapeFactory, Pool pool,
            final TProcess bpmnProcess) {
        modelSearch.getAllItemsOfType(pool, SequenceFlow.class).stream().forEach(bonitaFlow -> {
            final TSequenceFlow bpmnFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
            setCommonAttributes(bonitaFlow, bpmnFlow);
            if (bonitaFlow.getCondition() != null
                    && bonitaFlow.getCondition().hasContent()) {
                bpmnFlow.setConditionExpression(convertExpression(
                        bonitaFlow.getCondition()));
            }
            final TFlowElement source = mapping.get(bonitaFlow.getSource());
            if (source != null) {
                bpmnFlow.setSourceRef(source.getId());
                final TFlowElement target = mapping.get(bonitaFlow.getTarget());
                if (target != null) {
                    bpmnFlow.setTargetRef(target.getId());
                    bpmnProcess.getFlowElement().add(bpmnFlow);

                    // graphic
                    final BPMNEdge edge = shapeFactory.createBPMNEdge(bpmnFlow.getId(), bonitaFlow);
                    if (edge != null) {
                        bpmnPlane.getDiagramElement().add(edge);
                    }
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
                }
            }
        });
    }

    private void populate(BPMNShapeFactory shapeFactory, Pool pool, List<Lane> lanes, final TProcess bpmnProcess,
            Bounds poolBounds, IModelSearch modelSearch) {
        if (lanes.isEmpty()) {
            populateContainer(shapeFactory, pool, bpmnProcess, null, poolBounds, modelSearch);
        } else {
            TLaneSet laneSet = ModelFactory.eINSTANCE.createTLaneSet();
            laneSet.setId(Strings.slugify(bpmnProcess.getName()) + "_laneSet");
            bpmnProcess.getLaneSet().add(laneSet);
            lanes.stream().forEach(lane -> {
                final TLane bpmnLane = ModelFactory.eINSTANCE.createTLane();
                bpmnLane.setName(lane.getName());
                bpmnLane.setId(lane.getName());
                setCommonAttributes(lane, bpmnLane);
                laneSet.getLane().add(bpmnLane);
                // graphic
                final BPMNShape laneShape = shapeFactory.createLane(bpmnLane.getId(), lane, poolBounds);
                bpmnPlane.getDiagramElement().add(laneShape);
                populateContainer(shapeFactory, lane, bpmnProcess, bpmnLane, laneShape.getBounds(), modelSearch);
            });

        }
    }

    private void populateContainer(BPMNShapeFactory shapeFactory, EObject container, TProcess bpmnProcess,
            TLane bpmnParentLane, Bounds parentBounds, IModelSearch modelSearch) {
        final Map<Data, TItemDefinition> localDataMap = new HashMap<>();
        container.eAllContents().forEachRemaining(element -> {
            if (element instanceof FlowElement && Objects.equals(container, element.eContainer())) {
                final FlowElement bonitaElement = (FlowElement) element;
                // semantic
                final TFlowElement bpmnElement = createTFlowElement(bonitaElement, modelSearch);
                bpmnProcess.getFlowElement().add(bpmnElement);
                if (bpmnParentLane != null) {
                    bpmnParentLane.getFlowNodeRef().add(bpmnElement.getId());
                }
                mapping.put(bonitaElement, bpmnElement);

                populateDataOnActivity(bpmnProcess, localDataMap, bonitaElement, bpmnElement);

                // graphic
                BPMNShape elementShape = shapeFactory.create(bonitaElement, bpmnElement.getId(), parentBounds);
                bpmnPlane.getDiagramElement().add(elementShape);
                createBoundaries(shapeFactory, bpmnProcess, bonitaElement, bpmnElement, elementShape);
            } else if (element instanceof SubProcessEvent) {
                // semantic
                final SubProcessEvent subProcEvent = (SubProcessEvent) element;
                final TSubProcess bpmnSubProcess = (TSubProcess) createTFlowElement(subProcEvent, modelSearch);
                bpmnProcess.getFlowElement().add(bpmnSubProcess);
                if (bpmnParentLane != null) {
                    bpmnParentLane.getFlowNodeRef().add(bpmnSubProcess.getId());
                }
                mapping.put(subProcEvent, bpmnSubProcess);

                // graphic
                final BPMNShape elementShape = shapeFactory.create(subProcEvent, bpmnSubProcess.getId(), parentBounds);
                bpmnPlane.getDiagramElement().add(elementShape);

                populateContainer(shapeFactory, subProcEvent, bpmnSubProcess, parentBounds, modelSearch);
            }
        });
    }

    private void populateContainer(final BPMNShapeFactory shapeFactory,
            SubProcessEvent container,
            TSubProcess bpmnSubProcess,
            Bounds parentBounds, IModelSearch modelSearch) {
        container.eAllContents().forEachRemaining(element -> {
            if (element instanceof FlowElement) {
                final FlowElement bonitaElement = (FlowElement) element;
                // semantic
                final TFlowElement bpmnElement = createTFlowElement(bonitaElement, modelSearch);
                bpmnSubProcess.getFlowElement().add(bpmnElement);
                mapping.put(bonitaElement, bpmnElement);

                // graphic
                BPMNShape elementShape = shapeFactory.create(bonitaElement, bpmnElement.getId(), parentBounds);
                bpmnPlane.getDiagramElement().add(elementShape);
            }
        });
    }

    private void populateDataOnActivity(final TProcess bpmnProcess,
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
                final TDataInput tDataInput = fillIOSpecificationWithNewDataInput((TActivity) bpmnElement,
                        dataItemDefinitionIdAsQname);

                if (bonitaData.getDefaultValue() != null
                        && bonitaData.getDefaultValue().getName() != null) {
                    final List<TDataInputAssociation> dataInputAssociation = ((TActivity) bpmnElement)
                            .getDataInputAssociation();
                    dataInputAssociation.add(createDataInputAssociation(tDataInput, bonitaData.getDefaultValue()));
                }
            }
        }
    }

    private void createBoundaries(BPMNShapeFactory shapeFactory,
            TProcess bpmnProcess,
            final FlowElement bonitaElement,
            final TFlowElement bpmnElement,
            BPMNShape parentShape) {
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
                final BPMNShape boundaryShape = shapeFactory.createBoundary(boundaryEvent, bpmnBoundary,
                        parentShape.getBounds());
                bpmnPlane.getDiagramElement().add(boundaryShape);
            }
        }
    }

    private TSignal getOrCreateTSignal(final SignalEvent signalEvent) {
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

    private void setCommonAttributes(final Element bonitaElement, final TBaseElement bpmnElement) {
        bpmnElement.setId(modelSearch.getEObjectID(bonitaElement));
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

    private TFlowElement createTFlowElement(final Element child, IModelSearch modelSearch) {
        TFlowElement res = null;
        if (child instanceof Activity) {
            res = createActivity((Activity) child, modelSearch);
            final Activity activity = (Activity) child;
            if (child instanceof ServiceTask
                    && !activity.getConnectors().isEmpty()) {
                final TServiceTask serviceTask = (TServiceTask) res;
                handleConnectorOnServiceTask(activity, serviceTask, modelSearch);
            } else if (child instanceof CallActivity) {
                /* Do the mapping */
                final CallActivity callActivity = (CallActivity) child;
                final TCallActivity ca = (TCallActivity) res;
                dataMappingWithCallActivity(callActivity, ca, modelSearch);
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
            if (conditionExpression != null && conditionExpression.hasContent()) {
                var condition = conditionExpression.getContent();
                final TExpression expression = ModelFactory.eINSTANCE.createTExpression();
                expression.setId(modelSearch.getEObjectID(expression));
                if (condition != null) {
                    if (isDuration(condition)) {
                        eventDef.setTimeDuration(expression);
                        FeatureMapUtil.addText(eventDef.getTimeDuration().getMixed(), condition);
                    } else if (isDate(condition)) {
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
            errors.add(String.format("Timer definition for event %s not exported", child.getName()));
            res = bpmnStart;
        } else if (child instanceof StartErrorEvent) {
            final StartErrorEvent bonitaSart = (StartErrorEvent) child;
            final TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            final TErrorEventDefinition eventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
            bpmnStart.getEventDefinition().add(eventDef);
            final String errorCode = bonitaSart.getErrorCode();
            final String eventDefId = errorCode != null ? errorCode + EcoreUtil.generateUUID()
                    : EcoreUtil.generateUUID();
            eventDef.setId(eventDefId);
            if (errorCode != null) {
                eventDef.setErrorRef(QName.valueOf(errorCode));
            }
            res = bpmnStart;
        } else if (child instanceof StartMessageEvent) {
            final TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            final TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
            bpmnStart.getEventDefinition().add(eventDef);
            final String eventId = ((StartMessageEvent) child).getEvent() != null
                    ? ((StartMessageEvent) child).getEvent() + EcoreUtil.generateUUID()
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
            final String eventDefId = errorCode != null ? errorCode + EcoreUtil.generateUUID()
                    : EcoreUtil.generateUUID();
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
            addSignalEventDefinition((SignalEvent) child, bpmnEnd);
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
                final String eventDefId = eventDef.getId();
                if (eventDefId != null) {
                    bpmnEvent.getEventDefinitionRef().add(QName.valueOf(eventDefId));
                }
                bpmnEvent.getEventDefinition().add(eventDef);
            }
            res = bpmnEvent;
        } else if (child instanceof IntermediateCatchSignalEvent) {
            final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            addSignalEventDefinition((SignalEvent) child, bpmnEvent);
            res = bpmnEvent;
        } else if (child instanceof IntermediateThrowSignalEvent) {
            final TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
            addSignalEventDefinition((SignalEvent) child, bpmnEvent);
            res = bpmnEvent;
        } else if (child instanceof CatchLinkEvent) {
            final CatchLinkEvent bonitaEvent = (CatchLinkEvent) child;
            final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            createTLinkEventDefinition(bonitaEvent, bpmnEvent);
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
                eventDef.setTarget(QName.valueOf(modelSearch.getEObjectID(to)));
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
            errors.add(String.format("Could not find a valuable replacement for %s. Put an activity instead.",
                    child.getName()));
            final TActivity bpmnActivity = ModelFactory.eINSTANCE.createTTask();
            setCommonAttributes(child, bpmnActivity);
            res = bpmnActivity;
        }
        setCommonAttributes(child, res);
        return res;
    }

    private void dataMappingWithCallActivity(final CallActivity callActivity,
            final TCallActivity ca, IModelSearch modelSearch) {
        final TDataInputAssociation dia = ModelFactory.eINSTANCE.createTDataInputAssociation();
        dia.setId(EcoreUtil.generateUUID());
        ca.getDataInputAssociation().add(dia);
        for (final InputMapping im : callActivity.getInputMappings()) {
            final TAssignment inputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            final Expression processSource = im.getProcessSource();
            if (processSource != null) {
                inputAssignment.setFrom(createBPMNExpressionFromString(processSource.getName()));
                final String dataTo = getDataReferenceValue(callActivity, im.getSubprocessTarget(), modelSearch);
                if (dataTo != null) {
                    inputAssignment.setTo(createBPMNExpressionFromString(dataTo));//FIXME: I think we need to search the real targeted data to find the correct id
                    dia.getAssignment().add(inputAssignment);
                }
            }
        }
        final TDataOutputAssociation doa = ModelFactory.eINSTANCE.createTDataOutputAssociation();
        doa.setId(EcoreUtil.generateUUID());
        ca.getDataOutputAssociation().add(doa);
        for (final OutputMapping om : callActivity.getOutputMappings()) {
            final TAssignment outputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            final String dataFrom = getDataReferenceValue(callActivity, om.getSubprocessSource(), modelSearch);
            outputAssignment.setFrom(createBPMNExpressionFromString(dataFrom));//FIXME: I think we need to search the real targeted data to find the correct id
            final Data processTarget = om.getProcessTarget();
            if (processTarget != null) {
                final TItemDefinition dataTo = dataScope.get(processTarget);
                outputAssignment
                        .setTo(createBPMNExpressionFromString(
                                dataTo != null ? dataTo.getId() : processTarget.getName()));
                doa.getAssignment().add(outputAssignment);
            }
        }
    }

    private void handleConnectorOnServiceTask(final Activity activity, final TServiceTask serviceTask,
            IModelSearch modelSearch) {
        final EList<Connector> connectors = activity.getConnectors();
        if (!connectors.isEmpty()) {
            final Connector connector = connectors.get(0);
            handleBonitaConnectorDefinition(connector.getDefinitionId(), modelSearch);
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
                    inputAssignment
                            .setFrom(convertExpression((Expression) cp.getExpression()));
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
                        outputAssignment
                                .setFrom(createBPMNExpressionFromString("getDataOutput('" + dataInput.getId() + "')/"
                                        + XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":"
                                        + opm.getRightOperand().getName()));
                    } else {
                        outputAssignment.setFrom(convertExpression(opm.getRightOperand()));
                    }
                    if (opm.getLeftOperand().hasContent()) {
                        outputAssignment.setTo(convertExpression(opm.getLeftOperand()));
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

    private String getDataReferenceValue(final CallActivity callActivity, final String bonitaReferenceString,
            IModelSearch modelSearch) {
        String result = bonitaReferenceString;
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
            final Optional<AbstractProcess> calledProcess = modelSearch.findProcess(calledActivityName.getContent(),
                    version);
            if (calledProcess.isPresent()) {
                for (final Data calledProcessData : calledProcess.get().getData()) {
                    if (calledProcessData.getName().equals(bonitaReferenceString)) {
                        result = modelSearch.getEObjectID(calledProcessData);//it will be the id of the targeted date
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
            sourceLink.add(QName.valueOf(modelSearch.getEObjectID(from)));
        }
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    private TSignalEventDefinition addSignalEventDefinition(final SignalEvent bonitaEvent,
            final TThrowEvent bpmnEvent) {
        final TSignalEventDefinition eventDef = createSignalEvent(bonitaEvent);
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    private TSignalEventDefinition createSignalEvent(final SignalEvent bonitaEvent) {
        final TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
        eventDef.setId(EcoreUtil.generateUUID());
        final TSignal tSignal = getOrCreateTSignal(bonitaEvent);
        if (tSignal != null) {
            eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
        }
        return eventDef;
    }

    private TSignalEventDefinition addSignalEventDefinition(final SignalEvent bonitaEvent,
            final TCatchEvent bpmnEvent) {
        final TSignalEventDefinition eventDef = createSignalEvent(bonitaEvent);
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    private TTimerEventDefinition createTimerEventDef(final AbstractTimerEvent bonitaEvent) {
        final TTimerEventDefinition eventDef = ModelFactory.eINSTANCE.createTTimerEventDefinition();
        final Expression conditionExpression = bonitaEvent.getCondition();
        if (conditionExpression != null && conditionExpression.hasContent()) {
            var condition = conditionExpression.getContent();
            final TExpression expression = ModelFactory.eINSTANCE.createTExpression();
            FeatureMapUtil.addText(expression.getMixed(), condition);
            if (isDuration(condition)) {
                eventDef.setTimeDuration(expression);
            } else if (isDate(condition)) {
                eventDef.setTimeDate(expression);
            } else {
                eventDef.setTimeCycle(expression);
            }
        }
        eventDef.setId("eventdef-" + bonitaEvent.getName());
        return eventDef;
    }

    private TFlowElement createGateway(final FlowElement child) {
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

    private TFlowElement createActivity(final FlowElement child, IModelSearch modelSearch) {
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
                final Expression calledVersion = cActivity.getCalledActivityVersion();
                String version = null;
                if (calledVersion != null && calledVersion.getContent() != null
                        && !calledVersion.getContent().isEmpty()) {
                    version = calledVersion.getContent();
                }
                final Optional<AbstractProcess> calledProcess = modelSearch.findProcess(calledActivityName.getContent(),
                        version);
                calledProcess.ifPresent(process -> tCallActivity
                        .setCalledElement(QName.valueOf(modelSearch.getEObjectID(process))));
            }
            res = tCallActivity;
        } else if (child instanceof ServiceTask) {
            res = ModelFactory.eINSTANCE.createTServiceTask();
        } else if (child instanceof ScriptTask) {
            res = ModelFactory.eINSTANCE.createTScriptTask();
        } else if (child instanceof Task) {
            final Task bonitaTask = (Task) child;
            final TUserTask bpmnTask = ModelFactory.eINSTANCE.createTUserTask();
            final Actor actor = bonitaTask.getActor();
            if (actor != null) {
                final EList<TResourceRole> resourceRoles = bpmnTask.getResourceRole();
                final TPerformer role = ModelFactory.eINSTANCE.createTPerformer();
                role.setResourceRef(QName.valueOf(modelSearch.getEObjectID(actor)));
                role.setId(EcoreUtil.generateUUID());
                resourceRoles.add(role);
                //TODO: check that a performer is the well thing to use search in the whole inheritance of ResourceRole
                //TODO: use assignment if specifying a name
                //TODO:
            }
            // TODO performer
            res = bpmnTask;
        } else if (child instanceof SendTask) {
            res = ModelFactory.eINSTANCE.createTSendTask();
        } else if (child instanceof ReceiveTask) {
            res = ModelFactory.eINSTANCE.createTReceiveTask();
        } else if (child instanceof Activity) {
            res = ModelFactory.eINSTANCE.createTTask();
        }
        if (child instanceof MultiInstantiable && res instanceof TActivity) {
            handleMultiInstance((MultiInstantiable) child, (TActivity) res);
        }
        return res;
    }

    private void handleMultiInstance(final MultiInstantiable multiInstantiable, final TActivity res) {
        final MultiInstanceType multiInstanceType = multiInstantiable.getType();
        TLoopCharacteristics loopCharacteristics = null;
        if (MultiInstanceType.STANDARD.equals(multiInstanceType)) {
            loopCharacteristics = ModelFactory.eINSTANCE.createTStandardLoopCharacteristics();
            ((TStandardLoopCharacteristics) loopCharacteristics).setTestBefore(multiInstantiable.getTestBefore());
            final Expression loopCondition = multiInstantiable.getLoopCondition();
            if (loopCondition != null) {
                ((TStandardLoopCharacteristics) loopCharacteristics)
                        .setLoopCondition(convertExpression(loopCondition));
            }
            final Expression loopMaximum = multiInstantiable.getLoopMaximum();
            if (loopMaximum != null && ExpressionConstants.CONSTANT_TYPE.equals(loopMaximum.getType())
                    && loopMaximum.getContent() != null
                    && !loopMaximum.getContent().isEmpty()) {
                ((TStandardLoopCharacteristics) loopCharacteristics)
                        .setLoopMaximum(new BigInteger(loopMaximum.getContent()));
            }
        } else if (MultiInstanceType.PARALLEL.equals(multiInstanceType)
                || MultiInstanceType.SEQUENTIAL.equals(multiInstanceType)) {
            loopCharacteristics = ModelFactory.eINSTANCE.createTMultiInstanceLoopCharacteristics();
            ((TMultiInstanceLoopCharacteristics) loopCharacteristics)
                    .setIsSequential(MultiInstanceType.SEQUENTIAL.equals(multiInstanceType));
            final Expression completionCondition = multiInstantiable.getCompletionCondition();
            if (completionCondition != null) {
                ((TMultiInstanceLoopCharacteristics) loopCharacteristics)
                        .setCompletionCondition(convertExpression(completionCondition));
            }
            final Expression cardinalityExpression = multiInstantiable.getCardinalityExpression();
            if (cardinalityExpression != null) {
                ((TMultiInstanceLoopCharacteristics) loopCharacteristics)
                        .setLoopCardinality(convertExpression(cardinalityExpression));
            }
        }
        if (loopCharacteristics != null) {
            loopCharacteristics.setId(EcoreUtil.generateUUID());
            res.setLoopCharacteristics(loopCharacteristics);
        }
    }

    private MultiStatus createErrorStatus(Throwable t) {
        MultiStatus status = new MultiStatus(BonitaToBPMNExporter.class, 0, null, null);
        status.add(new Status(IStatus.ERROR, BonitaToBPMNExporter.class, t.getMessage(), t));
        exportLimitations().stream().forEach(status::add);
        return status;
    }

    private MultiStatus createOKStatus() {
        MultiStatus status = new MultiStatus(BonitaToBPMNExporter.class, 0, null, null);
        exportLimitations().stream().forEach(status::add);
        return status;
    }

    private List<IStatus> exportLimitations() {
        final List<IStatus> result = new ArrayList<>();
        errors.stream().map(message -> new Status(IStatus.INFO, BonitaToBPMNExporter.class, message))
                .forEach(result::add);
        return result;
    }

    private static boolean isDate(String event) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.US);
        sdf.setLenient(false);
        boolean displayDate = true;
        boolean systemDate = true;

        try {
            sdf.parse(event);
        } catch (Exception e) {
            systemDate = false;
        }

        sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT, java.util.Locale.US);
        try {
            sdf.parse(event);
        } catch (Exception e) {
            displayDate = false;
        }

        return systemDate || displayDate;
    }

    private static boolean isDuration(String event) {
        long r = -1;
        try {
            r = Long.parseLong(event);
        } catch (NumberFormatException e) {
            return false;
        }

        return String.valueOf(r).equals(event);
    }

    public MultiStatus getStatus() {
        return status;
    }

}
