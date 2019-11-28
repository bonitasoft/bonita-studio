/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.intro.content.actions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.intro.content.RecentFilesContentProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.EditorHistory;
import org.eclipse.ui.internal.EditorHistoryItem;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class OpenEditorFromHistoryItemAction implements IIntroAction {

    @Override
    public void run(IIntroSite site, Properties params) {
        String itemName;
        try {
            itemName = URLDecoder.decode((String) params.get("file"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            BonitaStudioLog.error(e);
            return;
        }
        EditorHistory history = ((Workbench) PlatformUI.getWorkbench()).getEditorHistory();
        EditorHistoryItem item = findItem(history, RecentFilesContentProvider.MAX_HISTORY_SIZE, itemName)
                .orElseThrow(() -> new IllegalStateException("No EditorHistoryItem found with name: " + itemName));
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (page != null) {
            try {
                if (!item.isRestored()) {
                    item.restoreState();
                }
                IEditorInput input = item.getInput();
                IEditorDescriptor desc = item.getDescriptor();
                if (input == null || !input.exists() || desc == null) {
                    String title = WorkbenchMessages.OpenRecent_errorTitle;
                    String msg = NLS.bind(WorkbenchMessages.OpenRecent_unableToOpen, itemName);
                    MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title,
                            msg);
                    history.remove(item);
                } else {
                    page.openEditor(input, desc.getId());
                }
            } catch (PartInitException e2) {
                String title = WorkbenchMessages.OpenRecent_errorTitle;
                MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, e2
                        .getMessage());
                history.remove(item);
            }
        }

    }

    private Optional<EditorHistoryItem> findItem(EditorHistory history, int n, String itemName) {
        return Stream.of(history.getItems())
                .limit(n)
                .filter(item -> Objects.equals(item.getName(), itemName))
                .findFirst();
    }

}
