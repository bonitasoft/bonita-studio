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

import static org.mockito.Mockito.lenient;

import java.util.Optional;
import java.util.function.Supplier;

import org.assertj.core.api.Assertions;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RepositoryNameValidatorTest {

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

    public RepositoryNameValidator createFixture(Supplier<Boolean> newProjectSupplier) throws Exception {
        RepositoryNameValidator rnv = Mockito.spy(new RepositoryNameValidator(newProjectSupplier));
        lenient().doReturn("existing").when(repo).getProjectId();
        lenient().doReturn(repoManager).when(rnv).getRepositoryManager();
        lenient().doReturn(repo).when(repoManager).getRepository("existing");
        lenient().doReturn(Optional.of(repo)).when(repoManager).getCurrentRepository();
        return rnv;
    }

    @Test
    void testNotValid_withExisting() throws Exception {
        RepositoryNameValidator validator = createFixture(() -> true );
        Assertions.assertThat(validator.isValid("existing")).isEqualTo(String.format(Messages.projectAlreadyExist,"existing"));
    }

    @Test
    void testValid_withExisting_if_not_new() throws Exception {
        RepositoryNameValidator validator = createFixture(() -> false );
        Assertions.assertThat(validator.isValid("existing")).isNull();
    }

    @Test
    void testNotValid_withEmpty() throws Exception {
        RepositoryNameValidator validator = createFixture(() -> false );
        Assertions.assertThat(validator.isValid("")).isEqualTo(Messages.createNewProject_emptyText);
    }

    @Test
    void testNotValid_withinvalidFileCharactersAsterisk() throws Exception {
        RepositoryNameValidator validator = createFixture(() -> false );
        Assertions.assertThat(validator.isValid("a*")).isEqualTo(String.format(Messages.createNewProject_invalidCharacter, "*"));
    }

    @Test
    void testNotValid_withinvalidFileCharactersDoubleQuote() throws Exception {
        RepositoryNameValidator validator = createFixture(() -> false );
        Assertions.assertThat(validator.isValid("a\""))
                .isEqualTo(String.format(Messages.createNewProject_invalidCharacter, "\""));
    }

}
