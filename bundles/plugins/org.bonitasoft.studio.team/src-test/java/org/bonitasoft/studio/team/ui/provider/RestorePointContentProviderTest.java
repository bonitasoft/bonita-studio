/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.ui.provider;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestorePointContentProviderTest {

    @Mock
    IRepositoryContainer repositoryContainer;

    @Mock
    IRepositoryContainer tagRepositoryContainer;

    @Mock
    IRepositoryContainer child;

    @Test
    public void testGetElements() throws SVNConnectorException {
        final RestorePointContentProvider restorePointContentProvider = new RestorePointContentProvider();
        Mockito.doReturn(new IRepositoryResource[] { tagRepositoryContainer }).when(repositoryContainer).getChildren();
        Mockito.doReturn("tags").when(tagRepositoryContainer).getName();
        Mockito.doReturn(new IRepositoryResource[] { child }).when(tagRepositoryContainer).getChildren();

        final Object[] res = restorePointContentProvider.getElements(repositoryContainer);
        assertThat(res).contains(child);
    }

    @Test
    public void testGetElementsWithoutTag() throws SVNConnectorException {
        final RestorePointContentProvider restorePointContentProvider = new RestorePointContentProvider();
        Mockito.doReturn(new IRepositoryResource[] { tagRepositoryContainer }).when(repositoryContainer).getChildren();

        assertThat(restorePointContentProvider.getElements(repositoryContainer)).isEmpty();
    }

}
