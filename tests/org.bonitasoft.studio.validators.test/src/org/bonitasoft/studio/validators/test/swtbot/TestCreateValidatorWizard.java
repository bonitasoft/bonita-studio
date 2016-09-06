/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validators.test.swtbot;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.swtbot.framework.rule.LegacySWTGefBotRule;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.IEditorReference;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestCreateValidatorWizard {

    private SWTGefBot bot = new SWTGefBot();
    
    @Rule
    public LegacySWTGefBotRule botRule = new LegacySWTGefBotRule(bot);

    /**
     * @throws JavaModelException
     * @throws InterruptedException
     * @throws OperationCanceledException
     */
    @Test
    public void testCreateValidatorOnField() throws JavaModelException, OperationCanceledException, InterruptedException{
        openNewValidatorWizardDialog();

        final SWTBotShell newShell = bot.activeShell();
        final SWTBot dialogBot = newShell.bot();

        final String displayName = "cool displayNameTest";
        final String className = "MyValidatorTestClassName1";
        final String packageName = "org.boni.testme";
        dialogBot.textWithLabel("Display name *").setText(displayName);
        assertFalse(dialogBot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel("Class *").setText(className);
        assertFalse(dialogBot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel(Messages.createValidatorWizardPage_packageLabel+" *").setText(packageName);

        dialogBot.comboBox().setSelection("Field");

        dialogBot.button(IDialogConstants.FINISH_LABEL).click();

       // bot.waitUntil(Conditions.shellCloses(newShell), 10000);
        final Matcher<IEditorReference> matcher = WidgetMatcherFactory.withPartName(className+".java");
        bot.waitUntil(Conditions.waitForEditor(matcher));

        check(packageName, className, displayName, "IFormFieldValidator");
    }

    /**
     * @throws JavaModelException
     * @throws InterruptedException
     * @throws OperationCanceledException
     */
    @Test
    public void testCreateValidatorOnForm() throws JavaModelException, OperationCanceledException, InterruptedException{
        openNewValidatorWizardDialog();

        final SWTBotShell newShell = bot.activeShell();
        final SWTBot dialogBot = newShell.bot();
        final String className = "MyValidatorTestClassName2";
        final String packageName = "org.boni.testme";
        final String displayName = "cool displayNameTest2";
        dialogBot.textWithLabel("Display name *").setText(displayName);
        assertFalse(dialogBot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel("Class *").setText(className);
        assertFalse(dialogBot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel(Messages.createValidatorWizardPage_packageLabel+" *").setText(packageName);

        dialogBot.comboBox().setSelection("Page");
        dialogBot.button(IDialogConstants.FINISH_LABEL).click();

        final Matcher<IEditorReference> matcher = WidgetMatcherFactory.withPartName(className+".java");
        bot.waitUntil(Conditions.waitForEditor(matcher));
       // bot.waitUntil(Conditions.shellCloses(newShell), 10000);

        check(packageName, className, displayName, "IFormPageValidator");
    }




    protected void check(final String packageName, final String className, final String displayName, final String interfaceName)  throws JavaModelException, OperationCanceledException, InterruptedException {

        final ValidatorDescriptorRepositoryStore store =  RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class);
        assertNotNull("The validator descriptor is not created",store.getValidatorDescriptor(packageName+"."+className));
        assertEquals("The displayName is not the good one", displayName, store.getValidatorDescriptor(packageName+"."+className).getName());

        final ValidatorSourceRepositorySotre sourceStore = RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class);

        assertNotNull("The validator class is not created", sourceStore.getChild(packageName+"."+className));
    }


    protected void openNewValidatorWizardDialog() {
        bot.menu("Development").menu("6.x Validators").menu("New validator...").click();
        bot.waitUntil(Conditions.shellIsActive("New validator"));
    }

}

