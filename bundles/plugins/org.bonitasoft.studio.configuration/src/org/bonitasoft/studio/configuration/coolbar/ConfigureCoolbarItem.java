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
package org.bonitasoft.studio.configuration.coolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.bonitasoft.studio.configuration.ui.handler.ConfigureHandler;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * @author Romain Bioteau
 */
public class ConfigureCoolbarItem extends ContributionItem implements IBonitaContributionItem {

    private static final String CONFIGURE_COMMAND_ID = "org.bonitasoft.studio.configuration.configure";
    private static final String CREATE_ENVIRONMENT_COMMAND_ID = "org.bonitasoft.studio.configuration.environment.create.command";
    private ConfigureHandler configureHandler;
    private Label label;
    private ToolItem item;

    public ConfigureCoolbarItem() {
        configureHandler = new ConfigureHandler();
    }

    private Command getCommand(String commandId) {
        final ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
        return service.getCommand(commandId);
    }

    @Override
    public String getId() {
        return CONFIGURE_COMMAND_ID;
    }

    @Override
    public String getText() {
        return Messages.ConfigureButtonLabel;
    }

    @Override
    public boolean isEnabled() {
        return configureHandler.isEnabled();
    }

    class DropdownSelectionListener extends SelectionAdapter {

        private final Menu menu;

        public DropdownSelectionListener(final ToolItem dropdown) {
            menu = new Menu(dropdown.getParent().getShell());
        }

        public void add(final Command command) throws NotDefinedException {
            final MenuItem menuItem = new MenuItem(menu, SWT.NONE);
            menuItem.setText(command.getName());
            menuItem.addListener(SWT.Selection, e -> new CommandExecutor().executeCommand(command.getId(), null));
        }

        public void add(final String item) {
            final MenuItem menuItem = new MenuItem(menu, SWT.CHECK);
            menuItem.setText(item);
            String configuration = ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
            if (configuration == null || configuration.isEmpty()) {
                configuration = ConfigurationPlugin.getDefault().getPreferenceStore()
                        .getDefaultString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
            }
            menuItem.setSelection(configuration.equals(item));
            menuItem.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final SelectionEvent event) {
                    final MenuItem selected = (MenuItem) event.widget;
                    selected.setSelection(true);
                    ConfigurationPlugin.getDefault().getPreferenceStore()
                            .setValue(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION, selected.getText());
                    AbstractFileStore.refreshExplorerView();
                    final Command cmd = getCommand(CONFIGURE_COMMAND_ID);
                    final IHandlerService handlerService = PlatformUI.getWorkbench()
                            .getService(IHandlerService.class);
                    try {
                        final Parameterization p = new Parameterization(cmd.getParameter("configuration"),
                                selected.getText());
                        handlerService.executeCommand(new ParameterizedCommand(cmd, new Parameterization[] { p }),
                                null);
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    }
                }
            });
        }

        @Override
        public void widgetSelected(final SelectionEvent event) {
            if (event.detail == SWT.DROP_DOWN) {
                final ToolItem item = (ToolItem) event.widget;
                final Rectangle rect = item.getBounds();
                final Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
                menu.setLocation(pt.x, pt.y + rect.height);
                menu.setVisible(true);
            } else {
                final Command cmd = getCommand(CONFIGURE_COMMAND_ID);
                final String configuration = ConfigurationPlugin.getDefault().getPreferenceStore()
                        .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
                final IHandlerService handlerService = PlatformUI.getWorkbench()
                        .getService(IHandlerService.class);
                try {
                    final Parameterization p = new Parameterization(cmd.getParameter("configuration"), configuration);
                    handlerService.executeCommand(new ParameterizedCommand(cmd, new Parameterization[] { p }), null);
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    @Override
    public void fill(final ToolBar toolbar, final int index, final int iconSize) {
        item = new ToolItem(toolbar, SWT.DROP_DOWN);
        item.setToolTipText(Messages.configureButtonTooltip);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM);
        if (iconSize < 0) {
            item.setImage(Pics.getImage(PicsConstants.coolbar_configure_32));
            item.setHotImage(Pics.getImage(PicsConstants.coolbar_configure_hot_32));
        } else {
            item.setImage(Pics.getImage(PicsConstants.coolbar_configure_24));
            item.setHotImage(Pics.getImage(PicsConstants.coolbar_configure_hot_24));
        }
        setEnabled(false);
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (isEnabled()) {
                    final DropdownSelectionListener listener = new DropdownSelectionListener(item);
                    final List<String> confName = new ArrayList<>();
                    RepositoryManager.getInstance()
                            .getCurrentRepository()
                            .map(repo -> repo.getRepositoryStore(EnvironmentRepositoryStore.class))
                            .map(environmentStore -> environmentStore.getChildren().stream())
                            .ifPresent(environements -> environements
                                    .map(IDisplayable::toDisplayName).filter(Optional::isPresent).map(Optional::get)
                                    .peek(confName::add)
                                    .forEach(listener::add));

                    try {
                        listener.add(getCommand(CREATE_ENVIRONMENT_COMMAND_ID));
                    } catch (NotDefinedException notDefinedEx) {
                        BonitaStudioLog.error(notDefinedEx);
                    }
                    listener.widgetSelected(e);
                }
            }

        });

    }

    @Override
    public void setLabelControl(Label label) {
        this.label = label;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (item != null && !item.isDisposed()) {
            item.setEnabled(enabled);
        }
        if (label != null && !label.isDisposed()) {
            label.setEnabled(enabled);
        }
    }

}
