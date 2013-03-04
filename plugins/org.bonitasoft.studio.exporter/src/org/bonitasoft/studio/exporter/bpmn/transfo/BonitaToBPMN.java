/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.bpmn.transfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.model.process.XMLType;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventEditPart;
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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.omg.spec.bpmn.di.BPMNDiagram;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNPlane;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.DiFactory;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TAssignment;
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
import org.omg.spec.bpmn.model.TMessage;
import org.omg.spec.bpmn.model.TMessageEventDefinition;
import org.omg.spec.bpmn.model.TMessageFlow;
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
import org.omg.spec.bpmn.model.TStartEvent;
import org.omg.spec.bpmn.model.TSubProcess;
import org.omg.spec.bpmn.model.TTerminateEventDefinition;
import org.omg.spec.bpmn.model.TThrowEvent;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.omg.spec.bpmn.model.TUserTask;
import org.omg.spec.bpmn.model.util.ModelResourceFactoryImpl;
import org.omg.spec.dd.dc.Bounds;
import org.omg.spec.dd.dc.DcFactory;
import org.omg.spec.dd.di.Shape;


/**
 * @author Mickael Istria
 *
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
    private final Map<Data, TItemDefinition> dataMap = new HashMap<Data, TItemDefinition>();
    private DocumentRoot root;
    private File destBpmnFile;

    public BonitaToBPMN() {
        errors = new ArrayList<String>();
        errors.add(Messages.connectorsNotExported);
    }

    /**
     * @param part
     * @param destFile
     * @return if the operation has been successful
     */
    /* (non-Javadoc)
     * @see org.bonitasoft.studio.exporter.extension.IBonitaTransformer#transform(org.bonitasoft.studio.exporter.extension.IBonitaModelExporter, java.io.File, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public boolean transform(IBonitaModelExporter modelExporter, File destFile,IProgressMonitor monitor) {
        root = ModelFactory.eINSTANCE.createDocumentRoot();

        MainProcessEditPart part = modelExporter.getMainProcessEditPart() ;
        final MainProcess mainProcess = modelExporter.getDiagram() ;

        definitions = ModelFactory.eINSTANCE.createTDefinitions();
        definitions.setExpressionLanguage("http://groovy.codehaus.org/");
        collaboration = ModelFactory.eINSTANCE.createTCollaboration();
        setCommonAttributes(mainProcess, collaboration);
        definitions.getRootElement().add(collaboration);
        BPMNDiagram bpmnDiagram = DiFactory.eINSTANCE.createBPMNDiagram();
        bpmnDiagram.setName(mainProcess.getName());
        bpmnPlane = DiFactory.eINSTANCE.createBPMNPlane();
        bpmnDiagram.setBPMNPlane(bpmnPlane);
        final QName rootQNameIDValue = QName.valueOf(collaboration.getId());
        bpmnPlane.setBpmnElement(rootQNameIDValue);
        bpmnPlane.setId("plane_"+collaboration.getId());
        definitions.getBPMNDiagram().add(bpmnDiagram);
        definitions.setTargetNamespace("http://bonitasoft.com/"+rootQNameIDValue);
        definitions.setExporter("BonitaSoft");
        definitions.setExporterVersion(ProductVersion.CURRENT_VERSION);

        /*Handle Bonita connector*/
        destBpmnFile = destFile;
        //handleBonitaConnectorDefinition(destFile.getParentFile());
        //NO more sense? will create one xsd file on the fly for each required bpmn2 file

        for (Object childPart : part.getChildren()) {
            if (childPart instanceof PoolEditPart) {
                processPool((PoolEditPart)childPart, definitions, collaboration);
            }
        }

        populateWithMessageFlow(mainProcess);

        populateWithSignals(definitions);


        root.getXMLNSPrefixMap().put(JAVA_XMLNS, "http://jcp.org/en/jsr/detail?id=270");
        root.getXMLNSPrefixMap().put(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION, "http://www.bonitasoft.org/studio/connector/definition/6.0");
        /*Two lines only for Bruce validation tools conformance... but it currently doesn't work either...*/
        root.getXMLNSPrefixMap().put("xsi","http://www.w3.org/2001/XMLSchema-instance");
        root.getXSISchemaLocation().put("schemaLocation", "http://www.omg.org/spec/BPMN/20100524/MODEL schemas/BPMN20.xsd");//http://www.omg.org/spec/BPMN/20100501/BPMN20.xsd
        root.setDefinitions(definitions);
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".bpmn", new ModelResourceFactoryImpl());
        if (destFile.exists()) {
            destFile.delete();
        }
        Resource resource = resourceSet.createResource(URI.createFileURI(destFile.getAbsolutePath()), "org.omg.schema.spec.bpmn.content-type");
        resource.getContents().add(root);
        try {
            Map<Object,Object> saveOptions = new HashMap<Object,Object>();
            saveOptions.put( XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl( false ));
            saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(saveOptions);
        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
            return false;
        }
        return true;
    }

    protected void populateWithMessageFlow(final MainProcess mainProcess) {
        for (MessageFlow messageFlow : mainProcess.getMessageConnections()) {
            TMessageFlow bpmnMessageFlow = ModelFactory.eINSTANCE.createTMessageFlow();
            setCommonAttributes(messageFlow, bpmnMessageFlow);
            final TFlowElement source = mapping.get(messageFlow.getSource());
            bpmnMessageFlow.setSourceRef(QName.valueOf(source.getId()));
            final TFlowElement target = mapping.get(messageFlow.getTarget());
            bpmnMessageFlow.setTargetRef(QName.valueOf(target.getId()));
            collaboration.getMessageFlow().add(bpmnMessageFlow);
        }
    }

    protected void handleBonitaConnectorDefinition(String connectorDefId) {
        File connectorDefFile = createXSDForConnectorDef(connectorDefId);

        addConnectorDefInXsdIfNotYetIncluded(connectorDefFile);

        /*Handle input*/
        TMessage tMessageInputBonitaConnector = createConnectorDefInput(connectorDefId);
        /*handle output*/
        TMessage tMessageOutputBonitaConnector = createConnectorDefOutput(connectorDefId);
        /*Create interface with it operation*/
        TInterface tInterfaceBonitaConnector = ModelFactory.eINSTANCE.createTInterface();
        tInterfaceBonitaConnector.setName(connectorDefId+"_Bonita_Connector_Interface");
        tInterfaceBonitaConnector.setId(connectorDefId+"_Bonita_Connector_Interface");
        //tInterfaceBonitaConnector.setImplementationRef(QName.valueOf("BonitaConnector"));
        TOperation tOperationConnector = ModelFactory.eINSTANCE.createTOperation();
        tOperationConnector.setId("Exec"+connectorDefId);
        tOperationConnector.setName("Exec"+connectorDefId);
        tOperationConnector.setInMessageRef(QName.valueOf(tMessageInputBonitaConnector.getId()));
        tOperationConnector.setOutMessageRef(QName.valueOf(tMessageOutputBonitaConnector.getId()));
        tInterfaceBonitaConnector.getOperation().add(tOperationConnector);
        definitions.getRootElement().add(tInterfaceBonitaConnector);
    }

    protected TMessage createConnectorDefOutput(String connectorDefId) {
        TItemDefinition tItemDefinitionBonitaConnectorOutput = ModelFactory.eINSTANCE.createTItemDefinition();
        tItemDefinitionBonitaConnectorOutput.setStructureRef(QName.valueOf(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION+":"+connectorDefId+"OutputType"));
        final String itemDefConnectorOutput = generateConnectorOutputItemDef(connectorDefId);
        tItemDefinitionBonitaConnectorOutput.setId(itemDefConnectorOutput);
        definitions.getRootElement().add(tItemDefinitionBonitaConnectorOutput);
        TMessage tMessageOutputBonitaConnector =  ModelFactory.eINSTANCE.createTMessage();
        tMessageOutputBonitaConnector.setItemRef(QName.valueOf(itemDefConnectorOutput));
        tMessageOutputBonitaConnector.setId(connectorDefId+"ConnectorMessageOutput");
        definitions.getRootElement().add(tMessageOutputBonitaConnector);
        return tMessageOutputBonitaConnector;
    }

    protected TMessage createConnectorDefInput(String connectorDefId) {
        TItemDefinition tItemDefinitionBonitaConnectorInput = ModelFactory.eINSTANCE.createTItemDefinition();
        tItemDefinitionBonitaConnectorInput.setStructureRef(QName.valueOf(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION+":"+connectorDefId+"InputType"));
        final String itemDefConnectorInput = generateConnectorInputItemDef(connectorDefId);
        tItemDefinitionBonitaConnectorInput.setId(itemDefConnectorInput);
        definitions.getRootElement().add(tItemDefinitionBonitaConnectorInput);
        TMessage tMessageInputBonitaConnector =  ModelFactory.eINSTANCE.createTMessage();
        tMessageInputBonitaConnector.setItemRef(QName.valueOf(itemDefConnectorInput));
        tMessageInputBonitaConnector.setId(connectorDefId+"ConnectorMessageInput");
        definitions.getRootElement().add(tMessageInputBonitaConnector);
        return tMessageInputBonitaConnector;
    }

    protected File createXSDForConnectorDef(String connectorDefId) {
        File connectorDefFolder = new File(destBpmnFile.getParentFile().getAbsolutePath()+File.separator+"connectorDefs");
        /*Export the xsd*/
        if(!connectorDefFolder.exists()){
            connectorDefFolder.mkdirs();
        }
        File connectorDefFile = null;
        try {
            ConnectorDefRepositoryStore cdrs = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
            for(IRepositoryFileStore rfs : cdrs.getChildren()){
                ConnectorDefFileStore cdfs = (ConnectorDefFileStore)rfs;
                ConnectorDefinition cd = cdfs.getContent();
                if(cd.getId().equals(connectorDefId)){
                    connectorDefFile = new File(((ConnectorDefFileStore) rfs).getEMFResource().getURI().toFileString());
                    break;
                }
            }
            if(connectorDefFile != null){
            	generateXSDForConnector(connectorDefFile/*child.getResource().getFullPath().toFile()*/);
            } else {
            	errors.add("The connector with id "+ connectorDefId + " was not found.");
            }
        } catch (URISyntaxException e) {
            BonitaStudioLog.error(e);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        } catch (TransformerException e) {
            BonitaStudioLog.error(e);
        }
        return connectorDefFile;
    }

    protected void addConnectorDefInXsdIfNotYetIncluded(File connectorDefFile) {
    	if(connectorDefFile != null){
    		boolean alreadyImported =false;
    		final String locationImport = "connectorDefs/"+connectorDefFile.getName()+"connectors.xsd";
    		for(TImport imported : definitions.getImport()){
    			if(imported.getLocation().equals(locationImport)){
    				alreadyImported = true;
    				break;
    			}
    		}
    		if(!alreadyImported){
    			TImport tImportBonitaConnector = ModelFactory.eINSTANCE.createTImport();
    			tImportBonitaConnector.setImportType("http://www.w3.org/2001/XMLSchema");
    			tImportBonitaConnector.setLocation(locationImport);
    			tImportBonitaConnector.setNamespace("http://www.bonitasoft.org/studio/connector/definition/6.0");
    			definitions.getImport().add(tImportBonitaConnector);
    		}
    	}
    }

    protected String generateConnectorInputItemDef(String connectorDefId) {
        return connectorDefId+"ConnectorInput";
    }

    protected String generateConnectorOutputItemDef(String connectorDefId) {
        return connectorDefId+"ConnectorOutput";
    }


    private static Templates xslTemplate = null;

    private void generateXSDForConnector(File connectorToTransform) throws URISyntaxException, IOException, TransformerException {

        if(xslTemplate == null){
            TransformerFactory transFact = TransformerFactory.newInstance();
            URL xsltUrl = ConnectorPlugin.getDefault().getBundle().getEntry("transfo/genConnectorsXSD.xsl");
            File xsltFileoriginal = new File(FileLocator.toFileURL(xsltUrl).getFile());
            Source xsltSource = new StreamSource(xsltFileoriginal);
            xslTemplate = transFact.newTemplates(xsltSource);
        }

        //FIXME: this is only a workaround because currently we can't serialize the xml file in a way that both EMF and xslt can handle it correctly
        // see http://java.dzone.com/articles/emf-reading-model-xml-%E2%80%93-how
        File connectorToTransformWC = File.createTempFile(connectorToTransform.getName(), "");
        FileUtil.copy(connectorToTransform, connectorToTransformWC);
        FileUtil.replaceStringInFile(connectorToTransformWC,
                "xmlns:definition=\"http://www.bonitasoft.org/ns/connector/definition/6.0\"",
                "xmlns=\"http://www.bonitasoft.org/ns/connector/definition/6.0\" xmlns:definition=\"http://www.bonitasoft.org/ns/connector/definition/6.0\"");


        Source xmlSource = new StreamSource(connectorToTransformWC);

        final String generatedXsdName = connectorToTransform.getName()+"connectors.xsd";
        final File connectorsDefFolder = new File(destBpmnFile.getParentFile().getAbsolutePath() + File.separator + "connectorDefs");
        if(!connectorsDefFolder.exists()){
            connectorsDefFolder.mkdirs();
        }
        OutputStream stream = new FileOutputStream(new File(connectorsDefFolder.getAbsolutePath() + File.separator + generatedXsdName));
        Result result = new StreamResult(stream);

        final Transformer transformer = xslTemplate.newTransformer();
        transformer.setParameter("indent", true);
        transformer.transform(xmlSource, result);
    }

    /**
     * @param childPart
     * @param definitions
     * @param collaborationDiagram
     * @param collaboration
     */
    private void processPool(PoolEditPart childPart, TDefinitions definitions, TCollaboration collaboration) {
        Pool pool = (Pool)childPart.resolveSemanticElement();
        // create semantic process
        TProcess bpmnProcess = ModelFactory.eINSTANCE.createTProcess();
        setCommonAttributes(pool, bpmnProcess);
        definitions.getRootElement().add(bpmnProcess);
        TParticipant participant = ModelFactory.eINSTANCE.createTParticipant();
        participant.setProcessRef(QName.valueOf(bpmnProcess.getId()));
        participant.setName(bpmnProcess.getName());//should be done after populate test Common attributes
        participant.setId(EcoreUtil.generateUUID());
        collaboration.getParticipant().add(participant);
        
        for (Actor actor : pool.getActors()) {
            TParticipant actorParticipant = ModelFactory.eINSTANCE.createTParticipant();
            setCommonAttributes(actor, actorParticipant);
            collaboration.getParticipant().add(actorParticipant);
            participantMapping.put(actor, actorParticipant);
        }

        //create graphic process
        BPMNShape processShape = DiFactory.eINSTANCE.createBPMNShape();
        processShape.setBpmnElement(QName.valueOf(bpmnProcess.getId()));
        final IFigure bonitaPoolFigure = childPart.getFigure();
        final Rectangle bounds = bonitaPoolFigure.getBounds().getCopy();
        FiguresHelper.translateToAbsolute(bonitaPoolFigure, bounds);
        Bounds laneBounds = DcFactory.eINSTANCE.createBounds();
        laneBounds.setHeight(bounds.preciseHeight());
        laneBounds.setWidth(bounds.preciseWidth());
        laneBounds.setX(bounds.preciseX());
        laneBounds.setY(bounds.preciseY());
        processShape.setBounds(laneBounds);
        processShape.setId(ModelHelper.getEObjectID(childPart.getNotationView()));
        processShape.setIsHorizontal(true);
        bpmnPlane.getDiagramElement().add(processShape);

        populateWithData(pool, bpmnProcess);//create data before in order to have them accessible after
        populateWithMessage(pool, bpmnProcess);
        populate(childPart, bpmnProcess, null);
        populateWithSequenceFlow(childPart, bpmnProcess);

    }

    private void populateWithMessage(Pool pool, TProcess bpmnProcess) {
		//messageMap.clear();
		List<ThrowMessageEvent> thowMessageEvents = ModelHelper.getAllItemsOfType(pool, ProcessPackage.eINSTANCE.getThrowMessageEvent()) ;
		for (ThrowMessageEvent throwMessageEvent : thowMessageEvents) {
			for(Message message : throwMessageEvent.getEvents()){
				TMessage tMessage = ModelFactory.eINSTANCE.createTMessage();
				tMessage.setId(EcoreUtil.getID(message));
//				tMessage.setItemRef(value)
//				message.getMessageContent().getExpressions()
			}
		}
	}

	private void populateWithSignals(TDefinitions definitions) {
        definitions.getRootElement().addAll(tSignals);
    }

    protected void populateWithData(Pool pool, TProcess bpmnProcess) {
        dataMap.clear();
        for (EObject item : pool.getData()) {
            Data bonitaData = (Data) item;

            /*Create the itemDefinition*/
            TItemDefinition dataItemDefinition = createDataItemDefinition(bonitaData);
            dataMap.put(bonitaData, dataItemDefinition);
            QName dataItemDefinitionIdAsQname = QName.valueOf(dataItemDefinition.getId());

            /*Add the dataObject using the reference*/
            createDataObject(bpmnProcess, bonitaData, dataItemDefinitionIdAsQname);

            /*Define required data on the process, facultative?*/
            TDataInput tDataInput = fillIOSpecification(bpmnProcess, dataItemDefinitionIdAsQname);

            //TODO: handle default values of datas...
            /*Add default value as inputdata on the process*/
            Expression defaultValue = bonitaData.getDefaultValue();
            if(defaultValue != null){
                TDataInputAssociation tDataInputAssociation = createDataInputAssociation(tDataInput, defaultValue);
                //TODO: and now I put the inputassociation where??? I can't put it on processes :'(
                tDataInputAssociation.getAnyAttribute();
            }
        }
        TInputOutputSpecification ioSpecification = bpmnProcess.getIoSpecification();
        if(ioSpecification == null){
            ioSpecification = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            ioSpecification.setId(EcoreUtil.generateUUID());
            bpmnProcess.setIoSpecification(ioSpecification);
        }
        if(ioSpecification.getInputSet().isEmpty()){
            final TInputSet createTInputSet = ModelFactory.eINSTANCE.createTInputSet();
            createTInputSet.setId(EcoreUtil.generateUUID());
            ioSpecification.getInputSet().add(createTInputSet);
        }
        if(ioSpecification.getOutputSet().isEmpty()){
            final TOutputSet createTOutputSet = ModelFactory.eINSTANCE.createTOutputSet();
            createTOutputSet.setId(EcoreUtil.generateUUID());
            ioSpecification.getOutputSet().add(createTOutputSet);
        }
    }

    protected TDataInputAssociation createDataInputAssociation(TDataInput tDataInput, Expression defaultValue) {
        TDataInputAssociation tDataInputAssociation = ModelFactory.eINSTANCE.createTDataInputAssociation();
        tDataInputAssociation.setId(EcoreUtil.generateUUID());
        tDataInputAssociation.setTargetRef(tDataInput.getId());
        EList<TAssignment> assignment = tDataInputAssociation.getAssignment();
        TAssignment tAssignment = ModelFactory.eINSTANCE.createTAssignment();

        TFormalExpression toExpression = ModelFactory.eINSTANCE.createTFormalExpression();
        toExpression.setId(EcoreUtil.generateUUID());
        FeatureMapUtil.addText(toExpression.getMixed(), /*"getDataInput('"+*/tDataInput.getId()/*+"')"*/);
        tAssignment.setTo(toExpression);

        TFormalExpression fromExpression = createBPMNFormalExpressionFromBonitaExpression(defaultValue);
        tAssignment.setFrom(fromExpression);

        assignment.add(tAssignment);
        return tDataInputAssociation;
    }

    /**
     * Scope of data defined by this?
     * @param callableElement
     * @param dataItemDefinitionIdAsQname
     * @return
     */
    protected TDataInput fillIOSpecification(TCallableElement callableElement, QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = callableElement.getIoSpecification();
        if(tInputOutputAssociation == null){
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            callableElement.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataInput(dataItemDefinitionIdAsQname,	tInputOutputAssociation);
    }

    protected TDataInput fillIOSpecificationWithNewDataInput(QName dataItemDefinitionIdAsQname,	TInputOutputSpecification tInputOutputAssociation) {
        TDataInput tDataInput =  ModelFactory.eINSTANCE.createTDataInput();
        tDataInput.setItemSubjectRef(dataItemDefinitionIdAsQname);
        tDataInput.setId(EcoreUtil.generateUUID());

        TInputSet tInputSet = ModelFactory.eINSTANCE.createTInputSet();
        tInputSet.setId(EcoreUtil.generateUUID());
        tInputSet.getDataInputRefs().add(tDataInput.getId());
        EList<TDataInput> dataInput = tInputOutputAssociation.getDataInput();
        EList<TInputSet> inputSet = tInputOutputAssociation.getInputSet();
        inputSet.add(tInputSet);
        dataInput.add(tDataInput);
        return tDataInput;
    }

    protected TDataOutput fillIOSpecificationWithNewDataOutput(QName dataItemDefinitionIdAsQname, TInputOutputSpecification tInputOutputAssociation) {
        TDataOutput tDataOutput =  ModelFactory.eINSTANCE.createTDataOutput();
        tDataOutput.setItemSubjectRef(dataItemDefinitionIdAsQname);
        tDataOutput.setId(EcoreUtil.generateUUID());

        TOutputSet tOutputSet = ModelFactory.eINSTANCE.createTOutputSet();
        tOutputSet.setId(EcoreUtil.generateUUID());
        tOutputSet.getDataOutputRefs().add(tDataOutput.getId());
        EList<TDataOutput> dataOutput = tInputOutputAssociation.getDataOutput();
        EList<TOutputSet> outputSet = tInputOutputAssociation.getOutputSet();
        outputSet.add(tOutputSet);
        dataOutput.add(tDataOutput);
        return tDataOutput;
    }

    protected TDataInput fillIOSpecificationWithNewDataInput(TActivity tActivity, QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = tActivity.getIoSpecification();
        if(tInputOutputAssociation == null){
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            tActivity.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataInput(dataItemDefinitionIdAsQname,	tInputOutputAssociation);
    }


    private TDataOutput fillIOSpecificationWithNewDataOutput(TServiceTask serviceTask, QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = serviceTask.getIoSpecification();
        if(tInputOutputAssociation == null){
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            serviceTask.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataOutput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    protected void createDataObject(TProcess bpmnProcess, Data bonitaData,QName dataItemDefinitionIdAsQname) {
        TDataObject bpmnData = ModelFactory.eINSTANCE.createTDataObject();
        bpmnData.setItemSubjectRef(dataItemDefinitionIdAsQname);
        setCommonAttributes(bonitaData, bpmnData);
        bpmnData.setName(bonitaData.getName());
        bpmnData.setId("DataObject"+EcoreUtil.generateUUID()+bpmnData.getId());//avoid to have duplicate id for dataobject and itemDefinition
        bpmnProcess.getFlowElement().add(bpmnData);
        bpmnData.setIsCollection(bonitaData.isMultiple());
    }

    protected TItemDefinition createDataItemDefinition(Data bonitaData) {
        TItemDefinition dataItemDefinition = ModelFactory.eINSTANCE.createTItemDefinition();
        setCommonAttributes(bonitaData, dataItemDefinition);
        dataItemDefinition.setStructureRef(getStructureRef(bonitaData));
        definitions.getRootElement().add(dataItemDefinition);
        return dataItemDefinition;
    }


    protected TExpression createBPMNExpressionFromString(String value) {
        TExpression fromExpression = ModelFactory.eINSTANCE.createTExpression();
        fromExpression.setId(EcoreUtil.generateUUID());
        FeatureMapUtil.addText(fromExpression.getMixed(), value);
        return fromExpression;
    }

    protected TFormalExpression createBPMNFormalExpressionFromBonitaExpression(Expression bonitaExpression) {
        TFormalExpression res = ModelFactory.eINSTANCE.createTFormalExpression();
        res.setId(EcoreUtil.generateUUID());

        if(ExpressionConstants.SCRIPT_TYPE.equals(bonitaExpression.getType())
                && ExpressionConstants.GROOVY.equals(bonitaExpression.getInterpreter())){
            //The default one is Groovy
            //fromExpression.setLanguage(ExpressionConstants.GROOVY);//TODO: convert interpreter Bonita to official interpreter name?
            FeatureMapUtil.addText(res.getMixed(), bonitaExpression.getContent());
        } else if(ExpressionConstants.SCRIPT_TYPE.equals(bonitaExpression.getType())){
            res.setLanguage(bonitaExpression.getInterpreter());//it is another Interpreter, doesn't exist yet
            FeatureMapUtil.addText(res.getMixed(), bonitaExpression.getContent());
        } else if(ExpressionConstants.VARIABLE_TYPE.equals(bonitaExpression.getType())){
            Data bonitaData = (Data)bonitaExpression.getReferencedElements().get(0);
            if(bonitaData != null){
                TItemDefinition bpmnData = dataMap.get(ModelHelper.getDataReferencedInExpression(bonitaData));
                if(bonitaData.isTransient()){
                    if(bpmnData != null){
                        FeatureMapUtil.addText(res.getMixed(), "getActivityProperty('"+((Element)(bonitaData.eContainer().eContainer().eContainer().eContainer())).getName()+"','"+bpmnData.getId()+"')");
                    } else {//fallback
                        FeatureMapUtil.addText(res.getMixed(), "getActivityProperty('"+((Element)(bonitaData.eContainer().eContainer().eContainer().eContainer())).getName()+"','"+bonitaExpression.getContent()+"')");
                    }
                } else {
                    if(bpmnData != null){
                        FeatureMapUtil.addText(res.getMixed(), "getDataObject('"+bonitaData.getName()+"')");
                    } else {//fallback
                        FeatureMapUtil.addText(res.getMixed(), "getDataObject('"+bonitaExpression.getContent()+"')");
                    }
                }
            }
            res.setLanguage("http://www.w3.org/1999/XPath");
        } else {
            //FIXME : how to transmit information of Variable or constant more easily
            FeatureMapUtil.addText(res.getMixed(), bonitaExpression.getContent());
            res.setLanguage("http://www.w3.org/1999/XPath");
        }
        res.setEvaluatesToTypeRef(QName.valueOf(JAVA_XMLNS+":"+bonitaExpression.getReturnType()));
        return res;
    }

    protected QName getStructureRef(Data data) {
        if(data.getDataType() instanceof XMLType){
            String xmlnsDataType = getXmlns(((XMLData)data));
            return QName.valueOf(xmlnsDataType+":"+((XMLData)data).getType());
        } else {
            String technicalTypeFor = org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data);
            return QName.valueOf(JAVA_XMLNS+":"+technicalTypeFor);
        }
    }

    private int n = 0;

    private String getXmlns(XMLData data) {
        String dataTypeNamespace = data.getNamespace();
        for (Entry<String, String> prefixMap : root.getXMLNSPrefixMap().entrySet()) {
            if(prefixMap.getValue().equals(dataTypeNamespace)){
                return prefixMap.getKey();
            }
        }
        final String xmlnsIndex = "n"+n;
        root.getXMLNSPrefixMap().put(xmlnsIndex, dataTypeNamespace);
        n++;
        return xmlnsIndex;
    }

    protected void populateWithSequenceFlow(PoolEditPart poolEditPart, TProcess bpmnProcess) {
        Pool pool = (Pool) poolEditPart.resolveSemanticElement();
        for (EObject item : ModelHelper.getAllItemsOfType(pool, ProcessPackage.Literals.SEQUENCE_FLOW)) {
            SequenceFlow bonitaFlow = (SequenceFlow)item;
            TSequenceFlow bpmnFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
            setCommonAttributes(bonitaFlow, bpmnFlow);
            if (bonitaFlow.getCondition() != null
                    && bonitaFlow.getCondition().getContent() != null) {
                TExpression expression = createBPMNFormalExpressionFromBonitaExpression(bonitaFlow.getCondition());
                bpmnFlow.setConditionExpression(expression);
            }
            final TFlowElement source = mapping.get(bonitaFlow.getSource());
            if(source != null){
                bpmnFlow.setSourceRef(source.getId());
                final TFlowElement target = mapping.get(bonitaFlow.getTarget());
                if(target != null){
                    bpmnFlow.setTargetRef(target.getId());
                    bpmnProcess.getFlowElement().add(bpmnFlow);

                    // graphic
                    ConnectionNodeEditPart editPart = (ConnectionNodeEditPart)GMFTools.findEditPart(poolEditPart, bonitaFlow);
                    BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
                    edge.setBpmnElement(QName.valueOf(bpmnFlow.getId()));

                    PointList pointList = editPart.getConnectionFigure().getPoints();
                    for (int i = 0; i < pointList.size(); i++) {
                        org.omg.spec.dd.dc.Point ddPoint = DcFactory.eINSTANCE.createPoint();
                        Point point = pointList.getPoint(i);
                        ddPoint.setX(point.preciseX());
                        ddPoint.setY(point.preciseY());
                        edge.getWaypoint().add(ddPoint);
                    }

                    edge.setId(ModelHelper.getEObjectID(editPart.getNotationView()));
                    bpmnPlane.getDiagramElement().add(edge);
                    if(bonitaFlow instanceof SequenceFlow && source instanceof TGateway){
                    	if(bonitaFlow.isIsDefault()){
                    		if(source instanceof TInclusiveGateway){
                    			((TInclusiveGateway) source).setDefault(bpmnFlow.getId());
                    		} else if(source instanceof TExclusiveGateway){
                    			((TExclusiveGateway) source).setDefault(bpmnFlow.getId());
                    		} else if(source instanceof TComplexGateway){
                    			((TComplexGateway) source).setDefault(bpmnFlow.getId());
                    		}
                    	}
                    }
                } else {
                    BonitaStudioLog.log("Can't create the sequence flow named "+bonitaFlow.getName()+" because we can't find the target "+bonitaFlow.getTarget().getName());
                }
            } else {
                BonitaStudioLog.log("Can't create the sequence flow named "+bonitaFlow.getName()+" because we can't find the source "+bonitaFlow.getSource().getName());
            }
        }
    }

    /**
     * @param bpmnProcess
     * @param bpmnProcessDiagram
     * @param pool
     */
    private void populate(IGraphicalEditPart poolOrLanePart, TProcess bpmnProcess, TLane bpmnLane) {
        if (poolOrLanePart instanceof PoolEditPart) {
            for (Object subPart : poolOrLanePart.getChildren()) {
                if (subPart instanceof ShapeCompartmentEditPart) {
                    poolOrLanePart = (ShapeCompartmentEditPart)subPart;
                }
            }
        } else if (poolOrLanePart instanceof LaneEditPart) {
            for (Object subPart : poolOrLanePart.getChildren()) {
                if (subPart instanceof ShapeCompartmentEditPart) {
                    poolOrLanePart = (ShapeCompartmentEditPart)subPart;
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
    protected void populateWithLanes(IGraphicalEditPart poolOrLane, TProcess bpmnProcess) {
        TLaneSet laneSet = null;
        for (Object child : poolOrLane.getChildren()) {
            if (child instanceof LaneEditPart) {
                if(laneSet == null){
                    //init the laneset (we are using only one laneset per pool
                    laneSet = ModelFactory.eINSTANCE.createTLaneSet();
                    laneSet.setId(bpmnProcess.getName()+"_laneSet");
                    bpmnProcess.getLaneSet().add(laneSet);
                }

                // semantic
                LaneEditPart bonitaLanePart = (LaneEditPart)child;
                Lane bonitaLane = (Lane)bonitaLanePart.resolveSemanticElement();
                TLane bpmnLane = ModelFactory.eINSTANCE.createTLane();
                bpmnLane.setName(bonitaLane.getName());
                bpmnLane.setId(bonitaLane.getName());
                setCommonAttributes(bonitaLane, bpmnLane); 
                laneSet.getLane().add(bpmnLane);
                // graphic
                BPMNShape laneShape = DiFactory.eINSTANCE.createBPMNShape();
                laneShape.setBpmnElement(QName.valueOf(bpmnLane.getId()));
                final IFigure bonitaLaneFigure = bonitaLanePart.getFigure();
                final Rectangle bounds = bonitaLaneFigure.getBounds().getCopy();
                FiguresHelper.translateToAbsolute(bonitaLaneFigure, bounds);
                Bounds laneBounds = DcFactory.eINSTANCE.createBounds();
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


    protected void populateWithElements(IGraphicalEditPart part, TSubProcess bpmnSubProcess, TLane bpmnParentLane) {
        for (Object child : part.getChildren()) {
            if(child instanceof CompartmentEditPart){
                populateWithElements((IGraphicalEditPart) child, bpmnSubProcess, bpmnParentLane);
            } else if (!(child instanceof LaneEditPart)
                    && !(child instanceof SubProcessEventEditPart)
                    && !(child instanceof SubProcessEvent2EditPart)
                    && !(child instanceof ITextAwareEditPart)
                    && !(child instanceof CompartmentEditPart)) {
                ShapeNodeEditPart bonitaElementPart = (ShapeNodeEditPart)child;
                if(bonitaElementPart.resolveSemanticElement() instanceof FlowElement){
                    FlowElement bonitaElement = (FlowElement) bonitaElementPart.resolveSemanticElement();
                    // semantic
                    TFlowElement bpmnElement = createTFlowElement(bonitaElement);
                    bpmnSubProcess.getFlowElement().add(bpmnElement);
                    if (bpmnParentLane != null) {
                        bpmnParentLane.getFlowNodeRef().add(bpmnElement.getId());
                    }
                    mapping.put(bonitaElement, bpmnElement);

                    createGraphicForFlowElement(bonitaElementPart, bpmnElement);
                }
            } else if(child instanceof SubProcessEventEditPart
                    || child instanceof SubProcessEvent2EditPart){
                ShapeNodeEditPart bonitaElementPart = (ShapeNodeEditPart)child;
                SubProcessEvent subProcEvent = (SubProcessEvent) bonitaElementPart.resolveSemanticElement();
                // semantic
                TSubProcess bpmnSubProcess2 = (TSubProcess) createTFlowElement(subProcEvent);
                bpmnSubProcess.getFlowElement().add(bpmnSubProcess2);
                if (bpmnParentLane != null) {
                    bpmnParentLane.getFlowNodeRef().add(bpmnSubProcess.getId());
                }
                mapping.put(subProcEvent, bpmnSubProcess2);

                // graphic
                boolean isExpanded = ((ShapeCompartmentEditPart)((SubProcessEvent2EditPart)child).getChildren().get(0)).getCompartmentFigure().isExpanded();
                BPMNShape elementShape = DiFactory.eINSTANCE.createBPMNShape();
                elementShape.setIsExpanded(isExpanded);
                elementShape.setBpmnElement(QName.valueOf(bpmnSubProcess.getId()));
                final IFigure bonitaElementFigure = bonitaElementPart.getFigure();
                final Rectangle bounds = bonitaElementFigure.getBounds().getCopy();
                FiguresHelper.translateToAbsolute(bonitaElementFigure, bounds);
                Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
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
    protected void populateWithElements(IGraphicalEditPart part, TProcess bpmnProcess, TLane bpmnParentLane) {
        Map<Data, TItemDefinition> localDataMap = new HashMap<Data, TItemDefinition>();
        for (Object child : part.getChildren()) {
            localDataMap.clear();
            if (! (child instanceof LaneEditPart)
                    && !(child instanceof SubProcessEventEditPart)
                    && !(child instanceof SubProcessEvent2EditPart)) {
                ShapeNodeEditPart bonitaElementPart = (ShapeNodeEditPart)child;
                EObject resolvedSemanticElement = bonitaElementPart.resolveSemanticElement();
                if(resolvedSemanticElement instanceof FlowElement){
                    FlowElement bonitaElement = (FlowElement) resolvedSemanticElement;
                    // semantic
                    TFlowElement bpmnElement = createTFlowElement(bonitaElement);
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
            } else if(child instanceof SubProcessEventEditPart
                    || child instanceof SubProcessEvent2EditPart){
                ShapeNodeEditPart bonitaElementPart = (ShapeNodeEditPart)child;
                SubProcessEvent subProcEvent = (SubProcessEvent) bonitaElementPart.resolveSemanticElement();
                // semantic
                TSubProcess bpmnSubProcess = (TSubProcess) createTFlowElement(subProcEvent);
                bpmnProcess.getFlowElement().add(bpmnSubProcess);
                if (bpmnParentLane != null) {
                    bpmnParentLane.getFlowNodeRef().add(bpmnSubProcess.getId());
                }
                mapping.put(subProcEvent, bpmnSubProcess);

                // graphic
                boolean isExpanded = true;
                for(Object editpart : ((SubProcessEvent2EditPart)child).getChildren()){
                    if(editpart instanceof ShapeCompartmentEditPart){
                        isExpanded = ((ShapeCompartmentEditPart) editpart).getCompartmentFigure().isExpanded();
                        break;
                    }
                }
                BPMNShape elementShape = DiFactory.eINSTANCE.createBPMNShape();
                elementShape.setIsExpanded(isExpanded);
                elementShape.setBpmnElement(QName.valueOf(bpmnSubProcess.getId()));
                final IFigure bonitaElementFigure = bonitaElementPart.getFigure();
                final Rectangle bounds = bonitaElementFigure.getBounds().getCopy();
                FiguresHelper.translateToAbsolute(bonitaElementFigure, bounds);
                Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
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

    protected void createGraphicForFlowElement(ShapeNodeEditPart bonitaElementPart, TFlowElement bpmnElement) {
        BPMNShape elementShape = DiFactory.eINSTANCE.createBPMNShape();
        elementShape.setBpmnElement(QName.valueOf(bpmnElement.getId()));
        final IFigure bonitaElementFigure = bonitaElementPart.getFigure();
        final Rectangle bounds = bonitaElementFigure.getBounds().getCopy();
        FiguresHelper.translateToAbsolute(bonitaElementFigure, bounds);
        Bounds elementBounds = DcFactory.eINSTANCE.createBounds();
        elementBounds.setHeight(bounds.preciseHeight());
        elementBounds.setWidth(bounds.preciseWidth());
        elementBounds.setX(bounds.preciseX());
        elementBounds.setY(bounds.preciseY());
        elementShape.setBounds(elementBounds);
        elementShape.setId(ModelHelper.getEObjectID(bonitaElementPart.getNotationView()));
        bpmnPlane.getDiagramElement().add(elementShape);
    }

    protected void populateDataOnActivity(TProcess bpmnProcess,
            Map<Data, TItemDefinition> localDataMap,
            EObject resolvedSemanticElement, TFlowElement bpmnElement) {
        if (resolvedSemanticElement instanceof DataAware
                && bpmnElement instanceof TActivity) {
            for(Data bonitaData : ((DataAware) resolvedSemanticElement).getData()){
                /*Create the itemDefinition*/
                TItemDefinition dataItemDefinition = createDataItemDefinition(bonitaData);
                localDataMap.put(bonitaData, dataItemDefinition);
                QName dataItemDefinitionIdAsQname = QName.valueOf(dataItemDefinition.getId());
                if(!bonitaData.isTransient()){
                    /*Add the dataObject using the reference*/
                    createDataObject(bpmnProcess, bonitaData, dataItemDefinitionIdAsQname);
                } else {
                    /*it is a BPMN Property*/
                    TProperty tProperty = ModelFactory.eINSTANCE.createTProperty();
                    tProperty.setId(EcoreUtil.generateUUID());
                    tProperty.setName(bonitaData.getName());
                    tProperty.setItemSubjectRef(dataItemDefinitionIdAsQname);
                    ((TActivity)bpmnElement).getProperty().add(tProperty);
                }
                /*Define required data on the process, facultative?*/
                TDataInput tDataInput = fillIOSpecificationWithNewDataInput((TActivity) bpmnElement, dataItemDefinitionIdAsQname);

                if(bonitaData.getDefaultValue() != null
                        && bonitaData.getDefaultValue().getName() != null){
                    List<TDataInputAssociation> dataInputAssociation = ((TActivity)bpmnElement).getDataInputAssociation();
                    dataInputAssociation.add(createDataInputAssociation(tDataInput, bonitaData.getDefaultValue()));
                }
            }
        }
    }

    protected void createBoundaries(TProcess bpmnProcess,
            ShapeNodeEditPart bonitaElementPart, FlowElement bonitaElement,
            TFlowElement bpmnElement) {
        if(bonitaElement instanceof Activity){
            for(BoundaryEvent boundaryEvent  : ((Activity) bonitaElement).getBoundaryIntermediateEvents()){
                TBoundaryEvent bpmnBoundary = ModelFactory.eINSTANCE.createTBoundaryEvent();
                setCommonAttributes(boundaryEvent, bpmnBoundary);
                if(boundaryEvent instanceof IntermediateErrorCatchEvent){
                    TErrorEventDefinition errorventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
                    errorventDef.setId("eventdef-"+boundaryEvent.getName() + EcoreUtil.generateUUID());
                    final String errorCode = ((IntermediateErrorCatchEvent) boundaryEvent).getErrorCode();
                    if(errorCode != null && errorCode.length() != 0){
                        errorventDef.setErrorRef(QName.valueOf(errorCode));
                    }
                    bpmnBoundary.getEventDefinition().add(errorventDef);
                } else if(boundaryEvent instanceof BoundarySignalEvent){
                    TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
                    eventDef.setId("eventdef-"+boundaryEvent.getName() + EcoreUtil.generateUUID());
                    TSignal tSignal = getOrCreateTSignal((SignalEvent) boundaryEvent);
                    if(tSignal != null){
                        eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
                    }
                    bpmnBoundary.getEventDefinition().add(eventDef);
                } else if(boundaryEvent instanceof BoundaryMessageEvent){
                    TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
                    eventDef.setId("eventdef-"+boundaryEvent.getName() + EcoreUtil.generateUUID());
                    final String eventCaught = ((BoundaryMessageEvent) boundaryEvent).getEvent();
                    if(eventCaught != null && eventCaught.length() != 0){
                        eventDef.setMessageRef((QName.valueOf(eventCaught)));
                    }
                    bpmnBoundary.getEventDefinition().add(eventDef);
                } else if(boundaryEvent instanceof BoundaryTimerEvent){
                    TTimerEventDefinition eventDef = createTimerEventDef((AbstractTimerEvent) boundaryEvent);
                    bpmnBoundary.getEventDefinition().add(eventDef);
                }

                bpmnBoundary.setAttachedToRef(QName.valueOf(bpmnBoundary.getId()));
                bpmnProcess.getFlowElement().add(bpmnBoundary);
                mapping.put(boundaryEvent, bpmnBoundary);

                //graphic
                BPMNShape boundaryShape = DiFactory.eINSTANCE.createBPMNShape();
                boundaryShape.setBpmnElement(QName.valueOf(bpmnBoundary.getId()));
                IGraphicalEditPart boundaryElementPart = GMFTools.findEditPart(bonitaElementPart, boundaryEvent);
                final IFigure bonitaBoundaryFigure = boundaryElementPart.getFigure();
                final Rectangle bBounds = bonitaBoundaryFigure.getBounds().getCopy();
                FiguresHelper.translateToAbsolute(bonitaBoundaryFigure, bBounds);
                Bounds boundaryBounds = DcFactory.eINSTANCE.createBounds();
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

    protected TSignal getOrCreateTSignal(SignalEvent signalEvent) {
        final String signalCode = signalEvent.getSignalCode();
        TSignal tSignal = null;
        if(signalCode != null){
            tSignal = signalCodeTSignal.get(signalCode);
            if(tSignal == null){
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
    protected void setCommonAttributes(Element bonitaElement, TBaseElement bpmnElement) {
        final String name = bonitaElement.getName();
        /*We must assure that each element have an id*/
        //		if(name != null && name.length() != 0){
        //			bpmnElement.setId(name);
        //		} else {
        bpmnElement.setId(ModelHelper.getEObjectID(bonitaElement));
        //		}
        if (bpmnElement instanceof TFlowElement) {
            ((TFlowElement) bpmnElement).setName(bonitaElement.getName());
        } else if (bpmnElement instanceof TProcess) {
            ((TProcess) bpmnElement).setName(bonitaElement.getName());
        } else if(bpmnElement instanceof TParticipant){
        	((TParticipant) bpmnElement).setName(bonitaElement.getName());
        }
        String documentation = bonitaElement.getDocumentation();
        if (documentation != null && ! documentation.isEmpty()) {
            TDocumentation doc = ModelFactory.eINSTANCE.createTDocumentation();
            FeatureMapUtil.addText(doc.getMixed(), documentation);
            bpmnElement.getDocumentation().add(doc);
        }
    }

    public void setCommonDiagramAttributes(ShapeNodeEditPart part, Shape bpmnNode) {
        Element semantic = (Element)part.resolveSemanticElement();
        Bounds bounds = DcFactory.eINSTANCE.createBounds();
        Point absoluteLocation = part.getLocation().getCopy();
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
    private TFlowElement createTFlowElement(Element child) {
        TFlowElement res = null;
        if(child instanceof Activity){
            res = createActivity((Activity)child);
            final Activity activity = (Activity) child;
            if(child instanceof ServiceTask
                    && !activity.getConnectors().isEmpty()){
                TServiceTask serviceTask = (TServiceTask) res;
                handleConnectorOnServiceTask(activity, serviceTask);
            } else if(child instanceof CallActivity){
                /*Do the mapping*/
                CallActivity callActivity = (CallActivity) child;
                TCallActivity ca = (TCallActivity)res;
                dataMappingWithCallActivity(callActivity, ca);
            }
        } else if(child instanceof Gateway){
            res = createGateway((Gateway)child);
        } else if(child instanceof SubProcessEvent){
            TSubProcess eventSubProc = ModelFactory.eINSTANCE.createTSubProcess();
            eventSubProc.setTriggeredByEvent(true);
            //((SubProcessEvent) child).getElements()
            //errors.add(NLS.bind(Messages.subprocessReferenceLost, child.getName()));
            res = eventSubProc;
        }

        // Start Events
        else if (child instanceof StartTimerEvent) {
            TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            TTimerEventDefinition eventDef = ModelFactory.eINSTANCE.createTTimerEventDefinition();
            final Expression conditionExpression = ((StartTimerEvent) child).getCondition();
            if(conditionExpression != null){
                final String condition = conditionExpression.getContent();//FIXME
                //TExpression expression = ModelFactory.eINSTANCE.createTExpression();
                if(condition != null){
                    if(DateUtil.isDuration(condition)){
                        FeatureMapUtil.addText(eventDef.getTimeDuration().getMixed(), condition);
                    } else if(DateUtil.isDate(condition)){
                        FeatureMapUtil.addText(eventDef.getTimeDate().getMixed(), condition);
                    } else {
                        FeatureMapUtil.addText(eventDef.getTimeCycle().getMixed(), condition);
                    }
                }
            }
            eventDef.setId("event-def"+child.getName());
            bpmnStart.getEventDefinition().add(eventDef);
            errors.add(Messages.bind(Messages.timerDefinitionNotExported, child.getName()));
            res = bpmnStart;
        } else if (child instanceof StartErrorEvent) {
            StartErrorEvent bonitaSart = (StartErrorEvent)child;
            TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            TErrorEventDefinition eventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
            bpmnStart.getEventDefinition().add(eventDef);
            final String errorCode = bonitaSart.getErrorCode();
            final String eventDefId = errorCode != null ? errorCode + EcoreUtil.generateUUID() : EcoreUtil.generateUUID();
            eventDef.setId(eventDefId);
            if(errorCode != null){
                eventDef.setErrorRef(QName.valueOf(errorCode));
            }
            res = bpmnStart;
        } else if (child instanceof StartMessageEvent) {
            TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
            bpmnStart.getEventDefinition().add(eventDef);
            final String eventId = ((StartMessageEvent)child).getEvent() != null ?((StartMessageEvent)child).getEvent() + EcoreUtil.generateUUID() :EcoreUtil.generateUUID();
            eventDef.setId("event-def"+eventId);
            EList<Operation> messageContent = ((StartMessageEvent) child).getMessageContent();
			if(messageContent.size() > 0){
//            	createOperation
//            	eventDef.setOperationRef(value)
//            	bpmnStart.
            }
            res = bpmnStart;
        } else if (child instanceof StartSignalEvent) {
            TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            TSignal tSignal = getOrCreateTSignal((SignalEvent) child);
            TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
            if(tSignal != null){
                eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
            }
            bpmnStart.getEventDefinition().add(eventDef);
            eventDef.setId(((StartSignalEvent)child).getName());
            res = bpmnStart;
        } else if (child instanceof StartEvent) {
            TStartEvent bpmnStart = ModelFactory.eINSTANCE.createTStartEvent();
            res = bpmnStart;
        }

        // End events
        else if (child instanceof EndErrorEvent) {
            EndErrorEvent bonitaEnd = (EndErrorEvent)child;
            TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            TErrorEventDefinition eventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
            bpmnEnd.getEventDefinition().add(eventDef);
            final String errorCode = bonitaEnd.getErrorCode();
            final String eventDefId = errorCode != null ? errorCode + EcoreUtil.generateUUID() : EcoreUtil.generateUUID();
            eventDef.setId(eventDefId);
            if(errorCode != null){
                eventDef.setErrorRef(QName.valueOf(errorCode));
            }
            res = bpmnEnd;
        } else if (child instanceof EndMessageEvent) {
            TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            for(Message eventObject :((EndMessageEvent)child).getEvents()){
                TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
                //eventDef.setMessageRef(eventObject.get);
                //eventDef.setOperationRef(value);
                final String name = eventObject.getName()!=null?eventObject.getName():EcoreUtil.generateUUID();
                eventDef.setId("event-def"+name);
                bpmnEnd.getEventDefinition().add(eventDef);
            }
            res = bpmnEnd;
        } else if (child instanceof EndSignalEvent) {
            TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            createThrowSignalEventDefinition((EndSignalEvent)child, bpmnEnd);

            res = bpmnEnd;
        } else if(child instanceof EndTerminatedEvent){
            TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            TTerminateEventDefinition eventDef = ModelFactory.eINSTANCE.createTTerminateEventDefinition();
            bpmnEnd.getEventDefinition().add(eventDef);
            eventDef.setId(EcoreUtil.generateUUID());
            res = bpmnEnd;
        } else if (child instanceof EndEvent) {
            TEndEvent bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
            res = bpmnEnd;
        }

        // Events
        else if (child instanceof IntermediateCatchMessageEvent) {
            IntermediateCatchMessageEvent bonitaEvent = (IntermediateCatchMessageEvent)child;
            TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            final String event = bonitaEvent.getEvent();
            if(event != null){
                bpmnEvent.getEventDefinitionRef().add(QName.valueOf(event));
            }
            res = bpmnEvent;
        } else if (child instanceof IntermediateThrowMessageEvent) {
            IntermediateThrowMessageEvent bonitaEvent = (IntermediateThrowMessageEvent)child;
            TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
            for (Message bonitaEventDef : bonitaEvent.getEvents()) {
                TMessageEventDefinition eventDef = ModelFactory.eINSTANCE.createTMessageEventDefinition();
                final String name = bonitaEventDef.getName();
                eventDef.setId(name);
//                TMessage tMessage = createMessage(bonitaEventDef);
//                eventDef.setMessageRef(QName.valueOf(tMessage.getId()));
                final String eventDefId = eventDef.getId();
                if(eventDefId != null){
                    bpmnEvent.getEventDefinitionRef().add(QName.valueOf(eventDefId));
                }
                bpmnEvent.getEventDefinition().add(eventDef);
            }
            res = bpmnEvent;
        } else if (child instanceof IntermediateCatchSignalEvent) {
            IntermediateCatchSignalEvent bonitaEvent = (IntermediateCatchSignalEvent)child;
            TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            TSignal tSignal = getOrCreateTSignal(bonitaEvent);
            if(tSignal != null){
                bpmnEvent.getEventDefinitionRef().add(QName.valueOf(tSignal.getId()));
            }
            res = bpmnEvent;
        } else if (child instanceof IntermediateThrowSignalEvent) {
            IntermediateThrowSignalEvent bonitaEvent = (IntermediateThrowSignalEvent)child;
            TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
            /*TSignalEventDefinition eventDef = */createThrowSignalEventDefinition(bonitaEvent, bpmnEvent);
            //bpmnEvent.getEventDefinition().add(eventDef);
            res = bpmnEvent;
        } else if (child instanceof CatchLinkEvent) {
            CatchLinkEvent bonitaEvent = (CatchLinkEvent)child;
            TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            createTLinkEventDefinition(bonitaEvent, bpmnEvent);
            final EList<ThrowLinkEvent> fromLinkEvent = bonitaEvent.getFrom();
            for(ThrowLinkEvent from : fromLinkEvent){
                bpmnEvent.getEventDefinitionRef().add(QName.valueOf(from.getName()));
            }
            res = bpmnEvent;
        } else if (child instanceof ThrowLinkEvent) {
            ThrowLinkEvent bonitaEvent = (ThrowLinkEvent)child;
            TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
            TLinkEventDefinition eventDef = ModelFactory.eINSTANCE.createTLinkEventDefinition();
            final CatchLinkEvent to = bonitaEvent.getTo();
            if(to != null){
                final String targetName = to.getName();
                eventDef.setId(targetName);
                eventDef.setName(to.getName() != null ? to.getName() : targetName);
                eventDef.setTarget(QName.valueOf(targetName));
            } else {
                eventDef.setId(EcoreUtil.generateUUID());
            }
            bpmnEvent.getEventDefinition().add(eventDef);
            res = bpmnEvent;
        } else if (child instanceof IntermediateCatchTimerEvent) {
            IntermediateCatchTimerEvent bonitaEvent = (IntermediateCatchTimerEvent)child;
            TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
            TTimerEventDefinition eventDef = createTimerEventDef(bonitaEvent);
            bpmnEvent.getEventDefinition().add(eventDef);
            res = bpmnEvent;
        }
        else {
            errors.add(NLS.bind(Messages.defaultMappingToActivity, child.getName()));
            TActivity bpmnActivity = ModelFactory.eINSTANCE.createTTask();
            setCommonAttributes(child, bpmnActivity);
            res = bpmnActivity;
        }
        setCommonAttributes(child, res);
        return res;
    }

	protected void dataMappingWithCallActivity(CallActivity callActivity,
            TCallActivity ca) {
        TDataInputAssociation dia = ModelFactory.eINSTANCE.createTDataInputAssociation();
        dia.setId(EcoreUtil.generateUUID());
        ca.getDataInputAssociation().add(dia);
        for (InputMapping im : callActivity.getInputMappings()) {
            TAssignment inputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            final Data processSource = im.getProcessSource();
            if(processSource != null){
                final TItemDefinition dataFrom = dataMap.get(processSource);
                inputAssignment.setFrom(createBPMNExpressionFromString(dataFrom != null ? dataFrom.getId() : processSource.getName()));
                String dataTo = getDataReferenceValue(callActivity, im.getSubprocessTarget());
                inputAssignment.setTo(createBPMNExpressionFromString(dataTo));//FIXME: I think we need to search the real targeted data to find the correct id
                dia.getAssignment().add(inputAssignment);
            }
        }
        TDataOutputAssociation doa = ModelFactory.eINSTANCE.createTDataOutputAssociation();
        doa.setId(EcoreUtil.generateUUID());
        ca.getDataOutputAssociation().add(doa);
        for (OutputMapping om : callActivity.getOutputMappings()) {
            TAssignment outputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            String dataFrom = getDataReferenceValue(callActivity, om.getSubprocessSource());
            outputAssignment.setFrom(createBPMNExpressionFromString(dataFrom));//FIXME: I think we need to search the real targeted data to find the correct id
            final Data processTarget = om.getProcessTarget();
            if(processTarget != null){
                final TItemDefinition dataTo = dataMap.get(processTarget);
                outputAssignment.setTo(createBPMNExpressionFromString(dataTo != null ? dataTo.getId() : processTarget.getName()));
                doa.getAssignment().add(outputAssignment);
            }
        }
    }

    protected void handleConnectorOnServiceTask(final Activity activity, TServiceTask serviceTask) {
        final EList<Connector> connectors = activity.getConnectors();
        if(!connectors.isEmpty()){
            Connector connector = connectors.get(0);
            handleBonitaConnectorDefinition(connector.getDefinitionId());
            /*Service task should be used with a connector,
             * this connector will be the bpmn2 operation used
             * /!\BEGIN TO HANDLE A SINGLE OPERATION */
            serviceTask.setImplementation("BonitaConnector");
            serviceTask.setOperationRef(QName.valueOf("Exec"+connector.getDefinitionId()));

            TDataInput dataInput = fillIOSpecificationWithNewDataInput(serviceTask, QName.valueOf(generateConnectorInputItemDef(connector.getDefinitionId())));
            TDataOutput dataOutput = fillIOSpecificationWithNewDataOutput(serviceTask, QName.valueOf(generateConnectorOutputItemDef(connector.getDefinitionId())));


            TDataInputAssociation tDataInputAssociation = ModelFactory.eINSTANCE.createTDataInputAssociation();

            final EList<TAssignment> inputAssignments = tDataInputAssociation.getAssignment();

            for (ConnectorParameter cp : connector.getConfiguration().getParameters()) {
                TAssignment inputAssignment = ModelFactory.eINSTANCE.createTAssignment();
                if(cp.getExpression() instanceof Expression
                        && ((Expression)cp.getExpression()).getContent() != null){
                    inputAssignment.setFrom(createBPMNFormalExpressionFromBonitaExpression((Expression)cp.getExpression()));
                    inputAssignment.setTo(createBPMNExpressionFromString("getDataInput('"+dataInput.getId()+"')/"+XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION+":"+cp.getKey()));
                    inputAssignments.add(inputAssignment);
                }
            }
            if(!tDataInputAssociation.getAssignment().isEmpty()){
                serviceTask.getDataInputAssociation().add(tDataInputAssociation);
                tDataInputAssociation.setTargetRef(dataInput.getId());
                //tDataInputAssociation.getSourceRef().add(da);
            }


            TDataOutputAssociation tDataOutputAssociation = ModelFactory.eINSTANCE.createTDataOutputAssociation();

            final EList<TAssignment> outputAssignments = tDataOutputAssociation.getAssignment();
            for(Operation opm : connector.getOutputs()){
                if(opm.getRightOperand() != null
                        && opm.getRightOperand().getName() != null
                        && opm.getLeftOperand().getContent() != null){
                    TAssignment outputAssignment = ModelFactory.eINSTANCE.createTAssignment();
                    if(ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(opm.getRightOperand().getType())){
                        outputAssignment.setFrom(createBPMNExpressionFromString("getDataOutput('"+dataInput.getId()+"')/"+XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION+":"+opm.getRightOperand().getName()));
                    } else {
                        outputAssignment.setFrom(createBPMNFormalExpressionFromBonitaExpression(opm.getRightOperand()));
                    }
                    outputAssignment.setTo(createBPMNFormalExpressionFromBonitaExpression(opm.getLeftOperand()));
                    outputAssignments.add(outputAssignment);
                }
            }
            if(!tDataOutputAssociation.getAssignment().isEmpty()){
                serviceTask.getDataOutputAssociation().add(tDataOutputAssociation);
                tDataOutputAssociation.setTargetRef(dataOutput.getId());
            }
        }
    }

    protected String getDataReferenceValue(CallActivity callActivity, String bonitaReferenceString) {
        String result = bonitaReferenceString;
        DiagramRepositoryStore drs = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final Expression calledActivityName = callActivity.getCalledActivityName();
        if(calledActivityName != null
                && calledActivityName.getType().equals(ExpressionConstants.CONSTANT_TYPE)
                &&  calledActivityName.getContent() != null){
            String version= null ;
            final Expression calledActivityVersion = callActivity.getCalledActivityVersion();
            if(calledActivityVersion != null
                    && calledActivityVersion.getType().equals(ExpressionConstants.CONSTANT_TYPE)
                    &&  calledActivityVersion.getContent() != null){
                version = calledActivityVersion.getContent();
            }
            AbstractProcess calledProcess = drs.findProcess(calledActivityName.getContent(),version );
            if(calledProcess != null){
                for(Data calledProcessData : calledProcess.getData()){
                    if(calledProcessData.getName().equals(bonitaReferenceString)){
                        result = ModelHelper.getEObjectID(calledProcessData);//it will be the id of the targeted date
                        break;
                    }
                }
            }
        }
        return result;
    }

    private TLinkEventDefinition createTLinkEventDefinition(CatchLinkEvent bonitaEvent,
            TIntermediateCatchEvent bpmnEvent) {
        TLinkEventDefinition eventDef = ModelFactory.eINSTANCE.createTLinkEventDefinition();
        eventDef.setId(EcoreUtil.generateUUID());
        final EList<ThrowLinkEvent> fromLinkEvent = bonitaEvent.getFrom();
        final EList<QName> sourceLink = eventDef.getSource();
        for(ThrowLinkEvent from : fromLinkEvent){
            sourceLink.add((QName.valueOf(from.getName())));
        }
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    protected TSignalEventDefinition createThrowSignalEventDefinition(SignalEvent bonitaEvent, TThrowEvent bpmnEvent) {
        TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
        eventDef.setId(EcoreUtil.generateUUID());
        TSignal tSignal = getOrCreateTSignal(bonitaEvent);
        if(tSignal != null){
            eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
        }
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    protected TTimerEventDefinition createTimerEventDef(AbstractTimerEvent bonitaEvent) {
        TTimerEventDefinition eventDef = ModelFactory.eINSTANCE.createTTimerEventDefinition();
        final Expression conditionExpression = bonitaEvent.getCondition();
        if(conditionExpression != null){
            final String condition = conditionExpression.getContent();//FIXME
            if(condition != null){
                TExpression expression = ModelFactory.eINSTANCE.createTExpression();
                FeatureMapUtil.addText(expression.getMixed(), condition);
                if(DateUtil.isDuration(condition)){
                    eventDef.setTimeDuration(expression);
                } else if(DateUtil.isDate(condition)){
                    eventDef.setTimeDate(expression);
                } else {
                    eventDef.setTimeCycle(expression);
                }
            }
        }
        eventDef.setId("eventdef-"+bonitaEvent.getName());
        return eventDef;
    }

    protected TFlowElement createGateway(FlowElement child) {
        TFlowElement res = null;
        if (child instanceof XORGateway) {
            TExclusiveGateway bpmnGateway = ModelFactory.eINSTANCE.createTExclusiveGateway();        
            res = bpmnGateway;
        } else if (child instanceof ANDGateway) {
            TGateway bpmnGateway = ModelFactory.eINSTANCE.createTParallelGateway();
            res = bpmnGateway;
        } else if (child instanceof InclusiveGateway) {
            TInclusiveGateway bpmnGateway = ModelFactory.eINSTANCE.createTInclusiveGateway();   
            res = bpmnGateway;
        }
        return res;
    }

	protected TFlowElement createActivity(FlowElement child) {
        // Tasks
        TFlowElement res = null;
        if (child instanceof CallActivity) {
            //WARNING in fact this is a call activity
            //TSubProcess bpmnSubprocess = ModelFactory.eINSTANCE.createTSubProcess();
            TCallActivity tCallActivity = ModelFactory.eINSTANCE.createTCallActivity();
            //TODO: construct calledElement ID
            CallActivity cActivity = (CallActivity) child ;
            if(cActivity.getCalledActivityName() != null
                    && cActivity.getCalledActivityName().getType().equals(ExpressionConstants.CONSTANT_TYPE)
                    && cActivity.getCalledActivityName().getContent() != null){
                tCallActivity.setCalledElement(QName.valueOf(cActivity.getCalledActivityName().getContent()));
            }
            //bpmnSubprocess.set
            //errors.add(NLS.bind(Messages.subprocessReferenceLost, child.getName()));
            res = tCallActivity;
        } else if (child instanceof ServiceTask) {
            TServiceTask serviceTask = ModelFactory.eINSTANCE.createTServiceTask();
            res = serviceTask;
        } else if (child instanceof ScriptTask) {
            TScriptTask scriptTask = ModelFactory.eINSTANCE.createTScriptTask();
            res = scriptTask;
        } else if (child instanceof Task) {
            Task bonitaTask = (Task)child;
            TUserTask bpmnTask = ModelFactory.eINSTANCE.createTUserTask();
            final Actor actor = bonitaTask.getActor();
            if(actor != null){
            	EList<TResourceRole> resourceRoles = bpmnTask.getResourceRole();
            	TPerformer role = ModelFactory.eINSTANCE.createTPerformer();
            	role.setResourceRef(QName.valueOf(ModelHelper.getEObjectID(actor)));
            	role.setId(EcoreUtil.generateUUID());
            	resourceRoles.add(role);
            	//TODO: cjeck that a performer is teh well thinsg to use search in teh whole inheritance of ResourceRole
            	//TODO: use assignment if specifying a name
            	//TODO: 
            }
            // TODO performer
            res = bpmnTask;
        } else if (child instanceof SendTask) {
            TSendTask scriptTask = ModelFactory.eINSTANCE.createTSendTask();
            res = scriptTask;
        } else if (child instanceof ReceiveTask) {
            TReceiveTask scriptTask = ModelFactory.eINSTANCE.createTReceiveTask();
            res = scriptTask;
        }
        else if (child instanceof Activity) {
            Activity bonitaActivity = (Activity)child;
            TActivity bpmnActivity = ModelFactory.eINSTANCE.createTTask();
            res = bpmnActivity;
        }
        return res;
    }

    /**
     * @return
     */
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getErrorMessage(){
        return createErrorMessage(getErrors()) ;
    }

    /**
     * @param errors
     * @return
     */
    private String createErrorMessage(List<String> errors) {
        StringBuilder builder = new StringBuilder();
        builder.append(Messages.exportLimitations_message);
        builder.append(SWT.CR);
        for (String error : errors) {
            builder.append(SWT.CR+" * ");
            builder.append(error);
        }
        return builder.toString();
    }

}
