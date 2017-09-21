/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import java.util.List;

import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

/**
 * @author Baptiste Mesta
 */
public class CheckboxRepositoryTreeViewer extends ContainerCheckedTreeViewer {

    private IViewerObservableSet checkedElementsObservable;

    public CheckboxRepositoryTreeViewer(final Composite parent) {
        super(parent, SWT.VIRTUAL | SWT.BORDER | SWT.V_SCROLL);
        initialize();
    }

    private void initialize() {
        setLabelProvider(new FileStoreLabelProvider());
        setContentProvider(new ObservableListTreeContentProvider(new FileStoreObservableFactory(), new FileStoreTreeStructureAdvisor()));
    }

    public void bindTree(final DataBindingContext context, final List<IRepositoryStore<? extends IRepositoryFileStore>> stores) {
        setInput(new WritableList(stores, IRepositoryStore.class));
        final IObservableSet checkedElementsObservable = checkedElementsObservable();
        context.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                if (checkedElementsObservable.isEmpty()) {
                    return ValidationStatus.error(Messages.selectAtLeastOneArtifact);
                }
                return ValidationStatus.ok();
            }
        });
        collapseAll();
    }

    public IObservableSet checkedElementsObservable() {
        if(checkedElementsObservable == null) {
            checkedElementsObservable = ViewersObservables.observeCheckedElements(this, Object.class);
        }
        return checkedElementsObservable;
    }

}
