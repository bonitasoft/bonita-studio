/**
 * 
 */
package org.bonitasoft.studio.groovy.ui.viewer;

import java.util.ArrayList;

import org.bonitasoft.studio.groovy.ui.Activator;
import org.eclipse.core.expressions.Expression;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.IPageService;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.KeyBindingService;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.internal.SlavePageService;
import org.eclipse.ui.internal.SlavePartService;
import org.eclipse.ui.internal.SlaveSelectionService;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.contexts.SlaveContextService;
import org.eclipse.ui.internal.handlers.LegacyHandlerService;
import org.eclipse.ui.internal.menus.SlaveMenuService;
import org.eclipse.ui.internal.progress.WorkbenchSiteProgressService;
import org.eclipse.ui.internal.services.IServiceLocatorCreator;
import org.eclipse.ui.internal.services.IWorkbenchLocationService;
import org.eclipse.ui.internal.services.ServiceLocator;
import org.eclipse.ui.internal.services.WorkbenchLocationService;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.services.IDisposable;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.ui.services.IServiceScopes;

public class EmbeddedEditorSite implements IEditorSite {

    private IEclipseContext e4Context;
    private ISelectionProvider selectionProvider;
    protected final ServiceLocator serviceLocator;
    private WorkbenchSiteProgressService progressService;
    private KeyBindingService keyBindingService;
    private SlavePageService pageService;
    private SlavePartService partService;
    private SlaveSelectionService selectionService;
    private SlaveContextService contextService;
    private SlaveMenuService menuService;

    public EmbeddedEditorSite() {
        e4Context = getMWindow().getContext();
        IServiceLocatorCreator slc = (IServiceLocatorCreator) e4Context.get(IServiceLocatorCreator.class.getName());
        IWorkbenchWindow workbenchWindow = getWorkbenchWindow();
        this.serviceLocator = (ServiceLocator) slc.createServiceLocator(workbenchWindow, null, new IDisposable() {

            @Override
            public void dispose() {
                // not sure what to do here
            }
        }, e4Context);
        initializeDefaultServices();
    }

    /**
     * Initialize the local services.
     */
    private void initializeDefaultServices() {
        IHandlerService handlerService = new LegacyHandlerService(e4Context);
        e4Context.set(IHandlerService.class.getName(), handlerService);

        serviceLocator.registerService(IWorkbenchLocationService.class,
                new WorkbenchLocationService(IServiceScopes.PARTSITE_SCOPE, getWorkbenchWindow().getWorkbench(),
                        getWorkbenchWindow(), this, null, null, 2));
        // added back for legacy reasons
        serviceLocator.registerService(IWorkbenchPartSite.class, this);

        e4Context.set(IKeyBindingService.class.getName(), new ContextFunction() {

            @Override
            public Object compute(IEclipseContext context, String contextKey) {
                if (keyBindingService == null) {
                    keyBindingService = new KeyBindingService(EmbeddedEditorSite.this);
                }

                return keyBindingService;
            }
        });
        e4Context.set(IPageService.class.getName(), new ContextFunction() {

            @Override
            public Object compute(IEclipseContext context, String contextKey) {
                if (pageService == null) {
                    pageService = new SlavePageService(context.getParent().get(IPageService.class));
                }

                return pageService;
            }
        });
        e4Context.set(IPartService.class.getName(), new ContextFunction() {

            @Override
            public Object compute(IEclipseContext context, String contextKey) {
                if (partService == null) {
                    partService = new SlavePartService(context.getParent().get(IPartService.class));
                }
                return partService;
            }
        });
        e4Context.set(ISelectionService.class.getName(), new ContextFunction() {

            @Override
            public Object compute(IEclipseContext context, String contextKey) {
                if (selectionService == null) {
                    selectionService = new SlaveSelectionService(context.getParent().get(ISelectionService.class));
                }
                return selectionService;
            }
        });
        e4Context.set(IContextService.class.getName(), new ContextFunction() {

            @Override
            public Object compute(IEclipseContext context, String contextKey) {
                if (contextService == null) {
                    contextService = new SlaveContextService(context.getParent().get(IContextService.class),
                            Expression.TRUE);
                }
                return contextService;
            }
        });
        e4Context.set(IMenuService.class.getName(), new ContextFunction() {

            @Override
            public Object compute(IEclipseContext context, String contextKey) {
                if (menuService == null) {
                    menuService = new SlaveMenuService(context.getParent().get(IMenuService.class), getMWindow());
                }
                return menuService;
            }
        });
    }

