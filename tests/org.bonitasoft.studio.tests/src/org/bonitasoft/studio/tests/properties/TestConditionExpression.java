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

package org.bonitasoft.studio.tests.properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestConditionExpression implements SWTBotConstants {

    private final String poolName = "Test Conditions";
    private final String poolVersion = "1.0";
    private final String validErrorMessage = "wrong XText validation, no underline error should be displayed";
    private final String unvalidErrorMessage = "wrong XText validation, underline error should be displayed";

    private final SWTGefBot bot = new SWTGefBot();

    @Before
    public void initialiseTest() {
        new BotApplicationWorkbenchWindow(bot).createNewDiagram()
                .getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectPoolTab()
                .setName(poolName)
                .setVersion(poolVersion);
    }

    @Test
    public void testConditionExpressions() throws Exception {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);
        bot.buttonWithId(SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addNewData(bot, "myData", "Text", false, null);

        bot.buttonWithId(SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addNewData(bot, "myData2", "Boolean", false, null);

        SWTBotTestUtil.configureSequenceFlow(bot, "testCondition", poolName, false, "", ExpressionConstants.CONDITION_TYPE);
        bot.button(IDialogConstants.OK_LABEL).click();

        testValidConditionExpression("1234555<122233445", ExpressionConstants.CONDITION_TYPE);
        testValidConditionExpression("123444.345345==324234324.23423432", ExpressionConstants.CONDITION_TYPE);
        testValidConditionExpression("123.567777>=234234234324", ExpressionConstants.CONDITION_TYPE);

        testValidConditionExpression("myData<\"myString\"", ExpressionConstants.CONDITION_TYPE);

        testValidConditionExpression("!myData2", ExpressionConstants.CONDITION_TYPE);
        testValidConditionExpression("myData2", ExpressionConstants.CONDITION_TYPE);

        testUnvalidConditionExpression("myData != true", ExpressionConstants.CONDITION_TYPE, true, true);
        testUnvalidConditionExpression("myData", ExpressionConstants.CONDITION_TYPE, true, true);

        testValidConditionExpression("\"myString\"<\"myString1\"", ExpressionConstants.CONDITION_TYPE);
        testUnvalidConditionExpression("myString<\"myString1\"", ExpressionConstants.CONDITION_TYPE, true, false);
        testUnvalidConditionExpression("\"myString\"<myString1", ExpressionConstants.CONDITION_TYPE, true, true);
    }

    private void testUnvalidConditionExpression(final String condition, final String expressionType, final boolean leftError,
            final boolean rightError)
            throws OperationCanceledException, InterruptedException {
        SWTBotTestUtil.initializeComparisonExpression(bot, condition);
        final Point p1 = getLeftExpressionColumnLength(condition);
        StyleRange styles = SWTBotTestUtil.getTextStyleInEditExpressionDialog(bot, ExpressionConstants.CONDITION_TYPE, 0,
                p1.x);
        if (leftError) {
            assertTrue(unvalidErrorMessage + " expression = " + condition, styles.underline);
        } else {
            assertFalse(validErrorMessage + " expression = " + condition, styles.underline);
        }
        final Point p2 = getRighExpressionColumnLength(condition);
        bot.sleep(500);
        styles = SWTBotTestUtil.getTextStyleInEditExpressionDialog(bot, ExpressionConstants.CONDITION_TYPE, 0, p2.x);
        if (rightError) {
            assertTrue(unvalidErrorMessage + " expression = " + condition, styles.underline);
        } else {
            assertFalse(validErrorMessage + " expression = " + condition, styles.underline);
        }
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    private void testValidConditionExpression(final String condition, final String expressionType)
            throws OperationCanceledException, InterruptedException {
        SWTBotTestUtil.initializeComparisonExpression(bot, condition);
        final Point p1 = getLeftExpressionColumnLength(condition);
        StyleRange styles = SWTBotTestUtil.getTextStyleInEditExpressionDialog(bot, ExpressionConstants.CONDITION_TYPE, 0,
                p1.x);
        assertFalse(validErrorMessage, styles.underline);
        final Point p2 = getRighExpressionColumnLength(condition);
        bot.sleep(500);
        styles = SWTBotTestUtil.getTextStyleInEditExpressionDialog(bot, ExpressionConstants.CONDITION_TYPE, 0, p2.x);
        bot.button(IDialogConstants.OK_LABEL).click();
        assertFalse(validErrorMessage + " expression = " + condition, styles.underline);
    }

    private void configurePool(final String poolName, final String version) {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Pool");
        bot.button(org.bonitasoft.studio.common.Messages.edit).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        bot.textWithLabel(Messages.name).setText(poolName);
        bot.textWithLabel(Messages.version).setText(version);
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    private Point getLeftExpressionColumnLength(final String condition) {
        int operatorIndex;
        if (condition.contains(">")) {
            operatorIndex = condition.indexOf(">");
            return new Point(0, operatorIndex);
        } else if (condition.contains(">=")) {
            operatorIndex = condition.indexOf(">=");
            return new Point(0, operatorIndex);
        } else if (condition.contains("=")) {
            operatorIndex = condition.indexOf("==");
            return new Point(0, operatorIndex);
        } else if (condition.contains("!=")) {
            operatorIndex = condition.indexOf("!=");
            return new Point(0, operatorIndex);
        } else if (condition.contains("<")) {
            operatorIndex = condition.indexOf("<");
            return new Point(0, operatorIndex);
        } else if (condition.contains("<=")) {
            operatorIndex = condition.indexOf("<=");
            return new Point(0, operatorIndex);
        } else if (condition.contains("!")) {
            return new Point(1, condition.length());
        }
        return new Point(0, condition.length());
    }

    private Point getRighExpressionColumnLength(final String condition) {
        int operatorIndex;
        if (condition.contains(">")) {
            operatorIndex = condition.indexOf(">");
            return new Point(operatorIndex + 2, condition.length() - 1);
        } else if (condition.contains(">=")) {
            operatorIndex = condition.indexOf(">=");
            return new Point(operatorIndex + 3, condition.length() - 1);
        } else if (condition.contains("=")) {
            operatorIndex = condition.indexOf("==");
            return new Point(operatorIndex + 2, condition.length() - 1);
        } else if (condition.contains("!=")) {
            operatorIndex = condition.indexOf("!=");
            return new Point(operatorIndex + 3, condition.length() - 1);
        } else if (condition.contains("<")) {
            operatorIndex = condition.indexOf("<");
            return new Point(operatorIndex + 2, condition.length() - 1);
        } else if (condition.contains("<=")) {
            operatorIndex = condition.indexOf("<=");
            return new Point(operatorIndex + 3, condition.length() - 1);
        } else if (condition.contains("!")) {
            return new Point(1, condition.length());
        }
        return new Point(1, condition.length());
    }

    @After
    public void close() {
        bot.activeEditor().close();
    }
}
