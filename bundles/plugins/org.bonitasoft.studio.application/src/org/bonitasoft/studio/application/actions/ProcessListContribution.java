/**
 * Copyright (C2013) 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.application.actions;

import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorReference;

public class ProcessListContribution extends ContributionItem {

    public ProcessListContribution() {

    }

    public ProcessListContribution(String id) {
        super(id);
    }

    @Override
    public void fill(final Menu menu, final int index) {
        menu.addMenuListener(new MenuListener() {

            @Override
            public void menuHidden(MenuEvent e) {

            }

            @Override
            public void menuShown(MenuEvent e) {
                MenuItem item;
                for (MenuItem it : menu.getItems()) {
                    it.dispose();
                }
                if (!PlatformUtil.isHeadless()) {
                    DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance()
                            .getRepositoryStore(DiagramRepositoryStore.class);
                    List<DiagramFileStore> recentDiagrams = diagramStore.getRecentChildren(10);
                    for (int i = 0; i < recentDiagrams.size(); i++) {
                        //	for (final DiagramFileStore diagram:recentDiagrams){
                        final DiagramFileStore diagram = recentDiagrams.get(recentDiagrams.size() - 1 - i);
                        item = new MenuItem(menu, SWT.NONE, index);
                        item.setText(diagram.getDisplayName());

                        item.addSelectionListener(new SelectionAdapter() {

                            @Override
                            public void widgetSelected(SelectionEvent e) {
                                final IEditorReference openEditor = PlatformUtil.getOpenEditor(diagram.getName());
                                if (openEditor != null) {
                                    PlatformUtil.swtichToOpenedEditor(openEditor);
                                } else {
                                    diagram.open();
                                }
                            }
                        });

                    }

                    if (menu.getItemCount() == 0) {
                        item = new MenuItem(menu, SWT.NONE);
                        item.setText(Messages.noProcessAvailable);
                        item.setEnabled(false);
                    }
                }
            }
        });
    }
}
