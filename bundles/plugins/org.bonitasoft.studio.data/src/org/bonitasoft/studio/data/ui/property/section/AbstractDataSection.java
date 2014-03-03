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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
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
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
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
public abstract class AbstractDataSection extends AbstractBonitaDescriptionSection implements IDoubleClickListener,IBonitaVariableContext{


	private Button updateDataButton;
	private Button removeDataButton;
	private Button promoteDataButton;
	protected Composite mainComposite;
	protected ObservablesManager observablesManager = new ObservablesManager();
	private TableViewer dataTableViewer;
	private boolean isPageFlowContext = false;
	protected EMFDataBindingContext context;
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
		mainComposite.setLayout(createMainCompositeLayout());
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
		final Composite dataComposite = getWidgetFactory().createComposite(mainComposite) ;
		dataComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
		dataComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create()) ;
		createDataComposite(dataComposite);
	}


	protected GridLayout createMainCompositeLayout() {
		return GridLayoutFactory.fillDefaults().numColumns(1).margins(20, 15).create();
	}


	protected void createDataComposite(Composite parent) {
		createLabel(parent);

		Composite buttonsComposite = getWidgetFactory().createPlainComposite(parent, SWT.NONE);
		buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
		buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5,0).spacing(0, 3).create());

		createAddDataButton(buttonsComposite);
		updateDataButton = createEditDataButton(buttonsComposite);
		removeDataButton = createRemoveDataButton(buttonsComposite);
		promoteDataButton = createMoveDataButton(buttonsComposite);

		dataTableViewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS | SWT.H_SCROLL | SWT.V_SCROLL);
		dataTableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
		getWidgetFactory().adapt(dataTableViewer.getTable(), false, false) ;
		dataTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
		dataTableViewer.setSorter(new ViewerSorter());
		dataTableViewer.addDoubleClickListener(this);
		dataTableViewer.setContentProvider(new ArrayContentProvider());
		dataTableViewer.setLabelProvider(new DataStyledTreeLabelProvider());
	}

	public TableViewer getDataTableViewer(){
		return dataTableViewer;
	}

	protected abstract void createLabel(Composite dataComposite) ;


	protected Button createRemoveDataButton(final Composite parent) {
		Button removeButton = getWidgetFactory().createButton(parent, Messages.removeData, SWT.FLAT);
		removeButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
		removeButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (dataTableViewer != null && !((IStructuredSelection) dataTableViewer.getSelection()).isEmpty()) {
					removeData(((IStructuredSelection) dataTableViewer.getSelection()));
				}
			}

		});
		return removeButton;
	}

	protected String createMessage(IStructuredSelection structruredSelection) {
		Object[] selection = structruredSelection.toArray();
		StringBuilder res = new StringBuilder(Messages.deleteDialogConfirmMessage);
		res.append(' ');
		res.append(((Data) selection[0]).getName());
		for (int i = 1; i < selection.length; i++) {
			res.append(", ");res.append(((Data) selection[i]).getName()); //$NON-NLS-1$
		}
		res.append(" ?"); //$NON-NLS-1$
		return res.toString();
	}
	

