/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.form;

import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Diagram properties view folder.
 *
 * @author Joachim Segala
 */
public class BotFormDiagramPropertiesViewFolder extends BotBase {

    public BotFormDiagramPropertiesViewFolder(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Select the general tab.
     *
     * @return
     */
    public BotGeneralFormPropertiesView selectGeneralTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
        return new BotGeneralFormPropertiesView(bot);
    }


    public void selectAppearanceTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPEARANCE).show();
        //TODO:
    }


    public void selectValidationStatusTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_VALIDATION_STATUS).show();
        //TODO:
    }

    public void selectPreviewTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PREVIEW).show();
        //TODO:
    }
}
