package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.studio.common.repository.model.IBuildable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public class BuildableArtifact extends FileStoreArtifact implements IBuildable {

    public BuildableArtifact(Object parent, IRepositoryFileStore fStore) {
        super(parent, fStore);
    }

    @Override
    public void build(IPath buildPath, IProgressMonitor monitor) throws CoreException {
        ((IBuildable) fStore).build(buildPath, monitor);
    }

    @Override
    public String getName() {
        return fStore.getDisplayName();
    }

}
