package org.bonitasoft.studio.application.views;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.application.views.provider.UIDArtifactFilters;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jdt.ui.JavaElementComparator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class ProjectExplorerViewerComparator extends ViewerComparator {

    private ViewerComparator defaultSorter = new JavaElementComparator();
    private FileStoreFinder fileStoreFinder = new FileStoreFinder();

    private static Map<String, Integer> ORDER = new HashMap<>();
    static {
        ORDER.put("organizations", 1);
        ORDER.put("profiles", 2);
        ORDER.put("bdm", 3);
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
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        IProject project = currentRepository.getProject();
        IResource resource = Adapters.adapt(e1, IResource.class);
        if (resource != null && !Objects.equals(resource.getProject(), project)) {
            return defaultSorter.compare(viewer, e1, e2);
        }
        if (comparingWebPages(e1, e2)) {
            return compareWebPages(currentRepository, viewer, resource, Adapters.adapt(e2, IFolder.class));
        }
        return super.compare(viewer, e1, e2);
    }

    private int compareWebPages(Repository currentRepository, Viewer viewer, IResource webPage1, IResource webPage2) {
        Optional<WebPageFileStore> fileStore1 = fileStoreFinder.findFileStore(webPage1,
                currentRepository).filter(WebPageFileStore.class::isInstance).map(WebPageFileStore.class::cast);
        Optional<WebPageFileStore> fileStore2 = fileStoreFinder.findFileStore(webPage2,
                currentRepository).filter(WebPageFileStore.class::isInstance).map(WebPageFileStore.class::cast);;
        if (fileStore1.isPresent() && fileStore2.isPresent()) {
            return fileStore1.get().compareTo(fileStore2.get());
        }
        return super.compare(viewer, webPage1, webPage2);
    }

    private boolean comparingWebPages(Object e1, Object e2) {
        IFolder project1 = Adapters.adapt(e1, IFolder.class);
        IFolder project2 = Adapters.adapt(e2, IFolder.class);
        if (project1 != null && project2 != null) {
            return UIDArtifactFilters.isUIDArtifactFrom(project1, "web_page")
                    && UIDArtifactFilters.isUIDArtifactFrom(project2, "web_page");
        }
        return false;
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
