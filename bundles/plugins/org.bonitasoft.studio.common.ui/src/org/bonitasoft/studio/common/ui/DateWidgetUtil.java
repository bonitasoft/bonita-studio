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
package org.bonitasoft.studio.common.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;

public class DateWidgetUtil {

    public static String getWidgetDisplayDate(DateTime dateChooser, DateTime timeChooser) {
        SimpleDateFormat sdf;
        java.util.Date d = new java.util.Date();

        String time = dateChooser.getYear() + "/" + (dateChooser.getMonth() + 1) + "/" + dateChooser.getDay() + ":" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + timeChooser.getHours() + ":" + timeChooser.getMinutes() + ":" + timeChooser.getSeconds(); //$NON-NLS-1$ //$NON-NLS-2$

        sdf = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss", java.util.Locale.US); //$NON-NLS-1$
        sdf.setLenient(false);
        try {
            d = sdf.parse(time);
        } catch (ParseException e) {
            //BonitaStudioLog.log(e);
        }

        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", java.util.Locale.US); //$NON-NLS-1$
        return sdf.format(d);

    }

    public static String getWidgetMillisecond(Spinner yearSpinner, Spinner monthSpinner, Spinner daySpinner,
            Spinner hourSpinner, Spinner minutesSpinner, Spinner secondsSpinner) {

        return String.valueOf(getWidgetMillisecondAsLong(yearSpinner, monthSpinner, daySpinner, hourSpinner,
                minutesSpinner, secondsSpinner));
    }

    public static Long getWidgetMillisecondAsLong(Spinner yearSpinner, Spinner monthSpinner, Spinner daySpinner,
            Spinner hourSpinner, Spinner minutesSpinner, Spinner secondsSpinner) {

        long duration = 0;

        if (yearSpinner != null && !yearSpinner.isDisposed()) {
            duration = (long) ((long) yearSpinner.getSelection() * (long) 3600000 * (long) 24 * (long) 30 * (long) 12);
        }

        if (monthSpinner != null && !monthSpinner.isDisposed()) {
            duration += (long) ((long) monthSpinner.getSelection() * (long) 3600000 * (long) 24 * (long) 30);
        }

        if (daySpinner != null && !daySpinner.isDisposed()) {
            duration += (long) ((long) daySpinner.getSelection() * (long) (3600000 * (long) 24));
        }

        if (hourSpinner != null && !hourSpinner.isDisposed()) {
            duration += (long) ((long) hourSpinner.getSelection() * (long) (3600000));
        }

        if (minutesSpinner != null && !minutesSpinner.isDisposed()) {
            duration += (long) ((long) minutesSpinner.getSelection() * (long) (60000));
        }

        if (secondsSpinner != null && !secondsSpinner.isDisposed()) {
            duration += (long) ((long) secondsSpinner.getSelection() * (long) (1000));
        }

        return duration;

    }

    public static void setWidgetDisplayDuration(Spinner year, Spinner month, Spinner daySpinner, Spinner hourSpinner,
            Spinner minutesSpinner, Spinner secondsSpinner, long parseLong) {
        long rest;

        if (year != null) {
            int years = (int) (parseLong / (long) (3600000 * (long) 24 * (long) 30 * (long) 12));
            year.setSelection(years);
            rest = parseLong - (long) (years * (long) (3600000 * (long) 24 * (long) 30 * (long) 12));
            parseLong = rest;
        }

        if (month != null) {

            int months = (int) (parseLong / (long) ((long) 3600000 * (long) 24 * (long) 30));
            month.setSelection(months);
            rest = parseLong - (long) (months * (long) ((long) 3600000 * (long) 24 * (long) 30));
            parseLong = rest;
        }

        if (daySpinner != null) {
            int days = (int) (parseLong / (long) ((long) 3600000 * (long) 24));
            daySpinner.setSelection(days);
            rest = parseLong - (long) (days * (long) ((long) 3600000 * (long) 24));
            parseLong = rest;
        }

        if (hourSpinner != null) {
            int hours = (int) ((long) parseLong / (long) 3600000);
            hourSpinner.setSelection(hours);
            rest = parseLong - (hours * 3600000L);
            parseLong = rest;
        }

        if (minutesSpinner != null) {
            int minutes = (int) (parseLong / 60000);
            minutesSpinner.setSelection(minutes);
            rest = parseLong - minutes * 60000L;
            parseLong = rest;
        }

        if (secondsSpinner != null) {
            int seconds = (int) (parseLong / 1000);
            secondsSpinner.setSelection(seconds);
        }
    }
}
