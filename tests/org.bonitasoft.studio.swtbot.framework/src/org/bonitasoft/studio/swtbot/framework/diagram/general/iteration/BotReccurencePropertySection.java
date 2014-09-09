/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.iteration;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Recurrence property section.
 *
 * @author Romain Bioteau
 */
public class BotReccurencePropertySection extends BotBase {

    public BotReccurencePropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotNoneTypeStackPanel selectNoneType() {
        bot.radio(Messages.noneLabel).click();
        return new BotNoneTypeStackPanel(bot);
    }

    public BotStandardLoopTypeStackPanel selectStandardLoopType() {
        bot.radio(Messages.standardLoop).click();
        return new BotStandardLoopTypeStackPanel(bot);
    }

    public BotMultiInstanceTypeStackPanel selectParallelType() {
        bot.radio(Messages.parallelMultinstantition).click();
        return new BotMultiInstanceTypeStackPanel(bot);
    }

    public BotMultiInstanceTypeStackPanel selectSequentialType() {
        bot.radio(Messages.sequentialMultinstantition).click();
        return new BotMultiInstanceTypeStackPanel(bot);
    }

}
