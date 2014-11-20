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
import java.util.List;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
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
import org.bonitasoft.studio.model.process.diagram.providers.ProcessViewProvider;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.RGB;


public class NewDiagramFactory {

    private static final String BASE_VERSION = "1.0"; //$NON-NLS-1$
    private DiagramFileStore fileStore;


    public void create(final DiagramRepositoryStore diagramStore, final IProgressMonitor monitor) {
        monitor.beginTask(Messages.newDiagram, 7);
        final String diagramIdentifier = getNewProcessIdentifier(diagramStore);
        final String diagramName = NamingUtils.convertToId(org.bonitasoft.studio.diagram.custom.i18n.Messages.newFilePrefix + diagramIdentifier);
        final String uniqueFileName = NamingUtils.toDiagramFilename(diagramName, BASE_VERSION);
        final MainProcess model = createInitialModel(diagramName);
        final Diagram diagram = ViewService.createDiagram(model,
                MainProcessEditPart.MODEL_ID,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        diagram.setName(model.getName());
        diagram.setElement(model);

        final ProcessViewProvider processViewProvider = new ProcessViewProvider();

        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        ElementInitializers.getInstance().init_Pool_2007(pool);
        pool.setName(getPoolName(diagramStore, diagramIdentifier));
        pool.setVersion(BASE_VERSION);
        pool.getActors().addAll(createInitialActors());
        model.getElements().add(pool);

        final Node poolNode = processViewProvider.createPool_2007(pool, diagram, -1, true, ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

        monitor.worked(1);

        final Lane lane = ProcessFactory.eINSTANCE.createLane();
        ElementInitializers.getInstance().init_Lane_3007(lane);
        lane.setName(Messages.defaultLaneName);
        lane.setActor(pool.getActors().get(0));
        pool.getElements().add(lane);

        final Node laneNode = processViewProvider.createLane_3007(lane, (View) poolNode.getPersistedChildren().get(1), -1, true,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

        monitor.worked(1);

        final Task task = ProcessFactory.eINSTANCE.createTask();
        lane.getElements().add(task);
        ElementInitializers.getInstance().init_Task_3005(task);
        task.setOverrideActorsOfTheLane(false);


        final View laneCompartmentView = (View) laneNode.getPersistedChildren().get(1);
        final Node stepShape = processViewProvider.createTask_3005(task, laneCompartmentView, -1, true,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        final Bounds taskLayoutConstraint = (Bounds) stepShape.getLayoutConstraint();
        taskLayoutConstraint.setX(160);
        taskLayoutConstraint.setY(60);
        final ShapeStyle shapeStyle = (ShapeStyle) stepShape.getStyle(NotationPackage.Literals.SHAPE_STYLE);
        shapeStyle.setLineColor(FigureUtilities.RGBToInteger(new RGB(44,109,163)));
        shapeStyle.setFillColor(FigureUtilities.RGBToInteger(new RGB(184, 185, 218)));

        monitor.worked(1);

        final StartEvent startEvent = ProcessFactory.eINSTANCE.createStartEvent();
        lane.getElements().add(startEvent);
        ElementInitializers.getInstance().init_StartEvent_3002(startEvent);

        final Node startEventShape = processViewProvider.createStartEvent_3002(startEvent, laneCompartmentView, -1, true,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        final Bounds stepLayoutConstraint = (Bounds) startEventShape.getLayoutConstraint();
        stepLayoutConstraint.setX(60);
        stepLayoutConstraint.setY(68);

        monitor.worked(1);

        final SequenceFlow sequenceFlow = ProcessFactory.eINSTANCE.createSequenceFlow();
        ElementInitializers.getInstance().init_SequenceFlow_4001(sequenceFlow);
        sequenceFlow.setSource(startEvent);
        sequenceFlow.setTarget(task);
        pool.getConnections().add(sequenceFlow);
        final Edge edge = processViewProvider.createSequenceFlow_4001(sequenceFlow, diagram, -1, true, ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        edge.setSource(startEventShape);
        edge.setTarget(stepShape);
        monitor.worked(1);

        fileStore = diagramStore.createRepositoryFileStore(uniqueFileName);
        fileStore.stopResourceListening();
        fileStore.save(Arrays.asList(model, diagram));

        monitor.worked(1);

        createDefaultProcessArtifact(TransactionUtil.getEditingDomain(fileStore.getEMFResource()), model);

        monitor.worked(1);
    }

    protected String getPoolName(final DiagramRepositoryStore diagramStore, final String diagramIdentifier) {
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

    protected void createDefaultProcessArtifact(final TransactionalEditingDomain editingDomain, final MainProcess diagram) {
        final Pool pool = (Pool) diagram.getElements().get(0);
        final String processUUID = ModelHelper.getEObjectID(pool);
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        final IRepositoryFileStore confFile = processConfStore.createRepositoryFileStore(processUUID + ".conf");
        final Configuration conf = ConfigurationFactory.eINSTANCE.createConfiguration();
        conf.setVersion(ModelVersion.CURRENT_VERSION);
        createDefaultActorMapping(conf);
        confFile.save(conf);

        final ApplicationResourceRepositoryStore resourceStore = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
        final LookNFeelRepositoryStore lookNFeelStore = RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID);
        if (artifact == null) {
            final String themeId = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                    .getString(BonitaPreferenceConstants.DEFAULT_APPLICATION_THEME);
            final ApplicationLookNFeelFileStore file = (ApplicationLookNFeelFileStore) lookNFeelStore.getChild(themeId);
            final CompoundCommand templateCommand = WebTemplatesUtil.createAddTemplateCommand(editingDomain, pool, file);
            // add an empty application folder
            editingDomain.getCommandStack().execute(templateCommand);
            final org.eclipse.emf.common.command.Command createDefaultResourceFolders = WebTemplatesUtil.createDefaultResourceFolders(editingDomain, pool);
            if (createDefaultResourceFolders != null) {
                editingDomain.getCommandStack().execute(createDefaultResourceFolders);
            }
        }

    }

    protected void createDefaultActorMapping(final Configuration conf) {
        final ActorMappingsType amType = ActorMappingFactory.eINSTANCE.createActorMappingsType();
        amType.getActorMapping().add(createGroupActorMapping("Employee actor", "/acme"));
        conf.setActorMappings(amType);
    }

    private ActorMapping createGroupActorMapping(final String actorName, final String grouId) {
        final ActorMapping mapping = ActorMappingFactory.eINSTANCE.createActorMapping();
        mapping.setName(actorName);
        final Groups company = ActorMappingFactory.eINSTANCE.createGroups();
        company.getGroup().add(grouId);
        mapping.setGroups(company);
        mapping.setRoles(ActorMappingFactory.eINSTANCE.createRoles());
        mapping.setMemberships(ActorMappingFactory.eINSTANCE.createMembership());
        mapping.setUsers(ActorMappingFactory.eINSTANCE.createUsers());
        return mapping;
    }

    /**
     * @return the artifact
     */
    public DiagramFileStore getNewDiagramFileStore() {
        return fileStore;
    }

    private String getNewProcessIdentifier(final DiagramRepositoryStore store) {
        String processName = Messages.newFilePrefix;
        String fileName = NamingUtils.toDiagramFilename(processName, BASE_VERSION);
        Integer i = 1;
        while (store.getResource().getFile(fileName).exists()) {
            processName = Messages.newFilePrefix + i;
            fileName = NamingUtils.toDiagramFilename(processName, BASE_VERSION);
            i++;
        }
        if (i > 1) {
            return "" + (i - 1);
        } else {
            return "";
        }
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
        final List<AbstractProcess> l = new ArrayList<AbstractProcess>();
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



    protected List<Actor> createInitialActors() {
        final List<Actor> actors = new ArrayList<Actor>(7);
        final Actor initiator = ProcessFactory.eINSTANCE.createActor();
        initiator.setInitiator(true);
        initiator.setName("Employee actor");
        initiator.setDocumentation(Messages.initiatorDescription);
        actors.add(initiator);
        return actors;
    }


    private MainProcess createInitialModel(final String diagramName) {
        final MainProcess proc = ProcessFactory.eINSTANCE.createMainProcess();
        proc.setName(diagramName);
        proc.setVersion("1.0"); //$NON-NLS-1$
        proc.setBonitaVersion(ProductVersion.CURRENT_VERSION);
        proc.setBonitaModelVersion(ModelVersion.CURRENT_VERSION);
        proc.setEnableValidation(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.PREF_ENABLE_VALIDATION));
        proc.setConfigId(getConfigurationId(proc));
        ModelHelper.addDataTypes(proc);
        return proc;
    }


    private Object getConfigurationId(final MainProcess proc) {
        return ConfigurationIdProvider.getConfigurationIdProvider().getConfigurationId(proc);
    }

}
