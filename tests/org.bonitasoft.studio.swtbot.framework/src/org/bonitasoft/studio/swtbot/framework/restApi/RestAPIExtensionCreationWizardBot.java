/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.restApi;

import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotCreateExtensionProjectWizard;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class RestAPIExtensionCreationWizardBot extends BotCreateExtensionProjectWizard {

    public RestAPIExtensionCreationWizardBot(SWTGefBot bot) {
        super(bot, Messages.newRestApiExtensionTitle, false);
    }

    public RestAPIExtensionCreationWizardBot withName(String name) {
        return (RestAPIExtensionCreationWizardBot) super.withName(name);
    }

    public RestAPIExtensionCreationWizardBot withPackage(String packageName) {
        return (RestAPIExtensionCreationWizardBot) super.withPackage(packageName);
    }

    public RestAPIExtensionCreationWizardBot withProjectName(String projectName) {
        return (RestAPIExtensionCreationWizardBot) super.withProjectName(projectName);
    }

    public RestAPIExtensionCreationWizardBot withPathTemplate(String pathTemplate) {
        bot.textWithId("org.bonitasoft.studio.rest.api.extension.ui.wizard.pathTemplateText").setText(pathTemplate);
        return this;
    }

    public RestAPIExtensionCreationWizardBot nextPage() {
        next();
        return this;
    }
}
