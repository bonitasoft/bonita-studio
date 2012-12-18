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

import java.util.Calendar;
import java.util.Date;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;

/**
 * @author Romain Bioteau
 *
 */
public class TimerEventLabelProvider  {


	public static String getText(Object element) {
		if(element instanceof AbstractTimerEvent){
			AbstractTimerEvent event = (AbstractTimerEvent) element ;
			
			
			if(event instanceof StartTimerEvent){
				StartTimerEvent startTimer = (StartTimerEvent) event;
				if (startTimer.getScriptType() != null) {
					switch (startTimer.getScriptType()) {
					case YEARLY_DAY_OF_MONTH:
						return Messages.StartTimerCondition_every + " " + startTimer.getDayNumber() + " " + Messages.StartTimerCondition_of + " "
						+ getMonth(startTimer.getMonth());
					case YEARLY_DAY_OF_YEAR:
						return Messages.StartTimerCondition_every + " " + startTimer.getDayNumber() + " " + Messages.StartTimerCondition_dayOfTheYear;
					case MONTHLY_DAY_NUMBER:
						return Messages.StartTimerCondition_every + " " + startTimer.getDayNumber() + " " + Messages.StartTimerCondition_ofEachMonth;
					case MONTHLY_DAY_OF_WEEK:
						return Messages.StartTimerCondition_every + " " + startTimer.getDayNumber() + " " + getDay(startTimer.getDay()) + " "
						+ Messages.StartTimerCondition_ofEachMonth;
					case WEEKLY:
						return Messages.StartTimerCondition_every + " "+  getDay(startTimer.getDay()) + " "
						+ Messages.StartTimerCondition_ofEachWeek;
					case DAILY:
						Date at = startTimer.getAt();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(at);
						return Messages.StartTimerCondition_everyDayAt + " "+ calendar.get(Calendar.HOUR_OF_DAY)+":"+ calendar.get(Calendar.MINUTE)+":"+ calendar.get(Calendar.SECOND);
					case HOURLY:
						return Messages.StartTimerCondition_every + " "+ startTimer.getHours() + " " +Messages.StartTimerCondition_hours;
					case MINUTELY:
						return Messages.StartTimerCondition_every + " "+ startTimer.getMinutes() + " " +Messages.StartTimerCondition_minutes;
					case CONSTANT:
						return Messages.StartTimerCondition_at + " "+ startTimer.getAt();
					default:
						break;
					}
				}
			}
			
			Expression condition = event.getCondition();
			if(condition != null && condition.getContent() != null && !condition.getContent().isEmpty()){
			    String content = condition.getContent();
				if(ExpressionConstants.CONSTANT_TYPE.equals(condition.getType())) {
                    if (DateUtil.isDuration(content)) {
                    	return DateUtil.getDisplayDuration(content) ;
                    }else if(DateUtil.isDate(content)){
                        return DateUtil.getDisplayDate(content) ;
                    }
                }
				
				if(ExpressionConstants.SCRIPT_TYPE.equals(condition.getType())){
					return event.getName();
				}	
			}
			return event.getName() ;
		}
		return "" ;
	}

	/**
	 * @param day
	 * @return
	 */
	private static String getDay(int day) {
		switch (day) {
		case 1:
			return Messages.sunday;
		case 2:
			return Messages.monday;
		case 3:
			return Messages.tuesday;
		case 4:
			return Messages.wednesday;
		case 5:
			return Messages.thursday;
		case 6:
			return Messages.friday;
		case 7:
			return Messages.saturday;
		}
		return "";
	}

	/**
	 * @param month
	 * @return
	 */
	private static String getMonth(int month) {
		switch (month) {
		case 0:
			return Messages.january;
		case 1:
			return Messages.february;
		case 2:
			return Messages.march;
		case 3:
			return Messages.april;
		case 4:
			return Messages.may;
		case 5:
			return Messages.june;
		case 6:
			return Messages.july;
		case 7:
			return Messages.august;
		case 8:
			return Messages.september;
		case 9:
			return Messages.october;
		case 10:
			return Messages.november;
		case 11:
			return Messages.december;
		}
		return "";
	}


}
