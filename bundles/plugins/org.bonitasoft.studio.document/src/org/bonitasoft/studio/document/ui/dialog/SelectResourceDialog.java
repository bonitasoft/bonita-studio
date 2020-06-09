/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.document.ui.dialog;

import org.bonitasoft.studio.document.core.repository.DocumentFileStore;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.document.i18n.Messages;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Aurelien Pupier
 */
public class SelectResourceDialog extends FileStoreSelectDialog {

    public SelectResourceDialog(final IShellProvider parentShell) {
        super(parentShell);
    }

    public SelectResourceDialog(final Shell parentShell) {
        super(parentShell);
    }

    public DocumentFileStore getSelectedDocument() {
        return (DocumentFileStore) getSelectedFileStore();
    }

    @Override
    protected Class<DocumentRepositoryStore> getRepositoryStoreClass() {
        return DocumentRepositoryStore.class;
    }

    @Override
    protected String getDialogTitle() {
        return Messages.selectResourceDialogTitle;
    }

    @Override
    protected String getDialogDescription() {
        return Messages.selectResourceDescription;
    }

}
