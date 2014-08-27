/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.parameters;

import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Parameter property section.
 * 
 * @author Joachim Segala
 */
public class BotParametersPropertySection extends BotBase {

    public BotParametersPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Add a new parameter.
     * 
     * @return
     */
    public BotAddParameterDialog addParameter() {
        bot.button(Messages.addData).click();
        return new BotAddParameterDialog(bot);
    }
}
