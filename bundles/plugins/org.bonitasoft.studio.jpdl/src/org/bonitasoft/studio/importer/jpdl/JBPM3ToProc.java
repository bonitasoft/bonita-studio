/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.jpdl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.importer.builder.IProcBuilder.DataType;
import org.bonitasoft.studio.importer.builder.IProcBuilder.EventType;
import org.bonitasoft.studio.importer.builder.IProcBuilder.GatewayType;
import org.bonitasoft.studio.importer.builder.ProcBuilder;
import org.bonitasoft.studio.importer.builder.ProcBuilderException;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.jbpm.jpdl32.AssignmentType;
import org.jbpm.jpdl32.DecisionType;
import org.jbpm.jpdl32.DocumentRoot;
import org.jbpm.jpdl32.EndStateType;
import org.jbpm.jpdl32.ForkType;
import org.jbpm.jpdl32.JoinType;
import org.jbpm.jpdl32.MailNodeType;
import org.jbpm.jpdl32.MailType;
import org.jbpm.jpdl32.NodeType;
import org.jbpm.jpdl32.ProcessDefinitionType;
import org.jbpm.jpdl32.ProcessStateType;
import org.jbpm.jpdl32.StartStateType;
import org.jbpm.jpdl32.StateType;
import org.jbpm.jpdl32.SubProcessType;
import org.jbpm.jpdl32.SwimlaneType;
import org.jbpm.jpdl32.TaskNodeType;
import org.jbpm.jpdl32.TaskType;
import org.jbpm.jpdl32.TransitionType;
import org.jbpm.jpdl32.VariableType;
import org.jbpm.jpdl32.util.jpdl32ResourceFactoryImpl;
import org.jbpm.jpdl32.util.jpdl32ResourceImpl;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier - use new generic API interface
 */
public class JBPM3ToProc extends ToProcProcessor {

    private static final String JBPM3_ASSIGNMENT_TO_BONITA_ROLE_RESOLVER = "jBPM3ToBonitaRoleResolver";
    private static final String MAIL_CONNECTOR_ID = "email";
    private static final String MAIL_CONNECTOR_TO = "setTo";
    private static final String MAIL_CONNECTOR_SUBJECT = "setSubject";
    private static final String MAIL_CONNECTOR_BODY = "setMessage";
    private final List<TransitionDesc> transitions = new ArrayList<TransitionDesc>();
    private final Map<String, String> groups = new HashMap<String, String>();
    private final Map<String, Point> locations = new HashMap<String, Point>();
    protected Point poolSize;
    private ProcBuilder builder;
    private File result;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#createDiagram(org.eclipse.emf.common.util.URI, org.eclipse.emf.common.util.URI,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public File createDiagram(final URL sourceURL, final IProgressMonitor progressMonitor) throws Exception {
        progressMonitor.beginTask(Messages.importFromJBPM, IProgressMonitor.UNKNOWN);
        URI sourceJBPMUri = URI.createURI(sourceURL.toURI().toString());
        final URI gpdFileName = sourceJBPMUri.trimSegments(1).appendSegment("gpd.xml");

        // copy files
        final File tmpDir = new File(ProjectUtil.getBonitaStudioWorkFolder(), "importJBPMtemp");
        tmpDir.mkdirs();

