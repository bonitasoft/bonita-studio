package org.bonitasoft.studio.application.views;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.application.views.provider.UIDArtifactFilters;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
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

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
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

    private int compareWebPages(IRepository currentRepository, Viewer viewer, IResource webPage1, IResource webPage2) {
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
            Integer rank = AbstractRepositoryStore.REPO_STORE_ORDER.get(resource.getName());
            if (rank != null) {
                return -100 + rank;
            }
        }
        return super.category(element);
    }

}
