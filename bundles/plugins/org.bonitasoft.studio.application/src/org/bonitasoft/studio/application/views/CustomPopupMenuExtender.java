/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.ContributionsAnalyzer;
import org.eclipse.e4.ui.internal.workbench.OpaqueElementUtil;
import org.eclipse.e4.ui.internal.workbench.swt.AbstractPartRenderer;
import org.eclipse.e4.ui.internal.workbench.swt.MenuService;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuSeparator;
import org.eclipse.e4.ui.model.application.ui.menu.MPopupMenu;
import org.eclipse.e4.ui.model.application.ui.menu.impl.MenuFactoryImpl;
import org.eclipse.e4.ui.workbench.renderers.swt.MenuManagerRenderer;
import org.eclipse.e4.ui.workbench.swt.factories.IRendererFactory;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.internal.IObjectActionContributor;
import org.eclipse.ui.internal.PluginActionContributionItem;
import org.eclipse.ui.internal.ViewerActionBuilder;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

public class CustomPopupMenuExtender implements IMenuListener2,
        IRegistryChangeListener {

    private static Set<String> INCLUDES = new HashSet<>();
    static {
        INCLUDES.add("team.main");
        INCLUDES.add("org.eclipse.ui.file.refresh");
    }

    /**
     * The bit in <code>bitSet</code> that stores whether the static actions
     * have been read from the registry.
     */
    private static final int STATIC_ACTION_READ = 1;

    /**
     * The bit in <code>bitSet</code> that stores whether the editor input
     * should be included for the sake of object contributions.
     */
    private static final int INCLUDE_EDITOR_INPUT = 1 << 1;

    private final MenuManager menu;

    private SubMenuManager menuWrapper;

    private final ISelectionProvider selProvider;

    private final IWorkbenchPart part;

    private Map<String, ViewerActionBuilder> staticActionBuilders = null;

    /**
     * The boolean properties maintained by this extender. A bit set is used to
     * save memory.
     */
    private int bitSet = 0;

    private ArrayList<PluginActionContributionItem> actionContributionCache = new ArrayList<>();
    private boolean cleanupNeeded = false;

    private MPart modelPart;

    /**
     * The context that will be used to create the popup menu's context under.
     */
    private IEclipseContext context;

    public CustomPopupMenuExtender(String id, MenuManager menu, ISelectionProvider prov,
            IWorkbenchPart part, IEclipseContext context) {
        this(id, menu, prov, part, context, true);
    }

    public CustomPopupMenuExtender(final String id, final MenuManager menu,
            final ISelectionProvider prov, final IWorkbenchPart part, IEclipseContext context,
            final boolean includeEditorInput) {
        super();
        this.menu = menu;
        this.selProvider = prov;
        this.part = part;
        this.context = context;
        this.modelPart = part.getSite().getService(MPart.class);
        if (includeEditorInput) {
            bitSet |= INCLUDE_EDITOR_INPUT;
        }
        menu.addMenuListener(this);
        if (!menu.getRemoveAllWhenShown()) {
            menuWrapper = new SubMenuManager(menu);
            menuWrapper.setVisible(true);
        }
        createModelFor(id);
        addMenuId(id);

        Platform.getExtensionRegistry().addRegistryChangeListener(this);
    }

    private void createModelFor(String id) {
        if (id == null) {
            id = getClass().getName() + '.' + System.identityHashCode(this);
        }
        menuModel = null;
        for (MMenu item : modelPart.getMenus()) {
            if (id.equals(item.getElementId()) && item instanceof MPopupMenu
                    && item.getTags().contains("popup")) { //$NON-NLS-1$
                menuModel = (MPopupMenu) item;
                break;
            }
        }
        if (menuModel == null) {
            menuModel = MenuFactoryImpl.eINSTANCE.createPopupMenu();
            menuModel.setElementId(id);
            menuModel.getTags().add(ContributionsAnalyzer.MC_POPUP);
            modelPart.getMenus().add(menuModel);
        }
        IRendererFactory factory = modelPart.getContext().get(IRendererFactory.class);
        AbstractPartRenderer obj = factory.getRenderer(menuModel, null);
        if (obj instanceof MenuManagerRenderer) {
            ((MenuManagerRenderer) obj).linkModelToManager(menuModel, menu);
        }
        registerE4Support();
        cleanUpContributionCache();
    }

    private void registerE4Support() {
        if (menuModel.getWidget() == null && menu.getMenu() != null) {
            MenuService.registerMenu(menu.getMenu().getParent(), menuModel, context);
        }
    }

    // getMenuId() added by Dan Rubel (dan_rubel@instantiations.com)
    /**
     * Return the menu identifiers for this extender.
     *
     * @return The set of all identifiers that represent this extender.
     */
    public Set<String> getMenuIds() {
        if (staticActionBuilders == null) {
            return Collections.emptySet();
        }

        return staticActionBuilders.keySet();
    }

    /**
     * <p>
     * Adds another menu identifier to this extender. An extender can represent
     * many menu identifiers. These identifiers should represent the same menu
     * manager, selection provider and part. Duplicate identifiers are
     * automatically ignored.
     * </p>
     * <p>
     * For example, it is necessary to filter out duplicate identifiers for
     * <code>CompilationUnitEditor</code> instances, as these define both
     * <code>"#CompilationUnitEditorContext"</code> and
     * <code>"org.eclipse.jdt.ui.CompilationUnitEditor.EditorContext"</code>
     * as menu identifier for the same pop-up menu. We don't want to contribute
     * duplicate items in this case.
     * </p>
     *
     * @param menuId
     *        The menu identifier to add to this extender; should not be
     *        <code>null</code>.
     */
    public final void addMenuId(final String menuId) {
        bitSet &= ~STATIC_ACTION_READ;
        if (menuModel != null) {
            List<String> tags = menuModel.getTags();
            String tag = "popup:" + menuId; //$NON-NLS-1$
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }
        readStaticActionsFor(menuId);
    }

    /**
     * Determines whether this extender would be the same as another extender
     * created with the given values. Two extenders are equivalent if they have
     * the same menu manager, selection provider and part (i.e., if the menu
     * they represent is about to show, they would populate it with duplicate
     * values).
     *
     * @param menuManager
     *        The menu manager with which to compare; may be
     *        <code>null</code>.
     * @param selectionProvider
     *        The selection provider with which to compare; may be
     *        <code>null</code>.
     * @param part
     *        The part with which to compare; may be <code>null</code>.
     * @return <code>true</code> if the menu manager, selection provider and
     *         part are all the same.
     */
    public final boolean matches(final MenuManager menuManager,
            final ISelectionProvider selectionProvider,
            final IWorkbenchPart part) {
        return (this.menu == menuManager)
                && (this.selProvider == selectionProvider)
                && (this.part == part);
    }

    /**
     * Contributes items registered for the currently active editor.
     */
    private void addEditorActions(IMenuManager mgr, Set<IObjectActionContributor> alreadyContributed) {
        ISelectionProvider activeEditor = new ISelectionProvider() {

            @Override
            public void addSelectionChangedListener(
                    ISelectionChangedListener listener) {
                throw new UnsupportedOperationException(
                        "This ISelectionProvider is static, and cannot be modified."); //$NON-NLS-1$
            }

            @Override
            public ISelection getSelection() {
                if (part instanceof IEditorPart) {
                    final IEditorPart editorPart = (IEditorPart) part;
                    return new StructuredSelection(new Object[] { editorPart
                            .getEditorInput() });
                }

                return new StructuredSelection(new Object[0]);
            }

            @Override
            public void removeSelectionChangedListener(
                    ISelectionChangedListener listener) {
                throw new UnsupportedOperationException(
                        "This ISelectionProvider is static, and cannot be modified."); //$NON-NLS-1$
            }

            @Override
            public void setSelection(ISelection selection) {
                throw new UnsupportedOperationException(
                        "This ISelectionProvider is static, and cannot be modified."); //$NON-NLS-1$
            }
        };

        if (CustomObjectActionContributorManager.getManager().contributeObjectActions(part, mgr,
                activeEditor, alreadyContributed)) {
            mgr.add(new Separator());
        }
    }

    /**
     * Contributes items registered for the object type(s) in
     * the current selection.
     */
    private void addObjectActions(IMenuManager mgr, Set<IObjectActionContributor> alreadyContributed) {
        if (selProvider != null) {
            if (CustomObjectActionContributorManager.getManager().contributeObjectActions(part,
                    mgr,
                    selProvider, alreadyContributed)) {
                mgr.add(new Separator());
            }
        }
    }

    /**
     * Disposes all of the static actions.
     */
    private final void clearStaticActions() {
        bitSet &= ~STATIC_ACTION_READ;
        if (staticActionBuilders != null) {
            final Iterator<ViewerActionBuilder> staticActionBuilderItr = staticActionBuilders
                    .values().iterator();
            while (staticActionBuilderItr.hasNext()) {
                final ViewerActionBuilder staticActionBuilder = staticActionBuilderItr.next();
                staticActionBuilder.dispose();
            }
        }
    }

    /**
     * Adds static items to the context menu.
     */
    private void addStaticActions(IMenuManager mgr) {
        if (staticActionBuilders != null) {
            final Iterator<ViewerActionBuilder> staticActionBuilderItr = staticActionBuilders
                    .values().iterator();
            while (staticActionBuilderItr.hasNext()) {
                final ViewerActionBuilder staticActionBuilder = staticActionBuilderItr.next();
                staticActionBuilder.contribute(mgr, null, true);
            }
        }
    }

    /**
     * Notifies the listener that the menu is about to be shown.
     */
    @Override
    public void menuAboutToShow(IMenuManager mgr) {
        INCLUDES.add("compareWithMenu");
        INCLUDES.add("replaceWithMenu");
        INCLUDES.add("org.eclipse.jdt.ui.source.menu");
        INCLUDES.add("org.eclipse.jdt.ui.refactoring.menu");
        IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        StructuredSelection repositoryProject = new StructuredSelection(project);
        ISelection selection = selProvider.getSelection();
        if (repositoryProject.equals(selection)) {
            INCLUDES.remove("compareWithMenu");
            INCLUDES.remove("replaceWithMenu");
            INCLUDES.remove("org.eclipse.jdt.ui.source.menu");
            INCLUDES.remove("org.eclipse.jdt.ui.refactoring.menu");
        }
        Object resource = ((StructuredSelection) selection).getFirstElement();
        if (resource instanceof IResource) {
            IProject parentProject = ((IResource) resource).getProject();
            StructuredSelection structuredSelection = new StructuredSelection(parentProject);
            if (structuredSelection.equals(repositoryProject)) {
                INCLUDES.remove("compareWithMenu");
                INCLUDES.remove("replaceWithMenu");
                INCLUDES.remove("org.eclipse.jdt.ui.source.menu");
                INCLUDES.remove("org.eclipse.jdt.ui.refactoring.menu");
            }
        }

        registerE4Support();

        // Add this menu as a visible menu.
        final IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            final IWorkbench workbench = site.getWorkbenchWindow()
                    .getWorkbench();
            if (workbench instanceof Workbench) {
                final Workbench realWorkbench = (Workbench) workbench;
                runCleanUp(realWorkbench);
                ISelection input = null;
                if ((bitSet & INCLUDE_EDITOR_INPUT) != 0) {
                    if (part instanceof IEditorPart) {
                        final IEditorPart editorPart = (IEditorPart) part;
                        input = new StructuredSelection(
                                new Object[] { editorPart.getEditorInput() });
                    }
                }
                ISelection s = (selProvider == null ? null : selProvider
                        .getSelection());
                realWorkbench.addShowingMenus(getMenuIds(), s, input);
            }
        }

        addMenuContributions(mgr);

        readStaticActions();
        // test for additions removed to comply with menu contributions
        if (menuWrapper != null) {
            mgr = menuWrapper;
            menuWrapper.removeAll();
        }
        Set<IObjectActionContributor> contributedItems = new HashSet<>();
        if ((bitSet & INCLUDE_EDITOR_INPUT) != 0) {
            addEditorActions(mgr, contributedItems);
        }
        addObjectActions(mgr, contributedItems);
        addStaticActions(mgr);
    }

    /**
     * well, this goes to the renderer.
     *
     * @param mgr
     */
    public void addMenuContributions(IMenuManager mgr) {
        IRendererFactory factory = modelPart.getContext().get(IRendererFactory.class);
        AbstractPartRenderer obj = factory.getRenderer(menuModel, null);
        if (obj instanceof MenuManagerRenderer) {
            MenuManagerRenderer renderer = (MenuManagerRenderer) obj;
            renderer.reconcileManagerToModel(menu, menuModel);
            renderer.processContributions(menuModel, menuModel.getElementId(), false, true);
            // double cast because we're bad people
            renderer.processContents((MElementContainer<MUIElement>) ((Object) menuModel));
            List<MMenuElement> toRemove = new ArrayList<>();
            for (MMenuElement e : menuModel.getChildren()) {
                if (!(e instanceof MMenuSeparator || INCLUDES.contains(e.getElementId())
                        || e.getElementId() == null)) {
                    if (e.getElementId() != null && !e.getElementId().startsWith("org.bonitasoft.studio.")) {
                        toRemove.add(e);
                        e.setVisible(false);
                    }
                }
            }
            menuModel.getChildren().removeAll(toRemove);
        }

    }

    private MPopupMenu menuModel;

    /**
     * Notifies the listener that the menu is about to be hidden.
     */
    @Override
    public final void menuAboutToHide(final IMenuManager mgr) {
        gatherContributions(mgr);
        cleanupNeeded = true;
        // Remove this menu as a visible menu.
        final IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            final IWorkbench workbench = site.getWorkbenchWindow().getWorkbench();
            if (workbench instanceof Workbench) {
                // try delaying this until after the selection event
                // has been fired.
                // This is less threatening if the popup: menu
                // contributions aren't tied to the evaluation service
                workbench.getDisplay().asyncExec(() -> {
                    final Workbench realWorkbench = (Workbench) workbench;
                    runCleanUp(realWorkbench);
                });
            }
        }
    }

    private void runCleanUp(Workbench realWorkbench) {
        if (!cleanupNeeded) {
            return;
        }
        cleanupNeeded = false;
        realWorkbench.removeShowingMenus(getMenuIds(), null, null);
        cleanUpContributionCache();
    }

    private void gatherContributions(final IMenuManager mgr) {
        final IContributionItem[] items = mgr.getItems();
        for (IContributionItem item : items) {
            if (item instanceof PluginActionContributionItem) {
                actionContributionCache.add((PluginActionContributionItem) item);
            } else if (item instanceof IMenuManager) {
                gatherContributions(((IMenuManager) item));
            }
        }
    }

    private void cleanUpContributionCache() {
        if (!actionContributionCache.isEmpty()) {
            PluginActionContributionItem[] items = actionContributionCache
                    .toArray(new PluginActionContributionItem[actionContributionCache.size()]);
            actionContributionCache.clear();
            for (PluginActionContributionItem item : items) {
                item.dispose();
            }
        }

        if (modelPart == null || menuModel == null) {
            return;
        }
        IEclipseContext modelContext = modelPart.getContext();
        if (modelContext != null) {
            IRendererFactory factory = modelContext.get(IRendererFactory.class);
            if (factory != null) {
                AbstractPartRenderer obj = factory.getRenderer(menuModel, null);
                if (obj instanceof MenuManagerRenderer) {
                    MenuManagerRenderer renderer = (MenuManagerRenderer) obj;
                    renderer.cleanUp(menuModel);
                }
            }
        }
    }

    /**
     * Read all of the static items for the content menu.
     */
    private final void readStaticActions() {
        if (staticActionBuilders != null) {
            final Iterator<String> menuIdItr = staticActionBuilders.keySet().iterator();
            while (menuIdItr.hasNext()) {
                final String menuId = menuIdItr.next();
                readStaticActionsFor(menuId);
            }
        }
    }

    /**
     * Read static items for a particular menu id, into the context menu.
     */
    private void readStaticActionsFor(final String menuId) {
        if ((bitSet & STATIC_ACTION_READ) != 0) {
            return;
        }

        bitSet |= STATIC_ACTION_READ;

        // If no menu id provided, then there is no contributions
        // to add. Fix for bug #33140.
        if ((menuId == null) || (menuId.length() < 1)) {
            return;
        }

        if (staticActionBuilders == null) {
            staticActionBuilders = new HashMap<>();
        }

        Object object = staticActionBuilders.get(menuId);
        if (!(object instanceof ViewerActionBuilder)) {
            object = new ViewerActionBuilder();
            staticActionBuilders.put(menuId, (ViewerActionBuilder) object);
        }
        final ViewerActionBuilder staticActionBuilder = (ViewerActionBuilder) object;
        staticActionBuilder.readViewerContributions(menuId, selProvider, part);
    }

    /**
     * Dispose of the menu extender. Should only be called when the part
     * is disposed.
     */
    public void dispose() {
        clearStaticActions();
        Platform.getExtensionRegistry().removeRegistryChangeListener(this);
        menu.removeMenuListener(this);

        if (menuModel != null) {
            // unlink ourselves from the renderer
            IRendererFactory factory = modelPart.getContext().get(IRendererFactory.class);
            AbstractPartRenderer obj = factory.getRenderer(menuModel, null);
            if (obj instanceof MenuManagerRenderer) {
                MenuManagerRenderer renderer = (MenuManagerRenderer) obj;
                unlink(renderer, menuModel);
                renderer.clearModelToManager(menuModel, menu);
            }

            modelPart.getMenus().remove(menuModel);
        }
    }

    /**
     * Unlink all contribution items from the given model menu.
     *
     * @param renderer
     *        the renderer that is holding the links
     * @param menu
     *        the model menu whose children should have its items unlinked
     *        from their corresponding contribution items
     */
    private void unlink(MenuManagerRenderer renderer, MMenu menu) {
        for (MMenuElement menuElement : menu.getChildren()) {
            if (OpaqueElementUtil.isOpaqueMenuItem(menuElement)
                    || OpaqueElementUtil.isOpaqueMenuSeparator(menuElement)) {
                Object item = OpaqueElementUtil.getOpaqueItem(menuElement);
                if (item instanceof IContributionItem) {
                    renderer.clearModelToContribution(menuElement, (IContributionItem) item);
                    OpaqueElementUtil.clearOpaqueItem(menuElement);
                }
            } else if (menuElement instanceof MMenu) {
                MMenu subMenu = (MMenu) menuElement;
                unlink(renderer, subMenu);
                MenuManager manager = renderer.getManager(subMenu);
                if (manager != null) {
                    renderer.clearModelToManager(subMenu, manager);
                }
            } else {
                IContributionItem contribution = renderer.getContribution(menuElement);
                if (contribution != null) {
                    renderer.clearModelToContribution(menuElement, contribution);
                }
            }
        }
    }

    @Override
    public void registryChanged(final IRegistryChangeEvent event) {
        Display display = Display.getDefault();
        if (part != null) {
            display = part.getSite().getPage().getWorkbenchWindow().getWorkbench().getDisplay();
        }
        //check the delta to see if there are any viewer contribution changes.  if so, null our builder to cause reparsing on the next menu show
        IExtensionDelta[] deltas = event.getExtensionDeltas();
        for (IExtensionDelta delta : deltas) {
            IExtensionPoint extensionPoint = delta.getExtensionPoint();
            if (extensionPoint.getContributor().getName().equals(WorkbenchPlugin.PI_WORKBENCH)
                    && extensionPoint.getSimpleIdentifier().equals(
                            IWorkbenchRegistryConstants.PL_POPUP_MENU)) {

                boolean clearPopups = false;
                IConfigurationElement[] elements = delta.getExtension().getConfigurationElements();
                for (IConfigurationElement element : elements) {
                    if (element.getName().equals(IWorkbenchRegistryConstants.TAG_VIEWER_CONTRIBUTION)) {
                        clearPopups = true;
                        break;
                    }
                }

                if (clearPopups) {
                    display.syncExec(() -> clearStaticActions());
                }
            }
        }
    }

    public MenuManager getManager() {
        return menu;
    }

    public MenuManagerRenderer getMenuManagerRenderer() {
        IRendererFactory factory = modelPart.getContext().get(IRendererFactory.class);
        AbstractPartRenderer obj = factory.getRenderer(menuModel, null);
        if (obj instanceof MenuManagerRenderer) {
            return (MenuManagerRenderer) obj;
        }
        return null;
    }

    public MPopupMenu getMenuModel() {
        return menuModel;
    }

}
