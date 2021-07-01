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

import java.util.Date;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.widgets.DurationComposite;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.timer.cron.CronEditor;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.quartz.CronExpression;

/**
 * @author Romain Bioteau
 */
public class TimerConditionWizardPage extends WizardPage {

    private final AbstractTimerEvent event;
    private final Expression condition;
    private ExpressionViewer conditionViewer;
    private boolean enableCycles = false;
    private Button generateExpression;

    protected TimerConditionWizardPage(final AbstractTimerEvent event, final Expression condition) {
        super(TimerConditionWizardPage.class.getName());
        setImageDescriptor(Pics.getWizban());
        setTitle(Messages.timerConditionWizardTitle);
        this.event = event;
        this.condition = condition;
        if (event instanceof StartTimerEvent && !ModelHelper.isInEvenementialSubProcessPool(event)) {
            enableCycles = true;
            setDescription(Messages.startTimerConditionDescription);
        } else {
            setDescription(Messages.timerConditionDescription);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Composite radioComposite = new Composite(mainComposite, SWT.NONE);
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        int col = 4;
        if (!enableCycles) {
            col = 3;
        }
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(col).create());

        final Label basedOn = new Label(radioComposite, SWT.NONE);
        basedOn.setText(Messages.basedOn);

        Button cycleButton = null;
        if (enableCycles) {
            cycleButton = new Button(radioComposite, SWT.RADIO);
            cycleButton.setText(Messages.cycle);
        }
        final Button fixedDateButton = new Button(radioComposite, SWT.RADIO);
        fixedDateButton.setText(Messages.fixedDate);

        final Button durationButton = new Button(radioComposite, SWT.RADIO);
        durationButton.setText(Messages.durationLabel);

        final Composite stackedComposite = new Composite(mainComposite, SWT.NONE);
        final StackLayout stackLayout = new StackLayout();
        stackedComposite.setLayout(stackLayout);
        stackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final CronEditor editor = createCronEditor(stackedComposite);
        final Control calendarEditor = createCalendarEditor(stackedComposite);
        final Control durationEditor = createDurationEditor(stackedComposite);

        if (cycleButton != null) {
            cycleButton.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final SelectionEvent e) {
                    if (((Button) e.getSource()).getSelection()) {
                        stackLayout.topControl = editor.getParent();
                        stackedComposite.layout();
                    }
                }
            });
        }

        fixedDateButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (fixedDateButton.getSelection()) {
                    stackLayout.topControl = calendarEditor;
                    stackedComposite.layout();
                }
            }
        });

        durationButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (durationButton.getSelection()) {
                    stackLayout.topControl = durationEditor;
                    stackedComposite.layout();
                }
            }
        });

        final Composite conditionComposite = new Composite(mainComposite, SWT.NONE);
        conditionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        conditionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Label conditionLabel = new Label(conditionComposite, SWT.NONE);
        conditionLabel.setText(Messages.timerCondition);

        conditionViewer = new ExpressionViewer(conditionComposite, SWT.BORDER,
                ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION);
        conditionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        if (enableCycles) {
            conditionViewer.setMessage(Messages.startTimerConditionHint);
            conditionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.SCRIPT_TYPE,
                    ExpressionConstants.PARAMETER_TYPE, ExpressionConstants.CONSTANT_TYPE }));
        } else {
            conditionViewer.setMessage(Messages.timerConditionHint);
            conditionViewer.addFilter(new AvailableExpressionTypeFilter(
                    new String[] { ExpressionConstants.SCRIPT_TYPE, ExpressionConstants.PARAMETER_TYPE,
                            ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE }));
        }
        conditionViewer.addExpressionValidator(new IExpressionValidator() {

            private Expression inputExpression;

            @Override
            public IStatus validate(final Object value) {
                if (inputExpression.getReturnType().equals(String.class.getName())) {
                    final String cron = inputExpression.getContent();
                    if (!cron.isEmpty() && !CronExpression.isValidExpression(cron)) {
                        return ValidationStatus.error(Messages.invalidCronExpression);
                    }
                }
                return ValidationStatus.ok();
            }

            @Override
            public void setInputExpression(final Expression inputExpression) {
                this.inputExpression = inputExpression;
            }

            @Override
            public void setDomain(final EditingDomain domain) {

            }

            @Override
            public void setContext(final EObject context) {
            }

            @Override
            public boolean isRelevantForExpressionType(final String type) {
                return ExpressionConstants.CONSTANT_TYPE.equals(type);
            }
        });
        conditionViewer.setInput(event);
        conditionViewer.setSelection(new StructuredSelection(condition));

        if (condition == null || condition.getContent() == null || condition.getContent().isEmpty()) {
            if (enableCycles) {
                cycleButton.setSelection(true);
                stackLayout.topControl = editor.getParent();
            } else {
                fixedDateButton.setSelection(true);
                stackLayout.topControl = calendarEditor;
            }
        } else if (condition.getReturnType().equals(String.class.getName())) {
            if (enableCycles) {
                cycleButton.setSelection(true);
                stackLayout.topControl = editor.getParent();
            } else {
                fixedDateButton.setSelection(true);
                stackLayout.topControl = calendarEditor;
            }
        } else if (condition.getReturnType().equals(Long.class.getName())) {
            durationButton.setSelection(true);
            stackLayout.topControl = durationEditor;
        } else if (condition.getReturnType().equals(Date.class.getName())) {
            fixedDateButton.setSelection(true);
            stackLayout.topControl = calendarEditor;
        }

        WizardPageSupport.create(this, editor.getContext());
        setControl(mainComposite);
    }

    @Override
    public void setPageComplete(final boolean complete) {
        super.setPageComplete(complete);
        setCronGenerationEnabled();
    }

    private void setCronGenerationEnabled() {
        generateExpression.setEnabled(getErrorMessage() == null);
    }

    protected Control createCalendarEditor(final Composite stackedComposite) {
        final Composite calendarControl = new Composite(stackedComposite, SWT.NONE);
        calendarControl.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(15, 15).create());
        calendarControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Label selectDateLabel = new Label(calendarControl, SWT.NONE);
        selectDateLabel.setText(Messages.selectDateLabel);

        final DateTime dateChooser = new DateTime(calendarControl, SWT.DATE | SWT.DROP_DOWN | SWT.BORDER);

        final Label atLabel = new Label(calendarControl, SWT.NONE);
        atLabel.setText(Messages.at);

        final DateTime timeChooser = new DateTime(calendarControl, SWT.TIME | SWT.BORDER);

        final Button generateFixedDate = new Button(calendarControl, SWT.PUSH);
        generateFixedDate.setLayoutData(GridDataFactory.swtDefaults().span(4, 1).create());
        generateFixedDate.setText(Messages.generateFixedDateLabel);
        generateFixedDate.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final String displayDate = DateUtil.getWidgetDisplayDate(dateChooser, timeChooser);
                condition.setName(displayDate);
                condition.setContent(DateUtil.getDateExpressionContent(dateChooser.getYear(),
                        dateChooser.getMonth(),
                        dateChooser.getDay(),
                        timeChooser.getHours(),
                        timeChooser.getMinutes(),
                        timeChooser.getSeconds()));
                condition.setType(ExpressionConstants.SCRIPT_TYPE);
                condition.setInterpreter(ExpressionConstants.GROOVY);
                condition.setReturnType(Date.class.getName());
                conditionViewer.setSelection(new StructuredSelection(condition));
            }
        });

        return calendarControl;
    }

    protected Control createDurationEditor(final Composite stackedComposite) {
        final Composite durationControl = new Composite(stackedComposite, SWT.NONE);
        durationControl.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());
        durationControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Label selectDurationLabel = new Label(durationControl, SWT.NONE);
        selectDurationLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(4, 1).create());
        selectDurationLabel.setText(Messages.selectDurationLabel);

        final DurationComposite durationWidget = new DurationComposite(durationControl, true, true, true, true, true, true,
                null);
        durationWidget.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, 0).create());

        final Button generateDuration = new Button(durationControl, SWT.PUSH);
        generateDuration.setLayoutData(GridDataFactory.swtDefaults().span(4, 1).create());
        generateDuration.setText(Messages.generateDurationLabel);
        generateDuration.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final long duration = durationWidget.getDuration();
                condition.setName(DateUtil.getDisplayDuration(duration));
                condition.setContent(String.valueOf(duration) + "L");
                condition.setType(ExpressionConstants.SCRIPT_TYPE);
                condition.setInterpreter(ExpressionConstants.GROOVY);
                condition.setReturnType(Long.class.getName());
                conditionViewer.setSelection(new StructuredSelection(condition));
            }
        });

        return durationControl;
    }

    protected CronEditor createCronEditor(final Composite stackedComposite) {
        final Composite cronGroup = new Composite(stackedComposite, SWT.NONE);
        cronGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        cronGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final CronEditor editor = new CronEditor(cronGroup, SWT.NONE);
        editor.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        editor.addTabChangedListener(new Listener() {

            @Override
            public void handleEvent(final Event event) {
                generateExpression.setEnabled(true);
            }
        });

        generateExpression = new Button(cronGroup, SWT.PUSH);
        generateExpression.setText(Messages.generateCronButtonLabel);
        generateExpression.setLayoutData(GridDataFactory.swtDefaults().create());
        generateExpression.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final String cronExpression = editor.getExpression();
                if (cronExpression != null && !cronExpression.isEmpty()) {
                    condition.setName(cronExpression);
                    condition.setContent(cronExpression);
                    condition.setType(ExpressionConstants.CONSTANT_TYPE);
                    condition.setReturnType(String.class.getName());
                    conditionViewer.setSelection(new StructuredSelection(condition));
                }
            }
        });
        return editor;
    }

    @Override
    public boolean isPageComplete() {
        return true;
    }

    public Expression getCondition() {
        return condition;
    }

}
