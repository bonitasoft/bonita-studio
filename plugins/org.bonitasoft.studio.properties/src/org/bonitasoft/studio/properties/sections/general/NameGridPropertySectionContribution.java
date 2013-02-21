/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.general;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.OpenNameAndVersionDialog;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.URLEncodableInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractNamePropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Mickael Istria
 * @author Romain Bioteau
 */
public class NameGridPropertySectionContribution extends AbstractNamePropertySectionContribution {


	protected ISWTObservableValue observable;

	private UpdateValueStrategy labelModelToTargetUpdate;

	private UpdateValueStrategy labelTargetToModelUpdate;

	private boolean bindingInitialized = false;

	private final NotificationListener updateMessage =new NotificationListener() {

		@Override
		public void notifyChanged(Notification notification) {
			List<AbstractCatchMessageEvent> messages = ModelHelper.getAllItemsOfType(element, ProcessPackage.eINSTANCE.getAbstractCatchMessageEvent()) ;
			for(AbstractCatchMessageEvent m : messages){
				String eventName = m.getEvent() ;
				final Message event = ModelHelper.findEvent(ModelHelper.getMainProcess(element), eventName);
				if(event != null){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, event, ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION, ExpressionHelper.createConstantExpression(element.getName(), String.class.getName()))) ;
				}
			}
		}
	};

	private final DiagramRepositoryStore diagramStore;

	/**
	 * @param tabbedPropertySheetPage
	 * @param extensibleGridPropertySection
	 */
	 public NameGridPropertySectionContribution(TabbedPropertySheetPage tabbedPropertySheetPage,
			 ExtensibleGridPropertySection extensibleGridPropertySection) {
		 super(tabbedPropertySheetPage, extensibleGridPropertySection);
		 diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
	 }


	 protected void updateEvents(Element element) {
		 for(AbstractCatchMessageEvent ev :  ModelHelper.getAllCatchEvent(ModelHelper.getMainProcess(element))){
			 Message eventObject = ModelHelper.findEvent(element, ev.getEvent());
			 if(eventObject != null){
				 editingDomain.getCommandStack().execute(new SetCommand(editingDomain, eventObject, ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION, ExpressionHelper.createConstantExpression(element.getName(), String.class.getName())));
			 }
		 }

	 }


	 /*
	  * (non-Javadoc)
	  * 
	  * @seeorg.bonitasoft.studio.properties.sections.general.
	  * IExtenstibleGridPropertySectionContribution#getLabel()
	  */
	 @Override
	 public String getLabel() {
		 return Messages.GeneralSection_Name;
	 }



	 /*
	  * (non-Javadoc)
	  * 
	  * @seeorg.bonitasoft.studio.properties.sections.general.
	  * IExtenstibleGridPropertySectionContribution
	  * #setEObject(org.eclipse.emf.ecore.EObject)
	  */
	 @Override
	 public void setEObject(EObject object) {

		 if (object instanceof Lane) {
			 element = ModelHelper.getParentProcess(object);
		 } else {
			 element = (Element) object;
		 }

		 if(element instanceof MainProcess){
			 updateBindings();
		 }

		 if(element instanceof Pool){
			 activateNameListener();
		 }

	 }


	 protected void activateNameListener() {
		 if(editingDomain != null){
			 DiagramEventBroker.getInstance(editingDomain).addNotificationListener(element,ProcessPackage.eINSTANCE.getElement_Name(),updateMessage);
		 }
	 }


	 /*
	  * (non-Javadoc)
	  * 
	  * @seeorg.bonitasoft.studio.properties.sections.general.
	  * IExtenstibleGridPropertySectionContribution
	  * #setSelection(org.eclipse.jface.viewers.ISelection)
	  */
	 @Override
	 public void setSelection(ISelection selection) {
		 if (((StructuredSelection) selection).getFirstElement() instanceof LaneEditPart) {
			 this.selection = new StructuredSelection(
					 ((LaneEditPart) ((StructuredSelection) selection)
							 .getFirstElement()).getParent());
		 }
		 this.selection = selection;

	 }

	 private void updateBindings() {
		 if(bindingInitialized){
			 if(text != null && !text.isDisposed()){
				 int start = text.getSelection().x  ;
				 context.dispose() ;
				 context = new EMFDataBindingContext();
				 context.bindValue(observable, EMFEditObservables.observeValue(editingDomain, element, ProcessPackage.Literals.ELEMENT__NAME),labelTargetToModelUpdate,labelModelToTargetUpdate);
				 text.setSelection(start);
			 }
		 }

	 }

	 /* (non-Javadoc)
	  * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
	  */
	 @Override
	 public void dispose() {
		 super.dispose();
		 if(element instanceof Pool){
			 deactivateNameListener();
		 }
	 }

	 protected void deactivateNameListener() {
		 if(editingDomain != null ){
			 DiagramEventBroker.getInstance(editingDomain).removeNotificationListener(element,ProcessPackage.eINSTANCE.getElement_Name(),updateMessage);
		 }
	 }


	 @Override
	 protected void createBinding(EMFDataBindingContext context) {
		 labelTargetToModelUpdate = new UpdateValueStrategy();
		 labelTargetToModelUpdate.setAfterGetValidator(new URLEncodableInputValidator(Messages.name)) ;
		 labelTargetToModelUpdate.setBeforeSetValidator(new InputLengthValidator(Messages.name, 50)) ;

		 observable = SWTObservables.observeDelayedValue(400, SWTObservables.observeText(text, SWT.Modify));
		 observable.addValueChangeListener(new IValueChangeListener() {

			 @Override
			 public void handleValueChange(ValueChangeEvent event) {
				 updatePropertyTabTitle();
			 }
		 });

		 ControlDecorationSupport.create(context.bindValue(observable, EMFEditObservables.observeValue(editingDomain, element, ProcessPackage.Literals.ELEMENT__NAME),labelTargetToModelUpdate,null),SWT.LEFT);
		 bindingInitialized = true ;
	 }

	 @Override
	 protected void editProcessNameAndVersion() {
		 if(element instanceof Pool){
			 MainProcess diagram = ModelHelper.getMainProcess(element);
			 final OpenNameAndVersionDialog dialog1 = new OpenNameAndVersionDialog(Display.getDefault().getActiveShell(),diagram,element.getName(), ((Pool) element).getVersion(),diagramStore) ;
			 if(dialog1.open() == Dialog.OK ) {
				 proceedForPools(dialog1);
			 }

		 }else{
			 MainProcess diagram = ModelHelper.getMainProcess(element);
			 //            String oldName = diagram.getName();
			 //            String oldVerison = diagram.getVersion();
			 final OpenNameAndVersionForDiagramDialog nameDialog = new OpenNameAndVersionForDiagramDialog(Display.getDefault().getActiveShell(),diagram,diagramStore) ;
			 if(nameDialog.open() == Dialog.OK ) {
				 DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
				 MainProcess newProcess   = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement() ;
				 //                if(!(oldName.equals(nameDialog.getDiagramName()) && oldVerison.equals(nameDialog.getDiagramVersion()))){
				 //                    if(diagramStore.getDiagram(nameDialog.getDiagramName(), nameDialog.getDiagramVersion()) != null ){
				 //                        MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.diagramAlreadyExistsTitle, Messages.diagramAlreadyExistsMsg) ;
				 //                        return ;
				 //                    }
				 //                }


				 changeProcessNameAndVersion(newProcess, nameDialog.getDiagramName(), nameDialog.getDiagramVersion());

				 for(ProcessesNameVersion pnv : nameDialog.getPools()){
					 changeProcessNameAndVersion(pnv.getAbstractProcess(), pnv.getNewName(), pnv.getNewVersion());
				 }

				 try{
					 ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class) ;
					 org.eclipse.core.commands.Command c = service.getCommand("org.eclipse.ui.file.save") ;
					 if(c.isEnabled()){
						 c.executeWithChecks(new ExecutionEvent()) ;
					 }
				 }catch (Exception e) {
					 BonitaStudioLog.error(e) ;
				 }

			 }
		 }
	 }


	 private void proceedForPools(final OpenNameAndVersionDialog dialog1) {
		 String oldPoolName = element.getName() ;
		 final String newPoolName = dialog1.getDiagramName() ;
		 final String oldVersion = ((Pool) element).getVersion() ;
		 final String newVersion = dialog1.getDiagramVersion() ;

		 List<AbstractProcess> processes = diagramStore.getAllProcesses() ;
		 StringBuilder activitiesToUpdateMsg = new StringBuilder() ;
		 final Set<CallActivity> toUpdate = new HashSet<CallActivity>();
		 for(AbstractProcess process : processes){
			 List<CallActivity> callActivities = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.CALL_ACTIVITY) ;
			 for(CallActivity sub : callActivities){
				 String subprocessName = null;
				 if(sub.getCalledActivityName() != null
						 && sub.getCalledActivityName().getContent() != null){
					 subprocessName = sub.getCalledActivityName().getContent();
				 }
				 String subprocessVersion = null;
				 if(sub.getCalledActivityVersion() != null
						 && sub.getCalledActivityVersion().getContent() != null){
					 subprocessVersion = sub.getCalledActivityVersion().getContent();
				 }

				 if(subprocessName != null && subprocessName.equals(oldPoolName) //same pool name
						 && (subprocessVersion == null /*ie Latest*/ || subprocessVersion.equals(oldVersion))){ //same version or Latest
					 activitiesToUpdateMsg.append(sub.getName()+" ("+process.getName()+")");
					 activitiesToUpdateMsg.append(SWT.CR) ;
					 toUpdate.add(sub) ;
				 }
			 }
		 }

		 CompoundCommand cc = new CompoundCommand("Rename pool") ;
		 cc.append(SetCommand.create(editingDomain,element, ProcessPackage.eINSTANCE.getElement_Name(), newPoolName)) ;
		 cc.append(SetCommand.create(editingDomain, element, ProcessPackage.eINSTANCE.getAbstractProcess_Version(), newVersion)) ;
		 editingDomain.getCommandStack().execute(cc) ;

		 if(!toUpdate.isEmpty() && MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.updateReferencesTitle, Messages.bind(Messages.updateReferencesMsg,activitiesToUpdateMsg.toString()))){
			 IProgressService service = PlatformUI.getWorkbench().getProgressService() ;
			 try {
				 service.run(true,false, new IRunnableWithProgress() {

					 @Override
					 public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
						 monitor.beginTask(Messages.updatingReferences, IProgressMonitor.UNKNOWN) ;
						 for(CallActivity sub : toUpdate){
							 String subprocessName = null;
							 if(sub.getCalledActivityName() != null
									 && sub.getCalledActivityName().getContent() != null){
								 subprocessName = sub.getCalledActivityName().getContent();
							 }
							 String subprocessVersion = null;
							 if(sub.getCalledActivityVersion() != null
									 && sub.getCalledActivityVersion().getContent() != null){
								 subprocessVersion = sub.getCalledActivityVersion().getContent();
							 }
							 if(subprocessName != null){

								 CompoundCommand cc = new CompoundCommand("Update pool references") ;
								 CallActivity toModify = sub ;
								 TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(toModify) ;
								 boolean loadResource = domain == null ;
								 Resource res = null ;
								 if(loadResource){
									 URI uri = EcoreUtil.getURI(sub) ;
									 domain = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain() ;
									 res = domain.getResourceSet().createResource(uri) ;

									 try {
										 res.load(Collections.emptyMap()) ;
									 } catch (IOException e) {
										 BonitaStudioLog.error(e) ;
									 }

									 MainProcess mainProc = (MainProcess) res.getContents().get(0) ;
									 for(EObject subproc : ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CALL_ACTIVITY)){
										 if(ModelHelper.getEObjectID(subproc).equals(EcoreUtil.getURI(sub).fragment())){
											 toModify = (CallActivity) subproc ;
											 break ;
										 }
									 }
								 }

								 if(!subprocessName.equals(NamingUtils.convertToId(newPoolName))){
									 cc.append(SetCommand.create(domain, toModify, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME, createStringExpression(newPoolName))) ;
								 }
								 if(subprocessVersion != null  && newVersion != null && !(newVersion.equals(oldVersion))){
									 cc.append(SetCommand.create(domain, toModify, ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION, createStringExpression(newVersion))) ;
								 }
								 domain.getCommandStack().execute(cc) ;


								 if(loadResource && res != null){
									 try {
										 res.save(Collections.emptyMap()) ;
									 } catch (IOException e) {
										 BonitaStudioLog.error(e) ;
									 }
									 domain.dispose();
								 }
							 }
						 }

					 }
				 });
			 } catch (InvocationTargetException e) {
				 BonitaStudioLog.error(e) ;
			 } catch (InterruptedException e) {
				 BonitaStudioLog.error(e) ;
			 }
		 }
	 }

	 protected Expression createStringExpression(String value) {
		 Expression exp = ExpressionFactory.eINSTANCE.createExpression();
		 exp.setName(value);
		 exp.setContent(value);
		 exp.setReturnType(String.class.getName());
		 exp.setType(ExpressionConstants.CONSTANT_TYPE);
		 return exp;
	 }


	 private void changeProcessNameAndVersion(final AbstractProcess process,final String name, final String version) {
		 CompoundCommand cc = new CompoundCommand() ;
		 cc.append(SetCommand.create(editingDomain, process, ProcessPackage.eINSTANCE.getElement_Name(), name)) ;
		 cc.append(SetCommand.create(editingDomain, process, ProcessPackage.eINSTANCE.getAbstractProcess_Version(), version)) ;
		 editingDomain.getCommandStack().execute(cc) ;
	 }
}
