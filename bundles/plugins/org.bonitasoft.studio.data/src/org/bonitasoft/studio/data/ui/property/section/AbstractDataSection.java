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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
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
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
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
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractDataSection extends AbstractBonitaDescriptionSection implements IDoubleClickListener, IBonitaVariableContext, IAddData {

    private Button updateDataButton;

    private Button removeDataButton;

    private Button promoteDataButton;

    protected Composite mainComposite;

    private TableViewer dataTableViewer;

    private boolean isPageFlowContext = false;

    protected EMFDataBindingContext context;

    private boolean isOverviewContext = false;

    @Override
    protected void createContent(final Composite parent) {
        mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayout(createMainCompositeLayout());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Composite dataComposite = getWidgetFactory().createComposite(mainComposite);
        dataComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        dataComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).extendedMargins(0, 20, 5, 15).create());
        createDataComposite(dataComposite);
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

    protected WizardDialog createWizardDialog(
            final Wizard wizard, final String finishLabel) {
        return new CustomWizardDialog(getActiveShell(), wizard, finishLabel);
    }

    protected Shell getActiveShell() {
        return Display.getDefault().getActiveShell();
    }

    protected IStructuredSelection getStructuredSelection(final ISelectionProvider selectionProvider) {
        return (IStructuredSelection) selectionProvider.getSelection();
    }

    protected GridLayout createMainCompositeLayout() {
        return GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 15).create();
    }

    protected void createDataComposite(final Composite parent) {
        createViewerHeaderLabel(parent);

        final Composite buttonsComposite = getWidgetFactory().createComposite(parent, SWT.NONE);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());

        createAddDataButton(buttonsComposite);
        updateDataButton = createEditDataButton(buttonsComposite);
        removeDataButton = createRemoveDataButton(buttonsComposite);
        promoteDataButton = createMoveDataButton(buttonsComposite);

        dataTableViewer = new TableViewer(
                getWidgetFactory().createTable(parent, GTKStyleHandler
                        .removeBorderFlag(SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS | SWT.H_SCROLL | SWT.V_SCROLL)));
        dataTableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
        dataTableViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_PROCESS_DATA_LIST);
        dataTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
        dataTableViewer.setSorter(new ViewerSorter());
        dataTableViewer.addDoubleClickListener(this);
        dataTableViewer.getTable().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    e.doit = false;
                    removeData((IStructuredSelection) dataTableViewer.getSelection());
                }
            }
        });
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

    protected abstract void createViewerHeaderLabel(final Composite parent);

    public TableViewer getDataTableViewer() {
        return dataTableViewer;
    }

    protected Button createRemoveDataButton(final Composite parent) {
        final Button removeButton = getWidgetFactory().createButton(parent, Messages.removeData, SWT.FLAT);
        removeButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_REMOVE_PROCESS_DATA);
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

    protected void removeData(final IStructuredSelection structuredSelection) {
        new RemoveDataHandler().execute(structuredSelection, getEObject(), getDataFeature());
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
        addDataButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA);
        addDataButton.setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addDataButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addData();
            }

        });
    }

    @Override
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
        updateButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_EDIT_PROCESS_DATA);
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
        return Messages.localVariableDecsription;
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
