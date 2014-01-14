/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

import java.util.Calendar;
import java.util.Date;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.eclipse.swt.SWT;

/**
 * Utility that generate recusrion script for timers
 * 
 * @author Baptiste Mesta
 */
public class TimerUtil {

    private static final String CRON_EVERY = "*/";

    public static final String GROOVY_PREFIX = "${";//$NON-NLS-1$

    public static final String GROOVY_SUFFIX = "}";//$NON-NLS-1$

    private static final String CRON_WILDCARD = "*";

    private static final String CRON_NOT_SET = "?";

    private static final String CRON_DAY_OF_MONTH = "#";

    /**
     * @param item
     * @return
     */
    public static String getScript(final StartTimerEvent item) {
        String script = "";
        switch (item.getScriptType()) {
            case GROOVY:
                Expression condition = item.getCondition();
                if(condition != null){
                    return condition.getContent();
                }else{
                    return null;
                }
            case YEARLY_DAY_OF_MONTH:
                script = generateYearlyDayOfMonth(item.getDayNumber(), item.getMonth()+1, item.getAt());
                break;
            case YEARLY_DAY_OF_YEAR:
                script = generateYearlyDayOfYear(item.getDayNumber(), item.getAt());
                break;
            case MONTHLY_DAY_NUMBER:
                script = generateMonthlyDayNumber(item.getDayNumber(), item.getAt());
                break;
            case MONTHLY_DAY_OF_WEEK:
                script = generateMonthlyDayOfWeek(item.getDay(), item.getDayNumber(), item.getAt());
                break;
            case WEEKLY:
                script = generateWeekly(item.getDay(), item.getAt());
                break;
            case DAILY:
                script = generateDaily(item.getAt());
                break;
            case HOURLY:
                script = generateHourly(item.getHours(), item.getAt());
                break;
            case MINUTELY:
                script = generateMinutely(item.getMinutes(), item.getAt());
                break;
            case CONSTANT:
                script = generateConstant(item.getAt());
                break;

            default:
                break;
        }

        return generateScriptWithFrom(script, item.getFrom());
    }

