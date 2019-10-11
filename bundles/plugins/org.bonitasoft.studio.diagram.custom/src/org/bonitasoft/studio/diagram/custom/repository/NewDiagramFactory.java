/**
 * Copyright (C) 2009-2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.diagram.custom.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.providers.CustomProcessViewProvider;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.providers.ElementInitializers;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;

public class NewDiagramFactory {

    /**
     * 
     */
    private static final String POOL_DEFAULT_WIDTH = "poolDefaultWidth";
    private static final String BASE_VERSION = "1.0"; //$NON-NLS-1$
    private DiagramFileStore fileStore;
    private final IRepository repository;
    private final CustomProcessViewProvider processViewProvider;
    private final ProcessFactory processFactory;
    private final IPreferenceStore preferenceStore;

    public NewDiagramFactory(final IRepository repository, final IPreferenceStore preferenceStore) {
        this.repository = repository;
        this.preferenceStore = preferenceStore;
        processViewProvider = new CustomProcessViewProvider();
        processFactory = ProcessFactory.eINSTANCE;
    }

    public void create(final IProgressMonitor monitor) {
        monitor.beginTask(Messages.newDiagram, 7);

        final String diagramIdentifier = getNewProcessIdentifier();
        final Map<Class<?>, EObject> domainElements = createlModel(processFactory, diagramIdentifier,
                ElementInitializers.getInstance(), ModelVersion.CURRENT_VERSION, monitor);
        final Diagram diagram = createViews(domainElements, monitor);

        final MainProcess mainProcess = (MainProcess) domainElements.get(MainProcess.class);
        saveNewResource(mainProcess, diagram, monitor);
        createDefaultProcessArtifact(mainProcess, monitor);
    }

    protected Diagram createViews(final Map<Class<?>, EObject> domainElements, final IProgressMonitor monitor) {
        final MainProcess mainProcess = (MainProcess) domainElements.get(MainProcess.class);
        final Diagram diagram = ViewService.createDiagram(mainProcess,
                MainProcessEditPart.MODEL_ID,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        diagram.setName(mainProcess.getName());
        diagram.setElement(mainProcess);

        final Node poolNode = processViewProvider.createPool_2007(domainElements.get(Pool.class), diagram, -1, true,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

        final Node laneNode = processViewProvider.createLane_3007(domainElements.get(Lane.class),
                (View) poolNode.getPersistedChildren().get(1), -1, true,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        monitor.worked(1);

        final View laneCompartmentView = (View) laneNode.getPersistedChildren().get(1);
        final Node stepShape = processViewProvider.createTask_3005(domainElements.get(Task.class), laneCompartmentView, -1,
                true,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        final Bounds taskLayoutConstraint = (Bounds) stepShape.getLayoutConstraint();
        taskLayoutConstraint.setX(160);
        taskLayoutConstraint.setY(60);
        monitor.worked(1);

        final Node startEventShape = processViewProvider.createStartEvent_3002(domainElements.get(StartEvent.class),
                laneCompartmentView, -1, true,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        final Bounds stepLayoutConstraint = (Bounds) startEventShape.getLayoutConstraint();
        stepLayoutConstraint.setX(60);
        stepLayoutConstraint.setY(68);
        monitor.worked(1);

        final Connector edge = (Connector) processViewProvider.createSequenceFlow_4001(
                domainElements.get(SequenceFlow.class), diagram, -1, true,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        edge.setSource(startEventShape);
        edge.setTarget(stepShape);
        monitor.worked(1);

        return diagram;
    }

    protected void saveNewResource(final MainProcess model, final Diagram diagram, final IProgressMonitor monitor) {
        final DiagramRepositoryStore diagramStore = repository.getRepositoryStore(DiagramRepositoryStore.class);
        final String uniqueFileName = NamingUtils.toDiagramFilename(model.getName(), BASE_VERSION);
        fileStore = diagramStore.createRepositoryFileStore(uniqueFileName);
        fileStore.stopResourceListening();
        fileStore.save(Arrays.asList(model, diagram));

        monitor.worked(1);
    }

    protected String getPoolName(final String diagramIdentifier) {
        final DiagramRepositoryStore diagramStore = repository.getRepositoryStore(DiagramRepositoryStore.class);
        String newProcessName = Messages.newProcessPrefix;
        if (diagramIdentifier != null && !diagramIdentifier.isEmpty()) {
            Integer i = null;
            try {
                i = Integer.parseInt(diagramIdentifier);
            } catch (final NumberFormatException e) {
                i = 1;
            }
            newProcessName = Messages.newProcessPrefix + i;
            final List<AbstractProcess> allProcess = getAllProcess(diagramStore);
            while (processExist(allProcess, newProcessName)) {
                i++;
                newProcessName = Messages.newProcessPrefix + i;
            }
        }
        return newProcessName;
    }

    protected void createDefaultProcessArtifact(final MainProcess diagram,
            final IProgressMonitor monitor) {
        final Pool pool = (Pool) diagram.getElements().get(0);
        final String processUUID = ModelHelper.getEObjectID(pool);
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        final IRepositoryFileStore confFile = processConfStore.createRepositoryFileStore(processUUID + ".conf");
        final Configuration conf = ConfigurationFactory.eINSTANCE.createConfiguration();
        conf.setVersion(ModelVersion.CURRENT_VERSION);
        createDefaultActorMapping(conf);
        confFile.save(conf);
        monitor.worked(1);
    }

    protected void createDefaultActorMapping(final Configuration conf) {
        final ActorMappingsType amType = ActorMappingFactory.eINSTANCE.createActorMappingsType();
        amType.getActorMapping().add(createGroupActorMapping("Employee actor", "/acme"));
        conf.setActorMappings(amType);
    }

    private ActorMapping createGroupActorMapping(final String actorName, final String grouId) {
        final ActorMappingFactory actorMappingFactory = ActorMappingFactory.eINSTANCE;
        final ActorMapping mapping = actorMappingFactory.createActorMapping();
        mapping.setName(actorName);
        final Groups company = actorMappingFactory.createGroups();
        company.getGroup().add(grouId);
        mapping.setGroups(company);
        mapping.setRoles(actorMappingFactory.createRoles());
        mapping.setMemberships(actorMappingFactory.createMembership());
        mapping.setUsers(actorMappingFactory.createUsers());
        return mapping;
    }

    public DiagramFileStore getNewDiagramFileStore() {
        return fileStore;
    }

    private String getNewProcessIdentifier() {
        final DiagramRepositoryStore store = repository.getRepositoryStore(DiagramRepositoryStore.class);
        String processName = Messages.newFilePrefix;
        String fileName = NamingUtils.toDiagramFilename(processName, BASE_VERSION);
        int i = 1;
        while (store.getChild(fileName, true) != null) {
            processName = Messages.newFilePrefix + i;
            fileName = NamingUtils.toDiagramFilename(processName, BASE_VERSION);
            i++;
        }
        if (i > 1) {
            return String.valueOf(i - 1);
        }
        return "";
    }

    private Boolean processExist(final List<AbstractProcess> l, final String newProcessName) {
        for (final AbstractProcess abstractProcess : l) {
            if (abstractProcess.getName().equals(newProcessName) && abstractProcess.getVersion().equals("1.0")) {
                return true;
            }
        }
        return false;
    }

    private List<AbstractProcess> getAllProcess(final DiagramRepositoryStore diagramStore) {
        final List<AbstractProcess> l = new ArrayList<>();
        for (final DiagramFileStore diagramFileStore : diagramStore.getChildren()) {
            final MainProcess m = diagramFileStore.getContent();
            l.addAll(ModelHelper.getAllProcesses(m));
        }
        return l;
    }

    protected void createAssignableActor(final DiagramEditPart diagramEp,
            final CompoundCommand cc, final List<Actor> actors, final Lane lane) {
        cc.append(SetCommand.create(
                diagramEp.getEditingDomain(),
                lane,
                ProcessPackage.Literals.ASSIGNABLE__ACTOR,
                actors.get(0)));
    }

    protected List<Actor> createInitialActors(final ProcessFactory factory) {
        final List<Actor> actors = new ArrayList<>();
        final Actor initiator = factory.createActor();
        initiator.setInitiator(true);
        initiator.setName("Employee actor");
        initiator.setDocumentation(Messages.initiatorDescription);
        actors.add(initiator);
        return actors;
    }

    protected Map<Class<?>, EObject> createlModel(final ProcessFactory processFactory,
            final String diagramIdentifier,
            final ElementInitializers initializers,
            final String modelVersion,
            final IProgressMonitor monitor) {
        final Map<Class<?>, EObject> domainElements = new HashMap<>();
        final String diagramName = NamingUtils
                .convertToValidURI(org.bonitasoft.studio.diagram.custom.i18n.Messages.newFilePrefix + diagramIdentifier);
        final MainProcess mainProcess = processFactory.createMainProcess();
        mainProcess.setName(diagramName);
        mainProcess.setVersion(BASE_VERSION);
        mainProcess.setBonitaVersion(ProductVersion.CURRENT_VERSION);
        mainProcess.setBonitaModelVersion(modelVersion);
        mainProcess.setEnableValidation(true);
        mainProcess.setConfigId(getConfigurationId(mainProcess));
        ModelHelper.addDataTypes(mainProcess);
        domainElements.put(MainProcess.class, mainProcess);

        //Add Pool
        final Pool pool = processFactory.createPool();
        initializers.init_Pool_2007(pool);
        pool.setName(getPoolName(diagramIdentifier));
        pool.setVersion(BASE_VERSION);
        pool.getActors().addAll(createInitialActors(processFactory));
        mainProcess.getElements().add(pool);
        domainElements.put(Pool.class, pool);

        //Add Lane
        final Lane lane = processFactory.createLane();
        initializers.init_Lane_3007(lane);
        lane.setName(Messages.defaultLaneName);
        lane.setActor(pool.getActors().get(0));
        pool.getElements().add(lane);
        domainElements.put(Lane.class, lane);

        //Add Start Event
        final StartEvent startEvent = processFactory.createStartEvent();
        lane.getElements().add(startEvent);
        initializers.init_StartEvent_3002(startEvent);
        domainElements.put(StartEvent.class, startEvent);

        //Add Task
        final Task task = processFactory.createTask();
        lane.getElements().add(task);
        initializers.init_Task_3005(task);
        task.setOverrideActorsOfTheLane(false);
        domainElements.put(Task.class, task);

        //Add Sequence Flow
        final SequenceFlow sequenceFlow = processFactory.createSequenceFlow();
        initializers.init_SequenceFlow_4001(sequenceFlow);
        sequenceFlow.setSource(startEvent);
        sequenceFlow.setTarget(task);
        pool.getConnections().add(sequenceFlow);
        domainElements.put(SequenceFlow.class, sequenceFlow);

        return domainElements;
    }

    protected Object getConfigurationId(final MainProcess proc) {
        return ConfigurationIdProvider.getConfigurationIdProvider().getConfigurationId(proc);
    }

    public void setDefaultPoolWidth(final int defaultWidth) {
        final IPreferenceStore store = (IPreferenceStore) ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT
                .getPreferenceStore();
        store.setDefault(POOL_DEFAULT_WIDTH, defaultWidth);
    }

}
