package org.bonitasoft.studio.application.views;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jdt.ui.JavaElementSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerSorter;

public class ProjectExplorerViewerComparator extends ViewerComparator {

    private ViewerSorter defaultSorter = new JavaElementSorter();
    private static Map<String, Integer> ORDER = new HashMap<>();
    static {
        ORDER.put("organizations", 1);
        ORDER.put("bdm", 2);
        ORDER.put("profiles", 9);
        ORDER.put("applications", 10);
        ORDER.put("diagrams", 11);
        ORDER.put("web_page", 12);
        ORDER.put("web_widgets", 13);
        ORDER.put("web_fragments", 14);
        ORDER.put("themes", 15);
        ORDER.put("restAPIExtensions", 16);
        ORDER.put("connectors-def", 17);
        ORDER.put("connectors-impl", 18);
        ORDER.put("connectors-conf", 19);
        ORDER.put("src-connectors", 20);
        ORDER.put("filters-def", 21);
        ORDER.put("filters-impl", 22);
        ORDER.put("filters-conf", 23);
        ORDER.put("src-filters", 24);
        ORDER.put("customTypes", 25);
        ORDER.put("src-customTypes", 26);
        ORDER.put("environements", 27);
        ORDER.put("src-groovy", 28);
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        IResource resource = Adapters.adapt(e1, IResource.class);
        if (resource != null && !Objects.equals(resource.getProject(), project)) {
            return defaultSorter.compare(viewer, e1, e2);
        }
        return super.compare(viewer, e1, e2);
    }

    @Override
    public int category(Object element) {
        IRepositoryStore store = element instanceof IRepositoryStore
                ? (IRepositoryStore) element
                : RepositoryManager.getInstance().getRepositoryStore(element).orElse(null);
        if (store != null) {
            Integer rank = ORDER.get(store.getResource().getName());
            if (rank != null) {
                return -100 + rank;
            }
        }
        return super.category(element);
    }

}
