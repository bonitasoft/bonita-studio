/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.documents;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.StudioAPIUtil;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.swtbot.framework.expression.BotScriptExpressionEditor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Add document dialog.
 *
 * @author Joachim Segala
 */
public class BotAddDocumentDialog extends BotWizardDialog {

    public BotAddDocumentDialog(final SWTGefBot bot) {
        super(bot, Messages.newDocument);
    }

    protected BotAddDocumentDialog(final SWTGefBot bot, final String dialogTitle) {
        super(bot, dialogTitle);
    }

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
     * SINGLE CONTENT
     */

    /**
     * Choose "Single" radio button
     */
    public void chooseSingleContent() {
        bot.radio(Messages.radioButtonSingle).click();
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
     * Choose "From Bonita" radio button.
     */
    public void chooseInternalInitialContent() {
        bot.radio(Messages.initialValueButtonInternal).click();
    }

    public void chooseScriptInitialContent() {
        bot.radio(Messages.initialValueButtonScript).click();
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
     * get URL expressionEditor.
     *
     * @param pURL
     */
    public void setURLWithExpressionEditor(final String pURL) {
        bot.textWithId(ExpressionViewer.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).setText(pURL);
    }

    /**
     * MULTIPLE CONTENT
     */

    /**
     * Choose "Multiple" radio button
     */
    public void chooseMultipleContent() {
        bot.radio(Messages.radioButtonMultiple).click();
    }

    /**
     * MIME TYPE.
     */

    /**
     * Set Initial Contents script of a multiple document
     *
     * @param scriptName
     * @param initialContentsScript
     */
    public void setInitialContents(final String scriptName, final String initialContentsScript) {
    	SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        final BotExpressionEditorDialog expressionDialog = new BotExpressionEditorDialog(bot,activeShell);
        final BotScriptExpressionEditor scriptEditor = new BotScriptExpressionEditor(bot, expressionDialog);
        scriptEditor.setName(scriptName);
        scriptEditor.setScriptContent(initialContentsScript);
        scriptEditor.ok();
    }

    /**
     * Manage MIME type.
     */
    public void showMimeTypeField() {
        bot.link("<A>" + Messages.manageMimeType + "</A>").click();
    }

    /**
     * Hide MIME type field.
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
     * Is MIME Type field
     */

    public boolean isMymeTypeFieldEnabled() {
        return bot.link("<A>" + Messages.manageMimeType + "</A>").isEnabled();
    }

    /**
     * VALIDATION
     */

    /**
     * Return true if URL error message is present.
     *
     * @return
     */
    public boolean hasErrorMessageUrl() {
        return hasErrorMessage(Messages.error_documentURLEmpty);
    }

    /**
     * Return true if File error message is present.
     *
     * @return
     */
    public boolean hasErrorMessageFile() {
        return hasErrorMessage(Messages.error_documentDefaultIDEmpty);
    }

    /**
     * Return true if name empty error message is present.
     *
     * @return
     */
    public boolean isErrorMessageNameEmpty() {
        //FIXME: hard coded value.
        return hasErrorMessage("Name is mandatory");
    }

    /**
     * Return true if error message for already exist document is visible.
     *
     * @return
     */
    public boolean isErrorMessageAlreadyExist(final String docName) {
        return hasErrorMessage(NLS.bind(org.bonitasoft.studio.common.Messages.unicityErrorMessage, docName));
    }

    /**
     * BUTTON BEHAVIOR
     */

    public boolean isFinishEnabled() {
        return bot.button(IDialogConstants.FINISH_LABEL).isEnabled();
    }

    public boolean isFinishAndAddEnabled() {
        return bot.button(org.bonitasoft.studio.common.Messages.createAndNewButton).isEnabled();
    }

    public BotFileStoreSelectDialog browseInternalFile() {
        assertThat(bot.radio(Messages.initialValueButtonInternal).isSelected()).isTrue();
        bot.button(Messages.Browse).click();
        return new BotFileStoreSelectDialog(bot);
    }

    /**
     * Click on Finish && Add
     */
    public BotAddDocumentDialog finishAndAdd() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(org.bonitasoft.studio.common.Messages.createAndNewButton)),
                5000);
        bot.button(org.bonitasoft.studio.common.Messages.createAndNewButton).click();
        return new BotAddDocumentDialog(bot);
    }

    /**
     * @return
     */
    public boolean isInitialContentEmpty() {
        bot.shell(Messages.newDocument).setFocus();
        return bot.textWithLabel(Messages.documentInternalLabel + " *").getText().isEmpty();
    }

    public boolean hasNoValidationError() {
        return hasTextVisible(Messages.newDocumentWizardDescription) || hasTextVisible(Messages.editDocumentDescription);
    }

    public boolean canFinish() {
        return isFinishEnabled() && isFinishAndAddEnabled();
    }
}
