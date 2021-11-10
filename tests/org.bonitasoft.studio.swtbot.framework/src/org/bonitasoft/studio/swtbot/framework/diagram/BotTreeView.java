/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram;

import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

/**
 * Tree view.
 * 
 * @author Joachim Segala
 */
public class BotTreeView extends BotBase {

    private final SWTBotTree overviewTree;

    public BotTreeView(final SWTGefBot bot) {
        super(bot);
        overviewTree = bot.treeWithId("bonitaOverviewTreeId");
        bot.waitUntil(Conditions.widgetIsEnabled(overviewTree));
        overviewTree.setFocus();
    }

    public void selectFirstSequenceFlow(final String pPool) {
        waitUntilPoolDisplayed(pPool);
        final SWTBotTreeItem treeItem = overviewTree.getTreeItem(pPool);
        if (!treeItem.isExpanded()) {
            treeItem.expand();
        }
        treeItem.select("Sequence Flow");
    }

    private void waitUntilPoolDisplayed(final String pPool) {
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                SWTBotTreeItem item = null;
                try {
                    item = overviewTree.getTreeItem(pPool);
                } catch (final TimeoutException e) {
                    return false;
                }
                return item != null;
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                final StringBuilder res = new StringBuilder();
                for (final SWTBotTreeItem sti : overviewTree.getAllItems()) {
                    res.append(sti.getText() + "\n");
                }
                return "Pool " + pPool + " not found in overview tree.\n" + res;
            }
        }, 10000, 500);
    }
}
