/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.maven;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.maven.Maven;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.businessobject.maven.MavenInstallFileCommand;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.internal.embedder.MavenExecutionContext;
import org.eclipse.m2e.core.internal.embedder.MavenImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;

public class MavenInstallFileCommandTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_install_jar_file_artifact_in_repository() throws Exception {
        final MavenImpl mavenEngine = mock(MavenImpl.class);
        final MavenExecutionContext context = mock(MavenExecutionContext.class);
        final Maven maven = mock(Maven.class);
        final MavenExecutionRequest executionRequest = mock(MavenExecutionRequest.class);
        when(executionRequest.getSystemProperties()).thenReturn(new Properties());
        when(context.getExecutionRequest()).thenReturn(executionRequest);
        when(mavenEngine.createExecutionContext()).thenReturn(context);
        when(mavenEngine.lookupComponent(Maven.class)).thenReturn(maven);

        final MavenInstallFileCommand installFileCommand = new MavenInstallFileCommand(mavenEngine);

        final IProgressMonitor progressMonitor = mock(IProgressMonitor.class);
        final Properties configuration = new Properties();
        configuration.put("groupId", "org.test");
        configuration.put("artifactId", "my-artifact");
        configuration.put("packaging", "jar");
        configuration.put("generatePom", "true");
        configuration.put("file", "path/to/a/file");
        installFileCommand.execute(configuration, progressMonitor);

        @SuppressWarnings("rawtypes")
        final ArgumentCaptor<ICallable> callableCaptor = ArgumentCaptor.forClass(ICallable.class);
        verify(context).execute(callableCaptor.capture(), eq(progressMonitor));

        final ICallable<MavenExecutionResult> callable = callableCaptor.getValue();
        callable.call(context, progressMonitor);
        final ArgumentCaptor<MavenExecutionRequest> requestCaptor = ArgumentCaptor.forClass(MavenExecutionRequest.class);
        verify(maven).execute(requestCaptor.capture());