        final File processDefFile = new File(tmpDir, "processdefinition.xml");
        try (final InputStream is = FileLocator.toFileURL(new java.net.URI(sourceJBPMUri.toString()).toURL()).openStream();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                FileOutputStream out = new FileOutputStream(processDefFile);) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.replace("urn:jbpm.org:jpdl-3.2", "");
                out.write(line.getBytes());
            }
        }

        final File gpdFileTmp = new File(tmpDir, "gpd.xml");
        try (FileOutputStream out = new FileOutputStream(gpdFileTmp);
                final InputStream src = new java.net.URL(gpdFileName.toString()).openStream();) {
            FileUtil.copy(src, out);
        }
        progressMonitor.worked(1);

        // Update URIs
        sourceJBPMUri = URI.createURI(processDefFile.toURI().toString());

        // parse resource
        final Resource resource = new jpdl32ResourceFactoryImpl().createResource(sourceJBPMUri);
        final Map<String, String> options = new HashMap<String, String>();
        options.put(jpdl32ResourceImpl.NO_NAMESPACE_SCHEMA_LOCATION, Boolean.TRUE.toString());
        resource.load(options);
        final DocumentRoot docRoot = (DocumentRoot) resource.getContents().get(0);
        final ProcessDefinitionType processDef = docRoot.getProcessDefinition();
        final String diagramName = sourceJBPMUri.lastSegment();
        progressMonitor.worked(1);

        // parse GPD
        try {

            final URI gpd = gpdFileName; // TODO test
            final URL gpdUrl = FileLocator.toFileURL(new URL(gpd.toString()));
            final File gpdFile = new File(gpdUrl.getFile());
            if (gpdFile.exists()) {
                populateLocationMap(gpdFile);
            }
        } catch (final IOException ex) {
            BonitaStudioLog.log("Could not use gpd for " + sourceJBPMUri.toString());
        }
        progressMonitor.worked(1);

        builder = new ProcBuilder(progressMonitor);
        result = File.createTempFile(diagramName, ".proc");
        result.deleteOnExit();
        builder.createDiagram(diagramName, diagramName, "1.0", result);
        try {
            importFromJBPM(processDef);
            gpdFileTmp.delete();
            processDefFile.delete();
            tmpDir.delete();
            builder.done();
            return result;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;

    }

    /**
     * @param gpdFile
     */
    private void populateLocationMap(final File gpdFile) {
        try (final FileInputStream fis = new FileInputStream(gpdFile);) {
            final XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(new DefaultHandler() {

                @Override
                public void startElement(final String namespaceURI, final String localName,
                        final String qualifiedName, final Attributes atts) throws SAXException {

                    // Locations and sizes are mirrored to read diagram from left to right
                    if (localName.equals("root-container") || localName.equals("process-diagram")) {
                        poolSize = new Point();
                        poolSize.x = Integer.parseInt(atts.getValue("height")) + 50;//50 for margin
                        poolSize.y = Integer.parseInt(atts.getValue("width")) + 50;//50 for margin
                    } else if (localName.equals("node")) {
                        final Point nodeLocation = new Point();
                        nodeLocation.x = Integer.parseInt(atts.getValue("y")) + 50;//50 for margin
                        nodeLocation.y = poolSize.x - Integer.parseInt(atts.getValue("x")) + 50;//30 for margin
                        final String nodeName = atts.getValue("name");
                        locations.put(nodeName, nodeLocation);
                    }

                }
            });
            reader.parse(new InputSource(fis));
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
    }

    /**
     * @param processDef
     * @param modelProcess
     * @param diagram
     * @throws ProcBuilderException
     */
    protected void importFromJBPM(final ProcessDefinitionType processDef) throws ProcBuilderException {

        final String processDefNameConvertedInId = NamingUtils.convertToId(processDef.getName());
        builder.addPool(processDefNameConvertedInId, processDef.getName(), "1.0", new Point(0, 0),
                new Dimension(poolSize.x, poolSize.y));

        //		modelProcess.getIncludedEntries().addAll(new AddGroupPerformer(null).getEnclosingJarArtifactNames(USER_LIST_ROLE_RESOLVER));
        //		modelProcess.getIncludedEntries().addAll(new AddGroupPerformer(null).getEnclosingJarArtifactNames(MAIL_CONNECTOR_ID));

        /*
         * Still TODO
         * -TaskType -> Assignement
         * -ActionType -> Connectors
         * -EventType
         * -MailType
         * -SuperStateType
         */

        for (final SwimlaneType swimlane : processDef.getSwimlane()) {
            final AssignmentType assignment = swimlane.getAssignment();
            if (assignment != null) {
                final String groupId = createGroupIdFromAssignment(assignment);
                groups.put(swimlane.getName(), groupId);
            }
        }

        for (final StartStateType jpdl : processDef.getStartState()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addEvent(idFromJpdl, jpdlName, locations.get(jpdlName), null, EventType.START);
            builder.addDescription(toBonitaString(jpdl.getDescription()));

            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }
        }
        for (final TaskNodeType jpdl : processDef.getTaskNode()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addTask(idFromJpdl, jpdlName, locations.get(jpdlName), null,
                    org.bonitasoft.studio.importer.builder.IProcBuilder.TaskType.HUMAN);
            for (final TaskType task : jpdl.getTask()) {
                for (final AssignmentType assignment : task.getAssignment()) {
                    final String groupId = createGroupIdFromAssignment(assignment);
                    builder.addAssignableActor(groupId);
                }
                if (task.getSwimlane() != null && task.getSwimlane().trim().length() > 0) {
                    final String groupId = groups.get(task.getSwimlane());
                    if (groupId != null) {
                        builder.addAssignableActor(groupId);
                    }
                }
            }

            builder.addDescription(toBonitaString(jpdl.getDescription()));
            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }
        }
        for (final DecisionType jpdl : processDef.getDecision()) {

            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addGateway(idFromJpdl, jpdlName, locations.get(jpdlName), null, GatewayType.XOR);
            builder.addDescription(toBonitaString(jpdl.getDescription()));
            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }
        }
        for (final ForkType jpdl : processDef.getFork()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addGateway(idFromJpdl, jpdlName, locations.get(jpdlName), null, GatewayType.AND);
            builder.addDescription(toBonitaString(jpdl.getDescription()));
            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }
        }
        for (final JoinType jpdl : processDef.getJoin()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addGateway(idFromJpdl, jpdlName, locations.get(jpdlName), null, GatewayType.AND);
            builder.addDescription(toBonitaString(jpdl.getDescription()));
            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }
        }
        for (final StateType jpdl : processDef.getState()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addTask(idFromJpdl, jpdlName, locations.get(jpdlName), null,
                    org.bonitasoft.studio.importer.builder.IProcBuilder.TaskType.ABSTRACT);
            builder.addDescription(toBonitaString(jpdl.getDescription()));
            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }
        }
        for (final NodeType jpdl : processDef.getNode()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addTask(idFromJpdl, jpdlName, locations.get(jpdlName), null,
                    org.bonitasoft.studio.importer.builder.IProcBuilder.TaskType.SERVICE);
            builder.addDescription(toBonitaString(jpdl.getDescription()));
            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }

            // Copy-pasted because no inheritance in jPDL Ecore
            final MailType jpdlMail = jpdl.getMail();
            if (jpdlMail != null) {
                builder.addConnector(MAIL_CONNECTOR_ID, MAIL_CONNECTOR_ID, MAIL_CONNECTOR_ID, "1.0",
                        ConnectorEvent.ON_FINISH, false);
                final String mailTo = jpdlMail.getTo();
                if (mailTo != null && mailTo.trim().length() > 0) {
                    builder.addConnectorParameter(MAIL_CONNECTOR_TO, toBonitaString(mailTo));
                } else if (jpdlMail.getActors() != null && jpdlMail.getActors().trim().length() > 0) {
                    builder.addConnectorParameter(MAIL_CONNECTOR_TO, toBonitaString(jpdlMail.getActors()));
                }
                final EList<String> mailSubject = jpdlMail.getSubject();
                if (mailSubject != null && mailSubject.size() > 0) {
                    builder.addConnectorParameter(MAIL_CONNECTOR_SUBJECT, toBonitaString(mailSubject));
                } else if (jpdlMail.getSubject1() != null && jpdlMail.getSubject1().trim().length() > 0) {
                    builder.addConnectorParameter(MAIL_CONNECTOR_SUBJECT, toBonitaString(jpdlMail.getSubject1()));
                }
                final EList<String> mailText = jpdlMail.getText();
                if (mailText != null && mailText.size() > 0) {
                    builder.addConnectorParameter(MAIL_CONNECTOR_BODY, toBonitaString(mailText));
                } else if (jpdlMail.getText1() != null && jpdlMail.getText1().trim().length() > 0) {
                    builder.addConnectorParameter(MAIL_CONNECTOR_BODY, toBonitaString(jpdlMail.getText1()));
                }
            }
        }
        for (final MailNodeType jpdl : processDef.getMailNode()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addTask(idFromJpdl, jpdlName, locations.get(jpdlName), null,
                    org.bonitasoft.studio.importer.builder.IProcBuilder.TaskType.SERVICE);
            builder.addDescription(toBonitaString(jpdl.getDescription()));
            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }

            builder.addConnector(MAIL_CONNECTOR_ID, MAIL_CONNECTOR_ID, MAIL_CONNECTOR_ID, "1.0", ConnectorEvent.ON_FINISH,
                    false);
            final String mailTo = jpdl.getTo();
            if (mailTo != null && mailTo.trim().length() > 0) {
                builder.addConnectorParameter(MAIL_CONNECTOR_TO, toBonitaString(mailTo));
            } else if (jpdl.getActors() != null && jpdl.getActors().trim().length() > 0) {
                builder.addConnectorParameter(MAIL_CONNECTOR_TO, toBonitaString(jpdl.getActors()));
            }
            final EList<String> mailSubject = jpdl.getSubject();
            if (mailSubject != null && mailSubject.size() > 0) {
                builder.addConnectorParameter(MAIL_CONNECTOR_SUBJECT, toBonitaString(mailSubject));
            } else if (jpdl.getSubject1() != null && jpdl.getSubject1().trim().length() > 0) {
                builder.addConnectorParameter(MAIL_CONNECTOR_SUBJECT, toBonitaString(jpdl.getSubject1()));
            }
            final EList<String> mailText = jpdl.getText();
            if (mailText != null && mailText.size() > 0) {
                builder.addConnectorParameter(MAIL_CONNECTOR_BODY, toBonitaString(mailText));
            } else if (jpdl.getText1() != null && jpdl.getText1().trim().length() > 0) {
                builder.addConnectorParameter(MAIL_CONNECTOR_BODY, toBonitaString(jpdl.getText1()));
            }
        }

        for (final ProcessStateType jpdl : processDef.getProcessState()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addTask(idFromJpdl, jpdlName, locations.get(jpdlName), null,
                    org.bonitasoft.studio.importer.builder.IProcBuilder.TaskType.CALL_ACTIVITY);
            builder.addDescription(toBonitaString(jpdl.getDescription()));
            for (final TransitionType jpdlTransition : jpdl.getTransition()) {
                transitions.add(new TransitionDesc(idFromJpdl, jpdlTransition));
            }

            for (final VariableType jpdlVariableType : jpdl.getVariable()) {
                builder.addData(jpdlVariableType.getName(), jpdlVariableType.getName(), null, false, DataType.STRING);
            }

            if (jpdl.getSubProcess() != null && jpdl.getSubProcess().size() > 0) {
                final SubProcessType subProcessType = jpdl.getSubProcess().get(0);
                builder.addCallActivityTargetProcess(NamingUtils.convertToId(subProcessType.getName()),
                        subProcessType.getVersion().toString());
            }
            //TODO: manage data mapping
        }
        for (final EndStateType jpdl : processDef.getEndState()) {
            final String jpdlName = jpdl.getName();
            final String idFromJpdl = NamingUtils.convertToId(jpdlName);
            builder.addEvent(idFromJpdl, jpdlName, locations.get(jpdlName), null, EventType.END);
            builder.addDescription(toBonitaString(jpdl.getDescription()));
        }

        // Transitions
        for (final TransitionDesc transition : transitions) {
            builder.addSequenceFlow(transition.getName(), transition.getSource(), transition.getTo(), false, null, null,
                    null);
            if (transition.getCondition() != null && !transition.getCondition().isEmpty()) {
                builder.addSequenceFlowCondition(transition.getCondition(), ExpressionConstants.GROOVY,
                        ExpressionConstants.SCRIPT_TYPE);
            }
        }

    }

    /**
     * @param to
     * @return
     */
    private String toBonitaString(final String src) {
        return src.replace("#{", "${");
    }

    /**
     * @param assignment
     * @return
     *         the group of the assignment or null if the assignment is null
     * @throws ProcBuilderException
     */
    private String createGroupIdFromAssignment(final AssignmentType assignment) throws ProcBuilderException {
        if (assignment != null) {
            final String class_ = assignment.getClass_();
            if (class_ != null && !class_.trim().isEmpty()) {
                builder.addActor(JBPM3_ASSIGNMENT_TO_BONITA_ROLE_RESOLVER, "");
                return JBPM3_ASSIGNMENT_TO_BONITA_ROLE_RESOLVER;
            } else {
                final String actorId = assignment.getActorId();
                if (actorId != null && !actorId.trim().isEmpty()) {
                    builder.addActor(actorId, "");
                    ///builder.addConnectorParameter(USER_LIST_ROLE_RESOLVER_PARAM, actorId);
                    return actorId;
                } else {
                    final String expression = assignment.getExpression();
                    if (expression != null && !expression.trim().isEmpty()) {
                        final String expressionId = NamingUtils.convertToId(toBonitaString(expression));
                        builder.addActor(expression, "");
                        //    builder.addConnectorParameter(USER_LIST_ROLE_RESOLVER_PARAM, toBonitaString(expression));
                        return toBonitaString(expressionId);
                    } else {
                        final String pooledActors = assignment.getPooledActors();
                        if (pooledActors != null && !pooledActors.trim().isEmpty()) {
                            final String pooledActorsId = pooledActors;
                            builder.addActor(pooledActors, "");
                            return pooledActorsId;
                        }
                    }
                }
            }
            return "";
        }
        return null;
    }

    /**
     * @param description
     * @return
     */
    private String toBonitaString(final EList<String> description) {
        final StringBuilder res = new StringBuilder();
        for (final String desc : description) {
            res.append(desc);
            res.append('\n');
        }
        return res.toString().trim().replace("#{", "${");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#getExtension()
     */
    @Override
    public String getExtension() {
        return ".xml";
    }

    @Override
    public List<File> getResources() {
        return Collections.singletonList(result);
    }

}