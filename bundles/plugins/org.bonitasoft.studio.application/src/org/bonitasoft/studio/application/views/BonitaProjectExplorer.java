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

import javax.inject.Inject;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.PropertiesBrowserPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

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
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.navigator.CommonNavigator#createCommonViewerObject(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected CommonViewer createCommonViewerObject(Composite aParent) {
        CommonViewer commonViewer = new CommonViewer(getViewSite().getId(), aParent,
                SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL) {

            @Override
            protected void initDragAndDrop() {

            }
        };
        return commonViewer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (adapter == IPropertySheetPage.class) {
            IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .getActiveEditor();
            if (activeEditor instanceof ITabbedPropertySheetPageContributor) {
                return (T) new PropertiesBrowserPage((ITabbedPropertySheetPageContributor) activeEditor);
            }
        }
        return super.getAdapter(adapter);
    }

}
