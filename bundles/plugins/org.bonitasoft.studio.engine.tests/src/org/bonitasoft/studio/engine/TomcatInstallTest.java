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
package org.bonitasoft.studio.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assume.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.osgi.framework.Bundle;

/**
 * The install, exercised directly: every fixture points a real TomcatInstall at a temporary folder
 * instead of subclassing the manager to redirect one method per path, which is what these tests had
 * to do while this logic lived in BOSWebServerManager.
 */
public class TomcatInstallTest {

    /**
     * Pinned deliberately rather than read from BOSWebServerManager: the fixtures below hardcode the
     * versions they compare against (10.1.57, 10.11.2, 9.0.90), so tracking the constant would leave
     * this suite green while exercising a Tomcat line the product no longer ships.
     */
    private static final String TOMCAT_MAJOR_MINOR = "10.1";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    /**
     * Strict, unlike StaleServerStatePurgerTest: every stub here is created inside the test that uses
     * it, so STRICT_STUBS adds unused-stub detection on top of the validateMockitoUsage this rule
     * exists for, rather than failing shared fixtures.
     */
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    private TomcatInstall install(File bundleDirectory) {
        return new TomcatInstall(bundleDirectory, TOMCAT_MAJOR_MINOR);
    }

    @Test
    public void should_delete_the_tomcat_bundle_directory_on_disk() throws Exception {
        var bundleDir = temporaryFolder.newFolder("tomcat");
        var lib = Files.createDirectories(bundleDir.toPath().resolve("server").resolve("lib"));
        Files.writeString(lib.resolve("catalina.jar"), "stale");
        var realDeletion = install(bundleDir);

        realDeletion.deleteStaleBundle();

        assertThat(bundleDir).doesNotExist();
    }

    @Test
    public void should_keep_the_engine_log_when_resetting_the_bundle() throws Exception {
        // the purge deletes the tree the engine log lives in, and the recovery dialogs it can
        // raise point users at that log: the copy must survive and getBonitaLogFile must find it
        // while the fresh install has none
        var bundleDir = temporaryFolder.newFolder("tomcat");
        var logs = Files.createDirectories(bundleDir.toPath().resolve("server").resolve("logs"));
        Files.writeString(logs.resolve("bonita.2026-08-12.log"), "the failure being reported");
        var install = install(bundleDir);

        install.deleteStaleBundle();

        assertThat(bundleDir).doesNotExist();
        assertThat(install.engineLogFile()).isNotNull().hasContent("the failure being reported");
    }

    @Test
    public void should_replace_the_kept_engine_log_on_every_reset() throws Exception {
        // a fixed destination overwritten in place: a directory would have to be cleared first,
        // and a clear that failed on a locked file would leave the current logs destroyed and
        // the older generation served as if it described the failure being reported
        var bundleDir = temporaryFolder.newFolder("tomcat");
        var install = install(bundleDir);
        Files.writeString(install.previousLogFile().toPath(), "an older failure");
        var logs = Files.createDirectories(bundleDir.toPath().resolve("server").resolve("logs"));
        Files.writeString(logs.resolve("bonita.2026-08-12.log"), "the failure being reported");

        install.deleteStaleBundle();

        assertThat(install.engineLogFile()).isNotNull().hasContent("the failure being reported");
    }

    @Test
    public void should_reset_a_relocated_install_without_raising() throws Exception {
        // a user who moved <ws>/tomcat to another disk through a link: the reset must remove the
        // link and carry on, not report a deletion failure through the recovery dialog
        var target = temporaryFolder.newFolder("tomcat-on-another-disk");
        Files.createDirectories(target.toPath().resolve("server").resolve("lib"));
        var bundleDir = new File(temporaryFolder.getRoot(), "tomcat");
        try {
            Files.createSymbolicLink(bundleDir.toPath(), target.toPath());
        } catch (UnsupportedOperationException | IOException e) {
            assumeTrue("symbolic links are not available here: " + e.getMessage(), false);
        }

        install(bundleDir).deleteStaleBundle();

        assertThat(bundleDir).doesNotExist();
        // the guard in FileUtil.deleteDir stops at the link: the reset unlinks <ws>/tomcat and leaves
        // the relocated tree intact on the other disk, orphaned rather than emptied. Asserting the
        // deepest fixture node rather than its parent: it proves no recursion happened at all, not
        // just that the first level survived
        assertThat(target).isDirectory();
        assertThat(target.toPath().resolve("server").resolve("lib")).isDirectory();
    }

    @Test
    public void should_raise_the_recovery_instruction_when_the_bundle_deletion_fails() throws Exception {
        var bundleDir = temporaryFolder.newFolder("tomcat");
        // a spy rather than a subclass: simulating a deletion failure needs no real locked file
        var failingDeletion = spy(install(bundleDir));
        doReturn(false).when(failingDeletion).deleteDirectory(any());

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(failingDeletion::deleteStaleBundle)
                .withMessageContaining(bundleDir.getAbsolutePath());
    }

