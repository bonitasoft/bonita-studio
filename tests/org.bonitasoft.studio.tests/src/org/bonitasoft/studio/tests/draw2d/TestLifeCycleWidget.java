/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.draw2d;

import static org.junit.Assert.assertEquals;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.widgets.EventCircle;
import org.bonitasoft.studio.common.widgets.LifeCycleWidget;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestLifeCycleWidget {

    private final SWTGefBot bot = new SWTGefBot();
    LifeCycleWidget lcw;

    @Test
    public void testTaskOnEnterSelectionEvent() {
        final String eventType = ConnectorEvent.ON_ENTER.toString();
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_ENTER;
        final int taskLifeCycle = LifeCycleWidget.TASK_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    @Test
    public void testTaskDefaultSelectionEvent() {
        final String eventType = null;
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_FINISH;
        final int taskLifeCycle = LifeCycleWidget.TASK_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    @Test
    public void testTaskOnFinishSelectionEvent() {
        final String eventType = ConnectorEvent.ON_FINISH.toString();
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_FINISH;
        final int taskLifeCycle = LifeCycleWidget.TASK_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    @Test
    public void testProcessOnFinishSelectionEvent() {
        final String eventType = ConnectorEvent.ON_FINISH.toString();
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_FINISH;
        final int taskLifeCycle = LifeCycleWidget.PROCESS_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    @Test
    public void testProcessOnStartSelectionEvent() {
        final String eventType = ConnectorEvent.ON_ENTER.toString();
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_ENTER;
        final int taskLifeCycle = LifeCycleWidget.PROCESS_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    @Test
    public void testProcessOnDefaultSelectionEvent() {
        final String eventType = null;
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_FINISH;
        final int taskLifeCycle = LifeCycleWidget.PROCESS_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    @Test
    public void testActivityOnReadySelectionEvent() {
        final String eventType = ConnectorEvent.ON_ENTER.toString();
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_ENTER;
        final int taskLifeCycle = LifeCycleWidget.TASK_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    @Test
    public void testActivityDefaultSelectionEvent() {
        final String eventType = null;
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_FINISH;
        final int taskLifeCycle = LifeCycleWidget.TASK_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    @Test
    public void testActivityOnFinishSelectionEvent() {
        final String eventType = ConnectorEvent.ON_FINISH.toString();
        final ConnectorEvent eventTypeThatShouldBeselected = ConnectorEvent.ON_FINISH;
        final int taskLifeCycle = LifeCycleWidget.TASK_LIFE_CYCLE;

        testSelectionEvent(eventType, eventTypeThatShouldBeselected, taskLifeCycle);
    }

    Dialog dialog;

    private void testSelectionEvent(final String eventType,
            final ConnectorEvent eventTypeThatShouldBeselected, final int taskLifeCycle) {

        UIThreadRunnable.syncExec(new VoidResult() {

            @Override
            public void run() {
                final Dialog dialog = new Dialog(Display.getDefault().getActiveShell()) {

                    @Override
                    protected Control createDialogArea(Composite parent) {
                        final Control superParent = super.createDialogArea(parent);

                        lcw = new LifeCycleWidget(parent, eventType, null);
                        return superParent;
                    }

                    @Override
                    protected void configureShell(Shell newShell) {
                        super.configureShell(newShell);
                        newShell.setText(
                                "Test Life cycle widget: " + eventType + eventTypeThatShouldBeselected + taskLifeCycle);
                    }
                };
                dialog.setBlockOnOpen(false);
                dialog.open();

            }
        });

        bot.waitUntil(Conditions.shellIsActive(
                "Test Life cycle widget: " + eventType + eventTypeThatShouldBeselected + taskLifeCycle), 10000);
        bot.button(IDialogConstants.CANCEL_LABEL).click();

        final String eventTypeTheoric = eventTypeThatShouldBeselected.toString();
        for (final EventCircle eventCircle : lcw.getEventFigures()) {
            final String event = eventCircle.getEvent();
            if (event.equals(eventTypeTheoric)) {
                assertEquals("The event circle" + event + " should be selected",
                        eventCircle.getLocalForegroundColor().getRed(), 73);
            } else {
                assertEquals("The event circle" + event + " should not be selected",
                        eventCircle.getLocalForegroundColor().getRed(), 235);
            }
        }

        bot.waitUntil(Conditions.shellIsActive("Bonita Studio"));

    }
}
