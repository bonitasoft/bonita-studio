/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.application;

import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.BotQAUtil;
import org.bonitasoft.studio.swtbot.framework.diagram.application.pageflow.BotPageflowPropertySection;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Application Diagram properties view.
 * 
 * @author Joachim Segala
 */
public class BotApplicationDiagramPropertiesView extends BotBase {

    public BotApplicationDiagramPropertiesView(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Select the pageflow tab.
     * 
     * @return
     */
    public BotPageflowPropertySection selectPageflowTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Pageflow");
        return new BotPageflowPropertySection(bot);
    }

}
