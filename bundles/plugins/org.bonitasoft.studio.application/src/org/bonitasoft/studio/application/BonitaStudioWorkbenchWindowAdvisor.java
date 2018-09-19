/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.application;

import org.bonitasoft.studio.application.dialog.ExitDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.AutomaticSwitchPerspectivePartListener;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.EditorHistory;
import org.eclipse.ui.internal.EditorHistoryItem;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.ide.IDEInternalPreferences;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.util.PrefUtil;

public class BonitaStudioWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private final IWorkbenchWindow window;

    public BonitaStudioWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer) {
        super(configurer);
        configurer.setShowProgressIndicator(true);
        window = configurer.getWindow();
    }

    @Override
    public ActionBarAdvisor createActionBarAdvisor(final IActionBarConfigurer configurer) {
        return new ActionBarAdvisor(configurer) {

            @Override
            protected void makeActions(final IWorkbenchWindow window) {
                super.makeActions(window);
                register(ActionFactory.UNDO.create(window));
                register(ActionFactory.REDO.create(window));
                register(ActionFactory.PREFERENCES.create(window));
                register(ActionFactory.ABOUT.create(window));
            }
        };
    }

    @Override
    public void postWindowCreate() {
        window.getShell().setMaximized(true);
    }

    @Override
    public void openIntro() {
        PrefUtil.getAPIPreferenceStore().setValue(IWorkbenchPreferenceConstants.SHOW_INTRO, true);
        PrefUtil.saveAPIPrefs();
        EditorHistory editorHistory = ((Workbench) ((WorkbenchPage) window.getActivePage()).getWorkbenchWindow()
                .getWorkbench()).getEditorHistory();
        EditorHistoryItem[] items = editorHistory.getItems();
        Display display = window.getShell().getDisplay();
        if(items.length > 0) {
            for (EditorHistoryItem item : items) {
                item.restoreState();
                IEditorDescriptor descriptor = item.getDescriptor();
                display.asyncExec(() -> {
                    try {
                        IEditorPart findEditor = window.getActivePage().findEditor(item.getInput());
                        if (findEditor == null) {
                            window.getActivePage().openEditor(item.getInput(), descriptor.getId());
                        }
                    } catch (PartInitException e) {
                        BonitaStudioLog.error(e);
                    }
                });
            }
        }else {
            display.asyncExec(new Runnable() {

                @Override
                public void run() {
                    if (!PlatformUtil.isIntroOpen()) {
                        PlatformUtil.openIntroIfNoOtherEditorOpen();
                    }
                }
            });
        }
    }

    /**
     * Register to selection service to update button enablement
     * Register the Automatic Perspective switch part listener
     */
    @Override
    public void postWindowOpen() {
        final MWindow model = ((WorkbenchPage) window.getActivePage()).getWindowModel();
        model.getContext().get(EPartService.class).addPartListener(new AutomaticSwitchPerspectivePartListener());
        final Object widget = model.getWidget();
        if (widget instanceof Shell) {
            ((Widget) widget).setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_MAIN_SHELL);
        }
    }

    @Override
    public boolean preWindowShellClose() {
        if (PlatformUI.getWorkbench().getWorkbenchWindowCount() > 1) {
            return true;
        }
        // the user has asked to close the last window, while will cause the
        // workbench to close in due course - prompt the user for confirmation
        if (promptOnExit(getWindowConfigurer().getWindow().getShell())) {
            if (PlatformUI.isWorkbenchRunning() && window != null && window.getActivePage() != null) {
                window.getActivePage().saveAllEditors(true);
            }
            return true;

        }
        return false;
    }

    private boolean promptOnExit(Shell parentShell) {
        final IPreferenceStore store = IDEWorkbenchPlugin.getDefault()
                .getPreferenceStore();
        final boolean promptOnExit = store
                .getBoolean(IDEInternalPreferences.EXIT_PROMPT_ON_CLOSE_LAST_WINDOW);

        if (promptOnExit) {
            if (parentShell == null) {
                final IWorkbenchWindow workbenchWindow = window;
                if (workbenchWindow != null) {
                    parentShell = workbenchWindow.getShell();
                }
            }
            if (parentShell != null) {
                parentShell.setMinimized(false);
                parentShell.forceActive();
            }
            final MessageDialogWithToggle dlg = ExitDialog.openExitDialog(parentShell);
            if (dlg.getReturnCode() != IDialogConstants.OK_ID) {
                return false;
            }
            if (dlg.getToggleState()) {
                store
                        .setValue(
                                IDEInternalPreferences.EXIT_PROMPT_ON_CLOSE_LAST_WINDOW,
                                false);
                IDEWorkbenchPlugin.getDefault().savePluginPreferences();
            }
        }

        return true;
    }
}
