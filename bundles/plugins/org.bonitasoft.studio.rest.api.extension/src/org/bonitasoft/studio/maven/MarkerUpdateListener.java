/*******************************************************************************
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.inject.Provider;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class MarkerUpdateListener implements IResourceChangeListener {

    private IResource parentResource;
    private Map<Long, Long> markerIndex = new HashMap<>();

    public MarkerUpdateListener(CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> parentStore) {
        this.parentResource = parentStore.getResource();
        initializeIndex(parentStore);
    }

    private void initializeIndex(CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> parentStore) {
        parentStore.getChildren().stream()
                .forEach(fStore -> syncMarkerIndex(fStore.getProject()));
    }

    private void syncMarkerIndex(IProject project) {
        if (project.exists() && project.isAccessible()) {
            try {
                IMarker[] originalMarkers = project.findMarkers(IMarker.PROBLEM, true,
                        IResource.DEPTH_INFINITE);
                IMarker[] parentMarkers = parentResource.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ZERO);
                for (IMarker originalMarker : originalMarkers) {
                    for (IMarker parentMarker : parentMarkers) {
                        if (Objects.deepEquals(originalMarker.getAttributes(), parentMarker.getAttributes())) {
                            markerIndex.put(originalMarker.getId(), parentMarker.getId());
                        }
                    }
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        IMarkerDelta[] markerDeltas = event.findMarkerDeltas(IMarker.PROBLEM, true);
        for (IMarkerDelta markerDelta : markerDeltas) {
            if (isChildResource(parentResource, markerDelta.getResource())) {
                if (markerDelta.getKind() == IResourceDelta.ADDED) {
                    asWorkspaceJob(() -> addMarker(parentResource, markerDelta)).schedule();
                } else if (markerDelta.getKind() == IResourceDelta.REMOVED) {
                    asWorkspaceJob(() -> removeMarker(parentResource, markerDelta)).schedule();
                } else if (markerDelta.getKind() == IResourceDelta.CHANGED) {
                    asWorkspaceJob(() -> updateMarker(parentResource, markerDelta)).schedule();
                }
            }
        }
    }

    private boolean isChildResource(IResource parent, IResource resource) {
        return resource != null
                && !Objects.equals(parent, resource)
                && parent.getLocation() != null
                && resource.getLocation() != null
                && parent.getLocation().isPrefixOf(resource.getLocation());
    }

    private WorkspaceJob asWorkspaceJob(Provider<IStatus> modifyOperation) {
        return new WorkspaceJob("Update markers") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                return modifyOperation.get();
            }

        };
    }

    private IStatus updateMarker(IResource resource, IMarkerDelta markerDelta) {
        try {
            Long id = markerIndex.get(markerDelta.getId());
            if (id != null) {
                IMarker markerToUpdate = resource.findMarker(id);
                if (markerToUpdate != null && markerDelta.getAttributes() != null) {
                    for (Entry<String, Object> entry : markerDelta.getAttributes().entrySet()) {
                        markerToUpdate.setAttribute(entry.getKey(), entry.getValue());
                    }
                }
            }
        } catch (CoreException e) {
            return e.getStatus();
        }
        return Status.OK_STATUS;
    }

    private IStatus removeMarker(IResource resource, IMarkerDelta markerDelta) {
        try {
            Long id = markerIndex.get(markerDelta.getId());
            if (id != null) {
                IMarker markerToDelete = resource.findMarker(id);
                if (markerToDelete != null) {
                    markerToDelete.delete();
                    markerIndex.remove(markerDelta.getId());
                }
            }
        } catch (CoreException e) {
            return e.getStatus();
        }
        return Status.OK_STATUS;
    }

    private IStatus addMarker(IResource resource, IMarkerDelta markerDelta) {
        try {
            IMarker newMarker = resource.createMarker(markerDelta.getType());
            newMarker.setAttributes(markerDelta.getAttributes());
            markerIndex.put(markerDelta.getId(), newMarker.getId());
        } catch (CoreException e) {
            return e.getStatus();
        }
        return Status.OK_STATUS;
    }

}
