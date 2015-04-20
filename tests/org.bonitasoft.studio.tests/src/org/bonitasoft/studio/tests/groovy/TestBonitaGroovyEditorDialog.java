/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.groovy;

import static org.bonitasoft.studio.dependencies.i18n.Messages.selectMissingJarTitle;

import java.io.IOException;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aurelien Pupier
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestBonitaGroovyEditorDialog extends SWTBotGefTestCase {

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void testOpenBonitaGroovyEditorDialog() throws ExecutionException, CoreException, IOException {
        bot.menu("Development").menu("Manage Groovy scripts...").click();
        bot.waitUntil(Conditions.shellIsActive("Manage Groovy scripts"));
        bot.tree().setFocus();
        bot.button("Create...").click();
        bot.waitUntil(Conditions.shellIsActive("Create new Groovy script"));
        bot.text().setText("MyTestScript");
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.tree().getTreeItem("MyTestScript").doubleClick();
        bot.waitUntil(Conditions.shellIsActive("Edit expression"));
        bot.styledText().setText("\"test me\"");
        bot.button("Evaluate").click();
        bot.waitUntil(Conditions.shellIsActive(selectMissingJarTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive("Evaluation results"), 60000);
        final boolean groovyEvaluateOK = "test me".equals(bot.text().getText());
        if (!groovyEvaluateOK) {
            bot.button(IDialogConstants.CANCEL_LABEL).click();
            bot.button(IDialogConstants.OK_LABEL).click();
        }
        assertTrue("Error while evaluating groovy script", groovyEvaluateOK);
        bot.button(IDialogConstants.OK_LABEL).click();
        if (!FileActionDialog.getDisablePopup()) {
            bot.button(IDialogConstants.OK_LABEL).click();
        }
        bot.button(IDialogConstants.CANCEL_LABEL);
    }

    @After
    public void closeDialog() throws Exception {
        SWTBotButton button = bot.button(IDialogConstants.CANCEL_LABEL);
        if (button.isEnabled()) {
            button.click();
        }
        button = bot.button(IDialogConstants.CANCEL_LABEL);
        if (button.isEnabled()) {
            button.click();
        }
    }

}
