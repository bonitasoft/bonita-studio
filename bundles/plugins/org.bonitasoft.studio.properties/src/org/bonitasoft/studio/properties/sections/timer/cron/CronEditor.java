/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.timer.cron;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.text.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.text.StringToNumberConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;


/**
 * @author Romain Bioteau
 */
public class CronEditor extends Composite {

    private static final String[] HOURS_IN_DAY = new String[] { "00",
            "01",
            "02",
            "03",
            "04",
            "05",
            "06",
            "07",
            "08",
            "09",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
    };

    private static final String[] MINUTES_IN_HOURS = new String[] { "00",
            "01",
            "02",
            "03",
            "04",
            "05",
            "06",
            "07",
            "08",
            "09",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30",
            "31",
            "32",
            "33",
            "34",
            "35",
            "36",
            "37",
            "38",
            "39",
            "40",
            "41",
            "42",
            "43",
            "44",
            "45",
            "46",
            "47",
            "48",
            "49",
            "50",
            "51",
            "52",
            "53",
            "54",
            "55",
            "56",
            "57",
            "58",
            "59",
    };

    private static final String[] RANK = new String[] { Messages.first, Messages.second, Messages.third, Messages.fourth,
            Messages.fifth };
    private static final String[] DAYS_OF_WEEK = new String[] { Messages.monday,
            Messages.tuesday,
            Messages.wednesday,
            Messages.thursday,
            Messages.friday,
            Messages.saturday,
            Messages.sunday,
    };

    private static final String[] MONTHS = new String[] { Messages.january,
            Messages.february,
            Messages.march,
            Messages.april,
            Messages.may,
            Messages.june,
            Messages.july,
            Messages.august,
            Messages.september,
            Messages.october,
            Messages.november,
            Messages.december,
    };

    private static final String CRON_DOCUMENTATION_URL = String.format(
            "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=669&bos_redirect_product=bos&bos_redirect_major_version=%s",
            ProductVersion.minorVersion());

    private CronExpression cronExpression;
    private DataBindingContext context;
    private IValidator dotValidator = new IValidator() {

        @Override
        public IStatus validate(Object value) {
            if (value != null &&
                    !value.toString().contains(".") && !value.toString().contains(",")) {
                return ValidationStatus.ok();
            }
            return ValidationStatus.error(Messages.bind(Messages.notAValidInput, value));
        }
    };

    private List<Listener> tabChangedListeners = new ArrayList<Listener>();