protected void removeData(IStructuredSelection structuredSelection) {
			String[] buttonList = {IDialogConstants.OK_LABEL,IDialogConstants.CANCEL_LABEL};
			OutlineDialog dialog = new OutlineDialog(Display.getDefault().getActiveShell(), Messages.deleteDataDialogTitle, Display.getCurrent().getSystemImage(SWT.ICON_WARNING),createMessage(structuredSelection),MessageDialog.CONFIRM,buttonList,1,structuredSelection.toList());
			if (dialog.open() == Dialog.OK) {
				IProgressService service = PlatformUI.getWorkbench().getProgressService();
				CompoundCommand cc = new CompoundCommand("Remove list of data");
				boolean canExecute=false;
				for(Object d : structuredSelection.toList()){
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
			try {
				RepositoryManager.getInstance().getCurrentRepository().getProject().build(IncrementalProjectBuilder.FULL_BUILD,XtextProjectHelper.BUILDER_ID,new HashMap<String, String>(),null);
			} catch (CoreException e) {
				BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
			}
		}
	}


	protected Button createMoveDataButton(final Composite parent) {
		Button moveData = getWidgetFactory().createButton(parent, Messages.moveData, SWT.FLAT);
		moveData.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
		moveData.setToolTipText(Messages.moveData_tooltip);
		moveData.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				moveData((IStructuredSelection) dataTableViewer.getSelection());
			}
		});
		return moveData;
	}

	@SuppressWarnings("unchecked")
	protected void moveData(IStructuredSelection structuredSelection) {
		MoveDataWizard moveDataWizard = new MoveDataWizard((DataAware) getEObject());
		if(new WizardDialog(Display.getDefault().getActiveShell(),moveDataWizard).open() == Dialog.OK){
			DataAware dataAware = moveDataWizard.getSelectedDataAwareElement();
			try {
				MoveDataCommand cmd = new MoveDataCommand(getEditingDomain(), (DataAware) getEObject(), structuredSelection.toList(), dataAware);
				OperationHistoryFactory.getOperationHistory().execute(cmd, null, null);

				if (!(cmd.getCommandResult().getStatus().getSeverity() == Status.OK)) {
					List<Object> data = (List<Object>) cmd.getCommandResult().getReturnValue();
					String dataNames = "";
					for (Object d : data) {
						dataNames = dataNames + ((Element) d).getName() + ",";
					}
					dataNames = dataNames.substring(0, dataNames.length() - 1);
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.PromoteDataWarningTitle,
							Messages.bind(Messages.PromoteDataWarningMessage, dataNames));
				}

			} catch (ExecutionException e1) {
				BonitaStudioLog.error(e1);
			}
			refresh();
		}
	}

	protected void createAddDataButton(final Composite parent) {
		final Button addDataButton = getWidgetFactory().createButton(parent, Messages.addData, SWT.FLAT);
		addDataButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
		addDataButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addData();
			}

		});
	}


	public void addData(){
		DataWizard wizard= new DataWizard(getEObject(), getDataFeature(), getDataFeatureToCheckUniqueID(), getShowAutoGenerateForm());
		wizard.setIsPageFlowContext(isPageFlowContext());
		wizard.setIsOverviewContext(isOverViewContext());
		if(new DataWizardDialog(Display.getCurrent().getActiveShell(),wizard ,this).open() == Dialog.OK){
			dataTableViewer.refresh();
		}
	}

	protected Button createEditDataButton(final Composite parent) {
		Button updateButton = getWidgetFactory().createButton(parent, Messages.updateData, SWT.FLAT);
		updateButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create()) ;
		updateButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				editData();
			}
		});
		return updateButton;
	}


	protected void editData() {
		IStructuredSelection selection = (IStructuredSelection) dataTableViewer.getSelection();
		if(onlyOneElementSelected(selection)){
			Data selectedData = (Data) selection.getFirstElement();
			DataWizard wizard = new DataWizard(selectedData,getDataFeature(),getDataFeatureToCheckUniqueID(), getShowAutoGenerateForm());
			wizard.setIsPageFlowContext(isPageFlowContext());
			wizard.setIsOverviewContext(isOverViewContext());
			new CustomWizardDialog(Display.getCurrent().getActiveShell(), wizard ,IDialogConstants.OK_LABEL).open();
			dataTableViewer.refresh() ;
		}
	}

	protected boolean onlyOneElementSelected(IStructuredSelection selection) {
		if (selection.size() != 1) {
			MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.selectOnlyOneElementTitle, Messages.selectOnlyOneElementMessage);
			return false;
		}
		return true;
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


	protected void bindSection() {
		if(context != null){
			context.dispose();
		}
		context = new EMFDataBindingContext();
		if (getEObject() != null) {
			if(dataTableViewer != null){
				context.bindValue(ViewersObservables.observeInput(dataTableViewer), EMFEditObservables.observeValue(getEditingDomain(), getEObject(),getDataFeature()));
				final UpdateValueStrategy enableStrategy = new UpdateValueStrategy() ;
				enableStrategy.setConverter(new Converter(Data.class,Boolean.class){

					@Override
					public Object convert(Object fromObject) {
						return fromObject != null;
					}

				});

				context.bindValue(SWTObservables.observeEnabled(updateDataButton), ViewersObservables.observeSingleSelection(dataTableViewer), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableStrategy);
				context.bindValue(SWTObservables.observeEnabled(removeDataButton), ViewersObservables.observeSingleSelection(dataTableViewer), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableStrategy);

				if(promoteDataButton != null){
					final UpdateValueStrategy enableMoveStrategy = new UpdateValueStrategy() ;
					enableMoveStrategy.setConverter(new Converter(Data.class,Boolean.class){

						@Override
						public Object convert(Object fromObject) {
							return fromObject != null && ModelHelper.getParentProcess(getEObject()) != null && !((Data)fromObject).isTransient();
						}

					});
					context.bindValue(SWTObservables.observeEnabled(promoteDataButton), ViewersObservables.observeSingleSelection(dataTableViewer), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableMoveStrategy);
				}
			}
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
		bindSection();
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
		super.dispose();
		if(context != null){
			context.dispose();
		}
	}

	@Override
	public void doubleClick(DoubleClickEvent event) {
		editData();
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

