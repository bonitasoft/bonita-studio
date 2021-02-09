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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateBonitaBPMProjectOperationTest {

    private CreateBonitaBPMProjectOperation createBonitaBPMProjectOperation;
    @Mock
    private IProgressMonitor monitor;

    @Mock
    private IWorkspace workspace;
    @Mock
    private IWorkspaceRoot root;

    @Mock
    private IProject project;
    @Mock
    private IJavaProject javaProject;
    @Mock
    private IFolder metaInfFolder;
    @Mock
    private IFile manifestFile;
    @Mock
    private IFile buildFile;
    @Mock
    private IEclipsePreferences prefNode;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createBonitaBPMProjectOperation = spy(new CreateBonitaBPMProjectOperation(workspace, "my project"));
        doReturn(root).when(workspace).getRoot();
        doReturn(javaProject).when(createBonitaBPMProjectOperation).asJavaProject();
        doReturn(buildFile).when(project).getFile("build.properties");
        doReturn(prefNode).when(createBonitaBPMProjectOperation).jdtLaunchingPrefNode();
    }

    @Test
    public void should_create_a_new_eclipse_project() throws Exception {
        doReturn(project).when(root).getProject("my project");

        createBonitaBPMProjectOperation.addNature("myCustomNature").addBuilder("aBuilderId").run(monitor);

        final IProject project = createBonitaBPMProjectOperation.getProject();
        assertThat(project).isNotNull();
        final InOrder orderedProjectCreation = inOrder(project);
        orderedProjectCreation.verify(project).create(monitor);
        orderedProjectCreation.verify(project).open(monitor);
        orderedProjectCreation.verify(project).setDescription(any(IProjectDescription.class), eq(monitor));

        verify(javaProject).setOption(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_11);
        verify(javaProject).setOption(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_11);
        verify(javaProject).setOption(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_11);
        verify(javaProject).setOption(JavaCore.CORE_JAVA_BUILD_INVALID_CLASSPATH, JavaCore.IGNORE);
        verify(prefNode).put(JavaRuntime.PREF_STRICTLY_COMPATIBLE_JRE_NOT_AVAILABLE, JavaCore.IGNORE);
    }

}
