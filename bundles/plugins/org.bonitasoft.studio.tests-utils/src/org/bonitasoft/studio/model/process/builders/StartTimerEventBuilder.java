/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.process.builders;

import java.util.Date;

import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;

/**
 * @author Romain Bioteau
 */
public class StartTimerEventBuilder extends FlowElementBuilder<StartTimerEvent, StartTimerEventBuilder> {

    public static StartTimerEventBuilder aStartTimerEvent() {
        return new StartTimerEventBuilder();
    }

    public StartTimerEventBuilder withScriptType(final StartTimerScriptType type) {
        getBuiltInstance().setScriptType(type);
        return this;
    }

    public StartTimerEventBuilder havingConditionExpression(final ExpressionBuilder conditionExpression) {
        getBuiltInstance().setCondition(conditionExpression.build());
        return this;
    }

    public StartTimerEventBuilder withDay(final int day) {
        getBuiltInstance().setDay(day);
        return this;
    }

    public StartTimerEventBuilder withDayNumber(final int dayNumber) {
        getBuiltInstance().setDayNumber(dayNumber);
        return this;
    }

    public StartTimerEventBuilder from(final Date date) {
        getBuiltInstance().setFrom(date);
        return this;
    }

    public StartTimerEventBuilder withHours(final int hours) {
        getBuiltInstance().setHours(hours);
        return this;
    }

    public StartTimerEventBuilder withMinutes(final int minutes) {
        getBuiltInstance().setMinutes(minutes);
        return this;
    }

    public StartTimerEventBuilder at(final Date at) {
        getBuiltInstance().setAt(at);
        return this;
    }

    public StartTimerEventBuilder withSeconds(final int seconds) {
        getBuiltInstance().setSeconds(seconds);
        return this;
    }

    public StartTimerEventBuilder withMonth(final int month) {
        getBuiltInstance().setMonth(month);
        return this;
    }

    @Override
    protected StartTimerEvent newInstance() {
        return ProcessFactory.eINSTANCE.createStartTimerEvent();
    }

}
