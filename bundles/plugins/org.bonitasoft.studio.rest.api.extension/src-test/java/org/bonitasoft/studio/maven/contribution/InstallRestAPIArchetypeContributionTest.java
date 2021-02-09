/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.contribution;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.bonitasoft.studio.maven.MavenLocalRepositoryContributor;
import org.bonitasoft.studio.maven.contribution.InstallRestAPIArchetypeContribution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InstallRestAPIArchetypeContributionTest {

    @Mock
    private File file;

    @Mock
    private MavenLocalRepositoryContributor mavenLocalRepositoryContributor;

    @Test
    public void should_execute_MavenLocalRepositoryContributor() throws Exception {
        final InstallRestAPIArchetypeContribution contribution = spy(new InstallRestAPIArchetypeContribution());
        doReturn(mavenLocalRepositoryContributor).when(contribution).newMavenLocalRepositoryContributor();

        contribution.execute();

        verify(mavenLocalRepositoryContributor).execute();
    }

}
