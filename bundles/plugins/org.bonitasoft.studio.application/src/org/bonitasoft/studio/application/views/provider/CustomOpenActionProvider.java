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
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ISourceReference;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.ui.actions.OpenAction;
import org.eclipse.jdt.ui.actions.OpenEditorActionGroup;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

@SuppressWarnings("restriction")
public class CustomOpenActionProvider extends CommonActionProvider {

    private OpenEditorActionGroup openFileAction;
    private ICommonViewerWorkbenchSite viewSite = null;
    private boolean contribute = false;
    private RepositoryAccessor repositoryAccessor;

    @Override
    public void init(ICommonActionExtensionSite aConfig) {
        if (aConfig.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            viewSite = (ICommonViewerWorkbenchSite) aConfig.getViewSite();
            openFileAction = new OpenEditorActionGroup((IViewPart) viewSite.getPart());
            contribute = true;
        }
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }


    @Override
    public void fillActionBars(IActionBars theActionBars) {
        if (!contribute) {
            return;
        }
        openFileAction.setContext(getContext());

        OpenAction customOpenAction = createCustomOpenAction();
        if (openFileAction.getOpenAction().isEnabled()) {
            theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN,
                    customOpenAction);
        } else if (UIDArtifactFilters
                .isUIDArtifact(((IStructuredSelection) getContext().getSelection()).getFirstElement())) {
            theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN,
                    customOpenAction);
        }
    }

    /**
     * Overwrite the default behavior for some files, in order to be able to open our 'wizard editors'
     */
    private OpenAction createCustomOpenAction() {
        return new OpenAction(((IViewPart) viewSite.getPart()).getSite()) {

            @Override
            public void run(IStructuredSelection selection) {
                if (!checkEnabled(selection))
                    return;
                run(selection.toArray());
            }

            @Override
            public void run(Object[] elements) {
                List<Object> elementsToKeep = new ArrayList<>();

                for (Object element : elements) {
                    if (element instanceof File || UIDArtifactFilters.isUIDArtifact(element)) {
                        IRepositoryFileStore fileStore = repositoryAccessor.getCurrentRepository()
                                .getFileStore((IResource) element);
                        if (fileStore != null) {
                            fileStore.open();
                            continue;
                        }
                    }
                    elementsToKeep.add(element);
                }

                ((OpenAction) openFileAction.getOpenAction()).run(elementsToKeep.toArray());
            }

            private boolean checkEnabled(IStructuredSelection selection) {
                if (selection.isEmpty())
                    return false;
                for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
                    Object element = iter.next();
                    if (element instanceof ISourceReference)
                        continue;
                    if (element instanceof IFile)
                        continue;
                    if (UIDArtifactFilters.isUIDArtifact(element))
                        continue;
                    if (JavaModelUtil.isOpenableStorage(element))
                        continue;
                    return false;
                }
                return true;
            }
        };
    }

}
