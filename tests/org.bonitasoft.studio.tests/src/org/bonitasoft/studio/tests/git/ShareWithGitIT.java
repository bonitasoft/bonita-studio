/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.git;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.egit.ui.internal.UIText;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ShareWithGitIT {

    private Path gitRepoPath;
    private SWTGefBot bot = new SWTGefBot();
    @Rule
    public SWTGefBotRule gefBotRule = new SWTGefBotRule(bot);
    private org.eclipse.jgit.lib.Repository remote;

    @BeforeClass
    public static void switchToDefault() throws Exception {
        TeamRepositoryUtil.switchToRepository(Messages.defaultRepositoryName, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Before
    public void create_bare_repository() throws Exception {
        gitRepoPath = Files.createDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "tmp_git_repo.git"));
        remote = new RepositoryBuilder().setBare().setGitDir(gitRepoPath.toFile()).build();
        remote.create(true);
    }

    @Test
    public void should_share_a_new_repositroy_with_Git_and_clone_it() throws Exception {
        BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);

        AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        assertThat(currentRepository.getName()).isEqualTo(Messages.defaultRepositoryName);
        assertThat(currentRepository.isShared(GitProvider.ID))
                .as("%s repository should not be connected", currentRepository.getName())
                .isFalse();

        botApplicationWorkbenchWindow.teamMenu().gitMenu().shareWithGit()
                .setRepositoryName("git_it_repo")
                .share()
                .commitMessage("This is a test message")
                .pushBranch().setURI(gitRepoPath.toUri().toString())
                .next()
                .next()
                .finish();

        String shellName = NLS.bind(UIText.PushResultDialog_title, gitRepoPath.toUri().toString());
        bot.waitUntil(Conditions.shellIsActive(shellName),
                60000);
        bot.shell(shellName).activate();
        bot.button(IDialogConstants.CLOSE_LABEL).click();

        //Check that the current repo is connected to Git and clean
        currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        assertThat(currentRepository.isShared(GitProvider.ID))
                .as("%s repository should be connected", currentRepository.getName())
                .isTrue();
        org.eclipse.jgit.lib.Repository localRepository = ResourceUtil.getRepository(currentRepository.getProject());
        try (Git git = new Git(localRepository)) {
            Status status = git.status().call();
            assertThat(status.isClean()).as("%s project should have a clean Git status",
                    currentRepository.getName()).isTrue();
        }

        //Check that the commit has been pushed properly
        Ref head = remote.exactRef("refs/heads/master");
        assertThat(head).isNotNull();
        try (RevWalk walk = new RevWalk(remote)) {
            RevCommit commit = walk.parseCommit(head.getObjectId());
            assertThat(commit.getFullMessage()).isEqualTo("This is a test message")
                    .as("Head on remote have the proper commit");
            walk.dispose();
        }

        //Clone created repository in another bonita project
        botApplicationWorkbenchWindow.teamMenu()
                .gitMenu()
                .gitClone()
                .setURI(gitRepoPath.toUri().toString())
                .next()
                .next()
                .finish();

        currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        assertThat(currentRepository.getName()).isEqualTo("tmp_git_repo");
        assertThat(currentRepository.isShared(GitProvider.ID))
                .as("%s project should be connected", currentRepository.getName())
                .isTrue();
    }

    @After
    public void reset() throws Exception {
        remote.close();
        Display.getDefault().syncExec(() -> {
            try {
                PlatformUI.getWorkbench().getProgressService().run(false, false, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        PlatformUtil.delete(gitRepoPath.toFile(), monitor);
                        TeamRepositoryUtil.switchToRepository(Messages.defaultRepositoryName, false, false, monitor);
                    }
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        
        
        
    }
}
