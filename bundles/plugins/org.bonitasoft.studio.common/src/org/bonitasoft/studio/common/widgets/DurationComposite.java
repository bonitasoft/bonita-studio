/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.widgets;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.Messages;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public class DurationComposite extends Composite {


    private Spinner yearsSpinner;
    private Spinner monthsSpinner;
    private Spinner daySpinner;
    private Spinner hourSpinner;
    private Spinner minutesSpinner;
    private Spinner secondSpinner;
    private long maximumDuration = Long.MAX_VALUE;

    /**
     * @param composite
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     */
    public DurationComposite(Composite parent, boolean showYear, boolean showMonth, boolean showDay, boolean showHour, boolean showMinute, boolean showSecond, TabbedPropertySheetWidgetFactory widgetFactory) {
        super(parent, SWT.NONE);
        if(widgetFactory != null){
            setBackground(ColorConstants.white);
        }
        int nbWidgets = 0;
        if (showYear) {
            nbWidgets++;
        }
        if (showMonth) {
            nbWidgets++;
        }
        if (showDay) {
            nbWidgets++;
        }
        if (showHour) {
            nbWidgets++;
        }
        if (showMinute) {
            nbWidgets++;
        }
        if (showSecond) {
            nbWidgets++;
        }

        setLayout(GridLayoutFactory.fillDefaults().numColumns(nbWidgets*2).margins(0, 0).spacing(0, 0).create());

        if(showYear) {

            Label label;
            if(widgetFactory != null) {
                label = widgetFactory.createLabel(this,org.bonitasoft.studio.common.Messages.yearsLabel,SWT.TRANSPARENT);
            } else {
                label = new Label(this, SWT.TRANSPARENT);
                label.setText(Messages.yearsLabel);
            }

            yearsSpinner = new Spinner(this, SWT.BORDER);
            yearsSpinner.setMaximum(999);
        }

        if(showMonth) {
            Label label;
            if(widgetFactory != null) {
                label = widgetFactory.createLabel(this,org.bonitasoft.studio.common.Messages.monthsLabel,SWT.TRANSPARENT);
            } else {
                label = new Label(this, SWT.TRANSPARENT);
                label.setText(Messages.monthsLabel);
            }
            monthsSpinner = new Spinner(this, SWT.BORDER);
            monthsSpinner.setMaximum(999);
        }

        if(showDay) {
            Label label;
            if(widgetFactory != null) {
                label = widgetFactory.createLabel(this,org.bonitasoft.studio.common.Messages.daysLabel,SWT.TRANSPARENT);
            } else {
                label = new Label(this, SWT.TRANSPARENT);
                label.setText(Messages.daysLabel);
            }
            daySpinner = new Spinner(this, SWT.BORDER);
            daySpinner.setMaximum(999);
        }

        if(showHour) {
            Label label;
            if(widgetFactory != null) {
                label = widgetFactory.createLabel(this,org.bonitasoft.studio.common.Messages.hoursLabel,SWT.TRANSPARENT);
            } else {
                label = new Label(this, SWT.TRANSPARENT);
                label.setText(Messages.hoursLabel);
            }
            hourSpinner = new Spinner(this, SWT.BORDER );
            hourSpinner.setMaximum(999);
        }


        if(showMinute) {
            Label label;
            if(widgetFactory != null) {
                label = widgetFactory.createLabel(this,org.bonitasoft.studio.common.Messages.minutesLabel,SWT.TRANSPARENT);
            } else {
                label = new Label(this, SWT.TRANSPARENT);
                label.setText(Messages.minutesLabel);
            }
            minutesSpinner = new Spinner(this, SWT.BORDER );
            minutesSpinner.setMaximum(999);
        }

        if(showSecond) {
            Label label;
            if(widgetFactory != null) {
                label = widgetFactory.createLabel(this,org.bonitasoft.studio.common.Messages.secondsLabel,SWT.TRANSPARENT);
            } else {
                label = new Label(this, SWT.TRANSPARENT);
                label.setText(Messages.secondsLabel);
            }
            secondSpinner = new Spinner(this, SWT.BORDER);
            secondSpinner.setMaximum(999);
        }


    }

    public void addModifyListener(ModifyListener modifyListener){
        if(yearsSpinner != null){
            yearsSpinner.addModifyListener(modifyListener);
        }
        if(monthsSpinner != null){
            monthsSpinner.addModifyListener(modifyListener);
        }
        if(daySpinner != null){
            daySpinner.addModifyListener(modifyListener);
        }
        if(hourSpinner != null){
            hourSpinner.addModifyListener(modifyListener);
        }
        if(minutesSpinner != null){
            minutesSpinner.addModifyListener(modifyListener);
        }
        if(secondSpinner != null){
            secondSpinner.addModifyListener(modifyListener);
        }
    }


    public void setDuration(long value){
        if(value > maximumDuration){
            DateUtil.setWidgetDisplayDuration(yearsSpinner, monthsSpinner, daySpinner, hourSpinner, minutesSpinner, secondSpinner, maximumDuration);
        }else{
            DateUtil.setWidgetDisplayDuration(yearsSpinner, monthsSpinner, daySpinner, hourSpinner, minutesSpinner, secondSpinner, value);
        }

    }

    public long getDuration(){
        return DateUtil.getWidgetMillisecondAsLong(yearsSpinner, monthsSpinner, daySpinner, hourSpinner, minutesSpinner, secondSpinner);
    }

    public void setMaxDuration(long executionTime) {
        maximumDuration = executionTime ;
        addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                if(getDuration() > maximumDuration){
                    setDuration(maximumDuration) ;
                }
                DurationComposite.this.notifyListeners(SWT.Modify, new Event());//need it to use databinding

            }
        });
    }
}
