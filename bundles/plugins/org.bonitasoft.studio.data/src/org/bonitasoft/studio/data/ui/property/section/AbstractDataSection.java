/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.data.ui.property.section;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.common.jface.EMFListFeatureTreeContentProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.refactoring.BonitaGroovyRefactoringAction;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.DataPlugin;
import org.bonitasoft.studio.data.commands.MoveDataCommand;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.operation.RefactorDataOperation;
import org.bonitasoft.studio.data.ui.wizard.DataWizard;
import org.bonitasoft.studio.data.ui.wizard.DataWizardDialog;
import org.bonitasoft.studio.data.ui.wizard.MoveDataWizard;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.xtext.ui.XtextProjectHelper;

/**
 * 
 * @author Romain Bioteau
 */
public abstract class AbstractDataSection extends AbstractBonitaDescriptionSection implements ISelectionChangedListener,IDoubleClickListener,IBonitaVariableContext{


	private Button updateDataButton;
	private Button removeDataButton;
	private Button promoteDataButton;
	protected Composite mainComposite;
	protected ObservablesManager observablesManager = new ObservablesManager();
	private TableViewer tableViewer;
	private IObservableList observeDataList;
	private IChangeListener dataListener;
	private IObservableList observeDataListNames;
	private IObservableList observeDataListType;
	private IObservableList observeTransient;
	private IObservableList observeJObjectType;
	private boolean isPageFlowContext = false;
	private boolean isOverviewContext = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bonitasoft.studio.properties.sections.data.DataSection#createControls
	 * (org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		mainComposite = getWidgetFactory().createComposite(parent);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(20, 15).create());
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
		final Composite dataComposite = getWidgetFactory().createComposite(mainComposite) ;
		dataComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
		dataComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create()) ;
		createDataComposite(dataComposite);
	}


	private void createDataComposite(Composite parent) {
		createLabel(parent);

		Composite buttonsComposite = getWidgetFactory().createPlainComposite(parent, SWT.NONE);
		buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
		buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5,0).spacing(0, 3).create());

		createAddDataButton(buttonsComposite);
		updateDataButton = createUpdateDataButton(buttonsComposite);
		removeDataButton = createRemoveDataButton(buttonsComposite);
		promoteDataButton = createPromoteDataButton(buttonsComposite);

		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS | SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
		getWidgetFactory().adapt(tableViewer.getTable(), false, false) ;
		tableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
		tableViewer.setSorter(new ViewerSorter());
		tableViewer.addDoubleClickListener(this);
		tableViewer.addSelectionChangedListener(this);



		EMFListFeatureTreeContentProvider cp = new EMFListFeatureTreeContentProvider(getDataFeature());
		tableViewer.setContentProvider(cp);


		DataStyledTreeLabelProvider labelProvider = new DataStyledTreeLabelProvider();
		tableViewer.setLabelProvider(labelProvider);

	}

	protected abstract void createLabel(Composite dataComposite) ;


	private void updateButtons() {
		if (tableViewer != null) {
			IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
			if (!removeDataButton.isDisposed()) {
				removeDataButton.setEnabled(!selection.isEmpty());
			}
			if (!updateDataButton.isDisposed()) {
				updateDataButton.setEnabled(selection.size() == 1);
			}
			if (promoteDataButton != null && !promoteDataButton.isDisposed()) {

				boolean enabled = selection.size() > 0 && ModelHelper.getParentProcess(getEObject()) != null;
				if(enabled) {
					for (Object obj : selection.toList()) {
						if (obj instanceof Data) {
							Data data = (Data) obj;
							if(data.isTransient()) {
								enabled = false;
								break;
							}
						}else{
							enabled = false;
							break;
						}
					}
				}
				promoteDataButton.setEnabled(enabled);

			}
		}
	}

	/**
	 * @param buttonsComposite
	 * @return
	 */
	 private Button createRemoveDataButton(final Composite parent) {
		Button removeButton = getWidgetFactory().createButton(parent, Messages.removeData, SWT.FLAT);
		removeButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
		removeButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (tableViewer != null && ((IStructuredSelection) tableViewer.getSelection()).size() > 0) {
					List<Object> selection = ((IStructuredSelection) tableViewer.getSelection()).toList();
					String[] buttonList = {IDialogConstants.OK_LABEL,IDialogConstants.CANCEL_LABEL};
					OutlineDialog dialog = new OutlineDialog(parent.getShell(), Messages.deleteDataDialogTitle, Display.getCurrent().getSystemImage(SWT.ICON_WARNING),createMessage(),MessageDialog.CONFIRM,buttonList,1,selection);
					int ok=0;
					if (ok==dialog.open()){
					//if (MessageDialog.openConfirm(parent.getShell(), Messages.deleteDataDialogTitle, createMessage())) {
						CompoundCommand cc = new CompoundCommand("Remove list of data");
						IProgressService service = PlatformUI.getWorkbench().getProgressService();
						boolean canExecute=false;
						for(Object d : selection){
							RefactorDataOperation op = new RefactorDataOperation(BonitaGroovyRefactoringAction.REMOVE_OPERATION);
							op.setCompoundCommand(cc);
							op.setContainer(ModelHelper.getParentProcess(eObject));
							op.setEditingDomain(getEditingDomain());
							op.setOldData((Data) d);
							op.updateReferencesInScripts();
							try {
							if (op.isCanExecute()){
								service.run(true, false, op);
								cc.append(DeleteCommand.create(getEditingDomain(), d));
								canExecute = canExecute || true;
							} else {
								canExecute = canExecute || false;
							}
							} catch (InvocationTargetException e) {
								BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
							} catch (InterruptedException e) {
								BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
							}
							
						}
						if (canExecute){
							getEditingDomain().getCommandStack().execute(cc);
						} else {
							cc.dispose();
						}
						tableViewer.refresh() ;
						try {
							RepositoryManager.getInstance().getCurrentRepository().getProject().build(IncrementalProjectBuilder.FULL_BUILD,XtextProjectHelper.BUILDER_ID,Collections.EMPTY_MAP,null);
						} catch (CoreException e) {
							BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
						}
					}
				}
			}

			public String createMessage() {
				Object[] selection = ((IStructuredSelection) tableViewer.getSelection()).toArray();
				StringBuilder res = new StringBuilder(Messages.deleteDialogConfirmMessage);
				res.append(' ');
				res.append(((Data) selection[0]).getName());
				for (int i = 1; i < selection.length; i++) {
					res.append(", ");res.append(((Data) selection[i]).getName()); //$NON-NLS-1$
				}
				res.append(" ?"); //$NON-NLS-1$
				return res.toString();
			}
		});
		return removeButton;
	 }

	 protected Button createPromoteDataButton(final Composite parent) {
		 Button moveData = getWidgetFactory().createButton(parent, Messages.moveData, SWT.FLAT);
		 moveData.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
		 moveData.setToolTipText(Messages.moveData_tooltip);
		 moveData.addSelectionListener(new SelectionAdapter() {

			 @Override
			 @SuppressWarnings("unchecked")
			 public void widgetSelected(SelectionEvent e) {
				 List<Data> datas = ((IStructuredSelection) tableViewer.getSelection()).toList();

				 MoveDataWizard moveDataWizard = new MoveDataWizard((DataAware) getEObject());
				 if(new WizardDialog(AbstractDataSection.this.getPart().getSite().getShell(),moveDataWizard).open() == Dialog.OK){
					 DataAware dataAware = moveDataWizard.getSelectedDataAwareElement();
					 try {
						 MoveDataCommand cmd = new MoveDataCommand(getEditingDomain(), (DataAware) getEObject(), datas, dataAware);
						 OperationHistoryFactory.getOperationHistory().execute(cmd, null, null);

						 if (!(cmd.getCommandResult().getStatus().getSeverity() == Status.OK)) {
							 List<Data> data = (List<Data>) cmd.getCommandResult().getReturnValue();
							 String dataNames = "";
							 for (Data d : data) {
								 dataNames = dataNames + d.getName() + ",";
							 }
							 dataNames = dataNames.substring(0, dataNames.length() - 1);
							 MessageDialog.openWarning(parent.getShell(), Messages.PromoteDataWarningTitle,
									 Messages.bind(Messages.PromoteDataWarningMessage, dataNames));
						 }

					 } catch (ExecutionException e1) {
						 BonitaStudioLog.error(e1);
					 }
					 refresh();
				 }
			 }

		 });

		 moveData.setEnabled(true);
		 return moveData;
	 }

	 private Button createAddDataButton(final Composite parent) {
		 final Button addData = getWidgetFactory().createButton(parent, Messages.addData, SWT.FLAT);
		 addData.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
		 addData.addSelectionListener(new SelectionListener() {

			 @Override
			 public void widgetSelected(SelectionEvent e) {
				 setWizardDialog();
			 }

			 @Override
			 public void widgetDefaultSelected(SelectionEvent e) {

			 }
		 });
		 return addData;
	 }


	 public void setWizardDialog(){
		 DataWizard wizard= new DataWizard(getEObject(), getDataFeature(), getDataFeatureToCheckUniqueID(), getShowAutoGenerateForm());
		 wizard.setIsPageFlowContext(this.isPageFlowContext());
		 wizard.setIsOverviewContext(this.isOverViewContext());
		 WizardDialog wizardDialog = new DataWizardDialog(Display.getCurrent().getActiveShell(),wizard ,this);
		 if(wizardDialog.open() == Dialog.OK){
			 tableViewer.refresh();
		 }
	 }

	 private Button createUpdateDataButton(final Composite parent) {
		 Button updateButton = getWidgetFactory().createButton(parent, Messages.updateData, SWT.FLAT);
		 updateButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
		 updateButton.addListener(SWT.Selection, new Listener() {

			 @Override
			 public void handleEvent(Event event) {
				 updateDataAction();
			 }
		 });
		 return updateButton;
	 }


	 private void updateDataAction() {
		 IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
		 if (selection.size() != 1) {
			 MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.selectOnlyOneElementTitle, Messages.selectOnlyOneElementMessage);
		 } else {
			 DataWizard wizard = new DataWizard((Data) selection.getFirstElement(),getDataFeature(),getDataFeatureToCheckUniqueID(), getShowAutoGenerateForm());
			 wizard.setIsPageFlowContext(isPageFlowContext());
			 wizard.setIsOverviewContext(isOverViewContext());
			 WizardDialog wizardDialog = new DataWizardDialog(Display.getCurrent().getActiveShell(), wizard,null);
			 wizardDialog.open();
			 tableViewer.setInput(getEObject());
		 }
	 }

	 protected boolean getShowAutoGenerateForm() {
		 return true;
	 }

	 protected EStructuralFeature getDataFeature() {
		 return ProcessPackage.Literals.DATA_AWARE__DATA;
	 }

	 protected Set<EStructuralFeature> getDataFeatureToCheckUniqueID() {
		 Set<EStructuralFeature> res = new HashSet<EStructuralFeature>();
		 res.add(ProcessPackage.Literals.DATA_AWARE__DATA);
		 return res;
	 }


	 protected void refreshBindings() {
		 if (tableViewer != null && getEObject() != null) {
			 bindDataTree();
		 }
	 }


	 private void bindDataTree() {

		 if(observeDataList != null
				 && dataListener != null
				 && observeDataListNames != null
				 && observeDataListType != null){
			 observeDataList.removeChangeListener(dataListener);
			 observeDataList.dispose();
			 observeDataListNames.removeChangeListener(dataListener);
			 observeDataListNames.dispose();
			 observeDataListType.removeChangeListener(dataListener);
			 observeDataListType.dispose();
			 observeTransient.removeChangeListener(dataListener);
			 observeTransient.dispose();
			 observeJObjectType.removeChangeListener(dataListener);
			 observeJObjectType.dispose();
		 }


		 IEMFEditListProperty list = EMFEditProperties.list(getEditingDomain(), getDataFeature());
		 observeDataList = list.observe(getEObject());
		 observeDataListNames = list.values(ProcessPackage.Literals.ELEMENT__NAME).observe(getEObject());
		 observeDataListType = list.values(ProcessPackage.Literals.DATA__DATA_TYPE).observe(getEObject());
		 observeTransient = list.values(ProcessPackage.Literals.DATA__TRANSIENT).observe(getEObject());
		 observeJObjectType = list.values(ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME).observe(getEObject());
		 dataListener = new IChangeListener() {

			 @Override
			 public void handleChange(ChangeEvent event) {
				 refreshDataTree();
			 }
		 };
		 observeDataList.addChangeListener(dataListener);
		 observeDataListNames.addChangeListener(dataListener);
		 observeDataListType.addChangeListener(dataListener);
		 observeTransient.addChangeListener(dataListener);
		 observeJObjectType.addChangeListener(dataListener);

		 tableViewer.setInput(getEObject());
		 updateButtons();
	 }

	 /**
	  * 
	  */
	  protected void refreshDataTree() {
		 if(!tableViewer.getTable().isDisposed()){
			 tableViewer.setInput(getEObject());
		 }
	 }

	 /*
	  * (non-Javadoc)
	  * 
	  * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
	  * AbstractModelerPropertySection#setEObject(org.eclipse.emf.ecore.EObject)
	  */
	  @Override
	  public void setEObject(EObject object) {
		 super.setEObject(object);
		 refreshBindings();
	  }

	  /*
	   * (non-Javadoc)
	   * 
	   * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
	   * AbstractModelerPropertySection#getEObject()
	   */
	  @Override
	  protected EObject getEObject() {
		  EObject eObject = super.getEObject();
		  if (eObject instanceof Lane) {
			  return ModelHelper.getParentProcess(eObject);
		  }
		  return eObject;
	  }


	  /* (non-Javadoc)
	   * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#dispose()
	   */
	  @Override
	  public void dispose() {
		  if(observeDataList != null) {
			  observeDataList.dispose();
		  }
		  if(observeDataListNames != null) {
			  observeDataListNames.dispose();
		  }
		  if(observeDataListType != null) {
			  observeDataListType.dispose();
		  }
		  if(observeTransient != null) {
			  observeTransient.dispose();
		  }
		  super.dispose();
	  }

	  @Override
	  public void doubleClick(DoubleClickEvent event) {
		  updateDataAction();
	  }



	  @Override
	  public void selectionChanged(SelectionChangedEvent event) {
		  updateButtons() ;
	  }

	  @Override
	  public String getSectionDescription() {
		  return Messages.dataSectionDescription;
	  }
	  
	  
	  @Override
	public boolean isPageFlowContext() {
		return isPageFlowContext;
	}
	  
	  @Override
	public void setIsPageFlowContext(boolean isPageFlowContext) {
		this.isPageFlowContext=isPageFlowContext;
		
	}
	  
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
	return isOverviewContext;
		
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
		this.isOverviewContext = isOverviewContext;
	}
}
