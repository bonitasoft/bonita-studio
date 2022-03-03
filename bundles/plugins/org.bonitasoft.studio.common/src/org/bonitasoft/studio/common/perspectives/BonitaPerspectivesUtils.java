/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.perspectives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author Baptiste Mesta
 *         Utility class that help works with perspectives
 */
public class BonitaPerspectivesUtils {

    private static final String VIEWS_EXTENSION_POINT = "org.bonitasoft.studio.application.PropertiesView";

    private static final String PERSPECTIVES_EXTENSION_POINT = "org.eclipse.ui.perspectives";

    private static List<String> contributedView;

    private static List<String> allPropertiesViews;

    private static Map<String, List<String>> contributedViewMap;

    private static List<AbstractPerspectiveFactory> contributedPerspectives;

    /**
     * @return
     */
    public static List<String> getContributedPropertiesViews() {
        if (contributedView == null) {
            contributedViewMap = new HashMap<>();
            contributedView = new ArrayList<>();
            final IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance()
                    .getConfigurationElements(VIEWS_EXTENSION_POINT);
            for (final IConfigurationElement extension : extensions) {
                try {
                    final String viewId = extension.getAttribute("ViewId");
                    final String perspectiveId = extension.getAttribute("perspectiveID");
                    contributedView.add(viewId);
                    List<String> list = contributedViewMap.get(perspectiveId);
                    if (list == null) {
                        list = new ArrayList<>();
                        contributedViewMap.put(perspectiveId, list);
                    }
                    list.add(viewId);
                } catch (final Exception ex) {
                    BonitaStudioLog.error(ex);
                }
            }
        }
        return contributedView;
    }

    public static List<String> getContributedPropertiesViews(final String perspectiveId) {
        if (contributedViewMap == null) {
            getContributedPropertiesViews();
        }
        final List<String> list = contributedViewMap.get(perspectiveId);
        if (list != null) {
            return list;
        } else {
            return Collections.emptyList();
        }
    }

    public static List<String> getAllPropertiesViews() {
        if (allPropertiesViews == null) {
            allPropertiesViews = new ArrayList<>();
            allPropertiesViews.addAll(getContributedPropertiesViews());
            allPropertiesViews.add("org.bonitasoft.studio.views.properties.process.general");
            allPropertiesViews.add("org.bonitasoft.studio.views.properties.application");
            allPropertiesViews.add("org.bonitasoft.studio.views.properties.form.general");
            allPropertiesViews.add("org.bonitasoft.studio.views.properties.form.appearance");
            allPropertiesViews.add("org.bonitasoft.studio.views.properties.process.data");
            allPropertiesViews.add("org.bonitasoft.studio.views.properties.process.execution");
            allPropertiesViews.add("org.bonitasoft.studio.views.properties.process.appearance");
            allPropertiesViews.add("org.bonitasoft.studio.bpmn.palette_view");
            allPropertiesViews.add("org.bonitasoft.studio.form.palette_view");
        }
        return allPropertiesViews;
    }

    /**
     * Switch to the perspective with id given as parameter
     * 
     * @param perspectiveID
     */
    public static synchronized void switchToPerspective(final String perspectiveID) {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        if (window != null) {
            final IWorkbenchPage activePage = window.getActivePage();
            if (activePage != null) {
                final IPerspectiveDescriptor activePerspective = activePage.getPerspective();
                if (activePerspective == null || !activePerspective.getId().equals(perspectiveID)) {
                    final IPerspectiveRegistry registry = workbench.getPerspectiveRegistry();
                    final IWorkbenchPage page = window.getActivePage();
                    final IPerspectiveDescriptor desc = registry.findPerspectiveWithId(perspectiveID);
                    page.setPerspective(desc);
                }
            }
        }
    }

    /**
     * @param perspectiveID
     * @return if the active perspective is the one with the ID perspectiveID
     */
    public static boolean isPerspectiveWithIDActive(final String perspectiveID) {
        final IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null) {
            final IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
            if (activePage != null) {
                final IPerspectiveDescriptor perspective = activePage.getPerspective();
                if (perspective != null) {
                    return perspective.getId().equals(perspectiveID);
                }
            }
        }
        return false;
    }

    /**
     * @param part
     * @return
     */
    public static String getPerspectiveId(final IEditorPart part) {
        if (contributedPerspectives == null) {
            initializePerspectives();
        }
        for (final AbstractPerspectiveFactory perspectiveFactory : contributedPerspectives) {
            if (perspectiveFactory.isRelevantFor(part)) {
                return perspectiveFactory.getID();
            }
        }
        return null;
    }

    public static void initializePerspectives() {
        contributedPerspectives = new ArrayList<>();
        final IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(PERSPECTIVES_EXTENSION_POINT);
        for (final IConfigurationElement extension : extensions) {
            try {
                final Object perspectiveFactory = extension.createExecutableExtension("class");
                if (perspectiveFactory instanceof AbstractPerspectiveFactory) {
                    contributedPerspectives.add((AbstractPerspectiveFactory) perspectiveFactory);
                }
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
    }
}
