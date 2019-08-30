package org.bonitasoft.studio.tests.diagram;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withId;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withMnemonic;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.commands.Command;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
@Ignore
public class TestSave {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    final static String SAVE_BUTTON_TEXT = "Save";
    private static final String SAVE_COMMAND_ID = "org.eclipse.ui.file.save";

    @Test
    public void testSaveButtonAndMenuNotEnableAtInitiationAndEnableAfterChange() {
        // When opening Bonita soft
        final Matcher<MenuItem> matcher = withMnemonic(SAVE_BUTTON_TEXT);
        bot.waitUntil(Conditions.waitForMenu(bot.activeShell(), matcher));
        bot.waitUntil(Conditions.waitForWidget(withId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR)));

        // test button
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                Assert.assertFalse("Error: Save button must be disabled when opening Bonita Studio.",
                        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).isEnabled());
            }
        });

        // test menu
        bot.waitUntil(widgetIsDisabled(bot.menu("File").click().menu("Save")));

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
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                Assert.assertTrue("Error: Save button must be enabled when creating a new diagram.",
                        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).isEnabled());

            }
        });

        // test menu
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("File").menu("Save")));

        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).click();

        // test button
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                Assert.assertFalse("Error: Save button must be disabled after saving a diagram.",
                        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).isEnabled());

            }
        });

        final ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        final Command cmd = service.getCommand(SAVE_COMMAND_ID);
        assertFalse(cmd.isEnabled());
    }

    private ICondition widgetIsDisabled(AbstractSWTBot<? extends Widget> widget) {
        return new ICondition() {

            @Override
            public boolean test() throws Exception {
                return !widget.isEnabled();
            }

            @Override
            public void init(final SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return widget.toString() + "is still enabled !";
            }
        };
    }

    @Test
    public void testSaveButtonAndMenuEnableWhenNewDiagram() {
        BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        BotProcessDiagramPerspective newDiagram = botApplicationWorkbenchWindow
                .createNewDiagram();
        newDiagram.activeProcessDiagramEditor().selectDiagram();
        newDiagram.getDiagramPropertiesPart().selectGeneralTab()
                .selectDiagramTab()
                .setName("testSave2");
        newDiagram.activeProcessDiagramEditor().selectDiagram();
        
        bot.waitUntil(Conditions.waitForWidget(withId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR)));


        // test button
        bot.waitUntil(new AssertionCondition() {

            protected void makeAssert() throws Exception {
                Assert.assertFalse("Error: Save button must not be enabled when changing diagram name.",
                        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).isEnabled());
            }
        });

        // test menu
        Assert.assertFalse("Error: Save menu must not be enabled when changing diagram name.",
                bot.menu("File").menu("Save").isEnabled());

        new BotApplicationWorkbenchWindow(bot)
                .createNewDiagram()
                .activeProcessDiagramEditor()
                .selectDiagram()
                .selectElement("Step1");

        // test button
        bot.waitUntil(new AssertionCondition() {

            protected void makeAssert() throws Exception {
                Assert.assertTrue("Error: Save button must enabled for the new editor of a new Dragram created.",
                        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).isEnabled());
            }
        }, 10000);

        // test menu
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("File").menu("Save")));

        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).click();

        // test button
        bot.waitUntil(new AssertionCondition() {

            protected void makeAssert() throws Exception {
                Assert.assertFalse("Error: Save button must be disabled just after saving a new diagram..",
                        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).isEnabled());
            }
        });

        // test menu
        final ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        final Command cmd = service.getCommand(SAVE_COMMAND_ID);
        assertFalse(cmd.isEnabled());
    }

}