        final Properties systemProperties = requestCaptor.getValue().getSystemProperties();
        assertThat(systemProperties).contains(entry("groupId", "org.test"));
        assertThat(systemProperties).contains(entry("artifactId", "my-artifact"));
        assertThat(systemProperties).contains(entry("packaging", "jar"));
        assertThat(systemProperties).contains(entry("generatePom", "true"));
        assertThat(systemProperties).doesNotContainKey("classifier");
        assertThat(systemProperties).containsKey("file");
    }

    @Test
    public void should_add_classifier_if_not_null() throws Exception {
        final MavenImpl mavenEngine = mock(MavenImpl.class);
        final MavenExecutionContext context = mock(MavenExecutionContext.class);
        final Maven maven = mock(Maven.class);
        final MavenExecutionRequest executionRequest = mock(MavenExecutionRequest.class);
        when(executionRequest.getSystemProperties()).thenReturn(new Properties());
        when(context.getExecutionRequest()).thenReturn(executionRequest);
        when(mavenEngine.createExecutionContext()).thenReturn(context);
        when(mavenEngine.lookupComponent(Maven.class)).thenReturn(maven);

        final MavenInstallFileCommand installFileCommand = new MavenInstallFileCommand(mavenEngine);

        final IProgressMonitor progressMonitor = mock(IProgressMonitor.class);
        final Properties configuration = new Properties();
        configuration.put("groupId", "org.test");
        configuration.put("artifactId", "my-artifact");
        configuration.put("packaging", "jar");
        configuration.put("generatePom", "true");
        configuration.put("version", "0.0.1");
        configuration.put("classifier", "classes");
        configuration.put("file", "path/to/a/file");

        installFileCommand.execute(configuration, progressMonitor);

        @SuppressWarnings("rawtypes")
        final ArgumentCaptor<ICallable> callableCaptor = ArgumentCaptor.forClass(ICallable.class);
        verify(context).execute(callableCaptor.capture(), eq(progressMonitor));

        final ICallable<MavenExecutionResult> callable = callableCaptor.getValue();
        callable.call(context, progressMonitor);
        final ArgumentCaptor<MavenExecutionRequest> requestCaptor = ArgumentCaptor.forClass(MavenExecutionRequest.class);
        verify(maven).execute(requestCaptor.capture());

        final Properties systemProperties = requestCaptor.getValue().getSystemProperties();
        assertThat(systemProperties).contains(entry("groupId", "org.test"));
        assertThat(systemProperties).contains(entry("artifactId", "my-artifact"));
        assertThat(systemProperties).contains(entry("version", "0.0.1"));
        assertThat(systemProperties).contains(entry("packaging", "jar"));
        assertThat(systemProperties).contains(entry("generatePom", "true"));
        assertThat(systemProperties).contains(entry("classifier", "classes"));
        assertThat(systemProperties).containsKey("file");
    }

    @Test
    public void should_add_pomFile_if_not_null() throws Exception {
        final MavenImpl mavenEngine = mock(MavenImpl.class);
        final MavenExecutionContext context = mock(MavenExecutionContext.class);
        final Maven maven = mock(Maven.class);
        final MavenExecutionRequest executionRequest = mock(MavenExecutionRequest.class);
        when(executionRequest.getSystemProperties()).thenReturn(new Properties());
        when(context.getExecutionRequest()).thenReturn(executionRequest);
        when(mavenEngine.createExecutionContext()).thenReturn(context);
        when(mavenEngine.lookupComponent(Maven.class)).thenReturn(maven);

        final MavenInstallFileCommand installFileCommand = new MavenInstallFileCommand(mavenEngine);

        final IProgressMonitor progressMonitor = mock(IProgressMonitor.class);
        final Properties configuration = new Properties();
        configuration.put("groupId", "org.test");
        configuration.put("artifactId", "my-artifact");
        configuration.put("packaging", "jar");
        configuration.put("generatePom", "true");
        configuration.put("version", "0.0.1");
        configuration.put("classifier", "classes");
        configuration.put("pomFile", "path/to/a/file");
        configuration.put("file", "path/to/a/file");

        installFileCommand.execute(configuration, progressMonitor);

        @SuppressWarnings("rawtypes")
        final ArgumentCaptor<ICallable> callableCaptor = ArgumentCaptor.forClass(ICallable.class);
        verify(context).execute(callableCaptor.capture(), eq(progressMonitor));

        final ICallable<MavenExecutionResult> callable = callableCaptor.getValue();
        callable.call(context, progressMonitor);
        final ArgumentCaptor<MavenExecutionRequest> requestCaptor = ArgumentCaptor.forClass(MavenExecutionRequest.class);
        verify(maven).execute(requestCaptor.capture());

        final Properties systemProperties = requestCaptor.getValue().getSystemProperties();
        assertThat(systemProperties).containsKey("pomFile");
    }

    @Test
    public void should_log_error_when_install_file_fails() throws Exception {
        final MavenImpl mavenEngine = mock(MavenImpl.class);
        final MavenInstallFileCommand installFileCommand = spy(new MavenInstallFileCommand(mavenEngine));
        doNothing().when(installFileCommand).logError(anyString(), anyString(), anyString(), anyString(),
                any(Exception.class));
        final MavenExecutionResult executionResult = mock(MavenExecutionResult.class);
        when(executionResult.hasExceptions()).thenReturn(true);
        final java.util.List<Throwable> exceptions = new ArrayList<Throwable>();
        final FileNotFoundException fileNotFoundException = new FileNotFoundException("myJarFile.jar");
        exceptions.add(fileNotFoundException);
        when(executionResult.getExceptions()).thenReturn(exceptions);
        doReturn(executionResult).when(installFileCommand)
                .execute(any(Properties.class), any(IProgressMonitor.class));
        final File jarFile = new File("myJarFile.jar");
        expectedException.expect(CoreException.class);
        installFileCommand.installFile("groupId", "artifactId", "version", "jar", "classes", jarFile);

        verify(installFileCommand).execute(any(Properties.class),
                notNull());
        verify(installFileCommand).logError("groupId", "artifactId", "jar", "version", fileNotFoundException);
    }

    @Test
    public void should_log_error_when_command_execution_fails() throws Exception {
        final MavenImpl mavenEngine = mock(MavenImpl.class);
        final MavenInstallFileCommand installFileCommand = spy(new MavenInstallFileCommand(mavenEngine));

        final MavenExecutionResult executionResult = mock(MavenExecutionResult.class);
        when(executionResult.hasExceptions()).thenReturn(true);
        final java.util.List<Throwable> exceptions = new ArrayList<Throwable>();
        final FileNotFoundException fileNotFoundException = new FileNotFoundException("myJarFile.jar");
        exceptions.add(fileNotFoundException);
        when(executionResult.getExceptions()).thenReturn(exceptions);
        final CoreException coreException = new CoreException(Status.OK_STATUS);
        doThrow(coreException).when(installFileCommand)
                .execute(any(Properties.class), any(IProgressMonitor.class));
        final File jarFile = new File("myJarFile.jar");
        expectedException.expect(CoreException.class);
        installFileCommand.installFile("groupId", "artifactId", "version", "jar", "classes", jarFile);

        verify(installFileCommand).logError("groupId", "artifactId", "jar", "version", coreException);

    }
}
