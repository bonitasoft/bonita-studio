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
package org.bonitasoft.studio.migration.utils;

import java.util.Calendar;
import java.util.Date;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.StartTimerEvent;

/**
 * Utility that generate recusrion script for timers
 *
 * @author Baptiste Mesta
 * @author Romain Bioteau
 */
public class LegacyTimerExpressionGenerator {

    private static final String CRON_EVERY = "*/";

    public static final String GROOVY_PREFIX = "${";//$NON-NLS-1$

    public static final String GROOVY_SUFFIX = "}";//$NON-NLS-1$

    private static final String CRON_WILDCARD = "*";

    private static final String CRON_NOT_SET = "?";

    private static final String CRON_DAY_OF_MONTH = "#";


    public String getTimerExpressionContent(final StartTimerEvent item) {
        switch (item.getScriptType()) {
            case GROOVY:
                return conditionExpressionContent(item);
            case YEARLY_DAY_OF_MONTH:
                return yearlyDayOfMonthCron(item);
            case YEARLY_DAY_OF_YEAR:
                return yearlyDayOfYearCron(item);
            case MONTHLY_DAY_NUMBER:
                return monthlyDayNumberCron(item);
            case MONTHLY_DAY_OF_WEEK:
                return monthlyDayOfWeekCron(item);
            case WEEKLY:
                return weeklyCron(item);
            case DAILY:
                return dailyCron(item);
            case HOURLY:
                return generateCronExpression(CRON_WILDCARD, CRON_EVERY + item.getHours(), CRON_WILDCARD, CRON_WILDCARD, CRON_NOT_SET);
            case MINUTELY:
                return generateCronExpression(CRON_EVERY + item.getMinutes(), CRON_WILDCARD, CRON_WILDCARD, CRON_WILDCARD, CRON_NOT_SET);
            case CONSTANT:
                return generateConstant(item.getAt());
            default:
                throw new IllegalArgumentException("Timer type" + item.getScriptType() + " is not supported.");
        }
    }


    protected String dailyCron(final StartTimerEvent item) {
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTime(item.getAt());
        return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)), String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), CRON_WILDCARD,
                CRON_WILDCARD, CRON_NOT_SET);
    }


    protected String weeklyCron(final StartTimerEvent item) {
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTime(item.getAt());
        return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)), String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), CRON_NOT_SET,
                CRON_WILDCARD, String.valueOf(item.getDay()));
    }


    protected String monthlyDayOfWeekCron(final StartTimerEvent item) {
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTime(item.getAt());
        return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)),
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),
                CRON_NOT_SET,
                CRON_WILDCARD,
                item.getDay() + CRON_DAY_OF_MONTH + item.getDayNumber());
    }


    protected String monthlyDayNumberCron(final StartTimerEvent item) {
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTime(item.getAt());
        return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)),
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),
                String.valueOf(item.getDayNumber()),
                CRON_WILDCARD,
                CRON_NOT_SET);
    }


    protected String yearlyDayOfYearCron(final StartTimerEvent item) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(item.getAt());
        calendar.set(Calendar.DAY_OF_YEAR, item.getDayNumber());
        return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)),
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
                String.valueOf(calendar.get(Calendar.MONTH) + 1),
                CRON_NOT_SET);
    }


    protected String yearlyDayOfMonthCron(final StartTimerEvent item) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(item.getAt());
        return generateCronExpression(String.valueOf(calendar.get(Calendar.MINUTE)),
                String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),
                String.valueOf(item.getDayNumber()),
                String.valueOf(item.getMonth() + 1),
                CRON_NOT_SET);
    }


    protected String conditionExpressionContent(final StartTimerEvent item) {
        final Expression condition = item.getCondition();
        if(condition != null){
            return condition.getContent();
        }
        return null;
    }


    public static boolean isCycle(final StartTimerEvent item) {
        switch (item.getScriptType()) {
            case YEARLY_DAY_OF_MONTH:
            case YEARLY_DAY_OF_YEAR:
            case MONTHLY_DAY_NUMBER:
            case MONTHLY_DAY_OF_WEEK:
            case WEEKLY:
            case DAILY:
            case HOURLY:
            case MINUTELY:
            case MONTHLY:
                return true;
            case GROOVY:
            case CONSTANT:
                return false;
            default:
                throw new IllegalArgumentException("Timer type" + item.getScriptType() + " is not supported.");
        }
    }


    private String generateCronExpression(final String min, final String hour, final String dayOfMonth, final String month, final String dayOfWeek) {
        final StringBuilder sb = new StringBuilder();
        sb.append("0");
        sb.append(' ');
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


    public static String generateConstant(final Date date) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("return new Date(");
        stringBuilder.append(date.getTime());
        stringBuilder.append(");");
        return stringBuilder.toString();
    }


}
