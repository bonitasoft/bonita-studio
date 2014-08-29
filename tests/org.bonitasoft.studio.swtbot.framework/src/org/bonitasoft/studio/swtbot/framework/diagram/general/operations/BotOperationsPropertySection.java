/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.operations;

import java.util.List;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.operation.OperationViewer;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.hamcrest.Matcher;

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
        final Matcher<OperationViewer> matcher = new IsInstanceOf<OperationViewer>(OperationViewer.class);
        final List<OperationViewer> composites = bot.getFinder().findControls(matcher);
        final SWTBot localBot = new SWTBot(composites.get(pIndex));
        return new BotOperationComposite(bot, localBot);
    }
}
