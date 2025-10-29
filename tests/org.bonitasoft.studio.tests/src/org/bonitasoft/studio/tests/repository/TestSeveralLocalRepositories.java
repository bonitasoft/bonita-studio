/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 */
package org.bonitasoft.studio.tests.repository;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertNotEnabled;
import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellIsActive;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.tests.util.ProjectUtil;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSeveralLocalRepositories {

    /** Test to run on IDE plugin (ensuring exclusive access to workspace operations) to assert UID started */
    private static final IWorkspaceRunnable UID_STARTED_TEST = monitor -> assertTrue(
            UIDesignerServerManager.getInstance().isStarted());

    /** Test to run on IDE plugin (ensuring exclusive access to workspace operations) to assert UID did not start */
    private static final IWorkspaceRunnable UID_NOT_STARTED_TEST = monitor -> assertFalse(
            UIDesignerServerManager.getInstance().isStarted());

    private IRepository currentRepo;

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Before
    public void setUp() throws Exception {
        BOSEngineManager.getInstance().start();
        currentRepo = RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
    }

    @After
    public void tearDown() throws Exception {
        if (!RepositoryManager.getInstance().getCurrentRepository().filter(currentRepo::equals).isPresent()) {
            RepositoryManager.getInstance().switchToRepository(currentRepo.getProjectId(), new NullProgressMonitor());
        }
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
    }

    @Test
    public void testCreateNewLocalRepo() {
        openNewLocalRepoDialog();

        /* Test can't finish for empty name */
        bot.textWithLabel(Messages.name + " *").setText("");
        assertNotEnabled(bot.button(Messages.create));

        /* Test creation of a new local repository */
        final String testRepoName = "test a new repo name";
        bot.textWithLabel(Messages.name + " *").setText(testRepoName);
        bot.button(Messages.create).click();

        waitForRepoCreation(testRepoName);

        /* now test that can't use the same name */
        openNewLocalRepoDialog();
        bot.textWithLabel(Messages.name + " *").setText(testRepoName);
        assertNotEnabled(bot.button(Messages.create));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    /**
     * Wait for the creation of a repository with the given name.
     * 
     * @param testRepoName the repository name to wait for
     */
    private void waitForRepoCreation(final String testRepoName) {
        ICondition condition = new ConditionBuilder()
                .withTest(() -> Objects.equals(testRepoName,
                        RepositoryManager.getInstance().getCurrentProject().orElseThrow().getDisplayName()))
                .withFailureMessage(() -> String.format("The project name should be %s, but is %s", testRepoName,
                        RepositoryManager.getInstance().getCurrentProject().orElseThrow().getDisplayName()))
                .create();
        bot.waitUntil(condition, 120000);
    }

    @Test
    public void should_not_startUID_when_autostart_is_false() throws CoreException {
        // given
        openNewLocalRepoDialog();
        final String testRepoName = "uidAutostartIsFalse";
        bot.textWithLabel(Messages.name + " *").setText(testRepoName);

        // when
        bot.checkBox(Messages.uiDesignerAutostart).deselect();
        bot.button(Messages.create).click();
        waitForRepoCreation(testRepoName);

        // then, UID should not start
        ProjectUtil.waitForProjectOperations(true);
        IDEWorkbenchPlugin.getPluginWorkspace().run(UID_NOT_STARTED_TEST, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void should_startUID_when_autostart_is_true() throws CoreException {
        // given
        openNewLocalRepoDialog();
        final String testRepoName = "uidAutostartIsTrue";
        bot.textWithLabel(Messages.name + " *").setText(testRepoName);

        // when
        bot.checkBox(Messages.uiDesignerAutostart).select();
        bot.button(Messages.create).click();
        waitForRepoCreation(testRepoName);

        // then, UID should start
        ProjectUtil.waitForProjectOperations(true);
        IDEWorkbenchPlugin.getPluginWorkspace().run(UID_STARTED_TEST, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void should_startUID_when_autostart_is_updated() throws CoreException {
        // given
        openNewLocalRepoDialog();
        final String testRepoName = "uidAutostartIsUpdated";
        bot.textWithLabel(Messages.name + " *").setText(testRepoName);
        bot.checkBox(Messages.uiDesignerAutostart).deselect();
        bot.button(Messages.create).click();
        waitForRepoCreation(testRepoName);
        ProjectUtil.waitForProjectOperations(true);
        IDEWorkbenchPlugin.getPluginWorkspace().run(UID_NOT_STARTED_TEST, AbstractRepository.NULL_PROGRESS_MONITOR);

        // when
        var worbenchBot = new BotApplicationWorkbenchWindow(bot);
        var projectDetailsBot = worbenchBot.openProjectOverview().toDashboardView();
        var wizBot = projectDetailsBot.editProjectMetadata();
        bot.checkBox(Messages.uiDesignerAutostart).select();
        wizBot.modify();

        // then, UID should start
        ProjectUtil.waitForProjectOperations(true);
        IDEWorkbenchPlugin.getPluginWorkspace().run(UID_STARTED_TEST, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    protected void openNewLocalRepoDialog() {
        ICondition menuAvailable = new ConditionBuilder()
                .withTest(() -> {
                    try {
                        bot.menu("File");
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> "The menu 'File' is not available.")
                .create();
        bot.waitUntil(menuAvailable, 120000);
        bot.menu("File").menu("New project...").click();
        bot.waitUntil(shellIsActive(org.bonitasoft.studio.application.i18n.Messages.newProjectWizardTitle));
    }

}
