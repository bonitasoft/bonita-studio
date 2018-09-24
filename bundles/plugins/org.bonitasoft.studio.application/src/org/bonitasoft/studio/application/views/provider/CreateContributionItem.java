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
package org.bonitasoft.studio.application.views.provider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.application.views.CustomPopupMenuExtender;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.e4.core.commands.ExpressionContext;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuSeparator;
import org.eclipse.e4.ui.workbench.renderers.swt.ContributionRecord;
import org.eclipse.e4.ui.workbench.renderers.swt.MenuManagerRenderer;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.navigator.NavigatorActionService;

public class CreateContributionItem extends ContributionItem {

    private NavigatorActionService navigatorActionService;
    private IWorkbenchPart part;

    public CreateContributionItem(IWorkbenchPart part, NavigatorActionService navigatorActionService) {
        this.part = part;
        this.navigatorActionService = navigatorActionService;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        IEclipseContext e4Context = ((PartSite) part.getSite()).getContext();
        ToolItem createItem = new ToolItem(parent, SWT.PUSH);
        createItem.setImage(Pics.getImage(PicsConstants.addFile));
        createItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                MenuManager menuManager = new MenuManager(getId());
                menuManager.setRemoveAllWhenShown(true);
                menuManager.createContextMenu(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
                CustomPopupMenuExtender customPopupMenuExtender = new CustomPopupMenuExtender(BonitaProjectExplorer.ID,
                        menuManager, projectSelectionProvider(), part, e4Context, true);
                Listener[] listeners = menuManager.getMenu().getListeners(SWT.Show);
                menuManager.getMenu().removeListener(SWT.Show, listeners[0]);
                menuManager.getMenu().removeListener(SWT.Hide, listeners[0]);
                MenuManagerRenderer renderer = customPopupMenuExtender.getMenuManagerRenderer();
                navigatorActionService.setContext(new ActionContext(projectSelectionProvider().getSelection()));
                navigatorActionService.fillContextMenu(menuManager);
                customPopupMenuExtender.menuAboutToShow(menuManager);
                MenuManager contributionItem = (MenuManager) menuManager
                        .find("org.bonitasoft.studio.application.project.explorer.new");
                MMenu mMenu = renderer.getMenuModel(contributionItem);
                Menu menu = new Menu(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
                ExpressionContext expressionContext = getExpressionContext(e4Context);
                for (MMenuElement menuElement : mMenu.getChildren()) {
                    IContributionItem contribution = renderer.getContribution(menuElement);
                    if (isVisible(renderer, mMenu, menuElement, expressionContext)) {
                        contribution.fill(menu, -1);
                    }
                }
                final ToolItem item = (ToolItem) event.widget;
                final Rectangle rect = item.getBounds();
                final Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
                menu.setLocation(pt.x, pt.y + rect.height);
                menu.setVisible(true);
            }

            private boolean isVisible(MenuManagerRenderer renderer, MMenu mMenu, MMenuElement menuElement,
                    ExpressionContext expressionContext) {
                if (menuElement instanceof MMenuSeparator) {
                    int indexOf = mMenu.getChildren().indexOf(menuElement);
                    if (indexOf > 0) {
                        MMenuElement mMenuElement = mMenu.getChildren().get(indexOf - 1);
                        if (mMenuElement instanceof MMenuSeparator) {
                            return false;
                        }
                    }
                }
                ContributionRecord contributionRecord = renderer.getContributionRecord(menuElement);
                contributionRecord.updateIsVisible(expressionContext);
                return contributionRecord.computeVisibility(new HashSet<>(), menuElement,
                        expressionContext);
            }
         
        });
    }
    
    private ExpressionContext getExpressionContext(IEclipseContext e4Context) {
        return new ExpressionContext(e4Context) {
            @Override
            public Object getDefaultVariable() {
                List<Object> vars = new ArrayList<>();
                vars.add(RepositoryManager.getInstance().getCurrentRepository().getProject());
                return vars;
            }
        };
    }


    private ISelectionProvider projectSelectionProvider() {
        return new ISelectionProvider() {

            @Override
            public void setSelection(ISelection selection) {
            }

            @Override
            public void removeSelectionChangedListener(ISelectionChangedListener listener) {
            }

            @Override
            public ISelection getSelection() {
                return getProjectSelection();
            }

            @Override
            public void addSelectionChangedListener(ISelectionChangedListener listener) {
            }
        };
    }

    private ISelection getProjectSelection() {
        return new StructuredSelection(RepositoryManager.getInstance().getCurrentRepository().getProject());
    }

}
