/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.core.maven.contribution;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InstallLocalRepositoryContributionTest {

    @Mock
    private File file;

    @Mock
    private MavenLocalRepositoryContributor mavenLocalRepositoryContributor;

    @Test
    public void should_execute_MavenLocalRepositoryContributor() throws Exception {
        final InstallLocalRepositoryContribution contribution = spy(new InstallLocalRepositoryContribution());
        doReturn(mavenLocalRepositoryContributor).when(contribution).newMavenLocalRepositoryContributor();

        contribution.execute();

        verify(mavenLocalRepositoryContributor).execute();
    }

}