package org.bonitasoft.studio.application.views;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.application.views.provider.UIDArtifactFilters;
import org.bonitasoft.studio.common.repository.AbstractRepository;
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

public class ProjectExplorerViewerComparator extends JavaElementComparator {

    private ViewerComparator defaultSorter = new JavaElementComparator();
    private FileStoreFinder fileStoreFinder = new FileStoreFinder();

    public static Map<String, Integer> REPO_STORE_ORDER = new HashMap<>();
    static {
        REPO_STORE_ORDER.put("organizations", 1);
        REPO_STORE_ORDER.put("profiles", 2);
        REPO_STORE_ORDER.put("bdm", 3);
        REPO_STORE_ORDER.put("applications", 10);
        REPO_STORE_ORDER.put("diagrams", 11);
        REPO_STORE_ORDER.put("web_page", 12);
        REPO_STORE_ORDER.put("web_widgets", 13);
        REPO_STORE_ORDER.put("web_fragments", 14);
        REPO_STORE_ORDER.put("themes", 15);
        REPO_STORE_ORDER.put("restAPIExtensions", 16);
        REPO_STORE_ORDER.put("connectors-def", 17);
        REPO_STORE_ORDER.put("connectors-impl", 18);
        REPO_STORE_ORDER.put("connectors-conf", 19);
        REPO_STORE_ORDER.put("src-connectors", 20);
        REPO_STORE_ORDER.put("filters-def", 21);
        REPO_STORE_ORDER.put("filters-impl", 22);
        REPO_STORE_ORDER.put("filters-conf", 23);
        REPO_STORE_ORDER.put("src-filters", 24);
        REPO_STORE_ORDER.put("customTypes", 25);
        REPO_STORE_ORDER.put("src-customTypes", 26);
        REPO_STORE_ORDER.put("environements", 27);
        REPO_STORE_ORDER.put("src-groovy", 28);
        REPO_STORE_ORDER.put("attachments", 29);
        REPO_STORE_ORDER.put("lib", 30);
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
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

    private int compareWebPages(AbstractRepository currentRepository, Viewer viewer, IResource webPage1, IResource webPage2) {
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
       IResource resource = element instanceof IFolder ? (IFolder) element : null;
        if (store != null || resource != null) {
            resource = store != null ? store.getResource() : resource;
            Integer rank = REPO_STORE_ORDER.get(resource.getName());
            if (rank != null) {
                return -100 + rank;
            }
        }
        return super.category(element);
    }

}
