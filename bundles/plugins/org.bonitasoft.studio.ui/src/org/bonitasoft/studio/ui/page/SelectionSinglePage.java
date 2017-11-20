/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.page;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.ui.provider.FileStoreLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SelectionSinglePage<T extends IRepositoryStore<? extends IRepositoryFileStore>> extends SelectionPage<T> {

    public static final String TABLE_ID = "org.bonitasoft.studio.la.ui.control.SelectionSinglePageTable";

    public SelectionSinglePage(RepositoryAccessor repositoryAccessor, Class<T> type, FileStoreLabelProvider provider) {
        super(repositoryAccessor, type, provider);
    }

    @Override
    protected TableViewer createTableViewer(Composite mainComposite) {
        TableViewer viewer = new TableViewer(mainComposite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        viewer.getControl().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, TABLE_ID);
        return viewer;
    }

    public IRepositoryFileStore getSingleSelection() {
        if (tableViewer.getSelection().isEmpty()) {
            throw new IllegalStateException("Selection is empty");
        }
        return (IRepositoryFileStore) ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
    }

}
