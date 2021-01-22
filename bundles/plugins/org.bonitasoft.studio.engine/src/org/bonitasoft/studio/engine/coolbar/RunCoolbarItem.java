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
package org.bonitasoft.studio.engine.coolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.environment.EnvironmentFactory;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * @author Romain Bioteau
 */
public class RunCoolbarItem extends ContributionItem implements IBonitaContributionItem {

    private Command getCommand() {
        final ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
        return service.getCommand("org.bonitasoft.studio.engine.runCommand");
    }


    @Override
    public String getText() {
        return Messages.RunButtonLabel;
    }

    private boolean isSelectionRunnable() {
        Optional<IEditorPart> editor = PlatformUtil.findActiveEditor();
        final boolean isADiagram = editor.filter(DiagramEditor.class::isInstance).isPresent();
        if (isADiagram) {
            DiagramEditor diagramEditor = (DiagramEditor) editor.get();
            final List<?> selectedEditParts = diagramEditor.getDiagramGraphicalViewer().getSelectedEditParts();
            if (selectedEditParts != null && !selectedEditParts.isEmpty()) {
                final Object selectedEp = selectedEditParts.iterator().next();
                if (selectedEp != null) {
                    return true;
                }
            }
        }
        return false;
    }
    

    class DropdownSelectionListener extends SelectionAdapter {

        private final Menu menu;

        public DropdownSelectionListener(final ToolItem dropdown) {
            menu = new Menu(dropdown.getParent().getShell());
        }

        public void add(final String item) {
            if (item.equals(org.bonitasoft.studio.configuration.i18n.Messages.newEnvironmentLabel)) {
                final MenuItem menuItem = new MenuItem(menu, SWT.NONE);
                menuItem.setText(item);
                menuItem.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        final IRepositoryStore<?> store = RepositoryManager.getInstance()
                                .getRepositoryStore(EnvironmentRepositoryStore.class);
                        final InputDialog dialog = new InputDialog(Display.getDefault().getActiveShell(),
                                org.bonitasoft.studio.configuration.i18n.Messages.newEnvironmentLabel,
                                org.bonitasoft.studio.configuration.i18n.Messages.name,
                                "",
                                input -> {
                                    if (input == null || input.isEmpty()) {
                                        return org.bonitasoft.studio.configuration.i18n.Messages.emptyName;
                                    }

                                    if (input.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)
                                            || store.getChild(
                                                    input + "." + EnvironmentRepositoryStore.ENV_EXT, true) != null) {
                                        return org.bonitasoft.studio.configuration.i18n.Messages.alreadyExists;
                                    }
                                    return null;
                                });
                        if (dialog.open() == Dialog.OK) {
                            final String name = dialog.getValue();
                            final IRepositoryFileStore file = store
                                    .createRepositoryFileStore(name + "." + EnvironmentRepositoryStore.ENV_EXT);
                            final Environment env = EnvironmentFactory.eINSTANCE.createEnvironment();
                            env.setName(name);
                            file.save(env);
                        }
                    }
                });
            } else {
                final MenuItem menuItem = new MenuItem(menu, SWT.CHECK);
                menuItem.setText(item);
                final String configuration = ConfigurationPlugin.getDefault().getPreferenceStore()
                        .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
                menuItem.setSelection(configuration.equals(item));
                menuItem.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(final SelectionEvent event) {
                        final MenuItem selected = (MenuItem) event.widget;
                        selected.setSelection(true);
                        ConfigurationPlugin.getDefault().getPreferenceStore()
                                .setValue(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION, selected.getText());
                        final Command cmd = getCommand();
                        final IHandlerService handlerService = PlatformUI.getWorkbench().getService(IHandlerService.class);
                        try {
                            final Parameterization p = new Parameterization(cmd.getParameter("configuration"),
                                    selected.getText());
                            handlerService.executeCommand(new ParameterizedCommand(cmd, new Parameterization[] { p }), null);
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e);
                        }
                    }

                });
            }
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
                final Command cmd = getCommand();
                final String configuration = ConfigurationPlugin.getDefault().getPreferenceStore()
                        .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
                final IHandlerService handlerService = PlatformUI.getWorkbench().getService(IHandlerService.class);
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
        final ToolItem item = new ToolItem(toolbar, SWT.DROP_DOWN);
        item.setToolTipText(Messages.RunButtonLabel);
        if (iconSize < 0) {
            item.setImage(Pics.getImage(PicsConstants.coolbar_run_48));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_run_disabled_48));
        } else {
            item.setImage(Pics.getImage(PicsConstants.coolbar_run_16));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_run_disabled_16));
        }
        item.setEnabled(false);
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (isEnabled()) {
                    final DropdownSelectionListener listener = new DropdownSelectionListener(item);
                    final List<String> confName = new ArrayList<>();

                    final EnvironmentRepositoryStore environmentStore = RepositoryManager.getInstance()
                            .getCurrentRepository()
                            .getRepositoryStore(EnvironmentRepositoryStore.class);
                    for (final IRepositoryFileStore file : environmentStore.getChildren()) {
                        confName.add(file.getDisplayName());
                    }
                    for (final String c : confName) {
                        listener.add(c);
                    }
                    listener.add(org.bonitasoft.studio.configuration.i18n.Messages.newEnvironmentLabel);
                    listener.widgetSelected(e);
                }
            }

        });

    }
    
    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.run";
    }


    @Override
    public boolean isEnabled() {
        final Command cmd = getCommand();
        return cmd.isEnabled() && isSelectionRunnable();
    }

 

}
