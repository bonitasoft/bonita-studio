/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.draw;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

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

    public void addWidget(final String pWidget, final int pColumn, final int pRow) {
        gmfEditor.click(200, 200);
        gmfEditor.activateTool(pWidget);
        gmfEditor.click(pColumn * COLUMN_SIZE, pRow * ROW_SIZE);
    }

    public void moveWidget(final String pWidget, final int pColumn, final int pRow) {
        gmfEditor.drag(pWidget, pColumn * COLUMN_SIZE, pRow * ROW_SIZE);
    }

    public void selectWidget(final int pColumn, final int pRow) {
        gmfEditor.click(pColumn * COLUMN_SIZE, pRow * ROW_SIZE);
    }

    public SWTBotGefEditPart selectWidget(final String pWidgetName) {
        final SWTBotGefEditPart gefEditPart = gmfEditor.getEditPart(pWidgetName).parent().select();
        bot.sleep(100);
        return gefEditPart;
    }
}
