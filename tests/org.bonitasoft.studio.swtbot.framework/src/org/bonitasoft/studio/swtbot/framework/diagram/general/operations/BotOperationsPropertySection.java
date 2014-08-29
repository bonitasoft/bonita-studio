/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.operations;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.composite.BotOperationComposite;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Operation property section.
 * 
 * @author Joachim Segala
 */
public class BotOperationsPropertySection extends BotBase {

    public BotOperationsPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public void addOperation() {
        bot.button(Messages.addAction).click();
    }

    public BotOperationComposite getOperation(final int pIndex) {
        return new BotOperationComposite(bot, pIndex);
    }
}
