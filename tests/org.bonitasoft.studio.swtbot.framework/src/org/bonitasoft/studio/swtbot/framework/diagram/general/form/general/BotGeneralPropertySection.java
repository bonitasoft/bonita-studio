/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.form.general;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.application.editor.BotHTMLEditor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * General property section.
 *
 * @author Romain Bioteau
 */
public class BotGeneralPropertySection extends BotBase {

    private static final String HTML_EDITOR_ID = "org.eclipse.wst.html.core.htmlsource.source";

    public BotGeneralPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotGeneralPropertySection setName(final String pName) {
        bot.button(org.bonitasoft.studio.common.Messages.edit).click();
        bot.textWithLabel(org.bonitasoft.studio.form.properties.i18n.Messages.name).setText(pName);
        bot.button(IDialogConstants.OK_LABEL).click();
        return this;
    }

    public BotGeneralPropertySection setFieldType(final String pType) {
        bot.comboBoxWithLabel(org.bonitasoft.studio.form.properties.i18n.Messages.formFieldType).setSelection(pType);
        return this;
    }

    public BotGeneralPropertySection setDisplayName(final String widgetLabel) {
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).setText(widgetLabel);
        return this;

    }

    public BotGeneralPropertySection useLayoutGeneratedFromDesign() {
        bot.button(Messages.Restore).click();
        return this;
    }

    public BotGeneralPropertySection clear() {
        bot.button(Messages.Clear).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.confirm_title));
        bot.button(IDialogConstants.OK_LABEL).click();
        return this;
    }

    public BotHTMLEditor editTemplate() {
        bot.button(Messages.Edit).click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return HTML_EDITOR_ID.equals(BotGeneralPropertySection.this.bot.activeEditor().getReference().getId());
            }

            @Override
            public String getFailureMessage() {
                return "HTML editor did not open. Current editor id = "
                        + BotGeneralPropertySection.this.bot.activeEditor().getReference().getId();
            }
        });
        return new BotHTMLEditor(bot);
    }

}
