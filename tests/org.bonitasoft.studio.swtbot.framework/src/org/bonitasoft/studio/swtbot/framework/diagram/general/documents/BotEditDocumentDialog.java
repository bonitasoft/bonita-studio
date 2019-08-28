/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.documents;

import org.bonitasoft.studio.document.i18n.Messages;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Edit document dialog.
 *
 * @author Joachim Segala
 */
public class BotEditDocumentDialog extends BotAddDocumentDialog {

    public BotEditDocumentDialog(final SWTGefBot bot) {
        super(bot, Messages.editDocument);
    }

    /**
     * Get the document name.
     *
     * @return
     */
    public String getName() {
        return bot.textWithLabel(Messages.name + " *").getText();
    }

    /**
     * Get the document desciption.
     *
     * @return
     */
    public String getDescription() {
        return bot.textWithLabel(Messages.description).getText();
    }
}
