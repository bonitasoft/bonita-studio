/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.JavaModel;
import org.eclipse.jdt.launching.environments.IExecutionEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProjectClasspathFactoryTest {

    @Mock
    private IProject project;
    @Mock
    private Repository repository;

    private ProjectClasspathFactory bonitaBPMProjectClasspath;
    @Mock
    private IProgressMonitor monitor;
    @Mock
    private IClasspathEntry classPathEntry;
    @Mock
    private IPath jrePath;

    @Mock
    private IExecutionEnvironment executionEnvironment;
    @Mock
    private IJavaProject javaProject;
    @Mock
    private JavaModel javaModel;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bonitaBPMProjectClasspath = spy(new ProjectClasspathFactory());
        doReturn(classPathEntry).when(bonitaBPMProjectClasspath).newContainerEntry(any(IPath.class), any(Boolean.class));
        doReturn(classPathEntry).when(bonitaBPMProjectClasspath).newSourceEntry(any(IPath.class));
        doReturn(jrePath).when(bonitaBPMProjectClasspath).newJREContainerPath(executionEnvironment);
        doReturn(executionEnvironment).when(bonitaBPMProjectClasspath).javaRuntimeEnvironment();
        doReturn(javaProject).when(bonitaBPMProjectClasspath).asJavaProject(repository);
        doReturn(newArrayList(aSourceRepositoryStore(), aRepositoryStore())).when(repository).getAllStores();
        doNothing().when(bonitaBPMProjectClasspath).updateCompilerJavaCompliance(notNull(String.class));
    }

    private IRepositoryStore<?> aSourceRepositoryStore() {
        final SourceRepositoryStore<?> sourceRepositoryStore = mock(SourceRepositoryStore.class);
        doReturn(mock(IFolder.class)).when(sourceRepositoryStore).getResource();
        return sourceRepositoryStore;
    }

    private IRepositoryStore<?> aRepositoryStore() {
        final IRepositoryStore<?> repositoryStore = mock(IRepositoryStore.class);
        doReturn(mock(IFolder.class)).when(repositoryStore).getResource();
        return repositoryStore;
    }

    @Test
    public void should_create_set_raw_classpath_to_project_with_default_entries_if_classpath_doesnt_exists() throws Exception {
        doReturn(false).when(bonitaBPMProjectClasspath).classpathExists(repository);

        bonitaBPMProjectClasspath.create(repository, monitor);

        verify(javaProject).setRawClasspath(notNull(IClasspathEntry[].class), eq(true), eq(monitor));
    }

    @Test
    public void should_create_do_nothing_if_classpath_exists() throws Exception {
        doReturn(true).when(bonitaBPMProjectClasspath).classpathExists(repository);

        bonitaBPMProjectClasspath.create(repository, monitor);

        verify(javaProject, never()).setRawClasspath(notNull(IClasspathEntry[].class), eq(true), eq(monitor));
    }

    @Test
    public void should_refreshExternalArchives_and_flush_build_path() throws Exception {
        doReturn(javaModel).when(bonitaBPMProjectClasspath).javaModel();
        doNothing().when(bonitaBPMProjectClasspath).flushBuildPath(repository, monitor);
        doReturn(true).when(bonitaBPMProjectClasspath).classpathExists(repository);

        bonitaBPMProjectClasspath.refresh(repository, monitor);

        verify(javaModel).refreshExternalArchives(null, monitor);
        verify(bonitaBPMProjectClasspath).flushBuildPath(repository, monitor);
    }

}
