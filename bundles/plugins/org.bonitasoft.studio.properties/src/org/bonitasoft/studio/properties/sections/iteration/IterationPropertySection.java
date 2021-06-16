/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.iteration;

import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import javax.inject.Inject;

import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.properties.Well;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.ui.property.section.DataLabelProvider;
import org.bonitasoft.studio.expression.editor.constant.ExpressionReturnTypeContentProvider;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.bonitasoft.studio.expression.editor.viewer.DefaultExpressionNameResolver;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.groovy.DisplayEngineExpressionWithName;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.refactoring.core.RefactorDataOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.emf.DetailObservableValueWithRefactor;
import org.bonitasoft.studio.refactoring.core.emf.EMFEditWithRefactorObservables;
import org.bonitasoft.studio.refactoring.core.emf.EditingDomainEObjectObservableValueWithRefactoring;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.observable.masterdetail.DetailObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.search.JavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.progress.ProgressManager;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class IterationPropertySection extends AbstractBonitaDescriptionSection {

    private static final int NAME_MAX_LENGTH = 50;

    private EMFDataBindingContext context;

    private ISWTObservableValue returnTypeComboTextObservable;

    private ComboViewer returnTypeCombo;

    private IObservableValue expressionReturnTypeDetailValue;

    private final MultiInstantiableAdaptableSelectionProvider selectionProvider;

    private final IProgressService progressService;

    @Inject
    public IterationPropertySection(final MultiInstantiableAdaptableSelectionProvider selectionProvider,
            final IProgressService progressService) {
        this.selectionProvider = selectionProvider;
        this.progressService = progressService;
    }

    @Override
    public String getSectionDescription() {
        return Messages.loopSectionDescription;
    }

    @Override
    protected void createContent(final Composite parent) {
        context = new EMFDataBindingContext();
        final TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();

        final Composite composite = widgetFactory.createPlainComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 5).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final SelectObservableValue recurrenceTypeObservable = createRecurrenceTypeRadioGroup(composite, widgetFactory);

        final Composite stackedComposite = widgetFactory.createPlainComposite(composite, SWT.NONE);
        stackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final StackLayout stackLayout = new StackLayout();
        stackedComposite.setLayout(stackLayout);

        final Composite noneComposite = widgetFactory.createComposite(stackedComposite, SWT.NONE);
        final Composite standardLoopContent = createStandardLoopContent(stackedComposite, widgetFactory);
        final Composite multiInstanceContent = createMultiInstanceContent(stackedComposite, widgetFactory);
        recurrenceTypeObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final MultiInstanceType type = (MultiInstanceType) event.diff.getNewValue();
                switch (type) {
                    case NONE:
                        stackLayout.topControl = noneComposite;
                        break;
                    case STANDARD:
                        stackLayout.topControl = standardLoopContent;
                        break;
                    default:
                        stackLayout.topControl = multiInstanceContent;
                        break;
                }
                stackedComposite.layout();
            }
        });

    }

    private Composite createStandardLoopContent(final Composite stackedComposite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite standardLoopComposite = widgetFactory.createPlainComposite(stackedComposite, SWT.NONE);
        standardLoopComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(0, 0, 10, 0).create());

        widgetFactory.createLabel(standardLoopComposite, "", SWT.NONE);// FILLER

        final Well well = new Well(standardLoopComposite, Messages.processScopeVariableWarning, widgetFactory,
                IStatus.WARNING);
        well.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).span(2, 1).create());

        widgetFactory.createLabel(standardLoopComposite, "", SWT.NONE);// FILLER

        final Button testAfterButton = widgetFactory.createButton(standardLoopComposite, Messages.testAfterLabel,
                SWT.RADIO);
        testAfterButton.setLayoutData(GridDataFactory.swtDefaults().create());

        final Button testBefore = widgetFactory.createButton(standardLoopComposite, Messages.testBeforeLabel,
                SWT.RADIO);
        testBefore.setLayoutData(GridDataFactory.swtDefaults().create());

        final SelectObservableValue testEventObservable = new SelectObservableValue(Boolean.class);
        testEventObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(testAfterButton));
        testEventObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(testBefore));

        final IObservableValue selectionObservable = ViewersObservables.observeSingleSelection(selectionProvider);
        context.bindValue(testEventObservable, CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                selectionObservable, ProcessPackage.Literals.MULTI_INSTANTIABLE__TEST_BEFORE));

        widgetFactory.createLabel(standardLoopComposite, Messages.loopConditionLabel)
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final ExpressionViewer loopConditionExpressionViewer = new ExpressionViewer(standardLoopComposite, SWT.BORDER,
                widgetFactory);

        loopConditionExpressionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        loopConditionExpressionViewer.addFilter(new AvailableExpressionTypeFilter(
                new String[] { ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
                        ExpressionConstants.PARAMETER_TYPE, ExpressionConstants.SCRIPT_TYPE }));
        loopConditionExpressionViewer.addFilter(new DisplayEngineExpressionWithName(new String[] {
                org.bonitasoft.engine.expression.ExpressionConstants.LOOP_COUNTER.getEngineConstantName() }));
        loopConditionExpressionViewer.addFilter(new OnlyProcessDataViewerFilter());
        loopConditionExpressionViewer.setExpressionNameResolver(new DefaultExpressionNameResolver("loopWhile"));
        context.bindValue(ViewersObservables.observeInput(loopConditionExpressionViewer), selectionObservable);
        context.bindValue(ViewersObservables.observeSingleSelection(loopConditionExpressionViewer),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), selectionObservable,
                        ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_CONDITION));

        widgetFactory.createLabel(standardLoopComposite, Messages.maximumLoopLabel)
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final ExpressionViewer maximumLoopExpressionViewer = new ExpressionViewer(standardLoopComposite, SWT.BORDER,
                widgetFactory);
        maximumLoopExpressionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        maximumLoopExpressionViewer.addFilter(new AvailableExpressionTypeFilter(
                new String[] { ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
                        ExpressionConstants.PARAMETER_TYPE, ExpressionConstants.SCRIPT_TYPE }));
        maximumLoopExpressionViewer.setMessage(Messages.optionalLabel);
        maximumLoopExpressionViewer.addFilter(new OnlyProcessDataViewerFilter());
        maximumLoopExpressionViewer.setExpressionNameResolver(new DefaultExpressionNameResolver("maximumLoop"));
        context.bindValue(ViewersObservables.observeInput(maximumLoopExpressionViewer), selectionObservable);
        context.bindValue(ViewersObservables.observeSingleSelection(maximumLoopExpressionViewer),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), selectionObservable,
                        ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_MAXIMUM));

        return standardLoopComposite;
    }

    private Composite createMultiInstanceContent(final Composite stackedComposite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite multiInstanceComposite = widgetFactory.createPlainComposite(stackedComposite, SWT.NONE);
        multiInstanceComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        multiInstanceComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(30, 0, 15, 0).create());

        final Composite cardinalityRadioGroup = widgetFactory.createPlainComposite(multiInstanceComposite, SWT.NONE);
        cardinalityRadioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        cardinalityRadioGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Button dataBasedInstanceButton = widgetFactory.createButton(cardinalityRadioGroup,
                Messages.dataBasedInstanceLabel, SWT.RADIO);
        dataBasedInstanceButton.setLayoutData(GridDataFactory.swtDefaults().create());

        final Button definedNumberOfInstancesButton = widgetFactory.createButton(cardinalityRadioGroup,
                Messages.definedNumberOfInstanceLabel, SWT.RADIO);
        definedNumberOfInstancesButton.setLayoutData(GridDataFactory.swtDefaults().create());

        final SelectObservableValue cardinalityObservable = new SelectObservableValue(Boolean.class);
        cardinalityObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(dataBasedInstanceButton));
        cardinalityObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(definedNumberOfInstancesButton));

        context.bindValue(cardinalityObservable,
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        ViewersObservables.observeSingleSelection(selectionProvider),
                        ProcessPackage.Literals.MULTI_INSTANTIABLE__USE_CARDINALITY));

        final Composite dataContainerComposite = widgetFactory.createPlainComposite(multiInstanceComposite, SWT.NONE);
        dataContainerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        final StackLayout dataStackLayout = new StackLayout();
        dataContainerComposite.setLayout(dataStackLayout);

        final Composite cardinalityContent = createCardinalityContent(dataContainerComposite, widgetFactory);
        final Composite dataContent = createDataContent(dataContainerComposite, widgetFactory);

        cardinalityObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final Boolean useCardinality = (Boolean) event.diff.getNewValue();
                if (useCardinality) {
                    dataStackLayout.topControl = cardinalityContent;

                } else {
                    dataStackLayout.topControl = dataContent;
                }
                dataContainerComposite.layout();

            }
        });

        return multiInstanceComposite;

    }

    private void createCompletionConditionViewer(final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite multiInstanceComposite) {
        final Composite completionComposite = widgetFactory.createPlainComposite(multiInstanceComposite, SWT.NONE);
        completionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        completionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        final Label label = widgetFactory.createLabel(completionComposite,
                Messages.multiInstance_completionConditionLabel);
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ExpressionViewer completionConditionViewer = new ExpressionViewer(completionComposite, SWT.BORDER,
                widgetFactory);
        completionConditionViewer
                .setExpressionNameResolver(new DefaultExpressionNameResolver("earlyCompletionCondition"));
        completionConditionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        completionConditionViewer.addFilter(new CompletionConditionFilter(selectionProvider));
        completionConditionViewer.addFilter(new DisplayEngineExpressionWithName(new String[] {
                org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_ACTIVE_INSTANCES.getEngineConstantName(),
                org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_COMPLETED_INSTANCES
                        .getEngineConstantName(),
                org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_INSTANCES.getEngineConstantName(),
                org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_TERMINATED_INSTANCES
                        .getEngineConstantName() }));
        completionConditionViewer.setMessage(Messages.multiInstance_completionConditionDescription);

        final IObservableValue selectionObservable = ViewersObservables.observeSingleSelection(selectionProvider);
        context.bindValue(ViewersObservables.observeInput(completionConditionViewer), selectionObservable);
        context.bindValue(ViewersObservables.observeSingleSelection(completionConditionViewer),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), selectionObservable,
                        ProcessPackage.Literals.MULTI_INSTANTIABLE__COMPLETION_CONDITION));
    }

    private Composite createDataContent(final Composite dataContainerComposite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite dataContent = widgetFactory.createPlainComposite(dataContainerComposite, SWT.NONE);
        dataContent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        dataContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());

        createInputForDataGroup(widgetFactory, dataContent);

        final Composite imageComposite = widgetFactory.createPlainComposite(dataContent, SWT.NONE);
        imageComposite.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, true).indent(0, 20).create());
        imageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).spacing(0, 0).create());
        widgetFactory.createLabel(imageComposite, "").setImage(Pics.getImage("icon-arrow-right.png"));
        widgetFactory.createLabel(imageComposite, "").setImage(Pics.getImage("task_group.png"));
        widgetFactory.createLabel(imageComposite, "").setImage(Pics.getImage("icon-arrow-right.png"));

        createOutputGroup(widgetFactory, dataContent);

        createCompletionConditionViewer(widgetFactory, dataContent);

        return dataContent;
    }

    private void createOutputGroup(final TabbedPropertySheetWidgetFactory widgetFactory, final Composite dataContent) {
        final Composite outputComposite = widgetFactory.createPlainComposite(dataContent, SWT.NONE);
        outputComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).indent(0, 20).create());
        outputComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Button storeOutputButton = widgetFactory.createButton(outputComposite, Messages.storeOutputResult,
                SWT.CHECK);
        storeOutputButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final ISWTObservableValue observeStoreOutputSelection = SWTObservables.observeSelection(storeOutputButton);
        final IObservableValue selectionObservable = ViewersObservables.observeSingleSelection(selectionProvider);
        context.bindValue(observeStoreOutputSelection, CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                selectionObservable, ProcessPackage.Literals.MULTI_INSTANTIABLE__STORE_OUTPUT));

        final Group outputGroup = widgetFactory.createGroup(outputComposite, Messages.output);
        outputGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        outputGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).spacing(5, 3).create());

        final Label outputDatalabel = widgetFactory.createLabel(outputGroup, Messages.outputData);
        outputDatalabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ComboViewer outputDataComboViewer = createComboViewer(widgetFactory, outputGroup,
                new ObservableListContentProviderWithProposalListenersForActivity());
        outputDataComboViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).indent(5, 0).create());
        outputDataComboViewer.setSorter(new DataViewerSorter());

        final IViewerObservableValue observeSingleSelection = ViewersObservables
                .observeSingleSelection(outputDataComboViewer);

        final IObservableValue observeDataValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                selectionObservable, ProcessPackage.Literals.MULTI_INSTANTIABLE__OUTPUT_DATA);
        selectionObservable.addValueChangeListener(
                createInputValueChanged(outputDataComboViewer, observeSingleSelection, observeDataValue, true));
        context.bindValue(observeSingleSelection, observeDataValue);

        outputDataComboViewer.addSelectionChangedListener(createComboSelectionListener(outputDataComboViewer, true));

        final Label label = widgetFactory.createLabel(outputGroup, "");
        label.setImage(Pics.getImage("icon-arrow-down.png"));
        label.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).align(SWT.CENTER, SWT.CENTER).create());

        final Label outputListlabel = widgetFactory.createLabel(outputGroup, Messages.outputList);
        outputListlabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ComboViewer outputListComboViewer = createComboViewer(widgetFactory, outputGroup,
                new ObservableListContentProviderWithProposalListenersForPool());
        outputListComboViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).indent(5, 0).create());
        outputListComboViewer.addFilter(new ListDataFilter());
        outputListComboViewer.setSorter(new DataViewerSorter());

        final IObservableValue observeDetailValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                selectionObservable, ProcessPackage.Literals.MULTI_INSTANTIABLE__LIST_DATA_CONTAINING_OUTPUT_RESULTS);
        final IViewerObservableValue observeSingleSelection2 = ViewersObservables
                .observeSingleSelection(outputListComboViewer);

        selectionObservable.addValueChangeListener(
                createInputValueChanged(outputListComboViewer, observeSingleSelection2, observeDetailValue, false));
        context.bindValue(observeSingleSelection2, observeDetailValue);

        context.bindValue(PojoObservables.observeValue(new RecursiveControlEnablement(outputGroup), "enabled"),
                observeStoreOutputSelection);

        outputListComboViewer.addSelectionChangedListener(createComboSelectionListener(outputListComboViewer, false));
    }

    private Object getDataFromName(final String newVariableName, final IObservableList input) {
        if (input instanceof IObservableList) {
            final Iterator<?> iterator = input.iterator();
            while (iterator.hasNext()) {
                final Object object = iterator.next();
                if (object instanceof Data) {
                    if (((Data) object).getName().equals(newVariableName)) {
                        return object;
                    }
                }

            }
        }
        return null;
    }

    private void createInputForDataGroup(final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite dataContent) {
        final Group inputGroup = widgetFactory.createGroup(dataContent, Messages.input);
        inputGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        inputGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).spacing(5, 3).create());

        final Label inputListlabel = widgetFactory.createLabel(inputGroup, Messages.inputList + " *");
        inputListlabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        final ControlDecoration inputListlabelDecoration = new ControlDecoration(inputListlabel, SWT.RIGHT);
        inputListlabelDecoration.setMarginWidth(-3);
        inputListlabelDecoration.setDescriptionText(Messages.inputListHint);
        inputListlabelDecoration.setImage(Pics.getImage(PicsConstants.hint));

        final ComboViewer inputListComboViewer = createComboViewer(widgetFactory, inputGroup,
                new ObservableListContentProviderWithProposalListenersForPool());
        inputListComboViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).indent(5, 0).create());
        inputListComboViewer.addFilter(new ListDataFilter());
        inputListComboViewer.setSorter(new DataViewerSorter());
        final IViewerObservableValue observeSingleSelection = ViewersObservables
                .observeSingleSelection(inputListComboViewer);
        final IObservableValue selectionObservable = ViewersObservables.observeSingleSelection(selectionProvider);
        final IObservableValue observeInputCollectionValue = CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), selectionObservable,
                ProcessPackage.Literals.MULTI_INSTANTIABLE__COLLECTION_DATA_TO_MULTI_INSTANTIATE);

        selectionObservable.addValueChangeListener(createInputValueChanged(inputListComboViewer, observeSingleSelection,
                observeInputCollectionValue, false));

        inputListComboViewer.addSelectionChangedListener(createComboSelectionListener(inputListComboViewer, false));
        inputListComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                updateReturnTypeFromSelectedInputCollection(inputListComboViewer);
            }

        });
        context.bindValue(observeSingleSelection, observeInputCollectionValue);

        final Label label = widgetFactory.createLabel(inputGroup, "");
        label.setImage(Pics.getImage("icon-arrow-down.png"));
        label.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).align(SWT.CENTER, SWT.CENTER).create());

        createIteratorControl(widgetFactory, inputGroup);
    }

    protected void updateReturnTypeFromSelectedInputCollection(final ComboViewer inputListComboViewer) {
        final Object data = ((IStructuredSelection) inputListComboViewer.getSelection()).getFirstElement();
        if (data instanceof Data) {
            if (((Data) data).isMultiple()) {
                final String technicalTypeFor = getQualifiedNameFromMultipleData((Data) data);
                if (!returnTypeComboTextObservable.isDisposed() && !expressionReturnTypeDetailValue.isDisposed()) {
                    if (isCurrentIteratorExpressiontObserved((DetailObservableValue) expressionReturnTypeDetailValue)) {
                        final String currentReturnType = (String) expressionReturnTypeDetailValue.getValue();
                        if (!technicalTypeFor.equals(currentReturnType)) {
                            returnTypeComboTextObservable.setValue(technicalTypeFor);
                        }
                    }
                }
            }
        }
    }

    protected boolean isCurrentIteratorExpressiontObserved(final DetailObservableValue detailObservableValue) {
        final Object observed = ((DetailObservableValue) expressionReturnTypeDetailValue).getObserved();
        final MultiInstantiable multiInstantiable = (MultiInstantiable) ViewersObservables
                .observeSingleSelection(selectionProvider).getValue();
        return observed != null && observed.equals(multiInstantiable.getIteratorExpression());
    }

    private ISelectionChangedListener createComboSelectionListener(final ComboViewer inputListComboViewer,
            final boolean stepData) {
        return new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (selection instanceof IProposalListener) {
                    EObject value = (EObject) ViewersObservables.observeSingleSelection(selectionProvider).getValue();
                    if (!stepData) {
                        value = ModelHelper.getParentProcess(value);
                    }
                    if (value != null) {
                        final String newVariableName = ((IProposalListener) selection).handleEvent(value, null);
                        if (newVariableName != null) {
                            final IObservableList observeList = (IObservableList) inputListComboViewer.getInput();
                            inputListComboViewer.setSelection(
                                    new StructuredSelection(getDataFromName(newVariableName, observeList)));
                        }
                    } else {
                        inputListComboViewer.setSelection(new StructuredSelection());
                    }

                }

            }
        };
    }

    private IValueChangeListener createInputValueChanged(final ComboViewer comboViewer,
            final IViewerObservableValue observeSingleSelection, final IObservableValue dataObservableValue,
            final boolean stepData) {
        return new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final EObject parentProcess = getTargetEObject(event, stepData);
                if (parentProcess != null) {
                    final IObservableList newInput = EMFObservables.observeList(parentProcess,
                            ProcessPackage.Literals.DATA_AWARE__DATA);
                    comboViewer.setInput(newInput);
                    newInput.addListChangeListener(new IListChangeListener() {

                        @Override
                        public void handleListChange(final ListChangeEvent event) {
                            if (comboViewer != null && !comboViewer.getCombo().isDisposed()) {
                                comboViewer.refresh();
                            }
                        }
                    });
                    final Object dataValue = dataObservableValue.getValue();
                    if (dataValue instanceof Data && newInput != null) {
                        final Iterator<?> iterator = newInput.iterator();
                        while (iterator.hasNext()) {
                            final Object object = iterator.next();
                            if (object instanceof Data) {
                                if (((Data) object).getName().equals(((Data) dataValue).getName())) {
                                    observeSingleSelection.setValue(object);
                                }
                            }

                        }
                    }
                }
            }

            private EObject getTargetEObject(final ValueChangeEvent event, final boolean stepData) {
                if (stepData) {
                    return (MultiInstantiable) event.diff.getNewValue();
                } else {
                    return ModelHelper.getParentProcess((MultiInstantiable) event.diff.getNewValue());
                }
            }
        };
    }

    private void createInputForCardinalityGroup(final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite dataContent) {
        final Group inputGroup = widgetFactory.createGroup(dataContent, Messages.input);
        inputGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        inputGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).spacing(5, 3).create());

        final Label label = widgetFactory.createLabel(inputGroup, Messages.numberOfInstancesToCreate, SWT.NONE);
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ExpressionViewer cardinalityExpression = new ExpressionViewer(inputGroup, SWT.BORDER, widgetFactory);
        cardinalityExpression.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        cardinalityExpression.addFilter(new AvailableExpressionTypeFilter(
                new String[] { ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
                        ExpressionConstants.PARAMETER_TYPE, ExpressionConstants.SCRIPT_TYPE }));
        cardinalityExpression.setExpressionNameResolver(new DefaultExpressionNameResolver("numberOfInstancesToCreate"));
        final IObservableValue selectionObservable = ViewersObservables.observeSingleSelection(selectionProvider);
        context.bindValue(ViewersObservables.observeInput(cardinalityExpression), selectionObservable);
        context.bindValue(ViewersObservables.observeSingleSelection(cardinalityExpression),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), selectionObservable,
                        ProcessPackage.Literals.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION));
    }

    protected void createIteratorControl(final TabbedPropertySheetWidgetFactory widgetFactory, final Composite parent) {
        final Composite iteratorComposite = widgetFactory.createComposite(parent);
        iteratorComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).indent(6, 0).create());
        iteratorComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(0, 10, 0, 0).create());

        final IObservableValue<Expression> iteratorObservable = CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(),
                ViewerProperties.singleSelection().observe(selectionProvider),
                ProcessPackage.Literals.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION);

        final Label iteratorLabel = widgetFactory.createLabel(iteratorComposite, Messages.iterator + " *");
        iteratorLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        final ControlDecoration ieratorDecoration = new ControlDecoration(iteratorLabel, SWT.RIGHT);
        ieratorDecoration.setDescriptionText(Messages.iteratorHint);
        ieratorDecoration.setImage(Pics.getImage(PicsConstants.hint));
        ieratorDecoration.setMarginWidth(-3);

        IteratorRefactorOperationFactory refactorOperationFactory = new IteratorRefactorOperationFactory();
        IObservableValue<String> expressionNameObservale = CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(),
                iteratorObservable, ExpressionPackage.Literals.EXPRESSION__NAME);
        IObservableValue<String> contentObserveValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                iteratorObservable, ExpressionPackage.Literals.EXPRESSION__CONTENT);
        new TextWidget.Builder()
                .fill()
                .withId(SWTBotConstants.SWTBOT_ID_ITERATOR_NAME_TEXTEDITOR)
                .horizontalSpan(2)
                .minimumWidth(250)
                .horizontalIndent(5)
                .grabHorizontalSpace()
                .transactionalEdit((oldValue, newValue) -> {
                    contentObserveValue.setValue(newValue);
                    RefactorDataOperation refactorOperation = refactorOperationFactory
                            .createRefactorOperation(getEditingDomain(), iteratorObservable.getValue(), oldValue, newValue);
                    try {
                        progressService.run(true, false, refactorOperation);
                    } catch (InvocationTargetException | InterruptedException e) {
                        BonitaStudioLog.error(e);
                    }
                })
                .withTargetToModelStrategy(UpdateStrategyFactory.convertUpdateValueStrategy()
                        .withValidator(multiValidator()
                                .addValidator(mandatoryValidator(Messages.name))
                                .addValidator(maxLengthValidator(Messages.iterator, NAME_MAX_LENGTH))
                                .addValidator(groovyReferenceValidator(Messages.iterator).startsWithLowerCase())
                                .addValidator(name -> {
                                    if (ModelHelper.getAccessibleData(ModelHelper.getParentPool(getEObject())).stream()
                                            .map(Data::getName)
                                            .anyMatch(name::equals)) {
                                        return ValidationStatus.error(NLS
                                                .bind(org.bonitasoft.studio.common.Messages.unicityErrorMessage, name));
                                    }
                                    return ValidationStatus.ok();
                                })
                                .create()))
                .bindTo(expressionNameObservale)
                .inContext(context)
                .adapt(widgetFactory)
                .useNativeRender()
                .createIn(iteratorComposite);

        expressionReturnTypeDetailValue = EMFEditWithRefactorObservables.observeDetailValueWithRefactor(
                Realm.getDefault(), iteratorObservable, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);

        final Label iteratorTypeLabel = widgetFactory.createLabel(iteratorComposite, Messages.type + " *");
        iteratorTypeLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        final ControlDecoration ieratorTypeDecoration = new ControlDecoration(iteratorTypeLabel, SWT.RIGHT);
        ieratorTypeDecoration.setDescriptionText(Messages.typeHint);
        ieratorTypeDecoration.setImage(Pics.getImage(PicsConstants.hint));
        ieratorTypeDecoration.setMarginWidth(-3);

        returnTypeCombo = new ComboViewer(new Combo(iteratorComposite, SWT.BORDER));
        returnTypeCombo.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).indent(5, 0).create());
        returnTypeCombo.setContentProvider(new ExpressionReturnTypeContentProvider());
        returnTypeCombo.setLabelProvider(new LabelProvider());
        returnTypeCombo.setSorter(new ViewerSorter() {

            @Override
            public int compare(final Viewer viewer, final Object e1, final Object e2) {
                final String t1 = (String) e1;
                final String t2 = (String) e2;
                return t1.compareTo(t2);
            }
        });
        returnTypeCombo.setInput(new Object());

        final Button browseClassesButton = widgetFactory.createButton(iteratorComposite, Messages.Browse, SWT.PUSH);
        browseClassesButton.setLayoutData(GridDataFactory.fillDefaults().create());

        returnTypeComboTextObservable = SWTObservables.observeText(returnTypeCombo.getCombo());
        context.bindValue(SWTObservables.observeDelayedValue(200, returnTypeComboTextObservable),
                expressionReturnTypeDetailValue,
                refactorReturnTypeStrategy(expressionReturnTypeDetailValue, iteratorObservable), null);

        browseClassesButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final String typeName = openClassSelectionDialog();
                if (typeName != null && !typeName.isEmpty()) {
                    returnTypeComboTextObservable.setValue(typeName);
                }
            }
        });

    }

    private UpdateValueStrategy refactorReturnTypeStrategy(final IObservableValue expressionReturnTypeDetailValue,
            final IObservableValue iteratorObservable) {
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setConverter(new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object value) {
                final String returnType = (String) value;
                final Expression expression = (Expression) iteratorObservable.getValue();
                final EditingDomainEObjectObservableValueWithRefactoring innerObservableValue = (EditingDomainEObjectObservableValueWithRefactoring) ((DetailObservableValueWithRefactor) expressionReturnTypeDetailValue)
                        .getInnerObservableValue();
                if (expression != null && returnType != null && innerObservableValue != null) {
                    final MultiInstantiable parentFlowElement = (MultiInstantiable) ModelHelper
                            .getParentFlowElement(expression);
                    final Data oldItem = ExpressionHelper.dataFromIteratorExpression(parentFlowElement, expression,
                            mainProcess(parentFlowElement));
                    final Expression expressionCopy = EcoreUtil.copy(expression);
                    expressionCopy.setReturnType(returnType);
                    final Data newItem = ExpressionHelper.dataFromIteratorExpression(parentFlowElement, expressionCopy,
                            mainProcess(parentFlowElement));
                    innerObservableValue.setRefactoringCommand(getRefactorCommand(oldItem, newItem, parentFlowElement));
                } else if (innerObservableValue != null) {
                    innerObservableValue.setRefactoringCommand(null);
                }
                return value;
            }

        });
        return strategy;
    }

    private MainProcess mainProcess(final EObject element) {
        return ModelHelper.getMainProcess(element);
    }

    protected CompoundCommand getRefactorCommand(final Data oldItem, final Data newItem,
            final MultiInstantiable container) {
        final RefactorDataOperation op = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        op.setDataContainer((DataAware) container);
        op.addItemToRefactor(newItem, oldItem);
        op.setEditingDomain(getEditingDomain());
        try {
            ProgressManager.getInstance().busyCursorWhile(op.createRunnableWithProgress());
            return op.getCompoundCommand();
        } catch (final InterruptedException e) {
            return null;
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private String openClassSelectionDialog() {
        final JavaSearchScope scope = new JavaSearchScope(true);
        try {
            scope.add(RepositoryManager.getInstance().getCurrentRepository().getJavaProject());
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
        final FilteredTypesSelectionDialog searchDialog = new FilteredTypesSelectionDialog(
                Display.getDefault().getActiveShell(), false, null, scope, IJavaSearchConstants.TYPE);
        if (searchDialog.open() == Dialog.OK) {
            return ((IType) searchDialog.getFirstResult()).getFullyQualifiedName();
        }
        return null;
    }

    protected ComboViewer createComboViewer(final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite composite, final ObservableListContentProviderWithProposalListeners contentProvider) {
        final ComboViewer comboViewer = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);

        comboViewer.setContentProvider(contentProvider);
        final IObservableSet knownElements = contentProvider.getKnownElements();
        final IObservableMap[] labelMaps = EMFObservables.observeMaps(knownElements,
                new EStructuralFeature[] { ProcessPackage.Literals.ELEMENT__NAME,
                        ProcessPackage.Literals.DATA__DATA_TYPE, ProcessPackage.Literals.DATA__MULTIPLE });
        comboViewer.setLabelProvider(new DataLabelProvider(labelMaps));

        final ToolBar toolBar = new ToolBar(composite, SWT.FLAT);
        widgetFactory.adapt(toolBar);
        final ToolItem toolItem = new ToolItem(toolBar, SWT.FLAT);
        toolItem.setImage(Pics.getImage(PicsConstants.clear));
        toolItem.setToolTipText(Messages.clearSelection);
        toolItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                comboViewer.setSelection(new StructuredSelection());
            }
        });

        return comboViewer;
    }

    private Composite createCardinalityContent(final Composite dataContainerComposite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite cardinalityContent = widgetFactory.createPlainComposite(dataContainerComposite, SWT.NONE);
        cardinalityContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        createInputForCardinalityGroup(widgetFactory, cardinalityContent);

        final Composite imageComposite = widgetFactory.createPlainComposite(cardinalityContent, SWT.NONE);
        imageComposite.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, true).indent(0, 20).create());
        imageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).spacing(0, 0).create());
        widgetFactory.createLabel(imageComposite, "").setImage(Pics.getImage("icon-arrow-right.png"));
        widgetFactory.createLabel(imageComposite, "").setImage(Pics.getImage("task_group.png"));
        widgetFactory.createLabel(imageComposite, "").setImage(Pics.getImage("icon-arrow-right.png"));

        createOutputGroup(widgetFactory, cardinalityContent);
        createCompletionConditionViewer(widgetFactory, cardinalityContent);

        return cardinalityContent;
    }

    private SelectObservableValue createRecurrenceTypeRadioGroup(final Composite composite,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite recurrenceTypeComposite = widgetFactory.createPlainComposite(composite, SWT.NONE);
        recurrenceTypeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).create());
        recurrenceTypeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Button noneRadio = widgetFactory.createButton(recurrenceTypeComposite, Messages.noneLabel, SWT.RADIO);
        noneRadio.setLayoutData(GridDataFactory.fillDefaults().create());

        final Button standardRadio = widgetFactory.createButton(recurrenceTypeComposite, Messages.standardLoop,
                SWT.RADIO);
        standardRadio.setLayoutData(GridDataFactory.fillDefaults().create());
        standardRadio.setImage(Pics.getImage("decoration/loop.png"));

        final Button parallelMultiRadio = widgetFactory.createButton(recurrenceTypeComposite,
                Messages.parallelMultinstantition, SWT.RADIO);
        parallelMultiRadio.setLayoutData(GridDataFactory.fillDefaults().create());
        parallelMultiRadio.setImage(Pics.getImage("decoration/parallel_multiInstance.png"));

        final Button sequentialMultiRadio = widgetFactory.createButton(recurrenceTypeComposite,
                Messages.sequentialMultinstantition, SWT.RADIO);
        sequentialMultiRadio.setLayoutData(GridDataFactory.fillDefaults().create());
        sequentialMultiRadio.setImage(Pics.getImage("decoration/sequential_multiInstance.png"));

        final SelectObservableValue recurrenceTypeObservable = new SelectObservableValue(MultiInstanceType.class);
        recurrenceTypeObservable.addOption(MultiInstanceType.NONE, SWTObservables.observeSelection(noneRadio));
        recurrenceTypeObservable.addOption(MultiInstanceType.STANDARD, SWTObservables.observeSelection(standardRadio));
        recurrenceTypeObservable.addOption(MultiInstanceType.PARALLEL,
                SWTObservables.observeSelection(parallelMultiRadio));
        recurrenceTypeObservable.addOption(MultiInstanceType.SEQUENTIAL,
                SWTObservables.observeSelection(sequentialMultiRadio));

        context.bindValue(recurrenceTypeObservable,
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        ViewersObservables.observeSingleSelection(selectionProvider),
                        ProcessPackage.Literals.MULTI_INSTANTIABLE__TYPE));

        return recurrenceTypeObservable;
    }

    @Override
    public void dispose() {
        if (context != null) {
            context.dispose();
        }
        super.dispose();
    }

    private String getQualifiedNameFromMultipleData(final Data data) {
        if (data.isMultiple()) {
            final Data copy = EcoreUtil.copy(data);
            copy.setMultiple(false);
            final String technicalTypeFor = DataUtil.getTechnicalTypeFor(copy);
            return technicalTypeFor;
        }
        return Object.class.getName();
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        selectionProvider.setSelection(selection);
    }

}
