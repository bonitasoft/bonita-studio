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
package org.bonitasoft.studio.groovy.ui.viewer;

import org.bonitasoft.studio.groovy.ui.Activator;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.EditorActionBars;
import org.eclipse.ui.internal.KeyBindingService;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.services.IServiceLocator;

/**
 * @author Romain Bioteau
 */
public class DummyEditorSite implements IEditorSite {

    private final GroovyEditor editor;
    private ISelectionProvider selectionProvider;
    private IKeyBindingService keyBindingService;
    private final Shell parentShell;

    public DummyEditorSite(Shell parentShell, GroovyEditor editor) {
        this.editor = editor;
        this.parentShell = parentShell;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPartSite#getId()
     */
    @Override
    public String getId() {
        return "org.bonitasoft.studio.DummyEditorSite";
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPartSite#getKeyBindingService()
     */
    @Override
    public IKeyBindingService getKeyBindingService() {
        final IEclipseContext context = (IEclipseContext) parentShell.getData("org.eclipse.e4.ui.shellContext");
        final IEclipseContext activeLeaf = context.getActiveLeaf();
        activeLeaf.set(IKeyBindingService.class.getName(), new ContextFunction() {

            @Override
            public Object compute(IEclipseContext context, String contextKey) {
                if (keyBindingService == null) {
                    keyBindingService = new KeyBindingService(DummyEditorSite.this);
                }

                return keyBindingService;
            }
        });
        return (IKeyBindingService) activeLeaf.get(IKeyBindingService.class.getName());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPartSite#getPart()
     */
    @Override
    public IWorkbenchPart getPart() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPartSite#getPluginId()
     */
    @Override
    public String getPluginId() {
        return Activator.PLUGIN_ID;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPartSite#getRegisteredName()
     */
    @Override
    public String getRegisteredName() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.ui.IWorkbenchPartSite#registerContextMenu(org.eclipse.jface.
     * action.MenuManager, org.eclipse.jface.viewers.ISelectionProvider)
     */
    @Override
    public void registerContextMenu(MenuManager arg0, ISelectionProvider arg1) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.ui.IWorkbenchPartSite#registerContextMenu(java.lang.String,
     * org.eclipse.jface.action.MenuManager,
     * org.eclipse.jface.viewers.ISelectionProvider)
     */
    @Override
    public void registerContextMenu(String arg0, MenuManager arg1, ISelectionProvider arg2) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchSite#getPage()
     */
    @Override
    public IWorkbenchPage getPage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                ? PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() : null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchSite#getSelectionProvider()
     */
    @Override
    public ISelectionProvider getSelectionProvider() {
        return editor.getSelectionProvider();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchSite#getShell()
     */
    @Override
    public Shell getShell() {

        // Compatibility: This method should not be used outside the UI
        // thread... but since this condition
        // was not always in the JavaDoc, we still try to return our best guess
        // about the shell if it is
        // called from the wrong thread.
        final Display currentDisplay = Display.getCurrent();
        if (currentDisplay == null) {
            // Uncomment this to locate places that try to access the shell from
            // a background thread
            // WorkbenchPlugin.log(new Exception("Error:
            // IWorkbenchSite.getShell() was called outside the UI thread. Fix
            // this code.")); //$NON-NLS-1$

            final IWorkbenchWindow workbenchWindow = getWorkbenchWindow();
            if (workbenchWindow != null) {
                return workbenchWindow.getShell();
            }
            return null;
        }
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        MWindow window = null;
        if (activeWorkbenchWindow != null) {
            window = ((WorkbenchWindow) activeWorkbenchWindow).getModel();
            final Control control = (Control) window.getWidget();
            if (control != null && !control.isDisposed()) {
                return control.getShell();
            }
        }
        // likely means the part has been destroyed, return the parent window's
        // shell, we don't just arbitrarily return the workbench window's shell
        // because we may be in a detached window
        return window == null ? getWorkbenchWindow().getShell() : (Shell) window.getWidget();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchSite#getWorkbenchWindow()
     */
    @Override
    public IWorkbenchWindow getWorkbenchWindow() {
        final IEclipseContext context = ((Workbench) PlatformUI.getWorkbench()).getContext();
        return (IWorkbenchWindow) context.get(ISources.ACTIVE_WORKBENCH_WINDOW_NAME);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.ui.IWorkbenchSite#setSelectionProvider(org.eclipse.jface.
     * viewers.ISelectionProvider)
     */
    @Override
    public void setSelectionProvider(ISelectionProvider selectionProvider) {
        this.selectionProvider = selectionProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(Class arg0) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.services.IServiceLocator#getService(java.lang.Class)
     */
    @Override
    public Object getService(Class api) {
        return PlatformUI.getWorkbench().getService(api);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.services.IServiceLocator#hasService(java.lang.Class)
     */
    @Override
    public boolean hasService(Class api) {
        return PlatformUI.getWorkbench().hasService(api);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IEditorSite#getActionBarContributor()
     */
    @Override
    public IEditorActionBarContributor getActionBarContributor() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IEditorSite#getActionBars()
     */
    @Override
    public IActionBars getActionBars() {
        return new EditorActionBars((WorkbenchPage) getPage(),
                (IServiceLocator) PlatformUI.getWorkbench().getService(IServiceLocator.class), GroovyEditor.EDITOR_ID);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.ui.IEditorSite#registerContextMenu(org.eclipse.jface.action.
     * MenuManager, org.eclipse.jface.viewers.ISelectionProvider, boolean)
     */
    @Override
    public void registerContextMenu(MenuManager arg0, ISelectionProvider arg1, boolean arg2) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IEditorSite#registerContextMenu(java.lang.String,
     * org.eclipse.jface.action.MenuManager,
     * org.eclipse.jface.viewers.ISelectionProvider, boolean)
     */
    @Override
    public void registerContextMenu(String arg0, MenuManager arg1, ISelectionProvider arg2, boolean arg3) {

    }

}
