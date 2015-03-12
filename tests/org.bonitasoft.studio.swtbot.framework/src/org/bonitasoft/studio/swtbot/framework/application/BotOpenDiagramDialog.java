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
package org.bonitasoft.studio.swtbot.framework.application;

import static org.bonitasoft.studio.application.i18n.Messages.OpenProcessButtonLabel;
import static org.bonitasoft.studio.diagram.custom.i18n.Messages.openProcessWizardPage_title;

import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.widget.BotTreeWidget;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.ui.dialogs.FilteredTree;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * @author Romain Bioteau
 */
public class BotOpenDiagramDialog extends BotDialog {

    public BotOpenDiagramDialog(final SWTGefBot bot) {
        super(bot, openProcessWizardPage_title);
    }

    public BotOpenDiagramDialog searchDiagram(final String searchText) {
        bot.text().setText(searchText);
        return this;
    }

    public BotOpenDiagramDialog selectDiagram(final String diagramName, final String diagramVersion) {

        System.out.println("===============================");
        System.out.println(String.format("ACTIVE SHELL BEFORE SELECTING DIAGRAM = %s", bot.activeShell().getText()));
        try {
            bot.widget(new BaseMatcher<Widget>() {

                @Override
                public boolean matches(final Object item) {
                    System.out.println(String.format("Found widget: %s", item));
                    return item instanceof FilteredTree;
                }

                @Override
                public void describeTo(final Description description) {

                }
            });

        } catch (final WidgetNotFoundException e) {

        }
        System.out.println("===============================");
        searchDiagram(String.format("%s (%s)", diagramName, diagramVersion));
        final SWTBotTree swtBotTree = bot.treeWithId(SWTBOT_ID_OPEN_DIAGRAM_TREE_ID);
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return swtBotTree.rowCount() == 1;
            }

            @Override
            public String getFailureMessage() {
                return String.format("No diagram found with name %s (%)", diagramName, diagramVersion);
            }
        });
        swtBotTree.select(0);
        return this;
    }

    public BotOpenDiagramDialog selectDiagram(final int indexInList) {
        bot.treeWithId(SWTBOT_ID_OPEN_DIAGRAM_TREE_ID).select(indexInList);
        return this;
    }

    public BotOpenDiagramDialog delete() {
        bot.button(org.bonitasoft.studio.diagram.custom.i18n.Messages.removeProcessLabel).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.diagram.custom.i18n.Messages.confirmProcessDeleteTitle));
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(getDialogTitle()));
        bot.activeShell().setFocus();
        return this;
    }

    public BotProcessDiagramPerspective open() {
        bot.button(OpenProcessButtonLabel).click();
        return new BotProcessDiagramPerspective(bot);
    }

    public boolean isOpenEnabled() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(OpenProcessButtonLabel)), 15000);
        return bot.button(OpenProcessButtonLabel).isEnabled();
    }

    public BotTreeWidget diagramList() {
        return new BotTreeWidget(bot.tree());

    }
}
