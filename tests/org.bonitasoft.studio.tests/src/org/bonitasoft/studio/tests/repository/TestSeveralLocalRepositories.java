/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 */
package org.bonitasoft.studio.tests.repository;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertNotEnabled;
import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellIsActive;

import java.util.Objects;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSeveralLocalRepositories {

    private AbstractRepository currentRepo;

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Before
    public void setUp() throws Exception {
        BOSEngineManager.getInstance().start();
        currentRepo = RepositoryManager.getInstance().getCurrentRepository();
    }

    @After
    public void tearDown() throws Exception {
        if (!RepositoryManager.getInstance().getCurrentRepository().equals(currentRepo)) {
            TeamRepositoryUtil.switchToRepository(currentRepo.getName(), new NullProgressMonitor());
        }
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
    }

    @Test
    public void testCreateNewLocalRepo() {
        openNewLocalRepoDialog();

        /* Test can't finish for empty name */
        bot.textWithLabel(Messages.name+ " *").setText("");
        assertNotEnabled(bot.button(Messages.create));

        /* Test creation of a new local repository */
        final String testRepoName = "test a new repo name";
        bot.textWithLabel(Messages.name+ " *").setText(testRepoName);
        bot.button(Messages.create).click();

        ICondition condition = new ConditionBuilder()
                .withTest(() -> Objects.equals(testRepoName,
                        RepositoryManager.getInstance().getCurrentRepository().getName()))
                .withFailureMessage(() -> String.format("The project name should be %s, but is %s", testRepoName,
                        RepositoryManager.getInstance().getCurrentRepository().getName()))
                .create();
        bot.waitUntil(condition, 120000);

        /* now test that can't use the same name */
        openNewLocalRepoDialog();
        bot.textWithLabel(Messages.name+ " *").setText(testRepoName);
        assertNotEnabled(bot.button(Messages.create));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
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
