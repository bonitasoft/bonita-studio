package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;

public class OrganizationArtifact extends TenantArtifact {

    public OrganizationArtifact(RepositoryStore parent, IRepositoryFileStore fStore) {
        super(parent, fStore);
    }

    public Organization getModel() {
        try {
            return (Organization) fStore.getContent();
        } catch (ReadFileStoreException e) {
           BonitaStudioLog.error(e);
           return null;
        }
    }

}