    public CronEditor(Composite parent, int style) {
        super(parent, style);
        setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Link aboutLabel = new Link(this, SWT.WRAP | SWT.NO_FOCUS);
        aboutLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        aboutLabel.setText(NLS.bind(Messages.cronShortDescription, CRON_DOCUMENTATION_URL));
        aboutLabel.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser("cron");
                    browser.openURL(new URL(e.text));
                } catch (Exception e1) {
                    BonitaStudioLog.error(e1);
                }
            }
        });

        final TabFolder tablFolder = new TabFolder(this, SWT.NONE);
        tablFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        tablFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                cronExpression.setMode(tablFolder.getSelection()[0].getText());
            }
        });

        context = new DataBindingContext();
        initializeExpression();

        createMinutesTab(tablFolder);
        createHourlyTab(tablFolder);
        createDailyTab(tablFolder);
        createWeeklyTab(tablFolder);
        createMonthlyTab(tablFolder);
        createYearyTab(tablFolder);
        tablFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                for (Listener l : tabChangedListeners) {
                    l.handleEvent(new Event());
                }
            }
        });
    }

    protected void initializeExpression() {
        cronExpression = new CronExpression();

        cronExpression.setDayFrequencyForDaily(1);
        cronExpression.setMinuteFrequencyForMinute(1);
        cronExpression.setHourFrequencyForHourly(1);
        cronExpression.setAtHour(12);
        cronExpression.setAtHourInDay(12);
        cronExpression.setAtHourInMonth(12);
        cronExpression.setAtHourInWeek(12);
        cronExpression.setAtHourInYear(12);

        cronExpression.setMonthInYearForMonthly(1);
        cronExpression.setMonthOfYearForMonthly(1);
        cronExpression.setMonthRankForMonthly(1);
        cronExpression.setDayOfWeekForMonthly(1);
        cronExpression.setDayOfMonthForMonthly(1);

        cronExpression.setMonthForYearly(1);
        cronExpression.setDayOfMonthForYearly(1);
        cronExpression.setMonthRankForYearly(1);
        cronExpression.setDayOfWeekForYearly(1);
        cronExpression.setMonthOfYearForYearly(1);

        cronExpression.setUseEveryHour(true);
        cronExpression.setUseDayInMonthForMonthly(true);
        cronExpression.setUseEveryDayForDaily(true);
        cronExpression.setEveryYearForYearly(true);
    }

    protected void createYearyTab(TabFolder tablFolder) {
        final TabItem item = new TabItem(tablFolder, SWT.NONE);
        item.setText(Messages.yearly);

        final Composite yearlyContent = new Composite(tablFolder, SWT.NONE);
        yearlyContent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        yearlyContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 10).create());

        final Button everyRadio = new Button(yearlyContent, SWT.RADIO);
        everyRadio.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        everyRadio.setText(Messages.every);

        context.bindValue(SWTObservables.observeSelection(everyRadio),
                PojoProperties.value("everyYearForYearly").observe(cronExpression));

        final Composite everyComposite = new Composite(yearlyContent, SWT.NONE);
        everyComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        everyComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        final Combo monthCombo = new Combo(everyComposite, SWT.READ_ONLY | SWT.BORDER);
        monthCombo.setItems(MONTHS);

        UpdateValueStrategy monthComboStrategy = new UpdateValueStrategy();
        monthComboStrategy.setConverter(new Converter(String.class, Integer.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                if (fromObject.toString().equals(Messages.january)) {
                    return 1;
                } else if (fromObject.toString().equals(Messages.february)) {
                    return 2;
                } else if (fromObject.toString().equals(Messages.march)) {
                    return 3;
                } else if (fromObject.toString().equals(Messages.april)) {
                    return 4;
                } else if (fromObject.toString().equals(Messages.may)) {
                    return 5;
                } else if (fromObject.toString().equals(Messages.june)) {
                    return 6;
                } else if (fromObject.toString().equals(Messages.july)) {
                    return 7;
                } else if (fromObject.toString().equals(Messages.august)) {
                    return 8;
                } else if (fromObject.toString().equals(Messages.september)) {
                    return 9;
                } else if (fromObject.toString().equals(Messages.october)) {
                    return 10;
                } else if (fromObject.toString().equals(Messages.november)) {
                    return 11;
                } else if (fromObject.toString().equals(Messages.december)) {
                    return 12;
                }
                return 1;
            }

        });
        UpdateValueStrategy monthComboStrategy2 = new UpdateValueStrategy();
        monthComboStrategy2.setConverter(new Converter(Integer.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                Integer value = (Integer) fromObject;
                switch (value) {
                    case 1:
                        return Messages.january;
                    case 2:
                        return Messages.february;
                    case 3:
                        return Messages.march;
                    case 4:
                        return Messages.april;
                    case 5:
                        return Messages.may;
                    case 6:
                        return Messages.june;
                    case 7:
                        return Messages.july;
                    case 8:
                        return Messages.august;
                    case 9:
                        return Messages.september;
                    case 10:
                        return Messages.october;
                    case 11:
                        return Messages.november;
                    case 12:
                        return Messages.december;
                    default:
                        break;
                }

                return Messages.january;
            }

        });

        context.bindValue(SWTObservables.observeText(monthCombo),
                PojoProperties.value("monthForYearly").observe(cronExpression), monthComboStrategy, monthComboStrategy2);

        final Text dayText = new Text(everyComposite, SWT.BORDER | SWT.SINGLE);
        dayText.setLayoutData(GridDataFactory.swtDefaults().hint(70, SWT.DEFAULT).create());

        UpdateValueStrategy dayTextStrategy = new UpdateValueStrategy();
        dayTextStrategy.setAfterGetValidator(dotValidator);
        dayTextStrategy.setConverter(StringToNumberConverter.toInteger(true));
        dayTextStrategy.setBeforeSetValidator(new DayInMonthValidator());
        context.bindValue(SWTObservables.observeText(dayText, SWT.Modify),
                PojoProperties.value("dayOfMonthForYearly").observe(cronExpression), dayTextStrategy, null);

        final Button theRadio = new Button(yearlyContent, SWT.RADIO);
        theRadio.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        theRadio.setText(Messages.the);

        final Composite theComposite = new Composite(yearlyContent, SWT.NONE);
        theComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        theComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(0, 0).create());

        final Combo rankCombo = new Combo(theComposite, SWT.READ_ONLY | SWT.BORDER);
        rankCombo.setItems(RANK);

        UpdateValueStrategy rankComboStrategy = new UpdateValueStrategy();
        rankComboStrategy.setConverter(new Converter(String.class, Integer.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                if (fromObject.toString().equals(Messages.first)) {
                    return 1;
                } else if (fromObject.toString().equals(Messages.second)) {
                    return 2;
                } else if (fromObject.toString().equals(Messages.third)) {
                    return 3;
                } else if (fromObject.toString().equals(Messages.fourth)) {
                    return 4;
                } else if (fromObject.toString().equals(Messages.fifth)) {
                    return 5;
                }
                return 1;
            }

        });
        UpdateValueStrategy rankComboStrategy2 = new UpdateValueStrategy();
        rankComboStrategy2.setConverter(new Converter(Integer.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                Integer value = (Integer) fromObject;
                switch (value) {
                    case 1:
                        return Messages.first;
                    case 2:
                        return Messages.second;
                    case 3:
                        return Messages.third;
                    case 4:
                        return Messages.fourth;
                    case 5:
                        return Messages.fifth;
                    default:
                        break;
                }

                return Messages.first;
            }

        });

        context.bindValue(SWTObservables.observeText(rankCombo),
                PojoProperties.value("monthRankForYearly").observe(cronExpression), rankComboStrategy, rankComboStrategy2);

        final Combo dayCombo = new Combo(theComposite, SWT.READ_ONLY | SWT.BORDER);
        dayCombo.setItems(DAYS_OF_WEEK);

        UpdateValueStrategy dayComboStrategy = new UpdateValueStrategy();
        dayComboStrategy.setConverter(new Converter(String.class, Integer.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                if (fromObject.toString().equals(Messages.monday)) {
                    return 1;
                } else if (fromObject.toString().equals(Messages.tuesday)) {
                    return 2;
                } else if (fromObject.toString().equals(Messages.wednesday)) {
                    return 3;
                } else if (fromObject.toString().equals(Messages.thursday)) {
                    return 4;
                } else if (fromObject.toString().equals(Messages.friday)) {
                    return 5;
                } else if (fromObject.toString().equals(Messages.saturday)) {
                    return 6;
                } else if (fromObject.toString().equals(Messages.sunday)) {
                    return 7;
                }
                return 1;
            }

        });
        UpdateValueStrategy dayComboStrategy2 = new UpdateValueStrategy();
        dayComboStrategy2.setConverter(new Converter(Integer.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                Integer value = (Integer) fromObject;
                switch (value) {
                    case 1:
                        return Messages.monday;
                    case 2:
                        return Messages.tuesday;
                    case 3:
                        return Messages.wednesday;
                    case 4:
                        return Messages.thursday;
                    case 5:
                        return Messages.friday;
                    case 6:
                        return Messages.saturday;
                    case 7:
                        return Messages.sunday;
                    default:
                        break;
                }

                return Messages.monday;
            }

        });

        context.bindValue(SWTObservables.observeText(dayCombo),
                PojoProperties.value("dayOfWeekForYearly").observe(cronExpression), dayComboStrategy, dayComboStrategy2);

        final Label ofLabel = new Label(theComposite, SWT.NONE);
        ofLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        ofLabel.setText(Messages.of);

        final Combo monthCombo2 = new Combo(theComposite, SWT.READ_ONLY | SWT.BORDER);
        monthCombo2.setItems(MONTHS);

        UpdateValueStrategy monthCombo2Strategy = new UpdateValueStrategy();
        monthCombo2Strategy.setConverter(new Converter(String.class, Integer.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                if (fromObject.toString().equals(Messages.january)) {
                    return 1;
                } else if (fromObject.toString().equals(Messages.february)) {
                    return 2;
                } else if (fromObject.toString().equals(Messages.march)) {
                    return 3;
                } else if (fromObject.toString().equals(Messages.april)) {
                    return 4;
                } else if (fromObject.toString().equals(Messages.may)) {
                    return 5;
                } else if (fromObject.toString().equals(Messages.june)) {
                    return 6;
                } else if (fromObject.toString().equals(Messages.july)) {
                    return 7;
                } else if (fromObject.toString().equals(Messages.august)) {
                    return 8;
                } else if (fromObject.toString().equals(Messages.september)) {
                    return 9;
                } else if (fromObject.toString().equals(Messages.october)) {
                    return 10;
                } else if (fromObject.toString().equals(Messages.november)) {
                    return 11;
                } else if (fromObject.toString().equals(Messages.december)) {
                    return 12;
                }
                return 1;
            }

        });
        UpdateValueStrategy monthCombo2Strategy2 = new UpdateValueStrategy();
        monthCombo2Strategy2.setConverter(new Converter(Integer.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return null;
                }
                Integer value = (Integer) fromObject;
                switch (value) {
                    case 1:
                        return Messages.january;
                    case 2:
                        return Messages.february;
                    case 3:
                        return Messages.march;
                    case 4:
                        return Messages.april;
                    case 5:
                        return Messages.may;
                    case 6:
                        return Messages.june;
                    case 7:
                        return Messages.july;
                    case 8:
                        return Messages.august;
                    case 9:
                        return Messages.september;
                    case 10:
                        return Messages.october;
                    case 11:
                        return Messages.november;
                    case 12:
                        return Messages.december;
                    default:
                        break;
                }

                return Messages.january;
            }

        });

        context.bindValue(SWTObservables.observeText(monthCombo2),
                PojoProperties.value("monthOfYearForYearly").observe(cronExpression), monthCombo2Strategy,
                monthCombo2Strategy2);

        final IObservableValue hourObservable = PojoProperties.value("atHourInYear").observe(cronExpression);
        final IObservableValue minuteObservable = PojoProperties.value("atMinuteInYear").observe(cronExpression);
        final IObservableValue secondObservable = PojoProperties.value("atSecondInYear").observe(cronExpression);
        createStartTimeComposite(yearlyContent, hourObservable,
                minuteObservable, secondObservable);

        item.setControl(yearlyContent);
    }

    protected Composite createStartTimeComposite(final Composite parentComposite,
            final IObservableValue hourObservable,
            final IObservableValue minuteObservable,
            final IObservableValue secondObservable) {
        final Composite timeComposite = new Composite(parentComposite, SWT.NONE);
        timeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        timeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).create());

        final Label startTimeLabel = new Label(timeComposite, SWT.NONE);
        startTimeLabel.setText(Messages.startTime);

        final Combo hourCombo = new Combo(timeComposite, SWT.READ_ONLY | SWT.BORDER);
        hourCombo.setItems(HOURS_IN_DAY);

        Format formatter = new DecimalFormat("#00");
        UpdateValueStrategy hourStrategy = new UpdateValueStrategy();
        hourStrategy.setConverter(StringToNumberConverter.toInteger(true));

        UpdateValueStrategy hourStrategy2 = new UpdateValueStrategy();
        hourStrategy2.setConverter(NumberToStringConverter.fromInteger(formatter, true));

        context.bindValue(SWTObservables.observeText(hourCombo), hourObservable, hourStrategy, hourStrategy2);

        final Combo minuteCombo = new Combo(timeComposite, SWT.READ_ONLY | SWT.BORDER);
        minuteCombo.setItems(MINUTES_IN_HOURS);
        UpdateValueStrategy minuteStrategy = new UpdateValueStrategy();
        minuteStrategy.setConverter(StringToNumberConverter.toInteger(true));
        UpdateValueStrategy minuteStrategy2 = new UpdateValueStrategy();
        minuteStrategy2.setConverter(NumberToStringConverter.fromInteger(formatter, true));
        context.bindValue(SWTObservables.observeText(minuteCombo), minuteObservable, minuteStrategy, minuteStrategy2);

        final Combo secondCombo = new Combo(timeComposite, SWT.READ_ONLY | SWT.BORDER);
        secondCombo.setItems(MINUTES_IN_HOURS);
        UpdateValueStrategy secondStrategy = new UpdateValueStrategy();
        secondStrategy.setConverter(StringToNumberConverter.toInteger(true));
        UpdateValueStrategy secondStrategy2 = new UpdateValueStrategy();
        secondStrategy2.setConverter(NumberToStringConverter.fromInteger(formatter, true));
        context.bindValue(SWTObservables.observeText(secondCombo), secondObservable, secondStrategy, secondStrategy2);

        return timeComposite;
    }

    protected void createMonthlyTab(TabFolder tablFolder) {
        final TabItem item = new TabItem(tablFolder, SWT.NONE);
        item.setText(Messages.monthly);

        final Composite monthlyContent = new Composite(tablFolder, SWT.NONE);
        monthlyContent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        monthlyContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 10).create());

        final Button dayRadio = new Button(monthlyContent, SWT.RADIO);
        dayRadio.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        dayRadio.setText(Messages.day);

        context.bindValue(SWTObservables.observeSelection(dayRadio),
                PojoProperties.value("useDayInMonthForMonthly").observe(cronExpression));

        final Composite dayComposite = new Composite(monthlyContent, SWT.NONE);
        dayComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        dayComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(0, 0).create());

        final Text dayText = new Text(dayComposite, SWT.BORDER | SWT.SINGLE);
        dayText.setLayoutData(GridDataFactory.fillDefaults().hint(70, SWT.DEFAULT).create());

        UpdateValueStrategy dayTextStrategy = new UpdateValueStrategy();
        dayTextStrategy.setAfterGetValidator(dotValidator);
        dayTextStrategy.setConverter(StringToNumberConverter.toInteger(true));
        dayTextStrategy.setBeforeSetValidator(new DayInMonthValidator());
        context.bindValue(SWTObservables.observeText(dayText, SWT.Modify),
                PojoProperties.value("dayOfMonthForMonthly").observe(cronExpression), dayTextStrategy, null);

        final Label ofEveryLabel = new Label(dayComposite, SWT.NONE);
        ofEveryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        ofEveryLabel.setText(Messages.ofEvery);

        final Text everyMonthText = new Text(dayComposite, SWT.BORDER | SWT.SINGLE);
        everyMonthText.setLayoutData(GridDataFactory.fillDefaults().hint(70, SWT.DEFAULT).create());

        UpdateValueStrategy everyMonthTextStrategy = new UpdateValueStrategy();
        everyMonthTextStrategy.setAfterGetValidator(dotValidator);
        everyMonthTextStrategy.setConverter(StringToNumberConverter.toInteger(true));
        everyMonthTextStrategy.setBeforeSetValidator(new MonthInYearValidator());
        context.bindValue(SWTObservables.observeText(everyMonthText, SWT.Modify),
                PojoProperties.value("monthInYearForMonthly").observe(cronExpression), everyMonthTextStrategy, null);

        final Label monthLabel = new Label(dayComposite, SWT.NONE);
        monthLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        monthLabel.setText(Messages.monthLabel);

        final Button theRadio = new Button(monthlyContent, SWT.RADIO);
        theRadio.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        theRadio.setText(Messages.the);

        final Composite theComposite = new Composite(monthlyContent, SWT.NONE);
        theComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        theComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(5).margins(0, 0).create());

        final Combo rankCombo = new Combo(theComposite, SWT.READ_ONLY | SWT.BORDER);
        rankCombo.setItems(RANK);

        UpdateValueStrategy rankComboStrategy = new UpdateValueStrategy();
        rankComboStrategy.setConverter(new Converter(String.class, Integer.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return 1;
                }
                if (Messages.first.equals(fromObject.toString())) {
                    return 1;
                } else if (Messages.second.equals(fromObject.toString())) {
                    return 2;
                } else if (Messages.third.equals(fromObject.toString())) {
                    return 3;
                } else if (Messages.fourth.equals(fromObject.toString())) {
                    return 4;
                } else if (Messages.fifth.equals(fromObject.toString())) {
                    return 5;
                }
                return 1;
            }

        });
        UpdateValueStrategy rankComboStrategy2 = new UpdateValueStrategy();
        rankComboStrategy2.setConverter(new Converter(Integer.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return Messages.first;
                }
                Integer value = (Integer) fromObject;
                switch (value) {
                    case 1:
                        return Messages.first;
                    case 2:
                        return Messages.second;
                    case 3:
                        return Messages.third;
                    case 4:
                        return Messages.fourth;
                    case 5:
                        return Messages.fifth;
                    default:
                        break;
                }

                return Messages.first;
            }

        });

        context.bindValue(SWTObservables.observeText(rankCombo),
                PojoProperties.value("monthRankForMonthly").observe(cronExpression), rankComboStrategy, rankComboStrategy2);

        final Combo dayCombo = new Combo(theComposite, SWT.READ_ONLY | SWT.BORDER);
        dayCombo.setItems(DAYS_OF_WEEK);

        UpdateValueStrategy dayComboStrategy = new UpdateValueStrategy();
        dayComboStrategy.setConverter(new Converter(String.class, Integer.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return 1;
                }
                if (fromObject.toString().equals(Messages.monday)) {
                    return 1;
                } else if (fromObject.toString().equals(Messages.tuesday)) {
                    return 2;
                } else if (fromObject.toString().equals(Messages.wednesday)) {
                    return 3;
                } else if (fromObject.toString().equals(Messages.thursday)) {
                    return 4;
                } else if (fromObject.toString().equals(Messages.friday)) {
                    return 5;
                } else if (fromObject.toString().equals(Messages.saturday)) {
                    return 6;
                } else if (fromObject.toString().equals(Messages.sunday)) {
                    return 7;
                }
                return 1;
            }

        });
        UpdateValueStrategy dayComboStrategy2 = new UpdateValueStrategy();
        dayComboStrategy2.setConverter(new Converter(Integer.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject == null) {
                    return Messages.monday;
                }
                Integer value = (Integer) fromObject;
                switch (value) {
                    case 1:
                        return Messages.monday;
                    case 2:
                        return Messages.tuesday;
                    case 3:
                        return Messages.wednesday;
                    case 4:
                        return Messages.thursday;
                    case 5:
                        return Messages.friday;
                    case 6:
                        return Messages.saturday;
                    case 7:
                        return Messages.sunday;
                    default:
                        break;
                }

                return Messages.monday;
            }

        });

        context.bindValue(SWTObservables.observeText(dayCombo),
                PojoProperties.value("dayOfWeekForMonthly").observe(cronExpression), dayComboStrategy, dayComboStrategy2);

        final Label ofEveryLabel2 = new Label(theComposite, SWT.NONE);
        ofEveryLabel2.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        ofEveryLabel2.setText(Messages.ofEvery);

        final Text everyMonthText2 = new Text(theComposite, SWT.BORDER | SWT.SINGLE);
        everyMonthText2.setLayoutData(GridDataFactory.swtDefaults().hint(70, SWT.DEFAULT).create());

        UpdateValueStrategy everyMonthText2Strategy = new UpdateValueStrategy();
        everyMonthText2Strategy.setConverter(StringToNumberConverter.toInteger(true));
        everyMonthText2Strategy.setBeforeSetValidator(new FrequencyValidator());
        context.bindValue(SWTObservables.observeText(everyMonthText2, SWT.Modify),
                PojoProperties.value("monthOfYearForMonthly").observe(cronExpression), everyMonthText2Strategy, null);

        final Label monthLabel2 = new Label(theComposite, SWT.NONE);
        monthLabel2.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        monthLabel2.setText(Messages.monthLabel);

        final IObservableValue hourObservable = PojoProperties.value("atHourInMonth").observe(cronExpression);
        final IObservableValue minuteObservable = PojoProperties.value("atMinuteInMonth").observe(cronExpression);
        final IObservableValue secondObservable = PojoProperties.value("atSecondInMonth").observe(cronExpression);
        createStartTimeComposite(monthlyContent, hourObservable, minuteObservable, secondObservable);

        item.setControl(monthlyContent);
    }

    protected void createWeeklyTab(TabFolder tablFolder) {
        final TabItem item = new TabItem(tablFolder, SWT.NONE);
        item.setText(Messages.weekly);

        final Composite weeklyContent = new Composite(tablFolder, SWT.NONE);
        weeklyContent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        weeklyContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(15, 10).create());

        final Button mondayButton = new Button(weeklyContent, SWT.CHECK);
        mondayButton.setText(Messages.monday);
        context.bindValue(SWTObservables.observeSelection(mondayButton),
                PojoProperties.value("onMonday").observe(cronExpression));

        final Button tuesdayButton = new Button(weeklyContent, SWT.CHECK);
        tuesdayButton.setText(Messages.tuesday);
        context.bindValue(SWTObservables.observeSelection(tuesdayButton),
                PojoProperties.value("onTuesday").observe(cronExpression));

        final Button wednesdayButton = new Button(weeklyContent, SWT.CHECK);
        wednesdayButton.setText(Messages.wednesday);
        context.bindValue(SWTObservables.observeSelection(wednesdayButton),
                PojoProperties.value("onWednesday").observe(cronExpression));

        final Button thursdayButton = new Button(weeklyContent, SWT.CHECK);
        thursdayButton.setText(Messages.thursday);
        context.bindValue(SWTObservables.observeSelection(thursdayButton),
                PojoProperties.value("onThursday").observe(cronExpression));

        final Button fridayButton = new Button(weeklyContent, SWT.CHECK);
        fridayButton.setText(Messages.friday);
        context.bindValue(SWTObservables.observeSelection(fridayButton),
                PojoProperties.value("onFriday").observe(cronExpression));

        final Button saturdayButton = new Button(weeklyContent, SWT.CHECK);
        saturdayButton.setText(Messages.saturday);
        context.bindValue(SWTObservables.observeSelection(saturdayButton),
                PojoProperties.value("onSaturday").observe(cronExpression));

        final Button sundayButton = new Button(weeklyContent, SWT.CHECK);
        sundayButton.setText(Messages.sunday);
        context.bindValue(SWTObservables.observeSelection(sundayButton),
                PojoProperties.value("onSunday").observe(cronExpression));

        final IObservableValue hourObservable = PojoProperties.value("atHourInWeek").observe(cronExpression);
        final IObservableValue minuteObservable = PojoProperties.value("atMinuteInWeek").observe(cronExpression);
        final IObservableValue secondObservable = PojoProperties.value("atSecondInWeek").observe(cronExpression);
        createStartTimeComposite(weeklyContent, hourObservable, minuteObservable, secondObservable)
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(4, 1).create());

        item.setControl(weeklyContent);
    }

    protected void createDailyTab(TabFolder tablFolder) {
        final TabItem item = new TabItem(tablFolder, SWT.NONE);
        item.setText(Messages.daily);

        final Composite dailyContent = new Composite(tablFolder, SWT.NONE);
        dailyContent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        dailyContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 10).create());

        final Button everyRadio = new Button(dailyContent, SWT.RADIO);
        everyRadio.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        everyRadio.setText(Messages.every);

        context.bindValue(SWTObservables.observeSelection(everyRadio),
                PojoProperties.value("useEveryDayForDaily").observe(cronExpression));

        final Composite everyComposite = new Composite(dailyContent, SWT.NONE);
        everyComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(-65, 0).create());
        everyComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        final Text dayText = new Text(everyComposite, SWT.BORDER | SWT.SINGLE);
        dayText.setLayoutData(GridDataFactory.fillDefaults().hint(70, SWT.DEFAULT).create());

        UpdateValueStrategy dayFrequencyStrategy = new UpdateValueStrategy();
        dayFrequencyStrategy.setAfterGetValidator(dotValidator);
        dayFrequencyStrategy.setConverter(StringToNumberConverter.toInteger(true));
        dayFrequencyStrategy.setBeforeSetValidator(new FrequencyValidator());
        context.bindValue(SWTObservables.observeText(dayText, SWT.Modify),
                PojoProperties.value("dayFrequencyForDaily").observe(cronExpression), dayFrequencyStrategy, null);

        final Label dayLabel = new Label(everyComposite, SWT.NONE);
        dayLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        dayLabel.setText(Messages.dayLabel);

        final Button everyWeekDayRadio = new Button(dailyContent, SWT.RADIO);
        everyWeekDayRadio.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        everyWeekDayRadio.setText(Messages.everyWeekDay);

        final Label filler = new Label(dailyContent, SWT.NONE);
        filler.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final IObservableValue hourObservable = PojoProperties.value("atHourInDay").observe(cronExpression);
        final IObservableValue minuteObservable = PojoProperties.value("atMinuteInDay").observe(cronExpression);
        final IObservableValue secondObservable = PojoProperties.value("atSecondInDay").observe(cronExpression);
        createStartTimeComposite(dailyContent, hourObservable, minuteObservable, secondObservable);

        item.setControl(dailyContent);
    }

    protected void createHourlyTab(TabFolder tablFolder) {
        final TabItem item = new TabItem(tablFolder, SWT.NONE);
        item.setText(Messages.hourly);

        final Composite hourlyContent = new Composite(tablFolder, SWT.NONE);
        hourlyContent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        hourlyContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 10).create());

        final Button everyRadio = new Button(hourlyContent, SWT.RADIO);
        everyRadio.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        everyRadio.setText(Messages.every);

        context.bindValue(SWTObservables.observeSelection(everyRadio),
                PojoProperties.value("useEveryHour").observe(cronExpression));

        final Composite everyComposite = new Composite(hourlyContent, SWT.NONE);
        everyComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        everyComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        final Text minuteText = new Text(everyComposite, SWT.BORDER | SWT.SINGLE);
        minuteText.setLayoutData(GridDataFactory.fillDefaults().hint(70, SWT.DEFAULT).create());

        UpdateValueStrategy hourFrequencyStrategy = new UpdateValueStrategy();
        hourFrequencyStrategy.setAfterGetValidator(dotValidator);
        hourFrequencyStrategy.setConverter(StringToNumberConverter.toInteger(true));
        hourFrequencyStrategy.setBeforeSetValidator(new FrequencyValidator());

        context.bindValue(SWTObservables.observeText(minuteText, SWT.Modify),
                PojoProperties.value("hourFrequencyForHourly").observe(cronExpression), hourFrequencyStrategy, null);

        final Label minuteLabel = new Label(everyComposite, SWT.NONE);
        minuteLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        minuteLabel.setText(Messages.hourLabel);

        final Button atRadio = new Button(hourlyContent, SWT.RADIO);
        atRadio.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        atRadio.setText(Messages.at);

        final Composite atComposite = new Composite(hourlyContent, SWT.NONE);
        atComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        atComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());

        final Combo hourCombo = new Combo(atComposite, SWT.READ_ONLY | SWT.BORDER);
        hourCombo.setItems(HOURS_IN_DAY);

        UpdateValueStrategy hourStrategy = new UpdateValueStrategy();
        hourStrategy.setConverter(StringToNumberConverter.toInteger(true));
        UpdateValueStrategy hourStrategy2 = new UpdateValueStrategy();

        Format formatter = new DecimalFormat("#00");
        hourStrategy2.setConverter(NumberToStringConverter.fromInteger(formatter, true));
        context.bindValue(SWTObservables.observeText(hourCombo),
                PojoProperties.value("atHour").observe(cronExpression), hourStrategy, hourStrategy2);

        final Combo minuteCombo = new Combo(atComposite, SWT.READ_ONLY | SWT.BORDER);
        minuteCombo.setItems(MINUTES_IN_HOURS);

        UpdateValueStrategy minuteStrategy = new UpdateValueStrategy();
        minuteStrategy.setConverter(StringToNumberConverter.toInteger(true));
        UpdateValueStrategy minuteStrategy2 = new UpdateValueStrategy();
        minuteStrategy2.setConverter(NumberToStringConverter.fromInteger(formatter, true));
        context.bindValue(SWTObservables.observeText(minuteCombo),
                PojoProperties.value("atMinute").observe(cronExpression), minuteStrategy, minuteStrategy2);

        final Combo secondCombo = new Combo(atComposite, SWT.READ_ONLY | SWT.BORDER);
        secondCombo.setItems(MINUTES_IN_HOURS);

        final IObservableValue secondObservable = PojoProperties.value("atSecond").observe(cronExpression);
        UpdateValueStrategy secondStrategy = new UpdateValueStrategy();
        secondStrategy.setConverter(StringToNumberConverter.toInteger(true));
        UpdateValueStrategy secondStrategy2 = new UpdateValueStrategy();
        secondStrategy2.setConverter(NumberToStringConverter.fromInteger(formatter, true));
        context.bindValue(SWTObservables.observeText(secondCombo), secondObservable, secondStrategy, secondStrategy2);

        item.setControl(hourlyContent);
    }

    protected void createMinutesTab(TabFolder tablFolder) {
        final TabItem item = new TabItem(tablFolder, SWT.NONE);
        item.setText(Messages.minutes);
        final Composite minuteContent = new Composite(tablFolder, SWT.NONE);
        minuteContent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        minuteContent.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(15, 10).create());

        final Label everyLabel = new Label(minuteContent, SWT.NONE);
        everyLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        everyLabel.setText(Messages.every);

        final Text minuteText = new Text(minuteContent, SWT.BORDER | SWT.SINGLE);
        minuteText.setLayoutData(GridDataFactory.fillDefaults().hint(70, SWT.DEFAULT).create());

        UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setAfterGetValidator(dotValidator);
        strategy.setConverter(StringToNumberConverter.toInteger(true));
        strategy.setBeforeSetValidator(new FrequencyValidator());
        context.bindValue(SWTObservables.observeText(minuteText, SWT.Modify),
                PojoProperties.value("minuteFrequencyForMinute").observe(cronExpression), strategy, null);

        final Label minuteLabel = new Label(minuteContent, SWT.NONE);
        minuteLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        minuteLabel.setText(Messages.minuteLabel);

        item.setControl(minuteContent);
        cronExpression.setMode(item.getText());
    }

    public DataBindingContext getContext() {
        return context;
    }

    public String getExpression() {
        return cronExpression.getExpression();
    }

    public void addTabChangedListener(Listener listener) {
        tabChangedListeners.add(listener);
    }

}
