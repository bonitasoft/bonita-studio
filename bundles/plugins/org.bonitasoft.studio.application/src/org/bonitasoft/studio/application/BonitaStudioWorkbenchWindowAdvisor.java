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

import org.bonitasoft.studio.application.views.CustomObjectActionContributorManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.common.ui.perspectives.AutomaticSwitchPerspectivePartListener;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.ide.EditorAreaDropAdapter;
import org.eclipse.ui.internal.registry.EditorRegistry;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.MarkerTransfer;
import org.eclipse.ui.part.ResourceTransfer;

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

    }

    /**
     * Register to selection service to update button enablement
     * Register the Automatic Perspective switch part listener
     */
    @Override
    public void postWindowOpen() {
        closeEmptyEditors();

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

    void closeEmptyEditors() {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (activePage != null) {
            IEditorReference[] refs = activePage.getEditorReferences();
            if (refs != null) {
                for (IEditorReference ref : refs) {
                    String editorId = ref.getId();
                    if (EditorRegistry.EMPTY_EDITOR_ID.equals(editorId)) {
                        BonitaStudioLog.warning(
                                "Empty editor reference found ! This might be caused by a non graceful shutdown.",
                                editorId);
                        activePage.closeEditors(new IEditorReference[] { ref }, false);
                    }
                }
            }
        }
    }

   
}
