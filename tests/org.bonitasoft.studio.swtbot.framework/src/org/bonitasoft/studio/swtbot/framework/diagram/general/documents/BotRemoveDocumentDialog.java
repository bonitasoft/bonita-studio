/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.documents;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Remove document dialog.
 *
 * @author Joachim Segala
 */
public class BotRemoveDocumentDialog extends BotDialog {

    public BotRemoveDocumentDialog(final SWTGefBot bot) {
        super(bot, Messages.removalConfirmationDialogTitle);
    }
}
