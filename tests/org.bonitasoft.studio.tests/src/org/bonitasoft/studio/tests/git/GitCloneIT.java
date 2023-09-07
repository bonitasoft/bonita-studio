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

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class GitCloneIT {

    private static SWTGefBot bot = new SWTGefBot();
    @Rule
    public SWTGefBotRule gefBotRule = new SWTGefBotRule(bot, false);
    private File repoRoot;

    @Before
    public void create_bare_repository() throws Exception {
        ErrorDialog.AUTOMATED_MODE = false;
        var tmpRemotePath = Paths.get(System.getProperty("java.io.tmpdir"), "tmp_git_repo.git");
        if (tmpRemotePath.toFile().exists()) {
            FileUtil.deleteDir(tmpRemotePath.toFile());
        }
        repoRoot = new File(FileLocator.toFileURL(GitCloneIT.class.getResource("/procurement-example")).getFile());
        IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(EnginePreferenceConstants.LAZYLOAD_ENGINE, true);
    }

    @After
    public void reset() throws Exception {
        ErrorDialog.AUTOMATED_MODE = true;
    }

    @Test
    public void should_clone_a_bonita_project_and_migrate_it() throws Exception {
        new BotApplicationWorkbenchWindow(bot).gitClone()
                .setURI(repoRoot.toURI().toString())
                .next()
                .finishWithMigration();

        var project = RepositoryManager.getInstance().getCurrentProject().orElseThrow();
        assertThat(project.getId()).isEqualTo("procurement-example");
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                var gitRepo = project.getAdapter(Repository.class);
                var lastCommit = getLastCommit(gitRepo);
                assertThat(lastCommit.getFullMessage())
                        .isEqualTo(String.format("Bonita '7.12.1' to '%s' automated migration",
                                ProductVersion.CURRENT_VERSION));
            }
        });

    }

    private RevCommit getLastCommit(Repository repository)
            throws Exception {
        RevCommit youngestCommit = null;
        try (var git = new Git(repository);
                var walk = new RevWalk(git.getRepository());) {
            var branches = git.branchList().setListMode(ListMode.ALL).call();
            for (Ref branch : branches) {
                RevCommit commit = walk.parseCommit(branch.getObjectId());
                if (youngestCommit == null || commit.getAuthorIdent().getWhen().compareTo(
                        youngestCommit.getAuthorIdent().getWhen()) > 0)
                    youngestCommit = commit;
            }
            return youngestCommit;
        }
    }

}