    protected static MWindow getMWindow() {
        Workbench workbench = (Workbench) PlatformUI.getWorkbench();
        WorkbenchWindow activeWorkbenchWindow = (WorkbenchWindow) workbench.getActiveWorkbenchWindow();
        return activeWorkbenchWindow.getModel();
    }

    @Override
    public String getId() {
        return "defaultGroovyEmbeddedEditor";
    }

    @Override
    public String getPluginId() {
        return Activator.PLUGIN_ID;
    }

    @Override
    public String getRegisteredName() {
        return "defaultGroovyEmbeddedEditor";
    }

    @Override
    public void registerContextMenu(String menuId, MenuManager menuManager, ISelectionProvider selectionProvider) {
        PartSite.registerContextMenu(menuId, menuManager, selectionProvider, true, getPart(), e4Context,
                new ArrayList(1));
    }

    @Override
    public void registerContextMenu(MenuManager menuManager, ISelectionProvider selectionProvider) {
        registerContextMenu(getId(), menuManager, selectionProvider);
    }

    @Override
    public IKeyBindingService getKeyBindingService() {
        return (IKeyBindingService) e4Context.get(IKeyBindingService.class.getName());
    }

    @Override
    public IWorkbenchPart getPart() {
        return null;
    }

    @Override
    public IWorkbenchPage getPage() {
        return getWorkbenchWindow().getActivePage();
    }

    @Override
    public ISelectionProvider getSelectionProvider() {
        return selectionProvider;
    }

    @Override
    public Shell getShell() {
        // Compatibility: This method should not be used outside the UI
        // thread... but since this condition
        // was not always in the JavaDoc, we still try to return our best guess
        // about the shell if it is
        // called from the wrong thread.
        Display currentDisplay = Display.getCurrent();
        if (currentDisplay == null) {
            // Uncomment this to locate places that try to access the shell from
            // a background thread
            // WorkbenchPlugin.log(new Exception("Error:
            // IWorkbenchSite.getShell() was called outside the UI thread. Fix
            // this code.")); //$NON-NLS-1$

            return getWorkbenchWindow().getShell();
        }

        Control control = (Control) getMWindow().getWidget();
        if (control != null && !control.isDisposed()) {
            return control.getShell();
        }
        // likely means the part has been destroyed, return the parent window's
        // shell, we don't just arbitrarily return the workbench window's shell
        // because we may be in a detached window
        MWindow window = e4Context.get(MWindow.class);
        return window == null ? getWorkbenchWindow().getShell() : (Shell) window.getWidget();
    }

    @Override
    public IWorkbenchWindow getWorkbenchWindow() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    }

    @Override
    public void setSelectionProvider(ISelectionProvider provider) {
        selectionProvider = provider;
    }

    @Override
    public Object getAdapter(Class adapter) {
        return null;
    }

    @Override
    public Object getService(Class api) {
        return serviceLocator.getService(api);
    }

    @Override
    public boolean hasService(Class api) {
        return serviceLocator.hasService(api);
    }

    @Override
    public IEditorActionBarContributor getActionBarContributor() {
        return null;
    }

    @Override
    public IActionBars getActionBars() {
        return new IActionBars() {

            @Override
            public void updateActionBars() {

            }

            @Override
            public void setGlobalActionHandler(String arg0, IAction arg1) {

            }

            @Override
            public IToolBarManager getToolBarManager() {
                return new ToolBarManager();
            }

            @Override
            public IStatusLineManager getStatusLineManager() {
                return new StatusLineManager();
            }

            @Override
            public IServiceLocator getServiceLocator() {
                return serviceLocator;
            }

            @Override
            public IMenuManager getMenuManager() {
                return new MenuManager();
            }

            @Override
            public IAction getGlobalActionHandler(String arg0) {
                return null;
            }

            @Override
            public void clearGlobalActionHandlers() {
                // TODO Auto-generated method stub

            }
        };
    }

    @Override
    public void registerContextMenu(MenuManager menuManager, ISelectionProvider selectionProvider,
            boolean includeEditorInput) {

    }

    @Override
    public void registerContextMenu(String menuId, MenuManager menuManager, ISelectionProvider selectionProvider,
            boolean includeEditorInput) {

    }

}
