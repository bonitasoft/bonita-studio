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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.bonitasoft.studio.application.dialog.ExitDialog;
import org.bonitasoft.studio.application.views.CustomObjectActionContributorManager;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.perspectives.AutomaticSwitchPerspectivePartListener;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.ide.EditorAreaDropAdapter;
import org.eclipse.ui.internal.ide.IDEInternalPreferences;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.util.PrefUtil;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.MarkerTransfer;
import org.eclipse.ui.part.ResourceTransfer;

public class BonitaStudioWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private final IWorkbenchWindow window;
    private static final Set<String> EDITOR_TYPE_TO_CLOSE_ON_EXIT = new HashSet<>();
    static {
        EDITOR_TYPE_TO_CLOSE_ON_EXIT.add("org.bonitasoft.studio.customProfile.editor");
        EDITOR_TYPE_TO_CLOSE_ON_EXIT.add("org.bonitasoft.studio.la.editor");
    }
    
    
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
    }

    /**
     * Register to selection service to update button enablement
     * Register the Automatic Perspective switch part listener
     */
    @Override
    public void postWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.addEditorAreaTransfer(EditorInputTransfer.getInstance());
        configurer.addEditorAreaTransfer(ResourceTransfer.getInstance());
        configurer.addEditorAreaTransfer(FileTransfer.getInstance());
        configurer.addEditorAreaTransfer(MarkerTransfer.getInstance());
        configurer.configureEditorAreaDropListener(new EditorAreaDropAdapter(
                configurer.getWindow()));

        final MWindow model = ((WorkbenchPage) window.getActivePage()).getWindowModel();
        model.getContext().get(EPartService.class).addPartListener(new AutomaticSwitchPerspectivePartListener());
        final Object widget = model.getWidget();
        if (widget instanceof Shell) {
            ((Widget) widget).setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_MAIN_SHELL);
        }
        // Replace ObjectActionContributorManager with filtered actions
        CustomObjectActionContributorManager.getManager();
    }

    @Override
    public boolean preWindowShellClose() {
        if (PlatformUI.getWorkbench().getWorkbenchWindowCount() > 1) {
            return true;
        }
        // the user has asked to close the last window, while will cause the
        // workbench to close in due course - prompt the user for confirmation
        if (promptOnExit(getWindowConfigurer().getWindow().getShell())) {
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
        
        IWorkbenchPage activePage = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage();
        IEditorReference[] editorReferences = activePage
                .getEditorReferences();
        return activePage.closeEditors(Stream.of(editorReferences)
                .filter(ref -> EDITOR_TYPE_TO_CLOSE_ON_EXIT.contains(ref.getId()))
                .toArray(IEditorReference[]::new), true);
    }
}
