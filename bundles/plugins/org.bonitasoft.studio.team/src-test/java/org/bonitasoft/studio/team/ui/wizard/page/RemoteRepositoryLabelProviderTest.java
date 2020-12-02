/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.ui.wizard.page;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.bonitasoft.studio.team.ui.wizard.RemoteRepositoryFinder;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RemoteRepositoryLabelProviderTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Mock
    RemoteRepositoryFinder remoteRepositoryFinder;

    @Test
    public void should_not_display_needToMigrated_when_emptyRepository() {
        final RemoteRepositoryLabelProvider provider = spy(new RemoteRepositoryLabelProvider(remoteRepositoryFinder));
        final ViewerCell cell = mock(ViewerCell.class);
        final Map<IRepositoryContainer, String> bonitaRepositories = new HashMap<IRepositoryContainer, String>();
        final List<IRepositoryResource> emptyContainers = new ArrayList<IRepositoryResource>();
        final List<IRepositoryResource> unknownContainers = new ArrayList<IRepositoryResource>();
        final IRepositoryContainer container = mock(IRepositoryContainer.class);

        bonitaRepositories.put(container, "empty");
        emptyContainers.add(container);

        doReturn("myRepository").when(container).getName();
        doReturn(bonitaRepositories).when(remoteRepositoryFinder).getBonitaRepositories();
        doReturn(emptyContainers).when(remoteRepositoryFinder).getEmptyContainers();
        doReturn(unknownContainers).when(remoteRepositoryFinder).getUnkownContainers();
        doReturn(false).when(provider).allRepositoriesContains(container);
        doReturn(container).when(cell).getElement();

        provider.update(cell);
        verify(provider, Mockito.never()).setMigrationText(any(StyledString.class));

    }

    @Test
    public void should_display_needToMigrated_when_NotEmptyRepository() {
        final RemoteRepositoryLabelProvider provider = spy(new RemoteRepositoryLabelProvider(remoteRepositoryFinder));
        final ViewerCell cell = mock(ViewerCell.class);
        final Map<IRepositoryContainer, String> bonitaRepositories = new HashMap<IRepositoryContainer, String>();
        final List<IRepositoryResource> emptyContainers = new ArrayList<IRepositoryResource>();
        final List<IRepositoryResource> unknownContainers = new ArrayList<IRepositoryResource>();
        final IRepositoryContainer container = mock(IRepositoryContainer.class);

        bonitaRepositories.put(container, "empty");

        doReturn("myRepository").when(container).getName();
        doReturn(bonitaRepositories).when(remoteRepositoryFinder).getBonitaRepositories();
        doReturn(emptyContainers).when(remoteRepositoryFinder).getEmptyContainers();
        doReturn(unknownContainers).when(remoteRepositoryFinder).getUnkownContainers();
        doReturn(true).when(provider).allRepositoriesContains(container);
        doReturn(true).when(provider).canBeMigrated(bonitaRepositories, container);
        doReturn(container).when(cell).getElement();

        provider.update(cell);
        verify(provider).setMigrationText(any(StyledString.class));

    }
}
