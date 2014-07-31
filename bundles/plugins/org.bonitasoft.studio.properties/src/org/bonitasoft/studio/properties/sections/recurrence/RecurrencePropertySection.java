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
import org.bonitasoft.studio.common.properties.EObjectSelectionProviderSection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.groovy.DisplayEngineExpressionWithName;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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

    private EMFDataBindingContext context;
    private StackLayout stackLayout;

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
        stackLayout = new StackLayout();
        stackedComposite.setLayout(stackLayout);

        final Composite noneComposite = widgetFactory.createComposite(stackedComposite, SWT.NONE);
        final Composite standardLoopContent = createStandardLoopContent(stackedComposite, widgetFactory);

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
                        stackLayout.topControl = noneComposite;
                        stackedComposite.layout();
                        break;
                }

            }
        });

    }

    private Composite createStandardLoopContent(final Composite stackedComposite, final TabbedPropertySheetWidgetFactory widgetFactory) {
        final Composite standardLoopComposite = widgetFactory.createPlainComposite(stackedComposite, SWT.NONE);
        standardLoopComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(0, 0, 20, 0).create());

        new Label(standardLoopComposite, SWT.NONE); // FILLER

        final Button testAfterButton = widgetFactory.createButton(standardLoopComposite, Messages.testAfterLabel, SWT.RADIO);
        testAfterButton.setLayoutData(GridDataFactory.swtDefaults().create());

        final Button testBefore = widgetFactory.createButton(standardLoopComposite, Messages.testBeforeLabel, SWT.RADIO);
        testBefore.setLayoutData(GridDataFactory.swtDefaults().create());

        final SelectObservableValue testEventObservable = new SelectObservableValue(Boolean.class);
        testEventObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(testAfterButton));
        testEventObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(testBefore));

        context.bindValue(testEventObservable, CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectMasterObservable(),
                ProcessPackage.Literals.MULTI_INSTANTIABLE__TEST_BEFORE));

        widgetFactory.createLabel(standardLoopComposite, Messages.loopConditionLabel).setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final ExpressionViewer loopConditionExpressionViewer = new ExpressionViewer(standardLoopComposite, SWT.BORDER, widgetFactory,
                ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_CONDITION);

        loopConditionExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        loopConditionExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE }));
        loopConditionExpressionViewer.addFilter(new DisplayEngineExpressionWithName(
                new String[] { org.bonitasoft.engine.expression.ExpressionConstants.LOOP_COUNTER
                        .getEngineConstantName() }));
        loopConditionExpressionViewer.addFilter(new StepDataViewerFilter());

        context.bindValue(ViewersObservables.observeInput(loopConditionExpressionViewer), getEObjectMasterObservable());
        context.bindValue(ViewersObservables.observeSingleSelection(loopConditionExpressionViewer), CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), getEObjectMasterObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_CONDITION));

        widgetFactory.createLabel(standardLoopComposite, Messages.maximumLoopLabel).setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final ExpressionViewer maximumLoopExpressionViewer = new ExpressionViewer(standardLoopComposite, SWT.BORDER, widgetFactory,
                ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_MAXIMUM);
        maximumLoopExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        maximumLoopExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE }));
        maximumLoopExpressionViewer.setMessage(Messages.optionalLabel, IStatus.INFO);
        maximumLoopExpressionViewer.addFilter(new StepDataViewerFilter());

        context.bindValue(ViewersObservables.observeInput(maximumLoopExpressionViewer), getEObjectMasterObservable());
        context.bindValue(ViewersObservables.observeSingleSelection(maximumLoopExpressionViewer), CustomEMFEditObservables.observeDetailValue(
                Realm.getDefault(), getEObjectMasterObservable(), ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_MAXIMUM));

        return standardLoopComposite;
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
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectMasterObservable(),
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
