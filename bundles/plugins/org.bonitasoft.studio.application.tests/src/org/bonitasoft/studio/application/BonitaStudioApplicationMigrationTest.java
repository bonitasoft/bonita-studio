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
package org.bonitasoft.studio.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

import org.bonitasoft.studio.application.BonitaStudioApplication.VersionsComparison;
import org.bonitasoft.studio.common.ZipUtil;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.osgi.internal.location.BasicLocation;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.widgets.Display;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.osgi.framework.Version;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BonitaStudioApplicationMigrationTest {

    private BonitaStudioApplication application;

    private static URL ORIGINAL_URL = Platform.getInstanceLocation().getURL();

    @BeforeEach
    void setUp() throws Exception {
        application = spy(new BonitaStudioApplication(Display.getDefault()));
        doNothing().when(application).openErrorDialog(any(Display.class), anyString());
        doReturn(IApplication.EXIT_OK).when(application).createAndRunWorkbench(nullable(Display.class));
        // do not dispose display for other tests
        doNothing().when(application).disposeDisplay(any(Display.class));
    }

    @AfterEach
    void tearDown() throws Exception {
        Job.getJobManager().removeJobChangeListener(application);
    }

    @Test
    void should_try_and_migrate() throws Exception {
        /*
         * We do require org.bonitasoft.studio as plugin dependency for this test.
         * Or Studio version won't be resolved.
         * Note that although it is mostly representative, workspace_7_14_0.zip is not a vanilla 7.14.0 workspace.
         * To reduce the size, the following folders have been removed:
         * - tomcat/server/webapps
         * - tomcat/server/lib
         * - tomcat/setup/lib
         * - .metadata/plugins/org.eclipse.jdt.core
         * - .metadata/.plugins/org.bonitasoft.studio.common.repository
         * and these files have been replaced by 0-bytes files:
         * - .metadata/plugins/org.bonitasoft.studio.designer/ui-designer-backend-webapp.jar
         * - .metadata/plugins/org.bonitasoft.studio.designer/bonita-data-repository-win.exe
         */
        executeTestInNewWorkspace(() -> {
            // unzip a 7.14.0 workspace to open the application on it
            File oldWsZipFile = new File(
                    FileLocator
                            .toFileURL(BonitaStudioApplicationMigrationTest.class.getResource("workspace_7_14_0.zip"))
                            .getFile());
            return ZipUtil.unzip(oldWsZipFile).toFile();
        }, wsPath -> {
            // mock the migration dialogs
            MessageDialogWithToggle warningDialog = mock(MessageDialogWithToggle.class);
            when(warningDialog.open()).thenReturn(IDialogConstants.YES_ID)
                    .thenThrow(new AssertionFailedException("Version warning raised more than once"));
            MessageDialogWithToggle infoDialog = mock(MessageDialogWithToggle.class);
            doThrow(new AssertionFailedException("Version migration info")).when(infoDialog).open();
            MessageDialog errorDialog = mock(MessageDialog.class);
            doThrow(new AssertionFailedException("Version error")).when(errorDialog).open();
            // and prevent opening actual dialogs from the application
            doReturn(infoDialog).when(application).makeWorkspaceVersionMigrationDialog(any(), any(), eq(false));
            doReturn(warningDialog).when(application).makeWorkspaceVersionMigrationDialog(any(), any(),
                    eq(true));
            doReturn(errorDialog).when(application).makeWorkspaceVersionErrorDialog(any(), any(), any());
            // start application
            Map<String, Object> appArgs = Map.of("osgi.instance.area", wsPath.toString(),
                    // use DevLaunchMode to avoid some popups in case of error
                    "-pdelaunch", Boolean.TRUE);
            IApplicationContext appContext = mock(IApplicationContext.class);
            doReturn(appArgs).when(appContext).getArguments();
            // check that application restart
            assertThat(application.start(appContext)).isEqualTo(IApplication.EXIT_RESTART);
            // and that migration dialogs were correctly opened
            verify(warningDialog, atLeastOnce()).open();
            // also test that versions are now aligned
            assertThat(application.compareWorkspaceAndStudioVersions(wsPath.toURI().toURL()))
                    .isEqualTo(VersionsComparison.COMPATIBLE_SAME_MAINTENANCE);
            // restart application to check that migration no longer occurs.
            Field lockerField = BasicLocation.class.getDeclaredField("locker");
            lockerField.setAccessible(true);
            lockerField.set(Platform.getInstanceLocation(), null);
            assertThat(application.start(appContext)).isEqualTo(IApplication.EXIT_OK);
            verify(warningDialog, atMostOnce()).open();
        });
    }

    @Test
    void should_open_next_maintenance() throws Exception {
        Version v = BonitaStudioApplication.WORKSPACE_CHECK_STUDIO_BUNDLE_VERSION;
        assertThat(v).isNotNull();
        Version nextMaintenance = new Version(v.getMajor(), v.getMinor(), v.getMicro() + 1);
        executeTestInNewWorkspace(() -> createEmptyWorkspace(nextMaintenance), wsPath -> {
            /*
             * The next maintenance version workspace should open smoothly
             */
            // mock the migration dialogs
            MessageDialogWithToggle warningDialog = mock(MessageDialogWithToggle.class);
            doThrow(new AssertionFailedException("Version migration warning")).when(warningDialog).open();
            MessageDialogWithToggle infoDialog = mock(MessageDialogWithToggle.class);
            doThrow(new AssertionFailedException("Version migration info")).when(infoDialog).open();
            MessageDialog errorDialog = mock(MessageDialog.class);
            doThrow(new AssertionFailedException("Version error")).when(errorDialog).open();
            // and prevent opening actual dialogs from the application
            doReturn(infoDialog).when(application).makeWorkspaceVersionMigrationDialog(any(), any(), eq(false));
            doReturn(warningDialog).when(application).makeWorkspaceVersionMigrationDialog(any(), any(),
                    eq(true));
            doReturn(errorDialog).when(application).makeWorkspaceVersionErrorDialog(any(), any(), any());
            // start application
            Map<String, Object> appArgs = Map.of("osgi.instance.area", wsPath.toString(),
                    // use DevLaunchMode to avoid some popups in case of error
                    "-pdelaunch", Boolean.TRUE);
            IApplicationContext appContext = mock(IApplicationContext.class);
            doReturn(appArgs).when(appContext).getArguments();
            // check that application successfully starts
            assertThat(application.start(appContext)).isEqualTo(IApplication.EXIT_OK);
            // also test that versions are sill not aligned
            assertThat(application.compareWorkspaceAndStudioVersions(wsPath.toURI().toURL()))
                    .isEqualTo(VersionsComparison.COMPATIBLE_STUDIO_MAINTENANCE_LOWER_THAN_WORKSPACE);
        });
    }

    @Test
    void should_not_open_next_minor() throws Exception {
        Version v = BonitaStudioApplication.WORKSPACE_CHECK_STUDIO_BUNDLE_VERSION;
        assertThat(v).isNotNull();
        Version nextMinor = new Version(v.getMajor(), v.getMinor() + 1, v.getMicro());
        executeTestInNewWorkspace(() -> createEmptyWorkspace(nextMinor), wsPath -> {
            /*
             * The next maintenance version workspace should open smoothly
             */
            // mock the migration dialogs
            MessageDialogWithToggle warningDialog = mock(MessageDialogWithToggle.class);
            doThrow(new AssertionFailedException("Version migration warning")).when(warningDialog).open();
            MessageDialogWithToggle infoDialog = mock(MessageDialogWithToggle.class);
            doThrow(new AssertionFailedException("Version migration info")).when(infoDialog).open();
            MessageDialog errorDialog = mock(MessageDialog.class);
            when(errorDialog.open()).thenReturn(IDialogConstants.CANCEL_ID);
            // and prevent opening actual dialogs from the application
            doReturn(infoDialog).when(application).makeWorkspaceVersionMigrationDialog(any(), any(), eq(false));
            doReturn(warningDialog).when(application).makeWorkspaceVersionMigrationDialog(any(), any(),
                    eq(true));
            doReturn(errorDialog).when(application).makeWorkspaceVersionErrorDialog(any(), any(), any());
            // start application
            Map<String, Object> appArgs = Map.of("osgi.instance.area", wsPath.toString(),
                    // use DevLaunchMode to avoid some popups in case of error
                    "-pdelaunch", Boolean.TRUE);
            IApplicationContext appContext = mock(IApplicationContext.class);
            doReturn(appArgs).when(appContext).getArguments();
            // check that application successfully stopped
            assertThat(application.start(appContext)).isEqualTo(IApplication.EXIT_OK);
            verify(errorDialog, atLeastOnce()).open();
            // also test that versions are sill not aligned
            assertThat(application.compareWorkspaceAndStudioVersions(wsPath.toURI().toURL()))
                    .isEqualTo(VersionsComparison.INCOMPATIBLE_STUDIO_MINOR_LOWER_THAN_WORKSPACE);
        });
    }

    /**
     * Create an empty fake workspace with the version
     * 
     * @param version the version which workspace is supposed to have been created with
     * @return File path to workspace
     * @throws IOException
     */
    private File createEmptyWorkspace(Version version) throws IOException {
        File targetDir = Files.createTempDirectory("workspace_" + version.toString().replace('.', '_')).toFile();
        File metadata = new File(targetDir, ".metadata");
        metadata.mkdir();
        File versionFile = new File(metadata, "version.ini");
        versionFile.createNewFile();
        Properties props = new Properties();
        props.setProperty(BonitaStudioApplication.WORKSPACE_CHECK_STUDIO_BUNDLE_NAME, version.toString());
        // write Studio version and do not care about IDE version
        try (OutputStream output = new FileOutputStream(versionFile)) {
            props.store(output, null);
        }
        return targetDir;
    }

    /**
     * Execute test in the new workspace
     * 
     * @param workspaceSupplier supplies the workspace ready location
     * @param test test to execute
     */
    private void executeTestInNewWorkspace(WorkspaceSupplier workspaceSupplier, WorkspaceConsumer test)
            throws Exception {
        // get location field for hacking instance location
        Location instanceLocation = Platform.getInstanceLocation();
        Field locationField = BasicLocation.class.getDeclaredField("location");
        locationField.setAccessible(true);
        Field lockerField = BasicLocation.class.getDeclaredField("locker");
        lockerField.setAccessible(true);
        Object originalLocker = lockerField.get(instanceLocation);
        try {
            File wsPath = workspaceSupplier.get();
            // hack platform instance location for the test and reset locker
            instanceLocation.release();
            locationField.set(instanceLocation, null);
            lockerField.set(instanceLocation, null);
            instanceLocation.set(wsPath.toURI().toURL(), false);
            test.accept(wsPath);
        } finally {
            // restore original platform instance location
            instanceLocation.release();
            locationField.set(instanceLocation, null);
            lockerField.set(instanceLocation, null);
            instanceLocation.set(ORIGINAL_URL, true);
            lockerField.set(instanceLocation, originalLocker);
        }
    }

    /**
     * Supplies a workspace location
     */
    @FunctionalInterface
    static interface WorkspaceSupplier {

        /**
         * Gets a result.
         *
         * @return a result workspace file
         */
        File get() throws IOException;

    }

    /**
     * Consumes a workspace location for executing the test
     */
    @FunctionalInterface
    static interface WorkspaceConsumer {

        /**
         * Performs this operation on the given argument.
         *
         * @param t the input argument workspace
         */
        void accept(File t) throws Exception;

    }

}