    /**
     * @param item
     * @return
     */
    public static String getTimerExpressionContent(final StartTimerEvent item) {
        Calendar calendar;
        switch (item.getScriptType()) {
            case GROOVY:
                Expression condition = item.getCondition();
                if(condition != null){
                    return condition.getContent();
                }else{
                    return null;
                }
            case YEARLY_DAY_OF_MONTH:
                calendar = Calendar.getInstance();
                calendar.setTime(item.getAt());
                return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)), String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),
                        String.valueOf(item.getDayNumber()), String.valueOf(item.getMonth()+1), CRON_NOT_SET);
            case YEARLY_DAY_OF_YEAR:
                calendar = Calendar.getInstance();
                calendar.setTime(item.getAt());
                calendar.set(Calendar.DAY_OF_YEAR, item.getDayNumber());
                return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)), String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),
                        String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), String.valueOf(calendar.get(Calendar.MONTH)+1), CRON_NOT_SET);
            case MONTHLY_DAY_NUMBER:
                calendar = Calendar.getInstance();
                calendar.setTime(item.getAt());
                return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)), String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),
                        String.valueOf(item.getDayNumber()), CRON_WILDCARD, CRON_NOT_SET);

            case MONTHLY_DAY_OF_WEEK:
                calendar = Calendar.getInstance();
                calendar.setTime(item.getAt());
                return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)), String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),
                        CRON_NOT_SET, CRON_WILDCARD, item.getDay()+CRON_DAY_OF_MONTH+item.getDayNumber());
            case WEEKLY:
                calendar = Calendar.getInstance();
                calendar.setTime(item.getAt());
                return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)), String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), CRON_NOT_SET,
                        CRON_WILDCARD, String.valueOf(item.getDay()));
            case DAILY:
                calendar = Calendar.getInstance();
                calendar.setTime(item.getAt());
                return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)), String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), CRON_WILDCARD,
                        CRON_WILDCARD, CRON_NOT_SET);
            case HOURLY:
                return generateCronExpression(CRON_WILDCARD, CRON_WILDCARD + item.getHours(), CRON_WILDCARD, CRON_WILDCARD, CRON_NOT_SET);
            case MINUTELY:
                return generateCronExpression(CRON_EVERY + item.getMinutes(), CRON_WILDCARD, CRON_WILDCARD, CRON_WILDCARD, CRON_NOT_SET);
            case CONSTANT:
                return generateConstant(item.getAt());// FIXME how to handle data constants?
            default:
                throw new RuntimeException("Timer type unknown");
        }
    }

    /**
     * @param item
     * @return
     */
    public static boolean isCycle(final StartTimerEvent item) {
        switch (item.getScriptType()) {
            case GROOVY:
                return false;
            case YEARLY_DAY_OF_MONTH:
                return true;
            case YEARLY_DAY_OF_YEAR:
                return true;
            case MONTHLY_DAY_NUMBER:
                return true;
            case MONTHLY_DAY_OF_WEEK://FIXME the engine must support that
                return true;
            case WEEKLY:
                return true;
            case DAILY:
                return true;
            case HOURLY:
                return true;
            case MINUTELY:
                return true;
            case CONSTANT:
                return false;// FIXME how to handle data constants?
            default:
                throw new RuntimeException("Timer type unknown");
        }
    }
    /**
     * @param item
     * @return
     */
    public static boolean isScript(final StartTimerEvent item) {
        switch (item.getScriptType()) {
            case GROOVY:
                return true;
            case YEARLY_DAY_OF_MONTH:
                return false;
            case YEARLY_DAY_OF_YEAR:
                return false;
            case MONTHLY_DAY_NUMBER:
                return false;
            case MONTHLY_DAY_OF_WEEK:
                return false;
            case WEEKLY:
                return false;
            case DAILY:
                return false;
            case HOURLY:
                return false;
            case MINUTELY:
                return false;
            case CONSTANT:
                return false;// FIXME how to handle data constants?
            default:
                throw new RuntimeException("Timer type unknown");
        }
    }

    /**
     * 
     * @param min
     * @param hour
     * @param dayOfMonth
     * @param month
     * @param dayOfWeek
     * @return
     */
    public static String generateCronExpression(String min, String hour, String dayOfMonth, String month, String dayOfWeek) {
        StringBuilder sb = new StringBuilder();
        sb.append("0 ");
        sb.append(min);
        sb.append(' ');
        sb.append(hour);
        sb.append(' ');
        sb.append(dayOfMonth);
        sb.append(' ');
        sb.append(month);
        sb.append(' ');
        sb.append(dayOfWeek);
        return sb.toString();

    }
    
    /**
     * @param at
     * @return
     */
    public static String generateConstant(Date at) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("return new Date(");
        stringBuilder.append(at.getTime());
        stringBuilder.append(");");
        return stringBuilder.toString();
    }

    /**
     * @param minutes
     * @param at
     * @return
     */
    public static String generateMinutely(final int minutes, final Date at) {
        final StringBuilder script = new StringBuilder();
        script.append("Calendar calendar = Calendar.getInstance();");
        script.append(SWT.CR);
        script.append("calendar.setTimeInMillis(");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append(");");
        script.append(SWT.CR);
        script.append("calendar.add(Calendar.MINUTE, ");
        script.append(minutes);
        script.append(");");
        script.append(SWT.CR);
        script.append("return calendar.getTime();");
        script.append(SWT.CR);
        return script.toString();
    }

    public static String generateWeekly(final int day, final Date at) {
        final StringBuilder script = new StringBuilder();
        script.append("Calendar calendar = Calendar.getInstance();");
        script.append(SWT.CR);
        script.append("calendar.setTimeInMillis(");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append(");");
        script.append(SWT.CR);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(at);
        script.append("calendar.set(Calendar.HOUR_OF_DAY, " + calendar.get(Calendar.HOUR_OF_DAY) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.MINUTE, " + calendar.get(Calendar.MINUTE) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.SECOND, " + calendar.get(Calendar.SECOND) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.DAY_OF_WEEK,");
        script.append(day);
        script.append(");\n");
        script.append("if(calendar.getTimeInMillis()<=");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append("){");
        script.append(SWT.CR);
        script.append("\tcalendar.add(Calendar.WEEK_OF_YEAR, 1);");
        script.append(SWT.CR);
        script.append("};");
        script.append(SWT.CR);
        script.append("return calendar.getTime();");
        script.append(SWT.CR);
        return script.toString();
    }

    /**
     * @param dayNumber
     * @param at
     * @return
     */
    public static String generateYearlyDayOfYear(final int dayNumber, final Date at) {
        final StringBuilder script = new StringBuilder();
        script.append("Calendar calendar = Calendar.getInstance();");
        script.append(SWT.CR);
        script.append("calendar.setTimeInMillis(");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append(");\n");
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(at);
        script.append("calendar.set(Calendar.HOUR_OF_DAY, " + calendar.get(Calendar.HOUR_OF_DAY) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.MINUTE, " + calendar.get(Calendar.MINUTE) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.SECOND, " + calendar.get(Calendar.SECOND) + ");");
        script.append(SWT.CR);

        script.append("calendar.set(Calendar.DAY_OF_YEAR,");
        script.append(dayNumber);
        script.append(");");
        script.append(SWT.CR);
        script.append("if(calendar.getTimeInMillis()<=");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append("){");
        script.append(SWT.CR);
        script.append("\tcalendar.add(Calendar.YEAR, 1);");
        script.append(SWT.CR);
        script.append("};");
        script.append(SWT.CR);
        script.append("return calendar.getTime();");
        script.append(SWT.CR);
        return script.toString();
    }

    /**
     * @param dayNumber
     * @param at
     * @return
     */
    public static String generateMonthlyDayNumber(final int dayNumber, final Date at) {
        final StringBuilder script = new StringBuilder();
        script.append("Calendar calendar = Calendar.getInstance();");
        script.append(SWT.CR);
        script.append("calendar.setTimeInMillis(");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append(");");
        script.append(SWT.CR);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(at);
        script.append("calendar.set(Calendar.HOUR_OF_DAY, " + calendar.get(Calendar.HOUR_OF_DAY) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.MINUTE, " + calendar.get(Calendar.MINUTE) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.SECOND, " + calendar.get(Calendar.SECOND) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.DAY_OF_MONTH, ");
        script.append(dayNumber);
        script.append(");");
        script.append(SWT.CR);
        script.append("if(calendar.getTimeInMillis()<=");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append("){");
        script.append(SWT.CR);
        script.append("\tcalendar.add(Calendar.MONTH, 1);");
        script.append(SWT.CR);
        script.append("};\n");
        script.append("return calendar.getTime();");
        script.append(SWT.CR);
        return script.toString();
    }

    /**
     * @param day
     *            day of the week
     * @param dayNumber
     *            1 = first [day of the week], 2 = second [day of the week], and
     *            so on
     * @param at
     * @return
     */
    public static String generateMonthlyDayOfWeek(final int day, final int dayNumber, final Date at) {
        final StringBuilder script = new StringBuilder();

        script.append("Calendar calendar = Calendar.getInstance();");
        script.append(SWT.CR);
        script.append("calendar.setTimeInMillis(");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append(");");
        script.append(SWT.CR);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(at);
        script.append("calendar.set(Calendar.HOUR_OF_DAY, " + calendar.get(Calendar.HOUR_OF_DAY) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.MINUTE, " + calendar.get(Calendar.MINUTE) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.SECOND, " + calendar.get(Calendar.SECOND) + ");");
        script.append(SWT.CR);

        script.append("calendar.set(Calendar.DAY_OF_WEEK, ");
        script.append(day);
        script.append(");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, ");
        script.append(dayNumber);
        script.append(");");
        script.append(SWT.CR);
        script.append("if(calendar.getTimeInMillis()<=");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append("){");
        script.append(SWT.CR);
        script.append("\tcalendar.add(Calendar.MONTH, 1);");
        script.append(SWT.CR);
        script.append("\tcalendar.set(Calendar.DAY_OF_WEEK, ");
        script.append(day);
        script.append(");");
        script.append(SWT.CR);
        script.append("\tcalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, ");
        script.append(dayNumber);
        script.append(");");
        script.append(SWT.CR);
        script.append("}");
        script.append(SWT.CR);
        script.append("return calendar.getTime();");
        return script.toString();
    }

    /**
     * @param at
     * @return
     */
    public static String generateDaily(final Date at) {
        final StringBuilder script = new StringBuilder();
        script.append("Calendar calendar = Calendar.getInstance();");
        script.append(SWT.CR);
        script.append("calendar.setTimeInMillis(");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append(");");
        script.append(SWT.CR);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(at);
        script.append("calendar.set(Calendar.HOUR_OF_DAY, " + calendar.get(Calendar.HOUR_OF_DAY) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.MINUTE, " + calendar.get(Calendar.MINUTE) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.SECOND, " + calendar.get(Calendar.SECOND) + ");");
        script.append(SWT.CR);

        script.append("if(calendar.getTimeInMillis()<=");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append("){");
        script.append(SWT.CR);
        script.append("\tcalendar.add(Calendar.DAY_OF_YEAR, 1);");
        script.append(SWT.CR);
        script.append("}");
        script.append(SWT.CR);
        script.append("return calendar.getTime();");
        script.append(SWT.CR);
        return script.toString();
    }

    /**
     * @param hours
     * @param at
     * @return
     */
    public static String generateHourly(final int hours, final Date at) {
        final StringBuilder script = new StringBuilder();
        script.append("Calendar calendar = Calendar.getInstance();");
        script.append(SWT.CR);
        //        script.append("calendar.setTimeInMillis(" + BonitaConstants.TIMER_LAST_EXECUTION + ");");
        script.append(SWT.CR);
        script.append("calendar.add(Calendar.HOUR_OF_DAY, " + hours + ");");
        script.append(SWT.CR);
        script.append("return calendar.getTime();");
        script.append(SWT.CR);
        return script.toString();
    }

    /**
     * @param dayNumber
     * @param i
     * @param at
     * @param from
     * @return
     */
    public static String generateYearlyDayOfMonth(final int dayNumber, final int month, final Date at) {
        final StringBuilder script = new StringBuilder();

        script.append("Calendar calendar = Calendar.getInstance();");
        script.append(SWT.CR);
        script.append("calendar.setTimeInMillis(");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append(");");
        script.append(SWT.CR);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(at);
        script.append("calendar.set(Calendar.HOUR_OF_DAY, " + calendar.get(Calendar.HOUR_OF_DAY) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.MINUTE, " + calendar.get(Calendar.MINUTE) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(Calendar.SECOND, " + calendar.get(Calendar.SECOND) + ");");
        script.append(SWT.CR);
        script.append("calendar.set(calendar.get(Calendar.YEAR), ");
        script.append(month);
        script.append(", ");
        script.append(dayNumber);
        script.append(");");
        script.append(SWT.CR);
        script.append("if(calendar.getTimeInMillis()<=");
        //        script.append(BonitaConstants.TIMER_LAST_EXECUTION);
        script.append("){");
        script.append(SWT.CR);
        script.append("\tcalendar.add(Calendar.YEAR, 1);");
        script.append(SWT.CR);
        script.append("};");
        script.append(SWT.CR);
        script.append("return calendar.getTime();");
        script.append(SWT.CR);
        return script.toString();
    }

    /**
     * create a script with a from date is not null
     * 
     * @param script
     * @param from
     * @return
     */
    public static String generateScriptWithFrom(String script, final Date from) {
        if (from != null) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(GROOVY_PREFIX);
            stringBuilder.append("if (");
            stringBuilder.append(from.getTime());
            stringBuilder.append("L > ");
            //            stringBuilder.append(BonitaConstants.TIMER_LAST_EXECUTION);
            stringBuilder.append("){ ");
            stringBuilder.append(SWT.CR);
            //            stringBuilder.append(BonitaConstants.TIMER_LAST_EXECUTION);
            stringBuilder.append(" = ");
            stringBuilder.append(from.getTime());
            stringBuilder.append("L;}");
            stringBuilder.append(SWT.CR);
            stringBuilder.append(script);
            stringBuilder.append(GROOVY_SUFFIX);
            script = stringBuilder.toString();
        } else {
            script = GROOVY_PREFIX + script + GROOVY_SUFFIX;
        }
        return script;
    }

    public static boolean isDuration(AbstractTimerEvent timer) {
        Expression exp = timer.getCondition();
        if(exp != null){
            return DateUtil.isDuration(exp.getContent());
        }
        return false;
    }

}
