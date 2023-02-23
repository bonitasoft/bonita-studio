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

import java.util.stream.Stream;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class RecentlyModifiedContribution extends ContributionItem {

    public RecentlyModifiedContribution() {

    }

    public RecentlyModifiedContribution(String id) {
        super(id);
    }

    @Override
    public void fill(final Menu menu, final int index) {
        var recentlyModified = new MenuItem(menu, SWT.CASCADE);
        recentlyModified.setText(Messages.RecentlyModified);
        recentlyModified.setImage(Pics.getImage("menuIcons/recently.png", ApplicationPlugin.getDefault()));
        var modifiedResourcesMenu = new Menu(menu.getShell(), SWT.DROP_DOWN);
        recentlyModified.setMenu(modifiedResourcesMenu);
        modifiedResourcesMenu.addMenuListener(new MenuAdapter() {

            @Override
            public void menuShown(MenuEvent e) {
                Stream.of(modifiedResourcesMenu.getItems()).forEach(MenuItem::dispose);
                var diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
                diagramStore.getRecentChildren(10).stream().forEach(diagram -> {
                    IDisplayable display = Adapters.adapt(diagram, IDisplayable.class);
                    Assert.isNotNull(display);
                    var item = new MenuItem(modifiedResourcesMenu, SWT.PUSH);
                    item.setText(display.getDisplayName());
                    /*
                     * FIXME : old code was using Pics.getImage(PicsConstants.diagram)
                     * why not using display.getIcon() ?
                     */
                    item.setImage(display.getIcon());
                    item.setImage(Pics.getImage(PicsConstants.diagram));
                    item.addSelectionListener(new SelectionAdapter() {

                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            diagram.open();
                        }

                    });
                });
                if (modifiedResourcesMenu.getItemCount() == 0) {
                    var item = new MenuItem(modifiedResourcesMenu, SWT.PUSH);
                    item.setText(Messages.noProcessAvailable);
                    item.setEnabled(false);
                }
            }
        });
        menu.addMenuListener(new MenuAdapter() {

            @Override
            public void menuShown(MenuEvent e) {
                recentlyModified.setEnabled(isEnabled());
            }

        });
    }

    @Override
    public boolean isEnabled() {
        return RepositoryManager.getInstance().getCurrentRepository().filter(IRepository::isLoaded).isPresent();
    }
}
