/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.draw;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Gef form diagram editor.
 *
 * @author Joachim Segala
 */
public class BotGefFormDiagramEditor extends BotGefBaseEditor {

    private static int COLUMN_SIZE = 400;

    private static int ROW_SIZE = 100;

    public BotGefFormDiagramEditor(final SWTGefBot bot) {
        super(bot);
    }

    public BotGefFormDiagramEditor addWidget(final String pWidget, final int pColumn, final int pRow) {
        gmfEditor.click(200, 200);
        gmfEditor.activateTool(pWidget);
        gmfEditor.click(pColumn * COLUMN_SIZE, pRow * ROW_SIZE);
        return this;
    }

    public BotGefFormDiagramEditor moveWidget(final String pWidget, final int pColumn, final int pRow) {
        gmfEditor.drag(pWidget, pColumn * COLUMN_SIZE, pRow * ROW_SIZE);
        return this;
    }

    public BotGefFormDiagramEditor selectWidget(final int pColumn, final int pRow) {
        gmfEditor.click(pColumn * COLUMN_SIZE, pRow * ROW_SIZE);
        return this;
    }

    public BotGefFormDiagramEditor selectWidget(final String pWidgetName) {
        gmfEditor.getEditPart(pWidgetName).click();
        gmfEditor.getEditPart(pWidgetName).parent().select();
        bot.sleep(100);
        return this;
    }

    public BotGefFormDiagramEditor save() {
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !BotGefFormDiagramEditor.this.bot.activeEditor().isDirty();
            }

            @Override
            public String getFailureMessage() {
                return "Editor is still dirty after the save.";
            }
        });
        return this;
    }

    public BotGefFormDiagramEditor selectForm() {
        gmfEditor.mainEditPart().select();
        return this;
    }

    public void closeGefFormDiagramEditor() {
        gmfEditor.close();
    }
}
