/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.composite;

import java.util.List;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.properties.form.sections.actions.contributions.InitialValueComposite;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.hamcrest.Matcher;

/**
 * One operation row.
 *
 * @author Florine Boudin
 */
public class BotDocInitialValueComposite extends BotBase {

    private final SWTBot localBot;

    public BotDocInitialValueComposite(final SWTGefBot bot) {
        super(bot);
        final Matcher<InitialValueComposite> matcher = new IsInstanceOf<InitialValueComposite>(InitialValueComposite.class);
        final List<InitialValueComposite> initComposites = bot.getFinder().findControls(matcher);
        localBot = new SWTBot(initComposites.get(0));
    }

    public String getInitialValue() {
        return localBot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).getText();
    }

}
