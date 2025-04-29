/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.input.NullInputStream;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.bonitasoft.studio.tests.util.InitialProjectRule;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests the validation of git ignored files
 * 
 * @author Vincent Hemery
 */
public class GitIgnoreTests {

    @Rule
    public InitialProjectRule projectRule = InitialProjectRule.INSTANCE;
    private RepositoryAccessor repositoryAccessor;
    private IProject sharedProject;

    @Before
    public void setUp() throws Exception {
        // create a project shared with git
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        var repo = repositoryAccessor.getCurrentRepository().orElseThrow();
        sharedProject = repo.getProject();
        File gitDir = new File(sharedProject.getLocation().toFile().getAbsolutePath(),
                Constants.DOT_GIT);
        if (!gitDir.exists()) {
            try (Repository repository = FileRepositoryBuilder.create(gitDir)) {
                repository.create();
                if (!gitDir.toString().contains("..")) //$NON-NLS-1$
                    sharedProject.refreshLocal(IResource.DEPTH_ONE,
                            new NullProgressMonitor());
                RepositoryUtil.INSTANCE.addConfiguredRepository(gitDir);
                ConnectProviderOperation op = new ConnectProviderOperation(sharedProject);
                op.execute(new NullProgressMonitor());
            }
        }
        // have .gitignore file corresponding to template
        var gitIgnore = sharedProject.getFile(Constants.GITIGNORE_FILENAME);
        URL gitIgnoreTemplateUrl = GitProject.getGitignoreTemplateFileURL();
        try (var templateStream = gitIgnoreTemplateUrl.openStream()) {
            if (!gitIgnore.exists()) {
                // create new gile from template
                gitIgnore.create(templateStream, true, new NullProgressMonitor());
            } else {
                // just restore it from template
                gitIgnore.setContents(templateStream, true, true, new NullProgressMonitor());
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        // disconnect and delete .gitignore
        RepositoryMapping mapping = RepositoryMapping.getMapping(sharedProject);
        Repository repository = mapping.getRepository();
        repository.close();
        var gitIgnore = sharedProject.getFile(Constants.GITIGNORE_FILENAME);
        if (gitIgnore.exists()) {
            gitIgnore.delete(true, null);
        }
        var gitDir = sharedProject.getFile(Constants.DOT_GIT);
        if (gitDir.exists()) {
            gitDir.delete(true, null);
        }
    }

    /**
     * Test that a committed and ignored file makes the validation fail
     * 
     * @throws Exception exception during test
     */
    @Test
    public void testCommittedIgnoredFile() throws Exception {
        // .classpath file is in gitignore template and will probably always be...
        // alter .gitignore
        removeGitIgnoreLine(".classpath");
        IFile file = sharedProject.getFile(".classpath");
        if (!file.exists()) {
            file.create(new NullInputStream(), true, null);
        }
        // commit the file
        try (var git = Git.open(sharedProject.getLocation().toFile())) {
            git.add().addFilepattern(".classpath").call();
            git.commit().setMessage("Commit .classpath").call();
        }
        // have .gitignore file corresponding back to template
        var gitIgnore = sharedProject.getFile(Constants.GITIGNORE_FILENAME);
        URL gitIgnoreTemplateUrl = GitProject.getGitignoreTemplateFileURL();
        try (var templateStream = gitIgnoreTemplateUrl.openStream()) {
            // just restore it from template
            gitIgnore.setContents(templateStream, true, true, new NullProgressMonitor());
        }
        // self-validate the repository with git constraints on .gitignore
        IRunnableWithStatus repositoryValidator = Adapters.adapt(
                repositoryAccessor.getCurrentRepository().orElseThrow(), IRunnableWithStatus.class);
        assertNotNull(repositoryValidator);
        repositoryValidator.run(new NullProgressMonitor());
        StatusAssert.assertThat(repositoryValidator.getStatus()).isError();
    }

    /**
     * Test that a file in the template-ignored target directory makes the validation fail when not ignored
     * 
     * @throws Exception exception during test
     */
    @Test
    public void testNotIgnoredDirectory() throws Exception {
        // target directory is in gitignore template and will probably always be...
        // alter .gitignore
        removeGitIgnoreLine("target/");
        // create file in target directory
        IFolder target = sharedProject.getFolder("target");
        if (!target.exists()) {
            target.create(true, true, null);
        }
        target.getFile("test.txt").create(new NullInputStream(), true, null);
        // self-validate the repository with git constraints on .gitignore
        IRunnableWithStatus repositoryValidator = Adapters.adapt(
                repositoryAccessor.getCurrentRepository().orElseThrow(), IRunnableWithStatus.class);
        assertNotNull(repositoryValidator);
        repositoryValidator.run(new NullProgressMonitor());
        StatusAssert.assertThat(repositoryValidator.getStatus()).isWarning();
    }

    /**
     * Test that a template-ignored file makes the validation fail when not ignored
     * 
     * @throws Exception exception during test
     */
    @Test
    public void testNotIgnoredFile() throws Exception {
        // .classpath file is in gitignore template and will probably always be...
        // alter .gitignore
        removeGitIgnoreLine(".classpath");
        IFile file = sharedProject.getFile(".classpath");
        if (!file.exists()) {
            file.create(new NullInputStream(), true, null);
        }
        // self-validate the repository with git constraints on .gitignore
        IRunnableWithStatus repositoryValidator = Adapters.adapt(
                repositoryAccessor.getCurrentRepository().orElseThrow(), IRunnableWithStatus.class);
        assertNotNull(repositoryValidator);
        repositoryValidator.run(new NullProgressMonitor());
        StatusAssert.assertThat(repositoryValidator.getStatus()).isWarning();
    }

    /**
     * Test that a simple file does'nt make the validation fail
     * 
     * @throws Exception exception during test
     */
    @Test
    public void testNominalCase() throws Exception {
        // a file README.MD should never get in the gitignore template...
        sharedProject.getFile("README.MD").create(new NullInputStream(), true, null);
        // self-validate the repository with git constraints on .gitignore
        IRunnableWithStatus repositoryValidator = Adapters.adapt(
                repositoryAccessor.getCurrentRepository().orElseThrow(), IRunnableWithStatus.class);
        assertNotNull(repositoryValidator);
        repositoryValidator.run(new NullProgressMonitor());
        StatusAssert.assertThat(repositoryValidator.getStatus()).isOK();
    }

    /**
     * Alter the .gitignore file to remove a particular line
     * 
     * @param line line to remove from file
     * @throws CoreException eclipse core exception
     * @throws IOException exception while reading or writing
     */
    private void removeGitIgnoreLine(String line) throws CoreException, IOException {
        IFile gitIgnore = sharedProject.getFile(Constants.GITIGNORE_FILENAME);
        try (var reader = new BufferedReader(new InputStreamReader(gitIgnore.getContents()))) {
            Predicate<String> matches = line::equals;
            List<String> lines = reader.lines().filter(matches.negate()).collect(Collectors.toList());
            String newContent = lines.stream().collect(Collectors.joining(System.lineSeparator()));
            try (var writeStream = new ByteArrayInputStream(newContent.getBytes())) {
                gitIgnore.setContents(writeStream, true, true, null);
            }
        }
    }
    
}
