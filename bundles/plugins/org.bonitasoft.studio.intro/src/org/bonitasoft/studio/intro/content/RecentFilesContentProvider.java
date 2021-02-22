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
package org.bonitasoft.studio.intro.content;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.intro.Activator;
import org.bonitasoft.studio.intro.content.actions.OpenEditorFromHistoryItemAction;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.EditorHistory;
import org.eclipse.ui.internal.EditorHistoryItem;
import org.eclipse.ui.internal.Workbench;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RecentFilesContentProvider implements DOMContentProvider {

    private static final String ID = "recent-files";
    public static final int MAX_HISTORY_SIZE = 5;

    @Override
    public boolean appliesTo(String id) {
        return ID.equals(id);
    }

    @Override
    public void createContent(Document dom, Element parent) {
        if (!RepositoryManager.getInstance().hasActiveRepository()
                || !RepositoryManager.getInstance().getCurrentRepository().isLoaded()) {
            return;
        }

        Element ul = dom.createElement("ul");
        parent.appendChild(ul);

        EditorHistory history = ((Workbench) PlatformUI.getWorkbench()).getEditorHistory();
        EditorHistoryItem[] historyItems = history.getItems();
        int maxElem = MAX_HISTORY_SIZE;
        for (int i = 0; i < historyItems.length; i++) {
            if (maxElem == 0)
                break;
            final EditorHistoryItem item = historyItems[i];
            if (item.isRestored() || item.restoreState().isOK()) {
                IEditorInput input = item.getInput();
                if(input == null) {
                    break;
                }
                IResource resource = input.getAdapter(IResource.class);
                if (resource != null && resource.isAccessible()) {
                    Element li = dom.createElement("li");
                    Element a = dom.createElement("a");
                    a.setAttribute("class", "hover:text-red-600");
                    a.setAttribute("title", item.getToolTipText());
                    try {
                        a.setAttribute("href",
                                String.format("http://org.eclipse.ui.intro/runAction?pluginId=%s&class=%s&file=%s",
                                        Activator.PLUGIN_ID,
                                        OpenEditorFromHistoryItemAction.class.getName(),
                                        URLEncoder.encode(item.getName(), "UTF-8")));
                    } catch (DOMException | UnsupportedEncodingException e) {
                        BonitaStudioLog.error(e);
                    }
                    a.setTextContent(item.getName());
                    li.appendChild(a);
                    maxElem--;
                    ul.appendChild(li);
                }
            }
        }
    }

}
