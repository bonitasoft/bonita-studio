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
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.bonitasoft.studio.application.views.provider.UIDArtifactFilters;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.packageview.ClassPathContainer;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.internal.navigator.NavigatorDecoratingLabelProvider;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonFilterDescriptor;
import org.eclipse.ui.navigator.NavigatorActionService;
import org.eclipse.ui.views.properties.IPropertySheetPage;

public class BonitaProjectExplorer extends CommonNavigator {

	public static final String ID = "org.bonitasoft.studio.application.project.explorer";

	@Inject
	private RepositoryAccessor repositoryAccessor;

	private JobChangeAdapter openIntroListener = new JobChangeAdapter() {

		@Override
		public void done(IJobChangeEvent event) {
			Job job = event.getJob();
			if (Objects.equals(job.getName(), getTitle())) {
				PlatformUtil.openIntroIfNoOtherEditorOpen();
			}
		}
	};

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
				new String[] { "org.bonitasoft.studio.application.extendedResourceLinkHelper" }, false);
		initContextMenu();
		getCommonViewer().expandToLevel(2);
		Job.getJobManager().addJobChangeListener(openIntroListener);
	}

	@Override
	public void dispose() {
		if (openIntroListener != null) {
			Job.getJobManager().removeJobChangeListener(openIntroListener);
		}
		super.dispose();
	}

	private void initContextMenu() {
		TreeViewer commonViewer = getCommonViewer();
		Menu previousMenu = commonViewer.getTree().getMenu();
		// Remove menu created by default
		if (previousMenu != null) {
			previousMenu.dispose();
		}
		MenuManager menuMgr = new MenuManager(getNavigatorContentService().getViewerDescriptor().getPopupMenuId());
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(manager -> fillContextMenu(manager));

		Menu menu = menuMgr.createContextMenu(commonViewer.getTree());
		commonViewer.getTree().setMenu(menu);

		IEclipseContext e4Context = ((PartSite) getSite()).getContext();
		new CustomPopupMenuExtender(ID, menuMgr, getSite().getSelectionProvider(), getSite().getPart(), e4Context,
				true);
	}

	protected void fillContextMenu(IMenuManager aMenuManager) {
		ISelection selection = getCommonViewer().getSelection();
		NavigatorActionService navigatorActionService = getNavigatorActionService();
		navigatorActionService.setContext(new ActionContext(selection));
		navigatorActionService.fillContextMenu(aMenuManager);
	}

	private void activateNestedProjectsState() {
		getNavigatorContentService().getActivationService().activateExtensions(
				new String[] { "org.eclipse.ui.navigator.resources.nested.nestedProjectContentProvider" }, false);
		List<String> activeFilters = Arrays
				.asList(getNavigatorContentService().getFilterService().getVisibleFilterDescriptors()).stream()
				.filter(ICommonFilterDescriptor::isActiveByDefault).map(ICommonFilterDescriptor::getId)
				.collect(Collectors.toList());
		activeFilters.add("org.eclipse.ui.navigator.resources.nested.HideFolderWhenProjectIsShownAsNested");
		getNavigatorContentService().getFilterService()
				.activateFilterIdsAndUpdateViewer((activeFilters.toArray(new String[activeFilters.size()])));
	}

	@Override
	protected CommonViewer createCommonViewerObject(Composite aParent) {
		CommonViewer commonViewer = new PackageExplorerProblemTreeViewer(getViewSite().getId(), aParent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		commonViewer.getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
				"org.bonitasoft.studio.application.projectExplorerTree");
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

	private class PackageExplorerProblemTreeViewer extends ProblemTreeViewer {

		// fix for 64372 Projects showing up in Package Explorer twice [package
		// explorer]
		private final List<Object> fPendingRefreshes;

		public PackageExplorerProblemTreeViewer(String id, Composite parent, int style) {
			super(id, parent, style);
			fPendingRefreshes = Collections.synchronizedList(new ArrayList<>());
			initizialize();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.navigator.CommonViewer#init()
		 */
		@Override
		protected void init() {

		}

		protected void initizialize() {
			setUseHashlookup(true);
			setContentProvider(getNavigatorContentService().createCommonContentProvider());
			setLabelProvider(
					new NavigatorDecoratingLabelProvider(getNavigatorContentService().createCommonLabelProvider()));
			initDragAndDrop();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.navigator.CommonViewer#initDragAndDrop()
		 */
		@Override
		protected void initDragAndDrop() {

		}

		@Override
		public void add(Object parentElement, Object[] childElements) {
			if (fPendingRefreshes.contains(parentElement)) {
				return;
			}
			super.add(parentElement, childElements);
		}

		@Override
		protected void internalRefresh(Object element, boolean updateLabels) {
			if (!getTree().isDisposed()) {
				try {
					fPendingRefreshes.add(element);
					super.internalRefresh(element, updateLabels);
				} finally {
					fPendingRefreshes.remove(element);
				}
			}
		}

		@Override
		protected boolean evaluateExpandableWithFilters(Object parent) {
			if (parent instanceof IJavaProject || parent instanceof ICompilationUnit || parent instanceof IClassFile
					|| parent instanceof ClassPathContainer) {
				return false;
			}
			if (parent instanceof IPackageFragmentRoot && ((IPackageFragmentRoot) parent).isArchive()) {
				return false;
			}
			return true;
		}

		@Override
		protected boolean isFiltered(Object object, Object parent, ViewerFilter[] filters) {
			boolean res = super.isFiltered(object, parent, filters);
			if (res && isEssential(object)) {
				return false;
			}
			return res;
		}

		/*
		 * Checks if a filtered object in essential (i.e. is a parent that should not be
		 * removed).
		 */
		private boolean isEssential(Object object) {
			try {
				if (object instanceof IPackageFragment) {
					IPackageFragment fragment = (IPackageFragment) object;
					if (!fragment.isDefaultPackage() && fragment.hasSubpackages()) {
						return hasFilteredChildren(fragment);
					}
				}
			} catch (JavaModelException e) {
				JavaPlugin.log(e);
			}
			return false;
		}

		@Override
		protected void handleInvalidSelection(ISelection invalidSelection, ISelection newSelection) {
			IStructuredSelection is = (IStructuredSelection) invalidSelection;
			List<Object> ns = null;
			if (newSelection instanceof IStructuredSelection) {
				ns = new ArrayList<Object>(((IStructuredSelection) newSelection).toList());
			} else {
				ns = new ArrayList<>();
			}
			boolean changed = false;
			for (Iterator<?> iter = is.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof IJavaProject) {
					IProject project = ((IJavaProject) element).getProject();
					if (!project.isOpen() && project.exists()) {
						ns.add(project);
						changed = true;
					}
				} else if (element instanceof IProject) {
					IProject project = (IProject) element;
					if (project.isOpen()) {
						IJavaProject jProject = JavaCore.create(project);
						if (jProject != null && jProject.exists()) {
							ns.add(jProject);
						}
						changed = true;
					}
				}
			}
			if (changed) {
				newSelection = new StructuredSelection(ns);
				setSelection(newSelection);
			}
			super.handleInvalidSelection(invalidSelection, newSelection);
		}

	}
}
