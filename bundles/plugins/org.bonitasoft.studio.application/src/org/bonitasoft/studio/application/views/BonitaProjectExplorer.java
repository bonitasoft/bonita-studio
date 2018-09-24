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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.bonitasoft.studio.application.views.provider.UIDArtifactFilters;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonFilterDescriptor;
import org.eclipse.ui.navigator.NavigatorActionService;
import org.eclipse.ui.views.properties.IPropertySheetPage;

public class BonitaProjectExplorer extends CommonNavigator {

    public static final String ID = "org.bonitasoft.studio.application.project.explorer";

    @Inject
    private RepositoryAccessor repositoryAccessor;

    @Override
    protected Object getInitialInput() {
        return repositoryAccessor.getWorkspace().getRoot();
    }

    public BonitaProjectExplorer() {
        super();
    }

    @Override
    public void createPartControl(Composite aParent) {
        super.createPartControl(aParent);
        setLinkingEnabled(true);
        activateNestedProjectsState();
        getNavigatorContentService().bindExtensions(
                new String[] { "org.bonitasoft.studio.application.extendedResourceLinkHelper" },
                false);
        initContextMenu();
    }


    private void initContextMenu() {
        TreeViewer commonViewer = getCommonViewer();
        Menu previousMenu = commonViewer.getTree().getMenu();
        //Remove menu created by default
        if (previousMenu != null) {
            previousMenu.dispose();
        }
        MenuManager menuMgr = new MenuManager(getNavigatorContentService()
                .getViewerDescriptor().getPopupMenuId());
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            @Override
            public void menuAboutToShow(IMenuManager manager) {
                fillContextMenu(manager);
            }
        });

        Menu menu = menuMgr.createContextMenu(commonViewer.getTree());
        commonViewer.getTree().setMenu(menu);

        IEclipseContext e4Context = ((PartSite) getSite()).getContext();
        new CustomPopupMenuExtender(ID, menuMgr, getSite().getSelectionProvider(), getSite().getPart(), e4Context, true);
    }

    protected void fillContextMenu(IMenuManager aMenuManager) {
        ISelection selection = getCommonViewer().getSelection();
        NavigatorActionService navigatorActionService = getNavigatorActionService();
        navigatorActionService.setContext(new ActionContext(selection));
        navigatorActionService.fillContextMenu(aMenuManager);
    }



    private void activateNestedProjectsState() {
        getNavigatorContentService().getActivationService().activateExtensions(
                new String[] { "org.eclipse.ui.navigator.resources.nested.nestedProjectContentProvider" },
                false);
        List<String> activeFilters = Arrays
                .asList(getNavigatorContentService().getFilterService().getVisibleFilterDescriptors()).stream()
                .filter(ICommonFilterDescriptor::isActiveByDefault)
                .map(ICommonFilterDescriptor::getId)
                .collect(Collectors.toList());
        activeFilters.add("org.eclipse.ui.navigator.resources.nested.HideFolderWhenProjectIsShownAsNested");
        getNavigatorContentService().getFilterService()
                .activateFilterIdsAndUpdateViewer((activeFilters.toArray(new String[activeFilters.size()])));
    }

    @Override
    protected CommonViewer createCommonViewerObject(Composite aParent) {
        CommonViewer commonViewer = new CommonViewer(getViewSite().getId(), aParent,
                SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL) {

            @Override
            protected void initDragAndDrop() {
                //Disable drag and drop
            }

        };
        commonViewer.addFilter(UIDArtifactFilters.filterUIDArtifactChildren());
        return commonViewer;
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (adapter == IPropertySheetPage.class) {
            return null;
        }
        return super.getAdapter(adapter);
    }
}
