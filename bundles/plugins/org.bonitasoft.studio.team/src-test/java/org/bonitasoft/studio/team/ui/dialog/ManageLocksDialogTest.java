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
package org.bonitasoft.studio.team.ui.dialog;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.provider.TeamRepositoryLabelDecorator;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Composite;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ManageLocksDialogTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    private ManageLocksDialog manageLocksDialog;

    @Mock
    private Repository repository;

    @Mock
    private IRepositoryFileStore fileStore;

    @Mock
    private IResource resource;

    @Mock
    private TeamRepositoryLabelDecorator decorator;

    @Mock
    private LockDialogModel lockDialogModel;

    private Composite composite;

    @Mock
    private IRepositoryStore<? extends IRepositoryFileStore> repositoryStore;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        composite = realm.createComposite();
        when(lockDialogModel.getSharedRepository()).thenReturn(repository);
        when(lockDialogModel.getDecorator()).thenReturn(decorator);
        manageLocksDialog = new ManageLocksDialog(composite.getShell(), lockDialogModel);
        manageLocksDialog.create();
    }

    @Test
    public void should_lockSelection_call_lockModelDialog() throws Exception {
        manageLocksDialog.lockSelection(fileStore);

        verify(lockDialogModel).handleLockSelection(fileStore);
    }

    @Test
    public void should_unlockSelection_call_lockModelDialog() throws Exception {
        manageLocksDialog.unlockSelection(fileStore);

        verify(lockDialogModel).handleUnlockSelection(fileStore);
    }

}
