/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionDescriptor;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.m2e.actions.MavenLaunchConstants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuildCustomPageOperationTest {

    @Mock
    private IProgressMonitor monitor;
    @Mock
    private ILaunchManager launchManager;

    @Mock
    private ILaunchConfigurationWorkingCopy workingCopy;

    @Rule
    public TemporaryFolder tmpRule = new TemporaryFolder();

    private File baseDir;

    private MavenProject mavenProject;

    @Before
    public void setUp() throws Exception {
        final ILaunchConfigurationType launchConfigurationType = mock(ILaunchConfigurationType.class);
        when(launchManager
                .getLaunchConfigurationType(MavenLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID))
                        .thenReturn(launchConfigurationType);
        when(launchConfigurationType.newInstance((IContainer) eq(null), Mockito.notNull())).thenReturn(workingCopy);
        mavenProject = mock(MavenProject.class);
        baseDir = tmpRule.newFolder();
        when(mavenProject.getBasedir()).thenReturn(baseDir);

    }

    @Test
    public void should_run_maven_install_on_a_rest_api_extension_project() throws Exception {
        final RestAPIExtensionDescriptor descriptor = mock(RestAPIExtensionDescriptor.class);
        when(descriptor.getProject()).thenReturn(mock(IProject.class));
        final BuildCustomPageOperation operation = spy(new BuildCustomPageOperation(descriptor,
                launchManager));
        final Launch launch = mock(Launch.class);
        when(launch.isTerminated()).thenReturn(true);

        final IProcess mavenProcess = mock(IProcess.class, RETURNS_DEEP_STUBS);
        when(mavenProcess.getStreamsProxy().getOutputStreamMonitor().getContents()).thenReturn("console output");
        when(launch.getProcesses()).thenReturn(new IProcess[] { mavenProcess });

        when(mavenProject.getArtifactId()).thenReturn("defaultRestAPIExtension");
        when(mavenProject.getVersion()).thenReturn("1.0.0-SNAPSHOT");
        doReturn(Optional.of(mavenProject)).when(descriptor).getMavenProject();
        doReturn(launch).when(workingCopy).launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR, true);
        doReturn("myProfile").when(operation).activeProfiles();

        final File targetFolder = new File(baseDir, "target");
        final File archiveFile = new File(targetFolder, "defaultRestAPIExtension-1.0.0-SNAPSHOT.zip");
        targetFolder.mkdirs();
        archiveFile.createNewFile();
        final FileOutputStream out = new FileOutputStream(archiveFile);
        FileUtil.copy(BuildCustomPageOperationTest.class.getResourceAsStream("/defaultRestAPIExtension-1.0.0-SNAPSHOT.zip"),
                out);

        operation.run(monitor);

        verify(workingCopy).launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR, true);

        StatusAssert.assertThat(operation.getStatus()).isOK();

        assertThat(operation.getArchiveContent()).isNotNull();
    }

    @Test
    public void should_fail_if_maven_install_process_return_error_exit_code() throws Exception {
        final RestAPIExtensionDescriptor descriptor = mock(RestAPIExtensionDescriptor.class);
        when(descriptor.getProject()).thenReturn(mock(IProject.class));
        final BuildCustomPageOperation operation = spy(new BuildCustomPageOperation(descriptor,
                launchManager));
        final Launch launch = mock(Launch.class);
        when(launch.isTerminated()).thenReturn(true);

        final IProcess mavenProcess = mock(IProcess.class, RETURNS_DEEP_STUBS);
        when(mavenProcess.getExitValue()).thenReturn(1);
        when(mavenProcess.getStreamsProxy().getOutputStreamMonitor().getContents()).thenReturn("console output");
        when(launch.getProcesses()).thenReturn(new IProcess[] { mavenProcess });

        doReturn(Optional.of(mavenProject)).when(descriptor).getMavenProject();
        doReturn(launch).when(workingCopy).launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR, true);
        doReturn("myProfile").when(operation).activeProfiles();

        operation.run(monitor);

        verify(workingCopy).launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR, true);

        StatusAssert.assertThat(operation.getStatus()).isNotOK();

    }

}
