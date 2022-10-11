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

package org.bonitasoft.studio.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

/**
 * @author Romain Bioteau
 */
public class DateUtil {

    /**
     * Bonita Date Format
     */
    private static final String DATE_FORMAT_1 = "yyyy/MM/dd/HH/mm/SSS"; //$NON-NLS-1$
    private static final String DATE_FORMAT_2 = "yyyy/MM/dd/HH/mm/ss"; //$NON-NLS-1$
    private static final String DATE_FORMAT_3 = "yyyy/MM/dd'T'HH:mm:ss.SSSZ"; //$NON-NLS-1$

    private static String DEFAULT_DATE_FORMAT = DATE_FORMAT_2;
    private static String DISPLAY_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss"; //$NON-NLS-1$

    public static String getDisplayDate(String systemDate) {
        SimpleDateFormat sdf;
        java.util.Date d = new java.util.Date();

        sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.US);

        try {
            d = sdf.parse(systemDate);
        } catch (ParseException e) {
            //BonitaStudioLog.log(e);
        }

        sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT, java.util.Locale.US);

        return sdf.format(d);
    }

    public static String getSystemFormatDate(String date) {
        SimpleDateFormat sdf;
        java.util.Date d = new java.util.Date();

        sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT, java.util.Locale.US);
        sdf.setLenient(false);
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            //BonitaStudioLog.log(e);
        }

        sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.format(d);

    }

    public static void setDefaultSystemFormat(String format) {

        if (DATE_FORMAT_1.equals(format)) {
            DEFAULT_DATE_FORMAT = DATE_FORMAT_1;
        } else if (DATE_FORMAT_2.equals(format)) {
            DEFAULT_DATE_FORMAT = DATE_FORMAT_2;
        } else if (DATE_FORMAT_3.equals(format)) {
            DEFAULT_DATE_FORMAT = DATE_FORMAT_3;
        }

    }

    public Date getDatefromModelData(String formattedModelData) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            return sdf.parse(formattedModelData);
        } catch (ParseException ex) {
            BonitaStudioLog.error(ex);
            return null;
        }
    }

    public static boolean checkDate(String deadline) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT, java.util.Locale.US);
        sdf.setLenient(false);
        try {
            sdf.parse(deadline);
        } catch (ParseException e) {
            return false;
        }

        return true;

    }

    public static boolean isDuration(String event) {

        long r = -1;
        try {
            r = Long.parseLong(event);
        } catch (NumberFormatException e) {
            return false;
        }

        return String.valueOf(r).equals(event);
    }

    public static boolean isDate(String event) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.US);
        sdf.setLenient(false);
        boolean displayDate = true;
        boolean systemDate = true;

        try {
            sdf.parse(event);
        } catch (Exception e) {
            systemDate = false;
        }

        sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT, java.util.Locale.US);
        try {
            sdf.parse(event);
        } catch (Exception e) {
            displayDate = false;
        }

        return systemDate || displayDate;
    }

    public static java.util.Date getDate(String time) {
        SimpleDateFormat sdf;
        java.util.Date d = null;

        sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.US);
        sdf.setLenient(false);
        try {
            d = sdf.parse(time);
        } catch (ParseException e) {
            return null;
        }

        return d;

    }

    public static String getDisplayDuration(String condition) {
        long parseLong = Long.parseLong(condition);
        return getDisplayDuration(parseLong);
    }

    /**
     * @param parseLong
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getDisplayDuration(long parseLong) {
        int days = (int) (parseLong / (3600000 * 24));
        long rest = parseLong - (days * (3600000 * 24L));
        int hours = (int) (rest / 3600000);
        rest = rest - (hours * 3600000L);
        int minutes = (int) (rest / 60000);
        rest = rest - minutes * 60000L;
        int seconds = (int) (rest / 1000);

        SimpleDateFormat sdf;
        java.util.Date d = new java.util.Date();
        d.setHours(hours);
        d.setMinutes(minutes);
        d.setSeconds(seconds);

        sdf = new SimpleDateFormat("HH:mm:ss", java.util.Locale.US); //$NON-NLS-1$

        return days > 0 ? days + " " + Messages.daysLabel + " " + sdf.format(d) : sdf.format(d); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static String getDateExpressionContent(int year, int month, int day, int hours, int minutes, int seconds) {
        return "Calendar calendar = GregorianCalendar.getInstance();\n" +
                "calendar.set(" + year + ", " + month + "," + day + ", " + hours + ", " + minutes + ", " + seconds
                + ");\n" +
                "calendar.getTime();";
    }

}
