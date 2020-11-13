/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.restApi;

import java.util.Objects;

import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;

public class RestAPIExtensionCreationWizardBot extends BotWizardDialog {

    public RestAPIExtensionCreationWizardBot(SWTGefBot bot) {
        super(bot, Messages.newRestApiExtensionTitle);
    }

    public RestAPIExtensionCreationWizardBot withName(String name) {
        bot.textWithId("org.bonitasoft.studio.rest.api.extension.ui.wizard.nameText").setText(name);
        return this;
    }

    public RestAPIExtensionCreationWizardBot withPackage(String packageName) {
        bot.textWithId("org.bonitasoft.studio.rest.api.extension.ui.wizard.groupIdText").setText(packageName);
        return this;
    }

    public RestAPIExtensionCreationWizardBot withProjectName(String projectName) {
        bot.textWithId("org.bonitasoft.studio.rest.api.extension.ui.wizard.artifactIdText").setText(projectName);
        return this;
    }

    public RestAPIExtensionCreationWizardBot withPathTemplate(String pathTemplate) {
        bot.textWithId("org.bonitasoft.studio.rest.api.extension.ui.wizard.pathTemplateText").setText(pathTemplate);
        return this;
    }

    public void create() {
        SWTBotButton createButton = bot.button("Create");
        bot.waitUntil(Conditions.widgetIsEnabled(createButton));
        createButton.click();
        ICondition condition = new ConditionBuilder()
                .withTest(() -> {
                    try {
                        return Objects.equals(bot.activeEditor().getTitle(), "page.properties");
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(
                        () -> String.format("The active editor title should be  %s instead of %s", "page.properties",
                                bot.activeEditor().getTitle()))
                .create();
        //Can be long due to maven repository search
        bot.waitUntil(condition, 60000);
    }

    public RestAPIExtensionCreationWizardBot nextPage() {
        next();
        return this;
    }
}
