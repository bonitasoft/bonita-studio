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
package org.bonitasoft.studio.common.repository.ui.viewer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.runtime.IStatus;
import org.junit.Rule;
import org.junit.Test;

public class CheckboxRepositoryTreeViewerTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void should_check_default_selection() throws Exception {
        final CheckboxRepositoryTreeViewer viewer = new CheckboxRepositoryTreeViewer(realm.createComposite());
        final IRepositoryFileStore aSelectedFileStore = mock(IRepositoryFileStore.class);
        viewer.bindTree(new DataBindingContext(), toList(aRepositoryStoreWithChildren(aSelectedFileStore)));

        viewer.setCheckedElements(new Object[] { aSelectedFileStore });

        assertThat(viewer.checkedElementsObservable().contains(aSelectedFileStore)).isTrue();
    }

    @Test
    public void should_have_an_error_status_if_no_element_is_checked() throws Exception {
        final CheckboxRepositoryTreeViewer viewer = new CheckboxRepositoryTreeViewer(realm.createComposite());
        final IRepositoryFileStore aSelectedFileStore = mock(IRepositoryFileStore.class);
        final DataBindingContext context = new DataBindingContext();
        viewer.bindTree(context, toList(aRepositoryStoreWithChildren(aSelectedFileStore)));

        assertThat(context.getValidationStatusProviders().size()).isEqualTo(1);
        final ValidationStatusProvider vsp = (ValidationStatusProvider) context.getValidationStatusProviders().get(0);
        StatusAssert.assertThat((IStatus) vsp.getValidationStatus().getValue()).isNotOK();
    }

    @Test
    public void should_not_have_an_error_status_if_at_least_one_element_is_checked() throws Exception {
        final CheckboxRepositoryTreeViewer viewer = new CheckboxRepositoryTreeViewer(realm.createComposite());
        final IRepositoryFileStore aSelectedFileStore = mock(IRepositoryFileStore.class);
        final DataBindingContext context = new DataBindingContext();
        viewer.bindTree(context, toList(aRepositoryStoreWithChildren(aSelectedFileStore)));

        viewer.setCheckedElements(new Object[] { aSelectedFileStore });

        assertThat(context.getValidationStatusProviders().size()).isEqualTo(1);
        final ValidationStatusProvider vsp = (ValidationStatusProvider) context.getValidationStatusProviders().get(0);
        StatusAssert.assertThat((IStatus) vsp.getValidationStatus().getValue()).isOK();
    }

    private IRepositoryStore<?> aRepositoryStoreWithChildren(final IRepositoryFileStore... selectedFiles) {
        final IRepositoryStore<?> repositoryStore = mock(IRepositoryStore.class);
        when(repositoryStore.isEmpty()).thenReturn(false);
        for (final IRepositoryFileStore selected : selectedFiles) {
            doReturn(repositoryStore).when(selected).getParentStore();
            doReturn(true).when(selected).canBeExported();
        }
        final List<IRepositoryFileStore> children = new ArrayList<IRepositoryFileStore>();
        for (final IRepositoryFileStore selected : selectedFiles) {
            children.add(selected);
        }
        children.add(aFileStore(repositoryStore));
        children.add(aFileStore(repositoryStore));
        doReturn(children).when(repositoryStore).getChildren();
        return repositoryStore;
    }

    private IRepositoryFileStore aFileStore(final IRepositoryStore<?> repositoryStore) {
        final IRepositoryFileStore fStore = mock(IRepositoryFileStore.class);
        doReturn(repositoryStore).when(fStore).getParentStore();
        doReturn(true).when(fStore).canBeExported();
        return fStore;
    }

    private List<IRepositoryStore<? extends IRepositoryFileStore>> toList(final IRepositoryStore<? extends IRepositoryFileStore>... repositoryStores) {
        final List<IRepositoryStore<? extends IRepositoryFileStore>> input = new ArrayList<IRepositoryStore<? extends IRepositoryFileStore>>();
        for (final IRepositoryStore<?> store : repositoryStores) {
            input.add(store);
        }
        return input;
    }
}
