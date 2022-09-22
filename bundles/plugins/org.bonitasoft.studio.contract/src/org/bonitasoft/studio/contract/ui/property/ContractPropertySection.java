/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.bonitasoft.studio.businessobject.ui.expression.CreateBusinessDataProposalListener;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.ContractConstraintBuilder;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.constraint.ContractConstraintController;
import org.bonitasoft.studio.contract.ui.property.constraint.ContractConstraintsTableViewer;
import org.bonitasoft.studio.contract.ui.property.input.ContractInputController;
import org.bonitasoft.studio.contract.ui.property.input.ContractInputTreeViewer;
import org.bonitasoft.studio.contract.ui.wizard.AddInputContractFromDataWizardDialog;
import org.bonitasoft.studio.contract.ui.wizard.ContractInputGenerationInfoDialogFactory;
import org.bonitasoft.studio.contract.ui.wizard.ContractInputGenerationWizard;
import org.bonitasoft.studio.contract.ui.wizard.ContractInputGenerationWizardPagesFactory;
import org.bonitasoft.studio.data.ui.property.section.PoolAdaptableSelectionProvider;
import org.bonitasoft.studio.designer.ui.contribution.CreateAndEditFormContributionItem;
import org.bonitasoft.studio.document.ui.DocumentProposalListener;
import org.bonitasoft.studio.groovy.ui.viewer.GroovySourceViewerFactory;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class ContractPropertySection extends AbstractBonitaDescriptionSection {

    private EMFDataBindingContext context;

    private ContractInputController inputController;

    private ContractConstraintController constraintController;

    private final ContractContainerAdaptableSelectionProvider selectionProvider;

    private final IEclipseContext eclipseContext;

    private final IProgressService progressService;

    private final ISharedImages sharedImages;

    private final RepositoryAccessor repositoryAccessor;

    private final FieldToContractInputMappingOperationBuilder fieldToContractInputMappingOperationBuilder;

    private final FieldToContractInputMappingExpressionBuilder fieldToContractInputMappingExpressionBuilder;

    private final PoolAdaptableSelectionProvider poolSelectionProvider;

    private ContractConstraintBuilder contractConstraintBuilder;

    @Inject
    public ContractPropertySection(final ISharedImages sharedImages,
            final IEclipseContext eclipseContext,
            final ContractContainerAdaptableSelectionProvider selectionProvider,
            final PoolAdaptableSelectionProvider poolSelectionProvider,
            final RepositoryAccessor repositoryAccessor,
            final FieldToContractInputMappingOperationBuilder fieldToContractInputMappingOperationBuilder,
            final FieldToContractInputMappingExpressionBuilder fieldToContractInputMappingExpressionBuilder,
            final ContractConstraintBuilder contractConstraintBuilder,
            final IProgressService progressService) {
        this.eclipseContext = eclipseContext;
        this.repositoryAccessor = repositoryAccessor;
        this.selectionProvider = selectionProvider;
        this.poolSelectionProvider = poolSelectionProvider;
        this.progressService = progressService;
        this.sharedImages = sharedImages;
        this.fieldToContractInputMappingOperationBuilder = fieldToContractInputMappingOperationBuilder;
        this.fieldToContractInputMappingExpressionBuilder = fieldToContractInputMappingExpressionBuilder;
        this.contractConstraintBuilder = contractConstraintBuilder;
    }

    @Override
    public String getSectionDescription() {
        return selectionProvider.getAdapter(EObject.class) instanceof Task ? Messages.stepContractSectionDescription
                : Messages.processContractSectionDescription;
    }

    @Override
    public String getSectionTitle() {
        return selectionProvider.getAdapter(EObject.class) instanceof Task ? Messages.taskInputs
                : Messages.processInstantiationInputs;
    }

    protected void init(final IObservableValue observeContractValue) {
        inputController = new ContractInputController(progressService);
        constraintController = new ContractConstraintController(observeContractValue);
        context = new EMFDataBindingContext();
    }

    @Override
    protected void updateToolbar(final IToolBarManager toolbarManager) {
        final CreateAndEditFormContributionItem newFormContributionItem = newContributionItem(
                CreateAndEditFormContributionItem.class);
        newFormContributionItem.setSelectionProvider(selectionProvider);
        toolbarManager.add(newFormContributionItem);
    }

    protected <T> T newContributionItem(final Class<T> clazz) {
        return ContextInjectionFactory.make(clazz, eclipseContext);
    }

    @Override
    public void refresh() {
        super.refresh();
        for (final IContributionItem object : form.getToolBarManager().getItems()) {
            object.update();
        }
    }

    @Override
    protected void createContent(final Composite parent) {
        final IObservableValue observeContractValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                ProcessPackage.Literals.CONTRACT_CONTAINER__CONTRACT);
        init(observeContractValue);

        final CTabFolder tabFolder = getWidgetFactory().createTabFolder(parent, SWT.TOP);
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        getWidgetFactory().adapt(tabFolder);

        final CTabItem inputTabItem = getWidgetFactory().createTabItem(tabFolder, SWT.NULL);
        inputTabItem.setText(Messages.inputTabLabel);
        final Composite inputComposite = getWidgetFactory().createComposite(tabFolder);
        inputComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        inputComposite.setLayout(GridLayoutFactory.fillDefaults().margins(15, 10).numColumns(2).extendedMargins(5, 0, 0, 5)
                        .spacing(LayoutConstants.getSpacing().x, 15).create());

        createInputTabContent(inputComposite, observeContractValue);

        inputTabItem.setControl(inputComposite);

        final Composite constraintComposite = getWidgetFactory().createComposite(tabFolder);
        constraintComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        constraintComposite.setLayout(GridLayoutFactory.fillDefaults().margins(15, 10).numColumns(2).extendedMargins(5, 0, 0, 5)
                .spacing(LayoutConstants.getSpacing().x, 15).create());

        createConstraintTabContent(constraintComposite, observeContractValue);

        final CTabItem constraintTabItem = getWidgetFactory().createTabItem(tabFolder, SWT.NULL);
        constraintTabItem.setText(Messages.constraintsTabLabel);
        constraintTabItem.setControl(constraintComposite);
        tabFolder.setSelection(0);
    }

    private void createConstraintTabContent(final Composite parent, final IObservableValue observeContractValue) {
        final Composite buttonsComposite = createButtonContainer(parent);
        final Button addButton = createButton(buttonsComposite, Messages.add);
        final Button upButton = createButton(buttonsComposite, Messages.up);
        final Button downButton = createButton(buttonsComposite, Messages.down);
        final Button removeButton = createButton(buttonsComposite, Messages.remove);

        final ContractConstraintsTableViewer constraintsTableViewer = new ContractConstraintsTableViewer(parent,
                getWidgetFactory());
        constraintsTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(500, SWT.DEFAULT).create());
        constraintsTableViewer.initialize(constraintController, getMessageManager(), context);
        constraintsTableViewer.setInput(CustomEMFEditObservables.observeDetailList(Realm.getDefault(), observeContractValue,
                ProcessPackage.Literals.CONTRACT__CONSTRAINTS));

        constraintsTableViewer.createAddListener(addButton);
        constraintsTableViewer.createMoveUpListener(upButton);
        constraintsTableViewer.createMoveDownListener(downButton);
        constraintsTableViewer.createRemoveListener(removeButton);

        bindAddConstraintButtonEnablement(addButton, observeContractValue);
        bindRemoveButtonEnablement(removeButton, constraintsTableViewer);
        bindUpButtonEnablement(upButton, constraintsTableViewer);
        bindDownButtonEnablement(downButton, constraintsTableViewer);
    }

    private void createInputTabContent(final Composite parent, final IObservableValue observeContractValue) {
        createBdmTipsSection(parent);

        final Composite buttonsComposite = createButtonContainer(parent);

        final Button generateButton = createGenerateButton(buttonsComposite);
        final Button addButton = createButton(buttonsComposite, Messages.add);
        final Button addChildButton = createButton(buttonsComposite, Messages.addChild);
        final Button removeButton = createButton(buttonsComposite, Messages.remove);

        final ContractInputTreeViewer inputsTableViewer = new ContractInputTreeViewer(parent, getWidgetFactory(),
                progressService, sharedImages);
        inputsTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        inputsTableViewer.initialize(inputController, getMessageManager(), context);
        inputsTableViewer.setInput(observeContractValue);

        inputsTableViewer.createAddListener(addButton);
        inputsTableViewer.createAddChildListener(addChildButton);
        inputsTableViewer.createRemoveListener(removeButton);

        bindRemoveButtonEnablement(removeButton, inputsTableViewer);
        bindAddChildButtonEnablement(addChildButton, inputsTableViewer);
        bindGenerateButtonEnablement(generateButton);

    }

    private void createBdmTipsSection(Composite parent) {
        final Section bdmTipsSection = getWidgetFactory().createSection(parent, Section.NO_TITLE);
        bdmTipsSection.setLayout(GridLayoutFactory.fillDefaults().create());
        bdmTipsSection.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

        CreateBusinessDataProposalListener createBusinessDataProposalListener = new CreateBusinessDataProposalListener();
        DocumentProposalListener documentProposalListener = new DocumentProposalListener();

        Link tips = new Link(bdmTipsSection, SWT.None);
        tips.setText(getBdmTipsMessage());
        getWidgetFactory().adapt(tips, true, true);
        tips.addListener(SWT.Selection, e -> {
            if (Objects.equals(e.text, "documents")) {
                documentProposalListener.handleEvent((EObject) poolSelectionProvider.getAdapter(EObject.class),
                        "", null);
            } else {
                createBusinessDataProposalListener.handleEvent((EObject) poolSelectionProvider.getAdapter(EObject.class),
                        "", null);
            }
        });
        selectionProvider.addSelectionChangedListener(e -> tips.setText(getBdmTipsMessage()));

        bdmTipsSection.setClient(tips);
    }

    private String getBdmTipsMessage() {
        return selectionProvider.getAdapter(EObject.class) instanceof Task ? Messages.taskBdmTips
                : Messages.poolBdmTips;
    }

    private Button createGenerateButton(final Composite buttonsComposite) {
        final Button generateButton = createButton(buttonsComposite, Messages.generate);
        generateButton.setToolTipText(Messages.warningAddFromData_noDataAvailable);
        generateButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                openAddInputWizardDialog();
            }
        });
        return generateButton;
    }

    public void openAddInputWizardDialog() {
        final AddInputContractFromDataWizardDialog dialog = new AddInputContractFromDataWizardDialog(
                Display.getCurrent().getActiveShell(),
                new ContractInputGenerationWizard(
                        (ContractContainer) selectionProvider.getAdapter(EObject.class),
                        getEditingDomain(),
                        repositoryAccessor,
                        fieldToContractInputMappingOperationBuilder,
                        fieldToContractInputMappingExpressionBuilder,
                        contractConstraintBuilder,
                        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore(),
                        sharedImages, new ContractInputGenerationInfoDialogFactory(),
                        new ContractInputGenerationWizardPagesFactory(),
                        new GroovySourceViewerFactory()),
                this, true);
        dialog.open();
    }

    private Button createButton(final Composite buttonsComposite, final String label) {
        final Button button = getWidgetFactory().createButton(buttonsComposite, label, SWT.PUSH);
        button.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        return button;
    }

    private Composite createButtonContainer(final Composite parent) {
        final Composite buttonsComposite = getWidgetFactory().createComposite(parent);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).align(SWT.LEFT, SWT.TOP).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());
        return buttonsComposite;
    }

    protected void bindGenerateButtonEnablement(final Button button) {
        final ISWTObservableValue observeEnabled = WidgetProperties.enabled().observe(button);

        final IObservableValue observeVariables = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(poolSelectionProvider),
                ProcessPackage.Literals.DATA_AWARE__DATA);
        final IObservableValue observeDocuments = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(poolSelectionProvider),
                ProcessPackage.Literals.POOL__DOCUMENTS);
        context.bindValue(observeEnabled, observeVariables,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(createObserveVariableToEnableButtonConverter(observeDocuments)).create());
        context.bindValue(observeEnabled, observeDocuments, neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(createObserveDocumentToEnableButtonConverter(observeVariables)).create());
    }

    private IConverter createObserveDocumentToEnableButtonConverter(final IObservableValue observeData) {
        return new Converter(List.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                if (fromObject != null) {
                    final List<Data> dataList = newArrayList(
                            filter((List<Data>) observeData.getValue(), instanceOf(BusinessObjectData.class)));
                    final List<EObject> documentList = (List<EObject>) fromObject;
                    return !dataList.isEmpty() || documentList != null && !documentList.isEmpty();
                }
                return false;
            }
        };
    }

    private IConverter createObserveVariableToEnableButtonConverter(final IObservableValue observeData) {
        return new Converter(List.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                if (fromObject != null) {
                    final List<Data> fromObjectList = newArrayList(
                            filter((List<Data>) fromObject, instanceOf(BusinessObjectData.class)));
                    final List<EObject> documentList = (List<EObject>) observeData.getValue();
                    return !fromObjectList.isEmpty() || (documentList != null && !documentList.isEmpty());
                }
                return false;
            }
        };
    }

    protected void bindAddConstraintButtonEnablement(final Button button, final IObservableValue contractObservable) {
        final ISWTObservableValue observeEnabled = WidgetProperties.enabled().observe(button);
        final IObservableList observeDetailList = CustomEMFEditObservables.observeDetailList(Realm.getDefault(),
                contractObservable,
                ProcessPackage.Literals.CONTRACT__INPUTS);
        observeDetailList.addListChangeListener(new IListChangeListener() {

            @Override
            public void handleListChange(final ListChangeEvent event) {
                if (!button.isDisposed()) {
                    observeEnabled.setValue(!event.getObservableList().isEmpty());
                }
            }
        });
    }

    protected void bindRemoveButtonEnablement(final Button button, final Viewer viewer) {
        context.bindValue(WidgetProperties.enabled().observe(button),
                ViewersObservables.observeSingleSelection(viewer),
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
                emptySelectionToBooleanStrategy());
    }

    protected void bindUpButtonEnablement(final Button button, final Viewer viewer) {
        context.bindValue(WidgetProperties.enabled().observe(button),
                ViewersObservables.observeSingleSelection(viewer),
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
                isFirstElementToBooleanStrategy());
    }

    protected void bindDownButtonEnablement(final Button button, final Viewer viewer) {
        context.bindValue(WidgetProperties.enabled().observe(button),
                ViewersObservables.observeSingleSelection(viewer),
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
                isLastElementToBooleanStrategy());
    }

    protected void bindAddChildButtonEnablement(final Button button, final Viewer viewer) {
        context.bindValue(WidgetProperties.enabled().observe(button),
                ViewersObservables.observeSingleSelection(viewer),
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
                emptySelectionAndComplexTypeToBooleanStrategy());

        context.bindValue(
                WidgetProperties.enabled().observe(button),
                EMFObservables.observeDetailValue(Realm.getDefault(), ViewersObservables.observeSingleSelection(viewer),
                        ProcessPackage.Literals.CONTRACT_INPUT__TYPE),
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
                complexTypeToBooleanStrategy());
    }

    private UpdateValueStrategy emptySelectionToBooleanStrategy() {
        final UpdateValueStrategy modelStrategy = new UpdateValueStrategy();
        modelStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object from) {
                return from != null;
            }
        });
        return modelStrategy;
    }

    private UpdateValueStrategy isLastElementToBooleanStrategy() {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object from) {
                if (from instanceof ContractConstraint) {
                    final Contract contract = (Contract) ((ContractConstraint) from).eContainer();
                    final EList<ContractConstraint> constraints = contract.getConstraints();
                    return constraints.size() > 1 && constraints.indexOf(from) < constraints.size() - 1;
                }
                return false;
            }
        });
        return strategy;
    }

    private UpdateValueStrategy isFirstElementToBooleanStrategy() {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object from) {
                if (from instanceof ContractConstraint) {
                    final Contract contract = (Contract) ((ContractConstraint) from).eContainer();
                    final EList<ContractConstraint> constraints = contract.getConstraints();
                    return constraints.size() > 1 && constraints.indexOf(from) > 0;
                }
                return false;
            }
        });
        return strategy;
    }

    private UpdateValueStrategy emptySelectionAndComplexTypeToBooleanStrategy() {
        final UpdateValueStrategy modelStrategy = new UpdateValueStrategy();
        modelStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object from) {
                if (from instanceof ContractInput) {
                    return ((ContractInput) from).getType() == ContractInputType.COMPLEX;
                }
                return false;
            }
        });
        return modelStrategy;
    }

    private UpdateValueStrategy complexTypeToBooleanStrategy() {
        final UpdateValueStrategy modelStrategy = new UpdateValueStrategy();
        modelStrategy.setConverter(new Converter(ContractInputType.class, Boolean.class) {

            @Override
            public Object convert(final Object from) {
                if (from instanceof ContractInputType) {
                    return from == ContractInputType.COMPLEX;
                }
                return false;
            }
        });
        return modelStrategy;
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        selectionProvider.setSelection(selection);
        poolSelectionProvider.setSelection(selection);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
    }

}
