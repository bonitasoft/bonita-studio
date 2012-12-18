/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
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

package org.bonitasoft.studio.properties.sections.timer.wizard;

import java.util.Calendar;
import java.util.Date;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.TimerUtil;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 * 
 */
public class StartTimerConditionWizardPage extends WizardPage {

    /**
     * @author Baptiste Mesta
     * 
     */
    private final class ScriptTypeLabelProvider extends LabelProvider {
        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
         */
        @Override
        public String getText(Object element) {
            if (element instanceof StartTimerScriptType) {
                switch ((StartTimerScriptType) element) {
                    case YEARLY:
                        return Messages.StartTimerCondition_everyYear;
                    case YEARLY_DAY_OF_MONTH:
                        return Messages.StartTimerCondition_dayOfMonth;

                    case YEARLY_DAY_OF_YEAR:
                        return Messages.StartTimerCondition_dayOfYear;

                    case MONTHLY:
                        return Messages.StartTimerCondition_everyMonth;
                    case MONTHLY_DAY_NUMBER:
                        return Messages.StartTimerCondition_dayOfMonth;
                    case MONTHLY_DAY_OF_WEEK:
                        return Messages.StartTimerCondition_dayOfMonthInWeek;
                    case DAILY:
                        return Messages.StartTimerCondition_everyDay;
                    case WEEKLY:
                        return Messages.StartTimerCondition_everyWeek;
                    case HOURLY:
                        return Messages.StartTimerCondition_everyHour;
                    case MINUTELY:
                        return Messages.StartTimerCondition_everyMinutes;
                    case CONSTANT:
                        return Messages.StartTimerCondition_fixedDate;
                    case CUSTOM:
                        return "custom";
                    case GROOVY:
                        return Messages.StartTimerCondition_script;
                    default:
                        break;
                }
            }
            return super.getText(element);
        }
    }

    private final StartTimerEvent event;
    private ListViewer typeCombo;
    private ComboViewer monthlyTypeChooser;
    private ComboViewer yearlyTypeChooser;
    private Text dayNumberYearlyDayOfYear;
    private Text dayNumberYearlyDayOfMonth;
    private Text dayNumberMonthlyDayNumber;
    private Text dayNumberMonthlyDayOfTheWeek;
    private Combo months;
    private Combo dayMonthlyDayOfWeek;
    private Combo dayWeekly;
    private Text hours;
    private Button useFrom;
    private DateTime fromDate;
    private DateTime fromTime;
    private DateTime dayAtDaily;
    private DateTime yearlyDayAt;
    private ExpressionViewer conditionViewer;
    private Text minutes;
    private Button customizeButton;
    private DateTime monthlyDayAt;
    private DateTime weeklyDayAt;
    private DateTime constantDate;
    private DateTime constantTime;
    private Expression condition;

    protected StartTimerConditionWizardPage(AbstractTimerEvent event, Expression condition) {
        super("timerWizard");//$NON-NLS-1$
        setImageDescriptor(Pics.getWizban());
        setTitle(Messages.timerConditionWizardTitle);
        this.event = (StartTimerEvent) event;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    public void createControl(Composite parent) {


        parent.setLayout(new GridLayout(2, false));

        Composite typeComboComposite = new Composite(parent, SWT.NONE);
        typeComboComposite.setLayout(new GridLayout(2, false));
        typeComboComposite.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.TOP).create());
        //		Label deadlineTypeLabel = new Label(typeComboComposite, SWT.NONE);
        //		deadlineTypeLabel.setText(Messages.deadlineTypeLabel);

