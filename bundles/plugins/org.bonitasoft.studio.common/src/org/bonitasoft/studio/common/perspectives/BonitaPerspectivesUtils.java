/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.perspectives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

/**
 * @author Baptiste Mesta
 * 
 * Utility class that help works with perspectives
 * 
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
			contributedViewMap = new HashMap<String, List<String>>();
			contributedView = new ArrayList<String>();
			IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(VIEWS_EXTENSION_POINT);
			for (IConfigurationElement extension : extensions) {
				try {
					String viewId = extension.getAttribute("ViewId");
					String perspectiveId = extension.getAttribute("perspectiveID");
					contributedView.add(viewId);
					List<String> list = contributedViewMap.get(perspectiveId);
					if (list == null) {
						list = new ArrayList<String>();
						contributedViewMap.put(perspectiveId, list);
					}
					list.add(viewId);
				} catch (Exception ex) {
					BonitaStudioLog.error(ex);
				}
			}
		}
		return contributedView;
	}

	public static List<String> getContributedPropertiesViews(String perspectiveId) {
		if (contributedViewMap == null) {
			getContributedPropertiesViews();
		}
		List<String> list = contributedViewMap.get(perspectiveId);
		if(list != null){
			return list;
		}else{
			return Collections.emptyList();
		}
	}

	public static List<String> getAllPropertiesViews() {
		if (allPropertiesViews == null) {
			allPropertiesViews = new ArrayList<String>();
			allPropertiesViews.addAll(getContributedPropertiesViews());
			allPropertiesViews.add("org.bonitasoft.studio.views.properties.process.general");
			allPropertiesViews.add("org.bonitasoft.studio.views.properties.application");
			allPropertiesViews.add("org.bonitasoft.studio.views.properties.form.general");
			allPropertiesViews.add("org.bonitasoft.studio.views.properties.form.appearance");
			allPropertiesViews.add("org.bonitasoft.studio.views.properties.process.appearance");
			allPropertiesViews.add("org.bonitasoft.studio.bpmn.palette_view");
			allPropertiesViews.add("org.bonitasoft.studio.form.palette_view");
		}
		return allPropertiesViews;
	}

	/**
	 * Switch to the perspective with id given as parameter
	 * @param perspectiveID
	 */
	public static synchronized void switchToPerspective(final String perspectiveID){
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		if (window != null) {
			final IWorkbenchPage activePage = window.getActivePage();
			if (activePage != null) {
				final IPerspectiveDescriptor activePerspective = activePage.getPerspective();
				if (activePerspective ==  null || !activePerspective.getId().equals(perspectiveID)) {
					IPerspectiveRegistry registry = workbench.getPerspectiveRegistry();
					IWorkbenchPage page = window.getActivePage();
					IPerspectiveDescriptor desc = registry.findPerspectiveWithId(perspectiveID);
					page.setPerspective(desc);
					UIJob job = new UIJob("changePerspective") {
						@Override
						public IStatus runInUIThread(IProgressMonitor monitor) {
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									if(activePage.getEditorReferences().length == 0){
										PlatformUtil.openIntro();
									}else{
										PlatformUtil.closeIntro();
									}

								}
							});
							return Status.OK_STATUS;
						}
					};
					job.setSystem(true);
					job.schedule();
				}
			}
		}
	}

	/**
	 * @param perspectiveID
	 * @return if the active perspective is the one with the ID perspectiveID
	 */
	public static boolean isPerspectiveWithIDActive(String perspectiveID){
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(activeWorkbenchWindow != null){
			IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
			if(activePage != null){
				IPerspectiveDescriptor perspective = activePage.getPerspective();
				if(perspective != null){
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
	public static String getPerspectiveId(IEditorPart part) {
		if (contributedPerspectives == null) {
			initializePerspectives();
		}
		for (AbstractPerspectiveFactory perspectiveFactory : contributedPerspectives) {
			if(perspectiveFactory.isRelevantFor(part)){
				return perspectiveFactory.getID();
			}
		}
		return null;
	}

	public static void initializePerspectives() {
		contributedPerspectives = new ArrayList<AbstractPerspectiveFactory>();
		IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(PERSPECTIVES_EXTENSION_POINT);
		for (IConfigurationElement extension : extensions) {
			try {
				Object perspectiveFactory = extension.createExecutableExtension("class");
				if(perspectiveFactory instanceof AbstractPerspectiveFactory){
					contributedPerspectives.add((AbstractPerspectiveFactory) perspectiveFactory);
				}
			} catch (Exception ex) {
				BonitaStudioLog.error(ex);
			}
		}
	}
}
