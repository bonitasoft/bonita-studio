/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.data.ui.property.section;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.ui.BusinessObjectDataStyledLabelProvider;
import org.bonitasoft.studio.businessobject.ui.wizard.AddBusinessObjectDataWizard;
import org.bonitasoft.studio.businessobject.ui.wizard.EditBusinessObjectDataWizard;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.DataPlugin;
import org.bonitasoft.studio.data.commands.MoveDataCommand;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.ui.wizard.DataWizard;
import org.bonitasoft.studio.data.ui.wizard.DataWizardDialog;
import org.bonitasoft.studio.data.ui.wizard.MoveDataWizard;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.refactoring.core.RefactorDataOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractDataSection extends AbstractBonitaDescriptionSection implements IDoubleClickListener, IBonitaVariableContext {

    private Button updateDataButton;

    private Button removeDataButton;

    private Button promoteDataButton;

    protected Composite mainComposite;

    private TableViewer dataTableViewer;

    private boolean isPageFlowContext = false;

    protected EMFDataBindingContext context;

    private boolean isOverviewContext = false;

    private static final String XTEXT_BUILDER_ID = "org.eclipse.xtext.ui.shared.xtextBuilder";

    private TableViewer businessDataTableViewer;

    private Button updateBusinessDataButton;

    private Button removeBusinessDataButton;

    private Composite businessDataComposite;

    @Override
    protected void createContent(final Composite parent) {
        mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayout(createMainCompositeLayout());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Composite dataComposite = getWidgetFactory().createComposite(mainComposite);
        dataComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        dataComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        createDataComposite(dataComposite);

        createBusinessData();
    }

    protected void createBusinessData() {
        businessDataComposite = createMainComposite();
        createBusinessDataComposite(businessDataComposite);
    }

    protected Composite createMainComposite() {
        final Composite dataComposite = getWidgetFactory().createComposite(mainComposite);
        dataComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).indent(20, 0).create());
        dataComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(2, 3).create());
        return dataComposite;
    }

    protected ViewerFilter hideBusinessObjectData() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return !(element instanceof BusinessObjectData);
            }
        };
    }

    protected BusinessObjectModelRepositoryStore getBusinessObjectDefinitionRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    protected WizardDialog createWizardDialog(
            final Wizard wizard, final String finishLabel) {
        return new CustomWizardDialog(getActiveShell(), wizard, finishLabel);
    }

    protected void createBusinessDataComposite(final Composite parent) {
        final Composite mainComposite = getWidgetFactory().createComposite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).indent(20, 0).span(2, 1).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        getWidgetFactory().createLabel(mainComposite, "", SWT.NONE);
        final Label label = getWidgetFactory().createLabel(mainComposite, Messages.businessData, SWT.NONE);
        label.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).span(1, 1).create());

        final ControlDecoration controlDecoration = new ControlDecoration(label, SWT.RIGHT, mainComposite);
        controlDecoration.setShowOnlyOnFocus(false);
        controlDecoration.setDescriptionText(Messages.businessDataHint);
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));

        final Composite buttonsComposite = getWidgetFactory().createPlainComposite(mainComposite, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(3, 3).create());

        createAddBusinessDataButton(buttonsComposite);
        updateBusinessDataButton = createEditBusinessDataButton(buttonsComposite);
        removeBusinessDataButton = createRemoveBusinessDataButton(buttonsComposite);

        businessDataTableViewer = new TableViewer(mainComposite, SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS | SWT.H_SCROLL | SWT.V_SCROLL);
        businessDataTableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
        getWidgetFactory().adapt(businessDataTableViewer.getTable(), false, false);
        businessDataTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
        businessDataTableViewer.setSorter(new ViewerSorter());
        businessDataTableViewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent event) {
                editBusinessData();
            }
        });

        businessDataTableViewer.addFilter(acceptBusinessObjectData());

        final ObservableListContentProvider contentProvider = new ObservableListContentProvider();
        businessDataTableViewer.setContentProvider(contentProvider);
        final IObservableSet knownElements = contentProvider.getKnownElements();
        final IObservableMap[] labelMaps = EMFObservables.observeMaps(knownElements, new EStructuralFeature[] { ProcessPackage.Literals.ELEMENT__NAME,
                ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME });
        businessDataTableViewer.setLabelProvider(new BusinessObjectDataStyledLabelProvider(RepositoryManager.getInstance().getRepositoryStore(
                BusinessObjectModelRepositoryStore.class), labelMaps));

    }

    protected void createAddBusinessDataButton(final Composite parent) {
        final Button addBusinessObjectDataButton = new Button(parent, SWT.FLAT);
        addBusinessObjectDataButton.setText(Messages.addData);
        addBusinessObjectDataButton.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addBusinessObjectDataButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addBusinessData();
            }
        });
    }

    protected void addBusinessData() {
        final AddBusinessObjectDataWizard addBusinessObjectDataWizard = createAddBusinessObjectDataWizard();
        createWizardDialog(addBusinessObjectDataWizard, IDialogConstants.FINISH_LABEL).open();
    }

    protected AddBusinessObjectDataWizard createAddBusinessObjectDataWizard() {
        return new AddBusinessObjectDataWizard((DataAware) getEObject(),
                getBusinessObjectDefinitionRepositoryStore(),
                getEditingDomain());
    }

    protected Shell getActiveShell() {
        return Display.getDefault().getActiveShell();
    }

    protected ViewerFilter acceptBusinessObjectData() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return element instanceof BusinessObjectData;
            }
        };
    }

    protected Button createRemoveBusinessDataButton(final Composite buttonsComposite) {
        final Button removeButton = getWidgetFactory().createButton(buttonsComposite, Messages.removeData, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        removeButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                removeData(getStructuredSelection(businessDataTableViewer));
            }
        });
        return removeButton;
    }

    protected Button createEditBusinessDataButton(final Composite buttonsComposite) {
        final Button updateButton = getWidgetFactory().createButton(buttonsComposite, Messages.updateData, SWT.FLAT);
        updateButton.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        updateButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                editBusinessData();
            }
        });
        return updateButton;
    }

    protected void editBusinessData() {
        final IStructuredSelection selection = getStructuredSelection(businessDataTableViewer);
        if (onlyOneElementSelected(selection)) {
            final BusinessObjectData selectedData = (BusinessObjectData) selection.getFirstElement();
            final EditBusinessObjectDataWizard wizard = createEditBusinessDataWizard(selectedData);
            createWizardDialog(wizard, IDialogConstants.OK_LABEL).open();
            refreshBusinessDataTableViewer();
        }
    }

    protected void refreshBusinessDataTableViewer() {
        businessDataTableViewer.refresh();
    }

    protected IStructuredSelection getStructuredSelection(final ISelectionProvider selectionProvider) {
        return (IStructuredSelection) selectionProvider.getSelection();
    }

    protected EditBusinessObjectDataWizard createEditBusinessDataWizard(
            final BusinessObjectData selectedData) {
        return new EditBusinessObjectDataWizard(selectedData, getBusinessObjectDefinitionRepositoryStore(), getEditingDomain());
    }

    protected GridLayout createMainCompositeLayout() {
        return GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 15).create();
    }

    protected void createDataComposite(final Composite parent) {
        createLabel(parent);

        final Composite buttonsComposite = getWidgetFactory().createPlainComposite(parent, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());

        createAddDataButton(buttonsComposite);
        updateDataButton = createEditDataButton(buttonsComposite);
        removeDataButton = createRemoveDataButton(buttonsComposite);
        promoteDataButton = createMoveDataButton(buttonsComposite);

        dataTableViewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS | SWT.H_SCROLL | SWT.V_SCROLL);
        dataTableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
        getWidgetFactory().adapt(dataTableViewer.getTable(), false, false);
        dataTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
        dataTableViewer.setSorter(new ViewerSorter());
        dataTableViewer.addDoubleClickListener(this);
        final ObservableListContentProvider contentProvider = new ObservableListContentProvider();
        dataTableViewer.setContentProvider(contentProvider);
        dataTableViewer.addFilter(hideBusinessObjectData());
        // create the label provider including monitoring
        // of the changes of the labels
        final IObservableSet knownElements = contentProvider.getKnownElements();
        final IObservableMap[] labelMaps = EMFObservables.observeMaps(knownElements, new EStructuralFeature[] { ProcessPackage.Literals.ELEMENT__NAME,
                ProcessPackage.Literals.DATA__DATA_TYPE });
        dataTableViewer.setLabelProvider(new DataStyledTreeLabelProvider(labelMaps));

    }

    protected void createLabel(final Composite parent) {
        getWidgetFactory().createLabel(parent, "", SWT.NONE);
        final Label label = getWidgetFactory().createLabel(parent, Messages.processData, SWT.NONE);
        label.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).span(1, 1).create());

        final ControlDecoration controlDecoration = new ControlDecoration(label, SWT.RIGHT);
        controlDecoration.setShowOnlyOnFocus(false);
        controlDecoration.setDescriptionText(Messages.processDataHint);
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
    }

    public TableViewer getDataTableViewer() {
        return dataTableViewer;
    }

    protected Button createRemoveDataButton(final Composite parent) {
        final Button removeButton = getWidgetFactory().createButton(parent, Messages.removeData, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        removeButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                if (dataTableViewer != null && !((IStructuredSelection) dataTableViewer.getSelection()).isEmpty()) {
                    removeData((IStructuredSelection) dataTableViewer.getSelection());
                }
            }

        });
        return removeButton;
    }

    protected String createMessage(final IStructuredSelection structruredSelection) {
        final Object[] selection = structruredSelection.toArray();
        final StringBuilder res = new StringBuilder(Messages.deleteDialogConfirmMessage);
        res.append(' ');
        res.append(((Data) selection[0]).getName());
        for (int i = 1; i < selection.length; i++) {
            res.append(", ");res.append(((Data) selection[i]).getName()); //$NON-NLS-1$
        }
        res.append(" ?"); //$NON-NLS-1$
        return res.toString();
    }

    protected void removeData(final IStructuredSelection structuredSelection) {
        final String[] buttonList = { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
        final OutlineDialog dialog = new OutlineDialog(Display.getDefault().getActiveShell(),
                org.bonitasoft.studio.common.Messages.removalConfirmationDialogTitle, Display.getCurrent().getSystemImage(
                        SWT.ICON_WARNING), createMessage(structuredSelection), MessageDialog.CONFIRM, buttonList, 1, structuredSelection.toList());
        if (dialog.open() == Dialog.OK) {
            final IProgressService service = PlatformUI.getWorkbench().getProgressService();
            final RefactorDataOperation op = new RefactorDataOperation(RefactoringOperationType.REMOVE);
            for (final Object d : structuredSelection.toList()) {
                op.setEditingDomain(getEditingDomain());
                op.addItemToRefactor(null, (Data) d);
                op.setDataContainer((DataAware) getEObject());
                op.setDataContainmentFeature(getDataFeature());
                op.setAskConfirmation(true);
            }
            try {
                if (op.canExecute()) {
                    service.run(true, true, op);
                }
            } catch (final InvocationTargetException e) {
                BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
            }
            try {
                RepositoryManager.getInstance().getCurrentRepository().getProject()
                        .build(IncrementalProjectBuilder.FULL_BUILD, XTEXT_BUILDER_ID, Collections.<String, String> emptyMap(), null);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
            }
        }
    }

    protected Button createMoveDataButton(final Composite parent) {
        final Button moveData = getWidgetFactory().createButton(parent, Messages.moveData, SWT.FLAT);
        moveData.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        moveData.setToolTipText(Messages.moveData_tooltip);
        moveData.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                moveData((IStructuredSelection) dataTableViewer.getSelection());
            }
        });
        return moveData;
    }

    @SuppressWarnings("unchecked")
    protected void moveData(final IStructuredSelection structuredSelection) {
        final MoveDataWizard moveDataWizard = new MoveDataWizard((DataAware) getEObject());
        if (new WizardDialog(Display.getDefault().getActiveShell(), moveDataWizard).open() == Dialog.OK) {
            final DataAware dataAware = moveDataWizard.getSelectedDataAwareElement();
            try {
                final MoveDataCommand cmd = new MoveDataCommand(getEditingDomain(), (DataAware) getEObject(), structuredSelection.toList(), dataAware);
                OperationHistoryFactory.getOperationHistory().execute(cmd, null, null);

                if (!(cmd.getCommandResult().getStatus().getSeverity() == Status.OK)) {
                    final List<Object> data = (List<Object>) cmd.getCommandResult().getReturnValue();
                    String dataNames = "";
                    for (final Object d : data) {
                        dataNames = dataNames + ((Element) d).getName() + ",";
                    }
                    dataNames = dataNames.substring(0, dataNames.length() - 1);
                    MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.PromoteDataWarningTitle,
                            Messages.bind(Messages.PromoteDataWarningMessage, dataNames));
                }

            } catch (final ExecutionException e1) {
                BonitaStudioLog.error(e1);
            }
            refresh();
        }
    }

    protected void createAddDataButton(final Composite parent) {
        final Button addDataButton = getWidgetFactory().createButton(parent, Messages.addData, SWT.FLAT);
        addDataButton.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addDataButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addData();
            }

        });
    }

    public void addData() {
        final DataWizard wizard = new DataWizard(getEditingDomain(), getEObject(), getDataFeature(), getDataFeatureToCheckUniqueID(), getShowAutoGenerateForm());
        wizard.setIsPageFlowContext(isPageFlowContext());
        wizard.setIsOverviewContext(isOverViewContext());
        if (new DataWizardDialog(Display.getCurrent().getActiveShell(), wizard, this).open() == Dialog.OK) {
            dataTableViewer.refresh();
        }
    }

    protected Button createEditDataButton(final Composite parent) {
        final Button updateButton = getWidgetFactory().createButton(parent, Messages.updateData, SWT.FLAT);
        updateButton.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        updateButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                editData();
            }
        });
        return updateButton;
    }

    protected void editData() {
        final IStructuredSelection selection = (IStructuredSelection) dataTableViewer.getSelection();
        if (onlyOneElementSelected(selection)) {
            final Data selectedData = (Data) selection.getFirstElement();
            if (selectedData.eContainer() == null) {
                final AbstractProcess parentProcess = ModelHelper.getParentProcess(eObject);
                BonitaStudioLog.error("Investigation trace for issue BS-11552:\n"
                        + "The context was not initialized.\n "
                        + "Please report the issue with details and attached impacted process.\n"
                        + "data: " + (selectedData != null ? selectedData.getName() : "null data") + "\n"
                        + "From diagram:" + (parentProcess != null ? parentProcess.getName() : "No process found."), DataPlugin.PLUGIN_ID);
            }

            final DataWizard wizard = new DataWizard(getEditingDomain(), selectedData, getDataFeature(),
                    getDataFeatureToCheckUniqueID(), getShowAutoGenerateForm());
            wizard.setIsPageFlowContext(isPageFlowContext());
            wizard.setIsOverviewContext(isOverViewContext());
            new CustomWizardDialog(Display.getDefault().getActiveShell(), wizard, IDialogConstants.OK_LABEL).open();
            dataTableViewer.refresh();
        }
    }

    protected boolean onlyOneElementSelected(final IStructuredSelection selection) {
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
        final Set<EStructuralFeature> res = new HashSet<EStructuralFeature>();
        res.add(ProcessPackage.Literals.DATA_AWARE__DATA);
        return res;
    }

    protected void bindSection() {
        if (context != null) {
            context.dispose();
        }
        context = new EMFDataBindingContext();
        if (getEObject() != null && dataTableViewer != null) {
            final IObservableList dataObservableList = EMFEditObservables.observeList(getEditingDomain(), getEObject(), getDataFeature());
            dataTableViewer.setInput(dataObservableList);
            final UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
            enableStrategy.setConverter(new Converter(Data.class, Boolean.class) {

                @Override
                public Object convert(final Object fromObject) {
                    return fromObject != null;
                }

            });

            context.bindValue(SWTObservables.observeEnabled(updateDataButton), ViewersObservables.observeSingleSelection(dataTableViewer),
                    new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableStrategy);
            context.bindValue(SWTObservables.observeEnabled(removeDataButton), ViewersObservables.observeSingleSelection(dataTableViewer),
                    new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableStrategy);

            if (promoteDataButton != null) {
                final UpdateValueStrategy enableMoveStrategy = new UpdateValueStrategy();
                enableMoveStrategy.setConverter(new Converter(Data.class, Boolean.class) {

                    @Override
                    public Object convert(final Object fromObject) {
                        return fromObject != null && ModelHelper.getParentProcess(getEObject()) != null && !((Data) fromObject).isTransient();
                    }

                });
                context.bindValue(SWTObservables.observeEnabled(promoteDataButton), ViewersObservables.observeSingleSelection(dataTableViewer),
                        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableMoveStrategy);
            }

            if (businessDataComposite != null) {
                businessDataComposite.setVisible(getEObject() instanceof Pool);
                final UpdateValueStrategy enableStrategy2 = new UpdateValueStrategy();
                enableStrategy2.setConverter(new Converter(Data.class, Boolean.class) {

                    @Override
                    public Object convert(final Object fromObject) {
                        return fromObject != null;
                    }

                });

                final IObservableList businessDataObservableList = EMFEditObservables.observeList(getEditingDomain(), getEObject(), getDataFeature());
                businessDataTableViewer.setInput(businessDataObservableList);

                context.bindValue(SWTObservables.observeEnabled(updateBusinessDataButton),
                        ViewersObservables.observeSingleSelection(businessDataTableViewer),
                        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableStrategy2);
                context.bindValue(SWTObservables.observeEnabled(removeBusinessDataButton),
                        ViewersObservables.observeSingleSelection(businessDataTableViewer),
                        new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableStrategy2);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(final EObject object) {
        super.setEObject(object);
        bindSection();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#getEObject()
     */
    @Override
    protected EObject getEObject() {
        final EObject eObject = super.getEObject();
        if (eObject instanceof Lane) {
            return ModelHelper.getParentProcess(eObject);
        }
        return eObject;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        editData();
    }

    @Override
    public String getSectionDescription() {
        if (getEObject() instanceof Pool) {
            return Messages.dataSectionWithBusinessData;
        }
        return Messages.dataSectionDescription;
    }

    @Override
    public boolean isPageFlowContext() {
        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return isOverviewContext;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
        this.isOverviewContext = isOverviewContext;
    }
}
