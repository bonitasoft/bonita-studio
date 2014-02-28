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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.OpenNameAndVersionDialog;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.databinding.MultiValidator;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.SpecialCharactersValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.UTF8InputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractNamePropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.refactoring.ProcessNamingTools;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
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
	private ProcessNamingTools processNamingTools ;
	
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
	
	@Override
	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		super.setEditingDomain(editingDomain);
		processNamingTools = new ProcessNamingTools(editingDomain);
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
		
		labelTargetToModelUpdate.setAfterGetValidator(new UTF8InputValidator(Messages.name)) ;
		List<IValidator> validators = new ArrayList<IValidator>();
		validators.add(new InputLengthValidator(Messages.name,element instanceof SequenceFlow ? 0 : 1,50));
		MultiValidator multiValidation = new MultiValidator(validators);
		labelTargetToModelUpdate.setBeforeSetValidator(multiValidation) ;
		labelTargetToModelUpdate.setAfterConvertValidator(new SpecialCharactersValidator());

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
				String oldPoolName = element.getName() ;
				final String newPoolName = dialog1.getDiagramName() ;
				final String oldVersion = ((Pool) element).getVersion() ;
				final String newVersion = dialog1.getDiagramVersion() ;
				processNamingTools.proceedForPools(element,newPoolName,oldPoolName,oldVersion,newVersion);
			}

		}else{
			MainProcess diagram = ModelHelper.getMainProcess(element);
			final OpenNameAndVersionForDiagramDialog nameDialog = new OpenNameAndVersionForDiagramDialog(Display.getDefault().getActiveShell(),diagram,diagramStore) ;
			if(nameDialog.open() == Dialog.OK ) {
				DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
				MainProcess newProcess   = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement() ;
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, newProcess, ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR, System.getProperty("user.name", "Unknown")));
				editor.doSave(Repository.NULL_PROGRESS_MONITOR);
				
				processNamingTools.changeProcessNameAndVersion(newProcess, nameDialog.getDiagramName(), nameDialog.getDiagramVersion());
				for(ProcessesNameVersion pnv : nameDialog.getPools()){
					processNamingTools.changeProcessNameAndVersion(pnv.getAbstractProcess(), pnv.getNewName(), pnv.getNewVersion());
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

	
	


}
