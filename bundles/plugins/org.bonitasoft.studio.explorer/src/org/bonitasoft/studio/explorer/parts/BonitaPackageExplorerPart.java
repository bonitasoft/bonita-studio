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
package org.bonitasoft.studio.explorer.parts;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.reflect.FieldUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("restriction")
public class BonitaPackageExplorerPart extends PackageExplorerPart {

    public static final String VIEW_ID = "org.bonitasoft.studio.explorer";

    private static final String MANAGE_ORGA_COMMAND = "org.bonitasoft.studio.organization.manage";
    private static final String MANAGE_BDM_COMMAND = "org.bonitasoft.studio.businessobject.manage";

    private ECommandService eCommandService;
    private EHandlerService eHandlerService;

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        eCommandService = getSite().getService(ECommandService.class);
        eHandlerService = getSite().getService(EHandlerService.class);
        IProject currentProject = RepositoryManager.getInstance().getCurrentRepository().getProject();

        TreeViewer treeViewer = getTreeViewer();
        treeViewer.addFilter(onlyCurrentProject(currentProject));

        try {
            Field listenersField = StructuredViewer.class.getDeclaredField("openListeners");
            listenersField.setAccessible(true);
            ListenerList<IOpenListener> openListeners = (ListenerList<IOpenListener>) FieldUtils.readField(listenersField,
                    treeViewer);
            Optional<IOpenListener> initialListener = openListeners.stream().findFirst();
            openListeners.clear();
            openListeners.add(createOpenListener(initialListener));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // TODO: filter unwanted folder, add popup menu filter ect
    }

    /**
     * Overwrite the initial behavior to open our wizards editor for the BDM and the Organization.
     */
    private IOpenListener createOpenListener(Optional<IOpenListener> initialListener) {
        return e -> {
            TreeSelection treeSelection = (TreeSelection) e.getSelection();
            if (treeSelection.getFirstElement() instanceof File) {
                File file = (File) treeSelection.getFirstElement();
                if (Objects.equals(file.getName(), ("bom.xml"))) {
                    openWizard(MANAGE_BDM_COMMAND);
                    return;
                } else if (file.getName().endsWith(".organization")) {
                    openWizard(MANAGE_ORGA_COMMAND);
                    return;
                }
            }
            initialListener.ifPresent(listener -> listener.open(e));
        };
    }

    private void openWizard(String command) {
        ParameterizedCommand openCommand = ParameterizedCommand.generateCommand(
                eCommandService.getCommand(command), null);
        eHandlerService.executeHandler(openCommand);
    }

    private ViewerFilter onlyCurrentProject(IProject currentProject) {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof IProject) {
                    IProject project = (IProject) element;
                    return Objects.equals(currentProject, project);
                }
                return true;
            }
        };
    }
}
