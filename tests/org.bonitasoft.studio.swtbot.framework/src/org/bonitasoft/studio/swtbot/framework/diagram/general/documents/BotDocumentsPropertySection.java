/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.documents;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Documents property section.
 * 
 * @author Joachim Segala
 */
public class BotDocumentsPropertySection extends BotBase {

    public BotDocumentsPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Add a new document.
     * 
     * @return
     */
    public BotAddDocumentDialog addDocument() {
        bot.button(Messages.AddSimple).click();
        return new BotAddDocumentDialog(bot);
    }

    /**
     * Edit a document.
     * 
     * @param pDocumentName
     * @return
     */
    public BotEditDocumentDialog editDocument(final String pDocumentName) {
        selectDocument(pDocumentName);
        bot.button(Messages.Edit).click();
        return new BotEditDocumentDialog(bot);
    }

    /**
     * Remove a document.
     * 
     * @param pDocumentName
     * @return
     */
    public BotRemoveDocumentDialog removeDocument(final String pDocumentName) {
        selectDocument(pDocumentName);
        bot.button(Messages.Remove).click();
        return new BotRemoveDocumentDialog(bot);
    }

    /**
     * Select a document in the list.
     * 
     * @param pDocumentName
     */
    public void selectDocument(final String pDocumentName) {
        bot.table(0).select(pDocumentName);
    }

}
