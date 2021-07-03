/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.coolbar;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandImageService;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

public class NewCoolbarItem extends ContributionItem implements IBonitaContributionItem {

    protected static final String NEW_DIAGRAM_CMD_ID = "org.bonitasoft.studio.diagram.command.newDiagram";
    private ICommandService commandService;
    private IHandlerService handlerService;
    private ICommandImageService imageService;

    public NewCoolbarItem() {
        commandService = PlatformUI.getWorkbench().getService(ICommandService.class);
        handlerService = PlatformUI.getWorkbench().getService(IHandlerService.class);
        imageService = PlatformUI.getWorkbench().getService(ICommandImageService.class);
    }

    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.new";
    }

    class DropdownSelectionListener extends SelectionAdapter {

        private final Menu menu;

        public DropdownSelectionListener(final ToolItem dropdown) {
            menu = new Menu(dropdown.getParent().getShell());
        }

        @Override
        public void widgetSelected(final SelectionEvent event) {
            final ToolItem item = (ToolItem) event.widget;
            final Rectangle rect = item.getBounds();
            final Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
            menu.setLocation(pt.x, pt.y + rect.height);
            menu.setVisible(true);
        }

        public boolean add(String commandId, String label, Image icon) {
            Command command = commandService.getCommand(commandId);
            if (command != null && command.isDefined() && command.isHandled()) {
                final MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
                try {
                    menuItem.setText(label != null ? label : command.getName());
                    //                    getCommandImage(commandId).ifPresent(menuItem::setImage);
                    menuItem.setImage(icon);
                } catch (NotDefinedException e1) {
                    BonitaStudioLog.error(e1);
                    menuItem.setText("unknown command: " + commandId);
                }
                menuItem.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(final SelectionEvent event) {
                        try {
                            handlerService.executeCommand(command.getId(), null);
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e);
                        }
                    }

                });
                return true;
            }
            return false;
        }

        public void addSeparator() {
            new MenuItem(menu, SWT.SEPARATOR | SWT.HORIZONTAL);
        }
    }

    @Override
    public String getText() {
        return Messages.NewButtonLabel;
    }

    @Override
    public void fill(final ToolBar toolbar, final int index, final int iconSize) {
        final ToolItem item = new ToolItem(toolbar, SWT.DROP_DOWN);
        item.setToolTipText(Messages.NewButtonTooltip);
        if (iconSize < 0) {
            item.setImage(Pics.getImage(PicsConstants.coolbar_new_48));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_new_disabled_48));
        } else {
            item.setImage(Pics.getImage(PicsConstants.coolbar_new_16));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_new_disabled_16));
        }
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (isEnabled()) {
                    final DropdownSelectionListener listener = new DropdownSelectionListener(item);
                    listener.add("org.bonitasoft.studio.identity.organization.create.command", Messages.organization,
                            Pics.getImage(PicsConstants.organization));
                    listener.addSeparator();

                    boolean hasNewBDM = RepositoryManager.getInstance()
                            .getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                            .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true) == null;
                    if (hasNewBDM) {
                        hasNewBDM = listener.add("org.bonitasoft.studio.businessobject.define",
                                Messages.businessDataModel, Pics.getImage(PicsConstants.bdm));
                    }
                    boolean hasNewBDMAccess = RepositoryManager.getInstance()
                            .getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                            .getChild("bdm_access_control.xml", true) == null;
                    if (hasNewBDMAccess) {
                        hasNewBDMAccess = listener.add(
                                "org.bonitasoft.studio.bdm.access.control.command.definebdmaccesscontrol",
                                Messages.bdmAccessControl, Pics.getImage(PicsConstants.accessControl));
                    }
                    if (hasNewBDM || hasNewBDMAccess) {
                        listener.addSeparator();
                    }
                    if (listener.add("org.bonitasoft.studio.customProfile.newFile.command", Messages.profile,
                            Pics.getImage(PicsConstants.profile))) {
                        listener.addSeparator();
                    }
                    listener.add("org.bonitasoft.studio.la.new.command", Messages.applicationDescriptor,
                            Pics.getImage(PicsConstants.application));
                    listener.addSeparator();
                    listener.add(NEW_DIAGRAM_CMD_ID, null, Pics.getImage(PicsConstants.diagram));
                    listener.addSeparator();
                    listener.add("org.bonitasoft.studio.designer.command.create.page", Messages.applicationPage,
                            Pics.getImage(PicsConstants.page));
                    listener.add("org.bonitasoft.studio.designer.command.create.layout", Messages.layout,
                            Pics.getImage(PicsConstants.page));
                    listener.add("org.bonitasoft.studio.designer.command.create.widget", Messages.customWidget,
                            Pics.getImage(PicsConstants.widget));
                    listener.add("org.bonitasoft.studio.designer.command.create.fragment", Messages.fragment,
                            Pics.getImage(PicsConstants.fragment));
                    if (listener.add("org.bonitasoft.studio.rest.api.extension.newCommand", Messages.restAPIExtension,
                            Pics.getImage(PicsConstants.restApi))) {
                        listener.addSeparator();
                    }
                    listener.widgetSelected(e);
                }
            }

        });

    }

}
