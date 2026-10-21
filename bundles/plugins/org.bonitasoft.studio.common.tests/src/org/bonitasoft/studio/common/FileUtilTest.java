/**
 * Copyright (C) 2026 Bonitasoft S.A.
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
package org.bonitasoft.studio.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assume.assumeTrue;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileUtilTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    /** The tree deleteDir is asked to delete. */
    private Path deletedTree;
    /** Outside that tree: nothing here may be touched by deleting the tree. */
    private Path preciousDirectory;
    private Path preciousFile;

    @Before
    public void createTreeAndAnUnrelatedDirectory() throws Exception {
        deletedTree = temporaryFolder.newFolder("workspace-tomcat").toPath();
        preciousDirectory = temporaryFolder.newFolder("somewhere-else").toPath();
        preciousFile = Files.writeString(preciousDirectory.resolve("engine.log"), "not ours to delete");
    }

    @Test
    public void should_delete_a_directory_tree() throws Exception {
        var nested = Files.createDirectories(deletedTree.resolve("server").resolve("logs"));
        Files.writeString(nested.resolve("bonita.log"), "ours");

        assertThat(FileUtil.deleteDir(deletedTree.toFile())).isTrue();

        assertThat(deletedTree).doesNotExist();
    }

    @Test
    public void should_delete_a_linked_directory_inside_the_tree_as_a_link() throws Exception {
        // File.isDirectory() resolves the link, so recursing into it would delete the contents of
        // a directory the caller never named - a user who moved server/logs to a bigger disk
        var link = symbolicLink(deletedTree.resolve("logs"), preciousDirectory);

        assertThat(FileUtil.deleteDir(deletedTree.toFile())).isTrue();

        assertThat(link).doesNotExist();
        assertThat(deletedTree).doesNotExist();
        assertThat(preciousDirectory).isDirectory();
        assertThat(preciousFile).hasContent("not ours to delete");
    }

    @Test
    public void should_delete_a_linked_directory_passed_as_the_argument_as_a_link() throws Exception {
        var link = symbolicLink(deletedTree.resolve("logs"), preciousDirectory);

        assertThat(FileUtil.deleteDir(link.toFile())).isTrue();

        assertThat(link).doesNotExist();
        assertThat(preciousDirectory).isDirectory();
        assertThat(preciousFile).hasContent("not ours to delete");
    }

    @Test
    public void should_delete_a_linked_directory_as_a_link_with_the_path_variant() throws Exception {
        // Files.walk does not follow links unless asked, so this variant was already safe: pinned
        // so the two overloads cannot drift apart
        var link = symbolicLink(deletedTree.resolve("logs"), preciousDirectory);

        FileUtil.deleteDir(deletedTree);

        assertThat(link).doesNotExist();
        assertThat(deletedTree).doesNotExist();
        assertThat(preciousDirectory).isDirectory();
        assertThat(preciousFile).hasContent("not ours to delete");
    }

    @Test
    public void should_delete_a_tree_reached_through_a_linked_parent() throws Exception {
        // a user who put the whole Studio workspace on another disk through a link: only the LAST
        // path component is tested for linkage, so <ws>/tomcat reached through a linked workspace
        // root is still an ordinary directory and must be deleted normally.
        //
        // The target is a DEDICATED folder, never the rule's root: a link pointing at the directory
        // that contains it is a cycle, and TemporaryFolder's cleanup is a plain listFiles() walk with
        // no link check - it would descend link/link/link until the path outgrows the OS limit. That
        // failure is swallowed today, but it breaks on Windows' shorter limit and turns into a hard
        // failure under assureDeletion()
        var relocatedWorkspace = temporaryFolder.newFolder("relocated-workspace").toPath();
        var relocatedTree = Files.createDirectories(relocatedWorkspace.resolve("tomcat"));
        Files.createDirectories(relocatedTree.resolve("server").resolve("lib"));
        var linkedWorkspace = symbolicLink(temporaryFolder.getRoot().toPath().resolve("workspace-link"),
                relocatedWorkspace);

        var treeThroughTheLink = linkedWorkspace.resolve("tomcat");

        assertThat(FileUtil.deleteDir(treeThroughTheLink.toFile())).isTrue();
        assertThat(relocatedTree).doesNotExist();
        assertThat(linkedWorkspace).isSymbolicLink();
        assertThat(relocatedWorkspace).isDirectory();
    }

    @Test
    public void should_delete_a_linked_directory_passed_as_the_argument_as_a_link_with_the_path_variant()
            throws Exception {
        // the shape the File overload pins and this one did not. It is already safe - Files.walk reads
        // the start node NOFOLLOW_LINKS, so a link argument is visited as a link and never descended -
        // but safe by a subtlety of FileTreeWalker is exactly what drifts unnoticed
        var link = symbolicLink(deletedTree.resolve("logs"), preciousDirectory);

        FileUtil.deleteDir(link);

        assertThat(link).doesNotExist();
        assertThat(preciousDirectory).isDirectory();
        assertThat(preciousFile).hasContent("not ours to delete");
    }

    @Test
    public void should_raise_a_named_failure_when_a_directory_cannot_be_enumerated() throws Exception {
        // a locked or unreadable tree is the case the recovery messages upstream exist FOR, so it has
        // to be loud: nearly every caller discards the boolean, and a false return would read as a
        // deletion that completed. Previously a bare NPE naming nothing
        var unreadable = Files.createDirectories(deletedTree.resolve("unreadable"));
        Files.writeString(unreadable.resolve("locked.txt"), "cannot be listed");
        // the chmod is undone BEFORE the assumption can throw: evaluated inside assumeTrue, a failed
        // assumption skipped the restoring finally below and left TemporaryFolder an unreadable
        // directory to clean up. Note this skips when the suite runs as root - containerised CI often
        // does - so a green run there does not mean this path was exercised
        var enumerationBlocked = unreadable.toFile().setReadable(false) && unreadable.toFile().list() == null;
        if (!enumerationBlocked) {
            unreadable.toFile().setReadable(true);
        }
        assumeTrue("the filesystem does not honour a non-readable directory here", enumerationBlocked);

        try {
            assertThatExceptionOfType(IllegalStateException.class)
                    .isThrownBy(() -> FileUtil.deleteDir(deletedTree.toFile()))
                    .withMessageContaining(unreadable.toFile().getAbsolutePath());
        } finally {
            // restored so TemporaryFolder can clean up: its walk would otherwise leave the tree behind
            unreadable.toFile().setReadable(true);
        }
    }

    /**
     * Creates a symbolic link, or skips the test where the filesystem or the OS refuses: Windows
     * needs a privilege that developer accounts do not always hold.
     * <p>
     * Deliberately narrow. These tests are the ones that pin the dangerous shapes, so a genuine
     * failure must not turn into a skip that reads exactly like success - and FileSystemException
     * alone is not narrow enough: FileAlreadyExistsException and NoSuchFileException extend it, so a
     * broken fixture would have been reported as "links are not available here". Those two are
     * rethrown; what remains is the OS or filesystem genuinely refusing, which is a real skip.
     */
    private static Path symbolicLink(Path link, Path target) throws IOException {
        try {
            return Files.createSymbolicLink(link, target);
        } catch (FileAlreadyExistsException | NoSuchFileException e) {
            throw e;
        } catch (UnsupportedOperationException | FileSystemException e) {
            throw new AssumptionViolatedException("symbolic links are not available here", e);
        }
    }
}
