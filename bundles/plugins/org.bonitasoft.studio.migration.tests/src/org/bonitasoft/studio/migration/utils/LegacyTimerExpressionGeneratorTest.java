/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.bonitasoft.studio.model.process.builders.StartTimerEventBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class LegacyTimerExpressionGeneratorTest {

    private LegacyTimerExpressionGenerator legacyTimerExpressionGenerator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        legacyTimerExpressionGenerator = new LegacyTimerExpressionGenerator();
    }

    @Test
    public void should_isCycle_return_false() throws Exception {
        assertThat(LegacyTimerExpressionGenerator
                .isCycle(StartTimerEventBuilder.aStartTimerEvent().withScriptType(StartTimerScriptType.CONSTANT)
                        .build())).isFalse();
        assertThat(
                LegacyTimerExpressionGenerator
                        .isCycle(StartTimerEventBuilder.aStartTimerEvent().withScriptType(StartTimerScriptType.GROOVY)
                                .build())).isFalse();
    }

    @Test
    public void should_isCycle_return_true() throws Exception {
        assertThat(
                LegacyTimerExpressionGenerator
                        .isCycle(StartTimerEventBuilder.aStartTimerEvent().withScriptType(StartTimerScriptType.DAILY)
                                .build())).isTrue();
        assertThat(
                LegacyTimerExpressionGenerator
                        .isCycle(StartTimerEventBuilder.aStartTimerEvent().withScriptType(StartTimerScriptType.HOURLY)
                                .build())).isTrue();
        assertThat(
                LegacyTimerExpressionGenerator
                        .isCycle(StartTimerEventBuilder.aStartTimerEvent().withScriptType(StartTimerScriptType.MINUTELY)
                                .build())).isTrue();
        assertThat(
                LegacyTimerExpressionGenerator
                        .isCycle(StartTimerEventBuilder.aStartTimerEvent().withScriptType(StartTimerScriptType.MONTHLY)
                                .build())).isTrue();
        assertThat(
                LegacyTimerExpressionGenerator.isCycle(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.YEARLY_DAY_OF_MONTH)
                        .build())).isTrue();
        assertThat(
                LegacyTimerExpressionGenerator.isCycle(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.YEARLY_DAY_OF_YEAR)
                        .build())).isTrue();
        assertThat(
                LegacyTimerExpressionGenerator.isCycle(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.MONTHLY_DAY_NUMBER)
                        .build())).isTrue();
        assertThat(
                LegacyTimerExpressionGenerator.isCycle(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.MONTHLY_DAY_OF_WEEK)
                        .build())).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_isCycle_throw_IllegalArgumentException() throws Exception {
        LegacyTimerExpressionGenerator
                .isCycle(StartTimerEventBuilder.aStartTimerEvent().withScriptType(StartTimerScriptType.CUSTOM)
                        .build());
    }

    @Test
    public void should_generateConstant_return_a_script_with_new_Date() throws Exception {
        final Date date = new Date();
        final long time = date.getTime();
        final String generateConstant = LegacyTimerExpressionGenerator.generateConstant(date);
        assertThat(generateConstant).isEqualTo("return new Date(" + time + ");");
    }

    @Test
    public void should_getTimerExpressionContent_return_expression_content_for_GROOVY_type() throws Exception {
        final String content = "new Date()";
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.GROOVY)
                        .havingConditionExpression(ExpressionBuilder.anExpression().withContent(content))
                        .build());
        assertThat(expressionContent).isEqualTo(content);
    }

    @Test
    public void should_getTimerExpressionContent_return_null_for_GROOVY_type() throws Exception {
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.GROOVY)
                        .build());
        assertThat(expressionContent).isNull();
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_YEARLY_DAY_OF_MONTH_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.YEARLY_DAY_OF_MONTH).at(at).withDayNumber(2).withMonth(2)
                        .build());
        assertThat(expressionContent).isEqualTo("0 30 9 2 3 ?");
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_YEARLY_DAY_OF_YEAR_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.YEARLY_DAY_OF_YEAR).at(at).withDayNumber(2)
                        .build());
        assertThat(expressionContent).isEqualTo("0 30 9 2 1 ?");
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_MONTHLY_DAY_NUMBER_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.MONTHLY_DAY_NUMBER).at(at).withDayNumber(2)
                        .build());
        assertThat(expressionContent).isEqualTo("0 30 9 2 * ?");
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_MONTHLY_DAY_OF_WEEK_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.MONTHLY_DAY_OF_WEEK).at(at).withDayNumber(2).withDay(1)
                        .build());
        assertThat(expressionContent).isEqualTo("0 30 9 ? * 1#2");
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_WEEKLY_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.WEEKLY).at(at).withDay(2)
                        .build());
        assertThat(expressionContent).isEqualTo("0 30 9 ? * 2");
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_DAILY_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.DAILY).at(at).withDay(2)
                        .build());
        assertThat(expressionContent).isEqualTo("0 30 9 * * ?");
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_HOURLY_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.HOURLY).at(at).withHours(2)
                        .build());
        assertThat(expressionContent).isEqualTo("0 * */2 * * ?");
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_MINUTELY_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.MINUTELY).at(at).withMinutes(30)
                        .build());
        assertThat(expressionContent).isEqualTo("0 */30 * * * ?");
    }

    @Test
    public void should_getTimerExpressionContent_return_cron_for_CONSTANT_type() throws Exception {
        final Date at = new Date(2014, 11, 2, 9, 30);
        final long time = at.getTime();
        final String expressionContent = legacyTimerExpressionGenerator
                .getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                        .withScriptType(StartTimerScriptType.CONSTANT).at(at).withMinutes(30)
                        .build());
        assertThat(expressionContent).isEqualTo("return new Date(" + time + ");");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getTimerExpressionContent_throw_IllegalArgumentException() throws Exception {
        legacyTimerExpressionGenerator.getTimerExpressionContent(StartTimerEventBuilder.aStartTimerEvent()
                .withScriptType(StartTimerScriptType.CUSTOM)
                .build());
    }

}
