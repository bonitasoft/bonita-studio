/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.application.pageflow;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Add form dialog.
 * 
 * @author Joachim Segala
 */
public class BotAddFormWizardDialog extends BotWizardDialog {

    public BotAddFormWizardDialog(final SWTGefBot bot) {
        super(bot, Messages.addFormTitle);
    }

    /**
     * Set form name.
     *
     * @param pName
     */
    public void setName(final String pName) {
        bot.textWithLabel(Messages.name).setText(pName);
    }

    /**
     * Set form description.
     *
     * @param pDescription
     */
    public void setDescription(final String pDescription) {
        bot.textWithLabel(Messages.description).setText(pDescription);
    }

    /**
     * /**
     * Select Process data tab.
     */
    public BotProcessDataMappingPanel selectProcessDataTab() {
        bot.tabItem(Messages.processData).activate();
        return new BotProcessDataMappingPanel(bot);
    }

    /**
     * Select Business data tab.
     */
    public BotBusinessDataMappingPanel selectBusinessDataTab() {
        bot.tabItem(Messages.businessData).activate();
        return new BotBusinessDataMappingPanel(bot);
    }

    /**
     * Check the selectAll
     */
    public BotAddFormWizardDialog selectAll() {
        bot.checkBox(Messages.selectAll).select();
        return this;
    }

    /**
     * Return content of the Widget column of a given element
     *
     * @param dataName
     * @return
     */
    public String getDataWidget(final String dataName) {
        return bot.tree().getTreeItem(dataName).cell(1);
    }

}
