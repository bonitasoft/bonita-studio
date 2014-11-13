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
package org.bonitasoft.studio.diagram.custom.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
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
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;


public class NewDiagramCommandHandler extends AbstractHandler {


    private static final String BASE_VERSION = "1.0"; //$NON-NLS-1$
    private static final int SCALE = 1000;
    private String diagramName;
    private String diagramIdentifier;
    private DiagramRepositoryStore diagramStore;
    private DiagramFileStore fileStore;

    public NewDiagramCommandHandler() {

    }


    @Override
    public DiagramFileStore execute(final ExecutionEvent event) throws ExecutionException {
        final IRepository repository = RepositoryManager.getInstance().getCurrentRepository();
        diagramStore = repository.getRepositoryStore(DiagramRepositoryStore.class);
        try {
            final IRunnableWithProgress runnable = new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.newDiagram, 40);

                    diagramIdentifier = getNewProcessIdentifier(diagramStore);
                    monitor.worked(1 * SCALE);
                    diagramName = NamingUtils.convertToId(org.bonitasoft.studio.diagram.custom.i18n.Messages.newFilePrefix + diagramIdentifier);
                    monitor.worked(1 * SCALE);
                    final String uniqueFileName = NamingUtils.toDiagramFilename(diagramName, BASE_VERSION);
                    monitor.worked(1 * SCALE);
                    fileStore = diagramStore.createRepositoryFileStore(uniqueFileName);
                    final Resource emfResource = fileStore.getEMFResource();
                    monitor.worked(1 * SCALE);
                    final MainProcess model = createInitialModel();
                    monitor.worked(1 * SCALE);
                    final Diagram diagram = ViewService.createDiagram(model,
                            MainProcessEditPart.MODEL_ID,
                            ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                    monitor.worked(1 * SCALE);
                    diagram.setName(model.getName());
                    monitor.worked(1 * SCALE);
                    diagram.setElement(model);
                    monitor.worked(1 * SCALE);
                    fileStore.save(Arrays.asList(model, diagram));
                    monitor.worked(1 * SCALE);
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            final Diagram d = (Diagram) emfResource.getContents().get(1);
                            monitor.worked(1 * SCALE);
                            buildDiagram(d, monitor);
                            monitor.worked(1 * SCALE);
                            final IEditorPart editor = (IEditorPart) fileStore.open();
                            monitor.worked(1 * SCALE);
                            if (editor instanceof DiagramEditor) {
                                final String author = System.getProperty("user.name", "unknown");
                                final TransactionalEditingDomain editingDomain = ((DiagramEditor) editor).getDiagramEditPart().getEditingDomain();
                                editingDomain.getCommandStack().execute(
                                        SetCommand.create(editingDomain,
                                                ((DiagramEditor) editor).getDiagramEditPart().resolveSemanticElement(),
                                                ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR, author));
                            }
                            monitor.worked(1 * SCALE);
                            //clear the OperationHistory because it implies otherwise and that we don't need undo/redo for the basic creation.
                            OperationHistoryFactory.getOperationHistory().dispose((IUndoContext) editor.getAdapter(IUndoContext.class), true, true, true);
                            monitor.worked(1 * SCALE);
                        }

                    });
                    monitor.done();
                }
            };

            final IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            progressService.run(true, false, runnable);

        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }

        return fileStore;
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
            return "" + (i - 1); //$NON-NLS-1$
        } else {
            return ""; //$NON-NLS-1$
        }
    }


    protected void buildDiagram(final Diagram diagram, final IProgressMonitor monitor) {
        //  final ResourceSet rSet = diagram.eResource().getResourceSet();
        monitor.worked(1 * SCALE);
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram.eResource());
        monitor.worked(1 * SCALE);
        final Shell shell = new Shell();
        monitor.worked(1 * SCALE);
        final DiagramEditPart diagramEp = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell,
                ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        monitor.worked(1 * SCALE);
        diagramEp.refresh();
        monitor.worked(1 * SCALE);
        CompoundCommand cc;
        monitor.worked(1 * SCALE);
        final List<Actor> actors = createPool(diagramEp);
        monitor.worked(1 * SCALE);
        final String poolSemanticHint = ProcessVisualIDRegistry.getType(PoolEditPart.VISUAL_ID);
        monitor.worked(1 * SCALE);
        final String poolCompartmentSemanticHint = ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID);
        monitor.worked(1 * SCALE);
        final PoolEditPart poolEditPart = (PoolEditPart) diagramEp.getChildBySemanticHint(poolSemanticHint);
        monitor.worked(1 * SCALE);
        final PoolPoolCompartmentEditPart poolCompartment = (PoolPoolCompartmentEditPart) poolEditPart.getChildBySemanticHint(poolCompartmentSemanticHint);
        monitor.worked(1 * SCALE);
        final ViewAndElementDescriptor laneViewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.Lane_3007)),
                Node.class,
                ((IHintedType) ProcessElementTypes.Lane_3007).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());
        monitor.worked(1 * SCALE);
        final CreateViewAndElementRequest laneReq = new CreateViewAndElementRequest(laneViewDescriptor);
        monitor.worked(1 * SCALE);
        laneReq.setLocation(new Point(0, 0));
        monitor.worked(1 * SCALE);
        poolCompartment.getDiagramEditDomain().getDiagramCommandStack()
                .execute(poolCompartment.getCommand(laneReq), new SubProgressMonitor(monitor, 1 * SCALE));
        monitor.worked(1 * SCALE);
        cc = new CompoundCommand();
        monitor.worked(1 * SCALE);
        final Lane lane = (Lane) laneReq.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class);
        monitor.worked(1 * SCALE);
        cc.append(SetCommand.create(
                diagramEp.getEditingDomain(),
                lane,
                ProcessPackage.Literals.ELEMENT__NAME,
                Messages.defaultLaneName));

        monitor.worked(1 * SCALE);
        createAssignableActor(diagramEp, cc, actors, lane);
        monitor.worked(1 * SCALE);
        diagramEp.getEditingDomain().getCommandStack().execute(cc);
        final LaneLaneCompartmentEditPart laneCompartment = createLaneCompartment(diagramEp, poolCompartment);
        monitor.worked(1 * SCALE);
        createStartEventTaskAndSeqFlow(diagramEp, poolCompartment, laneCompartment);
        monitor.worked(1 * SCALE);
        createDefaultProcessArtifact(editingDomain, fileStore.getContent());
        monitor.worked(1 * SCALE);
        fileStore.save(null);
        diagramEp.deactivate();
        shell.dispose();
        // editingDomain.dispose();
        monitor.worked(1 * SCALE);
    }

    private Boolean processExist(final List<AbstractProcess> l, final String newProcessName) {
        for (final AbstractProcess abstractProcess : l) {
            if (abstractProcess.getName().equals(newProcessName) && abstractProcess.getVersion().equals("1.0")) {
                return true;
            }
        }
        return false;
    }

    private List<AbstractProcess> getAllProcess() {
        final List<AbstractProcess> l = new ArrayList<AbstractProcess>();
        for (final DiagramFileStore diagramFileStore : diagramStore.getChildren()) {
            final MainProcess m = diagramFileStore.getContent();
            l.addAll(ModelHelper.getAllProcesses(m));
        }
        return l;
    }

    protected List<Actor> createPool(final DiagramEditPart diagramEp) {
        final ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.Pool_2007)),
                Node.class,
                ((IHintedType) ProcessElementTypes.Pool_2007).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());

        final CreateViewAndElementRequest req = new CreateViewAndElementRequest(viewDescriptor);
        diagramEp.getDiagramEditDomain().getDiagramCommandStack().execute(diagramEp.getCommand(req));
        final CompoundCommand cc = new CompoundCommand();
        final AbstractProcess pool = (AbstractProcess) req.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class);
        //pool.eAdapters().add(new PoolNotificationListener());

        String newProcessName = Messages.newProcessPrefix;
        if (diagramIdentifier != null && !diagramIdentifier.isEmpty()) {
            Integer i = null;
            try {
                i = Integer.parseInt(diagramIdentifier);
            } catch (final NumberFormatException e) {
                i = 1;
            }
            newProcessName = Messages.newProcessPrefix + i;
            final List<AbstractProcess> allProcess = getAllProcess();
            while (processExist(allProcess, newProcessName)) {
                i++;
                newProcessName = Messages.newProcessPrefix + i;
            }
        }

        final SetCommand setPoolNameCommand = new SetCommand(
                diagramEp.getEditingDomain(),
                pool,
                ProcessPackage.Literals.ELEMENT__NAME,
                newProcessName);
        cc.append(setPoolNameCommand);
        final SetCommand setPoolVersionCommand = new SetCommand(
                diagramEp.getEditingDomain(),
                pool,
                ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION, "1.0"); //$NON-NLS-1$
        cc.append(setPoolVersionCommand);
        final List<Actor> actors = createInitialActors();
        cc.append(AddCommand.create(diagramEp.getEditingDomain(),
                pool,
                ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS,
                actors));


        diagramEp.getEditingDomain().getCommandStack().execute(cc);
        return actors;
    }


    protected void createAssignableActor(final DiagramEditPart diagramEp,
            final CompoundCommand cc, final List<Actor> actors, final Lane lane) {
        cc.append(SetCommand.create(
                diagramEp.getEditingDomain(),
                lane,
                ProcessPackage.Literals.ASSIGNABLE__ACTOR,
                actors.get(0)));
    }


    protected LaneLaneCompartmentEditPart createLaneCompartment(final DiagramEditPart diagramEp,
            final PoolPoolCompartmentEditPart poolCompartment) {
        final String laneSemanticHint = ProcessVisualIDRegistry.getType(LaneEditPart.VISUAL_ID);
        final String laneCompartmentSemanticHint = ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID);
        final LaneEditPart laneEditPart = (LaneEditPart) poolCompartment.getChildBySemanticHint(laneSemanticHint);
        final LaneLaneCompartmentEditPart laneCompartment = (LaneLaneCompartmentEditPart) laneEditPart.getChildBySemanticHint(laneCompartmentSemanticHint);

        final ViewAndElementDescriptor taskViewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.Task_3005)),
                Node.class,
                ((IHintedType) ProcessElementTypes.Task_3005).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());
        final CreateViewAndElementRequest taskReq = new CreateViewAndElementRequest(taskViewDescriptor);
        taskReq.setLocation(new Point(200, 100 - FiguresHelper.ACTIVITY_HEIGHT / 2));
        laneCompartment.getDiagramEditDomain().getDiagramCommandStack().execute(laneCompartment.getCommand(taskReq));

        final Task task = (Task) taskReq.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class);
        diagramEp.getEditingDomain().getCommandStack().execute(SetCommand.create(
                diagramEp.getEditingDomain(),
                task,
                ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE,
                false));
        return laneCompartment;
    }


    protected void createStartEventTaskAndSeqFlow(final DiagramEditPart diagramEp,
            final PoolPoolCompartmentEditPart poolCompartment,
            final LaneLaneCompartmentEditPart laneCompartment) {
        final ViewAndElementDescriptor startViewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.StartEvent_3002)),
                Node.class,
                ((IHintedType) ProcessElementTypes.StartEvent_3002).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());
        final CreateViewAndElementRequest startRequest = new CreateViewAndElementRequest(startViewDescriptor);
        startRequest.setLocation(new Point(100, 100 - FiguresHelper.EVENT_WIDTH / 2));
        laneCompartment.getDiagramEditDomain().getDiagramCommandStack().execute(laneCompartment.getCommand(startRequest));

        final CreateConnectionViewAndElementRequest sequenceFlowRequest = new CreateConnectionViewAndElementRequest(
                ProcessElementTypes.SequenceFlow_4001,
                ((IHintedType) ProcessElementTypes.SequenceFlow_4001).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());

        final String startSemanticHint = ProcessVisualIDRegistry.getType(StartEvent2EditPart.VISUAL_ID);
        final String taskSemanticHint = ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID);

        final StartEvent2EditPart startEventEp = (StartEvent2EditPart) laneCompartment.getChildBySemanticHint(startSemanticHint);
        final Task2EditPart taskEp = (Task2EditPart) laneCompartment.getChildBySemanticHint(taskSemanticHint);

        /*
         * flush the stack because we don't want any operation to be kept on the "new" process creation
         * (and that it avoids leaks : the operation are never flushed even on the close of the editor)
         */
        //taskEp.getEditingDomain().getCommandStack().flush();

        final Command seqFlowCmd = CreateConnectionViewAndElementRequest.getCreateCommand(sequenceFlowRequest, startEventEp, taskEp);
        poolCompartment.getDiagramEditDomain().getDiagramCommandStack().execute(seqFlowCmd);
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


    private MainProcess createInitialModel() {
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


    public static void setCharset(final IFile file) {
        if (file == null) {
            return;
        }
        try {
            file.setCharset("UTF-8", new NullProgressMonitor()); //$NON-NLS-1$
        } catch (final CoreException e) {
            ProcessDiagramEditorPlugin.getInstance().logError(
                    "Unable to set charset for file " + file.getFullPath(), e); //$NON-NLS-1$
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
