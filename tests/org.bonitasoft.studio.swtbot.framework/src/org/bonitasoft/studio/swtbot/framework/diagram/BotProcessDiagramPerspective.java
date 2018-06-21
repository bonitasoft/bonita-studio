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
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefFormDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Process diagram perspective.
 *
 * @author Joachim Segala
 */
public class BotProcessDiagramPerspective extends BotBase {

    public static String FORM_ID = "org.bonitasoft.studio.diagram.form.custom.part.CustomFormDiagram";
    public static String FORM_EX_ID = "org.bonitasoft.studio.diagram.form.custom.ex.part.FormDiagramEditorEx";

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

    public BotGefProcessDiagramEditor activeProcessDiagramEditor() {
        return new BotGefProcessDiagramEditor(bot);
    }

    public BotTreeView getTreeViewPart() {
        final SWTBotView view = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
        view.show();
        view.setFocus();
        return new BotTreeView(bot);
    }

    public BotGefFormDiagramEditor activeFormDiagramEditor() {
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                final String id = BotProcessDiagramPerspective.this.bot.activeEditor().getReference().getId();
                return FORM_ID.equals(id) || FORM_EX_ID.equals(id);
            }

            @Override
            public String getFailureMessage() {
                return "No active form editor. Active editor id = "
                        + BotProcessDiagramPerspective.this.bot.activeEditor().getReference().getId();
            }
        });
        return new BotGefFormDiagramEditor(bot);
    }

    public void closeActiveDiagram() {
        BotProcessDiagramPerspective.this.bot.activeEditor().close();
    }

}
