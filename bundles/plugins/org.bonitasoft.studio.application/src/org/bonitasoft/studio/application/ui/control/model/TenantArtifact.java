package org.bonitasoft.studio.application.ui.control.model;

import java.util.Map;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ITenantResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class TenantArtifact extends FileStoreArtifact implements ITenantResource {

    public TenantArtifact(RepositoryStore parent, IRepositoryFileStore fStore) {
        super(parent, fStore);
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        return ((ITenantResource)fStore).deploy(session, options, monitor);
    }
    
}