        typeCombo = new ListViewer(typeComboComposite, SWT.BORDER);
        typeCombo.setLabelProvider(new ScriptTypeLabelProvider());
        typeCombo.add(StartTimerScriptType.YEARLY);
        typeCombo.add(StartTimerScriptType.MONTHLY);
        typeCombo.add(StartTimerScriptType.WEEKLY);
        typeCombo.add(StartTimerScriptType.DAILY);
        typeCombo.add(StartTimerScriptType.HOURLY);
        typeCombo.add(StartTimerScriptType.MINUTELY);
        typeCombo.add(StartTimerScriptType.CONSTANT);
        // typeCombo.add(ScriptType.CUSTOM);
        typeCombo.add(StartTimerScriptType.GROOVY);
        typeCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(false,true).create());

        Composite conditionComposite = new Composite(parent, SWT.NONE);
        conditionComposite.setLayout(new GridLayout(1, false));

        final Composite fromComposite = new Composite(conditionComposite, SWT.NONE);
        fromComposite.setLayout(new GridLayout(4, false));
        useFrom = new Button(fromComposite, SWT.CHECK);
        Label label = new Label(fromComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_from);
        fromDate = new DateTime(fromComposite, SWT.BORDER | SWT.DATE);
        fromTime = new DateTime(fromComposite, SWT.BORDER | SWT.TIME);
        SelectionAdapter useFromListener = new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                fromDate.setEnabled(useFrom.getSelection());
                fromTime.setEnabled(useFrom.getSelection());
                if(useFrom.getSelection()){
                    fromDate.setBackground(ColorConstants.white);
                    fromDate.setForeground(ColorConstants.menuForeground);
                    fromTime.setBackground(ColorConstants.white);
                    fromTime.setForeground(ColorConstants.menuForeground);
                }else{

                    fromDate.setBackground(ColorConstants.titleInactiveBackground);
                    fromDate.setForeground(ColorConstants.titleInactiveForeground);
                    fromTime.setBackground(ColorConstants.titleInactiveBackground);
                    fromTime.setForeground(ColorConstants.titleInactiveForeground);

                }
            }
        };
        useFrom.addSelectionListener(useFromListener);
        final Composite deadlineComposite = new Composite(conditionComposite, SWT.NONE);
        deadlineComposite.setLayout(new GridLayout(3, false));

        final Composite parentDeadlineLayer = new Composite(deadlineComposite, SWT.NONE);
        final StackLayout stack = new StackLayout();
        parentDeadlineLayer.setLayout(stack);
        GridData gd1 = new GridData();
        gd1.horizontalSpan = 3;
        gd1.horizontalIndent = -5;
        parentDeadlineLayer.setLayoutData(gd1);

        final Composite yearlyComposite = createYearlyComposite(parentDeadlineLayer);

        final Composite monthlyComposite = createMonthlyComposite(parentDeadlineLayer);

        final Composite weeklyComposite = createWeeklyComposite(parentDeadlineLayer);

        final Composite dailyComposite = createDailyComposite(parentDeadlineLayer);

        final Composite hourlyComposite = createHourlyComposite(parentDeadlineLayer);

        final Composite minutelyComposite = createMinutelyComposite(parentDeadlineLayer);

        final Composite customComposite = createCustomComposite(parentDeadlineLayer);

        final Composite constantComposite = createConstantComposite(parentDeadlineLayer);

        final Composite groovyComposite = createGroovyComposite(parentDeadlineLayer);

        customizeButton = new Button(conditionComposite, SWT.PUSH);
        customizeButton.setText(Messages.StartTimerCondition_customize);
        customizeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String script = "";
                switch (getScriptType()) {
                    case YEARLY_DAY_OF_MONTH:
                        script = TimerUtil.generateYearlyDayOfMonth(getDayNumber(), getMonth(), getToDate());
                        break;
                    case YEARLY_DAY_OF_YEAR:
                        script = TimerUtil.generateYearlyDayOfYear(getDayNumber(), getToDate());
                        break;
                    case MONTHLY_DAY_OF_WEEK:
                        script = TimerUtil.generateMonthlyDayOfWeek(getDay(), getDayNumber(), getToDate());
                        break;
                    case MONTHLY_DAY_NUMBER:
                        script = TimerUtil.generateMonthlyDayNumber(getDayNumber(), getToDate());
                        break;
                    case WEEKLY:
                        script = TimerUtil.generateWeekly(getDay(), getToDate());
                        break;
                    case DAILY:
                        script = TimerUtil.generateDaily(getToDate());
                        break;
                    case HOURLY:
                        script = TimerUtil.generateHourly(getHours(), getToDate());
                        break;
                    case MINUTELY:
                        script = TimerUtil.generateMinutely(getMinutes(), getToDate());
                        break;
                    case CONSTANT:
                        script = TimerUtil.generateConstant(getToDate());
                        break;
                    default:
                        break;
                }
                condition.setType(ExpressionConstants.SCRIPT_TYPE);
                condition.setInterpreter(ExpressionConstants.GROOVY);
                condition.setContent(script);
                condition.setReturnType(Date.class.getName());
                typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.GROOVY));
            }
        });

        parentDeadlineLayer.layout();
        typeCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                StartTimerScriptType selection = (StartTimerScriptType) ((IStructuredSelection) event.getSelection()).getFirstElement();
                Composite toShow = null;
                boolean showFrom;
                boolean showCustomize = true;
                switch (selection) {
                    case YEARLY:
                        toShow = yearlyComposite;
                        showFrom = true;
                        break;
                    case MONTHLY:
                        toShow = monthlyComposite;
                        showFrom = true;
                        break;
                    case WEEKLY:
                        toShow = weeklyComposite;
                        showFrom = true;
                        break;
                    case DAILY:
                        toShow = dailyComposite;
                        showFrom = true;
                        break;
                    case HOURLY:
                        toShow = hourlyComposite;
                        showFrom = true;
                        break;
                    case MINUTELY:
                        toShow = minutelyComposite;
                        showFrom = true;
                        break;
                    case GROOVY:
                        toShow = groovyComposite;
                        showFrom = false;
                        showCustomize = false;
                        break;
                    case CUSTOM:
                        toShow = customComposite;
                        showFrom = true;
                        break;
                    case CONSTANT:
                        toShow = constantComposite;
                        showFrom = false;
                        break;
                    default:
                        toShow = yearlyComposite;
                        showFrom = true;
                        break;
                }
                fromComposite.setVisible(showFrom);
                customizeButton.setVisible(showCustomize);
                stack.topControl = toShow;
                parentDeadlineLayer.layout();
            }
        });
        typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.YEARLY));
        setControl(conditionComposite);

        initControls();
        useFromListener.widgetSelected(null);

    }

    private Composite createConstantComposite(Composite parentDeadlineLayer) {
        final Composite ConstantComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        ConstantComposite.setLayout(new GridLayout(3, false));
        Label label = new Label(ConstantComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_fixedDate);
        constantDate = new DateTime(ConstantComposite, SWT.DATE);
        constantTime = new DateTime(ConstantComposite, SWT.TIME);
        return ConstantComposite;
    }

    /**
     * 
     */
    private void initControls() {
        yearlyTypeChooser.setSelection(new StructuredSelection(StartTimerScriptType.YEARLY_DAY_OF_YEAR));
        monthlyTypeChooser.setSelection(new StructuredSelection(StartTimerScriptType.MONTHLY_DAY_NUMBER));
        if(event != null && event.getScriptType() != null) {
            switch (event.getScriptType()) {
                case YEARLY_DAY_OF_MONTH:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.YEARLY));
                    yearlyTypeChooser.setSelection(new StructuredSelection(StartTimerScriptType.YEARLY_DAY_OF_MONTH));
                    dayNumberYearlyDayOfMonth.setText(""+event.getDayNumber());
                    if(months.getItemCount()>event.getMonth() && event.getMonth()>=0) {
                        months.setText(months.getItem(event.getMonth()));
                    }
                    break;
                case YEARLY_DAY_OF_YEAR:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.YEARLY));
                    yearlyTypeChooser.setSelection(new StructuredSelection(StartTimerScriptType.YEARLY_DAY_OF_YEAR));
                    dayNumberYearlyDayOfYear.setText(""+event.getDayNumber());
                    break;
                case MONTHLY_DAY_NUMBER:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.MONTHLY));
                    monthlyTypeChooser.setSelection(new StructuredSelection(StartTimerScriptType.MONTHLY_DAY_NUMBER));
                    dayNumberMonthlyDayNumber.setText(""+event.getDayNumber());
                    break;
                case MONTHLY_DAY_OF_WEEK:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.MONTHLY));
                    monthlyTypeChooser.setSelection(new StructuredSelection(StartTimerScriptType.MONTHLY_DAY_OF_WEEK));
                    dayNumberMonthlyDayOfTheWeek.setText(""+event.getDayNumber());
                    if(dayMonthlyDayOfWeek.getItemCount()>event.getDay() && event.getDay()>0) {
                        dayMonthlyDayOfWeek.setText(dayMonthlyDayOfWeek.getItem(event.getDay()-1));
                    }
                    break;
                case WEEKLY:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.WEEKLY));
                    if(dayWeekly.getItemCount()>event.getDay() && event.getDay()>0) {
                        dayWeekly.setText(dayWeekly.getItem(event.getDay()-1));
                    }
                    break;
                case DAILY:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.DAILY));
                    if(event.getAt() != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(event.getAt());
                        dayAtDaily.setTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
                    }
                    break;
                case HOURLY:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.HOURLY));
                    hours.setText(""+event.getHours());
                    break;
                case MINUTELY:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.MINUTELY));
                    minutes.setText(""+event.getMinutes());
                    break;
                case CONSTANT:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.CONSTANT));
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(event.getAt());
                    constantDate.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                    constantTime.setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
                    break;
                case GROOVY:
                    typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.GROOVY));
                    initialiseConditionViewer();
                    break;
                default:
                    break;
            }
            useFrom.setSelection(event.getFrom()!=null);
            if(event.getFrom() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(event.getFrom());
                fromDate.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                fromTime.setTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
            }
            if(event.getAt() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(event.getAt());
                yearlyDayAt.setTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
            }

        } else{
            useFrom.setSelection(true);
            typeCombo.setSelection(new StructuredSelection(StartTimerScriptType.YEARLY));
        }

    }

    /**
     * @param parentDeadlineLayer
     * @return
     */
    private Composite createGroovyComposite(Composite parentDeadlineLayer) {

        final Composite groovyComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        groovyComposite.setLayout(new GridLayout(1, false));
        conditionViewer = new ExpressionViewer(groovyComposite,SWT.BORDER, ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION) ;
        conditionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        initialiseConditionViewer();
        return groovyComposite;
    }

    private void initialiseConditionViewer() {
        conditionViewer.setInput(event) ;
        condition = event.getCondition() ;
        if(condition == null){
            condition = ExpressionFactory.eINSTANCE.createExpression() ;
        }else{
            condition = EcoreUtil.copy(condition);
        }
        conditionViewer.setSelection(new StructuredSelection(condition)) ;
    }

    /**
     * @param parentDeadlineLayer
     * @return
     */
    private Composite createCustomComposite(Composite parentDeadlineLayer) {

        final Composite customComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        customComposite.setLayout(new GridLayout(3, false));
        Label label = new Label(customComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_every);
        new Text(customComposite, SWT.BORDER);
        label = new Label(customComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_hours);

        return customComposite;
    }

    /**
     * @param parentDeadlineLayer
     * @return
     */
    private Composite createHourlyComposite(Composite parentDeadlineLayer) {
        final Composite hourlyComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        hourlyComposite.setLayout(new GridLayout(3, false));
        Label label = new Label(hourlyComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_every);
        hours = new Text(hourlyComposite, SWT.BORDER);
        hours.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
        label = new Label(hourlyComposite, SWT.NONE);
        //TODO add minutes + seconds
        label.setText(Messages.StartTimerCondition_hours);
        return hourlyComposite;
    }

    /**
     * @param parentDeadlineLayer
     * @return
     */
    private Composite createMinutelyComposite(Composite parentDeadlineLayer) {
        final Composite minutelyComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        minutelyComposite.setLayout(new GridLayout(3, false));
        Label label = new Label(minutelyComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_every);
        minutes = new Text(minutelyComposite, SWT.BORDER);
        label = new Label(minutelyComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_minutes);
        return minutelyComposite;
    }

    /**
     * @param parentDeadlineLayer
     * @return
     */
    private Composite createDailyComposite(final Composite parentDeadlineLayer) {
        final Composite dailyComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        dailyComposite.setLayout(new GridLayout(2, false));
        Label label = new Label(dailyComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_everyDayAt);
        dayAtDaily = new DateTime(dailyComposite, SWT.BORDER | SWT.TIME);
        return dailyComposite;
    }


    /**
     * @param parentDeadlineLayer
     * @return
     */
    private Composite createWeeklyComposite(final Composite parentDeadlineLayer) {
        final Composite weeklyComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        weeklyComposite.setLayout(new GridLayout(3, false));

        Label label = new Label(weeklyComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_every);

        dayWeekly = new Combo(weeklyComposite, SWT.READ_ONLY);
        dayWeekly.add(Messages.sunday);
        dayWeekly.add(Messages.monday);
        dayWeekly.add(Messages.tuesday);
        dayWeekly.add(Messages.wednesday);
        dayWeekly.add(Messages.thursday);
        dayWeekly.add(Messages.friday);
        dayWeekly.add(Messages.saturday);
        dayWeekly.setText(Messages.sunday);

        label = new Label(weeklyComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_ofEachWeek);

        weeklyDayAt = createAtComposite(weeklyComposite);
        return weeklyComposite;
    }

    /**
     * @param parentDeadlineLayer
     * @return
     */
    private Composite createMonthlyComposite(final Composite parentDeadlineLayer) {
        final Composite monthlyComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        monthlyComposite.setLayout(new GridLayout(2, false));

        monthlyTypeChooser = new ComboViewer(monthlyComposite, SWT.READ_ONLY);
        monthlyTypeChooser.setLabelProvider(new ScriptTypeLabelProvider());
        monthlyTypeChooser.add(StartTimerScriptType.MONTHLY_DAY_NUMBER);
        monthlyTypeChooser.add(StartTimerScriptType.MONTHLY_DAY_OF_WEEK);

        final Composite monthlyStackComposite = new Composite(monthlyComposite, SWT.NONE);
        final StackLayout stackLayout = new StackLayout();
        monthlyStackComposite.setLayout(stackLayout);

        final Composite dayNumberComposite = new Composite(monthlyStackComposite, SWT.NONE);
        dayNumberComposite.setLayout(new GridLayout(3, false));
        Label label = new Label(dayNumberComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_every);
        dayNumberMonthlyDayNumber = new Text(dayNumberComposite, SWT.BORDER);
        label = new Label(dayNumberComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_ofEachMonth);

        final Composite dayOfweek = new Composite(monthlyStackComposite, SWT.NONE);
        dayOfweek.setLayout(new GridLayout(4, false));
        Label every = new Label(dayOfweek, SWT.NONE);
        every.setText(Messages.StartTimerCondition_every);


        dayNumberMonthlyDayOfTheWeek = new Text(dayOfweek, SWT.BORDER);
        dayNumberMonthlyDayOfTheWeek.setLayoutData(GridDataFactory.swtDefaults().indent(5, SWT.DEFAULT).create());
        ControlDecoration hint = new ControlDecoration(dayNumberMonthlyDayOfTheWeek, SWT.LEFT | SWT.TOP);
        hint.setImage(Pics.getImage(PicsConstants.hint));
        hint.setDescriptionText(Messages.StartTimerCondition_hint_monthlyDayOfTheWeek);

        dayMonthlyDayOfWeek = new Combo(dayOfweek, SWT.READ_ONLY);
        dayMonthlyDayOfWeek.add(Messages.sunday);
        dayMonthlyDayOfWeek.add(Messages.monday);
        dayMonthlyDayOfWeek.add(Messages.tuesday);
        dayMonthlyDayOfWeek.add(Messages.wednesday);
        dayMonthlyDayOfWeek.add(Messages.thursday);
        dayMonthlyDayOfWeek.add(Messages.friday);
        dayMonthlyDayOfWeek.add(Messages.saturday);
        dayMonthlyDayOfWeek.setText(Messages.sunday);

        label = new Label(dayOfweek, SWT.NONE);
        label.setText(Messages.StartTimerCondition_ofEachMonth);
        monthlyDayAt = createAtComposite(monthlyComposite);
        monthlyTypeChooser.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                StartTimerScriptType selection = (StartTimerScriptType) ((IStructuredSelection) event.getSelection()).getFirstElement();
                Composite toShow = null;
                switch (selection) {
                    case MONTHLY_DAY_NUMBER:
                        toShow = dayNumberComposite;
                        break;
                    case MONTHLY_DAY_OF_WEEK:
                        toShow = dayOfweek;
                        break;
                    default:
                        toShow = dayNumberComposite;
                        break;
                }
                stackLayout.topControl = toShow;
                monthlyStackComposite.layout();
            }
        });


        return monthlyComposite;
    }

    /**
     * @param parentDeadlineLayer
     * @return
     */
    private Composite createYearlyComposite(final Composite parentDeadlineLayer) {
        final Composite yearlyComposite = new Composite(parentDeadlineLayer, SWT.NONE);
        yearlyComposite.setLayout(new GridLayout(2, false));

        yearlyTypeChooser = new ComboViewer(yearlyComposite, SWT.READ_ONLY);
        yearlyTypeChooser.setLabelProvider(new ScriptTypeLabelProvider());
        yearlyTypeChooser.add(StartTimerScriptType.YEARLY_DAY_OF_MONTH);
        yearlyTypeChooser.add(StartTimerScriptType.YEARLY_DAY_OF_YEAR);

        final Composite yearlyStackComposite = new Composite(yearlyComposite, SWT.NONE);
        final StackLayout stackLayout = new StackLayout();
        yearlyStackComposite.setLayout(stackLayout);

        final Composite dayNumberComposite = new Composite(yearlyStackComposite, SWT.NONE);
        dayNumberComposite.setLayout(new GridLayout(3, false));
        Label label = new Label(dayNumberComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_every);
        dayNumberYearlyDayOfYear = new Text(dayNumberComposite, SWT.BORDER);
        label = new Label(dayNumberComposite, SWT.NONE);
        label.setText(Messages.StartTimerCondition_dayOfTheYear);

        final Composite dayOfMonth = new Composite(yearlyStackComposite, SWT.NONE);
        dayOfMonth.setLayout(new GridLayout(4, false));
        label = new Label(dayOfMonth, SWT.NONE);
        label.setText(Messages.StartTimerCondition_every);
        dayNumberYearlyDayOfMonth = new Text(dayOfMonth, SWT.BORDER);
        label = new Label(dayOfMonth, SWT.NONE);
        label.setText(Messages.StartTimerCondition_of);
        months = new Combo(dayOfMonth, SWT.READ_ONLY);
        months.add(Messages.january);
        months.add(Messages.february);
        months.add(Messages.march);
        months.add(Messages.april);
        months.add(Messages.may);
        months.add(Messages.june);
        months.add(Messages.july);
        months.add(Messages.august);
        months.add(Messages.september);
        months.add(Messages.october);
        months.add(Messages.november);
        months.add(Messages.december);
        months.setText(Messages.january);

        yearlyDayAt = createAtComposite(yearlyComposite);

        yearlyTypeChooser.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                StartTimerScriptType selection = (StartTimerScriptType) ((IStructuredSelection) event.getSelection()).getFirstElement();
                Composite toShow = null;
                switch (selection) {
                    case YEARLY_DAY_OF_MONTH:
                        toShow = dayOfMonth;
                        break;
                    case YEARLY_DAY_OF_YEAR:
                        toShow = dayNumberComposite;
                        break;
                    default:
                        toShow = dayNumberComposite;
                        break;
                }
                stackLayout.topControl = toShow;
                yearlyStackComposite.layout();
            }
        });
        return yearlyComposite;
    }

    /**
     * @param parent
     * @return
     */
    protected DateTime createAtComposite(final Composite parent) {
        Composite timeComposite = new Composite(parent, SWT.NONE);
        timeComposite.setLayout(new GridLayout(2, false));
        final Label timeLabel = new Label(timeComposite, SWT.NONE);
        timeLabel.setText(Messages.StartTimerCondition_at);
        return new DateTime(timeComposite, SWT.BORDER | SWT.TIME);
    }

    @Override
    public boolean isPageComplete() {
        return true;
    }

    public Expression getCondition() {
        if (getScriptType().equals(StartTimerScriptType.GROOVY)) {
            return condition;
        } else {
            return null;
        }
    }

    /**
     * 
     */
    public StartTimerScriptType getScriptType() {
        StartTimerScriptType type = (StartTimerScriptType) ((IStructuredSelection) typeCombo.getSelection()).getFirstElement();
        switch (type) {
            case YEARLY:
                type = (StartTimerScriptType) ((IStructuredSelection) yearlyTypeChooser.getSelection()).getFirstElement();
                break;
            case MONTHLY:
                type = (StartTimerScriptType) ((IStructuredSelection) monthlyTypeChooser.getSelection()).getFirstElement();
                break;
            default:
                break;
        }
        return type;
    }

    public Date getFromDate() {
        StartTimerScriptType scriptType = getScriptType();
        if(!scriptType.equals(StartTimerScriptType.GROOVY) && useFrom.getSelection()){
            Calendar calendar = Calendar.getInstance();
            calendar.set(fromDate.getYear(), fromDate.getMonth(), fromDate.getDay(), fromTime.getHours(), fromTime.getMinutes(), fromTime.getSeconds());
            return calendar.getTime();

        }
        return null;
    }

    public Date getToDate() {
        DateTime widget = null;
        switch (getScriptType()) {
            case YEARLY_DAY_OF_YEAR:
                widget = yearlyDayAt;
                break;
            case YEARLY_DAY_OF_MONTH:
                widget = yearlyDayAt;
                break;
            case MONTHLY_DAY_NUMBER:
                widget = monthlyDayAt;
                break;
            case MONTHLY_DAY_OF_WEEK:
                widget = monthlyDayAt;
                break;
            case WEEKLY:
                widget = weeklyDayAt;
                break;
            case DAILY:
                widget = dayAtDaily;
                break;
            case CONSTANT:
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, constantTime.getHours());
                cal.set(Calendar.MINUTE, constantTime.getMinutes());
                cal.set(Calendar.SECOND, constantTime.getSeconds());
                cal.set(Calendar.YEAR, constantDate.getYear());
                cal.set(Calendar.MONTH, constantDate.getMonth());
                cal.set(Calendar.DAY_OF_MONTH, constantDate.getDay());
                return cal.getTime();
            default:
                break;
        }
        if(widget != null){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,widget.getHours());
            calendar.set(Calendar.MINUTE,widget.getMinutes());
            calendar.set(Calendar.SECOND,widget.getSeconds());
            return calendar.getTime();
        }
        return null;
    }

    public int getDay() {
        switch (getScriptType()) {
            case MONTHLY_DAY_OF_WEEK:
                return dayMonthlyDayOfWeek.getSelectionIndex()+1;
            case WEEKLY:
                return dayWeekly.getSelectionIndex()+1;
            default:
                break;
        }
        return -1;
    }

    public int getMonth() {
        switch (getScriptType()) {
            case YEARLY_DAY_OF_MONTH:
                return months.getSelectionIndex();
            default:
                break;
        }
        return -1;
    }

    public int getDayNumber() {
        try {
            switch (getScriptType()) {
                case YEARLY_DAY_OF_MONTH:
                    return Integer.parseInt(dayNumberYearlyDayOfMonth.getText());
                case YEARLY_DAY_OF_YEAR:
                    return Integer.parseInt(dayNumberYearlyDayOfYear.getText());
                case MONTHLY_DAY_NUMBER:
                    return Integer.parseInt(dayNumberMonthlyDayNumber.getText());
                case MONTHLY_DAY_OF_WEEK:
                    return Integer.parseInt(dayNumberMonthlyDayOfTheWeek.getText());
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            setErrorMessage("the day field must be a number");
        }
        return -1;
    }

    public int getHours() {
        try {
            switch (getScriptType()) {
                case HOURLY:
                    return Integer.parseInt(hours.getText());
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            setErrorMessage("the hours field must be a number");
        }
        return -1;
    }

    public int getMinutes() {
        try {
            switch (getScriptType()) {
                case MINUTELY:
                    return Integer.parseInt(minutes.getText());
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            setErrorMessage("the minutes field must be a number");
        }
        return -1;
    }


}
