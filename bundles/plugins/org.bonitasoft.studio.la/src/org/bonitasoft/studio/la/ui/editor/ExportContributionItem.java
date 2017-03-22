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
package org.bonitasoft.studio.la.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.ui.OverwriteFileFilter;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.core.ExportApplicationRunnable;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;

public class ExportContributionItem extends ContributionItem {

    private static final String ID = "org.bonitasoft.studio.la.ui.editor.export";
    private final FormEditor formEditor;

    public ExportContributionItem(FormEditor formEditor) {
        super(ID);
        this.formEditor = formEditor;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        final ToolItem item = new ToolItem(parent, SWT.PUSH);
        item.setToolTipText(Messages.export);
        item.setImage(LivingApplicationPlugin.getImage("icons/export.png"));
        item.addListener(SWT.Selection, event -> onClick(parent.getShell()));
    }

    private void onClick(Shell shell) {
        final Optional<String> path = getPath(shell);
        path.ifPresent(filePath -> export(shell, filePath));
    }

    protected Optional<String> getPath(Shell shell) {
        final DirectoryDialog fd = new DirectoryDialog(shell, SWT.SAVE | SWT.SHEET);
        fd.setText(Messages.exportApplicationDescriptor);
        return Optional.ofNullable(fd.open());
    }

    protected void export(Shell shell, String path) {
        final String name = formEditor.getEditorInput().getName();
        final List<IRepositoryFileStore> selectedFiles = new OverwriteFileFilter(path)
                .resolveConflicts(Arrays.asList(toFileStore(name)));
        if (!selectedFiles.isEmpty()) {
            final ExportApplicationRunnable operation = new ExportApplicationRunnable(path, selectedFiles);
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
                MessageDialog.openInformation(shell, Messages.exportDoneTitle,
                        String.format(Messages.exportDoneMessage));
            } catch (InvocationTargetException | InterruptedException e) {
                new ExceptionDialogHandler().openErrorDialog(shell, Messages.exportFailedTitle, e);
            }
        }
    }

    private ApplicationFileStore toFileStore(String name) {
        return RepositoryManager.getInstance().getRepositoryStore(ApplicationRepositoryStore.class).getChild(name);
    }
}