    @Test
    public void should_drop_the_version_marker_before_the_bulk_deletion() throws Exception {
        // FileUtil.deleteDir stops at the first failure and File.list ordering is unspecified: if
        // catalina.jar survived a partial deletion it would report a current install, the early
        // return would skip the retry and the copy would skip extraction over the mangled tree
        var bundleDir = TomcatBundleFixture.bundleWithCatalinaJar(temporaryFolder, "server.info=Apache Tomcat/10.1.57\n");
        var failingDeletion = spy(install(bundleDir));
        doReturn(false).when(failingDeletion).deleteDirectory(any());

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(failingDeletion::deleteStaleBundle);

        assertThat(new File(bundleDir, "server/lib/catalina.jar")).doesNotExist();
        assertThat(failingDeletion.matchesAdapter()).isFalse();
    }

    @Test
    public void should_raise_the_recovery_instruction_when_the_deletion_throws() throws Exception {
        // FileUtil.deleteDir raises IllegalStateException for a directory that exists but cannot be
        // enumerated - the locked-tree case: the recovery dialog only catches CoreException, so a
        // runtime exception must be wrapped at the seam
        var bundleDir = temporaryFolder.newFolder("tomcat");
        var throwingDeletion = spy(install(bundleDir));
        doThrow(new IllegalStateException(
                String.format("Cannot enumerate %s to delete it", bundleDir.getAbsolutePath())))
                        .when(throwingDeletion).deleteDirectory(any());

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(throwingDeletion::deleteStaleBundle)
                .withMessageContaining(bundleDir.getAbsolutePath());
    }

    @Test
    public void should_report_no_engine_log_when_neither_the_install_nor_the_copy_has_one() throws Exception {
        var bundleDir = temporaryFolder.newFolder("tomcat");
        var install = install(bundleDir);

        install.deleteStaleBundle();

        assertThat(install.engineLogFile()).isNull();
    }

    @Test
    public void should_fail_when_the_product_moves_off_the_tomcat_line_these_fixtures_pin() {
        // the pin above is deliberate, but on its own it only DEFERS the drift it warns about: a
        // bump to the production constant would leave this whole suite green while exercising a
        // Tomcat line the product no longer ships. This turns that into a failure at the bump
        assertThat(BOSWebServerManager.TOMCAT_MAJOR_MINOR)
                .as("bump the hardcoded versions in this class alongside the production constant")
                .isEqualTo(TOMCAT_MAJOR_MINOR);
    }

    @Test
    public void should_match_a_fresh_workspace_without_any_bundle_directory() throws Exception {
        var checker = install(new File(temporaryFolder.getRoot(), "absent"));

        assertThat(checker.matchesAdapter()).isTrue();
    }

    @Test
    public void should_detect_a_partially_purged_install_as_stale() throws Exception {
        // FileUtil.deleteDir stops at the first failure: a present bundle directory without
        // server/lib is a half-deleted tree the bundle copy would merge the new Tomcat into
        var bundleDir = temporaryFolder.newFolder("tomcat");
        Files.createDirectories(bundleDir.toPath().resolve("server").resolve("webapps"));

        assertThat(install(bundleDir).matchesAdapter()).isFalse();
    }

    @Test
    public void should_detect_a_missing_catalina_jar_as_stale() throws Exception {
        var bundleDir = temporaryFolder.newFolder("tomcat");
        Files.createDirectories(bundleDir.toPath().resolve("server").resolve("lib"));

        assertThat(install(bundleDir).matchesAdapter()).isFalse();
    }

    @Test
    public void should_match_an_install_of_the_adapter_tomcat_line() throws Exception {
        var bundleDir = TomcatBundleFixture.bundleWithCatalinaJar(temporaryFolder, "server.info=Apache Tomcat/10.1.57\n");

        assertThat(install(bundleDir).matchesAdapter()).isTrue();
    }

    @Test
    public void should_match_an_install_reporting_no_patch_version() throws Exception {
        // the patch separator in the prefix is what stops 10.11 matching 10.1, so a build
        // reporting the bare line must be accepted explicitly: reading it as stale would purge,
        // re-extract and fail again on every single start
        var bundleDir = TomcatBundleFixture.bundleWithCatalinaJar(temporaryFolder, "server.info=Apache Tomcat/10.1\n");

        assertThat(install(bundleDir).matchesAdapter()).isTrue();
    }

