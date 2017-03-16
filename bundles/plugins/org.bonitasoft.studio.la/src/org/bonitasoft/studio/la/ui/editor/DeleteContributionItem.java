/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.editor;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.editor.FormEditor;

public class DeleteContributionItem extends ContributionItem {

    private static final String ID = "org.bonitasoft.studio.la.ui.editor.delete";
    private FormEditor formEditor;

    public DeleteContributionItem(FormEditor formEditor) {
        super(ID);
        this.formEditor = formEditor;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        final ToolItem item = new ToolItem(parent, SWT.PUSH);
        item.setToolTipText(Messages.delete);
        item.setImage(LivingApplicationPlugin.getImage("icons/delete_24.png"));
        item.addListener(SWT.Selection, event -> onClick(parent.getShell()));
    }

    private void onClick(Shell shell) {
        String name = formEditor.getEditorInput().getName();
        if (MessageDialog.openQuestion(shell, Messages.deleteConfirmation,
                String.format(Messages.deleteSingleConfirmationMessage, "\n" + name))) {
            ApplicationFileStore applicationFileStore = RepositoryManager.getInstance()
                    .getRepositoryStore(ApplicationRepositoryStore.class).getChild(name);
            applicationFileStore.close();
            applicationFileStore.delete();
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.deleteDoneTitle,
                    Messages.deleteSingleDoneMessage);
        }
    }

}
