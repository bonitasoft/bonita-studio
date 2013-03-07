package org.bonitasoft.studio.validators.test.swtbot;
/**

 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.IEditorReference;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aurelien Pupier
 * @author Romain Bioteau
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestCreateValidatorWizard extends SWTBotEclipseTestCase{


    /**
     * @throws JavaModelException
     * @throws InterruptedException
     * @throws OperationCanceledException
     */
    @Test
    public void testCreateValidatorOnField() throws JavaModelException, OperationCanceledException, InterruptedException{
        openNewValidatorWizardDialog();

        SWTBotShell newShell = bot.activeShell();
        SWTBot dialogBot = newShell.bot();

        String displayName = "cool displayNameTest";
        String className = "MyValidatorTestClassName1";
        String packageName = "org.boni.testme";
        dialogBot.textWithLabel("Display name *").setText(displayName);
        assertFalse(dialogBot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel("Class *").setText(className);
        assertFalse(dialogBot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel(Messages.createValidatorWizardPage_packageLabel+" *").setText(packageName);

        dialogBot.comboBox().setSelection("Field");
        
        dialogBot.button(IDialogConstants.FINISH_LABEL).click();

       // bot.waitUntil(Conditions.shellCloses(newShell), 10000);
        Matcher<IEditorReference> matcher = WidgetMatcherFactory.withPartName(className+".java");
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

        SWTBotShell newShell = bot.activeShell();
        SWTBot dialogBot = newShell.bot();
        String className = "MyValidatorTestClassName2";
        String packageName = "org.boni.testme";
        String displayName = "cool displayNameTest2";
        dialogBot.textWithLabel("Display name *").setText(displayName);
        assertFalse(dialogBot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel("Class *").setText(className);
        assertFalse(dialogBot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel(Messages.createValidatorWizardPage_packageLabel+" *").setText(packageName);

        dialogBot.comboBox().setSelection("Page");
        dialogBot.button(IDialogConstants.FINISH_LABEL).click();

        Matcher<IEditorReference> matcher = WidgetMatcherFactory.withPartName(className+".java");
        bot.waitUntil(Conditions.waitForEditor(matcher));
       // bot.waitUntil(Conditions.shellCloses(newShell), 10000);

        check(packageName, className, displayName, "IFormPageValidator");
    }




    protected void check(String packageName, String className, String displayName, String interfaceName)  throws JavaModelException, OperationCanceledException, InterruptedException {

        ValidatorDescriptorRepositoryStore store =  (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class);
        assertNotNull("The validator descriptor is not created",store.getValidatorDescriptor(packageName+"."+className));
        assertEquals("The displayName is not the good one", displayName, store.getValidatorDescriptor(packageName+"."+className).getName());

        ValidatorSourceRepositorySotre sourceStore = (ValidatorSourceRepositorySotre) RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class);

        assertNotNull("The validator class is not created", sourceStore.getChild(packageName+"."+className));
    }


    protected void openNewValidatorWizardDialog() {
        bot.menu("Development").menu("Validators").menu("New validator...").click();
        bot.waitUntil(Conditions.shellIsActive("New validator"));
    }

}

