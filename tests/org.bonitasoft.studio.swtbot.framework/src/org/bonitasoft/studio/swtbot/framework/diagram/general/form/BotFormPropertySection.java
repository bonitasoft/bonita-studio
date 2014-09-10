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
 * Form property section.
 * 
 * @author Joachim Segala
 */
public class BotFormPropertySection extends BotBase {

    public BotFormPropertySection(final SWTGefBot bot) {
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

}
