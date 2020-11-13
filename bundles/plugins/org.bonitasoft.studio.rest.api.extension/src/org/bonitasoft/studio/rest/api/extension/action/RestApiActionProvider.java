/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.action;

import java.util.Optional;

import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.internal.ui.actions.ActionMessages;
import org.eclipse.jdt.ui.actions.CCPActionGroup;
import org.eclipse.jdt.ui.actions.GenerateActionGroup;
import org.eclipse.jdt.ui.actions.IJavaEditorActionDefinitionIds;
import org.eclipse.jdt.ui.actions.OpenTypeHierarchyAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.OpenWithMenu;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.resources.GoIntoActionProvider;

public class RestApiActionProvider extends CommonActionProvider {

    private GoIntoActionProvider goIntoActionProvider;
    private ActionContributionItem openTypeInHierarchyActionContributionItem;
    private GenerateActionGroup generateActionGroup;
    private CCPActionGroup ccpActionGroup;
    private IViewPart viewPart;

    @Override
    public void init(ICommonActionExtensionSite aSite) {
        super.init(aSite);
        initActionProviders(aSite);
    }

    private void initViewPartBasedActions() {
        if (viewPart == null) {
            viewPart = findProjectExplorerViewPart().orElse(null);
            if (viewPart == null) {
                return;
            }
            if (openTypeInHierarchyActionContributionItem == null) {
                OpenTypeHierarchyAction openTypeHierarchyAction = new OpenTypeHierarchyAction(viewPart.getSite());
                openTypeHierarchyAction.setActionDefinitionId(IJavaEditorActionDefinitionIds.OPEN_TYPE_HIERARCHY);
                openTypeInHierarchyActionContributionItem = new ActionContributionItem(openTypeHierarchyAction);
            }

            generateActionGroup = new GenerateActionGroup(viewPart);
            ccpActionGroup = new CCPActionGroup(viewPart);
        }

    }

    private void initActionProviders(ICommonActionExtensionSite aSite) {
        goIntoActionProvider = new GoIntoActionProvider();
        goIntoActionProvider.init(aSite);
    }

    private Optional<IViewPart> findProjectExplorerViewPart() {
        return Optional.ofNullable(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(BonitaProjectExplorer.ID));
    }

    @Override
    public void fillContextMenu(IMenuManager menu) {
        initViewPartBasedActions();
        if (viewPart != null) {
            IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
            if (!selection.isEmpty() && isRestApiElement(selection)) {
                final IResource resource = ((IAdaptable) selection.getFirstElement()).getAdapter(IResource.class);
                if(resource instanceof IFile) {
                    IMenuManager submenu= new MenuManager(ActionMessages.OpenWithMenu_label);
                    submenu.add(new OpenWithMenu(viewPart.getSite().getPage(), (IFile) resource));
                    menu.appendToGroup("group.open", submenu);
                }
                goIntoActionProvider.fillContextMenu(menu);
                if (!isIProject(selection)) {
                    menu.appendToGroup("group.open", openTypeInHierarchyActionContributionItem);
                    ccpActionGroup.fillContextMenu(menu);
                }
            }
        }
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        initViewPartBasedActions();
        if (viewPart != null) {
            IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
            if (!selection.isEmpty() && isRestApiElement(selection)) {
                goIntoActionProvider.fillActionBars(actionBars);
                generateActionGroup.fillActionBars(actionBars);
                if (!isIProject(selection)) {
                    ccpActionGroup.fillActionBars(actionBars);
                }
            }
        }
    }

    private boolean isIProject(IStructuredSelection selection) {
        Object firstSelectedElement = selection.getFirstElement();
        return firstSelectedElement instanceof IProject;
    }

    private boolean isRestApiElement(IStructuredSelection selection) {
        Object firstSelectedElement = selection.getFirstElement();
        if (firstSelectedElement instanceof IAdaptable) {
            final IResource resource = ((IAdaptable) firstSelectedElement).getAdapter(IResource.class);
            final IProject project = resource != null ? resource.getProject() : null;
            try {
                return project != null && project.hasNature(RestAPIExtensionNature.NATURE_ID);
            } catch (CoreException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void updateActionBars() {
        initViewPartBasedActions();
        if (viewPart != null) {
            goIntoActionProvider.updateActionBars();
            IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
            OpenTypeHierarchyAction openTypeHierarchyAction = (OpenTypeHierarchyAction) openTypeInHierarchyActionContributionItem
                    .getAction();
            openTypeHierarchyAction.selectionChanged(selection);
            openTypeInHierarchyActionContributionItem.setVisible(openTypeHierarchyAction.isEnabled());
        }
    }

}
