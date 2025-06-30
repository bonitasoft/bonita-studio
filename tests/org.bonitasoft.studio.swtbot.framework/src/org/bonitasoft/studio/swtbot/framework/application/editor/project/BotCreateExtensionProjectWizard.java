/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.swtbot.framework.application.editor.project;

import java.util.Objects;

import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorPart;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.wizard.NewExtensionProjectArtifactConfigurationPage;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;

public class BotCreateExtensionProjectWizard extends BotWizardDialog {

    private final boolean useNameForPropertiesFile;
    private String propertiesFileName = "page.properties";

    public BotCreateExtensionProjectWizard(SWTGefBot bot, String dialogTitle, boolean useProjectNameForPropertiesFile) {
        super(bot, dialogTitle, Messages.create);
        this.useNameForPropertiesFile = useProjectNameForPropertiesFile;
    }

    public BotCreateExtensionProjectWizard withName(String name) {
        bot.textWithId(NewExtensionProjectArtifactConfigurationPage.SWTBOT_NAME_TEXT).setText(name);
        return this;
    }

    public BotCreateExtensionProjectWizard withPackage(String packageName) {
        bot.textWithId(NewExtensionProjectArtifactConfigurationPage.SWTBOT_PACKAGE_TEXT).setText(packageName);
        return this;
    }

    public BotCreateExtensionProjectWizard withClassName(String className) {
        bot.textWithId(NewExtensionProjectArtifactConfigurationPage.SWTBOT_CLASS_NAME_TEXT).setText(className);
        return this;
    }

    public BotCreateExtensionProjectWizard withProjectName(String projectName) {
        bot.textWithId(NewExtensionProjectArtifactConfigurationPage.SWTBOT_ARTIFACT_ID_TEXT).setText(projectName);
        if (useNameForPropertiesFile) {
            propertiesFileName = projectName + ".properties";
        }
        return this;
    }

    public void create() {
        SWTBotButton createButton = bot.button("Create");
        bot.waitUntil(Conditions.widgetIsEnabled(createButton));
        createButton.click();
        ICondition condition = new ConditionBuilder()
                .withTest(() -> {
                    try {
                        return Objects.equals(bot.activeEditor().getTitle(), propertiesFileName);
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(
                        () -> String.format("The active editor title should be  %s instead of %s", propertiesFileName,
                                bot.activeEditor().getTitle()))
                .create();
        // can be long due to maven repository search
        bot.waitUntil(condition, 60000);
        // go back to Overview
        bot.editorById(ProjectOverviewEditorPart.ID).show();
    }

}
