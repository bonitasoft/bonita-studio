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
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.StudioAPIUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;

/**
 * Add document dialog.
 * 
 * @author Joachim Segala
 */
public class BotAddDocumentDialog extends BotWizardDialog {

    public BotAddDocumentDialog(final SWTGefBot bot) {
        super(bot);
    }

    @Override
    protected void waitShell() {
        bot.waitUntil(Conditions.shellIsActive(Messages.newDocument));
        bot.shell(Messages.newDocument);
    };

    /**
     * Set name of document.
     * 
     * @param pName
     */
    public void setName(final String pName) {
        bot.textWithLabel(Messages.name + " *").setText(pName);
    }

    /**
     * Set description of document.
     * 
     * @param pDescription
     */
    public void setDescription(final String pDescription) {
        bot.textWithLabel(Messages.description).setText(pDescription);
    }

    /**
     * INITIAL CONTENT.
     */

    /**
     * Choose "None" radio button.
     */
    public void chooseNoneInitialContent() {
        bot.radio(Messages.initialValueButtonNone).click();
    }

    /**
     * Choose "From Bonita BPM" radio button.
     */
    public void chooseInternalInitialContent() {
        bot.radio(Messages.initialValueButtonInternal).click();
    }

    /**
     * Set File.
     * 
     * @param pFileName
     * @see StudioAPIUtil.importDocumentInBonitaStudioRepository
     */
    public void setFile(final String pFileName) {
        bot.textWithLabel(Messages.documentInternalLabel + " *").setText(pFileName);
    }

    /**
     * Choose "From an external system" radio button.
     */
    public void chooseExternalInitialContent() {
        bot.radio(Messages.initialValueButtonExternal).click();
    }

    /**
     * Set URL.
     * 
     * @param pURL
     */
    public void setURL(final String pURL) {
        bot.textWithLabel(Messages.documentExternalLabel + " *").setText(pURL);
    }

    /**
     * MIME TYPE.
     */

    /**
     * Manage MIME type.
     */
    public void showMimeTypeField() {
        bot.link("<A>" + Messages.manageMimeType + "</A>").click();
    }

    /**
     * Hide MIME type filed.
     */
    public void hideMimeTypeField() {
        bot.link("<A>" + Messages.hideMimeType + "</A>").click();
    }

    /**
     * Set MIME type.
     * 
     * @param pMimeType
     */
    public void setMimeType(final String pMimeType) {
        bot.textWithLabel(Messages.mimeType).setText(pMimeType);
    }

    /**
     * VALIDATION
     */

    /**
     * Return true if URL error message is present.
     * 
     * @return
     */
    public boolean isErrorMessageUrl() {
        return isErrorMessage(Messages.error_documentURLEmpty);
    }

    /**
     * Return true if File error message is present.
     * 
     * @return
     */
    public boolean isErrorMessageFile() {
        return isErrorMessage(Messages.error_documentDefaultIDEmpty);
    }

    /**
     * Return true if name empty error message is present.
     * 
     * @return
     */
    public boolean isErrorMessageNameEmpty() {
        return isErrorMessage(Messages.error_empty);
    }

    /**
     * Return true if error message for already exist document is visible.
     * 
     * @return
     */
    public boolean isErrorMessageAlreadyExist() {
        return isErrorMessage(Messages.error_documentAllreadyexist);
    }
}