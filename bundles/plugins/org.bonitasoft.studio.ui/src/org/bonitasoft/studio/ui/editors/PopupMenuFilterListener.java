/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.ui.refactoring.reorg.DeleteAction;
import org.eclipse.jdt.internal.ui.workingsets.ConfigureWorkingSetAssignementAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.ui.actions.NewWizardMenu;
import org.eclipse.ui.actions.OpenInNewWindowAction;
import org.eclipse.ui.dialogs.PropertyDialogAction;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;

public class PopupMenuFilterListener implements IMenuListener {

    private static final String CONVERT_TO_GROOVY_ACTION = "org.codehaus.groovy.eclipse.convertToGroovy.action";
    private static final String VIEWS_SHOW_IN_ID = "viewsShowIn";

    private static final String FILTERED_MENUS_PROPERTIES_FILENAME = "filteredMenus.properties";
    private static final String MAVEN_POPUP_MENU = "mavenPopupMenu";
    private static final String FILTERED_PACKAGE_EXPLORER_POPUP_MENU = "packageExplorerPopupMenu";

    private final Set<String> filteredPopupMenus;
    private final Set<String> filteredMavenPopupMenuIds;
    private final ISelectionProvider selectionProvider;

    public PopupMenuFilterListener(ISelectionProvider selectionProvider) {
        this.selectionProvider = selectionProvider;
        this.filteredPopupMenus = filteredPopupMenuIds();
        this.filteredMavenPopupMenuIds = filteredMavenPopupMenuIds();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    public void menuAboutToShow(IMenuManager manager) {
        final ISelection selection = selectionProvider.getSelection();
        filterPopupMenus(filteredPopupMenus, manager, selection);
        filterMavenMenu(filteredMavenPopupMenuIds,
                (MenuManager) find(newArrayList(manager.getItems()), mavenMenuManger(), null));
    }

    protected void filterMavenMenu(final Set<String> filteredMavenPopupMenuIds, final MenuManager mavenMenuManger) {
        if (mavenMenuManger != null) {
            for (final String menuId : filteredMavenPopupMenuIds) {
                mavenMenuManger.remove(menuId);
            }
        }
    }

    private Predicate<IContributionItem> mavenMenuManger() {
        return new Predicate<IContributionItem>() {

            @Override
            public boolean apply(final IContributionItem item) {
                if (item instanceof MenuManager) {
                    for (final IContributionItem c : ((MenuManager) item).getItems()) {
                        if ("org.eclipse.m2e.disableAction".equals(c.getId())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
    }

    @SuppressWarnings("unchecked")
    protected void filterPopupMenus(final Set<String> filteredPopupMenus, final IMenuManager manager,
            final ISelection selection) {
        final Iterable<IContributionItem> toRemove = filter(newArrayList(manager.getItems()),
                Predicates.or(viewsShowInMenu(),
                        withContributionsId(filteredPopupMenus, selection),
                        Predicates.and(withContributionId("team.main"), projectNotShared(selection)),
                        menuManagerWith(NewWizardMenu.class),
                        menuManagerWith(CONVERT_TO_GROOVY_ACTION),
                        actionInstanceOf(DeleteAction.class),
                        actionInstanceOf(ConfigureWorkingSetAssignementAction.class),
                        actionInstanceOf(OpenInNewWindowAction.class),
                        actionInstanceOf(PropertyDialogAction.class)));
        for (final IContributionItem item : toRemove) {
            manager.remove(item);
        }
    }

    private Predicate<IContributionItem> projectNotShared(final ISelection selection) {
        return new Predicate<IContributionItem>() {

            @Override
            public boolean apply(IContributionItem item) {
                if (selection instanceof StructuredSelection) {
                    final Object firstElement = ((StructuredSelection) selection).getFirstElement();
                    if (firstElement instanceof IAdaptable) {
                        final IProject project = (IProject) ((IAdaptable) firstElement).getAdapter(IProject.class);
                        if (project != null) {
                            return !RepositoryProvider.isShared(project);
                        }
                    }
                }
                return true;
            }
        };
    }

    private Predicate<? super IContributionItem> menuManagerWith(final String actionId) {
        return new Predicate<IContributionItem>() {

            @Override
            public boolean apply(IContributionItem item) {
                if (item instanceof MenuManager) {
                    for (final IContributionItem citem : ((MenuManager) item).getItems()) {
                        if (Objects.equals(actionId, citem.getId())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
    }

    private Predicate<IContributionItem> menuManagerWith(final Class<?> clazz) {
        return new Predicate<IContributionItem>() {

            @Override
            public boolean apply(IContributionItem item) {
                if (item instanceof MenuManager) {
                    for (final IContributionItem citem : ((MenuManager) item).getItems()) {
                        if (clazz.isAssignableFrom(citem.getClass())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
    }

    private Predicate<IContributionItem> withContributionsId(final Set<String> filteredPopupMenus,
            final ISelection selection) {
        return new Predicate<IContributionItem>() {

            @Override
            public boolean apply(final IContributionItem item) {
                final String id = item.getId();
                if ("org.eclipse.jdt.ui.refactoring.menu".equals(id)) {
                    if (selection instanceof StructuredSelection) {
                        final Object firstElement = ((StructuredSelection) selection).getFirstElement();
                        return firstElement instanceof JavaProject || firstElement instanceof IProject;
                    }
                }
                return filteredPopupMenus.contains(id);
            }
        };
    }

    private Predicate<IContributionItem> withContributionId(final String contributionId) {
        return new Predicate<IContributionItem>() {

            @Override
            public boolean apply(final IContributionItem item) {
                return Objects.equals(item.getId(), contributionId);
            }
        };
    }

    private Predicate<IContributionItem> actionInstanceOf(final Class<?> actionClass) {
        return new Predicate<IContributionItem>() {

            @Override
            public boolean apply(final IContributionItem item) {
                if (item instanceof ActionContributionItem) {
                    return instanceOf(actionClass).apply(((ActionContributionItem) item).getAction());
                }
                return false;
            }
        };
    }

    private Predicate<IContributionItem> viewsShowInMenu() {
        return new Predicate<IContributionItem>() {

            @Override
            public boolean apply(final IContributionItem item) {
                if (item instanceof MenuManager) {
                    for (final IContributionItem c : ((MenuManager) item).getItems()) {
                        if (VIEWS_SHOW_IN_ID.equals(c.getId())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
    }

    private Set<String> filteredPopupMenuIds() {
        final Properties properties = new Properties();
        try (InputStream is = PopupMenuFilterListener.class.getResourceAsStream(FILTERED_MENUS_PROPERTIES_FILENAME);) {
            properties.load(is);
            return newHashSet(Splitter.on(",").split(properties.getProperty(FILTERED_PACKAGE_EXPLORER_POPUP_MENU)));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to retrieve filtered menu ids", e);
        }

    }

    private Set<String> filteredMavenPopupMenuIds() {
        final Properties properties = new Properties();
        try (InputStream is = PopupMenuFilterListener.class.getResourceAsStream(FILTERED_MENUS_PROPERTIES_FILENAME);) {
            properties.load(is);
            return newHashSet(Splitter.on(",").split(properties.getProperty(MAVEN_POPUP_MENU)));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to retrieve filtered menu ids", e);
        }
    }
}
