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
import org.bonitasoft.studio.swtbot.framework.diagram.general.form.BotFormDiagramPropertiesViewFolder;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefFormDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Process diagram perspective.
 *
 * @author Joachim Segala
 */
public class BotProcessDiagramPerspective extends BotBase {

    public BotProcessDiagramPerspective(final SWTGefBot bot) {
        super(bot);
    }

    public void getPaletteView() {
        //TODO:
    }

    /**
     * Get the diagram properties part.
     *
     * @return
     */
    public BotProcessDiagramPropertiesViewFolder getDiagramPropertiesPart() {
        return new BotProcessDiagramPropertiesViewFolder(bot);
    }

    public BotGefProcessDiagramEditor drawDiagram() {
        return new BotGefProcessDiagramEditor(bot);
    }

    public BotTreeView getTreeViewPart() {
        final SWTBotView view = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
        view.show();
        view.setFocus();
        return new BotTreeView(bot);
    }

    public BotFormDiagramPropertiesViewFolder getFormPropertiesPart() {
        return new BotFormDiagramPropertiesViewFolder(bot);
    }

    public BotGefFormDiagramEditor drawForm() {
        bot.sleep(1000);
        return new BotGefFormDiagramEditor(bot);
    }
}
