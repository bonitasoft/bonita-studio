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

package org.bonitasoft.studio.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;

/**
 * @author Romain Bioteau
 *
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
		SimpleDateFormat sdf ;
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
		SimpleDateFormat sdf ;
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

	public static String getWidgetDisplayDate(DateTime dateChooser, DateTime timeChooser) {
		SimpleDateFormat sdf ;
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

	public static String getWidgetMillisecond(Spinner yearSpinner,Spinner monthSpinner,Spinner daySpinner, Spinner hourSpinner,Spinner minutesSpinner,Spinner secondsSpinner) {

		return String.valueOf(getWidgetMillisecondAsLong(yearSpinner, monthSpinner, daySpinner, hourSpinner, minutesSpinner, secondsSpinner));
	}
	

	public static Long getWidgetMillisecondAsLong(Spinner yearSpinner,Spinner monthSpinner,Spinner daySpinner, Spinner hourSpinner,Spinner minutesSpinner,Spinner secondsSpinner) {
		
		
		long duration = 0;
		
		if(yearSpinner != null && !yearSpinner.isDisposed()){
			duration = (long)((long)yearSpinner.getSelection()*(long)3600000*(long)24*(long)30*(long)12) ;
		}
		
		if(monthSpinner != null && !monthSpinner.isDisposed()){
			duration += (long)((long)monthSpinner.getSelection()*(long)3600000* (long)24* (long) 30) ;
		}
		
		if(daySpinner != null && !daySpinner.isDisposed()){
			duration +=  (long)((long)daySpinner.getSelection()*(long)(3600000*(long)24)) ;
		}
		
		if(hourSpinner != null && !hourSpinner.isDisposed()){
			duration +=  (long)((long)hourSpinner.getSelection()*(long)(3600000)) ;
		}
		
		if(minutesSpinner != null && !minutesSpinner.isDisposed()){
			duration +=  (long)((long)minutesSpinner.getSelection()*(long)(60000)) ;
		}
		
		if(secondsSpinner != null && !secondsSpinner.isDisposed()){
			duration +=  (long)((long)secondsSpinner.getSelection()*(long)(1000)) ;
		}
		
		return duration;

	}

	public static void setDefaultSystemFormat(String format) {

		if(DATE_FORMAT_1.equals(format)){
			DEFAULT_DATE_FORMAT = DATE_FORMAT_1;
		}else if(DATE_FORMAT_2.equals(format)){
			DEFAULT_DATE_FORMAT = DATE_FORMAT_2;
		}else if(DATE_FORMAT_3.equals(format)){
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
		SimpleDateFormat sdf ;
		sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT, java.util.Locale.US);
		sdf.setLenient(false);
		try {
			sdf.parse(deadline);
		} catch (ParseException e) {
			return false ;
		}

		return true ;

	}

	public static boolean isDuration(String event) {

		long r = -1 ;
		try {
			r = Long.parseLong(event);
		} catch (NumberFormatException e) {
			return false ;
		}

		return String.valueOf(r).equals(event);
	}

	public static boolean isDate(String event) {
		SimpleDateFormat sdf ;
		sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.US);
		sdf.setLenient(false);
		boolean displayDate = true  ;
		boolean systemDate = true  ;

		try {
			sdf.parse(event);
		} catch (Exception e) {
			systemDate = false ;
		}

		sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT, java.util.Locale.US);
		try {
			sdf.parse(event);
		} catch (Exception e) {
			displayDate = false ;
		}

		return systemDate || displayDate;
	}

	public static java.util.Date getDate(String time) {
		SimpleDateFormat sdf ;
		java.util.Date d = null;

		sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.US);
		sdf.setLenient(false);
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			return null ;
		}

		return d;

	}

	public static void setWidgetDisplayDuration(Spinner year,Spinner month,Spinner daySpinner, Spinner hourSpinner,Spinner minutesSpinner,Spinner secondsSpinner,long parseLong) {
		long rest  ;

		if(year != null){
			int years = (int) (parseLong/(long)(3600000*(long)24*(long)30*(long)12)) ;
			year.setSelection(years);
			rest = parseLong - (long)(years*(long)(3600000*(long)24*(long)30*(long)12)) ;
			parseLong = rest ;
		}

		if(month != null){

			int months = (int) (parseLong/(long)((long)3600000*(long)24*(long)30)) ;
			month.setSelection(months);
			rest = parseLong - (long)(months*(long)((long)3600000*(long)24*(long)30)) ;
			parseLong = rest ;
		}

		if(daySpinner != null){
			int days = (int) (parseLong/(long)((long)3600000*(long)24)) ;
			daySpinner.setSelection(days);
			rest = parseLong - (long)(days*(long)((long)3600000*(long)24)) ;
			parseLong = rest ;
		}

		if(hourSpinner != null){
			int hours = (int) ((long)parseLong/(long)3600000) ;
			hourSpinner.setSelection(hours);
			rest = parseLong - (hours*3600000L) ;
			parseLong = rest ;
		}

		if(minutesSpinner != null){
			int minutes = (int) (parseLong/60000) ;
			minutesSpinner.setSelection(minutes);
			rest = parseLong - minutes*60000L ;
			parseLong = rest ;
		}

		if(secondsSpinner != null){
			int seconds = (int) (parseLong/1000);
			secondsSpinner.setSelection(seconds);
		}
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
		int days =  (int) (parseLong/(3600000*24));
		long rest = parseLong - (days*(3600000*24L)) ;
		int hours = (int) (rest/3600000) ;
		rest = rest - (hours*3600000L) ;
		int minutes = (int) (rest/60000) ;
		rest = rest - minutes*60000L ;
		int seconds = (int) (rest/1000);

		SimpleDateFormat sdf ;
		java.util.Date d = new java.util.Date();
		d.setHours(hours);
		d.setMinutes(minutes);
		d.setSeconds(seconds);

		sdf = new SimpleDateFormat("HH:mm:ss", java.util.Locale.US); //$NON-NLS-1$

		return days>0?days+" "+Messages.daysLabel+" "+sdf.format(d):sdf.format(d); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static String getDateExpressionContent(int year, int month, int day,int hours, int minutes, int seconds) {
		return "Calendar calendar = GregorianCalendar.getInstance();\n"+
				"calendar.set("+year+", "+month+","+ day+", "+hours+", "+minutes+", "+seconds+");\n"+
				"calendar.getTime();";
	}
	
	

}
