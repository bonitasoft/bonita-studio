/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.Messages;
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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
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
    private String diagramName ;
    private String diagramIdentifier;
    private DiagramRepositoryStore diagramStore;
    private DiagramFileStore fileStore;



    public NewDiagramCommandHandler(){

    }


    public Object execute(ExecutionEvent event) throws ExecutionException {
        IRepository repository = RepositoryManager.getInstance().getCurrentRepository() ;
        diagramStore = (DiagramRepositoryStore) repository.getRepositoryStore(DiagramRepositoryStore.class) ;
        try{
            IRunnableWithProgress runnable = new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.newDiagram, IProgressMonitor.UNKNOWN) ;

                    diagramIdentifier = getNewProcessIdentifier(diagramStore);
                    diagramName =  NamingUtils.convertToId(org.bonitasoft.studio.diagram.custom.Messages.newFilePrefix + diagramIdentifier)  ;

                    String uniqueFileName = NamingUtils.toDiagramFilename(diagramName ,BASE_VERSION) ;

                    fileStore = diagramStore.createRepositoryFileStore(uniqueFileName) ;

                    MainProcess model = createInitialModel();
                    final Diagram diagram = ViewService.createDiagram(model,
                            MainProcessEditPart.MODEL_ID,
                            ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

                    diagram.setName(model.getName());
                    diagram.setElement(model);
                    fileStore.getEMFResource().getContents().add(model) ;
                    fileStore.getEMFResource().getContents().add(diagram) ;
                    fileStore.save(null) ;

                    Display.getDefault().syncExec(new Runnable() {

                        public void run() {
                            Diagram d = (Diagram) fileStore.getEMFResource().getContents().get(1) ;
                            buildDiagram(d);
                            IEditorPart editor = (IEditorPart) fileStore.open() ;
                            //clear the OperationHistory because it implies otherwise and that we don't need undo/redo for the basic creation.
                            OperationHistoryFactory.getOperationHistory().dispose((IUndoContext) editor.getAdapter(IUndoContext.class), true, true, true);
                        }

                    });

                }
            };

            IProgressService progressService = PlatformUI.getWorkbench().getProgressService() ;
            progressService.run(true, false, runnable) ;

        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }

        return null ;
    }


    protected void createDefaultProcessArtifact(TransactionalEditingDomain editingDomain, MainProcess diagram) {
        Pool pool = (Pool) diagram.getElements().get(0) ;
        String processUUID = ModelHelper.getEObjectID(pool) ;
        ProcessConfigurationRepositoryStore processConfStore = (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class) ;
        IRepositoryFileStore confFile = processConfStore.createRepositoryFileStore(processUUID+".conf") ;
        Configuration conf = ConfigurationFactory.eINSTANCE.createConfiguration();
        conf.setVersion(ProductVersion.CURRENT_VERSION);
        createDefaultActorMapping(conf);
        confFile.save(conf) ;

        ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
        LookNFeelRepositoryStore lookNFeelStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class) ;
        ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
        if (artifact == null) {
            String themeId = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.DEFAULT_APPLICATION_THEME) ;
            ApplicationLookNFeelFileStore file = (ApplicationLookNFeelFileStore) lookNFeelStore.getChild(themeId) ;
            CompoundCommand templateCommand = WebTemplatesUtil.createAddTemplateCommand(editingDomain,pool , file);
            // add an empty application folder
            editingDomain.getCommandStack().execute(templateCommand);
            org.eclipse.emf.common.command.Command createDefaultResourceFolders = WebTemplatesUtil.createDefaultResourceFolders(editingDomain, pool);
            if (createDefaultResourceFolders != null) {
                editingDomain.getCommandStack().execute(createDefaultResourceFolders);
            }
        }

    }


    protected void createDefaultActorMapping(Configuration conf) {
        ActorMappingsType amType = ActorMappingFactory.eINSTANCE.createActorMappingsType();
        amType.getActorMapping().add(createGroupActorMapping("Employee actor","/acme"));
        //        amType.getActorMapping().add(createGroupActorMapping("Finance","/acme/finance"));
        //        amType.getActorMapping().add(createGroupActorMapping("Marketing","/acme/marketing"));
        //        amType.getActorMapping().add(createGroupActorMapping("Human resource","/acme/hr"));
        //        amType.getActorMapping().add(createGroupActorMapping("Production","/acme/production"));
        //        amType.getActorMapping().add(createGroupActorMapping("Infrastructure","/acme/it"));
        //        amType.getActorMapping().add(createGroupActorMapping("Sales","/acme/sales"));
        conf.setActorMappings(amType) ;
    }


    private ActorMapping createGroupActorMapping(String actorName, String grouId) {
        ActorMapping mapping = ActorMappingFactory.eINSTANCE.createActorMapping();
        mapping.setName(actorName);
        Groups company = ActorMappingFactory.eINSTANCE.createGroups();
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


    private String getNewProcessIdentifier(DiagramRepositoryStore store) {
        String processName = Messages.newFilePrefix;
        String fileName = NamingUtils.toDiagramFilename(processName, BASE_VERSION) ;
        Integer i = 1;
        while (store.getResource().getFile(fileName).exists()) {
            processName = Messages.newFilePrefix + i;
            fileName =  NamingUtils.toDiagramFilename(processName, BASE_VERSION) ;
            i++;
        }
        if (i > 1) {
            return "" + (i - 1); //$NON-NLS-1$
        } else {
            return ""; //$NON-NLS-1$
        }
    }


    protected void buildDiagram(final Diagram diagram){
        ResourceSet rSet = diagram.eResource().getResourceSet() ;
        final TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.getInstance().createEditingDomain(rSet) ;
        final Shell shell = new Shell() ;
        DiagramEditPart	diagramEp = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram,shell ,ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT) ;
        diagramEp.refresh() ;

        CompoundCommand cc;
        List<Actor> actors = createPool(diagramEp);

        String poolSemanticHint = ProcessVisualIDRegistry.getType(PoolEditPart.VISUAL_ID);
        String poolCompartmentSemanticHint = ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID);
        PoolEditPart poolEditPart = (PoolEditPart) diagramEp.getChildBySemanticHint(poolSemanticHint);
        PoolPoolCompartmentEditPart poolCompartment = (PoolPoolCompartmentEditPart) poolEditPart.getChildBySemanticHint(poolCompartmentSemanticHint);

        ViewAndElementDescriptor laneViewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.Lane_3007)),
                Node.class,
                ((IHintedType) ProcessElementTypes.Lane_3007).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());
        CreateViewAndElementRequest laneReq = new CreateViewAndElementRequest(laneViewDescriptor);
        laneReq.setLocation(new Point(0, 0));
        poolCompartment.getDiagramEditDomain().getDiagramCommandStack().execute(poolCompartment.getCommand(laneReq));

        cc = new CompoundCommand();
        Lane lane =  (Lane) laneReq.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class) ;
        cc.append(SetCommand.create(
                diagramEp.getEditingDomain(),
                lane,
                ProcessPackage.Literals.ELEMENT__NAME,
                Messages.defaultLaneName));


        createAssignableActor(diagramEp, cc, actors, lane);

        diagramEp.getEditingDomain().getCommandStack().execute(cc);

        LaneLaneCompartmentEditPart laneCompartment = createLaneCompartment(diagramEp,	poolCompartment);

        createStartEventTaskAndSeqFlow(diagramEp, poolCompartment, laneCompartment);

        createDefaultProcessArtifact(editingDomain,fileStore.getContent()) ;
        fileStore.save(null) ;


        editingDomain.dispose() ;
        shell.dispose() ;
    }


    protected List<Actor> createPool(DiagramEditPart diagramEp) {
        ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.Pool_2007)),
                Node.class,
                ((IHintedType) ProcessElementTypes.Pool_2007).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());

        CreateViewAndElementRequest req = new CreateViewAndElementRequest(viewDescriptor);
        diagramEp.getDiagramEditDomain().getDiagramCommandStack().execute(diagramEp.getCommand(req));
        CompoundCommand cc = new CompoundCommand();
        AbstractProcess pool = (AbstractProcess) req.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class) ;
        SetCommand setPoolNameCommand = new SetCommand(
                diagramEp.getEditingDomain(),
                pool ,
                ProcessPackage.Literals.ELEMENT__NAME,
                Messages.newProcessPrefix + diagramIdentifier);
        cc.append(setPoolNameCommand);
        SetCommand setPoolVersionCommand = new SetCommand(
                diagramEp.getEditingDomain(),
                pool,
                ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION,"1.0"); //$NON-NLS-1$
        cc.append(setPoolVersionCommand);
        List<Actor> actors = createInitialActors();
        cc.append(AddCommand.create(diagramEp.getEditingDomain(),
                pool,
                ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS,
                actors));


        diagramEp.getEditingDomain().getCommandStack().execute(cc);
        return actors;
    }


    protected void createAssignableActor(DiagramEditPart diagramEp,
            CompoundCommand cc, List<Actor> actors, Lane lane) {
        cc.append(SetCommand.create(
                diagramEp.getEditingDomain(),
                lane,
                ProcessPackage.Literals.ASSIGNABLE__ACTOR,
                actors.get(0)));
    }


    protected LaneLaneCompartmentEditPart createLaneCompartment(DiagramEditPart diagramEp,
            PoolPoolCompartmentEditPart poolCompartment) {
        String laneSemanticHint = ProcessVisualIDRegistry.getType(LaneEditPart.VISUAL_ID);
        String laneCompartmentSemanticHint = ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID);
        LaneEditPart laneEditPart = (LaneEditPart) poolCompartment.getChildBySemanticHint(laneSemanticHint);
        LaneLaneCompartmentEditPart laneCompartment = (LaneLaneCompartmentEditPart) laneEditPart.getChildBySemanticHint(laneCompartmentSemanticHint);

        ViewAndElementDescriptor taskViewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.Task_3005)),
                Node.class,
                ((IHintedType) ProcessElementTypes.Task_3005).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());
        CreateViewAndElementRequest taskReq = new CreateViewAndElementRequest(taskViewDescriptor);
        taskReq.setLocation(new Point(150, 100 - FiguresHelper.ACTIVITY_HEIGHT / 2));
        laneCompartment.getDiagramEditDomain().getDiagramCommandStack().execute(laneCompartment.getCommand(taskReq));

        Task task =  (Task) taskReq.getViewAndElementDescriptor().getElementAdapter().getAdapter(EObject.class) ;
        diagramEp.getEditingDomain().getCommandStack().execute(SetCommand.create(
                diagramEp.getEditingDomain(),
                task,
                ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE,
                false));
        return laneCompartment;
    }


    protected void createStartEventTaskAndSeqFlow(DiagramEditPart diagramEp,
            PoolPoolCompartmentEditPart poolCompartment,
            LaneLaneCompartmentEditPart laneCompartment) {
        ViewAndElementDescriptor startViewDescriptor = new ViewAndElementDescriptor(
                new CreateElementRequestAdapter(new CreateElementRequest(ProcessElementTypes.StartEvent_3002)),
                Node.class,
                ((IHintedType) ProcessElementTypes.StartEvent_3002).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());
        CreateViewAndElementRequest startRequest = new CreateViewAndElementRequest(startViewDescriptor);
        startRequest.setLocation(new Point(80, 100 - FiguresHelper.EVENT_WIDTH / 2));
        laneCompartment.getDiagramEditDomain().getDiagramCommandStack().execute(laneCompartment.getCommand(startRequest));

        CreateConnectionViewAndElementRequest sequenceFlowRequest = new CreateConnectionViewAndElementRequest(
                ProcessElementTypes.SequenceFlow_4001,
                ((IHintedType) ProcessElementTypes.SequenceFlow_4001).getSemanticHint(),
                diagramEp.getDiagramPreferencesHint());

        String startSemanticHint = ProcessVisualIDRegistry.getType(StartEvent2EditPart.VISUAL_ID);
        String taskSemanticHint = ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID);

        StartEvent2EditPart startEventEp = (StartEvent2EditPart) laneCompartment.getChildBySemanticHint(startSemanticHint);
        Task2EditPart taskEp = (Task2EditPart) laneCompartment.getChildBySemanticHint(taskSemanticHint);

        /*flush the stack because we don't want any operation to be kept on the "new" process creation
         * (and that it avoids leaks : the operation are never flushed even on the close of the editor)*/
        //taskEp.getEditingDomain().getCommandStack().flush();

        Command seqFlowCmd = CreateConnectionViewAndElementRequest.getCreateCommand(sequenceFlowRequest,startEventEp, taskEp);
        poolCompartment.getDiagramEditDomain().getDiagramCommandStack().execute(seqFlowCmd);
    }

    protected List<Actor> createInitialActors() {
        List<Actor> actors = new ArrayList<Actor>(7);

        Actor initiator = ProcessFactory.eINSTANCE.createActor() ;
        initiator.setInitiator(true) ;
        initiator.setName("Employee actor") ;
        initiator.setDocumentation(Messages.initiatorDescription) ;
        actors.add(initiator);

        //        Actor finance = ProcessFactory.eINSTANCE.createActor() ;
        //        finance.setInitiator(false) ;
        //        finance.setName("Finance") ;
        //        actors.add(finance);
        //
        //        Actor hr = ProcessFactory.eINSTANCE.createActor() ;
        //        hr.setInitiator(true) ;
        //        hr.setName("Human resource") ;
        //        actors.add(hr);
        //
        //        Actor infra = ProcessFactory.eINSTANCE.createActor() ;
        //        infra.setInitiator(false) ;
        //        infra.setName("Infrastructure") ;
        //        actors.add(infra);
        //
        //        Actor sales = ProcessFactory.eINSTANCE.createActor() ;
        //        sales.setInitiator(false) ;
        //        sales.setName("Sales") ;
        //        actors.add(sales);
        //
        //        Actor production = ProcessFactory.eINSTANCE.createActor() ;
        //        production.setInitiator(false) ;
        //        production.setName("Production") ;
        //        actors.add(production);
        //
        //        Actor marketing = ProcessFactory.eINSTANCE.createActor() ;
        //        marketing.setInitiator(false) ;
        //        marketing.setName("Marketing") ;
        //        actors.add(production);

        return actors;
    }


    private MainProcess createInitialModel() {
        MainProcess proc = ProcessFactory.eINSTANCE.createMainProcess();
        proc.setName(diagramName);
        proc.setVersion("1.0"); //$NON-NLS-1$
        proc.setBonitaVersion(ProductVersion.CURRENT_VERSION);
        proc.setBonitaModelVersion(ProductVersion.CURRENT_VERSION);
        proc.setEnableValidation(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.PREF_ENABLE_VALIDATION)) ;
        ModelHelper.addDataTypes(proc);
        return proc ;
    }


    public static void setCharset(IFile file) {
        if (file == null) {
            return;
        }
        try {
            file.setCharset("UTF-8", new NullProgressMonitor()); //$NON-NLS-1$
        } catch (CoreException e) {
            ProcessDiagramEditorPlugin.getInstance().logError(
                    "Unable to set charset for file " + file.getFullPath(), e); //$NON-NLS-1$
        }
    }




    /* (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


}