    @Test
    public void should_detect_a_foreign_tomcat_line_as_stale() throws Exception {
        var bundleDir = TomcatBundleFixture.bundleWithCatalinaJar(temporaryFolder, "server.info=Apache Tomcat/9.0.90\n");

        assertThat(install(bundleDir).matchesAdapter()).isFalse();
    }

    @Test
    public void should_detect_an_adjacent_minor_line_as_stale() throws Exception {
        // the case the patch separator exists for: 10.11 is not the 10.1 line
        var bundleDir = TomcatBundleFixture.bundleWithCatalinaJar(temporaryFolder, "server.info=Apache Tomcat/10.11.2\n");

        assertThat(install(bundleDir).matchesAdapter()).isFalse();
    }

    @Test
    public void should_defer_to_wtp_validation_when_the_jar_cannot_be_read() throws Exception {
        // a transient read failure must not trigger a full purge and re-extraction
        var bundleDir = temporaryFolder.newFolder("tomcat");
        var lib = Files.createDirectories(bundleDir.toPath().resolve("server").resolve("lib"));
        Files.writeString(lib.resolve("catalina.jar"), "not a jar");

        assertThat(install(bundleDir).matchesAdapter()).isTrue();
    }

    @Test
    public void should_defer_to_wtp_validation_when_the_server_info_value_is_blank() throws Exception {
        // an entry with no server.info carries the same information as no entry at all, so it must
        // get the same outcome: read as stale it would purge, re-extract, and then tell the user
        // to reinstall the Studio
        var bundleDir = TomcatBundleFixture.bundleWithCatalinaJar(temporaryFolder, "some.other.key=value\n");

        assertThat(install(bundleDir).matchesAdapter()).isTrue();
    }

    @Test
    public void should_defer_to_wtp_validation_when_the_jar_has_no_server_info() throws Exception {
        // as uninformative as an unreadable jar: validation reads the same entry and fails
        // visibly if the install is genuinely broken
        var bundleDir = TomcatBundleFixture.bundleWithJarEntry(temporaryFolder, "META-INF/MANIFEST.MF", "");

        assertThat(install(bundleDir).matchesAdapter()).isTrue();
    }

    @Test
    public void should_keep_an_existing_install_instead_of_re_extracting() throws Exception {
        // the KEPT short-circuit: without the lib gate every start would re-extract a good install,
        // which is the multi-hundred-MB cost this enum distinguishes from a genuine failure
        var bundleDir = temporaryFolder.newFolder("tomcat");
        assertThat(new File(bundleDir, "server/lib").mkdirs()).isTrue();
        var install = spy(install(bundleDir));

        assertThat(install.copyBundle(new NullProgressMonitor()))
                .isEqualTo(BundleCopy.KEPT);
        // the shipped bundle is never consulted: that is what "kept" means
        verify(install, never()).shippedBundle();
    }

    @Test
    public void should_report_a_failed_copy_when_the_shipped_bundle_has_no_tomcat_resource() throws Exception {
        // a broken installation of the Studio itself. It must be FAILED rather than EXTRACTED, so
        // the caller raises the reinstall instruction instead of starting an empty install
        var bundleDir = new File(temporaryFolder.getRoot(), "absent");
        var install = spy(install(bundleDir));
        var shipped = mock(Bundle.class);
        doReturn(null).when(shipped).getResource("tomcat");
        doReturn(shipped).when(install).shippedBundle();

        assertThat(install.copyBundle(new NullProgressMonitor()))
                .isEqualTo(BundleCopy.FAILED);
        assertThat(bundleDir).doesNotExist();
    }

    @Test
    public void should_extract_the_shipped_bundle_into_the_workspace() throws Exception {
        // the seam exists for exactly this: a real directory stands in for the plugin resource, so
        // the extraction is exercised end to end rather than mocked away.
        //
        // Both folders are named "tomcat" because PlatformUtil.copyResource copies a source
        // DIRECTORY into the destination's PARENT, by the source's own name - so extraction lands in
        // the right place only while the shipped resource folder and the workspace bundle directory
        // share a name. Renaming either one would silently extract elsewhere and still report
        // EXTRACTED, which is why this test pins the pair rather than an arbitrary source name
        var shippedContent = temporaryFolder.newFolder("shipped", "tomcat");
        Files.writeString(new File(shippedContent, "marker.txt").toPath(), "shipped");
        var bundleDir = new File(temporaryFolder.newFolder("workspace"), "tomcat");
        var install = spy(install(bundleDir));
        var shipped = mock(Bundle.class);
        doReturn(shippedContent.toURI().toURL()).when(shipped).getResource("tomcat");
        doReturn(shipped).when(install).shippedBundle();

        assertThat(install.copyBundle(new NullProgressMonitor()))
                .isEqualTo(BundleCopy.EXTRACTED);
        assertThat(new File(bundleDir, "marker.txt")).hasContent("shipped");
    }

}
