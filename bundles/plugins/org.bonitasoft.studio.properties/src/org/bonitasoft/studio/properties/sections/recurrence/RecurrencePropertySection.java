/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.recurrence;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.EObjectSelectionProviderSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.ui.property.section.DataLabelProvider;
import org.bonitasoft.studio.expression.editor.constant.ExpressionReturnTypeContentProvider;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.groovy.DisplayEngineExpressionWithName;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.search.JavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;



/**
 * @author Romain Bioteau
 *
 */
public class RecurrencePropertySection extends EObjectSelectionProviderSection implements ISelectionProvider {

    /**
     * @author Aurelien
     *         Display only Process data, it filters out step data. The step requires to be the context of the expressionviewer
     */
    private final class StepDataViewerFilter extends ViewerFilter {

        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
            if (element instanceof Expression) {
                if (ExpressionConstants.VARIABLE_TYPE.equals(((Expression) element).getType())) {
                    if (parentElement instanceof Activity) {
                        final String expressionName = ((Expression) element).getName();
                        for (final Data activityData : ((Activity) parentElement).getData()) {
                            if (expressionName.equals(activityData.getName())) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    private final Converter eObjectToListObservable = new Converter(EObject.class, IObservableList.class) {

        @Override
        public Object convert(final Object from) {
            if (from instanceof EObject) {
                final AbstractProcess parentProcess = ModelHelper.getParentProcess((EObject) from);
                return EMFObservables.observeList(parentProcess, ProcessPackage.Literals.DATA_AWARE__DATA);
            }
            return null;
        }
    };

    private EMFDataBindingContext context;


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getSectionDescription()
     */
    @Override
    public String getSectionDescription() {
        return Messages.loopSectionDescription;
    }

    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);

        context = new EMFDataBindingContext();
        final TabbedPropertySheetWidgetFactory widgetFactory = aTabbedPropertySheetPage.getWidgetFactory();

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
                        stackedComposite.layout();
                        break;
                    case STANDARD:
                        stackLayout.topControl = standardLoopContent;
                        stackedComposite.layout();
                        break;
                    default:
                        stackLayout.topControl = multiInstanceContent;
                        stackedComposite.layout();
                        break;
                }

            }
        });

    }

    private Composite createStandardLoopContent(final Composite stackedComposite, final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite standardLoopComposite = widgetFactory.createPlainComposite(stackedComposite, SWT.NONE);
        standardLoopComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(0, 0, 20, 0).create());

        widgetFactory.createLabel(standardLoopComposite, "", SWT.NONE);//FIILER

        final Button testAfterButton = widgetFactory.createButton(standardLoopComposite, Messages.testAfterLabel, SWT.RADIO);
        testAfterButton.setLayoutData(GridDataFactory.swtDefaults().create());

        final Button testBefore = widgetFactory.createButton(standardLoopComposite, Messages.testBeforeLabel, SWT.RADIO);
        testBefore.setLayoutData(GridDataFactory.swtDefaults().create());

        final SelectObservableValue testEventObservable = new SelectObservableValue(Boolean.class);
        testEventObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(testAfterButton));
        testEventObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(testBefore));

        context.bindValue(testEventObservable, CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectObservable(),
                ProcessPackage.Literals.MULTI_INSTANTIABLE__TEST_BEFORE));

        widgetFactory.createLabel(standardLoopComposite, Messages.loopConditionLabel).setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final ExpressionViewer loopConditionExpressionViewer = new ExpressionViewer(standardLoopComposite, SWT.BORDER, widgetFactory);

        loopConditionExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        loopConditionExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE }));
        loopConditionExpressionViewer.addFilter(new DisplayEngineExpressionWithName(
                new String[] { org.bonitasoft.engine.expression.ExpressionConstants.LOOP_COUNTER
                        .getEngineConstantName() }));
        loopConditionExpressionViewer.addFilter(new StepDataViewerFilter());

        context.bindValue(ViewersObservables.observeInput(loopConditionExpressionViewer), getEObjectObservable());
        context.bindValue(ViewersObservables.observeSingleSelection(loopConditionExpressionViewer), CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), getEObjectObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_CONDITION));

        widgetFactory.createLabel(standardLoopComposite, Messages.maximumLoopLabel).setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final ExpressionViewer maximumLoopExpressionViewer = new ExpressionViewer(standardLoopComposite, SWT.BORDER, widgetFactory);
        maximumLoopExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        maximumLoopExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE }));
        maximumLoopExpressionViewer.setMessage(Messages.optionalLabel, IStatus.INFO);
        maximumLoopExpressionViewer.addFilter(new StepDataViewerFilter());

        context.bindValue(ViewersObservables.observeInput(maximumLoopExpressionViewer), getEObjectObservable());
        context.bindValue(ViewersObservables.observeSingleSelection(maximumLoopExpressionViewer), CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), getEObjectObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_MAXIMUM));

        return standardLoopComposite;
    }

    private Composite createMultiInstanceContent(final Composite stackedComposite, final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite multiInstanceComposite = widgetFactory.createPlainComposite(stackedComposite, SWT.NONE);
        multiInstanceComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        multiInstanceComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(30, 0, 15, 0).create());

        final Composite cardinalityRadioGroup = widgetFactory.createPlainComposite(multiInstanceComposite, SWT.NONE);
        cardinalityRadioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        cardinalityRadioGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Button dataBasedInstanceButton = widgetFactory.createButton(cardinalityRadioGroup, Messages.dataBasedInstanceLabel, SWT.RADIO);
        dataBasedInstanceButton.setLayoutData(GridDataFactory.swtDefaults().create());

        final Button definedNumberOfInstancesButton = widgetFactory.createButton(cardinalityRadioGroup, Messages.definedNumberOfInstanceLabel, SWT.RADIO);
        definedNumberOfInstancesButton.setLayoutData(GridDataFactory.swtDefaults().create());

        final SelectObservableValue cardinalityObservable = new SelectObservableValue(Boolean.class);
        cardinalityObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(dataBasedInstanceButton));
        cardinalityObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(definedNumberOfInstancesButton));

        context.bindValue(cardinalityObservable, CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectObservable(),
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
                if(useCardinality){
                    dataStackLayout.topControl = cardinalityContent;

                }else{
                    dataStackLayout.topControl = dataContent;
                }
                dataContainerComposite.layout();

            }
        });


        return multiInstanceComposite;

    }

    private void createCompletionConditionViewer(final TabbedPropertySheetWidgetFactory widgetFactory, final Composite multiInstanceComposite) {
        final Composite completionComposite = widgetFactory.createPlainComposite(multiInstanceComposite, SWT.NONE);
        completionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        completionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        final Label label = widgetFactory.createLabel(completionComposite, Messages.multiInstance_completionConditionLabel);
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ExpressionViewer completionConditionViewer = new ExpressionViewer(completionComposite, SWT.BORDER, widgetFactory);
        completionConditionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        completionConditionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE }));
        completionConditionViewer.addFilter(new DisplayEngineExpressionWithName(new String[] {
                org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_ACTIVE_INSTANCES.getEngineConstantName(),
                org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_COMPLETED_INSTANCES.getEngineConstantName(),
                org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_INSTANCES.getEngineConstantName(),
                org.bonitasoft.engine.expression.ExpressionConstants.NUMBER_OF_TERMINATED_INSTANCES.getEngineConstantName() }));
        completionConditionViewer.setMessage(Messages.multiInstance_completionConditionDescription, IStatus.INFO);

        context.bindValue(ViewersObservables.observeInput(completionConditionViewer), getEObjectObservable());
        context.bindValue(ViewersObservables.observeSingleSelection(completionConditionViewer), CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), getEObjectObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__COMPLETION_CONDITION));
    }

    private Composite createDataContent(final Composite dataContainerComposite, final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite dataContent = widgetFactory.createPlainComposite(dataContainerComposite, SWT.NONE);
        dataContent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        dataContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());

        createInputGroup(widgetFactory, dataContent);

        final Composite imageComposite = widgetFactory.createPlainComposite(dataContent, SWT.NONE);
        imageComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, true).indent(0, 20).create());
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

        final Button storeOutputButton = widgetFactory.createButton(outputComposite, Messages.storeOutputResult, SWT.CHECK);
        storeOutputButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final ISWTObservableValue observeStoreOutputSelection = SWTObservables.observeSelection(storeOutputButton);
        context.bindValue(observeStoreOutputSelection, CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                getEObjectObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__STORE_OUTPUT));

        final Group outputGroup = widgetFactory.createGroup(outputComposite, Messages.output);
        outputGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        outputGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).spacing(5, 3).create());

        final Label outputDatalabel = widgetFactory.createLabel(outputGroup, Messages.outputData);
        outputDatalabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ComboViewer outputDataComboViewer = createComboViewer(widgetFactory, outputGroup);
        outputDataComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).indent(5, 0).create());
        outputDataComboViewer.setContentProvider(ArrayContentProvider.getInstance());

        context.bindValue(ViewersObservables.observeInput(outputDataComboViewer),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectObservable(), ProcessPackage.Literals.DATA_AWARE__DATA));
        context.bindValue(ViewersObservables.observeSingleSelection(outputDataComboViewer), CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                getEObjectObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__OUTPUT_DATA));

        final Label label = widgetFactory.createLabel(outputGroup, "");
        label.setImage(Pics.getImage("icon-arrow-down.png"));
        label.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).align(SWT.CENTER, SWT.CENTER).create());

        final Label outputListlabel = widgetFactory.createLabel(outputGroup, Messages.outputList);
        outputListlabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ComboViewer outputListComboViewer = createComboViewer(widgetFactory, outputGroup);
        outputListComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).indent(5, 0).create());
        outputListComboViewer.addFilter(new ListDataFilter());

        final UpdateValueStrategy eObjectToDataList = new UpdateValueStrategy();
        eObjectToDataList.setConverter(eObjectToListObservable);

        context.bindValue(ViewersObservables.observeInput(outputListComboViewer), getEObjectObservable(), null, eObjectToDataList);
        context.bindValue(ViewersObservables.observeSingleSelection(outputListComboViewer), CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                getEObjectObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__LIST_DATA_CONTAINING_OUTPUT_RESULTS));

        context.bindValue(PojoObservables.observeValue(new RecursiveControlEnablement(outputGroup), "enabled"),
                observeStoreOutputSelection);
    }

    private void createInputGroup(final TabbedPropertySheetWidgetFactory widgetFactory, final Composite dataContent) {
        final Group inputGroup = widgetFactory.createGroup(dataContent, Messages.input);
        inputGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        inputGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).spacing(5, 3).create());

        final Label inputListlabel = widgetFactory.createLabel(inputGroup, Messages.inputList + " *");
        inputListlabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ComboViewer inputListComboViewer = createComboViewer(widgetFactory, inputGroup);
        inputListComboViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).indent(5, 0).create());
        inputListComboViewer.addFilter(new ListDataFilter());


        final UpdateValueStrategy eObjectToDataList = new UpdateValueStrategy();
        eObjectToDataList.setConverter(eObjectToListObservable);

        context.bindValue(ViewersObservables.observeInput(inputListComboViewer), getEObjectObservable(), null, eObjectToDataList);
        context.bindValue(ViewersObservables.observeSingleSelection(inputListComboViewer), CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                getEObjectObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__COLLECTION_DATA_TO_MULTI_INSTANTIATE));

        final Label label = widgetFactory.createLabel(inputGroup, "");
        label.setImage(Pics.getImage("icon-arrow-down.png"));
        label.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).align(SWT.CENTER, SWT.CENTER).create());

        final Label instanceDatalabel = widgetFactory.createLabel(inputGroup, Messages.instanceData + " *");
        instanceDatalabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        createReturnTypeCombo(widgetFactory, inputGroup);
    }

    protected void createReturnTypeCombo(final TabbedPropertySheetWidgetFactory widgetFactory, final Composite parent) {
        final Composite iteratorComposite = widgetFactory.createPlainComposite(parent, SWT.NONE);
        iteratorComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        iteratorComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).extendedMargins(0, 10, 0, 0).create());

        final Text instanceDataNameText = widgetFactory.createText(iteratorComposite, "", SWT.BORDER);
        instanceDataNameText.setLayoutData(GridDataFactory.swtDefaults().hint(150, SWT.DEFAULT).create());

        final IObservableValue iteratorObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectObservable(),
                ProcessPackage.Literals.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION);

        final ISWTObservableValue observeinstanceDataNameText = SWTObservables.observeText(instanceDataNameText, SWT.Modify);

        final UpdateValueStrategy updateIteratorNameTarget = new UpdateValueStrategy();
        updateIteratorNameTarget.setConverter(new Converter(String.class, Expression.class) {

            @Override
            public Object convert(final Object name) {
                final Expression expression = (Expression) iteratorObservable.getValue();
                if (expression != null && name != null) {
                    final CompoundCommand cc = new CompoundCommand();
                    cc.append(SetCommand.create(getEditingDomain(), expression, ExpressionPackage.Literals.EXPRESSION__NAME, name));
                    cc.append(SetCommand.create(getEditingDomain(), expression, ExpressionPackage.Literals.EXPRESSION__CONTENT, name));
                    getEditingDomain().getCommandStack().execute(cc);
                }
                return expression;
            }
        });

        final UpdateValueStrategy updateIteratorNameModel = new UpdateValueStrategy();
        updateIteratorNameModel.setConverter(new Converter(Expression.class, String.class) {

            @Override
            public Object convert(final Object from) {
                final Expression expression = (Expression) from;
                if (expression != null) {
                    return expression.getName();
                }
                return "";
            }
        });

        context.bindValue(observeinstanceDataNameText, iteratorObservable, updateIteratorNameTarget, updateIteratorNameModel);

        final Label instanceDataTypeLabel = widgetFactory.createLabel(iteratorComposite, Messages.type + " *");
        instanceDataTypeLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());


        final ComboViewer returnTypeCombo = new ComboViewer(new Combo(iteratorComposite, SWT.BORDER));
        returnTypeCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());
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

        final Button browseClassesButton = widgetFactory.createButton(iteratorComposite, Messages.Browse, SWT.PUSH);
        browseClassesButton.setLayoutData(GridDataFactory.fillDefaults().create());

        final UpdateValueStrategy updateInputModel = new UpdateValueStrategy();
        updateInputModel.setConverter(new Converter(Expression.class, Object.class) {

            @Override
            public Object convert(final Object from) {
                final Expression exp = (Expression) from;
                if (exp != null) {
                    return exp.getReturnType();
                }
                return new Object();
            }
        });
        context.bindValue(ViewersObservables.observeInput(returnTypeCombo),iteratorObservable,null,updateInputModel);

        final UpdateValueStrategy updateIteratorReturnTypeTarget = new UpdateValueStrategy();
        updateIteratorReturnTypeTarget.setConverter(new Converter(String.class, Expression.class) {

            @Override
            public Object convert(final Object returnType) {
                final Expression expression = (Expression) iteratorObservable.getValue();
                if (expression != null && returnType != null && !((String) returnType).isEmpty()) {
                    getEditingDomain().getCommandStack().execute(
                            SetCommand.create(getEditingDomain(), expression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, returnType));
                }
                return expression;
            }
        });

        final UpdateValueStrategy updateIteratorReturnTypeModel = new UpdateValueStrategy();
        updateIteratorReturnTypeModel.setConverter(new Converter(Expression.class, String.class) {

            @Override
            public Object convert(final Object from) {
                final Expression expression = (Expression) from;
                if (expression != null) {
                    return expression.getReturnType();
                }
                return Object.class.getName();
            }
        });

        final ISWTObservableValue observeSelection = SWTObservables.observeText(returnTypeCombo.getCombo());
        context.bindValue(observeSelection, iteratorObservable, updateIteratorReturnTypeTarget, updateIteratorReturnTypeModel);

        browseClassesButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final String typeName = openClassSelectionDialog();
                observeSelection.setValue(typeName);
            }
        });
    }

    /**
     * @param classText
     */
    @SuppressWarnings("restriction")
    private String openClassSelectionDialog() {
        final JavaSearchScope scope = new JavaSearchScope();
        try {
            scope.add(RepositoryManager.getInstance().getCurrentRepository().getJavaProject());
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
        final FilteredTypesSelectionDialog searchDialog = new FilteredTypesSelectionDialog(Display.getDefault().getActiveShell(), false, null, scope,
                IJavaSearchConstants.TYPE);
        if (searchDialog.open() == Dialog.OK) {
            return ((IType) searchDialog.getFirstResult()).getFullyQualifiedName();
        }
        return null;
    }

    protected ComboViewer createComboViewer(final TabbedPropertySheetWidgetFactory widgetFactory, final Composite composite) {
        final ComboViewer comboViewer = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
        final ObservableListContentProvider contentProvider = new ObservableListContentProvider();
        comboViewer.setContentProvider(contentProvider);
        final IObservableSet knownElements = contentProvider.getKnownElements();
        final IObservableMap[] labelMaps = EMFObservables.observeMaps(knownElements, new EStructuralFeature[] { ProcessPackage.Literals.ELEMENT__NAME,
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

    private Composite createCardinalityContent(final Composite dataContainerComposite, final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite cardinalityContent = widgetFactory.createPlainComposite(dataContainerComposite, SWT.NONE);
        cardinalityContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        final Label label = widgetFactory.createLabel(cardinalityContent, Messages.numberOfInstancesToCreate, SWT.NONE);
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        final ExpressionViewer cardinalityExpression = new ExpressionViewer(cardinalityContent, SWT.BORDER, widgetFactory);
        cardinalityExpression.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        cardinalityExpression.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE }));
        cardinalityExpression.setMessage(Messages.multiInstance_useCardinalityDescription, IStatus.INFO);

        context.bindValue(ViewersObservables.observeInput(cardinalityExpression), getEObjectObservable());
        context.bindValue(ViewersObservables.observeSingleSelection(cardinalityExpression), CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                getEObjectObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION));

        createCompletionConditionViewer(widgetFactory, cardinalityContent);

        return cardinalityContent;
    }

    private SelectObservableValue createRecurrenceTypeRadioGroup(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite recurrenceTypeComposite = widgetFactory.createPlainComposite(composite, SWT.NONE);
        recurrenceTypeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).create());
        recurrenceTypeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Button noneRadio = widgetFactory.createButton(recurrenceTypeComposite, Messages.noneLabel, SWT.RADIO);
        noneRadio.setLayoutData(GridDataFactory.fillDefaults().create());

        final Button standardRadio = widgetFactory.createButton(recurrenceTypeComposite,Messages.standardLoop, SWT.RADIO);
        standardRadio.setLayoutData(GridDataFactory.fillDefaults().create());
        standardRadio.setImage(Pics.getImage("decoration/loop.png"));

        final Button parallelMultiRadio = widgetFactory.createButton(recurrenceTypeComposite, Messages.parallelMultinstantition, SWT.RADIO);
        parallelMultiRadio.setLayoutData(GridDataFactory.fillDefaults().create());
        parallelMultiRadio.setImage(Pics.getImage("decoration/parallel_multiInstance.png"));

        final Button sequentialMultiRadio = widgetFactory.createButton(recurrenceTypeComposite,Messages.sequentialMultinstantition, SWT.RADIO);
        sequentialMultiRadio.setLayoutData(GridDataFactory.fillDefaults().create());
        sequentialMultiRadio.setImage(Pics.getImage("decoration/sequential_multiInstance.png"));

        final SelectObservableValue recurrenceTypeObservable = new SelectObservableValue(MultiInstanceType.class);
        recurrenceTypeObservable.addOption(MultiInstanceType.NONE, SWTObservables.observeSelection(noneRadio));
        recurrenceTypeObservable.addOption(MultiInstanceType.STANDARD, SWTObservables.observeSelection(standardRadio));
        recurrenceTypeObservable.addOption(MultiInstanceType.PARALLEL, SWTObservables.observeSelection(parallelMultiRadio));
        recurrenceTypeObservable.addOption(MultiInstanceType.SEQUENTIAL, SWTObservables.observeSelection(sequentialMultiRadio));

        context.bindValue(recurrenceTypeObservable,
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectObservable(),
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



}
