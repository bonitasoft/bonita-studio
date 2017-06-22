package org.bonitasoft.studio.diagram.test;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withMnemonic;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.Command;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSave {

    private final SWTGefBot bot = new SWTGefBot();

    // Before and After
    private static boolean disablePopup;

    @BeforeClass
    public static void setUpBeforeClass() {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        FileActionDialog.setDisablePopup(disablePopup);
    }

    private final ICondition saveMenuDisabled = new ICondition() {

        @Override
        public boolean test() throws Exception {
            return !bot.menu("File").click().menu("Save").isEnabled();
        }

        @Override
        public void init(final SWTBot bot) {

        }

        @Override
        public String getFailureMessage() {
            return "Save menu is still enabled !";
        }
    };
    private final ICondition saveMenuEnabled = new ICondition() {

        @Override
        public boolean test() throws Exception {
            return bot.menu("File").menu("Save").isEnabled();
        }

        @Override
        public void init(final SWTBot bot) {

        }

        @Override
        public String getFailureMessage() {
            return "Save menu is still disabled !";
        }
    };

    @After
    public void tearDown() throws Exception {
        bot.saveAllEditors();
    }

    final static String SAVE_BUTTON_TEXT = "Save";
    final static String WELCOME_EDITOR_TEXT = "Welcome to Bonita Studio";
    private static final String SAVE_COMMAND_ID = "org.eclipse.ui.file.save";

    @Test
    public void testSaveButtonAndMenuNotEnableAtInitiationAndEnableAfterChange() {

        boolean isSaveButtonEnable = true;

        // When opening Bonita soft
        final Matcher<MenuItem> matcher = withMnemonic(SAVE_BUTTON_TEXT);
        bot.waitUntil(Conditions.waitForMenu(bot.activeShell(), matcher));

        // test button
        isSaveButtonEnable = bot.toolbarButton(SAVE_BUTTON_TEXT).isEnabled();
        Assert.assertFalse("Error: Save button must be disabled when opening Bonita Studio.", isSaveButtonEnable);

        // test menu
        bot.waitUntil(saveMenuDisabled, 5000, 500);

        // When Creating a new Diagram
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        final List<SWTBotGefEditPart> runnableEPs = gmfEditor.editParts(new BaseMatcher<EditPart>() {

            @Override
            public boolean matches(final Object item) {
                return item instanceof PoolEditPart;
            }

            @Override
            public void describeTo(final Description description) {

            }
        });
        Assert.assertFalse(runnableEPs.isEmpty());
        gmfEditor.select(runnableEPs.get(0));

        // test button
        isSaveButtonEnable = bot.toolbarButton(SAVE_BUTTON_TEXT).isEnabled();
        Assert.assertTrue("Error: Save button must be enabled when creating a new diagram.", isSaveButtonEnable);

        // test menu
        bot.waitUntil(saveMenuEnabled, 5000, 500);

        bot.toolbarButton(SAVE_BUTTON_TEXT).click();

        // test button
        isSaveButtonEnable = bot.toolbarButton(SAVE_BUTTON_TEXT).isEnabled();
        Assert.assertFalse("Error: Save button must be disabled when creating a new diagram.", isSaveButtonEnable);

        final ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        final Command cmd = service.getCommand(SAVE_COMMAND_ID);
        assertFalse(cmd.isEnabled());
    }

    @Test
    public void testSaveButtonAndMenuEnableWhenNewDiagram() {

        boolean isSaveButtonEnable = false;

        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotTestUtil.changeDiagramName(bot, "testSave2");

        // test button
        isSaveButtonEnable = bot.toolbarButton(SAVE_BUTTON_TEXT).isEnabled();
        Assert.assertFalse("Error: Save button must not be enabled when changing diagram name.", isSaveButtonEnable);

        // test menu
        Assert.assertFalse("Error: Save menu must not be enabled when changing diagram name.",
                bot.menu("File").menu("Save").isEnabled());

        SWTBotTestUtil.createNewDiagram(bot);

        // test button
        isSaveButtonEnable = bot.toolbarButton(SAVE_BUTTON_TEXT).isEnabled();
        Assert.assertTrue("Error: Save button must enabled for the new editor of a new Dragram created.",
                isSaveButtonEnable);

        // test menu
        bot.waitUntil(saveMenuEnabled, 5000, 500);

        bot.toolbarButton(SAVE_BUTTON_TEXT).click();

        // test button
        isSaveButtonEnable = bot.toolbarButton(SAVE_BUTTON_TEXT).isEnabled();
        Assert.assertFalse("Error: Save button must be disabled just after saving a new diagram..", isSaveButtonEnable);

        // test menu
        final ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        final Command cmd = service.getCommand(SAVE_COMMAND_ID);
        assertFalse(cmd.isEnabled());
    }

}
