/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.properties.sections.timer.wizard;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

/**
 * @author Romain Bioteau
 */
public class TimerConditionWizardPage extends WizardPage {

    private Expression condition;

    private Spinner hourSpinner;

    private Spinner daySpinner;

    private Spinner minutesSpinner;

    private Spinner secondsSpinner;

    private final AbstractTimerEvent event;

    private final SelectionAdapter updateConditionListener = new SelectionAdapter() {

        @Override
        public void widgetSelected(SelectionEvent e) {
            setCondition(ExpressionHelper.createConstantExpression(DateUtil.getWidgetMillisecond(null, null, daySpinner, hourSpinner, minutesSpinner, secondsSpinner),Long.class.getName()));
            getContainer().updateButtons();
        }

    };

	private ExpressionViewer dataChooser;

    protected TimerConditionWizardPage(AbstractTimerEvent event, Expression condition) {
        super("timerWizard");//$NON-NLS-1$
        setImageDescriptor(Pics.getWizban());
        setTitle(Messages.timerConditionWizardTitle);
        this.condition = condition;
        this.event = event;
    }

    @Override
    public void createControl(Composite parent) {

        parent.setLayout(new GridLayout(2, false));

        Label deadlineTypeLabel = new Label(parent, SWT.NONE);
        deadlineTypeLabel.setText(Messages.deadlineTypeLabel);

        Composite radioDeadlineGroup = new Composite(parent, SWT.NONE);
        radioDeadlineGroup.setLayout(new GridLayout(2, false));
        final Button durationChoice;
        durationChoice = new Button(radioDeadlineGroup, SWT.RADIO);
        durationChoice.setText(Messages.durationLabel);

        final Button varChoice = new Button(radioDeadlineGroup, SWT.RADIO);
        varChoice.setText(Messages.varDataType);

        final Composite deadlineComposite = new Composite(parent, SWT.NONE);
        deadlineComposite.setLayout(new GridLayout(2, false));
        GridData gd4 = new GridData();
        gd4.horizontalSpan = 2;
        deadlineComposite.setLayoutData(gd4);

        final Composite parentDeadlineLayer = new Composite(deadlineComposite, SWT.NONE);
        final StackLayout stack = new StackLayout();
        parentDeadlineLayer.setLayout(stack);
        GridData gd1 = new GridData();
        gd1.horizontalSpan = 2;
        gd1.horizontalIndent = -5;
        parentDeadlineLayer.setLayoutData(gd1);

        final Composite variableComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        variableComposite.setLayout(new GridLayout(2, false));

        Label varLabel = new Label(variableComposite, SWT.NONE);
        varLabel.setText(Messages.deadlineVarNameLabel);
        gd1 = new GridData();
        gd1.horizontalIndent = 10;
        varLabel.setLayoutData(gd1);

        dataChooser = new ExpressionViewer(variableComposite,SWT.BORDER, ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION);
        dataChooser.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(250, SWT.DEFAULT).create());
        dataChooser.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,ExpressionConstants.SCRIPT_TYPE,ExpressionConstants.VARIABLE_TYPE}));
        dataChooser.setInput(event);
        dataChooser.setSelection(new StructuredSelection(condition));

        final Composite durationComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        durationComposite.setLayout(new GridLayout(8, false));

        Label dayLabel = new Label(durationComposite, SWT.NONE);
        GridData gd3 = new GridData();
        gd3.horizontalIndent = 100;
        dayLabel.setLayoutData(gd3);
        dayLabel.setText(org.bonitasoft.studio.common.Messages.daysLabel);

        daySpinner = new Spinner(durationComposite, SWT.BORDER);
        daySpinner.setMaximum(999);

        Label hoursLabel = new Label(durationComposite, SWT.NONE);
        hoursLabel.setText(org.bonitasoft.studio.common.Messages.hoursLabel);

        hourSpinner = new Spinner(durationComposite, SWT.BORDER);
        hourSpinner.setMaximum(999);

        Label minutesLabel = new Label(durationComposite, SWT.NONE);
        minutesLabel.setText(org.bonitasoft.studio.common.Messages.minutesLabel);

        minutesSpinner = new Spinner(durationComposite, SWT.BORDER);
        minutesSpinner.setMaximum(999);

        Label secondsLabel = new Label(durationComposite, SWT.NONE);
        secondsLabel.setText(org.bonitasoft.studio.common.Messages.secondsLabel);

        secondsSpinner = new Spinner(durationComposite, SWT.BORDER);
        secondsSpinner.setMaximum(999);

        daySpinner.addSelectionListener(updateConditionListener);
        hourSpinner.addSelectionListener(updateConditionListener);
        minutesSpinner.addSelectionListener(updateConditionListener);
        secondsSpinner.addSelectionListener(updateConditionListener);

        durationChoice.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (durationChoice.getSelection()) {
                    setCondition(ExpressionHelper.createConstantExpression(DateUtil.getWidgetMillisecond(null, null, daySpinner, hourSpinner, minutesSpinner, secondsSpinner), Long.class.getName()));
                    stack.topControl = durationComposite;
                    parentDeadlineLayer.layout();
                    getContainer().updateButtons();
                }

            }

        });

        varChoice.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (varChoice.getSelection()) {
                    stack.topControl = variableComposite;
                    parentDeadlineLayer.layout();
                    getContainer().updateButtons();
                }

            }

        });

        if (condition == null) {
            hourSpinner.setSelection(0);
            daySpinner.setSelection(0);
            minutesSpinner.setSelection(0);
            secondsSpinner.setSelection(0);
        }

        String conditionContent = null;
        if (getCondition() != null) {
            conditionContent = getCondition().getContent();
        }
        if (conditionContent != null && !conditionContent.isEmpty()) {
            if (DateUtil.isDuration(conditionContent) && !ExpressionConstants.SCRIPT_TYPE.equals(getCondition().getType())) {
                durationChoice.setSelection(true);
                stack.topControl = durationComposite;
                DateUtil.setWidgetDisplayDuration(null, null, daySpinner, hourSpinner, minutesSpinner, secondsSpinner, Long.parseLong(conditionContent));
            } else {
                varChoice.setSelection(true);
                stack.topControl = variableComposite;
            }
        } else {
            stack.topControl = durationComposite;
            durationChoice.setSelection(true);
            setCondition(ExpressionHelper.createConstantExpression("0",Long.class.getName())); //$NON-NLS-1$

        }

        parentDeadlineLayer.layout();

        setControl(parent);

    }

    @Override
    public boolean isPageComplete() {
        return condition != null;
    }

    public void setCondition(Expression expressionCondition) {
        condition = expressionCondition;
        if(dataChooser != null && !dataChooser.getControl().isDisposed()){
        	dataChooser.setSelection(new StructuredSelection(condition));
        }

    }

    public Expression getCondition() {
        return condition;
    }

}
