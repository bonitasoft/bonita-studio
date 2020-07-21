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
package org.bonitasoft.studio.common.repository;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

import org.assertj.core.api.Assertions;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryNameValidatorTest {

    @Spy
    private RepositoryNameValidator rnv;

    @Mock
    private RepositoryManager repoManager;
    @Mock
    private AbstractRepository repo;
    @Mock
    private IProject project;
    @Mock
    private IWorkspace ws;
    @Mock
    private IWorkspaceRoot wsRoot;

    @Before
    public void setup() throws Throwable {
        doReturn(repoManager).when(rnv).getRepositoryManager();
        doReturn(repo).when(repoManager).getRepository("existing");
        doReturn(null).when(repoManager).getRepository("notExisting");
        doReturn(repo).when(repoManager).getCurrentRepository();
        doReturn(project).when(repo).getProject();
        doReturn(ws).when(project).getWorkspace();
        doReturn(wsRoot).when(ws).getRoot();
        doReturn(new IResource[] {}).when(wsRoot).members();
    }

    @Test
    public void testNotValid_withExisting() throws Exception {
        Assertions.assertThat(rnv.isValid("existing")).isEqualTo(Messages.projectAlreadyExist);
    }

    @Test
    public void testNotValid_withEmpty() throws Exception {
        Assertions.assertThat(rnv.isValid("")).isEqualTo(Messages.createNewProject_emptyText);
    }

    @Test
    public void testNotValid_withinvalidFileCharactersAsterisk() throws Exception {
        doReturn(null).when(repoManager).getRepository(anyString());
        Assertions.assertThat(rnv.isValid("a*")).isEqualTo(String.format(Messages.createNewProject_invalidCharacter, "*"));
    }

    @Test
    public void testNotValid_withinvalidFileCharactersDoubleQuote() throws Exception {
        doReturn(null).when(repoManager).getRepository(anyString());
        Assertions.assertThat(rnv.isValid("a\""))
                .isEqualTo(String.format(Messages.createNewProject_invalidCharacter, "\""));
    }

}
