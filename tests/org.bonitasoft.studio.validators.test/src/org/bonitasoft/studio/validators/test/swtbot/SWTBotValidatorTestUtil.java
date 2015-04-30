package org.bonitasoft.studio.validators.test.swtbot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.IEditorReference;
import org.hamcrest.Matcher;

/**
 * @author aurelie Zara
 */
public class SWTBotValidatorTestUtil {

    /**
     * open the New validator wizard Dialog (menu: Development>Validators>New validator...)
     * 
     * @param bot
     */
    public static void openNewValidatorWizardDialog(final SWTBot bot) {
        bot.menu("Development").menu("6.x Validators").menu("New validator...").click();
        bot.waitUntil(Conditions.shellIsActive("New validator"));
    }

    /**
     * open the validator wizard and create an on page validator whith displayName, packageName and className as paramaters.
     * (menu>Development>Validators>New validators.
     * 
     * @param bot
     * @param className
     * @param packageName
     * @param displayName
     * @throws JavaModelException
     * @throws OperationCanceledException
     * @throws InterruptedException
     */
    public static void createValidatorOnField(final SWTBot bot, final String displayName, final String className, final String packageName)
            throws JavaModelException, OperationCanceledException, InterruptedException {
        openNewValidatorWizardDialog(bot);
        final SWTBotShell newShell = bot.activeShell();
        final SWTBot dialogBot = newShell.bot();
        dialogBot.textWithLabel("Display name *").setText(displayName);
        org.junit.Assert.assertFalse("button Finish should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel("Class *").setText(className);
        org.junit.Assert.assertFalse("button Finish should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel(Messages.createValidatorWizardPage_packageLabel + " *").setText(packageName);
        dialogBot.comboBox().setSelection("Field");
        dialogBot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(newShell), 10000);
        final Matcher<IEditorReference> matcher = WidgetMatcherFactory.withPartName(className + ".java");
        bot.waitUntil(Conditions.waitForEditor(matcher));
        check(packageName, className, displayName, "IFormFieldValidator");
    }

    /**
     * open the validator wizard and create an on field validator whith displayName, packageName and className as paramaters.
     * (menu>Development>Validators>New validators.
     * 
     * @param bot
     * @param className
     * @param packageName
     * @param displayName
     * @throws JavaModelException
     * @throws OperationCanceledException
     * @throws InterruptedException
     */
    public static void createValidatorOnPage(final SWTBot bot, final String className, final String packageName, final String displayName)
            throws JavaModelException, OperationCanceledException, InterruptedException {
        openNewValidatorWizardDialog(bot);
        final SWTBotShell newShell = bot.activeShell();
        final SWTBot dialogBot = newShell.bot();
        dialogBot.textWithLabel("Display name *").setText(displayName);
        assertFalse("button Finish should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel("Class *").setText(className);
        assertFalse("button Finish should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        dialogBot.textWithLabel(Messages.createValidatorWizardPage_packageLabel + " *").setText(packageName);
        dialogBot.comboBox().setSelection("Page");
        dialogBot.button(IDialogConstants.FINISH_LABEL).click();
        final Matcher<IEditorReference> matcher = WidgetMatcherFactory.withPartName(className + ".java");
        bot.waitUntil(Conditions.waitForEditor(matcher));
        bot.waitUntil(Conditions.shellCloses(newShell), 10000);
        check(packageName, className, displayName, "IFormPageValidator");
    }

    /**
     * Diagram should have been created with "new diagram", "Step1" should exist, and the focus should be on the diagram editor
     * 
     * @param bot
     * @param validatorType
     */
    public static void addValidatorOnPage(final SWTGefBot bot, final String validatorType) {
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Validators");
        bot.button("Add...").click();
        bot.comboBoxWithLabel("Validator type *").setSelection(validatorType);
        bot.textWithLabel("Error message *").setText("error");
        bot.sleep(1000);
    }

    /**
     * Diagram should have been created with "new diagram", "Step1" should exist, and the focus should be on the diagram editor
     * 
     * @param bot
     * @param validatorType
     */
    public static void addValidatorOnField(final SWTGefBot bot, final String validatorType) {
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
        gmfEditor.activateTool("Text area");
        gmfEditor.click(200, 200);
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Validators");
        bot.button("Add...").click();
        bot.comboBoxWithLabel("Validator type *").setSelection(validatorType);
        bot.textWithLabel("Error message *").setText("error");
        bot.sleep(1000);
    }

    private static void check(final String packageName, final String className, final String displayName, final String interfaceName)
            throws JavaModelException, OperationCanceledException, InterruptedException {
        final ValidatorDescriptorRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class);
        assertNotNull("The validator descriptor is not created", store.getValidatorDescriptor(packageName + "." + className));
        assertEquals("The displayName is not the good one", displayName, store.getValidatorDescriptor(packageName + "." + className).getName());
        final ValidatorSourceRepositorySotre sourceStore = RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class);
        assertNotNull("The validator class is not created", sourceStore.getChild(packageName + "." + className));
    }
}
